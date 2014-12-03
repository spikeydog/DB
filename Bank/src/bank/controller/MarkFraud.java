package bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.bean.Transaction;
import bank.bean.User;
import bank.util.AccountAgent;

/**
 * This controller handles requests to mark a transaction as fraudulent.
 * 
 * Servlet implementation class MarkFraud
 * 
 * @author Spikeydog
 */
@WebServlet(name="MarkFraud", urlPatterns="/MarkFraud")
public class MarkFraud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarkFraud() {
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
		int index = Integer.valueOf(request.getParameter("index"));
		HttpSession session = request.getSession();
		AccountAgent agent = new AccountAgent();
		
		@SuppressWarnings("unchecked")
		List<Transaction> trans = (List<Transaction>) session.getAttribute("trans");
		User user = (User) session.getAttribute("user");
		Transaction tx = trans.get(index);
		
		/* Make sure the transaction and user both exist */
		if (null != tx && null != user) {
			agent.setFraudulent(tx);
			session.setAttribute("trans", 
					agent.getTransactions(tx.getAccountNumber(), user.getRole()));
			}
		
		request.getRequestDispatcher("AccountDetails.jsp").forward(request, response);
	}

}
