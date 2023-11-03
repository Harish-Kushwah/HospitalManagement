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
   
    String reportPath = ".\\report\\report2.jrxml";
    String testReportPath = ".\\report\\test_report1.jrxml";
    JasperReport jr ;
    JasperReport testReportJr;
    Database DB = Database.getInstance();
    @Override
    public void run()
    {
        try {
           this.jr = JasperCompileManager.compileReport(reportPath);
           this.testReportJr = JasperCompileManager.compileReport(testReportPath);
           System.out.println("hello");
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
    
    public Connection getConnection()
    {
        return DB.connect();
    }
    
}
