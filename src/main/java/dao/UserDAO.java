package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.User;

public class UserDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement psmt;
	private Statement stmt;
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BOARD_TEMPLATE";
			String dbID = "root";
			String dbPassword = "0000";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB CONNECTION FAILED");
		}
	}
	
	public int insertUser(User user) {
		String sql = "INSERT INTO USER_TB(USER_ID, PASSWORD, NAME) VALUES(?, ?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, user.getUserId());
			psmt.setString(2, user.getPassword());
			psmt.setString(3, user.getName());
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public User selectUser(String userId) {
		User user = null;
		String sql = "SELECT * FROM USER_TB WHERE USER_ID = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("USER_ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setCreatedAt(rs.getTimestamp("CREATED_AT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
