package bank.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Transaction implements Serializable {
	private int accountNumber;
	private int transID;
	private Timestamp date;
	private String issuer;
	private Double amount;
	private boolean isFraud;
	private boolean isReversed;
	private static final long serialVersionUID = 1L;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getTransID() {
		return transID;
	}

	public void setTransID(int transID) {
		this.transID = transID;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public boolean isFraud() {
		return isFraud;
	}

	public void setFraud(boolean isFraud) {
		this.isFraud = isFraud;
	}

	public boolean isReversed() {
		return isReversed;
	}

	public void setReversed(boolean isReversed) {
		this.isReversed = isReversed;
	}

}
