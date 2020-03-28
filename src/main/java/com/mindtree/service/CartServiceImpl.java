package com.mindtree.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.dao.CartDaoImpl;
import com.mindtree.entities.Cart;
import com.mindtree.entities.CartProduct;
import com.mindtree.entities.Product;
import com.mindtree.entities.User;

@Service
@Transactional
public class CartServiceImpl implements CartService{
	
	private static final Logger logger = 
            LoggerFactory.getLogger(CartServiceImpl.class);
	
	@Autowired
	private CartDaoImpl cartDaoImpl;

	@Override
	public void addToCart(final User user, final int productId) {
		try {
		cartDaoImpl.addToCart(user, productId);
		}catch(ConstraintViolationException validationEx) {
			logger.debug("error while adding product - {} to cart ", productId, validationEx);
			throw validationEx;
		}
	}
	
	@Override
	public List<CartProduct> getCartDetailsByUser(final User user) {
		logger.info("fetching cart details for user - {} ", user);
		return cartDaoImpl.getCartDetailsByUser(user);
	}
	
	@Override
	public void deleteProductFromCart(final User user, final Integer productId) {
		cartDaoImpl.deleteProductFromCart(user, productId);
	}
	
	@Override
	public void deleteAllProductsFromCart(final User user) {
		cartDaoImpl.deleteAllProductsFromCart(user);
	}
	
	@Override
	public Cart getCartByUser(final User user) {
		logger.info("fetching cart details for user - {} ", user);
		return cartDaoImpl.getCartByUser(user);
	}
	
	@Override
	public void updateCart(final Integer quantity, final Cart cart, final Product product) {
		logger.info("updating cart product - {} ", product);
		try {
	    cartDaoImpl.updateCart(quantity, cart, product);
		} catch(ConstraintViolationException validationEx) {
			logger.debug("error while updating cart product ", validationEx);
				throw validationEx;
		}
	}

}
