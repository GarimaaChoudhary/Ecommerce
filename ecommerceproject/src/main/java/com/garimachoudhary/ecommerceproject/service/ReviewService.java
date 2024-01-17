package com.garimachoudhary.ecommerceproject.service;

import java.util.List;

import com.garimachoudhary.ecommerceproject.exception.ProductException;
import com.garimachoudhary.ecommerceproject.model.Review;
import com.garimachoudhary.ecommerceproject.model.User;
import com.garimachoudhary.ecommerceproject.request.ReviewRequest;

public interface ReviewService {

	
	public Review createReview(ReviewRequest req,User user) throws ProductException;
	public List<Review>getAllReview(Long productId);
}
