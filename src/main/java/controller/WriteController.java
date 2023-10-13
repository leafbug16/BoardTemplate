package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import util.JSFunction;

@WebServlet("/write")
public class WriteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("userId") == null) {
			JSFunction.alertBack("로그인 해주세요", resp);
		}
		req.getRequestDispatcher("/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		HttpSession session = req.getSession();
		String sessionId = session.getAttribute("userId").toString();
		BoardDAO boardDao = new BoardDAO();
		int result = boardDao.insertBoard(title, content, sessionId);
		if (result == -1) {
			JSFunction.alertBack("글쓰기 실패", resp);
		} else {
			resp.sendRedirect("board");
		}
	}
	
}
