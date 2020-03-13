/**
 * 
 */
package com.example.demo.logging.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.example.demo.logging.loggingservice.LoggingService;

/**
 * The Class CustomResponseBodyAdviceAdapter.
 */
@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

    /** The logging service. */
    @Autowired
    LoggingService loggingService;

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#supports(org.springframework.core.MethodParameter, java.lang.Class)
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
	return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#beforeBodyWrite(java.lang.Object, org.springframework.core.MethodParameter, org.springframework.http.MediaType, java.lang.Class, org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse)
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
	    Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
	    ServerHttpResponse serverHttpResponse) {
	if (serverHttpRequest instanceof ServletServerHttpRequest
		&& serverHttpResponse instanceof ServletServerHttpResponse) {

	    loggingService.logResponse(((ServletServerHttpRequest) serverHttpRequest).getServletRequest(),
		    ((ServletServerHttpResponse) serverHttpResponse).getServletResponse(), o);
	}

	return o;
    }
}