package bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.bean.Account;
import bank.bean.Customer;
import bank.bean.Transaction;
import bank.bean.User;
import bank.util.AccountAgent;
import bank.util.UserAgent;

/**
 * This controller processes requests to provide customer, account, and 
 * transaction data.
 * 
 * Servlet implementation class CustomerAccountDetails
 * 
 * @author Spikeydog
 */
@WebServlet("/CustomerAccountDetails")
public class CustomerAccountDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAccountDetails() {
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
		HttpSession session = request.getSession();
		AccountAgent agentA = new AccountAgent();
		UserAgent agentC = new UserAgent();
		User user = (User) session.getAttribute("user");
		
		int customerID = Integer.valueOf(request.getParameter("customerID")); 
		int accountID  = Integer.valueOf(request.getParameter("accountID"));
		
		/* Set session data the JSP uses */
		agentC.getCustomer(customerID, session);
		session.setAttribute("accountID", accountID);
		session.setAttribute("accounts", agentA.getAccounts(customerID));
		session.setAttribute("trans", agentA.getTransactions(accountID, user.getRole()));
		agentC.getCustomer(customerID, session);
		
		request.getRequestDispatcher("CustomerAccountDetails.jsp").forward(request, response);
	}

}
