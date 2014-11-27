package bank.bean;

import bank.util.Role;

public class User {
	private int userID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Role role;
	
	public User() {
		
	}
	
	public User(int userID, String username, String password, String firstName, 
			String lastName, Role role) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
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
	
	@Override
	public String toString() {
		StringBuilder scribe = new StringBuilder();
		scribe.append(super.toString());
		scribe.append(firstName).append(lastName);
		scribe.append(role);
		return scribe.toString();
	
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
