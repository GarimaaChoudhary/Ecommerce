package com.garimachoudhary.ecommerceproject.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.garimachoudhary.ecommerceproject.exception.ProductException;
import com.garimachoudhary.ecommerceproject.model.Product;
import com.garimachoudhary.ecommerceproject.request.CreateProductRequest;

public interface ProductService {
	
	public Product createProduct(CreateProductRequest req );
	
	public String deleteProduct(Long productId)throws ProductException;
	
	public Product updateProduct(Long productId,Product req) throws ProductException;
	
	public Product findProductByid(Long id) throws ProductException;
	
	public List<Product> findProductByCategory(String category);
	
	public Page<Product>getAllProduct(String category,List<String>colors,List<String>sizes,Integer minPrice,Integer maxPrice
			,Integer minDiscount,String sort,String stock,Integer pagenumber,Integer pageSize );
	
	
	

}
