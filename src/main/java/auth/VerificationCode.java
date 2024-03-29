package auth;

import email.SendingOtp;
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
      
        SendingOtp sendingEmailWithoutAttachment = new SendingOtp(to, subject, this.code);
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
        String num = Integer.toString(rand.nextInt(1000000 - 100000) + 100000);
        return num;
    }

    public String getCode() {
        return this.code;
    }

}
