<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
var derivedFactorL1=0.0;
var isValid=true;
function changeIncidentL1PC(){
	
	var simple = document.getElementById("solutionL1OperationsDTO.pcsimpleIncidents").value;
	var medium = document.getElementById("solutionL1OperationsDTO.pcmediumIncidents").value;
	var complex = document.getElementById("solutionL1OperationsDTO.pccomplexIncidents");
	
	if(!isNaN(parseInt(simple)) && !isNaN(parseInt(medium))){
		if(parseInt(simple) +parseInt(medium) > 100){
			alert("Sum of the percentage can't be greater than 100");
			document.getElementById("solutionL1OperationsDTO.pcsimpleIncidents").focus();
			return false;
		}
		complex.value = (100 - (parseInt(simple)+parseInt(medium)));
	}
	//alert(complex.value);
}

function handleWindowChangeL1(){
	var strObj = "solutionL1OperationsDTO\\.supportWindowMatrixDTO\\.supportWindowMatrixId";
 	var index = $("#"+strObj).prop("selectedIndex");
	var derivedFacStr = $("#derivedFactorL1").val();
	derivedFacStr = derivedFacStr.substr(1,derivedFacStr.length);
	var arr = derivedFacStr.split(';');
	derivedFactorL1 = arr[index];
 
	document.getElementById('windowL1Div1').innerHTML = $("#"+strObj+" option:selected").text();
	document.getElementById('windowL1Div2').innerHTML = $("#"+strObj+" option:selected").text();
  
}

