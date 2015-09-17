<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
var derivedFactorL3=0.0;
function changeIncidentPCL3(){
	
	var simple = document.getElementById("solutionL3OperationsDTO.pcsimpleIncidents").value;
	var medium = document.getElementById("solutionL3OperationsDTO.pcmediumIncidents").value;
	var complex = document.getElementById("solutionL3OperationsDTO.pccomplexIncidents");

	if(!isNaN(parseInt(simple)) && !isNaN(parseInt(medium))){
		if(parseInt(simple) +parseInt(medium) > 100){
			alert("Sum of the percentage can't be greater than 100");
			document.getElementById("solutionL3OperationsDTO.pcmediumIncidents").focus();
			return false;
		}
		complex.value = (100 - (parseInt(simple)+parseInt(medium)));
	}
	//alert(complex.value);
}

function handleWindowChangeL3(){
	
		var strObj = "solutionL3OperationsDTO\\.supportWindowMatrixDTO\\.supportWindowMatrixId";
	 	var index = $("#"+strObj).prop("selectedIndex");
		var derivedFacStr = $("#derivedFactorL3").val();
		derivedFacStr = derivedFacStr.substr(1,derivedFacStr.length);
		var arr = derivedFacStr.split(';');
		derivedFactorL3 = arr[index];
	  
		document.getElementById('windowL3Div1').innerHTML = $("#"+strObj+" option:selected").text();
		document.getElementById('windowL3Div2').innerHTML = $("#"+strObj+" option:selected").text();
	  
}

function calTotalL3Incident(){
	var transL2PerYearObj = document.getElementById("solutionL2OperationsDTO.totalL2incidentsperYr");
	var obj = $('#solutionL3OperationsDTO\\.pcofL2incidentsConvL3');
	 if(transL2PerYearObj != null){
		var l2TransPerYear =  transL2PerYearObj.value;
		
		if(!isNaN(parseInt(l2TransPerYear)) &&  !isNaN(parseInt(obj.val()))){
			
			var transL3PerYear = l2TransPerYear*obj.val()/100;
			$('#solutionL3OperationsDTO\\.totalL3bugFixesPerYear').val(parseFloat(transL3PerYear).toFixed(2));
		}
	 }
}

