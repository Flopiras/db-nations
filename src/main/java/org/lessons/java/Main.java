package org.lessons.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
	
	private static final String url = "jdbc:mysql://localhost:3306/db_nations";
	private static final String user = "root";
	private static final String pws = "root";

	public static void main(String[] args) {
		
		milestone2();
	}
	
	public static void milestone2() {
		
		try (Connection con 
			      = DriverManager.getConnection(url, user, pws)) {  
			  
			  final String sql = "SELECT "
					  		   + "country_id AS 'id', "
					  		   + "countries.name AS 'country_name', "
					  		   + "continents.name AS 'continent_name', "
					  		   + "regions.name AS 'region_name' "
				  			   + "FROM `countries` "
				  			   + "JOIN regions "
				  			   + "ON countries.region_id = regions.region_id "
				  			   + "JOIN continents "
				  			   + "ON regions.continent_id = continents.continent_id "
				  			   + "ORDER BY countries.name"
				  			   + " ; ";
			  
			  try(PreparedStatement ps = con.prepareStatement(sql)){
			    try(ResultSet rs = ps.executeQuery()){
			    	
			    	while(rs.next()) {
			    		
			    		int id = rs.getInt(1);
			    		String country = rs.getString(2);
			    		String continent = rs.getString(3);
			    		String region = rs.getString(4);
			    		
			    		System.out.println("[" + id + "] " + country + " - " + continent + " - " + region);
			    	}
			    }
			  }
			} catch (Exception e) {
				
				System.out.println("Error in db: " + e.getMessage());
			}
	}
}
