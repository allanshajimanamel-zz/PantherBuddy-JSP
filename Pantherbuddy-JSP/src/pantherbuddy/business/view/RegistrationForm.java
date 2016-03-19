package pantherbuddy.business.view;

/**
 * Represents the view for the register user use case.
 *
 * @author ALLAN
 *
 */
public class RegistrationForm implements Form {

	/**
	 * The email id of the user
	 */
	public String emailId;

	/**
	 * The last name of the user
	 */
	public String lname;

	/**
	 * The first name of the user
	 */
	public String fname;

	/**
	 * The phone number of the user
	 */
	public Integer phoneNumber;

	@Override
	public void loadPage() {
		// TODO Auto-generated method
	}

	@Override
	public void submitPageDataAndAction() {
		// TODO Auto-generated method
	}

}
