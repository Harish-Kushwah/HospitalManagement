package reports;

import database.Database;
import java.sql.Connection;
import javaswingdev.system.SystemReports;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/*
  This class is created for generating the compiled reports 
  Reason for using mltithreading, To reduce the time during printing , 
  keep the compiled report first and just use each time 
 */
public class MultithredingReports extends Thread {

    JasperReport jr;
    JasperReport prescriptionGuidelinesJr;
    JasperReport exerciseSubreportJr;
    JasperReport adviceSubreportJr;
    JasperReport investigationSubreportJr;
    JasperReport testReportJr;
    JasperReport medicalReportJr;
    JasperReport medicalReportJrFormat2;
    JasperReport medicalCertificate;

    @Override
    public void run() {
        try {
            synchronized (this) {
                this.jr = JasperCompileManager.compileReport(SystemReports.PRESCRIPTION_REPORT);
                this.prescriptionGuidelinesJr = JasperCompileManager
                        .compileReport(SystemReports.PRESCRIPTION_GUIDELINES_REPORT);
                this.exerciseSubreportJr = JasperCompileManager.compileReport(SystemReports.EXERCISE_SUBREPORT);
                this.adviceSubreportJr = JasperCompileManager.compileReport(SystemReports.ADVICE_SUBREPORT);
                this.investigationSubreportJr = JasperCompileManager.compileReport(SystemReports.INVESTIGATION_SUBREPORT);
                this.testReportJr = JasperCompileManager.compileReport(SystemReports.TEST_REPORT);
                this.medicalReportJr = JasperCompileManager.compileReport(SystemReports.REFEREL_LETTER_FORMAT_1);
                this.medicalReportJrFormat2 = JasperCompileManager.compileReport(SystemReports.REFEREL_LETTER_FORMAT_2);
                this.medicalCertificate = JasperCompileManager.compileReport(SystemReports.MEDICAL_CERTIFICATE);
                System.out.println("All reports compiled successfully");
                this.notifyAll();
            }
        } catch (JRException | JRRuntimeException ex) {
            System.err.println("Error compiling reports in thread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public synchronized JasperReport getCompiledPrescriptionReport() {
        if (this.jr == null) {
            try {
                this.jr = JasperCompileManager.compileReport(SystemReports.PRESCRIPTION_REPORT);
            } catch (JRException | JRRuntimeException ex) {
                System.err.println("Error compiling prescription report: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return this.jr;
    }

    public synchronized JasperReport getCompiledPrescriptionGuidelinesReport() {
        if (this.prescriptionGuidelinesJr == null) {
            try {
                this.prescriptionGuidelinesJr = JasperCompileManager.compileReport(SystemReports.PRESCRIPTION_GUIDELINES_REPORT);
            } catch (JRException | JRRuntimeException ex) {
                System.err.println("Error compiling prescription guidelines report: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return this.prescriptionGuidelinesJr;
    }

    public synchronized JasperReport getCompiledExerciseSubreport() {
        if (this.exerciseSubreportJr == null) {
            try {
                this.exerciseSubreportJr = JasperCompileManager.compileReport(SystemReports.EXERCISE_SUBREPORT);
            } catch (JRException | JRRuntimeException ex) {
                System.err.println("Error compiling exercise subreport: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return this.exerciseSubreportJr;
    }

    public synchronized JasperReport getCompiledAdviceSubreport() {
        if (this.adviceSubreportJr == null) {
            try {
                this.adviceSubreportJr = JasperCompileManager.compileReport(SystemReports.ADVICE_SUBREPORT);
            } catch (JRException | JRRuntimeException ex) {
                System.err.println("Error compiling advice subreport: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return this.adviceSubreportJr;
    }

    public synchronized JasperReport getCompiledInvestigationSubreport() {
        if (this.investigationSubreportJr == null) {
            try {
                this.investigationSubreportJr = JasperCompileManager.compileReport(SystemReports.INVESTIGATION_SUBREPORT);
            } catch (JRException | JRRuntimeException ex) {
                System.err.println("Error compiling investigation subreport: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return this.investigationSubreportJr;
    }

    public synchronized JasperReport getCompliedTestReport() {
        if (this.testReportJr == null) {
            try {
                this.testReportJr = JasperCompileManager.compileReport(SystemReports.TEST_REPORT);
            } catch (JRException | JRRuntimeException ex) {
                System.err.println("Error compiling test report: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return this.testReportJr;
    }

    public synchronized JasperReport getCompliedMedicalReportFormat1() {
        if (this.medicalReportJr == null) {
            try {
                this.medicalReportJr = JasperCompileManager.compileReport(SystemReports.REFEREL_LETTER_FORMAT_1);
            } catch (JRException | JRRuntimeException ex) {
                System.err.println("Error compiling medical report format 1: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return this.medicalReportJr;
    }

    public synchronized JasperReport getCompliedMedicalReportFormat2() {
        if (this.medicalReportJrFormat2 == null) {
            try {
                this.medicalReportJrFormat2 = JasperCompileManager.compileReport(SystemReports.REFEREL_LETTER_FORMAT_2);
            } catch (JRException | JRRuntimeException ex) {
                System.err.println("Error compiling medical report format 2: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return this.medicalReportJrFormat2;
    }

    public synchronized JasperReport getCompliedMedicalCertificate() {
        if (this.medicalCertificate == null) {
            try {
                this.medicalCertificate = JasperCompileManager.compileReport(SystemReports.MEDICAL_CERTIFICATE);
            } catch (JRException | JRRuntimeException ex) {
                System.err.println("Error compiling medical certificate: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return this.medicalCertificate;
    }

    public Connection getConnection() {
        return Database.getInstance().connect();
    }

}
