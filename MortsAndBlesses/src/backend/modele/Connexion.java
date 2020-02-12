package backend.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {
	private static String host = "localhost";
	private static int port = 3306;
	private static String dbName = "mortsetblesses";
	private static String user = "root";
	private static String password = "";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String cc = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?serverTimezone=UTC";
		
		Properties props = new Properties();
		props.setProperty("user", user);
		props.setProperty("password", password);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(cc, props);
	}
}