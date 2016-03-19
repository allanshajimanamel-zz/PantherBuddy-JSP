package pantherbuddy.business.controller.message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import pantherbuddy.business.controller.exceptions.InvalidInputException;
import pantherbuddy.business.model.MessageModel;
import pantherbuddy.datastore.dao.MessageDAO;

/**
 * The business object class that implements the logic for all operations
 * related to the messages.
 *
 * @author ALLAN
 *
 */
public class MessageBO {

	/**
	 * The method checks for Cross Site Scripting in the input {@link String}
	 *
	 * @param message
	 *            An instance of {@link String} representing the input message.
	 * @throws InvalidInputException
	 *             Exception thrown if invalid input was found.
	 */
	private String checkXSS(String message) throws InvalidInputException {
		if (message != null) {
			// Avoid null characters
			message = message.replaceAll("", "");

			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			message = scriptPattern.matcher(message).replaceAll("");

			// Avoid anything in a src='...' type of expression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			message = scriptPattern.matcher(message).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			message = scriptPattern.matcher(message).replaceAll("");

			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			message = scriptPattern.matcher(message).replaceAll("");

			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			message = scriptPattern.matcher(message).replaceAll("");

			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			message = scriptPattern.matcher(message).replaceAll("");

			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			message = scriptPattern.matcher(message).replaceAll("");

			// Avoid javascript:... expressions
			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			message = scriptPattern.matcher(message).replaceAll("");

			// Avoid vbscript:... expressions
			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			message = scriptPattern.matcher(message).replaceAll("");

			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			message = scriptPattern.matcher(message).replaceAll("");
		}
		return message;
	}

	public boolean saveMessage(MessageModel message)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, InvalidInputException {
		String checkedMessage = checkXSS(message.getMessage());
		if(checkedMessage.equals("")){
			throw new InvalidInputException();
		}
		if(checkedMessage.length()>499){
			checkedMessage = checkedMessage.substring(0, 499);
		}
		message.setMessage(checkedMessage);
		MessageDAO dao = new MessageDAO();
		dao.createMessage(message);
		return true;
	}

	public List<MessageModel> getMessagesToShow(Integer rangeFrom) {
		MessageDAO dao = new MessageDAO();
		List<MessageModel> models = new ArrayList<>();
		try {
			if(rangeFrom == null){
				rangeFrom = 0;
			}
			models = dao.getMessages(rangeFrom);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return models;
	}

	public boolean deleteMessage(Integer id) {
		MessageDAO dao = new MessageDAO();
		boolean result = true;
		try {
			dao.deleteMessage(id);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

}
