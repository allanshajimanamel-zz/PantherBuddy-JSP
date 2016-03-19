package pantherbuddy.datastore.entity;

import java.util.Date;

/**
 * The class represents the message; entity.
 *
 * @author ALLAN
 *
 */
public class Message {

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
	 * @return the messageId
	 */
	public Integer getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId
	 *            the messageId to set
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
	 * @param userId
	 *            the userId to set
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
	 * @param message
	 *            the message to set
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
	 * @param messageDate
	 *            the messageDate to set
	 */
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

}
