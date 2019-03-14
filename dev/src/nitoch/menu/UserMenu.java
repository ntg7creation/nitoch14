package nitoch.menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Scanner;

import nitoch.sql.SQLConnection;

public class UserMenu extends Menu {

	String UserName = null;

	public UserMenu(String userName) {
		UserName = userName;
	}

	@Override
	public void run() {
		Scanner MyScanner = new Scanner(System.in);
		while (true) {
			System.out.println(
					"enter you requset:\n1. Get my info \n2. Update name <Name>\n3. Update FamilyName <FamilyName>\n4. Update ID <ID>\n5. Update PassWord <PassWord>\n6. Quit");
			int action = -1;
			String newInfo = "";
			while (action == -1) {
				String input = MyScanner.nextLine();
				try (Scanner temp = new Scanner(input)) {
					action = temp.nextInt();
					if (action != 1 && action != 6)
						newInfo = temp.next();
				} catch (NoSuchElementException e) {
					System.out.println("ples enter correct input ex- 2 dani");
					action = -1;
				}
			}

			switch (action) {
			case 1:
				PrintUserInfo();
				break;
			case 2:
				ChangeName(newInfo);
				break;
			case 3:
				ChangeUserFamilyName(newInfo);
				break;
			case 4:
				ChangeId(newInfo);
				break;
			case 5:
				ChangePassword(newInfo);
				break;
			case 6:
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}

	private void PrintUserInfo() {
		SQLConnection sql = new SQLConnection();
		String SQlCommand = "SELECT * from Users where UserName = '" + UserName + "';";

		try (Connection conn = sql.connect(); Statement stmt = conn.createStatement();) {
			// output = stmt.executeQuery(SQlCommand);
			ResultSet rs = stmt.executeQuery(SQlCommand);
			while (rs.next()) {
				System.out.println(rs.getString("Name") + "\t" + rs.getString("FamilyName") + "\t" + rs.getInt("ID")
						+ "\t" + rs.getString("UserName") + "\t" + rs.getString("PassWord"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void ChangeName(String NewName) {
		SQLConnection sql = new SQLConnection();
		String SqlCommand = "update users set Name = '" + NewName + "' where UserName ='" + UserName + "';";
		sql.sendCommand(SqlCommand);
		System.out.println(UserName+" you have successfully changed your name to "+NewName);
		UserName = NewName;
	}

	private void ChangeUserFamilyName(String NewUserName) {
		SQLConnection sql = new SQLConnection();
		String SqlCommand = "update users set FamilyName = '" + NewUserName + "' where UserName ='" + UserName + "';";
		sql.sendCommand(SqlCommand);
		System.out.println(UserName+" you have successfully changed your  family name to "+NewUserName);
		
	}


	private void ChangeId(String ID) {
		SQLConnection sql = new SQLConnection();
		int id = Integer.parseInt(ID);

		String SqlCommand = "update users set ID = '" + id + "' where UserName ='" + UserName + "';";
		sql.sendCommand(SqlCommand);
		System.out.println(UserName+" you have successfully changed your  ID to "+ID);
	}

	private void ChangePassword(String NewPassword) {
		SQLConnection sql = new SQLConnection();
		String SqlCommand = "update users set PassWord = '" + NewPassword + "' where UserName ='" + UserName + "';";
		sql.sendCommand(SqlCommand);
		System.out.println(UserName+" you have successfully changed your password to "+NewPassword);
	}
}
