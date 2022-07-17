package com.example.splitmycostsapi.models.requestmodels;

import java.util.List;

public class CreateEventRequest {

    public String name;

    public List<Integer> contributors;

    public CreateEventRequest() {
    }

    public CreateEventRequest(String name, List<Integer> contributors) {
        this.name = name;
        this.contributors = contributors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getContributors() {
        return contributors;
    }

    public void setContributors(List<Integer> contributors) {
        this.contributors = contributors;
    }
}
