package com.example.Teller_Automation.BACKEND.CustomerModule.Transaction;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
public class Transaction {
//
//    private Customer customer;
//
//    public PendingTransaction(Customer customer) {
//        this.customer = customer;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "")
    private String transactionId;;



    @Column(name = "")
    private double amount;

    @Column(name = "")
    private Date date;

    @Column(name = "")
    private boolean isCompleted = false;
    private Long teller_id;
    private Long account_id;


//    public boolean isCompleted(){
//        return completed;
//    }
//    public void setCompleted(boolean completed){
//        this.completed = completed;
//    }



}



