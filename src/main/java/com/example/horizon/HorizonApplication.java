package com.example.horizon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class HorizonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorizonApplication.class, args);
	}

}
