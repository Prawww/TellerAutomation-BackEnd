package com.example.Teller_Automation.BACKEND.CustomerModule.account;


import com.example.Teller_Automation.BACKEND.CustomerModule.Transaction.Transaction;
import com.example.Teller_Automation.BACKEND.CustomerModule.Utils.EntityResponse;

public interface AccountService {

    EntityResponse<?> create(Account account);

    EntityResponse<?> findById(Long id);

    EntityResponse<?> findAll();

    EntityResponse<?> addTransaction(Long id, Transaction transaction);
}
