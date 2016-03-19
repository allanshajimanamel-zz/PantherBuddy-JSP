package pantherbuddy.business.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Is the interface to excute the action requested by the user. Every action
 * class should implement this interface. The Action follows the Command
 * pattern with the strategy pattern.
 *
 * @author ALLAN
 *
 */
public interface Action {

	/**
	 * The method to call to execute the said command logic. The execution
	 * returns the string path of the page to navigate to.
	 *
	 * @param request
	 *            An instance of {@link HttpServletRequest}
	 * @param response
	 *            An instance of {@link HttpServletResponse}
	 * @return An instance of {@link String} that contains the path to the page
	 *         to navigate.
	 * @throws Exception
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}
