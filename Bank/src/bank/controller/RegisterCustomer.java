package bank.controller;

import java.io.IOException;

import bank.bean.Customer;
import bank.bean.User;
import bank.util.UserAgent;
import bank.util.Code;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This controller handles registration requests for customers.
 * 
 * Servlet implementation class RegisterCustomer
 * 
 * @author Spikeydog
 */
@WebServlet(name="RegisterCustomer", urlPatterns="/RegisterCustomer")
public class RegisterCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Register Customer Servlet called");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String ssn = request.getParameter("ssn");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String phone = request.getParameter("phone");
		User user = new User(0, username, password, firstName, lastName, null);
		Customer c = new Customer(user, email, ssn, address1, address2, city, 
				state, zip, phone, 0, 0);
		
		UserAgent agent = new UserAgent();
		Code code = agent.registerCustomer(c);
		request.getSession().setAttribute("message", code.message);
		String URL = null;
		
		/* Only refer to Login page on success */
		switch (code) {
		case OK: URL = "Login.jsp"; break;
		default: URL = "RegisterCustomer.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(URL);
		dispatcher.forward(request, response);
	}

}
