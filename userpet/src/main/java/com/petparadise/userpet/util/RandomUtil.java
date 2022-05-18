package com.petparadise.userpet.util;

import java.util.Random;

public class RandomUtil {

    public static String getRandom(int count){
        int [] array = new int[count];
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<count;i++){
            array[i] = r.nextInt(10);
            sb.append(array[i]);
        }
        return sb.toString();
    }
}
