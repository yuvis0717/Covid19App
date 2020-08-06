package com.covid19app.ui.symptom;

public class UserDetail {
    private String userName;
    private String userAge;

    public UserDetail(String userName, String userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAge() {
        return userAge;
    }
}
