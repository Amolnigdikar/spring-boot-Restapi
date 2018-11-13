package com.spring.restapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restapi.DTO.OrderDTO;
import com.spring.restapi.models.*;
import com.spring.restapi.repositories.OrderItemRepository;
import com.spring.restapi.repositories.OrderRepository;
import com.spring.restapi.repositories.ProductRepository;
import com.spring.restapi.services.OrderItemService;

import io.swagger.annotations.ApiOperation;


@RestController
public class OrderItemController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderItemService orderItemService;
	
	
	
	@RequestMapping(method=RequestMethod.GET,value= "/orders")
	@ApiOperation(value="Retrieves all orders from the database")
	public List<OrderItem> getOrders()
	{
		return orderItemRepository.findAll() ;
		
	}
	
	@PostMapping(path="/create_order",consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Creates a new order", notes = "Takes Product_id and quantity as a request body and creates a order for single as well as multiple products")
	public OrderItem createOrder(@RequestBody List <OrderDTO> order) {
		
		Order order_item =null;
		List<Order> orders =new ArrayList<Order>();
		int i=order.size();
		int total=0;
		
		for(int j=0;j<i;j++) {
			order_item=new Order(order.get(j).getProduct_id(),order.get(j).getQuantity());
			orders.add(orderItemService.insert(order_item));
			orderRepository.save(orders);
			
			 total+=order_item.getAmount();
			}
		    OrderItem or=new OrderItem(orders,total);
		    orderItemRepository.save(or);
		    return or;
		    
		
		
		
		}
		
	@RequestMapping(method=RequestMethod.DELETE,value="/delete/{id}")
	@ApiOperation(value = "Delete the order by order_id", notes = "Takes order_id as parameter and delete the specific record")
	public String deleteOrder(@RequestParam(required=true)String id) {
		OrderItem order=orderItemRepository.findOne(id);
		orderItemRepository.delete(order);
		return "Order deleted";
		
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/update_order/{id}")
	@ApiOperation(value = "Update the specific order", notes = "Takes order_id as parameter and product_id and quantity as request body and updates the specific order ")
	public OrderItem update(@PathVariable String id,@RequestBody List<OrderDTO> order) {
		OrderItem or=orderItemRepository.findOne(id);
		List<Order> orders=or.getOrder();
		
		int i=orders.size();
		int total = 0;
		
		for(int j=0;j<i;j++) {
			if(order.get(j).getProduct_id()!=null)
				orders.get(j).setProduct_id(order.get(j).getProduct_id());
			
			orders.get(j).setQuantity(order.get(j).getQuantity());
			Product prod=productRepository.findOne(order.get(j).getProduct_id());
			orders.get(j).setAmount(prod.getProdPrice()*order.get(j).getQuantity());
			 
			orderRepository.save(orders);
			
			
			or.setTotalAmount(total+=orders.get(j).getAmount());
			}
		
		orderItemRepository.save(or);
		return or;
		
        
      }
	
	@RequestMapping(method=RequestMethod.GET,value="orders/{id}")
	@ApiOperation(value="Returns the record of specific order by order_id")

	public OrderItem getOrder(@PathVariable String id) {
		OrderItem getOrder=orderItemRepository.findOne(id);
		return getOrder;
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="getProducts/{orderid}")
	@ApiOperation(value="Returns all the products of a specific order ")
	public List<Product> getProducts(@PathVariable String orderid) {
		OrderItem or=orderItemRepository.findOne(orderid);
		List<Order> order=or.getOrder();
		List<Product>products =new ArrayList<Product>();
		 
		int i=order.size();
		for(int j=0;j<i;j++)
		{
			String prodid=order.get(j).getProduct_id();
			products.add(productRepository.findOne(prodid));
			
		}
		
		return products;
		
	}
		
				
}
	
	
	