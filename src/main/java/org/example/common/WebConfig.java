package org.example.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类 - 注册拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestLoggingInterceptor requestLoggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册请求日志拦截器，拦截所有API请求
        registry.addInterceptor(requestLoggingInterceptor)
                .addPathPatterns("/api/**")  // 只拦截/api开头的请求
                .excludePathPatterns("/test.html", "/static/**");  // 排除静态资源
    }
}
