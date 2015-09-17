<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">
$(document).ready(function () {
	var errMessage = "${errMessage}";
	if(errMessage.length>5){
		 $("input").attr("disabled","disabled");
		 $("select").attr("disabled","disabled");
	}
	 calRamupPC('plan');
	 calRamupPC('learn');
	 calRamupPC('assist');
	 calRamupPC('perform');
	 calRamupPC('deliver');
	
	$('#next').click(function() {
		var url = "<%=request.getContextPath()%>/solution/ttJobroleDistribution";    
		$(location).attr('href',url);
	});

	$('#prev').click(function() {
    	var url = "<%=request.getContextPath()%>/solution/ttDetail";    
    	$(location).attr('href',url);
    });
	
	$('#save').click(function() {
		var statFlag = true;
		$.each($('#ttFTEBreakupForm .textBoxSmallNumric'), function(){
			if($(this).val() == ""){
			alert("Please enter values for all the fields");
			statFlag = false;
			return false;
		}
		});
		
		if(statFlag){
			$('#ttFTEBreakupForm').attr("action",'../solution/saveFTEBreakup');
			$('#ttFTEBreakupForm').submit();
		}
	});
	
	$('#selPartitionId').change(function(){
		$('#ttFTEBreakupForm').attr("action",'../solution/ttFTEBreakup');
		$('#ttFTEBreakupForm').submit();
	});
	
	var stDate=$('#startDate').val();
	
	var edDate=$('#endDate').val();
	
	var startDate = new Date(stDate);
	var endDate = new Date(edDate);
	
	$(".calendar").datepicker({ showOn: 'button', dateFormat: 'dd-M-yy', minDate:startDate , maxDate: endDate, 
		beforeShowDay: function(date){ return [date.getDay() == 1,""]},
	buttonImageOnly: true, buttonImage: '../images/icon_cal.png' });	
	
	$(".calendar2").datepicker({ showOn: 'button', dateFormat: 'dd-M-yy', minDate:startDate , maxDate: endDate, 
		beforeShowDay: function(date){ return [date.getDay() == 5,""]},
	buttonImageOnly: true, buttonImage: '../images/icon_cal.png' });	
});

function calRamupPC(param){
	var fteCount = $('#partitionDetailDTO\\.'+param+'Ftecount').val();
	var partitionFTE = "${partitionFTE}";
	if(parseFloat(fteCount) > parseFloat(partitionFTE) ){
		
	}
	var rampupPC = (fteCount*100)/partitionFTE;
	if(rampupPC != 0){
		$('#'+param+'Rampup').val(parseFloat(rampupPC).toFixed(2));
	}
}

function calOnOffFTE(param){
	
	var fteCount = $('#partitionDetailDTO\\.'+param+'Ftecount').val();
	
	var onPc = $('#ttOnOffRatioDTO\\.'+param+'OnPc').val();
	var offPc = 100-onPc;
	$('#ttOnOffRatioDTO\\.'+param+'OffPc').val(offPc);
	
	var onFTE =  (onPc*fteCount)/100;
	var offFTE =  fteCount-onFTE;
	
	$('#ttOnOffRatioDTO\\.'+param+'OnFte').val(parseFloat(onFTE).toFixed(2));
	$('#ttOnOffRatioDTO\\.'+param+'OffFte').val(parseFloat(offFTE).toFixed(2));
	
}
var monthValue={'Jan':0,'Feb':1,'Mar':2,'Apr':3,'May':4,'Jun':5,'Jul':6,'Aug':7,'Sep':8,'Oct':9,'Nov':10,'Dec':11};

