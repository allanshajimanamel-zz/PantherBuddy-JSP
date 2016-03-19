package testcases;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import pantherbuddy.business.controller.account.AccountBO;
import pantherbuddy.business.controller.exceptions.InvalidInputException;
import pantherbuddy.business.controller.exceptions.LoginException;
import pantherbuddy.business.controller.exceptions.UserAlreadyExistsException;
import pantherbuddy.business.controller.exceptions.UserNotFoundException;
import pantherbuddy.business.controller.utility.UpdateColumn;
import pantherbuddy.business.model.UserModel;
import pantherbuddy.datastore.dao.UserDAO;

public class Test_01_UnitTest_AccountBO {

	@Test
	public void testLoginUserPositive() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	LoginException {
		UserModel model = new UserModel();
		model.setEmailId("xyz@fiu.edu");
		model.setPassword("dddd");

		AccountBO accountBO = Mockito.mock(AccountBO.class);
		when(accountBO.checkUserExists(any())).thenCallRealMethod();
		when(accountBO.loginUser(model)).thenCallRealMethod();

		Integer id = accountBO.loginUser(model);

		Assert.assertTrue(id == 1);
	}

	@Test(expected = LoginException.class)
	public void testLoginUserNegative() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	LoginException {
		UserModel model = new UserModel();
		model.setEmailId("xyz@fiu.edu");
		model.setPassword("ddss");

		AccountBO accountBO = Mockito.mock(AccountBO.class);
		when(accountBO.checkUserExists(any())).thenReturn(getUserModel());
		when(accountBO.loginUser(model)).thenCallRealMethod();
		accountBO.loginUser(model);
	}

	@Test
	public void testActivateUserPositive() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException {
		UserModel model = new UserModel();
		model.setEmailId("test@gmail.com");
		model.setPassword("dddd");
		model.setUserId(2);

		AccountBO accountBO = Mockito.mock(AccountBO.class);
		when(accountBO.activateUser(model)).thenCallRealMethod();

		boolean success = accountBO.activateUser(model);

		UserDAO dao = new UserDAO();
		UserModel userModel = dao.getUser(2);

		Assert.assertTrue(success);
		Assert.assertTrue(userModel.isStatus());
	}

