package com.petparadise.userpet;

import com.petparadise.userpet.model.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginException extends Exception{

    //打印日志
    private static Logger log = LoggerFactory.getLogger(LoginException.class);

    public static ResultSet loginIllegal(String message) {
        ResultSet resultSet = new ResultSet();
        resultSet.setRetCode("1212");
        resultSet.setDataRows(message);
        log.info("----------------------------用户非法登录---------------------------------");
        return resultSet;
    }
}
