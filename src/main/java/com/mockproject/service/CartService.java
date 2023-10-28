package com.mockproject.service;

import java.sql.SQLException;

import com.mockproject.dto.CartDto;
import com.mockproject.entity.Users;

public interface CartService {
	
	CartDto updateProduct(CartDto cart, long idProduct, int quantity, boolean isUpdate);
	Integer getTotalQuantity(CartDto cart);
	Double getTotalPrice(CartDto cart);
	void insert(CartDto cart, Users user, String address, String phone) throws SQLException;
}