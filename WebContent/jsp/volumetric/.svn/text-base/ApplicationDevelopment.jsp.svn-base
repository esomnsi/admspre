<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">

$(document).ready(function () {
	$('#prevAppDev').click(function() {
		var url = "<%=request.getContextPath()%>/opportunity/defineScope";    
    	$(location).attr('href',url);
	  });
	 
	 $('#nextAppDev').click(function() {
			var url = "<%=request.getContextPath()%>/solution/volumetricApplicationTesting";    
	    	$(location).attr('href',url);
   });
	
});

function formatInput(inputVal,id){
	var tmp = inputVal.value;
	 if(isNaN(tmp) || tmp < 0) {
		document.getElementById(id).value = tmp;
		}
	 else if(tmp == "" || tmp == null) {
		document.getElementById(id).value = parseFloat(0).toFixed(2);
	 }
	else{
		tmp = parseFloat(tmp).toFixed(2);
		document.getElementById(id).value = tmp;
	} 
};

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
				        <c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 18)}">
								<div id="dsDiv">
								<jsp:include page="demandSupport.jsp">
									<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include>
								</div>
							</c:if>
							
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 19)}">
								<div id="designDiv">
								<jsp:include page="designBuild.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include>
								</div>
							</c:if>
							
							<%-- <c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 20)}">
								<div id="buildDiv">
								<jsp:include page="designBuild.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include>
								</div>
							</c:if> --%>
							
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 21)}">
								<div id="depRODiv">
								<jsp:include page="deploymentRollOut.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include>
								</div>
							</c:if>
							
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 22)}">
								<div id="postRelActDiv">
								<jsp:include page="postReleaseActivity.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include>
								<%-- <c:set var="postRelActFlag" value="true" scope="application"/>  --%>
								</div>
							</c:if>			
					  </li>
			      </ul>
			    </li>
			</c:forEach>
			   </ul>
   			
		
		<div style="margin-top: 20px; float: right">
	   		 <table width="100%">
	   		
	   		 <tr bgcolor="#f7f7f7">
		     <td class="tdButtonRow" align="right" bgcolor="#f7f7f7">
		     	 <a id="nextAppDev" class="TabsCommonButtons" >Next</a>
		      	<a id="prevAppDev" class="TabsCommonButtons" >Previous</a>
		   	  </td>
		        </tr>
	      </table>
   		 </div>
</div>