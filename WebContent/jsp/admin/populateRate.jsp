<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <script type="text/javascript" src="../js/jquery-1.8.2.js"> </script>


<script type="text/javascript">

	function getJobstages(){
		
		var selectedJobLen = $("#selectedJob").val().length;
		 if(selectedJobLen == 0){
			return; 
		}
		document.forms[0].action="/ADM_PRE/admin/getJobStages?param="+$("#selectedJob").val();
		document.forms[0].submit();
		
	}
	/* 
	 $("#customerSolutionResponsible").change(function(){
		 
	 }); */
		
	function submitData(option)
	{
		document.forms[0].action="/ADM_PRE/admin/saveRateData?param="+option;
		document.forms[0].submit();
		
	}

</script>
	
<form:form id="rateManagementForm" commandName="rateManagementDTO" method="POST" action="./saveCurrencyExchangeValue">
	
		<c:choose>
			<c:when test="${option == 'add' }">
				<div id="addRateDataRow">
					<table>
						<tr>
							<td>
								Choose Country : 
								<form:select  path="countryID" id="selectedCountry" class="inputjumptext">
				               			<form:option value="Select "/>
										<form:options items="${countriesList}" itemValue="countryId" itemLabel="countryName" />
								</form:select>
							</td>	
							<td>
								Choose Job Role : 
				               	<form:select  path="jobRoleID" id="selectedJob" class="inputjumptext" onchange="getJobstages()">
				               			<form:option value="Select "/>
										<form:options items="${jobRoleList}" itemValue="jobRoleId" itemLabel="role" />
								</form:select>
							</td>
							<td>	
								Choose Job Stage : 
								<form:select  path="jobStageID" id="selectedStage" class="inputjumptext" >
				               			<form:option value="Select "/>
										<form:options items="${jobStageList}" itemValue="jobStageID" itemLabel="stage" />
								</form:select>
							</td>
							<td>	
								Rate <form:input id="rate" path="rate" type="text" class="openSolutionComnInput" />
							</td>
						</tr>
					</table>
				</div>
					<div class="tabsDefineScoprButtons" style="">
	               		<a id="sub" class="TabsCommonButtons" onclick="submitData('sac')">Save And Close</a>
	            	</div>
	            	<div class="tabsDefineScoprButtons" style="">
	               		<a id="sub" class="TabsCommonButtons" onclick="submitData('sc')">Save And Continue</a>
	            	</div>
	            	
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		
</form:form>