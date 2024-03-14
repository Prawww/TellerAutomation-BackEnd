package com.example.Teller_Automation.BACKEND.CustomerModule.account;

import com.example.Teller_Automation.BACKEND.CustomerModule.Transaction.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor


public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String accno;
    private double balance;

    public Account(String accno, double balance) {

        this.accno = accno;
        this.balance  = balance;
    }
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Set<Transaction> transaction = new HashSet<>();

}


