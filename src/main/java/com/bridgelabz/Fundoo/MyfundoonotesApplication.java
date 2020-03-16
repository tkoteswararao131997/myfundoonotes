package com.bridgelabz.Fundoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableSwagger2
@Slf4j
public class MyfundoonotesApplication{

	public static void main(String[] args) {
		SpringApplication.run(MyfundoonotesApplication.class, args);
		//log.info("Simple log statement with inputs {}, {} and {}", 1, 2, 3);
	}
}
