package com.mockproject.service;

import java.util.List;

import com.mockproject.entity.Products;

public interface ProductsService {

	List<Products> findAllAvailable();
	List<Products> findByTypeId(Integer typeId);
	Products findBySlug(String slug);
	Products findById(Long id);
	void updateQuantity(Integer quantity, Long id);
	}
