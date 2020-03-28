package com.mindtree.util;

import java.util.Comparator;

import com.mindtree.entities.Product;

public class ProductPriceComparator implements Comparator<Product>{

	@Override
	public int compare(Product firstProduct, Product secondProduct) {
		if(firstProduct.getPrice().equals(secondProduct.getPrice())){
			return 0;
		}
		return firstProduct.getPrice() < secondProduct.getPrice() ? -1 : +1;
	}

}
