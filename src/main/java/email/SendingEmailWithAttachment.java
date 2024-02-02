package email;

import java.io.File;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import static email.SendingEmail.from;
import static email.SendingEmail.session;

/**
 *
 * @author haris
 */
public class SendingEmailWithAttachment extends SendingEmail {

    String to,subject,message;
    File file;
    public SendingEmailWithAttachment(String to, String subject, String message , File file) {
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.file = file;
    }

    @Override
    public void run() {

        //session.setDebug(true);
        //step 2:compose the message 
        MimeMessage msg = new MimeMessage(SendingEmail.session);

        try {
            //from email
            msg.setFrom(from);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            msg.setSubject(this.subject);
            msg.setText(this.message);

            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart fileMime = new MimeBodyPart();

            MimeBodyPart textMime = new MimeBodyPart();
            try {
                textMime.setText(this.message);
                fileMime.attachFile(this.file);
            } catch (Exception exp) {
                exp.printStackTrace();
            }

            mimeMultipart.addBodyPart(textMime);
            mimeMultipart.addBodyPart(fileMime);

            msg.setContent(mimeMultipart);
            //send the message using transport class 
            Transport.send(msg);
            System.out.println("Email send successfully");

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

}