function validateDates(smaller,greater,phaseDifference){
	
	var day1, day2;
	var month1, month2;
	var year1, year2;
	var date1, date2;
	
	var start = document.getElementById(smaller).value;
	var end = document.getElementById(greater).value;
	
	
	if(start != '')
	{
		day1 = start.substring (0, start.indexOf ("-"));
		month1 = start.substring (start.indexOf ("-")+1, start.lastIndexOf ("-"));
		year1 = start.substring (start.lastIndexOf ("-")+1, start.length);
		date1 = new Date(year1+"/"+monthValue[month1]+"/"+day1);
	}
	if(end != ''){
		
		day2 = end.substring (0, end.indexOf ("-"));
		month2 = end.substring (end.indexOf ("-")+1, end.lastIndexOf ("-"));
		year2 = end.substring (end.lastIndexOf ("-")+1, end.length);
		date2 = new Date(year2+"/"+monthValue[month2]+"/"+day2);
	}
	
	if(date2 < date1){
		alert("Selected date should not be less than its previous date");
		document.getElementById(greater).value = "";
		document.getElementById(greater).focus();
		return false;
	}
	
	if(phaseDifference){
		msPerDay = 24 * 60 * 60 * 1000;
		
		var dd = Math.round((Date.parse(date2)).valueOf() - (Date.parse(date1)).valueOf())/msPerDay + 1;
		
		if(dd > 5){
			alert("Difference of dates between phases should not exceed 1 week");
			document.getElementById(greater).value = "";
			document.getElementById(greater).focus();
			return false;
		}
	}
}

</script>

<form:form id="ttFTEBreakupForm" method="post" modelAttribute="ttDetailForm">

<input type="hidden" id="startDate" value="${opportunityDTO.opportunityDetailsDTO.tsd}">
<input type="hidden" id="endDate" value="${opportunityDTO.opportunityDetailsDTO.ted}">

<jsp:include page="opportunitySummary.jsp"></jsp:include>

 <div class="tdHeaderLabel FteHeaderText" style="margin-bottom:20px;">T&T FTE Breakup</div>
 
<c:if test="${not empty errMessage}">
		<div  id="messageDiv" class="errorMessageDisp" >
				${errMessage}
		</div>
	</c:if>
	
	<c:if test="${not empty message}">
		<div   class="message">
				${message}
		</div>
	</c:if>
<div id="bodyDiv1" class="mainBodyDiv1"
			style="float: left; width: 100%; margin-top: 2px;">
			
<table width="1112" border="0" cellspacing="0" cellpadding="0" class="partition">
<tr class="commonTextForlever">
            <td width="10" height="20">Select Partition</td>
            <td width="500">
            <form:select path="selPartitionId" class="listBoxSmall">
				<form:options items="${partitionNames}"
					itemValue="ttpartitionNameId" itemLabel="ttpartitionName" />
				</form:select>
            </td>
</tr>

</table>
  
  <div class="ui-widget-header tdTableHead">
	
	<span> FTE loading </span>
	</div>
	 <form:hidden path="partitionDetailDTO.ttpartitionDetailId" />
