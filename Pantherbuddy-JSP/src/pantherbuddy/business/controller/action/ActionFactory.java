package pantherbuddy.business.controller.action;

import javax.servlet.http.HttpServletRequest;

/**
 * The class follows the factory pattern and is used to get the specific
 * implementation of the {@link Action} class.
 *
 * @author ALLAN
 *
 */
public class ActionFactory {



	/**
	 * The method returns an implementation of the {@link Action} interface
	 * based on the information provided by the request.
	 *
	 * @param request
	 *            An instance of {@link HttpServletRequest}
	 * @return An instance of {@link Action}
	 */
	public static Action getAction(HttpServletRequest request) {
		String action = request.getParameter("action");
		switch(action){
		case "login" : return new LoginAction();
		case "registration" : return new RegistrationAction();
		case "recoverpassword" : return new RecoverPasswordAction();
		case "deletemessage" : return new DeleteMessageAction();
		case "postmessage" : return new PostMessageAction();
		case "viewprofile" : return new ViewProfileAction();
		case "changepass" : return new ChangePasswordAction();
		case "homepage" : return new ViewMessagesAction();
		case "logout" : return new LogoutAction();
		}
		return null;
	}

}
