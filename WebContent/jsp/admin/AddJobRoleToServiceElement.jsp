<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"	media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Service Element Management</title>
</head>
<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		o1Table = $('#mydataTable').dataTable({
			"sPaginationType" : "full_numbers",
			"bJQueryUI" : true,
			"bSort" : true,
			"fnDrawCallback" : function() {
				$('#mydataTable tr').click(function(event) {
					$(this).find('input[type=radio]').prop('checked', true);
					$(this).find('input').prop('disabled', false);
					isSelected = true;
				});
			}
		});
	});
	
	var tr = $('#mydataTable').find('tr');
	tr.bind('click', function(event) {
		var values = '';
		tr.removeClass('highlight');
		var tds = $(this).addClass('highlight').find('td');
		
	});
	
	var isSelected = false;
	function saveServiceEleJobRoleMapping() {
		if(isSelected){
			document.forms[0].action = "/ADM_PRE/admin/saveServiceElementJobRoleMapping";
			document.forms[0].submit();
		}else{
			$("#showError").addClass('errorMessageDisp');
			$("#showError").html(' &nbsp&nbsp  *	   '+ 'Select one Role to Add');
		}
}
	
	
	
</script>
<body>
<div id="showError" class="errorMessageLogin"
	style="text-align: left; -webkit-border-radius: 5px 5px 5px 5px; -moz-border-radius: 5px 5px 5px 5px; border: 1px solid #FF0000;">
	<c:if test="${not empty message}">
		<font color="red" size="2" style="margin-left: 40%;">${message}
	</c:if>
</div>

<div class="adminDivdHeaderr">Add Job Role and Job Stages</div>
		
		<form:form id="ServEleJobRoleMgmtForm" method="POST" modelAttribute="serviceElementJobRoleStagesDTO">
		<form:hidden path="serviceElement.serviceElementID" value="${serviceElement}"/>
			<table id="mydataTable" width="1098px;" border="0" align="center"
				cellpacing="1" style="margin-left: 6px; font-size: 10px;">
				<thead>
					<tr class="subminApprovalTdProperty">
						<td class="tdSubTableHead">Select</td>
						<td class="tdSubTableHead">Job Role Name</td>
						<td class="tdSubTableHead">Job Stage</td>
						<td class="tdSubTableHead">Onshore Local</td>
						<td class="tdSubTableHead">Onshore GSC</td>
						<td class="tdSubTableHead">Onshore 3PP</td>
						<td class="tdSubTableHead">Nearshore</td>
						<td class="tdSubTableHead">Offshore</td>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${listJobRoleStages}" var="jobRoleStage" varStatus="status">
						<tr bgcolor="#f1f1f1">
							<td><form:radiobutton id="accessRadio"	path="jobRoleStages.jobRoleStagesId" value="${jobRoleStage.jobRoleStagesId}" /></td>
							<td style="color: black;">${jobRoleStage.jobRoleDTO.role}</td>
							<td style="color: black;">${jobRoleStage.jobStageDTO.stage}</td>
							<td style="color: black;"><form:input path="onshoreLocalDefault" class="textBoxSNumric" disabled="true"/></td>
							<td style="color: black;"><form:input path="onshoreGSCDefault" class="textBoxSNumric" disabled="true"/></td>
							<td style="color: black;"><form:input path="onshore3PPDefault" class="textBoxSNumric" disabled="true"/></td>
							<td style="color: black;"><form:input path="nearshoreDefault" class="textBoxSNumric" disabled="true"/></td>
							<td style="color: black;"><form:input path="offshoreDefault" class="textBoxSNumric" disabled="true"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form:form>
		<tr>
			<td colspan="10" class="tdButtonRow" nowrap="nowrap"><a
				class="TabsCommonButtons"
				onclick="javascript:saveServiceEleJobRoleMapping();">Add</a></td>
		</tr>
</body>
</html>