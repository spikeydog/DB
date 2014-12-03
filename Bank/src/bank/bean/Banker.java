package bank.bean;

import bank.util.Role;

/**
 * This class models a banker within the database application
 * 
 * @author Spikeydog
 *
 */
public class Banker extends User {
	private int employeeID;
	
	public Banker() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a new Banker from the given User and employeeID
	 * @param user
	 * @param employeeID
	 */
	public Banker(User user, String employeeID) {
		super(user.getUserID(), user.getUsername(), user.getPassword(), 
				user.getFirstName(), user.getLastName(), Role.BANKER);
		try {
			this.employeeID = Integer.valueOf(employeeID);
		} catch (NumberFormatException ex) {
			this.employeeID = 0;
		}
		
	}

	/**
	 * Auto-generated getters and setters below
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

}
