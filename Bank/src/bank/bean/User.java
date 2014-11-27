package bank.bean;

public class User {
	private int userID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public User() {
		
	}
	
	public User(int userID, String username, String password, String firstName, 
			String lastName) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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
		return scribe.toString();
	
	}
}
