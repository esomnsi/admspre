<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="org.springframework.ui.Model" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>

<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>
<!-- <link href="../css/demo_table.css" rel="stylesheet" type="text/css" /> -->
<!-- <link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" /> -->
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css" rel="stylesheet"
	type="text/css" media="all" />

	
<script type="text/javascript">

$(document).ready(function() {
	
	var messageText = '${message}';
	var errorText = '${errorMessage}';
	if(messageText !== null && messageText !== undefined && messageText !== ''){
		$("#messageDiv").addClass('actionPerformedMessage');
		$("#messageDiv").html(messageText);
	}
	if(errorText !== null && errorText !== undefined && errorText !== ''){
		$("#messageDiv").addClass('errorMessageDisp');
		$("#messageDiv").html(errorText);
	}
	
	var accesss = ${hasEditSolAccess};	
	if(!accesss){
		// disable all input fields if user does not has access.
		$(" :input").prop("disabled", "disabled");
	}
	
	$('#prevAppMain').click(function() {
	    var url = "<%=request.getContextPath()%>/solution/productEstimation";    
    	$(location).attr('href',url);
	});
	
	$('#nextAppMain').click(function() {
		var url = "<%=request.getContextPath()%>/solution/productAppTesting";
		$(location).attr('href',url);
	});
	
	$('#reviewAppMain').click(function() {
	    var url = "<%=request.getContextPath()%>/solution/applicationMaintenanceView";    
    	$(location).attr('href',url);
	});
	
	$('#saveAppMainSlaForm').click(function() {
		//$( "#id_severity").prop( "disabled", false );
		$("#id_appMainSlaForm :input").prop("disabled", false); // this will enable all the elements of the form.
		$("#id_appMainSlaForm").attr("action", "/ADM_PRE/solution/saveAppMainSLA");
		$("#id_appMainSlaForm").submit();
	});
	
	$('#saveTktDistForm').click(function() {
		if(calculateSum()){
			//$( "#id_severity").prop( "disabled", false );
			$("#id_tktDistForm :input").prop("disabled", false); // this will enable all the elements of the form.
			$("#id_tktDistForm").attr("action", "/ADM_PRE/solution/saveTicketDist");
			$("#id_tktDistForm").submit();
		}else{
			document.getElementById("showErrorTktDist").style.display = 'block';
			$("#showErrorTktDist").addClass('errorMessageDisp');
			$("#showErrorTktDist").html('* Sum of Ticket Distribution percentages should be 100');
		}
	});
	
	$('#saveSuppAct').click(function() {
		//$( "#id_severity").prop( "disabled", false );
		$("#id_supportActivityForm :input").prop("disabled", false); // this will enable all the elements of the form.
		$("#id_supportActivityForm").attr("action", "/ADM_PRE/solution/saveAppMainSupportActivity");
		$("#id_supportActivityForm").submit();
	});
	
	
aTable = $('#dataTableSla').dataTable({
		'bAutoWidth': false,
		"aoColumns" : [
		               null,
		               { sWidth: '30px' },
		               { sWidth: '30px' },
		               { sWidth: '30px' },
		               { sWidth: '30px' }
		           ],
		'sDom': 't' ,
		"bInfo": false,
		"bFilter" : false,
		"bPaginate" : false,
		"bJQueryUI" : true,
		"bSort" : false,
			});
	
bTable = $('#dataTableTktDist').dataTable({
	'bAutoWidth': false,
	"aoColumns" : [
	              null,
	              null
	           ],
		'sDom': 't' ,
		"bInfo": false,
		"bFilter" : false,
		"bPaginate" : false,
		"bJQueryUI" : true,
		"bSort" : false,
            
			});
	
cTable = $('#dataTableSuppAct').dataTable({
	'bAutoWidth': false,
	"aoColumns" : [
	               { sWidth: '100px' },
	               { sWidth: '80px' },
	               { sWidth: '100px' },
	               { sWidth: '100px' },
	              null
	           ],
	'sDom': 't' ,
	"bInfo": false,
	"bFilter" : false,
	"bPaginate" : false,
	"bJQueryUI" : true,
	"bSort" : false,
		});
		
		
		
$('#resetTktDist').click(
		function(){
			$('#id_tktDistForm').each(
				function(){
				  this.reset();
				  document.getElementById("showErrorTktDist").innerHTML = '';
				  document.getElementById("showErrorTktDist").style.display = 'none';
				});
		});
		
$('#resetAppMainSla').click(
		function(){
			$('#id_appMainSlaForm').each(
				function(){
				  this.reset();
				});
		});
		
		
$('#resetSuppAct').click(
		function(){
			$('#id_supportActivityForm').each(
				function(){
				  this.reset();
				});
		});		
	
});
	
	function calculateSum(){
		var percent1 = $('#id_tktPercnt0').val() || 0;
		var percent2 = $('#id_tktPercnt1').val() || 0;
		var percent3 = $('#id_tktPercnt2').val() || 0;
		var percent4 = $('#id_tktPercnt3').val() || 0;
		var percent5 = $('#id_tktPercnt4').val() || 0;
		var percent6 = $('#id_tktPercnt5').val() || 0;
		
		var sum = parseFloat(percent1)+parseFloat(percent2)+parseFloat(percent3)+parseFloat(percent4)+parseFloat(percent5)+parseFloat(percent6);
		//alert(sum);
		if(sum == 100) {return true;}else{return false;}
	}
	
	
	
