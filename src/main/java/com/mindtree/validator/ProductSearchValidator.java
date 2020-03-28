package com.mindtree.validator;

import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.mindtree.dto.ProductSearchDto;
import com.mindtree.entities.Product;
import com.mindtree.service.ProductServiceImpl;

@Component
public class ProductSearchValidator extends ShoppingCartValidator{
	
	private static final Logger logger = 
            LoggerFactory.getLogger(ProductSearchValidator.class);
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ProductSearchDto.class);
	}

	@Override
	protected void validateRequest(Object target, Errors errors) {
		ProductSearchDto productSearchDto = (ProductSearchDto)target;
		logger.debug("validating product search by criteria - {} ", productSearchDto);
		Integer productId = productSearchDto.getProductId();
		if(productId != null) {
			Product product = null;
			try {
				product = productServiceImpl.findProductById(productId);
			} catch(NoSuchElementException exp) {
				errors.rejectValue("productId", "errorCode", "No Product Found With The Product Id - "+productId);
			}
			if(product==null) {
				errors.rejectValue("productId", "errorCode", "No Product Found With The Product Id - "+productId);
			}
		} else if(productId==null && StringUtils.isEmpty(productSearchDto.getProductName()) && StringUtils.isEmpty(productSearchDto.getCategory())) {
			errors.rejectValue("productId", "errorCode", "One Of The Field Is Required For Searching The Product");
		}
	}

}
