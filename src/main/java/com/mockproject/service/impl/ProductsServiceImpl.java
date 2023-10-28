package com.mockproject.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.entity.Products;
import com.mockproject.repository.ProductsRepository;
import com.mockproject.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
	private ProductsRepository repo;

	@Override
	public List<Products> findAllAvailable() {
		return repo.findAllAvailable();
	}

	@Override
	public List<Products> findByTypeId(Integer typeId) {
		return repo.findByTypeOfProduct_Id(typeId);
	}

	@Override
	public Products findBySlug(String slug) {
		return repo.findBySlug(slug);
	}

	@Override
	public Products findById(Long id) {
		Optional<Products> result = repo.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	@Transactional
	public void updateQuantity(Integer quantity, Long id) {
		repo.updateQuantity(quantity, id);
	}

}
