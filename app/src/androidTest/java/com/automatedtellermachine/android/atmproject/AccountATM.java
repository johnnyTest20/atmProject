package com.automatedtellermachine.android.atmproject;


/**
 * Created by matthewkim on 12/21/17.
 */

public class AccountATM {

    private int _id;
    private String _accountname;

    public AccountATM(){

    }

    public AccountATM(String accountname) {
        this._accountname = accountname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_accountname(String _accountname) {
        this._accountname = _accountname;
    }

    public int get_id() {
        return _id;
    }

    public String get_accountname() {
        return _accountname;
    }
}
