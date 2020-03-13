/**
 * 
 */
package com.example.demo.config;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Class JwtAuthorizationFilter.
 *
 * @author utkarsh
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    /**
     * Instantiates a new jwt authorization filter.
     *
     * @param authManager the auth manager
     */
    public JwtAuthorizationFilter(AuthenticationManager authManager) {
	super(authManager);
    }

    /**
     * Do filter internal.
     *
     * @param req   the req
     * @param res   the res
     * @param chain the chain
     * @throws IOException      Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
	    throws IOException, ServletException {
	String header = req.getHeader(JwtProperties.HEADER_STRING);
	if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
	    chain.doFilter(req, res);
	    return;
	}
	UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
	SecurityContextHolder.getContext().setAuthentication(authentication);
	chain.doFilter(req, res);
    }

    /**
     * Gets the authentication.
     *
     * @param request the request
     * @return the authentication
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
	String token = request.getHeader(JwtProperties.HEADER_STRING);
	if (token != null) {
	    String user = Jwts.parser().setSigningKey(JwtProperties.SECRET)
		    .parseClaimsJws(token.replace(JwtProperties.TOKEN_PREFIX, "")).getBody().getSubject();
	    if (user != null) {
		return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
	    }
	    return null;
	}
	return null;
    }
}
