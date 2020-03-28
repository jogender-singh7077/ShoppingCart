package com.mindtree.exception;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations = Controller.class)
public class ShoppingCartExceptionHandler {
	
	private static final Logger logger = 
            LoggerFactory.getLogger(ShoppingCartExceptionHandler.class);
 
	@ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public String handleValidationError(ConstraintViolationException ex) {
		logger.debug("constraint violation while processing user request ", ex);
        return ex.getConstraintViolations().stream().findFirst().get().getMessage();
    }
	
	@ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex) {
		logger.debug("error occured while processing user request ", ex);
		return new ModelAndView("error", "errorMessage", 
				(ex.getMessage()==null || ex.getMessage().isEmpty())?"Unexpected error occurred while processing your request":ex.getMessage());
    }
}
