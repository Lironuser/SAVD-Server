package com.example.javaserverapi.service;
import com.example.javaserverapi.error.AppError;
import com.example.javaserverapi.dto.EmployeeVo;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class PictureServer {

    private AppError e;
    public AppError scanImage(EmployeeVo employeeVo, String string_image){
        try {
            URL url = new URL("http://localhost:8085/scan");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");


            byte[] bytes_image = imageToByteArray(string_image);

            if (bytes_image != null) {
                System.out.println("תמונה נקראה בהצלחה כמערך של בייטים.");
            } else {
                System.out.println("אירעה שגיאה בקריאת התמונה.");
            }
            employeeVo.setImage(bytes_image);

            int status = con.getResponseCode();

            System.out.println("HTTP Status Code: " + status);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();

            // המידע מהשרת ב-Python
            System.out.println("תשובה מהשרת: " + content.toString());
        } catch (Exception E) {
            E.printStackTrace();
            return e.UserNotFound;
        }
        return e.GOOD;
    }

    public static byte[] imageToByteArray(String imagePath) {
        byte[] imageBytes = null;
        Path path = Paths.get(imagePath);

        try {
            imageBytes = Files.readAllBytes(path); // קריאת תמונה כמערך של בייטים
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageBytes;
    }
}
