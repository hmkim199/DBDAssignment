package assignment5;

import java.sql.*;

public class SelectQuery {
	
	public SelectQuery(String db_connection_url, String query_string) {
		/* Retrieve DB authentication information */
		DatabaseAuthInformation db_info = new DatabaseAuthInformation();
		db_info.parse_auth_info("auth/mysql.auth");
		
		/* Prepare the URL for DB connection */
		db_connection_url = String.format(db_connection_url, db_info.getHost(), db_info.getPort(), db_info.getDatabase_name());

		/* DB insertion process */
		try (Connection db_connection = DriverManager.getConnection(db_connection_url, db_info.getUsername(),
				db_info.getPassword()); Statement db_statement = db_connection.createStatement()) {
			
			/* Send query and get the result */
			ResultSet result_set = db_statement.executeQuery(query_string);
			while (result_set.next()) {
				System.out.println("노선명 : " + result_set.getString(1) + "\t요일: " + result_set.getString(2) + "    출발시간: " + result_set.getFloat(3) + "\t도착시간: " + result_set.getFloat(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
