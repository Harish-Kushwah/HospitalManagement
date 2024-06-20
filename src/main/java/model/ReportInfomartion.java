package model;

import java.util.ArrayList;
import java.util.Date;
public class ReportInfomartion {
    String report_name  , patient_name ;
    int patient_number;
    Date date ;
    ArrayList<String> reports = new  ArrayList<String>();
    public void setDate(Date date) {
        this.date = date;
    }
    public void setReportType(String report_name)
    {
        this.report_name = report_name;
    }
    public void setPatientName(String patient_name)
    {
        this.patient_name = patient_name;
    }
    public void setPatientNumber(int patient_number)
    {
        this.patient_number = patient_number;
    }
    public void setReportName(String name)
    {
        reports.add(name);
    }
    
    public String getReportType()
    {
        return this.report_name;
    }
    public String getPatientName()
    {
        return this.patient_name;
    }
    public int getPatientNumber()
    {
        return this.patient_number;
    }
    public ArrayList<String> getReportNames()
    {
        return reports;
    }
     public Date getDate() {
        return this.date;
    }
    
    
}
