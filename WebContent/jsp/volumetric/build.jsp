<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
$('#saveBuild').click(function() {
	//if (validateBuildInputValues()) {
		$("#BuildForm").submit();
	//}
	
});

$('#BuildForm').submit(function(){
	showProgress();
	var options = {
		success : function(html) {
			$("#buildDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			
		},
		url : "./saveBuild"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetBuild').click(
	function(){
		$('#BuildForm').each(
			function(){
			  this.reset();
			});
    	
	}
);

});

function calculateBuildSum() {
	
	var sum = 0;
	var isInvalidValue = false;
	hrsCreatePlan = $("#id_createPlan").val() || 0;
	hrsWriteScript = $("#id_writeScript").val() || 0;
	hrsIdentifyDependency = $("#id_identifyDependency").val() || 0;
	hrsBuildBinary = $("#id_buildBinary").val() || 0;
	
	if(parseInt(hrsCreatePlan) >= 0){sum = sum + parseInt(hrsCreatePlan);} else {isInvalidValue = true;}
	if(parseInt(hrsWriteScript) >= 0){sum = sum + parseInt(hrsWriteScript);} else {isInvalidValue = true;}
	if(parseInt(hrsIdentifyDependency) >= 0){sum = sum + parseInt(hrsIdentifyDependency);} else {isInvalidValue = true;}
	if(parseInt(hrsBuildBinary) >= 0){sum = sum + parseInt(hrsBuildBinary);} else {isInvalidValue = true;}
	
	if(!isInvalidValue){document.getElementById("id_TotalBuildHours").value = sum*12;}  
    else{document.getElementById("id_TotalBuildHours").value = 0;}
	
	if(!validateBuildInputValues()){document.getElementById('saveBuild').hidden = true;}
    else{document.getElementById('saveBuild').hidden = false;}
    
} ;

function validateBuildInputValues() {
	var errorMsg = "<ul>";
	var flag = true;
	document.getElementById("showError").innerHTML = '';
	document.getElementById("showError").style.display = 'block';
	
	if (isNaN(hrsCreatePlan) || hrsCreatePlan < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for Create Plan</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_createPlan").value = "";
		flag = false;
	}
	if (isNaN(hrsWriteScript) || hrsWriteScript < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for Write Script</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_writeScript").value = "";
		flag = false;
	}
	if (isNaN(hrsIdentifyDependency)	|| hrsIdentifyDependency < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for Identification Dependency</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_identifyDependency").value = "";
		flag = false;
	}
	if (isNaN(hrsBuildBinary) || hrsBuildBinary < 0) {
		errorMsg += "<ul><li>Please enter Numeric value for Build Binary</li></ul>";
		document.getElementById("messageDiv").innerHTML = "";
		//document.getElementById("id_buildBinary").value = "";
		flag = false;
	}
	if(!(errorMsg == "<ul>")){
		document.getElementById("showError").innerHTML = errorMsg+"</ul>";
	}
	return flag;
} 

</script>
<form:form id="BuildForm" method="post" modelAttribute="buildForm">

	<table width="100%">
		
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right">Create Plan (Hours / Month)</br></td>
			
			<td class="tdInputBox" align="left" width="70%"><form:input type="text" 
				path="BuildDTO.createPlan"
				id="id_createPlan"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="3" onchange="calculateBuildSum() "/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right">Write Script (Hours / Month)</br></td>
			
			<td class="tdInputBox" align="left" width="70%"><form:input type="text" 
				path="BuildDTO.writeScript"
				id="id_writeScript"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="3" onchange="calculateBuildSum() "/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right">Identification Dependency (Hours / Month)</br></td>
			
			<td class="tdInputBox" align="left" width="70%"><form:input type="text" 
				path="BuildDTO.identifyDependency"
				id="id_identifyDependency"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="3" onchange="calculateBuildSum() "/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right">Build Binary (Hours / Month)</br></td>
			
			<td class="tdInputBox" align="left" width="70%"><form:input type="text" 
				path="BuildDTO.buildBinary"
				id="id_buildBinary"
				class="textBoxSmallNumric" style="font-weight: bold;" maxlength="3" onchange="calculateBuildSum() "/>
			</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right"><b>Total Build Hours (Hours per Year)</b></br></td>
			
			<td class="tdInputBox" align="left" width="70%"><form:input type="text" 
				path="BuildDTO.totalBuildHours"
				id="id_TotalBuildHours"
				class="textBoxCalculateResults" style="font-weight: bold;" maxlength="3" disabled="true" />
			</td>
		</tr>
		<form:input type="hidden" path="BuildDTO.buildId" />
		<form:input type="hidden" path="BuildDTO.opportunityScopeDTO.opportunityScopeId" />
		<form:input type="hidden" path="BuildDTO.solutionDTO.solutionId" />
	</table>
	
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
						<a id="resetBuild"	class="TabsCommonButtons">Reset</a> 
						<a id="saveBuild" class="TabsCommonButtons" onclick="document.getElementById('id_TotalBuildHours').disabled=''">Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>

</form:form>