$(document).ready (function () {
	handleWindowChangeL3();
	
	  $('#saveL3').click(function() {
		  
		  if($('#dimensionAttributeId') != null){
				$('#Level3SupportForm input[name="solutionDimentionAttId"]').val($('#dimensionAttributeId').val()); 
			}
		  
		  	$('#calculateL3').click();
			$('#calculateL3Add').click();
			
	    	$('#Level3SupportForm').submit();
	    });
	  
	  $('#resetL3').click(function() {
		  	$('#Level3SupportForm').each (function(){
    		  this.reset();
    		});
	    });
	  
	  $("#Level3SupportForm").submit(function() {
			showProgress();
			var options = {
					success: function(html) {
						 $("#operationL3Div").html(html);
						 $("#messageDiv").addClass("message");
						 $("#messageDiv").html("Your data has been saved successfully.");
						//$("#sdDiv").replaceWith($('#sdDiv', $(html)));
						 refreshServiceBucket();
					},
					error: function(res) {
						 $("#messageDiv").html("There is a error while saving data. Please contact administrator");
					},
					url: "./saveSolutionL3Operations"
					
				};
			
				$(this).ajaxSubmit(options);
				hideProgress();
				return false;
		});
	  
	  
	  if(document.getElementById("solutionL2OperationsDTO.totalL2incidentsperYr") == null){
			$('#solutionL3OperationsDTO\\.pcofL2incidentsConvL3').attr("disabled", true);
		}
	  
	  $('#Level3SupportForm input[name="defaultL3"]').change(function(e){	
		   if (!$(this).is(':checked')) {
			  $('#Level3SupportForm').each (function(){
	    		  this.reset();
	    	});
		  }else{
			 $.ajax({
					type : "POST",
					url : "./loadDefaultValuesForServiceScopes",
					data : {"serviceId": '4'},
					datatype : "text",
					success : function(result){
						var data = eval('('+result+')');
						
						$('#solutionL3OperationsDTO\\.pcofL2incidentsConvL3').val(data.pcofL2incidentsConvL3);
						
						$('#solutionL3OperationsDTO\\.pcsimpleIncidents').val(data.pcsimpleIncidents);
						$('#solutionL3OperationsDTO\\.pcmediumIncidents').val(data.pcmediumIncidents);
						$('#solutionL3OperationsDTO\\.pccomplexIncidents').val(data.pccomplexIncidents);
						
						$('#solutionL3OperationsDTO\\.hrsSimpleIncidents').val(data.hrsSimpleIncidents);
						$('#solutionL3OperationsDTO\\.hrsMediumIncidents').val(data.hrsMediumIncidents);
						$('#solutionL3OperationsDTO\\.hrsComplexIncidents').val(data.hrsComplexIncidents);
						//$('#solutionL3OperationsDTO\\.utilizationperYr').val(data.utilizationperYr);
						
						calTotalL3Incident();
						
					},
					error : function(e) {
						alert("Operation failed.");
					}
				});	
		  }
	  });
	
	  
	  $('#solutionL3OperationsDTO\\.supportWindowMatrixDTO\\.supportWindowMatrixId').change(function(){
		  handleWindowChangeL3();
	  });
	  
	  $('#calculateL3').click(function(){
		  
		  	var simple =  $('#solutionL3OperationsDTO\\.pcsimpleIncidents').val();
			var medium =  $('#solutionL3OperationsDTO\\.pcmediumIncidents').val();
			var complex =  $('#solutionL3OperationsDTO\\.pccomplexIncidents').val();
			
			var simplehrs =  $('#solutionL3OperationsDTO\\.hrsSimpleIncidents').val();
			var mediumhrs =  $('#solutionL3OperationsDTO\\.hrsMediumIncidents').val();
			var complexhrs =  $('#solutionL3OperationsDTO\\.hrsComplexIncidents').val();
			
			
			var avgResolutionTimeHrsObj  = $('#solutionL3OperationsDTO\\.avgResolutionTimeHrs');
			var anualHrsSpentobj  = $('#solutionL3OperationsDTO\\.anualHrsSpent');
			var totalL3bugFixesPerYearObj  = $('#solutionL3OperationsDTO\\.totalL3bugFixesPerYear');
			//var utilizationperYr  =  $('#solutionL3OperationsDTO\\.utilizationperYr').val();
			var baseL3operationFteObj  = $('#solutionL3OperationsDTO\\.baseL3operationFte');
			var augmentedL3operationFteObj  = $('#solutionL3OperationsDTO\\.augmentedL3operationFte');
			
			 if(document.getElementById("solutionL2OperationsDTO.totalL2incidentsperYr") != null){
				  calTotalL3Incident();
			}
			
			if(isNaN(parseInt(simple)) || isNaN(parseInt(medium)) || isNaN(parseInt(complex)) || 
					isNaN(parseInt(simplehrs)) || isNaN(parseInt(mediumhrs)) || isNaN(parseInt(complexhrs)) ){
				alert("Please enter numeric value for Simple, Medium and Complex ");
				return false;
			}
			
			var totalL3bugFixesPerYear  = totalL3bugFixesPerYearObj.val();
			if(isNaN(parseInt(totalL3bugFixesPerYear))){
				alert("Please enter value for total volume of bug fixes level 3 ");
				return false;
			}
			
			totalL3bugFixesPerYearObj.val(parseFloat(totalL3bugFixesPerYear).toFixed(2));
			
			var aveResoluTime = (simple/100)*simplehrs + (medium/100)*mediumhrs +(complex/100)*complexhrs ;
			avgResolutionTimeHrsObj.val(parseFloat(aveResoluTime).toFixed(2));
			
			var anualHrsSpent = aveResoluTime * parseInt(totalL3bugFixesPerYear);
			anualHrsSpentobj.val(parseFloat(anualHrsSpent).toFixed(2));
			//calculating base FTE
			var baseL3operationFte = anualHrsSpent/utilizationPerYear;
			baseL3operationFteObj.val(parseFloat(baseL3operationFte).toFixed(2));
			//calculating total FTE
		
			var augmentedL3operationFte = baseL3operationFte * parseFloat(derivedFactorL3);
			augmentedL3operationFteObj.val(parseFloat(augmentedL3operationFte).toFixed(2));
		  
	  });
	  
	  
	  $('#calculateL3Add').click(function(){
		  
		 // var utilizationperYr  =  $('#solutionL3OperationsDTO\\.utilizationperYr').val();
		  var addBaseFTE=0.0;
		  var fte;
		  var anualHrsSpent  = $('#solutionL3OperationsDTO\\.anualHrsSpent').val();
		  
		  var scope =  $('#solutionL3AddServicesDTO\\.scopeIntegTest');
		  var unit =  $('#solutionL3AddServicesDTO\\.unitIntegTest');
		  var effort =  $('#solutionL3AddServicesDTO\\.valueIntegTest');
		  var fteobj =  $('#solutionL3AddServicesDTO\\.fteintegTest');
		  
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  if(unit.val()=='1' && !isNaN(anualHrsSpent)){
				  fte = (parseFloat(effort.val())/100)*anualHrsSpent/utilizationPerYear;
			  }else{
			 	 fte = parseInt(effort.val())*12/utilizationPerYear;
			  }
			 
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
	
			  addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL3AddServicesDTO\\.scopeRegreTest');
		  unit =  $('#solutionL3AddServicesDTO\\.unitRegreTest');
		  effort =  $('#solutionL3AddServicesDTO\\.valueRegreTest');
		  fteobj =  $('#solutionL3AddServicesDTO\\.fteregreTest');
		  
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  if(unit.val()=='1' && !isNaN(anualHrsSpent)){
				  fte = (parseFloat(effort.val())/100)*anualHrsSpent/utilizationPerYear;
			  }else{
			 	 fte = parseInt(effort.val())*12/utilizationPerYear;
			  }
			 
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
	
			  addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL3AddServicesDTO\\.scopeOtherTest');
		  unit =  $('#solutionL3AddServicesDTO\\.unitOtherTest');
		  effort =  $('#solutionL3AddServicesDTO\\.valueOtherTest');
		  fteobj =  $('#solutionL3AddServicesDTO\\.fteotherTest');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  if(unit.val()=='1' && !isNaN(anualHrsSpent)){
				  fte = (parseFloat(effort.val())/100)*anualHrsSpent/utilizationPerYear;
			  }else{
			 	 fte = parseInt(effort.val())*12/utilizationPerYear;
			  }
			 
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
	
			  addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL3AddServicesDTO\\.scopeMisceSupp');
		  unit =  $('#solutionL3AddServicesDTO\\.unitMisceSupp');
		  effort =  $('#solutionL3AddServicesDTO\\.valueMisceSupp');
		  fteobj =  $('#solutionL3AddServicesDTO\\.ftemisceSupp');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  if(unit.val()=='1' && !isNaN(anualHrsSpent)){
				  fte = (parseFloat(effort.val())/100)*anualHrsSpent/utilizationPerYear;
			  }else{
			 	 fte = parseInt(effort.val())*12/utilizationPerYear;
			  }
			 
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
	
			  addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL3AddServicesDTO\\.scopePhysicalDatabase');
		  unit =  $('#solutionL3AddServicesDTO\\.unitPhysicalDatabase');
		  effort =  $('#solutionL3AddServicesDTO\\.valuePhysicalDatabase');
		  fteobj =  $('#solutionL3AddServicesDTO\\.ftephysicalDatabase');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  if(unit.val()=='1' && !isNaN(anualHrsSpent)){
				  fte = (parseFloat(effort.val())/100)*anualHrsSpent/utilizationPerYear;
			  }else{
			 	 fte = parseInt(effort.val())*12/utilizationPerYear;
			  }
			 
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
	
			  addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
				 
		  scope =  $('#solutionL3AddServicesDTO\\.scopeServerAndVirt');
		  unit =  $('#solutionL3AddServicesDTO\\.unitServerAndVirt');
		  effort =  $('#solutionL3AddServicesDTO\\.valueServerAndVirt');
		  fteobj =  $('#solutionL3AddServicesDTO\\.fteserverAndVirt');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  if(unit.val()=='1' && !isNaN(anualHrsSpent)){
				  fte = (parseFloat(effort.val())/100)*anualHrsSpent/utilizationPerYear;
			  }else{
			 	 fte = parseInt(effort.val())*12/utilizationPerYear;
			  }
			 
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
	
			  addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL3AddServicesDTO\\.scopeOtherInfra');
		  unit =  $('#solutionL3AddServicesDTO\\.unitOtherInfra');
		  effort =  $('#solutionL3AddServicesDTO\\.valueOtherInfra');
		  fteobj =  $('#solutionL3AddServicesDTO\\.fteotherInfra');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  if(unit.val()=='1' && !isNaN(anualHrsSpent)){
				  fte = (parseFloat(effort.val())/100)*anualHrsSpent/utilizationPerYear;
			  }else{
			 	 fte = parseInt(effort.val())*12/utilizationPerYear;
			  }
			 
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
	
			  addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		
		
		  var augmentedL3serviceFte = parseFloat(addBaseFTE) * parseFloat(derivedFactorL3);
	
		  $('#solutionL3AddServicesDTO\\.baseL3serviceFte').val(parseFloat(addBaseFTE).toFixed(2));
		  $('#solutionL3AddServicesDTO\\.augmentedL3serviceFte').val(parseFloat(augmentedL3serviceFte).toFixed(2));
		  

	  });
	  
	  //end of calculation for additional FTE
	
	  
	  $('#solutionL3AddServicesDTO\\.scopeIntegTest').change(function(){
		  var obj1 =  $('#solutionL3AddServicesDTO\\.scopeIntegTest');
		  var obj2 =  $('#solutionL3AddServicesDTO\\.valueIntegTest');
		  var obj3 =  $('#solutionL3AddServicesDTO\\.fteintegTest');
		  var obj4 =  $('#solutionL3AddServicesDTO\\.unitIntegTest');
		  
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true);obj2.val(''); 
			  obj4.attr("disabled", true);obj3.val(''); 
			  
		  }else{
			  obj2.attr("disabled", false);
			  obj4.attr("disabled", false);
		  }
	  });
	  
	  $('#solutionL3AddServicesDTO\\.scopeRegreTest').change(function(){
		  
		  var obj1 =  $('#solutionL3AddServicesDTO\\.scopeRegreTest');
		  var obj2 =  $('#solutionL3AddServicesDTO\\.valueRegreTest');
		  var obj3 =  $('#solutionL3AddServicesDTO\\.fteregreTest');
		  var obj4 =  $('#solutionL3AddServicesDTO\\.unitRegreTest');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true);obj2.val(''); 
			  obj4.attr("disabled", true);obj3.val(''); 
			  
		  }else{
			  obj2.attr("disabled", false);
			  obj4.attr("disabled", false);
		  }
	  });
	  $('#solutionL3AddServicesDTO\\.scopeOtherTest').change(function(){
		  
		  var obj1 =  $('#solutionL3AddServicesDTO\\.scopeOtherTest');
		  var obj2 =  $('#solutionL3AddServicesDTO\\.valueOtherTest');
		  var obj3 =  $('#solutionL3AddServicesDTO\\.fteotherTest');
		  var obj4 =  $('#solutionL3AddServicesDTO\\.unitOtherTest');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true);obj2.val(''); 
			  obj4.attr("disabled", true);obj3.val(''); 
			  
		  }else{
			  obj2.attr("disabled", false);
			  obj4.attr("disabled", false);
		  }
		 
	  });
	  $('#solutionL3AddServicesDTO\\.scopeMisceSupp').change(function(){
		  
		  var obj1 =  $('#solutionL3AddServicesDTO\\.scopeMisceSupp');
		  var obj2 =  $('#solutionL3AddServicesDTO\\.valueMisceSupp');
		  var obj3 =  $('#solutionL3AddServicesDTO\\.ftemisceSupp');
		  var obj4 =  $('#solutionL3AddServicesDTO\\.unitMisceSupp');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true);obj2.val(''); 
			  obj4.attr("disabled", true);obj3.val(''); 
			  
		  }else{
			  obj2.attr("disabled", false);
			  obj4.attr("disabled", false);
		  }
	  });
	  $('#solutionL3AddServicesDTO\\.scopePhysicalDatabase').change(function(){
		  
		  var obj1 =  $('#solutionL3AddServicesDTO\\.scopePhysicalDatabase');
		  var obj2 =  $('#solutionL3AddServicesDTO\\.valuePhysicalDatabase');
		  var obj3 =  $('#solutionL3AddServicesDTO\\.ftephysicalDatabase');
		  var obj4 =  $('#solutionL3AddServicesDTO\\.unitPhysicalDatabase');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true);obj2.val(''); 
			  obj4.attr("disabled", true);obj3.val(''); 
			  
		  }else{
			  obj2.attr("disabled", false);
			  obj4.attr("disabled", false);
		  }
	  });
	  $('#solutionL3AddServicesDTO\\.scopeServerAndVirt').change(function(){
		  
		  var obj1 =  $('#solutionL3AddServicesDTO\\.scopeServerAndVirt');
		  var obj2 =  $('#solutionL3AddServicesDTO\\.valueServerAndVirt');
		  var obj3 =  $('#solutionL3AddServicesDTO\\.fteserverAndVirt');
		  var obj4 =  $('#solutionL3AddServicesDTO\\.unitServerAndVirt');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true);obj2.val(''); 
			  obj4.attr("disabled", true);obj3.val(''); 
			  
		  }else{
			  obj2.attr("disabled", false);
			  obj4.attr("disabled", false);
		  }
	  });
	  
	  $('#solutionL3AddServicesDTO\\.scopeOtherInfra').change(function(){
		  
		  var obj1 =  $('#solutionL3AddServicesDTO\\.scopeOtherInfra');
		  var obj2 =  $('#solutionL3AddServicesDTO\\.valueOtherInfra');
		  var obj3 =  $('#solutionL3AddServicesDTO\\.fteotherInfra');
		  var obj4 =  $('#solutionL3AddServicesDTO\\.unitOtherInfra');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true);obj2.val(''); 
			  obj4.attr("disabled", true);obj3.val(''); 
			  
		  }else{
			  obj2.attr("disabled", false);
			  obj4.attr("disabled", false);
		  }
		});
	  
	
	 
	   
});



