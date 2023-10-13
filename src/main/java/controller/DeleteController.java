package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dto.Board;
import util.JSFunction;

@WebServlet("/delete")
public class DeleteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tmp = req.getParameter("boardId");
		if (tmp == null) {
			JSFunction.alertLocation("잘못된 접근입니다", "main", resp);
			return;
		}
		int boardId = Integer.parseInt(tmp);
		BoardDAO boardDao = new BoardDAO();
		Board board = new Board();
		board = boardDao.selectBoard(boardId);
		HttpSession session = req.getSession();
		String sessionId = session.getAttribute("userId").toString();
		int result = 0;
		if (sessionId == null || board == null) {
			JSFunction.alertBack("잘못된 접근입니다", resp);
		} else if (board.getAuthor().equals(sessionId)) {
			result = boardDao.deleteBoard(boardId);
			if (result == -1) {
				JSFunction.alertBack("삭제 실패", resp);
			} else {
				resp.sendRedirect("board");
			}
		}
	}
	
}
