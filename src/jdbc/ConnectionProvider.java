package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	public static Connection getConnection() throws Exception{
		String url = "jdbc:apache:commons:dbcp:acob";
		return DriverManager.getConnection(url);
	}

}