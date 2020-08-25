package com.covid19app.models;

import java.io.Serializable;

/**
 * Created by Utsav on 2/24/2018.
 */

public class UserInformation implements Serializable {

    private String email, password;
    private String name, uid, lastName;


    public UserInformation() {

    }

    public UserInformation(String email, String password, String name, String uid, String lastName) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.uid = uid;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getLastName() {
        return lastName;
    }


}
