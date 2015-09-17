 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
$(document).ready (function () { 
	$('#saveRICEFW').click(function() {
			
		  if($('#dimensionAttributeId') != null){
				$('#enhancementAndDevForm input[name="solutionDimentionAttId"]').val($('#dimensionAttributeId').val()); 
			}
			$('#calculateRICEFW').click();
			$('#enhancementAndDevForm').submit();
	});
	
	$('#resetRICEFW').click(function() {
		 $('#enhancementAndDevForm').each (function(){
			  this.reset();
			});
	});
	
	$("#enhancementAndDevForm").submit(function() {
		showProgress();
		var options = {
				success: function(html) {
					 $("#RICEFWPanel").html(html);
					 $("#messageDiv").addClass("message");
					 $("#messageDiv").html("Your data has been saved successfully.");
					 refreshServiceBucket();
				},
				url: "./saveSolutionEnhanDev"
				
			};
		
			$(this).ajaxSubmit(options);
			hideProgress();
			return false;
	});
	
	$('#enhancementAndDevForm input[name="defaultRICEFW"]').change(function(e){	
		  if (!$(this).is(':checked')) {
			  $('#enhancementAndDevForm').each (function(){
	    		  this.reset();
	    	});
		  }else{
			 $.ajax({
				 
					type : "POST",
					url : "./loadDefaultValuesForServiceScopes",
					data : {"serviceId": '5'},
					datatype : "text",
					success : function(result){
					
						var data = eval('('+result+')');
						
						 $('#solutionEnhanDevRicefwDTO\\.qaunSimpleInterface').val(data.qaunSimpleInterface);
				 		 $('#solutionEnhanDevRicefwDTO\\.qaunMediumInterface').val(data.qaunMediumInterface);
				 		 $('#solutionEnhanDevRicefwDTO\\.qaunComplexInterface').val(data.qaunComplexInterface);
				 		
				 		 $('#solutionEnhanDevRicefwDTO\\.qaunSimpleExtension').val(data.qaunSimpleExtension);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.qaunMediumExtension').val(data.qaunMediumExtension);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.qaunComplexExtension').val(data.qaunComplexExtension);
				 	   
				 	   	 $('#solutionEnhanDevRicefwDTO\\.qaunSimpleReport').val(data.qaunSimpleReport);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.qaunMediumReport').val(data.qaunMediumReport);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.qaunComplexReport').val(data.qaunComplexReport);
				 	    
				 	   	 $('#solutionEnhanDevRicefwDTO\\.qaunSimpleWorkflow').val(data.qaunSimpleWorkflow);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.qaunMediumWorkflow').val(data.qaunMediumWorkflow);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.qaunComplexWorkflow').val(data.qaunComplexWorkflow);
						
				 	   	$('#solutionEnhanDevRicefwDTO\\.effortSimpleInterface').val(data.effortSimpleInterface);
				 		 $('#solutionEnhanDevRicefwDTO\\.effortMediumInterface').val(data.effortMediumInterface);
				 		 $('#solutionEnhanDevRicefwDTO\\.effortComplexInterface').val(data.effortComplexInterface);
				 		
				 		 $('#solutionEnhanDevRicefwDTO\\.effortSimpleExtension').val(data.effortSimpleExtension);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.effortMediumExtension').val(data.effortMediumExtension);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.effortComplexExtension').val(data.effortComplexExtension);
				 	   
				 	   	 $('#solutionEnhanDevRicefwDTO\\.effortSimpleReport').val(data.effortSimpleReport);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.effortMediumReport').val(data.effortMediumReport);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.effortComplexReport').val(data.effortComplexReport);
				 	    
				 	   	 $('#solutionEnhanDevRicefwDTO\\.effortSimpleWorkflow').val(data.effortSimpleWorkflow);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.effortMediumWorkflow').val(data.effortMediumWorkflow);
				 	   	 $('#solutionEnhanDevRicefwDTO\\.effortComplexWorkflow').val(data.effortComplexWorkflow);
						
						//calTotalL2Incident();
						
					},
					error : function(e) {
						alert("Operation failed.");
					}
				});	
		  }
	  });
	
	$('#calculateRICEFW').click(function() {
		
	   var durition = 	$('#solutionEnhanDevRicefwDTO\\.durition').val();
	   if(durition == ''){
		   alert('Please enter values for durition');
		   return false;
	   }
	   
	  var utilizationPerMonth = 	$('#utilizationPerMonth').val();
		var utilizationPerWeek = $('#utilizationPerWeek').val();
		var utilizationPerDay = 	$('#utilizationPerDay').val();		
		
		var qaunSimpleInterface = $('#solutionEnhanDevRicefwDTO\\.qaunSimpleInterface').val();
		var qaunMediumInterface = $('#solutionEnhanDevRicefwDTO\\.qaunMediumInterface').val();
		var qaunComplexInterface = $('#solutionEnhanDevRicefwDTO\\.qaunComplexInterface').val();
		
		var qaunSimpleExtension = $('#solutionEnhanDevRicefwDTO\\.qaunSimpleExtension').val();
	   	var qaunMediumExtension = $('#solutionEnhanDevRicefwDTO\\.qaunMediumExtension').val();
	   	var qaunComplexExtension = $('#solutionEnhanDevRicefwDTO\\.qaunComplexExtension').val();
	   
	   	var qaunSimpleReport = $('#solutionEnhanDevRicefwDTO\\.qaunSimpleReport').val();
	   	var qaunMediumReport = $('#solutionEnhanDevRicefwDTO\\.qaunMediumReport').val();
	   	var qaunComplexReport = $('#solutionEnhanDevRicefwDTO\\.qaunComplexReport').val();
	    
	   	var qaunSimpleWorkflow = $('#solutionEnhanDevRicefwDTO\\.qaunSimpleWorkflow').val();
	   	var qaunMediumWorkflow = $('#solutionEnhanDevRicefwDTO\\.qaunMediumWorkflow').val();
	   	var qaunComplexWorkflow = $('#solutionEnhanDevRicefwDTO\\.qaunComplexWorkflow').val();
		
	   	qaunSimpleInterface = (isNaN(parseInt(qaunSimpleInterface))?0:qaunSimpleInterface);
	   	qaunMediumInterface = (isNaN(parseInt(qaunMediumInterface))?0:qaunMediumInterface);
	  	qaunComplexInterface = (isNaN(parseInt(qaunComplexInterface))?0:qaunComplexInterface);
	 	qaunSimpleExtension = (isNaN(parseInt(qaunSimpleExtension))?0:qaunSimpleExtension);
		qaunMediumExtension = (isNaN(parseInt(qaunMediumExtension))?0:qaunMediumExtension);
		qaunComplexExtension = (isNaN(parseInt(qaunComplexExtension))?0:qaunComplexExtension);
		
		qaunSimpleReport = (isNaN(parseInt(qaunSimpleReport))?0:qaunSimpleReport);
		qaunMediumReport = (isNaN(parseInt(qaunMediumReport))?0:qaunMediumReport);
		qaunComplexReport = (isNaN(parseInt(qaunComplexReport))?0:qaunComplexReport);
		qaunSimpleWorkflow = (isNaN(parseInt(qaunSimpleWorkflow))?0:qaunSimpleWorkflow);
		qaunMediumWorkflow = (isNaN(parseInt(qaunMediumWorkflow))?0:qaunMediumWorkflow);
		qaunComplexWorkflow = (isNaN(parseInt(qaunComplexWorkflow))?0:qaunComplexWorkflow);
	   
	 	var qaunSimpleTotal = parseInt(qaunSimpleInterface) + parseInt(qaunSimpleExtension) 
	 						+parseInt(qaunSimpleReport)+ parseInt(qaunSimpleWorkflow);
	 
	 	$('#solutionEnhanDevRicefwDTO\\.qaunSimpleTotal').val(qaunSimpleTotal);
	  
	 	var qaunMediumTotal = parseInt(qaunMediumInterface) + parseInt(qaunMediumExtension) 
							+parseInt(qaunMediumReport)+ parseInt(qaunMediumWorkflow);
	 	
	 	
	 	$('#solutionEnhanDevRicefwDTO\\.qaunMediumTotal').val(qaunMediumTotal);
	
		var qaunComplexTotal = parseInt(qaunComplexInterface) + parseInt(qaunComplexExtension) 
								+parseInt(qaunComplexReport)+ parseInt(qaunComplexWorkflow);
	 	
		$('#solutionEnhanDevRicefwDTO\\.qaunComplexTotal').val(qaunComplexTotal);
		
		var effortSimpleInterface = $('#solutionEnhanDevRicefwDTO\\.effortSimpleInterface').val();
		var effortMediumInterface = $('#solutionEnhanDevRicefwDTO\\.effortMediumInterface').val();
		var effortComplexInterface = $('#solutionEnhanDevRicefwDTO\\.effortComplexInterface').val();
		
		var effortSimpleExtension = $('#solutionEnhanDevRicefwDTO\\.effortSimpleExtension').val();
	   	var effortMediumExtension = $('#solutionEnhanDevRicefwDTO\\.effortMediumExtension').val();
	   	var effortComplexExtension = $('#solutionEnhanDevRicefwDTO\\.effortComplexExtension').val();
	   
	   	var effortSimpleReport = $('#solutionEnhanDevRicefwDTO\\.effortSimpleReport').val();
	   	var effortMediumReport = $('#solutionEnhanDevRicefwDTO\\.effortMediumReport').val();
	   	var effortComplexReport = $('#solutionEnhanDevRicefwDTO\\.effortComplexReport').val();
	    
	   	var effortSimpleWorkflow = $('#solutionEnhanDevRicefwDTO\\.effortSimpleWorkflow').val();
	   	var effortMediumWorkflow = $('#solutionEnhanDevRicefwDTO\\.effortMediumWorkflow').val();
	   	var effortComplexWorkflow = $('#solutionEnhanDevRicefwDTO\\.effortComplexWorkflow').val();
	   
		effortSimpleInterface = (isNaN(parseInt(effortSimpleInterface))?0:effortSimpleInterface);
	   	effortMediumInterface = (isNaN(parseInt(effortMediumInterface))?0:effortMediumInterface);
	  	effortComplexInterface = (isNaN(parseInt(effortComplexInterface))?0:effortComplexInterface);
	 	effortSimpleExtension = (isNaN(parseInt(effortSimpleExtension))?0:effortSimpleExtension);
		effortMediumExtension = (isNaN(parseInt(effortMediumExtension))?0:effortMediumExtension);
		effortComplexExtension = (isNaN(parseInt(effortComplexExtension))?0:effortComplexExtension);
		
		effortSimpleReport = (isNaN(parseInt(effortSimpleReport))?0:effortSimpleReport);
		effortMediumReport = (isNaN(parseInt(effortMediumReport))?0:effortMediumReport);
		effortComplexReport = (isNaN(parseInt(effortComplexReport))?0:effortComplexReport);
		effortSimpleWorkflow = (isNaN(parseInt(effortSimpleWorkflow))?0:effortSimpleWorkflow);
		effortMediumWorkflow = (isNaN(parseInt(effortMediumWorkflow))?0:effortMediumWorkflow);
		effortComplexWorkflow = (isNaN(parseInt(effortComplexWorkflow))?0:effortComplexWorkflow);
	   	
	   	
	 	var effortSimpleTotal = parseInt(effortSimpleInterface) + parseInt(effortSimpleExtension) 
	 						+parseInt(effortSimpleReport)+ parseInt(effortSimpleWorkflow);
	 	$('#solutionEnhanDevRicefwDTO\\.effortSimpleTotal').val(effortSimpleTotal);
	  
	 	var effortMediumTotal = parseInt(effortMediumInterface) + parseInt(effortMediumExtension) 
							+parseInt(effortMediumReport)+ parseInt(effortMediumWorkflow);
		$('#solutionEnhanDevRicefwDTO\\.effortMediumTotal').val(effortMediumTotal);
	
		var effortComplexTotal = parseInt(effortComplexInterface) + parseInt(effortComplexExtension) 
								+parseInt(effortComplexReport)+ parseInt(effortComplexWorkflow);
		$('#solutionEnhanDevRicefwDTO\\.effortComplexTotal').val(effortComplexTotal);
	   
		
		
		var totalEffortHrsInterface = (parseInt(qaunSimpleInterface)*parseInt(effortSimpleInterface)) + 
										(parseInt(qaunMediumInterface)*parseInt(effortMediumInterface)) + 
										(parseInt(qaunComplexInterface)*parseInt(effortComplexInterface));
		
		var totalEffortHrsExtension = (parseInt(qaunSimpleExtension)*parseInt(effortSimpleExtension)) + 
									(parseInt(qaunMediumExtension)*parseInt(effortMediumExtension)) + 
									(parseInt(qaunComplexExtension)*parseInt(effortComplexExtension));
		
		var totalEffortHrsReport = (parseInt(qaunSimpleReport)*parseInt(effortSimpleReport)) + 
									(parseInt(qaunMediumReport)*parseInt(effortMediumReport)) + 
									(parseInt(qaunComplexReport)*parseInt(effortComplexReport));
		
		var totalEffortHrsWorkflow = (parseInt(qaunSimpleWorkflow)*parseInt(effortSimpleWorkflow)) + 
									(parseInt(qaunMediumWorkflow)*parseInt(effortMediumWorkflow)) + 
									(parseInt(qaunComplexWorkflow)*parseInt(effortComplexWorkflow));
		
		var totalEffortHrs = totalEffortHrsInterface + totalEffortHrsExtension+totalEffortHrsReport+totalEffortHrsWorkflow;
		
		
		
		$('#solutionEnhanDevRicefwDTO\\.totalEffortHrsInterface').val(totalEffortHrsInterface);
		$('#solutionEnhanDevRicefwDTO\\.totalEffortHrsExtension').val(totalEffortHrsExtension);
		$('#solutionEnhanDevRicefwDTO\\.totalEffortHrsReport').val(totalEffortHrsReport);
		$('#solutionEnhanDevRicefwDTO\\.totalEffortHrsWorkflow').val(totalEffortHrsWorkflow);
		
		$('#solutionEnhanDevRicefwDTO\\.totalEffortHrs').val(totalEffortHrs);
		
		
		var effortManDays = totalEffortHrs/utilizationPerDay;
		var effortManWeeks= totalEffortHrs/utilizationPerWeek;
	    var effortManMonths = totalEffortHrs/utilizationPerMonth;
	    
	   	$('#solutionEnhanDevRicefwDTO\\.effortManDays').val(parseFloat(effortManDays).toFixed(2));
	 	$('#solutionEnhanDevRicefwDTO\\.effortManWeeks').val(parseFloat(effortManWeeks).toFixed(2));
	 	$('#solutionEnhanDevRicefwDTO\\.effortManMonths').val(parseFloat(effortManMonths).toFixed(2));
	
	 	var averageFte;
	 
	    var duritionUnit = $('#solutionEnhanDevRicefwDTO\\.duritionUnit').val();
	    if(duritionUnit == 'Days'){
	    	averageFte = effortManDays/durition;
	    }else if(duritionUnit == 'Weeks'){
	    	averageFte = effortManWeeks/durition;
	    }else if(duritionUnit == 'Months'){
	    	averageFte = effortManMonths/durition;
	    }
	    
		$('#solutionEnhanDevRicefwDTO\\.averageFte').val(parseFloat(averageFte).toFixed(2));
		
	});
	
});

