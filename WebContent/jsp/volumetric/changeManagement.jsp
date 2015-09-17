<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculateCMSum();
		});

	
$('#saveCM').click(function() {
		$("#ChangeManagementForm").submit();	
	
});

$('#ChangeManagementForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#cmDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveChangeManagement"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetCM').click(
	function(){
		$('#ChangeManagementForm').each(
			function(){
			  this.reset();
			  document.getElementById('saveCM').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculateCMSum();
			});
    	
	}
);

});

function calculateCMSum() {
	
	var sum = 0;
	var isInvalidValue = false;
	hrsReqGatheringAndAnalysis = $("#id_reqGatheringAndAnalysis").val() || 0;
	hrsFeasibilityImpactAndCostAnalysis = $("#id_feasibilityImpactAndCostAnalysis").val() || 0;
	hrsApproveChangesAndPlanning = $("#id_approveChangesAndPlanning").val() || 0;
	hrsEnsureStandards = $("#id_ensureStandards").val() || 0;
	hrsManageTraceability = $("#id_manageTraceability").val() || 0;
	
	if(parseFloat(hrsReqGatheringAndAnalysis) >= 0){sum = sum + parseFloat(hrsReqGatheringAndAnalysis);} else {isInvalidValue = true;}
	if(parseFloat(hrsFeasibilityImpactAndCostAnalysis) >= 0){sum = sum + parseFloat(hrsFeasibilityImpactAndCostAnalysis);} else {isInvalidValue = true;}
	if(parseFloat(hrsApproveChangesAndPlanning) >= 0){sum = sum + parseFloat(hrsApproveChangesAndPlanning);} else {isInvalidValue = true;}
	if(parseFloat(hrsEnsureStandards) >= 0){sum = sum + parseFloat(hrsEnsureStandards);} else {isInvalidValue = true;}
	if(parseFloat(hrsManageTraceability) >= 0){sum = sum + parseFloat(hrsManageTraceability);} else {isInvalidValue = true;}
	
    if(!isInvalidValue){document.getElementById("id_totalChangeManagementHours").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_totalChangeManagementHours").value = 0;}
    
    if(!validateCMInputValues()){document.getElementById('saveCM').hidden = true;}
    else{document.getElementById('saveCM').hidden = false;}
} ;

function validateCMInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsReqGatheringAndAnalysis) || hrsReqGatheringAndAnalysis < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Entry point for gathering and first analysis all changes (requirements for new functionalities, bug fixes, improvements) effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsFeasibilityImpactAndCostAnalysis) || hrsFeasibilityImpactAndCostAnalysis < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Interact with Demand Support for feasibility, impact analysis and cost estimation effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsApproveChangesAndPlanning)	|| hrsApproveChangesAndPlanning < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Approval of changes and planning (in accordance with Release mgmt) effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsEnsureStandards)	|| hrsEnsureStandards < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Ensures that standardised methods and procedures are used for efficient and prompt handling of all changes effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsManageTraceability)	|| hrsManageTraceability < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Manage the Traceability Matrix for new reqs, fixing tasks and improvement tasks effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>

<form:form id="ChangeManagementForm" action="saveChangeManagement"
	method="post" modelAttribute="changeManagementForm">

	<form:hidden path="opportunityScopeId" />
	<form:input type="hidden" path="changeManagementDTO.changeManagementID" />

	<table width="100%">

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
			<tr>
			<td class="tdLabelLeft" align="right" width="50%">Entry point for gathering and first analysis all changes (requirements for new functionalities, bug fixes,
improvements) effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="changeManagementDTO.reqGatheringAndAnalysis"
				id="id_reqGatheringAndAnalysis"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateCMSum()"/></td>

		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Interact with Demand Support for feasibility, impact analysis and cost estimation
 effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="changeManagementDTO.feasibilityImpactAndCostAnalysis" 
						id="id_feasibilityImpactAndCostAnalysis"
						class="textBoxSNumric" style="font-weight: bold;" 
						maxlength="10" onchange="formatInput(this,id);calculateCMSum()"/></td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Approval of changes and planning (in accordance with Release mgmt)
 effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="changeManagementDTO.approveChangesAndPlanning" 
						id="id_approveChangesAndPlanning"
						class="textBoxSNumric" style="font-weight: bold;" maxlength="10" 
						onchange="formatInput(this,id);calculateCMSum()"/>
			</td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Ensures that standardised methods and procedures are used for efficient and prompt handling of all changes
 effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="changeManagementDTO.ensureStandards" 
						id="id_ensureStandards"
						class="textBoxSNumric" style="font-weight: bold;" 
						maxlength="10" onchange="formatInput(this,id);calculateCMSum()"/>
			</td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Manage the Traceability Matrix for new reqs, fixing tasks and improvement tasks
 effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="changeManagementDTO.manageTraceability" id="id_manageTraceability"
				class="textBoxSNumric" style="font-weight: bold;" 
				maxlength="10" onchange="formatInput(this,id);calculateCMSum()"/>
			</td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><strong>FTE for Change Management</strong></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" 
				path="changeManagementDTO.totalChangeManagementHours"
				id="id_totalChangeManagementHours" readonly="true"
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10" />
			</td>

		</tr>
	</table>
	
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="right">
						<a id="resetCM"	class="TabsCommonButtons">Reset</a> 
						<a id="saveCM" class="TabsCommonButtons">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
</form:form>