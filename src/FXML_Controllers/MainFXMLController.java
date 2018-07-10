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

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Chiragasourabh
 */
public class MainFXMLController implements Initializable {
    
    @FXML
    private Button MinimizeButton;
    
    @FXML
    private MaterialDesignIconView closeButton;
    
    @FXML
    private AnchorPane mainPagePane,rootPane;
    
    @FXML
    private ImageView svceLogo;
    
    @FXML
    private JFXButton homeButton,dashboardButton,overviewButton,manageButton,generateButton,settingButton,helpButton,aboutButton;
    
    private final String homeFXML ="/FXML_Documnets/HomeFXML.fxml";
    private final String dashboardFXML ="/FXML_Documnets/dashboardFXML.fxml";
    private final String ovrviewFXML ="/FXML_Documnets/ovrviewFXML.fxml";
    private final String manageFXML ="/FXML_Documnets/manageFXML.fxml";
    private final String generateFXML ="/FXML_Documnets/generateFXML.fxml";
    private final String settingFXML ="/FXML_Documnets/settingFXML.fxml";
    private final String helpFXML ="/FXML_Documnets/helpFXML.fxml";
    private final String aboutFXML ="/FXML_Documnets/aboutFXML.fxml";
    private final String loginFXML ="/FXML_Documnets/LoginFXML.fxml";

    /**
     *
     */
    public static String cr="";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        try {
            
            Preferences loggedUserDetail = Preferences.userNodeForPackage(LoginFXMLController.class);
            if(!"admin".equals(loggedUserDetail.get("privilege", "default"))){
                generateButton.setDisable(true);
            }
            
            // TODO

            showHome();
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
    private void Logout(MouseEvent event) throws IOException{
            Preferences loggedUserDetail = Preferences.userNodeForPackage(LoginFXMLController.class);
            loggedUserDetail.remove("name");
            loggedUserDetail.remove("privilege");
            rootPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(loginFXML));
            rootPane.getChildren().setAll(pane);
        }
    
    @FXML
    private void showFxml(String s) throws IOException {
        if(cr.equals(s))
        {}
        else{
            removeFxml();
            try {
                // TODO
                AnchorPane pane = FXMLLoader.load(getClass().getResource(s));
                mainPagePane.getChildren().setAll(pane);
                System.out.println(s+" pane added");
                cr=s;
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void removeFxml() throws IOException {
       mainPagePane.getChildren().clear();
       System.out.println(cr+" pane removed");
       
    }
    
    @FXML
    private void showHome() throws IOException{
        //removeFxml();
        showFxml(homeFXML);
    } 
    
    @FXML
    private void showDashboard() throws IOException{
        //removeFxml();
        showFxml(dashboardFXML);
    }
    
    @FXML
    private void showOvrview() throws IOException{
        //removeFxml();
        showFxml(ovrviewFXML);
    }
    
    @FXML
    private void showManage() throws IOException{
        //removeFxml();
        showFxml(manageFXML);
    }
    
    @FXML
    private void showGenerate() throws IOException{
        //removeFxml();
        showFxml(generateFXML);
    }
    
    @FXML
    private void showSetting() throws IOException{
        //removeFxml();
        showFxml(settingFXML);
    }
    
    @FXML
    private void showHelp() throws IOException{
        //removeFxml();
        showFxml(helpFXML);
    }
    
    @FXML
    private void showAbout() throws IOException{
        //removeFxml();
        showFxml(aboutFXML);
    }
    
        
}
