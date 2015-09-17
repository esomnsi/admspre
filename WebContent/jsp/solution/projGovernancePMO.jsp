<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
        $(document).ready(function() {
        	$("#PMOAddRow").click(function() {
            	var row = $('#PMOdataTable tbody>tr:last').clone(true);
            	$("td input:text", row).val("");
        		$("td select", row).val("");
               /* 	$("td input:checkbox", row).attr('checked',false); */
               	row.insertAfter('#PMOdataTable tbody>tr:last');
              	return false;
            });
        	
        	$('#GovAndPMOSaveData').click(function() {
       		 if($('#dimensionAttributeId') != null){
    				$('#PMOmiscForm input[name="solutionDimentionAttId"]').val($('#dimensionAttributeId').val()); 
    			}
            	$('#PMOmiscForm').submit();
    	    });
        	
        	$("#PMOmiscForm").submit(function() {
    			showProgress();
    			var options = {
    					success: function(html) {
    						 $("#govPMODiv").html(html);
    						 $("#messageDiv").addClass("message");
    						 $("#messageDiv").html("Your data has been saved successfully.");
    						//$("#sdDiv").replaceWith($('#sdDiv', $(html)));
    						 refreshServiceBucket();
    					},
    					error: function(res) {
    						 $("#messageDiv").html("There is a error while saving data. Please contact administrator");
    					},
    					url: "./saveGovernanceAndPmo"
    				};
    			
    				$(this).ajaxSubmit(options);
    				hideProgress();
    				return false;
    		});
        });
       
     
    </script>
<div style="overflow:scroll; height:150px;"	>

	<div id="bodyDiv1" class="mainBodyDivwithoutLine">
		<form:form id="PMOmiscForm" method="POST" commandName="solutionGovPMOForm" >
			
			<form:hidden path="opportunityScopeId"/> 
			<form:hidden path="solutionDimentionAttId" /> 
			<table id="PMOdataTable" cellspacing="2" cellpadding="4" class="tdLabelLeft">
  <tr>
    <td width="306" bgcolor="#F1F1F1"><strong>Role (As Per    Rate Card)</strong></td>
    <td width="88" align="center" bgcolor="#F1F1F1"><strong>FTE</strong></td>
    <!-- <td width="86" align="center" bgcolor="#F1F1F1"><strong>Skill</strong></td> -->
    <td width="90" align="center" bgcolor="#F1F1F1"><strong>Remarks</strong></td>
  </tr>
  
 <c:if test="${not empty solutionGovPMOForm.solutionOtherMiscList}">
 <c:forEach var="item" items="${solutionGovPMOForm.solutionOtherMiscList}" varStatus="status">
 
	<input type="hidden"  name="solutionOtherMiscList[${status.index}].solutionOtherMiscId" value="${item.solutionOtherMiscId}"></input>
  <tr>
    <td bgcolor="#FAFAFA">
<%--     	<form:select  path="jobRoleId" 	multiple="false" class="textBoxLarge ">
			<form:option value="" label="--Select--" />
			<form:options items="${jobRoleDTOList}" itemValue="jobRoleId" itemLabel="role" />
		</form:select> --%>
		
			<form:select multiple="false" path="jobRoleId" class="listBoxMedium">
									<c:forEach var="jobItem" items="${jobRoleDTOList}">
									<c:choose>
									<c:when test="${jobItem.key == item.jobRoleDTO.jobRoleId}">
									<form:option selected="true" value="${jobItem.key}"> ${jobItem.value}</form:option>
									</c:when>
									<c:otherwise>
											<form:option value="${jobItem.key}">${jobItem.value}
											</form:option>
											</c:otherwise>
									</c:choose>
									</c:forEach>
									<!-- <option value="2">Role2 </option> -->
								</form:select>
    </td>
    <td bgcolor="#FAFAFA">
    	<form:input type="text" path="fte"  value="${item.fte}"  maxlength="7" class="textBoxSNumric" onkeypress="return isDecimal(event);" />
    </td>
   <%--  <td bgcolor="#FAFAFA">
    	<form:input type="text" path="skill"  value="${item.skill}"  maxlength="1000" class="textBoxSmalldevlopment" />
    </td> --%>
    <td bgcolor="#FAFAFA">
    	<form:input type="text" path="remarks"  value="${item.remarks}" maxlength="500"  class="textBoxSmalldevlopment"/>
    </td>
  </tr>
  
</c:forEach>
 </c:if>
 
 <c:if test="${empty solutionGovPMOForm.solutionOtherMiscList}"> 
  <tr>
    <td bgcolor="#FAFAFA">
    	<%-- <form:select  path="jobRoleId" id="" multiple="false"	class="textBoxLarge ">
			<form:option value="" label="--Select--" />
			<form:options items="${jobRoleDTOList}" itemValue="jobRoleId" itemLabel="role" />
		</form:select> --%>
		
		<form:select multiple="false" path="jobRoleId" class="listBoxMedium">
		<c:forEach var="item" items="${jobRoleDTOList}">
			<form:option value="${item.key}">${item.value}</form:option>
		</c:forEach>
	</form:select>
    </td>
    <td bgcolor="#FAFAFA">
    	<form:input type="text" path="fte" id="" value=""  maxlength="7" class="textBoxSNumric" onkeypress="return isDecimal(event);" />
    </td>
   <%--  <td bgcolor="#FAFAFA">
    	<form:input type="text" path="skill" id="" value=""  maxlength="1000" class="textBoxSmalldevlopment" />
    </td> --%>
    <td bgcolor="#FAFAFA">
    	<form:input type="text" path="remarks" id="" value="" maxlength="500"   class="textBoxSmalldevlopment"/>
    </td>
  </tr>

 </c:if>
  
</table>
  <c:if test="${hasEditSolAccess!='false'}">
<table>
	<tr><td></td>
	<tr>
	<td class="basicform_button" id="PMOAddRow">Add Row</td>
	<td class="basicform_button" id="GovAndPMOSaveData">Save</td>
  </tr>
</table>
	  </c:if>			
	</form:form>
</div>
</div>