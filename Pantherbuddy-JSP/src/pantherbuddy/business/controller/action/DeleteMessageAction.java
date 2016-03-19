package pantherbuddy.business.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pantherbuddy.business.controller.message.MessageBO;
import pantherbuddy.business.model.MessageModel;

/**
 * Action class for Delete Message
 *
 * @author ALLAN
 *
 */
public class DeleteMessageAction implements Action {

	/**
	 * The id of the message to delete.
	 */
	public Integer messageId;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		Integer id = Integer.parseInt(request.getParameter("msgid"));

		try {
			MessageBO messageBO = new MessageBO();
			boolean result = messageBO.deleteMessage(id);
			if(!result){
				request.setAttribute("error",
						"Could not delete message");
			}
		} catch (Exception e) {
			request.setAttribute("error",
					"Could not delete message");
		}
		List<MessageModel> messages = new MessageBO().getMessagesToShow(0);
		request.getSession().removeAttribute("messages");
		request.getSession().setAttribute("messages", messages);
		return "viewmessages.jsp"; // Redirect to home page.
	}

}
