/*
 * Copyright (C) 2018 Chiragasourabh
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
import java.util.prefs.Preferences;

/**
 *
 * @author Chiragasourabh
 */
public class DatabaseConnection {
    public static Connection getConn()
    {
        try {
	Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
        }
        Connection connection = null;
        try {
        Preferences appSettings = Preferences.userNodeForPackage(SettingFXMLController.class);
        /*connection = DriverManager.getConnection(appSettings.get("fullDbString", "default"), appSettings.get("dBUserName", "default"),
					appSettings.get("dBPassword", "default"));
        */
        connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "qpdsadmin",
					"1234");
        }
        catch(SQLException e)
        {
            
        }
        return connection;
    }
}
