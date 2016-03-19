package pantherbuddy.business.controller.exceptions;

/**
 * Exception class for invalid input.
 *
 * @author ALLAN
 *
 */
public class InvalidInputException extends Exception {

	/**
	 * The message to be shown for the exception.
	 */
	private static final String MESSAGE = "The entered data is invalid";

	/**
	 * Constructor
	 */
	public InvalidInputException() {
		super(MESSAGE);
		// TODO Auto-generated constructor stub
	}

}
