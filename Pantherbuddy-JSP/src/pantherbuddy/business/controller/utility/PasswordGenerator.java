package pantherbuddy.business.controller.utility;

import java.util.Random;

/**
 * The utility class is used to generate a random password for an user.
 *
 * @author ALLAN
 *
 */
public class PasswordGenerator {
	private static final char[] symbols;

	static {
		StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch)
			tmp.append(ch);
		for (char ch = 'a'; ch <= 'z'; ++ch)
			tmp.append(ch);
		symbols = tmp.toString().toCharArray();
	}

	private final static Random random = new Random();

	/**
	 * The method generates a random string to be used as password for the user.
	 *
	 * @return An instance of {@link String} that represents the generated
	 *         password.
	 */
	public static String generatePassword() {
		char[] buf = new char[10];
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}

}
