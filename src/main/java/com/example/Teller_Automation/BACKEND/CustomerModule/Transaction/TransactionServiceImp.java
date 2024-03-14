package com.example.Teller_Automation.BACKEND.CustomerModule.Transaction;

import com.example.Teller_Automation.BACKEND.CustomerModule.Utils.EntityResponse;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImp implements TransactionService {

    private final TransactionRepo transactionRepo;
    @Autowired
    public TransactionServiceImp(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public EntityResponse<?> create(Transaction transaction){
        EntityResponse<Transaction> res = new EntityResponse<>();
        try{
            Transaction trans = transactionRepo.save(transaction);
            res.setMessage("Transaction created successfully");
            res.setStatusCode(HttpStatus.CREATED.value());
            res.setEntity(trans);
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
    public EntityResponse<?> findById(Long id){

        EntityResponse<Transaction> res = new EntityResponse<>();
        try {
            if(id == null){
                res.setMessage("Please enter valid ");
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
                res.setEntity(null);
            }
            Optional<Transaction> t = transactionRepo.findById(id);
            if(t.isPresent()){
                Transaction trans = t.get();
                res.setMessage("Transaction " + trans.getTransactionId() + " is retrieved successfully");
                res.setStatusCode(HttpStatus.FOUND.value());
                res.setEntity(trans);
            }
            else{
                res.setMessage("Transaction is not found");
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
    public EntityResponse<?> modify(Transaction transaction) {
            EntityResponse<Transaction> res = new EntityResponse<>();
            try {
                Optional<Transaction> trans = transactionRepo.findById(transaction.getId());

                if(trans.isPresent()){
                    Transaction t = trans.get();

                    t.setAmount(transaction.getAmount());
                    transactionRepo.save(t);
                    res.setMessage("Transaction details updated successfully");
                    res.setStatusCode(HttpStatus.RESET_CONTENT.value());
                    res.setEntity(t);

                }
                else{
                    res.setMessage("Transaction not found.");
                    res.setStatusCode(HttpStatus.NOT_FOUND.value());
                    res.setEntity(null);
                }
            }
            catch(Exception e){
                Log.error("Exception {}" + e);
                res.setMessage("Error was encountered");
                res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                res.setEntity(null);
            }
            return res;
    }

}
