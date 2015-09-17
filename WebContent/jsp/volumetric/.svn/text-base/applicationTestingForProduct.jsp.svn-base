<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../js/jquery.jqGrid-4.4.3.js" type="text/javascript"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" media="screen"
	type="text/css" />
<link href="../css/ui.jqgrid.css" rel="stylesheet" media="screen"
	type="text/css" />
<link href="../css/jquery.multiSelect.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
var success_message = "Data saved successfully.";

$(document).ready(function() {
	loadTestingDistributionTable();
	
	$("#saveTestingEffort").on("click",function() {

		var allRowsInGrid = $("#testingEffortDistributionGrid")
					.jqGrid('getRowData');

		$.ajax({
			type : "POST",
			url : "./savePercentDistribution",
			data : JSON.stringify(allRowsInGrid),
			dataType : "text",
			contentType : "application/json",
			success : function(
					response) {
				loadMessageInDiv(success_message,"messageDiv","message");
			},
			error : function(e) {
				loadMessageInDiv(e,"messageDiv","errorMessageDisp");
			}
		});
	});
	
	$('#appTestingPrev').click(function() {
		var url = "<%=request.getContextPath()%>/solution/applicationMaintenanceNew";
		$(location).attr('href',url);
	});
	
	$('#appTestingNext').click(function() {
		var url = "<%=request.getContextPath()%>/solution/applicationManagement";
		$(location).attr('href',url);
	});
});

function isEditable() {
	if ("${hasEditSolAccess}" == 'false') {
		return false;
	}
	return true;
}

function loadTestingDistributionTable() {
	var inputParamStr = "opportunityId=" + $("#opportunityId").val()
	+ "&serviceFunctionCode=" + $("#serviceFunctionCode").val();
	$("#testingEffortDistributionGrid")
		.jqGrid(
			{
				url : './loadTicketDistribution?' + inputParamStr,
				datatype : 'json',
				mtype : 'POST',
				colNames : [ 'Service Scope ID',
						'Opportunity Scope ID',
						'Selected Service Elements',
						'Service Elements',
						'Default % Distribution',
						'Custom % Distribution',
						'Effort (in Hours)' ],
				colModel : [
						{
							name : 'serviceScopeID',
							index : 'serviceScopeID',
							hidden : true
						},
						{
							name : 'opportunityScopeID',
							index : 'opportunityScopeID',
							hidden : true
						},
						{
							name : 'selected',
							index : 'selected',
							width : 80,
							editable : false,
							edittype : 'checkbox',
							editoptions : {
								value : "True:False"
							},
							formatter : "checkbox",
							formatoptions : {
								disabled : true
							},
							cellattr : function(rowId, val) {
								var titleStr = 'This Service Element was ';
								if ($(val).val() == 'n')
									titleStr += 'not ';
								titleStr += 'selected in previous step.';
								return 'title="' + titleStr + '" class="inputtextTabsNotEditable"';
							}
						},
						{
							name : 'serviceElementName',
							index : 'serviceElementName',
							width : 200,
							cellattr: function(){
								return 'class="inputtextTabsNotEditable"';
							}
						},
						{
							name : 'defaultValue',
							index : 'defaultValue',
							width : 150,
							formatter : 'number',
							formatoptions : {
								decimalSeparator : ".",
								thousandsSeparator : "",
								decimalPlaces : 2,
								defaultValue : '0.00'
							},
							cellattr : function() {
								return 'title="This is the default percentage distribution." class="inputtextTabsNotEditable"';
							}
						},
						{
							name : 'editablePercentDistribution',
							index : 'editablePercentDistribution',
							width : 150,
							formatter : 'number',
							formatoptions : {
								decimalSeparator : ".",
								thousandsSeparator : "",
								decimalPlaces : 2,
								defaultValue : '0.00'
							},
							editable : isEditable(),
							edittype : 'text',
							editoptions : {
								maxlength : 10
							},
							sortable : false,
							cellattr : function(rowId,val,rawObject,cm,rdata) {
								var className = "inputtextTabsNotEditable not-editable-cell";
								var title = "This can not be edited since this Service Element was not selected in previous step.";
								if(rdata.selected == 'Y'){
									className = "inputtextTabs";
									title = "You can edit this percentage.";
								}
								return 'title="'+title+'" class="'+className+'"';
							}
						},
						{
							name : 'effortForServiceElement',
							index : 'effortForServiceElement',
							width : 150,
							formatter : function(cellvalue,
									options, rowObject) {
								if (cellvalue == null) {
									var totalEffort = $("#totalEffort").val();
									cellvalue = Number(
											(rowObject["editablePercentDistribution"] * totalEffort) / 100)
											.toFixed(2);
								}
								return cellvalue;
							},
							formatoptions : {
								decimalSeparator : ".",
								thousandsSeparator : "",
								decimalPlaces : 2,
								defaultValue : '0.00'
							},
							sortable : false,
							cellattr : function() {
								return 'title="Calculated as percentage (in previous column) of the total effort of all the CBIO components." class="inputtextTabsNotEditable"';
							}
						} ],
				align : 'center',
				caption : "Service Element Effort Distribution",
	            /* hiddengrid: true, */
				headertitles : true,
				postData : {},
				rowNum : 0,
				pgbuttons : false,
				pgtext : null,
				shrinkToFit : false,
				//width : 1100,
				autowidth : true,
				rownumbers : true,
				sortname : 'serviceScopeID',
				viewrecords : true,
				gridview : true,
				footerrow : true,
				sortorder : "desc",
				emptyrecords : "No records",
				loadonce : false,
				cellEdit : true,
				cellsubmit : 'clientArray',
				jsonReader : {
					root : "ticketDistributionList",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					cell : "cell",
					id : "serviceScopeID"
				},
				loadComplete : function(data) {
					var total = $("#testingEffortDistributionGrid")
							.jqGrid('getCol', 'defaultValue',
									false, 'sum');

					var totalEditable = $("#testingEffortDistributionGrid")
							.jqGrid('getCol',
									'editablePercentDistribution',
									false, 'sum');

					var totalEffortForServiceElement = $(
							"#testingEffortDistributionGrid").jqGrid(
							'getCol', 'effortForServiceElement',
							false, 'sum');

					$("#testingEffortDistributionGrid")
							.jqGrid(
									'footerData',
									'set',
									{
										serviceElementName : '<label align="right">TOTAL:</label>',
										defaultValue : total
												.toFixed(2),
										editablePercentDistribution : totalEditable
												.toFixed(2),
										effortForServiceElement : totalEffortForServiceElement
												.toFixed(2)
									});
				},
				afterSaveCell : function(rowid, cellname, value,
						iRow, iCol) {
					/*Calculating Effort*/
					var totalEffort = $("#totalEffort").val();

					$("#testingEffortDistributionGrid")
							.jqGrid(
									'setCell',
									rowid,
									(iCol + 1),
									Number(
											(value * totalEffort) / 100)
											.toFixed(2), false,
									false, true);
					/*End*/

					/*Calculating Totals*/
					var total = $("#testingEffortDistributionGrid")
							.jqGrid('getCol', 'defaultValue',
									false, 'sum');

					var totalEditable = $("#testingEffortDistributionGrid")
							.jqGrid('getCol',
									'editablePercentDistribution',
									false, 'sum');
					var totalEffortForServiceElement = $(
							"#testingEffortDistributionGrid").jqGrid(
							'getCol', 'effortForServiceElement',
							false, 'sum');

					$("#testingEffortDistributionGrid")
							.jqGrid(
									'footerData',
									'set',
									{
										serviceElementName : '<label align="right">TOTAL:</label>',
										defaultValue : total
												.toFixed(2),
										editablePercentDistribution : totalEditable
												.toFixed(2),
										effortForServiceElement : totalEffortForServiceElement
												.toFixed(2)
									});
					/*End*/
				}
			});
}

