package com.mindtree.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cart_products")
@NamedQueries({
    @NamedQuery(name="CartProduct.findByProduct",
                query="SELECT cp FROM CartProduct cp WHERE cp.product = :product"),
    @NamedQuery(name="CartProduct.findCartProductByCart",
    			query="SELECT cp FROM CartProduct cp WHERE cp.cart = :cart"),
    @NamedQuery(name="CartProduct.deleteCartProductByProductIdAndCartId",
				query="DELETE FROM CartProduct cp WHERE cp.product = :product AND cp.cart = :cart"),
    @NamedQuery(name="CartProduct.deleteCartProductByCartId",
	query="DELETE FROM CartProduct cp WHERE cp.cart = :cart"),
	@NamedQuery(name="CartProduct.updateCart",
	query="update CartProduct cp set cp.quantity = :quantity where cp.cart = :cart and  cp.product = :product")
})
public class CartProduct implements Comparable<CartProduct>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "Product quantity in cart cannot be null")
	@Min(value=1, message="Cannot Add product to cart with 0 quantity")
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	@NotNull(message = "Product should belong to available products")
	private Product product;
	
	@ManyToOne
	@NotNull(message = "Cart should have a valid cart id")
	private Cart cart;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int compareTo(CartProduct cartProduct) {
		return this.id - cartProduct.id;
	}

}
