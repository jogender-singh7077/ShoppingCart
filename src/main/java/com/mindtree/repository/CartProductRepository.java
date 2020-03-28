package com.mindtree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.entities.Cart;
import com.mindtree.entities.CartProduct;
import com.mindtree.entities.Product;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer>{

	CartProduct findByProduct(@Param("product") Product product );
	
	List<CartProduct> findCartProductByCart(@Param("cart") Cart cart);
	
	@Transactional
	@Modifying
	void deleteCartProductByProductIdAndCartId(@Param("product") Product product, @Param("cart") Cart cart);
	
	@Transactional
	@Modifying
	void deleteCartProductByCartId(@Param("cart") Cart cart);
	
	@Transactional
	@Modifying
	void updateCart(@Param("quantity") Integer quantity, @Param("cart") Cart cart, @Param("product") Product product);
}
