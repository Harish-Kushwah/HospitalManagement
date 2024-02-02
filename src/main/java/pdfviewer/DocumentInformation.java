package pdfviewer;

import java.io.File;

/**
 *
 * @author haris
 */
public class DocumentInformation {
    File file;
    int file_id,patient_id;
    String file_name,file_ref_name;
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_ref_name() {
        return file_ref_name;
    }

    public void setFile_ref_name(String file_ref_name) {
        this.file_ref_name = file_ref_name;
    }
   
    
    
}
