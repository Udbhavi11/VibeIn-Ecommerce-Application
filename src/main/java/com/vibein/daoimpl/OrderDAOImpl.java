package com.vibein.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vibein.dao.OrderDAO;
import com.vibein.model.Order;
import com.vibein.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

	private static final String PLACE_ORDER =
			"INSERT INTO orders(user_id, total_amount, shipping_address, payment_method, order_status) VALUES(?,?,?,?,?)";

	private static final String GET_ORDER_BY_ID =
			"SELECT * FROM orders WHERE order_id=?";

	private static final String GET_ORDERS_BY_USER_ID =
			"SELECT * FROM orders WHERE user_id=?";

	private static final String GET_ALL_ORDERS =
			"SELECT * FROM orders";

	private static final String UPDATE_ORDER_STATUS =
			"UPDATE orders SET order_status=? WHERE order_id=?";

	@Override
	public int placeOrder(Order order) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt =
					 con.prepareStatement(
							 PLACE_ORDER,
							 PreparedStatement.RETURN_GENERATED_KEYS)) {

			pstmt.setInt(1, order.getUserId());
			pstmt.setBigDecimal(2, order.getTotalAmount());
			pstmt.setString(3, order.getShippingAddress());
			pstmt.setString(4, order.getPaymentMethod());
			pstmt.setString(5, order.getOrderStatus());

			int rows = pstmt.executeUpdate();

			if(rows > 0) {

				ResultSet rs =
						pstmt.getGeneratedKeys();

				if(rs.next()) {
					return rs.getInt(1);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public Order getOrderById(int orderId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_ORDER_BY_ID)) {

			pstmt.setInt(1, orderId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				Order order = new Order();

				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setTotalAmount(rs.getBigDecimal("total_amount"));
				order.setShippingAddress(rs.getString("shipping_address"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setOrderDate(rs.getTimestamp("order_date"));

				return order;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {

		List<Order> orders = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_ORDERS_BY_USER_ID)) {

			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Order order = new Order();

				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setTotalAmount(rs.getBigDecimal("total_amount"));
				order.setShippingAddress(rs.getString("shipping_address"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setOrderDate(rs.getTimestamp("order_date"));

				orders.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public List<Order> getAllOrders() {

		List<Order> orders = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_ALL_ORDERS);
			 ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {

				Order order = new Order();

				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setTotalAmount(rs.getBigDecimal("total_amount"));
				order.setShippingAddress(rs.getString("shipping_address"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setOrderDate(rs.getTimestamp("order_date"));

				orders.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public boolean updateOrderStatus(int orderId, String orderStatus) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(UPDATE_ORDER_STATUS)) {

			pstmt.setString(1, orderStatus);
			pstmt.setInt(2, orderId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}