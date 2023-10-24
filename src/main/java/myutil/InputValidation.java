/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myutil;

/**
 *
 * @author haris
 */
public class InputValidation {
    
    public boolean isVlalidName(String name)
    {
        
        String special_str = "1234567890-=+[]'./;:,</?!@#$%^&*()";
        for(int i=0;i<name.length();i++)
        {
            if(special_str.indexOf(name.charAt(i))!=-1){
                return false;
            }
        }
        return true;
    }
    public boolean isValidMobileNumber(String mobile)
    {
      
        String special_str = "+ 1234567890";
        for(int i=0;i<mobile.length();i++)
        {
            if(special_str.indexOf(mobile.charAt(i))==-1 || mobile.length()>10 ){
                return false;
            }
        }
        return true;
    }
    public boolean isValidAge(String _age)
    {
        try{
        int age = Integer.parseInt(_age);
          if(age>=130 || age<=0)
              return false;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
        
    }
    public boolean isValidWeight(String _weight)
    {
         try{
        int weight = Integer.parseInt(_weight);
          if(weight>=130 || weight<=0)
              return false;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    public boolean isValidBloodPressure(String pressure)
    {
        String special_pressure = "/1234567890 ";
        for(int i=0;i<pressure.length();i++)
        {
            if(special_pressure.indexOf(pressure.charAt(i))==-1){
                return false;
            }
        }
        return true;
    } 
    public boolean isValidPulseRate(String _pulse)
    {
        try{
        int pulse = Integer.parseInt(_pulse);
          if(pulse>=150 || pulse<=0)
              return false;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    } 
    
    public boolean isValidSugar(String _sugar)
    {
        try{
        int sugar = Integer.parseInt(_sugar);
          if(sugar>=1000)
              return false;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
}
