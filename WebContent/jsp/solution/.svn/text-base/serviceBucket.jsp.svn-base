
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="../js/wz_tooltip.js"></script>
<script type="text/javascript">
	function toggleServiceBucketDisplay(value) {
		if ($("#serviceBucketDetails").css('display') == "none") {
			$("#serviceBucketDetails").css('display', "");
		} else {
			$("#serviceBucketDetails").css('display', "none");
		}
	}
</script>


<!-- Service Bucket Code Started here -->
<c:set var="oldCategory" value="" />
<c:set var="sbDefaultDispVal" value="_" />
<c:set var="hoursTot" value="0" />
<c:set var="yearTotalFTE" value="0" />
<%-- <c:set var="sbDefaultVal" value="NA" />
<!-- Sum total variable values  -->
<c:set var="dayTotalFTE" value="0" />
<c:set var="dayTotalHC" value="0" />
<c:set var="yearTotalFTE" value="0" />
<c:set var="yearTotalHC" value="0" />
<c:set var="headC" value="0" /> --%>
<table width="730" cellpadding="0" cellspacing="1"
	class="tableTextCircular" align="center">
	<tr onclick="toggleServiceBucketDisplay();" style="cursor: pointer;"
		onmouseover="Tip('Click to Expand/Collapse Service Breakup Details', BGCOLOR, '#FFFFFF')"
		onmouseout="UnTip()">
		<td  rowspan="2" bgcolor="#093862"
			style="padding-left: 5px;"><strong>Service Function</strong></td>
		<td rowspan="2" bgcolor="#093862" style="padding-left: 5px;"><strong>Service
				Element</strong></td>
		<!-- <td height="20" colspan="2" align="center" bgcolor="#093862"><strong>Day
				1 People Dimension</strong></td> -->
		<!-- <td colspan="2" align="center" bgcolor="#093862"><strong>Year
				1 Effort (Hours)</strong></td> -->
	</tr>
	<tr onclick="toggleServiceBucketDisplay();" style="cursor: pointer;"
		onmouseover="Tip('Click to Expand/Collapse Service Breakup Details', BGCOLOR, '#FFFFFF')"
		onmouseout="UnTip()">
		<td width="106" align="center" bgcolor="#093862"><strong>FTE</strong></td>
		<td width="97" height="20" align="center" bgcolor="#093862"><strong>Hours</strong></td>
		<!-- <td width="91" align="center" bgcolor="#093862">For FTE</td>
		<td width="91" align="center" bgcolor="#093862">For HC</td> -->
	</tr>
	<tbody id="serviceBucketDetails" style="display: none">
		<c:forEach var="values" items="${serviceBucketData}"
			varStatus="status">
			<c:if test="${not empty values }">
				<c:set var="splittedVal" value="${fn:split(values, ';')}" />
				<tr class="ComontableTextTd">
					<td height="20" bgcolor="#f1f1f1"><strong> <c:if
								test="${splittedVal[0]!=oldCategory}">
										${splittedVal[0]}
										<c:set var="oldCategory" value="${splittedVal[0]}" />
							</c:if>
					</strong></td>
					<td bgcolor="#f1f1f1">
					<c:choose>
							<c:when
								test="${not empty splittedVal[1] && splittedVal[1] != sbDefaultVal && splittedVal[1]!=splittedVal[0]}">
									<div>	${splittedVal[1]} </div>
									</c:when>
							<c:otherwise>
									<div>	${sbDefaultDispVal} </div>
									</c:otherwise>
						</c:choose>
					
						</td>
					
					
					<%-- <td align="center" bgcolor="#f1f1f1"><span class="smfVal">
							<c:choose>
								<c:when
									test="${not empty splittedVal[3] && splittedVal[3] != sbDefaultVal}">
									<input type="text" class="txtinputComonfieldRight"
										value="-" disabled="disabled" />
									<c:set var="dayTotalHC" value="${dayTotalHC+splittedVal[3]}" />
									<c:set var="headC" value="${splittedVal[3]}" />
								</c:when>
								<c:otherwise>
									<input type="text" class="txtinputComonfieldRight"
										value="${sbDefaultDispVal}" disabled="disabled" />
								</c:otherwise>
							</c:choose>
					</span></td> --%>
					
					
					<!-- 					This is displaying FTE
 -->					
					
					 <td align="center" bgcolor="#f1f1f1"><span class="smfVal">
							<c:choose>
								<c:when
									test="${not empty splittedVal[3] && splittedVal[3] != sbDefaultVal}">
									<fmt:formatNumber var="yearFTE" value="${splittedVal[3]}"
										maxFractionDigits="2" />
									<input type="text" class="txtinputComonfieldRight"
										value="${yearFTE}" disabled="disabled" />
									<c:set var="yearTotalFTE"
										value="${yearTotalFTE+splittedVal[3]}" />
								</c:when>
								<c:otherwise>
									<input type="text" class="txtinputComonfieldRight"
										value="${sbDefaultDispVal}" disabled="disabled" />
								</c:otherwise>
							</c:choose>
					</span></td>
					
					
					
					
					<!-- 					This is displaying  Hours 
 -->
					
					<td align="center" bgcolor="#f1f1f1"><span class="smfVal">
							<c:choose>
								<c:when
									test="${not empty splittedVal[2] && splittedVal[2] != sbDefaultVal}">
									<fmt:formatNumber var="hours" value="${splittedVal[2]}"
										maxFractionDigits="2" />
									<input type="text" class="txtinputComonfieldRight"
										value="${hours}" disabled="disabled" />
									<c:set var="hoursTot" value="${ hoursTot+splittedVal[2]}" />
								</c:when>
								<c:otherwise>
									<input type="text" class="txtinputComonfieldRight"
										value="${sbDefaultDispVal}" disabled="disabled" />
								</c:otherwise>
							</c:choose>
					</span></td>
					
					
					

					<%--
					<td align="center" bgcolor="#f1f1f1"><span class="smfVal">
							<c:choose>
								<c:when
									test="${not empty splittedVal[5] && splittedVal[5] != sbDefaultVal}">
									<input type="text" class="txtinputComonfieldRight"
										value="-" disabled="disabled" />
									<c:set var="yearTotalHC" value="${yearTotalHC+splittedVal[5]}" />
								</c:when>
								<c:otherwise>
									<input type="text" class="txtinputComonfieldRight"
										value="${sbDefaultDispVal}" disabled="disabled" />
								</c:otherwise>
							</c:choose>
					</span></td> --%>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
 	<fmt:formatNumber var="hoursTot" value="${hoursTot}"
		maxFractionDigits="2" />
		<fmt:formatNumber var="yearTotalFTE" value="${yearTotalFTE}"
		maxFractionDigits="2" />
