package iText_Pdf_Generation;


import Database.GetQuestion;
import Database.GetQuestionKey;
import com.itextpdf.text.BadElementException;
import java.io.FileNotFoundException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OneSetOfQuestions {	
	int mark = 0;
public void OneSetOfQuestionsMethod(PdfPTable parentTable,List Keylist,int ModuleNumber, String subject_code) throws FileNotFoundException, ClassNotFoundException, BadElementException, IOException{
        try {
            
            PdfPTable alphaQuestionSeriesQuestionAndMarkTable =new PdfPTable(3);
            alphaQuestionSeriesQuestionAndMarkTable.setWidths(new float[]{4,83,13});
            alphaQuestionSeriesQuestionAndMarkTable.setHorizontalAlignment(0);
            alphaQuestionSeriesQuestionAndMarkTable.setTotalWidth(500);
            alphaQuestionSeriesQuestionAndMarkTable.setLockedWidth(true);
            alphaQuestionSeriesQuestionAndMarkTable.getDefaultCell().setBorder(0);
            
            int qNO=0;
            int temp_mark;
            while(mark!=16)
            {	
                
            	temp_mark = 0;
                
                temp_mark = nextMark.getNextMark(mark);
                String temp_Key;
                do
                {
                    temp_Key = GetQuestionKey.GetQuestionKeyMethod(temp_mark,ModuleNumber,subject_code);
                }while(Keylist.contains(temp_Key));
                Keylist.add(temp_Key);
                String y = GetQuestion.GetQuestionMethod(temp_Key);
                
                
                OneQuestion.oneQuestionMethod(alphaQuestionSeriesQuestionAndMarkTable,temp_mark,qNO,y,temp_Key);
            	System.out.println(temp_mark);
                mark=mark+temp_mark;
                qNO++;
            }            
            parentTable.addCell(alphaQuestionSeriesQuestionAndMarkTable);
            System.out.println("Done");
        } catch (DocumentException e) {
            	e.printStackTrace();
        	} catch (SQLException ex) {
                Logger.getLogger(OneSetOfQuestions.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
	String checkListContains(List l, String a,int mark,int ModuleNumber,String subject_code) throws ClassNotFoundException
        {
            if(l.contains(a))
            {
                a = GetQuestionKey.GetQuestionKeyMethod(mark,ModuleNumber,subject_code);
                checkListContains(l,a,mark,ModuleNumber,subject_code);
            }
            else
            {
                l.add(a);
                return a;
            }
            return a;
        }

        

}
