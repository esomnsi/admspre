<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="msg" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="../js/wz_tooltip.js"></script>
<script>
	function toggleDisplay(value) {
		if ($("#oppDetails").css('display') == "none") {
			$("#oppDetails").css('display', "");
		} else {
			$("#oppDetails").css('display', "none");
		}
	}
	function errorDisp(message) {
		$("#message").addClass('errorMessageDisp');
		$("#message").html(message);
	}
	$(document)
			.ready(
					function() {
						//var hasEditSolAccess = "${hasEditSolAccess}";
						if ("${hasEditSolAccess}" == 'false') {
							$("input").attr("disabled", "disabled");
							// $("select").attr("disabled","disabled");
						}
						function validateData(formName) {
							try {
								var status = true;
								//var values = [];
								$("#nlcForm input").each(function() {
									this.style.borderColor = "";
									//values.push(this.value);
								});
								$("#nlcForm input").each(function() {
									if (this.value != "" && isNaN(this.value)) {
										alert('Please enter a valid number!');
										this.style.borderColor = "red";
										this.focus();
										status = false;
										//return false;
									}
								});
								if (!status) {
									return status;
								}
								$("#nlcForm input")
										.each(
												function() {
													if (this.value != ""
															&& this.value
																	.indexOf("\.") > 0) {
														alert('Please enter a valid number without having decimal values!');
														this.style.borderColor = "red";
														this.focus();
														status = false;
														//return false;
													}
												});
								return status;
							} catch (e) {
								//alert(e.message);
							}
						}

						$("#formReset").click(function() {
							try {
								$("#message").removeClass();
								$("#message").html("");
								document.getElementById("nlcForm").reset();
								/* $("#nlcForm input").each(function() {
									this.value = "";
								}); */
							} catch (e) {
								//alert(e.message);
							}
						});

						$("#saveNext")
								.click(
										function() {
											if (validateData('nlcForm')) {
												var dataString = $("#nlcForm")
														.serialize();
												$
														.ajax({
															type : "POST",
															url : "./saveNonLabourCost",
															data : dataString,
															async : false,
															success : function(
																	data) {
																$("#message")
																		.addClass(
																				'actionPerformedMessage');
																$("#message")
																		.html(
																				'Data saved successfully');
																//$("#nlcForm").attr('action',"../approval/loadApproverPage");
																//$("#nlcForm").submit();
																document.location.href = "../approval/loadApproverPage";
																/* document.location
																		.reload(); */
																//location.reload;
																// append the returned result to your #dynamicdiv
															}
														});
												return true;
											}
										});
						$("#save")
								.click(
										function() {
											if (validateData('nlcForm')) {
												var dataString = $("#nlcForm")
														.serialize();
												$
														.ajax({
															type : "POST",
															url : "./saveNonLabourCost",
															data : dataString,
															async : false,
															success : function(
																	data) {
																$("#message")
																		.addClass(
																				'actionPerformedMessage');
																$("#message")
																		.html(
																				'Data saved successfully');
																//$("#nlcForm").attr('action',"../approval/loadApproverPage");
																//$("#nlcForm").submit();
																//document.location.href="../approval/loadApproverPage";
																/* document.location
																		.reload(); */
																//location.reload;
																// append the returned result to your #dynamicdiv
															}
														});
												return true;
											}
										});

					});
</script>

	<div id="serviceBucket" style="margin-bottom:10px;">
			<jsp:include page="./serviceBucket.jsp"/>
	</div>
	
