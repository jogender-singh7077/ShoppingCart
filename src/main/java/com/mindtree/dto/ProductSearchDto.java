package com.mindtree.dto;

public class ProductSearchDto {
	
	private Integer productId;
	
	private String productName;
	
	private String category;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductSearchDto [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ "]";
	}
	
}
