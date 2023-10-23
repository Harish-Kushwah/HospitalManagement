/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myutil;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author haris
 */
public class MultithredingReports extends Thread {
   
    String reportPath = ".\\report\\report2.jrxml";
    String testReportPath = ".\\report\\test_report.jrxml";
    JasperReport jr ;
    JasperReport testReportJr;
    Database DB = Database.getInstance();
    public void run()
    {
        try {
           this.jr = JasperCompileManager.compileReport(reportPath);
           this.testReportJr = JasperCompileManager.compileReport(testReportPath);
           System.out.println("hello");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public JasperReport getReportComipled()
    {
        return this.jr;
    }
    public JasperReport getCompiledTestReport()
    {
        return this.testReportJr;
    }
    
    public Connection getConnection()
    {
        return DB.connect();
    }
    
}