<%--	<fmt:formatNumber var="dayTotalHC" value="${dayTotalHC}"
		maxFractionDigits="2" />
	
	<fmt:formatNumber var="yearTotalHC" value="${yearTotalHC}"
		maxFractionDigits="2" />
	<c:set var="yrHC"
		value="${headC>0.00&&dayTotalFTE!=yearTotalFTE?(headC*12):headC}" /> --%>

	<tr class="ComontableTextTd" onclick="toggleServiceBucketDisplay();"
		style="cursor: pointer;"
		onmouseover="Tip('Click to Expand/Collapse Service Breakup Details', BGCOLOR, '#FFFFFF')"
		onmouseout="UnTip()">
		<td colspan="2" bgcolor="#f1f1f1"><strong>TOTAL</strong></td>

		<!-- 		Total Fte for first year
 -->		<td align="center" bgcolor="#f1f1f1"><span class="smfVal">
				<input type="text" id="yearTotalFTE" class="txtinputComonfieldRight"
				style="font-weight: bold;" value="${yearTotalFTE}"
				readonly="readonly"/>
		</span></td>
		
		
		
		<!-- 		total of Hours
 -->
		<td align="center" bgcolor="#f1f1f1"><span class="smfVal">
				<input type="text" id="hoursTot" class="txtinputComonfieldRight"
				style="font-weight: bold;" value="${hoursTot} " disabled="disabled" />
		</span></td>
		

		
		<%-- <td align="center" bgcolor="#f1f1f1"><span class="smfVal">
				<input type="text" class="txtinputComonfieldRight"
				style="font-weight: bold;" value="${yearTotalFTE}"
				disabled="disabled" />
		</span></td> --%>
		<%-- <td align="center" bgcolor="#f1f1f1"><span class="smfVal">
				<input type="text" class="txtinputComonfieldRight"
				style="font-weight: bold;" value="${yrHC}" disabled="disabled" />
		</span></td> --%>
	</tr>

</table>
<!-- Service Bucket Code End here -->
