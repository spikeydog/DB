package bank.bean;

import bank.util.Role;

/**
 * This class models a Customer of the web application
 * 
 * @author Spikeydog
 *
 */
/**
 * @author Spikeydog
 *
 */
public class Customer extends User {
	private String emailAddress;
	private String ssn;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipCode;
	private String telephone;
	private int bankerID;
	private int riskRating;
	
	/**
	 * Default no-args constructor.
	 */
	public Customer() {
	}
	
	
	/**
	 * Creates a new Customer from an existing User
	 * @param user
	 */
	public Customer(User user) {
		super(user.getUserID(), user.getUsername(), user.getPassword(), 
				user.getFirstName(), user.getLastName(), user.getRole());
	}

	
	/**
	 * @param user			The existing User to base this Customer from
	 * @param emailAddress
	 * @param ssn
	 * @param address1
	 * @param address2
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param telephone
	 * @param bankerID
	 * @param riskRating
	 */
	public Customer(User user, String emailAddress, String ssn, String address1, 
			String address2, String city, String state, String zipCode, 
			String telephone, int bankerID, int riskRating) {
		
		super(user.getUserID(), user.getUsername(), user.getPassword(), 
				user.getFirstName(), user.getLastName(), user.getRole());
		this.emailAddress = emailAddress;
		this.ssn = ssn;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.telephone = telephone;
		this.bankerID = bankerID;
		this.riskRating = riskRating;
	}

	/**
	 * Auto-generated getters and setters below
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getBankerID() {
		return bankerID;
	}

	public void setBankerID(int bankerID) {
		this.bankerID = bankerID;
	}

	public int getRiskRating() {
		return riskRating;
	}

	public void setRiskRating(int riskRating) {
		this.riskRating = riskRating;
	}
	
	@Override
	public String toString() {
		StringBuilder scribe = new StringBuilder();
		scribe.append(super.toString());
		scribe.append(address1).append(address2).append(telephone).append(emailAddress);
		return scribe.toString();
	
	}

}
