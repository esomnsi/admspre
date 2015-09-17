<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculateAVLSum();
		});
	
$('#saveAVL').click(function() {
		$("#AvailabilityManagementForm").submit();	
	
});

$('#AvailabilityManagementForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#avlDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveAvailabilityManagement"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetAVL').click(
	function(){
		$('#AvailabilityManagementForm').each(
			function(){
			  this.reset();
			  document.getElementById('saveAVL').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculateAVLSum();
			});
    	
	}
);

});

function calculateAVLSum() {
	
	var sum = 0;
	var isInvalidValue = false;
	hrsManageAvailability = $("#id_manageAvailability").val() || 0;
	hrsManageOutageAndRisks = $("#id_manageOutageAndRisks").val() || 0;
	
	if(parseFloat(hrsManageAvailability) >= 0){sum = sum + parseFloat(hrsManageAvailability);} else {isInvalidValue = true;}
	if(parseFloat(hrsManageOutageAndRisks) >= 0){sum = sum + parseFloat(hrsManageOutageAndRisks);} else {isInvalidValue = true;}
	
    if(!isInvalidValue){document.getElementById("id_fteAvailabilityManagement").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("id_fteAvailabilityManagement").value = 0;}
    
    if(!validateAVLInputValues()){document.getElementById('saveAVL').hidden = true;}
    else{document.getElementById('saveAVL').hidden = false;}
} ;

function validateAVLInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsManageAvailability) || hrsManageAvailability < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Guarantees the applications and systems availability effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if (isNaN(hrsManageOutageAndRisks) || hrsManageOutageAndRisks < 0) {
		errorMsg += "<li>"+"Please enter Numeric value for 'Managing the planned outage and avoiding system faults or data corruption by managing risks that could seriously impact IT Services effort'"+"</li>";
		document.getElementById("messageDiv").innerHTML = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>

<form:form id="AvailabilityManagementForm" action="saveAvailabilityManagement"
	method="post" modelAttribute="availabilityManagementForm">

	<form:hidden path="opportunityScopeId" />
	<form:input type="hidden" path="availabilityManagementDTO.availabilityManagementID" />


	<table width="100%">

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
			<tr>
			<td class="tdLabelLeft" align="right" width="50%">Guarantees the applications and systems availability effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="availabilityManagementDTO.manageAvailability"
				id="id_manageAvailability"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateAVLSum()"/></td>

		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Managing the planned outage and avoiding system faults or data corruption by managing risks that could seriously
impact IT Services effort (Hours/Year)<br></td>

			<td class="tdInputBox" align="left" width="50%">
			<form:input type="text" path="availabilityManagementDTO.manageOutageAndRisks" id="id_manageOutageAndRisks"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onchange="formatInput(this,id);calculateAVLSum()"/></td> 
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><strong>FTE for Availability Management</strong><br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="availabilityManagementDTO.fteAvailabilityManagement"
				id="id_fteAvailabilityManagement"
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10"/></td>

		</tr>
		
	
	</table>
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
						<a id="resetAVL" class="TabsCommonButtons">Reset</a> 
						<a id="saveAVL" class="TabsCommonButtons">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
	
</form:form>