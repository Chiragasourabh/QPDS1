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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;

/**
 *
 * @author Chiragasourabh
 */
public class AssignSubject {
    public static void assignSubject(String qpdsId,String subjectCode) {


		Connection connection = null;
		Statement stmt = null;

		try {

			/*connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "qappadmin",
					"1234");*/
                        connection=DatabaseConnection.getConn();
			
			stmt = connection.createStatement();
			
			//String ID ="002",NAM="chirag",CLG="svce",DEPT="CSE",SID="15CS03",PN="8553617237",EM="chiragasourabh@gmail.com",PAS="0000",ST="1";
			
			//String sql = "INSERT INTO QPDSUSERS (QPDS_ID,NAME,COLLEGE,DEPARTMENT,STAFF_ID,PHONE_NO,EMAIL_ID,PASSWORD,STATUS)"
			//		+"VALUES ('"+ID+"','"+NAM+"','"+CLG+"','"+DEPT+"','"+SID+"','"+PN+"','"+EM+"','"+PAS+"','"+ST+"');";
			String sql = "INSERT INTO subject_assignments (QPDS_ID,SUBJECT_CODE)"
					 +"VALUES ('"+qpdsId+"','"+subjectCode+"');";
			
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
			
			System.out.println("User created Successfully");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

	}

    
}
