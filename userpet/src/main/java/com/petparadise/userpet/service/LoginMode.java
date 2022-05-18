package com.petparadise.userpet.service;

import com.petparadise.userpet.model.LoginVo;
import com.petparadise.userpet.model.ResultSet;
import com.petparadise.userpet.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录方式的接口
 */
public interface LoginMode {
    ResultSet login(LoginVo user, HttpServletRequest request);
}
