package com.example.splitmycostsapi.models;

import com.example.splitmycostsapi.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Contribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @OneToOne
    private UserEntity contributor;

    @JsonIgnore
    @ManyToOne
    private Expense expense;

    public Contribution() {
    }

    public Contribution(BigDecimal amount, UserEntity contributor, Expense expense) {
        this.amount = amount;
        this.contributor = contributor;
        this.expense = expense;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UserEntity getContributor() {
        return contributor;
    }

    public void setContributor(UserEntity contributor) {
        this.contributor = contributor;
    }
}
