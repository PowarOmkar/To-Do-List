package com.example.Task;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan("com.*.*")
public class Task {
	public static void main(String[] args) {
		SpringApplication.run(Task.class, args);
	}


}
