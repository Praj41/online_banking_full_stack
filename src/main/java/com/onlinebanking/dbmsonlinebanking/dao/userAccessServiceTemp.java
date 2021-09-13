package com.onlinebanking.dbmsonlinebanking.dao;

import com.onlinebanking.dbmsonlinebanking.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("tempDao")
public class userAccessServiceTemp implements userDao{

    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(Long user_id, User user) {
        DB.add(new User(user.getUser_id(),
                        user.getEmail(),
                        user.isEnabled(),
                        user.getFirst_name(),
                        user.getLast_name(),
                        user.getPassword(),
                        user.getPhone(),
                        user.getUsername(),
                        user.getPrimary_account_id(),
                        user.getSavings_account_id()));
        return 1;
    }

    @Override
    public List<User> selectAllUser() {
        return DB;
    }

    @Override
    public Optional<User> selectUserById(Long user_id) {
        return DB.stream()
                .filter(user -> user.getUser_id().equals(user_id))
                .findFirst();
    }

    @Override
    public int disableUserById(Long user_id) {
        int indexOfUserToDisable = DB.indexOf(selectUserById(user_id).get());
        if (indexOfUserToDisable >= 0) {
            DB.get(indexOfUserToDisable).setEnabled(false);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateUserById(Long user_id, User user) {
        return selectUserById(user_id)
                .map(user1 -> {
                    int indexOfUserToUpdate = DB.indexOf(user1);
                    if (indexOfUserToUpdate >= 0) {
                        DB.set(indexOfUserToUpdate, user);
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }
}
