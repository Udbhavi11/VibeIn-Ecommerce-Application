package com.vibein.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vibein.dao.CartDAO;
import com.vibein.dao.CartItemDAO;
import com.vibein.dao.ProductDAO;
import com.vibein.dao.ProductVariantDAO;
import com.vibein.daoimpl.CartDAOImpl;
import com.vibein.daoimpl.CartItemDAOImpl;
import com.vibein.daoimpl.ProductDAOImpl;
import com.vibein.daoimpl.ProductVariantDAOImpl;
import com.vibein.model.Cart;
import com.vibein.model.CartItem;
import com.vibein.model.CartViewItem;
import com.vibein.model.Product;
import com.vibein.model.ProductVariant;
import com.vibein.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final CartDAO cartDAO =
			new CartDAOImpl();

	private final CartItemDAO cartItemDAO =
			new CartItemDAOImpl();

	private final ProductVariantDAO variantDAO =
			new ProductVariantDAOImpl();

	private final ProductDAO productDAO =
			new ProductDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request,
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

			request.getRequestDispatcher(
					"/WEB-INF/views/cart.jsp")
					.forward(request, response);

			return;
		}

		List<CartItem> cartItems =
				cartItemDAO.getCartItemsByCartId(
						cart.getCartId());

		List<CartViewItem> viewItems =
				new ArrayList<>();

		BigDecimal grandTotal =
				BigDecimal.ZERO;

		for(CartItem cartItem : cartItems) {

			ProductVariant variant =
					variantDAO.getVariantById(
							cartItem.getVariantId());

			if(variant == null) {
				continue;
			}

			Product product =
					productDAO.getProductById(
							variant.getProductId());

			if(product == null) {
				continue;
			}

			CartViewItem item =
					new CartViewItem();

			item.setCartItemId(
					cartItem.getCartItemId());

			item.setProductName(
					product.getProductName());

			item.setBrand(
					product.getBrand());

			item.setImageUrl(
					product.getImageUrl());

			item.setSize(
					variant.getSize());

			item.setColor(
					variant.getColor());

			item.setPrice(
					variant.getPrice());

			item.setQuantity(
					cartItem.getQuantity());

			BigDecimal subtotal =
					variant.getPrice().multiply(
							BigDecimal.valueOf(
									cartItem.getQuantity()));

			item.setSubtotal(subtotal);

			grandTotal =
					grandTotal.add(subtotal);

			viewItems.add(item);
		}

		request.setAttribute(
				"cart",
				cart);

		request.setAttribute(
				"cartItems",
				viewItems);

		request.setAttribute(
				"grandTotal",
				grandTotal);

		request.getRequestDispatcher(
				"/WEB-INF/views/cart.jsp")
				.forward(request, response);
	}
}