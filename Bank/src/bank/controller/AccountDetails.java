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
import bank.bean.User;
import bank.util.AccountAgent;

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
		User user = (User) session.getAttribute("user");
		int index = Integer.valueOf(request.getParameter("index"));
		AccountAgent agent = new AccountAgent();
		Account account = null;
		List<Account> accounts = (List<Account>) session.getAttribute("accounts");
		account = accounts.get(index);
		session.setAttribute("account", account);
		if (null == account || null == user) {System.out.println("DEBUG");}
		session.setAttribute("trans",agent.getTransactions(account.getAccountNumber(), user.getRole()));
		request.getRequestDispatcher("AccountDetails.jsp").forward(request, response);
	}

}
