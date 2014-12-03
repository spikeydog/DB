package bank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.bean.Customer;
import bank.bean.User;
import bank.util.Code;
import bank.util.UserAgent;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
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
		User user = (User) request.getSession().getAttribute("user");
		
		UserAgent agent = new UserAgent();
		Customer customer = null;
		String password = null;
		String emailAddress = null;
		String address1 = null;
		String address2 = null;
		String city = null;
		String state = null;
		String zipCode = null;
		String telephone = null;
		Code code = null;
		RequestDispatcher dispatcher = null;
		String URL;
		if (null != user) {
			password = request.getParameter("password1");
			if (null != password) {user.setPassword(password);}
			emailAddress = request.getParameter("email");
			address1 = request.getParameter("address1");
			address2 = request.getParameter("address2");
			city = request.getParameter("city");
			state = request.getParameter("state");
			zipCode = request.getParameter("zip");
			telephone = request.getParameter("phone");
			customer = new Customer(user);
			customer.setAddress1(address1);
			customer.setAddress2(address2);
			customer.setEmailAddress(emailAddress);
			customer.setCity(city);
			customer.setState(state);
			customer.setZipCode(zipCode);
			customer.setTelephone(telephone);
			agent.changePassword(user);
			code = agent.updateCustomerProfile(customer);
			agent.getCustomerProfile(request.getSession());
			request.getSession().setAttribute("message", code.message);
			switch (code) {
			case OK: URL = "ViewProfile.jsp"; break;
			default: URL = "UpdateProfile.jsp"; break;
			}
		} else {
			URL = "Logout";
		}
		dispatcher = request.getRequestDispatcher(URL);
		dispatcher.forward(request, response);
	}
}
