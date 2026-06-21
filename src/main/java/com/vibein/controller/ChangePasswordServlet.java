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
import jakarta.servlet.http.HttpSession;

@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final UserDAO userDAO =
			new UserDAOImpl();

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

		request.getRequestDispatcher(
				"/WEB-INF/views/change-password.jsp")
				.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session =
				request.getSession(false);

		User user =
				(User) session.getAttribute(
						"loggedInUser");

		String currentPassword =
				request.getParameter(
						"currentPassword");

		String newPassword =
				request.getParameter(
						"newPassword");

		String confirmPassword =
				request.getParameter(
						"confirmPassword");

		if(!user.getPassword().equals(
				currentPassword)) {

			request.setAttribute(
					"error",
					"Current password is incorrect");

			request.getRequestDispatcher(
					"/WEB-INF/views/change-password.jsp")
					.forward(request, response);

			return;
		}

		if(!newPassword.equals(
				confirmPassword)) {

			request.setAttribute(
					"error",
					"Passwords do not match");

			request.getRequestDispatcher(
					"/WEB-INF/views/change-password.jsp")
					.forward(request, response);

			return;
		}

		user.setPassword(
				newPassword);

		userDAO.updateUser(
				user);

		session.setAttribute(
				"loggedInUser",
				user);

		response.sendRedirect(
				request.getContextPath() + "/profile");
	}
}