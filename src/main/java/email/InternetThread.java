
package email;

import java.io.IOException;
import myutil.InternetAvailabilityChecker;


public class InternetThread extends Thread{
    boolean isInternetConnected = false;
    static int total_run = 0;
    @Override
    public void run()
    {
        try {
            if (InternetAvailabilityChecker.isInternetAvailable()) {
                this.isInternetConnected = true;
              System.out.println("Internet is  connecetd");
            } else {
                System.out.println("Internet is not connecetd");
                 this.isInternetConnected = false;
            }
        } catch (IOException exp) {
        }
        System.out.println(total_run++);
    }
//    public SendingEmail getEmailSender()
//    {
//        return sendingEmail;
//    }
    public boolean isInternetAvailable()
    {
        return this.isInternetConnected;
    }
}
