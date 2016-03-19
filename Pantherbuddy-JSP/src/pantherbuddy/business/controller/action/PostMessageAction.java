package pantherbuddy.business.controller.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pantherbuddy.business.controller.exceptions.InvalidInputException;
import pantherbuddy.business.controller.message.MessageBO;
import pantherbuddy.business.model.MessageModel;

/**
 * Action class for Post Message.
 *
 * @author ALLAN
 *
 */
public class PostMessageAction implements Action {

	/**
	 * An instance of {@link MessageModel} class.
	 */
	public MessageModel message;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Integer userid = (Integer) request.getSession().getAttribute("userid");
		String message = request.getParameter("message");
		this.message = new MessageModel();
		this.message.setMessage(message);
		this.message.setUserId(userid);

		MessageBO bo = new MessageBO();
		try {
			bo.saveMessage(this.message);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException | InvalidInputException e) {
			e.printStackTrace();
			request.setAttribute("error", "Could not save the message. Please check the message and try again.");
		}

		List<MessageModel> messages = new MessageBO().getMessagesToShow(0);
		request.getSession().setAttribute("messages", messages);

		return "/viewmessages.jsp";
	}

}
