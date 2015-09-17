<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculatePRASum();
		});
	
$('#savePRA').click(function() {
		$("#PostReleaseActivitiesForm").submit();	
	
});

$('#PostReleaseActivitiesForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#postRelActDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./savePostReleaseActivity"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetPRA').click(
	function(){
		$('#PostReleaseActivitiesForm').each(
			function(){
			  this.reset();
			  document.getElementById('savePRA').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculatePRASum();
			});
    	
	}
);

});

function calculatePRASum() {
	
	var sum = 0;
	var isInvalidValue = false;
	hrsPostInstRelIssues = $("#id_postInstRelIssues").val() || 0;
	hrsSuppBusinessSimulations = $("#id_suppBusinessSimulations").val() || 0;
	hrsManageStaggeredAct = $("#id_manageStaggeredAct").val() || 0;
	hrsTunePerformance = $("#id_tunePerformance").val() || 0;
	hrsPostInstallationComm = $("#id_postInstallationComm").val() || 0;
	
	if(parseFloat(hrsPostInstRelIssues) >= 0){sum = sum + parseFloat(hrsPostInstRelIssues);} else {isInvalidValue = true;}
	if(parseFloat(hrsSuppBusinessSimulations) >= 0){sum = sum + parseFloat(hrsSuppBusinessSimulations);} else {isInvalidValue = true;}
	if(parseFloat(hrsManageStaggeredAct) >= 0){sum = sum + parseFloat(hrsManageStaggeredAct);} else {isInvalidValue = true;}
	if(parseFloat(hrsTunePerformance) >= 0){sum = sum + parseFloat(hrsTunePerformance);} else {isInvalidValue = true;}
	if(parseFloat(hrsPostInstallationComm) >= 0){sum = sum + parseFloat(hrsPostInstallationComm);} else {isInvalidValue = true;}
	
    if(!isInvalidValue){document.getElementById("id_TotalPostReleaseActivityHours").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_TotalPostReleaseActivityHours").value = 0;}
    
    if(!validatePRAInputValues()){document.getElementById('savePRA').hidden = true;}
    else{document.getElementById('savePRA').hidden = false;}
} ;

function validatePRAInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsPostInstRelIssues) || hrsPostInstRelIssues < 0) {
		//document.getElementById("showError").innerHTML = "<ul><li>Please enter Numeric value for Health Check</li></ul>";
		errorMsg += "<li>"+"Please enter Numeric value for Resolve post-installation and post-release issues effort"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_healthCheck").value = "";
		flag = false;
	}
	if (isNaN(hrsSuppBusinessSimulations) || hrsSuppBusinessSimulations < 0) {
		//document.getElementById("showError").innerHTML = "<ul><li>Please enter Numeric value for Functional Testing</li></ul>";
		errorMsg += "<li>"+"Please enter Numeric value for Support business simulations on request effort"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_functionalTesting").value = "";
		flag = false;
	}
	if (isNaN(hrsManageStaggeredAct)	|| hrsManageStaggeredAct < 0) {
		//document.getElementById("showError").innerHTML = "<ul><li>Please enter Numeric value for Communicate</li></ul>";
		errorMsg += "<li>"+"Please enter Numeric value for Manage staggered activation of interfaces effort"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_PRAcommunicate").value = "";
		flag = false;
	}
	if (isNaN(hrsTunePerformance)	|| hrsTunePerformance < 0) {
		//document.getElementById("showError").innerHTML = "<ul><li>Please enter Numeric value for Communicate</li></ul>";
		errorMsg += "<li>"+"Please enter Numeric value for Tune performance effort"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_PRAcommunicate").value = "";
		flag = false;
	}
	if (isNaN(hrsPostInstallationComm)	|| hrsPostInstallationComm < 0) {
		//document.getElementById("showError").innerHTML = "<ul><li>Please enter Numeric value for Communicate</li></ul>";
		errorMsg += "<li>"+"Please enter Numeric value for Send post-installation communications to users effort"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_PRAcommunicate").value = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>
<form:form id="PostReleaseActivitiesForm" method="post" modelAttribute="postReleaseActivitiesForm">

<form:hidden path="opportunityScopeID" />

	<table width="100%">
		
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Resolve post-installation and post-release issues effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="postRelActivityDTO.postInstRelIssues"
				id="id_postInstRelIssues"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculatePRASum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Support business simulations on request effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="postRelActivityDTO.suppBusinessSimulations"
				id="id_suppBusinessSimulations"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculatePRASum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Manage staggered activation of interfaces effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="postRelActivityDTO.manageStaggeredAct"
				id="id_manageStaggeredAct"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculatePRASum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Tune performance effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="postRelActivityDTO.tunePerformance"
				id="id_tunePerformance"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculatePRASum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Send post-installation communications to users effort (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="postRelActivityDTO.postInstallationComm"
				id="id_postInstallationComm"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculatePRASum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><b>FTE for Post-Release Activities</b><br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="postRelActivityDTO.totalPostRelActHours"
				id="id_TotalPostReleaseActivityHours"
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10" disabled="true" />
			</td>
		</tr>
		<form:input type="hidden" path="postRelActivityDTO.postRelActivityID" />
	</table>
	
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
						<a id="resetPRA"	class="TabsCommonButtons">Reset</a> 
						<a id="savePRA" class="TabsCommonButtons" onclick="document.getElementById('id_TotalPostReleaseActivityHours').disabled=''">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>

</form:form>