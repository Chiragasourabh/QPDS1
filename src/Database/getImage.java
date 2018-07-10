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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Chiragasourabh
 */
public class getImage {
    public static ResultSet getImageMethod(String Key) {
		
                ResultSet rs=null;

		try {

			Connection connection=DatabaseConnection.getConn();
			
			System.out.println("Opened Database Successfully!");
			
	
			Statement s = connection.createStatement();
			rs = s.executeQuery("select image from images where key = '"+Key+"'");
			/*InputStream imageStream;
			OutputStream out;
			while(rs.next())
			{
				imageStream =rs.getBinaryStream(1);
				out = new FileOutputStream(new File("D:/abcde.jpg"));
				int c =0;
				while((c = imageStream.read()) != -1){
				//for(InputStream i : imageStream.read())
						out.write(c);
				}
				
			}*/
                        
			return rs;
			
			
		} catch (SQLException e) {

			System.err.println(e);
                         return rs;
		}
               
	}
}
