<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>
<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">


 
 function populateTab(index){

		var selectCriteria=null;
		
		if(index ==1 ){
			
			selectCriteria = 'TAB1';
			document.getElementById("jobRoleManagementForm").action="./serviceJobRoleManagement";
			document.getElementById("jobRoleManagementForm").submit();
		}
		else if(index == 2){
			selectCriteria = 'TAB2';
			document.getElementById("serviceElementManagementForm").action="./showAlreadyMappedRoles";
			document.getElementById("serviceElementManagementForm").submit();
			
		}
		

	}
 
 function setTab(index){
		
		currentSelectedTab = index;

 		document.getElementById('tt1').className = 'usual';
 	    document.getElementById('tt2').className = 'usual';
 				
		
		document.getElementById('tab1').style.display = "none";
		document.getElementById('tab2').style.display = "none";
			
 		document.getElementById('tt' + (currentSelectedTab)).className = 'selected';
 		document.getElementById('tab' + (currentSelectedTab)).style.display = "block";
	}
 window.onload = loadTab;

 function loadTab(){
 	var index=1;
 	var tab = "${selectedTab}";
 		
 	if('TAB1' == tab){
 		index = 1;
 	}else if('TAB2' == tab){
 		index = 2;
 	}

 	setTab(index);
 	
 }


	
function addJobRoleToServiceElement() {
	//document.forms[serviceElementManagementForm].action = "/ADM_PRE/admin/addJobRoleToServiceElement";
	//document.forms[serviceElementManagementForm].submit();
	$("#serviceElementManagementForm").attr("action","/ADM_PRE/admin/addJobRoleToServiceElement");
	$("#serviceElementManagementForm").submit();
}

