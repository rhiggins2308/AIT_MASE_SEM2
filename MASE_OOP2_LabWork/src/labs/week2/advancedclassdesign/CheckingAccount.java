package labs.week2.advancedclassdesign;

public class CheckingAccount extends Account {

	private final double overDraftLimit;
	
	public CheckingAccount(double balance, double overDraftLimit) {
		super(balance);
		this.overDraftLimit = overDraftLimit;
	}
	
	public CheckingAccount(double balance) {
		this(balance, 0.00);
	}
	
	@Override
	public boolean withdraw(double amount) {
		if (this.getBalance() - amount < 0) {
			if (Math.abs(this.getBalance() - amount) > Math.abs(this.overDraftLimit)) {
				return false;
			} else {
				this.balance -= amount;
				return true;
			}
		} else {
			this.balance -= amount;
			return true;
		}	
	}
	
	@Override
	public String getDescription() {
		return "Checking Account";
	}
}
