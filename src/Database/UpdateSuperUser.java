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
public class UpdateSuperUser {
    

	public static void updatePassword(String pass) {

		

		Connection connection = null;
		Statement stmt = null;

		try {
                        Preferences appSettings = Preferences.userNodeForPackage(SettingFXMLController.class);
			connection=DatabaseConnection.getConn();
			
			System.out.println("Opened Database Successfully!");
			stmt = connection.createStatement();
			
                        String username = appSettings.get("username", "default");
			String sql = "update superusers set password = '"+pass+"' where username = '"+username+"' ;";
			
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
			
			System.out.println("Table created Successfully");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		}
	}

}
