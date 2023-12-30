/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package myutil;

import com.sun.mail.util.MailConnectException;
import java.io.File;
import java.net.UnknownHostException;
import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SendingEmail {

  
    
    String from;
    Session session;
    public void setAuthenicationDetails(String from , String password)
    {
       
        
        this.from = from;
        //Variabl from gmail host
        String host = "smtp.gmail.com";

        //get the system properties 
        Properties properties = System.getProperties();
        System.out.println("Properties :" + properties);

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //step 1: to get the session object
        
         this.session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                
                return new PasswordAuthentication(from,password);
            }
        });
       
      
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String message = "This Another message  ";
        String subject = "Test email message";
        String to = "harishkushwah54321@gmail.com";
        String from = "testify8953@gmail.com";

       // sendEmail(message, subject, to, from);
       // sendAttatch(message, subject, to, from);
    }

    
    public  void sendEmail( String to ,String subject, String message) {
        this.session.setDebug(true);
        //step 2:compose the message 
        MimeMessage msg = new MimeMessage(this.session);

        try {
            //from email
            msg.setFrom(this.from);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(message);

            //send the message using transport class 
            Transport.send(msg);
            System.out.println("Email send successfully");

        } catch ( MessagingException ex) {
            ex.printStackTrace();
        }

    }

    //TO send the attachment
    private  void sendAttatch(String message, String subject, String to, String from) {
        //Variabl from gmail host
        String host = "smtp.gmail.com";

        //get the system properties 
        Properties properties = System.getProperties();
        System.out.println("Properties :" + properties);

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //step 1: to get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("testify8953@gmail.com", "kaom ridr dvep qlfe");
            }
        });

        session.setDebug(true);
        //step 2:compose the message 
        MimeMessage msg = new MimeMessage(session);

        try {
            //from email
            msg.setFrom(from);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            // msg.setText(message);

            //attachment 
            //file path
            String path = "C:\\Users\\haris\\Documents\\NetBeansProjects\\SendingEmail\\src\\main\\java\\com\\mycompany\\sendingemail\\doctor_splash1.jpg";
            String report = "C:\\Users\\haris\\Desktop\\rrr.pdf";
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart fileMime = new MimeBodyPart();

            MimeBodyPart textMime = new MimeBodyPart();
            try {
                textMime.setText(message);
                File file = new File(report);
                fileMime.attachFile(file);
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
    
    public  void sendEmailWithAttatch( String to ,String subject, String message ,File file) {
        this.session.setDebug(true);
        //step 2:compose the message 
        MimeMessage msg = new MimeMessage(this.session);

        try {
            //from email
            msg.setFrom(this.from);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(message);

             MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart fileMime = new MimeBodyPart();

            MimeBodyPart textMime = new MimeBodyPart();
            try {
                textMime.setText(message);
                fileMime.attachFile(file);
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

