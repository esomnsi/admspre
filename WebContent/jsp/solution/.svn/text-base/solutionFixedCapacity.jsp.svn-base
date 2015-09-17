<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>

function calculateFTEHrsPerMonth(){
	var unit = $('#solutionEnhanDevFixedCapacityDTO\\.customerBuyingUnit').val();
	var value = $('#solutionEnhanDevFixedCapacityDTO\\.customerBuying').val();
	var aveFteObj = $('#solutionEnhanDevFixedCapacityDTO\\.averageFtehrs');
	var aveFTE=0.0;
	if(unit == 1 && !isNaN(parseInt(value))){
		aveFTE = parseInt(value)/170;
	}
	aveFteObj.val(parseInt(aveFTE).toFixed(2));
}


function changePCofCR(){
	
	var simple = $('#solutionEnhanDevFixedCapacityDTO\\.pcqaunSimpleCr').val();
	var medium = $('#solutionEnhanDevFixedCapacityDTO\\.pcqaunMediumCr').val();
	var complexObj = $('#solutionEnhanDevFixedCapacityDTO\\.pcqaunComplexCr');

	if(!isNaN(parseInt(simple)) && !isNaN(parseInt(medium))){
		var simMed = parseInt(simple) + parseInt(medium);
		if(parseInt(simMed)>100) {
			alert("Percentage can't be greater than 100");
			$('#solutionEnhanDevFixedCapacityDTO\\.pcqaunMediumCr').focus();
			return false;
		}
		var complex = (100 - (parseInt(simple)+parseInt(medium)));
		complexObj.val(complex);
	}
	
}

