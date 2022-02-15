package labs.week2.enums;

public enum DepositLength {

	THREE_MONTHS(90),
	SIX_MONTHS(180);
	
	private int days;
	
	private DepositLength(int days) {
		this.days = days;
	}
	
	public int getDays() {
		return this.days;
	}
}
