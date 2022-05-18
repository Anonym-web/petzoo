package com.petparadise.userpet.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IntefaceUtil {

    public static String getTimeByNow(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    public static String getuuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
