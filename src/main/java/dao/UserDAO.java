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
			String dbURL = "jdbc:mysql://localhost:3306/board_template";
			String dbID = "root";
			String dbPassword = "0000";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB연결 실패");
		}
	}
	
	public int insertUser(User user) {
		String sql = "INSERT INTO user(id, password, name) VALUES(?, ?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, user.getId());
			psmt.setString(2, user.getPassword());
			psmt.setString(3, user.getName());
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public User selectUser(String id) {
		User user = null;
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setName(rs.getString(3));
				user.setRegDate(rs.getTimestamp(4));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
