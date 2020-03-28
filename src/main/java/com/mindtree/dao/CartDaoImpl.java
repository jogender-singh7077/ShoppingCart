package com.mindtree.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.entities.Cart;
import com.mindtree.entities.CartProduct;
import com.mindtree.entities.Product;
import com.mindtree.entities.User;
import com.mindtree.repository.CartProductRepository;
import com.mindtree.repository.CartRepository;
import com.mindtree.repository.ProductRepository;

@Component
public class CartDaoImpl implements CartDao {
	
	private static final Logger logger = 
            LoggerFactory.getLogger(CartDaoImpl.class);
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartProductRepository cartProductRepository;
	
	@Override
	public void addToCart(User user, int productId) {
		logger.info("adding the product - {} to the cart for user - {} ", productId, user);
		Cart cart = cartRepository.findByUser(user);
		Optional<Product> newProduct = productRepository.findById(productId);
		CartProduct cartProduct = cartProductRepository.findByProduct(newProduct.get());
		if(cartProduct != null) {
			cartProduct.setQuantity(cartProduct.getQuantity() + 1);
			cartProductRepository.save(cartProduct);
		}else {
			cartProduct = new CartProduct();
			cartProduct.setProduct(newProduct.get());
			cartProduct.setQuantity(1);
			cartProduct.setCart(cart);
			cartProductRepository.save(cartProduct);
		}
		cart.getProducts().add(cartProduct);
		cartRepository.saveAndFlush(cart);
	}
	
	@Override
	public List<CartProduct> getCartDetailsByUser(final User user) {
		logger.info("fetching cart details for the user -  {} ", user);
		Cart cart = cartRepository.findByUser(user);
		List<CartProduct> cartProducts = cartProductRepository.findCartProductByCart(cart);
		return cartProducts;
	}
	
	@Override
	public Cart getCartByUser(final User user) {
		Cart cart = cartRepository.findByUser(user);
		return cart;
	}

	@Override
	public void deleteProductFromCart(final User user, final Integer productId) {
		logger.debug("deleting the product - {} from the cart for user - {}", productId, user);
		Cart cart = cartRepository.findByUser(user);
		Product product = productRepository.findById(productId).get();
		cartProductRepository.deleteCartProductByProductIdAndCartId(product, cart);
	}
	
	@Override
	public void deleteAllProductsFromCart(final User user) {
		logger.info("deleting all the products from the cart for user - {}", user);
		Cart cart = cartRepository.findByUser(user);
		cartProductRepository.deleteCartProductByCartId(cart);
	}
	
	@Override
	public void updateCart(final Integer quantity, final Cart cart, final Product product) {
		logger.debug("updating the cart product - {} with quantity - {} ", product, quantity);
		cartProductRepository.updateCart(quantity, cart, product);
	}
	
}
