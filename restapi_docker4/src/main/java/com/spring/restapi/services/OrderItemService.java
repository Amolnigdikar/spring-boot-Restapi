package com.spring.restapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.restapi.models.*;
import com.spring.restapi.repositories.OrderRepository;
import com.spring.restapi.repositories.ProductRepository;
@Service
public class OrderItemService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	public Order insert(Order order) {
		Product id=productRepository.findOne(order.getProduct_id());
		order.setAmount(id.getProdPrice()*order.getQuantity());
		return order;
	}

}
