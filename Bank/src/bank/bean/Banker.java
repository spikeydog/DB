package bank.bean;

public class Banker extends User {
	private int employeeID;
	
	public Banker() {
		// TODO Auto-generated constructor stub
	}

	public Banker(int userID, String username, String password, String firstName,
			String lastName, String employeeID) {
		super(userID, username, password, firstName, lastName);
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
