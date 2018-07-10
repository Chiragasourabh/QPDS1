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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Chiragasourabh
 */


public class CheckImagePresence {

	public static ResultSet checkimagePresence(String key) {

	
		Connection connection = null;
		Statement stmt = null;
                   ResultSet rs=null;
		try {

			connection=DatabaseConnection.getConn();
			
			stmt = connection.createStatement();
			
			//String ID ="002",NAM="chirag",CLG="svce",DEPT="CSE",SID="15CS03",PN="8553617237",EM="chiragasourabh@gmail.com",PAS="0000",ST="1";
			
			String sql = " select count(*) from images where key = '"+key+"'";
			
			rs = stmt.executeQuery(sql);
			
                        return rs;

		} catch (SQLException e) {
                    
                        Alert alert = new Alert(Alert.AlertType.ERROR); 
                        alert.setTitle("Error"); 
                        alert.initStyle(StageStyle.DECORATED);
                        alert.setResizable(false);
                        alert.setContentText("Failed to create user (QPDS ID already Exists)");
                        alert.show();
			e.printStackTrace();
                        return rs;
		}

	}

}
