package yazamimDB;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection implements Serializable {

	private static final long serialVersionUID = 1522775348243389875L;

	public static Connection getConnection() {
		Connection connection = null;
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/yazamimDB");
			connection = ds.getConnection();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return connection;
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

}
