package bank.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

import bank.bean.Account;
import bank.bean.Banker;
import bank.bean.Customer;
import bank.bean.OwnedAccount;
import bank.bean.Terms;
import bank.bean.Transaction;
import bank.bean.User;
import bank.util.AbstractDatabaseClass;

/**
 * This class performs account-related queries to the database.
 * 
 * @author Spikeydog
 */
public class AccountAgent extends AbstractDatabaseClass {
	String query = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	public AccountAgent() {
		super();
	}
	
	/**
	 * This method obtains the list of account for a given User
	 * 
	 * @param userID
	 * @return
	 */
	public List<Account> getAccounts(int userID) {
		List<Account> accounts = new LinkedList<Account>();
		Account account = null;
		query = "SELECT * FROM general_accounts WHERE user_id=?";
		
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, userID);
			results = statement.executeQuery();
			while (results.next()) {
				account = new Account();
				account.setAccountNumber(results.getInt("account_number"));
				account.setUserID(results.getInt("user_id"));
				account.setType(AccountType.getType(results.getString("account_type")));
				account.setDescription(results.getString("account_descrip"));
				account.setBalance(results.getDouble("balance"));
				account.setDateCreated(results.getTimestamp("date_created"));
				account.setFrozen(results.getBoolean("frozen"));
				accounts.add(account);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return accounts;
	}
	
	/**
	 * This method obtains the terms for a given account.
	 * 
	 * @param account
	 * @return
	 */
	public Terms getTerms(Account account) {
		Terms terms = null;
		int accountNumber = account.getAccountNumber();
		query = "SELECT * "
				+ "FROM terms "
				+ "WHERE account_number=? "
				+ "ORDER BY terms_id DESC "
				+ "LIMIT 1";
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, accountNumber);
			results = statement.executeQuery();
			
			if (results.next()) {
				terms = new Terms();
				terms.setTermsID(results.getInt("terms_id"));
				terms.setAccountNumber(results.getInt("account_number"));
				terms.setTermsID(results.getInt("terms_id"));
				terms.setFees(results.getFloat("fees"));
				terms.setMinBalance(results.getDouble("min_balance"));
				terms.setMaxBalance(results.getDouble("max_balance"));
				terms.setPeriod(results.getDate("period"));
			} else {
				System.out.println("No terms");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return terms;
	}
	
	/**
	 * This class inserts a new account into the database.
	 * 
	 * @param account
	 */
	public void requestAccount(Account account) {
		query = "INSERT INTO general_accounts "
				+ "(account_number, user_id, account_type, account_descrip, "
				+ "balance, date_created, frozen )"
				+ "VALUES(DEFAULT, ?,?,?,?,?,?)";
		int i = 1;
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(i++, account.getUserID());
			statement.setString(i++, account.getType().string);
			statement.setString(i++, account.getDescription());
			statement.setDouble(i++, account.getBalance());
			statement.setTimestamp(i++, account.getDateCreated());
			statement.setBoolean(i++, account.isFrozen());
			statement.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
	}
	
	/**
	 * This method gets an Account object that represents the account with the 
	 * given accountID.
	 * 
	 * @param accountID		the account_number of the account
	 * @return
	 */
	public Account getAccount(int accountID) {
		query = "SELECT * FROM general_accounts WHERE account_number=?";
		Account account = new Account();
		
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, accountID);
			results = statement.executeQuery();
			if (null != results && results.next()) {
				account.setAccountNumber(results.getInt("account_number"));
				account.setUserID(results.getInt("user_id"));
				account.setType(AccountType.getType(results.getString("account_type")));
				account.setDescription(results.getString("account_descrip"));
				account.setBalance(results.getDouble("balance"));
				account.setDateCreated(results.getTimestamp("date_created"));
				account.setFrozen(results.getBoolean("frozen"));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return account;
	}
	
	/**
	 * This method obtains the list of transaction for the given account number
	 * and role. Bankers can view transactions that have been reversed.
	 * 
	 * @param accountNumber
	 * @param role
	 * @return
	 */
	public List<Transaction> getTransactions(int accountNumber, Role role) {
		List<Transaction> trans = new LinkedList<Transaction>();
		Transaction tx = null;
		if (Role.CUSTOMER == role) {
			query = "SELECT * FROM transactions WHERE account_number=?"
					+ " AND reversed=FALSE";
		} else if (Role.BANKER == role) {
			query = "SELECT * FROM transactions WHERE account_number=?";
		}
		
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, accountNumber);
			results = statement.executeQuery();
			while (results.next()) {
				tx = new Transaction();
				tx.setAccountNumber(results.getInt("account_number"));
				tx.setAmount(results.getDouble("trans_amount"));
				tx.setDate(results.getTimestamp("trans_date"));
				tx.setFraud(results.getBoolean("fraud"));
				tx.setIssuer(results.getString("trans_issuer"));
				tx.setReversed(results.getBoolean("reversed"));
				tx.setTransID(results.getInt("trans_id"));
				trans.add(tx);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
	
		return trans;
	}
	
