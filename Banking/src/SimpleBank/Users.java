package SimpleBank;

import java.util.ArrayList;

public class Users 
{
	//Creates a listed first name of the account
	private String firstName;
	//Creates a listed last name of the account
	private String lastName;
	//Creates a username for the user
	private String userName;
	//Creates a password for the user
	private String password;
	
	//Allows for multiple accounts
	private ArrayList<Accounts> accounts;
	
	public Users(String firstName, String lastName, String userName, String password, BankInfo bank)
	{
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		
		this.accounts = new ArrayList<Accounts>();
		
		//New account message
		System.out.printf("New user: %s, %s", lastName, firstName);
	}
	
	public void addAccount(Accounts newAccount)
	{
		//Adds an account for the user
		this.accounts.add(newAccount);
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void printAccountSummary()
	{
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for(int i = 0; i < this.accounts.size(); i++)
		{
			System.out.printf("  %d)  %s\n", i+1,
			this.accounts.get(i).getSummary());
		}
	}
	
	public int numAccounts()
	{
		return this.accounts.size();
	}
	
	public void printAccountTransactionHistory(int aIndex)
	{
		this.accounts.get(aIndex).printTransactionHistory();
	}
	
	public double getAccountBalance(int aIndex)
	{
		return this.accounts.get(aIndex).getBalance();
	}
	public String getAccountUserName(int aIndex)
	{
		return this.accounts.get(aIndex).getUserName();
	}
	
	public String getAccountPassword(int aIndex)
	{
		return this.accounts.get(aIndex).getPassword();
	}
	
	public void addAccountTransaction(int aIndex, double amount, String transMemo)
	{
		this.accounts.get(aIndex).addTransaction(amount, transMemo);
	}

}
