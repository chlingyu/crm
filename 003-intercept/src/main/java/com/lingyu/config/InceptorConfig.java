package com.lingyu.config;


import com.lingyu.intercept.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//拦截器的配置文件，相当于mvc中的xml配置，需要添加 configuration注解
@Configuration
public class InceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截路径
        String[] addPathPatterns={"/user/**"};
        //放行路径
        String[] excludePathPatterns={"/user/error","/user/login"};
        registry.addInterceptor(new UserInterceptor())
        .addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
