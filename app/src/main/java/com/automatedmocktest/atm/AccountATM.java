package com.automatedtellermachine.android.atmproject;

/**
 * Created by matthewkim on 12/21/17.
 */

public class AccountATM {

    private Integer id;
    private Integer UserId;
    private Float checking;
    private Float savings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Float getChecking() {
        return checking;
    }

    public void setChecking(Float checking) {
        this.checking = checking;
    }

    public Float getSavings() {
        return savings;
    }

    public void setSavings(Float savings) {
        this.savings = savings;
    }
}
