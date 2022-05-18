package com.petparadise.userpet.service;

import com.alibaba.fastjson.JSONObject;
import com.petparadise.userpet.model.LoginVo;
import com.petparadise.userpet.model.ResultSet;
import com.petparadise.userpet.model.User;
import com.petparadise.userpet.util.RandomUtil;
import com.petparadise.userpet.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Resource
    private RedisUtils redisUtils;

    //打印日志
    private static Logger log = LoggerFactory.getLogger(UserService.class);

    /**
     * 登录统一方法
     * @param loginflag 登录方式
     * @param user 用户数据
     * @param request 请求包
     * @return
     */
    public ResultSet login(String loginflag, LoginVo user, HttpServletRequest request){
        ResultSet resultSet = new ResultSet();
        //根据登录方式去判断登录的接口
        boolean flag = judgeLogin(loginflag,user,request);
        if(flag == true){
            resultSet.setRetCode("success");
            resultSet.setDataRows("登录成功");
        }
        return resultSet;
    }

    private boolean judgeLogin(String loginflag,LoginVo user,HttpServletRequest request) {
        //先判断是否实现了统一登录接口
        try {
            Class c = Class.forName(loginflag+"Login");
            Object loginmeode = c.newInstance();
            if (loginmeode instanceof LoginMode){
                Method method1 = c.getDeclaredMethod("login");
                method1.setAccessible(true);
                return (boolean) method1.invoke(user,request);
            }else{
                log.error("-------------该类（"+loginflag+"Login"+"）无法被使用，因为没有实现接口（LoginMode）");
                return false;
            }
        } catch (ClassNotFoundException e) {
            log.error("---------------无法找到该类（"+loginflag+"Login"+"）----------------");
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ResultSet verificationCode(String user_phone) {
        ResultSet resultSet = new ResultSet();
        try {
            Map<String,Object> map = new HashMap<>();
            String randomString = RandomUtil.getRandom(4);
//            map.put("appkey","016a73fa167191091e16a59c9ab5be51");
//            map.put("mobile",phone);
//            map.put("content","【创信】你的验证码是："+ randomString +"，3分钟内有效！");
//            String json = HttpClientUtils.doPost("https://way.jd.com/chuangxin/VerCodesms",map);
            String json = "{\n" +
                    "    \"code\": \"10000\",\n" +
                    "    \"charge\": false,\n" +
                    "    \"remain\": 1305,\n" +
                    "    \"msg\": \"查询成功\",\n" +
                    "    \"result\": {\n" +
                    "        \"ReturnStatus\": \"Success\",\n" +
                    "        \"Message\": \"ok\",\n" +
                    "        \"RemainPoint\": 420842,\n" +
                    "        \"TaskID\": 18424321,\n" +
                    "        \"SuccessCounts\": 1\n" +
                    "    }\n" +
                    "}";
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject resultJson = (JSONObject) jsonObject.get("result");
            String success = (String) resultJson.get("ReturnStatus");
            if("Success".equals(success)){
//            证明发送成功,将验证码存到redis中
                boolean redisflag = redisUtils.set(user_phone+"V",randomString,180L, TimeUnit.SECONDS);
                if(redisflag){
                    log.info("--------------"+user_phone+"的验证码"+randomString+"已存放成功"+"------------------------");
                    resultSet.setRetCode("1");
                }else{
                    log.info("--------------"+user_phone+"的验证码未存放成功"+"------------------------");
                }
            }else{
                log.info("验证码发送失败");
                resultSet.setRetCode("0");
                resultSet.setRetVal("验证码发送失败");
                return resultSet;
            }
        }catch (Exception e){
            log.info(e.getMessage());
            e.printStackTrace();
        }

        System.out.println();
        return resultSet;
    }
}
