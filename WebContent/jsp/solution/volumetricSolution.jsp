<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
var utilizationPerYear;

function setUtilizationPerYear(){
	
	utilizationPerYear = $('#utilizationPerYear').val();
	
}
$(document).ready(function () {
	var hasEditSolAccess = "${hasEditSolAccess}";
	if(hasEditSolAccess=='false'){
		 $("input").attr("disabled","disabled");
		 $("select").attr("disabled","disabled");
	}
	setUtilizationPerYear();
	
    $('.collapse').collapsiblePanel();
    
    $('#dimensionAttributeId').change(function() {
    	$('#volumetricSolutionForm').submit();
    });
  
    $('#proceedAPA').click(function() {
		
		 $('#volumetricSolutionForm').attr("action",'../solution/APAnalysis');
	  	  // $("#solutionForm").validate().cancelSubmit = true;
	    	$('#volumetricSolutionForm').submit();
	   });
	 
	 $('#prev').click(function() {
		 $('#volumetricSolutionForm').attr("action",'../opportunity/defineScope');
	  	  // $("#solutionForm").validate().cancelSubmit = true;
	    	$('#volumetricSolutionForm').submit();
	   });
	 
	 $('#next').click(function() {
		 $('#volumetricSolutionForm').attr("action",'../solution/complexityAdjuster');
	  	  // $("#solutionForm").validate().cancelSubmit = true;
	    	$('#volumetricSolutionForm').submit();
	   });
	 
});

function refreshServiceBucket (){
	
	 $.ajax({
			type : "POST",
			url : "./refreshServiceBucket",
			datatype : "text",
			success : function(html){
				
				 $("#serviceBucket").html(html);
			
			},
			error : function(e) {
				alert("Operation failed to load service bucket.");
			}
		});			 	
	 
	 		 	
	
}





