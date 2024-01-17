package com.garimachoudhary.ecommerceproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.garimachoudhary.ecommerceproject.exception.CartItemException;
import com.garimachoudhary.ecommerceproject.exception.UserException;
import com.garimachoudhary.ecommerceproject.model.CartItem;
import com.garimachoudhary.ecommerceproject.model.User;
import com.garimachoudhary.ecommerceproject.response.ApiResponse;
import com.garimachoudhary.ecommerceproject.service.CartItemService;
import com.garimachoudhary.ecommerceproject.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
			@RequestHeader("Authorization")String jwt)throws UserException,CartItemException{
		User user=userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse res=new ApiResponse();
		res.setMessage("item deleted from cart");
		res.setStatus(true);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem> updateCartItem(
			@RequestBody CartItem cartitem,
			@PathVariable Long cartItemId,
			@RequestHeader("Authorization")String jwt)throws UserException,CartItemException{
		
		User user=userService.findUserProfileByJwt(jwt);
		CartItem updatedCartItem=cartItemService.updateCartItem(user.getId(), cartItemId, cartitem);
		return new 	ResponseEntity<>(updatedCartItem,HttpStatus.OK);
	}
	

}
