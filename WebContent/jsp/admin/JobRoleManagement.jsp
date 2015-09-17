<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css" rel="stylesheet"	type="text/css" media="all" />

<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>

<title>Job Role Management</title>
<script type="text/javascript">
	var oTable;
	//var vTable;
	$(document).ready(function() {
		oTable = $('#dataTable').dataTable({
			"sPaginationType" : "full_numbers",
			"bJQueryUI" : true,
			"bSort" : true,
			"fnDrawCallback" : function() {
				$('#dataTable tr').click(function(event) {
					$(this).find('input[type=radio]').prop('checked', true);
					isRadioButtonSelected = true;
				});
			}
		});
	});

	function clearForm() {
		document.getElementById("id_jobRoleId").value = "";
		document.getElementById("id_jobRole").value = "";
		document.getElementById("id_HRMSObject").value = "";
		document.getElementById("id_jobRoleFamily").value = "";
		document.getElementById("id_jobRoleFamilyNumber").value = "";
		document.getElementById("id_usedByFA").value = "";
	}

	function listJobRoles() {
		document.forms[0].action = "/ADM_PRE/admin/listJobRoles";
		document.forms[0].submit();
	}
	
	var isRadioButtonSelected = false;
	var progressIndicator = true;
	function showProgress() {
		if (!progressIndicator) {
			return false;
		}
		$
				.blockUI({
					message : '<h1><img src="../images/ajax-loader.gif" /><font size="3px;"> Please wait,it may take few seconds...</font></h1>',
					css : {
						border : '3px solid #00477D',
						padding : '10px',
						backgroundColor : 'white',
						'-webkit-border-radius' : '10px',
						'-moz-border-radius' : '10px',
						opacity : .8,
						width : '300px',
						height : '80px',
						color : 'black'
					}
				});

		var tr = $('#dataTable').find('tr');
		tr.bind('click', function(event) {
			var values = '';
			tr.removeClass('highlight');
			var tds = $(this).addClass('highlight').find('td');
		});
	}

	function addRole() {

		document.forms[0].action = "/ADM_PRE/admin/addRole";
		document.forms[0].submit();
	}

	function editRole() {
		if(isRadioButtonSelected){
		document.forms[0].action = "/ADM_PRE/admin/editRole";
		document.forms[0].submit();
		}else{

			$("#showError").addClass('errorMessageDisp');
			$("#showError").html(' &nbsp&nbsp  *	   '+ 'Select one Role to Edit');
		}
	}
</script>
<script type="text/javascript" src="js/jquery-1.js"></script>
<script type="text/javascript" src="js/jquery_003.js"></script>

<link href="it_mssp.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />


<style type="text/css">
.highlight {
	color: blue;
	font-size: 10px;
	background-color: red;
}

.row_selected {
	background-color: grey;
	background: #FFFFBB;
}

.markrow {
	background-color: #ffbba9 !important;
}
</style>
<%-- </head>

<jsp:include page="header.jsp"/>
<jsp:include page="menu.jsp"/> --%>
<div id="messageDiv" class="message" style="display: none;">
	<c:if test="${not empty successMessage}">
			${successMessage}
		</c:if>
</div>
<div id="Divmessage" class="message" style="display: block;"></div>
<div id="showError" class="errorMessageLogin"
	style="text-align: left; -webkit-border-radius: 5px 5px 5px 5px; display: block; -moz-border-radius: 5px 5px 5px 5px; border: 1px solid #FF0000;">
	<c:if test="${not empty message}">
		<font color="red" size="2" style="margin-left: 40%;">${message}</font>
		
	</c:if>
