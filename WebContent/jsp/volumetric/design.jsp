<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
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
	
	if(!isInvalidValue){document.getElementById("id_TotalDesignBuildHours").value = sum*12;}  
    else{document.getElementById("id_TotalDesignBuildHours").value = 0;}
	
	if(!validateDesignInputValues()){document.getElementById('saveDesignBuild').hidden = true;}
    else{document.getElementById('saveDesignBuild').hidden = false;}
    
} ;

function validateDesignInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	if (isNaN(hrsUseCaseRealisation) || hrsUseCaseRealisation < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for Use Case Realisation</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_useCaseRealisation").value = "";
		flag = false;
	}
	if (isNaN(hrsworkflowOfUseCase) || hrsworkflowOfUseCase < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for Workflow Of Use Cases</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_workflowOfUseCase").value = "";
		flag = false;
	}
	if (isNaN(hrsManageInterfaces)	|| hrsManageInterfaces < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for Manage Interfaces</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_manageInterfaces").value = "";
		flag = false;
	}
	if (isNaN(hrsManageNFR) || hrsManageNFR < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for Manage NFR</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_manageNFR").value = "";
		flag = false;
	}
	if (isNaN(hrsDesignDocument) || hrsDesignDocument < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for Design Document</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_designDocument").value = "";
		flag = false;
	}
	if (isNaN(hrsReview)	|| hrsReview < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for Review</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_review").value = "";
		flag = false;
	}
	if (isNaN(hrsBaseLine)	|| hrsBaseLine < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for BaseLine</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_baseLine").value = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>
<form:form id="DesignAndBuildForm" method="post" modelAttribute="designAndBuildForm">

	<table width="100%">
		
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Detailed technical design effort (Hours/Month)</br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.detailTechDesign"
				id="id_detailTechDesign"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="5" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Test cases design and test cycles plan effort (Hours/Month)</br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.testCaseDesignTestCyclePlan"
				id="id_testCaseDesignTestCyclePlan"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="5" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Functional specifications development and finalization effort (Hours/Month)</br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.functionalSpecDevFinal"
				id="id_functionalSpecDevFinal"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="5" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Capacity planning; hardware/software licenses needs determination effort (Hours/Month)</br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.capacityPlanning"
				id="id_capacityPlanning"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="5" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Software changes effort (Hours/Month)</br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.softwareChanges"
				id="id_softwareChanges"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="5" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Configuration changes effort (Hours/Month)</br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.configChanges"
				id="id_configChanges"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="5" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Unit/Component test effort (Hours/Month)</br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.unitComponentTest"
				id="id_unitComponentTest"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="5" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Release notes preparation and distribution effort (Hours/Month)</br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.relNotesPrepareDistribute"
				id="id_relNotesPrepareDistribute"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="5" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">User and Operation&Maintanance manuals creation effort (Hours/Month)</br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.maintananceManualCreation"
				id="id_maintananceManualCreation"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="5" onchange="formatInput(this,id);calculateDesignSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><b>Total Design Hours (Hours per Year)</b></br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DesignBuildDTO.totalDesignBuildHours"
				id="id_TotalDesignBuildHours"
				class="textBoxCalculateResults" style="font-weight: bold;" maxlength="5" disabled="true" />
			</td>
		</tr>
		<form:input type="hidden" path="DesignBuildDTO.designID" />
		<form:input type="hidden" path="DesignBuildDTO.opportunityScopeDTO.opportunityScopeId" />
		<form:input type="hidden" path="DesignBuildDTO.solutionDTO.solutionId" />
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