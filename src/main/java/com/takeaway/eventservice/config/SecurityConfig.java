package com.takeaway.eventservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author Naveen Kumashi
 */

@Configuration  
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)  
public class SecurityConfig extends WebSecurityConfigurerAdapter {			
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable();	
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);	
		http
			.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").permitAll().and()
			.authorizeRequests().antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN").and()
			.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN").and()
			.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
			.anyRequest().permitAll()
	        .and()
	        .httpBasic()
	        .and()
	        .formLogin();
	}  
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
            throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password("{noop}admin123")
            .roles("ADMIN");
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
		web.ignoring().antMatchers("/index.html", "/*.js", "/*.js.map", "/*.css", "/favicon.ico");
	}
}
