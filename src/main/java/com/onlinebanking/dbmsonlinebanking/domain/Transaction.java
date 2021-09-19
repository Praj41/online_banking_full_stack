package com.onlinebanking.dbmsonlinebanking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Transaction {

    @JsonProperty("id")
    Long id;

    @JsonProperty("amount")
    double amount;

    @JsonProperty("available_balance")
    double availableBalance;

    @JsonProperty("date")
    Timestamp date;

    @JsonProperty("description")
    String description;

    @JsonProperty("status")
    String status;

    @JsonProperty("type")
    String type;

    @JsonProperty("primary_account_id")
    Long primaryAccountId;

    @JsonProperty("loan_account_id")
    Long loanAccountId;

    public Transaction(Long id,
                       double amount,
                       double availableBalance,
                       Timestamp date,
                       String description,
                       String status,
                       String type,
                       Long primaryAccountId,
                       Long loanAccountId) {
        this.id = id;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.date = date;
        this.description = description;
        this.status = status;
        this.type = type;
        this.primaryAccountId = primaryAccountId;
        this.loanAccountId = loanAccountId;
    }

    public Long getLoanAccountId() {
        return loanAccountId;
    }

    public void setLoanAccountId(Long loanAccountId) {
        this.loanAccountId = loanAccountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrimaryAccountId() {
        return primaryAccountId;
    }

    public void setPrimaryAccountId(Long primaryAccountId) {
        this.primaryAccountId = primaryAccountId;
    }
}
