package com.mindtree.service;

import java.util.List;

import com.mindtree.dto.ProductSearchDto;
import com.mindtree.entities.Product;

public interface ProductService {

	List<Product> searchProductsByCriteria(ProductSearchDto searchCriteria);
	
	List<Product> getAllProducts();
	
	Product findProductById(Integer productId);
	
	List<Product> findProductByProductIds(List<Integer> productIds);
}
