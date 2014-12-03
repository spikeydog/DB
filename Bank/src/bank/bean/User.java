package bank.bean;

import bank.util.Role;

/**
 * This class models a generic user of the database application
 * @author Spikeydog
 *
 */
public class User {
	private int userID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Role role;
	
	public User() {
		
	}
	
	/**
	 * Creates a new User from the given parameters.
	 * @param userID
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param role
	 */
	public User(int userID, String username, String password, String firstName, 
			String lastName, Role role) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
	/**
	 * Auto-generated getters and setters below
	 */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
