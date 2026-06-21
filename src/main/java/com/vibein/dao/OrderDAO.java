package com.vibein.dao;

import java.util.List;

import com.vibein.model.Order;

public interface OrderDAO {

	int placeOrder(Order order);

	Order getOrderById(int orderId);

	List<Order> getOrdersByUserId(int userId);

	List<Order> getAllOrders();

	boolean updateOrderStatus(int orderId, String orderStatus);
}