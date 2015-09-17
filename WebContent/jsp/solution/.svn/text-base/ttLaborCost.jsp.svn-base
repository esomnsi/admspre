<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>

<script type="text/javascript">

	function lc()
	{
		document.forms[0].action = "/ADM_PRE/solution/LaborCost";
		document.forms[0].submit();
	}
	
	
	$(document).ready(function () {
		
		$('#save').click(function() {
			$('#ttLaborcostForm').attr("action",'../solution/saveTtLaborCost');
			$('#ttLaborcostForm').submit();
		});
		});
	
	
	
</script>

<script type="text/javascript" src="../js/wz_tooltip.js"></script>
<div  class="message">
		${message}
		</div>
<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
	-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;">
		
	</div>

<form:form id="ttLaborcostForm" method="post" modelAttribute="ttLaborCostForm">
	<table width="1112" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><div class="tdHeaderLabel FteHeaderText"
					style="mafloat: left; margin-bottom: 5px;">Labor Cost For TnT</div></td>
		</tr>
		<tr>
			<td align="center"><jsp:include page="opportunitySummary.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td align="center" height="5"></td>
		</tr>
		<tr>
			<td align="center"><jsp:include page="./serviceBucket.jsp" /></td>
		</tr>
		<tr>
			<td height="5"></td>
		</tr>
		<tr>
			<td><table width="1112" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td><div class="FteBodyDiv">
								<div class="FTEscrollProperty">
									
									<!-- TODAY STARTS -->
									
									<!-- Time line interval table -->
									<c:set var="index" value="0" />
									<table border="0" cellpadding="0" cellspacing="2"
										class="payroTable" id="1" style="width: 100%;">
										<tr>
											<td bgcolor="#e3e3e3"><strong>Time line Interval</strong></td>
											<td bgcolor="#e3e3e3"><strong>Currency</strong></td>
											<c:forEach items="${timeLineInterval}" varStatus="loop">
												<td align="center" bgcolor="#e3e3e3"><strong>
														${timeLineInterval[loop.index]} </strong></td>
											</c:forEach>
										</tr>
										
										<c:forEach  items="${jobRoleDTOList}" var="jobRole" varStatus="hh">
												<tr>
													
													<td bgcolor="#f7f7f7">${jobRole.role}</td>
													<td bgcolor="#f7f7f7">
														${currencyCode }
													</td>
													<c:forEach var="weekDateInterval" items="${timeLineInterval}" varStatus="loop1">
														<td align="center" bgcolor="#f1f1f1" class="tooltip">
															<c:forEach items="${selectedJobRoleList}" var="item">
															<fmt:formatDate value="${item.weekDate}" pattern="dd-MMM-yy" var="weekDate"/>
																<c:if test="${weekDate == weekDateInterval}">
																	<c:if test="${jobRole.jobRoleId == item.jobRoleDTO.jobRoleId}">
																		<c:forEach items="${rateCardDTOs}" var="rateCardDto">
																		<c:if test="${item.jobRoleDTO.jobRoleId == rateCardDto.jobRoleDTO.jobRoleId}">
																			<c:set var="fteCount" value="${item.ftecount}" />
																			<c:set var="rateForFte" value="${rateCardDto.rateInr}" />
															          		<c:set var="output" value="${fteCount*rateForFte}" />
																			<span class="smfVal">
																			
																			<c:if test="${currencyCode == 'USD'}">
																					<c:set var="usd" value="${exchangeRates.get(0) * output}" />
																					<c:set var="inr" value="${exchangeRates.get(2)}"/>
																					<c:set var="pound" value="${exchangeRates.get(4)}"/>
																					<c:set var="res1" value="${inr * usd}" />
																					<c:set var="res2" value="${pound * usd}" />
																					<c:set var="one" value="INR"/>
																					<c:set var="two" value="GBP"/>
																				
																			</c:if>
																			
																			<c:if test="${currencyCode == 'GBP'}">
																			
																				<c:set var="gbp" value="${exchangeRates.get(1) * output}" />
																				<c:set var="inr" value="${exchangeRates.get(3)}" />
																				<c:set var="dollar" value="${exchangeRates.get(5)}" />
																				<c:set var="res1" value="${inr * gbp}" />
																				<c:set var="res2" value="${dollar * gbp}" />
																				<c:set var="one" value="INR" />
																				<c:set var="two" value="USD" />
																			</c:if>
																			<c:if test="${currencyCode == 'INR'}">
																				<c:set var="dollar" value="${exchangeRates.get(0)}" />
																				<c:set var="pound" value="${exchangeRates.get(1)}" />
																				<c:set var="res1" value="${dollar * output}" />
																				<c:set var="res2" value="${pound * output}" />
																				<c:set var="one" value="USD" />
																				<c:set var="two" value="GBP" />
																			</c:if>
																	<fmt:formatNumber var="r1" value="${res1}" maxFractionDigits="2" />
																	<fmt:formatNumber var="r2" value="${res2}" maxFractionDigits="2" />
																	
																	<p style="width: 200px;">${one}: ${r1} | ${two }: ${r2}</p>
																		
																		
																		<c:if test="${currencyCode == 'GBP'}">
																			<fmt:formatNumber var="rate" value="${gbp}"
																		maxFractionDigits="2" />
																		
																		<input type="hidden" id="ttLaborCostList[${index}].rate" 
											name="ttLaborCostList[${index}].rate"  readonly tabIndex="-1"
											class="textBoxSCalculateCenter" value="${rate}">
																		
																		${rate}
																		</c:if>
																		<c:if test="${currencyCode == 'USD'}">
																		<fmt:formatNumber var="rate" value="${usd}"
																		maxFractionDigits="2" />
																		<input type="hidden" id="ttLaborCostList[${index}].rate" 
											name="ttLaborCostList[${index}].rate"  readonly tabIndex="-1"
											class="textBoxSCalculateCenter" value="${rate}">
																		${rate}
																		</c:if>
																		<c:if test="${currencyCode == 'INR'}">
																		<fmt:formatNumber var="rate" value="${output}"
																		maxFractionDigits="2" />
																		<input type="hidden" id="ttLaborCostList[${index}].rate" 
											name="ttLaborCostList[${index}].rate"  readonly tabIndex="-1"
											class="textBoxSCalculateCenter" value="${rate}">
																		${rate}
																		</c:if>
															          		</span>
														          		</c:if>
														          		<input type="hidden" id="ttLaborCostList[${index}].weekDate" 
											name="ttLaborCostList[${index}].weekDate"  readonly tabIndex="-1"
											class="textBoxSCalculateCenter" value="${weekDateInterval}">
														          		</c:forEach>
																	</c:if>
																</c:if>
															</c:forEach>
															
														</td>
														<c:set var="index" value="${index+1}" />
													</c:forEach>
												</tr>
											</c:forEach>										
									</table>
									
									<!-- Time line interval table -->
									
									
									
									<!-- TODAY ENDS -->
									
								</div>
							
							</div></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<table align="right">
	<tr>
    	<td align="right"><a id="prev" class="portfolioButtons" href="/ADM_PRE/solution/labourCost" >Previous</a> 
    	<a id="save" class="portfolioButtons" >Save</a>
    	</td>
    </tr>
</table>
</form:form>