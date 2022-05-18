package com.petparadise.userpet.service;

import com.petparadise.userpet.model.LoginVo;
import com.petparadise.userpet.model.ResultSet;
import com.petparadise.userpet.model.User;

import javax.servlet.http.HttpServletRequest;

public class AccountLogin implements LoginMode {
    @Override
    public ResultSet login(LoginVo user, HttpServletRequest request) {
        return null;
    }
}
