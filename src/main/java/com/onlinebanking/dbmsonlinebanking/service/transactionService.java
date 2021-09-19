package com.onlinebanking.dbmsonlinebanking.service;

import com.onlinebanking.dbmsonlinebanking.dao.transactionDao;
import com.onlinebanking.dbmsonlinebanking.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //public primaryAccountTransaction

}
