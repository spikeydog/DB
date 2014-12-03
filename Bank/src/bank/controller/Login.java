package bank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import	bank.bean.Banker;
import	bank.bean.Customer;
import bank.bean.User;
import bank.util.AccountAgent;
import bank.util.Role;
import	bank.util.UserAgent;
/**
 * This controller processes login requests. 
 * Servlet implementation class Login
 * 
 * @author Spikeydog
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
       
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String employeeID = request.getParameter("employeeID");
		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession();
		
		employeeID = employeeID.equals("")? null : employeeID;
		String URL = null;
		
		/* The user is attempting to authenticate as a customer */
		if (null == employeeID) {
			Customer customer = new Customer(
					new User(0, username, password, null, null, null));
			customer = agent.AuthenticateCustomer(customer);
			
			/* The customer authenticated */
			if (null != customer) {
				request.getSession().setAttribute("user", customer);
				AccountAgent accountant = new AccountAgent();
				session.setAttribute("accounts", 
						accountant.getAccounts(customer.getUserID()));
				URL = "CustomerHome.jsp";
				
			/* Failed authentication */
			} else {
				URL = "Login.jsp";
				session.setAttribute("customer", null);
				session.setAttribute("message", "Login unsuccessful");
			}
			
		/* User is authenticating as a banker */
		} else {
			User user = new User(0, username, password, null, null, Role.BANKER);
			Banker banker = new Banker(user, employeeID);
			banker = agent.AuthenticateBanker(banker);
			
			/* Authentication successful */
			if (null != banker) {
				URL = "BankerHome";
				request.getSession().setAttribute("user", banker);
			
			/* Authentication failed */
			} else {
				URL = "Login.jsp";
				session.setAttribute("message", "Login unsuccessful");
			}
		}
		
		dispatcher = request.getRequestDispatcher(URL);
		dispatcher.forward(request, response);
		
	}

}
