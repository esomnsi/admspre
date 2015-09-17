<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>


<script type="text/javascript">
$(document).ready(function () {
	
	$('#save').click(function() {
		$('#calculate').click();
		$('#distributionForm').attr("action",'../solution/saveJobroleDistribution');
		$('#distributionForm').submit();
	});
	
	$('#next').click(function() {
		var url = "<%=request.getContextPath()%>/solution/createStaffingPlan";    
		$(location).attr('href',url);
	});

	$('#prev').click(function() {
    	var url = "<%=request.getContextPath()%>/solution/ttFTEBreakup";    
    	$(location).attr('href',url);
    });
	
	$('#calculate').click(function() {
		if(validate()){
		
			var len =  $('input[name^="ttDistributionList"][name$="distributionPc"]').length;
			for(var index=0; index<len ; index++){
				calJobFte(index);
			}
		}
   	
    });
});

function validate(){
	var numOfWeeks = $('#numOfWeeks').val();
	var lenJobrole = $('#lenJobrole').val();
	
	
	var weekIndex;
	var len =  $('input[name^="ttDistributionList"][name$="distributionPc"]').length;
	for(var index=0; index<numOfWeeks ; index++){
		var totalPC = 0;;
		for(var j=0;j<lenJobrole;j++){
			var pcIndex = index+numOfWeeks*j;
			pc = document.getElementById("ttDistributionList["+pcIndex+"].distributionPc").value;
			if(!isNaN(parseInt(pc))){
				totalPC += parseInt(pc);
			}
		}
		
		if(totalPC != 100){
			alert("Weekwise sum of the percentage for job role should be 100");
			return false;
		}
	}
	return true;
} 

function calJobFte(index){

	var numOfWeeks = $('#numOfWeeks').val();
	
	var weekIndex = index%numOfWeeks;
	var weekFte = $('#total_'+weekIndex).val();
	var distPC = document.getElementById("ttDistributionList["+index+"].distributionPc").value;
	
	var fteCount =(distPC*weekFte)/100;

	if(fteCount != 0){
		document.getElementById("ttDistributionList["+index+"].ftecount").value = parseFloat(fteCount).toFixed(2);
	}
	//var fteCount = $('#ttDistributionList'+param+'Ftecount').val();
}
</script>
<script type="text/javascript" src="../js/wz_tooltip.js"></script>

	<div id="serviceBucket" style="margin-bottom:10px;">
			<jsp:include page="./serviceBucket.jsp"/>
	</div>
	
<c:if test="${not empty message}">
		<div   class=errorMessageDisp>
				${message}
		</div>
	</c:if>
	
