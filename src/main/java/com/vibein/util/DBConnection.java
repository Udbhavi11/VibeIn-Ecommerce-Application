package com.vibein.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String URL = "jdbc:mysql://acela.proxy.rlwy.net:31767/railway";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "SrBrVXVocihttKdrXZimOKzYtEFExDQO";

	public static Connection getConnection() {

		Connection con = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

		return con;
	}
}