package com.vibein.dao;

import java.util.List;

import com.vibein.model.Product;

public interface ProductDAO {

	boolean addProduct(Product product);

	Product getProductById(int productId);

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(int categoryId);

	boolean updateProduct(Product product);

	boolean deleteProduct(int productId);
}