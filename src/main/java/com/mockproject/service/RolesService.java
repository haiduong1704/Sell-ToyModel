package com.mockproject.service;

import com.mockproject.entity.Roles;

public interface RolesService {
	
	Roles findById(Integer id);
	Roles findByDescription(String description);

}
