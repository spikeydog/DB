package bank.util;

import bank.util.AbstractDatabaseClass;

import	java.sql.PreparedStatement;
import	java.sql.ResultSet;
import	java.sql.SQLException;
import	java.sql.Types;

import javax.servlet.http.HttpSession;

import	bank.bean.Customer;
import	bank.bean.Banker;
import bank.bean.User;
import	static bank.util.Code.*;

public class UserAgent extends AbstractDatabaseClass {
	/** The actual query to execute. */
	private String query = null;
	/** The PreparedStatement used to query the database. */
	private PreparedStatement statement = null;
	/***/
	private ResultSet results = null;
	
	/** No-args constructor */
	public UserAgent() {
		super();
	}
	
	/** 
	 * This method authenticates a user with the given username and password. 
	 *
	 * @param	username	The username of the user to authenticate under
	 * @param	password	The password to authenticate with
	 * @return	<code>true</code> if the user authenticated successfully
	 */
	public Customer AuthenticateCustomer(Customer customer) {
		// The result set from the query
		results = null;
		
		this.query = "SELECT user_id, username, first_name FROM user WHERE " + 
				"user.username=? AND user.user_password=?";
				
		//this.query = "{call auth_customer}";
		super.connect();
		//results = super.executeQuery(query);
		// Attempt to get data from the results set
		try {
			//statement = getPreparedCall(query);
			statement = getPreparedStatement(query);
			if (null == statement) {
				System.out.println("call is null");
			}
			
			statement.setString(1, customer.getUsername());
			statement.setString(2, customer.getPassword());
			results = executeQuery(statement);
			customer = null;
			if (results.next()) {
				customer = new Customer();
				customer.setUserID(results.getInt("user_id"));
				customer.setUsername(results.getString("username"));
				customer.setFirstName(results.getString("first_name"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return customer;
	}
	
	public void getCustomerProfile(HttpSession session) {
		query = "SELECT * FROM customers WHERE user_id=?";
		User user = (User) session.getAttribute("user");
		Customer customer = new Customer();
		results = null;
		
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, user.getUserID());
			results = statement.executeQuery();
			if (results.next()) {
				customer.setEmailAddress(results.getString("email_address"));
				customer.setAddress1(results.getString("cus_address1"));
				customer.setAddress2(results.getString("cus_address2"));
				customer.setCity(results.getString("cus_city"));
				customer.setState(results.getString("cus_state"));
				customer.setZipCode(results.getString("cus_zip_code"));
				customer.setFirstName(user.getFirstName());
				customer.setLastName(user.getLastName());
				customer.setTelephone(results.getString("cus_telephone"));
			}
		} catch (SQLException ex) {
			
		}
		System.out.println(customer.toString());
		session.setAttribute("customer", customer);
	}
	
	public Code updateCustomerProfile(Customer customer) {
		Customer updatedCustomer = customer;
		query = "UPDATE customers SET(email_address=?,address1=?,address2=?,"
				+ "cus_city=?, cus_state=?, cus_zip_code=?, cus_telephone=?)";
		query = "CALL update_customer(?,?,?,?,?,?,?,?,?)";
		results = null;
		Code code = null;
		int i = 1;
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(i++, customer.getUserID());
			statement.setString(i++, customer.getPassword());
			statement.setString(i++, customer.getEmailAddress());
			statement.setString(i++, customer.getAddress1());
			statement.setString(i++, customer.getAddress2());
			statement.setString(i++, customer.getCity());
			statement.setString(i++, customer.getState());
			statement.setString(i++, customer.getZipCode());
			statement.setString(i++, customer.getTelephone());
			results = statement.executeQuery();
			if (results.next()) {
				code = Code.getCode(Integer.valueOf(results.getString("code")));
			}
		} catch (SQLException ex) {
			
		} finally {
			super.disconnect();
		}
		
		return code;
	}
	
	/** 
	 * This method authenticates a user with the given username, password, and
	 * employeeID. 
	 *
	 * @param	username	The username of the user to authenticate under
	 * @param	password	The password to authenticate with
	 * @param	employeeID	The employeeID to authenticate under
	 * @return	<code>true</code> if the user authenticated successfully
	 */
	public Banker AuthenticateBanker(Banker banker) {
		// The result set from the query
		ResultSet results = null;

		this.query = "SELECT user_id, username, first_name "
				+ "FROM user "
				+ "INNER JOIN banker USING(user_id) "
				+ "WHERE username=? "
				+ "AND user_password=? "
				+ "AND employee_id=?;";
		super.connect();

		// Attempt to get data from the results set
		try {
			statement = getPreparedStatement(query);
			statement.setString(1, banker.getUsername());
			statement.setString(2, banker.getPassword());
			statement.setInt(3, banker.getEmployeeID());
			results = executeQuery(statement);
			banker = null;
			
			if (results.next()) {
				banker = new Banker();
				banker.setUserID(results.getInt("user_id"));
				banker.setUsername(results.getString("username"));
				banker.setFirstName(results.getString("first_name"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return banker;
	}
	
	/**
	 * This method returns true if the username exists.
	 * 
	 * @param username	the username to check
	 * @return	true if the username exists
	 */
	public boolean userExists(String username) {
		if (null == username || "".equals(username)) {
			throw new RuntimeException("username is empty");
		}
		
		// Flag indicating authentication was successful 
		boolean userExists = false;
		// The result set from the query
		ResultSet results = null;

		this.query = 
				"SELECT user.username"
				+ "FROM user "
				+ "WHERE user.username=?";
		super.connect();

		// Attempt to get data from the results set
		try {
			statement = getPreparedStatement(query);
			statement.setString(1, username);
			results = executeQuery(statement);
			if (results.next()) {
				userExists = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return userExists;
	}
	/*
	private boolean registerUser() {
		
	}
	*/
	public Code registerCustomer(Customer c) {
		// Flag indicating authentication was successful 
		Code code = null;
		boolean isRegistered = false;
		// The result set from the query
		ResultSet results = null;
		int i = 1;
		this.query = "CALL register_customer(?,?,?,?,?,?,?,?,?,?,?,?)";
		super.connect();

		// Attempt to get data from the results set
		try {
			statement = getPreparedStatement(query);
			statement.setString(i++, c.getUsername());
			statement.setString(i++, c.getPassword());
			statement.setString(i++, c.getFirstName());
			statement.setString(i++, c.getLastName());
			statement.setString(i++, c.getEmailAddress());
			statement.setString(i++, c.getSsn());
			statement.setString(i++, c.getAddress1());
			statement.setString(i++, c.getAddress2());
			statement.setString(i++, c.getCity());
			statement.setString(i++, c.getState());
			statement.setString(i++, c.getZipCode());
			statement.setString(i++, c.getTelephone());
			
			results = statement.executeQuery();
			System.out.println(results.toString());
			if (results.next()) {
				code = Code.getCode(results.getInt("code"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return code;
	}
}
