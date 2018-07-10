package iText_Pdf_Generation;


import java.io.FileNotFoundException;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.util.List;

public class MainQuestionNumberandQuestions {
	
	public void MainQuestionNumberandQuestionsMethod(PdfPTable parentTable,int ModuleNumber,int QuestionNumber,List Keylist,String subject_code) throws FileNotFoundException, ClassNotFoundException
	{

        try {

            
            Font marksFont = FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD);

            //open
            
            PdfPTable mainQuestionNumberAndOneSetQuestionsTable =new PdfPTable(2);
            mainQuestionNumberAndOneSetQuestionsTable.setWidths(new float[]{4,96});
            mainQuestionNumberAndOneSetQuestionsTable.setHorizontalAlignment(0);
            mainQuestionNumberAndOneSetQuestionsTable.setTotalWidth(525);
            mainQuestionNumberAndOneSetQuestionsTable.setLockedWidth(true);
            mainQuestionNumberAndOneSetQuestionsTable.getDefaultCell().setBorder(0);
            
            //Paragraph mainQuestionNumber=new Paragraph();
            //mainQuestionNumber.setFont(marksFont);
            //mainQuestionNumber.add("1.");
            
           
            PdfPCell oneSetOfQuestionCellTwo = new PdfPCell();
            
            
            oneSetOfQuestionCellTwo.setBorder(Rectangle.NO_BORDER);
            oneSetOfQuestionCellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            OneSetOfQuestions OneSetOfQuestionsObject = new OneSetOfQuestions();
            
            
            
            	Paragraph mainQuestionNumber=new Paragraph();
            	mainQuestionNumber.setFont(marksFont);
            	mainQuestionNumber.add(QuestionNumber+".");
            	{
            			mainQuestionNumberAndOneSetQuestionsTable.addCell(mainQuestionNumber);
                                try{
            			OneSetOfQuestionsObject.OneSetOfQuestionsMethod(mainQuestionNumberAndOneSetQuestionsTable,Keylist,ModuleNumber,subject_code);
                                }
                                catch(IOException e){
                                    System.err.println(e);
                                }
            	}
            	mainQuestionNumber=null;
            
            
            parentTable.addCell(mainQuestionNumberAndOneSetQuestionsTable);
            
            System.out.println("Done");
            

        } catch (DocumentException e) {
            e.printStackTrace();
        }

		
	}

}
