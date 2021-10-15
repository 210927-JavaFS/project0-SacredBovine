package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;

public class AccountServiceTest {

	public static AccountDAOImpl accountDAO;
	public static AccountService accountService;
	public static int accountID1;
	public static int accountID2;
	public static Account account1;
	public static Account account2;
	public static double bal1;
	public static double bal2;
	public static double bal3;
	public static double bal4;
	public static double newBal1;
	public static double newBal2;
	public static boolean result;
		
	@BeforeAll
	public static void setAccountService() {
		accountService = new AccountService();
	}
	
	@BeforeEach
	public void setValues() {
		accountDAO = new AccountDAOImpl();
		accountID1 = 10000059;
		accountID2 = 10000060; 
		account1 = accountService.getByID(accountID1);
		account2 = accountService.getByID(accountID2);
		bal1 = 1000.50;
		bal2 = 2000.25;
		bal3 = -100;
		bal4 = 0.005;
		account1.setBalance(bal1);
		account2.setBalance(bal2);
		accountDAO.updateAccount(account1);
		accountDAO.updateAccount(account2);
	}
	
	@Test
	public void testDeposit() {
		result = accountService.deposit(account1, 100.25);
		assertEquals(accountService.getByID(accountID1).getBalance(),1100.75);
		assertTrue(result);
		result = accountService.deposit(account1, bal3);
		assertTrue(!result);
		result = accountService.deposit(account1, bal4);
		assertTrue(!result);
	}
	
	@Test
	public void testWithdraw() {
		result = accountService.withdraw(account1, 100.25);
		assertEquals(accountService.getByID(accountID1).getBalance(),900.25);
		assertTrue(result);
		result = accountService.withdraw(account1, bal3);
		assertTrue(!result);
		result = accountService.withdraw(account1, bal4);
		assertTrue(!result);
	}
	
	@Test
	public void testTransfer() {
		result = accountService.transfer(account1, account2, bal1);
		assertEquals(accountService.getByID(accountID1).getBalance(),0);
		assertEquals(accountService.getByID(accountID2).getBalance(),3000.75);
		assertTrue(result);
		result = accountService.transfer(account1, account2, 1000.25);
		assertTrue(!result);
		result = accountService.transfer(account2, account1, 5000);
		assertTrue(!result);
		result = accountService.transfer(account2, account1, bal3);
		assertTrue(!result);
		result = accountService.transfer(account2, account1, bal4);
		assertTrue(!result);
	}
}
