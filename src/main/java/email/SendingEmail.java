package email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.mail.Authenticator;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.*;
import myutil.UserInformation;

public class SendingEmail extends Thread {

    public static String from, password;
    public static Session session = null;

    public SendingEmail() {
        UserInformation user = new UserInformation();
        SendingEmail.from = user.getEmail();
        SendingEmail.password = user.getPassword();
        
        setSessionDetails();
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

    public static Session getSession() {
        // if(SendingEmail.session == null)
        return SendingEmail.session;

    }
}
