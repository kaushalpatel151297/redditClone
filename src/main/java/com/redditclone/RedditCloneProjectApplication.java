package com.redditclone;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RedditCloneProjectApplication {



	public static void main(String[] args) {
		SpringApplication.run(RedditCloneProjectApplication.class, args);
	}

@Bean
	ModelMapper modelmapper()
{
	return new ModelMapper();
}

}
