package com.automatedtellermachine.android.atmproject;

import android.accounts.Account;

/**
 * Created by matthewkim on 12/21/17.
 */

public class UserATM {

    private Integer id;  // Use this for acces to your account
    private String Name;
    private String Password;
    private Account accountInfo;

    public Account getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Account accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

