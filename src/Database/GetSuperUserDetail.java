/*
 * Copyright (C) 2017 Chiragasourabh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Database;

import FXML_Controllers.SettingFXMLController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;

/**
 *
 * @author Chiragasourabh
 */
public class GetSuperUserDetail {
    
    public static String GetSuperUserPasswordMethod(String username) throws ClassNotFoundException {
            
		System.out.println("-------- PostgreSQL ------------");

		Connection connection = null;
		Statement stmt = null;
                String password=null;

		try {
                        connection=DatabaseConnection.getConn();
			
			
			stmt = connection.createStatement();
			
                    try (ResultSet rs = stmt.executeQuery("SELECT password FROM superusers where username = '"+username+"'")) {
                        while(rs.next()){
                            
                            password = rs.getString("password").trim();
                            
                            System.out.println("Password from database = "+password);
                            
                            System.out.println();
                            
                        }
                    }
			stmt.close();
			connection.close();
			
			
                        
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			

		}
                return password;
	}
    
    public static String GetSuperUserNameMethod(String username) throws ClassNotFoundException {
            
		System.out.println("-------- PostgreSQL ------------");

		Connection connection = null;
		Statement stmt = null;
                String name=null;

		try {
                        
                        connection=DatabaseConnection.getConn();
			
			
			stmt = connection.createStatement();
			
                    try (ResultSet rs = stmt.executeQuery("SELECT name FROM superusers where username = '"+username+"'")) {
                        while(rs.next()){
                            
                            name = rs.getString("name").trim();
                            
                            System.out.println("name from database = "+name);
                            
                            System.out.println();
                            
                        }
                    }
			stmt.close();
			connection.close();
			
			
                        
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			

		}
                return name;
	}
    
    public static String GetSuperUserPrivilegeMethod(String username) throws ClassNotFoundException {
            
		System.out.println("-------- PostgreSQL ------------");

		Connection connection = null;
		Statement stmt = null;
                String privilege=null;

		try {
                        
                       connection=DatabaseConnection.getConn();
			
			
			stmt = connection.createStatement();
			
                    try (ResultSet rs = stmt.executeQuery("SELECT privilege FROM superusers where username = '"+username+"'")) {
                        while(rs.next()){
                            
                            privilege = rs.getString("privilege").trim();
                            
                            System.out.println("privilege from database = "+privilege);
                            
                            System.out.println();
                            
                        }
                    }
			stmt.close();
			connection.close();
			
			
                        
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			

		}
                return privilege;
	}
    
    public static String GetSuperUserSecretKeyMethod(String username) throws ClassNotFoundException {
            
		System.out.println("-------- PostgreSQL ------------");

		Connection connection = null;
		Statement stmt = null;
                String secretKey=null;

		try {
                        
                        connection=DatabaseConnection.getConn();
			
			stmt = connection.createStatement();
			
                    try (ResultSet rs = stmt.executeQuery("SELECT secret_key FROM superusers where username = '"+username+"'")) {
                        while(rs.next()){
                            
                            secretKey = rs.getString("secret_key").trim();
                            
                            System.out.println("secret Key from database = "+secretKey);
                            
                            System.out.println();
                            
                        }
                    }
			stmt.close();
			connection.close();
			
			
                        
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			

		}
                return secretKey;
	}
    
}
