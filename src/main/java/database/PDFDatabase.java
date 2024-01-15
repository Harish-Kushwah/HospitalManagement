package database;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author haris
 */
public class PDFDatabase {

    private final String INSERT_FILE = "INSERT INTO documents (filename, content, compression_type,patient_fk ,file_ref_name) VALUES (?,?, ?, ?,?)";
    private final String GET_DOCUMENT = "SELECT id, filename, content, compression_type FROM documents WHERE id = ?";

    private final Connection connection = Database.getInstance().getConnection();

    public boolean storePdf(String file_ref_name,File file,int patient_id) {

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
