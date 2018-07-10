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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chiragasourabh
 */
public class ManageFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane managePane;
    
    
    private final String addUserFXML ="/FXML_Documnets/manage/addUserFXML.fxml";
    private final String addModifySubjectFXML ="/FXML_Documnets/manage/addModifySubjectFXML.fxml";
    private final String assignSubjecttoUsersFXML ="/FXML_Documnets/manage/assignSubjectToUserFXML.fxml";
    private final String modifyRemoveQuestionsFXML ="/FXML_Documnets/manage/modifyRemoveQuestionsFXML.fxml";
    private final String deactivateReactivateUserFXML ="/FXML_Documnets/manage/deactivateReactivateUserFXML.fxml";
    private final String moreFXML ="/FXML_Documnets/manage/moreFXML.fxml";
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void showFxml(String s) throws IOException {
        
                removeFxml();
            try {
                // TODO
                AnchorPane pane = FXMLLoader.load(getClass().getResource(s));
                managePane.getChildren().setAll(pane);
                System.out.println(s+" pane added");
                MainFXMLController.cr="";
                
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    @FXML
    private void removeFxml() throws IOException {
       managePane.getChildren().clear();
       System.out.println(" pane removed");
       
    }
    @FXML
    private void showAddUser() throws IOException{
        showFxml(addUserFXML);
    }
    
    @FXML
    private void showAddModifySubject() throws IOException{
        showFxml(addModifySubjectFXML);
    }
    
    @FXML
    private void showAssignSubjectToUser() throws IOException{
        showFxml(assignSubjecttoUsersFXML);
    }
    
    @FXML
    private void showModifyRemoveQuestions() throws IOException{
        showFxml(modifyRemoveQuestionsFXML);
    }
    
    @FXML
    private void showDeactivateReactivate() throws IOException{
        showFxml(deactivateReactivateUserFXML);
    }
    
    
    @FXML
    private void showmore() throws IOException{
        showFxml(moreFXML);
    }
            
}
