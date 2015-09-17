package com.ericsson.mssp.admin.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
/*import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.CountryDTO;
import com.ericsson.mssp.common.dto.SearchDTO;
import com.ericsson.mssp.common.dto.UserDTO;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.search.controller.SearchController;
import com.ericsson.mssp.search.service.ISearchService;
import com.ericsson.mssp.solution.controller.BaseController;
import com.ericsson.mssp.solution.service.ISolutionService;

@Controller
public class SolutionManagementController extends BaseController implements MSSPConstants{

	
	@Autowired
	private ISolutionService solutionService;
	
	@Autowired
	ISearchService searchService;
	
	public static Logger logger = Logger.getLogger(SearchController.class);
	
	String message = "";
	String successMessage = "";
	
	
    private ArrayList<ArrayList> csvData = null;
    private int maxRowWidth = 0;
    
   /* private Workbook workbook = null;
    private DataFormatter formatter = null;
    private FormulaEvaluator evaluator = null;*/
	
	@ModelAttribute("regions")
	public Map<String,String> listCountries()
	{
		Map<String,String> a = new HashMap<String, String>();
		List<CountryDTO> regions = searchService.getRegions();
		for(int i = 0;i<regions.size();i++){
			
				if(!a.containsValue(regions.get(i).getRegion())){
				a.put(regions.get(i).getRegion(), regions.get(i).getRegion());
				}
		}
		return a;
	}
	
	@ModelAttribute("representatives")
	public List<UserDTO> listRepresentatives(Map<String, Object> map)
	{
		List<UserDTO> representativesList = new ArrayList<UserDTO>();
		representativesList = solutionService.getRepresentatives();
		return representativesList;
	}
	
	@RequestMapping(value="/admin/solutionManagement")
	public String openSolution(ModelMap model)
	{
		String mes = "";
		mes = message;
		message = "";
		logger.info("inside solution management");
		SearchDTO searchDTO = new SearchDTO();
		SearchDTO sdt = new SearchDTO();
		model.put("message", mes);
		model.put("searchDTO", searchDTO);
		model.put("sdt", sdt);
		return "solutionManagement";
	}
	
	@RequestMapping(value="/admin/listSolutions")
	public String listSolutions(ModelMap model,@ModelAttribute("sdt")SearchDTO sdt,@RequestParam("dataString") String dataString)
	{
		logger.info("inside list opportunities");
				
		String [] data = dataString.split(",");
		
		for(int i=0;i<data.length;i++){
		    switch(i)
			{
			case 0:
			  sdt.setOpportunityName(data[0]);
			  break;
			case 1:
			  sdt.setCustomerName(data[1]);
			  break;
			case 2:
			 sdt.setRegion(data[2]);
			  break;
			default:
			  //
			}
		}
		
				
		List<SearchDTO> search = new ArrayList<SearchDTO>();
		List<SearchDTO> resultList = new ArrayList<SearchDTO>();
		
		search = searchService.getOpportunities(sdt);
		
		for(SearchDTO sDTO : search){
			logger.debug("Iterating SearchDTO for Solution Ids.");
			Integer solutionId = sDTO.getSolutionID();
			if(solutionId!=null){
				resultList.add(sDTO);
			}
		}
		logger.info("search list size : " + resultList.size());
		String mes = new String();
		mes = "";
		if(search.size() == 0)
		{
			mes = "<ul><li>No Result Found</li></ul>";
		}
		else
		{
			/*mes= message;
			message = "";*/
		}
				
		SearchDTO searchDTO = new SearchDTO();
		logger.info("message value set : " + mes);
		model.put("message", mes);	
		model.put("searchDTO", searchDTO);	
		model.put("resultList",resultList);
		return "solutionList";
	}
	
	
	@RequestMapping(value="/admin/listOpportunities")
	public String listOpportunities(ModelMap model,@ModelAttribute("searchDTO")SearchDTO searchDTO)
	{
		logger.info("inside admin list opportunities");
		logger.info("opportunity name : "
				+ searchDTO.getOpportunityName()+" | customer name : "
				+ searchDTO.getCustomerName()+ " | region : "
				+ searchDTO.getRegion()
				);
		
		if(("".equals(searchDTO.getOpportunityName())||(searchDTO.getOpportunityName() == null))
				&& ("".equals(searchDTO.getCustomerName())||(searchDTO.getCustomerName() == null))
				&& ("".equals(searchDTO.getRegion())||(searchDTO.getRegion() == null))
				){
			message = "<ul><li>"+ApplicationPropertiesUtil.getProperty("msg.search.selectAnyOne")+"</li></ul>";
			return "redirect:/admin/solutionManagement";
		}
		
		List<SearchDTO> search = new ArrayList<SearchDTO>();
		
		search = searchService.getOpportunitiesToBeReAssigned(searchDTO);
		logger.info("search list size : " + search.size());
		String mes = new String();
		String mess = "";
		mes = "";
		if(search.size() == 0)
		{
			mes = "<ul><li>"+ApplicationPropertiesUtil.getProperty("msg.solution.noResult")+"</li></ul>";
		}
		else
		{
			mes= message;
			message = "";
		}
		mess = successMessage;
		successMessage = "";
		SearchDTO sdt = new SearchDTO();
		logger.info("message value set : " + mes);
		model.put("message", mes);
		model.put("successMessage", mess);
		model.put("searchList",search);
		model.put("sdt",sdt);
		return "solutionManagement";
	}
	
	@RequestMapping(value="/admin/viewRecordsToReAssign")
	public String viewRecords(ModelMap model,HttpSession session,@ModelAttribute("searchDTO")SearchDTO searchDTO)
	{
		message = "";
		logger.info("inside reassign view records");
		logger.info("selected : " + searchDTO.getSelected());
		logger.info(searchDTO.getCustomerName()+"|"+searchDTO.getOpportunityName()+"|"+searchDTO.getCurrencyCode());
		int i=0;
		boolean flag = false;
		
		
		if(searchDTO.getSelected() != null){
			try{
				
				String []temp = searchDTO.getCurrencyCode().split(",");
				for(;i<temp.length;i++){
					if(temp[i].trim().length() == 7){
						flag = true;
						break;
					}
					else
						continue;
				}
				
				if(flag){
				searchDTO.setCurrencyCode(temp[i]);
				String id = searchService.updateOpportunity(searchDTO);
				successMessage = ApplicationPropertiesUtil.getProperty("msg.solution.management.update");
				}
				else{
					message = "<ul><li>"+ApplicationPropertiesUtil.getProperty("msg.solution.selectAssignee")+"</li></ul>";
				}
			}catch(Exception e){
				logger.info(e.getMessage());
			}
			model.put("searchDTO",searchDTO);
			return "forward:./listOpportunities";
		}
		else{
			message = "<ul><li>"+ApplicationPropertiesUtil.getProperty("msg.search.selectToReAssign")+"</li></ul>";
			model.put("message",message);
			model.put("searchDTO",searchDTO);
			return "forward:./listOpportunities";
		}
	}
	
	/*@RequestMapping(value="/admin/upload")
	public @ResponseBody String loadData(@RequestParam("fileName")String fileName){
		String rValue = "false";
		int fileSizeLimit = Integer.parseInt(ApplicationPropertiesUtil.getProperty("msg.admin.fileSizeLimit"));
		logger.info("file name received : " + fileName);
		
		String []extensions = {"csv","txt","xls"};
		boolean validExtension = false;
		String filename = "";
		String ext = "";
		int mid;
		
		String filePath = ApplicationPropertiesUtil.getProperty("msg.admin.filesPath");
		
		try{
			mid = fileName.lastIndexOf(".");
			filename = fileName.substring(0,mid).toLowerCase();
			ext=fileName.substring(mid+1,fileName.length()).toLowerCase();
			logger.info("file name : " + filename +"| ext : " + ext);
		}catch(Exception e){
			rValue = "<ul><li>"+ApplicationPropertiesUtil.getProperty("msg.solution.management.extRequired")+"</li></ul>";
			return rValue;
		}
		
		
		for(int counter=0;counter<extensions.length;counter++){
			if(extensions[counter].equals(ext)){
				validExtension = true;
				break;
			}
		}
		if(!validExtension){
			rValue = "<ul><li>"+ApplicationPropertiesUtil.getProperty("msg.solution.management.invalidExt")+"</li></ul>";
			return rValue;
		}
		if(ext.equalsIgnoreCase("xls")){
			fileName = cToCSV(filePath,fileName);
		}
		File f = new File(filePath+fileName);
		if(!(((double)f.length()/(1024*1024)) < fileSizeLimit)){
			rValue = "<ul><li>"+ApplicationPropertiesUtil.getProperty("msg.solution.management.fileSize")+"</li></ul>";
			return rValue;
		}
		
		if(solutionService.updateTable(fileName,filePath,filename)){
			rValue = "true";
		}else{
			rValue = "<ul><li>"+ApplicationPropertiesUtil.getProperty("msg.solution.management.contentFormatError")+"</li></ul>";
		}
		return rValue;
	}*/
	
	
	@RequestMapping(value="/admin/deleteSolution")
	public String deleteSolution(ModelMap model,HttpSession session,@ModelAttribute("sdt")SearchDTO sdt,
			@RequestParam("solId") String solId,@RequestParam("dataString") String dataString){
		logger.info("Deleting solution"+solId);
		try {
			boolean deleted = searchService.deleteSolution(solId);
			logger.debug("Operation returned with result :: " + deleted);
			
			List<SearchDTO> search = new ArrayList<SearchDTO>();
			List<SearchDTO> resultList = new ArrayList<SearchDTO>();
			
			String [] data = dataString.split(",");
			
			for(int i=0;i<data.length;i++){
			    switch(i)
				{
				case 0:
				  sdt.setOpportunityName(data[0]);
				  break;
				case 1:
				  sdt.setCustomerName(data[1]);
				  break;
				case 2:
				 sdt.setRegion(data[2]);
				  break;
				default:
				  //
				}
			}
			
			search = searchService.getOpportunities(sdt);
			
			for(SearchDTO sDTO : search){
				logger.debug("Iterating SearchDTO for Solution Ids.");
				Integer solutionId = sDTO.getSolutionID();
				if(solutionId!=null){
					resultList.add(sDTO);
				}
			}
			model.put("resultList",resultList);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "solutionList";		
	}
	
}
	/*public String cToCSV(String filePath, String fileName){
		String filename = "";
		try {
			SolutionManagementController converter = new SolutionManagementController();
            converter.openWorkbook(filePath+fileName);
            converter.convertToCSV();
            
           int mid = fileName.lastIndexOf(".");
			filename = fileName.substring(0,mid);
			logger.info(" ***** creating file : " + filePath+filename +".csv ****** ");
            converter.saveCSVFile(filePath+filename+".csv", ",");
        }
        catch(Exception ex) {
            System.out.println("Caught an: " + ex.getClass().getName());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Stacktrace follows:.....");
            ex.printStackTrace(System.out);
            return null;
        }
		return filename+".csv";
	}
	
	 public void openWorkbook(String filename) throws FileNotFoundException,
     IOException, InvalidFormatException {
	File file = null;
	FileInputStream fis = null;
	try {
		file = new File(filename);
		fis = new FileInputStream(file);
		this.workbook = WorkbookFactory.create(fis);
		this.evaluator =
		this.workbook.getCreationHelper().createFormulaEvaluator();
		this.formatter = new DataFormatter();
	}
	finally {
		if(fis != null) {
			fis.close();
		}
	}
}

public void convertToCSV() {
Sheet sheet = null;
Row row = null;
int lastRowNum = 0;
this.csvData = new ArrayList<ArrayList>();

// Discover how many sheets there are in the workbook....
int numSheets = this.workbook.getNumberOfSheets();

// and then iterate through them.
for(int i = 0; i < numSheets; i++) {

sheet = this.workbook.getSheetAt(i);
if(sheet.getPhysicalNumberOfRows() > 0) {

lastRowNum = sheet.getLastRowNum();
for(int j = 0; j <= lastRowNum; j++) {
row = sheet.getRow(j);
this.rowToCSV(row);
}
}
}
}

public void saveCSVFile(String filename, String separator)
             throws
FileNotFoundException,
                    IOException {
File file = null;
FileWriter fw = null;
BufferedWriter bw = null;
ArrayList<String> line = null;
StringBuffer buffer = null;
String csvLineElement = null;
try {
// Open a writer onto the CSV file.
file = new File(filename);
fw = new FileWriter(file);
bw = new BufferedWriter(fw);

for(int i = 0; i < this.csvData.size(); i++) {
buffer = new StringBuffer();

line = this.csvData.get(i);
for(int j = 0; j < this.maxRowWidth; j++) {
if(line.size() > j) {
csvLineElement = line.get(j);
if(csvLineElement != null) {
buffer.append(csvLineElement);
}
}
if(j < (this.maxRowWidth - 1)) {
buffer.append(separator);
}
}

bw.write(buffer.toString().trim());

if(i < (this.csvData.size() - 1)) {
bw.newLine();
}
}
}
finally {
if(bw != null) {
bw.flush();
bw.close();
}
}
}

private void rowToCSV(Row row) {
		Cell cell = null;
		int lastCellNum = 0;
		ArrayList<String> csvLine = new ArrayList<String>();

		if (row != null) {

			lastCellNum = row.getLastCellNum();
			for (int i = 0; i <= lastCellNum; i++) {
				cell = row.getCell(i);
				if (cell == null) {
					csvLine.add("");
				} else {
					if (cell.getCellType() != Cell.CELL_TYPE_FORMULA) {
						csvLine.add(this.formatter.formatCellValue(cell));
					} else {
						csvLine.add(this.formatter.formatCellValue(cell,
								this.evaluator));
					}
				}
			}
			if (lastCellNum > this.maxRowWidth) {
				this.maxRowWidth = lastCellNum;
			}
		}
		this.csvData.add(csvLine);
	}
}
*/