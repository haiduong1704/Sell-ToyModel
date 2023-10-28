package com.mockproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.TypesOfProduct;

@Repository
public interface TypesOfProductRepo extends JpaRepository<TypesOfProduct, Integer> {

	TypesOfProduct findBySlug(String slug);
	
}
