package com.onlinebanking.dbmsonlinebanking.dao;

import com.onlinebanking.dbmsonlinebanking.domain.Transaction;
import com.onlinebanking.dbmsonlinebanking.domain.loanAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class loanDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public loanDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public loanAccount createLoanAccount(Long userId) {
        String sql = "CALL createLoan(?)";

        //jdbcTemplate.update(sql, userId);

        return jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> new loanAccount(
                        resultSet.getLong(1),
                        Double.parseDouble(resultSet.getString(2)),
                        Double.parseDouble(resultSet.getString(3)),
                        Float.parseFloat(resultSet.getString(4)),
                        resultSet.getInt(5),
                        resultSet.getLong(6)), userId);

    }

    public loanAccount getLoan(double loanTotal, int years, Long accountNumber) {

        String sql = "CALL loanReq(?, ?, ?)";

        return jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> new loanAccount(
                        resultSet.getLong(1),
                        Double.parseDouble(resultSet.getString(2)),
                        Double.parseDouble(resultSet.getString(3)),
                        Float.parseFloat(resultSet.getString(4)),
                        resultSet.getInt(5),
                        resultSet.getLong(6)), loanTotal, years, accountNumber);

    }

    public Transaction payEMI(Long primary_account_id, Long loan_account_id) {
        String sql = "CALL payLoan(?, ?)";

        return jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> new Transaction(
                        resultSet.getLong(1),
                        resultSet.getDouble(2),
                        resultSet.getDouble(3),
                        resultSet.getTimestamp(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getLong(8),
                        resultSet.getLong(9)
                ), loan_account_id, primary_account_id);

    }
}
