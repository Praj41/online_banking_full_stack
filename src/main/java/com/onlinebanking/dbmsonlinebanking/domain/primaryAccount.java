package com.onlinebanking.dbmsonlinebanking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class primaryAccount {

    @JsonProperty("user_id")
    private Long user_id;

    @JsonProperty("account_balance")
    private double account_Balance;

    @JsonProperty("account_number")
    private Long account_number;

    public primaryAccount(Long user_id, double account_Balance, Long account_number) {
        this.user_id = user_id;
        this.account_Balance = account_Balance;
        this.account_number = account_number;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

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
