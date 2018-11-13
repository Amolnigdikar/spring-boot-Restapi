package com.spring.restapi.repositories;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.spring.restapi.models.Order;


public interface OrderRepository extends MongoRepository<Order,Serializable>{
	
	

}
