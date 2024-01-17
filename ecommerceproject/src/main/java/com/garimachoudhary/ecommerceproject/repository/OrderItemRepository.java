package com.garimachoudhary.ecommerceproject.repository;
import com.garimachoudhary.ecommerceproject.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
	
}


