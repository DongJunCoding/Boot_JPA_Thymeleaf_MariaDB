package com.example.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BootJpaThymeleafMariaDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootJpaThymeleafMariaDbApplication.class, args);
	}

}
