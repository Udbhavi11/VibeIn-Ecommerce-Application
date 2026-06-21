package com.vibein.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vibein.dao.CartItemDAO;
import com.vibein.model.CartItem;
import com.vibein.util.DBConnection;

public class CartItemDAOImpl implements CartItemDAO {

	private static final String INSERT_CART_ITEM =
			"INSERT INTO cart_items(cart_id, variant_id, quantity) VALUES(?,?,?)";

	private static final String UPDATE_CART_ITEM =
			"UPDATE cart_items SET quantity=? WHERE cart_item_id=?";

	private static final String REMOVE_CART_ITEM =
			"DELETE FROM cart_items WHERE cart_item_id=?";

	private static final String GET_CART_ITEM_BY_ID =
			"SELECT * FROM cart_items WHERE cart_item_id=?";

	private static final String GET_CART_ITEMS_BY_CART_ID =
			"SELECT * FROM cart_items WHERE cart_id=?";

	private static final String CLEAR_CART =
			"DELETE FROM cart_items WHERE cart_id=?";

	@Override
	public boolean addCartItem(CartItem cartItem) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(INSERT_CART_ITEM)) {

			pstmt.setInt(1, cartItem.getCartId());
			pstmt.setInt(2, cartItem.getVariantId());
			pstmt.setInt(3, cartItem.getQuantity());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateCartItem(CartItem cartItem) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(UPDATE_CART_ITEM)) {

			pstmt.setInt(1, cartItem.getQuantity());
			pstmt.setInt(2, cartItem.getCartItemId());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean removeCartItem(int cartItemId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(REMOVE_CART_ITEM)) {

			pstmt.setInt(1, cartItemId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public CartItem getCartItemById(int cartItemId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_CART_ITEM_BY_ID)) {

			pstmt.setInt(1, cartItemId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				CartItem cartItem = new CartItem();

				cartItem.setCartItemId(rs.getInt("cart_item_id"));
				cartItem.setCartId(rs.getInt("cart_id"));
				cartItem.setVariantId(rs.getInt("variant_id"));
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setAddedAt(rs.getTimestamp("added_at"));

				return cartItem;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<CartItem> getCartItemsByCartId(int cartId) {

		List<CartItem> cartItems = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_CART_ITEMS_BY_CART_ID)) {

			pstmt.setInt(1, cartId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				CartItem cartItem = new CartItem();

				cartItem.setCartItemId(rs.getInt("cart_item_id"));
				cartItem.setCartId(rs.getInt("cart_id"));
				cartItem.setVariantId(rs.getInt("variant_id"));
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setAddedAt(rs.getTimestamp("added_at"));

				cartItems.add(cartItem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartItems;
	}

	@Override
	public boolean clearCart(int cartId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(CLEAR_CART)) {

			pstmt.setInt(1, cartId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}