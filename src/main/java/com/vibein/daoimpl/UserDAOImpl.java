package com.vibein.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vibein.dao.UserDAO;
import com.vibein.model.User;
import com.vibein.util.DBConnection;

public class UserDAOImpl implements UserDAO {

	private static final String INSERT_USER =
			"INSERT INTO users(full_name,email,phone,password,address) VALUES(?,?,?,?,?)";

	private static final String GET_USER_BY_ID =
			"SELECT * FROM users WHERE user_id=?";

	private static final String GET_USER_BY_EMAIL =
			"SELECT * FROM users WHERE email=?";

	private static final String GET_ALL_USERS =
			"SELECT * FROM users";

	private static final String UPDATE_USER =
			"UPDATE users SET full_name=?, email=?, phone=?, password=?, address=? WHERE user_id=?";

	private static final String DELETE_USER =
			"DELETE FROM users WHERE user_id=?";

	private static final String VALIDATE_USER =
			"SELECT * FROM users WHERE email=? AND password=?";

	@Override
	public boolean addUser(User user) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(INSERT_USER)) {

			pstmt.setString(1, user.getFullName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getAddress());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public User getUserById(int userId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_USER_BY_ID)) {

			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				User user = new User();

				user.setUserId(rs.getInt("user_id"));
				user.setFullName(rs.getString("full_name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setCreatedAt(rs.getTimestamp("created_at"));

				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User getUserByEmail(String email) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_USER_BY_EMAIL)) {

			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				User user = new User();

				user.setUserId(rs.getInt("user_id"));
				user.setFullName(rs.getString("full_name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setCreatedAt(rs.getTimestamp("created_at"));

				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<User> getAllUsers() {

		List<User> users = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(GET_ALL_USERS);
			 ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {

				User user = new User();

				user.setUserId(rs.getInt("user_id"));
				user.setFullName(rs.getString("full_name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setCreatedAt(rs.getTimestamp("created_at"));

				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public boolean updateUser(User user) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(UPDATE_USER)) {

			pstmt.setString(1, user.getFullName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getAddress());
			pstmt.setInt(6, user.getUserId());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteUser(int userId) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(DELETE_USER)) {

			pstmt.setInt(1, userId);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public User validateUser(String email, String password) {

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(VALIDATE_USER)) {

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				User user = new User();

				user.setUserId(rs.getInt("user_id"));
				user.setFullName(rs.getString("full_name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setCreatedAt(rs.getTimestamp("created_at"));

				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}