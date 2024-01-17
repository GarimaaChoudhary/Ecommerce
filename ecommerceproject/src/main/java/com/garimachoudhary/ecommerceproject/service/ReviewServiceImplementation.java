package com.garimachoudhary.ecommerceproject.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.garimachoudhary.ecommerceproject.exception.ProductException;
import com.garimachoudhary.ecommerceproject.model.Product;
import com.garimachoudhary.ecommerceproject.model.Review;
import com.garimachoudhary.ecommerceproject.model.User;
import com.garimachoudhary.ecommerceproject.repository.ProductRepository;
import com.garimachoudhary.ecommerceproject.repository.ReviewRepository;
import com.garimachoudhary.ecommerceproject.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {
	
	private ProductService productService;
	private ReviewRepository reviewRepository;
	private ProductRepository productRepository;
	
	public ReviewServiceImplementation( ProductService productService,
			ReviewRepository reviewRepository,
			ProductRepository productRepository) {
		this.productService=productService;
		this.reviewRepository=reviewRepository;
		this.productRepository=productRepository;
	}
	

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
	Product product=productService.findProductByid(req.getProductId());
	
	Review review=new Review();
	review.setUser(user);
	review.setProduct(product);
	review.setReview(req.getReview());
	review.setCreatedAt(LocalDateTime.now());
	
	
		return reviewRepository.save(review) ;
	}

	@Override
	public List<Review> getAllReview(Long productId) {
	
		return reviewRepository.getAllProductsReview(productId);
	}

}
