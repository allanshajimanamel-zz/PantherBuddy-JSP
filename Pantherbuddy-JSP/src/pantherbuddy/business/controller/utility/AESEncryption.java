package pantherbuddy.business.controller.utility;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * The class is used to encrypt and decrypt the user password. It uses AES 128
 * bit for encryption and decryption.
 *
 * @author ALLAN
 *
 */
public class AESEncryption {

	/**
	 * The key used for the encryption.
	 */
	private static final String KEYVALUE = "XhYBZsASBcCe1K2y";

	/**
	 * The method is used to encrypt the password using the
	 * {@linkplain KEYVALUE}
	 *
	 * @param password
	 *            An instance of {@link String} representing the password to
	 *            encrypt.
	 * @return An instance of {@link String} representing the encrypted
	 *         password.
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String encrypt(String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Key key = generateKey();
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(password.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}

	/**
	 * The method is used to decrypt the password using the
	 * {@linkplain KEYVALUE}
	 *
	 * @param password
	 *            An instance of {@link String} representing the password to
	 *            decrypt.
	 * @return An instance of {@link String} representing the decrypted
	 *         password.
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String decrypt(String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Key key = generateKey();
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.getDecoder().decode(password);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() {
		Key key = new SecretKeySpec(KEYVALUE.getBytes(), "AES");
		return key;
	}
}
