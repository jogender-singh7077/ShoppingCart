package com.mindtree.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.entities.Product;
import com.mindtree.repository.ProductRepository;

@Component
public class ProductDaoImpl implements ProductDao{
	
	private static final Logger logger = 
            LoggerFactory.getLogger(ProductDaoImpl.class);

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> searchProductsByCriteria(Integer productId, String productName, String category) {
		logger.debug("fetching the product by id - {} name - {} category - {}", productId, productName, category);
		List<Product> products = productRepository.findByProductIdOrProductNameOrProductType(productId, productName, category);
		return products;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public Product findProductById(final Integer productId) {
		Product product = null;
		try {
			product = productRepository.findById(productId).get();
		} catch(NoSuchElementException exp) {
			logger.debug("error while fetching the product - {} ", productId, exp);
			throw exp;
		}
		return product;
	}
	
	@Override
	public List<Product> findProductByProductIds(final List<Integer> productIds) {
		logger.debug("fetching products by ids - {}", productIds);
		return productRepository.findProductByIds(productIds);
	}

}
