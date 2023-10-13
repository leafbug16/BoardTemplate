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

@WebServlet("/like")
public class LikeController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String boardId = req.getParameter("boardId");
    String mode = req.getParameter("mode");
    BoardDAO boardDao = new BoardDAO();
    HttpSession session = req.getSession();
    String sessionId = session.getAttribute("userId")+"";
    if (mode.equals("likeOn")) {
        int res = boardDao.insertLike(sessionId, Integer.parseInt(boardId));
        if(res==1) {
        	System.out.println("좋아요");
        	boardDao.updateLikeCnt(1, Integer.parseInt(boardId));
        }
    }
    else {
        int res = boardDao.deleteLike(sessionId, Integer.parseInt(boardId));
        if(res==1) {
        	System.out.println("좋아요 취소");
        	boardDao.updateLikeCnt(-1, Integer.parseInt(boardId));
        }
    }
   resp.sendRedirect("view?boardId="+boardId);
	}
}
