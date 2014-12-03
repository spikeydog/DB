package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.bean.User;
import bank.util.AccountAgent;
import bank.util.UserAgent;

/**
 * Servlet implementation class CustomerByAccount
 */
@WebServlet("/CustomerByAccount")
public class CustomerByAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerByAccount() {
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
		AccountAgent agentA = new AccountAgent();
		UserAgent agentU = new UserAgent();
		User customer = null;
		int accountID = Integer.valueOf(request.getParameter("accountID"));
		int customerID = agentA.getCustomerIdByAccountID(accountID);
		agentU.getCustomer(customerID, request.getSession());
		String URL = "CustomerAccountDetails?customerID=" + customerID 
				+ "&accountID=" + accountID;
		request.getRequestDispatcher(URL).forward(request, response);
	}

}
