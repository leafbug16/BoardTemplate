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
			String dbURL = "jdbc:mysql://localhost:3306/BOARD_TEMPLATE";
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
		String sql = "SELECT * FROM BOARD_TB ";
		if (map.get("searchWord") != null) {
			sql += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		sql += " ORDER BY BOARD_ID DESC ";
		sql += " LIMIT " + map.get("offset") + "," + map.get("pageSize");
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) { // 결과를 순화하며...
				Board board = new Board();
				board.setBoardId(rs.getInt("BOARD_ID")); // 일련번호
				board.setTitle(rs.getString("TITLE")); // 제목
				board.setContent(rs.getString("CONTENT")); // 내용
				board.setAuthor(rs.getString("AUTHOR")); // 작성자 아이디
				board.setViewCnt(rs.getInt("VIEW_CNT")); // 조회수
				board.setCreatedAt(rs.getTimestamp("CREATED_AT")); // 작성일
				board.setCommentCnt(rs.getInt("COMMENT_CNT")); //댓글수
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
		String sql = "SELECT COUNT(*) FROM BOARD_TB";
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
	public Board selectBoard(int boardId) {
		String sql = "SELECT * FROM BOARD_TB WHERE BOARD_ID = ?";
		Board board = null;
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, boardId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				board.setBoardId(rs.getInt("BOARD_ID")); // 일련번호
				board.setTitle(rs.getString("TITLE")); // 제목
				board.setContent(rs.getString("CONTENT")); // 내용
				board.setAuthor(rs.getString("AUTHOR")); // 작성자 아이디
				board.setViewCnt(rs.getInt("VIEW_CNT")); // 조회수
				board.setCreatedAt(rs.getTimestamp("CREATED_AT")); // 작성일
				board.setCommentCnt(rs.getInt("COMMENT_CNT")); //댓글수
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectBoard 에러");
		}
		return board;
	}
	
	//조회수
	public int updateViewCount(int boardId) {
		String sql = "UPDATE BOARD_TB SET VIEW_CNT = VIEW_CNT + 1 WHERE BOARD_ID = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, boardId);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateViewCount 에러");
		}
		return -1;
	}
	
	//글쓰기
	public int insertBoard(String title, String content, String author) {
		String sql = "INSERT INTO BOARD_TB(TITLE, CONTENT, AUTHOR) VALUES(?, ?, ?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, author);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insertBoard 에러");
		}
		return -1;
	}
	
	//글수정
	public int updateBoard(Board board) {
		String sql = "UPDATE BOARD_TB SET TITLE = ?, CONTENT = ? WHERE BOARD_ID = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setInt(3, board.getBoardId());
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateBoard 에러");
		}
		return -1;
	}
	
	//글삭제
	public int deleteBoard(int boardId) {
		String sql = "DELETE FROM BOARD_TB WHERE BOARD_ID = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, boardId);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("deleteBoard 에러");
		}
		return -1;
	}
	
	// 댓글수 수정
	public int updateCommentCnt(int boardId, int commentCnt) {
		String sql = "UPDATE BOARD_TB SET COMMENT_CNT = COMMENT_CNT + ? WHERE BOARD_ID = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, commentCnt);
			psmt.setInt(2, boardId);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}

































