package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.LoginService;
import com.revature.services.UserService;

public class AdminController extends TellerController {
		
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	private LoginService loginService = new LoginService();
	private UserService userService = new UserService();
	private AccountService accountService = new AccountService();
	
	public void decisionMenu(User user) {
		System.out.println(" What menu would you like to access? \n"
				+ "   1: Bank Admin Menu \n"
				+ "   2: Personal Banking Menu");
		int input = scan.nextInt();
		switch(input) {
			case 1 :
				adminMenu(user);
				break;
			case 2 : 
				accountHolderMenu(user);
				break;
			case 3 : 
				System.out.println(" Invalid input. Please try again. \n");
				decisionMenu(user);
				break;
		}					
	}
	
	public void adminMenu(User user) {
		int input = 0;
		while (input!=5) {
			System.out.println(" What would you like to access? \n"
					+ "   1: Accounts Admin Menu \n"
					+ "   2: Users Admin Menu \n"
					+ "   3: Open Reqests \n"
					+ "   4: Personal Banking Menu \n"
					+ "   5: Logout");
			input = scan.nextInt();
			switch(input) {
				case 1 :
					input = 0;
					while (input!=5) {
						System.out.println(" What would you like to do? \n"
								+ "   1: Deposit Funds \n"
								+ "   2: Withdraw Funds \n"
								+ "   3: Transfer Funds \n"
								+ "   4: Close Account \n"
								+ "   5: Go Back ");
						input = scan.nextInt();
						switch (input) {
							case 1 :
								depositFunds();
								break;
							case 2 : 
								withdrawFunds();
								break;
							case 3 :
								transferFunds();
								break;
							case 4 :
								closeAccount();
								break;
							case 5 :
								break;
							default :
								System.out.println(" Invalid input \n");
								break;
						}
					}
					break;
				case 2 :	
					input = 0;
					while (input!=2) {
						System.out.println(" What would you like to do? \n"
								+ "   1: Open User \n"
								+ "   2: Go Back ");
						input = scan.nextInt();
						switch (input) {
							case 1 :
								selectUserMenu();
								break;
							case 2 : 
								break;
							default :
								System.out.println(" Invalid input \n");
								break;
						}
					break;
					}
				case 3 :
					this.viewRequests();
					break;
				case 4 :
					accountHolderMenu(user);
					break;
				case 5 :
					break;
				default :
					System.out.println(" Invalid input \n");
					break;
			}
		}
	}
	
