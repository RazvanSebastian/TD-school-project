package com.application.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{

}
