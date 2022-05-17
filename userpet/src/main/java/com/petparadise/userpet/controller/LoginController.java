package com.petparadise.userpet.controller;

import com.petparadise.userpet.config.RequestLimit;
import com.petparadise.userpet.model.ResultSet;
import com.petparadise.userpet.service.UserService;
import com.petparadise.userpet.util.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtils redisUtils;
    /**
     *
     * @param loginflag 登录方式：account为账号登录，wx为微信登录，QQ为QQ登录
     * @param loginUserName
     * @param loginPassWord
     * @return
     */
    @RequestMapping("/login")
    @RequestLimit(count = 2)
    public ResultSet login(@RequestParam String loginflag,String loginUserName,String loginPassWord){
        ResultSet resultSet = new ResultSet();
        resultSet = userService.login(loginflag,loginUserName,loginPassWord);
        return resultSet;
    }
}
