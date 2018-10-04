package com.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.library.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByRole(String role);
}
