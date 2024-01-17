package com.garimachoudhary.ecommerceproject.service;

import com.garimachoudhary.ecommerceproject.exception.UserException;
import com.garimachoudhary.ecommerceproject.model.User;

public interface UserService {

	
	public User findUserById(Long userId)throws UserException;
	
	public User findUserProfileByJwt(String jwt)throws UserException;
	
}
