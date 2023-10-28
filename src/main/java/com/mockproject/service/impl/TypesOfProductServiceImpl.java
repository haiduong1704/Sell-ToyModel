package com.mockproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.entity.TypesOfProduct;
import com.mockproject.repository.TypesOfProductRepo;
import com.mockproject.service.TypesOfProductService;

@Service
public class TypesOfProductServiceImpl implements TypesOfProductService {

	@Autowired
	private TypesOfProductRepo repo;

	@Override
	public TypesOfProduct findBySlug(String slug) {
		return repo.findBySlug(slug);
	}
}
