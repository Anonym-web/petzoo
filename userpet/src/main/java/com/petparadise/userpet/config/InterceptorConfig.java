package com.petparadise.userpet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //所要拦截的请求路径
    String[] addPathPatterns = {
            "/web/user/**"
    };

    //不需要拦截的请求路径
    String[] excludePathPatterns = {
            "/web/user/myLogin","/web/user/loginAndRegister","/web/user/verificationCode"
    };

    //mvc:interceptor class=""
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}

