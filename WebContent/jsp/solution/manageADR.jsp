<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#addA").click(
								function() {
									$("#aForm #message").val("");
									$('#aTable tbody>tr:last').clone(true)
											.insertAfter(
													'#aTable tbody>tr:last');
									$('#aTable tbody>tr:last #aSolutionAdrid')
											.val("");
									$('#aTable tbody>tr:last #area')
											.val("NONE");
									$('#aTable tbody>tr:last #area').css(
											"borderColor", "");
									$('#aTable tbody>tr:last #type')
											.val("NONE");
									$('#aTable tbody>tr:last #type').css(
											"borderColor", "");
									$('#aTable tbody>tr:last #impact').val(
											"NONE");
									$('#aTable tbody>tr:last #impact').css(
											"borderColor", "");
									$('#aTable tbody>tr:last #wtg').val("");
									$('#aTable tbody>tr:last #stmnt').val("");
									var slno = $('#aTable tbody>tr:last #num')
											.text();
									//$("#aTable tbody>tr:last").each(function() {alert($(this).closest('tr').index());});					
									$('#aTable tbody>tr:last #num').text(
											parseInt(slno) + 1);
									return false;
								});
						$("#addD").click(
								function() {
									$("#dForm #message").val("");
									$('#dTable tbody>tr:last').clone(true)
											.insertAfter(
													'#dTable tbody>tr:last');
									$('#dTable tbody>tr:last #wtg').val("");
									$('#dTable tbody>tr:last #aSolutionAdrid')
											.val("");
									$('#dTable tbody>tr:last #area')
											.val("NONE");
									$('#dTable tbody>tr:last #area').css(
											"borderColor", "");
									$('#dTable tbody>tr:last #type')
											.val("NONE");
									$('#dTable tbody>tr:last #type').css(
											"borderColor", "");
									$('#dTable tbody>tr:last #impact').val(
											"NONE");
									$('#dTable tbody>tr:last #impact').css(
											"borderColor", "");
									$('#dTable tbody>tr:last #stmnt').val("");
									var slno = $('#dTable tbody>tr:last #num')
											.text();
									$('#dTable tbody>tr:last #num').text(
											parseInt(slno) + 1);
									return false;
								});
						$("#addR").click(
								function() {
									$("#rForm #message").val("");
									$('#rTable tbody>tr:last').clone(true)
											.insertAfter(
													'#rTable tbody>tr:last');
									$('#rTable tbody>tr:last #area')
											.val("NONE");
									$('#rTable tbody>tr:last #area').css(
											"borderColor", "");
									$('#rTable tbody>tr:last #type')
											.val("NONE");
									$('#rTable tbody>tr:last #type').css(
											"borderColor", "");
									$('#rTable tbody>tr:last #impact').val(
											"NONE");
									$('#rTable tbody>tr:last #impact').css(
											"borderColor", "");
									$('#rTable tbody>tr:last #wtg').val("");
									$('#rTable tbody>tr:last #stmnt').val("");
									$('#rTable tbody>tr:last #aSolutionAdrid')
											.val("");
									var slno = $('#rTable tbody>tr:last #num')
											.text();
									$('#rTable tbody>tr:last #num').text(
											parseInt(slno) + 1);
									return false;
								});
						$("#search")
								.click(
										function() {
											try {
												var url = "?area="
														+ $("#sArea").val()
														+ "&type="
														+ $("#sType").val()
														+ "&impact="
														+ $("#sImpact").val();
												document.forms[0].action = "./manageADR"
														+ url;
												document.forms[0].submit();
											} catch (e) {
												//alert(e.message);
											}
										});
						var validationSuccess = "";
						var emptyStmnt = false;
						function validateData(formName) {
							try {
								validationSuccess = true;
								emptyStmnt = false;
								var adrIDfound = false;
								var anyNonEmptySt = false;
								var emptyInMid = false; //Dont allow empty statement in middle of the rows
								var emptyIndex = "";
								var adrs = [];

								$("#" + formName + "#message").val("");
								if (formName == "aForm") {
									<c:forEach items="${manageADRForm.assumpList}" var="assum" varStatus="x">
									adrs.push('${assum.adrStatement}');
									</c:forEach>
								} else if (formName == "dForm") {
									<c:forEach items="${manageADRForm.dependList}" var="depend" varStatus="x">
									adrs.push('${depend.adrStatement}');
									</c:forEach>
								} else {
									<c:forEach items="${manageADRForm.riskList}" var="risk" varStatus="x">
									adrs.push('${risk.adrStatement}');
									</c:forEach>
								}
								//Statements field empty check
								var stmnts = [];
								$('#' + formName + ' textarea[id^="stmnt"]')
										.each(function() {
											stmnts.push(this.value);
										});
								$.each(stmnts, function(key, value) {
									//Dont allow empty statement in middle of the rows 
									if (value == '') {
										emptyStmnt = true;
										if (emptyIndex == "") {
											emptyIndex = 'i' + key;
										}
									} else {
										anyNonEmptySt = true;
										if (emptyIndex != "") {
											emptyInMid = true;
										}
									}
								});
								//Dont allow empty statement in middle of the rows
								if (emptyInMid) {
									alert('Please make bottom statement(s) empty to get deleted!');
									//Reset empty field values to old values
									if ("" != adrs) {
										var indx = 0;
										$(
												'#'
														+ formName
														+ ' textarea[id^="stmnt"]')
												.each(
														function() {
															if (this.value == "") {
																this.value = adrs[indx] != undefined ? adrs[indx]
																		: "";
															}
															indx++;
														});
									}

									validationSuccess = false;
									return false;
								}
								var solADRIds = [];
								$(
										'#'
												+ formName
												+ ' input[id^="aSolutionAdrid"]')
										.each(function() {
											solADRIds.push(this.value);
										});
								$.each(solADRIds, function(key, value) {//Existing ID statement empty check
									if (value != '') {
										adrIDfound = true;
									}
								});
								if ((emptyStmnt && stmnts.length == 1 && !adrIDfound)
										|| (!anyNonEmptySt && !adrIDfound)) {
									$("#" + formName + " #message").val(
											'Nothing to be saved!');
									document.location.reload();
									validationSuccess = false;
								} else {
									//Number format validation for wightage
									var numNumericVal = false;
									$('#' + formName + ' input[id^="wtg"]')
											.each(function() {
												if (isNaN(this.value)) {
													this.value = "0";
													numNumericVal = true;
												}
												if (this.value == '') {
													this.value = "0";
												}
											});
									$("#" + formName + " #area")
											.each(
													function() {
														if (this.value == "NONE") {
															this.style.borderColor = "red";
															validationSuccess = false;
															//return false;
														} else {
															this.style.borderColor = "";
														}
													});
									$("#" + formName + " #type")
											.each(
													function() {
														if (this.value == "NONE") {
															this.style.borderColor = "red";
															validationSuccess = false;
														} else {
															this.style.borderColor = "";
														}
													});
									$("#" + formName + " #impact")
											.each(
													function() {
														if (this.value == "NONE") {
															this.style.borderColor = "red";
															validationSuccess = false;
															//return false;
														} else {
															this.style.borderColor = "";
														}
													});
									if (!validationSuccess) {
										$("#" + formName + " #message")
												.val(
														'Please select valid value(s)!');
										return false;
									}
									if (emptyStmnt && numNumericVal) {
										alert('Empty statments record(s) will get removed with alphanumeric weightage field to zero!');
									} else {
										if (emptyStmnt) {
											alert('Empty statement field value record(s) will get removed!');
										} else if (numNumericVal) {
											alert('Alphanumeric weightage will set to zero!');
										}

									}

								}

							} catch (e) {
								//alert(e.message);
							}
						}
						var isClicked = false;
						$("#aSave")
								.click(
										function() {
											if (isClicked) {
												return;
											}
											var dataString = $("#aForm")
													.serialize()
													+ "&act=aForm";
											//alert(dataString);
											validateData('aForm');
											if (validationSuccess) {
												isClicked = true;
												$
														.ajax({
															type : "POST",
															url : "./saveADR",
															data : dataString,
															async : false,
															success : function(
																	data) {
																isClicked = false;
																if (data != "") {
																	$(
																			"#aForm #message")
																			.val(
																					data);
																} else {
																	if (emptyStmnt) {
																		alert('Assumption(s) saved successfully!');
																	}

																	$(
																			"#aForm #message")
																			.val(
																					"Assumption(s) saved successfully!");
																}
																document.location
																		.reload();
																//location.reload;
																// append the returned result to your #dynamicdiv
															}
														});
											}
											validationSuccess = "";
											return true;
										});
						$("#dSave")
								.click(
										function() {
											if (isClicked) {
												return;
											}
											//alert($("#solutionAdrid"));
											var dataString = $("#dForm")
													.serialize()
													+ "&act=dForm";
											validateData('dForm');
											if (validationSuccess) {
												isClicked = true;
												$
														.ajax({
															type : "POST",
															url : "./saveADR",
															data : dataString,
															async : false,
															success : function(
																	data) {
																isClicked = false;
																if (emptyStmnt) {
																	alert('Dependencies saved successfully!');
																}
																$(
																		"#dForm #message")
																		.val(
																				"Dependencies saved successfully!");
																document.location
																		.reload();
																// append the returned result to your #dynamicdiv
															}
														});
											}
											validationSuccess = "";
											return true;
										});
						$("#rSave")
								.click(
										function() {
											//alert($("#solutionAdrid"));
											if (isClicked) {
												return;
											}
											var dataString = $("#rForm")
													.serialize()
													+ "&act=rForm";
											validateData('rForm');
											if (validationSuccess) {
												isClicked = true;
												$
														.ajax({
															type : "POST",
															url : "./saveADR",
															data : dataString,
															async : false,
															success : function(
																	data) {
																isClicked = false;
																if (emptyStmnt) {
																	alert('Risk(s) saved successfully!');
																}
																$(
																		"#rForm #message")
																		.val(
																				"Risk(s) saved successfully!");
																document.location
																		.reload();
																// append the returned result to your #dynamicdiv
															}
														});
											}
											isClicked = false;
											validationSuccess = "";
											return true;
										});
					});
