package assignment5;

import java.sql.*;


public class InsertQuery {
	
	//route 테이블에 삽입하는 생성자
	public InsertQuery(String db_connection_url, String route_name, String start_stop, String arrival_stop) {
		/* Retrieve DB authentication information */
		DatabaseAuthInformation db_info = new DatabaseAuthInformation();
		db_info.parse_auth_info("auth/mysql.auth");
		/* Prepare the URL for DB connection */
		db_connection_url = String.format(
				"jdbc:mysql://%s:%s/%s?useUnicode=true & useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				db_info.getHost(), db_info.getPort(), db_info.getDatabase_name());
		/* Prepare the query statement */
		String query_string = "insert into route (route_name, start_stop, arrival_stop) VALUES (?, ?, ?)";
		/* DB insertion process */
		try (Connection db_connection = DriverManager.getConnection(db_connection_url, db_info.getUsername(),
				db_info.getPassword()); PreparedStatement db_statement = db_connection.prepareStatement(query_string)) {
			/* Set the query statement */
			db_statement.setString(1, route_name);
			db_statement.setString(2, start_stop);
			db_statement.setString(3, arrival_stop);

			/* Send query and get the result */
			int result = db_statement.executeUpdate();
			System.out.println("Updated query: " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//run_schedule 테이블에 삽입하는 생성자
	public InsertQuery(String db_connection_url, String route_name, String day, float start_time, float arrival_time, String transfer_code) {
		/* Retrieve DB authentication information */
		DatabaseAuthInformation db_info = new DatabaseAuthInformation();
		db_info.parse_auth_info("auth/mysql.auth");
		/* Prepare the URL for DB connection */
		db_connection_url = String.format(
				"jdbc:mysql://%s:%s/%s?useUnicode=true & useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				db_info.getHost(), db_info.getPort(), db_info.getDatabase_name());
		/* Prepare the query statement */
		String query_string = "insert into run_schedule (route_name, day, start_time, arrival_time, transfer_code) VALUES (?, ?, ?, ?, ?)";
		/* DB insertion process */
		try (Connection db_connection = DriverManager.getConnection(db_connection_url, db_info.getUsername(),
				db_info.getPassword()); PreparedStatement db_statement = db_connection.prepareStatement(query_string)) {
			/* Set the query statement */
			db_statement.setString(1, route_name);
			db_statement.setString(2, day);
			db_statement.setFloat(3, start_time);
			db_statement.setFloat(4, arrival_time);
			db_statement.setString(5, transfer_code);
			
			/* Send query and get the result */
			int result = db_statement.executeUpdate();
			System.out.println("Updated query: " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//stop 테이블에 삽입하는 생성자
	public InsertQuery(String db_connection_url, String stop_name, String address) {
		/* Retrieve DB authentication information */
		DatabaseAuthInformation db_info = new DatabaseAuthInformation();
		db_info.parse_auth_info("auth/mysql.auth");
		/* Prepare the URL for DB connection */
		db_connection_url = String.format(
				"jdbc:mysql://%s:%s/%s?useUnicode=true & useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				db_info.getHost(), db_info.getPort(), db_info.getDatabase_name());
		/* Prepare the query statement */
		String query_string = "insert into stop (stop_name, address) VALUES (?, ?)";
		/* DB insertion process */
		try (Connection db_connection = DriverManager.getConnection(db_connection_url, db_info.getUsername(),
				db_info.getPassword()); PreparedStatement db_statement = db_connection.prepareStatement(query_string)) {
			/* Set the query statement */
			db_statement.setString(1, stop_name);
			db_statement.setString(2, address);

			/* Send query and get the result */
			int result = db_statement.executeUpdate();
			System.out.println("Updated query: " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//transfer 테이블에 삽입하는 생성자
	public InsertQuery(String db_connection_url, String transfer_code, String stop_name, float arrival_time, float start_time) {
		/* Retrieve DB authentication information */
		DatabaseAuthInformation db_info = new DatabaseAuthInformation();
		db_info.parse_auth_info("auth/mysql.auth");
		/* Prepare the URL for DB connection */
		db_connection_url = String.format(
				"jdbc:mysql://%s:%s/%s?useUnicode=true & useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				db_info.getHost(), db_info.getPort(), db_info.getDatabase_name());
		/* Prepare the query statement */
		String query_string = "insert into transfer (transfer_code, stop_name, arrival_time, start_time) VALUES (?, ?, ?, ?)";
		/* DB insertion process */
		try (Connection db_connection = DriverManager.getConnection(db_connection_url, db_info.getUsername(),
				db_info.getPassword()); PreparedStatement db_statement = db_connection.prepareStatement(query_string)) {
			/* Set the query statement */
			db_statement.setString(1, transfer_code);
			db_statement.setString(2, stop_name);
			db_statement.setFloat(3, arrival_time);
			db_statement.setFloat(4, start_time);
			
			/* Send query and get the result */
			int result = db_statement.executeUpdate();
			System.out.println("Updated query: " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	//input_table 테이블에 삽입하는 생성자
	public InsertQuery(String db_connection_url, String getin_stop_name, String getoff_stop_name, float time) {
		/* Retrieve DB authentication information */
		DatabaseAuthInformation db_info = new DatabaseAuthInformation();
		db_info.parse_auth_info("auth/mysql.auth");
		/* Prepare the URL for DB connection */
		db_connection_url = String.format(
				"jdbc:mysql://%s:%s/%s?useUnicode=true & useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				db_info.getHost(), db_info.getPort(), db_info.getDatabase_name());
		/* Prepare the query statement */
		String query_string = "insert into input_table (getin_stop_name, getoff_stop_name, time) VALUES (?, ?, ?)";
		/* DB insertion process */
		try (Connection db_connection = DriverManager.getConnection(db_connection_url, db_info.getUsername(),
				db_info.getPassword()); PreparedStatement db_statement = db_connection.prepareStatement(query_string)) {
			/* Set the query statement */
			db_statement.setString(1, getin_stop_name);
			db_statement.setString(2, getoff_stop_name);
			db_statement.setFloat(3, time);
			
			/* Send query and get the result */
			int result = db_statement.executeUpdate();
			System.out.println("Updated query: " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}	}
}
