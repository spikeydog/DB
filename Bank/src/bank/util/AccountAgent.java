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

public class AccountAgent extends AbstractDatabaseClass {
	String query = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	public AccountAgent() {
		super();
	}
	
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
	
	public Terms getTerms(Account account) {
		Terms terms = null;
		int accountNumber = account.getAccountNumber();
		query = "SELECT * FROM terms WHERE account_number=?"
				+ "ORDER BY terms_id DESC LIMIT 1";
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, accountNumber);
			results = statement.executeQuery();
			if (results.next()) {
				terms = new Terms();
				terms.setAccountNumber(results.getInt("account_number"));
				terms.setTermsID(results.getInt("terms_id"));
				terms.setFees(results.getFloat("fees"));
				terms.setMinBalance(results.getDouble("min_balance"));
				terms.setMaxBalance(results.getDouble("max_balance"));
				terms.setPeriod(results.getDate("period"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
		
		return terms;
	}
	
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
	
	public void freezeAccount(int accountID) {
		query = "UPDATE general_accounts SET frozen=TRUE WHERE account_number=?";
		super.connect();
		try {
			statement = getPreparedStatement(query);
			statement.setInt(1, accountID);
			statement.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.disconnect();
		}
	}
	
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
