package com.garimachoudhary.ecommerceproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.garimachoudhary.ecommerceproject.exception.OrderException;
import com.garimachoudhary.ecommerceproject.model.Orderr;
import com.garimachoudhary.ecommerceproject.response.ApiResponse;
import com.garimachoudhary.ecommerceproject.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public ResponseEntity<List<Orderr>> getAllOrdersHandler(){
		List<Orderr> orders=orderService.getAllOrders();
		return new ResponseEntity<List<Orderr>>(orders,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/confirmed")
	public ResponseEntity<Orderr> ConfirmedOrderHandler(@PathVariable Long orderId,
	@RequestHeader("Authorization")String jwt)throws OrderException{
		Orderr order=orderService.confirmedOrder(orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<Orderr> ShippedOrderHandler(@PathVariable Long orderId,
	@RequestHeader("Authorization")String jwt)throws OrderException{
		Orderr order=orderService.ShippedOrder(orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<Orderr> DeliverOrderHandler(@PathVariable Long orderId,
	@RequestHeader("Authorization")String jwt)throws OrderException{
		Orderr order=orderService.deliveredOrder(orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/dcancel")
	public ResponseEntity<Orderr> CancelOrderHandler(@PathVariable Long orderId,
	@RequestHeader("Authorization")String jwt)throws OrderException{
		Orderr order=orderService.cancelledOrder(orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	@PutMapping("/{orderId}/delete")
	public ResponseEntity<ApiResponse> DeleteOrderHandler(@PathVariable Long orderId,
	@RequestHeader("Authorization")String jwt)throws OrderException{
		
		orderService.deleteOrder(orderId);
		
		ApiResponse res=new ApiResponse();
		res.setMessage("order deleted successfully");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
