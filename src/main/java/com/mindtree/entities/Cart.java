package com.mindtree.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
@NamedQueries({
    @NamedQuery(name="Cart.findByUser",
                query="SELECT c FROM Cart c WHERE c.user = :user")
}) 
public class Cart implements Comparable<Cart>{
	
	public Cart() {
		super();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id", unique=true)
	private int cartId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
	
	@OneToMany(mappedBy = "cart")
	private List<CartProduct> cartProducts =  new ArrayList<>();

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartProduct> getProducts() {
		return cartProducts;
	}

	public void setProducts(List<CartProduct> products) {
		this.cartProducts = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		result = prime * result + ((cartProducts == null) ? 0 : cartProducts.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartId != other.cartId)
			return false;
		if (cartProducts == null) {
			if (other.cartProducts != null)
				return false;
		} else if (!cartProducts.equals(other.cartProducts))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public int compareTo(Cart cart) {
		return this.cartId - cart.cartId;
	}
	
	
	
}
