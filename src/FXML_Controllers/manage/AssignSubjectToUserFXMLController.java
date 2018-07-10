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

import Database.AssignSubject;
import Database.GetQpdsUserName;
import Database.GetSubjectname;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Chiragasourabh
 */
public class AssignSubjectToUserFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextField edQpdsId,edSubjectCode;
    
    @FXML
    JFXButton buttonAssign ;
    
    @FXML
    Text errorText,qpdsIdInfo,subjectCodeInfo;
    
    private String sub_name,user_name;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void assignMethod(){
        if(checkFieldEmpty()==true)
        {
            if(user_name==null)
            {
                errorText.setText("no user is registered with ID : "+edQpdsId.getText());
            }
            else if(sub_name==null)
            {
                errorText.setText("no Subject is registered with Subject Code : "+edSubjectCode.getText());
            }
            else{
            try{
            AssignSubject.assignSubject(edQpdsId.getText().trim(),edSubjectCode.getText().toUpperCase().trim());
            edQpdsId.setText(null);
            edSubjectCode.setText(null);
            errorText.setText("Successfully Assigned");
            qpdsIdInfo.setText(null);
            subjectCodeInfo.setText(null);
            }
            catch(Exception e){
                errorText.setText("Failed to Assign");
            }
            }
        }
    }
    
    
    @FXML
    private boolean checkFieldEmpty(){
        if(edQpdsId.getText().isEmpty())
        {
            errorText.setText("QPDS Id is missing");
            return false;
        }
        else if(edSubjectCode.getText().isEmpty())
        {
            errorText.setText("Subject Code is missing");
            return false;
        }
        return true;
    }
    
    @FXML
    private void showSubjectCodeInfo(KeyEvent event) throws ClassNotFoundException
    {
        sub_name = GetSubjectname.GetSubjectnameMethod(edSubjectCode.getText().toUpperCase());
        subjectCodeInfo.setText("Subject Name : "+sub_name);
    }
    
    @FXML
    private void showQpdsIdInfo(KeyEvent event) throws ClassNotFoundException
    {
        user_name = GetQpdsUserName.GetQpdsUserNameMethod(edQpdsId.getText().toUpperCase());
        qpdsIdInfo.setText("User Name : "+user_name);
    }
}
