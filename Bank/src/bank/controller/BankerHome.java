package bank.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.bean.Account;
import bank.bean.Banker;
import bank.bean.OwnedAccount;
import bank.bean.Transaction;
import bank.bean.User;
import bank.util.AccountAgent;
import bank.util.Role;

/**
 * Servlet implementation class BankerHome
 */
@WebServlet(name="BankerHome", urlPatterns="/BankerHome")
public class BankerHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankerHome() {
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
		AccountAgent agent = new AccountAgent();
		HttpSession session = request.getSession();
		Banker banker = (Banker) session.getAttribute("user");
		String URL = null;
		
		if (null != banker && Role.BANKER == banker.getRole()) {
			List<OwnedAccount> accounts = agent.getFrozenAccounts(banker);
			List<Transaction> trans = agent.getFraudulentTransactions(banker);
			session.removeAttribute("accounts");
			session.removeAttribute("trans");
			session.setAttribute("accounts", accounts);
			session.setAttribute("trans", trans);
			URL = "BankerHome.jsp";
		} else {
			URL = "Logout";
		}
		
		request.getRequestDispatcher(URL).forward(request, response);
	}
}
