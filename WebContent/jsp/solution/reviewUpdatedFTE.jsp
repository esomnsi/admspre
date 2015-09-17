<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="../js/wz_tooltip.js"></script>
<%@taglib prefix="msg" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
//console.log("SUTIRTHA:");
//console.log($('#sServiceScope').get(0));
$(document).ready(function(){
	
	
	
	$('#sServiceScope').change(function() {
		//alert($(this).val());
		var scopeId = $(this).val();
		if (scopeId != -1 ) {
			window.location = 'reviewsscopeFTE?sServiceScope='+scopeId;
		}
	     //$(this).val() will work here
	});
	
});
function isNotAuthorized() {
	if ("${hasEditSolAccess}" == 'false') {
		$("#message").addClass('errorMessageDisp');
		$("#message").html(
				'<msg:message code="msg.common.err.notauthorized"/>');
		return true;
	}
	return false;
}
function generate()
{
	 if(isNotAuthorized())
		{
			return false;
		}
	$('#message').addClass('actionPerformedMessage');
	$('#message').html('<msg:message code="fte.generate.msg"/>');
	var img = document.createElement("IMG");
	img.src = "../images/loading.gif";
	document.getElementById('message').appendChild(img);
	document.location.href='./regenerateFTE';
}
</script>


<!--start your page from here  -->
			<div id="serviceBucket" style="margin-bottom:0px;" >
					<table width="730" align="center">
					<tr>
						<td align="center" colspan="6">
						<jsp:include page="../solution/serviceBucket.jsp"/>
						</td>
					</tr>
					</table>
			</div>

<div align="center" id="message"></div>

<c:if test="${fn:length(errormessage)!=0}">
	<div align="center" id="errormessage" class="errorMessageDisp">${errormessage}</div>
</c:if>

<!-- Review Updated FTE -->


