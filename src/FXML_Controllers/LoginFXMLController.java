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
import HashPassword.HashPassword;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Chiragasourabh
 */
public class LoginFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXButton SIGNINBUTTON;
    
    @FXML
    private MaterialDesignIconView closeButton;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private JFXTextField EditTextUsername;
    
    @FXML
    private JFXPasswordField EditTextPassword;
    private Text errorText;
    
    String userName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image im = new Image("images/back2.jpg");
        imageView.setImage(im);
        
        EditTextUsername.setText(null);
        EditTextPassword.setText(null);
    }    
    
    @FXML
    private void change() throws IOException{
        String name = EditTextUsername.getText();
        String password = EditTextPassword.getText();
        if("chi".equals(name)&&"123".equals(password))
            showFxml();
        else
          System.out.println("wrong credentials");  
            
    } 
    
    
    @FXML
    private void showFxml() throws IOException {
        try {
            // TODO
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXML_Documnets/MainFXMLDocument.fxml"));
            rootPane.getChildren().setAll(pane);
            System.out.println("pane added");
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void Minimize(MouseEvent event){
            Stage stage = (Stage)((MaterialDesignIconView)event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        }
    @FXML
    public void Close(MouseEvent event){
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    
    @FXML
    private void signInMethod() throws ClassNotFoundException, IOException
    {
        if(findBlank()){
        userName = EditTextUsername.getText().trim();
        try
        {
        String PasswordInDatabase = GetSuperUserDetail.GetSuperUserPasswordMethod(userName);
        String PasswordEntered = HashPassword.HashIt(EditTextPassword.getText());
        System.out.println("#Password from Database = "+PasswordInDatabase);
        System.out.println("#Password from Hashed = "+PasswordEntered);
        if(PasswordInDatabase.equals(PasswordEntered)||PasswordInDatabase==null)
        {
            
            String name = GetSuperUserDetail.GetSuperUserNameMethod(userName);
            String privilege = GetSuperUserDetail.GetSuperUserPrivilegeMethod(userName);
            Preferences loggedUserDetail = Preferences.userNodeForPackage(LoginFXMLController.class);
            loggedUserDetail.put("name", name);
            loggedUserDetail.put("privilege", privilege);
            loggedUserDetail.put("username", userName);
            System.out.println(loggedUserDetail.get("name", "default"));
            System.out.println(loggedUserDetail.get("privilege", "default"));
            showFxml();
        }
        else
        {
            System.out.println("failed");
            alert("Invalid Username or Password");
            //errorText.setText("Wrong Credentials");
        }
        }catch(Exception e)
        {
            
            alert("failed to Log In");
            
        }
        }
    }
    
    @FXML
    private boolean findBlank(){
        if(EditTextUsername.getText()==null){
            
            alert("Username Cannot be empty");
            
            return false;
        }
        else if(EditTextPassword.getText()==null){
            
            alert("Password Cannot be empty");
            
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
