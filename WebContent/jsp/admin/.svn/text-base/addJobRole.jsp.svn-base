<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Job Role</title>
</head>

<style type="text/css">
      .myCSS{
  	background: #FFFFFF; 
	box-sizing: border-box;
  	-moz-box-sizing: border-box;
	width:100%;
	margin-left:0px;
	margin-top:50px;
	margin-bottom:220px;
	/*border: 1px solid #000080;*/
	moz-border-radius: 5px;
   	webkit-border-radius: 5px;
   	border-radius: 5px;
   	
} 
</style>

<script type="text/javascript">

$(window).load(function() {
	 var dataException= '${exception}';
	 var errorMsg = "<ul>";
		if(!(dataException==null || dataException == "")){
			
			errorMsg += "<ul><li>Cannot delete or update a parent row: a foreign key constraint fails</li></ul>";
		}
		if(!(errorMsg == "<ul>")){
			document.getElementById('errorDiv').style.display = 'block';
			document.getElementById("errorDiv").innerHTML = errorMsg+"</ul>";
		}
});

function saveJobRole(){
	if(checkMandatory()){
		
	document.forms[0].action = "/ADM_PRE/admin/saveJobRole";
	document.forms[0].submit();
	
	}
};

function checkMandatory(){
	
	var errorMsg = "<ul>";
	var flag = true;
	jobRole = $("#id_jobRole").val();
	if(jobRole==null || jobRole == ""){
		errorMsg += "<ul><li>Job Role Name is mandatory</li></ul>";
		flag = false;
	}
	
	
	 
	 
	 
	if(!(errorMsg == "<ul>")){
		document.getElementById('errorDiv').style.display = 'block';
		document.getElementById("errorDiv").innerHTML = errorMsg+"</ul>";
	}
	return flag;
};


</script>
<body>
<div id="variable"></div>
<div  id="messageDiv" ></div>
<div id="errorDiv" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000; display:none;"></div>
<div class="tdHeaderLabel" style="width:1096px;margin-bottom:4px;margin-left:1px;">Add Job Role</div> 
	<div class="subminApprovalBodyArea" style="height: 100%;">
		<form:form id="AddJobRoleForm" modelAttribute="jobRoleForm" method="post">
		<form:hidden path="jobRoleDTO.jobRoleId"/>
		   <table width="100%" style="padding-right: 20px">
		   	<tr align="left" style="margin-left: 0%">
		    <td>
			<div class="adminTitle">
		    	<h3 style="width:110px;">Job Role Name<b><font color="red">*</font></b> :</h3>
		        <span><form:input id="id_jobRole" path="jobRoleDTO.role" type="text" class="inputtextTabs"></form:input></span>
		    </div>
		    </td>
		    <td>
		    <div class="adminTitle">
		    	<h3 style="width:110px;">Job Role Family :</h3>
		        <span><form:input id="jobRoleFamily" path="jobRoleDTO.jobRoleFamily" type="text" class="inputtextTabs"></form:input></span>
		    </div>
		    </td>
		    <td>
		    <div class="adminTitle">
		    	<h3 style="width:110px;">JobRole Family No. </h3>
		        <span><form:input id="jobRoleFamilyNumber" path="jobRoleDTO.jobRoleFamilyNumber" type="text" class="inputtextTabs" onkeypress="return isNumericWithDecimal(event,this);"></form:input></span>
		    </div>
		    </td>
		    
		    </tr>
		    <tr>
		    <td>
		    <div class="adminTitle">
		    	<h3 style="width:110px;">HRMS Object :</h3>
		        <span><form:input id="HRMSObject" path="jobRoleDTO.HRMSObject" type="text" class="inputtextTabs" onkeypress="return isNumericWithDecimal(event,this);"></form:input></span>
		    </div>
		    </td>
		    <td>
		    <div class="adminTitle">
		    	<h3 style="width:110px;">Used By FA :</h3>
		        <span><form:input id="usedByFA" path="jobRoleDTO.usedByFA" type="text" class="inputtextTabs"></form:input></span>
		    </div>
		    </td>
		    <td>
		    <div class="adminTitle">
		    	<h3 style="width:110px;">CCM Flag :</h3>
		        <span><form:select path="jobRoleDTO.CCMFlag">
		        			<form:option value="1">Yes</form:option>
		        			<form:option value="0">No</form:option>
		        	   </form:select></span>
		    </div>
		    </td>
		    </tr>
		    
		    </table>
		    
	
		    <div class="adminDivdHeaderr" >Select Job Stages</div>
			<div class="myCSS" style="margin-left: 50px">
				<%-- 		       	<form:checkboxes id ="myId"  element="span class='checkbox'" items="${jobStageList}" path="listJobStageId" itemLabel="stage" itemValue="jobStageID"  delimiter="   |"/>		
 --%>

				<table width="50%" border="0" align="left" cellspacing="0"
					style="padding-right: 300px;" >
					<tr>
						<td><span
							style="font-style: italic; font-family: Arial; font-weight: normal; font-size: 12px; color: #333">
								<c:forEach var="i" begin="0" end="8">
									<form:checkbox path="listJobStageId"
										value="${jobStageList[i].jobStageID}"
										label="${jobStageList[i].stage}" />
									<br>
								</c:forEach>
						</span></td>

						<td valign="top"><span
							style="font-style: italic; font-family: Arial; font-weight: normal; font-size: 12px; color: #333">
								<c:forEach var="i" begin="9" end="12">
									<form:checkbox path="listJobStageId"
										value="${jobStageList[i].jobStageID}"
										label="${jobStageList[i].stage}" />
									<br>
								</c:forEach>
						</span></td>
					</tr>
				</table>

			</div>
		</form:form>	
		</div>
		
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: left; margin-bottom: 5x;">
			<table width="100%"  align="left" border="0" style="padding-right: 20px">
				<tr>
					<td class="tdButtonRow" align="left">
						<a id="saveJobRole" class="TabsCommonButtons" onclick="saveJobRole()">Save</a>
					</td>
				</tr>
			</table>
		</div>
</body>
</html>


<%-- <form:checkbox path="jobStageIdList" value="${jobStage.jobStageID}" label="${jobStage.stage}" /> --%>
<%-- <form:checkboxes items="${jobStageList}" path="jobStageIdList" itemLabel="stage" itemValue="jobStageID" delimiter="  |  " /> --%>