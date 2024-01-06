package auth;

import email.SendingEmailWithoutAttachment;
import java.util.Random;

/**
 *
 * @author haris
 */
public class VerificationCode {

    String code;

    public boolean sendVerificationCode(String to) {
        String subject = "Verification Code";
        this.code = getRandomCode();
      
        SendingEmailWithoutAttachment sendingEmailWithoutAttachment = new SendingEmailWithoutAttachment(to, subject, this.code);
        sendingEmailWithoutAttachment.usingAdminEmail();
        sendingEmailWithoutAttachment.start();
        try {
            sendingEmailWithoutAttachment.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sendingEmailWithoutAttachment.getEmailSendStatus();
    }

    
    public String getRandomCode() {
        Random rand = new Random();
        String num = Integer.toString(rand.nextInt(100000));
        return num;
    }

    public String getCode() {
        return this.code;
    }

}
