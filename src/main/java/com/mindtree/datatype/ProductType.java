package com.mindtree.datatype;

public enum ProductType {
	
	BOOK("book"),
	
	APPARAL("apparal");
	
	private String value;
	
	ProductType(final String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
