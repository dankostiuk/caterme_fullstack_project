package com.dan.rest;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dan.entity.Order;
import com.dan.entity.User;
import com.dan.manager.OrderManager;
import com.dan.manager.UserManager;

/**
 * JAX-RS API endpoints for the Order entity.
 * 
 * @author dan
 */
@Path("/order")
public class OrderResource {

	OrderManager _orderManager;
	
	public OrderResource() {
		_orderManager = new OrderManager();
	}
	
	/**
	 * Get a single order by id.
	 * @param id The id of the order.
	 * @return Response containing the order for the given id.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrder(@QueryParam("id") Integer id) {
		if (id == null)
		{
			return Response.status(400).build();
		}
		
		Order order = null;
		try {
			order = _orderManager.getOrder(id);
		} catch (NamingException e) {
			return Response.status(500).entity("Error occured while retrieving order.").build();
		}
		
		if (order == null) {
			return Response.status(404).entity("Order could not be found.").build();
		}
		
		return Response.status(200).entity(order).build();
	}
	
	/**
	 * Get all orders.
	 * @return Response containing all orders.
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrders() {
		List<Order> orders = _orderManager.getAll();
		
		if (orders == null || orders.size() == 0) {
			return Response.status(404).entity("Orders could not be found.").build();
		}
		
		return Response.status(200).entity(orders).build();
	}
	
	/**
	 * Creates an order
	 * @param order The order passed in from the client.
	 * @return Response indicating a success or error.
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(Order order) {
		_orderManager.createOrder(order);
		return Response.status(200).build();
	}
	
	/**
	 * Updates an order
	 * @param order The order passed in from the client.
	 * @return Response indicating a success or error.
	 */
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrder(Order order) {
		_orderManager.updateOrder(order);
		return Response.status(200).build();
	}
}