<table width="1112" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    <td align="center"><table width="1112" cellpadding="0" cellspacing="1" class="tableText">
      
      <tr>
        <td width="264" height="20" align="center" bgcolor="#093862">Phases</td>
        <td width="210" align="center" bgcolor="#093862">Start Date</td>
        <td width="210" align="center" bgcolor="#093862">End Date</td>
        <td width="210" align="center" bgcolor="#093862">FTE Count</td>
         <td width="210" align="center" bgcolor="#093862">Ramp Up Pattern (%)</td>
      </tr>
      <tr class="ComontableTextTd">
        <td height="20" bgcolor="#f1f1f1"><strong>Planning</strong></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
           <form:input id="planStartDate" path="partitionDetailDTO.planStartDate" class="textBoxSmallNumric calendar" readonly="true" onchange="validateDates('planStartDate','planEndDate')"/>
        </span></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input id="planEndDate"  path="partitionDetailDTO.planEndDate" class="textBoxSmallNumric calendar2"  readonly="true" onchange="validateDates('planStartDate','planEndDate')"/>
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
           <form:input path="partitionDetailDTO.planFtecount" class="textBoxSmallNumric" onkeypress="return isDecimal(event);" onBlur="calRamupPC('plan')"/>
        </span></td>
        <td width="210" align="left" bgcolor="#f1f1f1"><span class="smfVal">
             <input id="planRampup" type="text" class=textBoxCalculateResults readonly/>
        </span></td>
       
      </tr>
      <tr class="ComontableTextTd">
        <td height="20" bgcolor="#f1f1f1"><strong>Learning</strong></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
           <form:input id="learnStartDate" path="partitionDetailDTO.learnStartDate" class="textBoxSmallNumric calendar" readonly="true" onchange="validateDates('planEndDate','learnStartDate','true')"
           />
        </span></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input id="learnEndDate"  path="partitionDetailDTO.learnEndDate" class="textBoxSmallNumric calendar2"  readonly="true"  onchange="validateDates('learnStartDate','learnEndDate')" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
           <form:input path="partitionDetailDTO.learnFtecount" class="textBoxSmallNumric" onkeypress="return isDecimal(event);" onBlur="calRamupPC('learn')"/>
        </span></td>
        <td width="210" align="left" bgcolor="#f1f1f1"><span class="smfVal">
             <input id="learnRampup" type="text" class=textBoxCalculateResults readonly/>
        </span></td>
       
      </tr>
      <tr class="ComontableTextTd">
        <td height="20" bgcolor="#f1f1f1"><strong>Assist</strong></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
           <form:input id="assistStartDate" path="partitionDetailDTO.assistStartDate" class="textBoxSmallNumric calendar" readonly="true"  onchange="validateDates('learnEndDate','assistStartDate','true')" />
        </span></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input id="assistEndDate"  path="partitionDetailDTO.assistEndDate" class="textBoxSmallNumric calendar2"  readonly="true" onchange="validateDates('assistStartDate','assistEndDate')"/>
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
           <form:input path="partitionDetailDTO.assistFtecount" class="textBoxSmallNumric"  onkeypress="return isDecimal(event);" onBlur="calRamupPC('assist')"/>
        </span></td>
        <td width="210" align="left" bgcolor="#f1f1f1"><span class="smfVal">
             <input id="assistRampup" type="text" class=textBoxCalculateResults readonly/>
        </span></td>
        
      </tr>
      <tr class="ComontableTextTd">
        <td height="20" bgcolor="#f1f1f1"><strong>Perform</strong></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
           <form:input id="performStartDate" path="partitionDetailDTO.performStartDate" class="textBoxSmallNumric calendar" readonly="true" onchange="validateDates('assistEndDate','performStartDate','true')" />
        </span></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input id="performEndDate"  path="partitionDetailDTO.performEndDate" class="textBoxSmallNumric calendar2"  readonly="true" onchange="validateDates('performStartDate','performEndDate')"/>
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
           <form:input path="partitionDetailDTO.performFtecount" class="textBoxSmallNumric" onkeypress="return isDecimal(event);" onBlur="calRamupPC('perform')"/>
        </span></td>
        <td width="210" align="left" bgcolor="#f1f1f1"><span class="smfVal">
             <input id="performRampup" type="text" class=textBoxCalculateResults readonly/>
        </span></td>
      </tr>
      <tr class="ComontableTextTd">
        <td  bgcolor="#f1f1f1"><strong>Deliver</strong></td>
       <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
           <form:input id="deliverStartDate" path="partitionDetailDTO.deliverStartDate" class="textBoxSmallNumric calendar" readonly="true" onchange="validateDates('performEndDate','deliverStartDate','true')"/>
        </span></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input id="deliverEndDate"  path="partitionDetailDTO.deliverEndDate" class="textBoxSmallNumric calendar2"  readonly="true" onchange="validateDates('deliverStartDate','deliverEndDate')"/>
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
           <form:input path="partitionDetailDTO.deliverFtecount" class="textBoxSmallNumric" value="${partitionFTE}" readonly="true"/>
        </span></td>
        <td width="210" align="left" bgcolor="#f1f1f1"><span class="smfVal">
             <input id="deliverRampup" type="text" class=textBoxCalculateResults value="100" readonly/>
        </span></td>
        
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="5"></td>
  </tr>
</table>

<div  class="ui-widget-header tdTableHead">
	
	<span> Onshore - Offshore Breakup </span>
	</div>

