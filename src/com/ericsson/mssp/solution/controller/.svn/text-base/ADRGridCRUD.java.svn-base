package com.ericsson.mssp.solution.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.forms.CustomADRGenericResponse;
import com.ericsson.mssp.solution.forms.CustomADRResponse;
import com.ericsson.mssp.solution.forms.ManageADRForm;
import com.ericsson.mssp.solution.service.ISolutionService;

@Controller
// @RequestMapping("/solution/crud")
public class ADRGridCRUD extends BaseController {
    public static Logger logger = Logger.getLogger(ADRGridCRUD.class);

    Integer solutionID = 1;// Solution ID
    String opMode = "E";// Mode of Operation E/V

    @Autowired
    private ISolutionService solutionService;

    @RequestMapping("/solution/crud")
    public @ResponseBody
    CustomADRResponse getAll(
	    @ModelAttribute("manageADRForm") ManageADRForm manageADRForm,
	    @RequestParam(value = "filters", required = false, defaultValue = "NONE") String filters,
	    @RequestParam(value = "_search", required = false, defaultValue = "false") boolean _search,
	    @RequestParam(value = "page", required = false, defaultValue = "NONE") String page,
	    @RequestParam(value = "rows", required = false, defaultValue = "NONE") String rows,
	    @RequestParam(value = "sidx", required = false, defaultValue = "NONE") String sortField,
	    @RequestParam(value = "sord", required = false, defaultValue = "NONE") String sortOrder,
	    HttpSession session) {

	// Initialize our custom ADR response wrapper
	CustomADRResponse response = new CustomADRResponse();
	String sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? session
		.getAttribute(SOLUTION_ID).toString() : null;

	try {

	    if (null != sessSolId) {
		try {
		    solutionID = Integer.valueOf(sessSolId);
		} catch (NumberFormatException nfe) {
		    logger.info("Session solution ID not found for key:"
			    + SOLUTION_ID + " Returned value:" + sessSolId);
		}
	    } else {
		manageADRForm.setSolutionID(null);
		return response;
	    }
	    // mode of operation
	    String sessOpMode = (null != session.getAttribute(OPERATION_MODE)) ? session
		    .getAttribute(OPERATION_MODE).toString() : null;
	    if (null != sessOpMode && sessOpMode.length() == 1) {
		opMode = sessOpMode.toUpperCase();
	    }
	    List<SolutionADRDTO> aDRsList = solutionService
		    .getSolutionAllADRBySolID("NONE", "NONE", "NONE",
			    solutionID);

	    response.setSolutionADRList(aDRsList);
	    // Assign the total number of records found. This is used for paging
	    response.setRecords(String.valueOf(aDRsList.size()));

	    // Since our service is just a simple service for teaching purposes
	    // We didn't really do any paging. But normally your DAOs or your
	    // persistence layer should support this
	    // Assign a dummy page
	    response.setPage(page);

	    // Same. Assign a dummy total pages
	    response.setTotal(""
		    + Math.ceil((float) aDRsList.size()
			    / Integer.parseInt(rows)));
	} catch (Exception e) {
	    logger.info("Error in Grid data-" + e);
	    e.printStackTrace();
	}
	logger.debug("Complete");
	return response;
    }

    /**
     * 
     * Description : areaList drop down values Method Name : populateAreaList
     * Input& Output:
     * 
     * @return String
     */
    @RequestMapping(value = "/solution/areaList")
    public @ResponseBody
    String populateAreaList() {
	Map<String, String> area = ApplicationPropertiesUtil
		.getMapConfigKeyValue(ADR_AREAS);// solutionService.getAllAreas();
	StringBuffer areas = new StringBuffer("");
	for (Map.Entry<String, String> entry : area.entrySet()) {
	    areas.append(entry.getKey()).append(":").append(entry.getValue())
		    .append(";");
	}

	return areas.substring(0, areas.lastIndexOf(";"));
    }

    /**
     * 
     * Description : Type list drop down values Method Name : populateTypeList
     * Input& Output:
     * 
     * @return Map<String, String>
     */
    @RequestMapping(value = "/solution/typeList")
    public @ResponseBody
    String populateTypeList() {
	Map<String, String> type = ApplicationPropertiesUtil
		.getMapConfigKeyValue(ADR_TYPES);
	StringBuffer types = new StringBuffer("");
	for (Map.Entry<String, String> entry : type.entrySet()) {
	    types.append(entry.getKey()).append(":").append(entry.getValue())
		    .append(";");
	}

	return types.substring(0, types.lastIndexOf(";"));
    }

    /**
     * 
     * Description : Impact list drop down values Method Name :
     * populateImpactList Input& Output:
     * 
     * @return Map<String, String>
     */
    @RequestMapping(value = "/solution/impactList")
    public @ResponseBody
    String populateImpactList() {
	Map<String, String> complexities = ApplicationPropertiesUtil
		.getMapConfigKeyValue(ADR_COMPLEXITIES, false, true);
	StringBuffer complexitiess = new StringBuffer("");
	for (Map.Entry<String, String> entry : complexities.entrySet()) {
	    complexitiess.append(entry.getKey()).append(":")
		    .append(entry.getValue()).append(";");
	}

	return complexitiess.substring(0, complexitiess.lastIndexOf(";"));
    }

