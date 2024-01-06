package email;

import auth.User;
import javax.mail.Authenticator;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.*;
import myutil.AdminInformation;

public class SendingEmail extends Thread {

    public static String from, password;
    public static Session session = null;

    public SendingEmail() {     
        setSessionDetails();
    }

    public void usingAdminEmail()
    {
        AdminInformation user = new AdminInformation();
        SendingEmail.from = user.getEmail();
        SendingEmail.password = user.getPassword();
    }
    public void usingCutomeUserDetails(User user)
    {
        SendingEmail.from = user.getEmail();
        SendingEmail.password = user.getEmailPassword();
    }
    public static void setSessionDetails() {

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
        if (SendingEmail.session == null) {

            SendingEmail.session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(SendingEmail.from, SendingEmail.password);
                }
            });
        }

    }

    public  Session getSession() {
        // if(SendingEmail.session == null)
        return SendingEmail.session;

    }
}
