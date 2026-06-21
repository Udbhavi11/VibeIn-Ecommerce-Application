package com.vibein.dao;

import com.vibein.model.Cart;

public interface CartDAO {

	boolean createCart(int userId);

	Cart getCartById(int cartId);

	Cart getCartByUserId(int userId);

	boolean deleteCart(int cartId);
}