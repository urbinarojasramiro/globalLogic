package com.globalExcercise.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.globalExcercise.exception.GlobalBusinessException;
import com.globalExcercise.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    UserServiceImpl userDetailsService;

    @Autowired
    JwtEntryPoint jwtEntryPoint;
	
	@Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception, GlobalBusinessException {
		http.csrf().disable()
		.addFilterAfter(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/userApiRest/saveUser").permitAll()
		.antMatchers(HttpMethod.POST, "/userApiRest/createRol").permitAll()
		.anyRequest().authenticated();
	}
	
	
}
