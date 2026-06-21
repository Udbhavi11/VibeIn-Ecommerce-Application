package com.vibein.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vibein.dao.CartDAO;
import com.vibein.model.Cart;
import com.vibein.util.DBConnection;

public class CartDAOImpl implements CartDAO {

	private static final String CREATE_CART =
			"INSERT INTO cart(user_id) VALUES(?)";

	private static final String GET_CART_BY_ID =
			"SELECT * FROM cart WHERE cart_id=?";

	private static final String GET_CART_BY_USER_ID =
			"SELECT * FROM cart WHERE user_id=?";

	private static final String DELETE_CART =
			"DELETE FROM cart WHERE cart_id=?";

	@Override
	public boolean createCart(int userId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(CREATE_CART)) {

			pstmt.setInt(1, userId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Cart getCartById(int cartId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_CART_BY_ID)) {

			pstmt.setInt(1, cartId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				Cart cart = new Cart();

				cart.setCartId(rs.getInt("cart_id"));
				cart.setUserId(rs.getInt("user_id"));
				cart.setCreatedAt(rs.getTimestamp("created_at"));

				return cart;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Cart getCartByUserId(int userId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_CART_BY_USER_ID)) {

			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				Cart cart = new Cart();

				cart.setCartId(rs.getInt("cart_id"));
				cart.setUserId(rs.getInt("user_id"));
				cart.setCreatedAt(rs.getTimestamp("created_at"));

				return cart;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean deleteCart(int cartId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(DELETE_CART)) {

			pstmt.setInt(1, cartId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}