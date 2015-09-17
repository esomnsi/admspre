<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculateSECSum();
		});
	
$('#saveSEC').click(function() {
		$("#SecurityManagementForm").submit();	
	
});

$('#SecurityManagementForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#secDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveSecurityManagement"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetSEC').click(
	function(){
		$('#SecurityManagementForm').each(
			function(){
			  this.reset();
			  document.getElementById('saveSEC').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculateSECSum();
			});
    	
	}
);

});

function calculateSECSum() {
	
	var sum = 0;
	var isInvalidValue = false;
	hrsManageSecurity = $("#id_manageSecurity").val() || 0;
	
	if(parseFloat(hrsManageSecurity) >= 0){sum = sum + parseFloat(hrsManageSecurity);} else {isInvalidValue = true;}
	
    if(!isInvalidValue){document.getElementById("id_fteSecurityManagement").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_fteSecurityManagement").value = 0;}
    
    if(!validateSECInputValues()){document.getElementById('saveSEC').hidden = true;}
    else{document.getElementById('saveSEC').hidden = false;}
} ;

function validateSECInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsManageSecurity) || hrsManageSecurity < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Manages the definition, implementation and compliance verification of policies, standards, procedures and guidelines in order to avoid unauthorized access to the applications and relevant information effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>
<form:form id="SecurityManagementForm" action="saveSecurityManagement"
	method="post" modelAttribute="securityManagementForm">

	
	<form:hidden path="opportunityScopeId" />
	<form:input type="hidden" path="securityManagementDTO.securityManagementID" />


	<table width="100%">

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
			<tr>
			<td class="tdLabelLeft" align="right" width="50%">Manages the definition, implementation and compliance verification of policies, standards, procedures and
guidelines in order to avoid unauthorized access to the applications and relevant information effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="securityManagementDTO.manageSecurity"
				id="id_manageSecurity"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateSECSum()"/></td>

		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><strong>FTE for Security Management</strong><br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="securityManagementDTO.fteSecurityManagement"
				id="id_fteSecurityManagement" readonly="true"
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10"/></td>

		</tr>
		
	
	</table>
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
						<a id="resetSEC" class="TabsCommonButtons">Reset</a> 
						<a id="saveSEC" class="TabsCommonButtons">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
	
</form:form>