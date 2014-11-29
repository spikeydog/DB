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
import bank.bean.Terms;
import bank.bean.Transaction;
import bank.bean.User;
import bank.util.AccountAgent;
import bank.util.Role;

/**
 * Servlet implementation class AccountDetails
 */
@WebServlet(name="AccountDetails", urlPatterns="/AccountDetails")
public class AccountDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDetails() {
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
		AccountAgent agent = new AccountAgent();
		User user = (User) session.getAttribute("user");
		int index = Integer.valueOf(request.getParameter("index"));
		Role role = user.getRole();
		Account account = null;
		List<Account> accounts = (List<Account>) session.getAttribute("accounts");
		account = accounts.get(index);
		int accountNumber = account.getAccountNumber();
		List<Transaction> trans = agent.getTransactions(accountNumber, role);
		Terms terms = agent.getTerms(account);
		session.setAttribute("account", account);
		session.setAttribute("trans", trans);
		session.setAttribute("terms", terms);
		request.getRequestDispatcher("AccountDetails.jsp").forward(request, response);
	}

}