</script>


<form:form id="Level3SupportForm" action="./saveSolutionL3Operations" method="post" modelAttribute="solutionL3OperationsForm">

	
	<form:hidden path="solutionL3OperationsDTO.solutionL3operationsId" /> 
	<form:hidden path="solutionL3AddServicesDTO.solutionL3addServicesId" /> 
	<form:hidden path="solutionDimentionAttId" /> 
	<form:hidden path="opportunityScopeId"/> 
	
	<table width="100%">
				<tr>
				<td class="tdLabelLeft">Window Of Operation</td>
				<td class="tdLabelLeft">
					<form:select path="solutionL3OperationsDTO.supportWindowMatrixDTO.supportWindowMatrixId" class="listBoxSmall" >
					<c:forEach var="item" items="${supportWindowList}">
					<c:set var="derivedFactor" value="${derivedFactor};${item.derivedFactor}" />
						<form:option  value="${item.supportWindowMatrixId}"> ${item.supportWindow}</form:option>
				</c:forEach>
				</form:select>
				<input type="hidden" id="derivedFactorL3"  value="${derivedFactor}" />
				</td>
			
			 <td width="16%" class="tdInputBox">
	          <input type="checkbox" name="defaultL3" value="" >
	          <b>Default</b></td>
	        <td width="25%">&nbsp;</td>
	    
	      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="3" align="left" valign="top" class="tdInputBox"></td><td width="0%"></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Percentage of L2 Tickets converting into L3 Bug Fix</td>
        <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.pcofL2incidentsConvL3" class="textBoxSmallNumric" maxlength="3"  onblur="calTotalL3Incident();" onkeypress="return isDecimal(event);" /></td>
        <td colspan="3" rowspan="7" align="left" valign="top" class="tdInputBox"><table width="560" cellspacing="3" style="background-color:#fbfbfb; border:1px solid #ededed;">
          <tr>
            <td width="33%" align="center"><b>Simple</b></td>
            <td width="34%" align="center"><b>Medium</b></td>
            <td width="33%" align="center"><b>Complex</b></td>
          </tr>
          <tr>
            <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.pcsimpleIncidents" class="textBoxSmallNumric"  maxlength="3"  onkeypress="return isDecimal(event);" onblur="changeIncidentPCL3()"/>%</td>
            <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.pcmediumIncidents" class="textBoxSmallNumric"  maxlength="3"  onkeypress="return isDecimal(event);" onblur="changeIncidentPCL3()" />%</td>
            <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.pccomplexIncidents" class="textBoxCalculateResults"  readonly="true"/>%</td>
          </tr>
          
           <tr>
            <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.hrsSimpleIncidents" class="textBoxSmallNumric" maxlength="3"  onkeypress="return isDecimal(event);" /></td>
            <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.hrsMediumIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);" /></td>
            <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.hrsComplexIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);"/></td>
        
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
        <td class="tdLabelLeft">Total Volume of Bug Fixes at Level 3 Per Year</td>
     		<td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.totalL3bugFixesPerYear" class="textBoxSmallNumric" maxlength="6" onkeypress="return isDecimal(event);"/></td>  
      </tr>
      <tr>
        <td class="tdLabelLeft">Avg. resolution time Bug Fix (in Hr.)</td>
          <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.avgResolutionTimeHrs" class="textBoxCalculateResults"  readonly="true" /></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Annual Hours Spent in Bug Fixing at Level 3</td>
          <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.anualHrsSpent" class="textBoxCalculateResults"  readonly="true" /></td>
      </tr>
      <%--   <tr>
        <td class="tdLabelLeft">Utilization Per Year</td>
          <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.utilizationperYr" class="textBoxSmallNumric" maxlength="4"  /></td>
      </tr> --%>
      <tr>
        <td class="tdLabelLeft">Base L3 FTE</td>
          <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.baseL3operationFte" class="textBoxCalculateResults"  readonly="true" /></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Augmented Heads for <b> <span id="windowL3Div1" > </span></b></td>
         <td class="tdLabelLeft"><form:input path="solutionL3OperationsDTO.augmentedL3operationFte" class="textBoxCalculateResults"  readonly="true" /></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td> 
        <c:if test="${hasEditSolAccess!='false'}">
        	<a id="calculateL3" class="TabsCommonButtons" >Calculate</a>
        </c:if>
        </td>
       
      </tr>
    </table>
