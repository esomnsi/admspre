<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="msg" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../js/grid.locale-en.js" type="text/javascript"></script>
<script src="../js/jquery.jqGrid.min.js" type="text/javascript"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" media="screen"
	type="text/css" />
<link href="../css/ui.jqgrid.css" rel="stylesheet" media="screen"
	type="text/css" />
<link href="../css/jquery.multiSelect.css" rel="stylesheet"
	type="text/css" />

<!-- <script type="text/javascript" src="../js/jquery-ui-1.8.6.custom.min.js"></script>
 -->
<script type="text/javascript">
	//var jq = jQuery.noConflict();
	function toggleDisplay(value) {
		if ($("#oppDetails").css('display') == "none") {
			$("#oppDetails").css('display', "");
		} else {
			$("#oppDetails").css('display', "none");
		}
	}
	$(function() {

		var areaList = $.ajax({
			url : './areaList',
			async : false,
			success : function(data, result) {
				if (!result)
					alert('Failure to retrieve the Areas.');
			}
		}).responseText;
		var typeList = $.ajax({
			url : './typeList',
			async : false,
			success : function(data, result) {
				if (!result)
					alert('Failure to retrieve the types.');
			}
		}).responseText;
		var impactList = $.ajax({
			url : './impactList',
			async : false,
			success : function(data, result) {
				if (!result)
					alert('Failure to retrieve the imapcts.');
			}
		}).responseText;

		$("#grid")
				.jqGrid(
						{
							url : './crud',
							datatype : 'json',
							mtype : 'GET',
							colNames : [ 'solutionAdrid', 'Statement',
									'Category', 'Area', 'Type', 'Impact',
									'Weightage' ],
							colModel : [
									{
										name : 'solutionAdrid',
										index : 'solutionAdrid',
										width : 55,
										editable : true,
										editoptions : {
											readonly : true,
											size : 10
										},
										hidden : true
									},
									{
										name : 'adrStatement',
										index : 'adrStatement',
										width : 60,
										editable : true,
										editrules : {
											required : true
										},
										editoptions : {
											size : 30,
											maxlength : 255
										},
										searchoptions : {
											sopt : [ 'cn' ]
										}
									},
									{
										name : 'adrCategory',
										index : 'adrCategory',
										width : 15,
										editable : true,
										edittype : "select",
										stype : "select",
										editrules : {
											required : true
										},
										editoptions : {
											value : "ASSUMPTION:ASSUMPTION;DEPENDENCY:DEPENDENCY;RISK:RISK",
											size : 30
										},
										sortable : true,
										searchoptions : {
											sopt : [ 'eq', 'ne' ]
										}
									}, {
										name : 'adrArea',
										index : 'adrArea',
										width : 12,
										editable : true,
										edittype : "select",
										stype : "select",
										editrules : {
											required : true
										},
										editoptions : {
											value : areaList,
											size : 10
										},
										searchoptions : {
											sopt : [ 'eq', 'ne' ]
										}
									}, {
										name : 'adrType',
										index : 'adrType',
										width : 10,
										editable : true,
										edittype : "select",
										stype : "select",
										editrules : {
											required : true
										},
										editoptions : {
											value : typeList,
											size : 10
										},
										searchoptions : {
											sopt : [ 'eq', 'ne' ]
										}
									}, {
										name : 'adrImpactString',
										index : 'adrImpactString',
										width : 10,
										editable : true,
										edittype : "select",
										stype : "select",
										editrules : {
											required : true
										},
										editoptions : {
											value : impactList,
											size : 10
										},
										searchoptions : {
											sopt : [ 'eq', 'ne' ]
										}
									}, {
										name : 'adrWeightage',
										index : 'adrWeightage',
										width : 10,
										editable : true,
										editrules : {
											number : true,
											required : true,
											minValue : 0,
											maxValue : 9999999
										},
										sorttype : 'number',
										formatter : 'number',
										editoptions : {
											size : 10,
											maxlength : 10
										},
										searchtype : "number",
										searchoptions : {
											sopt : [ 'eq', 'ne', 'gt', 'lt' ]
										}
									} ],
							postData : {},
							rowNum : 10,
							rowList : [ 5, 10, 20, 30, 40, 50 ],
							height : 200,
							autowidth : true,
							rownumbers : true,
							pager : '#pager',
							sortname : 'adrCategory',
							viewrecords : true,
							gridview : true,
							sortorder : "asc",
							caption : "",
							emptyrecords : "Empty records",
							loadonce : true,
							//multiselect:true,
							editurl : "./cu",
							loadComplete : function() {
								$("#message").text('');
								$("#message").removeClass();
								//								$("#message").css('color', '#000000');
							},
							jsonReader : {
								root : "solutionADRList",
								page : "page",
								total : "total",
								records : "records",
								repeatitems : false,
								cell : "cell",
								id : "solutionAdrid"
							},

						});
		$("#grid").jqGrid('navGrid', '#pager', {
			edit : false,
			add : false,
			del : false,
			search : true
		}, {}, {
			closeAfterAdd : true
		}, {
			url : './delete'
		}, {
			sopt : [ 'eq', 'ne' ], //, 'lt', 'gt', 'cn', 'bw', 'ew'
			closeOnEscape : true,
			multipleSearch : true,
			closeAfterSearch : true
		});

		/* jQuery("#grid").jqGrid('filterToolbar', {
			stringResult : true,
			searchOnEnter : true
		}); */
		/* $("#grid")
			.setGridParam(
					{
						postData : {
							filters : '{"groupOp":"AND","rules":[{"field":"adrCategory","op":"bw","datatype":"select","data":"A:a"}]}'
						},
						search : true
					}).trigger('reloadGrid'); */

		$("#grid").navButtonAdd('#pager', {
			caption : "Add",
			buttonicon : "ui-icon-plus",
			onClickButton : addRow,
			position : "last",
			title : "Add new Record",
			cursor : "pointer"
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Edit",
			buttonicon : "ui-icon-pencil",
			onClickButton : editRow,
			position : "last",
			title : "Edit Record",
			cursor : "pointer"
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Delete",
			buttonicon : "ui-icon-trash",
			onClickButton : deleteRow,
			position : "last",
			title : "Delete Record",
			cursor : "pointer"
		});
		$("#grid").navButtonAdd('#pager', {
			caption : "Export",
			buttonicon : "ui-icon-document",
			onClickButton : exportData,
			position : "last",
			title : "Export to Excel",
			cursor : "pointer"
		});
	});
	function exportData() {
		try {
			var columnNms = $("#grid").jqGrid('getGridParam', 'colNames');
			var theads = "";
			for ( var indx = 2; indx < columnNms.length; indx++) {
				theads = theads + columnNms[indx] + ",";
			}
			var gridData = new Array();
			gridData = jQuery("#grid").getDataIDs(); // Get All IDs
			var rowData = jQuery("#grid").getRowData(gridData[0]); // Get First row to get the labels
			var colNames = new Array();
			indx = 0;
			for ( var i in rowData) {
				colNames[indx++] = i;
			}
			var data = theads + "\n";
			for ( var rowIndx = 0; rowIndx < gridData.length; rowIndx++) {
				rowData = jQuery("#grid").getRowData(gridData[rowIndx]); // get each row
				for ( var colIndx = 1; colIndx < colNames.length; colIndx++) {
					data += rowData[colNames[colIndx]] + ","; // output each column as tab delimited
				}
				data += "\n";
			}
			location.href = 'data:application/csv;charset=UTF-8,'
					+ encodeURIComponent(data);
		} catch (e) {
			//alert(e.message);
		}

	}
	jQuery().ready(function() {
		//fillGrid();
	});
	function isNotAuthorized() {
		if ("${hasEditSolAccess}" == 'false') {
			$("#message").addClass('errorMessageDisp');
			$("#message").html(
					'<msg:message code="msg.common.err.notauthorized"/>');
			return true;
		}
		return false;
	}
	function addRow() {
		$("#message").text('');
		$("#message").removeClass();

		if (isNotAuthorized()) {
			return false;
		}
		// Get the currently selected row
		$("#grid").jqGrid(
				'editGridRow',
				'new',
				{
					url : "./cu",
					mtype : 'POST',
					editData : {},
					recreateForm : true,
					beforeShowForm : function(form) {
						//alert(form.serialize());
					},
					closeAfterAdd : true,
					reloadAfterSubmit : false,
					afterSubmit : function(response, postdata) {
						var result = eval('(' + response.responseText + ')');
						var errors = "";

						if (!result.success) {
							for ( var i = 0; i < result.message.length; i++) {
								errors += result.message[i] + "";
							}
						} else {
							$("#message").addClass('actionPerformedMessage');
							$("#message").text(
									'<msg:message code="adr.added.success"/>');

						}
						// only used for adding new records
						return [ result.success, errors, result.id ];
					}
				});

	}

	function editRow() {
		// Get the currently selected row
		$("#message").text('');
		$("#message").removeClass();
		var row = $("#grid").jqGrid('getGridParam', 'selrow');
		if (isNotAuthorized()) {
			return false;
		}
		if (row != null)
			$("#grid")
					.jqGrid(
							'editGridRow',
							row,
							{
								url : "./cu",
								editData : {},
								mtype : 'POST',
								recreateForm : true,
								beforeShowForm : function(form) {
								},
								closeAfterEdit : true,
								reloadAfterSubmit : false,
								afterSubmit : function(response, postdata) {
									var result = eval('('
											+ response.responseText + ')');
									var errors = "";

									if (result.success == false) {
										for ( var i = 0; i < result.message.length; i++) {
											errors += result.message[i] + "";
										}
									} else {
										$("#message").addClass(
												'actionPerformedMessage');
										$("#message")
												.text(
														'<msg:message code="adr.update.success"/>');

									}

									return [ result.success, errors, null ];
								}
							});
		else {
			$("#message").addClass('errorMessageDisp');
			$("#message").text('<msg:message code="adr.err.select.record"/>');
		}
	}

	function deleteRow() {
		$("#message").text('');
		$("#message").removeClass();
		//Not authorized code here
		if (isNotAuthorized()) {
			return false;
		}
		// Get the currently selected row
		var row = $("#grid").jqGrid('getGridParam', 'selrow');
		// A pop-up dialog will appear to confirm the selected action
		if (row != null)
			$("#grid")
					.jqGrid(
							'delGridRow',
							row,
							{
								url : './delete?solutionAdrid=' + row,
								recreateForm : true,
								mtype : 'POST',
								beforeShowForm : function(form) {
									//change title
									$(".delmsg").replaceWith(
											'<span style="white-space: pre;">'
													+ 'Delete selected record?'
													+ '</span>');

									//hide arrows
									$('#pData').hide();
									$('#nData').hide();
								},
								reloadAfterSubmit : true,
								closeAfterDelete : true,
								afterSubmit : function(response, postdata) {
									var result = eval('('
											+ response.responseText + ')');
									var errors = "";

									if (result.success == false) {
										for ( var i = 0; i < result.message.length; i++) {
											errors += result.message[i] + "";
										}
									} else {
										$("#message").addClass(
												'actionPerformedMessage');
										$("#message")
												.text(
														"<msg:message code='adr.del.success'/>");
										//$("#grid").trigger('reloadGrid');
									}
									// only used for adding new records
									var new_id = null;

									return [ result.success, errors, new_id ];
								}
							});
		else {
			$("#message").addClass('errorMessageDisp');
			$("#message").text("<msg:message code='adr.err.select.record'/>");

		}
	}
