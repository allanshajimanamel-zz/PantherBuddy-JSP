package pantherbuddy.business.controller.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Action class for Logout.
 *
 * @author ALLAN
 *
 */
public class LogoutAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		//invalidate the session if exists
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}
		//no encoding because we have invalidated the session
		return "login.jsp";
	}

}
