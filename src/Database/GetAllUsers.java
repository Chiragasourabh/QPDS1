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
public class GetAllUsers {
    
	public static ResultSet GetAllUserMethod() throws ClassNotFoundException {
            
		System.out.println("-------- PostgreSQL ------------");

		Connection connection = null;
		Statement stmt = null;
                String name=null;
                ResultSet s=null;

		try {
                        
                        connection=DatabaseConnection.getConn();
			
			
			stmt = connection.createStatement();
			
                        
                        //String sub_code="15CS31";
                        ResultSet rs = stmt.executeQuery("SELECT * from qpdsusers ;");
                        //s=rs;
			/*while(rs.next()){
				
				name = rs.getString("qpds_id");
				
				System.out.println("Qpds Id = "+name);
                                
                                name = rs.getString("name");
				
				System.out.println("NAME = "+name);
				
				System.out.println();
                                
			}*/
				
			return rs;
			//stmt.close();
			//connection.close();
                        
			
			
                        
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			

		}
                return s;
	}
        
        

}
