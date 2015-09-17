<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">

$(document).ready(function () {
	var totalFTE = document.getElementById("yearTotalFTE").value;
	var noOfPart = $('#planningDTO\\.noofPartition').val();
	
	totalFTE=totalFTE.replace(/,/g, "");
	
	$('#go').click(function() {
		var noOfPart = $('#planningDTO\\.noofPartition').val();
		var ttplanningId = $("#planningDTO\\.ttplanningId").val();
		if(noOfPart == '' || parseInt(noOfPart) <1){
			alert("Partition should be a numeric value");
			return false;
		}
		 	$.get("<%=request.getContextPath()%>/solution/addTTPartition", { numOfPartition: noOfPart, totalFTE:totalFTE},
				function(data){
		 		
		 		if(ttplanningId != ''){
		 			$("#delPlanningId").val(ttplanningId);
		 			$("#planningDTO\\.ttplanningId").val('');
		 		}
		 			$('#noOfPart').html(data);
				}); 
			});	
	
	$('#save').click(function() {
		if(validateFTE(totalFTE)){
			$('#ttForm').attr("action",'../solution/savePartition');
			$('#ttForm').submit();
		}
	});
	
	$('#prev').click(function() {
		if(validateFTE(totalFTE)){
			$('#ttForm').attr("action",'../solution/reviewUpdatedFTE');
			$('#ttForm').submit();
		}
	});
	
	$('#next').click(function() {
		var url = "<%=request.getContextPath()%>/solution/ttFTEBreakup";    
		$(location).attr('href',url);
	});
});

function validateFTE(totalFTE){

	var len =  $('input[name^="partitionNameList"][name$="ttpartitionFte"]').length;
	var fteSum=0;;
	for(var index=0; index<len ; index++){
		var fte = document.getElementById("partitionNameList["+index+"].ttpartitionFte").value;
		fteSum += parseFloat(fte);
	}
	fteSum = parseFloat(fteSum).toFixed(2);
//	alert(fteSum  +" | "+parseFloat(totalFTE));
	if(parseFloat(totalFTE) != fteSum){
		alert("Sum of partition FTE should be equal to "+totalFTE);
		return false;
	}
	return true;
	
}
</script>

<div id="bodyDiv" class="mainBodyDiv1" style="margin-top:5px;">
<jsp:include page="opportunitySummary.jsp"></jsp:include>
		</div>
		<div id="serviceBucket" style="margin-bottom:10px;">
			<jsp:include page="./serviceBucket.jsp"/>
		</div>

 <div class="tdHeaderLabel FteHeaderText" style="margin-bottom:20px;">T&T Partitioning Detail</div>
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
<form:form id="ttForm" method="post" modelAttribute="ttDetailForm">
 <form:hidden path="planningDTO.ttplanningId" />
  <form:hidden path="delPlanningId" />


		

<div id="bodyDiv1" class="mainBodyDiv1"
			style="float: left; width: 100%; margin-top: 2px;">
		 
<table width="600" cellpadding="0" cellspacing="1"  align="center" style="margin-top:0px;" >
      <tr class="ComontableTextTd">
        <td width="214" height="20" bgcolor="#f1f1f1"><strong>Enter the number of partitions</strong></td>
        <td width="281" align="left" bgcolor="#f1f1f1"><span class="smfVal" >
       <form:input path="planningDTO.noofPartition" class="textBoxSNumric"  onkeypress="return isNumeric(event);" maxlength="2" />
           <a id="go" class="portfolioButtons" style="margin-right: 100px;">Go</a> </span></td>
       
      </tr>
      
     <tr ><td colspan="2" bgcolor="#f1f1f1" id="noOfPart">
     
     	<c:choose>
			<c:when test="${not empty ttDetailForm.partitionNameList}">
     			<table width="100%"  cellpadding="0" cellspacing="1" border="0">
				  <tr class="ComontableTextTd"> <td width="210" align="left" bgcolor="#f1f1f1">
				 Partition Name</td><td  align="left" bgcolor="#f1f1f1">
				 FTE</td></tr>
				 <c:set var="totalFTE" value="0" />
				 <c:set var="rowcount" value="${0}"/>
				<c:forEach var="partName" items="${ttDetailForm.partitionNameList}" varStatus="i">
				
			 
     			 <tr class="ComontableTextTd">
				    <td  align="center" bgcolor="#f1f1f1"><span class="smfVal">
				    	<input type="text" class="textBoxSmalldevlopment" id="partitionNameList[${rowcount}].ttpartitionName"
							name="partitionNameList[${rowcount}].ttpartitionName" value="${partName.ttpartitionName}" maxlength="50"> 
				      </span></td>
				     <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
				     <input type="test" class="textBoxSmallNumric" id="partitionNameList[${rowcount}].ttpartitionFte"
							name="partitionNameList[${rowcount}].ttpartitionFte" value="${partName.ttpartitionFte}" onkeypress="return isDecimal(event);" maxlength="7"> 
				    </span></td>
				   </tr>
				  
				   	<input type="hidden" id="partitionNameList[${rowcount}].ttpartitionNameId"
							name="partitionNameList[${rowcount}].ttpartitionNameId" value="${partName.ttpartitionNameId}" >
					 <c:set var="rowcount" value="${rowcount+1}"/>
				   <c:set var="totalFTE" value="${totalFTE+partName.ttpartitionFte}" />
				   </c:forEach>
				  <fmt:formatNumber var="totalFTE" value="${totalFTE}" maxFractionDigits="2" />
				   <tr class="ComontableTextTd">
				        <td  height="20" bgcolor="#f1f1f1"><strong>Total FTE Count</strong></td>
				        <td align="left" bgcolor="#f1f1f1"><span class="smfVal" >
				          <input type="text" class="textBoxCalculateResults" value="${totalFTE}" readonly="true"/>
				        </span></td>
				   </tr> 
				   </table>
     		
     		
     		</c:when>
     	</c:choose>
     	
     
     </td>
     </tr>
      <tr class="ComontableTextTd">
        <td width="214" height="20" bgcolor="#f1f1f1"></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          
        </span></td>
       
        
      </tr>
      <tr class="ComontableTextTd">
        <td width="214" height="20" bgcolor="#f1f1f1"><strong>Start date of the T&amp;T</strong></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="startDate" type="text" class="textBoxCalculateResults" readonly="true" value="${opportunityDTO.opportunityDetailsDTO.tsd}"/>
   
        </span></td>
       
        
      </tr>
      <tr class="ComontableTextTd">
        <td width="214" height="20" bgcolor="#f1f1f1"><strong>End date of the T&amp;T</strong></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <form:input path="endDate" type="text" class="textBoxCalculateResults" readonly="true" value="${opportunityDTO.opportunityDetailsDTO.ted}"/>
        </span></td>
       
        
      </tr>
    </table>
    <table align="right">
	<tr>
    	<td align="right">
    	 <a id="next" class="portfolioButtons" >Next</a>
    	 <a id="save" class="portfolioButtons" >Save</a> 
    	 <a id="prev" class="portfolioButtons" >Previous</a> 
    	 </td>
    </tr>
	</table>
</div>
</form:form>