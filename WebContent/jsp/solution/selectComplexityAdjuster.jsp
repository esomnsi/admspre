<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="../js/wz_tooltip.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Complexity TC Adjuster</title>
<script src="../js/complexityAdjuster.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.8.2.js"></script>
<script>
$(document).ready(function() {
		computedComplexityLoad();
		 $('form').bind('submit', function() {
	    	$.each($('.disCom'), function(){
	    	   $(this).removeAttr('disabled');
	    	});
	    }); 
	});

$().ready(function () {
	  $('#save').click(function() {
	  	$('#scaPage').attr("action",'../solution/savePageComplexityAdjuster');
	  	$('#scaPage').submit();
	  });
	  $('#next').click(function() {
		   var url = "<%=request.getContextPath()%>/solution/productivityLevers";    
	    	$(location).attr('href',url);
	  
	  });
	  $('#prev').click(function() {
	   	$('#scaPage').attr("action",'../solution/APAnalysis');
	   	$('#scaPage').submit();
	  });
	  $('#reset').click(function() {
	   	$('#scaPage').attr("action",'../solution/complexityAdjuster');
	 	    $("#scaPage").cancelSubmit = true;
	   	$('#scaPage').submit();
	  });
});
</script>
<style type="">
#zero {
	width: 100%;
}

#one {
	width: 50%;
	float: left;
}

#two {
	width: 50%;
	float: left;
}

#three {
	width: 100%;
	float: left;
}

 .capInputClass{
	width:80px;
	outline:none;
	text-align: right;
	float:left;
	font:normal 11px Arial, Helvetica, sans-serif;
	height:18px;
	-webkit-border-radius: 5px 5px 5px 5px;
	-moz-border-radius: 5px 5px 5px 5px;
	border-radius: 5px 5px 5px 5px;
	-moz-box-shadow: inset 0 1px 2px #dbdbdb;
	-webkit-box-shadow: inset 0 1px 2px #dbdbdb;
	 margin-left:0px;
	 background-color: #EDFFDA;
    border: 1px solid #BEEC8E;
    box-shadow: 0 1px 2px #DBDBDB inset;
    color: #666666;
	 
}
</style>
</head>

		<c:set var="str" value="display:none;"></c:set>
			<c:if test="${not empty message}">
				<c:set var="str" value="display:block;"></c:set>
			</c:if>
			<div id="messageDiv" class="actionPerformedMessage" style="${str}">
					${message}
			</div>
			<c:set var="strErr" value="display:none;"></c:set>
			<c:if test="${not empty messageError}">
				<c:set var="strErr" value="display:block;"></c:set>
			</c:if>
			<div id="errorDiv" class="errorMessageDisp" style="${strErr}">
				${messageError}
			</div>
			<div style="margin-bottom:10px;">
			<jsp:include page="./serviceBucket.jsp" />
			</div>
