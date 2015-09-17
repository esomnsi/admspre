<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="msg" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" language="javascript"
	src="../js/jquery.dataTables.js"></script>
<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css" rel="stylesheet"
	type="text/css" media="all" />
<script type="text/javascript">
	var oTable;
	$(document).ready(function() {

		oTable = $('#dataTable').dataTable({
			"sPaginationType" : "full_numbers",
			"bJQueryUI" : true,
			"bSort" : true,
			"fnDrawCallback" : function() {
				$('#dataTable tr').click(function(event) {
					$(this).find('input[type=radio]').prop('checked', true);
				});
			}

		});
	});

	function MM_jumpMenu(targ, selObj, restore) {
		eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value
				+ "'");
		if (restore)
			selObj.selectedIndex = 0;
	}
	function clearForm() {
		document.getElementById("oppname").value = "";
		document.getElementById("cname").value = "";
		document.getElementById("min").value = "";
		document.getElementById("max").value = "";
		
		
		
	}
	function searchOpportunities() {
		var maxLength = document.getElementById("max").value;
		var minLength = document.getElementById("min").value;
		if(maxLength.length > 10)
			{
				document.getElementById("max").value = "";
				$("#showError").addClass(
				'errorMessageDisp');
				$("#showError")
				.html(
						'<msg:message code="msg.search.maxvalue.limit"/>');
		
			}
		else if(minLength.length > 10){
			document.getElementById("min").value = "";
			$("#showError").addClass(
			'errorMessageDisp');
			$("#showError")
			.html(
					'<msg:message code="msg.search.minvalue.limit"/>');
		}
		else{	
		document.forms[0].action = "/ADM_PRE/search/listOpportunities";
		document.forms[0].submit();
		}
	}

	function viewRecord() {
		document.forms[0].action = "/ADM_PRE/search/viewRecords";
		document.forms[0].submit();
	}

	var isSubmitted = false;
	function cloneOfSolution() {
		try {

			if ($('#accessRadio:checked').val() != undefined) {
				if (confirm('Do you really want to make clone of Opportunity_Solution:'
						+ $('#accessRadio:checked').val() + "?")) {
					if (!isSubmitted) {
						isSubmitted = true;
						$
								.ajax({
									type : "POST",
									url : "./cloneSolution?oppSolID="
											+ $('#accessRadio:checked').val(),
									//data : dataString,
									async : false,
									success : function(data) {
										//alert(data);
										if (data != "failure") {
											$("#showError").removeClass();
											$("#showError").addClass(
													'actionPerformedMessage');
											$("#showError")
													.html(
															'<msg:message code="msg.common.datasave.success"/>');
										} else {
											$("#showError").addClass(
													'errorMessageDisp');
											$("#showError")
													.html(
															'<msg:message code="fte.actn.fail.err.msg"/>');
										}
										isSubmitted = false;
										$("#LForm").submit();
									}
								});
					}
				} else {
					$("#showError").addClass('errorMessageDisp');
					$("#showError")
							.html(
									'*  '
											+ '<msg:message code="msg.search.selectToView"/>');
				}
			}
		} catch (e) {
			alert(e.message);
		}
	}
</script>

<style type="text/css">
.markrow {
	background-color: #ffbba9 !important;
}