</script>
</head>
<body>
<br><br><br>
<div align="center" id="messageDiv"></div>
<table width="100%" style="vertical-align: middle;">
	<tr>
		<td class="tablepanel" width="50%" height="50%" valign="top" align="center">
			<div class="adminDivdHeaderr1" style="width: 81%;float: none;color: #000000">INPUT FOR SUPPORT SLA DEFINITION</div>
			<form:form id="id_appMainSlaForm" modelAttribute="appMainSlaForm" name="appMainSlaForm">
				<table id="" border="0"  cellspacing="2"
					cellpadding="0" style="margin-left: 6px; font-size: 10px;" class="tablepanel" width="85%">
					<thead>
						<tr>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Severity</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Level 1 (L1)</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Level 2 (L2)</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Level 3 (L3)</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">% Distribution</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${appMainSlaForm.list}" var="sla" varStatus="status">
							
							<tr>
							<form:hidden path="list[${status.index}].appMainSlaID"/>
							<form:hidden path="list[${status.index}].opportunity.opportunityId" value="${oppID}" />
							<form:hidden path="list[${status.index}].severity"/>
							<td class="textBoxSNumric1">${sla.severity}</td>
							<td style="color: black;"><form:input path="list[${status.index}].LOneResponse" class="textBoxSNumric" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);"/></td>
							<td style="color: black;"><form:input path="list[${status.index}].LTwoResponse" class="textBoxSNumric" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);"/></td>
							<td style="color: black;"><form:input path="list[${status.index}].LThreeResponse" class="textBoxSNumric" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);"/></td>
							<td style="color: black;"><form:input path="list[${status.index}].ticketDistribution" class="textBoxSNumric" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);"/></td>										
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:choose>
				<c:when test="${hasEditSolAccess!='false'}">
				<div style="float: right;margin-right: 30px;margin-top: 10px;margin-bottom: 10px;" >
					<a id="resetAppMainSla"	class="TabsCommonButtons">Reset</a> 
					<a id="saveAppMainSlaForm" class="TabsCommonButtons">Save</a>
				</div>
				</c:when>
				</c:choose>
			</form:form>
		</td>
		<td class="tablepanel" align="center">
			<div id="showErrorTktDist" align="left"
				 style="width:85%;margin-left:8px;text-align: center; -webkit-border-radius: 5px 5px 5px 5px; display: none; -moz-border-radius: 5px 5px 5px 5px; border: 1px solid #FF0000;">
				<c:if test="${not empty message}">
					<font color="red" size="2" style="margin-left: 40%;">${message}</font>
					
				</c:if>
			</div>
				<form:form id="id_tktDistForm" modelAttribute="tktDistForm">
			<div class="adminDivdHeaderr1" style="float: none;width: 83%;color: #000000">INPUT FOR TICKET DISTRIBUTION RULE OF EACH LEVEL</div>
			<div style="height:auto;">
				<table id="" style="margin-left: 6px; font-size: 10px; width: 85%;" class="tablepanel">
					<thead>
						<tr>
							<td class="tdSubTableHead" width="100%" style="font-weight: normal;font-size: small;">Service Element</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">% Distribution</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tktDistForm.tktDistList}" var="tktDist" varStatus="status">
						
							<tr>
								<form:hidden path="tktDistList[${status.index}].ticketDistributionID"/>
								<form:hidden path="tktDistList[${status.index}].opportunityScope.opportunityScopeId"/>											
								<td class="textBoxSNumric11">${tktDist.opportunityScope.serviceScope.serviceScopeName}</td>
								<td style="color: black;"><form:input id="id_tktPercnt${status.index}" path="tktDistList[${status.index}].percentTicketDistribution" class="textBoxSNumric" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);"/></td>		
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<c:choose>
				<c:when test="${hasEditSolAccess!='false'}">
				<div style="float: right;margin-right: 30px;margin-top: 10px; margin-bottom: 10px;">
					<a id="resetTktDist"	class="TabsCommonButtons">Reset</a> 
					<a id="saveTktDistForm" class="TabsCommonButtons">Save</a>		
				</div>
				</c:when>
				</c:choose>
			</form:form>
		
		</td>
	</tr>
	<tr>
		<td class="tablepanel" align="left" colspan="2" style="margin-left: 40px">
			<div class="adminDivdHeaderr1" style="float: left;width: 57%;margin-left: 40px;color: #000000">INPUT FOR SUPPORT ACTIVITY</div>
			<br><br><br>&nbsp
			<form:form id="id_supportActivityForm" modelAttribute="supportActivityForm">

			<table id="" border="0" width="58%" cellspacing="2" cellpadding="0" style="margin-left:40px; font-size: 10px;" class="tablepanel">
					<thead>
						<tr>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Call Volume Per Day (Million)</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Support Window</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">On-Call Support</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Module Name</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Total Ticket per Month</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${supportActivityForm.appMainActivityDTOList}" var="suppAct" varStatus="status">
					
						
						<tr>
							<form:hidden path="appMainActivityDTOList[${status.index}].appMainSupportActivityID"/>
							<form:hidden path="appMainActivityDTOList[${status.index}].opportinityComponent.OpportunityComponentID" />
							<td><form:select path="appMainActivityDTOList[${status.index}].callVolume" class="listBoxMed">
								<form:option value="Below 25" />
								<form:option value="Above 25" />
							</form:select></td>
							<td>
								<form:select path="appMainActivityDTOList[${status.index}].supportWindowMatrix.supportWindowMatrixId" class="listBoxMed1">
									<form:options items="${suppWindMatrixList}" itemLabel="supportWindow" itemValue="supportWindowMatrixId"/>
								</form:select>
							</td>
							<td>
								<form:select path="appMainActivityDTOList[${status.index}].onCallSupport" class="listBoxMed1">
									<form:option value="Y" label="YES"/>
									<form:option value="N" label="NO"/>
								</form:select>
							</td>
							<td>
								<%-- <form:input path="appMainActivityDTOList[${status.index}].opportinityComponent.component.componentName" disabled="true" class="textBoxSNumric"/> --%>
								<a class="textBoxSNumric1">${suppAct.opportinityComponent.component.componentName}</a>
							</td>
							<td>
								<form:input path="appMainActivityDTOList[${status.index}].ticketsPerMonth" class="textBoxSNumric2" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);"/>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<c:choose>
				<c:when test="${hasEditSolAccess!='false'}">
				<div style="float: right;margin-right: 410px;margin-top: 10px;margin-bottom: 10px;">
					<a id="resetSuppAct"	class="TabsCommonButtons">Reset</a>
					<a id="saveSuppAct" class="TabsCommonButtons">Save</a>
				</div>
				</c:when>
				</c:choose>
			</form:form>
		</td>
		<td valign="bottom">
		<!-- <div style="float: right;">
			<a id="nextAppMain" class="TabsCommonButtons">Next</a> 
			<a id="prevAppMain" class="TabsCommonButtons">Previous</a>
			<a id="reviewAppMain" class="TabsCommonButtons">Review</a>
		</div> -->
		</td>
		
	</tr>
</table>
<div style="float: right;">
			<a id="nextAppMain" class="TabsCommonButtons">Next</a> 
			<a id="reviewAppMain" class="TabsCommonButtons">Review</a>
			<a id="prevAppMain" class="TabsCommonButtons">Previous</a>			
		</div><br>




</body>
</html>

