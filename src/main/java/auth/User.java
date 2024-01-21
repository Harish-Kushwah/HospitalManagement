
package auth;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 2111220202093239L;
    
    private String email=null , username , hospital_name=null , password, type,email_password;
    private int user_id;
    private boolean status = false;
    
    public void setEmailVerificationStatus(boolean status)
    {
        this.status = status;
    }
    public void setEmailPassword(String email_password)
    {
        this.email_password = email_password;
    }
    public void setUserId(int user_id)
    {
        this.user_id = user_id;
    }
    
    public void setUserName(String username)
    {
        this.username = username;
    }
    public void setHospitalName(String hospital_name)
    {
        this.hospital_name = hospital_name;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setUserType(String userType)
    {
        this.type = userType;
    }
    
    public String getUserType()
    {
        return this.type;
    }
    
    public String getUserName()
    {
       return this.username ;
    }
    public String getHospitalName()
    {
        return this.hospital_name ;
    }
    public String getPassword()
    {
        return this.password ;
    }
    public String getEmail()
    {
        return this.email ;
    }
    public String getEmailPassword()
    {
        return this.email_password;
    }
    public int getUserId()
    {
        return this.user_id;
    }
    public boolean getEmailVerificationStatus()
    {
        return this.status;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", username=" + username + ", hospital_name=" + hospital_name + ", password=" + password + ", type=" + type + ", email_password=" + email_password + ", user_id=" + user_id + ", status=" + status + '}';
    }
  
    
}
