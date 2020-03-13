/**
 * 
 */
package com.example.demo.logging.interceptor;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.logging.loggingservice.LoggingService;

/**
 * The Class LogInterceptor.
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    /** The logging service. */
    @Autowired
    LoggingService loggingService;

    /** The Constant log. */
    private static final Logger log = Logger.getLogger("LogInterceptor.class");

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

	if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
		&& request.getMethod().equals(HttpMethod.GET.name())) {
	    loggingService.logRequest(request, null);
	}
	request.setAttribute("startTime", System.currentTimeMillis());
	return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
	    ModelAndView modelAndView) throws Exception {
	// empty method
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
	    Object o, Exception e) throws Exception {

	long executeTime = System.currentTimeMillis() - (long) httpServletRequest.getAttribute("startTime");
	log.info((String) httpServletRequest.getAttribute("request") + "\n"
		+ (String) httpServletRequest.getAttribute("response") + "\n" + "TIMETAKEN: " + executeTime + "\n");

    }
}