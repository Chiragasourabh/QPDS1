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
package FXML_Controllers.manage;

import Database.CreateUser;
import HashPassword.HashPassword;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Chiragasourabh
 */
public class AddUserFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    ChoiceBox internalOrExternal;
    
    @FXML
    AnchorPane paneId;
    
    @FXML
    MenuItem menuInternal,menuExternal;
    
    @FXML
    TextField qpdsId,edName,edCollege,edDepartment,edStaffId,edPhoneNo,edEmailId;
    
    @FXML
    JFXButton buttonSave ;
    
    @FXML
    Text errorText;
    
    String ststus ="0";
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //internalOrExternal.getItems().addAll(new MenuItem("Internal Faculty"),new MenuItem("External Faculty"));
        
        internalOrExternal.getItems().addAll( FXCollections.observableArrayList("Internal Faculty", "External Faculty"));  
        internalOrExternal.setStyle("-fx-font:25px\"Serif\";");
        internalOrExternal.setValue("hello");
        internalOrExternal.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> { 
            String selectedItem = (String) internalOrExternal.getValue();
            if("Internal Faculty".equals((String) internalOrExternal.getItems().get((Integer)newValue))){
                edCollege.setText("SVCE");
            }
            else if("External Faculty".equals((String) internalOrExternal.getItems().get((Integer)newValue))){
                edCollege.setText(null);    
                }
            
         }); 
    }    
    
    @FXML
    private void saveMethod(){
        if(checkFieldEmpty()==true)
        {
            try{
            if(CreateUser.addUser(qpdsId.getText(), edName.getText(), edCollege.getText(), edDepartment.getText(), edStaffId.getText(), edPhoneNo.getText(), edEmailId.getText(), HashPassword.HashIt(qpdsId.getText()), ststus))
            {
                //errorText.setText("Success");
                Alert alert = new Alert(Alert.AlertType.INFORMATION); 
                        alert.setTitle("Error"); 
                        alert.initStyle(StageStyle.DECORATED);
                        alert.setResizable(false);
                        alert.setContentText("User Created Successfully");
                        alert.show();
                qpdsId.setText("");
                edName.setText("");
                edCollege.setText("");
                edDepartment.setText("");
                edStaffId.setText("");
                edPhoneNo.setText("");
                edEmailId.setText("");
            }
            }
            catch(Exception e){
                alert("Error Adding User");
            }
        }
        
    }
    
    @FXML
    private boolean checkFieldEmpty(){
        if(qpdsId.getText().isEmpty())
        {
            alert("QPDS Id is missing");
            return false;
        }
        else if(edName.getText().trim().isEmpty())
        {
            alert("Name is missing");
            return false;
        }
        else if(edCollege.getText().trim().isEmpty())
        {
            alert("College Name is missing");
            return false;
        }
        else if(edDepartment.getText().trim().isEmpty())
        {
            alert("Department Name is missing");
            return false;
        }
        else if(edStaffId.getText().trim().isEmpty())
        {
            alert("Staff Id is missing");
            return false;
        }
        else if(edPhoneNo.getText().trim().isEmpty())
        {
            alert("Phone Number is missing");
            return false;
        }
        else if(edEmailId.getText().trim().isEmpty())
        {
            alert("Email is missing");
            return false;
        }
        
        return true;
    }
    

    private void alert(String s){
        Alert alert = new Alert(Alert.AlertType.ERROR); 
            alert.setTitle("Error"); 
            alert.initStyle(StageStyle.DECORATED);
            alert.setResizable(false);
            alert.setContentText(s);
            alert.show();
    }
}
    

