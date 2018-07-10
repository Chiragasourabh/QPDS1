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
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;
/**
 *
 * @author Chiragasourabh
 */

public class PutSubjectintoDatabase {

	public static void PutSubjectintoDatabaseMethod(String sCode,String sName,int ch) {
            
		

		Connection connection = null;
		Statement stmt = null;

		try {
                        connection=DatabaseConnection.getConn();
			
			System.out.println("Opened Database Successfully!");
			stmt = connection.createStatement();
                        String sql=null;
			switch(ch)
                        {
                            case 1:  sql = "INSERT INTO SUBJECT (SUBJECT_CODE,SUBJECT_NAME)"+
                                            "VALUES ('"+sCode+"','"+sName+"');";
                                     stmt.executeUpdate(sql);
                                     break;
                            case 2:  sql = "UPDATE SUBJECT \n" +
                                                    "SET SUBJECT_NAME='"+sName+"'\n" +
                                                    "WHERE SUBJECT_CODE = '"+sCode+"';";
                                     stmt.executeUpdate(sql);
                                     break;
                            default : System.out.println("No Query Executed");
                        }
			

			
			stmt.close();
			connection.close();
			
			System.out.println("Subject added Successfully");
                        

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		
		}

	}

}
