package bank.util;

public enum Code {
	OK(1, "Action successful!"),
	SQL_ERROR(-1, "A database error occurred"),
	DUP_USER(11, "Username already in use"),
	DUP_CUST(12, "Account information already in use"),
	DUP_EMAIL(21, "Email address already in use");
	
	
	public int value;
	public String message;
	
	private Code(int value, String message) {
		this.value = value;
		this.message = message;
	}
	
	public static Code getCode(int value) {
		Code code = null;
		switch (value) {
		case -1 : code = SQL_ERROR; break;
		case 1 : code = OK; break;
		case 11 : code = DUP_USER; break;
		case 12 : code = DUP_CUST; break;
		case 21 : code = DUP_EMAIL; break;
		}
		return code;
	}
}
