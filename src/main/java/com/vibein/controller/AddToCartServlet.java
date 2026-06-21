package com.vibein.controller;

import java.io.IOException;

import com.vibein.dao.CartDAO;
import com.vibein.dao.CartItemDAO;
import com.vibein.daoimpl.CartDAOImpl;
import com.vibein.daoimpl.CartItemDAOImpl;
import com.vibein.model.Cart;
import com.vibein.model.CartItem;
import com.vibein.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final CartDAO cartDAO = new CartDAOImpl();
	private final CartItemDAO cartItemDAO = new CartItemDAOImpl();

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

		int variantId =
				Integer.parseInt(
						request.getParameter("variantId"));

		int quantity =
				Integer.parseInt(
						request.getParameter("quantity"));

		Cart cart =
				cartDAO.getCartByUserId(
						user.getUserId());

		if(cart == null) {

			cartDAO.createCart(
					user.getUserId());

			cart =
					cartDAO.getCartByUserId(
							user.getUserId());
		}

		CartItem item = new CartItem();

		item.setCartId(cart.getCartId());
		item.setVariantId(variantId);
		item.setQuantity(quantity);

		cartItemDAO.addCartItem(item);

		response.sendRedirect(
				request.getContextPath() + "/cart");
	}
}