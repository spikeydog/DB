package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.bean.Banker;
import bank.bean.User;
import bank.util.Code;
import bank.util.Role;
import bank.util.UserAgent;

/**
 * This controller handles registration for employee accounts. 
 * 
 * Servlet implementation class RegisterEmployee
 * 
 * @author Spikeydog
 */
@WebServlet(name="RegisterEmployee", urlPatterns="/RegisterEmployee")
public class RegisterEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserAgent agent = new UserAgent();
		User user = null;
		Banker banker = null;
		Code code = null;
		String URL = null;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String employeeID = request.getParameter("employeeID");
		user = new User(0, username, password, firstName, lastName, Role.BANKER);
		banker = new Banker(user, employeeID);
		code = agent.registerEmployee(banker);
		
		/* Registration successful */
		if (Code.OK == code) {
			URL = "Login.jsp";
			request.getSession().setAttribute("user", banker);
			
		/* Registration failed */
		} else {
			URL = "RegisterEmployee.jsp";
		}
		
		request.getSession().setAttribute("message", code.message);
		request.getRequestDispatcher(URL).forward(request, response);
	}

}
