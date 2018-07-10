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
package FXML_Controllers.generate;

import FXML_Controllers.GenerateFXMLController;
import static FXML_Controllers.GenerateFXMLController.varDate;
import static FXML_Controllers.GenerateFXMLController.varFTime;
import static FXML_Controllers.GenerateFXMLController.varMonth;
import static FXML_Controllers.GenerateFXMLController.varSem;
import static FXML_Controllers.GenerateFXMLController.varSubCode;
import static FXML_Controllers.GenerateFXMLController.varSubjectName;
import static FXML_Controllers.GenerateFXMLController.varTTime;
import static FXML_Controllers.GenerateFXMLController.varYear;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.octicons.OctIconView;
import iText_Pdf_Generation.DocumentCreator;
import iText_Pdf_Generation.HeaderGeneration;
import iText_Pdf_Generation.OneModuleFullQuestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Chiragasourabh
 */
public class PostGenerateFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Text subjectcodetext,headerOne,headerTwo,Date,Time;
    
    @FXML 
    public JFXProgressBar progressBar;
    public ProgressBar pgrs;
    
    @FXML
    public OctIconView SuccessIcon;
    public JFXSpinner loadingSpinner;
    public Text percent;
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        subjectcodetext.setText(GenerateFXMLController.varSubCode);
        headerOne.setText(GenerateFXMLController.varSem+" Semester B.E Degree Examination, "+GenerateFXMLController.varMonth+" "+GenerateFXMLController.varYear);
        headerTwo.setText(GenerateFXMLController.varSubjectName);
        Date.setText("Date : "+GenerateFXMLController.varDate);
        Time.setText("Time : "+GenerateFXMLController.varFTime+" - "+GenerateFXMLController.varTTime);
        
        //progressBar.setProgress(0.0);
        SuccessIcon.setVisible(true);
        loadingSpinner.setVisible(false);
        progressBar.setProgress(1.0);
        percent.setText("100"+"%");
        
        
    }
  
}

