package pantherbuddy.business.view;

/**
 * Represents the view for the view profile use case.
 *
 * @author ALLAN
 *
 */
public class ViewProfileForm implements Form {

	/**
	 * The last name of the user
	 */
	public String lname;

	/**
	 * The email id of the user
	 */
	public String emailId;

	/**
	 * The first name of the user.
	 */
	public String fname;

	/**
	 * The phone number of the user.
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
