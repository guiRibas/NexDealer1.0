package ModelDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {

	
	private static String locate = "jdbc:mysql://localhost/nexDealer";
	private static String user = "root";
	private static String password = "Cabrit0"; 
	
	private static ConnectionDAO connectionDAO;
	
	public ConnectionDAO() {
	}
	
	public static ConnectionDAO getInstance(){
		if (connectionDAO == null) {
			connectionDAO = new ConnectionDAO();
		}
		return connectionDAO;
	}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(locate, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
