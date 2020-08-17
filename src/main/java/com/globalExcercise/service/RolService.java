package com.globalExcercise.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalExcercise.entity.RolEntity;
import com.globalExcercise.repository.RolRepository;
import com.globalExcercise.util.RolName;

@Service
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<RolEntity> getByRolName(RolName rolName){
        return rolRepository.findByRolName(rolName);
    }

    public void save(RolEntity rol){
        rolRepository.save(rol);
    }
}