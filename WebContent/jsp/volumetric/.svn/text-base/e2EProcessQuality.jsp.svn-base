<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<script type="text/javascript">
$(document).ready(function() {
	
$(function(){
	calculateDSSum();
	});
	
$('#saveE2E').click(function() {
	alert(document.getElementById("totalHours").value);
		$("#E2EProcessQualityForm").submit();
	});

$('#E2EProcessQualityForm').submit(function(){

	showProgress();
	var options = {
		success : function(html) {
			
			$("#e2eDiv").html(html);
			$("#messageDiv").addClass("message");
			$("#messageDiv").html("Your data has been saved successfully.");
			refreshServiceBucket();
		},
		url : "./saveE2EProcessQuality"

	};

	$(this).ajaxSubmit(options);
	hideProgress();
	return false;

});

$('#resetE2E').click(
	function(){
		$('#E2EProcessQualityForm').each(
			function(){
			  this.reset();
			  document.getElementById('saveE2E').hidden = false;
			  document.getElementById("showError").innerHTML = '';
			  document.getElementById("showError").style.display = 'block';
			  calculateDSSum();
			});
	}
);


});


function calculateDSSum() {
	 var sum = 0;
	var isInvalidValue = false;
	hrsmonitorBusinessProcess = $("#monitorBusinessProcess").val() || 0;
	hrsmonitorDataQualityAndAlign = $("#monitorDataQualityAndAlign").val() || 0;
	hrsanalyzeBPandDataQuality = $("#analyzeBPandDataQuality").val() || 0;
	hrsimprovementOfProcesses = $("#improvementOfProcesses").val() || 0;
	hrsanalyzeRCofTTVolume = $("#analyzeRCofTTVolume").val() || 0;
	hrsreportPreparation = $("#reportPreparation").val() || 0;
	
	if(parseFloat(hrsmonitorBusinessProcess) >= 0){sum = sum + parseFloat(hrsmonitorBusinessProcess);} else {isInvalidValue = true;}
	if(parseFloat(hrsmonitorDataQualityAndAlign) >= 0){sum = sum + parseFloat(hrsmonitorDataQualityAndAlign);} else {isInvalidValue = true;}
	if(parseFloat(hrsanalyzeBPandDataQuality) >= 0){sum = sum + parseFloat(hrsanalyzeBPandDataQuality);} else {isInvalidValue = true;}
	if(parseFloat(hrsimprovementOfProcesses) >= 0){sum = sum + parseFloat(hrsimprovementOfProcesses);} else {isInvalidValue = true;}
	if(parseFloat(hrsanalyzeRCofTTVolume) >= 0){sum = sum + parseFloat(hrsanalyzeRCofTTVolume);} else {isInvalidValue = true;}
	if(parseFloat(hrsreportPreparation) >= 0){sum = sum + parseFloat(hrsreportPreparation);} else {isInvalidValue = true;}
	
	if(!isInvalidValue){document.getElementById("totalHours").value = parseFloat(sum/"${utilizationPerYear}").toFixed(2);}  
    else{document.getElementById("totalHours").value = 0;}
	

    
} ;



</script>
<form:form id="E2EProcessQualityForm" method="post" modelAttribute="e2EProcessQualityDTO">

<form:hidden path="opportunityScopeDTO.opportunityScopeId" />
<form:input type="hidden" path="e2EProcessQualityId" />

	
	<table width="100%">
		
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Plan, design and develop scripts to monitor the business processes KPIs (volumes/
performances) (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="monitorBusinessProcess"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateDSSum() "/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Plan, design, develop scripts to monitor the data quality and alignment (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="monitorDataQualityAndAlign"
			class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateDSSum()" />
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Analyze business process KPIs and outcomes of data quality checks (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="analyzeBPandDataQuality"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateDSSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Identify, design and managed the relevant improvements, resolution processes or
massive data re-alignments (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="improvementOfProcesses"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateDSSum()"/>
			</td>
		</tr>
		
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Analyze root causes driving trouble tickets volumes (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="analyzeRCofTTVolume"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateDSSum()"/>
			</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%">Reports preparation (Hours/Year)<br></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="reportPreparation"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="10" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);calculateDSSum()"/>
			</td>
		</tr>
		<tr>
			<td class="tdLabelLeft" align="right" width="50%"><b>Total Hours</b></td>
			
			<td class="tdInputBox" align="left" width="50%"><form:input type="text" 
				path="totalHours"
				class="textBoxSCalculateResults" style="font-weight: bold;" maxlength="10"  />
			</td>
		</tr>
		
	</table>
	
	<c:if test="${hasEditSolAccess!='false'}">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 5%">
				<tr>
					<td class="tdButtonRow" align="center">
						<a id="resetE2E"	class="TabsCommonButtons">Reset</a> 
						<a id="saveE2E" class="TabsCommonButtons" >Save</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>

</form:form>