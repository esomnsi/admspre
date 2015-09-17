<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="../js/grid.locale-en.js" type="text/javascript"></script>
<script src="../js/jquery.jqGrid-4.4.3.js" type="text/javascript"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" media="screen"
	type="text/css" />
<link href="../css/ui.jqgrid.css" rel="stylesheet" media="screen"
	type="text/css" />
<link href="../css/jquery.multiSelect.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	var accordianSelectedClassName = "heading current";
	var saveObjectArr = [];
	var customerBaseDropDownName = "CustomerBase";
	var thirdPartyDropDownName = "CBIOImpacted3PPNodes";
	var success_message = "Data saved successfully.";

	$(document)
			.ready(
					function() {

						$(document)
								.ajaxStart(
										function() {
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
										});

						$(document).ajaxStop(function() {
							$.unblockUI();
						});
						
						$("table[id^='grid']").each(
									function() {
							$("#messageDiv").removeClass();
							$("#messageDiv").html("");
							
							/* var serviceScopeId = $(this)
								.attr("serviceScopeId"); */
							var componentId = $(this)
									.attr("componentId");
							var oppComponentId = $(this).attr(
									"oppComponentId");
							var paramStr = "componentId="
									+ componentId
									+ "&oppComponentId="
									+ oppComponentId;

							$(this).jqGrid(
								{
									url : './activities?'
											+ paramStr,
									datatype : 'json',
									mtype : 'POST',
									colNames : [
									        'productEstimationForSolutionID',
											'productActivitiesComplexityAssumptionsID',
											'Configuration Type',
											'Configuration Area',
											'Activity Name',
											'Complexity',
											'Factor (Person Hours)',
											'Per Month Event Count',
											'Total Calculated Effort' ],
									colModel : [
											{
												name : 'productEstimationForSolutionID',
												index : 'productEstimationForSolutionID',
												hidden : true
											},{
												name : 'productActivitiesComplexityAssumptionsID',
												index : 'productActivitiesComplexityAssumptionsID',
												hidden : true
											},
											{
												name : 'configurationType',
												index : 'configurationType',
												cellattr: function(){
													return 'class="inputtextTabsNotEditable"';
												}
											},
											{
												name : 'configurationArea',
												index : 'configurationArea',
												cellattr: function(){
													return 'class="inputtextTabsNotEditable"';
												}
											},
											{
												name : 'activityName',
												index : 'activityName',
												sortable: false,
												cellattr: function(){
													return 'class="inputtextTabsNotEditable"';
												}
											},
											{
												name : 'estimationComplexityName',
												index : 'estimationComplexityName',
												sortable: false,
												cellattr: function(){
													return 'class="inputtextTabsNotEditable"';
												}
											},
											{
												name : 'estimationEffortFactorValue',
												index : 'estimationEffortFactorValue',
												formatter : 'number',
												formatoptions:{decimalSeparator:".", thousandsSeparator: "", decimalPlaces: 2, defaultValue: '0.00'},
												sortable: false,
												cellattr: function(rowId,val){
													return 'title="'+val+' Person Hours needed to complete the activity" class="inputtextTabsNotEditable"';
												}
											},
											{
												name : 'perMonthEventCountInput',
												index : 'perMonthEventCountInput',
												formatter : 'number',
												formatoptions: {decimalSeparator:".", thousandsSeparator: "", decimalPlaces: 2, defaultValue: '0.00'},
												editable : isEditable(),
												edittype : 'text',
												editoptions : {
													maxlength : 10
												},
												sortable: false,
												cellattr: function(){
													return 'title="Enter the event count probable to occur in a month" class="inputtextTabs"';
												}
											},
											{
												name : 'totalEffortDerived',
												index : 'totalEffortDerived',
												formatter : 'number',
												formatoptions:{decimalSeparator:".", thousandsSeparator: "", decimalPlaces: 2, defaultValue: '0.00'},
												sortable: false,
												cellattr: function () {
													return ' title="Auto-calculated Total Effort = Per Month Event Count*(Factor)*(Steady State Duration)*(the factors for Customer Base, 3PP nodes, etc.)" class="inputtextTabsNotEditable"';
												}
											} ],
									caption: $(this).attr("captionName"),
									headertitles: true,
									postData : {},
									rowNum : 0,
									pgbuttons : false,
									pgtext : null,
									height : 200,
									//width : null,
									shrinkToFit: false,
									autowidth: true,
									rownumbers : true,
									sortname : 'productEstimationActivitiesID',
									viewrecords : true,
									gridview : true,
									pager : '#pager'+ componentId,
									footerrow : true,
									sortorder : "desc",
									emptyrecords : "No records",
									loadonce : false,
									cellEdit : true,
									cellsubmit : 'clientArray',
									jsonReader : {
										root : "productEstimationActivityList",
										page : "page",
										total : "total",
										records : "records",
										repeatitems : false,
										cell : "cell",
										id : "productActivitiesComplexityAssumptionsID"
									},
									loadComplete : function(
											data) {
										var total = $(
												this)
												.jqGrid(
														'getCol',
														'totalEffortDerived',
														false,
														'sum');

										$(this)
												.jqGrid(
														'footerData',
														'set',
														{
															estimationComplexityName : '<label align="right">TOTAL:</label>',
															totalEffortDerived : total
														});
									},
									grouping : true,
									groupingView : {
										groupField : [
												'configurationType'/* ,
												'configurationArea' */ ],
										groupColumnShow : [
												false/* ,
												false  */],
										groupCollapse : true,
										groupOrder : [
												'asc'/* ,
												'asc' */ ],
										groupSummary : [
												true/* ,
												true */ ]
									},
									afterSaveCell: function(rowid, cellname, value, iRow, iCol){
										var totalValue = (value) * $(this).jqGrid("getCell", rowid, 'estimationEffortFactorValue') * $("#steadyStateDuration").val();
										$("select[id^='param_']  option:selected").each(function() {
											totalValue = totalValue * $(this).attr("calculationFactor");
										});
										$(this).jqGrid("setCell", rowid, 'totalEffortDerived', totalValue);
										
										var total = $(
												this)
												.jqGrid(
														'getCol',
														'totalEffortDerived',
														false,
														'sum');
										$(this)
										.jqGrid(
												'footerData',
												'set',
												{
													estimationComplexityName : '<label align="right">TOTAL:</label>',
													totalEffortDerived : total
												});
										
										populateTotalDistribution();
									}
								});
						});

						loadDistributionTable();
						
						$("#appDevSave").on("click",function() {
							var changedArr = $("table[id^='grid']").getChangedCells('all');

							var auxiliaryParamMap = {};

							$("select[id^='param_']")
									.each(
											function() {
												auxiliaryParamMap[(($(this)
														.attr('id'))
														.split('param_'))[1]] = ($(
														this)
														.val()+"__"+ $(this).find("option:selected").attr("selectedKey"));
											});

							var dataJson = {
								solutionId : $("#solutionId")
										.val(),
								auxiliaryParamMap : auxiliaryParamMap,
								persistentArray : changedArr
							};

							$.ajax({
									type : "POST",
									url : "./saveEstimation",
									data : JSON
											.stringify(dataJson),
									dataType: "text",
									contentType : "application/json",
									success : function(
											response) {
										loadMessageInDiv(success_message,"messageDiv","message");
										$("table[id^='grid']").each(function() {
											$(this).trigger('reloadGrid');
										});
									},
									error : function(e) {
										loadMessageInDiv(e,"messageDiv","errorMessageDisp");
									}
								});
								saveEffortDistribution();
							});
								$("select[id^='param_']").on("change", function(){
									var selectedKey = "";
									$(this).children().each(function() {
										if($(this).attr("selectedKey") != ""){
											selectedKey = $(this).attr("selectedKey");
											return false;
										}
									});
									$("option:selected",this).attr("selectedKey",selectedKey);
									$("table[id^='grid']").each(function(){
										var dataIds = $(this).jqGrid('getDataIDs');
										for(var count=0; count<dataIds.length; count++){
											var rowid = dataIds[count];
											var value = $(this).jqGrid("getCell", rowid, "perMonthEventCountInput");
											if(value > 0){
												var totalValue = (value) * $(this).jqGrid("getCell", rowid, 'estimationEffortFactorValue') * $("#steadyStateDuration").val();
												$("select[id^='param_']  option:selected").each(function() {
													totalValue = totalValue * $(this).attr("calculationFactor");
												});
												
												// Mark the rows as edited so as to save them
												$("#" + $.jgrid.jqID(rowid)).addClass("edited");
												$(this).jqGrid("setCell", rowid, 'totalEffortDerived', totalValue, "dirty-cell");
												
												var total = $(
														this)
														.jqGrid(
																'getCol',
																'totalEffortDerived',
																false,
																'sum');
												$(this)
												.jqGrid(
														'footerData',
														'set',
														{
															estimationComplexityName : '<label align="right">TOTAL:</label>',
															totalEffortDerived : total
														});
											}
										}
									});
									populateTotalDistribution();
								});
								
						$('#appDevPrev').click(function() {
						    var url = "<%=request.getContextPath()%>/opportunity/defineScope";    
					    	$(location).attr('href',url);
						});
						
						$('#appDevNext').click(function() {
							var url = "<%=request.getContextPath()%>/solution/applicationMaintenanceNew";
				    		$(location).attr('href',url);
						});
						$('#movetoApaAdmSupp').click(function() {
							var url = "<%=request.getContextPath()%>/solution/APAnalysis";    
					    	$(location).attr('href',url);
						});
					});

	function isEditable() {
		if ("${hasEditSolAccess}" == 'false') {
			return false;
		}
		return true;
	}

