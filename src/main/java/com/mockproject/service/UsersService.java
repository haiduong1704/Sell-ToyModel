package com.mockproject.service;

import java.util.List;

import com.mockproject.entity.Users;

public interface UsersService {

	List<Users> findAll();
	Users findByUsername(String username);
	Users findById(Long id);
	Users login(String username, String password);
	Users save(Users user);
	
}