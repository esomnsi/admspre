package com.ericsson.mssp.approval.createpdf;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;


public class AddHeaderAndFooter extends PdfPageEventHelper{
	
	private HttpServletRequest request;
	private OpportunityDTO opportunityDTO;
	public AddHeaderAndFooter(){
		
	}
	
	public AddHeaderAndFooter(HttpServletRequest request,OpportunityDTO opportunityDTO){
		this();
		this.request = request;
		this.opportunityDTO = opportunityDTO;
	}
	
	private static Font smallBold = new Font(Font.TIMES_ROMAN, 9,Font.BOLD,CMYKColor.LIGHT_GRAY);
	private static Font smallFont = new Font(Font.TIMES_ROMAN, 8,Font.NORMAL,CMYKColor.GRAY);
	private static Font mediumFont = new Font(Font.TIMES_ROMAN, 9,Font.NORMAL,CMYKColor.GRAY);
	
 
  public void onStartPage(PdfWriter writer, Document document){
  	
  	//System.out.println("Page number = "+ document.getPageNumber());
  	try {
  	//Resource resource = new ClassPathResource("ericsson_logo_pdf.jpg");
  	//String imageUrl = "http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/images/ericsson_logo_pdf.jpg";
  	String imageUrl = "http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/images/Ericsson_Logo_1.gif";
  	Image image = Image.getInstance(imageUrl);
  	//image.scalePercent(40);
  	image.scalePercent(2);
  	
  	PdfPTable tbl = new PdfPTable(3);
  	PdfPCell cell = new PdfPCell(image);
  	cell.setBorderColor(CMYKColor.WHITE);
  	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
  	cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
  	tbl.addCell(cell);
  	
  	cell = new PdfPCell();cell.setBorderColor(CMYKColor.WHITE);tbl.addCell(cell);
  	
  	cell = new PdfPCell(new Phrase("Ericsson Internal\n"+opportunityDTO.getCustomerDTO().getCustomerName().toUpperCase()+" CEM ADM SOLUTION         ("+Integer.toString(document.getPageNumber())+")",smallBold));
  	cell.setBorderColor(CMYKColor.WHITE);
  	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
  	cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
  	tbl.addCell(cell);
  	tbl.setHorizontalAlignment(0);
	tbl.setWidthPercentage(100);
	document.add(tbl);
	
	PdfPTable table2 = new PdfPTable(2);table2.setWidthPercentage(100);
	Phrase phrase1 = new Phrase("Prepared (Subject resp)",smallFont);
	Phrase phrase2 = new Phrase(opportunityDTO.getOpportunityDetailsDTO().getOpportunityOwner(),mediumFont);
	PdfPCell cell1 = new PdfPCell();
	cell1.addElement(phrase1);cell1.addElement(phrase2);
	cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
	cell1.setBorderColor(CMYKColor.LIGHT_GRAY);
	table2.addCell(cell1);
	
	PdfPCell cell2 = new PdfPCell(new Phrase("No.",smallFont));
	cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
	cell2.setBorderColor(CMYKColor.LIGHT_GRAY);
	table2.addCell(cell2);   
	
	
	PdfPTable smallTable1 = new PdfPTable(2);
	smallTable1.setWidthPercentage(100);
	PdfPCell smallCell1 = new PdfPCell(new Phrase("Approved (Document resp)",smallFont));
	smallCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
	smallCell1.setBorderColor(CMYKColor.LIGHT_GRAY);
	
	PdfPCell smallCell2 = new PdfPCell(new Phrase("Checked",smallFont));
	smallCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
	smallCell2.setBorderColor(CMYKColor.LIGHT_GRAY);
	
	smallTable1.addCell(smallCell1);smallTable1.addCell(smallCell2);
	PdfPCell cell3 = new PdfPCell(smallTable1);
	cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
	cell3.setBorderColor(CMYKColor.LIGHT_GRAY);
	table2.addCell(cell3);
	
	PdfPTable smallTable2 = new PdfPTable(3);
	Phrase phrase3 = new Phrase("Date",smallFont);
	
	Date date = new Date();
	SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
	Phrase phrase4 = new Phrase(ft.format(date),mediumFont);
	
	Chunk phrase5 = new Chunk("Rev",smallFont);
	Chunk phrase6 = new Chunk("PA1",mediumFont);
	
	Phrase phrase7 = new Phrase("Reference",smallFont);
	
	PdfPCell smallCell3 = new PdfPCell();
	smallCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
	smallCell3.setBorderColor(CMYKColor.LIGHT_GRAY);
	smallCell3.addElement(phrase3);
	smallCell3.addElement(phrase4);
	
	PdfPCell smallCell4 = new PdfPCell();
	smallCell4.setHorizontalAlignment(Element.ALIGN_LEFT);
	smallCell4.setBorderColor(CMYKColor.LIGHT_GRAY);
	smallCell4.addElement(phrase5);
	smallCell4.addElement(phrase6);
	
	PdfPCell smallCell5 = new PdfPCell(phrase7);
	smallCell5.setHorizontalAlignment(Element.ALIGN_LEFT);
	smallCell5.setBorderColor(CMYKColor.LIGHT_GRAY);
	
	smallTable2.addCell(smallCell3);
	smallTable2.addCell(smallCell4);
	smallTable2.addCell(smallCell5);
	
	PdfPCell cell4 = new PdfPCell(smallTable2);
	cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
	cell4.setBorderColor(CMYKColor.LIGHT_GRAY);
	
	table2.addCell(cell4);
	document.add(table2);
	
	    
	    //LineSeparator UNDERLINE =  new LineSeparator(0, 100, null, Element.ALIGN_LEFT, 15);
	    //Paragraph para = new Paragraph();
	    //para.add(UNDERLINE);
	    //document.add(para);
	    
  		    
		} catch (BadElementException e) {
			e.printStackTrace();
					
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
      
  	
  }
  
}

