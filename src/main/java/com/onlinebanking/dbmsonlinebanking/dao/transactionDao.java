package com.onlinebanking.dbmsonlinebanking.dao;

import com.onlinebanking.dbmsonlinebanking.domain.Transaction;
import com.onlinebanking.dbmsonlinebanking.domain.TransactionBtwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Locale;

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

    public TransactionBtwUser btwUser(TransactionBtwUser trans) {
        String sql = "INSERT INTO transactionbtwuser (amount, to_account_id, from_account_id) VALUES (?, ?, ?)";

        try {
            jdbcTemplate.update(sql, trans.getAmount(), trans.getToAccountId(), trans.getFromAccountId());
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getLocalizedMessage());
            sql = "INSERT INTO transactionbtwuser (amount, description, from_account_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, trans.getAmount(), trans.getToAccountId() + " does Not Exist", trans.getFromAccountId());
        }

        sql = "SELECT * FROM transactionbtwuser WHERE from_account_id = ? ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            return new TransactionBtwUser(
                    resultSet.getLong(1),
                    resultSet.getDouble(2),
                    resultSet.getTimestamp(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getLong(6),
                    resultSet.getLong(7)
            );
        }, trans.getFromAccountId());
    }
}
