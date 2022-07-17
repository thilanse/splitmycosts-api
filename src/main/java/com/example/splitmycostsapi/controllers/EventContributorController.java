package com.example.splitmycostsapi.controllers;

import com.example.splitmycostsapi.models.Event;
import com.example.splitmycostsapi.models.requestmodels.AddContributorRequest;
import com.example.splitmycostsapi.services.EventService;
import com.example.splitmycostsapi.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventContributorController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events/{eventId}/contributors")
    public List<UserEntity> getEventContributors(@PathVariable Long eventId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Event event = eventService.getEventById(eventId).orElseThrow();

        return event.getContributors();
    }

    @PostMapping("/events/{eventId}/contributors")
    public Event addContributor(@PathVariable Long eventId, @RequestBody AddContributorRequest addContributorRequest){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return eventService.addContributor(eventId, addContributorRequest.getContributorId());
    }

    @DeleteMapping("/events/{eventId}/contributors/{contributorId}")
    public Event removeContributor(
            @PathVariable Long eventId, @PathVariable Long contributorId
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return eventService.removeContributor(eventId, contributorId);

    }
}
