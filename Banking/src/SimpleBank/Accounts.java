package SimpleBank;

import java.util.ArrayList;

public class Accounts 
{	
	//Creates names of the accounts
	private String name;
	//Creates an account owner/joined account
	private Users accOwner;
	//Lists all the transactions
	private ArrayList<Transactions> transactions;
	
	public Accounts(String name, Users accOwner, BankInfo bank)
	{
		//set account name and owner of the account
		this.name = name;
		this.accOwner = accOwner;
		
		//Initialize transactions to an empty ArrayList at first.
		this.transactions = new ArrayList<Transactions>();
	}
	
	public String getSummary()
	{
		//returns account's balance
		double balance = this.getBalance();
		
		//see if the account is negative in amount
		if(balance >=0)
		{
			// This formats a float with a 2 decimal precision
			return String.format("%s , $%.02f : %s", this.accOwner, balance, this.name);
		}
		else
		{
			return String.format("%s , ($%.02f) : %s", this.accOwner, balance, this.name);
		}
	}
	
	public double getBalance()
	{
		double balance = 0.0;
		for(Transactions t : this.transactions)
		{
			balance += t.getAmount();
		}
		return balance;
	}
	
	public void printTransactionHistory()
	{
		System.out.printf("\nTransaction history for account %s\n", this.accOwner);
		for(int t = this.transactions.size()-1; t >=0; t--)
		{
			System.out.println(this.transactions.get(t).getSummary());
		}
		System.out.println();
	}

	public String getUserName() 
	{
		return this.getUserName();
	}
	
	public String getPassword()
	{
		return this.getPassword();
	}
	
	public void addTransaction(double amount, String transMemo)
	{
		Transactions newTrans = new Transactions(amount, transMemo, this);
		this.transactions.add(newTrans);
	}
}
