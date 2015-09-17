<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculateCFGSum();
		});
	
$('#saveCFG').click(function() {
		$("#ConfigurationManagementForm").submit();	
	
});

$('#ConfigurationManagementForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#cfgDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveConfigurationManagement"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetCFG').click(
	function(){
		$('#ConfigurationManagementForm').each(
			function(){
			  this.reset();
			  document.getElementById('saveCFG').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculateCFGSum();
			});
    	
	}
);

});

function calculateCFGSum() {
	
	var sum = 0;
	var isInvalidValue = false;
	hrsManageConfigurationItems = $("#id_manageConfigurationItems").val() || 0;
	hrsManageConfigInfoAndDocumentation = $("#id_manageConfigInfoAndDocumentation").val() || 0;
	
	if(parseFloat(hrsManageConfigurationItems) >= 0){sum = sum + parseFloat(hrsManageConfigurationItems);} else {isInvalidValue = true;}
	if(parseFloat(hrsManageConfigInfoAndDocumentation) >= 0){sum = sum + parseFloat(hrsManageConfigInfoAndDocumentation);} else {isInvalidValue = true;}
	
    if(!isInvalidValue){document.getElementById("id_fteConfigurationManagement").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_fteConfigurationManagement").value = 0;}
    
    if(!validateCFGInputValues()){document.getElementById('saveCFG').hidden = true;}
    else{document.getElementById('saveCFG').hidden = false;}
} ;

function validateCFGInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsManageConfigurationItems) || hrsManageConfigurationItems < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Identify, control, maintain and verify the versions of Configuration Items (CIs) in existence (for sw items,infrastructure and documents) effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsManageConfigInfoAndDocumentation) || hrsManageConfigInfoAndDocumentation < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Provides accurate information on configurations and their documentation to support Incident Management,Problem Management, Change Management and Release Management effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>

<form:form id="ConfigurationManagementForm" action="saveConfigurationManagement"
	method="post" modelAttribute="configurationManagementForm">

	<form:hidden path="opportunityScopeId" />
	<form:input type="hidden" path="configurationManagementDTO.configurationManagementID" />
	
	<table width="100%">

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
			<tr>
			<td class="tdLabelLeft" align="right" width="50%">Identify, control, maintain and verify the versions of Configuration Items (CIs) in existence (for sw items,
infrastructure and documents) effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="configurationManagementDTO.manageConfigurationItems"
				id="id_manageConfigurationItems"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateCFGSum()"/></td>

		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Provides accurate information on configurations and their documentation to support Incident Management,
Problem Management, Change Management and Release Management effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="configurationManagementDTO.manageConfigInfoAndDocumentation" id="id_manageConfigInfoAndDocumentation"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateCFGSum()"/></td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><strong>FTE for Configuration Management</strong><br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="configurationManagementDTO.fteConfigurationManagement"
				id="id_fteConfigurationManagement" readonly="true"
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10"/></td>

		</tr>
		
	
	</table>
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
						<a id="resetCFG" class="TabsCommonButtons">Reset</a> 
						<a id="saveCFG"	class="TabsCommonButtons">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
	
</form:form>