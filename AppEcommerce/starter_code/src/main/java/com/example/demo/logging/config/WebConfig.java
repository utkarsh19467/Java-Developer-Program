package com.example.demo.logging.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.logging.interceptor.LogInterceptor;

/**
 * The Class WebConfig.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /** The log interceptor. */
    @Autowired
    LogInterceptor logInterceptor;

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(logInterceptor);
    }
}