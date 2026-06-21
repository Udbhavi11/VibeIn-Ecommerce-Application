package com.vibein.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vibein.dao.ProductDAO;
import com.vibein.daoimpl.ProductDAOImpl;
import com.vibein.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ProductDAO productDAO =
			new ProductDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		List<Product> products =
				productDAO.getAllProducts();

		String query =
				request.getParameter("query");

		String categoryIdStr =
				request.getParameter("categoryId");

		// Search Filter

		if(query != null &&
				!query.trim().isEmpty()) {

			List<Product> filteredProducts =
					new ArrayList<>();

			query =
					query.toLowerCase();

			for(Product product : products) {

				if(product.getProductName()
						.toLowerCase()
						.contains(query)

						||

						product.getBrand()
						.toLowerCase()
						.contains(query)) {

					filteredProducts.add(
							product);
				}
			}

			products =
					filteredProducts;
		}

		// Category Filter

		if(categoryIdStr != null &&
				!categoryIdStr.isEmpty()) {

			int categoryId =
					Integer.parseInt(
							categoryIdStr);

			List<Product> filteredProducts =
					new ArrayList<>();

			for(Product product : products) {

				if(product.getCategoryId()
						== categoryId) {

					filteredProducts.add(
							product);
				}
			}

			products =
					filteredProducts;
		}

		request.setAttribute(
				"products",
				products);

		request.getRequestDispatcher(
				"/WEB-INF/views/products.jsp")
				.forward(request,
						response);
	}
}