function changeBuyingUnit(){
	
	var unit = $('#solutionEnhanDevFixedCapacityDTO\\.customerBuyingUnit').val();
	
	 if(unit == 1){
 	   $('#hrsDiv').show();
 	   $('#crDiv').hide();
	 }else{
		 $('#hrsDiv').hide();
	  	 $('#crDiv').show();
	 }
}
$(document).ready (function () { 
	changeBuyingUnit();
	
	$('#solutionEnhanDevFixedCapacityDTO\\.customerBuyingUnit').change(function() {
		changeBuyingUnit();
	});

	
	
	$('#saveFC').click(function() {
		 if($('#dimensionAttributeId') != null){
				$('#enhancementAndDevFormFC input[name="solutionDimentionAttId"]').val($('#dimensionAttributeId').val()); 
			}
	
		$('#enhancementAndDevFormFC').submit();
	});
	
	$("#enhancementAndDevFormFC").submit(function() {
		showProgress();
		var options = {
				success: function(html) {
					 $("#FixedCapacityPanel").html(html);
					 $("#messageDiv").addClass("message");
					 $("#messageDiv").html("Your data has been saved successfully.");
					 refreshServiceBucket();
				},
				url: "./saveSolutionEnhanDevFC"
				
			};
		
			$(this).ajaxSubmit(options);
		
			hideProgress();
			
			return false;
	});
	$('#resetFC').click(function() {
		 $('#enhancementAndDevFormFC').each (function(){
  		  this.reset();
  		});
	});
	
	 

	 $('#enhancementAndDevFormFC input[name="defaultFC"]').change(function(e){	
		  if (!$(this).is(':checked')) {
			 /*  $('#enhancementAndDevForm').each (function(){
	    		  this.reset(); 
	    	});*/
		  }else{
			$.ajax({
		 
			type : "POST",
			url : "./loadDefaultValuesForServiceScopes",
			data : {"serviceId": '5'},
			datatype : "text",
			success : function(result){
				var data = eval('('+result+')');
				 $('#solutionEnhanDevFixedCapacityDTO\\.pcqaunSimpleCr').val(data.pcqaunSimpleCr);
		 		 $('#solutionEnhanDevFixedCapacityDTO\\.pcqaunMediumCr').val(data.pcqaunMediumCr);
		 		 $('#solutionEnhanDevFixedCapacityDTO\\.pcqaunComplexCr').val(data.pcqaunComplexCr);
		 		
		 		 $('#solutionEnhanDevFixedCapacityDTO\\.effortSimpleCr').val(data.effortSimpleCr);
		 	   	 $('#solutionEnhanDevFixedCapacityDTO\\.effortMediumCr').val(data.effortMediumCr);
		 	   	 $('#solutionEnhanDevFixedCapacityDTO\\.effortComplexCr').val(data.effortComplexCr);
			}
		 });
		  }
		
	});
	
	$('#calculateFC').click(function() {
		
		var unit = $('#solutionEnhanDevFixedCapacityDTO\\.customerBuyingUnit').val();
		if(unit == 1){
			calculateFTEHrsPerMonth();
		}else{
		
		var utilizationPerMonth = 	$('#utilizationPerMonth').val();
		var utilizationPerWeek = $('#utilizationPerWeek').val();
		var utilizationPerDay = 	$('#utilizationPerDay').val();		
		
		var buying = $('#solutionEnhanDevFixedCapacityDTO\\.customerBuying').val();
		
		 var durition = 	$('#solutionEnhanDevFixedCapacityDTO\\.durition').val();
		 
		
		var simple = $('#solutionEnhanDevFixedCapacityDTO\\.pcqaunSimpleCr').val();
		var medium = $('#solutionEnhanDevFixedCapacityDTO\\.pcqaunMediumCr').val();
		var complex = $('#solutionEnhanDevFixedCapacityDTO\\.pcqaunComplexCr').val();
		
		var qaunSimpleCrObj = $('#solutionEnhanDevFixedCapacityDTO\\.qaunSimpleCr');
		var qaunMediumCrObj = $('#solutionEnhanDevFixedCapacityDTO\\.qaunMediumCr');
		var qaunComplexCrObj = $('#solutionEnhanDevFixedCapacityDTO\\.qaunComplexCr');
		if(isNaN(parseInt(buying))){
			alert("Please enter numric value for Customer Buying");
			return false;
		}
		
		if(isNaN(parseInt(simple)) || isNaN(parseInt(medium))){
			alert("Please enter numric value for CRs percentage");
			return false;
		}
		
		 if(isNaN(parseInt(durition)) ){
 			alert("Please enter numric value for durition");
 			return false; 
 		 }
			var quanSimple = parseInt(simple)*parseInt(buying)/100;
			var quanMedium = parseInt(medium)*parseInt(buying)/100;
			var quanComplex = parseInt(buying) - (parseInt(quanMedium)+parseInt(quanSimple));
				
			qaunSimpleCrObj.val(parseInt(quanSimple));
			qaunMediumCrObj.val(parseInt(quanMedium));
			qaunComplexCrObj.val(parseInt(quanComplex));
			
			var effortSimpleCr = $('#solutionEnhanDevFixedCapacityDTO\\.effortSimpleCr').val();
	 		var effortMediumCr = $('#solutionEnhanDevFixedCapacityDTO\\.effortMediumCr').val();
	 		var effortComplexC = $('#solutionEnhanDevFixedCapacityDTO\\.effortComplexCr').val();
		
	 		var totalEffortHrsCr = (parseInt(quanSimple)*parseInt(effortSimpleCr)) + 
				(parseInt(quanMedium)*parseInt(effortMediumCr)) + 
				(parseInt(quanComplex)*parseInt(effortComplexC));
	 		
	 		
	 		$('#solutionEnhanDevFixedCapacityDTO\\.totalEffortHrsCr').val(totalEffortHrsCr);
	 		
	 		var effortManDays = totalEffortHrsCr/utilizationPerDay;
	 		var effortManWeeks= totalEffortHrsCr/utilizationPerWeek;
	 	    var effortManMonths = totalEffortHrsCr/utilizationPerMonth;
	 	    
	 	   	$('#solutionEnhanDevFixedCapacityDTO\\.effortManDays').val(parseFloat(effortManDays).toFixed(2));
	 	 	$('#solutionEnhanDevFixedCapacityDTO\\.effortManWeeks').val(parseFloat(effortManWeeks).toFixed(2));
	 	 	$('#solutionEnhanDevFixedCapacityDTO\\.effortManMonths').val(parseFloat(effortManMonths).toFixed(2));
		
	 	 	var averageFte;
	 	   
	 	    var duritionUnit = $('#solutionEnhanDevFixedCapacityDTO\\.duritionUnit').val();
	 	    if(duritionUnit == 'Days'){
	 	    	averageFte = effortManDays/durition;
	 	    }else if(duritionUnit == 'Weeks'){
	 	    	averageFte = effortManWeeks/durition;
	 	    }else if(duritionUnit == 'Months'){
	 	    	averageFte = effortManMonths/durition;
	 	    }
	 	    
	 		$('#solutionEnhanDevFixedCapacityDTO\\.averageFtecr').val(parseFloat(averageFte).toFixed(2));
		}
	});
});

</script>
<form:form id="enhancementAndDevFormFC" action="saveSolutionEnhanDev" method="post" modelAttribute="enhancementAndDevForm">

<form:hidden path="solutionDimentionAttId" /> 
<form:hidden path="opportunityScopeId"/> 
<form:hidden path="solutionEnhanDevFixedCapacityDTO.solutionEnhanDevFixedCapacityId"/>
<table width="100%" cellspacing="1" cellpadding="2" class="tdLabelLeft">

  <tr align="center" bgcolor="#e3e3e3">
    <td height="25" colspan="4"><strong>Fixed Capacity</strong></td>
  </tr>

  <tr bgcolor="#f7f7f7">
    <td width="20%">Customer is buying</td>
    <td width="20%" align="center"><form:input path="solutionEnhanDevFixedCapacityDTO.customerBuying" class="textBoxSNumric"  maxlength="7" onkeypress="return isDecimal(event);" onblur="calculateFTEHrsPerMonth();" /></td>
    <td width="20%">
    	  <form:select path="solutionEnhanDevFixedCapacityDTO.customerBuyingUnit" class="listBoxSmall"  >
          <form:options items="${unitFCList}" />
        </form:select>
    </td>
    
    <td  width="40%" > <input type="checkbox" name="defaultFC" value="" >
	          <b>Default</b></td></td>
  </tr>
  </tr>
  </table>
   <div id="hrsDiv" style="display:block" width="100%">
   <table width="100%" cellspacing="1" cellpadding="2" class="tdLabelLeft">
  	<tr bgcolor="#f7f7f7">
    <td width="20%">Equivalent FTE</td>
    <td width="20%">
       <form:input path="solutionEnhanDevFixedCapacityDTO.averageFtehrs" class="textBoxSCalculateResults"  readonly="true"  /> </td>

    <td width="20%">&nbsp;</td>
     <td width="40%">&nbsp;</td>
  </tr>
 </table>
 </div>
 
  <div id="crDiv" style=" "  >
 
