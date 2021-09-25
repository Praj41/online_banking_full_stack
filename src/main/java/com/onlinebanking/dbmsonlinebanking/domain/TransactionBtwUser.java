package com.onlinebanking.dbmsonlinebanking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class TransactionBtwUser {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("date")
    private Timestamp date;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

    @JsonProperty("to_account_id")
    private Long toAccountId;

    @JsonProperty("from_account_id")
    private Long fromAccountId;

    public TransactionBtwUser(Long id,
                              Double amount,
                              Timestamp date,
                              String description,
                              String status,
                              Long toAccountId,
                              Long fromAccountId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.status = status;
        this.toAccountId = toAccountId;
        this.fromAccountId = fromAccountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
}
