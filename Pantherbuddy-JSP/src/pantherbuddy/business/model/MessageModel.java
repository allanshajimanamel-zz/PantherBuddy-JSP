package pantherbuddy.business.model;

import java.util.Date;
import java.util.List;

/**
 * This is the model class to hold the data related to the messages for views
 * showing or accessing messages.
 *
 * @author ALLAN
 *
 */
public class MessageModel {

	/**
	 * The unique mesage identifier. Represents the primary key of the message
	 * entity.
	 */
	private Integer messageId;

	/**
	 * The unique user identifier. Represents the prikary key of the user
	 * entity. It signifis the id of the user who posted the message.
	 */
	private Integer userId;

	/**
	 * The message in the message entity.
	 */
	private String Message;

	/**
	 * The date when the message was posted.
	 */
	private Date messageDate;

	/**
	 * Is used for pagination on the view messages view. Represents the starting
	 * number of the range of messages displayed on the page.
	 */
	private Integer rangeFrom;

	/**
	 * full name of the user who posted the message.
	 */
	private String username;

	/**
	 * The messages to be shown on the view messages view.
	 */
	private List<MessageModel> messages;

	/**
	 * @return the messageId
	 */
	public Integer getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		Message = message;
	}

	/**
	 * @return the messageDate
	 */
	public Date getMessageDate() {
		return messageDate;
	}

	/**
	 * @param messageDate the messageDate to set
	 */
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	/**
	 * @return the rangeFrom
	 */
	public Integer getRangeFrom() {
		return rangeFrom;
	}

	/**
	 * @param rangeFrom the rangeFrom to set
	 */
	public void setRangeFrom(Integer rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	/**
	 * @return the messages
	 */
	public List<MessageModel> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<MessageModel> messages) {
		this.messages = messages;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