	/**
	 * This method updates a transaction to set it as possibly fraudulent.
	 * 
	 * @param tx		the transaction to update
	 */
	public void setFraudulent(Transaction tx) {
		query = "UPDATE transactions SET fraud=TRUE WHERE trans_id=?";
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, tx.getTransID());
			statement.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
	}
	
	/**
	 * This method uses a transactional procedure to transfer money from one
	 * account to another and generate appropriate transactions.
	 * 
	 * @param source
	 * @param target
	 * @param amount
	 * @return
	 */
	public Code transfer(int source, int target, double amount) {
		Code code = null;
		query = "CALL transfer(?, ?, ?)";
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, source);
			statement.setInt(2, target);
			statement.setDouble(3, amount);
			results = statement.executeQuery();
			if (results.next()) {
				code = Code.getCode(Integer.valueOf(results.getString("message")));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return code;
	}
	
	/**
	 * This method deletes the given account from the database. Credit Dauphine
	 * is nihilist.
	 * 
	 * @param accountNumber
	 */
	public void closeAccount(int accountNumber) {
		query = "CALL close_account(?)";
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, accountNumber);
			statement.executeQuery();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
	}
	
	/**
	 * This method obtains all of the frozen accounts visible to the given
	 * banker.
	 * 
	 * @param banker
	 * @return
	 */
	public List<OwnedAccount> getFrozenAccounts(Banker banker) {
		List<OwnedAccount> accounts = new LinkedList<OwnedAccount>();
		OwnedAccount account = null;
		User user = null;
		query = "CALL get_new_accounts(?)";
		
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, banker.getUserID());
			results = statement.executeQuery();
			if (null != results) {
				while (results.next()) {
					account = new OwnedAccount();
					user = new User();
					account.setUserID(results.getInt("user_id"));
					user.setUserID(results.getInt("user_id"));
					user.setFirstName(results.getString("first_name"));
					user.setLastName(results.getString("last_name"));
					account.setUser(user);
					account.setAccountNumber(results.getInt("account_number"));
					account.setBalance(results.getDouble("balance"));
					account.setDateCreated(results.getTimestamp("date_created"));
					account.setType(AccountType.getType(results.getString("account_type")));
					accounts.add(account);
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return accounts;
	}
	
	/**
	 * This method creates a list of all of the transactions marked as fraud 
	 * a particular banker can view. 
	 *  
	 * @param banker
	 * @return
	 */
	public List<Transaction> getFraudulentTransactions(Banker banker) {
		List<Transaction> trans = new LinkedList<Transaction>();
		Transaction tx = null;
		query = "SELECT * "
				+ "	FROM transactions "
				+ "	JOIN general_accounts USING(account_number)"
				+ "	JOIN customers USING(user_id)"
				+ "	WHERE fraud=TRUE "
				+ "		AND reversed=FALSE"
				+ "		AND customers.banker_user_id=?";
		
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, banker.getUserID());
			results = statement.executeQuery();
			
			if (null != results) {
				while (results.next()) {
					tx = new Transaction();
					tx.setTransID(results.getInt("trans_id"));
					tx.setAccountNumber(results.getInt("account_number"));
					tx.setDate(results.getTimestamp("trans_date"));
					tx.setIssuer(results.getString("trans_issuer"));
					tx.setAmount(results.getDouble("trans_amount"));
					trans.add(tx);
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return trans;
	}
	
	/**
	 * This method obtains the user_id for the customer that controls an 
	 * account with the given account_number
	 * 
	 * @param accountID		the account_number to locate the user_id for
	 * @return
	 */
	public int getCustomerIdByAccountID(int accountID) {
		int customerID = 0;
		query = "SELECT user_id FROM general_accounts WHERE account_number=?";
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, accountID);
			results = statement.executeQuery();
			
			if (null != results) {
				while (results.next()) {
					customerID = results.getInt("user_id");
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return customerID;
	}
	
	public void reverseTx(int txid) {
		query = "CALL reverse_tx(?)";
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, txid);
			statement.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
	}
	
	/**
	 * This method either freezes or thaw the given account by number.
	 * 
	 * @param accountID		the account_number to freeze or unfreeze
	 * @param freeze		flag indicating if it should be frozen or thawed
	 */
	public void freezeAccount(int accountID, boolean freeze) {
		query = "UPDATE general_accounts SET frozen=? WHERE account_number=?";
		System.out.println("Freeze? " + freeze);
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setBoolean(1, freeze);
			statement.setInt(2, accountID);
			statement.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
	}
	
	/**
	 * This method updates the terms for the associated account
	 * @param terms
	 */
	public void setTerms(Terms terms) {
		query = "INSERT INTO terms VALUES(DEFAULT,?,?,?,?,?,?)";
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, terms.getAccountNumber());
			statement.setDouble(2, terms.getMinBalance());
			statement.setDouble(3, terms.getMaxBalance());
			statement.setDouble(4, terms.getInterestRate());
			statement.setDate(5, terms.getPeriod());
			statement.setFloat(6, terms.getFees());
			statement.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
	}
}
