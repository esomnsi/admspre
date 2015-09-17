	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculatePMSum();
		});
	
$('#savePM').click(function() {
		$("#ProgramManagementForm").submit();	
	
});

$('#ProgramManagementForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#pmDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveProgramManagement"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetPM').click(
	function(){
		$('#ProgramManagementForm').each(
			function(){
			  this.reset();
			  document.getElementById('savePM').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculatePMSum();
			});
    	
	}
);

});

function calculatePMSum() {
	
	var sum = 0;
	var isInvalidValue = false;
	hrsCoordinateProjects = $("#id_coordinateProjects").val() || 0;
	hrsEnsureAchievement = $("#id_ensureAchievement").val() || 0;
	hrsManageClient = $("#id_manageClient").val() || 0;
	hrsManageQualityRiskIssues = $("#id_manageQualityRiskIssues").val() || 0;
	hrsMeasureADMServiceKPI = $("#id_measureADMServiceKPI").val() || 0;
	
	if(parseFloat(hrsCoordinateProjects) >= 0){sum = sum + parseFloat(hrsCoordinateProjects);} else {isInvalidValue = true;}
	if(parseFloat(hrsEnsureAchievement) >= 0){sum = sum + parseFloat(hrsEnsureAchievement);} else {isInvalidValue = true;}
	if(parseFloat(hrsManageClient) >= 0){sum = sum + parseFloat(hrsManageClient);} else {isInvalidValue = true;}
	if(parseFloat(hrsManageQualityRiskIssues) >= 0){sum = sum + parseFloat(hrsManageQualityRiskIssues);} else {isInvalidValue = true;}
	if(parseFloat(hrsMeasureADMServiceKPI) >= 0){sum = sum + parseFloat(hrsMeasureADMServiceKPI);} else {isInvalidValue = true;}
	
    if(!isInvalidValue){document.getElementById("id_totalProgramManagementHours").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_totalProgramManagementHours").value = 0;}
    
    if(!validatePMInputValues()){document.getElementById('savePM').hidden = true;}
    else{document.getElementById('savePM').hidden = false;}
} ;

function validatePMInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsCoordinateProjects) || hrsCoordinateProjects < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Coordinate Projects in an ADM program effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsEnsureAchievement) || hrsEnsureAchievement < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Ensures Achievement of scope,time,cost and quality objectives and goals effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsManageClient)	|| hrsManageClient < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Manage client meetings,reporting and communication,escalation path, acceptance tracking and reporting effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsManageQualityRiskIssues)	|| hrsManageQualityRiskIssues < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Manage quality,risks and issues effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsMeasureADMServiceKPI)	|| hrsMeasureADMServiceKPI < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Measurement of and reporting on ADM service KPIs/SLAs effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>

<form:form id="ProgramManagementForm" action="saveProgramManagement"
	method="post" modelAttribute="programManagementForm">

	
	<form:hidden path="opportunityScopeId" />
	<form:input type="hidden" path="ProgramManagementDTO.programManagementID" />

	<table width="100%">

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Coordinate Projects in an ADM program effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
				<form:input type="text" 
							path="ProgramManagementDTO.coordinateProjects"
							id="id_coordinateProjects" 
							class="textBoxSNumric" 
							style="font-weight: bold;" 
							maxlength="10" onchange="formatInput(this,id);calculatePMSum()" />
			</td>
		</tr>
			
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Ensures Achievement of scope,time,cost and quality objectives and goals effort (Hours/Year)<br></td>

			<td  class="tdInputBox" align="left" width="50%">
			<form:input type="text" 
			path="ProgramManagementDTO.ensureAchievement"
				id="id_ensureAchievement" 
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculatePMSum()" /></td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Manage client meetings,reporting and communication,escalation path, acceptance tracking and reporting effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text"  
			path="ProgramManagementDTO.manageClient"
				id="id_manageClient" 
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculatePMSum()" /></td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Manage quality,risks and issues (limited to the project/program execution scope) effort (Hours/Year)</br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="ProgramManagementDTO.manageQualityRiskIssues"
				id="id_manageQualityRiskIssues" 
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculatePMSum()" /></td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Measurement of and reporting on ADM service KPIs/SLAs effort (Hours/Year)<br></td>

			<td  class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="ProgramManagementDTO.measureADMServiceKPI"
				id="id_measureADMServiceKPI"  
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculatePMSum()" /></td> 
		</tr>		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><b>FTE for Program Management</b><br></td>

			<td class="tdInputBox" align="left" width="50%" > 			
				<form:input type="text" 
				path="ProgramManagementDTO.totalProgramManagementHours"
				id="id_totalProgramManagementHours"  readonly="true" 
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10"/>
			</td>

		</tr>
		
		
	</table>
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
						<a id="resetPM"	class="TabsCommonButtons">Reset</a> 
						<a id="savePM" class="TabsCommonButtons">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
	
</form:form>