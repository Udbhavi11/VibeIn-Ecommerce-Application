package com.vibein.controller;

import java.io.IOException;

import com.vibein.dao.CartItemDAO;
import com.vibein.daoimpl.CartItemDAOImpl;
import com.vibein.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-cart")
public class UpdateCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final CartItemDAO cartItemDAO =
			new CartItemDAOImpl();

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		int cartItemId =
				Integer.parseInt(
						request.getParameter("cartItemId"));

		int quantity =
				Integer.parseInt(
						request.getParameter("quantity"));

		CartItem cartItem =
				cartItemDAO.getCartItemById(cartItemId);

		if(cartItem != null) {

			cartItem.setQuantity(quantity);

			cartItemDAO.updateCartItem(cartItem);
		}

		response.sendRedirect(
				request.getContextPath() + "/cart");
	}
}