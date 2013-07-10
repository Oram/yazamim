package yazamimDB;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnectionBackup implements Serializable {

	private static final long serialVersionUID = 1522775348243389875L;

	private static Connection connection = null;

	private static void initDBconnection() {
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/yazamimDB");
			connection = ds.getConnection();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}

	public static void closeConnection() {
		try {
			connection.close();
			connection = null;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connection == null) {
			initDBconnection();
		}
		return connection;
	}

}
