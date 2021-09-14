package com.onlinebanking.dbmsonlinebanking.service;

import com.onlinebanking.dbmsonlinebanking.dao.UserDao;
import com.onlinebanking.dbmsonlinebanking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userService {

    private final UserDao userDao;

    @Autowired
    public userService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUser();
    }

    public Optional<User> getUserById(Long user_id) {
        return userDao.selectUserById(user_id);
    }

    public int disableUserById(Long user_id) {
        return userDao.disableUserById(user_id);
    }

    public int updateUserById(Long user_id, User user) {
        return userDao.updateUserById(user_id, user);
    }
}
