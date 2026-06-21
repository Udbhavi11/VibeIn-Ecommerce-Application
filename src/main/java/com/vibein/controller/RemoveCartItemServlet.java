package com.vibein.controller;

import java.io.IOException;

import com.vibein.dao.CartItemDAO;
import com.vibein.daoimpl.CartItemDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/remove-cart-item")
public class RemoveCartItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final CartItemDAO cartItemDAO =
			new CartItemDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		int cartItemId =
				Integer.parseInt(
						request.getParameter("cartItemId"));

		cartItemDAO.removeCartItem(cartItemId);

		response.sendRedirect(
				request.getContextPath() + "/cart");
	}
}