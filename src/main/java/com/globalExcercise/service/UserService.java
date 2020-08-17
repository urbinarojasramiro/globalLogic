package com.globalExcercise.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalExcercise.dto.PhoneDTO;
import com.globalExcercise.dto.UserRequestDTO;
import com.globalExcercise.dto.UserResponseDTO;
import com.globalExcercise.entity.PhoneEntity;
import com.globalExcercise.entity.RolEntity;
import com.globalExcercise.entity.UserEntity;
import com.globalExcercise.exception.RolException;
import com.globalExcercise.repository.PhoneRepository;
import com.globalExcercise.repository.UserRepository;
import com.globalExcercise.util.RolName;

@Service
@Transactional
public class UserService {
	
	private static Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	@Qualifier("UserRepository")
	UserRepository userRepository;
	
	@Autowired
	@Qualifier("PhoneRepository")
	PhoneRepository phoneRepository;
	
	@Autowired
	RolService rolService;
	
	public List<UserEntity> getUser() {
		return (List<UserEntity>) userRepository.findAll();
	}
	
	public UserResponseDTO saveUser(UserRequestDTO user) throws RolException {
		LOG.info("Obetenemos el Entity de User desde objeto input");
		UserEntity userEntity = new UserEntity(user);
		
		LocalDate localDate = LocalDate.now();
		Date createdDate = java.sql.Date.valueOf(localDate);
		
		userEntity.setCreated(createdDate);
		userEntity.setModified(createdDate);
		userEntity.setLast_login(createdDate);
		userEntity.setIsactive(true);
		try {
			Set<RolEntity> roles = new HashSet<>();
	        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
	        userEntity.setRoles(roles);
		} catch(Exception e) {
			LOG.info("Error al recuperar Roles");
			throw new RolException(e);
		}
		
		LOG.info("guardamos userEntity: " + userEntity.toString());
		UserEntity userEntityResult = new UserEntity();
		userEntityResult = userRepository.save(userEntity);
		LOG.info("obtenemos el result de user: " + userEntityResult.toString());
		savePhoneEntity(user, userEntityResult);
		
		return new UserResponseDTO(userEntityResult);
		
	}
	
	private void savePhoneEntity(UserRequestDTO user, UserEntity userEntityResult) {
		LOG.info("Obetenemos el Entity de Phone desde objeto input");
		
		for(PhoneDTO phoneDTO: user.getPhones())
		{
			PhoneEntity phoneEntity = new PhoneEntity(phoneDTO);
			phoneEntity.setId(userEntityResult.getId());
			phoneRepository.save(phoneEntity);
		}
	}
	

	public Optional<UserEntity> findByEmail(String email) {
		LOG.info("Entramos desde userService al repositorio a buscar Usuario por Email");
		
		return userRepository.findByEmail(email);
	}
	
	public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
