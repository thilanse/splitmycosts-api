package com.example.splitmycostsapi.controllers;

import com.example.splitmycostsapi.models.Event;
import com.example.splitmycostsapi.models.Expense;
import com.example.splitmycostsapi.repositories.EventRepository;
import com.example.splitmycostsapi.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ExpenseController {

    @Autowired
    public EventRepository eventRepository;

    @Autowired
    public ExpenseRepository expenseRepository;

    @GetMapping("/events/{eventId}/expenses")
    public ResponseEntity<?> getExpenses(@PathVariable Long eventId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Optional<Event> eventOptional = eventRepository.findById(eventId);

        if (eventOptional.isEmpty()){
            return new ResponseEntity<>(
                    String.format("Event with id:%s not found.", eventId),
                    HttpStatus.NOT_FOUND
                    );
        }

        Event event = eventOptional.get();

        if (!event.getOwner().getEmail().equals(user.getUsername())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(event.getExpenses(),HttpStatus.OK);
    }

    @GetMapping("/events/{eventId}/expenses/{expenseId}")
    public ResponseEntity<?> getExpense(@PathVariable Long eventId, @PathVariable Long expenseId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Optional<Event> eventOptional = eventRepository.findById(eventId);

        if (eventOptional.isEmpty()){
            return new ResponseEntity<>(
                    String.format("Event with id:%s not found.", eventId),
                    HttpStatus.NOT_FOUND
            );
        }

        Event event = eventOptional.get();

        if (!event.getOwner().getEmail().equals(user.getUsername())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        List<Expense> expenses = event.getExpenses();

        for (Expense exp: expenses){
            if (exp.getId().equals(expenseId)){
                return new ResponseEntity<>(exp, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(
                String.format("Event (id:%s) does not have Expense (id:%s)", eventId, expenseId),
                HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/events/{eventId}/expenses")
    public ResponseEntity<?> createExpense(@PathVariable Long eventId, @RequestBody Expense newExpense){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Optional<Event> eventOptional = eventRepository.findById(eventId);

        if (eventOptional.isEmpty()){
            return new ResponseEntity<>(
                    String.format("Event with id:%s not found.", eventId),
                    HttpStatus.NOT_FOUND
            );
        }

        Event event = eventOptional.get();

        if (!event.getOwner().getEmail().equals(user.getUsername())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        Expense expense = new Expense(newExpense.getName(), event);

        return new ResponseEntity<>(expenseRepository.save(expense), HttpStatus.CREATED);
    }

    @PutMapping("/events/{eventId}/expenses/{expenseId}")
    public ResponseEntity<?> updateExpense(
            @PathVariable Long eventId, @PathVariable Long expenseId, @RequestBody Expense newExpense
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Optional<Event> eventOptional = eventRepository.findById(eventId);

        if (eventOptional.isEmpty()){
            return new ResponseEntity<>(
                    String.format("Event with id:%s not found.", eventId),
                    HttpStatus.NOT_FOUND
            );
        }

        Event event = eventOptional.get();

        if (!event.getOwner().getEmail().equals(user.getUsername())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        Optional<Expense> expenseOptional = event.getExpenses()
                .stream().filter(e -> e.getId().equals(expenseId)).findFirst();

        if (expenseOptional.isEmpty()){
            return new ResponseEntity<>(
                    String.format("Expense (id:%s) not found", expenseId), HttpStatus.NOT_FOUND
            );
        }

        Expense expense = expenseOptional.get();
        expense.setName(newExpense.getName());

        return new ResponseEntity<>(expenseRepository.save(expense), HttpStatus.OK);
    }

    @DeleteMapping("/events/{eventId}/expenses/{expenseId}")
    public ResponseEntity<?> deleteExpense(
            @PathVariable Long eventId, @PathVariable Long expenseId
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Optional<Event> eventOptional = eventRepository.findById(eventId);

        if (eventOptional.isEmpty()){
            return new ResponseEntity<>(
                    String.format("Event with id:%s not found.", eventId),
                    HttpStatus.NOT_FOUND
            );
        }

        Event event = eventOptional.get();

        if (!event.getOwner().getEmail().equals(user.getUsername())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        Optional<Expense> expenseOptional = event.getExpenses()
                .stream().filter(e -> e.getId().equals(expenseId)).findFirst();

        if (expenseOptional.isEmpty()){
            return new ResponseEntity<>(
                    String.format("Expense (id:%s) not found", expenseId), HttpStatus.NOT_FOUND
            );
        }

        Expense expense = expenseOptional.get();
        expenseRepository.delete(expense);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
