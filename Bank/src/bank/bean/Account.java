package bank.bean;

import java.sql.Timestamp;
import java.io.Serializable;

import bank.util.AccountType;

/**
 * This class models a bank account
 * 
 * @author Spikeydog
 */
public class Account implements Serializable {
	private int accountNumber;
	private int userID;
	private AccountType type;
	private String description;
	private double balance;
	private Timestamp dateCreated;
	private boolean isFrozen;
	private static final long serialVersionUID = 1L;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor used for quick construction of test objects. 
	 * 
	 * @param accountNumber
	 * @param userID
	 * @param type
	 * @param descr
	 * @param balance
	 * @param dateCreated
	 * @param isFrozen
	 */
	public Account(int accountNumber, int userID, String type, String descr,
			double balance, Timestamp dateCreated, boolean isFrozen) {
		this.accountNumber = accountNumber;
		this.userID = userID;
		this.type = AccountType.getType(type);
		this.description = descr;
		this.balance = balance;
		this.dateCreated = dateCreated;
		this.isFrozen = isFrozen;
	}

	/**
	 * Auto-generated getters and setters below
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isFrozen() {
		return isFrozen;
	}

	public void setFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}

}
