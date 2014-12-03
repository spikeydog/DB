package bank.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.bean.Account;
import bank.bean.Terms;
import bank.bean.User;
import bank.util.AccountAgent;
import bank.util.AccountType;

/**
 * This controller handles requests to create new accounts.
 * 
 * Servlet implementation class RequestNewAccount
 * 
 * @author Spikeydog
 */
@WebServlet(name="RequestNewAccount", urlPatterns="/RequestNewAccount")
public class RequestNewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestNewAccount() {
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
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		User user = (User) session.getAttribute("user");
		AccountAgent agent = new AccountAgent();
		
		/* Make sure the user actually exists */
		if (null != user) {
			Account account = new Account();
			account.setUserID(user.getUserID());
			account.setType(AccountType.getType(request.getParameter("type")));
			account.setDescription(request.getParameter("description"));
			account.setFrozen(true);
			account.setDateCreated(new Timestamp(System.currentTimeMillis()));
			account.setBalance(0);			
			agent.requestAccount(account);
			agent.getAccounts(user.getUserID());
		}
		
		dispatcher = request.getRequestDispatcher("CustomerHome");
		dispatcher.forward(request, response);
	}
}
