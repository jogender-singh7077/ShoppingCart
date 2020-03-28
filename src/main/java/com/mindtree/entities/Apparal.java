package com.mindtree.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("apparal")
public class Apparal extends Product{

	public Apparal() {
		super();
	}

	@Column(name = "cloth_type")
	private String clothType;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "design")
	private String design;

	public String getType() {
		return clothType;
	}

	public void setType(String type) {
		this.clothType = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((clothType == null) ? 0 : clothType.hashCode());
		result = prime * result + ((design == null) ? 0 : design.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apparal other = (Apparal) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (clothType == null) {
			if (other.clothType != null)
				return false;
		} else if (!clothType.equals(other.clothType))
			return false;
		if (design == null) {
			if (other.design != null)
				return false;
		} else if (!design.equals(other.design))
			return false;
		return true;
	}
	
}
