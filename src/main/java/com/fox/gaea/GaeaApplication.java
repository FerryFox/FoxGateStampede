package com.fox.gaea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GaeaApplication {

	public static void main(String[] args)
	{
		System.out.println("Run Git Action!");
		SpringApplication.run(GaeaApplication.class, args);
	}

}
