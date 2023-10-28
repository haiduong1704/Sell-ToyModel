	package com.mockproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mockproject.constant.SessionConst;
import com.mockproject.dto.CartDto;
import com.mockproject.entity.Products;
import com.mockproject.entity.Users;
import com.mockproject.service.ProductsService;
import com.mockproject.service.UsersService;

@Controller(value = "controllerOfUser")
public class HomeController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private ProductsService productService;
	
	@GetMapping({"/index", "/"})
	public String doGetIndex(Model model, HttpSession session) {
		List<Products> listProduct = productService.findAllAvailable();
		model.addAttribute("listProduct", listProduct);
		
		CartDto currentCart = (CartDto) session.getAttribute(SessionConst.CURRENT_CART);
		if (ObjectUtils.isEmpty(currentCart)) {
			session.setAttribute(SessionConst.CURRENT_CART, new CartDto());
		}
		
		return "home/index";
	}
	
	@GetMapping("/login")
	public String doGetLogin(Model model) {
		model.addAttribute("user", new Users());
		return "home/login";
	}
	
	@PostMapping("/login")
	public String doPostLogin(Model model,
		@Validated	@ModelAttribute("user") Users userRequest,BindingResult errors,
			HttpSession session) {
		Users userResponse = userService.login(userRequest.getUsername(), 
												userRequest.getHashPassword());
		
		if(errors.hasErrors()){
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây !");
       }
           else{
            model.addAttribute("message", "Chúc mừng, bạn đã nhập đúng !");
        }
		   
		if (userResponse != null) {
			session.setAttribute(SessionConst.CURRENT_USER, userResponse);
			return "redirect:/index";
		} else {
			String message = "Login failed, please try again!";
			model.addAttribute("message", message);
			return "home/login";
		}
		
	}
	
	@GetMapping("/register")
	public String doGetRegister(Model model) {
		model.addAttribute("user", new Users());
		return "home/register";
	}
	
	@PostMapping("/register")
	public String doPostRegister(Model model,
		@Validated	@ModelAttribute("user") Users userRequest,BindingResult errors,
			HttpSession session) {
		Users userResponse = userService.save(userRequest);
		if(errors.hasErrors()){
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây !");
       }
           else{
            model.addAttribute("message", "Chúc mừng, bạn đã nhập đúng !");
        }
		
		
		if (ObjectUtils.isEmpty(userResponse)) {
			model.addAttribute("message", "Register failed, please try again!");
			return "home/register";
		} else {
			session.setAttribute(SessionConst.CURRENT_USER, userResponse);
			return "redirect:/index";			
		}
	}
	
	@GetMapping("/logout")
	public String doGetLogOut(Model model, HttpSession session) {
		session.removeAttribute(SessionConst.CURRENT_USER);
		return "redirect:/index";
	}
	
}
