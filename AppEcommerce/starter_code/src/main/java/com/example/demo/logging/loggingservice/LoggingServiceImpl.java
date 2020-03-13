/**
 * 
 */
package com.example.demo.logging.loggingservice;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

/**
 * The Class LoggingServiceImpl.
 *
 * @author utkarsh
 */
@Service
public class LoggingServiceImpl implements LoggingService {

    /* (non-Javadoc)
     * @see com.example.demo.logging.loggingservice.LoggingService#logRequest(javax.servlet.http.HttpServletRequest, java.lang.Object)
     */
    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
	StringBuilder stringBuilder = new StringBuilder();
	Map<String, String> parameters = buildParametersMap(httpServletRequest);

	stringBuilder.append("REQUEST ");
	stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
	stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
	stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

	if (!parameters.isEmpty()) {
	    stringBuilder.append("parameters=[").append(parameters).append("] ");
	}

	if (body != null) {
	    stringBuilder.append("body=[" + body + "]");
	}

	httpServletRequest.setAttribute("request", stringBuilder.toString());
    }

    /* (non-Javadoc)
     * @see com.example.demo.logging.loggingservice.LoggingService#logResponse(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
	    Object body) {
	StringBuilder stringBuilder = new StringBuilder();

	stringBuilder.append("RESPONSE ");
	stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
	stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
	stringBuilder.append("status=[ ").append(httpServletResponse.getStatus()).append(" ] ");
	// stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("]
	// ");
	if (body != null)
	    stringBuilder.append("responseBody=[").append(body.toString()).append("] ");

	httpServletRequest.setAttribute("response", stringBuilder.toString());
    }

    /**
     * Builds the parameters map.
     *
     * @param httpServletRequest the http servlet request
     * @return the map
     */
    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
	Map<String, String> resultMap = new HashMap<>();
	Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

	while (parameterNames.hasMoreElements()) {
	    String key = parameterNames.nextElement();
	    String value = httpServletRequest.getParameter(key);
	    resultMap.put(key, value);
	}

	return resultMap;
    }

    /**
     * Builds the headers map.
     *
     * @param request the request
     * @return the map
     */
    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
	Map<String, String> map = new HashMap<>();

	Enumeration<String> headerNames = request.getHeaderNames();
	while (headerNames.hasMoreElements()) {
	    String key = (String) headerNames.nextElement();
	    String value = request.getHeader(key);
	    map.put(key, value);
	}

	return map;
    }

//    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
//	Map<String, String> map = new HashMap<>();
//
//	Collection<String> headerNames = response.getHeaderNames();
//	for (String header : headerNames) {
//	    map.put(header, response.getHeader(header));
//	}
//
//	return map;
//    }
}
