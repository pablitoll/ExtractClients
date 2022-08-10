package com.rp.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ExtractPlataformaClientsApplication {

	public static void main(String[] args) {
		  for(String arg:args) {
	            System.out.println(arg);
	        }
		SpringApplication.run(ExtractPlataformaClientsApplication.class, args);
		

	}
}
