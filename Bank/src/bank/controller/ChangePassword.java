package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.bean.User;
import bank.util.Role;
import bank.util.UserAgent;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String password = (String) request.getParameter("password1");
		UserAgent agent = new UserAgent();
		String URL = null;
		
		if (null != user && null != password) {
			agent.changePassword(user);			
		}
		if (Role.BANKER == user.getRole()) {
			URL = "BankerHome";
			
		} else if (Role.CUSTOMER == user.getRole()) {
			URL = "UpdateProfile";
		}
		
		session.setAttribute("message", "Password changed successfully");
		request.getRequestDispatcher(URL).forward(request, response);
	}

}