<form:form name="scaPage" action="savePage" method="post" modelAttribute="complexityAdjusterForm" id="scaPage">
	<div id="mainBody" class="bodyCss">

				<table width="1112" border="0" cellspacing="0" cellpadding="0">
								
				
					<tr>
						<td class="tdHeaderLabel" colspan="2"><b>Adjust Complexity Details</b></td>
					</tr>
					<tr>
						<td height="5" colspan="2"></td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<div id="bodyDiv" class="mainBodyDiv1">
								<jsp:include page="opportunitySummary.jsp"></jsp:include>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center" height="5" colspan="2"></td>
					</tr>
					<tr bgcolor="#fafafa">
						<td colspan="2">
							<table>
								<tr>
		<%--  <td width="25%"  align="center" class="tdLabelLeft" bgcolor="#efefef">Utilization</td>
	    <td width="5%" class="tdLabelLeft" >Year</td>
	    <td class="tdLabelRight">
	      <input type="text" id="utilizationPerYear"  value="2040"  class="textBoxSNumric"  maxlength="6" onkeypress="return isDecimal(event);" onblur="setUtilizationPerYear();" >
	  	</td>
	    <td width="5%" class="tdLabelLeft">Month</td>
	    <td class="tdLabelRight">
	      <input type="text" id="utilizationPerMonth" value="170" class="textBoxSNumric" maxlength="6" onkeypress="return isDecimal(event);" />
	    </td>
	    <td width="5%" class="tdLabelLeft">Week</td>
	    <td>
	      <input type="text" id="utilizationPerWeek" value="42.5" class="textBoxSNumric" maxlength="5" onkeypress="return isDecimal(event);" />
	  	</td>
	    <td width="5%" class="tdLabelLeft">Day</td>
	    <td>
	      <input type="text" id="utilizationPerDay" value="8.5" class="textBoxSNumric" maxlength="5" onkeypress="return isDecimal(event);" />
	   	</td> --%>
								</tr>
							</table>
						</td>
			  		</tr>
			  		<tr>
						<td align="center" height="5" colspan="2"></td>
					</tr>
					
					<tr>
						<td height="5" colspan="2"></td>
					</tr>			
					<tr>
						<td width="60%">
							<table width="100%" border="0">
								<tr>
									<td class="tdHeaderLabel" colspan="9">Adjust Complexity</td>
								</tr>
								<tr>
									<td align="center" class="tdTableHead" colspan="4"></td>
									<td align="center" class="tdTableHead" colspan="3">Rating</td>
									<td align="center" class="tdTableHead" colspan="2">Weightage</td>
								</tr>
								<tr> 
									<td class="tdTableHead" colspan="7"></td>
									<td class="tdTableHead" colspan="1">Default</td>
									<td class="tdTableHead" colspan="1">Override</td>
								</tr>
						<c:if test="${empty complexityAdjusterForm}">
						
								<tr>
									<td class="tdLabel" colspan="5">Skill/module/functional area adjustor</td>
									<td  colspan="2">
										<form:select path="skillRating" Class="skillRating inputtextTabs" onchange="SummaryOfComplexityAdjustors(event)">
											<form:option value="" label="--Select--" />
											<form:options items="${complexityList}" />
										</form:select>
									</td>
									
									<td align="right" class="tdTableSubHeadCenter">4
										<input type="hidden" name="solutionComplexityId"></input>
									</td>
									<td align="center" class="smfVal"><input type="text"
										name="skillWeightage" id="skillWeightage"
										onblur="calculateOverrideValue(this);SummaryOfComplexityAdjustors(event);"
										onfocus="emptyFieldForInput(this)" 
										onkeypress="return isDecimalField(this,event);return false;"
										value="" class="capInputClass"></input></td>
								</tr>
								
								<tr>
									<td class="tdLabel" colspan="5">Region Adjustor</td>
									<td  colspan="2">
										<form:select path="regionRating" Class="regionRating inputtextTabs" onchange="SummaryOfComplexityAdjustors(event)">
											<form:option value="" label="--Select--" />
											<form:options items="${complexityList}" />
										</form:select>
									</td>
									<td align="right" class="tdTableSubHeadCenter">2</td>
									<td align="center" class="regionAdjVal"><input type="text"
										name="regionWeightage" id="regionWeightage" 
										onblur="calculateOverrideValue(this);SummaryOfComplexityAdjustors(event);"
										onfocus="emptyFieldForInput(this)"
										onkeypress="return isDecimalField(this,event);return false;"
										value="" class="capInputClass"></input></td>
								</tr>
								<tr>
									<td class="tdLabel" colspan="5">Audit Adjustor</td>
									<td  colspan="2">
										<form:select path="auditRating" Class="auditRating inputtextTabs" onchange="SummaryOfComplexityAdjustors(event)">
											<form:option value="" label="--Select--" />
											<form:options items="${complexityList}" />
										</form:select>
									</td>
									<td align="right" class="tdTableSubHeadCenter">1</td>
									<td align="center" class="auditAdjVal"><input type="text"
										id="auditWeightage" name="auditWeightage"
										onblur="calculateOverrideValue(this);SummaryOfComplexityAdjustors(event);"
										onfocus="emptyFieldForInput(this)"
										onkeypress="return isDecimalField(this,event);return false;"
										value="" class="capInputClass"></input></td>
										
								</tr>
								<tr>
									<td class="tdLabel" colspan="5">SLA Adjustor</td>
									<td  colspan="2">
										<form:select path="slarating" Class="slarating inputtextTabs" onchange="SummaryOfComplexityAdjustors(event)">
											<form:option value="" label="--Select--" />
											<form:options items="${complexityList}" />
										</form:select>
									</td>
									<td align="right" class="tdTableSubHeadCenter">3</td>
									<td align="center" class="SLAAdjVal"><input type="text"
										id="slaweightage" name="slaweightage"
										onblur="calculateOverrideValue(this);SummaryOfComplexityAdjustors(event);"
										onkeypress="return isDecimalField(this,event);return false;"
										onfocus="emptyFieldForInput(this)"
										value="" class="capInputClass"></input></td> 
									
								</tr>
								<tr>
									<td align="center" colspan="5">&nbsp;</td>
									<td align="right" class="tdTableSubHeadCenter" colspan="2">&nbsp;Total:</td>
									<td class="tdTableSubHeadCenter">10</td>
									<td align="center" class="tdTableSubHeadCenter calc"></td>
								</tr>
								<tr>
									<td class="tdTableHead" colspan="5"><b>Computed Complexity Adjustors Rating  :</b>
										<input type="hidden" name="computedComplexity" id="computedComplexity" value=""/>
									</td>
									<td class="tdTableHead" id="computedComplexityHtmlValue" colspan="4">
									</td>
								</tr>
								 
						</c:if>		
						
					
			<c:if test="${not empty complexityAdjusterForm}">
			
								<tr>
									<td class="tdLabel" colspan="5">Skill/module/functional area adjustor</td>
									
									<td colspan="2">
										<form:select path="solutionComplexity.skillRating" Class="skillRating inputtextTabs" onchange="SummaryOfComplexityAdjustors(event)">
											<form:option value="" label="--Select--" />
											<c:forEach var="item" items="${complexityList}">
												<c:choose>
													<c:when test="${complexityAdjusterForm.solutionComplexity.skillRating==item.key}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select>
									</td>
									<td align="right" class="tdTableSubHeadCenter">4
										<input type="hidden" name="solutionComplexity.solutionComplexityId" value="${complexityAdjusterForm.solutionComplexity.solutionComplexityId}"></input>
									</td>
									<td align="center" class="smfVal">
										<input type="text" name="solutionComplexity.skillWeightage" id="skillWeightage"
										onblur="calculateOverrideValue(this);SummaryOfComplexityAdjustors(event);"
										onfocus="emptyFieldForInput(this)"
										onkeypress="return isDecimalField(this,event);return false;"
										value="${complexityAdjusterForm.solutionComplexity.skillWeightage}" class="capInputClass"></input>
									</td>	
								</tr>
								 
								<tr>
									<td class="tdLabel" colspan="5">Region Adjustor</td>
									
									<td  colspan="2">
										<form:select path="solutionComplexity.regionRating" Class="regionRating inputtextTabs"  onchange="SummaryOfComplexityAdjustors(event)">
											<form:option value="0" label="--Select--" />
											<c:forEach var="item" items="${complexityList}">
												<c:choose>
													<c:when test="${complexityAdjusterForm.solutionComplexity.regionRating==item.key}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select>
									</td>
									<td align="right" class="tdTableSubHeadCenter">2</td>
									<td align="center" class="regionAdjVal"><input type="text"
										name="solutionComplexity.regionWeightage" id="regionWeightage" 
										onblur="calculateOverrideValue(this);SummaryOfComplexityAdjustors(event);"
										onfocus="emptyFieldForInput(this)"
										onkeypress="return isDecimalField(this,event);return false;"
										value="${complexityAdjusterForm.solutionComplexity.regionWeightage}" class="capInputClass"></input></td>
										
								</tr>
								<tr>
									<td class="tdLabel" colspan="5">Audit Adjustor</td>
									<td  colspan="2">
										<form:select path="solutionComplexity.auditRating" Class="auditRating inputtextTabs"  onchange="SummaryOfComplexityAdjustors(event)">
											<form:option value="0" label="--Select--" />
											<c:forEach var="item" items="${complexityList}">
												<c:choose>
													<c:when test="${complexityAdjusterForm.solutionComplexity.auditRating==item.key}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select>
									</td>
									<td align="right" class="tdTableSubHeadCenter">1</td>
									<td align="center" class="auditAdjVal"><input type="text"
										id="auditWeightage" name="solutionComplexity.auditWeightage"
										onblur="calculateOverrideValue(this);SummaryOfComplexityAdjustors(event);"
										onfocus="emptyFieldForInput(this)"
										onkeypress="return isDecimalField(this,event);return false;"
										value="${complexityAdjusterForm.solutionComplexity.auditWeightage}" class="capInputClass"></input></td>
								</tr>
								<tr>
									<td class="tdLabel" colspan="5">SLA Adjustor</td>
									<td  colspan="2">
										<form:select path="solutionComplexity.slarating" Class="slarating inputtextTabs"  onchange="SummaryOfComplexityAdjustors(event)">
											<form:option value="0" label="--Select--" />
											<c:forEach var="item" items="${complexityList}">
												<c:choose>
													<c:when test="${complexityAdjusterForm.solutionComplexity.slarating==item.key}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select>
									</td>
									<td align="right" class="tdTableSubHeadCenter">3</td>
									<td align="center" class="SLAAdjVal"><input type="text"
										id="slaweightage" name="solutionComplexity.slaweightage"
										onblur="calculateOverrideValue(this);SummaryOfComplexityAdjustors(event);"
										onkeypress="return isDecimalField(this,event);return false;"
										onfocus="emptyFieldForInput(this)"
										value="${complexityAdjusterForm.solutionComplexity.slaweightage}" class="capInputClass"></input></td> 
								</tr>
								<tr>
									<td align="center" colspan="5">&nbsp;</td>
									<td align="right" class="tdTableSubHeadCenter" colspan="2">&nbsp;Total:</td>
									<td class="tdTableSubHeadCenter">10</td>
									<td align="center" class="tdTableSubHeadCenter calc"></td>
								</tr>
								
								<tr>
									<td class="tdTableHead" colspan="5"><b>Computed Complexity Adjustors Rating  :</b>
										<input type="hidden" name="solutionComplexity.computedComplexity" id="computedComplexity" value="${complexityAdjusterForm.solutionComplexity.computedComplexity}"/>
									</td>
									<td class="tdTableHead" id="computedComplexityHtmlValue" colspan="4">
									</td>
								</tr>
								
								
		</c:if>
					<!-- =====================end================================================= -->	
							</table>

						</td>
						<td  width="40%" valign="top">
						
							<table width="100%" border="0">

					<c:if test="${empty complexityAdjusterForm}">	
								<tr>
									<td class="tdHeaderLabel" colspan="3">Prob Res Complexity &
										Effort Contingency</td>
								</tr>
								<tr>
									<td class="tdTableHead"></td>
									<td class="tdTableHead"><b>Default</b></td>
									<td class="tdTableHead"><b>Overriden</b></td>
								</tr>
								<tr>
									<td class="tdTableHead" colspan="3"></td>
								</tr>								
								<tr>
									<td class="tdLabel">Rating</td>
									<td class="tdInputBox" id="computedComplexityA"></td>
									<td class="tdInputBox">
										<select name="overriddenComplexity" id="overriddenComplexity" class="inputtextTabs" onclick="OverAllComplexity(this)">
												<option>--Select--</option>
												<option value="1">Simple</option>
												<option value="2">Medium</option>
												<option value="3">Complex</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="tdLabel">Complexity Adjustor</td>
									<td class="tdInputBox" id="defaultComplexityAdjustorValue"></td>
									<td><input type="text" onkeypress="return isDecimalField(this,event);return false;"
										name="complexityAdjustor" id="complexityAdjustor"
										class="capInputClass" onblur="adjustedValueAll(this)"></input> %</td>
								</tr>
						</c:if>
						<!-- if data exsist start from here -->		
						<c:if test="${not empty complexityAdjusterForm}">	
								<tr>
									<td class="tdHeaderLabel" colspan="3">Prob Res Complexity &
										Effort Contingency</td>
								</tr>
								<tr>
									<td class="tdTableHead"></td>
									<td class="tdTableHead"><b>Default</b></td>
									<td class="tdTableHead"><b>Overriden</b></td>
								</tr>
								
								<tr>
									<td colspan="3"></td>
								</tr>
															
								<tr>
									<td class="tdLabel">Rating</td>
									<td class="tdInputBox" id="computedComplexityA"></td>
									<td>
										<form:select path="solutionComplexity.overriddenComplexity" class="inputtextTabs" onclick="OverAllComplexity(this)">
											<form:option value="0" label="--Select--" />
											<c:forEach var="item" items="${complexityList}">
												<c:choose>
													<c:when test="${complexityAdjusterForm.solutionComplexity.overriddenComplexity==item.key}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select>
									</td>
								</tr>
								<tr>
									<td class="tdLabel">Complexity Adjustor</td>
									<td class="tdInputBox" id="defaultComplexityAdjustorValue"></td>
									<td><input type="text" onkeypress="return isDecimalField(this,event);return false;"
										name="solutionComplexity.complexityAdjustor" id="complexityAdjustor" value="${complexityAdjusterForm.solutionComplexity.complexityAdjustor}"
										class="capInputClass" onblur="adjustedValueAll(this)"></input>%</td>
								</tr>
						
						
						</c:if>
						
					<!-- =====================end================================================= -->		
								<tr>
									<!-- <td class="tdLabel">Effort Contingency</td>
									<td class="tdInputBox Contingency"> 15 %</td>
									<td class="tdInputBox"></td> -->
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>

 
			<div id="mainBody" class="bodyCss">
	
				<table border="0" width="1112">
					<tr>
						<td>
							<table border="0" width="100%">
								<tr>
									<td class="tdHeaderLabel" colspan="15">Apply Application Portfolio Complexity</td>
								</tr>
								<tr>
									<td class="tdTableHead" colspan="2" style="width: 23%">In-Scope Services</td>
									<td class="tdTableHead" colspan="2" style="width: 10%">Pre-Adjusted</td>
									<td class="tdTableHead" colspan="2" style="width: 20%" nowrap="nowrap">Assessed Application Complexity</td>
									<td class="tdTableHead" colspan="2" style="width: 10%">Adjust FTE %</td>
									<td class="tdTableHead" colspan="3" style="width: 27%" align="center">Effort Contingency</td>
									<td class="tdTableHead" colspan="4" style="width: 10%">Adjusted</td>
								</tr>
								<tr>
									<td class="tdTableHead" colspan="2"></td>
									<td class="tdTableHead">FTE</td>
									<td class="tdTableHead">Hours</td>
									<td class="tdTableHead">Default</td>
									<td class="tdTableHead">Override</td>
									<td class="tdTableHead">Default</td>
									<td class="tdTableHead">Override</td>
									<td class="tdTableHead">Deal Characteristics</td>
									<td class="tdTableHead">Effort</td>
									<td class="tdTableHead">Cost</td>
									<td class="tdTableHead" colspan="2" style="width: 5%">FTE</td>
									<td class="tdTableHead" colspan="2">Hours</td>
								</tr>
