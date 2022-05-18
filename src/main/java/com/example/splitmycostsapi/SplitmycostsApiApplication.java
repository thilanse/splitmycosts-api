package com.example.splitmycostsapi;

import com.example.splitmycostsapi.user.UserEntity;
import com.example.splitmycostsapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SplitmycostsApiApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {

		SpringApplication.run(SplitmycostsApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		UserEntity user = new UserEntity("Thilan", "Senanayake", "thilanse", "thilansenanayake@gmail.com", "t321");
//		userRepository.save(user);
	}
}
