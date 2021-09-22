package com.onlinebanking.dbmsonlinebanking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class loanAccount {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("loan_balance")
    private Double loanBalance;

    @JsonProperty("loan_total")
    private Double loanTotal;

    @JsonProperty("rate")
    private Float rate;

    @JsonProperty("years")
    private int years;

    @JsonProperty("account_number")
    private Long accountNumber;

    public loanAccount(Long id, Double loanBalance, Double loanTotal, Float rate, int years, Long accountNumber) {
        this.id = id;
        this.loanBalance = loanBalance;
        this.loanTotal = loanTotal;
        this.rate = rate;
        this.years = years;
        this.accountNumber = accountNumber;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(Double loanBalance) {
        this.loanBalance = loanBalance;
    }

    public double getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(double loanTotal) {
        this.loanTotal = loanTotal;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
