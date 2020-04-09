package com.globant.springboot.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootExampleConsoleApplication implements CommandLineRunner {
	public static final Logger LOGGER = LoggerFactory.getLogger(SpringBootExampleConsoleApplication.class);
	public static void main(String[] args) {
		LOGGER.info("STARTING THE APPLICATION");
		SpringApplication.run(SpringBootExampleConsoleApplication.class, args);
		LOGGER.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("EXECUTING : command line runner");
		  
        for (int i = 0; i < 10; ++i) {
        	LOGGER.info("message {}:", i);
        }
		
	}

}
