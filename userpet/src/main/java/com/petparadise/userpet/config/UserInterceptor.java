package com.petparadise.userpet.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("token");
        if("".equals(token) || token == null){
            request.getRequestDispatcher("/web/user/myLogin").forward(request, response);  //对于未登录的用户跳转到登录页面
            return false;
        }
        return true;
    }
}