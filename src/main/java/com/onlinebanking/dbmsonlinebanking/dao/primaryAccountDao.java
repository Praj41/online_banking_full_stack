package com.onlinebanking.dbmsonlinebanking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class primaryAccountDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public primaryAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createAcc(double default_balance, long acc_no) {
        String sql = "INSERT INTO primary_account VALUES (?, ?)";
        jdbcTemplate.update(sql, default_balance,acc_no);
        return 1;
    }
}
