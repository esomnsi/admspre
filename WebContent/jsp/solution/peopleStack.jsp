<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String appName = request.getContextPath();
	String contextPath = "http://"+request.getServerName()+":"+request.getServerPort()+appName;
%>

	<div id="opportunityDiv" class="mainBodyDiv1" style="margin-top:5px;">
			<jsp:include page="opportunitySummary.jsp"></jsp:include>
					</div>
					<div id="serviceBucket">
				<table width="730" align="center">
				<tr>
					<td align="center" colspan="6">
					<jsp:include page="./serviceBucket.jsp"/>
					</td>
				</tr>
				</table>
				</div>
	<div id="id_div_errorMsg" style="display: none;" class="errorMessageDisp"></div>
	<div id="bodyDiv" class="mainBodyDivwithoutLine">
	<form:form  method="post" modelAttribute="peopleStackForm" >
	<c:if test="${not empty peopleStackForm.solId}">
			<form:hidden path="solId" class="textBoxM" value="${peopleStackForm.solId}"/>
			<div id="mainBody" class="bodyCss">
		
			<div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 5px;">
	
				<h1 class="tdHeaderLabel" style="margin-top: 0px;margin-bottom: 0px;"> People Stack</h1>
			</div>
			<div id="bodyDiv" class="mainBodyDiv1" style="height:150;overflow-y:auto">
			
				<table id="peopleStackResourceTable" width="100%">
					<tbody>
					<tr>
          				<td class="tdTableHead" colspan="15" >Resources Detail</td>
        			</tr>
				     <tr>
				     	<td class="tdSubTableHead" style="width:5%">Name</td>
				     	<td class="tdSubTableHead" style="width:5%">Role/Job Position</td>
				     	
	     				<td class="tdSubTableHead" style="width:10%">Category</td>
	     				<td class="tdSubTableHead" style="width:5%">Reporting Manager</td>
	     				<td class="tdSubTableHead" style="width:5%">Department</td>
				     	
				     	<td class="tdSubTableHead" style="width:5%">Inscope for Outsourcing?</td>
				     	<td class="tdSubTableHead" style="width:12%">Scope</td>
	     				<td class="tdSubTableHead" style="width:10%">Base Location</td>
	     				
	     				<td class="tdSubTableHead" style="width:5%">Annual Hours</td>
	     				<td class="tdSubTableHead" style="width:5%">Yearly Utiliztion(%)</td>
	     				<td class="tdSubTableHead" style="width:10%">Annual Salary</td>
	     				<td class="tdSubTableHead" style="width:10%">Currency</td>
	     				<td class="tdSubTableHead" style="width:10%">Annual Salary(USSD)</td>
	     				<td class="tdSubTableHead" style="width:3%">Years of Employment</td>
	     				<!-- td class="tdSubTableHead" style="width:2%">Delete</td-->
				     <c:forEach items="${peopleStackForm.peopleStackResourceDetailDTOs}" var="details" varStatus="status" >
					     <tr>
						       <td class="tdCenterLabel" style="width:5%"> 
						        	<form:input maxlength="40" name="details[${status.index}].name" path="peopleStackResourceDetailDTOs[${status.index}].name" class="textBoxM"  value="${details.name}"/>
						        	<form:hidden name="details[${status.index}].dtoPK.solId" path="peopleStackResourceDetailDTOs[${status.index}].dtoPK.solId" class="textBoxM"  value="${peopleStackForm.solId}"/>
						        	<form:hidden name="details[${status.index}].dtoPK.resourceId" path="peopleStackResourceDetailDTOs[${status.index}].dtoPK.resourceId" class="textBoxM"  value="${status.index+1}"/>
						        </td> 
						         <td class="tdInputBox" style="width:5%"> 
						        	<form:input maxlength="40" name="details[${status.index}].jobPosition" path="peopleStackResourceDetailDTOs[${status.index}].jobPosition" class="textBoxM"  value="${details.jobPosition}"/>
						        </td> 
						         <td class="tdInputBox" style="width:10%"> 
						         	<form:select path="peopleStackResourceDetailDTOs[${status.index}].category" items="${peopleStackForm.categoryList}" />
						        </td> 
						         <td class="tdInputBox" style="width:5%"> 
						        	<form:select path="peopleStackResourceDetailDTOs[${status.index}].reportingMgr" class="listBoxS">
										<form:option value="0">Yes </form:option>
										<form:option value="1">No </form:option>
									</form:select>
						        </td> 
						         <td class="tdInputBox" style="width:5%"> 
						        	<form:input maxlength="40" name="details[${status.index}].department" path="peopleStackResourceDetailDTOs[${status.index}].department" class="textBoxM" value="${details.department}"/>
						        </td> 
								<td class="tdInputBox" style="width:5%"> 
						        	<form:select path="peopleStackResourceDetailDTOs[${status.index}].inScope" class="listBoxS">
										<form:option value="0">Yes </form:option>
										<form:option value="1">No </form:option>
									</form:select>
						        </td> 
						         <td class="tdInputBox" style="width:12%"> 
						        	<form:input maxlength="40" class="textBoxM"  name="details[${status.index}].scope" path="peopleStackResourceDetailDTOs[${status.index}].scope"   value="${details.scope}"/>
						        </td> 
						         <td class="tdInputBox" style="width:10%"> 
						        	<form:input maxlength="40" name="details[${status.index}].baseLocation" path="peopleStackResourceDetailDTOs[${status.index}].baseLocation" class="textBoxM"  value="${details.baseLocation}"/>
						        </td> 
						        <td class="tdInputBox" style="width:5%"> 
						        	<form:input name="details[${status.index}].annualHrs" path="peopleStackResourceDetailDTOs[${status.index}].annualHrs" class="textBoxM" value="${details.annualHrs}"/>
						        </td> 
						         <td class="tdInputBox" style="width:5%"> 
						         			${details.yearlyUtilization}
						         			<form:hidden name="details[${status.index}].yearlyUtilization" path="peopleStackResourceDetailDTOs[${status.index}].yearlyUtilization" class="textBoxM" value="${details.yearlyUtilization}"/>
						          </td> 
						         <td class="tdInputBox" style="width:10%"> 
						        	<form:input name="details[${status.index}].annualSummary" path="peopleStackResourceDetailDTOs[${status.index}].annualSummary" class="textBoxM"  value="${details.annualSummary}"/>
						        </td>
						        <td class="tdInputBox" style="width:10%"> 
						        	<form:select path="peopleStackResourceDetailDTOs[${status.index}].cuurency" items="${peopleStackForm.currencyList}" />
						        </td> 
						        <td class="tdInputBox" style="width:10%"> ${details.annualSalaryUSSD}
						        	<form:hidden name="details[${status.index}].annualSalaryUSSD" path="peopleStackResourceDetailDTOs[${status.index}].annualSalaryUSSD" class="textBoxM"  value="${details.annualSalaryUSSD}"/>
						        </td> 
						        <td class="tdInputBox" style="width:3%"> 
						        	<form:input name="details[${status.index}].yearsOfEmployment" path="peopleStackResourceDetailDTOs[${status.index}].yearsOfEmployment" class="textBoxM"  value="${details.yearsOfEmployment}"/>
						        </td>
						        <!-- td class="tdInputBox" style="width:2%;"> 
						        	<div align="center">
							        	<a href="javascript:deleteRow(${peopleStackForm.solId},${status.index+1})">
							        		<img align="top" src="<%=contextPath%>/img/delete.png">
							        	</a>
							        </div>	
						        </td-->  
						</tr>
					</c:forEach>	
					</tbody>
			</table>
			</div>
			<div class="mainBodyDiv1" style="width: 100%; float: left;">
				<table border=0 width="100%">
					<tbody><tr>
					<td colspan="4" class="tdButtonRow" align="center">
					<a href="#" id="addA" class="button" target="_blank">Add New</a>
					<a class="button" target="_self" onClick="javascript:saveResourceDetails();">Save</a>
					</td></tr>
				</tbody></table>
		</div>
			<div id="bodyDiv1" class="mainBodyDiv1" style="height:220;width:50%;float: left;overflow-y:auto;">
	
		<table style="width: 100%;height:220;" border="0" >
					<tbody><tr>
						<td class="tdTableHead" colspan="5">In Scope Summary Report</td>
		        		</tr>
					<tr>
						<td class="tdSubTableHead" width="15%" >Dept</td>
						<td class="tdSubTableHead" width="15%" >Category</td>
						<td class="tdSubTableHead" width="15%" >Avg Utiliztion(%)</td>
						<td class="tdSubTableHead" width="15%" >Total Outsourced(Hrs)</td>
						<td class="tdSubTableHead">Comments</td>
					</tr>
					<c:forEach items="${peopleStackForm.scopeSummaryReportDTOs}" var="summary" varStatus="status" >
						<tr>
						 	<td class="tdInputBox"><c:out value="${summary.department}"/>
						 		<form:hidden name="summary[${status.index}].department" path="scopeSummaryReportDTOs[${status.index}].department" class="textBoxM" value="${summary.department}"/>
						 	</td> 
						 	<td class="tdInputBox"><c:out value="${summary.category}"/>
						 		<form:hidden name="summary[${status.index}].category" path="scopeSummaryReportDTOs[${status.index}].category" class="textBoxM" value="${summary.category}"/>
						 	</td> 
						 	<td class="tdInputBox"><c:out value="${summary.avgUtil}"/>
						 		<form:hidden name="summary[${status.index}].avgUtil" path="scopeSummaryReportDTOs[${status.index}].avgUtil" class="textBoxM" value="${summary.avgUtil}"/>
						 	</td> 
						 	<td class="tdInputBox"><c:out value="${summary.totalOutsourcedHrs}"/>
						 		<form:hidden name="summary[${status.index}].totalOutsourcedHrs" path="scopeSummaryReportDTOs[${status.index}].totalOutsourcedHrs" class="textBoxM" value="${summary.totalOutsourcedHrs}"/>
						 	</td> 
						 	<td class="tdInputBox"></td> 
						  </tr> 
					</c:forEach>		   
						<tr>
					 	<td class="tdInputBox" colspan="3"><b>Sum</b></td> 
					 	<td class="tdInputBox"><b>${peopleStackForm.totalAnnualHrs}</b>
					 		<form:hidden name="totalOutsourcedHrs" path="totalAnnualHrs" class="textBoxM" value="${totalAnnualHrs}"/>
					 	</td> 
					 	<td class="tdInputBox"></td> 
					  </tr> 
					 <tr>
					 	<td class="tdInputBox" colspan="3">Less - People Management Effort </td> 
					 	<td class="tdInputBox">
					 		<form:input  id="peopleMgmt" path="lessEffortDTO.peopleMgmt" class="textBoxM"  value="${peopleStackForm.lessEffortDTO.peopleMgmt}"/>
					 	</td> 
					 	<td class="tdInputBox">${peopleStackForm.peopleManagementPercent}% of Outsourced effort (1:${peopleStackForm.peopleManagementPercentRatio})
					 		<form:hidden name="peopleManagementPercent" path="peopleManagementPercent" class="textBoxM" value="${peopleStackForm.peopleManagementPercent}"/>
					 		<form:hidden name="peopleManagementPercentRatio" path="peopleManagementPercentRatio" class="textBoxM" value="${peopleStackForm.peopleManagementPercentRatio}"/>
					 	</td> 
					  </tr> 
					  
					  <tr>
					 	<td class="tdInputBox" colspan="3">Less Others </td> 
					 	<td class="tdInputBox">
					 		<form:input id="others" path="lessEffortDTO.others" class="textBoxM"  value="${peopleStackForm.lessEffortDTO.others}"/>
					 	</td> 
					 	<td class="tdInputBox">
					 		<form:textarea id="comments" path="lessEffortDTO.comments"   style="width:200;height:50"/>
					 	</td> 
					  </tr> 
					  <tr>
					 	<td class="tdInputBox" colspan="3"><b>Net Outsourced Effort Baseline</b></td> 
					 	<td class="tdInputBox"><b>${peopleStackForm.netOutSourcedEffort}</b>
					 		<form:hidden name="netOutSourcedEffort" path="netOutSourcedEffort" class="textBoxM" value="${netOutSourcedEffort}"/>
					 	</td> 
					 	<td class="tdInputBox"></td> 
					  </tr> 
			</tbody></table>
			
		</div>
		<div id="bodyDiv1" class="mainBodyDiv1" style="width:49%;float: left; margin-left:10px;padding-bottom:12px;">
		<table style="width: 100%;" border="0" id="staffAllocationDetails">
					<tbody><tr>
						<td class="tdTableHead" colspan="5">Steady State Day 1 Allocation</td>
		        		</tr>
					<tr>
						<td class="tdSubTableHead" width="30%">Staff Category</td>
						<td class="tdSubTableHead" width="20%">Percentage of  Effort</td>
						<td class="tdSubTableHead" width="20%">Annual Utilization(%)</td>
						<td class="tdSubTableHead" width="15%">FTE</td>
						<td class="tdSubTableHead" width="15%">Effort</td>
					</tr>
					<c:forEach items="${peopleStackForm.staffCategoryDTOs}" var="staffCategory" varStatus="status" >
						<tr>
						 	<td class="tdInputBox">${staffCategory.staffCategory.category}
						 		<form:hidden name="statusCategory[${status.index}].staffCategory.category" path="staffCategoryDTOs[${status.index}].staffCategory.category" class="textBoxM"  value="${staffCategory.staffCategory.category}"/>
						 		<form:hidden name="statusCategory[${status.index}].staffCategory.categoryId" path="staffCategoryDTOs[${status.index}].staffCategory.categoryId" class="textBoxM" value="${staffCategory.staffCategory.categoryId}"/>
						 		<form:hidden name="staffCategory[${status.index}].primaryKey.solId" path="staffCategoryDTOs[${status.index}].primaryKey.solId" class="textBoxM"  value="${staffCategory.primaryKey.solId}"/>
						        <form:hidden name="staffCategory[${status.index}].primaryKey.category" path="staffCategoryDTOs[${status.index}].primaryKey.category" class="textBoxM"  value="${staffCategory.primaryKey.category}"/>
						 	</td> 
						 	<td class="tdInputBox">
						 		<form:input name="statusCategory[${status.index}].percentageEffort" path="staffCategoryDTOs[${status.index}].percentageEffort" class="textBoxM"  value="${staffCategory.percentageEffort}"/>
						 	</td> 
						 	<td class="tdInputBox">
						 		<form:input name="statusCategory[${status.index}].annualUtil" path="staffCategoryDTOs[${status.index}].annualUtil" class="textBoxM"  value="${staffCategory.annualUtil}"/>
						 	</td>
						 	<td class="tdInputBox">
						 		${staffCategory.fte}
						 		<form:hidden name="statusCategory[${status.index}].fte" path="staffCategoryDTOs[${status.index}].fte" class="textBoxM"  value="${staffCategory.fte}"/>
						 	</td> 
						 	<td class="tdInputBox">
						 		${staffCategory.effort}
						 		<form:hidden name="statusCategory[${status.index}].effort" path="staffCategoryDTOs[${status.index}].effort" class="textBoxM"  value="${staffCategory.effort}"/>
						 	</td> 
						  </tr> 
					 </c:forEach>
			</tbody></table>
		</div>
		<div class="mainBodyDiv1" style="width: 100%; float: left;">
				<table border=0 width="100%">
					<tbody><tr>
					<td colspan="4" class="tdButtonRow" align="center">
					<a class="button" href="<%=appName%>/opportunity/defineScope">Previous</a>
					<a class="button" target="_self" onClick="javascript:saveScopeSummaryDetails();">Save</a>
					<a class="button" href="<%=appName%>/solution/APAnalysis">Next</a>
					</td></tr>
				</tbody></table>
		</div>
	</div>	
	</c:if>
	<c:if test="${empty peopleStackForm.solId}">
		<div>No Solution Id Selected</div>
	</c:if>
	</form:form>
