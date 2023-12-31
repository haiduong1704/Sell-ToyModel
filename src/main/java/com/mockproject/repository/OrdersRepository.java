package com.mockproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{
	
}