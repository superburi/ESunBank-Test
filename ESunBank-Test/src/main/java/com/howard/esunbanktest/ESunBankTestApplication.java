package com.howard.esunbanktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ESunBankTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ESunBankTestApplication.class, args);
    }

}
