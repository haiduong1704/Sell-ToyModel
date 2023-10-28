package com.mockproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mockproject.constant.SessionConst;
import com.mockproject.dto.CartDto;
import com.mockproject.service.CartService;
import com.mockproject.service.UsersService;


@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
    	private CartService cartService;
	@GetMapping("")
	public String doGetViewCart(Model model, HttpSession session) {
		CartDto currentCart = (CartDto) session.getAttribute(SessionConst.CURRENT_CART);
		if (ObjectUtils.isEmpty(currentCart)) {
			session.setAttribute(SessionConst.CURRENT_CART, new CartDto());
		}
		return "home/cart";
	}
	
	// localhost:8080/cart/update?product=...&quantity=..&isUpdate=...
		@GetMapping("/update")
		public String doGetUpdateCart(
				@RequestParam("product") Long productId,
				@RequestParam("quantity") Integer quantity,
				@RequestParam("isUpdate") Boolean isUpdate,
				HttpSession session) {
			
			CartDto currentCart = (CartDto) session.getAttribute(SessionConst.CURRENT_CART);
			
			currentCart = cartService.updateProduct(currentCart, productId, quantity, isUpdate);
			session.setAttribute(SessionConst.CURRENT_CART,currentCart);
			return "home/cart::#viewCartFragment";
		}
}