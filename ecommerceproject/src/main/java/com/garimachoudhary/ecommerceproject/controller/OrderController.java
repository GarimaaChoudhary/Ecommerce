package com.garimachoudhary.ecommerceproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garimachoudhary.ecommerceproject.exception.OrderException;
import com.garimachoudhary.ecommerceproject.exception.UserException;
import com.garimachoudhary.ecommerceproject.model.Address;
import com.garimachoudhary.ecommerceproject.model.Orderr;
import com.garimachoudhary.ecommerceproject.model.User;
import com.garimachoudhary.ecommerceproject.service.OrderService;
import com.garimachoudhary.ecommerceproject.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<Orderr>createOrder(@RequestBody Address shippingAddress,
			@RequestHeader("Authorization")String jwt)throws UserException{
		
		User user=userService.findUserProfileByJwt(jwt);
		Orderr order=orderService.createOrder(user, shippingAddress);
		System.out.println("order"+order);
		return new ResponseEntity<Orderr>(order,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Orderr>>usersOrderHistory(
		@RequestHeader("Authorization")String jwt)throws UserException{
			User user=userService.findUserProfileByJwt(jwt);
			List<Orderr> orders = orderService.usersOrderHistory(user.getId());
			return new ResponseEntity<>(orders,HttpStatus.CREATED);
		}
	

    @GetMapping("/{Id}")
    public ResponseEntity<Orderr>findOrderById(
    	@PathVariable("Id")Long orderId,
    	@RequestHeader("Authorization")String jwt)throws UserException,OrderException{
    	
    	User user=userService.findUserProfileByJwt(jwt);
    	Orderr order=orderService.findOrderById(orderId);
    	
    	return new ResponseEntity<>(order,HttpStatus.ACCEPTED);}
    
}
    
	
	
	
	

