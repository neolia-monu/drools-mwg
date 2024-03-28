package org.monu.droolsmwg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DroolsMwgApplication {

	static Logger logger = LoggerFactory.getLogger(DroolsMwgApplication.class);

	public static void main(String[] args) {
		logger.info("==== Started the Main Application Class ====");
		SpringApplication.run(DroolsMwgApplication.class, args);
	}

}
