package bank.util;

public enum Code {
	OK(1, "Action successful!"),
	SQL_ERROR(-1, "A database error occurred"),
	DUP_USER(11, "Username already in use"),
	DUP_CUST(12, "Account information already in use"),
	DUP_EMAIL(21, "Email address already in use"),
	LOW_FUND(31, "Transfer would reduce source account below minimum balance"),
	OVER_FUND(32, "Transfer would increase target account above maximum balance");
	
	
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
		case 31 : code = LOW_FUND; break;
		case 32 : code = OVER_FUND; break;
		}
		return code;
	}
}
