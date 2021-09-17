package com.onlinebanking.dbmsonlinebanking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class primaryAccountTransaction {

    @JsonProperty("id")
    Long id;

    @JsonProperty("amount")
    double amount;

    @JsonProperty("available_balance")
    BigDecimal availableBalance;

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

    public primaryAccountTransaction(Long id,
                                     double amount,
                                     BigDecimal availableBalance,
                                     Timestamp date,
                                     String description,
                                     String status,
                                     String type,
                                     Long primaryAccountId) {
        this.id = id;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.date = date;
        this.description = description;
        this.status = status;
        this.type = type;
        this.primaryAccountId = primaryAccountId;
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

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
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
