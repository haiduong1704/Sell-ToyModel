package com.mockproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mockproject.dto.CartDetailDto;
import com.mockproject.entity.OrderDetails;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long> {

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO order_details(orderId, productId, price, quantity)"
			+ " VALUES (:#{#dto.idOrder}, :#{#dto.idProduct}, :#{#dto.price}, :#{#dto.quantity})", 
			nativeQuery = true)
	void insert(@Param("dto") CartDetailDto dto);
}
