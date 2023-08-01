package com.teamtag.tagweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.teamtag.tagweb.board"})
public class TagwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagwebApplication.class, args);
	}

}
