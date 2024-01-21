/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package email;

import static email.SendingEmail.from;
import static email.SendingEmail.session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import EmailTemplates.OtpVerification;
/**
 *
 * @author codew
 */
public class SendingOtp extends SendingEmail {

    String to,subject,message;
    private boolean status=false;
    public SendingOtp(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public void run() {

        session.setDebug(true);
        //step 2:compose the message 
        MimeMessage msg = new MimeMessage(SendingEmail.session);

        try {
            //from email
            msg.setFrom(from);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            msg.setSubject(this.subject);
            msg.setText(this.message);
            msg.setContent(OtpVerification.getHtml(message),"text/html");
            Transport.send(msg);
            System.out.println("Email send successfully");
            status=true;
           
        } catch (MessagingException ex) {
            ex.printStackTrace();
            status=false;
        }

    }
    public boolean getEmailSendStatus()
    {
        return this.status;
    } 
}
