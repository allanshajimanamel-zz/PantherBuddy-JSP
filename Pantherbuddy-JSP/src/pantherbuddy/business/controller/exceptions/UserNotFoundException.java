package pantherbuddy.business.controller.exceptions;

/**
 * Exception thrown when user with input data is not found.
 *
 * @author ALLAN
 *
 */
public class UserNotFoundException extends Exception {

	/**
	 * The message to be shown for the exception.
	 */
	private static final String MESSAGE = "The user with provided data does not exist.";

	/**
	 * Constructor
	 */
	public UserNotFoundException() {
		super(MESSAGE);
		// TODO Auto-generated constructor stub
	}
}
