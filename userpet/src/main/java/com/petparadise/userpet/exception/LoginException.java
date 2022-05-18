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
        resultSet.setRetCode("1212");
        resultSet.setDataRows(message);
        log.info("----------------------------"+message+"---------------------------------");
        return resultSet;
    }

}
