<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">



$(document).ready(function () {
	$('#prevAppTest').click(function() {
		var url = "<%=request.getContextPath()%>/solution/volumetricAppDev";    
    	$(location).attr('href',url);
	  });
	 
	 $('#nextAppTest').click(function() {
			var url = "<%=request.getContextPath()%>/solution/applicationMaintenance";  
	    	$(location).attr('href',url);
   });
	
});


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
						 					
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 25)}">
								<div id="operationLTaasDiv">
								<jsp:include page="../solution/taas.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include> 
								</div>
							</c:if>
						</li>
			      </ul>	
			    </li>
			</c:forEach>
			   </ul>
		</div>
		<div style="margin-top: 20px; float: right">
	   		 <table width="100%">
	   		
	   		 <tr bgcolor="#f7f7f7">
		     <td class="tdButtonRow" align="right" bgcolor="#f7f7f7">
		     	 <a id="nextAppTest" class="TabsCommonButtons" >Next</a>
		      	<a id="prevAppTest" class="TabsCommonButtons" >Previous</a>
		   	  </td>
		        </tr>
	      </table>
   		 </div>
		 
