package com.example.splitmycostsapi.services;

import com.example.splitmycostsapi.models.Event;
import com.example.splitmycostsapi.repositories.EventRepository;
import com.example.splitmycostsapi.user.UserEntity;
import com.example.splitmycostsapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Event createEvent(String eventName, List<Integer> contributorIds, String email){
        UserEntity user = userRepository.findByEmail(email).orElseThrow();

        List<UserEntity> contributors = new ArrayList<>();

        for (Integer contributorId: contributorIds) {
            UserEntity contributor = userRepository.findById(Long.valueOf(contributorId)).orElseThrow();
            contributors.add(contributor);
        }

        Event event = new Event(eventName, contributors, user);

        return eventRepository.save(event);
    }

    public Event updateEvent(Event existingEvent, Event updatedEvent) {

        existingEvent.setName(updatedEvent.getName());

        return eventRepository.save(existingEvent);
    }

    public Optional<Event> getEventById(Long id) {

        return eventRepository.findById(id);
    }

    public String deleteEvent(Event event) {

        eventRepository.delete(event);

        return "Success";
    }

    public Event addContributor(Long eventId, Long contributorId) {
        Event event = eventRepository.findById(eventId).orElseThrow();

        UserEntity contributor = userRepository.findById(contributorId).orElseThrow();

        event.addContributor(contributor);

        return eventRepository.save(event);
    }

    public Event removeContributor(Long eventId, Long contributorId) {

        Event event = eventRepository.findById(eventId).orElseThrow();

//        UserEntity contributor = userRepository.findById(contributorId).orElseThrow();

        event.getContributors().removeIf(
                e -> e.getId().equals(contributorId)
        );

        return eventRepository.save(event);

    }
}
