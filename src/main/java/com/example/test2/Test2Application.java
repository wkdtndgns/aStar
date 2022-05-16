package com.example.test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Test2Application {
    public static void main(String[] args) {
        System.out.println("test1123");
        SpringApplication.run(Test2Application.class, args);
    }

}
