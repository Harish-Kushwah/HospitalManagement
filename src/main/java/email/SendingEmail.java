package email;

import javax.mail.Authenticator;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.*;


public class SendingEmail extends Thread {

    public static String from, password;
    public static Session session = null;

    public SendingEmail() {
        getCredentials();
        setSessionDetails();
    }

    
    private static void getCredentials() {
//        File file = new File("./logs/email_auth.txt");
//        try {
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (FileNotFoundException ex) {
//        } catch (IOException ex) {
//        }
        SendingEmail.from = "testify8953@gmail.com";

        SendingEmail.password = "kaom ridr dvep qlfe";

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
