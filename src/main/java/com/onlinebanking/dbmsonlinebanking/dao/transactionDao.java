package com.onlinebanking.dbmsonlinebanking.dao;

import com.onlinebanking.dbmsonlinebanking.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class transactionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public transactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Transaction primaryToExternal(Transaction pat) {
        String sql = "CALL transactPE(?, ?)";
        return getTransaction(pat, sql);

    }

    public Transaction primaryDeposit(Transaction pat) {
        String sql = "CALL transactDeposit(?, ?)";
        return getTransaction(pat, sql);
    }

    private Transaction getTransaction(Transaction pat, String sql) {
        jdbcTemplate.update(sql, pat.getAmount(), pat.getPrimaryAccountId());

        sql = "SELECT * FROM transaction WHERE primary_account_id = ? ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> {
                    return new Transaction(
                            resultSet.getLong(1),
                            resultSet.getDouble(2),
                            (Double.parseDouble(resultSet.getString(3))),
                            resultSet.getTimestamp(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getLong(8),
                            resultSet.getLong(9));
                }, pat.getPrimaryAccountId());
    }

    public List<Transaction> selectTransactions(Long accountNo) {
        String sql = "SELECT * FROM transaction WHERE primary_account_id = ?";

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Transaction(
                    resultSet.getLong(1),
                    resultSet.getDouble(2),
                    (Double.parseDouble(resultSet.getString(3))),
                    resultSet.getTimestamp(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getLong(8),
                    resultSet.getLong(9));
        }, accountNo);

    }
}
