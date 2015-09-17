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
	
</script>

<script type="text/javascript" src="../js/wz_tooltip.js"></script>

<form:form id="form1" action="">
	<table width="1112" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><div class="tdHeaderLabel FteHeaderText"
					style="mafloat: left; margin-bottom: 5px;">Review FTE For TnT</div></td>
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
			<td height="5">
				
			</td>
		</tr>
		
		<tr>
			<td><table width="1112" border="0" cellspacing="0"
					cellpadding="0">

				
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
										<c:forEach  items="${jobRoleDTOList}" var="jobRole" varStatus="hh">
												<tr>
													<td bgcolor="#f7f7f7">${jobRole.role}</td>
													
													<c:forEach var="weekDateInterval" items="${timeLineInterval}" varStatus="loop1">
														<td align="center" bgcolor="#f1f1f1">
															<c:forEach items="${selectedJobRoleList}" var="item">
															<fmt:formatDate value="${item.weekDate}" pattern="dd-MMM-yy" var="weekDate"/>
															
																<c:if test="${weekDate == weekDateInterval}">
																	<c:if test="${jobRole.jobRoleId == item.jobRoleDTO.jobRoleId}">
																		<span class="smfVal">
														          			${item.ftecount}
														          		</span>
																</c:if>
																</c:if>
															</c:forEach>
															
														</td>
													</c:forEach>
												</tr>
											</c:forEach>
									</table>
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
    	<td align="right"><a id="prev" class="portfolioButtons" href="/ADM_PRE/solution/reviewFTE" >Previous</a> </td>
    </tr>
</table>
</form:form>