var isServiceElementSelected = false;
var isRadioButtonSelected = false;
function editServiceElementJobRoleStage(){
	if(isRadioButtonSelected){
		/* document.forms[serviceElementManagementForm].action = "/ADM_PRE/admin/editServiceElementJobRoleStage";
		document.forms[serviceElementManagementForm].submit(); */
		$("#serviceElementManagementForm").attr("action","/ADM_PRE/admin/editServiceElementJobRoleStage");
		$("#serviceElementManagementForm").submit();
	}else{
		
	    document.getElementById('showError').style.display = 'block'; 
		$("#showError").addClass('errorMessageDisp');
		$("#showError").html(' &nbsp&nbsp  *	   '+ 'Select one Role to Edit');
	}
}

	var oTable;
	var oTable2;
	//var vTable;
	$(document).ready(function() {
		
		$("#selectedServiceElement").change(function(){
			var val = $("#selectedServiceElement").val().length;
			if(val <=0){
				alert("Please select an apropriate option");
				return false;
			}
			$("#serviceElementManagementForm").attr("action","/ADM_PRE/admin/showAlreadyMappedRoles");
			$("#serviceElementManagementForm").submit();
/* 			$.ajax({
		           url : "/ADM_PRE/admin/showAlreadyMappedRoles",
		           type : "post",
		           cache: false,
		           datatype : "text",
		           data : "serviceElementID="+$("#selectedServiceElement").val(),
		           success : function(resp) {

		        	   var array1 = resp.split("|");
		        	   var i;
		        	   var splitted =array1[0].split("-");
/* 		        	   alert(array1);
 */ 		        	   alert("splitted is :"+splitted+ "   and splitted[0] is:"+splitted[0]+ "   Splitted lenght is "+
 		        			   splitted.length);
 //alert($("#dTable2").find());		        	  
// $("#jobR").html(splitted[0]);
 		        	  
 		        	  
 		        	//document.getElementById("jobR").value = splitted[0];
 //alert("document.getElementById(jobR).value  "+ document.getElementById("jobR").value);
		        	   
/* 		        	   for(i=0;i<array1.length-1;i++){
 						
		        		   
 					splitted = array1[i].split("-");
 					
 					jobrole = splitted[0];
 					jobstage = splitted[1];
 					
 					splitted="";
 					
		        	   }   
		        	    
					},
					error : function(resp) {
						alert("Error");
					}
		       }); */
		});
		
		
		oTable2 = $('#dTable2').dataTable({
			"sPaginationType" : "full_numbers",
			"bJQueryUI" : true,
			"bSort" : true,
			"fnDrawCallback" : function() {
				$('#dTable2 tr').click(function(event) {
					$(this).find('input[type=radio]').prop('checked', true);
					$(this).find('input').prop('disabled', false);
					isRadioButtonSelected = true;
				});
			}
		});		
		
		// this line of code unchecks the radio button on page load. otherwise due to already existing values
		// the radio button gets automatically selected when the values are populated 
		$(this).find('input[type=radio]').prop('checked', false);

		
		var tr2 = $('#dTable2').find('tr');
		tr2.bind('click', function(event) {
			var values = '';
			tr2.removeClass('highlight');
			var tds = $(this).addClass('highlight').find('td');
		});
		
		
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
		if (isRadioButtonSelected) {
			document.forms[0].action = "/ADM_PRE/admin/editRole";
			document.forms[0].submit();
		} else {
			
			 document.getElementById('showError').style.display = 'block';
			$("#showError").addClass('errorMessageDisp');
			$("#showError").html(
					' &nbsp&nbsp  *	   ' + 'Select one Role to Edit');
			 
			
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

<!--  div 1 start -->
<div id="messageDiv" class="message" style="display: none;">
	<c:if test="${not empty successMessage}">
			${successMessage}
		</c:if>
</div>
<div id="Divmessage" class="message" style="display: none;"></div>
<div id="showError" class="errorMessageLogin"
	style="text-align: left; -webkit-border-radius: 5px 5px 5px 5px; display: none; -moz-border-radius: 5px 5px 5px 5px; border: 1px solid #FF0000;">
	<c:if test="${not empty message}">
		<font color="red" size="2" style="margin-left: 40%;">${message}</font>
		
	</c:if>
</div>

<div id="mainBody" class="bodyCss">
	<div id="bodyDiv" class="mainBodyDivwithoutLine">
			<div id="bodyDiv" class="mainBodyDiv1"></div>
				
				
			<div id="usual1" class="adminTabPanel">
				
				<ul>
					<li style="width:220px;">
						<a class="" id =tt1  onClick="populateTab(1)"><span></span>Job Role Management<span></span></a>
					</li>
					<li style="width:220px;">
						<span></span><a class="" id=tt2    onClick="populateTab(2)">Service Element</a>
					</li>
					 
				</ul>
		<form:form id="jobRoleManagementForm" method="POST"	modelAttribute="jobRoleDTO">

				<div id="bodyDiv" class="mainBodyDiv1">

					<div style="display: block;" id="tab1"
						class="subminApprovalBodyArea">
<c:if test="${(selectedTab =='TAB1')}">  
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


						<div>
							<br>
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
						</div>
						<tr>
							 <td colspan="10" class="tdButtonRow" nowrap="nowrap"><a
								class="TabsCommonButtons" onclick="javascript:editRole();">View/Edit
									Role</a></td>
							<td colspan="10" class="tdButtonRow" nowrap="nowrap"><a
								class="TabsCommonButtons" onclick="javascript:addRole();">Add
									New Role</a></td>
						</tr>
</c:if>						
					</div>
				</div>
		</form:form>
<!-- this is div 1 end -->






<!-------------------------------------------  div 2 Start -->

<form:form id="serviceElementManagementForm"  method="POST" modelAttribute="serviceElementManagementForm">
		<!-- <div style="display: none;" id="tab2" class="subminApprovalBodyArea">

	</div> -->
	
						<div style="display: none;" id="tab2" class="subminApprovalBodyArea">
						<c:if test="${(selectedTab =='TAB2')}">  
						
						<div class="adminDivdHeaderr">Manage Service Elements</div>
						<div>
						<table id="dTable0" border="0" width="1098px;" cellspacing="2"
								cellpadding="0" style="margin-left: 0px; font-size: 10px;">
						<tr>
						<td width="15%" class="tdLabel">
						
						Service Element <font color="red">*</font>:
						
							</td>

							<td style=" width:80%">
							<form:select path="serviceElementId" id="selectedServiceElement" class="listBoxMedium">
								<form:option label="--- Select ----" value="-1" />
								<form:options items="${listServiceElements}"
									itemLabel="serviceElementName" itemValue="serviceElementID" />
							</form:select>
							</td>
					<td width="5%"  style="margin-right: 65px; float:right;">
						<c:if test="${isServiceElementSelected == 'true'}">
						<div  class="tdButtonRow" ><a
							class="TabsCommonButtons" id="id_AddRoleButton"
							onclick="javascript:addJobRoleToServiceElement();">Add Job Role</a>
						</div>
						</c:if>
						</td></tr>
						</table>
						</div>
						<!-- <div id="existingDataDiv"></div> -->


						<div class="FTEscrollProperty" style="margin-top:20px;">
							<table id="dTable2" border="0" width="1098px;" cellspacing="2"
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
								<a class="TabsCommonButtons" id="id_UpdateButton"
								   onclick="javascript:editServiceElementJobRoleStage();">Update</a>
							</td>
							</c:if>
						</div>	
						
						<!-- // -->
						<%-- <jsp:include page="ShowAlreadyMappedJobRoleStages.jsp">
						<jsp:param value="${serviceElementManagementForm}" name="serviceElementManagementForm"/>
						</jsp:include> --%>
						
						
						<!-- // -->
 				</c:if>  
					</div>
	
</form:form>
<!--  div 2 End -->


			</div>
	</div>
</div>




<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>















