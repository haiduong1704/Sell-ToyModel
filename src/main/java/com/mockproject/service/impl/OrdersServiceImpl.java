package com.mockproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.entity.Orders;
import com.mockproject.repository.OrdersRepository;
import com.mockproject.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	OrdersRepository repo;

	@Override
	@Transactional
	public Orders insert(Orders order) {
		return repo.saveAndFlush(order);
	}

}
