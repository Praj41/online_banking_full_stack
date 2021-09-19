package com.onlinebanking.dbmsonlinebanking.dao;

import com.onlinebanking.dbmsonlinebanking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertUser(User user) {
        String sql = "CALL insertUser(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getEmail(),
                user.isEnabled(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getPassword(),
                user.getPhone(),
                user.getUsername());
        return 1;
    }

    public List<User> selectAllUser() {
        String sql = "SELECT * FROM customer";
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
                    resultSet.getLong("loan_account_id"));
        });
        return users;
    }

    public Optional<User> selectUserById(Long user_id) {
        String sql = "SELECT * FROM customer WHERE user_id = ?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql,
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
                                resultSet.getLong("loan_account_id"));
                    }, user_id);
        } catch (EmptyResultDataAccessException exp) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

    public int disableUserById(Long user_id) {
        return 0;
    }

    public int updateUserById(Long user_id, User user) {
        return 0;
    }

    public Optional<User> selectUserByUsername(String username) {
        String sql = "SELECT * FROM customer WHERE username = ?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql,
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
                                resultSet.getLong("loan_account_id"));
                    }, username);
        } catch (EmptyResultDataAccessException exc) {
            System.out.println("EmptyResultDataAccessException Handled");
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }
}
