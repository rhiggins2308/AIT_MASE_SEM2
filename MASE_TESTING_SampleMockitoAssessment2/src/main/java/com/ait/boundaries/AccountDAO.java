package com.ait.boundaries;

import com.ait.atm.control.Account;

public interface AccountDAO {
	public Account findAccount(String accountNum);
}
