/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package email;

import auth.User;
import static email.SendingEmail.from;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import myutil.AdminInformation;

/**
 *
 * @author haris
 */
public class VerifyUserEmailSession {

    User user;
    String to,subject,msg;
    public VerifyUserEmailSession(User user) {
        this.user = user;
        AdminInformation admin = new AdminInformation();
        this.to  = admin.getEmail();
        this.subject = "Testing Message Send To Healix";
        this.msg ="Email Send Succefully , Use Healix For sendig Emails ";
        
    }


    public boolean isValidUserEmail() {
       SendingEmailWithoutAttachment sendingEmailWithoutAttachment = new SendingEmailWithoutAttachment(to, subject, this.msg);
        sendingEmailWithoutAttachment.usingCutomeUserDetails(user);
        sendingEmailWithoutAttachment.start();
        try {
            sendingEmailWithoutAttachment.join();
        } catch (InterruptedException e) {
            
        }
        return sendingEmailWithoutAttachment.getEmailSendStatus();
    }
    
       
      
    
    
    
}
