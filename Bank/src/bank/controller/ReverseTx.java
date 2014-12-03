package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.util.AccountAgent;

/**
 * Servlet implementation class ReverseTx
 */
@WebServlet("/ReverseTx")
public class ReverseTx extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReverseTx() {
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
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		int txid = Integer.valueOf(request.getParameter("txid"));
		int customerID = Integer.valueOf((String) request.getParameter("customerID"));
		int accountID = Integer.valueOf((String) request.getParameter("accountID"));
		AccountAgent agent = new AccountAgent();
		agent.reverseTx(txid);
		String URL = "CustomerAccountDetails?customerID=" + customerID
				+ "&accountID=" + accountID;
		request.getRequestDispatcher(URL).forward(request, response);
	}

}
