package com.example.splitmycostsapi.controllers;

import com.example.splitmycostsapi.models.Event;
import com.example.splitmycostsapi.models.requestmodels.CreateEventRequest;
import com.example.splitmycostsapi.services.EventService;
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
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        List<Event> events = eventService.getEventsForUser(user.getUsername());

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Optional<Event> event = eventService.getEventById(id);

        if (event.isEmpty()){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }

        if (!event.get().getOwner().getEmail().equals(user.getUsername())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(event.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest createEventRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Event createdEvent =  eventService.createEvent(
                createEventRequest.getName(), createEventRequest.getContributors(), user.getUsername()
        );

        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Optional<Event> event = eventService.getEventById(id);

        if (event.isEmpty()){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }

        if (!event.get().getOwner().getEmail().equals(user.getUsername())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(
                eventService.updateEvent(event.get(), updatedEvent),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Optional<Event> event = eventService.getEventById(id);

        if (event.isEmpty()){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }

        if (!event.get().getOwner().getEmail().equals(user.getUsername())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(
                eventService.deleteEvent(event.get()),
                HttpStatus.OK
        );
    }
}
