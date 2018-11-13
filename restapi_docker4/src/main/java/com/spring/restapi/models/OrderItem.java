package com.spring.restapi.models;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document (collection="orderitems")
public class OrderItem {
	public OrderItem(List<Order> order,int total_amount) {
		super();
		this.total_amount = total_amount;
		this.order = order;
	}

	@Id
	private String id;
	private int total_amount;
	
	@DBRef
	private List<Order> order=new ArrayList<>();
	
	

	public OrderItem() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTotalAmount() {
		return total_amount;
	}

	public void setTotalAmount(int amount) {
		this.total_amount = amount;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
}
