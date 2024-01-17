package com.garimachoudhary.ecommerceproject.service;

import org.springframework.stereotype.Service;

import com.garimachoudhary.ecommerceproject.exception.ProductException;
import com.garimachoudhary.ecommerceproject.model.Cart;
import com.garimachoudhary.ecommerceproject.model.CartItem;
import com.garimachoudhary.ecommerceproject.model.Product;
import com.garimachoudhary.ecommerceproject.model.User;
import com.garimachoudhary.ecommerceproject.repository.CartRepository;
import com.garimachoudhary.ecommerceproject.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService {

	private ProductService productService;
	private CartRepository cartRepository;
	private CartItemService cartItemService;
	
	public CartServiceImplementation(ProductService productService,
			CartRepository cartRepository,
			CartItemService cartItemService	) {
		this.productService=productService;
		this.cartRepository=cartRepository;
		this.cartItemService=cartItemService;
	}
	@Override
	public Cart createCart(User user) {
		Cart cart=new Cart();
		cart.setUser(user);
		
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
	Cart cart=cartRepository.findByUserId(userId);
	 Product product=productService.findProductByid(req.getProductId());
	 
	 CartItem isPresent=cartItemService.isCartItemExist(cart,product,req.getSize(),userId);
	 
	 if(isPresent==null) {
		 CartItem cartItem=new CartItem();
		 cartItem.setProduct(product);
		 cartItem.setCart(cart);
		 cartItem.setQuantity(req.getQuantity());
		 cartItem.setUserId(userId);
		 
		 int price=req.getQuantity()*product.getDiscountedPrice();
		 cartItem.setPrice(price);
		 cartItem.setSize(req.getSize());
		 
		 CartItem createdCartItem=cartItemService.createCartItem(cartItem);
		 cart.getCartItems().add(createdCartItem);
		 
	 }
		return "item added to cart";
	}

	@Override
	public Cart findUserCart(Long userId) {
	    
		Cart cart=cartRepository.findByUserId(userId);
		
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		
		//5
		//1->100
		//2->200
		//3->400
		
		for(CartItem cartItem :cart.getCartItems()) {
			totalPrice =totalPrice+cartItem.getPrice();
			totalDiscountedPrice=totalDiscountedPrice+cartItem.getDiscountedPrice();
			totalItem=totalItem+cartItem.getQuantity();
		}
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscount(totalPrice-totalDiscountedPrice);
		
		
		
		return cartRepository.save(cart);
	}
  
}
