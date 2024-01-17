package com.garimachoudhary.ecommerceproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garimachoudhary.ecommerceproject.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{
	

}
