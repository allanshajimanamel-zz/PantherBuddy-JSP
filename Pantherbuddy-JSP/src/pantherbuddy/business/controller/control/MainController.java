package pantherbuddy.business.controller.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pantherbuddy.business.controller.action.Action;
import pantherbuddy.business.controller.action.ActionFactory;
import pantherbuddy.business.controller.action.LoginAction;
import pantherbuddy.business.controller.action.LogoutAction;

/**
 * This is the entry point into the controller of the MVC architecture. It
 * follows the Front Controller pattern (which is a specialized kind of Mediator
 * pattern). It provides a centralized entry point of all requests. It will
 * create the Model based on information available by the request
 *
 * @author ALLAN
 *
 */
public class MainController extends HttpServlet {

	/**
	 * Generated Serial version uid.
	 */
	private static final long serialVersionUID = -7938187110346251972L;

	/**
	 * Used to service the get request
	 *
	 * @param request
	 *            An instance of {@link HttpServletRequest}
	 * @param response
	 *            An instance of {@link HttpServletResponse}
	 * @throws ServletException
	 */
	@Override
	public void doGet(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
					throws ServletException {
		doPost(request, response);
	}

	/**
	 * Used to service the post request
	 *
	 * @param request
	 *            An instance of {@link HttpServletRequest}
	 * @param response
	 *            An instance of {@link HttpServletResponse}
	 * @throws ServletException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		String button = null;
		request.getSession().removeAttribute("error");

		try {

			button = request.getParameter("Lbutton");
			if (button != null && "Register".equals(button)) {
				response.sendRedirect("registration.jsp");
				return;
			} else if (button != null && "Recover Password".equals(button)) {
				response.sendRedirect("recoverpassword.jsp");
				return;
			}

			button = request.getParameter("Rbutton");
			if (button != null && "Login".equals(button)) {
				response.sendRedirect("login.jsp");
				return;
			}

			button = request.getParameter("homepage");
			if (button != null && "Home".equals(button)) {
				response.sendRedirect("viewmessages.jsp");
				return;
			}

			Action action = ActionFactory.getAction(request);

			if (action == null) {
				response.sendRedirect("login.jsp");
			}

			String view = action.execute(request, response);

			if (LoginAction.class.isInstance(action)
					&& request.getAttribute("error") == null) {
				Integer userid = Integer.parseInt(view);
				HttpSession session = request.getSession();
				session.setAttribute("userid", userid); // Login user.
				Cookie userName = new Cookie("userid", userid.toString());
				response.addCookie(userName);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30 * 60);
				// Get the encoded URL string
				String encodedURL = response
						.encodeRedirectURL("viewmessages.jsp");
				response.sendRedirect(encodedURL);
			} else if (LogoutAction.class.isInstance(action)) {
				response.sendRedirect(view);
			} else if (request.getAttribute("error") == null) {
				request.getRequestDispatcher(view).forward(request, response);
			} else {
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher(view);
				rd.include(request, response);
			}
		}
		catch(NullPointerException e){
			e.printStackTrace();
			try {
				response.sendRedirect("login.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
