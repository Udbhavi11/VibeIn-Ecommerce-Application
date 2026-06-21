package com.vibein.controller;

import java.io.IOException;
import java.util.List;

import com.vibein.dao.OrderDAO;
import com.vibein.daoimpl.OrderDAOImpl;
import com.vibein.model.Order;
import com.vibein.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/my-orders")
public class MyOrdersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final OrderDAO orderDAO =
			new OrderDAOImpl();

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

		List<Order> orders =
				orderDAO.getOrdersByUserId(
						user.getUserId());

		request.setAttribute(
				"orders",
				orders);

		request.getRequestDispatcher(
				"/WEB-INF/views/my-orders.jsp")
				.forward(request, response);
	}
}