package com.vibein.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.vibein.dao.CartDAO;
import com.vibein.dao.CartItemDAO;
import com.vibein.dao.OrderDAO;
import com.vibein.dao.OrderItemDAO;
import com.vibein.dao.ProductVariantDAO;
import com.vibein.daoimpl.CartDAOImpl;
import com.vibein.daoimpl.CartItemDAOImpl;
import com.vibein.daoimpl.OrderDAOImpl;
import com.vibein.daoimpl.OrderItemDAOImpl;
import com.vibein.daoimpl.ProductVariantDAOImpl;
import com.vibein.model.Cart;
import com.vibein.model.CartItem;
import com.vibein.model.Order;
import com.vibein.model.OrderItem;
import com.vibein.model.ProductVariant;
import com.vibein.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final OrderDAO orderDAO =
			new OrderDAOImpl();

	private final OrderItemDAO orderItemDAO =
			new OrderItemDAOImpl();

	private final CartDAO cartDAO =
			new CartDAOImpl();

	private final CartItemDAO cartItemDAO =
			new CartItemDAOImpl();

	private final ProductVariantDAO variantDAO =
			new ProductVariantDAOImpl();

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session =
				request.getSession(false);

		if(session == null ||
				session.getAttribute("loggedInUser") == null) {

			response.sendRedirect(
					request.getContextPath() + "/login");
			return;
		}

		User user =
				(User) session.getAttribute("loggedInUser");

		Cart cart =
				cartDAO.getCartByUserId(
						user.getUserId());

		if(cart == null) {

			response.sendRedirect(
					request.getContextPath() + "/cart");
			return;
		}

		List<CartItem> cartItems =
				cartItemDAO.getCartItemsByCartId(
						cart.getCartId());

		if(cartItems == null ||
				cartItems.isEmpty()) {

			response.sendRedirect(
					request.getContextPath() + "/cart");
			return;
		}

		String address =
				request.getParameter("address");

		BigDecimal totalAmount =
				BigDecimal.ZERO;

		for(CartItem item : cartItems) {

			ProductVariant variant =
					variantDAO.getVariantById(
							item.getVariantId());

			if(variant != null) {

				BigDecimal subtotal =
						variant.getPrice().multiply(
								BigDecimal.valueOf(
										item.getQuantity()));

				totalAmount =
						totalAmount.add(subtotal);
			}
		}

		Order order = new Order();

		order.setUserId(
				user.getUserId());

		order.setTotalAmount(
				totalAmount);

		order.setShippingAddress(
				address);

		order.setPaymentMethod(
				"Cash On Delivery");

		order.setOrderStatus(
				"PLACED");

		int orderId =
				orderDAO.placeOrder(order);

		if(orderId == -1) {

			response.sendRedirect(
					request.getContextPath() + "/checkout");
			return;
		}

		for(CartItem item : cartItems) {

			ProductVariant variant =
					variantDAO.getVariantById(
							item.getVariantId());

			OrderItem orderItem =
					new OrderItem();

			orderItem.setOrderId(
					orderId);

			orderItem.setVariantId(
					item.getVariantId());

			orderItem.setQuantity(
					item.getQuantity());

			orderItem.setPrice(
					variant.getPrice());

			orderItemDAO.addOrderItem(
					orderItem);
		}

		cartItemDAO.clearCart(
				cart.getCartId());

		request.setAttribute(
				"orderId",
				orderId);

		request.getRequestDispatcher(
				"/WEB-INF/views/order-success.jsp")
				.forward(request, response);
	}
}