package bank.util;

import	java.sql.Connection;
import 	java.sql.DriverManager;
import 	java.sql.SQLException;
import	java.sql.SQLTimeoutException;
import	java.sql.ResultSet;
import	java.sql.Statement;
import	java.sql.PreparedStatement;

import java.sql.CallableStatement;
//import 	com.mysql.jdbc.Driver;

/**
 * This abstract class is a parent for all model classes that will interact with
 * the database. It has methods for establishing and closing a connection to the
 * database.
 * 
 * @author	Spike E. Dog
 * @version	2013.10.15
 */
//2345678901234567890123456789012345678901234567890123456789012345678901234567890
public abstract class AbstractDatabaseClass {
	/** The name of the Java class to obtain a Class object for. */
	private final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	/** The status code indicating a failure to locate the oracle driver. */
	public final int FAILURE = -1;
	/** The connection to use for interactions with the database. */
	private Connection connection;
	/** The statement to use for queries to the database. */
	private Statement statement;
	
	/**
	 * Instantiates a new <code>AbstractDatabaseClass</code>.
	 */
	protected AbstractDatabaseClass() {
		// Nothing to initialize
	}
	
	/**
	 * This method attempts to establish a new connection to the database.
	 * 
	 * @return	a reference to the <code>Connection</code> created
	 */
	protected final void connect() {
		/* Verify the oracle database driver is installed. */
		try {
			//Class.forName(DRIVER_CLASS);
			Class.forName(DRIVER_CLASS).newInstance();
			
		/* The oracle database driver was not found. */
		} catch (ClassNotFoundException ex) {
			System.err.println(ex);
			System.exit(FAILURE);
		} catch (IllegalAccessException ex) {
			System.err.println("Can't do that");
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/banking", "bank_db", "dbdb");
		} catch (SQLException ex) {
			System.out.println("Unable to connect to the database.");
			ex.printStackTrace();
		}
	}
	
	/**
	 * This method executes accepts a raw SQL statement and executes the 
	 * underlying operation on the database.
	 * 
	 * @param	statement	the <code>Statement</code> to use for the query
	 * @param	query		the <code>String</code> that contains the SQL query
	 * @return	results		the <code>ResultSet</code> generated by the statement
	 */

	protected ResultSet executeQuery(String query) {
		
		ResultSet results = null;
		
		
		try {
			System.out.println(connection==null?"null connector":"connector OK");
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
		} catch (SQLTimeoutException ex) {
			System.err.println("Timeout exceeded for this database query.");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
		
		return results;
	}
	
	protected PreparedStatement getPreparedStatement(String query) {
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(query);
		} catch (SQLException ex) {
			System.out.println("Could not prepare a statement");
			ex.printStackTrace();
		}
		// DEBUG
		if (null==statement) {System.out.println("statement create failed :(");}
		return statement;
	}
	
	protected CallableStatement getPreparedCall(String query) {
		CallableStatement statement = null;
		
		try {
			statement = connection.prepareCall(query);
		} catch (SQLException ex) {
			System.out.println("Could not prepare a statement");
			ex.printStackTrace();
		}
		// DEBUG
		if (null==statement) {System.out.println("callable create failed :(");}
		return statement;
	}
	
	protected ResultSet executeQuery(PreparedStatement statement) {
		ResultSet results = null;
		try {
			results = statement.executeQuery();
		} catch (SQLTimeoutException ex) {
			System.err.println("Timeout exceeded for this database query.");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
		
		return results;
	}
	
	/**
	 * This method attempts to gracefully disconnect from the database.
	 * 
	 * @param	connection	the <code>Connection</code> to close
	 */
	protected final void disconnect() {
		if (null != connection) {
			/* Attempt to gracefully disconnect. */
			try {
				connection.close();
				
			/* Some error occurred while disconnection. Print stacktrace. */
			} catch (SQLException ex) {
				System.err.println("Error encountered during disconnect.");
				ex.printStackTrace();
			}
		}
	}
}
