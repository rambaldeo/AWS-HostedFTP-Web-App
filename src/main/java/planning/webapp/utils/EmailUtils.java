package planning.webapp.utils;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtils {
	public static String sendEmail(String emailSender, String subject, String body) throws MessagingException {
		final String emailTo = "rambaldeo241@icloud.com";
		String emailFrom = emailSender;
		String status = null;
		try {
			//Acquire a secure SMPTs session
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.starttls.required", "true");

			
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailTo, System.getenv("EMAIL_auth")); 
				}
			});
			
			Message msg = new MimeMessage(session);
			msg.setSubject(subject);
			msg.setText(body);
			
			Address sender = new InternetAddress(emailFrom);
			Address receiver = new InternetAddress(emailTo);
			msg.setFrom(sender);
			msg.setRecipient(Message.RecipientType.TO, receiver);
			Transport.send(msg);
			//Sending the email
			status="E-mail successfully sent";
		}catch(MessagingException e) {
			status = e.toString();
		}
		System.out.println("The status when sending an email is: " + status);
		return status;
	}//sendEmail method
	

}//Class method











