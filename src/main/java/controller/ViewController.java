package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.Board;
import util.JSFunction;

@WebServlet("/view")
public class ViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tmp = req.getParameter("boardId");
		if (tmp == null) {
			JSFunction.alertLocation("잘못된 접근입니다", "main", resp);
			return; // or throw an exception
		}

		int boardId = Integer.parseInt(tmp);
		Board board = new Board();
		BoardDAO boardDao = new BoardDAO();
		board = boardDao.selectBoard(boardId);
		boardDao.updateViewCount(boardId);
		req.setAttribute("board", board);
		
		req.getRequestDispatcher("view.jsp").forward(req, resp);
	}
	
}
