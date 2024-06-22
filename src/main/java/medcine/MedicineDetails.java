package medcine;

import model.PatientDetails;

/**
 *
 * @author haris
 */
public class MedicineDetails {
     private String medicine_name,medicine_quantity , qty,medicine_time,medicine_meal_time;
     boolean before, morning, afternoon, evening;
     PatientDetails patientDetails;
     int medicine_no;

    public int getMedicine_no() {
        return medicine_no;
    }

    public void setMedicine_no(int medicine_no) {
        this.medicine_no = medicine_no;
    }
   
    
    public MedicineDetails(){

    }

    
    public   void setPatientDetails(PatientDetails patientDetails)
    {
        this.patientDetails = patientDetails;
    }
    public  PatientDetails getPatientDetails()
    {
        return this.patientDetails;
    }
    
    public void setMedicineName(String medicine_name)
    {
        this.medicine_name = medicine_name;
    }
     public String getMedicineName()
    {
        return this.medicine_name;
    }
     
    public void setMedicineQuantity(String mquety)
    {
        this.medicine_quantity = mquety;
    }
    public String getMedicineQuantity()
    {
       return this.medicine_quantity;
    }
     public void setTotalQuantity(String qty)
    {
        this.qty = qty;
    }
    public String getTotalQuantity()
    {
       return this.qty;
    }
    public void setMedicineMealTime(boolean before)
    {
        this.before=before;
    }
    public String getMedicineMealTime()
    {
        if(this.before==true)
        {
            return "1";
        }
        else{
            return "0";
        }
    }
    public String getMedicineMealTimeInWords()
    {
         if(this.before==true)
        {
            return "Before";
        }
        else{
            return "After";
        }
    }
   
    
    public void setMedicineTime(boolean morning,boolean afternoon,boolean evening)
    {
        this.morning = morning;
        this.afternoon = afternoon;
        this.evening = evening;
                
    }
    public String getMedicineTime()
    {
        StringBuffer str  = new StringBuffer();
        if(this.morning){
            str.append("1");
        }
        else{
            str.append("0");
        }
        str.append("--");
        if(this.afternoon){
            str.append("1");
        }
        else{
            str.append("0");
        }
         str.append("--");
        if(this.evening){
            str.append("1");
        }
        else{
            str.append("0");
        }
        return  new String(str);
    }
}