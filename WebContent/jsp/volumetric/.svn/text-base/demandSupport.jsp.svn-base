<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.ericsson.mssp.volumetric.forms.DemandSupportForm" %>
<%@ page import="com.ericsson.mssp.common.dto.DemandSupportDTO" %>
<%@ page import="org.springframework.ui.Model" %>

<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculateDSSum();
	});
	
$('#saveDS').click(function() {
		$("#DemandSupportForm").submit();
	});

$('#DemandSupportForm').submit(function(){

	showProgress();
	var options = {
		success : function(html) {
			$("#dsDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveDemandSupport"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetDS').click(
	function(){
		$('#DemandSupportForm').each(
			function(){
			  this.reset();
			  document.getElementById('saveDS').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculateDSSum();
			});
	}
);


});


function calculateDSSum() {
	var sum = 0;
	var isInvalidValue = false;
	hrsCRAnalysis = $("#id_crAnalysis").val() || 0;
	hrsRequirementGathering = $("#id_requirementGathering").val() || 0;
	hrsTechFeasibilityAnalysis = $("#id_techFeasibilityAnalysis").val() || 0;
	hrsEffCostEstimation = $("#id_effCostEstimation").val() || 0;
	hrsWorkPlanFormulation = $("#id_workPlanFormulation").val() || 0;
	
	if(parseFloat(hrsCRAnalysis) >= 0){sum = sum + parseFloat(hrsCRAnalysis);} else {isInvalidValue = true;}
	if(parseFloat(hrsRequirementGathering) >= 0){sum = sum + parseFloat(hrsRequirementGathering);} else {isInvalidValue = true;}
	if(parseFloat(hrsTechFeasibilityAnalysis) >= 0){sum = sum + parseFloat(hrsTechFeasibilityAnalysis);} else {isInvalidValue = true;}
	if(parseFloat(hrsEffCostEstimation) >= 0){sum = sum + parseFloat(hrsEffCostEstimation);} else {isInvalidValue = true;}
	if(parseFloat(hrsWorkPlanFormulation) >= 0){sum = sum + parseFloat(hrsWorkPlanFormulation);} else {isInvalidValue = true;}
	
	if(!isInvalidValue){document.getElementById("id_TotalDemandSupportHours").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_TotalDemandSupportHours").value = 0;}
	
	if(!validateDSInputValues()){document.getElementById('saveDS').hidden = true;}
    else{document.getElementById('saveDS').hidden = false;}
    
} ;

function validateDSInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsCRAnalysis) || hrsCRAnalysis < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'CR analysis effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("dsProposeIdea").value = "";
		flag = false;
	}
	if (isNaN(hrsRequirementGathering) || hrsRequirementGathering < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Requirement gathering effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("dsInitialReview").value = "";
		flag = false;
	}
	if (isNaN(hrsTechFeasibilityAnalysis)	|| hrsTechFeasibilityAnalysis < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Technical feasibility analysis effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("dsSelectPortfolio").value = "";
		flag = false;
	}
	if (isNaN(hrsEffCostEstimation) || hrsEffCostEstimation < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Effort/cost estimation effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("dsPlanBusinessCase").value = "";
		flag = false;
	}
	if (isNaN(hrsWorkPlanFormulation) || hrsWorkPlanFormulation < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Work plan formulation and release to stakeholders effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("dsReviewAndApproval").value = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>
<form:form id="DemandSupportForm" method="post" modelAttribute="demandSupportForm">

<form:hidden path="opportunityScopeId" />
<form:input type="hidden" path="demandSupportDTO.demandSupportID" />


	<table width="100%">
		
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">CR analysis effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="demandSupportDTO.crAnalysis"
				id="id_crAnalysis"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDSSum() "/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Requirement gathering effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="demandSupportDTO.requirementGathering"
				id="id_requirementGathering"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDSSum()" />
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Technical feasibility analysis effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="demandSupportDTO.techFeasibilityAnalysis"
				id="id_techFeasibilityAnalysis"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDSSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Effort/cost estimation effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="demandSupportDTO.effCostEstimation"
				id="id_effCostEstimation"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDSSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Work plan formulation and release to stakeholders effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="demandSupportDTO.workPlanFormulation"
				id="id_workPlanFormulation"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDSSum()"/>
			</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><b>FTE for Demand Support</b><br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="demandSupportDTO.totalDemandSupportHours"
				id="id_TotalDemandSupportHours"
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10" disabled="true" />
			</td>
		</tr>
		
	</table>
	
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
						<a id="resetDS"	class="TabsCommonButtons">Reset</a> 
						<a id="saveDS" class="TabsCommonButtons" onclick="document.getElementById('id_TotalDemandSupportHours').disabled=''">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>

</form:form>