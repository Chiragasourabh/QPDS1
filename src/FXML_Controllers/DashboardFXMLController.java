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
package FXML_Controllers;

import Database.countDashboard;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Chiragasourabh
 */
public class DashboardFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Text textSubjects,textQuestions,textUsers,textQpgenn;
    
    @FXML
    private AnchorPane dashboardPane;
    
    private final String subjectsFXML ="/FXML_Documnets/dashboard/subjectsFXML.fxml";
    private final String questionsFXML ="/FXML_Documnets/dashboard/questionsFXML.fxml";
    private final String usersFXML ="/FXML_Documnets/dashboard/usersFXML.fxml";
    private final String superUsersFXML ="/FXML_Documnets/dashboard/superUsersFXML.fxml";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            countDashboard.countDashboardMethod();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        textSubjects.setText(countDashboard.nOfSubject+"");
        textQuestions.setText(countDashboard.nOfQuestions+"");
        textUsers.setText(countDashboard.nOfUsers+"");
        textQpgenn.setText(countDashboard.nOfSuperUsers+"");
    }    
    
    @FXML
    private void showFxml(String s) throws IOException {
        
                removeFxml();
            try {
                // TODO
                AnchorPane pane = FXMLLoader.load(getClass().getResource(s));
                dashboardPane.getChildren().setAll(pane);
                System.out.println(s+" pane added");
                MainFXMLController.cr="";
                
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    @FXML
    private void removeFxml() throws IOException {
       dashboardPane.getChildren().clear();
       System.out.println(" pane removed");
       
    }
    
    @FXML
    private void showSubjects() throws IOException{
        showFxml(subjectsFXML);
    }
    
    @FXML
    private void showQuestions() throws IOException{
        showFxml(questionsFXML);
    }
    
    @FXML
    private void showUsers() throws IOException{
        showFxml(usersFXML);
    }
    
    @FXML
    private void showSuperUsers() throws IOException{
        showFxml(superUsersFXML);
    }
    
}
