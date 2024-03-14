package com.example.Teller_Automation.BACKEND.CustomerModule.account;

import com.example.Teller_Automation.BACKEND.CustomerModule.Transaction.Transaction;
import com.example.Teller_Automation.BACKEND.CustomerModule.Utils.EntityResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountServiceImp accountServices;

    public AccountController(AccountServiceImp accountServices) {
        this.accountServices = accountServices;
    }

    @PostMapping("/create")
    public EntityResponse<?> create(Account account) {
        return accountServices.create(account);
    }

    @PostMapping("addTransaction")
    public EntityResponse<?> addTransaction(@RequestParam Long id, @RequestBody Transaction transaction) {
        return accountServices.addTransaction(id, transaction);
    }

    @GetMapping("/get")
    public EntityResponse<?> findById(@RequestParam Long id) {
        return accountServices.findById(id);
    }
    @GetMapping("/getTransaction")
    public EntityResponse<?> findTransaction(@RequestParam Long id){
        return accountServices.findTransaction(id);
    }

    @PostMapping("/deposit")
    public EntityResponse<?> deposit(@RequestParam Long id, @RequestBody Transaction transaction){
        return accountServices.deposit(id,transaction);
    }
}