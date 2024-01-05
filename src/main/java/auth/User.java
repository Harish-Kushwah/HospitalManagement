
package auth;

public class User {

    String email=null , username , hospital_name=null , password;
    String type;
    int user_id;
    
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
    public int getUserId()
    {
        return this.user_id;
    }
  
    @Override
    public String toString()
    {
       return this.username +"\t" + this.email + "\t" +this.type; 
    }
}
