package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.Board;
import util.PageHandler;
import util.SearchCondition;

@WebServlet("/board")
public class BoardController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> param = new HashMap<>();
		SearchCondition searchCondition = null;
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		int page = 1;
		String pageTemp = req.getParameter("page");
		if (pageTemp != null && !"".equals(pageTemp)) {
			page = Integer.parseInt(pageTemp);
		}
		int pageSize = 5;
		if (searchWord != null && !"".equals(searchWord)) {
			param.put("searchField", searchField);
			param.put("searchWord", searchWord);
			searchCondition = new SearchCondition(searchField, searchWord, page, pageSize);
		} else {
			searchCondition = new SearchCondition(page, pageSize);
		}
		
		BoardDAO boardDao = new BoardDAO();
		int totalCount = boardDao.selectBoardCount(param);
		param.put("offset", searchCondition.getOffset(page)+"");
		param.put("pageSize", pageSize+"");
		List<Board> boards = boardDao.selectBoards(param);
		
		PageHandler pageHandler = new PageHandler(totalCount, searchCondition);
		req.setAttribute("pageHandler", pageHandler);
		req.setAttribute("boards", boards);
		req.getRequestDispatcher("/board.jsp").forward(req, resp);
	}
}
