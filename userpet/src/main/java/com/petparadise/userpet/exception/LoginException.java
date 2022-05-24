package com.petparadise.userpet.exception;

import com.petparadise.userpet.model.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginException extends Exception{

    //打印日志
    private static Logger log = LoggerFactory.getLogger(LoginException.class);

    /**
     * 可以给用户提出的异常（用户可以看得懂的）
     * @param message
     * @return
     */
    public static ResultSet loginIllegal(String message) {
        ResultSet resultSet = new ResultSet();
        resultSet.setRetCode("success");
        resultSet.setDataRows(message);
        resultSet.setRetVal(message);
        log.error("----------------------------"+message+"---------------------------------");
        return resultSet;
    }

    public static ResultSet codeIllegal(String code,String message) {
        ResultSet resultSet = new ResultSet();
        resultSet.setRetCode(code);
        resultSet.setDataRows(message);
        resultSet.setRetVal("服务器繁忙，请稍后再试");
        log.error("----------------------------"+message+"---------------------------------");
        return resultSet;
    }

}
