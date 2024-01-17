package com.garimachoudhary.ecommerceproject.service;

import java.util.List;

import com.garimachoudhary.ecommerceproject.exception.OrderException;
import com.garimachoudhary.ecommerceproject.model.Address;
import com.garimachoudhary.ecommerceproject.model.Orderr;
import com.garimachoudhary.ecommerceproject.model.User;

public interface OrderService {
   
	public Orderr createOrder(User user,Address shippingAddress);
	
	public Orderr findOrderById(Long orderId)throws OrderException;
	
	public List<Orderr> usersOrderHistory(Long userId);
	
   public Orderr placedOrder(Long orderId) throws OrderException;
   
   public Orderr confirmedOrder(Long orderId) throws OrderException;
   
   public Orderr ShippedOrder(Long orderId) throws OrderException;
   
   public Orderr deliveredOrder(Long orderId) throws OrderException;
   
   public Orderr cancelledOrder(Long orderId) throws OrderException;
   
   public List<Orderr>getAllOrders();
   
   public void deleteOrder(Long orderId)throws OrderException;
   
	
}
