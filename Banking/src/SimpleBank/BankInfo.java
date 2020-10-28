package SimpleBank;

import java.util.ArrayList;

public class BankInfo 
{
	private String name;
	
	private ArrayList<Users> users;
	
	private ArrayList<Accounts> accounts;
	
public void addAccount(Accounts newAccount)
{
	this.accounts.add(newAccount);
}
//-----------------------------------------------------------------------------------------------
public BankInfo(String name)
{
	//Creates a new instance of a bank object with empty lists of accounts and users 
	this.name = name;
	this.users = new ArrayList<Users>();
	this.accounts = new ArrayList<Accounts>();
}
//-----------------------------------------------------------------------------------------------
public Users addNewUser(String firstName, String lastName, String userName, String password)
{
	//Creates a new instance of a user object and adds it to the bank list
	Users newUser = new Users(firstName, lastName, userName, password, this);
	this.users.add(newUser);
	
	//Create a savings account for the user
	Accounts newAccount = new Accounts("Savings", newUser, this);
	//Add accounts to the acc_owner and the bank list
	newUser.addAccount(newAccount);
	this.addAccount(newAccount);
	
	return newUser;
}
//-----------------------------------------------------------------------------------------------
public Users authenticate(String userName, String password)
{
	// search through list of logins
	for(Users i : this.users)
	{
		//condition to check to see if the user login credentials are correct
		if(i.getUserName().compareTo(userName) == 0 && i.getPassword().compareTo(password) == 0)
		{
			return i;
		}
	}
	//If the user credentials are not valid
	return null;
}
//------------------------------------------------------------------------------------------------
public String getName()
{
	return this.name;
}
}
