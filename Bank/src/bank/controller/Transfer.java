package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.util.AccountAgent;
import bank.util.Code;

/**
 * This controller handles requests to transfer funds. 
 * 
 * Servlet implementation class Transfer
 * 
 * @author Spikeydog
 */
@WebServlet(name="Transfer", urlPatterns="/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
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
		int source = Integer.valueOf(request.getParameter("source"));
		int target = Integer.valueOf(request.getParameter("target"));
		double amount = Double.valueOf(request.getParameter("amount"));
		AccountAgent agent = new AccountAgent();
		
		/* Guarantee the amount is positive */
		if (amount > 0) {
			Code code = agent.transfer(source, target, amount);
			request.getSession().setAttribute("message", code.message); 
			
		/* Send a custom message instead */
		} else {
			request.getSession().setAttribute("message", "You cannot transfer a negative balance");
		}
		
		request.getRequestDispatcher("Transfer.jsp").forward(request, response);
	}
}
