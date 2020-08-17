package com.globalExcercise.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globalExcercise.entity.RolEntity;
import com.globalExcercise.util.RolName;

@Repository("RolRepository")
public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    Optional<RolEntity> findByRolName(RolName rolName);
}
