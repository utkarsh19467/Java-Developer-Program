/**
 * 
 */
package com.example.demo.logging.interceptor;

import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.example.demo.logging.loggingservice.LoggingService;

/**
 * The Class CustomRequestBodyAdviceAdapter.
 */
@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    /** The logging service. */
    @Autowired
    LoggingService loggingService;

    /** The http servlet request. */
    @Autowired
    HttpServletRequest httpServletRequest;

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice#supports(org.springframework.core.MethodParameter, java.lang.reflect.Type, java.lang.Class)
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type type,
	    Class<? extends HttpMessageConverter<?>> aClass) {
	return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter#afterBodyRead(java.lang.Object, org.springframework.http.HttpInputMessage, org.springframework.core.MethodParameter, java.lang.reflect.Type, java.lang.Class)
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
	    Class<? extends HttpMessageConverter<?>> converterType) {

	long currentTimeInMillis = System.currentTimeMillis();
	httpServletRequest.setAttribute("startTime", currentTimeInMillis);

	loggingService.logRequest(httpServletRequest, body);

	return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}