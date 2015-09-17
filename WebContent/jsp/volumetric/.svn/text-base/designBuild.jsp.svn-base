<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculateDesignSum();
});

$('#saveDesignBuild').click(function() {
		$("#DesignAndBuildForm").submit();
});

$('#DesignAndBuildForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#designDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveDesignBuild"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetDesignBuild').click(
	function(){
		$('#DesignAndBuildForm').each(
			function(){
			  this.reset();
			  document.getElementById('saveDesignBuild').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculateDesignSum();
			});
	}
);

});

function calculateDesignSum() {
	var sum = 0;
	var isInvalidValue = false;
	hrsDetailTechDesign = $("#id_detailTechDesign").val() || 0;
	hrsTestCaseDesignTestCyclePlan = $("#id_testCaseDesignTestCyclePlan").val() || 0;
	hrsFunctionalSpecDevFinal = $("#id_functionalSpecDevFinal").val() || 0;
	hrsCapacityPlanning = $("#id_capacityPlanning").val() || 0;
	hrsSoftwareChanges = $("#id_softwareChanges").val() || 0;
	hrsConfigChanges = $("#id_configChanges").val() || 0;
	hrsUnitComponentTest = $("#id_unitComponentTest").val() ||0;
	hrsRelNotesPrepareDistribute  = $("#id_relNotesPrepareDistribute").val() || 0;
	hrsMaintananceManualCreation  = $("#id_maintananceManualCreation").val() || 0;
	
	if(parseFloat(hrsDetailTechDesign) >= 0){sum = sum + parseFloat(hrsDetailTechDesign);} else {isInvalidValue = true;}
	if(parseFloat(hrsTestCaseDesignTestCyclePlan) >= 0){sum = sum + parseFloat(hrsTestCaseDesignTestCyclePlan);} else {isInvalidValue = true;}
	if(parseFloat(hrsFunctionalSpecDevFinal) >= 0){sum = sum + parseFloat(hrsFunctionalSpecDevFinal);} else {isInvalidValue = true;}
	if(parseFloat(hrsCapacityPlanning) >= 0){sum = sum + parseFloat(hrsCapacityPlanning);} else {isInvalidValue = true;}
	if(parseFloat(hrsSoftwareChanges) >= 0){sum = sum + parseFloat(hrsSoftwareChanges);} else {isInvalidValue = true;}
	if(parseFloat(hrsConfigChanges) >= 0){sum = sum + parseFloat(hrsConfigChanges);} else {isInvalidValue = true;}
	if(parseFloat(hrsUnitComponentTest) >= 0){sum = sum + parseFloat(hrsUnitComponentTest);} else {isInvalidValue = true;}
	if(parseFloat(hrsRelNotesPrepareDistribute) >= 0){sum = sum + parseFloat(hrsRelNotesPrepareDistribute);} else {isInvalidValue = true;}
	if(parseFloat(hrsMaintananceManualCreation) >= 0){sum = sum + parseFloat(hrsMaintananceManualCreation);} else {isInvalidValue = true;}
	
	if(!isInvalidValue){document.getElementById("id_TotalDesignBuildHours").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_TotalDesignBuildHours").value = 0;}
	
	if(!validateDesignInputValues()){document.getElementById('saveDesignBuild').hidden = true;}
    else{document.getElementById('saveDesignBuild').hidden = false;}
    
} ;

function validateDesignInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	if (isNaN(hrsDetailTechDesign) || hrsDetailTechDesign < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Detailed technical design effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsTestCaseDesignTestCyclePlan) || hrsTestCaseDesignTestCyclePlan < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Test cases design and test cycles plan effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsFunctionalSpecDevFinal) || hrsFunctionalSpecDevFinal < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Functional specifications development and finalization effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsCapacityPlanning) || hrsCapacityPlanning < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Capacity planning; hardware/software licenses'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsSoftwareChanges) || hrsSoftwareChanges < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Software changes effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsConfigChanges)	|| hrsConfigChanges < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Configuration changes effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsUnitComponentTest)	|| hrsUnitComponentTest < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Unit/Component test effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsRelNotesPrepareDistribute)	|| hrsRelNotesPrepareDistribute < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Release notes preparation and distribution effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsMaintananceManualCreation)	|| hrsMaintananceManualCreation < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'User and Operation&Maintanance manuals creation effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>
<form:form id="DesignAndBuildForm" method="post" modelAttribute="designAndBuildForm">

<form:input type="hidden" path="opportunityScopeID" />

	
	<table width="100%">
		
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Detailed technical design effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.detailTechDesign"
				id="id_detailTechDesign"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Test cases design and test cycles plan effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.testCaseDesignTestCyclePlan"
				id="id_testCaseDesignTestCyclePlan"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Functional specifications development and finalization effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.functionalSpecDevFinal"
				id="id_functionalSpecDevFinal"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Capacity planning; hardware/software licenses needs determination effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.capacityPlanning"
				id="id_capacityPlanning"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Software changes effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.softwareChanges"
				id="id_softwareChanges"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Configuration changes effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.configChanges"
				id="id_configChanges"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Unit/Component test effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.unitComponentTest"
				id="id_unitComponentTest"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Release notes preparation and distribution effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.relNotesPrepareDistribute"
				id="id_relNotesPrepareDistribute"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">User and Operation&Maintanance manuals creation effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.maintananceManualCreation"
				id="id_maintananceManualCreation"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><b>FTE for Design & Build</b><br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.totalDesignBuildHours"
				id="id_TotalDesignBuildHours"
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10" disabled="true" />
			</td>
		</tr>
		<form:input type="hidden" path="DesignBuildDTO.designAndBuildId" />
		
	</table>
	
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
						<a id="resetDesignBuild" class="TabsCommonButtons">Reset</a> 
						<a id="saveDesignBuild" class="TabsCommonButtons" onclick="document.getElementById('id_TotalDesignBuildHours').disabled=''">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>

</form:form>