package com.vibein.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vibein.dao.ProductDAO;
import com.vibein.model.Product;
import com.vibein.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

	private static final String INSERT_PRODUCT =
			"INSERT INTO products(category_id, product_name, description, brand, image_url) VALUES(?,?,?,?,?)";

	private static final String GET_PRODUCT_BY_ID =
			"SELECT * FROM products WHERE product_id=?";

	private static final String GET_ALL_PRODUCTS =
			"SELECT * FROM products";

	private static final String GET_PRODUCTS_BY_CATEGORY =
			"SELECT * FROM products WHERE category_id=?";

	private static final String UPDATE_PRODUCT =
			"UPDATE products SET category_id=?, product_name=?, description=?, brand=?, image_url=? WHERE product_id=?";

	private static final String DELETE_PRODUCT =
			"DELETE FROM products WHERE product_id=?";

	@Override
	public boolean addProduct(Product product) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(INSERT_PRODUCT)) {

			pstmt.setInt(1, product.getCategoryId());
			pstmt.setString(2, product.getProductName());
			pstmt.setString(3, product.getDescription());
			pstmt.setString(4, product.getBrand());
			pstmt.setString(5, product.getImageUrl());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Product getProductById(int productId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_PRODUCT_BY_ID)) {

			pstmt.setInt(1, productId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				Product product = new Product();

				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setDescription(rs.getString("description"));
				product.setBrand(rs.getString("brand"));
				product.setImageUrl(rs.getString("image_url"));
				product.setCreatedAt(rs.getTimestamp("created_at"));

				return product;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Product> getAllProducts() {

		List<Product> products = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_ALL_PRODUCTS);
			 ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {

				Product product = new Product();

				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setDescription(rs.getString("description"));
				product.setBrand(rs.getString("brand"));
				product.setImageUrl(rs.getString("image_url"));
				product.setCreatedAt(rs.getTimestamp("created_at"));

				products.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) {

		List<Product> products = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_PRODUCTS_BY_CATEGORY)) {

			pstmt.setInt(1, categoryId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Product product = new Product();

				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setDescription(rs.getString("description"));
				product.setBrand(rs.getString("brand"));
				product.setImageUrl(rs.getString("image_url"));
				product.setCreatedAt(rs.getTimestamp("created_at"));

				products.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public boolean updateProduct(Product product) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(UPDATE_PRODUCT)) {

			pstmt.setInt(1, product.getCategoryId());
			pstmt.setString(2, product.getProductName());
			pstmt.setString(3, product.getDescription());
			pstmt.setString(4, product.getBrand());
			pstmt.setString(5, product.getImageUrl());
			pstmt.setInt(6, product.getProductId());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteProduct(int productId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(DELETE_PRODUCT)) {

			pstmt.setInt(1, productId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}