</script>

 <form:form id="enhancementAndDevForm" action="saveSolutionEnhanDev" method="post" modelAttribute="enhancementAndDevForm">
 
<form:hidden path="solutionEnhanDevRicefwDTO.solutionEnhanDevRicefwId"/>
<form:hidden path="solutionDimentionAttId" /> 
<form:hidden path="opportunityScopeId"/> 
 <table width="100%" cellspacing="1" cellpadding="2" class="tdLabelLeft">
  <tr>
    <td height="25" colspan="8" align="center" bgcolor="#e3e3e3"><strong>RICEFW</strong> 
     <span style="margin-left:120px;">
      <input type="checkbox" name="defaultRICEFW" value="" >
	          <b>Default</b>
	      </span>    
	 </td>
    
  </tr>
  <tr bgcolor="#e3e3e3" align="center">
    <td width="209" rowspan="2" align="left" >Type of Objects</td>
    <td colspan="3">Quantity to be Developed</td>
    <td height="25" colspan="3" >Effort Per Object (In Hours)</td>
    <td width="101" rowspan="2">Total Effort <br />
      (in Hours)</td>
  </tr>
  <tr align="center" bgcolor="#e3e3e3">
    <td width="136" height="23" >Simple</td>
    <td width="126" >Medium</td>
    <td width="119" >Complex</td>
    <td width="122" >Simple</td>
    <td width="109" >Medium</td>
    <td width="115" >Complex</td>
    </tr>
  <tr bgcolor="#f7f7f7" align="center">
    <td align="left">Interfaces</td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.qaunSimpleInterface" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.qaunMediumInterface" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.qaunComplexInterface" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    
      <td > <form:input path="solutionEnhanDevRicefwDTO.effortSimpleInterface" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.effortMediumInterface" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.effortComplexInterface" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
  
     <td > <form:input path="solutionEnhanDevRicefwDTO.totalEffortHrsInterface" class="textBoxSCalculateResults"  readonly="true" /> </td>
  
  </tr>
  <tr bgcolor="#f7f7f7" align="center">
    <td align="left">Extensions or eScripts</td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.qaunSimpleExtension" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.qaunMediumExtension" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.qaunComplexExtension" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    
   <td > <form:input path="solutionEnhanDevRicefwDTO.effortSimpleExtension" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.effortMediumExtension" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.effortComplexExtension" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
     <td > <form:input path="solutionEnhanDevRicefwDTO.totalEffortHrsExtension" class="textBoxSCalculateResults"  readonly="true" /> </td>
 
  </tr>
  <tr bgcolor="#f7f7f7" align="center">
    <td align="left">Reports, Forms, Views</td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.qaunSimpleReport" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.qaunMediumReport" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.qaunComplexReport" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    
    <td > <form:input path="solutionEnhanDevRicefwDTO.effortSimpleReport" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.effortMediumReport" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.effortComplexReport" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.totalEffortHrsReport" class="textBoxSCalculateResults"  readonly="true"/> </td>
 
  </tr>
  <tr bgcolor="#f7f7f7" align="center">
    <td align="left">Workflows</td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.qaunSimpleWorkflow" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.qaunMediumWorkflow" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.qaunComplexWorkflow" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    
      <td > <form:input path="solutionEnhanDevRicefwDTO.effortSimpleWorkflow" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.effortMediumWorkflow" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.effortComplexWorkflow" class="textBoxSNumric"  maxlength="4" onkeypress="return isDecimal(event);" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.totalEffortHrsWorkflow" class="textBoxSCalculateResults"  readonly="true"/> </td>
 
  </tr>
  <tr bgcolor="#f7f7f7" align="center">
    <td align="left">TOTAL</td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.qaunSimpleTotal" class="textBoxSCalculateResults"  maxlength="4" readonly="true" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.qaunMediumTotal" class="textBoxSCalculateResults"  maxlength="4" readonly="true" /> </td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.qaunComplexTotal" class="textBoxSCalculateResults"  maxlength="4" readonly="true" /> </td>
    
      <td > <form:input path="solutionEnhanDevRicefwDTO.effortSimpleTotal" class="textBoxSCalculateResults"  maxlength="4" readonly="true" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.effortMediumTotal" class="textBoxSCalculateResults"  maxlength="4" readonly="true" /> </td>
    <td > <form:input path="solutionEnhanDevRicefwDTO.effortComplexTotal" class="textBoxSCalculateResults"  maxlength="4" readonly="true" /> </td>
   <td > <form:input path="solutionEnhanDevRicefwDTO.totalEffortHrs" class="textBoxSCalculateResults"  /> </td>
 
  </tr>
  <tr bgcolor="#f7f7f7" align="center">
    <td align="left">Effort in Man Days</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
     <td > <form:input path="solutionEnhanDevRicefwDTO.effortManDays" class="textBoxSCalculateResults"   readonly="true"/> </td>
  </tr>
  <tr bgcolor="#f7f7f7" align="center">
    <td align="left">Effort in Man Weeks</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
        <td > <form:input path="solutionEnhanDevRicefwDTO.effortManWeeks" class="textBoxSCalculateResults" readonly="true" /> </td>

  </tr>
  <tr bgcolor="#f7f7f7" align="center">
    <td align="left">Effort in Man Months </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
        <td > <form:input path="solutionEnhanDevRicefwDTO.effortManMonths" class="textBoxSCalculateResults" readonly="true" /> </td>

  </tr>
  <tr bgcolor="#f7f7f7">
    <td>Duration</td>
       <td > <form:input path="solutionEnhanDevRicefwDTO.durition" class="textBoxSNumric"  maxlength="5" onkeypress="return isDecimal(event);"/> </td>
	     <td ><form:select path="solutionEnhanDevRicefwDTO.duritionUnit" class="textBoxSmallNew" >
          <form:options items="${duritionUnitList}" />
        </form:select> </td>
	
    <td>&nbsp;</td>
    <td colspan="3" align="right" style="padding-right:10px;">Avgerage FTE</td>
         <td > <form:input path="solutionEnhanDevRicefwDTO.averageFte" class="textBoxSCalculateResults"  readonly="true"  /> </td>

  </tr>
    <c:if test="${hasEditSolAccess!='false'}">
  <tr bgcolor="#f7f7f7">

     <td class="tdButtonRow" colspan="7" bgcolor="#f7f7f7">
	      	<a id="resetRICEFW" class="TabsCommonButtons" >Reset</a>
	      	<a id="saveRICEFW" class="TabsCommonButtons" >Save</a>
	      	<a id="calculateRICEFW" class="TabsCommonButtons">Calculate</a>
	      </td>
	      
   
  </tr>
  </c:if>
</table>
</form:form>