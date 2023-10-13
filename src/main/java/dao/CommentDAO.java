package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import dto.Comment;

public class CommentDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement psmt;
	private Statement stmt;

	public CommentDAO() {
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
	
	//댓글 모두 조회
	public ArrayList<Comment> selectComments(int boardId) {
		String sql = "SELECT * FROM COMMENT_TB WHERE BOARD_ID = ? ORDER BY CREATED_AT";
		ArrayList<Comment> comments = new ArrayList<>();
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, boardId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt("COMMENT_ID"));
				comment.setBoardId(rs.getInt("BOARD_ID"));
				comment.setComment(rs.getString("COMMENT"));
				comment.setCommenter(rs.getString("COMMENTER"));
				comment.setCreatedAt(rs.getTimestamp("CREATED_AT"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	//댓글 작성
	public int insert(Comment comment) {
		int res = 0;
		String sql = "INSERT INTO COMMENT_TB(BOARD_ID, COMMENT, COMMENTER) VALUES(?, ?, ?);";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, comment.getBoardId());
			psmt.setString(2, comment.getComment());
			psmt.setString(3, comment.getCommenter());
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//댓글 수정
	public int update(Comment comment) {
		int res = 0;
		String sql = "UPDATE COMMENT_TB SET COMMENT = ?, CREATED_AT = now() WHERE COMMENT_ID = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, comment.getComment());
			psmt.setInt(2, comment.getCommentId());
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//댓글 삭제
	public int delete(int commentId) {
		int res = 0;
		String sql = "DELETE FROM COMMENT_TB WHERE COMMENT_ID = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, commentId);
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}





























