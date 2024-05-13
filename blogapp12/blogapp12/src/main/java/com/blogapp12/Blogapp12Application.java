package com.blogapp12;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Blogapp12Application {

	public static void main(String[] args) {
		SpringApplication.run(Blogapp12Application.class, args);
	}


	@Bean
	public ModelMapper getModelmapper()
	{
		return  new ModelMapper();
	}



}
