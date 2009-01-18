package org.jpericia.test.report;

import java.awt.Color;
import java.io.FileOutputStream;

import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class ITextTest {

	public static void main(String[] args)
	{
		try
		{
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/psaux/ITextTest.pdf"));
	
			document.open();
			document.add(new Paragraph("First page of the document."));
			document.add(new Paragraph("Some more text on the first page with different color and font type.", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new Color(255, 150, 200))));
			
			Paragraph title1 = new Paragraph("Chapter 1", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0, 255)));
			Chapter chapter1 = new Chapter(title1, 1);
			chapter1.setNumberDepth(0);
			
			Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(255, 0, 0)));
			Section section1 = chapter1.addSection(title11);
			Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");
			section1.add(someSectionText);
			someSectionText = new Paragraph("Following is a 3 X 2 table.");

			section1.add(someSectionText);
			
			Table t = new Table(3,2);
			t.setBorderColor(new Color(220, 255, 100));
			t.setPadding(5);
			t.setSpacing(5);
			t.setBorderWidth(1);
			Cell c1 = new Cell("header1");
			c1.setHeader(true);
			t.addCell(c1);
			c1 = new Cell("Header2");
			t.addCell(c1);
			c1 = new Cell("Header3");
			t.addCell(c1);
			t.endHeaders();
			t.addCell("1.1");
			t.addCell("1.2");
			t.addCell("1.3");
			section1.add(t);
			
			List l = new List(true, false, 10);
			l.add(new ListItem("First item of list"));
			l.add(new ListItem("Second item of list"));
			section1.add(l);
			
			document.add(chapter1);
			document.close();
		}
		catch( Exception e)
		{
			
		}
	}
}
