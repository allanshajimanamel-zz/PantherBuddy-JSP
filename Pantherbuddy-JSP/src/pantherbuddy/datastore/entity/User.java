package pantherbuddy.datastore.entity;

import java.util.Date;

/**
 * The class represents the user entity.
 *
 * @author ALLAN
 *
 */
public class User {

	/**
	 * The unique user identifier. Represents the primary key of the user
	 * entity.
	 */
	private Integer userId;

	/**
	 * First name of the user.
	 */
	private String fname;

	/**
	 * Last name of the user
	 */
	private String lname;

	/**
	 * The password used by the user
	 */
	private String password;

	/**
	 * The phone number of the user.
	 */
	private Integer phoneNumber;

	/**
	 * The email id of the user. Used as the username for login.
	 */
	private String emailId;

	/**
	 * Is a boolean variable that represents if a user is active in the system
	 * or not. A user needs to be active = true to access the system. A user
	 * activates himself the first time he logs into the pantherbuddy system.
	 */
	private boolean status;

	/**
	 * The date after which the user can be active on the PantherBuddy system.
	 */
	private Date activateTimestamp;

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
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phoneNumber
	 */
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the activateTimestamp
	 */
	public Date getActivateTimestamp() {
		return activateTimestamp;
	}

	/**
	 * @param activateTimestamp
	 *            the activateTimestamp to set
	 */
	public void setActivateTimestamp(Date activateTimestamp) {
		this.activateTimestamp = activateTimestamp;
	}

}
