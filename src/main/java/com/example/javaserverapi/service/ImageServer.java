package com.example.javaserverapi.service;
import com.example.javaserverapi.error.AppError;
import com.example.javaserverapi.dto.EmployeeVo;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class ImageServer {

    public AppError scanImage(EmployeeVo employeeVo, String string_image) {
        try {
            //url התחבר לשרת של הפייתון
            URL url = new URL("http://localhost:8085/scanimage");
            //יצירת חיבור לשרת הנוסף
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //הגדרת הבקשה
            con.setRequestMethod("POST");
            byte[] byte_image = imageToByteArray(string_image);

            int status = con.getResponseCode();
            System.out.println("HTTP Status Code: " + status);

            if (status == HttpURLConnection.HTTP_OK) {
                // המידע מהשרת ב-Python
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                long serverInput = 0;

                while ((inputLine = in.readLine()) != null) {
                    serverInput = Long.parseLong(inputLine);
                }

                in.close();

                con.disconnect();

                if (serverInput == 1){
                    // המידע מהשרת ב-Python
                    System.out.println("Success");
                    System.out.println(inputLine);
                    employeeVo.setImage(byte_image);

                }else {
                    System.out.println("Error!");
                    System.out.println(inputLine);
                }
            } else {
                System.out.println("HTTP Request failed with status: " + status);
            }

        } catch (Exception E) {
            E.printStackTrace();
            return AppError.EMPLOYEE_NOT_FOUND;
        }

        return AppError.GOOD;
    }

    public static byte[] imageToByteArray(String imagePath) {
        byte[] imageBytes = null;
        Path path = Paths.get(imagePath);

        try {
            imageBytes = Files.readAllBytes(path); // קריאת תמונה כמערך של בייטים
            if (imageBytes != null) {
                System.out.println("Picture transfer to byte[] successfully.");
            } else {
                System.out.println("Error to transfer picture to byte[].");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageBytes;
    }

    public AppError saveImage(){
        try{
            //url התחבר לשרת של הפייתון
            URL url = new URL("http://localhost:8085/path");
            //יצירת חיבור לשרת הנוסף
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //הגדרת הבקשה
            con.setRequestMethod("POST");

            int status = con.getResponseCode();
            System.out.println("HTTP Status Code: " + status);

            if (status == HttpURLConnection.HTTP_OK) {

            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        return AppError.GOOD;
    }


}
