package com.ldxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ldxx.dao")
public class LqtProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(LqtProjectApplication.class, args);
	} 
}
 