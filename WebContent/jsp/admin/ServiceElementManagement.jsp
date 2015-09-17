<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css" rel="stylesheet"
	type="text/css" media="all" />
<script type="text/javascript" language="javascript"
	src="../js/jquery.dataTables.js"></script>



<title>Service Element Management</title>
<script type="text/javascript">
	var oTable;
	$(document).ready(function() {
		oTable = $('#dTable').dataTable({
			"sPaginationType" : "full_numbers",
			"bJQueryUI" : true,
			"bSort" : true,
			"fnDrawCallback" : function() {
				$('#dTable tr').click(function(event) {
					$(this).find('input[type=radio]').prop('checked', true);
					$(this).find('input').prop('disabled', false);
					isRadioButtonSelected = true;
				});
			}
		});		
		
		// this line of code unchecks the radio button on page load. otherwise due to already existing values
		// the radio button gets automatically selected when the values are populated 
		$(this).find('input[type=radio]').prop('checked', false);
	});

	
	var tr = $('#dTable').find('tr');
	tr.bind('click', function(event) {
		var values = '';
		tr.removeClass('highlight');
		var tds = $(this).addClass('highlight').find('td');
	});
	
	

		
	$(document).ready(function() {
		$("#serviceElementId").change(function() {
			$("#serviceElementManagementForm").attr("action","/ADM_PRE/admin/showAlreadyMappedRoles");
			$("#serviceElementManagementForm").submit();
			
		});

	});

	

	function addJobRoleToServiceElement() {
		document.forms[0].action = "/ADM_PRE/admin/addJobRoleToServiceElement";
		document.forms[0].submit();
	}
	
	var isServiceElementSelected = false;
	var isRadioButtonSelected = false;
	function editServiceElementJobRoleStage(){
		if(isRadioButtonSelected){
			document.forms[0].action = "/ADM_PRE/admin/editServiceElementJobRoleStage";
			document.forms[0].submit();
		}else{
			$("#showError").addClass('errorMessageDisp');
			$("#showError").html(' &nbsp&nbsp  *	   '+ 'Select one Role to Edit');
		}
	}
	
	
</script>

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
		<form:form id="serviceElementManagementForm" method="POST"
			modelAttribute="serviceElementManagementForm">
			<div id="bodyDiv" class="mainBodyDiv1"></div>
			<div id="usual1" class="adminTabPanel">
				<ul>
					<li><a class="selected" href="#tab1"><span></span>Service
							Elements</a></li>
				</ul>



				<div id="bodyDiv" class="mainBodyDiv1">

					<div style="display: block;" id="tab1" class="subminApprovalBodyArea">
						<div class="adminDivdHeaderr">Manage Service Elements</div>
						<tr>
						<td>
						<div class="adminTitle">
							<h3 style="width: 110px;">Service Element <font color="red">*</font>:</h3>
							<form:select path="serviceElementId" >
								<form:option label="--- Select ----" value="-1" />
								<form:options items="${listServiceElements}"
									itemLabel="serviceElementName" itemValue="serviceElementID" />
							</form:select>
						</div>
						</td>
						<c:if test="${isServiceElementSelected == 'true'}">
						<td colspan="10" class="tdButtonRow"><a
							class="TabsCommonButtons" id="id_AddRoleButton"
							onclick="javascript:addJobRoleToServiceElement();">Add Job Role</a>
						</td>
						</c:if>
						</tr> <br><br><br><br><br>
						<!-- <div id="existingDataDiv"></div> -->


						<div class="FTEscrollProperty">
							<table id="dTable" border="0" width="100%" cellspacing="2"
								cellpadding="0" style="margin-left: 6px; font-size: 10px;">
								<thead>
									<tr>
										<td class="tdSubTableHead">Select</td>
										<td class="tdSubTableHead"><strong>Job Role</strong></td>
										<td class="tdSubTableHead"><strong>Job Stage</strong></td>
										<td class="tdSubTableHead"><strong>OnshoreLocal</strong></td>
										<td class="tdSubTableHead"><strong>OnshoreGSC</strong></td>
										<td class="tdSubTableHead"><strong>Onshore3PP</strong></td>
										<td class="tdSubTableHead"><strong>Nearshore</strong></td>
										<td class="tdSubTableHead"><strong>Offshore</strong></td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${serviceElementManagementForm.listServiceElementJobRoleStagesDTO}" var="servEleJobRoleStage" varStatus="status">
										
										<tr>
										<form:hidden path="listServiceElementJobRoleStagesDTO[${status.index}].jobRoleStages.jobRoleStagesId"/>
										<form:hidden path="listServiceElementJobRoleStagesDTO[${status.index}].serviceElement.serviceElementID"/>
										<form:hidden path="listServiceElementJobRoleStagesDTO[${status.index}].serviceElementJobRoleStagesId"/>
											<td>
											<form:radiobutton id="accessRadio"	path="selected"  value="${status.index}" />
											</td>
											<td>${servEleJobRoleStage.jobRoleStages.jobRole.role}</td>
											<td>${servEleJobRoleStage.jobRoleStages.jobStage.stage}</td>
											<td style="color: black;"><form:input path="listServiceElementJobRoleStagesDTO[${status.index}].onshoreLocalDefault" class="textBoxSNumric" disabled="true"/></td>
											<td style="color: black;"><form:input path="listServiceElementJobRoleStagesDTO[${status.index}].onshoreGSCDefault" class="textBoxSNumric" disabled="true"/></td>
											<td style="color: black;"><form:input path="listServiceElementJobRoleStagesDTO[${status.index}].onshore3PPDefault" class="textBoxSNumric" disabled="true"/></td>
											<td style="color: black;"><form:input path="listServiceElementJobRoleStagesDTO[${status.index}].nearshoreDefault" class="textBoxSNumric" disabled="true"/></td>
											<td style="color: black;"><form:input path="listServiceElementJobRoleStagesDTO[${status.index}].offshoreDefault" class="textBoxSNumric" disabled="true"/></td>
											
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:if test="${isServiceElementSelected == 'true'}">
							<td colspan="10" class="tdButtonRow" nowrap="nowrap">
								<a class="TabsCommonButtons" id="id_AddRoleButton"
								   onclick="javascript:editServiceElementJobRoleStage();">Update</a>
							</td>
							</c:if>
						</div>	
						
						<!-- // -->
						<%-- <jsp:include page="ShowAlreadyMappedJobRoleStages.jsp">
						<jsp:param value="${serviceElementManagementForm}" name="serviceElementManagementForm"/>
						</jsp:include> --%>
						
						
						<!-- // -->
 						
					</div>
				</div>
			</div>
		</form:form>

						<%-- <form:form id="ServEleJobRoleMgmtForm" method="POST" modelAttribute="serviceElementJobRoleStagesDTO">
						<jsp:include page="AddJobRoleToServiceElement.jsp">
						<jsp:param value="${addJobRoleToServiceElement}" name="addJobRoleToServiceElement"/>
						</jsp:include>
						</form:form> --%>



	</div>
</div>




<%-- value="${servEleJobRoleStage.serviceElementJobRoleStagesId}" --%>