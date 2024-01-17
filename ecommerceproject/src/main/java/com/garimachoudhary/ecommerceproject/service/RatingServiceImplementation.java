package com.garimachoudhary.ecommerceproject.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.garimachoudhary.ecommerceproject.exception.ProductException;
import com.garimachoudhary.ecommerceproject.model.Product;
import com.garimachoudhary.ecommerceproject.model.Rating;
import com.garimachoudhary.ecommerceproject.model.User;
import com.garimachoudhary.ecommerceproject.repository.RatingRepository;
import com.garimachoudhary.ecommerceproject.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService{
    
	private ProductService productService;
	private RatingRepository ratingRepository;
	
	public  RatingServiceImplementation(ProductService productService,RatingRepository ratingRepository) {
		this.productService=productService;
		this.ratingRepository=ratingRepository;
	}
	
	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		Product product=productService.findProductByid(req.getProductId());
		
		Rating rating=new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		
		return ratingRepository.getAllProductsRating(productId);
	}

}
