package com.example.demoSHIFT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DemoShiftApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoShiftApplication.class, args);
	}
}
