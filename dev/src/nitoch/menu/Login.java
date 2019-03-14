package nitoch.menu;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

	public static void main(String[] args) {
		// test
		Menu myMenu = null;
		Scanner input1 = new Scanner(System.in);
		while (myMenu == null)
			try {

				System.out.println("Enter Username : ");
				String username = input1.nextLine();
				System.out.println("Enter Password : ");
				String password = input1.nextLine();

				boolean isMaster = false;
				try (Connection con = DriverManager.getConnection("jdbc:sqlite:src\\data\\newdatabase.db")) {
					String sql = "Select * from Users where username=? and password=?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, username);
					pst.setString(2, password);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
						if (username == "master") {
							System.out.println("you have successfully connected as a master");
							myMenu = new MasterMenu(username);
							isMaster = true;
						}
						if (!isMaster) {
							System.out.println("you have successfully connected");
							myMenu = new UserMenu(username);
						}
					} else {
						System.out.println("Password or username are incorrect");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		myMenu.run();
	}

}
