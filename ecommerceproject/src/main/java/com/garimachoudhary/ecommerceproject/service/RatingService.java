package com.garimachoudhary.ecommerceproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.garimachoudhary.ecommerceproject.exception.ProductException;
import com.garimachoudhary.ecommerceproject.model.Rating;
import com.garimachoudhary.ecommerceproject.model.User;
import com.garimachoudhary.ecommerceproject.request.RatingRequest;


public interface RatingService {
	
	public Rating createRating(RatingRequest req,User user)throws ProductException;
	public List<Rating>getProductsRating(Long productId);
	

}
