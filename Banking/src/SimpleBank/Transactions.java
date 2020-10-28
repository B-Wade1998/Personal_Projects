package SimpleBank;

import java.util.Date;

public class Transactions 
{
	//Shows the transaction amount
	private double transAmount;
	//Shows the date/time when the transaction was made
	private Date time;
	//Shows what the transaction was for
	private String transMemo;
	//Traces transaction back to certain accounts
	private Accounts whichAccount;
	
	public Transactions(double transAmount, Accounts whichAccount)
	{
		this.transAmount = transAmount;
		this.whichAccount = whichAccount;
		this.time = new Date();
		this.transMemo = "";
	}
	
	public Transactions(double transAmount, String transMemo, Accounts whichAccount)
	{
		//Calls the 2 argument constructor first
		this(transAmount, whichAccount);
		
		//Sets the transMemo
		this.transMemo = transMemo;
	}
	
	public double getAmount()
	{
		return this.transAmount;
	}
	
	public String getSummary()
	{
		if(this.transAmount >= 0)
		{
			return String.format("%s : $%.02f : %s", this.time.toString(),
						this.transAmount, this.transMemo);
		}
		else
		{
			return String.format("%s : $(%.02f) : %s", this.time.toString(),
					-this.transAmount, this.transMemo);
		}
	}
}
