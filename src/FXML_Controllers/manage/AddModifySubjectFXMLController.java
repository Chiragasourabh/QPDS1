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

import Database.GetSubjectname;
import Database.PutSubjectintoDatabase;
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
public class AddModifySubjectFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField edSubjectCode,edSubjectName;
    
    @FXML
    private Text errotText;
    
    @FXML
    private JFXButton buttonAdd,buttonUpdate;
    
    private String s;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }   
    
    @FXML
    private void showSubjectName(KeyEvent event) throws ClassNotFoundException
    {
        //varSubjectName = subjectcode.getText();
        
        
        s = GetSubjectname.GetSubjectnameMethod(edSubjectCode.getText().toUpperCase());
        if(s!=null)
        {
            edSubjectName.setText(s);
            buttonUpdate.setDisable(false);
            buttonAdd.setDisable(true);
            errotText.setText("* Subject with ( Subject code "+edSubjectCode.getText().toUpperCase()+" ) already exists in database \n" +
                              "  with ( Subject Name: "+s.toUpperCase()+" ) \n" +
                              "  You can modify the subject name by Entering the new Name \n" +
                              "  in Subject name field and then click UPDATE");
        }
        else
        {
            edSubjectName.setText(null);
            buttonUpdate.setDisable(true);
            buttonAdd.setDisable(false);
            errotText.setText(null);
        }
        
    }
    
    
    	@FXML
        private void addMethod() throws ClassNotFoundException{
            if(null!=edSubjectCode.getText() && null!=edSubjectName.getText()){
            PutSubjectintoDatabase.PutSubjectintoDatabaseMethod(edSubjectCode.getText().toUpperCase().trim(),edSubjectName.getText().trim(),1);
            edSubjectName.setText(null);
            edSubjectCode.setText(null);
            errotText.setText(null);
            }
        }
        
        @FXML
        private void updateMethod() throws ClassNotFoundException{
            if(null!=edSubjectCode.getText() && null!=edSubjectName.getText()){
            PutSubjectintoDatabase.PutSubjectintoDatabaseMethod(edSubjectCode.getText().toUpperCase(),edSubjectName.getText(),2);
            edSubjectName.setText(null);
            edSubjectCode.setText(null);
            errotText.setText(null);
            buttonUpdate.setDisable(true);
            buttonAdd.setDisable(false);
            }
        }
    
}
