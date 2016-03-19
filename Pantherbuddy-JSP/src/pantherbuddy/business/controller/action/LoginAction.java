package pantherbuddy.business.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pantherbuddy.business.controller.account.AccountBO;
import pantherbuddy.business.controller.exceptions.LoginException;
import pantherbuddy.business.controller.message.MessageBO;
import pantherbuddy.business.model.MessageModel;
import pantherbuddy.business.model.UserModel;

/**
 * Action class for Login.
 *
 * @author ALLAN
 *
 */
public class LoginAction implements Action {

	/**
	 * An instance of {@link UserModel} class.
	 */
	public UserModel user;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Integer userid = null;

		user = new UserModel();
		user.setEmailId(email);
		user.setPassword(password);

		try {
			AccountBO accountBO = new AccountBO();
			userid = accountBO.loginUser(user);
		} catch (LoginException le) {
			request.setAttribute("error",
					"Unknown username/password. Please retry.");
			return "/login.jsp"; // Go back to redisplay login form with error.
		}
		List<MessageModel> messages = new MessageBO().getMessagesToShow(0);
		request.getSession().setAttribute("messages", messages);
		return userid.toString();

	}
}