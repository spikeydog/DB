package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.bean.Account;
import bank.bean.Customer;
import bank.util.AccountAgent;

/**
 * Servlet implementation class FreezeAccount
 */
@WebServlet("/FreezeAccount")
public class FreezeAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreezeAccount() {
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
		int accountID = Integer.valueOf(request.getParameter("accountID"));
		Customer c = (Customer) session.getAttribute("customer");
		
		int customerID = c.getUserID();
		
		AccountAgent agent = new AccountAgent();
		agent.freezeAccount(accountID);
		String URL = "CustomerAccountDetails?customer=" + customerID 
				+ "&accountID=" + accountID;
		request.getRequestDispatcher(URL).forward(request, response);
	}

}
