package com.smartvillage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartVillageBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartVillageBackendApplication.class, args);
		System.err.println("Smart Village Backend application started sucessfully..");
	}

}
