package pantherbuddy.business.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pantherbuddy.business.controller.account.AccountBO;
import pantherbuddy.business.controller.exceptions.InvalidInputException;
import pantherbuddy.business.controller.exceptions.UserAlreadyExistsException;
import pantherbuddy.business.model.UserModel;

/**
 * Action class for Registration.
 *
 * @author ALLAN
 *
 */
public class RegistrationAction implements Action {

	/**
	 * An instance of {@link UserModel} class.
	 */
	public UserModel user;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String phone = request.getParameter("phone");

		if (fname == null || fname.isEmpty()) {
			request.setAttribute("error", "Invalid first name entered");
			return "/registration.jsp";
		}
		if (lname == null || lname.isEmpty()) {
			request.setAttribute("error", "Invalid last name entered");
			return "/registration.jsp";
		}
		if (email == null || email.isEmpty()) {
			request.setAttribute("error", "Invalid email entered");
			return "/registration.jsp";
		}
		if (phone == null || phone.isEmpty()) {
			request.setAttribute("error", "Invalid phone number entered");
			return "/registration.jsp";
		}

		user = new UserModel();
		user.setEmailId(email);
		user.setFname(fname);
		user.setLname(lname);
		try {
			user.setPhoneNumber(Long.valueOf(phone));
		} catch (Exception e) {
			request.setAttribute("error", "Invalid phone number entered");
			return "/registration.jsp";
		}

		AccountBO accountBO = new AccountBO();
		try {
			accountBO.registerUser(user);
		} catch (UserAlreadyExistsException | InvalidInputException e) {
			request.setAttribute(
					"error",
					"An error occured during registration. Please contact the admistrators for support");
			return "/registration.jsp";
		}
		request.setAttribute("email",
				"An email with the password has been sent to you registered email.");
		return "/login.jsp";
	}

}
