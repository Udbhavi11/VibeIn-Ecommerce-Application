package com.vibein.controller;

import java.io.IOException;

import com.vibein.dao.UserDAO;
import com.vibein.daoimpl.UserDAOImpl;
import com.vibein.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final UserDAO userDAO = new UserDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(
				"/WEB-INF/views/register.jsp")
				.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String address = request.getParameter("address");

		User user = new User();

		user.setFullName(fullName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		user.setAddress(address);

		boolean status = userDAO.addUser(user);

		if(status) {

			response.sendRedirect(
					request.getContextPath() + "/login");

		} else {

			request.setAttribute(
					"error",
					"Registration Failed");

			request.getRequestDispatcher(
					"/WEB-INF/views/register.jsp")
					.forward(request, response);
		}
	}
}