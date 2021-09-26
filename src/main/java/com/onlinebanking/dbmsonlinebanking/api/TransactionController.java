package com.onlinebanking.dbmsonlinebanking.api;

import com.onlinebanking.dbmsonlinebanking.domain.Transaction;
import com.onlinebanking.dbmsonlinebanking.domain.TransactionBtwUser;
import com.onlinebanking.dbmsonlinebanking.service.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/transaction")
@RestController
@CrossOrigin
public class TransactionController {
    private final transactionService transactionService;

    @Autowired
    public TransactionController(com.onlinebanking.dbmsonlinebanking.service.transactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path = "PtoOut")
    public Transaction sendMoneyOut(@RequestBody Transaction PAT) {
        return transactionService.sendMoneyPtoOut(PAT);
    }

    @PostMapping(path = "Pdeposit")
    public Transaction Pdeposit(@RequestBody Transaction PAT) {
        return transactionService.primaryDeposit(PAT);
    }

    @GetMapping(path = "{id}")
    public List<Transaction> getTransactions(@PathVariable("id") Long accountNo) {
        return transactionService.selectTransactionsByAccno(accountNo);
    }

    @PostMapping(path = "transfer")
    public TransactionBtwUser UserToUser(@RequestBody TransactionBtwUser trans){
        return transactionService.btwUser(trans);
    }

    @GetMapping(path = "utu/{id}")
    public List<TransactionBtwUser> getTransactionsBtwUsr(@PathVariable("id") Long accountNo) {
        return transactionService.selectTransactionsBtwUsrByAccno(accountNo);
    }

}
