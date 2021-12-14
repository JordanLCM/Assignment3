package lesson.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	private Connection MyConnect;
	
	public JDBCConnection(String dburl, String username, String password) throws SQLException, ClassNotFoundException {
	Class.forName("com.mysql.jdbc.Driver");
	this.MyConnect = DriverManager.getConnection(dburl, username, password);
}

	public Connection getConnection() {
		return this.MyConnect;
	}
	
	public void closConnection() throws SQLException {
		this.MyConnect.close();
	}

}