</div>


<script type="text/javascript">
$(document).ready(function() {

    $("#addA").click(function() {
      var row = $('#peopleStackResourceTable tbody>tr:last').clone(true);
      //$("td input:text", row).val("");
      row.insertAfter('#peopleStackResourceTable tbody>tr:last'); 
      var lastIndex = row.index()-2;
      $("td input:text,td input:hidden,td select", row).each(function(){
    	  //var newName = "peopleStackResourceDetailDTOs["+lastIndex+"]."+$(this).attr("name").split(".")[1];
    	  var newName = $(this).attr("name").replace($(this).attr("name").split(".")[0],"peopleStackResourceDetailDTOs["+lastIndex+"]");
    	  var newId = $(this).attr("id").replace($(this).attr("id").split(".")[0],"peopleStackResourceDetailDTOs"+lastIndex);
    	  $(this).attr("name",newName);
    	  $(this).attr("id",newId);
      });
      var newVal = Number($("#peopleStackResourceDetailDTOs"+lastIndex+"\\.dtoPK\\.resourceId").val())+1;
      $("#peopleStackResourceDetailDTOs"+lastIndex+"\\.dtoPK\\.resourceId").val(newVal);
        return false;
   });
}); 

function saveResourceDetails() {
	trimAll();
	if(validateDetails())
	{
		document.forms[0].action = "<%=appName%>/solution/saveResourceDetails";
		document.forms[0].submit();
	}	
}    
function saveScopeSummaryDetails() {
	trimAll();
	if(validateScopeSummary())
	{
		document.forms[0].action = "<%=appName%>/solution/saveScopeSummary";
		document.forms[0].submit();
	}	
} 
function validateDetails()
{
	var index = $('#peopleStackResourceTable tbody>tr:last').index()-2;
	for(var c=0;c<=index;c++)
	{
		var v1 = validate('peopleStackResourceDetailDTOs'+c+'\\.department',1,0);
		var v2 = validate('peopleStackResourceDetailDTOs'+c+'\\.annualHrs',1,1);
		var v3 = validate('peopleStackResourceDetailDTOs'+c+'\\.annualSummary',0,1);
		var v4 = validate('peopleStackResourceDetailDTOs'+c+'\\.yearsOfEmployment',0,1);
		if(!v1)
		{
			$('#id_div_errorMsg').show();
			$('#id_div_errorMsg').html("Department can't be blank index:"+(c+1));
			//alert("Department can't be blank index:"+(c+1));
		    return false;
		} 
		if(!v2)
		{
			$('#id_div_errorMsg').show();
			$('#id_div_errorMsg').html("Annual Hrs can't be blank,non numeric or negetive index:"+(c+1));
			//alert("Annual Hrs can't be blank,non numeric or negetive index:"+(c+1));
		    return false;
		}
		if(!v3)
		{
			$('#id_div_errorMsg').show();
			$('#id_div_errorMsg').html("Annual Salary can't be blank,non numeric or negetive index:"+(c+1));
			//alert("Annual Salary can't be blank,non numeric or negetive index:"+(c+1));
		    return false;
		}
		if(!v4)
		{
			$('#id_div_errorMsg').show();
			$('#id_div_errorMsg').html("Years of Employment can't be non numeric or negetive index:"+(c+1));
			//alert("Years of Employment can't be non numeric or negetive index:"+(c+1));
		    return false;
		}
	}	
	return true;
}