</script>
<form:form id="volumetricSolutionForm" action="./volumetricSolution" method="post" modelAttribute="volumetricSolutionForm">
	
		<div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 4px; padding-bottom: 4px;">
			<table width="100%">
			
			<tr><td class="tdHeaderLabel" colspan="10"> Volumetric Solution</td></tr>
			<tr>
			
			<c:if test="${(not empty solutionDimentionList)}" >
			<td class="tdLabelLeft" style="margin-top: 4px;margin-bottom: 0px;" >Dimension Name : </td>
			<td> <form:select path="dimensionAttributeId" class="listBoxSmall" >
				<form:options items="${solutionDimentionList}" itemValue="solutionApproachDimensionId" itemLabel="dimensionAttributeName" />
				
			</form:select>
			</td>
			</div>
			</c:if>
			<tr>
			<td colspan="10">
				<jsp:include page="opportunitySummary.jsp"></jsp:include>
			</td>
			</tr>
			
			
			 <tr bgcolor="#fafafa">
			    <td width="20%"  align="center" class="tdLabelLeft" bgcolor="#efefef">Utilization</td>
			    <td width="5%" class="tdLabelLeft" >Year</td><td class="tdLabelRight">
			      <input type="text" id="utilizationPerYear"  value="2040"  class="textBoxSNumric"  maxlength="6" onkeypress="return isDecimal(event);" onblur="setUtilizationPerYear();" >
			   </td>
			    <td width="5%" class="tdLabelLeft">Month</td><td class="tdLabelRight">
			      <input type="text" id="utilizationPerMonth" value="170" class="textBoxSNumric" maxlength="6" onkeypress="return isDecimal(event);" />
			    </td>
			    <td width="5%" class="tdLabelLeft">Week</td><td>
			      <input type="text" id="utilizationPerWeek" value="42.5" class="textBoxSNumric" maxlength="5" onkeypress="return isDecimal(event);" />
			  </td>
			    <td width="5%" class="tdLabelLeft">Day</td><td>
			      <input type="text" id="utilizationPerDay" value="8.5" class="textBoxSNumric" maxlength="5" onkeypress="return isDecimal(event);" />
			   </td>
			  </tr>
			</table>
			<div id="serviceBucket">
				<table width="730" align="center">
				<tr>
					<td align="center" colspan="6">
					<jsp:include page="./serviceBucket.jsp"/>
					</td>
				</tr>
				</table>
				</div>
		</div>
		</form:form>
		<div  id="messageDiv" >
		
			</div>
	
		<div id="acdnmenu" style="width: 1112px; background-color: White; border: medium none;">
		  <ul>
		  
		  <c:if test="${empty serviceScopeList}">
		  		<div  class="message">
		  			Please select the service scope from define scope tab.
		  		</div>
		  </c:if>
		   
		<c:forEach var="oppScope" items="${serviceScopeList}">
			<c:if test="${(not empty serviceCate) && (oppScope.serviceScopeDTO.serviceScopeCategory != serviceCate)}">
		
		<!-- 	</div> -->
		</c:if>
			
			<%-- 
			<c:if test="${(empty serviceCate) || (serviceScope.serviceScopeCategory != serviceCate)}">
				<div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 5px;">
				<!--start your page from here  -->
				<div id="expander-demo-control" class="ui-widget-header tdTableHead">
					<span class="ui-icon ui-expander floatLeft "></span>
					 <span >${serviceScope.serviceScopeCategory}</span>
				</div>
				<p>
			</c:if>
			 --%>
				
				 <li><div>${oppScope.serviceScopeDTO.serviceScopeName}</div>
				      <ul>
				        <li>
						 
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 1)}">
								<div id="sdDiv">
								<jsp:include page="serviceDesk.jsp">
								<jsp:param value="${oppScope.opportunityScopeId}" name="opportunityScopeId"/>
								</jsp:include> 
								</div>
							</c:if>
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 2)}">
								<div id="operationL1Div">
									<jsp:include page="solutionL1Operations.jsp"></jsp:include>
								</div>
							</c:if>
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 3)}">
								<div id="operationL2Div">
									<jsp:include page="solutionL2Operations.jsp"></jsp:include>
								</div>
							</c:if>
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 4)}">
								<div id="operationL3Div">
								<jsp:include page="solutionL3Operations.jsp"></jsp:include>
								</div>
							</c:if>
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 5)}">
								<jsp:include page="solutionEnhanDevRicefw.jsp"></jsp:include>
							</c:if>
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 6)}">
								<div id="operationLTaasDiv">
								<jsp:include page="taas.jsp"></jsp:include>
								</div>
							</c:if>
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 7)}">
							<div id="govPMODiv">
								<jsp:include page="projGovernancePMO.jsp"></jsp:include>
							</div>
							</c:if>
							
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 8)}">
							
							</c:if>
							
							<c:if test="${(oppScope.serviceScopeDTO.serviceScopeId == 9)}">
								<div id="misceDiv">
								<jsp:include page="miscellaneous.jsp"></jsp:include>
								</div>
							</c:if>
						
							
					  </li>
			      </ul>
			    </li>
				
				
			<%-- <c:set var="serviceCate" value="${serviceScope.serviceScopeCategory}" /> --%>
			</c:forEach>
			   </ul>
   			
		</div>
		<div class="solutionAPA_Panel">
	        <div class="solutionAPA_PanelText">Want to analyze client's AS-IS application portfolio and generate Application  Portfolio Balanced Score Card</div>
	        <div class="solutionAPA_PanelButton" id="proceedAPA">PROCEED</div>
   		 </div>
   		 <br>
   		 <div style="margin-top: 5px; float: right">
	   		 <table width="100%">
	   		
	   		 <tr bgcolor="#f7f7f7">
		     <td class="tdButtonRow" align="right" bgcolor="#f7f7f7">
		     	 <a id="next" class="TabsCommonButtons" >Next</a>
		      	<a id="prev" class="TabsCommonButtons" >Previous</a>
		   	  </td>
		        </tr>
	      </table>
   		 </div>
	
	
