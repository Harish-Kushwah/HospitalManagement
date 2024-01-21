package database;

import pdfviewer.DocumentInformation;
import pdfviewer.PDFUtility;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author haris
 */
public class PDFDatabase {

    private static final String INSERT_FILE = "INSERT INTO documents (filename, content, compression_type,patient_fk ,file_ref_name) VALUES (?,?, ?, ?,?)";
    private static final String GET_DOCUMENT = "SELECT id, filename, content, compression_type FROM documents WHERE id = ?";
    private static final String GET_ALL_DOCUMENTS = "SELECT * FROM documents WHERE patient_fk = ?";
    private static final String DELETE_DOCUMENT = "delete from documents where id =? and patient_fk = ?";

    private final Connection connection = Database.getInstance().getConnection();

    public boolean storePdf(String file_ref_name, File file, int patient_id) {

        try {
            // Use a PreparedStatement to insert data into the database

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILE)) {

                preparedStatement.setString(1, file.getName());

                // Compress PDF data before storing
                byte[] compressedData = PDFUtility.compressPDF(file);

                preparedStatement.setBytes(2, compressedData);

                preparedStatement.setString(3, "deflate");

                preparedStatement.setInt(4, patient_id);

                preparedStatement.setString(5, file_ref_name);

                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public File getPDF(int file_id) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_DOCUMENT);

            preparedStatement.setInt(1, file_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int id = resultSet.getInt("id");
                String filename = resultSet.getString("filename");
                byte[] compressedBytes = resultSet.getBytes("content");
                String compressionType = resultSet.getString("compression_type");

                // Decompress the PDF data
                byte[] decompressedBytes = PDFUtility.decompressPDF(compressedBytes, compressionType);

                String saved_file_path = "./documents/" + filename;
                // Save the decompressed PDF data to a file
                FileOutputStream fileOutputStream = new FileOutputStream(saved_file_path);
                fileOutputStream.write(decompressedBytes);

                return new File(saved_file_path);

            } else {
                System.out.println("PDF not found for the given ID.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<DocumentInformation> getAllPDF(int patient_number) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DOCUMENTS);

            preparedStatement.setInt(1, patient_number);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<DocumentInformation> all_files = new ArrayList<>();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String file_name = resultSet.getString("filename");
                String file_ref_name = resultSet.getString("file_ref_name");
                byte[] compressedBytes = resultSet.getBytes("content");
                String compressionType = resultSet.getString("compression_type");

                // Decompress the PDF data
                byte[] decompressedBytes = PDFUtility.decompressPDF(compressedBytes, compressionType);

                String saved_file_path = "./documents/" + file_name;
                // Save the decompressed PDF data to a file
                FileOutputStream fileOutputStream = new FileOutputStream(saved_file_path);
                fileOutputStream.write(decompressedBytes);
                fileOutputStream.close();

                File file = new File(saved_file_path);
                DocumentInformation document_info = new DocumentInformation();

                document_info.setFile(file);
                document_info.setFile_id(id);
                document_info.setFile_ref_name(file_ref_name);
                document_info.setFile_name(file_name);
                document_info.setPatient_id(patient_number);

                all_files.add(document_info);
//                if(file.delete())
//                {
//                    System.out.println("File deleted ");
//                }
//                else{
//                    System.out.println("Unable to delete file");
//                            
//                }

            }
            return all_files;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean deleteFile(DocumentInformation document) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DOCUMENT);
            preparedStatement.setInt(1, document.getFile_id());
            preparedStatement.setInt(2, document.getPatient_id());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }        
    }

//    public static void main(String []args)
//    {
//        PDFDatabase pdf = new PDFDatabase();
//        File file = new File("./images/back.png");
////        if(pdf.storePdf(file,1)){
////            System.out.println("PDF saved successfully");
////        }
//        
//        file = pdf.getPDF(3);
//        if(file!=null){
//            System.out.println("file name :" + file.getName());
//        }
//        else{
//            System.out.println("Not found");
//        }
//        
//    }
}
