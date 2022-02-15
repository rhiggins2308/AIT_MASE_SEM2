package labs.week2.advancedclassdesign;

import java.util.Date;

public abstract class Account {

	protected double balance;
	
	public Account(double balance) {
        this.balance = balance;
    }
	
	public double getBalance() {
        return this.balance;
    }
	
	public void deposit(double amount) {
		this.balance += amount;
	}
	
	@Override
    public String toString() {
        return getDescription() + ": current balance is " + this.getBalance();
    }
	public abstract boolean withdraw(double amount);
	
	public abstract String getDescription();
}
