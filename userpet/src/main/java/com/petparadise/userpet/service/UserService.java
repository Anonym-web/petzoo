package com.petparadise.userpet.service;

import com.petparadise.userpet.LoginException;
import com.petparadise.userpet.config.AccountLogin;
import com.petparadise.userpet.config.LoginMode;
import com.petparadise.userpet.model.ResultSet;
import com.petparadise.userpet.util.RedisUtils;
import com.petparadise.userpet.util.SnowflakeAlgorithm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private RedisUtils redisUtils;

    public ResultSet login(String loginflag,String loginUserName,String loginPassWord){
        ResultSet resultSet = new ResultSet();
        //根据登录方式去判断登录的接口
        Object loginMode = judgeLogin(loginflag);
        if(loginMode instanceof AccountLogin){
            //如果是账号密码登录需验证
            if("".equals(loginUserName) || "".equals(loginPassWord)){
                LoginException.loginIllegal("用户名或密码为空");
                return resultSet;
            }
        }
        System.out.println("访问了一次");
        resultSet.setRetCode("success");
        resultSet.setDataRows("登录成功");
        return resultSet;
    }

    private Object judgeLogin(String loginflag) {
        Object loginMode = null;
        if("account".equals(loginflag)){
            loginMode = new AccountLogin();
        }else if("wx".equals(loginflag)){

        }else if("QQ".equals(loginflag)){

        }else{

        }
        return loginMode;
    }
}
