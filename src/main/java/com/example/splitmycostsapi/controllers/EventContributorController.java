package com.example.splitmycostsapi.controllers;

import com.example.splitmycostsapi.models.Event;
import com.example.splitmycostsapi.models.requestmodels.AddContributorRequest;
import com.example.splitmycostsapi.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventContributorController {

    @Autowired
    private EventService eventService;

    @PostMapping("/events/{eventId}/contributors")
    public Event addContributor(@PathVariable Long eventId, @RequestBody AddContributorRequest addContributorRequest){

        return eventService.addContributor(eventId, addContributorRequest.getContributorId());
    }
}
