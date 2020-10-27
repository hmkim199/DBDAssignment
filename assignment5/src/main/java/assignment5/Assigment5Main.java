package assignment5;

import java.util.Scanner;

public class Assigment5Main {

	public static void main(String[] args) {
		String db_connection_url = "jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		//table 만들기
		StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table route(")
                .append("route_name varchar(20),")
                .append("start_stop varchar(20),")
                .append("arrival_stop varchar(20),")
                .append("primary key (route_name)")
                .append(");").toString();
        CreateTable table = new CreateTable(db_connection_url, sql);
        
        sb = new StringBuilder();
        sql = sb.append("create table run_schedule(")
                .append("route_name varchar(20),")
                .append("day varchar(3),")
                .append("start_time numeric(4,2),")
                .append("arrival_time numeric(4,2),")
                .append("transfer_code varchar(3),")
                .append("primary key (route_name, day),")
                .append("foreign key (route_name) references route(route_name)")
                .append(");").toString();
        table = new CreateTable(db_connection_url, sql);
        
        sb = new StringBuilder();
        sql = sb.append("create table stop(")
                .append("stop_name varchar(20),")
                .append("address varchar(20),")
                .append("primary key (stop_name)")
                .append(");").toString();
        table = new CreateTable(db_connection_url, sql);
        
        sb = new StringBuilder();
        sql = sb.append("create table transfer(")
                .append("transfer_code varchar(3),")
                .append("stop_name varchar(20),")
                .append("arrival_time numeric(4,2),")
                .append("start_time numeric(4,2),")
                .append("primary key (transfer_code, stop_name, arrival_time),")
                .append("foreign key (stop_name) references stop(stop_name)")
                .append(");").toString();
        table = new CreateTable(db_connection_url, sql);
        
        //노선(route) insert
        String route_names[] = {"Gyeonggi_up", "Gyeonggi_down", "Gangwon_up", "Gangwon_down", "Chungcheong_up", "Chungcheong_down", "Gyeongsang_up", "Gyeongsang_down", "Jeolla_up", "Jeolla_down"};
        String start_stops[] = {"Yongin", "Seoul", "Seoul", "Sokcho", "Sejong", "Seoul", "Busan", "Seoul", "Jeonju", "Seoul"};
        String arrival_stops[] = {"Seoul", "Yongin", "Sokcho", "Seoul", "Seoul", "Sejong", "Seoul", "Busan", "Seoul", "Jeonju"};
        for (int i = 0; i < route_names.length; i++) {
			new InsertQuery(db_connection_url, route_names[i], start_stops[i], arrival_stops[i]);			
        }
        
        //운행 스케줄(run_schedule) insert
        String day = "MON";
        float start_times[] = {(float)9.0, (float)6.0, (float)9.0, (float)6.0, (float)9.0, (float)6.0, (float)9.0, (float)6.0, (float)9.0, (float)6.0};
        float arrival_times[] = {(float)20.0, (float)18.0, (float)20.0, (float)18.0, (float)20.0, (float)18.0, (float)20.0, (float)18.0, (float)20.0, (float)18.0};
        String transfer_codes[] = {"A", "A", "B", "B", "C", "C", "D", "D", "E", "E"};
        for (int i = 0; i < route_names.length; i++) {
			new InsertQuery(db_connection_url, route_names[i], day, start_times[i], arrival_times[i], transfer_codes[i]);			
        }
        
        //정류장(stop) insert
        String stop_names[] = {"Yongin", "Seoul", "Sokcho", "Sejong", "Busan", "Jeonju", "Pyeongtaek", "Anseong", "Gangneung", "Chuncheon", "Daejeon", "Cheonan", "Changwon", "Masan", "Yeosu", "Suncheon"};
        String addresses[] = {"in Gyeonggi", "in Seoul", "in Gangwon", "in Chungcheong", "in Gyeongsang", "in Jeolla", "in Gyeonggi", "in Gyeonggi", "in Gangwon", "in Gangwon", "in Chungcheong", "in Chungcheong", "in Gyeongsang", "in Gyeongsang", "in Jeolla","in Jeolla"};
        for (int i = 0; i < stop_names.length; i++) {
			new InsertQuery(db_connection_url, stop_names[i], addresses[i]);			
        }
        
        //경유(transfer) insert
        String stop_names_t[] = {"Pyeongtaek", "Anseong", "Gangneung", "Chuncheon", "Daejeon", "Cheonan", "Changwon", "Masan", "Yeosu", "Suncheon"};
        float arrival_t[] = {(float)14.0, (float)10.0, (float)15.0, (float)11.0, (float)13.0, (float)12.0, (float)16.0, (float)10.0, (float)12.0, (float)11.0};
        float start_time[] = {(float)14.20, (float)10.20, (float)15.20, (float)11.20, (float)13.20, (float)12.20, (float)16.20, (float)10.20, (float)12.20, (float)11.20};
        for (int i = 0; i < stop_names_t.length; i++) {
			new InsertQuery(db_connection_url, transfer_codes[i], stop_names_t[i], arrival_t[i], start_time[i]);			
        }
        System.out.println("튜플 삽입 완료.");
        
        //사용자의 input을 담는 table
        sb = new StringBuilder();
        sql = sb.append("create table input_table(")
                .append("getin_stop_name varchar(20),")
                .append("getoff_stop_name varchar(20),")
                .append("time numeric(4,2),")
                .append("primary key (getin_stop_name, getoff_stop_name, time)")
                .append(");").toString();
        table = new CreateTable(db_connection_url, sql);
        
		String query_string = "select route_name, day, start_time, arrival_time "
							+ "from run_schedule "
							+ "where (start_time >= all (select time from input_table)) and "
		
							+ "(route_name in((select route_name "
													+ "from route, transfer "
													+ "where route_name = run_schedule.route_name "
														+ "and transfer_code = run_schedule.transfer_code "
														+ "and(((start_stop = (select getin_stop_name from input_table)) "
																	+ "and (stop_name = (select getoff_stop_name from input_table)) "
																	+ "and run_schedule.start_time < arrival_time) "
																+ "or ((stop_name = (select getin_stop_name from input_table)) "
																	+ "and (arrival_stop = (select getoff_stop_name from input_table) "
																	+ "and start_time < run_schedule.arrival_time))) "
													+ "union "
													+ "(select route_name from route "
													+ "where (start_stop = (select getin_stop_name "
																		+ "from input_table)) "
															+ "and (arrival_stop = (select getoff_stop_name from input_table)))))"
									+ " or (transfer_code in (select A.transfer_code "
															+ "from transfer as A, transfer as B "
															+ "where A.transfer_code = B.transfer_code "
																+ "and A.stop_name <> B.stop_name "
																+ "and A.start_time < B.start_time "
																+ "and A.stop_name = (select getin_stop_name from input_table) "
																+ "and B.stop_name = (select getoff_stop_name from input_table))))";
		
		Scanner sc = new Scanner(System.in);
		System.out.println("승차정류장명:");
		String getin_stop_name = sc.nextLine();  
		System.out.println("하차정류장명:");
		String getoff_stop_name = sc.nextLine();  
		System.out.println("시간:");
		float time = Float.parseFloat(sc.nextLine());

		// input으로 받은 것들 input_table 테이블로 삽입
		new InsertQuery(db_connection_url, getin_stop_name, getoff_stop_name, time);
		
		System.out.println();
		// query_string을 통한 검색 진행
		new SelectQuery(db_connection_url, query_string);

	}
}
