package pantherbuddy.business.controller.exceptions;

/**
 * Exception class for login failure.
 *
 * @author ALLAN
 *
 */
public class LoginException extends Exception {

	/**
	 * The message to be shown for the exception.
	 */
	private static final String MESSAGE = "The username or password provided is invalid";

	/**
	 * Constructor
	 */
	public LoginException() {
		super(MESSAGE);
		// TODO Auto-generated constructor stub
	}
}
