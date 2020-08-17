package com.globalExcercise.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globalExcercise.entity.UserEntity;

@Repository("UserRepository")
public interface UserRepository extends CrudRepository<UserEntity, UUID>{

	Optional<UserEntity> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
