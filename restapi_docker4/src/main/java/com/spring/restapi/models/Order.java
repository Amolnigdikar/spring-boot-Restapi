package com.spring.restapi.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "orders")

public class Order{
	
	@Id
	private String product_id;
	private Integer quantity;
	private int amount;
	public Order(String product_id, Integer quantity) {
	
		this.product_id = product_id;
		this.quantity = quantity;
		
		this.amount=this.getAmount();
		
	} 
	
	
	public String getProduct_id() {
		return product_id;
	}
	public Order() {
		super();
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
	
	
	
	
	
	
	

}
