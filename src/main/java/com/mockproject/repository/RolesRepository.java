package com.mockproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{

	Optional<Roles> findById(Integer id);
	Roles findByDescription(String description);
}
