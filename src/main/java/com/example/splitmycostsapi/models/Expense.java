package com.example.splitmycostsapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal totalCost;

    @OneToMany(mappedBy = "expense")
    private List<Contribution> contributions;

    @JsonIgnore
    @ManyToOne
    private Event event;

    public Expense() {
    }

    public Expense(String name, Event event) {
        this.name = name;
        this.event = event;
        this.totalCost = new BigDecimal(0);
        this.contributions = new ArrayList<>();
    }

    public void calculateTotalCost(){
        this.totalCost =
                this.contributions.stream()
                        .map(Contribution::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addContribution(Contribution contribution){
        this.contributions.add(contribution);
        this.totalCost = this.totalCost.add(contribution.getAmount());
    }

    public void removeContribution(Contribution contribution){
        this.contributions.removeIf(c -> c.getId().equals(contribution.getId()));
        this.totalCost = this.totalCost.subtract(contribution.getAmount());
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

    public List<Contribution> getContributions() {
        return contributions;
    }

    public void setContributions(List<Contribution> contributions) {
        this.contributions = contributions;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
