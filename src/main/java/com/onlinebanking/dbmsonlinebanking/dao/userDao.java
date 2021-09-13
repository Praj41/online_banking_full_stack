package com.onlinebanking.dbmsonlinebanking.dao;

import com.onlinebanking.dbmsonlinebanking.domain.User;

import java.util.List;
import java.util.Optional;

public interface userDao {

    int insertUser(Long user_id, User user);

    default int insertUser(User user) {
        return insertUser(user.getUser_id(), user);
    }

    List<User> selectAllUser();

    Optional<User> selectUserById(Long user_id);

    int disableUserById(Long user_id);

    int updateUserById(Long user_id, User user);
}
