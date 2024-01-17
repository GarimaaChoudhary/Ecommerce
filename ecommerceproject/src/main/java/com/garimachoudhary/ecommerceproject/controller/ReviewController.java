package com.garimachoudhary.ecommerceproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garimachoudhary.ecommerceproject.exception.ProductException;
import com.garimachoudhary.ecommerceproject.exception.UserException;
import com.garimachoudhary.ecommerceproject.model.Rating;
import com.garimachoudhary.ecommerceproject.model.Review;
import com.garimachoudhary.ecommerceproject.model.User;
import com.garimachoudhary.ecommerceproject.request.RatingRequest;
import com.garimachoudhary.ecommerceproject.request.ReviewRequest;
import com.garimachoudhary.ecommerceproject.service.ReviewService;
import com.garimachoudhary.ecommerceproject.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/create")
	public ResponseEntity<Review>createReview(@RequestBody ReviewRequest req,
			@RequestHeader("Authorization")String jwt)throws UserException,ProductException{
		User user=userService.findUserProfileByJwt(jwt);
		Review review=reviewService.createReview(req,user);
		
		return new ResponseEntity<>(review,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId
			)throws UserException,ProductException{
		
		
		List<Review> reviews=reviewService.getAllReview(productId);
		
		return new ResponseEntity<>(reviews,HttpStatus.ACCEPTED);
	}
	
}
