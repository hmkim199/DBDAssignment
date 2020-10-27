package assignment5;

import java.sql.*;

public class CreateTable {
	public CreateTable(String db_connection_url, String sql) {
		/* Retrieve DB authentication information */
		DatabaseAuthInformation db_info = new DatabaseAuthInformation();
		db_info.parse_auth_info("auth/mysql.auth");
		
		/* Prepare the URL for DB connection */
		db_connection_url = String.format(db_connection_url, db_info.getHost(), db_info.getPort(), db_info.getDatabase_name());

		/* DB insertion process */
		try (Connection db_connection = DriverManager.getConnection(db_connection_url, db_info.getUsername(),
				db_info.getPassword()); Statement db_statement = db_connection.createStatement()) {
			
			db_statement.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}