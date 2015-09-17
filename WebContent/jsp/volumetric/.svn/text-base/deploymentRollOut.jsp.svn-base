<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {

$(function(){
	calculateDROSum();
});

$('#saveDRO').click(function() {
		$("#DeploymentRollOutForm").submit();
	
});

$('#DeploymentRollOutForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#depRODiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveDeploymentRollOut"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetDRO').click(
	function(){
		$('#DeploymentRollOutForm').each(
			function(){
			  this.reset();
			  document.getElementById('saveDRO').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculateDROSum();
			});
    	
	}
);

});



function calculateDROSum() {
	
	var sum = 0;
	var isInvalidValue = false;
	hrsOpsBusinessTraining = $("#id_opsBusinessTraining").val() || 0;
	hrsDeploySoftwareInstln = $("#id_deploySoftwareInstln").val() || 0;
	hrsDeployInstlnPlanExec = $("#id_deployInstlnPlanExec").val() || 0;
	hrsDataMigration = $("#id_dataMigration").val() || 0;
	hrsLegacySwitchOff = $("#id_legacySwitchOff").val() || 0;
	
	if(parseFloat(hrsOpsBusinessTraining) >= 0){sum = sum + parseFloat(hrsOpsBusinessTraining);} else {isInvalidValue = true;}
	if(parseFloat(hrsDeploySoftwareInstln) >= 0){sum = sum + parseFloat(hrsDeploySoftwareInstln);} else {isInvalidValue = true;}
	if(parseFloat(hrsDeployInstlnPlanExec) >= 0){sum = sum + parseFloat(hrsDeployInstlnPlanExec);} else {isInvalidValue = true;}
	if(parseFloat(hrsDataMigration) >= 0){sum = sum + parseFloat(hrsDataMigration);} else {isInvalidValue = true;}
	if(parseFloat(hrsLegacySwitchOff) >= 0){sum = sum + parseFloat(hrsLegacySwitchOff);} else {isInvalidValue = true;}
	
	if(!isInvalidValue){document.getElementById("id_totalDeploymentRollOutHours").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_totalDeploymentRollOutHours").value = 0;}
	
	if(!validateDROInputValues()){document.getElementById('saveDRO').hidden = true;}
    else{document.getElementById('saveDRO').hidden = false;}
    
} ;

function validateDROInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsOpsBusinessTraining) || hrsOpsBusinessTraining < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Operations and business training effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_ROcreatePlan").value = "";
		flag = false;
	}
	if (isNaN(hrsDeploySoftwareInstln) || hrsDeploySoftwareInstln < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Deployment and software installation planning effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_involveStakeHolder").value = "";
		flag = false;
	}
	if (isNaN(hrsDeployInstlnPlanExec)	|| hrsDeployInstlnPlanExec < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Deployment and installation plan execution effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_communicate").value = "";
		flag = false;
	}
	if (isNaN(hrsDataMigration) || hrsDataMigration < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Data Migration effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_performDeployment").value = "";
		flag = false;
	}
	if (isNaN(hrsLegacySwitchOff) || hrsLegacySwitchOff < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for 'Legacy switch-off effort'</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_smokeTest").value = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>
<form:form id="DeploymentRollOutForm" method="post" modelAttribute="deploymentRollOutForm">

<form:hidden path="opportunityScopeID" />

	<table width="100%">
		
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Operations and business training effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DeploymentRollOutDTO.opsBusinessTraining"
				id="id_opsBusinessTraining"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDROSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Deployment and software installation planning effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DeploymentRollOutDTO.deploySoftwareInstln"
				id="id_deploySoftwareInstln"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDROSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Deployment and installation plan execution effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DeploymentRollOutDTO.deployInstlnPlanExec"
				id="id_deployInstlnPlanExec"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDROSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Data Migration effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DeploymentRollOutDTO.dataMigration"
				id="id_dataMigration"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDROSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Legacy switch-off effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DeploymentRollOutDTO.legacySwitchOff"
				id="id_legacySwitchOff"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateDROSum()"/>
			</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><b>FTE for Deployment/Rollout</b><br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="DeploymentRollOutDTO.totalDeploymentRollOutHours"
				id="id_totalDeploymentRollOutHours"
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10" disabled="true" />
			</td>
		</tr>
		<form:input type="hidden" path="DeploymentRollOutDTO.deploymentRollOutId" />
	</table>
	
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
						<a id="resetDRO"	class="TabsCommonButtons">Reset</a> 
						<a id="saveDRO" class="TabsCommonButtons" onclick="document.getElementById('id_totalDeploymentRollOutHours').disabled=''">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>

</form:form>