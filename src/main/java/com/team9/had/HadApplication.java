package com.team9.had;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HadApplication {

    public static void main(String[] args)  {
        SpringApplication.run(HadApplication.class, args);
    }

}
