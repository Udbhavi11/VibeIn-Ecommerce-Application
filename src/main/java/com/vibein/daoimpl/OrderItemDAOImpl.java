package com.vibein.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vibein.dao.OrderItemDAO;
import com.vibein.model.OrderItem;
import com.vibein.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

	private static final String INSERT_ORDER_ITEM =
			"INSERT INTO order_items(order_id, variant_id, quantity, price) VALUES(?,?,?,?)";

	private static final String GET_ORDER_ITEM_BY_ID =
			"SELECT * FROM order_items WHERE order_item_id=?";

	private static final String GET_ORDER_ITEMS_BY_ORDER_ID =
			"SELECT * FROM order_items WHERE order_id=?";

	private static final String DELETE_ORDER_ITEM =
			"DELETE FROM order_items WHERE order_item_id=?";

	private static final String DELETE_ORDER_ITEMS_BY_ORDER_ID =
			"DELETE FROM order_items WHERE order_id=?";

	@Override
	public boolean addOrderItem(OrderItem orderItem) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(INSERT_ORDER_ITEM)) {

			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getVariantId());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setBigDecimal(4, orderItem.getPrice());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean addOrderItems(List<OrderItem> orderItems) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(INSERT_ORDER_ITEM)) {

			for (OrderItem orderItem : orderItems) {

				pstmt.setInt(1, orderItem.getOrderId());
				pstmt.setInt(2, orderItem.getVariantId());
				pstmt.setInt(3, orderItem.getQuantity());
				pstmt.setBigDecimal(4, orderItem.getPrice());

				pstmt.addBatch();
			}

			int[] result = pstmt.executeBatch();

			return result.length > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public OrderItem getOrderItemById(int orderItemId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_ORDER_ITEM_BY_ID)) {

			pstmt.setInt(1, orderItemId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				OrderItem orderItem = new OrderItem();

				orderItem.setOrderItemId(rs.getInt("order_item_id"));
				orderItem.setOrderId(rs.getInt("order_id"));
				orderItem.setVariantId(rs.getInt("variant_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setPrice(rs.getBigDecimal("price"));

				return orderItem;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) {

		List<OrderItem> orderItems = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_ORDER_ITEMS_BY_ORDER_ID)) {

			pstmt.setInt(1, orderId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				OrderItem orderItem = new OrderItem();

				orderItem.setOrderItemId(rs.getInt("order_item_id"));
				orderItem.setOrderId(rs.getInt("order_id"));
				orderItem.setVariantId(rs.getInt("variant_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setPrice(rs.getBigDecimal("price"));

				orderItems.add(orderItem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderItems;
	}

	@Override
	public boolean deleteOrderItem(int orderItemId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(DELETE_ORDER_ITEM)) {

			pstmt.setInt(1, orderItemId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteOrderItemsByOrderId(int orderId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(DELETE_ORDER_ITEMS_BY_ORDER_ID)) {

			pstmt.setInt(1, orderId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}