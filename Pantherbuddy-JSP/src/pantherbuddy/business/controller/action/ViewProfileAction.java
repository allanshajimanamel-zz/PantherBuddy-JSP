package pantherbuddy.business.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pantherbuddy.business.controller.account.AccountBO;
import pantherbuddy.business.controller.exceptions.InvalidInputException;
import pantherbuddy.business.model.UserModel;

/**
 * Represents the view for View Profile.
 *
 * @author ALLAN
 *
 */
public class ViewProfileAction implements Action {

	/**
	 * The userid of user of whom we want to view the profile.
	 */
	public Integer userId;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getParameter("userid") != null) {
			userId = Integer.parseInt(request.getParameter("userid"));
		} else {
			userId = (Integer) request.getSession().getAttribute("userid");
		}
		AccountBO accountBO = new AccountBO();
		try {
			UserModel model = accountBO.getUser(userId);
			request.getSession().setAttribute("user", model);
		} catch (InvalidInputException e) {
			e.printStackTrace();
			request.setAttribute("error", "Could not retreive the profile data for user.");
		}
		return "/viewprofile.jsp";
	}

}
