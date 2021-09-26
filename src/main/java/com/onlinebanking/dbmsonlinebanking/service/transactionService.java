package com.onlinebanking.dbmsonlinebanking.service;

import com.onlinebanking.dbmsonlinebanking.dao.transactionDao;
import com.onlinebanking.dbmsonlinebanking.domain.Transaction;
import com.onlinebanking.dbmsonlinebanking.domain.TransactionBtwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class transactionService {
    private final transactionDao transactionDao;

    @Autowired
    public transactionService(com.onlinebanking.dbmsonlinebanking.dao.transactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }


    public Transaction sendMoneyPtoOut(Transaction pat) {
        return transactionDao.primaryToExternal(pat);
    }

    public Transaction primaryDeposit(Transaction pat) {
        return transactionDao.primaryDeposit(pat);
    }


    public List<Transaction> selectTransactionsByAccno(Long accountNo) {
        return transactionDao.selectTransactions(accountNo);
    }

    public TransactionBtwUser btwUser(TransactionBtwUser trans) {
        return transactionDao.btwUser(trans);
    }

    public List<TransactionBtwUser> selectTransactionsBtwUsrByAccno(Long accountNo) {
        return transactionDao.selectTransactionsBtwUsr(accountNo);
    }
}
