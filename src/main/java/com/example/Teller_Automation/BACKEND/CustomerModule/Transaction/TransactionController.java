package com.example.Teller_Automation.BACKEND.CustomerModule.Transaction;

import com.example.Teller_Automation.BACKEND.CustomerModule.Utils.EntityResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/Transaction")
public class TransactionController {

 private final TransactionService transactionServices;

    @Autowired
    public TransactionController(TransactionServiceImp transactionServices) {
        this.transactionServices = transactionServices;
    }

    @GetMapping("/get")
    public EntityResponse<?> findById(@RequestParam Long id){
        return transactionServices.findById(id);
    }

    @PostMapping("/create")
    public EntityResponse<?> create(@RequestBody Transaction transaction){
        return transactionServices.create(transaction);
    }


    @PutMapping("/modify")
    public EntityResponse<?> modify(@RequestBody Transaction transaction){
      return transactionServices.modify(transaction);
    }

    @GetMapping("/getAllTransactions")
    public EntityResponse<?> getAllTransactions() {
        return transactionServices.getAll();
    }

//    @GetMapping("/findByTransactionType")
//    public EntityResponse<?> findByTransType(String transaction_type){
//        return transactionServices.findByTransaction_Type(transaction_type);
//    }

    @GetMapping("/getWithdraws")
    public EntityResponse<?> getWithdraws(){
        return transactionServices.getWithdraw();
    }

    @GetMapping("/getDeposits")
    public EntityResponse<?> getDeposits(){
        return transactionServices.getDeposit();
    }
}
