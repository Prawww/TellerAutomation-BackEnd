package com.example.Teller_Automation.BACKEND.CustomerModule.account;

import com.example.Teller_Automation.BACKEND.AdminModule.Teller.Teller;
import com.example.Teller_Automation.BACKEND.AdminModule.Teller.TellerRepo;
import com.example.Teller_Automation.BACKEND.CustomerModule.Transaction.Deposit;
import com.example.Teller_Automation.BACKEND.CustomerModule.Transaction.Transaction;
import com.example.Teller_Automation.BACKEND.CustomerModule.Transaction.TransactionRepo;
import com.example.Teller_Automation.BACKEND.CustomerModule.Transaction.Withdrawal;
import com.example.Teller_Automation.BACKEND.CustomerModule.Utils.EntityResponse;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImp implements AccountService{

    AccountRepo accountRepo;
    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    TellerRepo tellerRepo;


    @Autowired
    public AccountServiceImp(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }


    @Override
    public EntityResponse<?> create(Account account) {
        EntityResponse<Account> res = new EntityResponse<>();
        try{
            Account acc = accountRepo.save(account);
            res.setMessage("Account created successfully");
            res.setStatusCode(HttpStatus.CREATED.value());
            res.setEntity(acc);
        }
        catch(Exception e){
            Log.error("Exception {}" + e);
            res.setMessage("Error encountered");
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setEntity(null);
        }
        return res;
    }

    @Override
    public EntityResponse<?> findById(Long id) {
        EntityResponse<Account> res = new EntityResponse<>();
        try {
            if(id == null){
                res.setMessage("Please enter valid ");
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                res.setEntity(null);
            }
            Optional<Account> a = accountRepo.findById(id);
            if(a.isPresent()){
                Account acc = a.get();
                res.setMessage("Account " + acc.getAccno() + " is retrieved successfully");
                res.setStatusCode(HttpStatus.FOUND.value());
                res.setEntity(acc);
            }
            else{
                res.setMessage("Account is not found");
                res.setStatusCode(HttpStatus.NOT_FOUND.value());
                res.setEntity(null);
            }
        }
        catch (Exception e){
            res.setMessage("Error was encountered");
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setEntity(null);
        }
        return res;
    }

    @Override
    public EntityResponse<?> findAll() {
        return null;

    }

    @Override
    public EntityResponse<?> addTransaction(Long id, Transaction transaction) {
        EntityResponse<Transaction> res = new EntityResponse<>();
        try{
            if(id == null){
                res.setMessage("Please enter valid id");
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                res.setEntity(null);
            }

            Optional<Account> acc = accountRepo.findById(id);
            if(acc.isPresent()){
                Account a = acc.get();

                if(a.getTransaction() == null){
                    Set<Transaction> t = new HashSet<>();
                    t.add(transaction);
                    a.setTransaction(t);
                }
                else{
                    a.getTransaction().add(transaction);
                }
                accountRepo.save(a);
                res.setMessage("Transaction " + a.getTransaction() + " on " + a.getAccno() + " was initiated successfully");
                res.setStatusCode(HttpStatus.CREATED.value());
                res.setEntity(transaction);

            }
            else{
                res.setMessage("Account not found");
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                res.setEntity(null);
            }

        }
        catch (Exception e){
            Log.error("Exception {}" + e);
            res.setMessage("Error was encountered");
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setEntity(null);

        }
        return res;
    }
    @Override
    public EntityResponse<?> findTransaction(Long id){
        EntityResponse<Set<Transaction>> res = new EntityResponse<>();
        try{
            if(id <=0 ){
                res.setMessage("Please enter valid id");
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                res.setEntity(null);
            }
            Optional<Account> acc = accountRepo.findById(id);

            if(acc.isPresent()){
                Account a = acc.get();
                res.setMessage("Transactions from account " + a.getAccno() + " were fetched successfully");
                res.setStatusCode(HttpStatus.FOUND.value());
                res.setEntity(a.getTransaction());
            }
            else{
                res.setMessage("Transactions not found" );
                res.setStatusCode(HttpStatus.NOT_FOUND.value());
                res.setEntity(null);
            }
        }
        catch (Exception e){
            res.setMessage("Error was encountered");
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setEntity(null);
        }
        return res;
    }
    @Override
    public EntityResponse<?> deposit(Long id, Transaction transaction) {
        EntityResponse<Account> res = new EntityResponse<>();
        try {
            if(id <=0 && transaction == null){
                res.setMessage("Please enter valid transaction details");
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                res.setEntity(null);
            }
            else {
                Optional<Account> acc = accountRepo.findById(id);
                if (acc.isPresent()) {
                    Account a = acc.get();


                    if(a.getTransaction()==null){
                        Set<Transaction> s= new HashSet<>();
                        s.add(transaction);
                        a.setTransaction(s);}
                    else{
                        a.getTransaction().add(transaction);
                    }
                    Account save = accountRepo.save(a);
                    res.setMessage("Deposit pending");
                    res.setStatusCode(HttpStatus.OK.value());
                    res.setEntity(save);
                    if(transaction.isCompleted()){

                        double total = a.getBalance() + transaction.getAmount();
                        a.setBalance(total);
                        Account t = accountRepo.save(a);
                    }else{
                        res.setMessage("Transaction not approved");
                        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                        res.setEntity(null);
                    }
                }else{
                    res.setMessage("Account not found");
                    res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    res.setEntity(null);
                }
            }
        }
        catch(Exception e){
            Log.error("Exception {}" + e);
            res.setMessage("Error encountered");
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setEntity(null);
        }
        return res;
    }
    @Override
    public EntityResponse<?> depositReq(Long id, Deposit deposit) {
        EntityResponse<Transaction> res = new EntityResponse<>();
        try {
            if(id <=0 && deposit == null ){
                res.setMessage("Please enter valid transaction details");
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                res.setEntity(null);
            }
            else {
                Optional<Account> acc = accountRepo.findById(id);
                if (acc.isPresent()) {
                    Account a = acc.get();

                    deposit.setCompleted(false);

                    if(a.getTransaction()==null){
                        Set<Transaction> s= new HashSet<>();
                        s.add(deposit);
                        a.setTransaction(s);}
                    else{
                        a.getTransaction().add(deposit);
                    }
                    Account save = accountRepo.save(a);
                    res.setMessage("Deposit request successful");
                    res.setStatusCode(HttpStatus.OK.value());
                    res.setEntity(deposit);

                }else{
                    res.setMessage("Account not found");
                    res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    res.setEntity(null);
                }
            }
        }
        catch(Exception e){
            Log.error("Exception {}" + e);
            res.setMessage("Error encountered");
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setEntity(null);
        }
        return res;
    }


    @Override
    public EntityResponse<?> withdrawReq(Long id, Withdrawal withdrawal) {
        EntityResponse<Transaction> res = new EntityResponse<>();
        try{
            if(id <= 0){
                res.setMessage("Please enter valid details");
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                res.setEntity(null);
            }
            else{
                Optional<Account> acc = accountRepo.findById(id);
                if(acc.isPresent()){
                    Account a = acc.get();
                    double balance = a.getBalance();
                    if(balance > withdrawal.getAmount()) {



                        if(a.getTransaction() == null){
                            Set<Transaction> setT = new HashSet<>();
                            setT.add(withdrawal);
                            a.setTransaction(setT);
                        }
                        else{
                            a.getTransaction().add(withdrawal);
                        }
                        accountRepo.save(a);
                        res.setMessage("Withdrawal request successful");
                        res.setStatusCode(HttpStatus.CREATED.value());
                        res.setEntity(withdrawal);

                    }
                    else{
                        res.setMessage("Insufficient balance");
                        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                        res.setEntity(null);
                    }

                }
                else{
                    res.setMessage("Account not found");
                    res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    res.setEntity(null);
                }
            }
        }
        catch (Exception e){
            res.setMessage("Error encountered");
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setEntity(null);
        }

        return res;
    }

    public EntityResponse<?> findByAccno(Long accno){
        EntityResponse<Account> res = new EntityResponse<>();
        try{
            Optional<Account> acc = accountRepo.findByAccno(accno);

            if(acc.isPresent()) {
                Account a = acc.get();
                res.setMessage("Account " + accno + " is retrieved successfully");
                res.setStatusCode(HttpStatus.FOUND.value());
                res.setEntity(a);
            }
            else{
                res.setMessage("Account is not found");
                res.setStatusCode(HttpStatus.NOT_FOUND.value());
                res.setEntity(null);
            }
        }
        catch (Exception e){
            res.setMessage("Error was encountered");
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setEntity(null);
        }
        return res;
    }
    @Override
    public EntityResponse<?> approve(Long tellerid, Long transid) {
        EntityResponse<Transaction> res = new EntityResponse<>();
        try {
            Optional<Transaction> transaction = transactionRepo.findById(transid);
            Optional<Teller> teller = tellerRepo.findById(tellerid);

            if (transaction.isPresent() && teller.isPresent()) {
                Transaction trans = transaction.get();
                res.setMessage("Transaction found");
                res.setStatusCode(HttpStatus.FOUND.value());
                res.setEntity(trans);

                Optional<Account> acc = accountRepo.findById(trans.getAccount_id());
                if (acc.isPresent()) {
                    Account a = acc.get();

                    if (trans.isCompleted()) {
                        res.setMessage("Transaction is already complete");
                        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                        res.setEntity(null);
                    } else {
                        trans.setCompleted(true);
                        trans.setTeller_id(tellerid);

                        if (trans instanceof Withdrawal) {
                            double total = a.getBalance() - trans.getAmount();
                            a.setBalance(total);
                        } else if (trans instanceof Deposit) {
                            double total = a.getBalance() + trans.getAmount();
                            a.setBalance(total);
                        }
                        // Save both transaction and account changes
                        accountRepo.save(a);
                        transactionRepo.save(trans);
                    }
                } else {
                    res.setMessage("Associated account not found");
                    res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    res.setEntity(null);
                }
            } else {
                res.setMessage("Transaction or teller not found");
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                res.setEntity(null);
            }
        } catch (Exception e) {
            res.setMessage("Error encountered");
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setEntity(null);
        }
        return res;
    }

}
