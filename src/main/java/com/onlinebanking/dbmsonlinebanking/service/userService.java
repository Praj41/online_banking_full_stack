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

        Optional<User> user = userDao.selectUserById(user_id);

        return user;
    }

    public int disableUserById(Long user_id) {
        return userDao.disableUserById(user_id);
    }

    public int updateUserById(Long user_id, User user) {
        return userDao.updateUserById(user_id, user);
    }

    public Long getUserByUsernamePass(String username, String password) {
        Optional<User> user = userDao.selectUserByUsername(username);

        if (user.isPresent()) {
            if (user.get().getPassword().equals(password))
                return user.get().getUser_id();
            else
                return 0L;
        } else {
            return 0L;
        }
    }
}