function calTotalL1Incident(){
	var transPerYearObj = document.getElementById("ServiceDeskDTOList[3].totalTransPerYear"); 
	var obj = $('#solutionL1OperationsDTO\\.pcofL1incidents');
	 if(transPerYearObj != null){
		var sdTransPerYear =  transPerYearObj.value;
		
		if(!isNaN(parseInt(sdTransPerYear)) &&  !isNaN(parseInt(obj.val()))){
			
			var transL1PerYear = sdTransPerYear*obj.val()/100;
			$('#solutionL1OperationsDTO\\.totalL1incidentsperYr').val(parseFloat(transL1PerYear).toFixed(2));
		}
	 }
}
$(document).ready (function () {
		handleWindowChangeL1();
		//var utilizationperYr =2040;
		
		if(document.getElementById("ServiceDeskDTOList[3].totalTransPerYear") == null){
			$('#solutionL1OperationsDTO\\.pcofL1incidents').attr("disabled", true);
		}
		$('#saveL1').click(function() {
		if($('#dimensionAttributeId') != null){
			$('#Level1SupportForm input[name="solutionDimentionAttId"]').val($('#dimensionAttributeId').val()); 
		}
		 $('#calculateL1').click();
		 $('#calculateL1Add').click();
	     if(isValid){
		 	$('#Level1SupportForm').submit();
	     }
	    });
		
		$("#Level1SupportForm").submit(function() {
			showProgress();
			var options = {
					success: function(html) {
					
						 $("#operationL1Div").html(html);
						 $("#messageDiv").addClass("message");
						 $("#messageDiv").html("Your data has been saved successfully.");
						//$("#sdDiv").replaceWith($('#sdDiv', $(html)));
						 refreshServiceBucket();
					},
					error: function(res) {
						 $("#messageDiv").html("There is a error while saving data. Please contact administrator");
					},
					url: "./saveSolutionL1Operations"
					
				};
			
				$(this).ajaxSubmit(options);
				hideProgress();
				return false;
		});
	  
		 $('#resetL1').click(function() {
			  	$('#Level1SupportForm').each (function(){
	    		  this.reset();
	    		});
		    });
		 
	  $('#solutionL1OperationsDTO\\.supportWindowMatrixDTO\\.supportWindowMatrixId').change(function(){
		 	handleWindowChangeL1();
	  });
	  
		
	  $('#Level1SupportForm input[name="defaultL1"]').change(function(e){	
		  if (!$(this).is(':checked')) {
			  $('#Level1SupportForm').each (function(){
	    		  this.reset();
	    	});
		  }else{
		 $.ajax({
				type : "POST",
				url : "./loadDefaultValuesForServiceScopes",
				data : {"serviceId": '2'},
				datatype : "text",
				success : function(result){
				
					var data = eval('('+result+')');
					
					$('#solutionL1OperationsDTO\\.pcofL1incidents').val(data.pcofL1incidents);
					
					$('#solutionL1OperationsDTO\\.pcsimpleIncidents').val(data.pcsimpleIncidents);
					$('#solutionL1OperationsDTO\\.pcmediumIncidents').val(data.pcmediumIncidents);
					$('#solutionL1OperationsDTO\\.pccomplexIncidents').val(data.pccomplexIncidents);
					
					$('#solutionL1OperationsDTO\\.hrsSimpleIncidents').val(data.hrsSimpleIncidents);
					$('#solutionL1OperationsDTO\\.hrsMediumIncidents').val(data.hrsMediumIncidents);
					$('#solutionL1OperationsDTO\\.hrsComplexIncidents').val(data.hrsComplexIncidents);
					//$('#solutionL1OperationsDTO\\.utilizationperYr').val(data.utilizationperYr);
					calTotalL1Incident();
				},
				error : function(e) {
					alert("Operation failed.");
				}
			});	
		  }
		 
	 });		 
	  
	  $('#calculateL1').click(function(){
		    isValid=true;
		  	var simple =  $('#solutionL1OperationsDTO\\.pcsimpleIncidents').val();
			var medium =  $('#solutionL1OperationsDTO\\.pcmediumIncidents').val();
			var complex =  $('#solutionL1OperationsDTO\\.pccomplexIncidents').val();
			
			var simplehrs =  $('#solutionL1OperationsDTO\\.hrsSimpleIncidents').val();
			var mediumhrs =  $('#solutionL1OperationsDTO\\.hrsMediumIncidents').val();
			var complexhrs =  $('#solutionL1OperationsDTO\\.hrsComplexIncidents').val();
			
			//$('#solutionL1OperationsDTO\\.utilizationperYr').val(utilizationperYr);
			
			if(document.getElementById("ServiceDeskDTOList[3].totalTransPerYear") != null){
				calTotalL1Incident();
			}
			
			var avgResolutionTimeHrsObj  = $('#solutionL1OperationsDTO\\.avgResolutionTimeHrs');
			var anualHrsSpentobj  = $('#solutionL1OperationsDTO\\.anualHrsSpent');
			var totalL1incidentsperYrObj  = $('#solutionL1OperationsDTO\\.totalL1incidentsperYr');
			//var utilizationperYr  =  $('#solutionL1OperationsDTO\\.utilizationperYr').val();
			var baseL1operationFteObj  = $('#solutionL1OperationsDTO\\.baseL1operationFte');
			var augmentedL1operationFteObj  = $('#solutionL1OperationsDTO\\.augmentedL1operationFte');
			var totalL1incidentsperYr = totalL1incidentsperYrObj.val();
			
		
			if(isNaN(parseInt(totalL1incidentsperYr))){
				alert("Please enter value for total no of incidents Level 1 ");
				isValid=false;
				return false;
			}
			
			if(isNaN(parseInt(simple)) || isNaN(parseInt(medium)) || isNaN(parseInt(complex)) || 
					isNaN(parseInt(simplehrs)) || isNaN(parseInt(mediumhrs)) || isNaN(parseInt(complexhrs)) ){
				alert("Please enter numeric value for Simple, Medium and Complex ");
				isValid=false;
				return false;
			}
			
			var aveResoluTime = (simple/100)*simplehrs + (medium/100)*mediumhrs +(complex/100)*complexhrs ;
			avgResolutionTimeHrsObj.val(parseFloat(aveResoluTime).toFixed(2));
			
			var anualHrsSpent = aveResoluTime * parseInt(totalL1incidentsperYr);
			anualHrsSpentobj.val(parseFloat(anualHrsSpent).toFixed(2));
			//calculating base FTE
			var baseL1operationFte = anualHrsSpent/utilizationPerYear;
			baseL1operationFteObj.val(parseFloat(baseL1operationFte).toFixed(2));
			//calculating total FTE
			//alert(derivedFactorL1);
			var augmentedL1operationFte = baseL1operationFte * derivedFactorL1;
			augmentedL1operationFteObj.val(parseFloat(augmentedL1operationFte).toFixed(2));
		  
	  });
	  
	  
	  $('#calculateL1Add').click(function(){
		  
		  //var utilizationperYr  =  $('#solutionL1OperationsDTO\\.utilizationperYr').val();
		  var addBaseFTE=0.0;
		  var fte;
		  
		  var scope =  $('#solutionL1AddServicesDTO\\.scopeAccessMgmt');
		  var effort =  $('#solutionL1AddServicesDTO\\.effortAccessMgmt');
		  var fteobj =  $('#solutionL1AddServicesDTO\\.fteaccessMgmt');
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
	
			  addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL1AddServicesDTO\\.scopeRequestMgmt');
		  effort =  $('#solutionL1AddServicesDTO\\.effortRequestMgmt');
		  fteobj =  $('#solutionL1AddServicesDTO\\.fterequestMgmt');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL1AddServicesDTO\\.scopeEventMgmt');
		  effort =  $('#solutionL1AddServicesDTO\\.effortEventMgmt');
		  fteobj =  $('#solutionL1AddServicesDTO\\.fteeventMgmt');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL1AddServicesDTO\\.scopeL1billOperation');
		  effort =  $('#solutionL1AddServicesDTO\\.effortL1billOperation');
		  fteobj =  $('#solutionL1AddServicesDTO\\.ftel1billOperation');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
				 
		  scope =  $('#solutionL1AddServicesDTO\\.scopeL1preventive');
		  effort =  $('#solutionL1AddServicesDTO\\.effortL1preventive');
		  fteobj =  $('#solutionL1AddServicesDTO\\.ftel1preventive');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  
		  scope =  $('#solutionL1AddServicesDTO\\.scopeL1change');
		  effort =  $('#solutionL1AddServicesDTO\\.effortL1change');
		  fteobj =  $('#solutionL1AddServicesDTO\\.ftel1change');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  scope =  $('#solutionL1AddServicesDTO\\.scopeL1routine');
		  effort =  $('#solutionL1AddServicesDTO\\.effortL1routine');
		  fteobj =  $('#solutionL1AddServicesDTO\\.ftel1routine');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		  scope =  $('#solutionL1AddServicesDTO\\.scopeL1performance');
		  effort =  $('#solutionL1AddServicesDTO\\.effortL1performance');
		  fteobj =  $('#solutionL1AddServicesDTO\\.ftel1performance');
		 
		  if(scope.val() == 'Yes' && !isNaN(parseInt(effort.val()))){
			  fte = parseInt(effort.val())*12/utilizationPerYear;
			  fte = parseFloat(fte).toFixed(2);
			  fteobj.val(fte);
			    addBaseFTE = parseFloat(addBaseFTE) + parseFloat(fte);
		  }else{
			  fteobj.val('');
		  }
		
		  var augmentedL1serviceFte = parseFloat(addBaseFTE) * parseFloat(derivedFactorL1);
		
		  $('#solutionL1AddServicesDTO\\.baseL1serviceFte').val(parseFloat(addBaseFTE).toFixed(2));
		
		  $('#solutionL1AddServicesDTO\\.augmentedL1serviceFte').val(parseFloat(augmentedL1serviceFte).toFixed(2));
		  
	  });
	  
	  //end of calculation for additional FTE
	
	  $('#solutionL1AddServicesDTO\\.scopeAccessMgmt').change(function(){
		  var obj1 =  $('#solutionL1AddServicesDTO\\.scopeAccessMgmt');
		  var obj2 =  $('#solutionL1AddServicesDTO\\.effortAccessMgmt');
		  var obj3 =  $('#solutionL1AddServicesDTO\\.fteaccessMgmt');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	  
	  $('#solutionL1AddServicesDTO\\.scopeRequestMgmt').change(function(){
		  
		  var obj1 =  $('#solutionL1AddServicesDTO\\.scopeRequestMgmt');
		  var obj2 =  $('#solutionL1AddServicesDTO\\.effortRequestMgmt');
		  var obj3 =  $('#solutionL1AddServicesDTO\\.fterequestMgmt');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	  $('#solutionL1AddServicesDTO\\.scopeEventMgmt').change(function(){
		  
		  var obj1 =  $('#solutionL1AddServicesDTO\\.scopeEventMgmt');
		  var obj2 =  $('#solutionL1AddServicesDTO\\.effortEventMgmt');
		  var obj3 =  $('#solutionL1AddServicesDTO\\.fteeventMgmt');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
		 
	  });
	  $('#solutionL1AddServicesDTO\\.scopeL1billOperation').change(function(){
		  
		  var obj1 =  $('#solutionL1AddServicesDTO\\.scopeL1billOperation');
		  var obj2 =  $('#solutionL1AddServicesDTO\\.effortL1billOperation');
		  var obj3 =  $('#solutionL1AddServicesDTO\\.ftel1billOperation');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	  $('#solutionL1AddServicesDTO\\.scopeL1preventive').change(function(){
		  
		  var obj1 =  $('#solutionL1AddServicesDTO\\.scopeL1preventive');
		  var obj2 =  $('#solutionL1AddServicesDTO\\.effortL1preventive');
		  var obj3 =  $('#solutionL1AddServicesDTO\\.ftel1preventive');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	  $('#solutionL1AddServicesDTO\\.scopeL1change').change(function(){
		  
		  var obj1 =  $('#solutionL1AddServicesDTO\\.scopeL1change');
		  var obj2 =  $('#solutionL1AddServicesDTO\\.effortL1change');
		  var obj3 =  $('#solutionL1AddServicesDTO\\.ftel1change');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
	  });
	  
	  $('#solutionL1AddServicesDTO\\.scopeL1routine').change(function(){
		  
		  var obj1 =  $('#solutionL1AddServicesDTO\\.scopeL1routine');
		  var obj2 =  $('#solutionL1AddServicesDTO\\.effortL1routine');
		  var obj3 =  $('#solutionL1AddServicesDTO\\.ftel1routine');
		  if(obj1.val() == 'No'){
			  obj2.attr("disabled", true); obj2.val('');
			  obj3.attr("disabled", true); obj3.val('');
		  }else{
			  obj2.attr("disabled", false);
			  obj3.attr("disabled", false);
		  }
		});
	  
	  $('#solutionL1AddServicesDTO\\.scopeL1performance').change(function(){
		  
		  var obj1 =  $('#solutionL1AddServicesDTO\\.scopeL1performance');
		  var obj2 =  $('#solutionL1AddServicesDTO\\.effortL1performance');
		  var obj3 =  $('#solutionL1AddServicesDTO\\.ftel1performance');
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


<form:form id="Level1SupportForm" action="./saveSolutionL1Operations" method="post" modelAttribute="solutionL1OperationsForm">

	<form:hidden path="solutionL1OperationsDTO.solutionL1operationsId"/>
	<form:hidden path="solutionL1AddServicesDTO.solutionL1addServicesId"/>
	<form:hidden path="solutionDimentionAttId" /> 
	<form:hidden path="opportunityScopeId"/> 

	<%-- 			<input type="hidden" name="solutionL1OperationsDTO.solutionL1operationsId"  value="${solutionL1OperationsForm.solutionL1OperationsDTO.solutionL1operationsId}" />
				<input type="hidden" name="solutionL3AddServicesDTO.solutionL3addServicesId"  value="${solutionL3OperationsForm.solutionL3AddServicesDTO.solutionL3addServicesId}" />
	 --%>	
	<table width="100%">
				<tr>
				<td class="tdLabelLeft">Window Of Operation</td>
				<td class="tdLabelLeft">
					<form:select path="solutionL1OperationsDTO.supportWindowMatrixDTO.supportWindowMatrixId" class="listBoxSmall" >
					<c:forEach var="item" items="${supportWindowList}">
					<c:set var="derivedFactor" value="${derivedFactor};${item.derivedFactor}" />
						<form:option  value="${item.supportWindowMatrixId}"> ${item.supportWindow}</form:option>
						
					</c:forEach>
				</form:select>
				<input type="hidden" id="derivedFactorL1"  value="${derivedFactor}" />
				</td>
			
			 
	        <td width="18%" class="tdInputBox">
	          <input type="checkbox" name="defaultL1" value="" >
	          <b>Default</b></td>
	        <td width="20%">&nbsp;</td>
	    
	      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="3" align="left" valign="top" class="tdInputBox"></td><td width="0%"></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Percentage of Transactions converting into L1 Incidents</td>
        <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.pcofL1incidents" class="textBoxSmallNumric" maxlength="3" onblur="calTotalL1Incident();" onkeypress="return isDecimal(event);" /></td>
        <td colspan="3" rowspan="7" align="left" valign="top" class="tdInputBox"><table width="560" cellspacing="3" style="background-color:#fbfbfb; border:1px solid #ededed;">
          <tr>
            <td width="33%" align="center"><b>Simple</b></td>
            <td width="34%" align="center"><b>Medium</b></td>
            <td width="33%" align="center"><b>Complex</b></td>
          </tr>
       
          <tr>
            <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.pcsimpleIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);" onblur="changeIncidentL1PC()"/>%</td>
            <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.pcmediumIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);" onblur="changeIncidentL1PC()" />%</td>
            <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.pccomplexIncidents" class="textBoxCalculateResults"  onkeypress="return isDecimal(event);" readonly="true"/>%</td>
          </tr>
          
           <tr>
            <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.hrsSimpleIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);" /></td>
            <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.hrsMediumIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);" /></td>
            <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.hrsComplexIncidents" class="textBoxSmallNumric"  maxlength="3" onkeypress="return isDecimal(event);"/></td>
        
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
        <td class="tdLabelLeft">Total Volume of Incidents at Level 1 Per Year</td>
     		<td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.totalL1incidentsperYr" class="textBoxSmallNumric" maxlength="6" onkeypress="return isDecimal(event);"/></td>  
      </tr>
      <tr>
        <td class="tdLabelLeft">Avg. resolution time per incident (in Hr.)</td>
          <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.avgResolutionTimeHrs" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Annual Hours Spent in Solving Incidents at Level 1</td>
          <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.anualHrsSpent" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <%--   <tr>
        <td class="tdLabelLeft">Utilization Per Year</td>
          <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.utilizationperYr" class="textBoxSmallNumric" maxlength="4" onkeypress="return isDecimal(event);" /></td>
      </tr> --%>
      <tr>
        <td class="tdLabelLeft">Base L1 FTE</td>
          <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.baseL1operationFte" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Augmented Heads for <b> <span id="windowL1Div1" > </span></b></td>
         <td class="tdLabelLeft"><form:input path="solutionL1OperationsDTO.augmentedL1operationFte" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
       	<c:if test="${hasEditSolAccess!='false'}">
       	 <td><a id="calculateL1" class="TabsCommonButtons" >Calculate</a></td>
       	 </c:if>
      </tr>
    </table>
</div>

<div>

    <table width="984" cellspacing="6" style="background-color:#fbfbfb; border:1px solid #ededed; margin-top:20px;" >
      <tr>
        <td width="42%" height="30" align="left" class="tdLabelLeft"><b>Additional Level 1 Services</b></td>
        <td width="19%" align="center" class="tdLabelLeft"><b>In Scope</b></td>
        <td width="19%" align="center" class="tdInputBox"><b>Monthly Effort (Hours)</b></td>
        <td width="20%" align="center" class="tdInputBox"><b>FTE</b></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Access Management</td>
        <td class="tdLabelLeft"><form:select path="solutionL1AddServicesDTO.scopeAccessMgmt" class="listBoxSmall" >
          <form:options items="${scopeList}" />
        </form:select></td>
        <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.effortAccessMgmt" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.fteaccessMgmt" class="textBoxCalculateResults" readonly="true" /></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Svc Request Management</td>
           <td class="tdLabelLeft"><form:select path="solutionL1AddServicesDTO.scopeRequestMgmt" class="listBoxSmall"  >
           <form:options items="${scopeList}" />
        </form:select></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.effortRequestMgmt" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.fterequestMgmt" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Event Management</td>
            <td class="tdLabelLeft"><form:select path="solutionL1AddServicesDTO.scopeEventMgmt" class="listBoxSmall" >
           <form:options items="${scopeList}" />
       </form:select></td>
         <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.effortEventMgmt" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.fteeventMgmt" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L1 Billing Operations</td>
            <td class="tdLabelLeft"><form:select path="solutionL1AddServicesDTO.scopeL1billOperation" class="listBoxSmall" >
           <form:options items="${scopeList}" />
        </form:select></td>
        <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.effortL1billOperation" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"/></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.ftel1billOperation" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L1 Preventive Maintenance</td>
           <td class="tdLabelLeft"><form:select path="solutionL1AddServicesDTO.scopeL1preventive" class="listBoxSmall" >
            <form:options items="${scopeList}" />
        </form:select></td>
       <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.effortL1preventive" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);" /></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.ftel1preventive" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L1 Change Execution</td>
           <td class="tdLabelLeft"><form:select path="solutionL1AddServicesDTO.scopeL1change" class="listBoxSmall" >
           <form:options items="${scopeList}" />
        </form:select></td>
       <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.effortL1change" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);" /></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.ftel1change" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L1 Routine Operations</td>
            <td class="tdLabelLeft"><form:select path="solutionL1AddServicesDTO.scopeL1routine" class="listBoxSmall" >
           <form:options items="${scopeList}" />
        </form:select></td>
        <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.effortL1routine" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);" /></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.ftel1routine" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">L1 Performance Reporting</td>
             <td class="tdLabelLeft"><form:select path="solutionL1AddServicesDTO.scopeL1performance" class="listBoxSmall" >
         <form:options items="${scopeList}" />
        </form:select></td>
       <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.effortL1performance" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);" /></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.ftel1performance" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Additional Base L1 FTE</td>
          <td class="tdLabelLeft"></td>
    	  <td class="tdInputBox"></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.baseL1serviceFte" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
      <tr>
        <td class="tdLabelLeft">Augmented Additional Heads for <b> <span id="windowL1Div2" > </span></b></td>
          <td class="tdLabelLeft"></td>
      	  <td class="tdInputBox"></td>
          <td class="tdInputBox"><form:input path="solutionL1AddServicesDTO.augmentedL1serviceFte" class="textBoxCalculateResults" readonly="true"/></td>
      </tr>
    </table>
     <c:if test="${hasEditSolAccess!='false'}">
	    <div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 5x;">
		  <table width="100%" border="0" style="padding-right:5%" >
		    <tr>
		      <td class="tdButtonRow">
		      	<a id="resetL1" class="TabsCommonButtons" >Reset</a>
		      	<a id="saveL1" class="TabsCommonButtons" >Save</a>
		      	<a id="calculateL1Add" class="TabsCommonButtons" >Calculate</a>
		      </td>
		    </tr>
		  </table>
	</div>
 </c:if>

</form:form>