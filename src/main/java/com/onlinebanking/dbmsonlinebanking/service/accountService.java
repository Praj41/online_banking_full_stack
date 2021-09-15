package com.onlinebanking.dbmsonlinebanking.service;

import com.onlinebanking.dbmsonlinebanking.dao.primaryAccountDao;
import com.onlinebanking.dbmsonlinebanking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class accountService {

    private final primaryAccountDao primaryAccountDao;

    private final userService userService;

    private static long acc_no = 1121000000;
    private static final double default_balance = 5000.00;

    @Autowired
    public accountService(primaryAccountDao primaryAccountDao, userService userService) {
        this.primaryAccountDao = primaryAccountDao;
        this.userService = userService;
    }

    public int createAccount(User user) {
        user.setPrimary_account_id(acc_no);
        user.setPrimary_account_id(acc_no);
        primaryAccountDao.createAcc(default_balance, acc_no++);
        userService.addUser(user);
        return 1;
    }

}
