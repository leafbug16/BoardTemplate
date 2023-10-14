package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.BoardDAO;
import dto.Board;

@WebServlet("/like")
public class LikeController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String boardId = req.getParameter("boardId");
		String mode = req.getParameter("mode");
		BoardDAO boardDao = new BoardDAO();
		HttpSession session = req.getSession();
	    String sessionId = session.getAttribute("userId")+"";
	    System.out.println("lilkeController에서 boardId값 : " +boardId+", mode값 : " +mode);
	    
	    if (mode != null && !"".equals(mode)) {
	    	if (mode.equals("addLike")) {
	    		boardDao.insertLike(sessionId, Integer.parseInt(boardId));
	    		boardDao.updateLikeCnt(1, Integer.parseInt(boardId));
	    	} else if (mode.equals("deleteLike")) {
	    		boardDao.deleteLike(sessionId, Integer.parseInt(boardId));
	    		boardDao.updateLikeCnt(-1, Integer.parseInt(boardId));
	    	}	    	
	    }
	    
	    int res = boardDao.selectLikeCnt(sessionId, Integer.parseInt(boardId));
	    Board boardDto = boardDao.selectBoard(Integer.parseInt(boardId));
	    
	    JSONArray jArray = new JSONArray();
	    JSONObject jObject = new JSONObject();
	    jObject.put("res", res+"");
	    jObject.put("likeCnt", boardDto.getLikeCnt()+"");
	    jArray.add(jObject);
	    resp.setContentType("application/json; charset=utf-8");
	    PrintWriter out = resp.getWriter();
	    out.println(jArray.toJSONString());
	}
}