</script>



<%-- <form:form id="form" method="POST" commandName="manageADRForm"> --%>

<div id="mainBody" class="bodyCss">
	<div id="bodyDiv" class="mainBodyDiv"
		style="overflow-y: auto; overflow-x: auto; width: 100%">
		<div id="bodyDiv1" class="mainBodyDivwithoutLine">

			<div id="bodyDiv1" class="mainBodyDiv1"
				style="width: 100%; mafloat: left; margin-bottom: 5px;">

				<h1 class="tdHeaderLabel"
					style="margin-top: 0px; margin-bottom: 0px;">Manage ADR</h1>
			</div>
			<form:form id="sForm" method="POST" commandName="manageADRForm"
				action="./search">
				<div id="bodyDiv5" class="mainBodyDiv1"
					style="width: 100%; float: left; padding-bottom: 6px;">
					<table width="100%" border="0" align="center">

						<tr>

							<td class="tdTableHead" colspan="18">Search Criteria</td>
						</tr>
						<tr>
							<td class="tdInputBox" colspan="2" style="padding-left: 20px;">Select
								Area :</td>
							<!-- <td class="tdInputBox" style="padding-left: 20px;"></td> -->
							<td class=""><form:select path="sArea"
									cssClass="textBoxMedium">
									<form:option value="NONE" label="--- Select ---" />
									<form:options items="${areaList}" />
								</form:select></td>
							<td class="tdInputBox" style="padding-left: 20px;">Type :</td>
							<td class=""><form:select path="sType"
									cssClass="textBoxMedium">
									<form:option value="NONE" label="--- Select ---" />
									<form:options items="${typeList}" />
								</form:select></td>
							</td>
							<td class="tdInputBox" style="padding-left: 20px;">Impact :</td>
							<td class=""><form:select path="sImpact"
									cssClass="textBoxMedium">
									<form:option value="NONE" label="--- Select ---" />
									<form:options items="${impactList}" />
								</form:select></td>
							<td colspan="10"><c:if
									test="${not empty manageADRForm.solutionID}">
									<a id="search" class="button">Submit</a>
								</c:if> <a href="./manageADR" class="button">Clear</a></td>
						</tr>

					</table>
				</div>
			</form:form>
			<div id="bodyDiv4" class="mainBodyDiv1"
				style="width: 100%; float: left; margin-left: 0px; padding-bottom: 6px;">
				<!-- Assumption form started -->
				<form:form id="aForm" method="POST" commandName="manageADRForm">
					<c:set var="msg" value="" />

					<table id=aTable width="100%" border="0" align="center">
						<c:if
							test="${ manageADRForm.opMode == 'V' && not empty manageADRForm.solutionID}">
							<tr>
								<td colspan="9" class="tdTableHeadCenter">VIEW MODE: Non of
									the local changes will get reflected in the system</td>
							</tr>
						</c:if>
						<c:if test="${empty manageADRForm.solutionID}">
							<tr>
								<td colspan="9" class="tdRedAlertMsgCenter">VIEW MODE: Due
									to absence of solution ID</td>
							</tr>
						</c:if>
						<tr>
							<td class="tdTableHead" colspan="9">Add Assumption</td>
						</tr>

						<tr>
							<td class="tdSubTableHead">#</td>
							<td class="tdSubTableHead">Statement</td>
							<td class="tdSubTableHead">Area</td>
							<td class="tdSubTableHead">Type</td>
							<td class="tdSubTableHead">Impact</td>
							<td class="tdSubTableHead">Weightage(in %)</td>
						</tr>
						<tbody>
							<c:if test="${empty manageADRForm.assumpList}">
								<c:set var="msg" value="No item to be displayed" />
								<tr class="tdOddRow">
									<td class="" id="num">1</td>
									<td class=""><textarea path="aStatement" id="stmnt"
											name="aStatement" rows="1" cols="3" class="textBoxLarge"
											maxlength="255"></textarea></td>

									<td class=""><form:select path="aArea" id="area"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<form:options items="${areaList}" />
										</form:select></td>
									<td class=""><form:select path="aType" id="type"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<form:options items="${typeList}" />
										</form:select></td>
									<td class=""><form:select path="aImpact" id="impact"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<form:options items="${impactList}" />
										</form:select></td>
									<td class=""><form:input type="text" id="wtg"
											path="aWeightage" class="textBoxSmall"
											onkeypress="return isNumericWithDecimal(event,this);"
											maxlength="7"></form:input> <input type="hidden"
										name="aSolutionAdrid" id="aSolutionAdrid" value=""></td>
								</tr>
							</c:if>
							<c:forEach items="${manageADRForm.assumpList}" var="assumption"
								varStatus="status">

								<tr class="tdOddRow">
									<!-- class="${status.count%2 ==0?'tdEvenRow':'tdOddRow'}" -->
									<td align="center" id="num">${status.count}</td>
									<td class=""><textarea path="aStatement" id="stmnt"
											name="aStatement" rows="1" cols="3" class="textBoxLarge"
											maxlength="255">${assumption.adrStatement}</textarea></td>
									<td class=""><form:select path="aArea" id="area"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<c:forEach var="item" items="${areaList}">
												<c:choose>
													<c:when test="${assumption.adrArea==item.value}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}
													</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td class=""><form:select path="aType" id="type"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<c:forEach var="item" items="${typeList}">
												<c:choose>
													<c:when test="${assumption.adrType==item.value}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}
													</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td class=""><form:select path="aImpact" id="impact"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<c:forEach var="item" items="${impactList}">
												<c:choose>
													<c:when test="${assumption.adrImpact==item.key}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}
													</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td class=""><form:input type="text" id="wtg" name="wtg"
											path="aWeightage" value="${assumption.adrWeightage}"
											onkeypress="return isNumericWithDecimal(event,this);"
											class="textBoxSmall" maxlength="7"></form:input> <input
										type="hidden" name="aSolutionAdrid" id="aSolutionAdrid"
										value="${assumption.solutionAdrid}" /> <%-- 	<form:hidden
										path="aSolutionAdrid" id="solutionAdrid">${assumption.solutionAdrid}</form:hidden> --%>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<table>
						<c:if
							test="${ manageADRForm.opMode != 'V' && not empty manageADRForm.solutionID}">
							<tr class="tdOddRow">
								<td colspan="10" class="tdButtonRow"><a href="#" id="addA"
									class="button" target="_blank">Add Row</a> <a class="button"
									target="_blank" id="aSave">Save</a></td>
							</tr>
						</c:if>
						<tr class="tdOddRow">
							<td class="" colspan="10" width="9%">&nbsp; <c:choose>
									<c:when test="${not empty msg}">
										<input id="message" readonly="readonly"
											style="border: none; color: green; font-size: 11px; color: #000000; font-family: Arial; width: 100%"
											type="text" value="${msg}" />

									</c:when>
									<c:otherwise>
										<input id="message" readonly="readonly"
											style="border: none; color: green; font-size: 11px; color: #000000; font-family: Arial; width: 100%"
											type="text" value="" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
					<c:set var="msg" value="" />

				</form:form>
			</div>

			<div id="bodyDiv3" class="mainBodyDiv1"
				style="width: 100%; float: left; margin-left: 0px; padding-bottom: 6px;">
				<!-- Dependency form started -->
				<form:form id="dForm" method="POST" commandName="manageADRForm">

					<table id="dTable" width="100%" border="0" align="center">

						<tr>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td class="tdTableHead" colspan="10">Add Dependency</td>
						</tr>

						<tr>
							<td class="tdSubTableHead">#</td>
							<td class="tdSubTableHead">Statement</td>
							<td class="tdSubTableHead">Area</td>
							<td class="tdSubTableHead">Type</td>
							<td class="tdSubTableHead">Impact</td>
							<td class="tdSubTableHead">Weightage(in %)</td>
						</tr>
						<tbody>
							<c:if test="${empty manageADRForm.dependList}">
								<c:set var="msg" value="No item to be displayed" />

								<tr class="tdOddRow">
									<td class="" id="num">1</td>
									<td class=""><textarea path="aStatement" id="stmnt"
											name="aStatement" rows="1" cols="3" class="textBoxLarge"
											maxlength="255"></textarea></td>

									<td class=""><form:select path="aArea" id="area"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<form:options items="${areaList}" />
										</form:select></td>
									<td class=""><form:select path="aType" id="type"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<form:options items="${typeList}" />
										</form:select></td>
									<td class=""><form:select path="aImpact" id="impact"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<form:options items="${impactList}" />
										</form:select></td>
									<td class=""><form:input type="text" id="wtg"
											path="aWeightage" class="textBoxSmall"
											onkeypress="return isNumericWithDecimal(event,this);"
											maxlength="7"></form:input><input type="hidden"
										name="aSolutionAdrid" id="aSolutionAdrid" value=""></td>
								</tr>
							</c:if>
							<c:forEach items="${manageADRForm.dependList}" var="dependent"
								varStatus="status">

								<tr class="tdOddRow">
									<!-- class="${status.count%2 ==0?'tdEvenRow':'tdOddRow'}" -->
									<td align="center" id="num">${status.count}</td>
									<td class=""><textarea path="aStatement" id="stmnt"
											name="aStatement" rows="1" cols="3" class="textBoxLarge"  maxlength="255">${dependent.adrStatement}</textarea></td>

									<td class=""><form:select path="aArea" id="area"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<c:forEach var="item" items="${areaList}">
												<c:choose>
													<c:when test="${dependent.adrArea==item.value}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}
													</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td class=""><form:select path="aType" id="type"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<c:forEach var="item" items="${typeList}">
												<c:choose>
													<c:when test="${dependent.adrType==item.value}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}
													</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td class=""><form:select path="aImpact" id="impact"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />

											<c:forEach var="item" items="${impactList}">
												<c:choose>
													<c:when test="${dependent.adrImpact==item.key}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}
													</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td class=""><form:input type="text" id="wtg" name="wtg"
											path="aWeightage" value="${dependent.adrWeightage}"
											class="textBoxSmall"
											onkeypress="return isNumericWithDecimal(event,this);" maxlength="7"></form:input>
										<input type="hidden" name="aSolutionAdrid" id="aSolutionAdrid"
										value="${dependent.solutionAdrid}" /> <%-- 	<form:hidden
										path="aSolutionAdrid" id="solutionAdrid">${dependent.solutionAdrid}</form:hidden> --%>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table>
						<c:if
							test="${ manageADRForm.opMode != 'V' && not empty manageADRForm.solutionID}">
							<tr class="tdOddRow">
								<td colspan="10" class="tdButtonRow"><a class="button"
									target="_blank" id="addD">Add Row</a> <a class="button"
									id="dSave" target="_blank">Save</a></td>
							</tr>
						</c:if>
						<tr class="tdEvenRow">
							<td class="" colspan="10" width="9%">&nbsp; <c:choose>
									<c:when test="${not empty msg}">
										<input id="message" readonly="readonly"
											style="border: none; color: green; font-size: 11px; color: #000000; font-family: Arial; width: 100%"
											type="text" value="${msg}" />

									</c:when>
									<c:otherwise>
										<input id="message" readonly="readonly"
											style="border: none; color: green; font-size: 11px; color: #000000; font-family: Arial; width: 100%"
											type="text" value="" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
					<c:set var="msg" value="" />

				</form:form>
			</div>


			<div id="bodyDiv2" class="mainBodyDiv1"
				style="width: 100%; float: left; margin-left: 0px; padding-bottom: 6px;">
				<!-- Risk form started -->
				<form:form id="rForm" method="POST" commandName="manageADRForm">

					<table id="rTable" width="100%" border="0" align="center">
						<tr>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td class="tdTableHead" colspan="10">Add Risk</td>
						</tr>

						<tr>
							<td class="tdSubTableHead">#</td>
							<td class="tdSubTableHead">Statement</td>
							<td class="tdSubTableHead">Area</td>
							<td class="tdSubTableHead">Type</td>
							<td class="tdSubTableHead">Impact</td>
							<td class="tdSubTableHead">Weightage(in %)</td>
						</tr>
						<tbody>
							<c:if test="${empty manageADRForm.riskList}">
								<c:set var="msg" value="No item to be displayed" />

								<tr class="tdOddRow">
									<td class="" id="num">1</td>
									<td class=""><textarea path="aStatement" id="stmnt" maxlength="255"
											name="aStatement" rows="1" cols="3" class="textBoxLarge"></textarea></td>

									<td class=""><form:select path="aArea" id="area"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<form:options items="${areaList}" />
										</form:select></td>
									<td class=""><form:select path="aType" id="type"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<form:options items="${typeList}" />
										</form:select></td>
									<td class=""><form:select path="aImpact" id="impact"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />
											<form:options items="${impactList}" />
										</form:select></td>
									<td class=""><form:input type="text" id="wtg"
											path="aWeightage" class="textBoxSmall" maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"></form:input>
										<input type="hidden" name="aSolutionAdrid" id="aSolutionAdrid"
										value=""></td>
								</tr>
							</c:if>
							<c:forEach items="${manageADRForm.riskList}" var="risk"
								varStatus="status">

								<tr class="tdOddRow">
									<!-- class="${status.count%2 ==0?'tdEvenRow':'tdOddRow'}" -->
									<td align="center" id="num">${status.count}</td>
									<td class=""><textarea path="aStatement" id="stmnt"
											name="aStatement" rows="1" cols="3" class="textBoxLarge" maxlength="255">${risk.adrStatement}</textarea></td>

									<td class=""><form:select path="aArea" id="area"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />

											<c:forEach var="item" items="${areaList}">
												<c:choose>
													<c:when test="${risk.adrArea==item.value}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}
													</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td class=""><form:select path="aType" id="type"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />

											<c:forEach var="item" items="${typeList}">
												<c:choose>
													<c:when test="${risk.adrType==item.value}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}
													</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td class=""><form:select path="aImpact" id="impact"
											cssClass="textBoxMedium">
											<form:option value="NONE" label="--- Select ---" />

											<c:forEach var="item" items="${impactList}">
												<c:choose>
													<c:when test="${risk.adrImpact==item.key}">
														<form:option selected="true" value="${item.key}">${item.value}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${item.key}">${item.value}
													</form:option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td class=""><form:input type="text" id="wtg" name="wtg"
											path="aWeightage" value="${risk.adrWeightage}" maxlength="7"
											class="textBoxSmall"
											onkeypress="return isNumericWithDecimal(event,this);"></form:input>
										<input type="hidden" name="aSolutionAdrid" id="aSolutionAdrid"
										value="${risk.solutionAdrid}" /> <%-- 	<form:hidden
										path="aSolutionAdrid" id="solutionAdrid">${risk.solutionAdrid}</form:hidden> --%>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table>

						<c:if
							test="${ manageADRForm.opMode != 'V' && not empty manageADRForm.solutionID}">
							<tr class="tdOddRow">
								<td colspan="10" class="tdButtonRow"><a class="button"
									target="_blank" id="addR">Add Row</a> <a class="button"
									id="rSave" target="_blank">Save</a></td>
							</tr>
						</c:if>
						<tr class="tdOddRow">
							<td class="" colspan="10" width="9%">&nbsp; <c:choose>
									<c:when test="${not empty msg}">
										<input id="message" readonly="readonly"
											style="border: none; color: green; font-size: 11px; color: #000000; font-family: Arial; width: 100%"
											type="text" value="${msg}" />

									</c:when>
									<c:otherwise>
										<input id="message" readonly="readonly"
											style="border: none; color: green; font-size: 11px; color: #000000; font-family: Arial; width: 100%"
											type="text" value="" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>

					</table>
					<c:set var="msg" value="" />

				</form:form>
			</div>

		</div>

	</div>
</div>


