package com.petparadise.userpet.model;

import java.io.Serializable;

public class LoginVo implements Serializable {

    private String verificationCode;
    private User user;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