<form:hidden path="ttOnOffRatioDTO.ttonOffRatioId" />
<table width="1112" border="0" cellspacing="0" cellpadding="0" style="margin-top:0px;">
 
  <tr>
    <td align="left"><table width="1112" cellpadding="0" cellspacing="1" class="tableText">
      
      <tr>
        <td width="264" height="20" align="center" bgcolor="#093862">Phases</td>
        <td width="210" align="center" bgcolor="#093862">Onshore %</td>
        <td width="210" align="center" bgcolor="#093862">Offshore %</td>
        <td width="210" align="center" bgcolor="#093862">Onshore FTE</td>
         <td width="210" align="center" bgcolor="#093862">Offshore FTE</td>
      </tr>
      <tr class="ComontableTextTd">
        <td height="20" bgcolor="#f1f1f1"><strong>Planning</strong></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input path="ttOnOffRatioDTO.planOnPc" class="textBoxSmallNumric " onkeypress="return isDecimal(event);" onBlur="calOnOffFTE('plan')" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input path="ttOnOffRatioDTO.planOffPc" class="textBoxCalculateResults " readonly="true" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="ttOnOffRatioDTO.planOnFte" class="textBoxCalculateResults " readonly="true" />
        </span></td>
        <td width="210" align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="ttOnOffRatioDTO.planOffFte" class="textBoxCalculateResults " readonly="true" />
        </span></td>
       
      </tr>
      <tr class="ComontableTextTd">
        <td height="20" bgcolor="#f1f1f1"><strong>Learning</strong></td>
          <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input path="ttOnOffRatioDTO.learnOnPc" class="textBoxSmallNumric " onkeypress="return isDecimal(event);" onBlur="calOnOffFTE('learn')" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input path="ttOnOffRatioDTO.learnOffPc" class="textBoxCalculateResults " readonly="true" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="ttOnOffRatioDTO.learnOnFte" class="textBoxCalculateResults " readonly="true" />
        </span></td>
        <td width="210" align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="ttOnOffRatioDTO.learnOffFte" class="textBoxCalculateResults " readonly="true" />
        </span></td>
       
       
      </tr>
      <tr class="ComontableTextTd">
        <td height="20" bgcolor="#f1f1f1"><strong>Assist</strong></td>
          <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input path="ttOnOffRatioDTO.assistOnPc" class="textBoxSmallNumric " onkeypress="return isDecimal(event);" onBlur="calOnOffFTE('assist')" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input path="ttOnOffRatioDTO.assistOffPc" class="textBoxCalculateResults " readonly="true" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="ttOnOffRatioDTO.assistOnFte" class="textBoxCalculateResults " readonly="true" />
        </span></td>
        <td width="210" align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="ttOnOffRatioDTO.assistOffFte" class="textBoxCalculateResults " readonly="true" />
        </span></td>
       
        
      </tr>
      <tr class="ComontableTextTd">
     	  <td  bgcolor="#f1f1f1"><strong>Perform</strong></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input path="ttOnOffRatioDTO.performOnPc" class="textBoxSmallNumric " onkeypress="return isDecimal(event);" onBlur="calOnOffFTE('perform')" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input path="ttOnOffRatioDTO.performOffPc" class="textBoxCalculateResults " readonly="true" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="ttOnOffRatioDTO.performOnFte" class="textBoxCalculateResults " readonly="true" />
        </span></td>
        <td width="210" align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="ttOnOffRatioDTO.performOffFte" class="textBoxCalculateResults " readonly="true" />
        </span></td>
       
        
      </tr>
      <tr class="ComontableTextTd">
        <td  bgcolor="#f1f1f1"><strong>Deliver</strong></td>
          <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input path="ttOnOffRatioDTO.deliverOnPc" class="textBoxSmallNumric " onkeypress="return isDecimal(event);" onBlur="calOnOffFTE('deliver')" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
            <form:input path="ttOnOffRatioDTO.deliverOffPc" class="textBoxCalculateResults " readonly="true" />
        </span></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="ttOnOffRatioDTO.deliverOnFte" class="textBoxCalculateResults " readonly="true" />
        </span></td>
        <td width="210" align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="ttOnOffRatioDTO.deliverOffFte" class="textBoxCalculateResults " readonly="true" />
        </span></td>
       
        
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="5"></td>
    
  </tr>
</table>
<table align="right">
	<tr>
    	<td align="right"><a id="next" class="portfolioButtons" >Next</a> <a id="save" class="portfolioButtons" >Save</a> <a id="prev" class="portfolioButtons" href="#" >Previous</a> </td>
    </tr>
</table>


</form:form>

</div>
