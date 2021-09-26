package com.onlinebanking.dbmsonlinebanking.service;

import com.onlinebanking.dbmsonlinebanking.dao.loanDao;
import com.onlinebanking.dbmsonlinebanking.domain.Transaction;
import com.onlinebanking.dbmsonlinebanking.domain.User;
import com.onlinebanking.dbmsonlinebanking.domain.loanAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loanService {

    private final loanDao loanDao;

    @Autowired
    public loanService(loanDao loanDao) {
        this.loanDao = loanDao;
    }

    public loanAccount createLoanAcc(Long userId) {
        return loanDao.createLoanAccount(userId);
    }


    public loanAccount getLoan(loanAccount loan) {
        return loanDao.getLoan(loan.getLoanTotal(), loan.getYears(), loan.getAccountNumber());
    }

    public Transaction payEMI(User user) {
        return loanDao.payEMI(user.getPrimary_account_id(), user.getLoan_account_id());
    }
}
