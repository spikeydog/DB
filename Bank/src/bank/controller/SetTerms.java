package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.bean.Account;
import bank.bean.Terms;
import bank.util.AccountAgent;

import	java.sql.Date;

/**
 * Servlet implementation class SetTerms
 */
@WebServlet("/SetTerms")
public class SetTerms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetTerms() {
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
		int accountID = Integer.valueOf(request.getParameter("accountID"));
		AccountAgent agent = new AccountAgent();
		Terms terms = new Terms();
		String minStr = request.getParameter("min");
		String maxStr = request.getParameter("max");
		String rateStr = request.getParameter("rate");
		String periodStr = request.getParameter("period");
		String feesStr = request.getParameter("fees");
		double min = null == minStr? 0 : Double.valueOf(minStr);
		double max = null == maxStr? 0 : Double.valueOf(maxStr);
		float rate = null == minStr? 0 : Float.valueOf(rateStr);
		Date period = Date.valueOf(null == periodStr? "2014-09-09" : periodStr);
		float fees = null == feesStr? 0 : Float.valueOf(feesStr);
		Account account = agent.getAccount(accountID);
		
		terms.setAccountNumber(accountID);
		terms.setMinBalance(min);
		terms.setMaxBalance(max);
		terms.setInterestRate(rate);
		terms.setPeriod(period);
		terms.setFees(fees);
		agent.setTerms(terms);
		terms = agent.getTerms(account);
		if (null==terms) {System.out.println("terms null :(");}
		request.getSession().setAttribute("terms", agent.getTerms(account));
		
		String URL = "SetTerms.jsp?accountID=" + accountID;
		request.getRequestDispatcher(URL).forward(request, response);
	}

}
