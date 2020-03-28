package com.mindtree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mindtree.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	List<Product> findByProductIdOrProductNameOrProductType(@Param("productId") Integer productId, @Param("productName") String productName, @Param("type") String type);
	
	List<Product> findProductByIds(@Param("productIds") List<Integer> productIds);
	
}