</script>

<br>
<!-- <div id="showError" class="errorMessageLogin"
	style="text-align: left; -webkit-border-radius: 5px 5px 5px 5px; -moz-border-radius: 5px 5px 5px 5px; border: 1px solid #FF0000; width: 100%;">
</div> -->
<br>
<div id="messageDiv"></div>
<input type="hidden" name="solutionId" id="solutionId"
	value="${solutionId}" />
<input type="hidden" name="opportunityId" id="opportunityId"
	value="${opportunityId}" />
<input type="hidden" name="serviceFunctionCode" id="serviceFunctionCode"
	value="APP_TEST" />
<div
	style="width: 1112px; background-color: White; border: medium none; align: center;">
	<br> <br>
	<label class="tdLabel">Total Effort From Application Development & Maintenance: </label> <input
								type="text" style="width: 10%;" class="inputtextTabsNotEditable"
								name="totalEffort" id="totalEffort"
								readonly="readonly" value="${appTestResponse.totalEffort}" /><label
								class="tdLabel">hours</label>
	<div>&nbsp;</div>
	<div id="testingEffortDistributionDiv" style="width: 100%;" title="Show Effort Distribution for Service Elements">
		<table id="testingEffortDistributionGrid"></table>
	</div>
</div>

<br>

<div style="float: right">
	<table width="100%">

		<tr bgcolor="#f7f7f7">
			<td class="tdButtonRow" align="right" bgcolor="#f7f7f7">
				<a id="appTestingNext" class="TabsCommonButtons">Next</a>
				<c:choose>
					<c:when test="${hasEditSolAccess!='false'}">
					<a id="saveTestingEffort" class="TabsCommonButtons">Save</a>
					</c:when>
				</c:choose>
	      		<!-- <a id="movetoApaAdmSupp" class="TabsCommonButtons" >Move to APA</a> -->
				<a id="appTestingPrev" class="TabsCommonButtons">Previous</a></td>
		</tr>
	</table>
</div>