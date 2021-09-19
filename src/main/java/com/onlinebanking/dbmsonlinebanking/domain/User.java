package com.onlinebanking.dbmsonlinebanking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
//import javax.validation.constraints.NotBlank;

public class User {

    @JsonProperty("id")
    private Long user_id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("enable")
    private boolean enabled;

    @JsonProperty("fname")
    private String first_name;

    @JsonProperty("lname")
    private String last_name;

    @JsonProperty("pass")
    private String password;

    @JsonProperty("ph")
    private String phone;

    @JsonProperty("username")
    private String username;

    @JsonProperty("pid")
    private Long primary_account_id;

    @JsonProperty("lid")
    private Long loan_account_id;

    public User(Long user_id,
                String email,
                boolean enabled,
                String first_name,
                String last_name,
                String password,
                String phone,
                String username,
                Long primary_account_id,
                Long savings_account_id) {
        this.user_id = user_id;
        this.email = email;
        this.enabled = enabled;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.phone = phone;
        this.username = username;
        this.primary_account_id = primary_account_id;
        this.loan_account_id = savings_account_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPrimary_account_id() {
        return primary_account_id;
    }

    public void setPrimary_account_id(Long primary_account_id) {
        this.primary_account_id = primary_account_id;
    }

    public Long getLoan_account_id() {
        return loan_account_id;
    }

    public void setLoan_account_id(Long loan_account_id) {
        this.loan_account_id = loan_account_id;
    }
}
