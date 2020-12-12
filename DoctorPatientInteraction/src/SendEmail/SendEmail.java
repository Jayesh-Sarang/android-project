/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SendEmail;

/**
 *
 * @author kp
 */
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    public static String femail = "inbotics.testing@gmail.com";
    public static String pass = "M@HENDRA";

    public SendEmail(String toemail, String Subject, String message, String Attachment) {

        String to = toemail;
        String subject = Subject;
        String messageText = message;
        boolean sessionDebug = false;

        Session session = createSmtpSession();
// Set debug on the Session so we can see what is going on
// Passing false will not echo debug info, and passing true will.        
        session.setDebug(sessionDebug);
        try {
// Instantiate a new MimeMessage and fill it with the required information.
            String[] recipients = to.split(",");
            for (int n = 0; n < recipients.length; n++) {
                String recipient = recipients[n].replaceAll(" ", "");
                if (!recipient.equals("")) {
                    Message msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress(femail));
                    InternetAddress[] address = {new InternetAddress(recipient)};
                    msg.setRecipients(Message.RecipientType.TO, address);
                    msg.setSubject(subject);
                    msg.setSentDate(new Date());
// Hand the message to the default transport service for delivery.
                    MimeBodyPart messagePart = new MimeBodyPart();
                    messagePart.setText(messageText);
                    //
                    // Set the email attachment file
                    //
//                    MimeBodyPart attachmentPart = new MimeBodyPart();
//                    FileDataSource fileDataSource = new FileDataSource(Attachment) {
//                        @Override
//                        public String getContentType() {
//                            return "application/octet-stream";
//                        }
//                    };
//                    attachmentPart.setDataHandler(new DataHandler(fileDataSource));
//                    attachmentPart.setFileName(fileDataSource.getName());

                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messagePart);
//                    multipart.addBodyPart(attachmentPart);
                    msg.setContent(multipart);
                    Transport.send(msg);
                    System.out.println("Email Send Successfully.!!!");
                }
            }
        } catch (MessagingException mex) {
            System.out.println("Email does not Send.!!!");
        }
    }
    //===========================================================================

    public static Session createSmtpSession() {
        final Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "" + 587);
        props.setProperty("mail.smtp.starttls.enable", "true");

        return Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(femail, pass);
            }
        });
    }
}