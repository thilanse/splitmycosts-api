package com.example.splitmycostsapi.models;

import com.example.splitmycostsapi.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<UserEntity> contributors;

    @JsonIgnore
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<Expense> expenses;

    @JsonIgnore
    @ManyToOne
    private UserEntity owner;

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    public Event(String name, UserEntity owner) {
        this.name = name;
        this.owner = owner;
        this.contributors = new ArrayList<>();
    }

    public Event(String name, List<UserEntity> contributors, UserEntity owner) {
        this.name = name;
        this.contributors = contributors;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getContributors() {
        return contributors;
    }

    public void setContributors(List<UserEntity> contributors) {
        this.contributors = contributors;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public void addContributor(UserEntity contributor) {
        this.contributors.add(contributor);
    }
}
