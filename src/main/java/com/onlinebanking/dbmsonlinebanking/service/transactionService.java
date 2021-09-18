package com.onlinebanking.dbmsonlinebanking.service;

import com.onlinebanking.dbmsonlinebanking.dao.transactionDao;
import com.onlinebanking.dbmsonlinebanking.domain.primaryAccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class transactionService {
    private final transactionDao transactionDao;

    @Autowired
    public transactionService(com.onlinebanking.dbmsonlinebanking.dao.transactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }


    public primaryAccountTransaction sendMoneyPtoOut(primaryAccountTransaction pat) {
        return transactionDao.primaryToExternal(pat);
    }

    public primaryAccountTransaction primaryDeposit(primaryAccountTransaction pat) {
        return transactionDao.primaryDeposit(pat);
    }

    //public primaryAccountTransaction

}
