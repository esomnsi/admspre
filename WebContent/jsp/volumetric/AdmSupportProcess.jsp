<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">

$(document)
.ready(
		function() {
			$('#nextAdmSupp').click(function() {
				/* if (validateAdmSupportOpportunityDetails()) { */
					var url = "<%=request.getContextPath()%>/solution/productEstimation";    
		    		$(location).attr('href',url);
				/* }else{
					document.getElementById("showError").innerHTML = "<ul><li>Sum of the Percentage of Adm Support Process Effort should be 100</li></ul>";
					document.getElementById("messageDiv").innerHTML = "";
				} */
			  });
			
			 $('#prevAdmSupp').click(function() {
					var url = "<%=request.getContextPath()%>/solution/applicationMaintenance";    
			    	$(location).attr('href',url);
		   });

			 <%-- $('#movetoApaAdmSupp').click(function() {
				/*  if (validateAdmSupportOpportunityDetails()) { */
						var url = "<%=request.getContextPath()%>/solution/APAnalysis";    
				    	$(location).attr('href',url);
					/* }else{
						document.getElementById("showError").innerHTML = "<ul><li>Sum of the Percentage of Adm Support Process Effort should be 100</li></ul>";
						document.getElementById("messageDiv").innerHTML = "";
					} */
			  });
 --%>
			
		});
		
/* function validateAdmSupportOpportunityDetails() {

	 var scopeIdListVar ='${serviceScopeIDList}';
	 scopeIdListVar = scopeIdListVar.replace(/[\][]/g,''); 
	 var scopeIdList = scopeIdListVar.split(',');
	 var totalCheck  = 0;
	for (var i = 0; i < scopeIdList.length; i++) {
		switch(parseInt(scopeIdList[i])){
			case 10:
				if(parseInt(document.getElementById("pmPercentOfServiceElementEffort").value )>0)
					totalCheck = (parseInt(totalCheck) +parseInt(document.getElementById("pmPercentOfServiceElementEffort").value)); 
				break;

			case 11:
				if(parseInt(document.getElementById("rmPercentOfServiceElementEffort").value) >0)
					totalCheck = (parseInt(totalCheck) +parseInt(document.getElementById("rmPercentOfServiceElementEffort").value)); 
				break;

			case 12:
				if(parseInt(document.getElementById("cmPercentOfServiceElementEffort").value )>0)
					totalCheck = (parseInt(totalCheck) +parseInt(document.getElementById("cmPercentOfServiceElementEffort").value));
				break;

			case 13:
				if(parseInt(document.getElementById("cpyPercentOfServiceElementEffort").value) >0)
				totalCheck = (parseInt(totalCheck) +parseInt(document.getElementById("cpyPercentOfServiceElementEffort").value));  
				break;

			case 14:
				if(parseInt(document.getElementById("cfgPercentOfServiceElementEffort").value) >0)
					totalCheck = (parseInt(totalCheck) +parseInt(document.getElementById("cfgPercentOfServiceElementEffort").value));
				break;

			case 15:
				if(parseInt(document.getElementById("secPercentOfServiceElementEffort").value) >0)
					totalCheck = (parseInt(totalCheck) +parseInt(document.getElementById("secPercentOfServiceElementEffort").value)); 
				break;

			case 16:
				if(parseInt(document.getElementById("avlPercentOfServiceElementEffort").value) >0)
					totalCheck = (parseInt(totalCheck) +parseInt(document.getElementById("avlPercentOfServiceElementEffort").value)); 
				break;

			case 17:
				if(parseInt(document.getElementById("acPercentOfServiceElementEffort").value) >0)
					totalCheck = (parseInt(totalCheck) +parseInt(document.getElementById("acPercentOfServiceElementEffort").value));
				break;
	}
	}
	if(parseInt(totalCheck) == parseInt(100))
		return true;
	else
		return false;

} */



</script>
&nbsp;
&nbsp;
&nbsp;
<div  id="messageDiv" >
		
			</div>
	<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
	-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;">
		</div>

	
	<div id="acdnmenu" style="width: 1112px; background-color: White; border: medium none; margin-top:5px;">
		  <ul>
		  
		  <c:if test="${empty serviceScopeList}">
		  		<div  class="message">
		  			Please select the service scope from define scope tab.
		  		</div>
		  </c:if>


	
		<c:forEach var="oppScope" items="${serviceScopeList}">
			<c:if test="${(not empty serviceCate) && (oppScope.serviceScopeDTO.serviceScopeCategory != serviceCate)}">
		
		</c:if>	
								 <li><div>${oppScope.serviceScopeDTO.serviceScopeName}</div>
				      <ul>
				        <li>
						 
						 	<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 10)}">
								<div id="pmDiv">
								<jsp:include page="programManagement.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include> 
								</div>
							</c:if>
							 <c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 11)}">
								<div id="rmDiv">
								<jsp:include page="releaseManagement.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include> 
								</div>
							</c:if>	
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 12)}">
								<div id="cmDiv">
								<jsp:include page="changeManagement.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include> 
								</div>
							</c:if>	
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 13)}">
								<div id="cpyDiv">
								<jsp:include page="capacityManagement.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include> 
								</div>
							</c:if>	
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 14)}">
								<div id="cfgDiv">
								<jsp:include page="configurationManagement.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include> 
								</div>
							</c:if>	
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 15)}">
								<div id="secDiv">
								<jsp:include page="securityManagement.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include> 
								</div>
							</c:if>	
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 16)}">
								<div id="avlDiv">
								<jsp:include page="availabilityManagement.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include> 
								</div>
							</c:if>	
						
							<%-- <c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 17)}">
								<div id="acDiv">
								<jsp:include page="accessManagement.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include> 
								</div>
							</c:if>			 --%>			
					  </li>
			      </ul>	
			    </li>
			</c:forEach>
			   </ul>
   			
			<div style="margin-top: 20px; float: right">
	   		 <table width="100%">
	   		
	   		 <tr bgcolor="#f7f7f7">
		     <td class="tdButtonRow" align="right" bgcolor="#f7f7f7">
		    	<a id="nextAdmSupp" class="TabsCommonButtons" >Next</a>
		      	<!-- <a id="movetoApaAdmSupp" class="TabsCommonButtons" >Move to APA</a> -->
		      	<a id="prevAdmSupp" class="TabsCommonButtons" >Previous</a>
		   	  </td>
		        </tr>
	      </table>
   		 </div>
	
		</div>
