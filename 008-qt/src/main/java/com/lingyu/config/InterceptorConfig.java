package com.lingyu.config;


import com.lingyu.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")         //其他接口token验证
                .excludePathPatterns("/Identity/**","/Index/getActivit","/Index/getRecommend","/Userdetails/getTarget","/Userdetails/getMine","/Personal/**","/Activity/getData");  //所有用户都放心
    }
}
