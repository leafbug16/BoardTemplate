package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dto.Board;

public class BoardDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement psmt;
	private Statement stmt;

	public BoardDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/board_template";
			String dbID = "root";
			String dbPassword = "0000";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB connection failed");
		}
	}
	//게시글들을 리스트 형태로 반환
	public List<Board> selectBoards(Map<String, String> map) {
		List<Board> boards = new ArrayList<>(); // 결과(게시물 목록)를 담을 변수
		String sql = "SELECT * FROM board ";
		if (map.get("searchWord") != null) {
			sql += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		sql += " ORDER BY postId DESC ";
		sql += " LIMIT " + map.get("offset") + "," + map.get("pageSize");
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) { // 결과를 순화하며...
				Board board = new Board();
				board.setPostId(rs.getInt("postId")); // 일련번호
				board.setAuthorId(rs.getString("authorId")); // 작성자 아이디
				board.setTitle(rs.getString("title")); // 제목
				board.setContent(rs.getString("content")); // 내용
				board.setViewCount(rs.getInt("viewCount")); // 조회수
				board.setPostDate(rs.getTimestamp("postDate")); // 작성일
				boards.add(board); // 결과 목록에 저장
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectBoards 에러");
		}
		return boards;
	}
	
	//총 게시글 수 반환
	public int selectBoardCount(Map<String, String> map) {
		int totalBoardCount = 0;
		String sql = "SELECT COUNT(*) FROM board";
		if (map.get("searchWord") != null) {
			sql += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				totalBoardCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectBoardCount 에러");
		}
		return totalBoardCount;
	}
	
	//게시글 하나 반환
	public Board selectBoard(int postId) {
		String sql = "SELECT * FROM board WHERE postId = ?";
		Board board = null;
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, postId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				board.setPostId(rs.getInt("postId"));
				board.setAuthorId(rs.getString("authorId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setViewCount(rs.getInt("viewCount"));
				board.setPostDate(rs.getTimestamp("postDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectBoard 에러");
		}
		return board;
	}
	
	//조회수
	public int updateViewCount(int postId) {
		String sql = "UPDATE board SET viewCount = viewCount + 1 WHERE postId = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, postId);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("조회수 에러");
		}
		return -1;
	}
	
	//글쓰기
	public int insertBoard(String authorId, String title, String content) {
		String sql = "INSERT INTO board(authorId, title, content) VALUES(?, ?, ?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, authorId);
			psmt.setString(2, title);
			psmt.setString(3, content);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("글쓰기 에러");
		}
		return -1;
	}
	
	//글수정
	public int updateBoard(Board board) {
		String sql = "UPDATE board SET title = ?, content = ? WHERE postId = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setInt(3, board.getPostId());
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("글수정 에러");
		}
		return -1;
	}
	
	//글삭제
	public int deleteBoard(int postId) {
		String sql = "DELETE FROM board WHERE postId = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, postId);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("글삭제 에러");
		}
		return -1;
	}

}

































