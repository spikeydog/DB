package bank.util;

import bank.util.AbstractDatabaseClass;
import	java.sql.PreparedStatement;
import	java.sql.ResultSet;
import	java.sql.SQLException;

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
	 * This method authenticates a user with the given username, password, and
	 * employeeID (optionsal). 
	 *
	 * @param	username	The username of the user to authenticate under
	 * @param	password	The password to authenticate with
	 * @return	<code>true</code> if the user authenticated successfully
	 */
	public boolean AuthenticateCustomer(
			String username, String password) {
		if (null == username || "".equals(username)) {
			throw new RuntimeException("username is empty");
		}
		if (null == password || "".equals(password)) {
			throw new RuntimeException("password is empty");
		}
		// Flag indicating authentication was successful 
		boolean isAuthenticated = false;
		// The result set from the query
		ResultSet results = null;
		/*this.query = "SELECT username FROM user WHERE " +
				"user.username='" + username + "' AND user_password='" + password + "'";*/
		this.query = "SELECT username, user_password FROM user WHERE " + 
				"user.username=? AND user.user_password=?";
		super.connect();
		//results = super.executeQuery(query);
		// Attempt to get data from the results set
		try {
			 
			statement = getPreparedStatement(query);
			if (null == statement) {
				System.out.println("staemtne null");
			}
			statement.setString(1, username);
			statement.setString(2, password);
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
	 * employeeID (optionsal). 
	 *
	 * @param	username	The username of the user to authenticate under
	 * @param	password	The password to authenticate with
	 * @param	employeeID	The employeeID to authenticate under
	 * @return	<code>true</code> if the user authenticated successfully
	 */
	public boolean AuthenticateEmployee(
			String username, String password, String employeeID) {
		// Flag indicating authentication was successful 
		boolean isAuthenticated = false;
		// The result set from the query
		ResultSet results = null;

		this.query = 
				"SELECT user.username"
				+ "FROM user INNER JOIN banker USING(user_id)"
				+ "WHERE user.username=? "
				+ "AND user.user_password=? "
				+ "AND banker.employee_id=?";
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
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setInt(3, Integer.valueOf(employeeID));
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
}
