package com.globalExcercise.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.globalExcercise.dto.UserRequestDTO;
import com.globalExcercise.entity.UserEntity;
import com.globalExcercise.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LOG.info("Método de seguridad UserDetailService - busca por Email");
		
		UserEntity userEntity = userService.findByEmail(email).get();
		return UserRequestDTO.build(userEntity);
	}
	
	
}