<table width="100%" cellspacing="1" cellpadding="2" class="tdLabelLeft">
  <tr bgcolor="#e3e3e3" align="center">
    <td width="207" rowspan="3" align="left" >No. of CRs</td>
    <td colspan="3">Quantity to be Developed</td>
    <td height="25" colspan="3" >Effort Per Object (In Hours)</td>
    <td width="100" rowspan="3">Total Effort <br />
      (in Hours)</td>
  </tr>
  <tr align="center" bgcolor="#e3e3e3">
    <td width="139" height="22" >Simple</td>
    <td width="125" >Medium</td>
    <td width="119" >Complex</td>
    <td width="122" bgcolor="#e3e3e3" >Simple</td>
    <td width="109" >Medium</td>
    <td width="116" >Complex</td>
  </tr>
  <tr bgcolor="#f7f7f7" >
    <td > <form:input path="solutionEnhanDevFixedCapacityDTO.pcqaunSimpleCr" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" onblur="changePCofCR();"/> %</td>
    <td > <form:input path="solutionEnhanDevFixedCapacityDTO.pcqaunMediumCr" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" onblur="changePCofCR();"/>% </td>
    <td > <form:input path="solutionEnhanDevFixedCapacityDTO.pcqaunComplexCr" class="textBoxSCalculateResults"  maxlength="4" readonly="true"/> %</td>
      <td colspan="3">&nbsp;</td>
  </tr>
  <tr bgcolor="#f7f7f7" >
    <td align="left">#CR</td>
   
    <td > <form:input path="solutionEnhanDevFixedCapacityDTO.qaunSimpleCr" class="textBoxSCalculateResults"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevFixedCapacityDTO.qaunMediumCr" class="textBoxSCalculateResults"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    <td > <form:input path="solutionEnhanDevFixedCapacityDTO.qaunComplexCr" class="textBoxSCalculateResults"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    
      <td > <form:input path="solutionEnhanDevFixedCapacityDTO.effortSimpleCr" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevFixedCapacityDTO.effortMediumCr" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    <td > <form:input path="solutionEnhanDevFixedCapacityDTO.effortComplexCr" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
  
     <td > <form:input path="solutionEnhanDevFixedCapacityDTO.totalEffortHrsCr" class="textBoxSCalculateResults"  readonly="true" /> </td>
    </tr>
 <tr bgcolor="#f7f7f7" align="center">
    <td align="left">Effort in Man Days</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
     <td > <form:input path="solutionEnhanDevFixedCapacityDTO.effortManDays" class="textBoxSCalculateResults"   readonly="true"/> </td>
  </tr>
  <tr bgcolor="#f7f7f7" align="center">
    <td align="left">Effort in Man Weeks</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
        <td > <form:input path="solutionEnhanDevFixedCapacityDTO.effortManWeeks" class="textBoxSCalculateResults" readonly="true" /> </td>

  </tr>
  <tr bgcolor="#f7f7f7" align="center">
    <td align="left">Effort in Man Months</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
        <td > <form:input path="solutionEnhanDevFixedCapacityDTO.effortManMonths" class="textBoxSCalculateResults" readonly="true" /> </td>

  </tr>
  <tr bgcolor="#f7f7f7">
    <td>Duration</td>
       <td > <form:input path="solutionEnhanDevFixedCapacityDTO.durition" class="textBoxSNumric" onkeypress="return isDecimal(event);"  /> </td>
	     <td ><form:select path="solutionEnhanDevFixedCapacityDTO.duritionUnit" class="textBoxSmallNew" >
          <form:options items="${duritionUnitList}" />
        </form:select> </td>
	
    <td>&nbsp;</td>
    <td colspan="3" align="right" style="padding-right:10px;">Average FTE</td>
         <td > <form:input path="solutionEnhanDevFixedCapacityDTO.averageFtecr" class="textBoxSCalculateResults"  readonly="true"  /> </td>

  </tr>
  
</table>
</div>
<div>
  <c:if test="${hasEditSolAccess!='false'}">
<table width="100%">
<tr >

     <td class="tdButtonRow" colspan="7" bgcolor="#f7f7f7">
	      	<a id="resetFC" class="TabsCommonButtons" >Reset</a>
	      	<a id="saveFC" class="TabsCommonButtons" >Save</a>
	      	<a id="calculateFC" class="TabsCommonButtons">Calculate</a>
	      </td>
	       <td width="10%">&nbsp;</td>
   
  </tr>
  </table>
  </c:if>
</div>

</form:form>