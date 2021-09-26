package com.onlinebanking.dbmsonlinebanking.service;

import com.onlinebanking.dbmsonlinebanking.dao.primaryAccountDao;
import com.onlinebanking.dbmsonlinebanking.domain.User;
import com.onlinebanking.dbmsonlinebanking.domain.primaryAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class accountService {

    private final primaryAccountDao primaryAccountDao;

    private final userService userService;

    private static final double default_balance = 5000.00;

    @Autowired
    public accountService(primaryAccountDao primaryAccountDao, userService userService) {
        this.primaryAccountDao = primaryAccountDao;
        this.userService = userService;
    }

    public boolean empty(User user) {
        return user.getEmail().isEmpty() ||
                user.getUsername().isEmpty() ||
                user.getPassword().isEmpty();
    }

    public int createAccount(User user) {
        if (empty(user))
            return 0;
        user.setEnabled(true);
        primaryAccountDao.createAcc(default_balance);
        userService.addUser(user);
        return 1;
    }

    public String authLogin(User user) {
        Long uid = userService.getUserByUsernamePass(user.getUsername(), user.getPassword());
        if (uid == 0) {
            return "0";
        } else {
            return uid.toString();
        }
    }

    public User selectUser(Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.orElse(null);
    }

    public primaryAccount getPInfoById(Long accountId) {
        primaryAccount pri = primaryAccountDao.getAccountDetailsById(accountId);
        if (pri == null)
            return new primaryAccount(0L, 0, 0L);
        else
            return pri;
    }
}
