package SimpleBank;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) 
	{
		
		//Initializes the scanner
		Scanner scanner = new Scanner(System.in);

		//Initializes the Bank with info
		BankInfo bank = new BankInfo("B-Wade's Bank");
		
		//Adds a user to the bank (automatically creates a savings account)
		Users user1 = bank.addNewUser("Brandon", "Wade", "Wademan", "pop111");
		
		//Adds a checking account for our user
		Accounts newAccount = new Accounts("Checking", user1, bank);
		user1.addAccount(newAccount);
		bank.addAccount(newAccount);
		
		Users loginPrompt;
		while (true)
		{
			//stays in the login prompt until successful login
			loginPrompt = Driver.loginMenu(bank, scanner);
			
			//stay in the main menu until user quits
			Driver.printUserMenu(loginPrompt, scanner);
		}
		
	}
	
	public static Users loginMenu(BankInfo bank, Scanner scanner)
	{
		String userName;
		String password;
		Users authentification;
		
		//prompt the user for username and password until a correct one is reached
		do
		{
			System.out.printf("\n\nWelcome to %s,\n\n", bank.getName());
			System.out.print("Enter Username: ");
			userName = scanner.nextLine();
			System.out.print("Enter Password: ");
			password = scanner.nextLine();
			
			//Try to get the user object corresponding username and password
			authentification = bank.authenticate(userName, password);
			if(authentification == null)
			{
				System.out.println("Incorrect Username/Password combo. " + 
								   "Please Try again. ");
			}
			
		}	while(authentification == null); // continue looping until successful login
		return authentification;
	}

	public static void printUserMenu(Users theUser, Scanner scanner)
	{
		//print a summary of the user's accounts
		theUser.printAccountSummary();
		
		//Initialize
		int choice;
		
		//User menu
		do
		{
			System.out.printf("\nWelcome %s, what would you like to do?\n", theUser.getFirstName());
			System.out.println("  1)Show transaction history");
			System.out.println("  2)Deposit");
			System.out.println("  3)Withdrawl");
			System.out.println("  4)Transfer Money");
			System.out.println("  5)Quit");
			System.out.print("Enter choice: ");
			choice = scanner.nextInt();
			
			if (choice < 1 || choice > 5)
			{
				System.out.println("Invalid selection of a number, please choose 1-5 ");
			}
		} while(choice < 1 || choice > 5);
			
		//process the choice
		switch (choice)
		{
		case 1:
			Driver.showTransactionHistory(theUser, scanner);
			break;
		case 2:
			Driver.depositMoney(theUser, scanner);
			break;
		case 3:
			Driver.withdrawlMoney(theUser, scanner);
			break;
		case 4:
			Driver.transferMoney(theUser, scanner);
			break;
		}
		
	//redisplay the menu unless user is done
		if(choice != 5)
		{
			Driver.printUserMenu(theUser, scanner);
		}
	}
	
	public static void showTransactionHistory(Users theUser, Scanner scanner)
	{
		int whichAccount;
		
		//get account whose transaction history is wanted
		do
		{
			System.out.printf("Enter the number (1 - %d) of the account whose transactions wish to be viewed: ",
								theUser.numAccounts());
			whichAccount = scanner.nextInt()-1;
			if(whichAccount < 0 || whichAccount >= theUser.numAccounts())
			{
				System.out.println("Invalid account. Please try again.");
			}
		} while(whichAccount < 0 || whichAccount >= theUser.numAccounts());
			
		//print the transaction history of that account
		theUser.printAccountTransactionHistory(whichAccount);
	}
	
	public static void transferMoney(Users theUser, Scanner scanner)
	{
		//Initialize
		int fromAccount;
		int toAccount;
		double amount;
		double accountBalance;
		
		//Get the amount to transfer from
		do
		{
			System.out.printf("Enter the number (1-2) of the account \n to transer from: ");
			fromAccount = scanner.nextInt()-1;
			if(fromAccount < 0 || fromAccount >= theUser.numAccounts())
			{
				System.out.println("Invalid account. Please try again.");
			}
		} while(fromAccount < 0 || fromAccount >= theUser.numAccounts());
			accountBalance = theUser.getAccountBalance(fromAccount);
		
		//get the account to transfer to
		do
		{
			System.out.printf("Enter the number (1-%d) of the account \n" +
								"to transer to: ", theUser.numAccounts());
			toAccount = scanner.nextInt()-1;
			if(fromAccount < 0 || toAccount >= theUser.numAccounts())
			{
				System.out.println("Invalid account. Please try again.");
			}
		} while(toAccount < 0 || toAccount >= theUser.numAccounts());
			accountBalance = theUser.getAccountBalance(toAccount);
		
		//get the amount to transfer
			//get the amount to transfer
			do {
				accountBalance = theUser.getAccountBalance(fromAccount);
				System.out.printf("Enter the amount to transfer (max $%.02f): $", accountBalance);
				amount = scanner.nextDouble();
				if(amount < 0)
				{
					System.out.println("Amount must be greater than zero.");
				}
				else if(amount > accountBalance)
				{
					System.out.printf("Amount must be greater than \n" +
										"balance of $%.02f\n", accountBalance);
				}
			} while(amount < 0 || amount > accountBalance);
		
		//finally, do transfer
		theUser.addAccountTransaction(fromAccount, -1*amount, String.format(
				"Transfer to account %s", theUser.getAccountBalance(toAccount)));
		theUser.addAccountTransaction(toAccount, amount, String.format(
				"Transfer to account %s", theUser.getAccountBalance(fromAccount)));
		System.out.println("Transfer complete!");
	
	}
	
	public static void withdrawlMoney(Users theUser, Scanner scanner)
	{
		//Initialize
				int fromAccount;
				double amount;
				double accountBalance;
				String transMemo;
				
				//Get the account to transfer from
				do
				{
					System.out.printf("Enter the number (1-%d) of the account \n" +
										"to withdrawl from: ", theUser.numAccounts());
					fromAccount = scanner.nextInt()-1;
					if(fromAccount < 0 || fromAccount >= theUser.numAccounts())
					{
						System.out.println("Invalid account. Please try again.");
					}
				} while(fromAccount < 0 || fromAccount >= theUser.numAccounts());
					accountBalance = theUser.getAccountBalance(fromAccount);
				
				//get the amount to transfer
				do {
					System.out.printf("Enter the amount to withdrawl (max $%.02f): $", accountBalance);
					amount = scanner.nextDouble();
					if(amount < 0)
					{
						System.out.println("Amount must be greater than zero.");
					}
					else if(amount > accountBalance)
					{
						System.out.printf("Amount must be less than \n" +
											"balance of $%.02f\n", accountBalance);
					}
				} while(amount < 0 || amount > accountBalance);
				
				//clear the rest of the previous input
				scanner.nextLine();
				//Give a trans memo
				System.out.print("Enter transaction memo: ");
				transMemo = scanner.nextLine();
				
				//actual withdrawl
				theUser.addAccountTransaction(fromAccount, -1*amount, transMemo);
				}
	
	public static void depositMoney(Users theUser, Scanner scanner)
	{
		//Initialize
		int toAccount;
		double amount;
		double accountBalance;
		String transMemo;
		
		//Get the account to transfer from
		do
		{
			System.out.printf("Enter the number (1-%d) of the account \n" +
								"to deposit in: ", theUser.numAccounts());
			toAccount = scanner.nextInt()-1;
			if(toAccount < 0 || toAccount >= theUser.numAccounts())
			{
				System.out.println("Invalid account. Please try again.");
			}
		} while(toAccount < 0 || toAccount >= theUser.numAccounts());
			accountBalance = theUser.getAccountBalance(toAccount);
		
		//get the amount to transfer
		do {
			System.out.printf("Enter the amount to deposit ($%.02f): $", accountBalance);
			amount = scanner.nextDouble();
			if(amount < 0)
			{
				System.out.println("Amount must be greater than zero.");
			}
		} while(amount < 0);
		
		//clear the rest of the previous input
		scanner.nextLine();
		
		//get a transaction memo
		System.out.print("Enter transaction memo: ");
		transMemo = scanner.nextLine();
		
		//actual deposit
		theUser.addAccountTransaction(toAccount, amount, transMemo);
	}
}
