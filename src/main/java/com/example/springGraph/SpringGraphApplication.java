package com.example.springGraph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringGraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGraphApplication.class, args);
	}

}
