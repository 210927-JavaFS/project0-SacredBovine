package com.revature.controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.revature.services.VerificationService;
import com.revature.throwables.InvalidMoneyRuntimeException;

public class AdminController extends TellerController {
		
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(AdminController.class);
	private UserService userService = new UserService();
	private AccountService accountService = new AccountService();
	private VerificationService verificationService = new VerificationService();
	
	public void decisionMenu(User user) {
		try {
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
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			scan.next();
			decisionMenu(user);
		}
	}
	public void adminMenu(User user) {
		try {
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
					case 1 : // Accounts Admin Menu
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
								case 1 : // Deposit Funds
									depositFunds();
									log.info("Admin :"+user.getEMail()+" is conducting a deposit.");
									break;
								case 2 : // Withdraw Funds
									withdrawFunds();
									log.info("Admin :"+user.getEMail()+" is conducting a withdraw.");
									break;
								case 3 : // Transfer Funds
									transferFunds();
									log.info("Admin :"+user.getEMail()+" is conducting a transfer.");
									break;
								case 4 : // Close Account
									closeAccount();
									log.info("Admin :"+user.getEMail()+" is closing an account.");
									break;
								case 5 : // Go back
									break;
								default :
									System.out.println(" Invalid input. Please try again. \n");
									break;
							}
						}
						input = 1;
						break;
					case 2 : // Users Admin Menu
						input = 0;
						while (input!=2) {
							System.out.println(" What would you like to do? \n"
									+ "   1: Open User \n"
									+ "   2: Go Back ");
							int input2 = scan.nextInt();
							switch (input2) {
								case 1 :
									selectUserMenu();
									break;
								case 2 : 
									break;
								default :
									System.out.println(" Invalid input. Please try again. \n");
									break;
							}
						}
						input = 2;
						break;
					case 3 : // Requests Menu
						this.viewRequests();
						break;
					case 4 : // Personal Banking Menu
						accountHolderMenu(user);
						break;
					case 5 : // Logout
						break;
					default :
						System.out.println(" Invalid input. Please try again. \n");
						break;
				}
			}
		}catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			scan.next();
			adminMenu(user);
		}
	}
	void selectUserMenu() {
		try {
			System.out.println(" Input the email address of the user you would like to see: ");
			String eMail = scan.next().toLowerCase().trim();   
			log.info("Admin looking for :"+eMail+": \n");
			int input = 0;
			User user = userService.getByEMail(eMail);
			while (input!=3 && user.getID() > 0) {
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
									System.out.println(" Invalid input. Please try again. \n");
									break;
							}
						}
						input = 1;
						break;
					case 2:
						scan.next();
						System.out.println(" Input new phone number \n");
						userService.SetPhone(user, scan.nextLine().trim());
						log.info("User :"+user.getEMail()+" had their phone number updated.");
						break;
					case 3 :
						break;
					default :
						System.out.println(" Invalid input. Please try again. \n");
						break;
				}
			}
		}
		catch(InputMismatchException e) {
			log.error("Admin menu"+e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			scan.next();
			selectUserMenu();
		}
	}

// Accounts Admin Menu	
	boolean depositFunds() {
		try {
			System.out.println(" Input the account # to deposit funds into: ");
			@Min(value = 10000000, message = "Invalid Account Number")
			@Max(value = 99999999, message = "Invalid Account Number")
			int input = scan.nextInt();
			Account account = accountService.getByID(input);
			if (account.getID()>0) {
				System.out.println(" Input the amount to deposit: ");
				@Positive(message = "Input a positive Value")
				@Digits(fraction = 2, integer = 10, message = "Input a valid amount")
				double amount = scan.nextDouble();
				verificationService.verifyMoney(amount);
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
			System.out.println(" Could not fetch account #:"+input+" \n");
			return false;
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			return false;
		}
		catch(InvalidMoneyRuntimeException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			return false;
		}	
	}	
	boolean withdrawFunds() {
		System.out.println(" Input the account # to withdraw funds from: ");
		try {
			@Min(value = 10000000, message = "Invalid Account Number")
			@Max(value = 99999999, message = "Invalid Account Number")
			int input = scan.nextInt();
			Account account = accountService.getByID(input);
			if (account.getID()>0) {
				System.out.println(" Input the amount to withdraw: $");
				@Positive(message = "Input a positive Value")
				@Digits(fraction = 2, integer = 10, message = "Input a valid amount")
				double amount = scan.nextDouble();
				verificationService.verifyMoney(amount);
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
			System.out.println(" Could fetch account #: "+input+" \n");
			return false;
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Improper $ amount. \n"
					+ " Please try again.");
			return false;
		}
		catch(InvalidMoneyRuntimeException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Improper $ amount. \n"
					+ " Please try again.");	
			return false;
		}
	}
	boolean transferFunds() {
		try {
			System.out.println(" Input the account # to transfer funds from: ");
			@Min(value = 10000000, message = "Invalid Account Number")
			@Max(value = 99999999, message = "Invalid Account Number")
			int input = scan.nextInt();
			Account accountSource = accountService.getByID(input);
			if (accountSource.getID()>0) {
				System.out.println(" Input the account # to transfer funds into: ");
				input = scan.nextInt();
				Account accountDestination = accountService.getByID(input);
				if (accountDestination.getID()>0) {
					System.out.println(" How much would you like to transfer: $");
					@Positive(message = "Input a positive Value")
					@Digits(fraction = 2, integer = 10, message = "Input a valid amount")
					Double amount = scan.nextDouble();
					verificationService.verifyMoney(amount);
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
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			return false;
		}
		catch(InvalidMoneyRuntimeException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			return false;
		}	
	}
	boolean closeAccount() {
		try {
			System.out.println(" Input the account # to close: ");
			@Min(value = 10000000, message = "Invalid Account Number")
			@Max(value = 99999999, message = "Invalid Account Number")
			int input = scan.nextInt();
			Account account = accountService.getByID(input);
			if (account.getID()>0) {
				if(account.getBalance() > 0) {
					System.out.println(" The account has a balance of $"+account.getBalance()+" \n"
							+"    1: Transfer remaining funds to another account \n"
							+"    2: Withdraw remaining funds \n"
							+"    3: Cancel");
					int input2 = scan.nextInt();
					switch (input2) {
						case 1 :
							Double amount = account.getBalance();
							System.out.println(" Input the account # to transfer funds into: ");
							@Min(value = 10000000, message = "Invalid Account Number")
							@Max(value = 99999999, message = "Invalid Account Number")
							int input3 = scan.nextInt();
							Account accountDestination = accountService.getByID(input3);
							if (account.getID() > 0) {
								if(accountService.withdraw(account, amount)){
									if(accountService.deposit(accountDestination, amount)){
										System.out.println(" Successfully transfered $"+amount+" from account #: "+account.getID()
										+" to account #: "+accountDestination.getID()+" \n");
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
							System.out.println(" Destination account $: "+input3+" could not be found ");
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
							System.out.println(" Invalid input. Please try again. \n");
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
		}
		catch(InputMismatchException e) {
			log.error(e.getStackTrace().toString());
			System.out.println(" Invalid input. Please try again. \n");
			return false;
		}
		return false;
	}

}