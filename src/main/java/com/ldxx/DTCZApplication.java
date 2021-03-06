package com.ldxx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(
		exclude = {
				DataSourceAutoConfiguration.class
		}
)
@MapperScan("com.ldxx.dao")
public class DTCZApplication {
	public static void main(String[] args) {
		SpringApplication.run(DTCZApplication.class, args);
	} 
}