<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculateCPYSum();
		});
	
$('#saveCPY').click(function() {
		$("#CapacityManagementForm").submit();	
	
});

$('#CapacityManagementForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#cpyDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveCapacityManagement"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetCPY').click(
	function(){
		$('#CapacityManagementForm').each(
			function(){
			  this.reset();
			  document.getElementById('saveCPY').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculateCPYSum();
			});
    	
	}
);

});

function calculateCPYSum() {
	
	var sum = 0;
	var isInvalidValue = false;
	hrsPrepareAndUseModels = $("#id_prepareAndUseModels").val() || 0;
	
	if(parseFloat(hrsPrepareAndUseModels) >= 0){sum = sum + parseFloat(hrsPrepareAndUseModels);} else {isInvalidValue = true;}
	
    if(!isInvalidValue){document.getElementById("id_fteCapacityManagement").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_fteCapacityManagement").value = 0;}
    
    if(!validateCPYInputValues()){document.getElementById('saveCPY').hidden = true;}
    else{document.getElementById('saveCPY').hidden = false;}
} ;

function validateCPYInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsPrepareAndUseModels) || hrsPrepareAndUseModels < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Preparation and use of models for the estimation of volumes growth and planning/design the future needs in terms of hardware and software licenses effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>

<form:form id="CapacityManagementForm" action="saveCapacityManagement"
	method="post" modelAttribute="capacityManagementForm">

	<form:hidden path="opportunityScopeId" />
	<form:input type="hidden" path="capacityManagementDTO.capacityManagementID" />

	<table width="100%">

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Preparation and use of models for the estimation of volumes growth and planning/design the future needs in terms
of hardware and software licenses effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="capacityManagementDTO.prepareAndUseModels"
				id="id_prepareAndUseModels"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateCPYSum()"/></td>

		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><strong>FTE for Capacity Management</strong><br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="capacityManagementDTO.fteCapacityManagement"
				id="id_fteCapacityManagement" readonly="true"
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10"/></td>

		</tr>
		
	
	</table>
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
					<a id="resetCPY" class="TabsCommonButtons">Reset</a> 
					<a id="saveCPY"	class="TabsCommonButtons">Save</a>
				</td>
				</tr>
			</table>
		</div>
	</c:if>
	
</form:form>