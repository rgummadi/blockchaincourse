package com.upgrad.ImageHoster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ImageHosterApplication {
	
	public static void main(String [] args) {
		SpringApplication.run(ImageHosterApplication.class, args);
}
	
}