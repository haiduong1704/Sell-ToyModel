package com.mockproject.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.constant.SessionConst;
import com.mockproject.dto.CartDto;
import com.mockproject.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartApi {

	@Autowired
	private CartService cartService;
	
	@GetMapping("/update")
	public ResponseEntity<?> doGetUpdateCart(
			@RequestParam("product") Long productId,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("isUpdate") Boolean isUpdate,
			HttpSession session) {  
		CartDto currentCart = (CartDto) session.getAttribute(SessionConst.CURRENT_CART);
		currentCart = cartService.updateProduct(currentCart, productId, quantity, isUpdate);
		session.setAttribute(SessionConst.CURRENT_CART, currentCart);
		return ResponseEntity.ok(currentCart);
	}
	@GetMapping("/refresh")
	public ResponseEntity<?> doGetRefreshCart(HttpSession session) {
		CartDto currentCart = (CartDto) session.getAttribute(SessionConst.CURRENT_CART);
		return ResponseEntity.ok(currentCart);
	}
}
