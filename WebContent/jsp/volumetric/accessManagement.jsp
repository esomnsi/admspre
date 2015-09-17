<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>

$(document).ready(function() {
	

		calculateAMSum();
		
		
	$('#saveAC').click(function() {
			$("#AccessManagementForm").submit();
		});

	$('#AccessManagementForm').submit(function(){

		showProgress();
		var options = {
			success : function(html) {
				$("#accessDiv").html(html);
				$("#messageDiv").addClass("message");
				$("#messageDiv").html("Your data has been saved successfully.");
				refreshServiceBucket();
			},
			url : "./saveAccessManagement"

		};

		$(this).ajaxSubmit(options);
		hideProgress();
		return false;

	});

	$('#resetAC').click(
		function(){
			$('#AccessManagementForm').each(
				function(){
				  this.reset();
				  document.getElementById('saveAC').hidden = false;
				  document.getElementById("showError").innerHTML = '';
				  document.getElementById("showError").style.display = 'block';
				  calculateDSSum();
				});
		}
	);


	});


	function calculateAMSum() {
		 var sum = 0;
		var isInvalidValue = false;
		manageUsers = document.getElementById("AccessManagementDTO.manageUsers").value || 0;
		manageUsersRights = document.getElementById("AccessManagementDTO.manageUsersRights").value || 0;

		
		
		if(parseFloat(manageUsers) >= 0){sum = sum + parseFloat(manageUsers);} else {isInvalidValue = true;}
		if(parseFloat(manageUsersRights) >= 0){sum = sum + parseFloat(manageUsersRights);} else {isInvalidValue = true;}
	
		if(!isInvalidValue){document.getElementById("AccessManagementDTO.totalHours").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
	    else{document.getElementById("AccessManagementDTO.totalHours").value = 0;}
		
	    
	} 

	
</script>


<form:form id="AccessManagementForm" action="saveAccessManagement"
	method="post" modelAttribute="accessManagementForm">


	<form:hidden path="opportunityScopeId" />


	<table width="100%">

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right">Manage Users (Hours/Years)</br></td>

			<td class="tdInputBox" align="left" width="70%"><form:input type="text" 
				path="AccessManagementDTO.manageUsers" onkeypress="return isNumericWithDecimal(event,this);" onblur="calculateAMSum();"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10"/></td>

		</tr>
	
		

		<tr >
			<td class="tdLabelLeft" align="right">Manage Users Rights (Hours/Years)</td>

				<td class="tdInputBox" align="left" width="70%"><form:input type="text"
			 	path="AccessManagementDTO.manageUsersRights" onkeypress="return isNumericWithDecimal(event,this);" onblur="calculateAMSum();"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" /> </td> 

		</tr>
		
		<tr >
			<td class="tdLabelLeft" align="right">Total Hours</td>

				<td class="tdInputBox" align="left" width="70%">
				<form:input type="text"
			 	path="AccessManagementDTO.totalHours" 
				class="textBoxSCalculateResults" style="font-weight: bold;"  readonly="true"/> </td> 

		</tr>
		
		<form:input type="hidden" path="AccessManagementDTO.accessManagementID" />
	
	</table>
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center"><a id="resetAC"
						class="TabsCommonButtons">Reset</a> <a id="saveAC"
						class="TabsCommonButtons">Save</a></td>
				</tr>
			</table>
		</div>
	</c:if>
	
</form:form>