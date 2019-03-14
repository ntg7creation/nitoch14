package nitoch;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import nitoch.sql.SQLConnection;

public class Start_DataBase {

	public static void createNewDatabase(String fileName) {

		String url = "jdbc:sqlite:data" + fileName;

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void connect() {
		Connection conn = null;
		try {
			// db parameters
			String url = "jdbc:sqlite:src\\data\\newdatabase.db";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		// createNewDatabase("test.db");
		// connect();
		SQLConnection temp = new SQLConnection();

		String sqlCommand = "";


		sqlCommand = "CREATE TABLE IF NOT EXISTS Users(" + "Name Varchar," + "FamilyName Varchar," + "ID Int,"
				+ "UserName Varchar Primary key," + "PassWord Varchar" + ");";
		temp.sendCommand(sqlCommand);

		sqlCommand = "delete from Users;";
		temp.sendCommand(sqlCommand);
		
		sqlCommand = "insert into Users(Name,FamilyName,ID,UserName,PassWord)" + "VALUES"
				+ "('Natai','Ella',208,'ntg','gg')," + "('adafds','vcx',2323,'master','mastercode'),"
				+ "('faew','bfggd',11,'ews','vcdd')," + "('fewq','dgt',543,'bgh','asdf'),"
				+ "('fdsafewv','f',543,'btrr','ujhb')," + "('cds','zxc',2345,'jhgfd','asdfg')" + ";";

		temp.sendCommand(sqlCommand);


	}

}
