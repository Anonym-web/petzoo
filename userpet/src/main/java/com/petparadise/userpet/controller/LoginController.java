package com.petparadise.userpet.controller;

import com.petparadise.userpet.config.RequestLimit;
import com.petparadise.userpet.model.LoginVo;
import com.petparadise.userpet.model.ResultSet;
import com.petparadise.userpet.model.User;
import com.petparadise.userpet.service.UserService;
import com.petparadise.userpet.util.RedisUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtils redisUtils;
    /**
     *
     * @param loginflag 登录方式：Account为账号登录，WX为微信登录，QQ为QQ登录,Phone为手机登录
     * @return
     */
    @PostMapping("/login")
    @RequestLimit(count = 5)
    public ResultSet login(@RequestParam String loginflag, LoginVo user, HttpServletRequest request){
        ResultSet resultSet = new ResultSet();
        resultSet = userService.login(loginflag,user,request);
        return resultSet;
    }

    @PostMapping("/verificationCode")
    @RequestLimit(count = 3)
    public ResultSet verificationCode(@RequestParam String user_phone) throws Exception {
        ResultSet resultSet = new ResultSet();
        resultSet = userService.verificationCode(user_phone);
        return resultSet;
    }
}
