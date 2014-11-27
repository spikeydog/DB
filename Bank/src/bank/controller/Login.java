package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import	bank.bean.Banker;
import	bank.bean.Customer;
import	bank.util.UserAgent;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Login Servlet called");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserAgent agent = new UserAgent();
		boolean isAuthenticated = false;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String employeeID = request.getParameter("employeeID");
		
		employeeID = employeeID.equals("")? null : employeeID;
		String URL = null;
		
		if (null == employeeID) {
			Customer customer = new Customer(username, password, null, null);
			
			if (true == agent.AuthenticateCustomer(customer)) {
				response.getWriter().println("Customer authenticated!");
			} else {
				response.getWriter().println("Customer not authenticated!");
			}
		} else {
			Banker banker = new Banker(username, password, null, null, employeeID);
			if (agent.AuthenticateBanker(banker)) {
				response.getWriter().println("Employee authenticated!");
			} else {
				response.getWriter().println("Employee not authenticated!");
			}
		}
			
			
		
	}

}
