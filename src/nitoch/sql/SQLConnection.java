package nitoch.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {

	private static String url = "jdbc:sqlite:src\\data\\newdatabase.db";

	public ResultSet sendCommandwithReturn(String SQlCommand) {
		ResultSet output = null;
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement();) {
			// output = stmt.executeQuery(SQlCommand);
			ResultSet rs = stmt.executeQuery(SQlCommand);
			while (rs.next()) {
				System.out.println(rs.getString("Name") + "\t" + rs.getString("FamilyName") + "\t" + rs.getInt("ID"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return output;
	}
//test
	public void sendCommand(String SQlCommand) {
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.execute(SQlCommand);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
