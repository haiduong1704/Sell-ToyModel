package com.mockproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.mockproject.constant.RoleConst;
import com.mockproject.entity.Roles;
import com.mockproject.entity.Users;
import com.mockproject.repository.UsersRepository;
import com.mockproject.service.RolesService;
import com.mockproject.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepository repo;
	
	@Autowired
	private RolesService roleService;
	
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

	@Override
	public List<Users> findAll() {
		return repo.findAll();
	}

	@Override
	public Users findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public Users findById(Long id) {
		Optional<Users> result = repo.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public Users login(String username, String password) {
		Users user = repo.findByUsername(username);
		boolean checkPassword = false;
		if (!ObjectUtils.isEmpty(user)) {
			String hashPasswordInDB = user.getHashPassword();
			checkPassword = bcrypt.matches(password, hashPasswordInDB);
		}
		return checkPassword ? user : null;
	}

	@Override
	public Users save(Users user) {
		user.setHashPassword(bcrypt.encode(user.getHashPassword()));
		user.setIsDeleted(false);
		Roles role = roleService.findByDescription(RoleConst.ROLE_USER);
		user.setRole(role);
		return repo.saveAndFlush(user);
	}

}
