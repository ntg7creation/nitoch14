package nitoch.menu;

import java.util.NoSuchElementException;
import java.util.Scanner;

import entities.Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import nitoch.sql.SQLConnection;

public abstract class Menu {

	private static Manager manager = new Manager(); 
	private static SQLConnection sql = new SQLConnection(); 
	public abstract void run();

	public void mainMenu(){
		sql.connect();
		sql.creatMasterTable();
		sql.creatUserTable();

		Scanner myObj = new Scanner(System.in);
		System.out.println("Hello!\n For register enter 1.\n For login enter 2.");
//		System.out.println("Hello! \n For Master Menu enter 1.\n For User Menu enter 2.\n To register enter 3.");
		int i = 0;
		try {
			i = myObj.nextInt();
			if (i != 1 & i != 2 & i!= 3)
				i = 0;
		} catch (NoSuchElementException e) {
			System.out.println("please enter correct input");
			i = 0;
		}
		switch (i) {
			case 0:
				//go back to main menu
				mainMenu();
				break;
			case 1:
				register();
				MasterMenu Mm = new MasterMenu();
				Mm.run();
				break;
			case 2:
				System.out.println("Please enter UserName \n");
				String userName = myObj.nextLine();
				UserManu UM = new UserManu(userName);
				if(UM.UserName == null){
					mainMenu();
				}else{
					UM.run();
				}
				break;		
		}


	}

	public void register(){
		System.out.println("Enter 1 to register as a Master. \n Enter 2 to register as a User ");
		Scanner myObj = new Scanner(System.in);
		int reg = myObj.nextInt();
		System.out.println("Please enter first name, family name, ID number, UserName and Password \n Enter in this order with spaces in between each field");
		String user = myObj.nextLine();
		String [] info = user.split(" ");
		String name = info[0];
		String lname = info[1];
		int id = Integer.parseInt(info[2]);
		String userName =  info[3];
		String password = info[4];
		
		
		boolean sec = false;
		sec = manager.addUser(name, lname, id, userName, password);
		if(sec) {
			if(reg == 1) {
				manager.addMaster(info[3]);
			}
			System.out.println("registered successfully");
		}
		else {
		System.out.println("UserName is already in use please choose new name");
		register();
		}
	}

}
			
				
			
	
	


/*		SQLConnection sql = new SQLConnection();
String SQlCommand = "SELECT * from User where UserName = '" + info[3] + "';";
try (Connection conn = sql.connect(); Statement stmt = conn.createStatement();) {
	// output = stmt.executeQuery(SQlCommand);
	ResultSet rs = stmt.executeQuery(SQlCommand);
	if (!rs.next()) {
		String sqlCommand =
				"insert into User(Name,FamilyName,ID,UserName,PassWord)"
						+ "VALUES"
						+ info[0]+","
						+ info[1]+","
						+ Integer.parseInt(info[2])+","
						+ info[3]+","
						+ info[4]+","
						+ ";";
		sql.sendCommand(sqlCommand);
	}  */
