package pantherbuddy.business.controller.account;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.naming.NamingException;

import pantherbuddy.business.controller.exceptions.InvalidInputException;
import pantherbuddy.business.controller.exceptions.LoginException;
import pantherbuddy.business.controller.exceptions.UserAlreadyExistsException;
import pantherbuddy.business.controller.exceptions.UserNotFoundException;
import pantherbuddy.business.controller.utility.AESEncryption;
import pantherbuddy.business.controller.utility.EmailService;
import pantherbuddy.business.controller.utility.PasswordGenerator;
import pantherbuddy.business.controller.utility.UpdateColumn;
import pantherbuddy.business.model.UserModel;
import pantherbuddy.datastore.dao.UserDAO;

/**
 * The business object class that implements the logic for all operations
 * related to the account user.
 *
 * @author ALLAN
 *
 */
public class AccountBO {

	/**
	 * The method checks if the user with provided data already exusts or not.
	 *
	 * @param user
	 *            An instance of {@link UserModel}
	 * @return <code>true</code> if the user with simmilar data already exists
	 *         else <code>false</code>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public UserModel checkUserExists(UserModel user)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		UserModel model = null;
		UserDAO dao = new UserDAO();
		model = dao.getUserByEmail(user);
		return model;
	}

	/**
	 * Checks for SQL injection in the entered data.
	 *
	 * @param user
	 *            An instance of {@link UserModel}
	 * @throws InvalidInputException
	 *             Exception thrown if invalid data was found in input by user.
	 */
	private void checkSQLInjection(UserModel user) throws InvalidInputException {
		String regexString = "/((\\%3D)|(=))[^\\n]*((\\%27)|(\\')|(\\-\\-)|(\\%3B)|(;))/i";
		boolean badEmail = false;
		boolean badPassword = false;
		if (user.getEmailId() != null) {
			badEmail = user.getEmailId().matches(regexString);
		}
		if (user.getPassword() != null) {
			badPassword = user.getPassword().matches(regexString);
		}
		if (badEmail || badPassword) {
			throw new InvalidInputException();
		}
	}

	/**
	 * The method is used to activate a user on his first login.
	 *
	 * @param user
	 *            An instance of {@link UserModel}
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public boolean activateUser(UserModel user) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		dao.updateUser(user, UpdateColumn.STATUS);
		return true;
	}

	public Integer loginUser(UserModel user) throws LoginException {
		if (user.getEmailId() == null || user.getPassword() == null
				|| user.getEmailId().equals("")
				|| user.getPassword().equals("")) {
			throw new LoginException();
		}
		UserModel model = null;
		try {
			checkSQLInjection(user);
			model = checkUserExists(user);
			if (model == null) {
				throw new LoginException();
			}
			String decrypPassword = AESEncryption.decrypt(model.getPassword());

			if (!user.getPassword().equals(decrypPassword)) {
				throw new LoginException();
			}

			if (!model.isStatus()) {
				activateUser(model);
			}

			return model.getUserId();

		} catch (InvalidInputException | InstantiationException
				| IllegalAccessException | ClassNotFoundException
				| SQLException | InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			throw new LoginException();
		}
	}

	public boolean registerUser(UserModel user)
			throws UserAlreadyExistsException, InvalidInputException {
		checkSQLInjection(user);
		UserDAO dao = new UserDAO();
		try {
			UserModel model = checkUserExists(user);
			if (model != null) {
				throw new UserAlreadyExistsException();
			}
			String password = PasswordGenerator.generatePassword();
			String encrypString = AESEncryption.encrypt(password);
			user.setPassword(encrypString);
			dao.createUser(user);
			sendMail(user.getEmailId(), password);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | InstantiationException
				| IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			e.printStackTrace();
			throw new InvalidInputException();
		} catch (MessagingException | NamingException e) {
			e.printStackTrace();
			try {
				UserModel model = dao.getUserByEmail(user);
				dao.deleteUser(model.getUserId());
				throw new InvalidInputException();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}

	public UserModel getUser(Integer id) throws InvalidInputException {
		UserDAO dao = new UserDAO();
		UserModel model = null;
		try {
			model = dao.getUser(id);
			if(model == null){
				throw new InvalidInputException();
			}

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			throw new InvalidInputException();
		}
		return model;
	}

	/**
	 * The method is used to recover a users password.
	 *
	 * @param email
	 *            Email for which the password is to be recovered.
	 * @throws UserNotFoundException
	 */
	public boolean recoverPassword(String email) throws UserNotFoundException {
		UserModel user = new UserModel();
		user.setEmailId(email);
		try {
			checkSQLInjection(user);
			UserDAO dao = new UserDAO();
			user = dao.getUserByEmail(user);
			if (user == null) {
				throw new UserNotFoundException();
			}
			String password = AESEncryption.decrypt(user.getPassword());
			sendMail(email, password);
		} catch (InvalidInputException | InstantiationException
				| IllegalAccessException | ClassNotFoundException
				| SQLException | InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | MessagingException | NamingException e) {
			throw new UserNotFoundException();
		}
		return true;
	}

	public boolean updateUser(UserModel user) throws InvalidInputException {
		UserDAO dao = new UserDAO();
		try {
			UserModel model = dao.getUser(user.getUserId());

			String decryPassword = AESEncryption.decrypt(model.getPassword());

			if (!user.getPassword().equals(decryPassword)) {
				throw new InvalidInputException();
			} else if (!user.getNewPassword().equals(user.getPassword_retype())) {
				throw new InvalidInputException();
			}

			user.setPassword(user.getNewPassword());

			checkSQLInjection(user);

			user.setPassword(AESEncryption.encrypt(user.getNewPassword()));

			dao.updateUser(user, UpdateColumn.PASSWORD);

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException | InvalidKeyException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			throw new InvalidInputException();
		}
		return true;
	}

	public void sendMail(String email, String password)
			throws MessagingException, NamingException {
		EmailService.sendMail(email, password);
	}

}
