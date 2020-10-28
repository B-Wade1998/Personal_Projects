package com.Banking.com;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectingDB 
{
	public static void main(String[] args) 
	{
		Connection conection = null;
		
		try
		{
			Class.forName("or.posgres.Driver");
			connection = DriverManager.getConnection("my-database.cpdeyohaee0i.us-east-2.rds.amazonaws.com");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
