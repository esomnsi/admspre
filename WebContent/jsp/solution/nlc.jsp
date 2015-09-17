<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="msg" uri="http://www.springframework.org/tags"%>


<title>Define Scope</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#usual1 ul").idTabs();
		
		
		//calculateCalDayAndWrkHrs();
		/*$("input[id='ssTotCost']").each(function(index,element){
			var columnIndex=$(element).attr('colIndex');
			calculateTotalCostSS(columnIndex);
		});*/
		calculateMSDPCostOth();
		calculateDependencies($('#mobileCostPerOnsiteFTE'));
		calculateDependencies($('#onsiteConveyanceCost'));
		calculateDependencies($('#onsitePerdiem'));
		calculateDependencies($('#onsiteHotelCostPerNight'));
		calculateDependencies($('#shortTermVisaCost'));
		calculateDependencies($('#roundTripTicketCost'));
		$('.errorMessage_nonlabor').html("");
		$('.errorMessage_nonlabor').hide();
		var reviewFTESS = "${fn:length(NonLabourCostForm.reviewFTEForm.onShoreDataList)}";
		if(reviewFTESS==0){
			$('.errorMessage_nonlabor').html("Please check as FTE required Solution Lever, Location Breakup& Pyramid data not found !!! ");
			$('.errorMessage_nonlabor').show();
			$('#saveButton').attr("disabled", "disabled");
		}
		
		if("${successMsg}"!=""){
			$('.successMessage_nonLabor').html("${successMsg}");
			$('.successMessage_nonLabor').show();
			setTimeout("location.href='../solution/manageADRGrid'", 500);
		}
		
	});
</script>
<script type="text/javascript" src="../js/jquery-1.js"></script>
<script type="text/javascript" src="../js/jquery_003.js"></script>
<script type="text/javascript" src="../js/nlc.js"></script>
</head>


