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
import bank.util.AccountAgent;
import	bank.util.UserAgent;
/**
 * Servlet implementation class Login
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
		
		if (null == employeeID) {
			Customer customer = new Customer(0, username, password, null, null);
			customer = agent.AuthenticateCustomer(customer);
			if (null != customer) {
				request.getSession().setAttribute("user", customer);
				AccountAgent accountant = new AccountAgent();
				session.setAttribute("accounts", 
						accountant.getAccounts(customer.getUserID()));
				URL = "CustomerHome.jsp";
			} else {
				URL = "Login.jsp";
				session.setAttribute("customer", null);
				session.setAttribute("message", "Login unsuccessful");
			}
		} else {
			Banker banker = new Banker(0, username, password, null, null, employeeID);
			banker = agent.AuthenticateBanker(banker);
			if (null != banker) {
				request.getSession().setAttribute("user", banker);
				response.getWriter().println("Employee authenticated!");
			} else {
				response.getWriter().println("Employee not authenticated!");
			}
		}
		
		dispatcher = request.getRequestDispatcher(URL);
		dispatcher.forward(request, response);
		
	}

}
