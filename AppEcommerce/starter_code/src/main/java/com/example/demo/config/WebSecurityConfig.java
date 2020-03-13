/**
 * 
 */
package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The Class WebSecurityConfig.
 *
 * @author utkarsh
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /** The user details service. */
    @Autowired
    private UserDetailsService userDetailsService;

    /** The b crypt password encoder. */
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    /** The jwt authentication entry point. */
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     * Configure.
     *
     * @param http the http
     * @throws Exception the exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/create").permitAll()
		.antMatchers(HttpMethod.GET, "/api/item/**").permitAll().anyRequest().authenticated().and()
		.addFilter(new JwtAuthenticationFilter(authenticationManager()))
		.addFilter(new JwtAuthorizationFilter(authenticationManager())).exceptionHandling()
		.authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Configure.
     *
     * @param auth the auth
     * @throws Exception the exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}