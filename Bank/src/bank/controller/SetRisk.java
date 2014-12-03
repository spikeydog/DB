package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.bean.Customer;
import bank.util.UserAgent;

/**
 * This controller handles requests to update a customer's risk rating.
 * 
 * Servlet implementation class SetRisk
 * 
 * @author Spikeydog
 */
@WebServlet("/SetRisk")
public class SetRisk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetRisk() {
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
		String risk = (String) request.getParameter("risk");
		int customerID = Integer.valueOf(request.getParameter("customerID"));
		UserAgent agent = new UserAgent();
		
		/* Make sure a value was passed in; assumed OK */
		if (null != risk) {
			System.out.println("Setting risk to: " + risk);
			agent.setRisk(risk, customerID);
			agent.getCustomer(customerID, request.getSession());
		}
		
		String URL = "CustomerAccountDetails?customerID=" + customerID;
		request.getRequestDispatcher(URL).forward(request, response);
	}

}
