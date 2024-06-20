package reports;

import database.Database;
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
    String medicalReportPath =".\\report\\medical_report_with_sign_format_1.jrxml";
    String medicalReportPathForFormat2 =".\\report\\medical_report_with_sign_format_2.jrxml";
    String medicalCertificatePath =".\\report\\medical_certificate_with_sign_updated.jrxml";
    JasperReport jr ;
    JasperReport testReportJr;
    JasperReport medicalReportJr;
    JasperReport medicalReportJrFormat2;
    JasperReport medicalCertificate;
    Database DB = Database.getInstance();
    @Override
    public void run()
    {
        try {
           this.jr = JasperCompileManager.compileReport(reportPath);
           this.testReportJr = JasperCompileManager.compileReport(testReportPath);
           this.medicalReportJr = JasperCompileManager.compileReport(medicalReportPath);
           this.medicalReportJrFormat2 = JasperCompileManager.compileReport(medicalReportPathForFormat2);
           this.medicalCertificate = JasperCompileManager.compileReport(medicalCertificatePath);
         
        } catch (JRException ex) {
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
     public JasperReport getCompliedMedicalReportFormat1()
    {
        return this.medicalReportJr;
    }
      public JasperReport getCompliedMedicalReportFormat2()
    {
        return this.medicalReportJrFormat2;
    }
      public JasperReport getCompliedMedicalCertificate()
    {
        return this.medicalCertificate;
    }
    
    public Connection getConnection()
    {
        return DB.connect();
    }
    
}
