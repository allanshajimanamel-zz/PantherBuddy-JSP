package pantherbuddy.business.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pantherbuddy.business.controller.account.AccountBO;
import pantherbuddy.business.controller.exceptions.UserNotFoundException;

/**
 * Action class for Recover Password.
 *
 * @author ALLAN
 *
 */
public class RecoverPasswordAction implements Action {

	/**
	 * email id for which to recover password for.
	 */
	public String email;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		email = request.getParameter("email");

		if(email == null || email.equals("")){
			request.setAttribute("error", "Invalid email entered");
			return "/recoverpassword.jsp";
		}

		AccountBO accountBO = new AccountBO();
		try {
			accountBO.recoverPassword(email);
		} catch (UserNotFoundException e) {
			request.setAttribute("error", e.getMessage());
			return "/recoverpassword.jsp";
		}
		request.setAttribute("email",
				"The password has been sent to your email address");
		return "/recoverpassword.jsp";
	}

}
