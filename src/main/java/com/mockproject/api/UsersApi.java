package com.mockproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.entity.Users;
import com.mockproject.service.UsersService;

@RestController
@RequestMapping("/api/user")
public class UsersApi {

	@Autowired
	private UsersService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
		Users result = service.findById(id);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllUsers() {
		List<Users> listResult = service.findAll();
		return ResponseEntity.ok(listResult);
	}
}
