package com.vibein.dao;

import java.util.List;

import com.vibein.model.OrderItem;

public interface OrderItemDAO {

	boolean addOrderItem(OrderItem orderItem);

	boolean addOrderItems(List<OrderItem> orderItems);

	OrderItem getOrderItemById(int orderItemId);

	List<OrderItem> getOrderItemsByOrderId(int orderId);

	boolean deleteOrderItem(int orderItemId);

	boolean deleteOrderItemsByOrderId(int orderId);
}