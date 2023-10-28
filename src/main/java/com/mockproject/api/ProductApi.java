package com.mockproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.entity.Products;
import com.mockproject.service.ProductsService;

@RestController
@RequestMapping("/api/product")
public class ProductApi {
	
	@Autowired
	private ProductsService productService;
	
	@GetMapping("/{typeId}")
	public ResponseEntity<?> getByTypeId(@PathVariable("typeId") Integer typeId) {
		List<Products> listResult = productService.findByTypeId(typeId);
		return ResponseEntity.ok(listResult);
	}

}
