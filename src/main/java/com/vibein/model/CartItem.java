package com.vibein.model;

import java.sql.Timestamp;

public class CartItem {

	private int cartItemId;
	private int cartId;
	private int variantId;
	private int quantity;
	private Timestamp addedAt;

	public CartItem() {
		super();
	}

	public CartItem(int cartItemId, int cartId, int variantId, int quantity, Timestamp addedAt) {
		super();
		this.cartItemId = cartItemId;
		this.cartId = cartId;
		this.variantId = variantId;
		this.quantity = quantity;
		this.addedAt = addedAt;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getVariantId() {
		return variantId;
	}

	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Timestamp getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(Timestamp addedAt) {
		this.addedAt = addedAt;
	}

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", cartId=" + cartId + ", variantId=" + variantId
				+ ", quantity=" + quantity + ", addedAt=" + addedAt + "]";
	}
}