function validateScopeSummary()
{
	var v1 = validate('peopleMgmt',0,1);
	var v2 = validate('others',0,1);
	var v3 = validate('comments',1,0);
	if(!v1)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html("Peopple Effort Management can't be non numeric or negative");
		//alert("Peopple Effort Management can't be non numeric or negative");
	    return false;
	}
	if(!v2)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html("Others Effort Management can't be non numeric or negative");
		//alert("Others Effort Management can't be non numeric or negative");
	    return false;
	}
	if(!v3)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html("Comments can't be blank");
		//alert("Comments can't be blank");
	    return false;
	}
	return validateStaffAllocation();
}
function validateStaffAllocation()
{
	var index = $('#staffAllocationDetails tbody>tr:last').index()-2;
	var val = 0;
	for(var c=0;c<=index;c++)
	{
		if($('#staffCategoryDTOs'+c+'\\.percentageEffort').val()=="")
			$('#staffCategoryDTOs'+c+'\\.percentageEffort').val(0);
		if($('#staffCategoryDTOs'+c+'\\.annualUtil').val()=="")
			$('#staffCategoryDTOs'+c+'\\.annualUtil').val(0);
		if(isNaN($('#staffCategoryDTOs'+c+'\\.percentageEffort').val()) || Number($('#staffCategoryDTOs'+c+'\\.percentageEffort').val())<0)
		{
			$('#id_div_errorMsg').show();
			$('#id_div_errorMsg').html("Percentage of Effort can't be non numeric or negative  index:"+(c+1));
			//alert("Percentage of Effort can't be non numeric or negative  index:"+(c+1));
			return false;
		}
		if(isNaN($('#staffCategoryDTOs'+c+'\\.annualUtil').val()) || Number($('#staffCategoryDTOs'+c+'\\.annualUtil').val())<0)
		{
			$('#id_div_errorMsg').show();
			$('#id_div_errorMsg').html("Annual Utilization can't be non numeric or negative  index:"+(c+1));
			//alert("Annual Utilization can't be non numeric or negative  index:"+(c+1));
			return false;
		}
		if($('#staffCategoryDTOs'+c+'\\.percentageEffort').val()!=0 && $('#staffCategoryDTOs'+c+'\\.annualUtil').val()==0)
		{
			$('#id_div_errorMsg').show();
			$('#id_div_errorMsg').html("Annual Utilization can't be 0 when Percentage of Effort is non zero index:"+(c+1));
			//alert("Annual Utilization can't be 0 when Percentage of Effort is non zero index:"+(c+1));
			return false;
		}	
		val = Number(val) + Number($('#staffCategoryDTOs'+c+'\\.percentageEffort').val());
	}
	if(val!=100)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html("Sum of Percentage of Effort - not equals to 100%");
		//alert("Sum of Percentage of Effort - not equals to 100%");
		return false;
	}	
	return true;
}
</script>
