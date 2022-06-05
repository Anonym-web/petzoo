package com.petparadise.userpet.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.petparadise.userpet.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RequestLimitInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger("RequestLimitInterceptor");

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            //方法注解
            RequestLimit methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(RequestLimit.class);
            //类注解
            RequestLimit classAnnotation = ((HandlerMethod) handler).getBean().getClass().getAnnotation(RequestLimit.class);
            boolean vcode = true;
            if (methodAnnotation != null) {
                vcode = validateCode(request, methodAnnotation.count(), methodAnnotation.time());
            } else if (classAnnotation != null) {
                vcode = validateCode(request, classAnnotation.count(), classAnnotation.time());
            }
            if (vcode) {
                return true;
            } else {
                Map<String, Object> resultMap = Maps.newHashMap();
//            resultMap.put("retCode", ResponseCodeEnum.REQUESTFULL.getRetCode());
//            resultMap.put("retDesc", ResponseCodeEnum.REQUESTFULL.getRetDesc());
                resultMap.put("retCode", "");
                resultMap.put("retDesc", "");
                try {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter pw = response.getWriter();
                    pw.write(JSON.toJSONString(resultMap));
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    logger.error("返回页面数据出错！" + e.getMessage(), e);
                    throw e;
                }
                return false;
            }
        }else if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        return false;
    }

    /**
     * 接口的访问频次限制
     *
     * @param request
     * @return
     */
    private boolean validateCode(HttpServletRequest request, int maxSize, long timeOut) {
        boolean resultCode = true;
        try {
            String ip = RequestUtil.getRemoteAddr(request);
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);
            long count = redisTemplate.opsForValue().increment(key, 1);
            if (count == 1) {
                redisTemplate.expire(key, timeOut, TimeUnit.MILLISECONDS);
            }
            if (count > maxSize) {
                logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + maxSize + "]");
                resultCode = false;
            }
        } catch (Exception e) {
            logger.error("发生异常: ", e);
        }
        return resultCode;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}


