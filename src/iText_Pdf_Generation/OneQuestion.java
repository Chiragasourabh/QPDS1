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
package iText_Pdf_Generation;

import Database.CheckImagePresence;
import static Database.countDashboard.nOfQuestions;
import Database.getImage;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chiragasourabh
 */
public class OneQuestion {
    public static void oneQuestionMethod(PdfPTable parentTable,int temp_mark,int qNo,String question, String temp_key) throws DocumentException, SQLException, IOException
	{
                List<String> list = new ArrayList<String>(4);
    		list.add("a");
    		list.add("b");
    		list.add("c");
    		list.add("d");
                
                Font marksFont = FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD);
                Font questionNumberFont = FontFactory.getFont(FontFactory.TIMES, 15, Font.ITALIC);
                Font questionFont = FontFactory.getFont(FontFactory.TIMES, 15, Font.NORMAL);
                
		Paragraph alphaQuestionNumberParagraph=new Paragraph();
                alphaQuestionNumberParagraph.setFont(questionNumberFont);
                alphaQuestionNumberParagraph.setAlignment(Element.ALIGN_CENTER);
            
                Paragraph questionParagraph =new Paragraph();
                questionParagraph.setFont(questionFont);
                
                PdfPTable temptable =new PdfPTable(1);
                
                temptable.setWidths(new float[]{100});
                temptable.setHorizontalAlignment(0);
                temptable.setTotalWidth(420);
                temptable.setLockedWidth(true);
                temptable.getDefaultCell().setBorder(0);
            
                Paragraph marksParagraph=new Paragraph();
                marksParagraph.setFont(marksFont);
                     
                alphaQuestionNumberParagraph.add(list.get(qNo)+")");
                System.out.println(list.get(qNo));
		//alphaQuestionNumberParagraph.add("a)");
		//questionParagraph.add("Explain the Concept of Polymarphisn. Explain the Concept of Polymarphisn. Explain the Concept of Polymarphisn.");
                questionParagraph.add(question);
                marksParagraph.add("("+temp_mark+" Marks)");
                
                temptable.addCell(questionParagraph);
                ResultSet rs = CheckImagePresence.checkimagePresence(temp_key);
                int count=0;
                while(rs.next()){
                         count = rs.getInt("count");
                        }
                
                if(count>=1)
                {
                    ResultSet r = getImage.getImageMethod(temp_key);
                    InputStream imageStream;
			OutputStream out;
			while(r.next())
			{
				imageStream =r.getBinaryStream(1);
				out = new FileOutputStream(new File("D:/temp.jpg"));
				int c =0;
				while((c = imageStream.read()) != -1){
						out.write(c);
				}
				
			}
			
                        Image img = Image.getInstance("D:/temp.jpg");
                        //img.scaleAbsolute(1, 9);
                        //img.scaleAbsoluteHeight(5);
                        //img.scaleToFit(1, 5);
                        temptable.addCell(img);
                        
                }
                
                parentTable.addCell(alphaQuestionNumberParagraph);
                parentTable.addCell(temptable);
                parentTable.addCell(marksParagraph);
                File file = new File("D:/temp.jpg");
                file.delete();
        }
}
