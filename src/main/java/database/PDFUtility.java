package database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author haris
 */
public class PDFUtility {

    public static byte[] decompressPDF(byte[] compressedData, String compressionType) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        switch (compressionType.toLowerCase()) {
            case "gzip" -> {
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = gzipInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
            case "deflate" -> {
                try (InflaterInputStream inflaterInputStream = new InflaterInputStream(inputStream)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inflaterInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    inflaterInputStream.close();
                }
            }
            default ->
                throw new IllegalArgumentException("Unsupported compression type: " + compressionType);
        }
        // Add more cases for other compression types if needed
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }

    public static byte[] compressPDF(File file) {
        try {

            byte[] data = getFileDataInByte(file);

            Deflater deflater = new Deflater();
            deflater.setInput(data);

            // Create an output stream with a DeflaterOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            try {
                DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream, deflater);

                deflaterOutputStream.write(data);
                deflaterOutputStream.close();

            } catch (Exception exp) {

            }

            outputStream.close();

            // Get the compressed data from the ByteArrayOutputStream
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }

    public static byte[] getFileDataInByte(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            return data;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