</div>
<div id="mainBody" class="bodyCss">
	<div id="bodyDiv" class="mainBodyDivwithoutLine">
		<form:form id="jobRoleManagementForm" method="POST"
			modelAttribute="jobRoleDTO">
			<div id="bodyDiv" class="mainBodyDiv1"></div>
			<div id="usual1" class="adminTabPanel">
				<ul>
					<li><a class="selected" href="#tab1"><span></span>Job Role
							Management</a></li>
				</ul>



				<div id="bodyDiv" class="mainBodyDiv1">

					<div style="display: block;" id="tab1"
						class="subminApprovalBodyArea">

						<div class="adminDivdHeaderr">Manage Job Role</div>
						<div class="adminTitle">
							<h3 style="width: 110px;">Job Role Id :</h3>
							<span><form:input id="id_jobRoleId" path="jobRoleId"
									type="text" class="inputtextTabs"
									onkeypress="return isNumericWithDecimal(event,this);"></form:input></span>
						</div>
						<!-- style="width:110px;" -->
						<div class="adminTitle">
							<h3 style="width: 110px;">Job Role Name :</h3>
							<span><form:input id="id_jobRole" path="role" type="text"
									class="inputtextTabs"></form:input></span>
						</div>
						<div class="adminTitle">
							<h3 style="width: 110px;">HRMS Object :</h3>
							<span><form:input id="id_HRMSObject" path="HRMSObject"
									type="text" class="inputtextTabs"
									onkeypress="return isNumericWithDecimal(event,this);"></form:input></span>
						</div>
						<div class="adminTitle">
							<h3 style="width: 110px;">Job Role Family :</h3>
							<span><form:input id="id_jobRoleFamily"
									path="jobRoleFamily" type="text" class="inputtextTabs"></form:input></span>
						</div>
						<div class="adminTitle">
							<h3 style="width: 110px;">JobRole Family No.</h3>
							<span><form:input id="id_jobRoleFamilyNumber"
									path="jobRoleFamilyNumber" type="text" class="inputtextTabs"
									onkeypress="return isNumericWithDecimal(event,this);"></form:input></span>
						</div>
						<div class="adminTitle">
							<h3 style="width: 110px;">Used By FA :</h3>
							<span><form:input id="id_usedByFA" path="usedByFA"
									type="text" class="inputtextTabs"></form:input></span>
						</div>
						<div class="tabsDefineScoprButtons">
							<a id="searcUser" class="TabsCommonButtons"
								onClick="javascript:listJobRoles();showProgress();"
								target="_blank">SEARCH</a> <a id="clear"
								class="TabsCommonButtons" target="_blank"
								onclick="javascript:clearForm()">CLEAR</a>
						</div>

						
						<table id="dataTable" width="1098px;" border="0" align="center"
							cellpacing="1" style="margin-left: 6px; font-size: 10px;">
							<thead>
								<tr class="subminApprovalTdProperty">
									<td class="tdSubTableHead">Select</td>
									<td class="tdSubTableHead" nowrap="nowrap">JobRoleID</td>
									<td class="tdSubTableHead" nowrap="nowrap">Role Name</td>
									<td class="tdSubTableHead" nowrap="nowrap">HRMSObject</td>
									<td class="tdSubTableHead">JobRole Family</td>
									<td class="tdSubTableHead">JobRole Family Number</td>
									<td class="tdSubTableHead">UsedByFA</td>
									<td class="tdSubTableHead">CCMFlag</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${jobRoleList}" var="jobRole"
									varStatus="status">
									<tr bgcolor="#f1f1f1">
										<td><form:radiobutton id="accessRadio" path="selected"
												value="${jobRole.jobRoleId}" /></td>
										<td style="color: black;">${jobRole.jobRoleId}</td>
										<td style="color: black;">${jobRole.role}</td>
										<td style="color: black;">${jobRole.HRMSObject}</td>
										<td style="color: black;">${jobRole.jobRoleFamily}</td>
										<td style="color: black;">${jobRole.jobRoleFamilyNumber}</td>
										<td style="color: black;">${jobRole.usedByFA}</td>
										<td style="color: black;">${jobRole.CCMFlag}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<tr>
							<!-- <td colspan="10" class="tdButtonRow" nowrap="nowrap"><a
								class="TabsCommonButtons" onclick="javascript:editRole();">View/Edit
									Role</a></td> -->
							<td colspan="10" class="tdButtonRow" nowrap="nowrap"><a
								class="TabsCommonButtons" onclick="javascript:addRole();">Add
									New Role</a></td>
						</tr>
					</div>
				</div>
			</div>
		</form:form>

	</div>
</div>




