package com.vibein.dao;

import java.util.List;

import com.vibein.model.CartItem;

public interface CartItemDAO {

	boolean addCartItem(CartItem cartItem);

	boolean updateCartItem(CartItem cartItem);

	boolean removeCartItem(int cartItemId);

	CartItem getCartItemById(int cartItemId);

	List<CartItem> getCartItemsByCartId(int cartId);

	boolean clearCart(int cartId);
}