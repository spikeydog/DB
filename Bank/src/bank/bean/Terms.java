package bank.bean;

import java.sql.Date;

/**
 * This class models the terms of an Account.
 * 
 * @author Spikeydog
 *
 */
public class Terms {
	private int termsID;
	private int accountNumber;
	private double minBalance;
	private double maxBalance;
	private Date period;
	private float fees;
	private float interestRate;
	
	public Terms() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Auto-generated getters and setters below
	 */
	public int getTermsID() {
		return termsID;
	}

	public void setTermsID(int termsID) {
		this.termsID = termsID;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	public double getMaxBalance() {
		return maxBalance;
	}

	public void setMaxBalance(double maxBalance) {
		this.maxBalance = maxBalance;
	}

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period;
	}

	public float getFees() {
		return fees;
	}

	public void setFees(float fees) {
		this.fees = fees;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

}
