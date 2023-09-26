package myutil;

import java.util.Date;

public class PatientDetails
{
    
    private String name_input,gender,symptoms,others,mobileno_input,bloodpressure_input,pulse_input,sugar_input;
            int pid,age_input;
            int weight_input;
            
            Date date;
    
    public PatientDetails(){

    }

    
    public   void setName(String name)
    {
        this.name_input = name;
    }
    public void setPid(int pid){this.pid=pid;};
   
    public void setAge(int age) {this.age_input = age;}
    public void setBloodPressure(String bp) {this.bloodpressure_input = bp;}
    public void setPulse(String pulse) {this.pulse_input=pulse;}
    public  void setSugar(String sugar){this.sugar_input = sugar;}
    public  void setWeight(int weight){this.weight_input = weight;}
    public  void setMobileNo(String mobile_no){this.mobileno_input = mobile_no;}
    public  void setGender(String gender){this.gender= gender;}
    public  void setSymptoms(String symptoms){this.symptoms = symptoms;}
    public void setOther(String other){this.others=other;}
    public  void setDate(Date date){this.date = date;}
  

    

    public Date getDate(){return  this.date;}
    public int getAge() {return this.age_input;}
    public String  getBloodPressure() {return this.bloodpressure_input;}
    public String  getPulse() {return this.pulse_input;}
    public  String getSugar(){return this.sugar_input ;}
    public  int getWeight(){return this.weight_input ;}
    public  String getMobileNo(){return this.mobileno_input ;}
    public  String getGender(){return this.gender;}
    public  String getSymptoms(){return this.symptoms;}
    public String getOther(){return this.others;}
   
    public int getPid(){
        return this.pid;
    }
   
    public  String getName(){return this.name_input;}





}