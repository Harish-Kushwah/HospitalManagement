/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EmailTemplates;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author codew
 */
public class UpdatePassword {

    public static String getHtml(String name) {
        String filePath = "./html/updatepassword.xhtml";

        String old = "<p class=\"name\"></p>";
        String new_m = "<p class=\"name\">" + name + "</p>";

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
