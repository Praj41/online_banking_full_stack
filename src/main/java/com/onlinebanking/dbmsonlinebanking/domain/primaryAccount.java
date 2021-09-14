package com.onlinebanking.dbmsonlinebanking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class primaryAccount {

    @JsonProperty("account_balance")
    private double account_Balance;

    @JsonProperty("account_number")
    private Long account_number;

    public double getAccount_Balance() {
        return account_Balance;
    }

    public void setAccount_Balance(double account_Balance) {
        this.account_Balance = account_Balance;
    }

    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }
}
