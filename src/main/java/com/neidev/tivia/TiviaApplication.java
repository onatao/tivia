package com.neidev.tivia;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiviaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiviaApplication.class, args);
	}

}