<form:form id="distributionForm" method="post" modelAttribute="distributionForm">
	<table width="1112" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><div class="tdHeaderLabel FteHeaderText"
					style="mafloat: left; margin-bottom: 5px;">Labor Cost For TnT</div></td>
		</tr>
		<tr>
			<td align="center"><jsp:include page="opportunitySummary.jsp"></jsp:include></td>
		</tr>
	
		
		<tr>
			<td height="5"></td>
		</tr>
		<tr>
			<td><table width="1112" border="0" cellspacing="0"
					cellpadding="0">

				<tr>
					<table>
					<tr>
					<td bgcolor="#e3e3e3" style="font-family:arial;font-size: 12px;width: 190px;"><strong>Legend</strong></td>
					<td height="25" bgcolor="#f7f7f7" style="font-family:arial;font-size: 12px;">Planning</td>
					<td bgcolor="#8eb4e3" style="width: 80px;"></td><td></td>
					<td height="25" bgcolor="#f7f7f7" style="font-family:arial;font-size: 12px;">Learning</td>
					<td bgcolor="#f2dcdb" style="width: 80px;"></td><td></td>
					<td height="25" bgcolor="#f7f7f7" style="font-family:arial;font-size: 12px;">Assist</td>
					<td bgcolor="#ffc000" style="width: 80px;"></td><td></td>
					<td height="25" bgcolor="#f7f7f7" style="font-family:arial;font-size: 12px;">Perform</td>
					<td bgcolor="#b3a2c7" style="width: 80px;"></td><td></td>
					<td height="25" bgcolor="#f7f7f7" style="font-family:arial;font-size: 12px;">Deliver</td>
					<td bgcolor="#e46c0a" style="width: 80px;"></td><td></td>
						</tr>
					</table>
				</tr>
					<tr>
						<td><div class="FteBodyDiv">
								<div class="FTEscrollProperty">
									
									<!-- TODAY STARTS -->
									
									<!-- Time line interval table -->
									
									<table border="0" cellpadding="0" cellspacing="2"
										class="payroTable" id="1" style="width: 100%;">
										<tr>
											<td bgcolor="#e3e3e3"><strong>Time line Interval</strong></td>
											<c:forEach items="${timeLineInterval}" varStatus="loop">
												<td align="center" bgcolor="#e3e3e3"><strong>
														${timeLineInterval[loop.index]} </strong></td>
											</c:forEach>
										</tr>
										
										
										<c:set var="tf" value="false"></c:set>
										<c:forEach var="partName" items="${partitionNames}" varStatus="loop">
										<tr>
											<td height="25" bgcolor="#f7f7f7">${partName}</td>
												
												<c:forEach var="dateInterval" items="${timeLineInterval}" varStatus="loopIndex">
												
												<c:set var="colorcode" value="${colorCode[loop.index]}"/>  
								            	
								            	
								            	<c:set var="planPart" value="${colorcode['planningPartition']}" />
								            	<c:set var="learnPart" value="${colorcode['learningPartition']}" />
								  				<c:set var="assitPart" value="${colorcode['assistPartition']}" />
								  				<c:set var="performPart" value="${colorcode['performPartition']}" />
								  				<c:set var="deliverPart" value="${colorcode['deliverPartition']}" />
								  				
								  				
								  			
								  					<c:set var="planParts" value="${fn:split(planPart,'|')}" />	
								  					 <c:if test="${planParts[1] == dateInterval}" >
										            	      <td bgcolor="#8eb4e3" align="center" colspan="${planParts[0]}">&nbsp;</td> 
										               	</c:if>
										           
										           <c:if test="${planParts[1] != dateInterval && loopIndex.index == 0}" >
										            	      <td bgcolor="#f7f7f7" align="center" >&nbsp;</td> 
										               	</c:if>
										           
										             	<c:set var="learnParts" value="${fn:split(learnPart,'|')}" />
										            	<c:if test="${learnParts[1] == dateInterval}" >
										            	     <td bgcolor="#f2dcdb" align="center" colspan="${learnParts[0]}">&nbsp;</td>  
										               	</c:if>
														
														<c:set var="assistParts" value="${fn:split(assitPart,'|')}" />
										            	<c:if test="${assistParts[1] == dateInterval}" >
										            	     <td bgcolor="#ffc000" align="center" colspan="${assistParts[0]}">&nbsp;</td>  
										               </c:if>
										               
										               <c:set var="performParts" value="${fn:split(performPart,'|')}" />
										            	<c:if test="${performParts[1] == dateInterval}" >
										            	     <td bgcolor="#b3a2c7" align="center" colspan="${performParts[0]}">&nbsp;</td>  
										               </c:if>
										               
										               <c:set var="deliverParts" value="${fn:split(deliverPart,'|')}" />
										            	<c:if test="${deliverParts[1] == dateInterval}" >
										            	     <td bgcolor="#e46c0a" align="center" colspan="${deliverParts[0]}">&nbsp;</td>  
										               </c:if>
								            
											</c:forEach>
										</tr>
										</c:forEach>
										
										<!-- ftes table -->
											
												<tr>
													<td height="25" bgcolor="#f7f7f7" style="padding:0 9px 0 9px;">Onshore FTE</td>
													<c:forEach items="${onShore}" var="valOnShore">
														<td align="center" bgcolor="#f7f7f7">
									<fmt:formatNumber  minFractionDigits="2" value="${valOnShore}" />
														</td>
													</c:forEach>
												</tr>
												<tr>
													<td height="25" bgcolor="#f7f7f7" style="padding:0 9px 0 9px;">Offshore FTE</td>
													<c:forEach items="${offShore}" var="valOffShore">
														<td align="center" bgcolor="#f7f7f7">
									<fmt:formatNumber  minFractionDigits="2" value="${valOffShore}" />
														</td>
													</c:forEach>
												</tr>
												<tr>
													<td  align="center" bgcolor="#e3e3e3" style="padding:0 8px 0 8px;"><strong>Total</strong></td>
								<c:forEach items="${total}" var="valTotal"  varStatus="loop">
														<td align="center" bgcolor="#e3e3e3" style="padding:0 8px 0 8px;">
									<input type="hidden" id="total_${loop.index}" value="${valTotal}" >
										<strong><fmt:formatNumber  minFractionDigits="2" value="${valTotal}" /> </strong>
														</td>
													</c:forEach>
												</tr>
												
												<tr>
									                <td height="25" bgcolor="#f7f7f7" style="padding:0 9px 0 9px;">% Onshore</td>
									               	<c:forEach items="${onShorepc}" var="valOnShorepc">
														<td align="center" bgcolor="#f7f7f7">
										<strong><fmt:formatNumber  minFractionDigits="2" value="${valOnShorepc}" /></strong>
														</td>
													</c:forEach>
									              </tr>
									              <tr>
									                <td height="25" bgcolor="#f7f7f7" style="padding:0 9px 0 9px;">% Offshore</td>
									               	<c:forEach items="${offShorepc}" var="valOffShorepc">
														<td align="center" bgcolor="#f7f7f7">
										<strong><fmt:formatNumber  minFractionDigits="2" value="${valOffShorepc}" /></strong>
														</td>
													</c:forEach>
									               	
									               
									              </tr>
												
	              						<!-- ftes table ends -->
						<input type="hidden" id="numOfWeeks" value="${fn:length(timeLineInterval)}" />
						<input type="hidden" id="lenJobrole" value="${fn:length(jobRoleDTOList)}" />
					<!-- job role distribution list -->
						<form:hidden path="ttPlanningId" />
						<c:set var="index" value="0" />
						
						<c:forEach items="${jobRoleStagesDTOList}" var="js" varStatus="hh">
							<tr>
								<td bgcolor="#f7f7f7">${js.jobRoleDTO.role} (${js.jobStageDTO.stage})</td>
								
								<c:forEach var="weekDateInterval" items="${timeLineInterval}" varStatus="loop">
								<c:set var="distributionPc" value="" />
									<td align="center" bgcolor="#f1f1f1">
										<span class="smfVal">
						          			<input type="hidden" name="ttDistributionList[${index}].jobRoleDTO.jobRoleId" value="${js.jobRoleDTO.jobRoleId}">
						          			<input type="hidden" name="ttDistributionList[${index}].weekDate" value="${weekDateInterval}">
						          			
						          				<c:forEach items="${selectedJobRoleList}" var="item">
												<fmt:formatDate value="${item.weekDate}" pattern="dd-MMM-yy" var="weekDate"/>
												
													<c:if test="${weekDate == weekDateInterval}">
														<c:if test="${js.jobRoleDTO.jobRoleId == item.jobRoleDTO.jobRoleId}">
																<c:set var="distributionPc" value="${item.distributionPc}" />
											          	
													</c:if>
													</c:if>
												</c:forEach>
															
						          			<input type="text" onkeypress="return isDecimal(event);" id="ttDistributionList[${index}].distributionPc" 
											name="ttDistributionList[${index}].distributionPc"  maxlength="5"
											value="${distributionPc}" class="textBoxNumericCenter" >
			
						          			<c:set var="index" value="${index+1}" />
						          		</span>
									</td>
								</c:forEach>
							</tr>
										
						</c:forEach>
						<tr>
							<td align="center" bgcolor="#e3e3e3" style="padding:0 8px 0 8px;" colspan="${fn:length(timeLineInterval)+1}">
										
							<td>
							</tr>
						<!-- job role distribution list -->
						<c:set var="index" value="0" />
											<c:forEach items="${jobRoleStagesDTOList}" var="js" varStatus="hh">
												<tr>
													<td bgcolor="#f7f7f7">${js.jobRoleDTO.role} (${js.jobStageDTO.stage})</td>
													
								<c:forEach var="weekDateInterval" items="${timeLineInterval}" varStatus="loop">
									<c:set var="ftecount" value="" />
														<td align="center" bgcolor="#f1f1f1">
											<c:forEach items="${selectedJobRoleList}" var="item">
												<fmt:formatDate value="${item.weekDate}" pattern="dd-MMM-yy" var="weekDate"/>
												
													<c:if test="${weekDate == weekDateInterval}">
														<c:if test="${js.jobRoleDTO.jobRoleId == item.jobRoleDTO.jobRoleId}">
																<c:set var="ftecount" value="${item.ftecount}" />
											          	
													</c:if>
													</c:if>
												</c:forEach>
												
															<span class="smfVal">
						          		<input type="text" id="ttDistributionList[${index}].ftecount" 
											name="ttDistributionList[${index}].ftecount"  readonly tabIndex="-1"
											value="${ftecount}" class="textBoxSCalculateCenter" >
			
						          			<c:set var="index" value="${index+1}" />
											          		</span>
														</td>
													</c:forEach>
												</tr>
											</c:forEach>

										<!-- job role list ends -->
										
									</table>
									
									<!-- Time line interval table -->
									
									
									
									<!-- TODAY ENDS -->
									
								</div>
								<!-- <a href="/IT_MSSP/solution/labourCost" style="text-decoration: none;">
									<i><font style="color: blue; float: right; margin-right: 4px;" size="2px">
										<b>To Labor Cost</b>
									</i>
								</a> -->
							</div></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>
 <table align="right">
	<tr>
    	<td align="right"><a id="next" class="portfolioButtons" >Next</a> <a id="save" class="portfolioButtons" >Save</a><a id="calculate" class="portfolioButtons" >Calculate</a>  <a id="prev" class="portfolioButtons" href="#" >Previous</a> </td>
    </tr>
</table>

