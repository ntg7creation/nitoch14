package nitoch.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nitoch.structures.DataRows;

public class SQLConnection {

	private static String url = "jdbc:sqlite:src\\data\\newdatabase.db";

	//this funtion will return null dont use it
	public DataRows sendCommandwithReturn(String SQlCommand) {
		ResultSet output = null;
		DataRows drs=null; 
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement();) {
			// output = stmt.executeQuery(SQlCommand);
			ResultSet rs = stmt.executeQuery(SQlCommand);
			drs=new DataRows(rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return drs;
	}
//test
	public void sendCommand(String SQlCommand) {
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.execute(SQlCommand);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
