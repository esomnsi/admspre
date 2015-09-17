<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>

$(document).ready(function() {
	
	
		calculateSASum();
		
		
	$('#saveSA').click(function() {
			$("#ServiceAssuranceForm").submit();
		});

	$('#ServiceAssuranceForm').submit(function(){

		showProgress();
		var options = {
			success : function(html) {
				$("#servAssurDiv").html(html);
				$("#messageDiv").addClass("message");
				$("#messageDiv").html("Your data has been saved successfully.");
				refreshServiceBucket();
			},
			url : "./saveServiceAssurance"

		};

		$(this).ajaxSubmit(options);
		hideProgress();
		return false;

	});

	$('#resetSA').click(
		function(){
			$('#ServiceAssuranceForm').each(
				function(){
				  this.reset();
				  document.getElementById('saveSA').hidden = false;
				  document.getElementById("showError").innerHTML = '';
				  document.getElementById("showError").style.display = 'block';
				  calculateSASum();
				});
		}
	);


	});


	function calculateSASum() {
		var sum = 0;
		var isInvalidValue = false;
		hrsHelpDeskSupport = checkNull(document.getElementById("serviceAssuranceDTO.helpDeskSupport").value);
		hrsIncidentManagement = checkNull(document.getElementById("serviceAssuranceDTO.incidentManagement").value);
		hrsProblemManagement = checkNull(document.getElementById("serviceAssuranceDTO.problemManagement").value);
		hrsImpactAnalysis = checkNull(document.getElementById("serviceAssuranceDTO.impactAnalysis").value);
		hrsDataAlignment = checkNull(document.getElementById("serviceAssuranceDTO.dataAlignment").value);
		hrsIncidentReporting = checkNull(document.getElementById("serviceAssuranceDTO.incidentReporting").value);
		hrsBugFixing = checkNull(document.getElementById("serviceAssuranceDTO.bugFixing").value);

		document.getElementById("serviceAssuranceDTO.helpDeskSupport").value = hrsHelpDeskSupport;
		document.getElementById("serviceAssuranceDTO.incidentManagement").value = hrsIncidentManagement;
		document.getElementById("serviceAssuranceDTO.problemManagement").value = hrsProblemManagement;
		document.getElementById("serviceAssuranceDTO.impactAnalysis").value = hrsImpactAnalysis;
		document.getElementById("serviceAssuranceDTO.dataAlignment").value = hrsDataAlignment;
		document.getElementById("serviceAssuranceDTO.incidentReporting").value = hrsIncidentReporting;
		document.getElementById("serviceAssuranceDTO.bugFixing").value = hrsBugFixing;
		
		if(parseFloat(hrsHelpDeskSupport) >= 0){sum = sum + parseFloat(hrsHelpDeskSupport);} else {isInvalidValue = true;}
		if(parseFloat(hrsIncidentManagement) >= 0){sum = sum + parseFloat(hrsIncidentManagement);} else {isInvalidValue = true;}
		if(parseFloat(hrsProblemManagement) >= 0){sum = sum + parseFloat(hrsProblemManagement);} else {isInvalidValue = true;}
		if(parseFloat(hrsImpactAnalysis) >= 0){sum = sum + parseFloat(hrsImpactAnalysis);} else {isInvalidValue = true;}
		if(parseFloat(hrsDataAlignment) >= 0){sum = sum + parseFloat(hrsDataAlignment);} else {isInvalidValue = true;}
		if(parseFloat(hrsIncidentReporting) >= 0){sum = sum + parseFloat(hrsIncidentReporting);} else {isInvalidValue = true;}
		if(parseFloat(hrsBugFixing) >= 0){sum = sum + parseFloat(hrsBugFixing);} else {isInvalidValue = true;}
	
		if(!isInvalidValue){document.getElementById("serviceAssuranceDTO.totalServiceAssuranceHours").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
	    else{document.getElementById("serviceAssuranceDTO.totalServiceAssuranceHours").value = 0;}
		
	    
	} 

	function checkNull(value){
		if(value){
			return value;
		}else{
			return 0;
		}
		
	};
	
</script>


<form:form id="ServiceAssuranceForm" action="saveServiceAssurance"
	method="post" modelAttribute="serviceAssuranceForm">


	<form:hidden path="opportunityScopeId" />


	<table width="100%">

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Help Desk: Single point of contact for IT systems users,to manage trouble tickets in an
efficient and effective manner for resotration of normal , operational services. (Hours/Years)</br></td>

			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="serviceAssuranceDTO.helpDeskSupport" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateSASum();"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10"/></td>

		</tr>
	
		

		<tr >
			<td class="tdLabelLeft" align="right" width="50%">Incident Management: Restoriation of normal service operation (within SLA limits) as
quickly as possible while minimizing the impact on business operations. (Hours/Years)</td>

				<td class="tdInputBox" align="left" width="50%"><form:input type="text"
			 	path="serviceAssuranceDTO.incidentManagement" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateSASum();"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" /> </td> 

		</tr>
		
		<tr >
			<td class="tdLabelLeft" align="right" width="50%">Problem Management. TT management and root cause troubleshooting for incidents in
order to initiate actions to improve or correct the situation. (Hours/Years)</td>

				<td class="tdInputBox" align="left" width="50%"><form:input type="text"
			 	path="serviceAssuranceDTO.problemManagement" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateSASum();"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" /> </td> 

		</tr>
		
		<tr >
			<td class="tdLabelLeft" align="right" width="50%">Impact analysis for service interruptions (Hours/Years)</td>

				<td class="tdInputBox" align="left" width="50%"><form:input type="text"
			 	path="serviceAssuranceDTO.impactAnalysis" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateSASum();"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" /> </td> 

		</tr>
		
		<tr >
			<td class="tdLabelLeft" align="right" width="50%">Data alignment procedures for individual touble tickets. (Hours/Years)</td>

				<td class="tdInputBox" align="left" width="50%"><form:input type="text"
			 	path="serviceAssuranceDTO.dataAlignment" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateSASum();"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" /> </td> 

		</tr>
		
		<tr >
			<td class="tdLabelLeft" align="right" width="50%">Incident reporting. (Hours/Years)</td>

				<td class="tdInputBox" align="left" width="50%"><form:input type="text"
			 	path="serviceAssuranceDTO.incidentReporting" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateSASum();"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" /> </td> 

		</tr>
		
		<tr >
			<td class="tdLabelLeft" align="right" width="50%">Bug fixing (not for thrid party products). (Hours/Years)</td>

				<td class="tdInputBox" align="left" width="50%"><form:input type="text"
			 	path="serviceAssuranceDTO.bugFixing" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateSASum();"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" /> </td> 

		</tr>
		
		
		<tr >
			<td class="tdLabelLeft" align="right" width="50%"><b>FTE for Service Assurance</b></td>

				<td class="tdInputBox" align="left" width="50%">
				<form:input type="text"
			 	path="serviceAssuranceDTO.totalServiceAssuranceHours" 
				class="textBoxSCalculateResults" style="font-weight: bold;"  readonly="true"/> </td> 

		</tr>
		
		<form:input type="hidden" path="serviceAssuranceDTO.serviceAssuranceID" />
	
	</table>
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
					<a id="resetSA" class="TabsCommonButtons">Reset</a> 
					<a id="saveSA"	class="TabsCommonButtons">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
	
</form:form>