	public boolean setUserType() {
		return false;
	}
	public void closeAccount(User user, Account account) {}
	
		

/*methods CreateUser, ElevateUser, DeleteUser*/
	
	
	
// Accounts Admin Menu	
	boolean depositFunds() {
		System.out.println(" Input the account # to deposit funds into: ");
		int input = scan.nextInt();
		Account account = accountService.getByID(input);
		if (account!=null) {
			System.out.println(" Input the amount to deposit: ");
			double amount = scan.nextDouble();
			boolean foo = accountService.deposit(account, amount);	
			if (foo) {
				System.out.println(" Successfuly deposited $"+amount+" into account #: "+account.getID());
				return true;
			}
			else {
				System.out.println(" Could not deposit $"+amount+" into account #: "+account.getID()
					+" $"+account.getBalance()+" \n");
				return false;
			}
		}
		System.out.println(" Could fetch account #:"+account.getID()+" \n");
		return false;
	}	
	boolean withdrawFunds() {
		System.out.println(" Input the account # to withdraw funds from: ");
		int input = scan.nextInt();
		Account account = accountService.getByID(input);
		if (account!=null) {
			System.out.println(" Input the amount to withdraw: $");
			double amount = scan.nextDouble();
			boolean foo = accountService.deposit(account, amount);	
			if (foo) {
				System.out.println(" Successfully withdrew $"+amount+" from account#: "+account.getID());
				return foo;
			}
			else {
				System.out.println(" Could not withdraw $"+amount+" into account #: "+account.getID()
					+" $"+account.getBalance()+" \n");
				return false;
			}
		}
		System.out.println(" Could fetch account #: "+account.getID()+" \n");
		return false;
	}
	boolean transferFunds() {
		System.out.println(" Input the account # to transfer funds from: ");
		int input = scan.nextInt();
		Account accountSource = accountService.getByID(input);
		if (accountSource!=null) {
			System.out.println(" Input the account # to transfer funds into: ");
			input = scan.nextInt();
			Account accountDestination = accountService.getByID(input);
			if (accountDestination!=null) {
				System.out.println(" How much would you like to transfer: $");
				Double amount = scan.nextDouble();
				if(accountService.withdraw(accountSource, amount)){
					if(accountService.deposit(accountDestination, amount)){
						System.out.println(" Successfully transfered $"+amount+" from account #: "+accountSource.getID()+" to account #: "+accountDestination.getID()+" \n");
						return true;
					} else {
						System.out.println(" Could not deposit $"+amount+" into account #: "+accountDestination.getID()
						+" $"+accountDestination.getBalance()+" \n");
						return false;
					}
				}
				System.out.println(" Could not withdraw $"+amount+" from account #: "+accountSource.getID()
				+" $"+accountSource.getBalance()+" \n");
				return false;
			}
			System.out.println(" Destination account $: "+input+" could not be found ");
			return false;
		}
		System.out.println(" Source account $: "+input+" could not be found ");
		return false;
	}
	boolean closeAccount() {
		System.out.println(" Input the account # to close: ");
		int input = scan.nextInt();
		Account account = accountService.getByID(input);
		if (account!=null) {
			if(account.getBalance() > 0) {
				System.out.println(" The account has a balance of $"+account.getBalance()+" \n"
						+"    1: Transfer remaining funds to another account \n"
						+"    2: Withdraw remaining funds \n"
						+"    3: Cancel");
				input = scan.nextInt();
				switch (input) {
					case 1 :
						Double amount = account.getBalance();
						System.out.println(" Input the account # to transfer funds into: ");
						input = scan.nextInt();
						Account accountDestination = accountService.getByID(input);
						if (account != null) {
							if(accountService.withdraw(account, amount)){
								if(accountService.deposit(accountDestination, amount)){
									System.out.println(" Successfully transfered $"+amount+" from account #: "+account.getID()+" to account #: "+accountDestination.getID()+" \n");
									return accountService.closeAccount(account);
								} else {
									System.out.println(" Could not deposit $"+amount+" into account #: "+accountDestination.getID()
									+" $"+accountDestination.getBalance()+" \n");
									return false;
								}
							}
							System.out.println(" Could not withdraw $"+amount+" from account #: "+account.getID()
							+" $"+account.getBalance()+" \n");
							return false;
						}
						System.out.println(" Destination account $: "+input+" could not be found ");
						return false;
					case 2:
						if (accountService.withdraw(account, account.getBalance())) {
							if(accountService.closeAccount(account)) {
								System.out.println(" Successfully closed account #: "+ account.getID());
								return true;
							} else {
								System.out.println(" Could not close account #:"+ account.getID());
								return false;
							}
						}
						System.out.println(" Could not withdraw funds. Please try again. ");
						return false;
					case 3:
						System.out.println(" Canceling account closure.");
						return true;
					default:
						System.out.println(" Invalid input");
						break;
				}
			} 
			else if (account.getBalance()==0) {
				if(accountService.closeAccount(account)) {
					System.out.println(" Successfully closed account #: "+ account.getID());
					return true;
				} else {
					System.out.println(" Could not close account #:"+ account.getID());
					return false;
				}
			}
			else {
				System.out.println(" Account has a negative balance of $"+account.getBalance()+" \n Please reconcile account :"+ account.getID());
				return false;
			}
		}
		return false;
	}
	void selectUserMenu() {
		scan.nextLine();
		System.out.println(" Input the email address of the user you would like to see: \n");
		String eMail = scan.nextLine().toLowerCase().trim();
		int input = 0;
		User user = userService.getByEMail(eMail);
		while (input!=3) {
			System.out.println(userService.displayUserInfo(user)+" \n\n ");
			System.out.println(" What would you like to edit? \n"
					+ "   1: Account Type \n"
					+ "   2: Phone Number \n"
					+ "   3: Cancel");
			input = scan.nextInt();
			switch (input) {
				case 1 :
					while (input != 4) {
						System.out.println(" Set User to what type? \n"
								+ "   1: Customer \n"
								+ "   2: Employee \n"
								+ "   3: Bank Admin \n"
								+ "   4: Cancel");
						input = scan.nextInt();
						switch (input) {
							case 1 :
								userService.setType(user,input);
								input = 4;
								break;
							case 2 :
								userService.setType(user,input);
								input = 4;
								break;
							case 3 :
								userService.setType(user,input);
								input = 4;
								break;
							case 4 :
								break;
							default: 
								System.out.println(" Invalid input \n");
								break;
						}
					}
					break;
				case 2:
					scan.nextLine();
					System.out.println(" Input new phone number \n");
					userService.SetPhone(user, scan.nextLine().trim());
					break;
				case 3 :
					break;
				default :
					System.out.println(" Invalid input \n");
					break;
			}
		}
	}
}

	