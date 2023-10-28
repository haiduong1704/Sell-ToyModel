package com.mockproject.service.impl;

import java.sql.SQLException;
import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.mockproject.dto.CartDetailDto;
import com.mockproject.dto.CartDto;
import com.mockproject.entity.Orders;
import com.mockproject.entity.Products;
import com.mockproject.entity.Users;
import com.mockproject.service.CartService;
import com.mockproject.service.OrderDetailsService;
import com.mockproject.service.OrdersService;
import com.mockproject.service.ProductsService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductsService productService;

	@Autowired
	private OrdersService orderService;

	@Autowired
	private OrderDetailsService orderDetailService;

	@Override
	public CartDto updateProduct(CartDto cart, long idProduct, int quantity, boolean isUpdate) {
		Products product = productService.findById(idProduct);
		HashMap<Long, CartDetailDto> listDetail = cart.getListDetail();

		if (!listDetail.containsKey(idProduct)) {
			CartDetailDto cartDetail = new CartDetailDto();
			cartDetail.setIdProduct(product.getId());
			cartDetail.setImgUrl(product.getImgUrl());
			cartDetail.setName(product.getName());
			cartDetail.setPrice(product.getPrice());
			cartDetail.setQuantity(quantity);
			cartDetail.setSlug(product.getSlug());
			listDetail.put(idProduct, cartDetail);
		} else if (quantity > 0) {
			if (isUpdate) {
				listDetail.get(idProduct).setQuantity(quantity);
			} else {
				Integer oldQuantity = listDetail.get(idProduct).getQuantity();
				listDetail.get(idProduct).setQuantity(oldQuantity + quantity);
			}
		} else {
			listDetail.remove(idProduct);
		}
		cart.setTotalPrice(this.getTotalPrice(cart));
		cart.setTotalQuantity(this.getTotalQuantity(cart));
		return cart;
	}

	@Override
	public Integer getTotalQuantity(CartDto cart) {
		HashMap<Long, CartDetailDto> cartDetail = cart.getListDetail();
		Integer totalQuantity = 0;
		for (CartDetailDto cartDetailDto : cartDetail.values()) {
			totalQuantity += cartDetailDto.getQuantity();
		}
		return totalQuantity;
	}

	@Override
	public Double getTotalPrice(CartDto cart) {
		HashMap<Long, CartDetailDto> cartDetail = cart.getListDetail();
		Double totalPrice = 0D;
		for (CartDetailDto cartDetailDto : cartDetail.values()) {
			Products product = productService.findById(cartDetailDto.getIdProduct());
			totalPrice += product.getPrice() * cartDetailDto.getQuantity();
		}
		return totalPrice;
	}

	@Override
	@Transactional
	public void insert(CartDto cart, Users user, String address, String phone) throws SQLException {
		Orders order = new Orders();
		order.setAddress(address);
		order.setPhone(phone);
		order.setUser(user);
		try {
			Orders orderReturn = orderService.insert(order);
			if (!ObjectUtils.isEmpty(orderReturn)) {
				for (CartDetailDto cartDetailDto : cart.getListDetail().values()) {
					cartDetailDto.setIdOrder(orderReturn.getId());
					orderDetailService.insert(cartDetailDto);
					// update so luong cua san pham sau khi insert hoa don
					Products product = productService.findById(cartDetailDto.getIdProduct());
					Integer newQuantity = product.getQuantity() - cartDetailDto.getQuantity();
					productService.updateQuantity(newQuantity, cartDetailDto.getIdProduct());
				}
			} else {
				throw new SQLException("Cannot insert to DB");
			}
		} catch (SQLException e) {
			throw new SQLException("Cannot insert to DB");
		}
	}

}
