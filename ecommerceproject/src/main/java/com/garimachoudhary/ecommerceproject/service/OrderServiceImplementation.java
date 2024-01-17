package com.garimachoudhary.ecommerceproject.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.garimachoudhary.ecommerceproject.exception.OrderException;
import com.garimachoudhary.ecommerceproject.model.Address;
import com.garimachoudhary.ecommerceproject.model.Cart;
import com.garimachoudhary.ecommerceproject.model.CartItem;
import com.garimachoudhary.ecommerceproject.model.OrderItem;
import com.garimachoudhary.ecommerceproject.model.Orderr;
import com.garimachoudhary.ecommerceproject.model.User;
import com.garimachoudhary.ecommerceproject.repository.AddressRepository;
import com.garimachoudhary.ecommerceproject.repository.CartRepository;
import com.garimachoudhary.ecommerceproject.repository.OrderItemRepository;
import com.garimachoudhary.ecommerceproject.repository.OrderRepository;
import com.garimachoudhary.ecommerceproject.repository.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService{
	
	private OrderRepository orderRepository;
	private CartService cartService;
	private AddressRepository addressRepository;
	private UserRepository userRepository;
	private OrderItemService orderItemService;
	private OrderItemRepository orderItemRepository;
	
	
	
	
	

	public OrderServiceImplementation(OrderRepository orderRepository, CartService cartService,
			AddressRepository addressRepository, UserRepository userRepository, OrderItemService orderItemService,
			OrderItemRepository orderItemRepository) {
		super();
		this.orderRepository = orderRepository;
		this.cartService = cartService;
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		this.orderItemService = orderItemService;
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	public Orderr createOrder(User user, Address shippingAddress) {
		shippingAddress.setUser(user);
		Address address=addressRepository.save(shippingAddress);
		user.getAddress().add(address);
		userRepository.save(user);
		
		Cart cart=cartService.findUserCart(user.getId());
		List<OrderItem> orderItems=new ArrayList<>();
		
		for(CartItem item:cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItem createdOrderItem=orderItemRepository.save(orderItem);
			orderItems.add(createdOrderItem);
			
		}
		Orderr createdOrder=new Orderr();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());
		
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());
		
		Orderr savedOrder=orderRepository.save(createdOrder);
		
		for(OrderItem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		
		return savedOrder;
	}

	@Override
	public Orderr findOrderById(Long orderId) throws OrderException {
	    Optional<Orderr> opt=orderRepository.findById(orderId); 
	    
	    if(opt.isPresent()) {
	    	return opt.get();
	    }
		throw new OrderException("order not exist with id"+orderId);
	}

	@Override
	public List<Orderr> usersOrderHistory(Long userId) {
		List<Orderr> orders=orderRepository.getUsersOrders(userId);
		return orders;
	}

	@Override
	public Orderr placedOrder(Long orderId) throws OrderException {
		Orderr order=findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");
		
		return order;
	}

	@Override
	public Orderr confirmedOrder(Long orderId) throws OrderException {
		Orderr order=findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		
		return orderRepository.save(order);
	}

	@Override
	public Orderr ShippedOrder(Long orderId) throws OrderException {
	
		Orderr order=findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		
		return orderRepository.save(order);
	}
	

	@Override
	public Orderr deliveredOrder(Long orderId) throws OrderException {
		Orderr order=findOrderById(orderId);
		order.setOrderStatus("DELIVERED");
		
		return orderRepository.save(order);
	}

	@Override
	public Orderr cancelledOrder(Long orderId) throws OrderException {
		Orderr order=findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		
		return orderRepository.save(order);
	}

	@Override
	public List<Orderr> getAllOrders() {
		
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Orderr order=findOrderById(orderId);
		orderRepository.deleteById(orderId);
		
	}

}