div.dataTables_wrapper .ui-widget-header {
	font-weight: bold;
	width: 1100px;
	margin-left: 1px;
	color: white;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	-khtml-border-radius: 5px;
	border-radius: 5px;
	font-family: arial;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#0c3d67),
		to(#275d92));
	background-image: -webkit-linear-gradient(#0c3d67, #275d92);
	background-image: -moz-linear-gradient(#0c3d67, #275d92);
	background-image: -ms-linear-gradient(#0c3d67, #275d92);
	background-image: -o-linear-gradient(#0c3d67, #275d92);
	background-image: linear-gradient(#0c3d67, #275d92);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,
		startColorstr='#0c3d67', endColorstr='#275d92');
}

.subminApprovalTable {
	font-size: 12px;
	color: #FFF;
	margin-left: 1px;
	font-family: arial;
	border: 1px solid #265C90;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	-khtml-border-radius: 5px;
	border-radius: 5px;
	width: 1112px;
}
</style>
<c:if test="${not empty message}">
<div id="showError" class="errorMessageLogin"
	style="text-align: left; -webkit-border-radius: 5px 5px 5px 5px; -moz-border-radius: 5px 5px 5px 5px; border: 1px solid #FF0000;">
	
		<font color="red" size="2" style="margin-left: 40%;">${message}</font>
	
</div>
</c:if>

<div id="bodyDiv1" class="mainBodyDivwithoutLine">
	<form:form id="LForm" commandName="searchDTO" method="POST">
		<div id="bodyDiv" class="mainBodyDiv1">
			<table width="1112" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><table width="1112" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td><div class="tdHeaderLabel FteHeaderText">Search
										Open Solution</div></td>
							</tr>

							<tr>
								<td><table width="1112" border="0" cellspacing="2"
										cellpadding="0" class="staffingPlantopTable">
										<tr bgcolor="#f7f7f7">
											<td width="152" style="color: black;">Opportunity Name :</td>
											<td width="205"><form:input id="oppname"
													path="opportunityName" type="text"
													class="openSolutionComnInput" /></td>
											<td width="171" style="color: black;">Customer Name :</td>
											<td width="572"><form:input id="cname"
													path="customerName" type="text"
													class="openSolutionComnInput" /></td>
										</tr>
										<tr bgcolor="#f7f7f7">
											<td style="color: black;">Region :</td>
											<td><form:select path="region" id="selectregion"
													class="textBoxM">
													<form:option value="all">All</form:option>
													<c:forEach var="item" items="${regions}">
														<form:option value="${item.key}">${item.value}</form:option>
													</c:forEach>
												</form:select></td>
											<td style="color: black;">Deal Value : :</td>
											<td><table width="100%" border="0" cellspacing="0"
													cellpadding="0" class="subtable4openSoln">
													<tr>
														<td width="6%" style="color: black;">Min</td>
														<td width="18%"><form:input path="min" id="min"
																onkeypress="return isDecimal(event);" type="text"
																class="openSolutionComnSmallInput" /></td>
														<td width="6%" style="color: black;">Max</td>
														<td width="14%"><form:input path="max" id="max"
																onkeypress="return isDecimal(event);" type="text"
																class="openSolutionComnSmallInput" /></td>
														<td width="56%">
															<%-- <form:select path="currencyCode"
																id="selectcurrencycode" class="textBoxM">
																<form:option value="" label="--Select--" />
																<form:options items="${countries}" itemValue="countryId"
																	itemLabel="currencyCode" />
															</form:select></td> --%>
													</tr>
												</table></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td>
									<div class="tabsDefineScoprButtons" style="margin-top: 0px;">
										<div class="TabsCommonButtons" id="srch"
											onclick="searchOpportunities()" style="">SEARCH</div>
										<div class="TabsCommonButtons"
											onclick="javascript:clearForm()">CLEAR</div>
									</div>
								</td>
							</tr>
							<tr>
								<td></td>
							</tr>

						</table></td>
				</tr>
			</table>
			<br>
			<div class="tdHeaderLabel"
				style="width: 1096px; margin-left: 1px; margin-bottom: 3px;">Open
				Solutions</div>

			<table id="dataTable" width="100%" border="0" align="center"
				cellpacing="1" class="subminApprovalTable">
				<thead>
					<tr class="subminApprovalTdProperty">
						<td class="tdSubTableHead">Select</td>
						<td class="tdSubTableHead" nowrap="nowrap">Opportunity ID</td>
						<td class="tdSubTableHead" nowrap="nowrap">Solution ID</td>
						<td class="tdSubTableHead" nowrap="nowrap">Opportunity Name</td>
						<td class="tdSubTableHead" nowrap="nowrap">Customer Name</td>
						<td class="tdSubTableHead">Region</td>
						<td class="tdSubTableHead" nowrap="nowrap">Deal Value</td>
						<!-- <td class="tdSubTableHead">DBR</td>
						 --><td class="tdSubTableHead">Status</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${searchList}" var="search" varStatus="status">
						<tr bgcolor="#f1f1f1">
							<td><form:radiobutton id="accessRadio" path="selected"
									value="${search.opportunityID}_${search.solutionID}" /></td>
							<td style="color: black;">${search.opportunityID}</td>
							<td style="color: black;">${search.solutionID}</td>
							<td style="color: black;">${search.opportunityName}</td>
							<td style="color: black;">${search.customerName}</td>
							<td style="color: black;">${search.region}</td>
							<td style="color: black;">${search.min}</td>
							<%-- <td style="color: black;">${search.dbr}</td>
							 --%><td style="color: black;">${search.status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>


			<tr>
				<td colspan="10" class="tdButtonRow" nowrap="nowrap">
					<%-- <c:if test="${role_guest != 'true'}">
						<a class="TabsCommonButtons" onclick="cloneOfSolution();">Clone</a>
					</c:if> --%>
				 <a	class="TabsCommonButtons" onclick="javascript:viewRecord();">View</a>
				</td>
			</tr>
		</div>
	</form:form>
</div>


