package bank.util;

import bank.util.AbstractDatabaseClass;
import	java.sql.PreparedStatement;
import	java.sql.ResultSet;
import	java.sql.SQLException;
import	java.sql.Types;

import	bank.bean.Customer;
import	bank.bean.Banker;
import	static bank.util.Code.*;

public class UserAgent extends AbstractDatabaseClass {
	/** The actual query to execute. */
	private String query = null;
	/** The PreparedStatement used to query the database. */
	private PreparedStatement statement = null;
	
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
	public boolean AuthenticateCustomer(Customer customer) {
		// Flag indicating authentication was successful 
		boolean isAuthenticated = false;
		// The result set from the query
		ResultSet results = null;
		
		this.query = "SELECT username, user_password FROM user WHERE " + 
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
			
			if (results.next()) {
				isAuthenticated = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return isAuthenticated;
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
	public boolean AuthenticateBanker(Banker banker) {
		// Flag indicating authentication was successful 
		boolean isAuthenticated = false;
		// The result set from the query
		ResultSet results = null;

		this.query = "SELECT username "
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
			if (results.next()) {
				isAuthenticated = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return isAuthenticated;
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
			statement.setInt(i++, c.getZipCode());
			statement.setString(i++, c.getTelephone());
			
			results = executeQuery(statement);
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
