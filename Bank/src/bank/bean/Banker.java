package bank.bean;

import bank.util.Role;

public class Banker extends User {
	private int employeeID;
	
	public Banker() {
		// TODO Auto-generated constructor stub
	}

	public Banker(User user, String employeeID) {
		super(user.getUserID(), user.getUsername(), user.getPassword(), 
				user.getFirstName(), user.getLastName(), Role.BANKER);
		try {
			this.employeeID = Integer.valueOf(employeeID);
		} catch (NumberFormatException ex) {
			this.employeeID = 0;
		}
		
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

}
