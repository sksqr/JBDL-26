package com.gfg.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringDemoApplication.class);

	public static void main(String[] args) {
//		logger.debug("Starting application in DEBUG log level");
		SpringApplication.run(SpringDemoApplication.class, args);
		logger.debug("Starting application in DEBUG log level after spring start");
		logger.info("Starting application in INFO log level after spring start");
		logger.trace("Starting application in TRACE log level after spring start");
		logger.warn("Starting application in WARN log level after spring start");
		logger.error("Starting application in ERROR log level after spring start");
	}

}
