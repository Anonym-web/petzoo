package com.petparadise.userpet.service;

import com.petparadise.userpet.mapper.UserMapper;
import com.petparadise.userpet.model.LoginVo;
import com.petparadise.userpet.model.ResultSet;
import com.petparadise.userpet.model.User;
import com.petparadise.userpet.util.IntefaceUtil;
import com.petparadise.userpet.util.RedisUtils;
import com.petparadise.userpet.util.RequestUtil;
import com.petparadise.userpet.util.SnowflakeAlgorithm;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class PhoneLogin implements LoginMode{
//public class PhoneLogin{

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtils redisUtils;

//    @Override
    public ResultSet login(LoginVo loginVo, HttpServletRequest request) {
        ResultSet resultSet = new ResultSet();
        User user = loginVo.getUser();
        String phone = user.getUser_phone();
        boolean flag = false;
        //先去数据库查询是否有这个phone
        int userCount = userMapper.selectCountByPhone(phone);
        if(userCount == 0){
            //证明这个用户还没有创建
            user.setUser_id(String.valueOf(SnowflakeAlgorithm.uniqueLong()));
            user.setUser_ip(RequestUtil.getRemoteAddr(request));
            user.setUser_create_time(IntefaceUtil.getTimeByNow());
            user.setUser_update_time(IntefaceUtil.getTimeByNow());
            user.setUser_status(String.valueOf(1));
            user.setUser_name("爱宠"+IntefaceUtil.getuuid());
            userMapper.insertUser(user);
        }
        //去redis取目前此手机号的验证码，
        String verificationCode = (String) redisUtils.get(user.getUser_phone());
        if(!"".equals(verificationCode)){
            //证明验证码存在
            if(!verificationCode.equals(loginVo.getVerificationCode())){

            }
        }else {
            //此用户没有验证码
        }
        resultSet.setRetCode("success");
        return resultSet;
    }
}
