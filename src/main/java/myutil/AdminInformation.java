package myutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author haris
 */
public class AdminInformation {
    private String username,password,email;
    
    public AdminInformation()
    {
        getCredentials();
    }
    
    private  void getCredentials() {
        File file = new File("./logs/email_auth.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            HashMap<String, String> mp = new HashMap<String, String>();
           
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    mp.put("Name", line);
                } else {
                    mp.put("Pass", line);
                }
                i++;

            }
          
           this.setEmail(mp.get("Name"));
           this.setPassword(mp.get("Pass"));
          
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public void setEmail(String email)
    {
        this.email  = email;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    public String getUsername()
    {
        return this.username;
    }
    public String getEmail()
    {
        return this.email;
    }
}
