package com.petparadise.userpet.model;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 王培忠
 * @date 2020/4/17 15:15
 * @email 805705089@qq.com
 * @Description
 * @Reason ADDREASON
 * @since JDK 1.8
 */
public class ResultSet {

    private String RetCode;

    private String RetVal;

    private Object DataRows;

    private String flagPrompt;

    private String token;

    private HttpServletResponse response;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getFlagPrompt() {
        return flagPrompt;
    }

    public void setFlagPrompt(String flagPrompt) {
        this.flagPrompt = flagPrompt;
    }

    public Object getDataRows() {
        return DataRows;
    }

    public void setDataRows(Object dataRows) {
        DataRows = dataRows;
    }

    public String getRetCode() {
        return RetCode;
    }

    public void setRetCode(String retCode) {
        RetCode = retCode;
    }

    public String getRetVal() {
        return RetVal;
    }

    public void setRetVal(String retVal) {
        RetVal = retVal;
    }


}
