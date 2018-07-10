package iText_Pdf_Generation;

import static FXML_Controllers.GenerateFXMLController.varDate;
import static FXML_Controllers.GenerateFXMLController.varFTime;
import static FXML_Controllers.GenerateFXMLController.varMonth;
import static FXML_Controllers.GenerateFXMLController.varSem;
import static FXML_Controllers.GenerateFXMLController.varSubCode;
import static FXML_Controllers.GenerateFXMLController.varSubjectName;
import static FXML_Controllers.GenerateFXMLController.varTTime;
import static FXML_Controllers.GenerateFXMLController.varYear;
import FXML_Controllers.SettingFXMLController;
import FXML_Controllers.generate.PostGenerateFXMLController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.prefs.Preferences;

public class DocumentCreator {
	
	//private static final String FILE_NAME = "D:/pdfgeneration/itext45.pdf";

    //public static void main(String[] args) {
   //        writeUsingIText();
    //}

    public static void writeUsingIText(String subject_name,String subject_code,String sem,String examination_month,String examination_year,String exam_date,String from_time,String to_time) throws ClassNotFoundException {
    	Preferences appSettings = Preferences.userNodeForPackage(SettingFXMLController.class);
            String path = appSettings.get("filePath", "default");
            LocalDate ld = LocalDate.now();
            DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String date = ld.format(dt);
            String FILE_NAME = path+"/"+date+"   "+subject_code+".pdf";
        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));

            //open
            document.open();
            
            //Variables from DATABASE and user entry
            /*String subject_name = "Microprocessor and MicroController";
            String subject_code = "15CS52";
            String sem = "Fourth";
            String examination_month = "Jan/Feb";
            String examination_year = "2017/18";
            String exam_date = "15/12/2017";
            String from_time = "02.30 pm";
            String to_time = "05.30 pm";*/
            
            
            HeaderGeneration headergenerationObject = new HeaderGeneration();
            OneModuleFullQuestion OneModuleFullQuestionObject = new OneModuleFullQuestion();
            headergenerationObject.GenerateHeader(document, subject_code, sem, examination_month, examination_year, subject_name, exam_date, from_time, to_time);
            
            int QuestionNumber=1;
            double progress = 0;
            for(int i=1;i<=5;i++)
            {
            	//document.add(Chunk.NEWLINE);
            	OneModuleFullQuestionObject.OneModuleFullQuestionmethod(document, i, QuestionNumber,subject_code);
            	
            	QuestionNumber+=2;
                progress+=0.2;
                //PostGenerateFXMLController.pgrs.setProgress(0.1);
                //PostGenerateFXMLController x = new PostGenerateFXMLController();
                //x.SuccessIcon.setVisible(true);
                  
            }
            
           
        //close
        document.close();
        
        
        System.out.println(FILE_NAME);
        System.out.println("Done");

    } catch (FileNotFoundException | DocumentException e) {
        e.printStackTrace();
    }

}

    }
