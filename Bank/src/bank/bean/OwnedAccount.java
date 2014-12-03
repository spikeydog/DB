package bank.bean;

import java.io.Serializable;

/**
 * This class models an Account with associated Customer data
 * 
 * @author Spikeydog
 *
 */
public class OwnedAccount extends Account implements Serializable {
	private User user;
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Default no-args constructor.
	 */
	public OwnedAccount() {
		
	}

	/**
	 * Creates a new OwnedAccount from the given Account and User; allows 
	 * Bankers to view both account and customer data from a single bean
	 *  
	 * @param account
	 * @param user
	 */
	public OwnedAccount(Account account, User user) {
		super(account.getAccountNumber(), account.getUserID(), account.getType().string, 
				account.getDescription(), account.getBalance(), account.getDateCreated(),
				account.isFrozen());
		this.user = user;
	}

	/**
	 * Auto-generated getters and setters below
	 */
	public User getUser() {
		return user;
	}

	public void setUser(User customer) {
		this.user = customer;
	}

}
