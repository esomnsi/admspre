<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<!-- <script type="text/javascript" language="javascript" src="../js/jquery.min.js"></script>
<script src="./js/jquery-1.8.2.js"></script> -->
<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>
<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css" rel="stylesheet"	type="text/css" media="all" />
<script type="text/javascript">
	$(document).ready(function(){
		
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
		
		$('#prevMgmt').click(function() {
		    var url = "<%=request.getContextPath()%>/solution/productAppTesting"; 
		    $(location).attr('href',url);
		});
		
		$('#nextMgmt').click(function() {
			var url = "<%=request.getContextPath()%>/solution/solutionLever";
			$(location).attr('href',url);
		});
		
		/* $("#expanderHead").click(function(){
			$("#expanderContent").slideToggle();
			if ($("#expanderSign").text() == "   (click to expand)"){
				$("#expanderSign").html("   (click to collapse)");
			}
			else {
				$("#expanderSign").text("   (click to expand)");
			}
		}); */
		
		
		xTable = $('#dataTable1').dataTable({
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
		
yTable = $('#dataTable2').dataTable({
			
			'sDom': 't' ,
			"bInfo": false,
			"bFilter" : false,
			"bPaginate" : false,
			"bJQueryUI" : true,
			"bSort" : false,
				});
		
		$('#saveAppMgmtTktDistForm').click(function() {
			if(calculateSum()){
				$("#id_tktDistForm :input").prop("disabled", false); // this will enable all the elements of the form.
				$("#id_tktDistForm").attr("action", "/ADM_PRE/solution/saveAppMgmtTktDist");
				$("#id_tktDistForm").submit();
			}else{
				document.getElementById("showErrorTktDist").style.display = 'block';
				$("#showErrorTktDist").addClass('errorMessageDisp');
				$("#showErrorTktDist").html('* Sum of Ticket Distribution percentages should be 100');
			}
			
		});
		
		$('#resetAppMgmtTktDist').click(
				function(){
					$('#id_tktDistForm').each(
						function(){
						  this.reset();
						  document.getElementById("showErrorTktDist").innerHTML = '';
						  document.getElementById("showErrorTktDist").style.display = 'none';
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

<body>
<br><br><br>
<%-- <div id="mainBody" class="bodyCss">
<h4 id="expanderHead" style="cursor:pointer;" class="adminDivdHeaderr">INPUT FOR SUPPORT PROGRAM MANAGEMENT<span id="expanderSign" style="font-size: xx-small;font-style: italic;">   (click to expand)</span></h4>
<div id="expanderContent" style="display:none">
<form:form id="id_tktDistForm" modelAttribute="tktDistForm">
<table id="dataTable1" style="margin-left: 6px; font-size: 10px; width: 400px;" align="left" class="tablepanel">
					<thead>
						<tr>
							<td class="tdSubTableHead" width="82%">Service Element</td>
							<td class="tdSubTableHead" ><strong>% Distribution</strong></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tktDistForm.tktDistList}" var="tktDist" varStatus="status">
							<tr>
								<form:hidden path="tktDistList[${status.index}].ticketDistributionID"/>
								<form:hidden path="tktDistList[${status.index}].opportunityScope.opportunityScopeId"/>											
								<td style="color: black;">${tktDist.opportunityScope.serviceScope.serviceScopeName}</td>
								<td style="color: black;"><form:input id="id_tktPercnt${status.index}" path="tktDistList[${status.index}].percentTicketDistribution" class="textBoxSNumric" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);"/></td>		
							</tr>
						</c:forEach>
					</tbody>
				</table>
</form:form>
</div>
</div> --%>

<div align="center" id="messageDiv"></div>
<div>
<table width="100%" style="vertical-align: middle;height: auto;">
	<tr>
		<td class="tablepanel" width="50%" align="center" style="height: auto;">
		<div id="showErrorTktDist" align="left"
				 style="width:73%;margin-left:8px;text-align: center; -webkit-border-radius: 5px 5px 5px 5px; display: none; -moz-border-radius: 5px 5px 5px 5px; border: 1px solid #FF0000;">
				<c:if test="${not empty message}">
					<font color="red" size="2" style="margin-left: 40%;">${message}</font>
					
				</c:if>
			</div>
		<div class="adminDivdHeaderr1" style="width: 69%;float: none;color: #000000">INPUT FOR SUPPORT PROGRAM MANAGEMENT</div>
			<!-- <h4 id="expanderHead" style="cursor:pointer;" class="adminDivdHeaderr1">INPUT FOR SUPPORT PROGRAM MANAGEMENT<span id="expanderSign" style="font-size: xx-small;font-style: italic;">   (click to expand)</span></h4> -->
			<!-- <div id="expanderContent" style="display:none"> -->
			<form:form id="id_tktDistForm" modelAttribute="tktDistForm">
			<table id="" style="margin-left: 6px; font-size: 10px; width: 400px;height: auto;" class="tablepanel">
								<thead>
									<tr>
										<td class="tdSubTableHead" width="95%" style="font-weight: normal;font-size: small;">Service Element</td>
										<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">% Distribution</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${tktDistForm.tktDistList}" var="tktDist" varStatus="status">
										<tr>
											<form:hidden path="tktDistList[${status.index}].opportunityScope.serviceScope.serviceScopeName"/>
											<form:hidden path="tktDistList[${status.index}].ticketDistributionID"/>
											<form:hidden path="tktDistList[${status.index}].opportunityScope.opportunityScopeId"/>											
											<td class="textBoxSNumric11" style="width: 300px">${tktDist.opportunityScope.serviceScope.serviceScopeName}</td>
											<td style="color: black;"><form:input id="id_tktPercnt${status.index}" path="tktDistList[${status.index}].percentTicketDistribution" class="textBoxSNumric" onkeypress="return isNumericWithDecimal(event,this);" onchange="formatInput(this,id);"/></td>		
										</tr>
									</c:forEach>
								</tbody>
							</table>
				<c:choose>
				<c:when test="${hasEditSolAccess!='false'}">
				<div style="float: right;margin-right: 60px;margin-top: 10px; margin-bottom: 10px;">
					<a id="resetAppMgmtTktDist"	class="TabsCommonButtons">Reset</a> 
					<a id="saveAppMgmtTktDistForm" class="TabsCommonButtons">Save</a>		
				</div>
				</c:when>
				</c:choose>
			</form:form>
			<!-- </div> -->
		</td>
		<td class="tablepanel" align="center" style="height: auto;" valign="top">
			<div class="adminDivdHeaderr1" style="width: 69%;float: none;color: #000000">REVIEW APPLICATION MANAGEMENT</div>
			<div style="height:auto;">		
				<table id="" style="margin-left: 6px; font-size: 10px; width: 400px;" class="tablepanel">
					<thead>
						<tr>
							<td class="tdSubTableHead" width="82%" style="font-weight: normal;font-size: small;">Service Element</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">TotalBaseFTE</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">TotalBaseHours</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${servScopeData}" var="beanObject" varStatus="status">
						
							<tr bgcolor="fdf7e7">											
								<td style="color: black;height: 21px" class="textData">${beanObject.servScopeName}</td>
								<td style="color: black;font-family:Arial;font-size:12px;font-style:italic;" align="right">${beanObject.totalBaseFTE}</td>
								<td style="color: black;font-family:Arial;font-size:12px;font-style:italic;" align="right">${beanObject.totalBaseHours}</td>		
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
		</td>
	</tr>	
</table>
</div>
<div style="float: right;margin-bottom: 10px">
	<table>
		<tr bgcolor="#f7f7f7">
			<td class="tdButtonRow" bgcolor="#f7f7f7">
				<a id="nextMgmt" class="TabsCommonButtons" style="float: right">Next</a> 
				<a id="prevMgmt" class="TabsCommonButtons">Previous</a>
			</td>
		</tr>
	</table>
</div><br>

</body>
</html>