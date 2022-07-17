package com.example.splitmycostsapi.controllers;

import com.example.splitmycostsapi.models.Event;
import com.example.splitmycostsapi.models.requestmodels.CreateEventRequest;
import com.example.splitmycostsapi.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Event> getEvents(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return eventService.getEventsForUser(user.getUsername());
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Optional<Event> event = eventService.getEventById(id);

        if (event.isEmpty()){
            return null;
        }

        if (!event.get().getOwner().getEmail().equals(user.getUsername())){
            return null;
        }

        return event.get();
    }

    @PostMapping
    public Event createEvent(@RequestBody CreateEventRequest createEventRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return eventService.createEvent(
                createEventRequest.getName(), createEventRequest.getContributors(), user.getUsername()
        );
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return eventService.updateEvent(id, event.getName(), user.getUsername());
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return eventService.deleteEvent(id, user.getUsername());
    }
}
