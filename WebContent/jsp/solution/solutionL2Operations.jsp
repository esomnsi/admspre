<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
var derivedFactorL2=0.0;
var isValid=true;
function changeIncidentPCL2(){
	
	var simple = document.getElementById("solutionL2OperationsDTO.pcsimpleIncidents").value;
	var medium = document.getElementById("solutionL2OperationsDTO.pcmediumIncidents").value;
	var complex = document.getElementById("solutionL2OperationsDTO.pccomplexIncidents");

	if(!isNaN(parseInt(simple)) && !isNaN(parseInt(medium))){
		if(parseInt(simple) +parseInt(medium) > 100){
			alert("Sum of the percentage can't be greater than 100");
			document.getElementById("solutionL2OperationsDTO.pcsimpleIncidents").focus();
			return false;
		}
		complex.value = (100 - (parseInt(simple)+parseInt(medium)));
	}
	//alert(complex.value);
}

function handleWindowChangeL2(){
	
		var strObj = "solutionL2OperationsDTO\\.supportWindowMatrixDTO\\.supportWindowMatrixId";
	 	var index = $("#"+strObj).prop("selectedIndex");
		var derivedFacStr = $("#derivedFactorL2").val();
		derivedFacStr = derivedFacStr.substr(1,derivedFacStr.length);
		var arr = derivedFacStr.split(';');
		derivedFactorL2 = arr[index];
	  
		document.getElementById('windowL2Div1').innerHTML = $("#"+strObj+" option:selected").text();
		document.getElementById('windowL2Div2').innerHTML = $("#"+strObj+" option:selected").text();
	  
}

function calTotalL2Incident(){
	var transL1PerYearObj = document.getElementById("solutionL1OperationsDTO.totalL1incidentsperYr");
	var obj = $('#solutionL2OperationsDTO\\.pcofL2incidents');
	 if(transL1PerYearObj != null){
		var l1TransPerYear =  transL1PerYearObj.value;
		
		if(!isNaN(parseInt(l1TransPerYear)) &&  !isNaN(parseInt(obj.val()))){
			
			var transL2PerYear = l1TransPerYear*obj.val()/100;
			$('#solutionL2OperationsDTO\\.totalL2incidentsperYr').val(parseFloat(transL2PerYear).toFixed(2));
		}
	 }
}

