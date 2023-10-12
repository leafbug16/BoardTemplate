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
		String id = req.getParameter("id");
		String pass = req.getParameter("password");
		String rememberId = req.getParameter("rememberId");
		UserDAO userDao = new UserDAO();
		User user = userDao.selectUser(id);
		
		if (user != null && user.getId().equals(id) && user.getPassword().equals(pass)) {
			if (rememberId != null) {
				Cookie cookie = new Cookie("id", id);
				resp.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("id", "");
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", user.getName());
			resp.sendRedirect("./main");
		} else {
			req.getRequestDispatcher("login.jsp?logError=1").forward(req, resp);
		}
	}
	
}