    /**
     * Edit the current user.
     */
    @RequestMapping(value = "/solution/cu", method = RequestMethod.POST)
    public @ResponseBody
    CustomADRGenericResponse createUpdate(
	    @RequestParam(value = "solutionAdrid", required = false, defaultValue = "NONE") String solutionAdrid,
	    @RequestParam(value = "adrStatement", required = false, defaultValue = "NONE") String adrStatement,
	    @RequestParam(value = "adrArea", required = false, defaultValue = "NONE") String adrArea,
	    @RequestParam(value = "adrType", required = false, defaultValue = "NONE") String adrType,
	    @RequestParam(value = "adrImpactString", required = false, defaultValue = "NONE") String adrImpactString,
	    @RequestParam(value = "adrWeightage", required = false, defaultValue = "NONE") String adrWeightage,
	    @RequestParam(value = "adrCategory", required = false, defaultValue = "NONE") String adrCategory,
	    HttpSession session) {
	logger.info("Received request to edit ADR" + solutionAdrid
		+ " adrCategory=" + adrCategory);
	Boolean status = false;
	Integer solutionADRId = -1;
	CustomADRGenericResponse response = new CustomADRGenericResponse();
	try {

	    SolutionADRDTO solutionADRDTO = new SolutionADRDTO();
	    solutionADRDTO.setAdrStatement(null != adrStatement ? adrStatement
		    .trim() : adrStatement);
	    solutionADRDTO.setAdrArea(adrArea.equals("NONE") ? "Technical"
		    : adrArea);
	    solutionADRDTO.setAdrType(adrType.equals("NONE") ? "Internal"
		    : adrType);
	    solutionADRDTO.setAdrImpact(adrImpactString.equals("NONE") ? 3
		    : adrImpactString.equalsIgnoreCase("High") ? 1
			    : adrImpactString.equalsIgnoreCase("Medium") ? 2
				    : 3);
	    try {
		solutionADRDTO.setAdrWeightage(adrWeightage.equals("NONE") ? 0
			: Float.valueOf(adrWeightage));
	    } catch (NumberFormatException nfe) {
		solutionADRDTO.setAdrWeightage(0f);
		logger.info("Number format is not correct:" + adrWeightage);
		nfe.printStackTrace();
	    }

	    solutionADRDTO
		    .setAdrCategory(adrCategory.equals("NONE") ? ADR_ASSUM_CATEGORY
			    : adrCategory);
	    String sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? session
		    .getAttribute(SOLUTION_ID).toString() : null;

	    if (null != sessSolId) {
		try {
		    solutionID = Integer.valueOf(sessSolId);
		} catch (NumberFormatException nfe) {
		    logger.info("Session solution ID not found for key:"
			    + SOLUTION_ID + " Returned value:" + sessSolId);
		    nfe.printStackTrace();
		}
	    } else {
		solutionADRDTO.setSolutionId(null);
		response.setSuccess(false);
		response.setMessage("Action failure!");
		return response;
		// "Solution ID not found so action rejected by the system";
	    }
	    solutionADRDTO.setSolutionId(solutionID);
	    if (solutionAdrid != null && !solutionAdrid.equals("NONE")
		    && !solutionAdrid.equals("")) {
		try {
		    solutionADRDTO.setSolutionAdrid(Integer
			    .valueOf(solutionAdrid));
		    status = solutionService.saveSolutionADR(solutionADRDTO);
		} catch (NumberFormatException e) {
		    logger.error("solutionAdrid-" + solutionAdrid
			    + " Number format not supported-" + e);
		    e.printStackTrace();
		}
	    } else {
		solutionADRId = solutionService
			.saveSolutionADRReturnId(solutionADRDTO);
		status = solutionADRId >= 0 ? true : false;

	    }
	    // Check if successful
	    if (status) {
		// Success. Return a custom response
		response.setSuccess(true);
		response.setId(solutionADRId);
		response.setMessage("Action successful!");
		return response;

	    } else {
		// A failure. Return a custom response as well
		response.setSuccess(false);
		response.setMessage("Action failure!");
		return response;
	    }
	} catch (MSSPException e) {
	    logger.error("Exception - " + e);
	    e.printStackTrace();
	}
	return response;
    }

    /**
     * 
     * Description : Delete exiting ADR Method Name : delete Input& Output:
     * 
     * @param solutionAdrid
     * @return
     */
    @RequestMapping(value = "/solution/delete", method = RequestMethod.POST)
    public @ResponseBody
    CustomADRGenericResponse delete(
	    @RequestParam(value = "solutionAdrid", required = false, defaultValue = "NONE") String solutionAdrid) {

	logger.info("Received request to delete an existing ADR -"
		+ solutionAdrid);
	boolean status = false;

	if (null != solutionAdrid && !solutionAdrid.equals("")
		&& !solutionAdrid.equals("NONE")) {
	    Integer solutionAdridInt = -1;
	    try {
		SolutionADRDTO solutionADRDTO = new SolutionADRDTO();
		solutionAdridInt = Integer.valueOf(solutionAdrid);
		solutionADRDTO.setSolutionAdrid(solutionAdridInt);
		status = solutionService.deleteSolutionADR(solutionADRDTO);
	    } catch (NumberFormatException nfe) {
		logger.error("Number format error:" + solutionAdrid + " Error-"
			+ nfe);
		nfe.printStackTrace();
	    } catch (MSSPException e) {
		logger.error("Error in deletion" + e);
		e.printStackTrace();
	    }
	    // delete code here....

	}
	CustomADRGenericResponse response = new CustomADRGenericResponse();

	// Check if successful
	if (status) {
	    // Success. Return a custom response
	    response.setSuccess(true);
	    response.setMessage("Action successful!");
	    return response;
	} else {
	    // A failure. Return a custom response as well
	    response.setSuccess(false);
	    response.setMessage("Action failure!");
	    return response;
	}
    }

}