<div id="mainBody" class="bodyCss">
	<div id="bodyDiv" class="mainBodyDivwithoutLine">
		<div id="bodyDiv" class="mainBodyDiv1"></div>
         <div class="errorMessage_nonlabor" align="left" >
         </div>
          <div class="successMessage_nonLabor" align="left" >${successMsg}</div> 
 		  <c:if test="${fn:length(NonLabourCostForm.reviewFTEForm.onShoreDataList) gt 0}">
			<table width="100%">
				<%-- <tr>
					<td class="tdHeaderLabel" colspan="10"><b>Non LabourCost</b>
					</td>
				</tr> --%>
				
				<tr>
					<td align="center" colspan="2">
						<div id="bodyDiv" class="mainBodyDiv1">
							<jsp:include page="opportunitySummary.jsp"></jsp:include>
						</div></td>
				</tr>
				<tr>
					<td align="center" height="5" colspan="2"></td>
				</tr>
			</table>
		
			
			<div id="serviceBucket" style="margin-bottom:10px;">
				<jsp:include page="./serviceBucket.jsp"/>
			
			</div>
			
		<div class="tdHeaderLabel FteHeaderText" style="mafloat: left; ">Non Labor Cost
		</div>
		
		
			<div id="usual1" class="adminTabPanel">
			<ul>
				<li><a class="selected" href="#tab1" id="t1"><span></span>Input
						Parameters </a>
				</li>
				<li><a class="" href="#tab2" id="t2"><span></span>Transition..Travel Cost</a>
				</li>
				<li><a class="" href="#tab3" id="t3"><span></span>Steady State..Travel Cost
					</a>
				</li>
				<li><a class="" href="#tab4" id="t4"><span></span>Other Cost
					</a>
				</li>
			</ul>
			<form:form id="form1" method="POST" commandName="NonLabourCostForm"
				action="/ADM_PRE/solution/saveNLC">
				<!---------------------------------------Tab 1 (Input Params)--------------------------------------------->

				<div style="display: block;" id="tab1"
					class="subminApprovalBodyArea">

					 <jsp:include page="./nlcInputParams.jsp" />
					 <div class="tabsButtonPanel">
						 <div class="TabsCommonButtons" onclick="javascript:showTab('tab2');">Next</div>
					 </div>

				</div>
				<!---------------------------------------Tab 1 (Input Params DIV END from Here)--------------------------------------------->

				<!---------------------------------------Tab 2 (Travel and Cost MS Start from Here -Transition)--------------------------------------------->
				<c:if test="${not empty NonLabourCostForm.reviewFTEForm.monthYears }">
							<c:set var="splittedYrs"
								value="${fn:split(NonLabourCostForm.reviewFTEForm.monthYears, ';')}" />
				</c:if>
				<c:set var="loopLength" value="${fn:length(splittedYrs)}" />
				<c:set var="transloopLength" value="${fn:length(NonLabourCostForm.transMonthYears)}" />
				<c:set var="overlap" value="false"/>
				<div style="display: none;" id="tab2"
					class="subminApprovalBodyArea">
                    <div class="NonLeabrCostscrolBar">
                    <c:if test="${fn:length(NonLabourCostForm.transOnshoreFTE) eq 0}">
                         Transition data not available
                    </c:if>
                     <c:if test="${fn:length(NonLabourCostForm.transOnshoreFTE) gt 0}">
                      <table border="0" cellpadding="0" cellspacing="2"
							class="NonLeabrCostPanel">
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Travel
										Trips &amp; Visa</div>
								</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
									<td align="center"  id="ct_${count.index+1}">
									<b><c:out value="${value}"></c:out></b>
									<input type="hidden" id="" name="transMonthYears[${count.index}]" value="${value}" />
									</td>
								</c:forEach>
								
							</tr>
							<tr align="center" bgcolor="#f7f7f7"
								class="NonLeabrCostPanelheaderColor">
								<td height="25" colspan="${transloopLength+1}" align="left"><b>Managed
										Services - Transition In</b>
								</td>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Onsite Resources
									Short Term</td>
									<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
									<td>
									<input type="text"  id="transOnshoreFTE" readonly colIndex="${count.index+1}"  class="nonLaborReadOnly" name="transOnshoreFTE[${count.index}]" value="${NonLabourCostForm.transOnshoreFTE[count.index]}"/>
									</td>
									</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Round Trips</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td><input type="text"  id="transRoundTripNo"   colIndex="${count.index+1}" class="nonLaborInput" name="transRoundTripNo[${count.index}]" value="${NonLabourCostForm.transRoundTripNo[count.index]}" onblur="calculateIndividualCostTrans(this);" />
								   </td>
							    </c:forEach>
							</tr>
							<%--<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Long Term Visa</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td><input type="text"  id="transVisaNoLTerm"  colIndex="${count.index+1}" class="nonLaborInput" name="transLongTermVisa[${count.index}]" value="${NonLabourCostForm.transLongTermVisa[count.index]}" onblur="calculateIndividualCostTrans(this);"/>
								   </td>
							    </c:forEach>
							</tr>  --%>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Short Term Visa</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td><input type="text"  id="transVisaNoSTerm"   colIndex="${count.index+1}" class="nonLaborInput" name="transShortTermVisa[${count.index}]" value="${NonLabourCostForm.transShortTermVisa[count.index]}" onblur="calculateIndividualCostTrans(this);"/>
								   </td>
							    </c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Calendar Days Onsite</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td><input type="text" id="transCalDaysOnsite" readonly  colIndex="${count.index+1}" class="nonLaborReadOnly" name="transOnsiteCalDays[${count.index}]" value="${NonLabourCostForm.transOnsiteCalDays[count.index]}"/>
								   </td>
							    </c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Working Days Onsite</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td><input type="text"  id="transWrkHrsOnsite"  readonly colIndex="${count.index+1}" class="nonLaborReadOnly"  name="transOnsiteWorkDays[${count.index}]" value="${NonLabourCostForm.transOnsiteWorkDays[count.index]}"/>
								   </td>
							    </c:forEach>
							</tr> 
							<tr align="center" bgcolor="#f7f7f7"
								class="NonLeabrCostPanelheaderColor">
								<td height="25" colspan="${transloopLength+1}" style="text-align:left;"><b>Costs</b></td>
							</tr>
						    <tr align="center" bgcolor="#f7f7f7"
								class="NonLeabrCostPanelheaderColor">
								<td height="25" colspan="${transloopLength+1}" align="left"><b>Managed
										Services Transition In</b>
								</td>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Round Trip Cost</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td>
							       <input type="text" id="transRoundTripCost" readonly  colIndex="${count.index+1}"  class="nonLaborReadOnly" name="transRoundTripCost[${count.index}]" value="${NonLabourCostForm.transRoundTripCost[count.index]}" />
								   </td>
							    </c:forEach>
							</tr>
							<%--<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Long Term Visa Cost</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td>
							       <input type="text" id="transVisaCostLTerm" colIndex="${count.index+1}"  readonly class="nonLaborReadOnly" name="transVisaLTermCost[${count.index}]" value="${NonLabourCostForm.transVisaLTermCost[count.index]}" />
								   </td>
							    </c:forEach>
							</tr>  --%>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Short Term Visa Cost</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td>
							       <input type="text" id="transVisaCostSTerm" colIndex="${count.index+1}" readonly  class="nonLaborReadOnly" name="transVisaSTermCost[${count.index}]" value="${NonLabourCostForm.transVisaSTermCost[count.index]}" />
								   </td>
							    </c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Onsite Hotel Cost</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td>
							       <input type="text" id="transOnsiteHotelCost" colIndex="${count.index+1}" readonly class="nonLaborReadOnly" name="transOnsiteHotelCost[${count.index}]" value="${NonLabourCostForm.transOnsiteHotelCost[count.index]}" />
								   </td>
							    </c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Onsite Per Diem</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td>
							       <input type="text" id="transOnsitePerDiemCost" readonly colIndex="${count.index+1}"  class="nonLaborReadOnly" name="transOnsitePerdiemCost[${count.index}]" value="${NonLabourCostForm.transOnsitePerdiemCost[count.index]}" />
								   </td>
							    </c:forEach>
							</tr>
							<%--<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Onsite FSI Costs (if
									applicable)</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td>
							       <input type="text" id="transOnsiteFSICost" readonly colIndex="${count.index+1}"  class="nonLaborReadOnly" name="transOnsiteFSICost[${count.index}]" value="${NonLabourCostForm.transOnsiteFSICost[count.index]}" />
								   </td>
							    </c:forEach>
							</tr> --%>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Onsite Conveyance Costs</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td>
							       <input type="text" id="transOnsiteConveyanceCost" readonly colIndex="${count.index+1}"  class="nonLaborReadOnly" name="transOnsiteConveyanceCost[${count.index}]" value="${NonLabourCostForm.transOnsiteConveyanceCost[count.index]}" />
								   </td>
							    </c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Mobile Communication Costs</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td>
							       <input type="text" id="transMobileCommCost" readonly colIndex="${count.index+1}"  class="nonLaborReadOnly" name="transMobileCommCost[${count.index}]" value="${NonLabourCostForm.transMobileCommCost[count.index]}" />
								   </td>
							    </c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7"
								class="NonLeabrCostPanelheaderColor">
								<td height="25" align="left"><b>Total</b>
								</td>
								<c:forEach var="value" items="${NonLabourCostForm.transMonthYears}" varStatus="count">
							       <td>
							      <input type="text" id="transTotCost" readonly colIndex="${count.index+1}"  class="nonLaborReadOnly" name="transTotCost[${count.index}]" value="${NonLabourCostForm.transTotCost[count.index]}" />
								   </td>
							    </c:forEach>
							</tr> 
							</table>
							</c:if>
							
                    </div>
                    <div class="tabsButtonPanel">
						 <div class="TabsCommonButtons" onclick="javascript:showTab('tab3');">Next</div>
					 </div>
				</div>

				<!---------------------------------------Tab 2 (Travel and Cost :Transition ends)--------------------------------------------->
			
			<!---------------------------------------Tab 3 (Travel and Cost : Steady State)--------------------------------------------->
                 <div style="display: none;" id="tab3" class="subminApprovalBodyArea">


					<div class="NonLeabrCostscrolBar">
					<table border="0" cellpadding="0" cellspacing="2"
							class="NonLeabrCostPanel">
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Travel
										Trips &amp; Visa</div>
								</td>
								
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<c:choose>
									<c:when test="${not empty value && value != defaultVal}">
										<td align="center"  id="cs_${count.index+1}">
										<b><c:out value="${value}"></c:out></b>
										<input type="hidden" id="" name="ssMonthYears[${count.index}]" value="${value}" />
										</td>
								    </c:when>
								</c:choose>
							 </c:forEach>
								
							</tr>
							<tr align="center" bgcolor="#f7f7f7"
								class="NonLeabrCostPanelheaderColor">
								<td height="25" colspan="${loopLength+1}" align="left"><b>Managed
										Services Steady State</b>
								</td>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Onsite Resources
									Short Term</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
								<input type="text"  id="ssOnshoreFTE" colIndex="${count.index+1}" readonly class="nonLaborReadOnly" name="ssOnshoreFTE[${count.index}]" value="${NonLabourCostForm.ssOnshoreFTE[count.index]}"/>
								</td>
							</c:forEach>  
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Round Trips</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
								<input type="text" id="ssRoundTripNo" colIndex="${count.index+1}" class="nonLaborInput" name="ssRoundTripNo[${count.index}]" value="${NonLabourCostForm.ssRoundTripNo[count.index]}" onblur="calculateIndividualCostSS(this);"/>
								</td>
							</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Short Term Visa</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
						         <input type="text"   id="ssVisaNoSTerm" colIndex="${count.index+1}" class="nonLaborInput" name="ssShortTermVisa[${count.index}]" value="${NonLabourCostForm.ssShortTermVisa[count.index]}" onblur="calculateIndividualCostSS(this);" />		
								</td>
								</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Calendar Days Onsite</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
								<input type="text" readonly id="ssCalDaysOnsite" colIndex="${count.index+1}"  class="nonLaborReadOnly" name="ssOnsiteCalDays[${count.index}]" value="${NonLabourCostForm.ssOnsiteCalDays[count.index]}"/>
								</td>
								</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of Working Days Onsite</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
								<input type="text" readonly id="ssWrkDaysOnsite"  colIndex="${count.index+1}" class="nonLaborReadOnly" name="ssOnsiteWorkDays[${count.index}]" value="${NonLabourCostForm.ssOnsiteWorkDays[count.index]}"/>
								</td>
								</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7"
								class="NonLeabrCostPanelheaderColor">
								<td height="25" colspan="${loopLength+1}" style="text-align:left;"><b>Costs</b></td>
							</tr>
							<tr align="center" bgcolor="#f7f7f7"
								class="NonLeabrCostPanelheaderColor">
								<td height="25" colspan="${loopLength+1}" align="left"><b>Managed
										Services Steady State</b>
								</td>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Round Trip Cost</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
								<input type="text" id="ssRoundTripCost" readonly colIndex="${count.index+1}"  class="nonLaborReadOnly" name="ssRoundTripCost[${count.index}]" value="${NonLabourCostForm.ssRoundTripCost[count.index]}" />
								</td>
							</c:forEach>
								  
							</tr>
							<%--<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Long Term Visa Cost</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<c:choose>
									<c:when test="${not empty value && value != defaultVal}">
										<td align="center" bgcolor="#f7f7f7" id="t_td_${count.index+1}">
										<c:choose>
										<c:when test="${count.index<= transitionCnt-2}">
										</c:when>
										<c:when test="${count.index eq transitionCnt-1 && overlap}">
											<input type="text" id="ssVisaCostLTerm" readonly colIndex="${count.index+1}"  class="nonLaborReadOnly" name="ssVisaLTermCost[${count.index}]" value="${NonLabourCostForm.ssVisaLTermCost[count.index]}" />
										</c:when>
										<c:when test="${(count.index > transitionCnt-1)}">
										 	<input type="text"  id="ssVisaCostLTerm" readonly colIndex="${count.index+1}" class="nonLaborReadOnly" name="ssVisaLTermCost[${count.index}]" value="${NonLabourCostForm.ssVisaLTermCost[count.index]}" />
										</c:when>
										</c:choose>
										</td>
								    </c:when>
								</c:choose>
							 </c:forEach>
							</tr>  --%>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Short Term Visa Cost</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
								<input type="text" id="ssVisaCostSTerm" readonly colIndex="${count.index+1}"  class="nonLaborReadOnly" name="ssVisaSTermCost[${count.index}]" value="${NonLabourCostForm.ssVisaSTermCost[count.index]}" />
								</td>
								</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Onsite Hotel Cost</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
								 <input type="text"  id="ssOnsiteHotelCost" readonly colIndex="${count.index+1}" class="nonLaborReadOnly" name="ssOnsiteHotelCost[${count.index}]" value="${NonLabourCostForm.ssOnsiteHotelCost[count.index]}" />
								</td>
								</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Onsite Per Diem</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
									<input type="text" readonly id="ssOnsitePerDiemCost" colIndex="${count.index+1}"  class="nonLaborReadOnly" name="ssOnsitePerdiemCost[${count.index}]" value="${NonLabourCostForm.ssOnsitePerdiemCost[count.index]}" />			
								</td>
							</c:forEach>
							</tr>
							<%--<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Onsite FSI Costs (if
									applicable)</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<c:choose>
									<c:when test="${not empty value && value != defaultVal}">
										<td align="center" bgcolor="#f7f7f7" id="t_td_${count.index+1}">
										<c:choose>
										<c:when test="${count.index<= transitionCnt-2}">
										</c:when>
										<c:when test="${count.index eq transitionCnt-1 && overlap}">
											<input type="text" readonly id="ssOnsiteFSICost" colIndex="${count.index+1}"  class="nonLaborReadOnly" name="ssOnsiteFSICost[${count.index}]" value="${NonLabourCostForm.ssOnsiteFSICost[count.index]}" />
										</c:when>
										<c:when test="${(count.index > transitionCnt-1)}">
										 	<input type="text"  readonly id="ssOnsiteFSICost" colIndex="${count.index+1}" class="nonLaborReadOnly" name="ssOnsiteFSICost[${count.index}]" value="${NonLabourCostForm.ssOnsiteFSICost[count.index]}" />
										</c:when>
										</c:choose>
										</td>
								    </c:when>
								</c:choose>
							 </c:forEach>
							</tr>  --%>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Onsite Conveyance Costs</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
								<input type="text" readonly id="ssOnsiteConveyanceCost" colIndex="${count.index+1}"  class="nonLaborReadOnly" name="ssOnsiteConveyanceCost[${count.index}]" value="${NonLabourCostForm.ssOnsiteConveyanceCost[count.index]}" />
								</td>
								</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Mobile Communication Costs</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
								 <input type="text" readonly id="ssMobileCommCost" colIndex="${count.index+1}"  class="nonLaborReadOnly" name="ssMobileCommCost[${count.index}]" value="${NonLabourCostForm.ssMobileCommCost[count.index]}" />
								</td>
								</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7"
								class="">
								<td height="25" align="left"><b>Total</b>
								</td>
								<c:forEach var="value" items="${splittedYrs}" varStatus="count">
								<td>
								<input type="text"  readonly id="ssTotCost" colIndex="${count.index+1}" class="nonLaborReadOnly" name="ssTotCost[${count.index}]" value="${NonLabourCostForm.ssTotCost[count.index]}" />
								</td>
								</c:forEach>
							</tr>
						</table>
                       <br/>
						
					</div>
                    <div class="tabsButtonPanel">
						 <div class="TabsCommonButtons" onclick="javascript:showTab('tab4');">Next</div>
					 </div>
				
				</div>
				
				<!-- End of Tab 3 -->
				<!--  Tab 4 Other Cost -->
				<div style="display: none;" id="tab4"
					class="subminApprovalBodyArea">
                    <div class="NonLeabrCostscrolBar">
					 <table border="0" cellpadding="0" cellspacing="2"
							class="NonLeabrCostPanel">
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Other Costs</div>
								</td>
								<c:forEach var="value" items="${NonLabourCostForm.otherMonthYears}" varStatus="count">
								<td align="center"  id="co_${count.index+1}">
										<b><c:out value="${value}"></c:out></b>
										<input type="hidden" id="" name="otherMonthYears[${count.index}]" value="${value}" />
								</td>
								<%--<c:choose>
									<c:when test="${not empty value && value != defaultVal}">
										<td align="center"  id="co_${count.index+1}">
										<b><c:out value="${value}"></c:out></b></td>
								    </c:when>
								</c:choose>  --%>
							 </c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Number of OffShore FTE</td>
								<c:forEach var="value" items="${NonLabourCostForm.otherMonthYears}" varStatus="count">
								<td><input type="text"   id="otherOffShoreFTE" colIndex="${count.index+1}" class="nonLaborReadOnly" name="otherOffShoreFTE[${count.index}]" value="${NonLabourCostForm.otherOffShoreFTE[count.index]}"/></td>
								</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7"
								class="">
								<td height="25" align="left">Delivery plaform costs
								</td>
								<c:forEach var="value" items="${NonLabourCostForm.otherMonthYears}" varStatus="count">
								<td><input type="text" readonly id="msdpCost" colIndex="${count.index+1}"  class="nonLaborReadOnly" name="msdpCost[${count.index}]" value="${NonLabourCostForm.msdpCost[count.index]}" /></td>
								</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left"><div class="leftHeaderSpace">Connectivity
										- Client Env to \\\ Offshore</div>
								</td>
								<c:forEach var="value" items="${NonLabourCostForm.otherMonthYears}" varStatus="count">
								<td>
								<c:if test="${not empty NonLabourCostForm.connectivityToIndia[count.index]}">
								<input type="text" id="connectivityToIndia" colIndex="${count.index+1}"  class="nonLaborInput" 
								name="connectivityToIndia[${count.index}]" value="${NonLabourCostForm.connectivityToIndia[count.index]}"  
								onblur="calculateIndividualCostOther(this);"/>
								</c:if>
								<c:if test="${empty NonLabourCostForm.connectivityToIndia[count.index]}">
								<input type="text" id="connectivityToIndia" colIndex="${count.index+1}"  class="nonLaborInput" 
								name="connectivityToIndia[${count.index}]" value="0"  
								onblur="calculateIndividualCostOther(this);"/>
								</c:if>
								</td>
								</c:forEach>
							</tr>
							<tr align="center" bgcolor="#f7f7f7">
								<td height="25" align="left">Connectivity - \\\ Offshore to
									Dev Centre</td>
								<c:forEach var="value" items="${NonLabourCostForm.otherMonthYears}" varStatus="count">
								<td>
								<c:if test="${not empty NonLabourCostForm.connectivityToDev[count.index]}">
								<input type="text" id="connectivityToDev" colIndex="${count.index+1}"  class="nonLaborInput" name="connectivityToDev[${count.index}]" value="${NonLabourCostForm.connectivityToDev[count.index]}" onblur="calculateIndividualCostOther(this);" />
								</c:if>
								<c:if test="${empty NonLabourCostForm.connectivityToDev[count.index]}">
								<input type="text" id="connectivityToDev" colIndex="${count.index+1}"  class="nonLaborInput" name="connectivityToDev[${count.index}]" value="0" onblur="calculateIndividualCostOther(this);" />
								</c:if>
								
								</td>
								</c:forEach>	
							</tr>
							<tr align="center" bgcolor="#f7f7f7"
								class="">
								<td height="25" align="left"><b>Total Other Cost</b>
								</td>
								<c:forEach var="value" items="${NonLabourCostForm.otherMonthYears}" varStatus="count">
								<td><input type="text" id="otherTotal" colIndex="${count.index+1}"  class="nonLaborReadOnly" name="totalConnectivity[${count.index}]" value="${NonLabourCostForm.totalConnectivity[count.index]}" /></td>
								</c:forEach>
							</tr>
						</table>
						</div>
						<div class="tabsButtonPanel">
						 	<div class="TabsCommonButtons" onclick="saveData();">Save</div>
					 </div>

				</div>
				<!--  End of Tab 4 -->
			</form:form>
		</div>
        </c:if> 


	</div>


</div>
