package com.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeingTestApplication.class, args);
    }

}
