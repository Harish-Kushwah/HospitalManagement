package myutil;

import java.sql.Connection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
/*
  This class is created for generating the compiled reports 
  Reason for using mltithreading, To reduce the time during printing , 
  keep the compiled report first and just use each time 
*/
public class MultithredingReports extends Thread {
   
    String reportPath = ".\\report\\prescription_report_with_sign.jrxml";
    String testReportPath = ".\\report\\test_report_with_sign.jrxml";
    String medicalReportPath =".\\report\\medical_report_with_sign.jrxml";
    JasperReport jr ;
    JasperReport testReportJr;
    JasperReport medicalReportJr;
    Database DB = Database.getInstance();
    @Override
    public void run()
    {
        try {
           this.jr = JasperCompileManager.compileReport(reportPath);
           this.testReportJr = JasperCompileManager.compileReport(testReportPath);
           this.medicalReportJr = JasperCompileManager.compileReport(medicalReportPath);
           System.out.println("hello by");
        } catch (JRException ex) {
            System.out.println("File Not compile");
        }
    }
    public JasperReport getCompiledPrescriptionReport()
    {
        return this.jr;
    }
    public JasperReport getCompliedTestReport()
    {
        return this.testReportJr;
    }
     public JasperReport getCompliedMedicalReport()
    {
        return this.medicalReportJr;
    }
    
    
    public Connection getConnection()
    {
        return DB.connect();
    }
    
}
