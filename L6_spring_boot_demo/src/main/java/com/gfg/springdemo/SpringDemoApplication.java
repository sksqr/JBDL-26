package com.gfg.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringDemoApplication.class);

	public static void main(String[] args) {
//		logger.debug("Starting application in debug log level");
		logger.info("Starting application");
		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
