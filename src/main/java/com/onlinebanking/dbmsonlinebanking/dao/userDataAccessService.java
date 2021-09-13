package com.onlinebanking.dbmsonlinebanking.dao;

import com.onlinebanking.dbmsonlinebanking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("mysql")
public class userDataAccessService implements userDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public userDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(Long user_id, User user) {
        return 0;
    }

    @Override
    public List<User> selectAllUser() {
        String sql = "SELECT * FROM user";
        List<User> users = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new User(
                    resultSet.getLong("user_id"),
                    resultSet.getString("email"),
                    resultSet.getBoolean("enabled"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("password"),
                    resultSet.getString("phone"),
                    resultSet.getString("username"),
                    resultSet.getLong("primary_account_id"),
                    resultSet.getLong("savings_account_id"));
        });
        return users;
    }

    @Override
    public Optional<User> selectUserById(Long user_id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        User user = jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> {
                    return new User(
                            resultSet.getLong("user_id"),
                            resultSet.getString("email"),
                            resultSet.getBoolean("enabled"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("password"),
                            resultSet.getString("phone"),
                            resultSet.getString("username"),
                            resultSet.getLong("primary_account_id"),
                            resultSet.getLong("savings_account_id"));
                }, user_id);
        return Optional.ofNullable(user);
    }

    @Override
    public int disableUserById(Long user_id) {
        return 0;
    }

    @Override
    public int updateUserById(Long user_id, User user) {
        return 0;
    }
}
