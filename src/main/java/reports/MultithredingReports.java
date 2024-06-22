package reports;

import database.Database;
import java.sql.Connection;
import javaswingdev.system.SystemReports;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/*
  This class is created for generating the compiled reports 
  Reason for using mltithreading, To reduce the time during printing , 
  keep the compiled report first and just use each time 
 */
public class MultithredingReports extends Thread {

    JasperReport jr;
    JasperReport testReportJr;
    JasperReport medicalReportJr;
    JasperReport medicalReportJrFormat2;
    JasperReport medicalCertificate;

    @Override
    public void run() {
        try {
            this.jr = JasperCompileManager.compileReport(SystemReports.PRESCRIPTION_REPORT);
            this.testReportJr = JasperCompileManager.compileReport(SystemReports.TEST_REPORT);
            this.medicalReportJr = JasperCompileManager.compileReport(SystemReports.REFEREL_LETTER_FORMAT_1);
            this.medicalReportJrFormat2 = JasperCompileManager.compileReport(SystemReports.REFEREL_LETTER_FORMAT_2);
            this.medicalCertificate = JasperCompileManager.compileReport(SystemReports.MEDICAL_CERTIFICATE);

        } catch (JRException ex) {
        }
    }

    public JasperReport getCompiledPrescriptionReport() {
        return this.jr;
    }

    public JasperReport getCompliedTestReport() {
        return this.testReportJr;
    }

    public JasperReport getCompliedMedicalReportFormat1() {
        return this.medicalReportJr;
    }

    public JasperReport getCompliedMedicalReportFormat2() {
        return this.medicalReportJrFormat2;
    }

    public JasperReport getCompliedMedicalCertificate() {
        return this.medicalCertificate;
    }

    public Connection getConnection() {
        return Database.getInstance().connect();
    }

}
