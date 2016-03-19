package pantherbuddy.business.controller.utility;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * The class is used to send the user his password.
 *
 * @author ALLAN
 *
 */
public class EmailService {

	/**
	 * The method is used to send password of user to his registered email.
	 *
	 * @param email
	 *            An instance of {@link String} representing the email to which
	 *            the mail is to be sent.
	 * @param password
	 *            An instance of {@link String} representing the password to be
	 *            sent.
	 * @throws MessagingException
	 * @throws NamingException
	 */
	public static void sendMail(String email, String password)
			throws MessagingException, NamingException {
		//		Properties props = new Properties();
		//		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		//		Session mailSession = Session.getInstance(props, null);

		InitialContext context = new InitialContext();
		Session mailSession = (Session) context.lookup("java:jboss/mail/Gmail");

		MimeMessage m = new MimeMessage(mailSession);
		InternetAddress[] to = new InternetAddress[] { new InternetAddress(
				email) };
		m.setRecipients(Message.RecipientType.TO, to);
		m.setSubject("Pantherbuddy");
		m.setSentDate(new java.util.Date());
		String message = "Thanks for registering on Panther Buddy.\n The password for accessing Pantherbuddy is : "
				+ password + "\n. Thanks and best regards,\nPantherbuddy Team";
		m.setText(message, "utf-8", "html");
		Transport.send(m);
	}

}
