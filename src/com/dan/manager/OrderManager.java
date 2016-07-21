package com.dan.manager;

import java.util.List;

import javax.naming.NamingException;

import com.dan.entity.Order;

/**
 * Carries out Order ORM operations.
 * 
 * @author dan
 */
public class OrderManager extends AbstractManager<Order> {
	
	public OrderManager() {
		super(Order.class);
	}
	
	public Order getOrder(int id) throws NamingException {
		Order order = readTransaction(id);
		
		return order;
	}
	
	public List<Order> getAll() {
		List<Order> orders = readAllTransaction();
		return orders;
	}
	
	public void createOrder(Order order) {
		writeTransaction(order);
	}
}
