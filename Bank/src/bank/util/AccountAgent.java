package bank.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bank.bean.Account;
import bank.util.AbstractDatabaseClass;

public class AccountAgent extends AbstractDatabaseClass {

	public AccountAgent() {
		super();
	}
	
	public List<Account> getAccounts(int userID) {
		List<Account> accounts = new LinkedList<Account>();
		Account account = null;
		ResultSet results = null;
		PreparedStatement statement = null;
		String query = "SELECT * FROM general_accounts WHERE user_id=?";
		
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
				account.setDateCreated(results.getDate("date_created"));
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

}
