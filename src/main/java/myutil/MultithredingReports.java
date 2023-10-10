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
   
    String reportPath = "D:\\HospitalManagement\\src\\main\\java\\hospitalmanagement\\report2.jrxml";
    JasperReport jr ;
    Database DB = Database.getInstance();
    public void run()
    {
        try {
           this.jr = JasperCompileManager.compileReport(reportPath);
           System.out.println("hello");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public JasperReport getReportComipled()
    {
        return this.jr;
    }
    public Connection getConnection()
    {
        return DB.connect();
    }
    
}
