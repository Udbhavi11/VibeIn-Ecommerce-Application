package com.vibein.controller;

import java.io.IOException;

import com.vibein.dao.CategoryDAO;
import com.vibein.dao.ProductDAO;
import com.vibein.daoimpl.CategoryDAOImpl;
import com.vibein.daoimpl.ProductDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final CategoryDAO categoryDAO = new CategoryDAOImpl();
	private final ProductDAO productDAO = new ProductDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute(
				"categories",
				categoryDAO.getAllCategories());

		request.setAttribute(
				"products",
				productDAO.getAllProducts());

		request.getRequestDispatcher(
				"/WEB-INF/views/home.jsp")
				.forward(request, response);
	}
}