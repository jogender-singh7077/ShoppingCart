package com.mindtree.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class ShoppingCartValidator implements Validator{

	protected abstract void validateRequest(Object target, Errors errors);

	@Override
	public void validate(Object target, Errors errors) {
		validateRequest(target, errors);		
	}

}
