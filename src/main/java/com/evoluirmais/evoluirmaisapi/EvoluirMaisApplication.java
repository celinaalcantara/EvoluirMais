package com.evoluirmais.evoluirmaisapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EvoluirMaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvoluirMaisApplication.class, args);
	}

}