$(document).ready (function () {
	handleWindowChangeL2();
	
	  $('#saveL2').click(function() {
		  if($('#dimensionAttributeId') != null){
				$('#Level2SupportForm input[name="solutionDimentionAttId"]').val($('#dimensionAttributeId').val()); 
			}
		  
		  	$('#calculateL2').click();
			$('#calculateL2Add').click();
			 
			
			 if(isValid){
	    		$('#Level2SupportForm').submit();
			 }
	    });
	  
	  
		$("#Level2SupportForm").submit(function() {
			showProgress();
			var options = {
					success: function(html) {
						 $("#operationL2Div").html(html);
						 $("#messageDiv").html("Your data has been saved successfully.");
						//$("#sdDiv").replaceWith($('#sdDiv', $(html)));
						 refreshServiceBucket();
					},
					error: function(res) {
						 $("#messageDiv").html("There is a error while saving data. Please contact administrator");
					},
					url: "./saveSolutionL2Operations"
					
				};
			
				$(this).ajaxSubmit(options);
				hideProgress();
				return false;
		});
	  
	  $('#resetL2').click(function() {
		  	$('#Level2SupportForm').each (function(){
  		  this.reset();
  		});
	    });
	
	  	if(document.getElementById("solutionL1OperationsDTO.totalL1incidentsperYr") == null){
			$('#solutionL2OperationsDTO\\.pcofL2incidents').attr("disabled", true);
		}
	  
	  $('#Level2SupportForm input[name="defaultL2"]').change(function(e){	
		  if (!$(this).is(':checked')) {
			  $('#Level2SupportForm').each (function(){
	    		  this.reset();
	    	});
		  }else{
			 $.ajax({
					type : "POST",
					url : "./loadDefaultValuesForServiceScopes",
					data : {"serviceId": '3'},
					datatype : "text",
					success : function(result){
					
						var data = eval('('+result+')');
						
						$('#solutionL2OperationsDTO\\.pcofL2incidents').val(data.pcofL2incidents);
						
						$('#solutionL2OperationsDTO\\.pcsimpleIncidents').val(data.pcsimpleIncidents);
						$('#solutionL2OperationsDTO\\.pcmediumIncidents').val(data.pcmediumIncidents);
						$('#solutionL2OperationsDTO\\.pccomplexIncidents').val(data.pccomplexIncidents);
						
						$('#solutionL2OperationsDTO\\.hrsSimpleIncidents').val(data.hrsSimpleIncidents);
						$('#solutionL2OperationsDTO\\.hrsMediumIncidents').val(data.hrsMediumIncidents);
						$('#solutionL2OperationsDTO\\.hrsComplexIncidents').val(data.hrsComplexIncidents);
						//$('#solutionL2OperationsDTO\\.utilizationperYr').val(data.utilizationperYr);
						
						calTotalL2Incident();
						
					},
					error : function(e) {
						alert("Operation failed.");
					}
				});	
		  }
	  });
	  
	  $('#solutionL2OperationsDTO\\.supportWindowMatrixDTO\\.supportWindowMatrixId').change(function(){
		  handleWindowChangeL2();
	  });
	  
	  $('#calculateL2').click(function(){
		  	isValid=true;
		  	var simple =  $('#solutionL2OperationsDTO\\.pcsimpleIncidents').val();
			var medium =  $('#solutionL2OperationsDTO\\.pcmediumIncidents').val();
			var complex =  $('#solutionL2OperationsDTO\\.pccomplexIncidents').val();
			
			var simplehrs =  $('#solutionL2OperationsDTO\\.hrsSimpleIncidents').val();
			var mediumhrs =  $('#solutionL2OperationsDTO\\.hrsMediumIncidents').val();
			var complexhrs =  $('#solutionL2OperationsDTO\\.hrsComplexIncidents').val();
			
			//$('#solutionL2OperationsDTO\\.utilizationperYr').val(1020);
			
			var avgResolutionTimeHrsObj  = $('#solutionL2OperationsDTO\\.avgResolutionTimeHrs');
			var anualHrsSpentobj  = $('#solutionL2OperationsDTO\\.anualHrsSpent');
			var totalL2incidentsperYrObj  = $('#solutionL2OperationsDTO\\.totalL2incidentsperYr');
			//var utilizationperYr  =  $('#solutionL2OperationsDTO\\.utilizationperYr').val();
			var baseL2operationFteObj  = $('#solutionL2OperationsDTO\\.baseL2operationFte');
			var augmentedL2operationFteObj  = $('#solutionL2OperationsDTO\\.augmentedL2operationFte');
			
			if(document.getElementById("solutionL1OperationsDTO.totalL1incidentsperYr") != null){
				calTotalL2Incident();
			}
			
			if(isNaN(parseInt(simple)) || isNaN(parseInt(medium)) || isNaN(parseInt(complex)) || 
					isNaN(parseInt(simplehrs)) || isNaN(parseInt(mediumhrs)) || isNaN(parseInt(complexhrs)) ){
				alert("Please enter numeric value for Simple, Medium and Complex ");
				isValid=false;
				return false;
			}
			
			var totalL2incidentsperYr  = totalL2incidentsperYrObj.val();
			
			if(isNaN(parseInt(totalL2incidentsperYr))){
				alert("Please enter value for total no of incidents Level 2 ");
				isValid=false;
				return false;
			}
			var aveResoluTime = (simple/100)*simplehrs + (medium/100)*mediumhrs +(complex/100)*complexhrs ;
			avgResolutionTimeHrsObj.val(aveResoluTime);
			
			var anualHrsSpent = aveResoluTime * parseInt(totalL2incidentsperYr);
			anualHrsSpentobj.val(anualHrsSpent);
			//calculating base FTE
			var baseL2operationFte = anualHrsSpent/utilizationPerYear;
			baseL2operationFteObj.val(parseFloat(baseL2operationFte).toFixed(2));
			//calculating total FTE
		
			var augmentedL2operationFte = baseL2operationFte * parseFloat(derivedFactorL2);
			augmentedL2operationFteObj.val(parseFloat(augmentedL2operationFte).toFixed(2));
		  
	  });
	  
	  
	  $('#calculateL2Add').click(function(){
		  
		  //var utilizationperYr  =  $('#solutionL2OperationsDTO\\.utilizationperYr').val();
		  var addBaseFTE=0.0;
		  var fte;
		  
		  var scope =  $('#solutionL2AddServicesDTO\\.scopeAvailMgmt');
		  var effort =  $('#solutionL2AddServicesDTO\\.effortAvailMgmt');
		  var fteobj =  $('#solutionL2AddServicesDTO\\.fteavailMgmt');
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
	
			  addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  
		  scope =  $('#solutionL2AddServicesDTO\\.scopeCapacityMgmt');
		  effort =  $('#solutionL2AddServicesDTO\\.effortCapacityMgmt');
		  fteobj =  $('#solutionL2AddServicesDTO\\.ftecapacityMgmt');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL2AddServicesDTO\\.scopeSecurityMgmt');
		  effort =  $('#solutionL2AddServicesDTO\\.effortSecurityMgmt');
		  fteobj =  $('#solutionL2AddServicesDTO\\.ftesecurityMgmt');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL2AddServicesDTO\\.scopeL2billOperation');
		  effort =  $('#solutionL2AddServicesDTO\\.effortL2billOperation');
		  fteobj =  $('#solutionL2AddServicesDTO\\.ftel2billOperation');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
				 
		  scope =  $('#solutionL2AddServicesDTO\\.scopeL2preventive');
		  effort =  $('#solutionL2AddServicesDTO\\.effortL2preventive');
		  fteobj =  $('#solutionL2AddServicesDTO\\.ftel2preventive');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL2AddServicesDTO\\.scopeL2change');
		  effort =  $('#solutionL2AddServicesDTO\\.effortL2change');
		  fteobj =  $('#solutionL2AddServicesDTO\\.ftel2change');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  scope =  $('#solutionL2AddServicesDTO\\.scopeL2routine');
		  effort =  $('#solutionL2AddServicesDTO\\.effortL2routine');
		  fteobj =  $('#solutionL2AddServicesDTO\\.ftel2routine');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  scope =  $('#solutionL2AddServicesDTO\\.scopeL2performance');
		  effort =  $('#solutionL2AddServicesDTO\\.effortL2performance');
		  fteobj =  $('#solutionL2AddServicesDTO\\.ftel2performance');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL2AddServicesDTO\\.scopeL2releaseDev');
		  effort =  $('#solutionL2AddServicesDTO\\.effortL2releaseDev');
		  fteobj =  $('#solutionL2AddServicesDTO\\.ftel2releaseDev');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  
		
		  var augmentedL2serviceFte = parseFloat(addBaseFTE) * parseFloat(derivedFactorL2);
	
		  $('#solutionL2AddServicesDTO\\.baseL2serviceFte').val(parseFloat(addBaseFTE).toFixed(2));
		  $('#solutionL2AddServicesDTO\\.augmentedL2serviceFte').val(parseFloat(augmentedL2serviceFte).toFixed(2));
		  

	  });
	  
	  //end of calculation for additional FTE
	
	  
	  $('#solutionL2AddServicesDTO\\.scopeAvailMgmt').change(function(){
		  var obj1 =  $('#solutionL2AddServicesDTO\\.scopeAvailMgmt');
		  var obj2 =  $('#solutionL2AddServicesDTO\\.effortAvailMgmt');
		  var obj3 =  $('#solutionL2AddServicesDTO\\.fteavailMgmt');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true);
			  obj3.attr("disabled", true);
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	  
	  $('#solutionL2AddServicesDTO\\.scopeCapacityMgmt').change(function(){
		  
		  var obj1 =  $('#solutionL2AddServicesDTO\\.scopeCapacityMgmt');
		  var obj2 =  $('#solutionL2AddServicesDTO\\.effortCapacityMgmt');
		  var obj3 =  $('#solutionL2AddServicesDTO\\.ftecapacityMgmt');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	  $('#solutionL2AddServicesDTO\\.scopeSecurityMgmt').change(function(){
		  
		  var obj1 =  $('#solutionL2AddServicesDTO\\.scopeSecurityMgmt');
		  var obj2 =  $('#solutionL2AddServicesDTO\\.effortSecurityMgmt');
		  var obj3 =  $('#solutionL2AddServicesDTO\\.ftesecurityMgmt');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
		 
	  });
	  $('#solutionL2AddServicesDTO\\.scopeL2billOperation').change(function(){
		  
		  var obj1 =  $('#solutionL2AddServicesDTO\\.scopeL2billOperation');
		  var obj2 =  $('#solutionL2AddServicesDTO\\.effortL2billOperation');
		  var obj3 =  $('#solutionL2AddServicesDTO\\.ftel2billOperation');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	  $('#solutionL2AddServicesDTO\\.scopeL2preventive').change(function(){
		  
		  var obj1 =  $('#solutionL2AddServicesDTO\\.scopeL2preventive');
		  var obj2 =  $('#solutionL2AddServicesDTO\\.effortL2preventive');
		  var obj3 =  $('#solutionL2AddServicesDTO\\.ftel2preventive');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	  $('#solutionL2AddServicesDTO\\.scopeL2change').change(function(){
		  
		  var obj1 =  $('#solutionL2AddServicesDTO\\.scopeL2change');
		  var obj2 =  $('#solutionL2AddServicesDTO\\.effortL2change');
		  var obj3 =  $('#solutionL2AddServicesDTO\\.ftel2change');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	  
	  $('#solutionL2AddServicesDTO\\.scopeL2routine').change(function(){
		  
		  var obj1 =  $('#solutionL2AddServicesDTO\\.scopeL2routine');
		  var obj2 =  $('#solutionL2AddServicesDTO\\.effortL2routine');
		  var obj3 =  $('#solutionL2AddServicesDTO\\.ftel2routine');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
		});
	  
	  $('#solutionL2AddServicesDTO\\.scopeL2performance').change(function(){
		  
		  var obj1 =  $('#solutionL2AddServicesDTO\\.scopeL2performance');
		  var obj2 =  $('#solutionL2AddServicesDTO\\.effortL2performance');
		  var obj3 =  $('#solutionL2AddServicesDTO\\.ftel2performance');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	 
 $('#solutionL2AddServicesDTO\\.scopeL2releaseDev').change(function(){
		  
		  var obj1 =  $('#solutionL2AddServicesDTO\\.scopeL2releaseDev');
		  var obj2 =  $('#solutionL2AddServicesDTO\\.effortL2releaseDev');
		  var obj3 =  $('#solutionL2AddServicesDTO\\.ftel2releaseDev');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
});



</script>


<form:form id="Level2SupportForm" action="./saveSolutionL2Operations" method="post" modelAttribute="solutionL2OperationsForm">
<div>
		<form:hidden path="solutionL2OperationsDTO.solutionL2operationsId"/>
	<form:hidden path="solutionL2AddServicesDTO.solutionL2addServicesId"/>
	<form:hidden path="solutionDimentionAttId" /> 
	<form:hidden path="opportunityScopeId"/> 
	
	<table width="100%">
				<tr>
				<td class="tdLabelLeft">Window Of Operation</td>
				<td class="tdLabelLeft">
					<form:select path="solutionL2OperationsDTO.supportWindowMatrixDTO.supportWindowMatrixId" class="listBoxSmall" >
					<c:forEach var="item" items="${supportWindowList}">
					<c:set var="derivedFactor" value="${derivedFactor};${item.derivedFactor}" />
						<form:option  value="${item.supportWindowMatrixId}"> ${item.supportWindow}</form:option>
						
					</c:forEach>
				</form:select>
				<input type="hidden" id="derivedFactorL2"  value="${derivedFactor}" />
				</td>
			
			 	 
	        <td width="18%" class="tdInputBox">
	          <input type="checkbox" name="defaultL2" value="" >
	          <b>Default</b></td>
	        <td width="20%">&nbsp;</td>
	    
	      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="3" align="left" valign="top" class="tdInputBox"></td><td width="0%"></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Percentage of L1 Incidents converting into L2 Incidents</td>
        <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.pcofL2incidents" class="textBoxSmallNumric" maxlength="3" onblur="calTotalL2Incident();" onkeypress="return isDecimal(event);" /></td>
        <td colspan="3" rowspan="7" align="left" valign="top" class="tdInputBox"><table width="560" cellspacing="3" style="background-color:#fbfbfb; border:1px solid #ededed;">
          <tr>
            <td width="33%" align="center"><b>Simple</b></td>
            <td width="34%" align="center"><b>Medium</b></td>
            <td width="33%" align="center"><b>Complex</b></td>
          </tr>
          <tr>
            <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.pcsimpleIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);" onblur="changeIncidentPCL2()"/>%</td>
            <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.pcmediumIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);" onblur="changeIncidentPCL2()" />%</td>
            <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.pccomplexIncidents" class="textBoxCalculateResults"  onkeypress="return isDecimal(event);" readonly="true"/>%</td>
          </tr>
          
           <tr>
            <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.hrsSimpleIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);" /></td>
            <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.hrsMediumIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);" /></td>
            <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.hrsComplexIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);"/></td>
        
          </tr>
          <tr>
            <td class="tdLabelLeft">&nbsp;</td>
            <td class="tdLabelLeft">&nbsp;</td>
            <td class="tdInputBox">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
        <tr>
            <td class="tdLabelLeft">&nbsp;</td>
            <td class="tdLabelLeft">OR</td>
          
          </tr>
      <tr>
        <td class="tdLabelLeft">Total Volume of Incidents at Level 2 Per Year</td>
     		<td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.totalL2incidentsperYr" class="textBoxSmallNumric" maxlength="6" onkeypress="return isDecimal(event);" /></td>  
      </tr>
      <tr>
        <td class="tdLabelLeft">Avg. resolution time per incident (in Hr.)</td>
          <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.avgResolutionTimeHrs" class="textBoxCalculateResults"  readonly="true" /></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Annual Hours Spent in Solving Incidents at Level 2</td>
          <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.anualHrsSpent" class="textBoxCalculateResults"  readonly="true" /></td>
      </tr>
       <%--  <tr>
        <td class="tdLabelLeft">Utilization Per Year</td>
          <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.utilizationperYr" class="textBoxSmallNumric"  maxlength="4"  /></td>
      </tr> --%>
      <tr>
        <td class="tdLabelLeft">Base L2 FTE</td>
          <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.baseL2operationFte" class="textBoxCalculateResults"  readonly="true" /></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Augmented Heads for <b> <span id="windowL2Div1" > </span></b></td>
         <td class="tdLabelLeft"><form:input path="solutionL2OperationsDTO.augmentedL2operationFte" class="textBoxCalculateResults"  readonly="true" /></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
           	<c:if test="${hasEditSolAccess!='false'}">
        <td><a id="calculateL2" class="TabsCommonButtons" >Calculate</a></td>
        </c:if>
      </tr>
    </table>
</div>

<div>

    <table width="984" cellspacing="6" style="background-color:#fbfbfb; border:1px solid #ededed; margin-top:20px;" >
      <tr>
        <td width="42%" height="30" align="left" class="tdLabelLeft"><b>Additional Level 2 Services</b></td>
        <td width="19%" align="center" class="tdLabelLeft"><b>In Scope</b></td>
        <td width="19%" align="center" class="tdInputBox"><b>Monthly Effort (Hours)</b></td>
        <td width="20%" align="center" class="tdInputBox"><b>FTE</b></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Availability Management</td>
        <td class="tdLabelLeft"><form:select path="solutionL2AddServicesDTO.scopeAvailMgmt" class="listBoxSmall" >
        <form:options items="${scopeList}" />
        </form:select></td>
        <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.effortAvailMgmt" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.fteavailMgmt" class="textBoxCalculateResults" readonly="true" /></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Capacity Management</td>
           <td class="tdLabelLeft"><form:select path="solutionL2AddServicesDTO.scopeCapacityMgmt" class="listBoxSmall" >
          <form:options items="${scopeList}" />
        </form:select></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.effortCapacityMgmt" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.ftecapacityMgmt" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Security Management</td>
            <td class="tdLabelLeft"><form:select path="solutionL2AddServicesDTO.scopeSecurityMgmt" class="listBoxSmall" >
         <form:options items="${scopeList}" />
       </form:select></td>
         <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.effortSecurityMgmt" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.ftesecurityMgmt" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L2 Billing Operations</td>
            <td class="tdLabelLeft"><form:select path="solutionL2AddServicesDTO.scopeL2billOperation" class="listBoxSmall" >
         <form:options items="${scopeList}" />
        </form:select></td>
        <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.effortL2billOperation" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.ftel2billOperation" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L2 Preventive Maintenance</td>
           <td class="tdLabelLeft"><form:select path="solutionL2AddServicesDTO.scopeL2preventive" class="listBoxSmall" >
         <form:options items="${scopeList}" />
        </form:select></td>
       <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.effortL2preventive" class="textBoxSmallNumric" omaxlength="5" nkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.ftel2preventive" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L2 Change Analysis</td>
           <td class="tdLabelLeft"><form:select path="solutionL2AddServicesDTO.scopeL2change" class="listBoxSmall" >
         <form:options items="${scopeList}" />
        </form:select></td>
       <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.effortL2change" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.ftel2change" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L2 Routine Operations</td>
            <td class="tdLabelLeft"><form:select path="solutionL2AddServicesDTO.scopeL2routine" class="listBoxSmall" >
         <form:options items="${scopeList}" />
        </form:select></td>
        <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.effortL2routine" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);" /></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.ftel2routine" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L2 Performance Reporting</td>
             <td class="tdLabelLeft"><form:select path="solutionL2AddServicesDTO.scopeL2performance" class="listBoxSmall" >
        <form:options items="${scopeList}" />
        </form:select></td>
       <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.effortL2performance" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);" /></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.ftel2performance" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L2 Release & Deployment</td>
             <td class="tdLabelLeft"><form:select path="solutionL2AddServicesDTO.scopeL2releaseDev" class="listBoxSmall" >
          <form:options items="${scopeList}" />
        </form:select></td>
       <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.effortL2releaseDev" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);" /></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.ftel2releaseDev" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      
      <tr>
        <td class="tdLabelLeft">Additional Base L2 FTE</td>
          <td class="tdLabelLeft"></td>
    	  <td class="tdInputBox"></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.baseL2serviceFte" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Augmented Additional Heads for <b> <span id="windowL2Div2" > </span></b></td>
          <td class="tdLabelLeft"></td>
      	  <td class="tdInputBox"></td>
          <td class="tdInputBox"><form:input path="solutionL2AddServicesDTO.augmentedL2serviceFte" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
    </table>
     <c:if test="${hasEditSolAccess!='false'}">
    <div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 5x;">
	  <table width="100%" border="0" style="padding-right:5%" >
	    <tr>
	      <td class="tdButtonRow">
	      	<a id="resetL2" class="TabsCommonButtons" >Reset</a>
	      	<a id="saveL2" class="TabsCommonButtons" >Save</a>
	      	<a id="calculateL2Add" class="TabsCommonButtons" >Calculate</a>
	      </td>
	    </tr>
	  </table>
	</div>
	</c:if>
</div>
</form:form>