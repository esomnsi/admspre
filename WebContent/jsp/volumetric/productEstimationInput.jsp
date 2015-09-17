
<script type="text/javascript">
	var inputParamStr = "opportunityId=" + $("#opportunityId").val()
			+ "&serviceFunctionCode=" + $("#serviceFunctionCode").val();

	function loadDistributionTable() {
		$("#effortDistributionGrid")
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
										width : 300,
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
												title = "Please edit this percentage to make the sum 100.";
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
												var totalEffort = 0;
												$("table[id^='grid']")
														.each(
																function() {
																	totalEffort += $(
																			this)
																			.jqGrid(
																					'getCol',
																					'totalEffortDerived',
																					false,
																					'sum');
																});
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
							caption : "Service Element Effort Distribution - <i>Please click on this box if you wish to save the Total Effort</i>",
				            hiddengrid: true,
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
								var total = $("#effortDistributionGrid")
										.jqGrid('getCol', 'defaultValue',
												false, 'sum');

								var totalEditable = $("#effortDistributionGrid")
										.jqGrid('getCol',
												'editablePercentDistribution',
												false, 'sum');

								var totalEffortForServiceElement = $(
										"#effortDistributionGrid").jqGrid(
										'getCol', 'effortForServiceElement',
										false, 'sum');

								$("#effortDistributionGrid")
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
								var totalEffort = 0;
								$("table[id^='grid']").each(
										function() {
											totalEffort += $(this).jqGrid(
													'getCol',
													'totalEffortDerived',
													false, 'sum');
										});

								$("#effortDistributionGrid")
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
								var total = $("#effortDistributionGrid")
										.jqGrid('getCol', 'defaultValue',
												false, 'sum');

								var totalEditable = $("#effortDistributionGrid")
										.jqGrid('getCol',
												'editablePercentDistribution',
												false, 'sum');
								var totalEffortForServiceElement = $(
										"#effortDistributionGrid").jqGrid(
										'getCol', 'effortForServiceElement',
										false, 'sum');

								$("#effortDistributionGrid")
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
	
	function saveEffortDistribution() {
		if(!$("#effortDistributionGrid").is(":visible")){
			return;
		}
		var total = $("#effortDistributionGrid").jqGrid('getCol',
				'editablePercentDistribution', false, 'sum');

		if(Number(total) > 0){
			if ((Number(total).toFixed(2)) == (Number(100))) {
				var allRowsInGrid = $("#effortDistributionGrid")
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
			} else {
				alert("Custom % Distribution should add up to 100.");
				return;
			}
		}else{
			return;
		}
	}

	function populateTotalDistribution() {
		$("#effortDistributionGrid").trigger('reloadGrid');
	}
</script>

<div id="effortDistributionDiv" style="width: 100%;" title="Show Effort Distribution for Service Elements">
	<table id="effortDistributionGrid"></table>
</div>
