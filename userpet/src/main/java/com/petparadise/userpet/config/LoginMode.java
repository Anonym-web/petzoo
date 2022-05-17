package com.petparadise.userpet.config;

import com.petparadise.userpet.model.User;

/**
 * 登录方式的接口
 */
public interface LoginMode {
    void login(User user);
}
