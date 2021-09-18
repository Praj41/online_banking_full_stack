package com.onlinebanking.dbmsonlinebanking.dao;

import com.onlinebanking.dbmsonlinebanking.domain.primaryAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class primaryAccountDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public primaryAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createAcc(double default_balance) {
        String sql = "CALL insertpri(?)";
        jdbcTemplate.update(sql, Double.toString(default_balance));
        return 1;
    }

    public primaryAccount getAccountDetailsById(Long accountId) {

        String sql = "SELECT * FROM primary_account WHERE account_number = ?";
        primaryAccount primaryAcc = null;

        try {
            primaryAcc = jdbcTemplate.queryForObject(sql,
                    (resultSet, i) -> {
                        return new primaryAccount(
                                resultSet.getLong("user_id"),
                                Double.parseDouble(resultSet.getString("account_balance")),
                                resultSet.getLong("account_number"));
                    }, accountId);
        } catch (EmptyResultDataAccessException exc) {
            return null;
        }
        return primaryAcc;
    }
}
