package com.onlinebanking.dbmsonlinebanking.dao;

import com.onlinebanking.dbmsonlinebanking.domain.primaryAccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class transactionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public transactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public primaryAccountTransaction primaryToExternal(primaryAccountTransaction pat) {
        String sql = "CALL transactPE(?, ?)";
        jdbcTemplate.update(sql, pat.getAmount(), pat.getPrimaryAccountId());

        sql = "SELECT * FROM primary_transaction WHERE primary_account_id = ? ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> {
                    return new primaryAccountTransaction(
                            resultSet.getLong(1),
                            resultSet.getDouble(2),
                            (Double.parseDouble(resultSet.getString(3))),
                            resultSet.getTimestamp(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getLong(8));
                }, pat.getPrimaryAccountId());

    }

    public primaryAccountTransaction primaryDeposit(primaryAccountTransaction pat) {
        String sql = "CALL transactDeposit(?, ?)";
        jdbcTemplate.update(sql, pat.getAmount(), pat.getPrimaryAccountId());

        sql = "SELECT * FROM primary_transaction WHERE primary_account_id = ? ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> {
                    return new primaryAccountTransaction(
                            resultSet.getLong(1),
                            resultSet.getDouble(2),
                            (Double.parseDouble(resultSet.getString(3))),
                            resultSet.getTimestamp(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getLong(8));
                }, pat.getPrimaryAccountId());
    }
}
