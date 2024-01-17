package com.garimachoudhary.ecommerceproject.service;

import com.garimachoudhary.ecommerceproject.exception.CartItemException;
import com.garimachoudhary.ecommerceproject.exception.UserException;
import com.garimachoudhary.ecommerceproject.model.Cart;
import com.garimachoudhary.ecommerceproject.model.CartItem;
import com.garimachoudhary.ecommerceproject.model.Product;

public interface CartItemService {
       
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userid,Long id,CartItem cartitem) throws  CartItemException,UserException;
        
    public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
    
    public void removeCartItem(Long userId,Long cartItemId)throws CartItemException,UserException;
      
    public CartItem findCartItemById(Long cartItemId)throws CartItemException;
}
