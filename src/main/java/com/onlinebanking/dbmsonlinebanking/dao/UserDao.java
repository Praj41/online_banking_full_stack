package com.onlinebanking.dbmsonlinebanking.dao;

import com.onlinebanking.dbmsonlinebanking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getUser_id(),
                user.getEmail(),
                user.isEnabled(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getPassword(),
                user.getPhone(),
                user.getUsername(),
                user.getPrimary_account_id(),
                user.getSavings_account_id());
        return 1;
    }

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

    public int disableUserById(Long user_id) {
        return 0;
    }

    public int updateUserById(Long user_id, User user) {
        return 0;
    }
}
