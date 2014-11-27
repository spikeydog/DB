package bank.util;

public enum AccountType {
	CHECKING("checking"),
	SAVINGS("savings"),
	LOAN("loan"),
	CREDIT("credit");
	
	public String string;
	
	private AccountType (String string) {
		this.string = string;
	}
	
	public static AccountType getType(String string) {
		AccountType type = null;
		
		if (string.equals(CHECKING.string)) {
			type = CHECKING;
		} else if (string.equals(SAVINGS.string)) {
			type = SAVINGS;
		} else if (string.equals(LOAN.string)) {
			type = LOAN;
		} else if (string.equals(CREDIT.string)) {
			type = CREDIT;
		}
		
		return type;
	}
}
