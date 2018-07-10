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

import Database.GetSuperUserDetail;
import static FXML_Controllers.MainFXMLController.cr;
import HashPassword.HashPassword;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Chiragasourabh
 */
public class SettingFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField filePath,dBUrl,dBPort,dBName,dBUserName;
    
    @FXML
    private PasswordField dBPassword,edSecretKey;
    
    @FXML
    private Text textSecretKey;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Button browseButton,pathSaveButton,dBEditButton,dBSaveButton,dBCancelButton;
    
    @FXML
    private JFXButton changeMyPasswordButton,changeMySecretKeyButton,manageSuperUsersButton;
    
    private final String changeMyPasswordFXML ="/FXML_Documnets/settings/changeMyPasswordFXML.fxml";
    private final String changeMySecretKeyFXML ="/FXML_Documnets/settings/changeMySecretKeyFXML.fxml";
    private final String manageSuperUsersFXML ="/FXML_Documnets/settings/manageSuperUsersFXML.fxml";
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Preferences loggedUserDetail = Preferences.userNodeForPackage(LoginFXMLController.class);
        if(!"admin".equals(loggedUserDetail.get("privilege", "default"))){
                manageSuperUsersButton.setVisible(false);
                dBEditButton.setDisable(true);
                dBSaveButton.setDisable(true);
                changeMySecretKeyButton.setVisible(false);
                
            }
        
        Preferences appSettings = Preferences.userNodeForPackage(SettingFXMLController.class);
            filePath.setText(appSettings.get("filePath", "default"));
            dBUrl.setText(appSettings.get("dBUrl", "default"));
            dBPort.setText(appSettings.get("dBPort", "default"));
            dBName.setText(appSettings.get("dBName", "default"));
            dBUserName.setText(appSettings.get("dBUserName", "default"));
            dBPassword.setText(appSettings.get("dBPassword", "default"));
    }

    @FXML
    private void dbEditMethod(){
        dBEditButton.setVisible(false);
        dBSaveButton.setVisible(true);
        dBUrl.setDisable(false);
        dBPort.setDisable(false);
        dBName.setDisable(false);
        dBUserName.setDisable(false);
        dBPassword.setDisable(false);
        textSecretKey.setVisible(true);
        edSecretKey.setVisible(true);
        dBCancelButton.setVisible(true);
    }
    
    @FXML
    private void dbCancelMethod(){
        dBEditButton.setVisible(true);
        dBSaveButton.setVisible(false);
        dBUrl.setDisable(true);
        dBPort.setDisable(true);
        dBName.setDisable(true);
        dBUserName.setDisable(true);
        dBPassword.setDisable(true);
        textSecretKey.setVisible(false);
        edSecretKey.setVisible(false);
        dBCancelButton.setVisible(false);
        Preferences appSettings = Preferences.userNodeForPackage(SettingFXMLController.class);
            dBUrl.setText(appSettings.get("dBUrl", "default"));
            dBPort.setText(appSettings.get("dBPort", "default"));
            dBName.setText(appSettings.get("dBName", "default"));
            dBUserName.setText(appSettings.get("dBUserName", "default"));
            dBPassword.setText(appSettings.get("dBPassword", "default"));
    }
    
    @FXML
    private void browseMethod(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(null);
        if(selectedDirectory!=null){
            filePath.setText(selectedDirectory.getAbsolutePath());
        }
    }
    
    @FXML
    private void pathSaveMethod(){
        String path = filePath.getText();
        Preferences appSettings = Preferences.userNodeForPackage(SettingFXMLController.class);
            appSettings.put("filePath", path);
    }
    
    @FXML
    private void dBsaveMethod() throws ClassNotFoundException{
        if(checkFieldEmpty()==true){
        String secretKey = HashPassword.HashIt(edSecretKey.getText());
        Preferences loggedUserDetail = Preferences.userNodeForPackage(LoginFXMLController.class);
        String SecretKeyInDatabase = GetSuperUserDetail.GetSuperUserSecretKeyMethod(loggedUserDetail.get("username", "default"));
        if(secretKey.equals(SecretKeyInDatabase)){
            
            Preferences appSettings = Preferences.userNodeForPackage(SettingFXMLController.class);
            appSettings.put("dBUrl", dBUrl.getText().trim());
            appSettings.put("dBPort", dBPort.getText().trim());
            appSettings.put("dBName", dBName.getText().trim());
            appSettings.put("dBUserName", dBUserName.getText().trim());
            appSettings.put("dBPassword", dBPassword.getText().trim());
            appSettings.put("fullDbString", "jdbc:postgresql://"+dBUrl.getText().trim()+":"+dBPort.getText().trim()+"/"+dBName.getText().trim());
            
            
            dBEditButton.setVisible(true);
            dBSaveButton.setVisible(false);
            dBUrl.setDisable(true);
            dBPort.setDisable(true);
            dBName.setDisable(true);
            dBUserName.setDisable(true);
            dBPassword.setDisable(true);
            textSecretKey.setVisible(false);
            edSecretKey.setVisible(false);
            dBCancelButton.setVisible(false);

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR); 
        alert.setTitle("Error"); 
        alert.initStyle(StageStyle.DECORATED);
        
        alert.setResizable(false);
        alert.setContentText("Wrong Secret Key");
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
        
        if(dBUrl.getText().isEmpty())
        {
            alert.setContentText("URL is missing");
            alert.show();
            return false;
        }
        else if(dBPort.getText().isEmpty())
        {
            alert.setContentText("PORT missing");
            alert.show();
            return false;
        }
        else if(dBName.getText().isEmpty())
        {
            alert.setContentText("Database Name is missing");
            alert.show();
            return false;
        }
        else if(dBUserName.getText().isEmpty())
        {
            alert.setContentText("UserName is missing");
            alert.show();
            return false;
        }
        else if(dBPassword.getText().isEmpty())
        {
            alert.setContentText("Password is missing");
            alert.show();
            return false;
        }  
        return true;
    }
    
    
    @FXML
    private void showFxml(String s) throws IOException {
            removeFxml();
            try {
                // TODO
                AnchorPane pane = FXMLLoader.load(getClass().getResource(s));
                rootPane.getChildren().setAll(pane);
                System.out.println(s+" pane added");
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    @FXML
    private void removeFxml() throws IOException {
       rootPane.getChildren().clear();
       System.out.println(" pane removed");
       
    }
    
    
    @FXML
    private void changeMyPasswordMethod() throws IOException
    {
        showFxml(changeMyPasswordFXML);
    }
    
    @FXML
    private void changeMySecretKeyMethod() throws IOException
    {
        showFxml(changeMySecretKeyFXML);
    }
    
    @FXML
    private void manageSuperUsersMethod() throws IOException
    {
        showFxml(manageSuperUsersFXML);
    }
   
}
