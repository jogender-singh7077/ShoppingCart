package com.mindtree.dao;

import java.util.List;

import com.mindtree.entities.Cart;
import com.mindtree.entities.CartProduct;
import com.mindtree.entities.Product;
import com.mindtree.entities.User;

public interface CartDao {

	void addToCart(User user, int productId);

	List<CartProduct> getCartDetailsByUser(User user);

	void deleteProductFromCart(User user, Integer productId);

	void deleteAllProductsFromCart(User user);

	Cart getCartByUser(User user);

	void updateCart(Integer quantity, Cart cart, Product product);

}
