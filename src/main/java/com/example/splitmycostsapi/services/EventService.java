package com.example.splitmycostsapi.services;

import com.example.splitmycostsapi.models.Event;
import com.example.splitmycostsapi.repositories.EventRepository;
import com.example.splitmycostsapi.user.UserEntity;
import com.example.splitmycostsapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventsForUser(String email){
        // Todo: Fetch actual events from db for the logged in user

        UserEntity user = userRepository.findByEmail(email).orElseThrow();

        return user.getEvents();
    }

    public Event createEvent(String eventName, String email){
        UserEntity user = userRepository.findByEmail(email).orElseThrow();

        Event event = new Event(eventName, user);

        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, String eventName, String email) {

        Event event = eventRepository.findById(id).orElseThrow();

        if (!event.getOwner().getEmail().equals(email)){
            // Todo: throw exception
            return null;
        }

        event.setName(eventName);

        return eventRepository.save(event);
    }

    public Optional<Event> getEventById(Long id) {

        return eventRepository.findById(id);
    }

    public String deleteEvent(Long id, String email) {

        Event event = eventRepository.findById(id).orElseThrow();

        if (!event.getOwner().getEmail().equals(email)){
            // Todo: throw exception
            return "Failed";
        }

        eventRepository.delete(event);

        return "Success";
    }
}
