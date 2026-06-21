package com.vibein.controller;

import java.io.IOException;

import com.vibein.dao.ProductDAO;
import com.vibein.dao.ProductVariantDAO;
import com.vibein.daoimpl.ProductDAOImpl;
import com.vibein.daoimpl.ProductVariantDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product-details")
public class ProductDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ProductDAO productDAO = new ProductDAOImpl();
	private final ProductVariantDAO variantDAO = new ProductVariantDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		int productId =
				Integer.parseInt(request.getParameter("id"));

		request.setAttribute(
				"product",
				productDAO.getProductById(productId));

		request.setAttribute(
				"variants",
				variantDAO.getVariantsByProductId(productId));

		request.getRequestDispatcher(
				"/WEB-INF/views/product-details.jsp")
				.forward(request, response);
	}
	
	@Override
	public void init() {
		System.out.println("ProductDetailsServlet Loaded");
	}
}