package com.vibein.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vibein.dao.CategoryDAO;
import com.vibein.model.Category;
import com.vibein.util.DBConnection;

public class CategoryDAOImpl implements CategoryDAO {

	private static final String INSERT_CATEGORY =
			"INSERT INTO categories(category_name) VALUES(?)";

	private static final String GET_CATEGORY_BY_ID =
			"SELECT * FROM categories WHERE category_id=?";

	private static final String GET_ALL_CATEGORIES =
			"SELECT * FROM categories";

	private static final String UPDATE_CATEGORY =
			"UPDATE categories SET category_name=? WHERE category_id=?";

	private static final String DELETE_CATEGORY =
			"DELETE FROM categories WHERE category_id=?";

	@Override
	public boolean addCategory(Category category) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(INSERT_CATEGORY)) {

			pstmt.setString(1, category.getCategoryName());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Category getCategoryById(int categoryId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_CATEGORY_BY_ID)) {

			pstmt.setInt(1, categoryId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				Category category = new Category();

				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				category.setCreatedAt(rs.getTimestamp("created_at"));

				return category;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Category> getAllCategories() {

		List<Category> categories = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_ALL_CATEGORIES);
			 ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {

				Category category = new Category();

				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				category.setCreatedAt(rs.getTimestamp("created_at"));

				categories.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	@Override
	public boolean updateCategory(Category category) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(UPDATE_CATEGORY)) {

			pstmt.setString(1, category.getCategoryName());
			pstmt.setInt(2, category.getCategoryId());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteCategory(int categoryId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(DELETE_CATEGORY)) {

			pstmt.setInt(1, categoryId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}