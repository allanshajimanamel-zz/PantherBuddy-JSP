package pantherbuddy.business.model;

import java.util.Date;

/**
 * This is the model class to hold the data related to the user for views
 * showing or accessing user data.
 *
 * @author ALLAN
 *
 */
public class UserModel {

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
	private Long phoneNumber;

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
	 * The field holds the value for the retyped new password when the user is
	 * changing his password in the system.
	 */
	private String password_retype;

	/**
	 * The field holds the value for the new password when the user is changing
	 * his password in the system.
	 */
	private String newPassword;

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
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(Long phoneNumber) {
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

	/**
	 * @return the password_retype
	 */
	public String getPassword_retype() {
		return password_retype;
	}

	/**
	 * @param password_retype
	 *            the password_retype to set
	 */
	public void setPassword_retype(String password_retype) {
		this.password_retype = password_retype;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
