package nitoch.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import nitoch.sql.SQLConnection;

public class MasterMenu extends Menu {

	@Override
	public void run() {
		Scanner MyScanner = new Scanner(System.in);
		System.out.println(
				"enter you requset:\n1. Get info on <user>\n2. Change into on <user>\n3. Add user\n4. remove user <user>");
		int action = -1;
		String name = "";
		while (action == -1) {
			String input = MyScanner.nextLine();
			try (Scanner temp = new Scanner(input)) {
				action = temp.nextInt();
				if (action != 3)
					name = temp.next();
			} catch (NoSuchElementException e) {
				System.out.println("ples enter correct input ex- 2 dani");
				action = -1;
			}
		}

		System.out.println(action);
		switch (action) {
		case 1:
			PrintUserInfo(name);
			break;
		case 2:
			ChangeUserInfo(name);
			break;
		case 3:
			AddUser();
			break;
		case 4:
			RemoveUser(name);
			break;
		default:
			break;
		}
	}

	private void PrintUserInfo(String user) {
		SQLConnection sql = new SQLConnection();
		String SQlCommand = "SELECT * from Users where UserName = '" + user + "';";
		ResultSet rs = sql.sendCommandwithReturn(SQlCommand);
//		try {
//
//			// loop through the result set
//			while (rs.next()) {
//				System.out.println(rs.getString(0) + "\t" + rs.getString(1) + "\t" + rs.getInt(2) + "\t"
//						+ rs.getString(3) + "\t" + rs.getString(4));
//			}
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
	}

	private void ChangeUserInfo(String user) {

	}

	private void AddUser() {

	}

	private void RemoveUser(String user) {

	}
}