</div>

<div>
			
    <table width="984" cellspacing="6" style="background-color:#fbfbfb; border:1px solid #ededed; margin-top:20px;" >
      <tr>
        <td width="42%" height="30" align="left" class="tdLabelLeft"><b>Additional Level 3 Services</b></td>
      	<td width="18%" align="center" class="tdLabelLeft"><b>In Scope</b></td>
        <td width="16%" align="center" class="tdLabelLeft"><b>Units</b></td>
        <td width="17%" align="center" class="tdInputBox"><b>Value</b></td>
        <td width="19%" align="center" class="tdInputBox"><b>FTE</b></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Integration Testing Support</td>
        <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.scopeIntegTest" class="listBoxSmall" >
          <form:options items="${scopeList}" />
        </form:select></td>
         <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.unitIntegTest" class="listBoxSmall" >
           <form:options items="${unitL3List}" />
        </form:select></td>
        <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.valueIntegTest" class="textBoxSmallNumric" maxlength="5"  onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.fteintegTest" class="textBoxCalculateResults" readonly="true" /></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Regression Testing support</td>
           <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.scopeRegreTest" class="listBoxSmall" >
          <form:options items="${scopeList}" />
        </form:select></td>
         <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.unitRegreTest" class="listBoxSmall" >
           <form:options items="${unitL3List}" />
        </form:select></td>
          <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.valueRegreTest" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.fteregreTest" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Other Testing Support</td>
            <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.scopeOtherTest" class="listBoxSmall" >
        	 <form:options items="${scopeList}" />
       </form:select></td>
        <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.unitOtherTest" class="listBoxSmall" >
           <form:options items="${unitL3List}" />
        </form:select></td>
         <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.valueOtherTest" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.fteotherTest" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Other Miscellaneous Support</td>
          <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.scopeMisceSupp" class="listBoxSmall" >
      	   <form:options items="${scopeList}" />
        </form:select></td>
         <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.unitMisceSupp" class="listBoxSmall" >
           <form:options items="${unitL3List}" />
        </form:select></td>
        <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.valueMisceSupp" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.ftemisceSupp" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Physical DataBase Support</td>
           <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.scopePhysicalDatabase" class="listBoxSmall" >
       		  <form:options items="${scopeList}" />
        </form:select></td>
         <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.unitPhysicalDatabase" class="listBoxSmall" >
           <form:options items="${unitL3List}" />
        </form:select></td>
       <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.valuePhysicalDatabase" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.ftephysicalDatabase" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Server &amp; Virtualization Support</td>
           <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.scopeServerAndVirt" class="listBoxSmall" >
      	   <form:options items="${scopeList}" />
        </form:select></td>
         <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.unitServerAndVirt" class="listBoxSmall" >
           <form:options items="${unitL3List}" />
        </form:select></td>
       <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.valueServerAndVirt" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.fteserverAndVirt" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Other Infrastructure Support</td>
            <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.scopeOtherInfra" class="listBoxSmall" >
        	 <form:options items="${scopeList}" />
        </form:select></td>
         <td class="tdLabelLeft"><form:select path="solutionL3AddServicesDTO.unitOtherInfra" class="listBoxSmall" >
           <form:options items="${unitL3List}" />
        </form:select></td>
        <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.valueOtherInfra" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.fteotherInfra" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
    
      
      <tr>
        <td class="tdLabelLeft">Additional Base L3 FTE</td>
          <td class="tdLabelLeft"></td>
    	  <td class="tdInputBox"></td>
    	   <td class="tdInputBox"></td>
          <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.baseL3serviceFte" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Augmented Additional Heads for <b> <span id="windowL3Div2" > </span></b></td>
          <td class="tdLabelLeft"></td>
      	  <td class="tdInputBox"></td>
      	   <td class="tdInputBox"></td>
          <td class="tdInputBox"><form:input path="solutionL3AddServicesDTO.augmentedL3serviceFte" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
    </table>
    	 <c:if test="${hasEditSolAccess!='false'}">
	    <div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 5x;">
		  <table width="100%" border="0" style="padding-right:5%" >
		    <tr>
		      <td class="tdButtonRow">
		      	<a id="resetL3" class="TabsCommonButtons" >Reset</a>
		      	<a id="saveL3" class="TabsCommonButtons" >Save</a>
		      	<a id="calculateL3Add" class="TabsCommonButtons" >Calculate</a>
		      </td>
		    </tr>
		  </table>
		</div>
		</c:if>
<p></p>
</form:form>