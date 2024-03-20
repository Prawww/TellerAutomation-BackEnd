package com.example.Teller_Automation.BACKEND.CustomerModule.Transaction;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("withdrawal")
public class Withdrawal extends Transaction {
    private double amount;

//  public Withdrawal() {
//    super();
//  }

    // Getters and setters for specific properties
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

