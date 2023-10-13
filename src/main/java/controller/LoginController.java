package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.User;

@WebServlet("/login")
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String rememberUserId = req.getParameter("rememberUserId");
		UserDAO userDao = new UserDAO();
		User user = userDao.selectUser(userId);
		
		if (user != null && user.getUserId().equals(userId) && user.getPassword().equals(password)) {
			if (rememberUserId != null) {
				Cookie cookie = new Cookie("userId", userId);
				resp.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("userId", "");
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}
			HttpSession session = req.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("name", user.getName());
			resp.sendRedirect("./main");
		} else {
			req.getRequestDispatcher("login.jsp?logError=1").forward(req, resp);
		}
	}
	
}
