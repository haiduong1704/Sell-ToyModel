package com.mockproject.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.entity.Roles;
import com.mockproject.repository.RolesRepository;
import com.mockproject.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesRepository repo;

	@Override
	public Roles findById(Integer id) {
		Optional<Roles> opt = repo.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public Roles findByDescription(String description) {
		return repo.findByDescription(description);
	}

}
