package pantherbuddy.business.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pantherbuddy.business.controller.account.AccountBO;
import pantherbuddy.business.model.UserModel;

/**
 * Action class for Change Password
 *
 * @author ALLAN
 *
 */
public class ChangePasswordAction implements Action {

	/**
	 * An instance of {@link UserModel} class.
	 */
	public UserModel user;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String oldPassword = request.getParameter("password_old");
		String newPassword = request.getParameter("password_new");
		String password_retype = request.getParameter("password_retype");

		if(oldPassword == null || oldPassword.equals("")){
			request.setAttribute("error",
					"Please enter the current password.");
			return "/viewprofile.jsp";
		}

		if(newPassword == null || newPassword.equals("")){
			request.setAttribute("error",
					"Please enter the new password.");
			return "/viewprofile.jsp";
		}

		if(password_retype == null || password_retype.equals("")){
			request.setAttribute("error",
					"Please re-enter the new password.");
			return "/viewprofile.jsp";
		}

		Integer userId = (Integer) request.getSession().getAttribute("userid");

		user = new UserModel();
		user.setPassword(oldPassword);
		user.setNewPassword(newPassword);
		user.setPassword_retype(password_retype);
		user.setUserId(userId);

		try {
			AccountBO accountBO = new AccountBO();
			accountBO.updateUser(user);
		} catch (Exception e) {
			request.setAttribute("error",
					"Incorrect data entered. Please check the data and try again.");
			return "/viewprofile.jsp";
		}
		return "/viewprofile.jsp";
	}

}
