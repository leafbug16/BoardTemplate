package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.BoardDAO;
import dao.CommentDAO;
import dto.Comment;

@WebServlet("/comment")
public class CommentController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mode = req.getParameter("mode");
		if ("delete".equals(mode)) {
			this.doDelete(req, resp);
		} else if ("patch".equals(mode)) {
			this.doPatch(req, resp);
		} else super.service(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tmp = req.getParameter("boardId");
		int boardId = 0;
		if (tmp != null) boardId = Integer.parseInt(tmp);
		CommentDAO dao = new CommentDAO();
		ArrayList<Comment> comments = dao.selectComments(boardId);
		JSONArray jArray = new JSONArray();
		for (int i=0; i<comments.size(); i++) {
			JSONObject jObject = new JSONObject();
			jObject.put("commentId", comments.get(i).getCommentId()+"");
			jObject.put("boardId", comments.get(i).getBoardId()+"");
			jObject.put("comment", comments.get(i).getComment());
			jObject.put("commenter", comments.get(i).getCommenter());
			jObject.put("createdAt", comments.get(i).getCreatedAt()+"");
			jArray.add(jObject);
		}
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(jArray.toJSONString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tmp = req.getParameter("boardId");
		int boardId = 0;
		if (tmp != null) boardId = Integer.parseInt(tmp);
		String comment = req.getParameter("comment");
		CommentDAO dao = new CommentDAO();
		BoardDAO boardDao = new BoardDAO();
		HttpSession session = req.getSession();
		String sessionId = session.getAttribute("userId").toString();
		Comment commentDto = new Comment(boardId, comment, sessionId);
		int result = dao.insert(commentDto);
		if (result == 1) {
			boardDao.updateCommentCnt(boardId, 1);
		}
	}
	
	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int commentId = 0;
		String tmp = req.getParameter("commentId");
		if (tmp != null) commentId = Integer.parseInt(tmp);
		String comment = req.getParameter("comment");
		
		CommentDAO commentDao = new CommentDAO();
		HttpSession session = req.getSession();
		String sessionId = session.getAttribute("userId").toString();
		
		Comment commentDto = new Comment(comment, sessionId, commentId);
		commentDao.update(commentDto);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int commentId = 0;
		String tmp = req.getParameter("commentId");
		if (tmp != null) commentId = Integer.parseInt(tmp);

		int boardId = 0;
		String temp1 = req.getParameter("boardId");
		if (temp1 != null) boardId = Integer.parseInt(temp1);
		
		CommentDAO commentDao = new CommentDAO();
		BoardDAO boardDao = new BoardDAO();
		int res = commentDao.delete(commentId);
		if (res == 1) {
			boardDao.updateCommentCnt(boardId, -1);
		}
	}
	
}




















