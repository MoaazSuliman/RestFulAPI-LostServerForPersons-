package com.moaaz.lost;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LostApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostApplication.class, args);
        System.out.println("Moaaz is The Best Java Developer.! ==> That Not A Question.");
        System.out.println("Or I'll Be If I Married 5 women.! ==> From Moaaz.");

        System.out.println("Project Path ==> ");
        System.out.println(System.getProperty("user.dir"));
        System.out.println("Moaaz Will Go To The Hell!.");

    }

}
