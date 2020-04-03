package com.udacity.jwdnd.course1.cloudstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories("com.udacity.jwdnd.course1.cloudstorage.repositories")
@EntityScan("com.udacity.jwdnd.course1.cloudstorage.entities")
public class CloudStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}
}