<c:if test="${not empty listOfServiceScope}">			
	<c:forEach var="listOfServiceScope" items="${listOfServiceScope}" varStatus="i">
		<tr class="tdOddRow">
			<td class="serviceName" id="${listOfServiceScope.serviceScopeId}" colspan="2">${listOfServiceScope.serviceScopeName}</td>
				<c:if test="${empty complexityAdjusterForm.apacomplexityList}">
					<td class="actualFTE">0.0</td>
						<td background="#EDFFDA">
							<input type="text" name="actualHrsD" disabled="disabled" class="capInputClass actualHrsD" />
						</td>
						<td>
							<select name="complexityAssessed" disabled="disabled" class="inputtextTabs">
								<option value="0" selected="selected">--Select--</option>
							</select>
						</td>
						<td>
							<select name="complexityOverride" disabled="disabled" Class="inputtextTabs">
								<option value="0" selected="selected">--Select--</option>
							</select>
						</td>
						<td class="tdTableSubHeadCenter">  0 % </td>
						<td class="overriddenFTE">
							<input type="text" name="ftepercentage" disabled="disabled" class="capInputClass"/>
						</td>
						<td>
							<select Class="inputtextTabs" name="dealCharacteristicsId" disabled="disabled">
									<option value="0" selected="selected">--Select--</option>
							</select>
						</td>
						<td>
							<input type="text"  name="effort" disabled="disabled" class="txtinputComonfieldRight effort" />
						</td>
						<td>
							<input type="text"  name="cost" disabled="disabled" class="txtinputComonfieldRight cost" />
						</td>
						<td colspan="2">
							<input type="text"  name="adjFTE" disabled="disabled" class="txtinputComonfieldRight adjFTE" />
						</td>
						<td colspan="2">
							<input type="text"  name="adjHour" disabled="disabled" class="txtinputComonfieldRight adjHour" />
						</td>
				</c:if>
				<c:if test="${not empty complexityAdjusterForm.apacomplexityList}">	
	<c:set var='APAExsist'  value="false"/>			
				<c:forEach var="apacomplexityList" items="${complexityAdjusterForm.apacomplexityList}" varStatus="status">
	<c:set var="serviceIdAndOppScopeId" scope="session" value="${apacomplexityList.opportunityScope.opportunityScopeId};${listOfServiceScope.serviceScopeId}"/>
			
					<c:forEach var="startUpFTEMap" items="${complexityAdjusterForm.startUpFTEMap}">
					
	<c:set var="serviceIdAndOppScopeIdForNONAPA" scope="session" value="${startUpFTEMap.value};${listOfServiceScope.serviceScopeId}"/>				
	
	
						
						<c:if test="${startUpFTEMap.key eq serviceIdAndOppScopeId}">
						
							<td class="actualFTE"><fmt:formatNumber  value="${(startUpFTEMap.value)}"  maxFractionDigits="2" /></td>
							<td>
									<input type="text" name="actualHrs" class="capInputClass actualHrs" contenteditable="false" onchange="InputValueValidation(this,6123);"/>
							</td>
							<td class="actualAPA">
								<form:select path="apacomplexityList[${status.index}].complexityAssessed" Class="inputtextTabs disCom" disabled="true">
									<form:option value="" label="--Select--" />
									<c:forEach var="item" items="${complexityList}">
										<c:choose>
											<c:when test="${apacomplexities.complexityAssessed==item.key}">
												<form:option selected="true" value="${item.key}">${item.value}</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${item.key}">${item.value}</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select>
							</td>
							<td>
								<form:select path="apacomplexityList[${status.index}].complexityOverride" Class="inputtextTabs">
									<form:option value="0" label="--Select--" />
									<c:forEach var="item" items="${complexityList}">
										<c:choose>
											<c:when test="${apacomplexities.complexityOverride==item.key}">
												<form:option selected="true" value="${item.key}">${item.value}</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${item.key}">${item.value}</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select>
							</td>
							<td class="tdTableSubHeadCenter">  0 %
								<input type="hidden" name="apacomplexityList[${status.index}].apacomplexityId" value="${apacomplexityList.apacomplexityId}"></input>
								<input type="hidden" name="apacomplexityList[${status.index}].opportunityScope.opportunityScopeId" value="${apacomplexityList.opportunityScope.opportunityScopeId}"></input>
							</td>
							<td class="overriddenFTE">
								<input type="text" id="overriddenFTE" name="apacomplexityList[${status.index}].ftepercentage" onblur="adjustedValue(event,this,'${listOfServiceScope.serviceScopeId}')" 
								value="${apacomplexityList.ftepercentage }" onchange="InputValueValidation(this,100);" onkeypress="return isDecimalField(this,event);return false;" class="capInputClass"/>
							</td>
				<c:if test="${listOfServiceScope.serviceScopeId < 30 }">
							<td>
								<form:select path="apacomplexityList[${status.index}].dealCharacteristics.dealCharacteristicsId" Class="inputtextTabs" onchange="dealChange(this,'${complexityAdjusterForm.serviceDeliveryYear}','${listOfServiceScope.serviceScopeId}');adjustedValue(event,this,'${listOfServiceScope.serviceScopeId}')">
									<form:option value="0" label="--Select--" />
									
			<c:if test="${complexityAdjusterForm.serviceDeliveryYear == 1 && listOfServiceScope.serviceScopeId <= 3}">
				<c:set var='DealIdentifier'  value="1"/>	
			</c:if>
			<c:if test="${complexityAdjusterForm.serviceDeliveryYear == 1 && listOfServiceScope.serviceScopeId > 3 && listOfServiceScope.serviceScopeId < 30}">
				<c:set var='DealIdentifier'  value="3"/>	
			</c:if>
			<c:if test="${complexityAdjusterForm.serviceDeliveryYear == 2 && listOfServiceScope.serviceScopeId <=3}">
				<c:set var='DealIdentifier'  value="2"/>	
			</c:if>
			<c:if test="${complexityAdjusterForm.serviceDeliveryYear == 2 && listOfServiceScope.serviceScopeId > 3 && listOfServiceScope.serviceScopeId < 30}">
				<c:set var='DealIdentifier'  value="4"/>	
			</c:if>
									
									<c:forEach var="item" items="${complexityAdjusterForm.dealCharacteristicsList}">
								
										<c:choose>
											<c:when test="${not empty apacomplexityList.dealCharacteristics.dealCharacteristicsId && apacomplexityList.dealCharacteristics.dealCharacteristicsId==item.dealCharacteristicsId && DealIdentifier==item.dealIdentifier}">
												<form:option selected="true" value="${item.dealCharacteristicsId}">${item.dealCharacteristicsName}</form:option>
											</c:when>
											<c:when test="${DealIdentifier==item.dealIdentifier}">
												<form:option value="${item.dealCharacteristicsId}">${item.dealCharacteristicsName}</form:option>
											</c:when>
											<%-- <c:otherwise>
												<form:option value="${item.dealCharacteristicsId}">${item.dealCharacteristicsName}</form:option>
											</c:otherwise> --%>
										</c:choose>
										
									</c:forEach>
								</form:select>
							</td>
			<c:if test="${not empty apacomplexityList.dealCharacteristics.dealCharacteristicsId}">
				<c:set var='dealCharacteristicExsist'  value="false"/>		
				<c:forEach var="item" items="${complexityAdjusterForm.dealCharacteristicsList}">
				
					<c:choose>
						<c:when test="${not empty apacomplexityList.dealCharacteristics.dealCharacteristicsId && apacomplexityList.dealCharacteristics.dealCharacteristicsId==item.dealCharacteristicsId}">
							<c:set var='dealCharacteristicExsist'  value="true"/>
							<td>
								<input type="text"  name="effort" readonly="readonly" class="txtinputComonfieldRight effort" value="${item.effort }"/>
							</td>
								
							<td>
								<input type="text"  name="cost" disabled="disabled" class="txtinputComonfieldRight cost" value="${item.cost }"/>
							</td>
						</c:when>
						<%-- <c:when test="${not empty apacomplexityList.dealCharacteristics.dealCharacteristicsId && fn:contains(item, apacomplexityList.dealCharacteristics.dealCharacteristicsId)==false}">
							<td>
								<input type="text"  name="effort" readonly="readonly" class="txtinputComonfieldRight effort" />
							</td>
								
							<td>
								<input type="text"  name="cost" disabled="disabled" class="txtinputComonfieldRight cost" />
							</td>
						</c:when> --%>
					</c:choose>
				</c:forEach>
			</c:if>

					<c:if test="${dealCharacteristicExsist eq false || empty apacomplexityList.dealCharacteristics.dealCharacteristicsId}">
						<td>
							<input type="text"  name="effort" readonly="readonly" class="txtinputComonfieldRight effort" />
						</td>
							
						<td>
							<input type="text"  name="cost" disabled="disabled" class="txtinputComonfieldRight cost" />
						</td>
					</c:if>
				</c:if>
				<c:if test="${listOfServiceScope.serviceScopeId > 29 }">
						<td>
							<form:select path="apacomplexityList[${status.index}].dealCharacteristics.dealCharacteristicsId" Class="inputtextTabs disCom" disabled="true">
									<form:option value="0" label="--Select--"/>
							</form:select>
						</td>
						<td>
							<input type="text"  name="effort" disabled="disabled" class="txtinputComonfieldRight effort" />
						</td>
						<td>
							<input type="text"  name="cost" disabled="disabled" class="txtinputComonfieldRight cost" />
						</td>
				</c:if>
					<td colspan="2">
						<input type="text" name="apacomplexityList[${status.index}].adjustedFte" readonly="readonly" class="adjustedFTE txtinputComonfieldRight" value="${apacomplexityList.adjustedFte}"/>
					</td>
					<td colspan="2">
						<input type="text" name="apacomplexityList[${status.index}].adjustedHrs" readonly="readonly" class="adjustedHrs txtinputComonfieldRight" value="${apacomplexityList.adjustedHrs}"/>
					</td>
				</c:if>
				<c:if test="${startUpFTEMap.key eq serviceIdAndOppScopeIdForNONAPA && APAExsist eq false}">
					<c:set var='APAExsist'  value="true"/>	
						<td class="actualFTE">0.0</td>
						<td>
							<input type="text" name="actualHrsD" disabled="disabled" class="capInputClass actualHrsD" />
						</td>
						<td>
							<select name="complexityAssessed" disabled="disabled" class="inputtextTabs">
								<option value="0" selected="selected">--Select--</option>
							</select>
						</td>
						<td>
							<select name="complexityOverride" disabled="disabled" Class="inputtextTabs">
								<option value="0" selected="selected">--Select--</option>
							</select>
						</td>
						<td class="tdTableSubHeadCenter">  0 % </td>
						<td class="overriddenFTE">
							<input type="text" name="ftepercentage" disabled="disabled" class="capInputClass"/>
						</td>
						<td>
							<select Class="inputtextTabs" name="dealCharacteristicsId" disabled="disabled">
									<option value="0" selected="selected">--Select--</option>
							</select>
						</td>
						<td>
							<input type="text"  name="effort" disabled="disabled" class="txtinputComonfieldRight effort" />
						</td>
						<td>
							<input type="text"  name="cost" disabled="disabled" class="txtinputComonfieldRight cost" />
						</td>
						<td colspan="2">
							<input type="text"  name="adjFTE" disabled="disabled" class="txtinputComonfieldRight adjFTE" />
						</td>
						<td colspan="2">
							<input type="text"  name="adjHour" disabled="disabled" class="txtinputComonfieldRight adjHour" />
						</td>								
				</c:if>
				
				</c:forEach>
			</c:forEach> 
			</c:if>
		</tr>
	</c:forEach>
</c:if>
								<tr class="tdOddRow">
									<td class="" colspan="15"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		<div style="float:right;width:55%;">
			<div id="next" class="TabsCommonButtons">Next</div> 
			<c:if test="${hasEditSolAccess!='false'}">
					<div id="save" class="TabsCommonButtons">Save</div>
					<div id="reset" class="TabsCommonButtons">Reset</div> 
		      </c:if>
           	<div id="prev" class="TabsCommonButtons">Previous</div>
		</div>       
</form:form>
