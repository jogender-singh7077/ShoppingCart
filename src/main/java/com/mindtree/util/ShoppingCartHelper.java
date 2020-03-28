package com.mindtree.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mindtree.dto.CartProductDto;
import com.mindtree.dto.ProductDisplayDto;
import com.mindtree.dto.UpdateItemDto;
import com.mindtree.entities.Apparal;
import com.mindtree.entities.Book;
import com.mindtree.entities.CartProduct;
import com.mindtree.entities.Product;

public class ShoppingCartHelper {
	
	private static final Logger logger = 
            LoggerFactory.getLogger(ShoppingCartHelper.class);

	public static double calculateCartTotalPrice(List<CartProductDto> cartProducts) {
		logger.info("calculating the cart total price");
		return cartProducts.stream().mapToDouble(CartProductDto::getTotal).sum();
	}
	
	public static double calculateCartTotal(List<CartProduct> productList, UpdateItemDto updateItem) {
		double cartPrice = 0.0;
		for(CartProduct cartProduct : productList) {
			if(cartProduct.getProduct().getProductId().equals(updateItem.getProductId())) {
				cartProduct.setQuantity(updateItem.getQuantity());
			}
			cartPrice =  cartPrice + (cartProduct.getQuantity() * cartProduct.getProduct().getPrice());
		}
		logger.debug("cart total for the cart is - {}", cartPrice);
		return cartPrice;
	}
	
	public static List<CartProductDto> transfromProductsToDto(List<CartProduct> cartProducts, List<Product> products){
		List<CartProductDto> cartProductDtoList = new ArrayList<>();
		for(CartProduct cartProduct : cartProducts) {
			CartProductDto cartProductDto = new CartProductDto();
			Optional<Product> productItem = products.stream().filter(product -> product.getProductId().equals(cartProduct.getProduct().getProductId())).findFirst();
			Product item = productItem.get();
			
			cartProductDto.setProductId(item.getProductId());
			cartProductDto.setProductName(item.getProductName());
			cartProductDto.setQuantity(cartProduct.getQuantity());
			cartProductDto.setTotal(item.getPrice() * cartProduct.getQuantity());
			
			cartProductDtoList.add(cartProductDto);
		}
		return cartProductDtoList;
	}
	
	public static List<ProductDisplayDto> transformProductToScreenDto(final List<Product> products) {
		logger.info("transforming the products to screen dto");
		List<ProductDisplayDto> screenDtos = new ArrayList<>();
		for(Product product : products) {
			ProductDisplayDto screenDto = new ProductDisplayDto();
			if(product instanceof Book) {
				Book book = (Book) product;
				StringBuffer details = new StringBuffer();
				details.append(book.getProductName())
				.append("\n")
				.append(book.getGenre())
				.append("\n")
				.append(book.getAuthor())
				.append("\n")
				.append(book.getPublications());
				screenDto.setProductDetails(details.toString());
				screenDto.setProductId(book.getProductId());
				screenDto.setPrice(book.getPrice());
			}else if(product instanceof Apparal) {
				Apparal apparal = (Apparal) product;
				StringBuffer details = new StringBuffer();
				details.append(apparal.getProductName())
				.append("\n")
				.append(apparal.getType())
				.append("\n")
				.append(apparal.getBrand())
				.append("\n")
				.append(apparal.getDesign());
				screenDto.setProductDetails(details.toString());
				screenDto.setProductId(apparal.getProductId());
				screenDto.setPrice(apparal.getPrice());
			}
			screenDtos.add(screenDto);
		}
		return screenDtos;
	}
}
