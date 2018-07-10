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


import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
/**
 *
 * @author Chiragasourabh
 */
public class HeaderGeneration {

	public void GenerateHeader(Document document,String subject_code,String sem,String examination_month,String examination_year,String subject_name,String exam_date,String from_time,String to_time) throws DocumentException
	{
            
			
            Paragraph headerOne = new Paragraph();
            PdfPTable headerTwo = new PdfPTable(2);
            Paragraph headerThree = new Paragraph();
            Paragraph headerFour = new Paragraph();
            PdfPTable headerFive = new PdfPTable(2);
            Paragraph note = new Paragraph();
            
            //Font subjectCodeFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.ITALIC);
           
            //Font paragraph2Font = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD);
            Font headerOneFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 35, Font.NORMAL);
            Font headerTwoUSNFont = FontFactory.getFont(FontFactory.TIMES, 15, Font.BOLD);
            Font headerTwoSubjectCodeFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.ITALIC);
            Font headerThreeFont = FontFactory.getFont(FontFactory.TIMES, 17, Font.BOLD);
            Font headerFourFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 21, Font.BOLD);
            Font headerFiveFont = FontFactory.getFont(FontFactory.TIMES, 15, Font.NORMAL);
            
            
            
            PdfPTable usnBoxes = new PdfPTable(10);
            usnBoxes.setHorizontalAlignment(0);
            usnBoxes.setTotalWidth(200);
            usnBoxes.setLockedWidth(true);
            for(int aw=0;aw<10;aw++)
            	usnBoxes.addCell(" ");
            
            PdfPTable headerTwoTableUsnAndUsnBoxes =new PdfPTable(2);
            headerTwoTableUsnAndUsnBoxes.setHorizontalAlignment(0);
            headerTwoTableUsnAndUsnBoxes.setTotalWidth(80);
            headerTwoTableUsnAndUsnBoxes.setLockedWidth(true);
            Paragraph usnParagraph=new Paragraph();
            usnParagraph.setFont(headerTwoUSNFont);
            usnParagraph.add("USN ");
            PdfPCell cellOne = new PdfPCell(usnParagraph);
            PdfPCell cellTwo = new PdfPCell(usnBoxes);
            cellOne.setBorder(Rectangle.NO_BORDER);
            cellOne.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTwo.setBorder(Rectangle.NO_BORDER);
            cellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);
            headerTwoTableUsnAndUsnBoxes.addCell(cellOne);
            headerTwoTableUsnAndUsnBoxes.addCell(cellTwo);
            
            
            headerOne.setFont(headerOneFont);
            headerOne.add("CBCS Scheme");
            headerOne.setAlignment(Element.ALIGN_CENTER);
            headerOne.setLeading(10);
            
            headerTwo.setHorizontalAlignment(0);
            headerTwo.setTotalWidth(870);
            headerTwo.setLockedWidth(true);
            Chunk subjectCode = new Chunk();
            subjectCode.setFont(headerTwoSubjectCodeFont);
            subjectCode.append(subject_code);
            PdfPCell cellThree = new PdfPCell(headerTwoTableUsnAndUsnBoxes);
            PdfPCell cellFour = new PdfPCell(); cellFour.addElement(subjectCode);
            cellThree.setBorder(Rectangle.NO_BORDER);
            cellThree.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellFour.setBorder(Rectangle.NO_BORDER);
            cellFour.setHorizontalAlignment(Element.ALIGN_LEFT);
            headerTwo.addCell(cellThree);
            headerTwo.addCell(cellFour);
            
            headerThree.setFont(headerThreeFont);
            headerThree.add(sem+" Semester B.E Degree Examination, "+examination_month+" "+examination_year);
            headerThree.setAlignment(Element.ALIGN_CENTER);
            headerThree.setLeading(20);
            
            headerFour.setFont(headerFourFont);
            headerFour.add(subject_name);
            headerFour.setAlignment(Element.ALIGN_CENTER);
            headerFour.setLeading(20);
            
            headerFive.setHorizontalAlignment(0);
            headerFive.setTotalWidth(850);
            headerFive.setLockedWidth(true);
            Paragraph date = new Paragraph();
            		date.setFont(headerFiveFont);
            		date.add("Date : "+exam_date); 
            Paragraph max_marks = new Paragraph();
            		max_marks.setFont(headerFiveFont);
            		max_marks.add("Max Marks : 80"); 
            Paragraph time = new Paragraph();
            		time.setFont(headerFiveFont);
            		time.add("Time : "+from_time+" to "+to_time);             
            Paragraph duration = new Paragraph();
            		duration.setFont(headerFiveFont);
            		duration.add("Duration : 3Hrs."); 
            PdfPCell headerFiveCellOne = new PdfPCell(date);
            PdfPCell headerFiveCellTwo = new PdfPCell(max_marks);
            PdfPCell headerFiveCellThree = new PdfPCell(time);
            PdfPCell headerFiveCellFour = new PdfPCell(duration);
            headerFiveCellOne.setBorder(Rectangle.NO_BORDER);
            headerFiveCellOne.setHorizontalAlignment(Element.ALIGN_LEFT);
            headerFiveCellTwo.setBorder(Rectangle.NO_BORDER);
            headerFiveCellTwo.setHorizontalAlignment(Element.ALIGN_LEFT);
            headerFiveCellThree.setBorder(Rectangle.NO_BORDER);
            headerFiveCellThree.setHorizontalAlignment(Element.ALIGN_LEFT);
            headerFiveCellFour.setBorder(Rectangle.NO_BORDER);
            headerFiveCellFour.setHorizontalAlignment(Element.ALIGN_LEFT);
            headerFive.addCell(headerFiveCellOne);
            headerFive.addCell(headerFiveCellTwo);
            headerFive.addCell(headerFiveCellThree);
            headerFive.addCell(headerFiveCellFour);
            
            note.add("NOTE: Answer any FIVE full Questions choosing atleast one from each module.");
            note.setAlignment(Element.ALIGN_LEFT);
            note.setSpacingAfter(10);
            
            
            //add contents to PDF file
            document.add(headerOne);
            document.add(Chunk.NEWLINE);
            document.add(headerTwo);
            document.add(headerThree);
            document.add(headerFour);   
            document.add(headerFive);
            LineSeparator ls =new LineSeparator();
            document.add(new Chunk(ls));
            document.add(note);
    }
}