</script>





<%-- <form:form id="form" method="POST" commandName="manageADRForm"> --%>

	<div id="serviceBucket" style="margin-bottom:10px;">
			<jsp:include page="./serviceBucket.jsp"/>
	</div>
		
<div id="mainBody" class="bodyCss">
	<div id="bodyDiv1" class="mainBodyDiv1"
		style="width: 100%; mafloat: left; margin-bottom: 5px;">
		<h1 class="tdHeaderLabel" style="margin-top: 0px; margin-bottom: 0px;">
			<msg:message code="adr.header.msg" />
		</h1>
	</div>
	<table width="1112" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center"><div id="bodyDiv" class="mainBodyDiv1">
					<jsp:include page="opportunitySummary.jsp"></jsp:include>
				</div></td>
		</tr>
		

	</table>

	<c:if test="${empty manageADRForm.solutionID}">
		<div align="center" class="errorMessageDisp">Solution ID not
			found</div>
	</c:if>
	<div id="message" readonly="readonly" align="center"></div>
	<div id="bodyDiv1" class="mainBodyDivwithoutLine" style="width: 100%;">

		<div id="bodyDiv4" class="mainBodyDiv1"
			style="width: 100%; float: left; margin-left: 0px; padding-bottom: 1px;">
			<!-- Assumption form started -->
			<div id="jqgrid" style="width: 100%;">
				<table id="grid"></table>
				<div id="pager"></div>
			</div>
		</div>
		<c:choose>
			<c:when test="${hasEditSolAccess!='false'}">
				<div class="portfolioButtons" id="saveNext"
					style="margin-top: 0px; margin-bottom: 6px;"
					onclick="window.location = '../approval/loadApproverPage';">SUBMIT</div>
			</c:when>
			<c:otherwise>
				<div class="portfolioButtons"
					style="margin-top: 0px; margin-bottom: 6px;"
					onclick="location.href = '../approval/loadApproverPage';">Next</div>
			</c:otherwise>
		</c:choose>
	</div>