<c:if test="${isrecordsavailable}">
	<table width="1112" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><div class="tdHeaderLabel FteHeaderText"
						style="mafloat: left; margin-bottom: 5px;">
						<msg:message code="fte.header.msg" />
					</div></td>
			</tr>
			<tr>
				<td align="center"><jsp:include page="opportunitySummary.jsp"></jsp:include></td>
			</tr>
					
			<tr>
				<td height="5"></td>
			</tr>
			<tr>
				<td>
					<table width="1112" border="0" cellspacing="0" cellpadding="0">
						<%-- <tr>
							<td colspan="${loopLength+1}" class="tdHeaderLabel"><div
									class="tdHeaderLabel FteHeaderText">
									<msg:message code="fte.header.msg" />
								</div></td>
						</tr> --%>
						<tr>
							<td>
								<div class="FteBodyDiv">
									<div class="FTEscrollProperty">
									    <table cellspacing="0" cellpadding="0" class="tdLabelLeft">
									    	<tr>
									    		<td width="130" height="40">Select Service Scope</td>
									    		<td width="200">
									    		     <select name="sServiceScope" id="sServiceScope" class="listBoxSmall">
									    		     	<option value="-1">-----Select-----</option>
									    		     	<c:forEach var="servicescopeentry" items="${solutionScope}">
									    		     		<option value="${servicescopeentry.key}">${servicescopeentry.value}</option>
									    		     	</c:forEach>
									    		     </select>
									    		</td>
									    		<td>
									    			<a onmouseout="UnTip()" onmouseover="Tip('Click to re-generate FTE table values.&lt;br/&gt;All additional FTE values will get removed!', BGCOLOR, '#FFFFFF')" onclick="generate();" id="genrate" class="TabsCommonButtons">Re-Generate</a>
									    		</td>
									    	</tr>
									    </table>
										<table class="reviewFTETable" cellspacing="2" cellpadding="0" class="reviewFTETable">
										  
										  <!-- -----------------------------------------Onshore Local FTE Count---------------------------------------------------------------- -->
										   <tbody id="onshoreLocal">
										   		<tr>
										   		    <td height="25" bgcolor="#e3e3e3"><strong>Onshore Local&nbsp;&nbsp;&nbsp;(Time lines)</strong></td>
										   		    <c:forEach var="monthyear" items="${monthList}">
										   		       <td width="100" align="center" bgcolor="#e3e3e3"><strong><c:out value="${monthyear}"/></strong></td>
										   		    </c:forEach>
										   		</tr>
										   		<c:forEach var="scopeentry" items="${OnShoreLocalFTEMap}">
										   		   <tr>
										   		        <td bgcolor="#f7f7f7" nowrap="nowrap" height="25"><c:out value="${scopeentry.key}"/></td>
										   		        <c:forEach var="yearmonthentry" items="${scopeentry.value}">
										   		          <td bgcolor="#f7f7f7" align="center">
										   		          <c:choose>
										   		          	     <c:when test="${yearmonthentry.value== 0.0 }">
										   		          	     	<c:out value="-"/>
										   		          	     </c:when>
										   		          	     <c:otherwise>
										   		          	     	<c:out value="${yearmonthentry.value}"/>
										   		          	     </c:otherwise>
										   		          	</c:choose>
										   		          </td>
										   		        </c:forEach>
										   		   </tr>
										   		</c:forEach>
										   		<tr>
													<td>&nbsp;</td>
												</tr>
										   </tbody>
										   
										   <!-- -----------------------------------------Onshore GSCi FTE Count---------------------------------------------------------------- -->
										   <tbody id="onshoreLocal">
										   		<tr>
										   		    <td height="25" bgcolor="#e3e3e3"><strong>Onshore GSC&nbsp;&nbsp;&nbsp;(Time lines)</strong></td>
										   		    <c:forEach var="monthyear" items="${monthList}">
										   		       <td width="100" align="center" bgcolor="#e3e3e3"><strong><c:out value="${monthyear}"/></strong></td>
										   		    </c:forEach>
										   		</tr>
										   		<c:forEach var="scopeentry" items="${OnShoreLocalGSCFTEMap}">
										   		   <tr>
										   		        <td bgcolor="#f7f7f7" nowrap="nowrap" height="25"><c:out value="${scopeentry.key}"/></td>
										   		        <c:forEach var="yearmonthentry" items="${scopeentry.value}">
										   		          <td bgcolor="#f7f7f7" align="center">
										   		          <c:choose>
										   		          	     <c:when test="${yearmonthentry.value== 0.0 }">
										   		          	     	<c:out value="-"/>
										   		          	     </c:when>
										   		          	     <c:otherwise>
										   		          	     	<c:out value="${yearmonthentry.value}"/>
										   		          	     </c:otherwise>
										   		          	</c:choose>
										   		          </td>
										   		        </c:forEach>
										   		   </tr>
										   		</c:forEach>
										   		<tr>
													<td>&nbsp;</td>
												</tr>
										   </tbody>
										   
										   <!-- -----------------------------------------Onshore 3PP FTE Count---------------------------------------------------------------- -->
										   <tbody id="onshoreLocal">
										   		<tr>
										   		    <td height="25" bgcolor="#e3e3e3"><strong>Onshore 3PP&nbsp;&nbsp;&nbsp;(Time lines)</strong></td>
										   		    <c:forEach var="monthyear" items="${monthList}">
										   		       <td width="100" align="center" bgcolor="#e3e3e3"><strong><c:out value="${monthyear}"/></strong></td>
										   		    </c:forEach>
										   		</tr>
										   		<c:forEach var="scopeentry" items="${OnShoreLocal3PPFTEMap}">
										   		   <tr>
										   		        <td bgcolor="#f7f7f7" nowrap="nowrap" height="25"><c:out value="${scopeentry.key}"/></td>
										   		        <c:forEach var="yearmonthentry" items="${scopeentry.value}">
										   		          <td bgcolor="#f7f7f7" align="center">
										   		          <c:choose>
										   		          	     <c:when test="${yearmonthentry.value== 0.0 }">
										   		          	     	<c:out value="-"/>
										   		          	     </c:when>
										   		          	     <c:otherwise>
										   		          	     	<c:out value="${yearmonthentry.value}"/>
										   		          	     </c:otherwise>
										   		          	</c:choose>
										   		          </td>
										   		        </c:forEach>
										   		   </tr>
										   		</c:forEach>
										   		<tr>
													<td>&nbsp;</td>
												</tr>
										   </tbody>
										   
										   <!-- -----------------------------------------Nearshore FTE Count---------------------------------------------------------------- -->
										   <tbody id="onshoreLocal">
										   		<tr>
										   		    <td height="25" bgcolor="#e3e3e3"><strong>Nearshore&nbsp;&nbsp;&nbsp;(Time lines)</strong></td>
										   		    <c:forEach var="monthyear" items="${monthList}">
										   		       <td width="100" align="center" bgcolor="#e3e3e3"><strong><c:out value="${monthyear}"/></strong></td>
										   		    </c:forEach>
										   		</tr>
										   		<c:forEach var="scopeentry" items="${NearshoreFTEMap}">
										   		   <tr>
										   		        <td bgcolor="#f7f7f7" nowrap="nowrap" height="25"><c:out value="${scopeentry.key}"/></td>
										   		        <c:forEach var="yearmonthentry" items="${scopeentry.value}">
										   		          <td bgcolor="#f7f7f7" align="center">
										   		          <c:choose>
										   		          	     <c:when test="${yearmonthentry.value== 0.0 }">
										   		          	     	<c:out value="-"/>
										   		          	     </c:when>
										   		          	     <c:otherwise>
										   		          	     	<c:out value="${yearmonthentry.value}"/>
										   		          	     </c:otherwise>
										   		          	</c:choose>
										   		          </td>
										   		        </c:forEach>
										   		   </tr>
										   		</c:forEach>
										   		<tr>
													<td>&nbsp;</td>
												</tr>
										   </tbody>
										   
										   <!-- -----------------------------------------Offshore FTE Count---------------------------------------------------------------- -->
										   <tbody id="onshoreLocal">
										   		<tr>
										   		    <td height="25" bgcolor="#e3e3e3"><strong>Offshore&nbsp;&nbsp;&nbsp;(Time lines)</strong></td>
										   		    <c:forEach var="monthyear" items="${monthList}">
										   		       <td width="100" align="center" bgcolor="#e3e3e3"><strong><c:out value="${monthyear}"/></strong></td>
										   		    </c:forEach>
										   		</tr>
										   		<c:forEach var="scopeentry" items="${OffshoreFTEMap}">
										   		   <tr>
										   		        <td bgcolor="#f7f7f7" nowrap="nowrap" height="25"><c:out value="${scopeentry.key}"/></td>
										   		        <c:forEach var="yearmonthentry" items="${scopeentry.value}">
										   		          <td bgcolor="#f7f7f7" align="center">
										   		          <c:choose>
										   		          	     <c:when test="${yearmonthentry.value== 0.0 }">
										   		          	     	<c:out value="-"/>
										   		          	     </c:when>
										   		          	     <c:otherwise>
										   		          	     	<c:out value="${yearmonthentry.value}"/>
										   		          	     </c:otherwise>
										   		          	</c:choose>
										   		          </td>
										   		        </c:forEach>
										   		   </tr>
										   		</c:forEach>
										   		<tr>
													<td>&nbsp;</td>
												</tr>
										   </tbody>
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>	
			</tr>
	</table>
</c:if>
<div style="float: left; width: 1112px; height: 35px;">
		<a class="portfolioButtons" onclick="location.href='./labourCost'" id="nextLabourCost">Next</a>
		<a class="portfolioButtons" onclick="location.href='./productivityModelling';">Previous</a>
		
</div>		