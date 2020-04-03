/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author utkarsh
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        .authorizeRequests()
            .antMatchers(
                    "/register**",
                    "/js/**",
                    "/css/**",
                    "/img/**",
                    "/webjars/**",
                    "/h2-console/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().successHandler(successHandler)
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout")
            .permitAll();
		
		http.csrf()
        .ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().sameOrigin();
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
