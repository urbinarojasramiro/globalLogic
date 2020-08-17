package com.globalExcercise.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globalExcercise.entity.PhoneEntity;

@Repository("PhoneRepository")
public interface PhoneRepository extends CrudRepository<PhoneEntity, UUID>{

}
