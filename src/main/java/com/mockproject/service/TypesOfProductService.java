package com.mockproject.service;

import com.mockproject.entity.TypesOfProduct;

public interface TypesOfProductService {

	TypesOfProduct findBySlug(String slug);
}
