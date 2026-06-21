package com.vibein.util;

import java.util.List;

import com.vibein.dao.ProductDAO;
import com.vibein.daoimpl.ProductDAOImpl;
import com.vibein.model.Product;

public class DAOTest {

	public static void main(String[] args) {

		ProductDAO productDAO = new ProductDAOImpl();

		List<Product> products = productDAO.getAllProducts();

		if(products.isEmpty()) {
			System.out.println("No Products Found");
		}
		else {
			System.out.println("Products Retrieved Successfully\n");

			for(Product product : products) {
				System.out.println(product);
			}
		}
	}
}