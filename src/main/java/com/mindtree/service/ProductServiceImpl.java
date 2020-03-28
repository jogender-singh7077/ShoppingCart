package com.mindtree.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.dao.ProductDao;
import com.mindtree.dto.ProductSearchDto;
import com.mindtree.entities.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	private static final Logger logger = 
            LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> searchProductsByCriteria(ProductSearchDto searchCriteria) {
		logger.debug("searching product by criteria - {} ", searchCriteria);
		Integer productId = searchCriteria.getProductId();
		String productName = searchCriteria.getProductName();
		String category = searchCriteria.getCategory();
		List<Product> products = productDao.searchProductsByCriteria(productId, productName, category);
		return products;
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	
	@Override
	public Product findProductById(final Integer productId) {
		logger.debug("fetching prodct by id - {} ", productId);
		Product product = null;
		try {
			product = productDao.findProductById(productId);
		} catch(NoSuchElementException exp) {
			logger.debug("error while fetching product by id - {}", productId);
			throw exp;
		}
		return product;
	}

	@Override
	public List<Product> findProductByProductIds(List<Integer> productIds) {
		logger.debug("fetching product by ids - {}", productIds);
		return productDao.findProductByProductIds(productIds);
	}

}
