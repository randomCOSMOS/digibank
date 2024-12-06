package com.appproject.digibank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigibankApplication {
	public static void main(String[] args) {
		SpringApplication.run(DigibankApplication.class, args);
		System.out.println("Server Running on 8080!");
	}

}
 