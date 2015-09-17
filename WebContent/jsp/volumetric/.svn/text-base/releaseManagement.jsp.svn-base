<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculateRMSum();
		});
	
$('#saveRM').click(function() {
		$("#ReleaseManagementForm").submit();	
	
});

$('#ReleaseManagementForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#rmDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveReleaseManagement"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetRM').click(
	function(){
		$('#ReleaseManagementForm').each(
			function(){
			  this.reset();
			  document.getElementById('saveRM').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculateRMSum();
			});
    	
	}
);

});

function calculateRMSum() {
	
	var sum = 0;
	var isInvalidValue = false;
	hrsInstlnDistnOfSoftRel = $("#id_instlnDistnOfSoftRel").val() || 0;
	hrsDefineRolloutPlan = $("#id_defineRolloutPlan").val() || 0;
	hrsEnsureTraceability = $("#id_ensureTraceability").val() || 0;
	hrsEnsureInstallation = $("#id_ensureInstallation").val() || 0;
	
	if(parseFloat(hrsInstlnDistnOfSoftRel) >= 0){sum = sum + parseFloat(hrsInstlnDistnOfSoftRel);} else {isInvalidValue = true;}
	if(parseFloat(hrsDefineRolloutPlan) >= 0){sum = sum + parseFloat(hrsDefineRolloutPlan);} else {isInvalidValue = true;}
	if(parseFloat(hrsEnsureTraceability) >= 0){sum = sum + parseFloat(hrsEnsureTraceability);} else {isInvalidValue = true;}
	if(parseFloat(hrsEnsureInstallation) >= 0){sum = sum + parseFloat(hrsEnsureInstallation);} else {isInvalidValue = true;}
	
    if(!isInvalidValue){document.getElementById("id_totalReleaseManagementHours").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_totalReleaseManagementHours").value = 0;}
    
    if(!validateRMInputValues()){document.getElementById('saveRM').hidden = true;}
    else{document.getElementById('saveRM').hidden = false;}
} ;

function validateRMInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsInstlnDistnOfSoftRel) || hrsInstlnDistnOfSoftRel < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Installation and distribution of a Software Release effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsDefineRolloutPlan) || hrsDefineRolloutPlan < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Defining rollout plan and overseeing the rollout of software and and any related hardware effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsEnsureTraceability)	|| hrsEnsureTraceability < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Ensuring traceability of hardware and software being changed in conjunction with Configuration and Change Management effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsEnsureInstallation)	|| hrsEnsureInstallation < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Ensuring installation of correct, authorised and tested versions of software that are linked to Configuration and Change Management effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>

<form:form id="ReleaseManagementForm" action="saveReleaseManagement"
	method="post" modelAttribute="releaseManagementForm">

	<form:hidden path="opportunityScopeId" />
	<form:input type="hidden" path="releaseManagementDTO.releaseManagementID" />
	
	<table width="100%">

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
			<tr>
			<td class="tdLabelLeft" align="right" width="50%">Installation and distribution of a Software Release effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="releaseManagementDTO.instlnDistnOfSoftRel"
				id="id_instlnDistnOfSoftRel"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" 
				onchange="formatInput(this,id);calculateRMSum()" /></td>

		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50">Defining rollout plan and overseeing the rollout of software and and any related hardware effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="releaseManagementDTO.defineRolloutPlan"
						id="id_defineRolloutPlan" class="textBoxSNumric" 
						style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateRMSum()"/>
			</td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Ensuring traceability of hardware and software being changed in conjunction with Configuration and Change
Management effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="releaseManagementDTO.ensureTraceability"
				id="id_ensureTraceability"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateRMSum()"/>
			</td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Ensuring installation of correct, authorised and tested versions of software that are linked to Configuration and
Change Management effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="releaseManagementDTO.ensureInstallation"
						id="id_ensureInstallation" class="textBoxSNumric" 
						style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateRMSum()"/>
			</td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><strong>FTE for Release Management</strong></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" 
				path="releaseManagementDTO.totalReleaseManagementHours"
				id="id_totalReleaseManagementHours" readonly="true"
				class="textBoxSCalculateResults" style="font-weight: bold; " maxlength="10"/>
				</td>

		</tr>

	</table>
	
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="right">
						<a id="resetRM"	class="TabsCommonButtons">Reset</a> 
						<a id="saveRM" class="TabsCommonButtons">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
</form:form>