package com.ericsson.mssp.approval.createpdf;



import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ericsson.mssp.approval.forms.ADRReport;
import com.ericsson.mssp.approval.forms.CheckListElements;
import com.ericsson.mssp.approval.forms.ExecSummary;
import com.ericsson.mssp.approval.forms.ExecSummary.Row;
import com.ericsson.mssp.approval.forms.ServiceFTEReport;
import com.ericsson.mssp.approval.forms.SolutionSummary;
import com.ericsson.mssp.approval.forms.TNTReport;
import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.entity.SolutionADR;
import com.lowagie.text.Anchor;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class CreatePDF {
	
	  public static Logger logger = Logger.getLogger(CreatePDF.class);
	
	  private Font catFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
	  private Font redFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL, CMYKColor.RED);
	  private Font subFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
	  private Font smallBold = new Font(Font.TIMES_ROMAN, 10,Font.BOLD);
	  private Font smallData = new Font(Font.TIMES_ROMAN, 10,Font.NORMAL);
	  private Font title1 = new Font(Font.TIMES_ROMAN, 14,Font.BOLD,CMYKColor.GREEN);
	  private Font title2 = new Font(Font.TIMES_ROMAN, 20,Font.BOLD,CMYKColor.RED);
	  private Font title3 = new Font(Font.TIMES_ROMAN, 28,Font.BOLD,CMYKColor.BLUE);
	  
	  private Document document;
	  private PdfPTable opportunityTable;
	  private PdfPTable solutionTable;
	  private PdfPTable serviceScopeTable;
	  private PdfPTable transformationTable;
	  private PdfPTable checkListSolDevTable;
	  private PdfPTable checkListStaffPlanTable;
	  private PdfPTable financialTable;
	  private PdfPCell headCell;
	  private PdfPCell dataCell;
	  private Chapter chapter;
	  private Anchor anchor;
	  private PdfPTable documentControlTable;
	  private PdfPTable versionControlTable;
	  
	  private HttpServletRequest request;
	  private HttpServletResponse response;
	  private HttpSession session;
	  
	  List<PdfPCell> cellList = new ArrayList<PdfPCell>();
	  List<PdfPCell> manpowerRevenueCellList = new ArrayList<PdfPCell>();
	  List<Float> manpowerRevenueDataList = new ArrayList<Float>();
	  List<PdfPCell> travelRevenueCellList = new ArrayList<PdfPCell>();
	  List<Float> travelRevenueDataList = new ArrayList<Float>();
	  List<PdfPCell> othersRevenueCellList = new ArrayList<PdfPCell>();
	  List<Float> othersRevenueDataList = new ArrayList<Float>();
	  List<PdfPCell> grandTotalRevenueCellList = new ArrayList<PdfPCell>();
	  List<Float> grandTotalRevenueDataList = new ArrayList<Float>();
	  List<PdfPCell> manpowerCostCellList = new ArrayList<PdfPCell>();
	  List<Float> manpowerCostDataList = new ArrayList<Float>();
	  List<PdfPCell> travelCostCellList = new ArrayList<PdfPCell>();
	  List<Float> travelCostDataList = new ArrayList<Float>();
	  List<PdfPCell> othersCostCellList = new ArrayList<PdfPCell>();
	  List<Float> othersCostDataList = new ArrayList<Float>();
	  List<PdfPCell> grandTotalCostCellList = new ArrayList<PdfPCell>();
	  List<Float> grandTotalCostDataList = new ArrayList<Float>();
	  List<PdfPCell> markupNonManPowerCostCellList = new ArrayList<PdfPCell>();
	  List<Float> markupNonManpowerDataList = new ArrayList<Float>();
	  List<PdfPCell> percntMarkupNonManPowerCostCellList = new ArrayList<PdfPCell>();
	  List<Float> percntMarkupNonManpowerDataList = new ArrayList<Float>();
	  List<PdfPCell> onsiteEffortCellList = new ArrayList<PdfPCell>();
	  List<Float> onsiteEffortDataList = new ArrayList<Float>();
	  List<PdfPCell> offshoreEffortCellList = new ArrayList<PdfPCell>();
	  List<Float> offshoreEffortDataList = new ArrayList<Float>();
	  List<PdfPCell> grandTotalEffortCellList = new ArrayList<PdfPCell>();
	  List<Float> grandTotalEffortDataList = new ArrayList<Float>();	  
	  List<PdfPCell> onsiteEffortToTotalEffortCellList = new ArrayList<PdfPCell>();
	  List<Float> onsiteEffortToTotalEffortDataList = new ArrayList<Float>();
	  List<PdfPCell> offshoreEffortToTotalEffortCellList = new ArrayList<PdfPCell>();
	  List<Float> offshoreEffortToTotalEffortDataList = new ArrayList<Float>();	  
	  List<PdfPCell> blendedCostPerHourCellList = new ArrayList<PdfPCell>();
	  List<Float> blendedCostPerHourDataList = new ArrayList<Float>();
	  List<PdfPCell> manpowerAsPercntTotCostCellList = new ArrayList<PdfPCell>();
	  List<Float> manpowerAsPercntTotCostdataList = new ArrayList<Float>();
	  List<PdfPCell> travelAsPercntTotCostCellList = new ArrayList<PdfPCell>();
	  List<Float> travelAsPercntTotCostdataList = new ArrayList<Float>();
	  List<PdfPCell> othersAsPercntTotCostCellList = new ArrayList<PdfPCell>();
	  List<Float> othersAsPercntTotCostdataList = new ArrayList<Float>();
	  List<PdfPCell> travelCostPerOnsiteHourCellList = new ArrayList<PdfPCell>();
	  List<Float> travelCostPerOnsiteHourDataList = new ArrayList<Float>();
	  
	  public CreatePDF() {
			document = new Document();
			opportunityTable = new PdfPTable(2);	
			solutionTable = new PdfPTable(2);
			serviceScopeTable = new PdfPTable(3);
			transformationTable = new PdfPTable(4);
			checkListSolDevTable = new PdfPTable(2);
			checkListStaffPlanTable = new PdfPTable(2);	
			documentControlTable = new PdfPTable(4);
			versionControlTable = new PdfPTable(4);
		}
	  
	  public CreatePDF(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		  
		  this();
		  this.request = request;
		  this.response = response;
		  this.session = session;
	  }
	  
	  
		public void createPDFFile(OpportunityDTO opportunityDTO, SolutionSummary solutionSummary, List<ServiceFTEReport> sericeFTEList, List<TNTReport> tntReportRows, ADRReport adrReport,CheckListElements elements, Map<Integer,String> checkListValues, ExecSummary summary, String comments) throws Exception{
	  
			
			logger.info("Creating PDF");
		    try {
		      ServletOutputStream out = response.getOutputStream();	
		      response.setHeader("Content-Disposition", "attachment; filename="+opportunityDTO.getOpportunityName()+".pdf");
		      PdfWriter writer = PdfWriter.getInstance(document, out);
		      addMetaData(opportunityDTO);
		      document.open();
		      addTitlePage(opportunityDTO);
		      // Add Header and Footer on every page
		      writer.setPageEvent(new AddHeaderAndFooter(request,opportunityDTO));	
		      addContent(opportunityDTO,solutionSummary,sericeFTEList,tntReportRows,adrReport,elements,checkListValues,summary, comments);
		      //out.flush();
		      //out.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		      throw new Exception("Exception in generating PDF",e);
		    }finally{
		    	logger.info("Executing finally");
		    	document.close();
		    }
		  
		}
		
		
		 //iText allows to add metadata to the PDF which can be viewed in your Adobe Reader under File -> Properties
		 
		
		  private void addMetaData(OpportunityDTO opportunityDTO) throws Exception{
			  if(opportunityDTO.getOpportunityName()!=null || opportunityDTO.getOpportunityName()!=""){
				  document.addTitle(opportunityDTO.getOpportunityName());
				  document.addAuthor((String)session.getAttribute(MSSPConstants.USER_NAME));
				  document.addCreator((String)session.getAttribute(MSSPConstants.USER_NAME));
			  }
		    
		    
		  }

		  private void addTitlePage(OpportunityDTO opportunityDTO) throws DocumentException, MalformedURLException, IOException {
			  
			
			String imageUrl = "http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/images/ericsson-color-line.png";
			Image image = Image.getInstance(imageUrl);
			image.scalePercent(27);
			//document.add(image);
			
		    Paragraph preface = new Paragraph();
		    preface.setAlignment(Element.ALIGN_CENTER);
		    
		    Phrase phrase1 = new Phrase(opportunityDTO.getCustomerDTO().getCustomerName(), title3);		    
		    Phrase phrase2 = new Phrase("Report generated by: " + (String)session.getAttribute(MSSPConstants.USER_NAME),smallBold);
		    Phrase phrase3 = new Phrase(new Date().toString(),smallBold);
		    
		    preface.add("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		    preface.add(phrase1);
		    //addEmptyLine(preface, 3);
		    //preface.add(phrase2);
		    //addEmptyLine(preface, 1);
		    //preface.add(phrase3);
		    
		    
		    PdfPTable pageTable = new PdfPTable(1);
		    pageTable.setWidthPercentage(100);
		    
		    PdfPCell cell1 = new PdfPCell(new Phrase(opportunityDTO.getCustomerDTO().getCustomerName()+" CEM Project\n\n\n",title1));
		    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    cell1.setBorderColor(CMYKColor.WHITE);
		    PdfPCell cell2 = new PdfPCell(image);
		    cell2.setBorderColor(CMYKColor.WHITE);
		    PdfPCell cell3 = new PdfPCell(new Phrase("\n\n\n"+opportunityDTO.getCustomerDTO().getCustomerName().toUpperCase()+" CEM ADM SOLUTION",title2));
		    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    cell3.setBorderColor(CMYKColor.WHITE);
		    PdfPCell cell = new PdfPCell(preface);
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderColor(CMYKColor.WHITE);
		    
		    
		    pageTable.addCell(cell1);
		    pageTable.addCell(cell2);
		    pageTable.addCell(cell3);
		    pageTable.addCell(cell);
		    document.add(pageTable);
		  }
		  private void openChapter(String heading, int number){
			  anchor = new Anchor(heading , catFont);
			  chapter = new Chapter(new Paragraph(anchor),number);	
		  }
		  
		  private void addTableToChapter(PdfPTable table) throws DocumentException{
			  	addSpaceToChapter(2);
			    chapter.add(table);			    
		  }
		  private void addSpaceToChapter(int spaces){
			  	Paragraph emptyPara = new Paragraph();
			    addEmptyLine(emptyPara, spaces);
			    chapter.add(emptyPara);
		  }
		  private void addSpaceToDocument(int spaces) throws DocumentException{
			  	Paragraph emptyPara = new Paragraph();
			    addEmptyLine(emptyPara, spaces);
			    document.add(emptyPara);
		  }
		  private void addChapterToDocument() throws DocumentException{
			  addSpaceToDocument(5);
			  document.add(chapter);
		  }
		  
		  private PdfPCell getHeadCell(String head){
			  	headCell = new PdfPCell(new Anchor(head , smallBold));
			  	//headCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			  	headCell.setGrayFill(0.8f);
			  	headCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    	//headCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    	return headCell;
		  }
		  private PdfPCell getHeadCell1(String head){
			  	headCell = new PdfPCell(new Anchor(head , smallData));
			  	//headCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			  	headCell.setGrayFill(0.8f);
			  	headCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    	//headCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    	return headCell;
		  }
		  private PdfPCell getDataCell(String data){
			  dataCell = new PdfPCell(new Anchor(data , smallData));
			  dataCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			  return dataCell;
		  }
		  private void nextDocumentPage() throws DocumentException{
			  document.newPage();
		  }
		  
		  private void addNewSecionToChapter(String head){
			  addSpaceToChapter(2);
			  chapter.addSection(new Paragraph(head,subFont));
		  }
		  
		  private void addContent(OpportunityDTO opportunityDTO, SolutionSummary solutionSummary, List<ServiceFTEReport> sericeFTEList, List<TNTReport> tntReportRows, ADRReport adrReport,CheckListElements elements,Map<Integer,String> checkListValues ,ExecSummary summary,String comments) throws DocumentException, MalformedURLException, IOException {
			 
			  addReleaseNotices();	
			  addDocumentControl(opportunityDTO);
			  addVersionControlAndHistory(opportunityDTO);
			  addAbstract(opportunityDTO);
			  addApplication();
			  addPurpose(opportunityDTO);
			  addExecutiveSummary(opportunityDTO);
			  addServicesInScope(opportunityDTO,sericeFTEList);
			  addServicesOutOfScope();
			  addArchitecture();
			  addProposedSolution();
			  //addOpportunityOverview(opportunityDTO);
			  //addSolutionDetailsTable(solutionSummary,sericeFTEList);
			  //addTransformationDetails(tntReportRows);
			  //addTop3ADRDetails(adrReport);
			  //addCheckListsDetails(elements,checkListValues);
			  addCommercials(summary);
			  //addApproverComments(comments);
			  //displayAllListsSize();

		  }

		  private void addEmptyLine(Paragraph paragraph, int number) {
		    for (int i = 0; i < number; i++) {
		      paragraph.add(new Paragraph(" "));
		    }
		  }
		  
		  private void addReleaseNotices() throws DocumentException{
			  nextDocumentPage();
			  addSpaceToDocument(4);
			  String data = "The present document is extremely confidential and is subject to distribution control. This document belongs to Ericsson India Global Services Pvt Ltd. The number and version on the document are allocated to the person notified in the document distribution list. The person(s) to whom this document is issued will be responsible for any changes/reviews to this document.  Every copy of this document is subject to Quality control.";
			  document.add(new Paragraph(MSSPConstants.releaseNotices+"\n\n"+data));
		  }
		  
		  private void addDocumentControl(OpportunityDTO opportunityDTO) throws DocumentException{
			  addSpaceToDocument(2);
			  document.add(new Paragraph(MSSPConstants.documentControl));
			  addSpaceToDocument(1);
			  for(String head : MSSPConstants.documentControlList){
				  PdfPCell cell = getHeadCell(head);
				  documentControlTable.addCell(cell);
			  }
			  			  
				  documentControlTable.addCell(getDataCell("Client Name"));
				  documentControlTable.addCell(getDataCell(opportunityDTO.getCustomerDTO().getCustomerName()+"_"+opportunityDTO.getCustomerDTO().getCountryDTO().getCountryName()));
				  documentControlTable.addCell(getDataCell(""));
				  documentControlTable.addCell(getDataCell(""));	
				  
				  documentControlTable.addCell(getDataCell("Regional Owner"));
				  documentControlTable.addCell(getDataCell(opportunityDTO.getOpportunityDetailsDTO().getOpportunityOwner()));
				  documentControlTable.addCell(getDataCell(""));
				  documentControlTable.addCell(getDataCell(""));
			  
			  documentControlTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			  document.add(documentControlTable);
		  }
		  
		  private void addVersionControlAndHistory(OpportunityDTO opportunityDTO) throws DocumentException{
			  addSpaceToDocument(2);
			  document.add(new Paragraph(MSSPConstants.versionControl));
			  addSpaceToDocument(1);
			  for(String head : MSSPConstants.versionControlTableList){
				  PdfPCell cell = getHeadCell(head);
				  versionControlTable.addCell(cell);
			  }
			  			  
			  	  versionControlTable.addCell(getDataCell("1.0"));
				  versionControlTable.addCell(getDataCell(opportunityDTO.getOpportunityDetailsDTO().getOpportunityOwner()));
				  versionControlTable.addCell(getDataCell(new Date().toString()));
				  versionControlTable.addCell(getDataCell("Release of 1st draft"));	
				  
				 
			  
			  versionControlTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			  document.add(versionControlTable);
		  }
		  
		  private void addAbstract(OpportunityDTO opportunityDTO) throws DocumentException{
			  String data = "This document explains the technical solution and commercials offered"+(opportunityDTO.getOpportunityDetailsDTO().getVertical() == null ? "":" by "+opportunityDTO.getOpportunityDetailsDTO().getVertical())+" to Region "+opportunityDTO.getCustomerDTO().getCountryDTO().getRegion()+" for delivering the ADM services as per the project requirement scope for "+opportunityDTO.getCustomerDTO().getCustomerName()+" "+opportunityDTO.getCustomerDTO().getCountryDTO().getCountryName()+".";
			  nextDocumentPage();
			  addSpaceToDocument(4);
			  document.add(new Paragraph(MSSPConstants.abstractKey,subFont));
			  addSpaceToDocument(1);
			  document.add(new Paragraph(data));
		  }
		  
		  private void addApplication() throws DocumentException{
			  String data = "Solution outline document is a techno-commercial proposal made specific to a project requirement for managed service delivery. The document details project scope of work, technical solution, deliverables, project timelines, responsibility matrix, assumptions/dependency, SLA’s, exclusions, commercials involved and other project specific details.";
			  addSpaceToDocument(4);
			  document.add(new Paragraph("Application",subFont));
			  addSpaceToDocument(1);
			  document.add(new Paragraph(data));
		  }
		  
		  private void addPurpose(OpportunityDTO opportunityDTO) throws DocumentException{
			  String data = "This document is to detail the GSCI CSI/ADM offer in response to Region project requirement for "+opportunityDTO.getCustomerDTO().getCustomerName()+" CEM ADM Solution.";
			  addSpaceToDocument(4);
			  document.add(new Paragraph("Purpose",subFont));
			  addSpaceToDocument(1);
			  document.add(new Paragraph(data));		  
			  
		  }
		  private void addParaToChapter(Paragraph para){
			  chapter.add(new Paragraph("\n")); 
			  chapter.add(para);
		  }
		  
		  private void addExecutiveSummary(OpportunityDTO opportunityDTO) throws DocumentException{
			  openChapter(MSSPConstants.ExecutiveSummary,1);
			  addNewSecionToChapter(MSSPConstants.Overview);
			  
			  String data = "Region "+opportunityDTO.getCustomerDTO().getCountryDTO().getRegion()+" seeks a proposal from Global Services Center India (‘GSI’, Legal Name: EGI) to provide ADM services on CEM Solution for "+opportunityDTO.getCustomerDTO().getCustomerName()+" "+opportunityDTO.getCustomerDTO().getCountryDTO().getCountryName()+".\n\n"+
					  		"This involves availability of relevant and skilled GSI staff to provide development and support services on CEM and to handle technical queries and issues escalated from the local (onsite) Support team.\n\n"+
					  		"This document describes the scope, proposed solution approach and commercials for Development and support services, based on information shared by Region. In addition, this document provides an overview of the delivery model, engagement governance approach being proposed by GSI.";
			  
			  addParaToChapter(new Paragraph(data));
			  
			  addNewSecionToChapter(MSSPConstants.aboutClient);
			  addNewSecionToChapter(MSSPConstants.valueProposition);
			    
			    String data1 = "EGI, armed with >14000 strong resource base, is capable of quick ramp-up and ramp-down to meet skill and delivery needs of Managed Service engagements.\n"+ 
			    				"	a. Competitive advantage both in terms of capability and cost leveraging large pool for resources. Agile delivery model to quickly adapt to the customer / project need by taking advantage of the large pool of resources in a right-shore model.\n"+
			    				"	b. Continual Improvement in services with CMMI Level 5 certified delivery centre.\n"+
			    				"	c. Well Defined and proven Transition and Transformation methodology.";
			    addParaToChapter(new Paragraph(data1));
			    addChapterToDocument();
		  }
		  
		  private void addOpportunityOverview(OpportunityDTO opportunityDTO) throws DocumentException{			    
			    
			  openChapter(MSSPConstants.OpportunityHead,1);
			    
			    for (String head : MSSPConstants.oppDetailsList){
			    	PdfPCell cell = getHeadCell(head);
			    	opportunityTable.addCell(cell);
			    	 
			    	
			    	switch(head){			    	
			    	
			    	case MSSPConstants.ClientName : // This is mandatory input so not checking null here 			    		
			    		opportunityTable.addCell(getDataCell(opportunityDTO.getCustomerDTO().getCustomerName()+"_"+opportunityDTO.getCustomerDTO().getCountryDTO().getCountryName()));
			    		break;
			    	
			    	case MSSPConstants.Region : 
			    		if(opportunityDTO.getCustomerDTO() != null){
			    		if(opportunityDTO.getCustomerDTO().getCountryDTO() != null){
			    		if(opportunityDTO.getCustomerDTO().getCountryDTO().getRegion() != null && opportunityDTO.getCustomerDTO().getCountryDTO().getRegion() != ""){
			    			opportunityTable.addCell(getDataCell(opportunityDTO.getCustomerDTO().getCountryDTO().getRegion()));
						}else{
							
						}}else{
							opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
						}}else{
							opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
						} break;
						
			    	case MSSPConstants.OpportunnityName : 
			    		opportunityTable.addCell(getDataCell(opportunityDTO.getOpportunityName()));
			    		break;
			    		
			    	case MSSPConstants.OpportunitySource :
			    		if(opportunityDTO.getOpportunitySourceDTO() != null){
			    		if(opportunityDTO.getOpportunitySourceDTO().getName() != null && opportunityDTO.getOpportunitySourceDTO().getName() != ""){
			    			opportunityTable.addCell(getDataCell(opportunityDTO.getOpportunitySourceDTO().getName()));
						}else{
							opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
						}}else{
							opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
						}break;
						
			    	case MSSPConstants.vertical :
			    		if(opportunityDTO.getOpportunityDetailsDTO() != null){
			    		if(opportunityDTO.getOpportunityDetailsDTO().getVertical() != null && opportunityDTO.getOpportunityDetailsDTO().getVertical() != ""){
			    			opportunityTable.addCell(getDataCell(opportunityDTO.getOpportunityDetailsDTO().getVertical()));
						}else{
							opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
						}}else{
							opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
						}break;
						
			    	case MSSPConstants.duration : 
			    		if(opportunityDTO.getOpportunityDetailsDTO() != null){
			    		if(opportunityDTO.getOpportunityDetailsDTO().getContractDuration() != null && opportunityDTO.getOpportunityDetailsDTO().getContractDuration() != ""){
			    			opportunityTable.addCell(getDataCell(opportunityDTO.getOpportunityDetailsDTO().getContractDuration()));
						}else{
							opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
						}}else{
							opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
						}break;
						
			    	case MSSPConstants.opportunityId :
			    		if(opportunityDTO.getOpportunityId() != null && opportunityDTO.getOpportunityId() > 0){
			    			opportunityTable.addCell(getDataCell(Integer.toString(opportunityDTO.getOpportunityId())));
			    		}else{
			    			opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
			    		}break;
			    		
			    	case MSSPConstants.model :
			    		if(opportunityDTO.getCommercialModelDTO() != null){
			    		if(opportunityDTO.getCommercialModelDTO().getName() != null && opportunityDTO.getCommercialModelDTO().getName() != ""){
			    			opportunityTable.addCell(getDataCell(opportunityDTO.getCommercialModelDTO().getName()));
			    		}else{
			    			opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
						}}else{
							opportunityTable.addCell(getDataCell(MSSPConstants.NotAvailable));
			    		}break;
			    		
			    	default : throw new DocumentException("Error Adding Cell in Opportunity Table, unknown attribute : "+head);
			    	   
			    	}
			    	
			    	
			    }
			    
			    addTableToChapter(opportunityTable);			   
			    addChapterToDocument();
		  }
		  private void addServicesInScope(OpportunityDTO opportunityDTO, List<ServiceFTEReport> sericeFTEList) throws DocumentException{
			  openChapter(MSSPConstants.servicesInScope,2);
			  String data = "Scope for GSI is to provide below services for "+opportunityDTO.getCustomerDTO().getCountryDTO().getCountryName()+" instance.";
			  addParaToChapter(new Paragraph(data));
			  
			  if(sericeFTEList != null && sericeFTEList.size()>0){
				  List<String> devList = new ArrayList<String>();
				  List<String> mainList = new ArrayList<String>();
				  List<String> testList = new ArrayList<String>();
				  List<String> mgmtList = new ArrayList<String>();
				  for(ServiceFTEReport obj : sericeFTEList){
					  if(1<=obj.getServiceScopeId() && 6>=obj.getServiceScopeId()){
						  mgmtList.add(obj.getServiceName());
					  }else if(7<=obj.getServiceScopeId() && 12>=obj.getServiceScopeId()){
						  devList.add(obj.getServiceName());
					  }else if(13<=obj.getServiceScopeId() && 19>=obj.getServiceScopeId()){
						  testList.add(obj.getServiceName());
					  }else if(20<=obj.getServiceScopeId() && 25>=obj.getServiceScopeId()){
						  mainList.add(obj.getServiceName());
					  }
				  }
				  
				  if(null != devList && devList.size() > 0){
					  addNewSecionToChapter(MSSPConstants.developmentScope);
					  Paragraph para = new Paragraph();
					  for(String str : devList){					  
						  para.add("     *     "+str+"\n");
					  }
					  addParaToChapter(para);
				  }
				  
				  if(null != mainList && mainList.size() > 0){
					  addNewSecionToChapter(MSSPConstants.maintenanceScope);
					  Paragraph para = new Paragraph();
					  for(String str : mainList){
						  para.add("     *     "+str+"\n");
					  }
					  addParaToChapter(para);
				  }
				  
				  if(null != testList && testList.size() > 0){
					  addNewSecionToChapter(MSSPConstants.testingScope);
					  Paragraph para = new Paragraph();
					  for(String str : testList){
						  para.add("     *     "+str+"\n");
					  }
					  addParaToChapter(para);
				  }
				  
				  if(null != mgmtList && mgmtList.size() > 0){
					  addNewSecionToChapter(MSSPConstants.managementScope);
					  Paragraph para = new Paragraph();
					  for(String str : mgmtList){
						  para.add("     *     "+str+"\n");
					  }
					  addParaToChapter(para);
				  }
				  
			  }
			  addChapterToDocument();
		  }
		  
		  private void addServicesOutOfScope() throws DocumentException{
			  openChapter(MSSPConstants.servicesOutOfScope,3);
			  addChapterToDocument();
		  }
		  
		  private void addArchitecture() throws DocumentException{
			  openChapter(MSSPConstants.architecture,4);
			  addChapterToDocument();
		  }
		  
		  private void addProposedSolution() throws DocumentException, MalformedURLException, IOException{
			  openChapter(MSSPConstants.proposedSolution,5);
			  addNewSecionToChapter(MSSPConstants.supportSolution);
			  addNewSecionToChapter(MSSPConstants.crApproach);
			  
			  addNewSecionToChapter(MSSPConstants.tools);
			  String data = "Integrated and fully functional development environment to be made available to GSI during the transition by Region/Customer Team. Hardware, Software, Baseline Source code, Application Software & Licenses, Tools Licenses and Application Binaries is to be made available by Region/Customer team.";
			  addParaToChapter(new Paragraph(data));
			  
			  addNewSecionToChapter(MSSPConstants.connectivity);
			  data = "Network connectivity between offshore (GSI location) and onsite (TeliaSonera) for in scope services will be provided/arranged by the region/program.\n\n"+ 
					 "As per Ericsson Security Guidelines, GSI recommends to provide secured RSG connectivity solution for this opportunity. Region need to factor the cost for the same.";
			  addParaToChapter(new Paragraph(data));
			  
			  addNewSecionToChapter(MSSPConstants.estimates);
			  
			  addNewSecionToChapter(MSSPConstants.crEstimates);
			  
			  addNewSecionToChapter(MSSPConstants.knowledgeTransferMeth);
			  data = "Ericsson’s Standard Transition Methodology to be followed for Knowledge Transfer to GSI team.";
			  
			  Paragraph para = new Paragraph();		
			  para.add(data);
			  addParaToChapter(para);
			  
			  addNewSecionToChapter(MSSPConstants.serviceLevels);
			  
			  addNewSecionToChapter(MSSPConstants.serviceWindow);
			  
			  addNewSecionToChapter(MSSPConstants.engagementModel);
			  
			  addNewSecionToChapter(MSSPConstants.timelines);
			  
			  addNewSecionToChapter(MSSPConstants.assumptions);
			  loadAssumptionsAndDependencies();
			  addNewSecionToChapter(MSSPConstants.responsibility);
			  
			  addChapterToDocument();
		  }
		  
		  private void loadAssumptionsAndDependencies(){
			  Properties prop = new Properties();
			  InputStream input = null;
			  
			  try {
				  
					String filename = "AssumptionsAndDependencies.properties";
					input = getClass().getClassLoader().getResourceAsStream(filename);
					if (input == null) {
						logger.error("Sorry, unable to find " + filename);
						return;
					}
			 
					prop.load(input);
			 
					Enumeration<?> e = prop.propertyNames();
					while (e.hasMoreElements()) {
						String key = (String) e.nextElement();
						String value = prop.getProperty(key);
						//System.out.println("Key : " + key + ", Value : " + value);
						addParaToChapter(new Paragraph(value));
					}
			 
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					if (input != null) {
						try {
							input.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		  }
		  
		  private void addSolutionDetailsTable(SolutionSummary solutionSummary, List<ServiceFTEReport> sericeFTEList) throws DocumentException{
			  openChapter(MSSPConstants.SolutionHead,2);
			  
			  for(String head : MSSPConstants.solDetailsList){
				  PdfPCell cell = getHeadCell(head);
			      solutionTable.addCell(cell);
			      
			      switch(head){
			      
			      case MSSPConstants.deliveryModel:
			    	  if(solutionSummary.getDeliveryModel() != null && solutionSummary.getDeliveryModel() != ""){
			    	  solutionTable.addCell(getDataCell(solutionSummary.getDeliveryModel()));
			    	  }else{
			    		  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
			    	  }
			    	  break;
			    	  
			      case MSSPConstants.deliveryType:
			    	  if(solutionSummary.getDeliveryType() != null && solutionSummary.getDeliveryType() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getDeliveryType()));
				    	  }else{
				    		  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				    	  }
			    	  break;
			    	  
			      case MSSPConstants.proposedStartDate:
			    	  if(solutionSummary.getSsdText() != null && solutionSummary.getSsdText() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getSsdText()));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      case MSSPConstants.offshoreDeliveryLoc:
			    	  if(solutionSummary.getOffShoreLocation() != null && solutionSummary.getOffShoreLocation() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getOffShoreLocation()));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      case MSSPConstants.onshoreDeliveryLoc:
			    	  if(solutionSummary.getOnShoreLocation() != null && solutionSummary.getOnShoreLocation() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getOnShoreLocation()));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      case MSSPConstants.scopeOfService:
			    	  if(solutionSummary.getServiceScopeDefinedBy() != null && solutionSummary.getServiceScopeDefinedBy() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getServiceScopeDefinedBy()));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      case MSSPConstants.knowledgeTransfer:
			    	  if(solutionSummary.getKnowledgeTrnsfrInScope() != null && solutionSummary.getKnowledgeTrnsfrInScope() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getKnowledgeTrnsfrInScope()));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      case MSSPConstants.ktStartDate:
			    	  if(solutionSummary.getKTStartDtText() != null && solutionSummary.getKTStartDtText() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getKTStartDtText()));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      case MSSPConstants.ktDuration:
			    	  if((Integer)solutionSummary.getDurationKT() != null && solutionSummary.getDurationKT() > 0){
				    	  solutionTable.addCell(getDataCell(Integer.toString(solutionSummary.getDurationKT())));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      case MSSPConstants.knowledgeTransferLoc:
			    	  if(solutionSummary.getKtLocation() != null && solutionSummary.getKtLocation() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getKtLocation()));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      case MSSPConstants.prodLever:
			    	  if(solutionSummary.getpLeverApplied() != null && solutionSummary.getpLeverApplied() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getpLeverApplied()));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      case MSSPConstants.inputVolDefBy:
			    	  if(solutionSummary.getInputVolumetricsDefinedBy() != null && solutionSummary.getInputVolumetricsDefinedBy() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getInputVolumetricsDefinedBy()));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      case MSSPConstants.prodGainAppDate:
			    	  if(solutionSummary.getpLeverAppliedMnthText() != null && solutionSummary.getpLeverAppliedMnthText() != ""){
				    	  solutionTable.addCell(getDataCell(solutionSummary.getpLeverAppliedMnthText()));
				      }else{
				    	  solutionTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				      }
			    	  break;
			    	  
			      default : throw new DocumentException("Error Adding Cell in Solution Table Table. Unknown attribute : "+head);
			      }
			  }
			  
			  addTableToChapter(solutionTable);
			  addServiceScopeDetails(sericeFTEList);
			  
		  }
		  private void addServiceScopeDetails(List<ServiceFTEReport> sericeFTEList) throws DocumentException{

			  
			  addNewSecionToChapter(MSSPConstants.ServiceScopeHead);
			  
			  serviceScopeTable.addCell(getHeadCell(MSSPConstants.services));
			  serviceScopeTable.addCell(getHeadCell(MSSPConstants.operationWindow));
			  serviceScopeTable.addCell(getHeadCell(MSSPConstants.gscFte));
			  if(sericeFTEList != null && sericeFTEList.size()>0){
			  for(ServiceFTEReport obj : sericeFTEList){
				  if(obj.getServiceName()!=null && obj.getServiceName() != ""){
				      serviceScopeTable.addCell(getDataCell(obj.getServiceName()));
				  }else{
					  serviceScopeTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
				  if(obj.getOperationWindow()!=null && obj.getOperationWindow() != ""){
				  serviceScopeTable.addCell(getDataCell(obj.getOperationWindow()));
				  }else{
					  serviceScopeTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
				  if(obj.getFte()!=null){
					  serviceScopeTable.addCell(getDataCell(Double.toString(obj.getFte())));
				  }else{
					  serviceScopeTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
			  }
			  }else{
				  addNoDataFoundCell(3,serviceScopeTable);
			  }
			  addTableToChapter(serviceScopeTable);
			  addChapterToDocument();
			  //nextDocumentPage();
		  }
		  private void addTransformationDetails(List<TNTReport> tntReportRows) throws DocumentException{
			  float[] widths = new float[] {20f,30f,40f,40f};
			  openChapter(MSSPConstants.TransformationHead,3);
			  
			  transformationTable.setWidthPercentage(90);
			  transformationTable.setWidths(widths);
			  transformationTable.addCell(getHeadCell(MSSPConstants.partitionName));
			  transformationTable.addCell(getHeadCell(MSSPConstants.durationWeeks));
			  transformationTable.addCell(getHeadCell(MSSPConstants.rampUpPattern));
			  transformationTable.addCell(getHeadCell(MSSPConstants.date));
			  
			  if(tntReportRows != null && tntReportRows.size() > 0){
			  for(TNTReport report : tntReportRows){
				  if(report.getPartitionName()!=null && report.getPartitionName()!=""){
					  transformationTable.addCell(getDataCell(report.getPartitionName()));
				  }else{
					  transformationTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
				  if(report.getDurationWeeks()!=null && report.getDurationWeeks()!=""){
					  transformationTable.addCell(getDataCell(report.getDurationWeeks()));
				  }else{
					  transformationTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
				  if(report.getstaffRampUp()!=null && report.getstaffRampUp()!=""){
					  transformationTable.addCell(getDataCell(report.getstaffRampUp()));
				  }else{
					  transformationTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
				  if(report.getPartitionDuration()!=null && report.getPartitionDuration()!=""){
					  transformationTable.addCell(getDataCell(report.getPartitionDuration()));
				  }else{
					  transformationTable.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
			  }
			  }else{
				  addNoDataFoundCell(4, transformationTable);
			  }
			  
			  addTableToChapter(transformationTable);
			  addChapterToDocument();
			  
		  }
		  
		  private void addTop3ADRDetails(ADRReport adrReport) throws DocumentException{
			  
			  openChapter(MSSPConstants.Top3ADR, 4);
			  
			  addTop3Data(adrReport.getAssumptionList(),MSSPConstants.top3Assumptions);
			  addTop3Data(adrReport.getDependencyList(),MSSPConstants.top3Dependencies);
			  addTop3Data(adrReport.getRiskList(),MSSPConstants.top3Risks);
			  
			  addChapterToDocument();
		  }
		  
		  private void addCheckListsDetails(CheckListElements elements,Map<Integer,String> checkListValues ) throws DocumentException{
			  openChapter(MSSPConstants.checkLists, 5);
			  addNewSecionToChapter(MSSPConstants.checkListSolDev);			  

			  float[] widths = new float[] { 65f, 5f};
			  checkListSolDevTable.setWidths(widths);
			  
			  for(String head : MSSPConstants.checkListSolDevList){
				  checkListSolDevTable.addCell(getHeadCell(head));
				  
				  switch(head){
				  
				  case MSSPConstants.staffingPyramidCheck : checkListSolDevTable.addCell(getDataCell(checkListValues.get(elements.getCheckList1())));	break;			  
				  case MSSPConstants.governAndPmoCheck : checkListSolDevTable.addCell(getDataCell(checkListValues.get(elements.getCheckList2()))); break;
				  case MSSPConstants.transformationCheck : checkListSolDevTable.addCell(getDataCell(checkListValues.get(elements.getCheckList3()))); break;
				  case MSSPConstants.shoreCheck : checkListSolDevTable.addCell(getDataCell(checkListValues.get(elements.getCheckList4()))); break;
				  case MSSPConstants.allCostAddressCheck : checkListSolDevTable.addCell(getDataCell(checkListValues.get(elements.getCheckList5()))); break;
				  case MSSPConstants.onsiteRestrictionCheck : checkListSolDevTable.addCell(getDataCell(checkListValues.get(elements.getCheckList6()))); break;
				  case MSSPConstants.gscSlaCheck : checkListSolDevTable.addCell(getDataCell(checkListValues.get(elements.getCheckList7()))); break;
				  case MSSPConstants.slaParameterCheck : checkListSolDevTable.addCell(getDataCell(checkListValues.get(elements.getCheckList8()))); break;
				  default : throw new DocumentException("Error Adding Cell in CheckList - Solution Development Table. Unknown attribute : "+head);
				  }
			  }
			  addTableToChapter(checkListSolDevTable);
			  
			  addNewSecionToChapter(MSSPConstants.checkListStaffPlanning);
			  checkListStaffPlanTable.setWidths(widths);
			  for(String head1 : MSSPConstants.checkListStaffPlanningList){
				  checkListStaffPlanTable.addCell(getHeadCell(head1));
				  
				  switch(head1){
				  case MSSPConstants.availabilitySkillsCheck : checkListStaffPlanTable.addCell(getDataCell(checkListValues.get(elements.getCheckList9()))); break;
				  case MSSPConstants.startDateCheck : checkListStaffPlanTable.addCell(getDataCell(checkListValues.get(elements.getCheckList10()))); break;
				  case MSSPConstants.confirmDeployCheck : checkListStaffPlanTable.addCell(getDataCell(checkListValues.get(elements.getCheckList11()))); break;
				  case MSSPConstants.confirmTravelDeskCheck : checkListStaffPlanTable.addCell(getDataCell(checkListValues.get(elements.getCheckList12()))); break;
				  case MSSPConstants.approvalPracticeHeadCheck : checkListStaffPlanTable.addCell(getDataCell(checkListValues.get(elements.getCheckList13()))); break;
				  case MSSPConstants.demandsRaisedCheck : checkListStaffPlanTable.addCell(getDataCell(checkListValues.get(elements.getCheckList14()))); break;
				  default : throw new DocumentException("Error Adding Cell in CheckList - Staff Planning Table. Unknown attribute : "+head1);
				  }
			  }
			  addTableToChapter(checkListStaffPlanTable);
			  
			  addChapterToDocument();
		  }
		  
		  private void addApproverComments(String comments) throws DocumentException{
			  //openChapter(heading, number)
			  Phrase phrase1 = new Phrase(MSSPConstants.comments,subFont);
			  Phrase phrase2 = new Phrase(comments,smallData);
			  Paragraph para = new Paragraph();
			  addEmptyLine(para, 2);
			  para.add(phrase1);
			  addEmptyLine(para, 1);
			  para.add(phrase2);
			  document.add(para);
		  }
		  
		  private void addTop3Data(List<SolutionADR> list, String subHead) throws DocumentException{			  
			  addNewSecionToChapter(subHead);
			  PdfPTable table = new PdfPTable(5);
			  table.addCell(getHeadCell(MSSPConstants.adrStatement));
			  table.addCell(getHeadCell(MSSPConstants.adrArea));
			  table.addCell(getHeadCell(MSSPConstants.adrType));
			  table.addCell(getHeadCell(MSSPConstants.impact));
			  table.addCell(getHeadCell(MSSPConstants.weightage));
			  
			  if(list != null && list.size() > 0){
			  for(SolutionADR solutionADR : list){
				  
				  if(solutionADR.getAdrstatement()!=null && solutionADR.getAdrstatement()!=""){
					  table.addCell(getDataCell(solutionADR.getAdrstatement()));
				  }else{
					  table.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
				  
				  if(solutionADR.getAdrarea()!=null && solutionADR.getAdrarea()!=""){
					  table.addCell(getDataCell(solutionADR.getAdrarea()));
				  }else{
					  table.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
				  
				  if(solutionADR.getAdrtype()!=null && solutionADR.getAdrtype()!=""){
					  table.addCell(getDataCell(solutionADR.getAdrtype()));
				  }else{
					  table.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
				  
				  if(solutionADR.getAdrimpact()!=null && solutionADR.getAdrimpact()>=0){
					  table.addCell(getDataCell(Integer.toString(solutionADR.getAdrimpact())));
				  }else{
					  table.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
				  
				  if(solutionADR.getAdrweightage()!=null && solutionADR.getAdrweightage()>=0){
					  table.addCell(getDataCell(Float.toString(solutionADR.getAdrweightage())));
				  }else{
					  table.addCell(getDataCell(MSSPConstants.NotAvailable));
				  }
				  
			  }
			  }else{
				  addNoDataFoundCell(5, table);
			  }
			  
			  addTableToChapter(table);
			  
			  
		  }
		  
		  private void addCommercials(ExecSummary summary) throws DocumentException{
			  
			  int tableSpan = summary.getOtherCost().size()+2;
			  //logger.info("summary.getOtherCost() = "+summary.getOtherCost().size());
			  openChapter(MSSPConstants.commercials, 6);
			  addNewSecionToChapter(MSSPConstants.costSummary);
			  financialTable = new PdfPTable(summary.getOtherCost().size()+2);
			  financialTable.setWidthPercentage(100);
			  
			  int[] widths = getTableWidth(summary);
			  
			  //int[] widths = {23,5,5,5,5,5,5,5,5,7};
			  financialTable.setWidths(widths);
			  
			  // Currency : INR
			  addTableSpanCell(MSSPConstants.currency+summary.getCurrency(), tableSpan);
			  
			  // Particulars List
			  populateParticularsCellList(summary);
			  addCellsFromListToTable(cellList);
			  
			// EGIL Revenue
			  addTableSpanCell(MSSPConstants.egilRevenue, tableSpan);
			  
			  // Manpower revenue List
			  populateManpowerRevenueCellList(summary);
			  addCellsFromListToTable(manpowerRevenueCellList);
			  
			  // Travel Revenue List
			  populateTravelRevenueCellList(summary);
			  addCellsFromListToTable(travelRevenueCellList);
			  
			  // Others revenue List
			  populateOthersRevenueCellList(summary);
			  addCellsFromListToTable(othersRevenueCellList);
			  
			  
			  //  Grand Total revenue List
			  populateGrandTotalRevenueCellList(summary);
			  addCellsFromListToTable(grandTotalRevenueCellList);
			  
			// EGIL Cost
			  addTableSpanCell(MSSPConstants.egilCost, tableSpan);
			  
			  
			  // Manpower Cost List
			  populateManpowerCostCellList(summary);
			  addCellsFromListToTable(manpowerCostCellList);
			  
			  
			  // Travel Cost List
			  populateTravelCostList(summary);
			  addCellsFromListToTable(travelCostCellList);
			  		
			  
			  // Others Cost List
			  populateOthersCostList(summary);
			  addCellsFromListToTable(othersCostCellList);
			  
			  // Grand total Cost List
			  populateGrandTotalCostCellList(summary);
			  addCellsFromListToTable(grandTotalCostCellList);
			  
			  
			  // Calculate Markup Non Manpower Cost Cell List
			  populateMarkUpNonManpowerCellList(summary);
			  addCellsFromListToTable(markupNonManPowerCostCellList);
			  
			  
			  // calculate Percent Markup Non Manpower Cost Cell List
			  populatePercntMarkUpNonManpowerCellList(summary);
			  addCellsFromListToTable(percntMarkupNonManPowerCostCellList);
			  
			  
			  // Onsite Effort (in Hours)
			  populateOnsiteEffortCellList(summary);
			  addCellsFromListToTable(onsiteEffortCellList);
			  
			  
			// Offshore Effort (in Hours)
			  populateOffshoreEffortCellList(summary);
			  addCellsFromListToTable(offshoreEffortCellList);
			  
			  // Grand Total Effort
			  populateGrandTotalEffortCellList(summary);
			  addCellsFromListToTable(grandTotalEffortCellList);
			  
			  // Key Ratios
			  addTableSpanCell(MSSPConstants.keyRatios, tableSpan);
			  
			  // Onsite Effort To Total Effort
			  onsiteEffortToTotalEffortDataList = populateKeyRatios(summary,onsiteEffortDataList,grandTotalEffortDataList);
			  onsiteEffortToTotalEffortCellList = populateCellListFromDataList(MSSPConstants.onsiteEffortToTotalEffort,onsiteEffortToTotalEffortDataList);
			  addCellsFromListToTable(onsiteEffortToTotalEffortCellList);
			  
			  // Offshore Effort To Total Effort
			  offshoreEffortToTotalEffortDataList = populateKeyRatios(summary,offshoreEffortDataList,grandTotalEffortDataList);
			  offshoreEffortToTotalEffortCellList = populateCellListFromDataList(MSSPConstants.offshoreEffortToTotalEffort, offshoreEffortToTotalEffortDataList);
			  addCellsFromListToTable(offshoreEffortToTotalEffortCellList);
			  
			  
			  // Blended Cost / Hour
			  blendedCostPerHourDataList = populateKeyRatios(summary, manpowerCostDataList, grandTotalEffortDataList);
			  blendedCostPerHourCellList = populateCellListFromDataList(MSSPConstants.blendedCost, blendedCostPerHourDataList);
			  addCellsFromListToTable(blendedCostPerHourCellList);
			  
			  
			  // Normal Rate Cost as % of Overall Cost
			  //hardcoded values are used in jsp so not printing in PDF.
			  
			  // Hot Skill Rate as % of overall cost	
			  // hardcoded value are used in jsp so not printing in PDF.
			  
			  // Manpower as % of Total cost
			  manpowerAsPercntTotCostdataList = populateKeyRatios(summary, manpowerCostDataList, grandTotalCostDataList);
			  manpowerAsPercntTotCostCellList = populateCellListFromDataList(MSSPConstants.manpowerAsPercntTotalCost, manpowerAsPercntTotCostdataList);
			  addCellsFromListToTable(manpowerAsPercntTotCostCellList);
			  
			  
			  // Travel as % of Total cost
			  travelAsPercntTotCostdataList = populateKeyRatios(summary, travelCostDataList, grandTotalCostDataList);
			  travelAsPercntTotCostCellList = populateCellListFromDataList(MSSPConstants.travelAsPercntTotalCost, travelAsPercntTotCostdataList);
			  addCellsFromListToTable(travelAsPercntTotCostCellList);
			  
			  
			  // Other as % of Total cost	
			  othersAsPercntTotCostdataList = populateKeyRatios(summary, othersCostDataList, grandTotalCostDataList);
			  othersAsPercntTotCostCellList = populateCellListFromDataList(MSSPConstants.otherAsPercntTotalCost, othersAsPercntTotCostdataList);
			  addCellsFromListToTable(othersAsPercntTotCostCellList);
			  
			  // Travel cost per Onsite Hour	
			  travelCostPerOnsiteHourDataList = populateKeyRatios(summary, travelCostDataList, onsiteEffortDataList);
			  travelCostPerOnsiteHourCellList = populateCellListFromDataList(MSSPConstants.travelCostPerOnsiteHour, travelCostPerOnsiteHourDataList);
			  addCellsFromListToTable(travelCostPerOnsiteHourCellList);
			  
			  addTableToChapter(financialTable);
			  
			  // terms and conditions.
			  loadTermsAndConditions();
			  addChapterToDocument();
		  }
		  
		  private void loadTermsAndConditions(){
			  addNewSecionToChapter(MSSPConstants.termsAndCond);
			  Properties prop = new Properties();
			  InputStream input = null;
			  
			  try {
				  
					String filename = "TermsAndConditions.properties";
					input = getClass().getClassLoader().getResourceAsStream(filename);
					if (input == null) {
						System.out.println("Sorry, unable to find " + filename);
						return;
					}
			 
					prop.load(input);
			 
					Enumeration<?> e = prop.propertyNames();
					while (e.hasMoreElements()) {
						String key = (String) e.nextElement();
						String value = prop.getProperty(key);
						//System.out.println("Key : " + key + ", Value : " + value);
						addParaToChapter(new Paragraph(value));
					}
			 
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					if (input != null) {
						try {
							input.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		  }
		  
		  private void populateMarkUpNonManpowerCellList(ExecSummary summary){
			  
			  

				if("PO".equals(summary.getInvoiceType())){
					for(int i=0;i<grandTotalRevenueDataList.size();i++){
						markupNonManpowerDataList.add(roundOffFloatValue(grandTotalRevenueDataList.get(i) - grandTotalCostDataList.get(i)));
					}					
				}else{
								
					for(int i=0;i<grandTotalRevenueDataList.size();i++){
						Float val = (grandTotalRevenueDataList.get(i)-manpowerRevenueDataList.get(i))-(grandTotalCostDataList.get(i)-manpowerCostDataList.get(i));
						markupNonManpowerDataList.add(roundOffFloatValue(val));
					}
					
				}
				
				markupNonManPowerCostCellList.add(getHeadCell(MSSPConstants.markupNonManPowerCost));
				for(Float val : markupNonManpowerDataList){
					markupNonManPowerCostCellList.add(getDataCell(Float.toString(val)));
				}	  
			  
		  }
		  
		  private void populatePercntMarkUpNonManpowerCellList(ExecSummary summary){
			  
			    //logger.info("Invoice Type = "+summary.getInvoiceType());
				if("PO".equals(summary.getInvoiceType())){
					logger.info("Executing if condition");
					for(int i=0;i<grandTotalCostDataList.size();i++){
						if(grandTotalCostDataList.get(i)!=0){
						  Float val = markupNonManpowerDataList.get(i)/grandTotalCostDataList.get(i);	
						  percntMarkupNonManpowerDataList.add(roundOffFloatValue(val));
						}else{
						  percntMarkupNonManpowerDataList.add(roundOffFloatValue(0f));
						}
						
					  }
				}else{
					//logger.info("Executing else condition");
					// COT is not defined in JSP so not handling in PDF.
					for(int i=0;i<markupNonManpowerDataList.size();i++){
						if(travelCostDataList.get(i)!=0){
						Float val = markupNonManpowerDataList.get(i)/travelCostDataList.get(i);
						percntMarkupNonManpowerDataList.add(roundOffFloatValue(val));
						}else{
							percntMarkupNonManpowerDataList.add(roundOffFloatValue(0f));
						}
					}
					}
				percntMarkupNonManPowerCostCellList.add(getHeadCell(MSSPConstants.percntMarkupNonManPowerCost));
				for(Float val : percntMarkupNonManpowerDataList){
					percntMarkupNonManPowerCostCellList.add(getDataCell(Float.toString(val)));
				}
				
				
		  }
		  
		  private Float roundOffFloatValue(Float val){			  
			  return Math.round(val*100.0f)/100.0f;
		  }
		  
		  private void addCellsFromListToTable(List<PdfPCell> list){
			  for(PdfPCell singleCell : list){
				  financialTable.addCell(singleCell);
			  }
		  }
		  
		  private void addTableSpanCell(String str,int tableSpan){
			  PdfPCell cell = new PdfPCell(new Phrase(str));
			  cell.setColspan(tableSpan);
			  cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			  financialTable.addCell(cell);	
		  }
		  
		  private void populateParticularsCellList(ExecSummary summary){
			  PdfPCell cell = getHeadCell(MSSPConstants.particulars);
			  cellList.add(cell);
			  for(Row row : summary.getOtherCost()){
				  cellList.add(getHeadCell(Integer.toString(row.getYear())));
			  }
			  cell = getHeadCell(MSSPConstants.grandTotal);
			  cellList.add(cell);
		  }
		  
		  private void populateManpowerRevenueCellList(ExecSummary summary){
			  float sum = 0;
			  PdfPCell cell = getHeadCell1(MSSPConstants.manpower);
			  manpowerRevenueCellList.add(cell);
			  for(Row row : summary.getLaborCost()){
				  manpowerRevenueDataList.add(row.getRevenueValue());
				  manpowerRevenueCellList.add(getDataCell(Float.toString(row.getRevenueValue())));
				  sum = sum + row.getRevenueValue();
			  }
			  manpowerRevenueDataList.add(sum);
			  manpowerRevenueCellList.add(getDataCell(Float.toString(sum)));
		  }
		  
		  private void populateTravelRevenueCellList(ExecSummary summary){
			  float sum = 0;
			  PdfPCell cell = getHeadCell1(MSSPConstants.travel);
			  travelRevenueCellList.add(cell);
			  for(Row row : summary.getTravelCost()){
				  travelRevenueDataList.add(row.getRevenueValue());
				  travelRevenueCellList.add(getDataCell(Float.toString(row.getRevenueValue())));
				  sum = sum + row.getRevenueValue();
			  }
			  travelRevenueDataList.add(sum);
			  travelRevenueCellList.add(getDataCell(Float.toString(sum)));
		  }
		  
		  private void populateOthersRevenueCellList(ExecSummary summary){
			  float sum = 0;
			  PdfPCell cell = getHeadCell1(MSSPConstants.others);
			  othersRevenueCellList.add(cell);
			  for(Row row : summary.getOtherCost()){
				  othersRevenueDataList.add(row.getRevenueValue());
				  othersRevenueCellList.add(getDataCell(Float.toString(row.getRevenueValue())));
				  sum = sum + row.getRevenueValue();
			  }
			  othersRevenueDataList.add(sum);
			  othersRevenueCellList.add(getDataCell(Float.toString(sum)));
		  }
		  
		  private void populateGrandTotalRevenueCellList(ExecSummary summary){
			  float sum = 0;
			  grandTotalRevenueCellList.add(getHeadCell(MSSPConstants.grandTotalRevenue));
			  for(Float val : summary.getGrandTotalRevenue()){
				  grandTotalRevenueDataList.add(val);
				  grandTotalRevenueCellList.add(getDataCell(Float.toString(val)));
				  sum = sum + val;
			  }
			  grandTotalRevenueDataList.add(sum);
			  grandTotalRevenueCellList.add(getDataCell(Float.toString(sum)));
		  }
		  
		  private void populateManpowerCostCellList(ExecSummary summary){
			  float sum = 0;
			  PdfPCell cell = getHeadCell1(MSSPConstants.manpower);
			  manpowerCostCellList.add(cell);
			  for(Row row : summary.getLaborCost()){
				  manpowerCostDataList.add(row.getCostValue());
				  manpowerCostCellList.add(getDataCell(Float.toString(row.getCostValue())));
				  sum = sum + row.getCostValue();
			  }
			  manpowerCostDataList.add(sum);
			  manpowerCostCellList.add(getDataCell(Float.toString(sum)));
		  }
		  
		  private void populateTravelCostList(ExecSummary summary){
			  float sum = 0;
			  travelCostCellList.add(getHeadCell1(MSSPConstants.travel));
			  for(Row row : summary.getTravelCost()){
				  travelCostDataList.add(roundOffFloatValue(row.getCostValue()));
				  travelCostCellList.add(getDataCell(Float.toString(roundOffFloatValue(row.getCostValue()))));
				  sum = sum + row.getCostValue();
			  }
			  travelCostDataList.add(roundOffFloatValue(sum));
			  travelCostCellList.add(getDataCell(Float.toString(roundOffFloatValue(sum))));
		  }
		  
		  private void populateOthersCostList(ExecSummary summary){
			  float sum = 0;
			  othersCostCellList.add(getHeadCell1(MSSPConstants.others));
			  for(Row row : summary.getOtherCost()){
				  othersCostDataList.add(roundOffFloatValue(row.getCostValue()));
				  othersCostCellList.add(getDataCell(Float.toString(row.getCostValue())));
				  sum = sum + row.getCostValue();
			  }
			  othersCostDataList.add(roundOffFloatValue(sum));
			  othersCostCellList.add(getDataCell(Float.toString(sum)));
		  }
		  
		  private void populateGrandTotalCostCellList(ExecSummary summary){
			  float sum = 0;
			  grandTotalCostCellList.add(getHeadCell(MSSPConstants.grandTotalCost));
			  for(Float val : summary.getGrandTotalCost()){
				  grandTotalCostDataList.add(val);
				  grandTotalCostCellList.add(getDataCell(Float.toString(val)));
				  sum = sum + val;
			  }
			  grandTotalCostDataList.add(sum);
			  grandTotalCostCellList.add(getDataCell(Float.toString(sum)));
		  }
		  
		  private void populateOnsiteEffortCellList(ExecSummary summary){
			  float sum = 0;
			  onsiteEffortCellList.add(getHeadCell1(MSSPConstants.onsiteEffort));
			  for(Row row : summary.getTravelCost()){
				  onsiteEffortDataList.add(row.getFteValue());
				  onsiteEffortCellList.add(getDataCell(Float.toString(row.getFteValue())));
				  sum = sum + row.getFteValue();
			  }
			  onsiteEffortDataList.add(sum);
			  onsiteEffortCellList.add(getDataCell(Float.toString(sum)));
		  }
		  
		  private void populateOffshoreEffortCellList(ExecSummary summary){
			  float sum = 0;
			  offshoreEffortCellList.add(getHeadCell1(MSSPConstants.offshoreEffort));
			  for(Row row : summary.getOtherCost()){
				  offshoreEffortDataList.add(roundOffFloatValue(row.getFteValue()));
				  offshoreEffortCellList.add(getDataCell(Float.toString(roundOffFloatValue(row.getFteValue()))));
				  sum = sum + row.getFteValue();
			  }
			  offshoreEffortDataList.add(roundOffFloatValue(sum));
			  offshoreEffortCellList.add(getDataCell(Float.toString(roundOffFloatValue(sum))));
		  }
		  
		  private void populateGrandTotalEffortCellList(ExecSummary summary){
			  float sum = 0;
			  grandTotalEffortCellList.add(getHeadCell1(MSSPConstants.grandTotalEffort));
			  for(Float val : summary.getGrandTotalEffort()){
				  grandTotalEffortDataList.add(roundOffFloatValue(val));
				  grandTotalEffortCellList.add(getDataCell(Float.toString(roundOffFloatValue(val))));
				  sum  = sum + val;
			  }
			  grandTotalEffortDataList.add(roundOffFloatValue(sum));
			  grandTotalEffortCellList.add(getDataCell(Float.toString(roundOffFloatValue(sum))));
		  }
		  
		  // Ratios
		  private List<Float> populateKeyRatios(ExecSummary summary,List<Float> list1, List<Float> list2){
			  List<Float> listOfRatios = new ArrayList<Float>();
			  float ratio = 0;
			  
			  for(int i=0;i<summary.getOtherCost().size()+1;i++){
				  if(list1.get(i) == 0 || list2.get(i) == 0){
					  ratio = 0;
					  
				  }else{
					  ratio = list1.get(i)/list2.get(i);
				  }
				  ratio = roundOffFloatValue(ratio);
				  listOfRatios.add(ratio);
			  }
			  return listOfRatios;
		  }
		  
		  private List<PdfPCell> populateCellListFromDataList(String head, List<Float> dataList){
			  List<PdfPCell> cellList = new ArrayList<PdfPCell>();
			  cellList.add(getHeadCell1(head));
			  for(int i=0;i<dataList.size();i++){
				  cellList.add(getDataCell(Float.toString(roundOffFloatValue(dataList.get(i)))));
			  }
			  return cellList;
		  }
		  
		    
		  private int[] getTableWidth(ExecSummary summary){
			  int[] widths = new int[summary.getOtherCost().size()+2];
			  widths[0] = 23;
			  for(int i=1;i<=summary.getOtherCost().size();i++){
				  widths[i] = 5;
			  }
			  widths[summary.getOtherCost().size()+1] = 7;
			  
			  return widths;
		  }
		  
		  private void addNoDataFoundCell(int tableSpan, PdfPTable table){

			  PdfPCell cell = new PdfPCell(new Phrase(MSSPConstants.noDataFound,smallData));
			  cell.setColspan(tableSpan);
			  cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(cell);	
		  
		  }
		  
		  private void displayAllListsSize(){
			  logger.info("cellList = "+cellList.size());
			  logger.info("manpowerRevenueCellList = "+manpowerRevenueCellList.size());
			  logger.info("manpowerRevenueDataList = "+manpowerRevenueDataList.size());
			  logger.info("travelRevenueCellList = "+travelRevenueCellList.size());
			  logger.info("travelRevenueDataList = "+travelRevenueDataList.size());
			  logger.info("othersRevenueCellList = "+othersRevenueCellList.size());
			  logger.info("othersRevenueDataList = "+othersRevenueDataList.size());
			  logger.info("grandTotalRevenueCellList = "+grandTotalRevenueCellList.size());
			  logger.info("grandTotalRevenueDataList = "+grandTotalRevenueDataList.size());
			  logger.info("manpowerCostCellList = "+manpowerCostCellList.size());
			  logger.info("manpowerCostDataList = "+manpowerCostDataList.size());
			  logger.info("travelCostCellList = "+travelCostCellList.size());
			  logger.info("travelCostDataList = "+travelCostDataList.size());
			  logger.info("othersCostCellList = "+othersCostCellList.size());
			  logger.info("othersCostDataList = "+othersCostDataList.size());
			  logger.info("grandTotalCostCellList = "+grandTotalCostCellList.size());
			  logger.info("grandTotalCostDataList = "+grandTotalCostDataList.size());
			  logger.info("markupNonManPowerCostCellList = "+markupNonManPowerCostCellList.size());
			  logger.info("markupNonManpowerDataList = "+markupNonManpowerDataList.size());
			  logger.info("percntMarkupNonManPowerCostCellList = "+percntMarkupNonManPowerCostCellList.size());
			  logger.info("percntMarkupNonManpowerDataList = "+percntMarkupNonManpowerDataList.size());
			  logger.info("onsiteEffortCellList = "+onsiteEffortCellList.size());
			  logger.info("onsiteEffortDataList = "+onsiteEffortDataList.size());
			  logger.info("offshoreEffortCellList = "+offshoreEffortCellList.size());
			  logger.info("offshoreEffortDataList = "+offshoreEffortDataList.size());
			  logger.info("grandTotalEffortCellList = "+grandTotalEffortCellList.size());
			  logger.info("grandTotalEffortDataList = "+grandTotalEffortDataList	.size());
			  logger.info("onsiteEffortToTotalEffortCellList = "+onsiteEffortToTotalEffortCellList.size());
			  logger.info("onsiteEffortToTotalEffortDataList = "+onsiteEffortToTotalEffortDataList.size());
			  logger.info("offshoreEffortToTotalEffortCellList = "+offshoreEffortToTotalEffortCellList.size());
			  logger.info("offshoreEffortToTotalEffortDataList = "+offshoreEffortToTotalEffortDataList	.size());
			  logger.info("blendedCostPerHourCellList = "+blendedCostPerHourCellList.size());
			  logger.info("blendedCostPerHourDataList = "+blendedCostPerHourDataList.size());
			  logger.info("manpowerAsPercntTotCostCellList = "+manpowerAsPercntTotCostCellList.size());
			  logger.info("manpowerAsPercntTotCostdataList = "+manpowerAsPercntTotCostdataList.size());
			  logger.info("travelAsPercntTotCostCellList = "+travelAsPercntTotCostCellList.size());
			  logger.info("travelAsPercntTotCostdataList = "+travelAsPercntTotCostdataList.size());
			  logger.info("othersAsPercntTotCostCellList = "+othersAsPercntTotCostCellList.size());
			  logger.info("othersAsPercntTotCostdataList = "+othersAsPercntTotCostdataList.size());
			  logger.info("travelCostPerOnsiteHourCellList = "+travelCostPerOnsiteHourCellList.size());
			  logger.info("travelCostPerOnsiteHourDataList = "+travelCostPerOnsiteHourDataList.size());
		  }
}

