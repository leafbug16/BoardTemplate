package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.User;
import util.JSFunction;

@WebServlet("/register")
public class RegisterController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		UserDAO userDao = new UserDAO();
		User user = new User(id, password, name);
		int result = userDao.insertUser(user);
		if (result == -1) {
			JSFunction.alertBack("회원가입 실패", resp);
		} else {
			resp.sendRedirect("index.jsp");
		}
	}
	
}
