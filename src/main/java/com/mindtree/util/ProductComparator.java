package com.mindtree.util;

import java.util.Comparator;

import com.mindtree.entities.Product;

public class ProductComparator implements Comparator<Product>{

	@Override
	public int compare(Product firstProduct, Product secondProduct) {
		if(firstProduct.getProductId().equals(secondProduct.getProductId())  
				&& firstProduct.getProductName().equalsIgnoreCase(secondProduct.getProductName())) {
			return 0;
		}
		return firstProduct.getProductId() - secondProduct.getProductId();
	}

}
