package com.example.javaserverapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaServerApiApplication {

    public static void main(String[] args) {
        System.out.println("Server ON");
        SpringApplication.run(JavaServerApiApplication.class, args);
        System.out.println("Server OFF");
    }

}
