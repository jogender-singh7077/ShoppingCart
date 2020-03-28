package com.mindtree.dao;

import java.util.List;

import com.mindtree.entities.Product;

public interface ProductDao {

	List<Product> searchProductsByCriteria(Integer productId, String productName, String category);

	List<Product> getAllProducts();
	
	Product findProductById(Integer productId);
	
	List<Product> findProductByProductIds(List<Integer> productIds);
}
