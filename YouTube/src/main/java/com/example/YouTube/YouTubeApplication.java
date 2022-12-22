package com.example.YouTube;

import com.example.YouTube.util.MD5Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YouTubeApplication {

	public static void main(String[] args) {
		System.out.println(MD5Util.MD5("1999"));
		SpringApplication.run(YouTubeApplication.class, args);


	}

}
