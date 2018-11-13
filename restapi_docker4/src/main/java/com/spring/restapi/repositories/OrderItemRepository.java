package com.spring.restapi.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.spring.restapi.models.Order;
import com.spring.restapi.models.OrderItem;

public interface OrderItemRepository extends MongoRepository<OrderItem, Serializable> {

	void save(List<Order> orders);

	

}
