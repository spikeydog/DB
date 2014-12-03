package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.bean.User;
import bank.util.AccountAgent;

/**
 * This controller populates a customer's home page with summary data
 * Servlet implementation class CustomerAccountSummary
 * 
 * @author Spikeydog
 */
@WebServlet(name="CustomerHome", urlPatterns="/CustomerHome")
public class CustomerHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerHome() {
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
		User user = (User) session.getAttribute("user");
		
		AccountAgent agent = new AccountAgent();
		String URL = null;
		
		/* Guarantee the user exists */
		if (null != user) {
			session.setAttribute("accounts", agent.getAccounts(user.getUserID()));
			URL = "CustomerHome.jsp";
		} else {
			URL = "Logout";
		}
		
		request.getRequestDispatcher(URL).forward(request, response);
	}

}
