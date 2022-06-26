package com.letscode.commentNoteSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CommentNoteSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentNoteSystemApplication.class, args);
	}

}
