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
 * This controller handles requests to update a customer profile and password.
 * Password changes are passed into the ChangePassword controller. 
 * 
 * Servlet implementation class UpdateProfile
 * 
 * @author Spikeydog
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

		Code code = null;
		RequestDispatcher dispatcher = null;
		String URL;
		
		/* Verify there is a user authenticated */
		if (null != user) {
			String password = request.getParameter("password1");
			
			/* Only update the password if the user actually set one */
			if (null != password) {user.setPassword(password);}
			String emailAddress = request.getParameter("email");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zipCode = request.getParameter("zip");
			String telephone = request.getParameter("phone");
			
			/* Populate the bean */
			customer = new Customer(user);
			customer.setAddress1(address1);
			customer.setAddress2(address2);
			customer.setEmailAddress(emailAddress);
			customer.setCity(city);
			customer.setState(state);
			customer.setZipCode(zipCode);
			customer.setTelephone(telephone);
			
			/* Actually update stuff and set response message */
			agent.changePassword(user);
			code = agent.updateCustomerProfile(customer);
			agent.getCustomerProfile(request.getSession());
			request.getSession().setAttribute("message", code.message);
			
			/* Return to profile view on success */
			switch (code) {
			case OK: URL = "ViewProfile.jsp"; break;
			default: URL = "UpdateProfile.jsp"; break;
			}
			
		/* Bounce the unauthenticated out */	
		} else {
			URL = "Logout";
		}
		
		dispatcher = request.getRequestDispatcher(URL);
		dispatcher.forward(request, response);
	}
}
