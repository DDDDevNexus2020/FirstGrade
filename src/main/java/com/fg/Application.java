package com.fg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // SpringBoot annotation designating this class as the "root" class.
public class Application {

    public static void main(String[] args) { // Every Java program needs a main() method, no?
        SpringApplication.run(Application.class, args); // Let the magic begin!
    }

}
