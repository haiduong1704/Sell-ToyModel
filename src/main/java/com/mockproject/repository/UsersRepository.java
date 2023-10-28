package com.mockproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByUsername(String username);
	Users findByUsernameAndHashPassword(String username, String password);
}