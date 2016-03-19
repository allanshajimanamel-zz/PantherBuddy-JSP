package pantherbuddy.business.controller.exceptions;

/**
 * Exception for user already exists. Thrown for user registration when the data
 * entered already exists uniquely for a user in the system.
 *
 * @author ALLAN
 *
 */
public class UserAlreadyExistsException extends Exception {

	/**
	 * The message to be shown for the exception.
	 */
	private static final String MESSAGE = "The user with provided data already exists.";

	/**
	 * Constructor
	 */
	public UserAlreadyExistsException() {
		super(MESSAGE);
		// TODO Auto-generated constructor stub
	}
}
