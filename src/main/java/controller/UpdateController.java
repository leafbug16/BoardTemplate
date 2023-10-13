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

@WebServlet("/update")
public class UpdateController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tmp = req.getParameter("postId");
		if (tmp == null) {
			JSFunction.alertLocation("잘못된 접근입니다", "index.jsp", resp);
			return;
		}
		int postId = Integer.parseInt(tmp);
		BoardDAO boardDao = new BoardDAO();
		Board board = new Board();
		board = boardDao.selectBoard(postId);
		HttpSession session = req.getSession();
		String sessionId = session.getAttribute("id").toString();
		if (!sessionId.equals(board.getAuthorId())) {
			JSFunction.alertBack("작성자 본인만 수정할 수 있습니다", resp);
		}
		req.setAttribute("board", board);
		req.getRequestDispatcher("update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 int postId = Integer.parseInt(req.getParameter("postId"));
		 String title = req.getParameter("title");
		 String content = req.getParameter("content");
		 Board board = new Board(postId, title, content);
		 BoardDAO boardDao = new BoardDAO();
		 int result = boardDao.updateBoard(board);
		 if (result == -1) {
			 JSFunction.alertBack("수정 실패", resp);
		 } else {
			 resp.sendRedirect("view?postId="+board.getPostId());
		 }
	}
	
}
