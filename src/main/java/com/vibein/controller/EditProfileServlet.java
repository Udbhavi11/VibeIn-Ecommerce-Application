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

@WebServlet("/edit-profile")
public class EditProfileServlet extends HttpServlet {

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

		User user =
				(User) session.getAttribute(
						"loggedInUser");

		request.setAttribute(
				"user",
				user);

		request.getRequestDispatcher(
				"/WEB-INF/views/edit-profile.jsp")
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

		user.setFullName(
				request.getParameter("fullName"));

		user.setEmail(
				request.getParameter("email"));

		user.setPhone(
				request.getParameter("phone"));

		user.setAddress(
				request.getParameter("address"));

		userDAO.updateUser(user);

		session.setAttribute(
				"loggedInUser",
				user);

		response.sendRedirect(
				request.getContextPath() + "/profile");
	}
}