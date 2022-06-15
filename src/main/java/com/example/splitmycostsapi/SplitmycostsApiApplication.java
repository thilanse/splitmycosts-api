package com.example.splitmycostsapi;

import com.example.splitmycostsapi.models.Contribution;
import com.example.splitmycostsapi.models.Event;
import com.example.splitmycostsapi.models.Expense;
import com.example.splitmycostsapi.repositories.ContributionRepository;
import com.example.splitmycostsapi.repositories.EventRepository;
import com.example.splitmycostsapi.repositories.ExpenseRepository;
import com.example.splitmycostsapi.user.UserEntity;
import com.example.splitmycostsapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class SplitmycostsApiApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private ContributionRepository contributionRepository;

	public static void main(String[] args) {

		SpringApplication.run(SplitmycostsApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		UserEntity user = new UserEntity("Nisal", "Bulathsinghala", "bula", "nisal@gmail.com", "b321");
//		userRepository.save(user);
//
//		Event event = new Event("Mirissa Trip", user);
//		eventRepository.save(event);



//		Expense expense = expenseRepository.getById(1L);
//		UserEntity thilan = userRepository.getById(5L);
//
//		Contribution contribution = new Contribution(BigDecimal.valueOf(3000), thilan, expense);
//		contributionRepository.save(contribution);
	}
}
