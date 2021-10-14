package com.revature.services;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class VerificationService {
	
	public boolean verifyMoney(double amount)
	{
		if (amount > 0 && BigDecimal.valueOf(amount) <= 2 ) return true;
		return false;
	}
	public boolean verifyEmail(String eMail) {
		return false;
	}
	
}