<div id="mainBody" class="bodyCss">
	<!-- <div id="bodyDiv" class="mainBodyDiv"
		style="overflow-y: auto; overflow-x: auto; width: 100%"> -->
	<div id="message"></div>
	<c:if test="${empty nonLabourCostForm.solutionId}">
		<script>
			errorDisp('Due to absence of solution ID');
		</script>
	</c:if>

	<div id="bodyDiv1" class="mainBodyDiv1"
		style="width: 100%; mafloat: left; margin-bottom: 5px;">
		<h1 class="tdHeaderLabel" style="margin-top: 0px; margin-bottom: 0px;">Non
			Labor Cost</h1>
	</div>
	<table width="1112" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
				<div id="bodyDiv" class="mainBodyDiv1">
					<jsp:include page="opportunitySummary.jsp"></jsp:include>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center" height="5"></td>
		</tr>

	</table>
	
	
	<form:form id="nlcForm" method="POST" commandName="nonLabourCostForm"
		action="">
		<div id="bodyDiv" class="mainBodyDivwithoutLine">




			<!-- <div id="bodyDiv" class="mainBodyDiv1"
					style="overflow-y: auto; overflow-x: auto; width: 100%"> -->
			<c:set var="msg" value="" />
			<c:set var="defaultVal" value="NA" />
			<c:set var="splitted"
				value="${fn:split(nonLabourCostForm.year, ';')}" />
			<c:set var="loopLength" value="${fn:length(splitted)}" />
			<table width="100%" border="0" class="nonLaborCostTable">
				<tbody class="FTEscrollProperty">
					<tr>
						<td class="tdSubTableHeadCenter" width="25%">Sub-Components</td>
						<td class="tdSubTableHeadCenter">Unit</td>

						<c:forEach var="value" items="${splitted}">
							<c:choose>
								<c:when test="${not empty value && value != defaultVal}">
									<td class="tdSubTableHeadCenter"><form:label path="year">
											<c:out value="${value}" />
										</form:label></td>
								</c:when>
								<c:otherwise>
									<td class="tdSubTableHeadCenter"><form:label path="year">
											<c:out value="Year" />
										</form:label></td>
									<c:set var="msg"
										value="Please check opportunity steady state inputted timeline!" />
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
					<!-- NonLabourcostID hidden field values -->
					<c:choose>
						<c:when test="${not empty nonLabourCostForm.nonlabourCostId}">
							<c:set var="splitted"
								value="${fn:split(nonLabourCostForm.nonlabourCostId, ';')}" />
							<c:set var="localLength" value="${fn:length(splitted)}" />
							<c:forEach var="value" items="${splitted}">
								<c:choose>
									<c:when test="${not empty value && value != defaultVal}">
										<form:hidden path="nonlabourCostId" value="${value}"></form:hidden>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="nonlabourCostId" value=""
											class="textBoxSmallNumric"></input>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${(loopLength gt localLength)}">
									<c:forEach begin="1" end="${loopLength-localLength}">
										<input type="hidden" name="nonlabourCostId" value=""
											class="textBoxSmallNumric"></input>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:forEach var="val" begin="1" end="${loopLength}">
								<form:hidden path="nonlabourCostId"></form:hidden>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<tr>
						<td class="tdSubTableHead" width="25%" colspan="${loopLength+9}">
							International Travel (Short Term)</td>
					</tr>
					<tr>
						<td class="tdLabel">No. Of Travels</td>
						<td class="tdCenterLabel">#</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.itnoofTravels}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.itnoofTravels, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													path="itnoofTravels" type="text" value="${value}"
													onkeypress="return isNumeric(event);"
													class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="itnoofTravels" onkeypress="return isNumeric(event);"
												value="" class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="itnoofTravels" value=""
												onkeypress="return isNumeric(event);"
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											path="itnoofTravels" onkeypress="return isNumeric(event);"
											value="" class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Duration of stay in each travel</td>
						<td class="tdCenterLabel">Days</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.itstayDuration}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.itstayDuration, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													path="itstayDuration" type="text" value="${value }"
													onkeypress="return isNumericWithDecimal(event,this);"
													class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="itstayDuration" type="text" value=""
												onkeypress="return isNumericWithDecimal(event,this);"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="itstayDuration"
												onkeypress="return isNumericWithDecimal(event,this);"
												value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											path="itstayDuration"
											onkeypress="return isNumericWithDecimal(event,this);"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">One Short Term Visa Cost</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.itshortVisaCost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.itshortVisaCost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													path="itshortVisaCost" type="text" value="${value }"
													onkeypress="return isNumericWithDecimal(event,this);"
													class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="itshortVisaCost"
												onkeypress="return isNumericWithDecimal(event,this);"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="itshortVisaCost"
												onkeypress="return isNumericWithDecimal(event,this);"
												value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="itshortVisaCost" class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">One Round Trip Ticket Cost</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.ittickectCost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.ittickectCost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													path="ittickectCost" type="text" value="${value }"
													onkeypress="return isNumericWithDecimal(event,this);"
													class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="ittickectCost"
												onkeypress="return isNumericWithDecimal(event,this);"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="ittickectCost"
												onkeypress="return isNumericWithDecimal(event,this);"
												value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="ittickectCost" class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Onsite Daily Per Diem</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.itdailyPerDiem}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.itdailyPerDiem, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="itdailyPerDiem" id="itdailyPerDiem" type="text"
													value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="itdailyPerDiem"
												onkeypress="return isNumericWithDecimal(event,this);"
												id="itdailyPerDiem" class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="itdailyPerDiem"
												onkeypress="return isNumericWithDecimal(event,this);"
												value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="itdailyPerDiem" id="itdailyPerDiem"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>

						<td class="tdLabel">Onsite Hotel Cost Per Night of stay &nbsp
							&nbsp&nbsp<a href=""><img style="background-color: white;"
								width="14" height="14"
								onmouseover="Tip('Click to know international hotel rates', BGCOLOR, '#FFFFFF')"
								onmouseout="UnTip()" src="../images/help_icon.png"
								onclick="window.open('http://hotelzon.com/en/se/home','Help','width=900,height=650,resizable=yes');" />
						</a>
						</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.ithotelCostPerNight}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.ithotelCostPerNight, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													path="ithotelCostPerNight" type="text" value="${value}"
													onkeypress="return isNumericWithDecimal(event,this);"
													class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												onkeypress="return isNumericWithDecimal(event,this);"
												name="ithotelCostPerNight" class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												onkeypress="return isNumericWithDecimal(event,this);"
												name="ithotelCostPerNight" value=""
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="ithotelCostPerNight" class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Onsite Conveyance Costs Per Day per FTE</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.itconveyancePerDay}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.itconveyancePerDay, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="itconveyancePerDay" id="itconveyancePerDay"
													type="text" value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												onkeypress="return isNumericWithDecimal(event,this);"
												name="itconveyancePerDay" id="itconveyancePerDay"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												onkeypress="return isNumericWithDecimal(event,this);"
												name="itconveyancePerDay" value=""
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="itconveyancePerDay" id="itconveyancePerDay"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdSubTableHead" width="25%" colspan="${loopLength+9}">Domestic
							Travel (Short Term)</td>
					</tr>
					<tr>
						<td class="tdLabel">No. Of Travels</td>
						<td class="tdCenterLabel">#</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.dtnoofTravels}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.dtnoofTravels, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													path="dtnoofTravels" id="dtnoofTravels" type="text"
													onkeypress="return isNumeric(event);" value="${value }"
													class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="dtnoofTravels" onkeypress="return isNumeric(event);"
												id="dtnoofTravels" class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="dtnoofTravels" onkeypress="return isNumeric(event);"
												value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											path="dtnoofTravels" id="dtnoofTravels"
											onkeypress="return isNumeric(event);"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Duration of stay in each travel</td>
						<td class="tdCenterLabel">Days</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.dtstayDuration}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.dtstayDuration, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="dtstayDuration" id="dtstayDuration" type="text"
													value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="dtstayDuration"
												onkeypress="return isNumericWithDecimal(event,this);"
												id="dtstayDuration" class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="dtstayDuration"
												onkeypress="return isNumericWithDecimal(event,this);"
												value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="dtstayDuration" id="dtstayDuration"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">One Round Trip Ticket Cost</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.dttickectCost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.dttickectCost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="dttickectCost" id="dttickectCost" type="text"
													value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="dttickectCost"
												onkeypress="return isNumericWithDecimal(event,this);"
												id="dttickectCost" class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="dttickectCost"
												onkeypress="return isNumericWithDecimal(event,this);"
												value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											path="dttickectCost" id="dttickectCost"
											onkeypress="return isNumericWithDecimal(event,this);"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Onsite Daily Per Diem</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.dtdailyPerDiem}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.dtdailyPerDiem, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="dtdailyPerDiem" id="dtdailyPerDiem" type="text"
													value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="dtdailyPerDiem"
												onkeypress="return isNumericWithDecimal(event,this);"
												id="dtdailyPerDiem" class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="dtdailyPerDiem"
												onkeypress="return isNumericWithDecimal(event,this);"
												value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											path="dtdailyPerDiem" id="dtdailyPerDiem"
											onkeypress="return isNumericWithDecimal(event,this);"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Onsite Hotel Cost Per Night of stay</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.dthotelCostPerNight}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.dthotelCostPerNight, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="dthotelCostPerNight" id="dthotelCostPerNight"
													type="text" value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="dthotelCostPerNight" id="dthotelCostPerNight"
												onkeypress="return isNumericWithDecimal(event,this);"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												onkeypress="return isNumericWithDecimal(event,this);"
												name="dthotelCostPerNight" value=""
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="dthotelCostPerNight" id="dthotelCostPerNight"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Onsite Conveyance Costs Per Day per FTE</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.dtconveyancePerDay}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.dtconveyancePerDay, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="dtconveyancePerDay" id="dtconveyancePerDay"
													type="text" value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="dtconveyancePerDay" id="dtconveyancePerDay"
												onkeypress="return isNumericWithDecimal(event,this);"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												onkeypress="return isNumericWithDecimal(event,this);"
												name="dtconveyancePerDay" value=""
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="dtconveyancePerDay" id="dtconveyancePerDay"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>

					<tr>
						<td class="tdSubTableHead" width="25%" colspan="${loopLength+9}">
							Connectivity</td>
					</tr>
					<tr>
						<td class="tdLabel">Client Env to EGI connectivity cost</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.egiconnectivityCost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.egiconnectivityCost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="egiconnectivityCost" id="egiconnectivityCost"
													type="text" value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												onkeypress="return isNumericWithDecimal(event,this);"
												name="egiconnectivityCost" id="egiconnectivityCost"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												onkeypress="return isNumericWithDecimal(event,this);"
												name="egiconnectivityCost" id="egiconnectivityCost" value=""
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="egiconnectivityCost" id="egiconnectivityCost"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Emp to GSC Centre
							connectivity cost</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.gscconnectivityCost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.gscconnectivityCost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="gscconnectivityCost" id="gscconnectivityCost"
													type="text" value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="gscconnectivityCost" id="gscconnectivityCost"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="gscconnectivityCost" value=""
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="gscconnectivityCost" id="gscconnectivityCost"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Other Connectivity Cost</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when
								test="${not empty nonLabourCostForm.otherConnectivityCost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.otherConnectivityCost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="otherConnectivityCost" id="otherConnectivityCost"
													type="text" value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="otherConnectivityCost" id="otherConnectivityCost"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="otherConnectivityCost" value=""
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="otherConnectivityCost" id="otherConnectivityCost"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdSubTableHead" width="25%" colspan="${loopLength+9}">Service
							Beyond Normal Business Hours</td>
					</tr>
					<tr>
						<td class="tdLabel">Night Shift Allowance per FTE Per
							Month</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when
								test="${not empty nonLabourCostForm.monthlyNightAllowance}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.monthlyNightAllowance, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="monthlyNightAllowance" id="monthlyNightAllowance"
													type="text" value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="monthlyNightAllowance" id="monthlyNightAllowance"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="monthlyNightAllowance" value=""
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="monthlyNightAllowance" id="monthlyNightAllowance"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">% of people in Nght Shifts at Offshore</td>
						<td class="tdCenterLabel">%</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.pcpeopleAtNight}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.pcpeopleAtNight, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="pcpeopleAtNight" id="pcpeopleAtNight" type="text"
													value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="pcpeopleAtNight" id="pcpeopleAtNight"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="pcpeopleAtNight" value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="pcpeopleAtNight" id="pcpeopleAtNight"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Weekend Allowance per FTE Per month</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when
								test="${not empty nonLabourCostForm.monthlyWeekendAllowance}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.monthlyWeekendAllowance, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="monthlyWeekendAllowance" id="monthlyWeekendAllowance"
													type="text" value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="monthlyWeekendAllowance" id="monthlyWeekendAllowance"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="monthlyWeekendAllowance" value=""
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="monthlyWeekendAllowance" id="monthlyWeekendAllowance"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<%-- --%>
					<tr>
						<td class="tdLabel">% of People for weekend Allowance</td>
						<td class="tdCenterLabel">%</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.pcpeopleAtWeekend}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.pcpeopleAtWeekend, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="pcpeopleAtWeekend" id="pcpeopleAtWeekend" type="text"
													value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="pcpeopleAtWeekend" id="pcpeopleAtWeekend"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="pcpeopleAtWeekend" value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="pcpeopleAtWeekend" id="pcpeopleAtWeekend"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>

					<tr>
						<td class="tdLabel">Mobile communication cost per Offshore
							FTE per month</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.monthMobileCost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.monthMobileCost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="monthMobileCost" id="monthMobileCost" type="text"
													value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="monthMobileCost" id="monthMobileCost"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="monthMobileCost" value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="monthMobileCost" id="monthMobileCost"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdLabel">Data card cost per FTE per month</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.monthlyDataCardCost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.monthlyDataCardCost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="monthlyDataCardCost" id="monthlyDataCardCost"
													type="text" value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="monthlyDataCardCost" id="monthlyDataCardCost"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="monthlyDataCardCost" value=""
												class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="monthlyDataCardCost" id="monthlyDataCardCost"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>

					<tr>
						<td class="tdSubTableHead" colspan="${loopLength+9}">Project
							Specific Hardware/Software/3PP</td>
					</tr>
					<tr>
						<td class="tdLabel">Specific S/W , H/W , 3PP Cost</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.hwsw3ppcost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.hwsw3ppcost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="hwsw3ppcost" id="hwsw3ppcost" type="text"
													value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="hwsw3ppcost" id="hwsw3ppcost"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="hwsw3ppcost" value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="hwsw3ppcost" id="hwsw3ppcost"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdSubTableHead" colspan="${loopLength+9}">Training
							Costs</td>
					</tr>
					<tr>
						<td class="tdLabel">Project specific trainings Cost</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.trainingCost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.trainingCost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="trainingCost" id="trainingCost" type="text"
													value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="trainingCost" id="trainingCost"
												class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="trainingCost" value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="trainingCost" id="trainingCost"
											class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="tdSubTableHead" colspan="${loopLength+9}">Other
							Costs (If any)</td>
					</tr>
					<tr>
						<td class="tdLabel">E.g. Risks & contingency , EGI rate card
							does not factor <br />any kind of risks / contingencies
							associated with projects
						</td>
						<td class="tdCenterLabel">Rs.</td>
						<c:choose>
							<c:when test="${not empty nonLabourCostForm.otherCost}">
								<c:set var="splitted"
									value="${fn:split(nonLabourCostForm.otherCost, ';')}" />
								<c:set var="localLength" value="${fn:length(splitted)}" />
								<c:forEach var="value" items="${splitted}">
									<c:choose>
										<c:when test="${not empty value && value != defaultVal}">
											<td class="tdCenterLabel"><form:input maxlength="7"
													onkeypress="return isNumericWithDecimal(event,this);"
													path="otherCost" id="otherCost" type="text"
													value="${value }" class="textBoxSmallNumric"></form:input></td>
										</c:when>
										<c:otherwise>
											<td class="tdCenterLabel"><input maxlength="7"
												name="otherCost" id="otherCost" class="textBoxSmallNumric"></input></td>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${(loopLength gt localLength)}">
										<c:forEach begin="1" end="${loopLength-localLength}">
											<td class="tdCenterLabel"><input maxlength="7"
												name="otherCost" value="" class="textBoxSmallNumric"></input></td>
										</c:forEach>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach var="val" begin="1" end="${loopLength}">
									<td class="tdCenterLabel"><form:input maxlength="7"
											onkeypress="return isNumericWithDecimal(event,this);"
											path="otherCost" id="otherCost" class="textBoxSmallNumric"></form:input></td>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tr>
				</tbody>
			</table>



			<div class="mainBodyDiv1"
				style="width: 100%; float: left; margin-left: 0px;">
				<table width="100%" border="0">
					<tr>
						<td><c:if
								test="${not empty nonLabourCostForm.solutionId && nonLabourCostForm.operationMode == 'V'&& empty msg}">
								<!-- <div align="center"
									style="border: none; font-size: 12px; font-weight: bold; color: #000000; font-family: Arial; width: 100%;">
									Non of the local changes will get reflected in the system</div> -->
								<script>
									errorDisp('Non of the local changes will get reflected in the system');
								</script>
							</c:if> <c:if
								test="${not empty msg && not empty nonLabourCostForm.solutionId}">
								<%-- <div align="center"
									style="border: none; font-size: 12px; font-weight: bold; color: #FF0000; font-family: Arial; width: 100%;">
									<c:out value="${msg}"></c:out>
								</div> --%>
								<script>
									errorDisp("${msg}");
								</script>
							</c:if></td>
						<td align="center" colspan="3" class="tdButtonRow">
							<!-- View mode check display --> <c:choose>
								<c:when
									test="${not empty nonLabourCostForm.solutionId && nonLabourCostForm.operationMode != 'V' && empty msg && hasEditSolAccess!='false'}">
									<div class="portfolioButtons" id="saveNext">SUBMIT</div>
									<div class="portfolioButtons" id="save">Save</div>
									<div class="portfolioButtons" id="formReset">Reset</div>
								</c:when>
								<c:otherwise>
									<div class="portfolioButtons"
										onclick="location.href='../approval/loadApproverPage'">Next</div>
								</c:otherwise>
							</c:choose>
							<div class="portfolioButtons"
								onclick="location.href='./labourCost'">Previous</div>
						</td>
					</tr>
				</table>
			</div>

		</div>
	</form:form>

</div>
