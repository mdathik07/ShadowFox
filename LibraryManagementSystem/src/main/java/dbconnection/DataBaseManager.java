package dbconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseManager {	
	private static final String url = "jdbc:mysql://localhost:3306/librarymanagement";
	private static final String username = "root";
	private static final String password = "";
	public static Connection getConnection() throws SQLException{
		try {
		    // Dynamically load the MySQL JDBC driver
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {
		    System.out.println("Driver not found: " + e.getMessage());
		}
		return DriverManager.getConnection(url,username, password);
	}
}
