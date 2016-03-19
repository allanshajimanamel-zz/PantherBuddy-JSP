package pantherbuddy.business.view;

/**
 * Represents the view for the change password use case.
 *
 * @author ALLAN
 *
 */
public class ChangePasswordForm implements Form {

	/**
	 * The old password of the user
	 */
	public String password;

	/**
	 * The new password of the user.
	 */
	public String newPassword;

	/**
	 * The new password retyrped by the user.
	 */
	public String password_retype;

	@Override
	public void loadPage() {
		// TODO Auto-generated method
	}

	@Override
	public void submitPageDataAndAction() {
		// TODO Auto-generated method
	}

}
