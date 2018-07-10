package iText_Pdf_Generation;

import java.io.FileNotFoundException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.ArrayList;
import java.util.List;

public class OneModuleFullQuestion {
	
	public void OneModuleFullQuestionmethod(Document document,int moduleNumber,int QuestionNumber,String subject_code) throws FileNotFoundException, ClassNotFoundException{
        try {

            List<String> Keylist = new ArrayList<>();
            
            Font moduleFont = new Font(Font.getFamily("TIMES"), 12,Font.BOLD|Font.UNDERLINE);
            Font orFont = FontFactory.getFont(FontFactory.TIMES, 11, Font.NORMAL);

            //open
            
            PdfPTable wholeModuleQuestionsTable =new PdfPTable(1);
            wholeModuleQuestionsTable.setWidths(new float[]{100});
            wholeModuleQuestionsTable.setHorizontalAlignment(0);
            wholeModuleQuestionsTable.setTotalWidth(529);
            wholeModuleQuestionsTable.setLockedWidth(true);
            wholeModuleQuestionsTable.getDefaultCell().setBorder(0);
            
            Paragraph moduleNumberPara=new Paragraph();
            moduleNumberPara.setFont(moduleFont);
            moduleNumberPara.add("MODULE-"+moduleNumber);
            moduleNumberPara.setAlignment(Element.ALIGN_CENTER);
           
            moduleNumberPara.setLeading(10);
            //moduleNumberPara.setSpacingBefore(50);
            //moduleNumberPara.setSpacingAfter(10);
            
            
            
            Paragraph showsOR=new Paragraph();
            showsOR.setFont(orFont);
            showsOR.add("OR");
            showsOR.setAlignment(Element.ALIGN_CENTER);
            showsOR.setLeading(10);
            
            PdfPCell moduleNumbercellOne = new PdfPCell();
            PdfPCell textORCellTwo = new PdfPCell();
            
            moduleNumbercellOne.addElement(moduleNumberPara);
            textORCellTwo.addElement(showsOR);
            
            moduleNumbercellOne.setBorder(Rectangle.NO_BORDER);
            moduleNumbercellOne.setVerticalAlignment(Element.ALIGN_MIDDLE);
            moduleNumbercellOne.setHorizontalAlignment(Element.ALIGN_CENTER);
            moduleNumbercellOne.setFixedHeight(40f);
            textORCellTwo.setBorder(Rectangle.NO_BORDER);
            textORCellTwo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            textORCellTwo.setHorizontalAlignment(Element.ALIGN_CENTER);
            textORCellTwo.setFixedHeight(20f);
            
            
            MainQuestionNumberandQuestions MainQuestionNumberandQuestionsObject = new MainQuestionNumberandQuestions();
            
            wholeModuleQuestionsTable.addCell(moduleNumbercellOne);
            MainQuestionNumberandQuestionsObject.MainQuestionNumberandQuestionsMethod(wholeModuleQuestionsTable,moduleNumber, QuestionNumber,Keylist,subject_code);
            wholeModuleQuestionsTable.addCell(textORCellTwo);
            QuestionNumber++;
            MainQuestionNumberandQuestionsObject.MainQuestionNumberandQuestionsMethod(wholeModuleQuestionsTable,moduleNumber, QuestionNumber,Keylist,subject_code);
            
            
            document.add(wholeModuleQuestionsTable);
            
            
            
            
            
            System.out.println("Done");

        } catch (DocumentException e) {
            e.printStackTrace();
        }

		
	}

}