	@Test
	public void testRegisterUserPositive() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	UserAlreadyExistsException, InvalidInputException,
	MessagingException, NamingException {
		UserModel model = new UserModel();
		model.setEmailId("xyz1@fiu.edu");
		model.setFname("Abhinav");
		model.setLname("Dutt");
		model.setPhoneNumber(1234567890L);

		AccountBO accountBO = Mockito.mock(AccountBO.class);
		when(accountBO.checkUserExists(any(UserModel.class))).thenReturn(null);
		Mockito.when(accountBO.registerUser(any())).thenCallRealMethod();
		Mockito.doNothing().when(accountBO).sendMail(any(), any());

		boolean success = accountBO.registerUser(model);
		Assert.assertTrue(success);
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void testRegisterUserNegative() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	UserAlreadyExistsException, InvalidInputException,
	MessagingException, NamingException {
		UserModel model = new UserModel();
		model.setEmailId("xyz2@fiu.edu");
		model.setFname("Abdur");
		model.setLname("Rehman");
		model.setPhoneNumber(1224567890L);

		AccountBO accountBO = Mockito.mock(AccountBO.class);
		when(accountBO.checkUserExists(any(UserModel.class))).thenReturn(model);
		Mockito.when(accountBO.registerUser(any())).thenCallRealMethod();
		Mockito.doNothing().when(accountBO).sendMail(any(), any());
		accountBO.registerUser(model);
	}

	@Test(expected = InvalidInputException.class)
	public void testRegisterUserNegative2() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	UserAlreadyExistsException, InvalidInputException,
	MessagingException, NamingException {

		UserModel model = new UserModel();
		model.setEmailId("xyz1@fiu.edu");
		model.setFname("Abhinav");
		model.setLname("Dutt");
		model.setPhoneNumber(1234567890L);

		AccountBO accountBO = Mockito.mock(AccountBO.class);
		when(accountBO.checkUserExists(any(UserModel.class))).thenReturn(null);
		Mockito.when(accountBO.registerUser(any())).thenCallRealMethod();
		Mockito.doThrow(MessagingException.class).when(accountBO).sendMail(any(), any());
		accountBO.registerUser(model);
	}

	@Test
	public void testGetUserPositive() throws InvalidInputException {
		AccountBO accountBO = Mockito.mock(AccountBO.class);
		Mockito.when(accountBO.getUser(any())).thenCallRealMethod();
		UserModel model = accountBO.getUser(1);
		Assert.assertTrue(model.getUserId() == 1);
	}

	@Test(expected = InvalidInputException.class)
	public void testGetUserNegative() throws InvalidInputException {
		AccountBO accountBO = Mockito.mock(AccountBO.class);
		Mockito.when(accountBO.getUser(any())).thenCallRealMethod();
		accountBO.getUser(3);
	}

	@Test
	public void testRecoverPasswordPositive() throws UserNotFoundException,
	MessagingException, NamingException {
		String email = "xyz@fiu.edu";

		AccountBO accountBO = Mockito.mock(AccountBO.class);
		Mockito.when(accountBO.recoverPassword(any())).thenCallRealMethod();
		Mockito.doNothing().when(accountBO).sendMail(any(), any());
		boolean success = accountBO.recoverPassword(email);
		Assert.assertTrue(success);
	}

	@Test(expected = UserNotFoundException.class)
	public void testRecoverPasswordNegative() throws MessagingException,
	NamingException, UserNotFoundException {
		String email = "notexist@fiu.edu";

		AccountBO accountBO = Mockito.mock(AccountBO.class);
		Mockito.when(accountBO.recoverPassword(any())).thenCallRealMethod();
		Mockito.doNothing().when(accountBO).sendMail(any(), any());
		boolean success = accountBO.recoverPassword(email);
		Assert.assertTrue(success);
	}

	@Test
	public void testUpdateUserPositive() throws InvalidInputException {
		UserModel model = new UserModel();
		model.setUserId(1);
		model.setPassword("dddd");
		model.setNewPassword("ssss");
		model.setPassword_retype("ssss");

		AccountBO accountBO = Mockito.mock(AccountBO.class);
		when(accountBO.updateUser(any())).thenCallRealMethod();
		boolean success = accountBO.updateUser(model);
		Assert.assertTrue(success);
	}

	@Test(expected = InvalidInputException.class)
	public void testUpdateUserNegative() throws InvalidInputException {
		UserModel model = new UserModel();
		model.setUserId(1);
		model.setPassword("ddss");
		model.setNewPassword("ssss");
		model.setPassword_retype("ssss");

		AccountBO accountBO = Mockito.mock(AccountBO.class);
		when(accountBO.updateUser(any())).thenCallRealMethod();
		accountBO.updateUser(model);
	}

	private UserModel getUserModel() {
		UserModel model = new UserModel();
		model.setActivateTimestamp(new Date());
		model.setEmailId("xyz@fiu.edu");
		model.setFname("Allan");
		model.setLname("Shaji");
		model.setPassword("8oLsJNn7V7UMU9+tRhQFGg==");
		model.setPhoneNumber(7894561L);
		model.setStatus(false);
		model.setUserId(1);
		return model;
	}

	@After
	public void tearDown() throws Exception {

		UserDAO dao = new UserDAO();

		UserModel model1 = new UserModel();
		model1.setEmailId("xyz1@fiu.edu");
		model1 = dao.getUserByEmail(model1);
		if (model1 != null)
			dao.deleteUser(model1.getUserId());

		UserModel model2 = new UserModel();
		model2.setUserId(1);
		model2.setPassword("8oLsJNn7V7UMU9+tRhQFGg==");
		dao.updateUser(model2, UpdateColumn.PASSWORD);

		Connection connection = dao.getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE pantherbuddy.user SET status = FALSE WHERE userid = 2;");
		preparedStatement.executeUpdate();
		connection.close();
	}
}
