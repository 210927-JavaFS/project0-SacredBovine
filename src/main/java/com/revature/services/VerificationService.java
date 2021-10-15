package com.revature.services;

import com.revature.throwables.InvalidMoneyRuntimeException;

public class VerificationService {
	
	public void verifyMoney(double amount) throws InvalidMoneyRuntimeException {
		if (!(amount > 0 && 100*amount % 1 == 0)) throw new InvalidMoneyRuntimeException();
	}

}