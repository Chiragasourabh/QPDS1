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
package FXML_Controllers.dashboard;

import Database.GetAllUsers;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Chiragasourabh
 */
public class UsersFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane anchorPaneInScrollPane;
    
    @FXML
    private GridPane asdfg;
    
    @FXML
    private Node a;
    
    @FXML
    private Label n;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* asdfg.getChildren().removeAll();
        
        Text t = new Text();
        Text u = new Text();
        //t.setText("testing");
        //x.getChildren().add(t);
       // asdfg.add(x, 0, 0);
        
        
        try {

            ResultSet s = GetAllUsers.GetAllUserMethod();
            int i=0;
            while(s.next())
            {
                                HBox x = new HBox();
                                t.setText(null);
				t.setText(s.getString("qpds_id"));
                                u.setText(s.getString("name"));
                                //x=null;
                                x.getChildren().add(0,t);
                                //x.getChildren().add(1,u);
                               
				asdfg.add(x, i, i);
                                
                                
                                /*t.setText(s.getString("name"));
                                //x=null;
                                x.getChildren().add(1,t);
				//asdfg.add(x, 1, i);
                                
                                t.setText(s.getString("college"));
                                //x=null;
                                x.getChildren().add(2,t);
				//asdfg.add(x, 2, i);
                                
                                t.setText(s.getString("department"));
                                //x=null;
                                x.getChildren().add(3,t);
				//asdfg.add(x, 3, i);
                                
                                t.setText(s.getString("staff_id"));
                                //x=null;
                                x.getChildren().add(4,t);*/
				//asdfg.add(x, 4, i);
                                ///asdfg.addRow(i, x);
                               /* i++;
                                
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }    
    
}
