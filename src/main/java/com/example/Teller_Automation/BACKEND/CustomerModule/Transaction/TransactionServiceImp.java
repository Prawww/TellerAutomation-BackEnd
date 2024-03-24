package com.example.Teller_Automation.BACKEND.CustomerModule.Transaction;

import com.example.Teller_Automation.BACKEND.CustomerModule.Utils.EntityResponse;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public EntityResponse<?> getAll(){
        EntityResponse<List<Transaction>> response = new EntityResponse<>();
        try{
            List<Transaction>allTransactions = transactionRepo.findAll();
            if(!allTransactions.isEmpty()){
                response.setMessage("Transactions retrieved successfully");
                response.setStatusCode(HttpStatus.OK.value());
                response.setEntity(allTransactions);
            }
            else{
                response.setMessage("No transactions found");
                response.setStatusCode(HttpStatus.NO_CONTENT.value());
                response.setEntity(null);
            }
        }catch (Exception e){
            Log.error("An error occurred");
            response.setMessage("INTERNAL_SERVER_ERROR");
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setEntity(null);
        }
        return response;
    }

    @Override
    public EntityResponse<?> getWithdraw(){
        EntityResponse<List<Transaction>> response = new EntityResponse<>();
        try{
            List<Transaction> allTransactions = transactionRepo.findAll();
            if(!allTransactions.isEmpty()){
                for(Transaction trans : allTransactions){
                    List<Transaction> t= new ArrayList<>();
                    if(trans instanceof Withdrawal){
                        t.add(trans);
                        response.setMessage("Withdrawals retrieved successfully");
                        response.setStatusCode(HttpStatus.OK.value());
                        response.setEntity(t);
                    }
//                    else if(trans instanceof Deposit){
//                        t.add(trans);
//                        response.setMessage("Deposits retrieved successfully");
//                        response.setStatusCode(HttpStatus.OK.value());
//                        response.setEntity(t);
//                    }
                }

            }
            else{
                response.setMessage("No transactions found");
                response.setStatusCode(HttpStatus.NO_CONTENT.value());
                response.setEntity(null);
            }
        }catch (Exception e){
            Log.error("An error occurred");
            response.setMessage("INTERNAL_SERVER_ERROR");
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setEntity(null);
        }
        return response;
    }

    @Override
    public EntityResponse<?> getDeposit(){
        EntityResponse<List<Transaction>> response = new EntityResponse<>();
        try{
            List<Transaction> allTransactions = transactionRepo.findAll();
            if(!allTransactions.isEmpty()){
                for(Transaction trans : allTransactions){
                    List<Transaction> t= new ArrayList<>();

                    if(trans instanceof Deposit){
                        t.add(trans);
                        response.setMessage("Deposits retrieved successfully");
                        response.setStatusCode(HttpStatus.OK.value());
                        response.setEntity(t);
                    }
                }

            }
            else{
                response.setMessage("No transactions found");
                response.setStatusCode(HttpStatus.NO_CONTENT.value());
                response.setEntity(null);
            }
        }catch (Exception e){
            Log.error("An error occurred");
            response.setMessage("INTERNAL_SERVER_ERROR");
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setEntity(null);
        }
        return response;
    }

//    @Override
//    public EntityResponse<?> findByTransaction_Type(String transaction_type) {
//        EntityResponse<Transaction> res = new EntityResponse<>();
//        try{
//            Optional<Transaction> transaction = transactionRepo.findByTransaction_Type(transaction_type);
//
//            if(transaction.isPresent()){
//                Transaction t = transaction.get();
//
//                res.setMessage("Transaction found");
//                res.setStatusCode(HttpStatus.OK.value());
//                res.setEntity(t);
//            }
//            else{
//
//                res.setMessage("Transaction not found");
//                res.setStatusCode(HttpStatus.NOT_FOUND.value());
//                res.setEntity(null);
//            }
//        }
//        catch (Exception e){
//            res.setMessage("Error was encountered");
//            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            res.setEntity(null);
//        }
//        return res;
//    }
}
