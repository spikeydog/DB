package bank.bean;

import java.sql.Timestamp;

public class OwnedAccount extends Account {
	private User user;
	
	public OwnedAccount() {
		// TODO Auto-generated constructor stub
	}

	public OwnedAccount(Account account, User user) {
		super(account.getAccountNumber(), account.getUserID(), account.getType().string, 
				account.getDescription(), account.getBalance(), account.getDateCreated(),
				account.isFrozen());
		this.user = user;
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User customer) {
		this.user = customer;
	}

}
