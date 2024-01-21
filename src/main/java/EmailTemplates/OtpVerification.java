package EmailTemplates;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OtpVerification {

    public static String getHtml(String codes) {
        String filePath = "./html/verification.xhtml";

        String old = "<p id=\"message\" ></p>";
        String new_m = "<p id=\"message\" >" + codes + "</p>";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder htmlContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(old)) {
                    line = line.replace(old, new_m);
                }
                htmlContent.append(line).append("\n");
            }

            /**
             * Write the modified content back to the HTML file
             */
            /*
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(htmlContent.toString());
                System.out.println("Message appended to the <h1> tag in the HTML file: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            return new String(htmlContent);
        } catch (IOException e) {
        }
        return null;
    }

}