</script>
<br>
<!-- <div id="showError" class="errorMessageLogin"
	style="text-align: left; -webkit-border-radius: 5px 5px 5px 5px; -moz-border-radius: 5px 5px 5px 5px; border: 1px solid #FF0000;width:100%;">
</div> -->
<br>
<div id="messageDiv"></div>

<div style="width: 1112px; background-color: White; border: medium none;  align: center;">
<br>
<br>
<input type="hidden" name="solutionId" id="solutionId"
	value="${solutionId}" />
<input type="hidden" name="opportunityId" id="opportunityId"
	value="${opportunityId}" />
<input type="hidden" name="serviceFunctionCode" id="serviceFunctionCode"
	value="APP_DEV" />

<jsp:include page="productEstimationInput.jsp"></jsp:include>
<br>
<c:if test="${empty selOppComponentList}">
	<div class="message">Please select the Product components from Define Scope.</div>
</c:if>

<c:forEach var="oppComponent" items="${selOppComponentList}">
	<table
		id="grid<c:out value='${oppComponent.component.componentID}'/>"
		<%-- serviceScopeId="<c:out value='${oppComponent.serviceScopeDTO.serviceScopeId}'/>" --%> 
		componentId="<c:out value='${oppComponent.component.componentID}'/>"
		oppComponentId="<c:out value='${oppComponent.opportunityComponentID}'/>"
		captionName="${oppComponent.component.componentName}"></table>
	<div
		id="pager<c:out value='${oppComponent.component.componentID}'/>"></div>
<br>
</c:forEach>
</div>
<br>

<div class="tabsDefineScoprButtons" style="float: right; width: 35%;">
	<div id="appDevNext" class="TabsCommonButtons">Next</div>
	<c:choose>
		<c:when test="${hasEditSolAccess!='false'}">
		<div id="appDevSave" class="TabsCommonButtons">Save</div>
		</c:when>
	</c:choose>
	<!-- <div id="movetoApaAdmSupp" class="TabsCommonButtons" >Move to APA</div> -->
	<div id="appDevPrev" class="TabsCommonButtons">Previous</div>
</div>