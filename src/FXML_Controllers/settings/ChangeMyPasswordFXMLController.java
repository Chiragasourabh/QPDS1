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
package FXML_Controllers.settings;

import Database.GetSuperUserDetail;
import Database.UpdateSuperUser;
import FXML_Controllers.SettingFXMLController;
import HashPassword.HashPassword;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Chiragasourabh
 */
public class ChangeMyPasswordFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private PasswordField currentPassword,newPassword1,newPassword2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void changePassword() throws ClassNotFoundException{
          Alert alert = new Alert(Alert.AlertType.ERROR); 
                    alert.setTitle("Error"); 
                    alert.initStyle(StageStyle.DECORATED);
                    alert.setResizable(false);  
        if(checkFieldEmpty()){
            String p1= newPassword1.getText();
            String p2= newPassword2.getText();
            System.out.println(p1);
            System.out.println(p2);
            if(p1.equals(p2)){
                Preferences appSettings = Preferences.userNodeForPackage(SettingFXMLController.class);
                String PasswordInDatabase = GetSuperUserDetail.GetSuperUserPasswordMethod(appSettings.get("username", "default"));
                String PasswordEntered = HashPassword.HashIt(currentPassword.getText());
                
                if(PasswordInDatabase.equals(PasswordEntered)||PasswordInDatabase==null)
                {       
                    String newPassword = HashPassword.HashIt(newPassword2.getText());
                    UpdateSuperUser.updatePassword(newPassword);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION); 
                    alert1.setTitle("Success"); 
                    alert1.initStyle(StageStyle.DECORATED);
                    alert1.setResizable(false);
                    alert1.setContentText("Password Changed Successfully");
                    alert1.show();
                    
                }
                else
                {
                
                alert.setContentText("Wrong Current Password");
                alert.show();
                }
            }
            else
            {
                
                alert.setContentText("Password Do not Match");
                alert.show();
            }
        }
    }
    
    
    @FXML
    private boolean checkFieldEmpty(){
        
        Alert alert = new Alert(Alert.AlertType.ERROR); 
        alert.setTitle("Error"); 
        alert.initStyle(StageStyle.DECORATED);
        
        alert.setResizable(false);
        
        if(currentPassword.getText().isEmpty())
        {
            alert.setContentText("Current Password is missing");
            alert.show();
            return false;
        }
        else if(newPassword1.getText().isEmpty())
        {
            alert.setContentText("new Password is missing");
            alert.show();
            return false;
        }
        else if(newPassword2.getText().isEmpty())
        {
            alert.setContentText("re Enter New Password");
            alert.show();
            return false;
        }
        return true;
    }
}
