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

import Database.GetSubjectname;
import Database.GetSuperUserDetail;
import static FXML_Controllers.GenerateFXMLController.varDate;
import static FXML_Controllers.GenerateFXMLController.varFTime;
import static FXML_Controllers.GenerateFXMLController.varMonth;
import static FXML_Controllers.GenerateFXMLController.varSem;
import static FXML_Controllers.GenerateFXMLController.varSubCode;
import static FXML_Controllers.GenerateFXMLController.varSubjectName;
import static FXML_Controllers.GenerateFXMLController.varTTime;
import static FXML_Controllers.GenerateFXMLController.varYear;
import static FXML_Controllers.MainFXMLController.cr;
import HashPassword.HashPassword;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import iText_Pdf_Generation.DocumentCreator;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Chiragasourabh
 */
public class GenerateFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private TextField subjectcode,semester,month,year;
    
    @FXML
    private JFXCheckBox checkBox;
    
    @FXML
    private PasswordField edSecretKey;
    
    @FXML
    private JFXButton proceedButton;
    
    
    @FXML
    private JFXTimePicker jfromTime,jtoTime;
    
    @FXML
    private JFXDatePicker jexmDate;
    
    @FXML
    private Text subjectcodetext,headerOne,headerTwo,Date,Time;
    
    private final String postGenerateFXML ="/FXML_Documnets/generate/postGenerateFXML.fxml";
    
    public static String varSem="",varMonth="",varYear="",varDate="",varFTime="",varTTime="",varSubCode="",varSubjectName=null;
    
    @FXML
    private void showSubjectCode(KeyEvent event)
    {
        varSubCode =subjectcode.getText().toUpperCase();
        subjectcodetext.setText(varSubCode);
    }
    
    @FXML
    private void showSemester(KeyEvent event)
    {
        varSem = semester.getText();
        headerOne.setText(varSem+" Semester B.E Degree Examination, "+varMonth+" "+varYear);
    }
    
    @FXML
    private void showMonth(KeyEvent event)
    {
        varMonth = month.getText();
        headerOne.setText(varSem+" Semester B.E Degree Examination, "+varMonth+" "+varYear);
    }
    
    @FXML
    private void showYear(KeyEvent event)
    {
        varYear = year.getText();
        headerOne.setText(varSem+" Semester B.E Degree Examination, "+varMonth+" "+varYear);
    }
    
    @FXML
    private void showDate()
    {
        
        varDate = jexmDate.getValue().toString();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Date.setText("Date : "+(jexmDate.getValue()).format(dt));
       
    }
    
    @FXML
    private void showFromTime()
    {
        DateTimeFormatter formattime = DateTimeFormatter.ofPattern("hh:mm a");
        varFTime = (jfromTime.getValue()).format(formattime);
        Time.setText("Time : "+varFTime+" - "+varTTime);
    }
    @FXML
    private void showToTime()
    {
        DateTimeFormatter formattime = DateTimeFormatter.ofPattern("hh:mm a");
        varTTime = (jtoTime.getValue()).format(formattime);
        Time.setText("Time : "+varFTime+" - "+varTTime);
    }
    
    @FXML
    private void showSubjectName(KeyEvent event) throws ClassNotFoundException
    {
        //varSubjectName = subjectcode.getText();
        varSubjectName = GetSubjectname.GetSubjectnameMethod(subjectcode.getText().toUpperCase());
        headerTwo.setText(varSubjectName);
    }
    
    @FXML
    private void showBothSubjectNameAndSubjectCode(KeyEvent event) throws ClassNotFoundException{
        showSubjectName(event);
        showSubjectCode(event);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //checkBox.selectedProperty().addListener(cg);
        //checkBox.setOnAction(eh);
        proceedButton.setDisable(true);
        jtoTime.setStyle("-fx-font:20px\"Serif\";");
        jfromTime.setStyle("-fx-font:20px\"Serif\";");
        jfromTime.setEditable(false);
        jtoTime.setEditable(false);
        jexmDate.setStyle("-fx-font:20px\"Serif\";");
        LocalDate ld = LocalDate.now();
        jexmDate.setValue(ld);
        
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        varDate = (jexmDate.getValue()).format(dt);
        Date.setText("Date : "+(jexmDate.getValue()).format(dt));
        
    } 
    
    @FXML
    private void generateMethod() throws ClassNotFoundException, IOException {
        if(currentDayCheck() && checkFieldEmpty() ){
            String secretKey = HashPassword.HashIt(edSecretKey.getText());
        Preferences loggedUserDetail = Preferences.userNodeForPackage(LoginFXMLController.class);
        String SecretKeyInDatabase = GetSuperUserDetail.GetSuperUserSecretKeyMethod(loggedUserDetail.get("username", "default"));
        if(secretKey.equals(SecretKeyInDatabase)){
            showFxml(postGenerateFXML);
            //DocumentCreator.writeUsingIText(varSubjectName,varSubCode,varSem,varMonth,varYear,varDate,varFTime,varTTime);
            myThread t= new myThread(); 
            t.start();
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
    private void showFxml(String s) throws IOException {
            removeFxml();
            try {
                // TODO
                AnchorPane pane = FXMLLoader.load(getClass().getResource(s));
                rootPane.getChildren().setAll(pane);
                System.out.println(s+" pane added");
                cr=s;
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    
    @FXML
    private void removeFxml() throws IOException {
       rootPane.getChildren().clear();
       System.out.println(cr+" pane removed");
       
    }
    
    
    @FXML
    private boolean checkFieldEmpty(){
        
        Alert alert = new Alert(Alert.AlertType.ERROR); 
        alert.setTitle("Error"); 
        alert.initStyle(StageStyle.DECORATED);
        
        alert.setResizable(false);
        
        //alert.close();
                
        if(subjectcode.getText().isEmpty())
        {
            alert.setContentText("Subject Code is missing");
            alert.show();
            return false;
        }
        
        else if(varSubjectName==null)
        {
            alert.setContentText("no subject with subject code :"+subjectcode.getText());
            alert.show();
            return false;
        }
        else if(semester.getText().isEmpty())
        {
            alert.setContentText("Semester is missing");
            alert.show();
            return false;
        }
        else if(month.getText().isEmpty())
        {
            alert.setContentText("Examination Month is missing");
            alert.show();
            return false;
        }
        else if(year.getText().isEmpty())
        {
            alert.setContentText("Examination Year is missing");
            alert.show();
            return false;
        }
        else if(Date.getText().isEmpty())
        {
            alert.setContentText("Exam Date is missing");
            alert.show();
            return false;
        }
        else if(varFTime.equals(""))
        {
            alert.setContentText("Exam Start Time is missing");
            alert.show();
            return false;
        }
        else if(varTTime.equals(""))
        {
            alert.setContentText("Exam End Time is missing");
            alert.show();
            return false;
        }
        else if(edSecretKey.getText().isEmpty())
        {
            alert.setContentText("Enter Confidential Key");
            alert.show();
            return false;
        }
        alert.close();
        return true;
    }
    
    @FXML
    private void ckeckBoxAction(ActionEvent event)
    {
       if(checkBox.isSelected())
        {
            proceedButton.setDisable(false);
        }
        else{
            proceedButton.setDisable(true);
        }
        
    }
    
    private boolean currentDayCheck(){
        
        Alert alert = new Alert(Alert.AlertType.ERROR); 
        alert.setTitle("Error"); 
        alert.initStyle(StageStyle.DECORATED);
        
        alert.setResizable(false);
        
        int sys_day,sys_month,sys_year;
        int e_day,e_month,e_year;
                
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        e_day = Integer.parseInt((jexmDate.getValue()).format(day));
        e_month = Integer.parseInt((jexmDate.getValue()).format(month));
        e_year = Integer.parseInt((jexmDate.getValue()).format(year));
        LocalDate ld = LocalDate.now();
        sys_day = Integer.parseInt(ld.format(day));
        sys_month = Integer.parseInt(ld.format(month));
        sys_year = Integer.parseInt(ld.format(year));
        
        if(e_day!=sys_day || e_month!=sys_month || e_year!=sys_year)
        {
            alert.setContentText("Sorry You Cannot Generate Question paper on this Day");
            alert.show();
            return false;
        }
    return true;
    }
    
    private boolean currentTimeCheck(){
        
        Alert alert = new Alert(Alert.AlertType.ERROR); 
        alert.setTitle("Error"); 
        alert.initStyle(StageStyle.DECORATED);
        
        alert.setResizable(false);
        
        int sys_hr,sys_min;
        String sys_a;
        int e_hr,e_min;
        String e_a;
                
        DateTimeFormatter hr = DateTimeFormatter.ofPattern("hh");
        DateTimeFormatter min = DateTimeFormatter.ofPattern("mm");
        //DateTimeFormatter a = DateTimeFormatter.ofPattern("a");
        e_hr = Integer.parseInt((jfromTime.getValue()).format(hr));
        e_min = Integer.parseInt((jfromTime.getValue()).format(min));
        //e_a = (jfromTime.getValue()).format(a);
        
        LocalDate ld = LocalDate.now();
        sys_hr = Integer.parseInt(ld.format(hr));
        sys_min = Integer.parseInt(ld.format(min));
        //sys_a = ld.format(a);
        
        if(e_min<30)
        {
            if(e_hr==01)
            {
                if(sys_hr!=12)
                {
                alert.setContentText("Sorry You Cannot Generate Question paper at this time");
                alert.show();
                return false;
                }
                /*else if (sys_a.equals(e_a))
                {
                    alert.setContentText("Sorry You Cannot Generate Question paper at this time");
                    alert.show();
                    return false;
                }*/
                else if(sys_min-e_min<30)
                {
                    alert.setContentText("Sorry You Cannot Generate Question paper at this time");
                    alert.show();
                    return false;
                }
            }
            else if(sys_min-e_min<30)
            {
                alert.setContentText("Sorry You Cannot Generate Question paper at this time");
                alert.show();
                return false;
            }
        }
        else if (sys_min>e_min)
        {
            alert.setContentText("Exam Already Started");
            alert.show();
            return false;  
        }
        else if(e_min-sys_min > 30) 
        {
            alert.setContentText("Exam Already Started");
            alert.show();
            return false;
        }
    return true;
    }
}

class myThread extends Thread{
    public void run()
    {
        try {
            DocumentCreator.writeUsingIText(varSubjectName,varSubCode,varSem,varMonth,varYear,varDate,varFTime,varTTime);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(myThread.class.getName()).log(Level.SEVERE, null, ex);
        }
         varSubjectName=null;
         varSubCode=null;
         varSem=null;
         varMonth=null;
         varYear=null;
         varDate="";
         varFTime="";
         varTTime="";
    }
    
    
}