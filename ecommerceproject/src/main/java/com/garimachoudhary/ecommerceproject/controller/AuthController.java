package com.garimachoudhary.ecommerceproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garimachoudhary.ecommerceproject.Config.JwtProvider;
import com.garimachoudhary.ecommerceproject.exception.UserException;
import com.garimachoudhary.ecommerceproject.model.Cart;
import com.garimachoudhary.ecommerceproject.model.User;
import com.garimachoudhary.ecommerceproject.repository.UserRepository;
import com.garimachoudhary.ecommerceproject.request.LoginRequest;
import com.garimachoudhary.ecommerceproject.response.AuthResponse;
import com.garimachoudhary.ecommerceproject.service.CartService;
import com.garimachoudhary.ecommerceproject.service.CustomerUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CustomerUserServiceImplementation customerUserService;
	private CartService cartService;
	

	public AuthController(UserRepository userRepository,
			CustomerUserServiceImplementation customerUserService
			, PasswordEncoder passwordEncoder
			,JwtProvider jwtProvider,CartService cartService) {
		this.userRepository=userRepository;
		this.customerUserService=customerUserService;
		this.passwordEncoder=passwordEncoder;
		this.jwtProvider=jwtProvider;
		this.cartService=cartService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user)throws UserException{
		String email=user.getEmail();
		String password=user.getPassword();
		String firstname=user.getFirstName();
		String lastname=user.getLastName();
		
		User isEmailExist=userRepository.findByEmail(email);
		if(isEmailExist!=null) {
			throw new UserException("Email is already used with another account");
		}
		
		User createdUser=new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstname);
		createdUser.setLastName(lastname);
		
		User savedUser=userRepository.save(createdUser);
		Cart cart=cartService.createCart(savedUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	
	    String token=jwtProvider.generateToken(authentication);
	    AuthResponse authResponse=new AuthResponse();
	    authResponse.setJwt(token);
	    authResponse.setMessage("Signup success");
	    
	    return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}
     @PostMapping("/signin")
    public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest){
    	String username=loginRequest.getEmail();
    	String password=loginRequest.getPassword();
    	Authentication authentication=authenticate(username,password);
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	
    	 String token=jwtProvider.generateToken(authentication);
  	    AuthResponse authResponse=new AuthResponse();
  	     authResponse.setJwt(token);
	     authResponse.setMessage("Signin success");
  	    
  	    return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    	}

	private Authentication authenticate(String username, String password) {
	    UserDetails userDetails=customerUserService.loadUserByUsername(username);
	    
	    if(userDetails==null) {
	    	throw new BadCredentialsException("Invalid username");
	    }
	    
	    if(!passwordEncoder.matches(password,userDetails.getPassword())) {
	    	throw new BadCredentialsException("Invalid Password");
	    }
	    return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		
	}

}
   
