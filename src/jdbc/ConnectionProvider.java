package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	public static Connection getConnection() throws Exception{
		//url 네 db서버 주소
		String url = "";
		return DriverManager.getConnection(url);
	}
}
