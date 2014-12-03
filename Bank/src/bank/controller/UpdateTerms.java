package bank.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.bean.Account;
import bank.bean.Terms;
import bank.util.AccountAgent;

/**
 * This controller handles a user's entry to the UpdateTerms page. 
 * 
 * It makes sure that if there are no terms, an empty bean with an account
 * number is passed up instead.
 * 
 * Servlet implementation class UpdateTerms
 * 
 * @author Spikeydog
 */
@WebServlet("/UpdateTerms")
public class UpdateTerms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTerms() {
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
		int accountID = Integer.valueOf(request.getParameter("accountID"));
		AccountAgent agent = new AccountAgent();
		Account account = agent.getAccount(accountID);
		Terms terms = agent.getTerms(account);
			
		/* If there are no terms, we need to create a dummy to avoid crashes */
		if (null == terms) {
			terms = new Terms();
			terms.setAccountNumber(accountID);
		}
			
		request.getSession().setAttribute("terms", terms);
			
		String URL = "SetTerms.jsp?accountID=" + accountID;
		request.getRequestDispatcher(URL).forward(request, response);
	}

}
