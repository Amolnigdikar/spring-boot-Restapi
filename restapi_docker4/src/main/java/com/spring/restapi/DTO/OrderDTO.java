package com.spring.restapi.DTO;

public class OrderDTO {
	
	String product_id;
	int quantity;
	
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderDTO(String product_id, int quantity) {
		super();
		this.product_id = product_id;
		this.quantity = quantity;
	}

	public OrderDTO() {
		super();
	}
	
	

}
