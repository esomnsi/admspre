<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="../js/wz_tooltip.js"></script>
<%@taglib prefix="msg" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">

	$(document).ready(function() {
		var hasEditSolAccess = "${hasEditSolAccess}";
		if(hasEditSolAccess=='false'){
		 $('#site').attr("disabled","disabled");
		 $('#jobrole').attr("disabled","disabled");
		 $('#aFTE').attr("disabled","disabled");
		 $('#fDate').attr("disabled","disabled");
		 $('#fDate').removeAttr('id');
		 $('#tDate').attr("disabled","disabled");
		 $('#tDate').removeAttr('id');		 
		}else
			{
				if($('#sServiceScope').val()==-1)
					{
					 $('#site').attr("disabled","disabled");
					 $('#jobrole').attr("disabled","disabled");
					 $('#aFTE').attr("disabled","disabled");
					 $('#fDate').attr("disabled","disabled");
					 $('#fDate').removeAttr('id');
					 $('#tDate').attr("disabled","disabled");
					 $('#tDate').removeAttr('id');	
					}
			}
		function isDate(txtDate)
		{
		  var currVal = txtDate;
		  if(currVal == '')
			    return false;
		   
		  //Declare Regex 
	  	var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
		  var dtArray = currVal.match(rxDatePattern); // is format OK?
		 
		  if (dtArray == null)
		     return false;
		  
		  //Checks for mm/dd/yyyy format.
		  dtMonth = dtArray[3];
		  dtDay= dtArray[1];
		  dtYear = dtArray[5];
		 
		  if (dtMonth < 1 || dtMonth > 12)
		      return false;
		  else if (dtDay < 1 || dtDay> 31)
		      return false;
		  else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
		      return false;
		  else if (dtMonth == 2)
		  {
		     var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
		     if (dtDay> 29 || (dtDay ==29 && !isleap))
		          return false;
		  }
		  return true;
		}
		var deleteFTE = false;
		function validateData(formName)
		{
			var status= true;
			try
			{
				if($("#aFTE").val()==""&&$("#fDate").val()==""&&$("#tDate").val()==""&&
						$('#jobrole').val()==-1&&$("#site").val()=="NONE")
					{
						if($("#additionalFteid").val()>=0)
							{
							 if(confirm('<msg:message code="fte.del.conf.msg"/>'))
								 {
								 	$("#aFTE").val(-1);
								 	deleteFTE = true;
								 	return true;
								 }else
								 {
								 	return false;
								 }
							}else
							{
								$("#message").addClass('actionPerformedMessage');
								$("#message").html('<msg:message code="fte.nothingToDo.msg"/>');
								return false;
							}
					}
			//var fDate,tDate;
			if($('#sServiceScope').val()==-1)
			{
				$('#sServiceScope').css("borderColor","red");
				$('#sServiceScope').focus();
				$("#message").addClass('errorMessageDisp');
				$("#message").html('<msg:message code="fte.service.err.msg"/>');
				return false;
			}else
				{
					$('#sServiceScope').css("borderColor","");
				}
			if($('#jobrole').val()==-1)
			{
				$('#jobrole').css("borderColor","red");
				$('#jobrole').focus();
				$("#message").addClass('errorMessageDisp');
				$("#message").html('<msg:message code="fte.role.err.msg"/>');
				return false;
			}else
				{
					$('#jobrole').css("borderColor","");
				}
			if($('#site').val()=="NONE")
			{
				$('#site').css("borderColor","red");
				$('#site').focus();
				$("#message").addClass('errorMessageDisp');
				$("#message").html('<msg:message code="fte.site.err.msg"/>');
				return false;
			}else
				{
					$('#site').css("borderColor","");
				}
			if($('#aFTE').val()=="" || isNaN($('#aFTE').val()))
			{
				$('#aFTE').css("borderColor","red");
				$('#aFTE').focus();
				if($('#aFTE').val()=="")
					{
					$("#message").addClass('errorMessageDisp');
					$("#message").html('<msg:message code="fte.numeric.add.fte.err.empty"/>');
					}else
					{
						$("#message").addClass('errorMessageDisp');
						$("#message").html('<msg:message code="fte.numeric.add.fte.err.msg"/>');
					}
				return false;
			}else
				{
					$('#aFTE').css("borderColor","");
				}
			$('#fDate').each(function() {
				//fDate = this.value;
				if(!isDate(this.value))
				{
					this.style.borderColor = "red";
					this.focus();	
					$("#message").addClass('errorMessageDisp');
					$("#message").html("<msg:message code="fte.frm.dt.err.msg"/>");
					status = false;
					return status;
				}else
				{
					this.style.borderColor = "";
					status = true;
				}			
			});
		if(!status)
			{
			return status;
			}
			
			$('#tDate').each(function() {
				//tDate = this.value;
				if(!isDate(this.value))
				{
					this.style.borderColor = "red";
					this.focus();
					$("#message").addClass('errorMessageDisp');
					$("#message").html("<msg:message code="fte.to.dt.err.msg"/>");
					status = false;	
					return false;
				}else
				{
					this.style.borderColor = "";
					status = true;
				}			
			});
			if(!status)
			{
				return status;
			}
			//Converting to mm/dd/yyyy
			var dt = $('#fDate').val().split("/");
			var fDate = new Date( dt[1]+"/"+dt[0]+"/"+dt[2] );
			dt = $('#tDate').val().split("/");
			tDate = new Date( dt[1]+"/"+dt[0]+"/"+dt[2] );
			if(fDate>tDate)
				{
				$("#message").addClass('errorMessageDisp');
				$("#message").html('<msg:message code="fte.dt.grtrthn.err.msg"/>');
				//$("#message").removeClass('errorMessageDisp');
					$('#tDate').css("borderColor","red");
					status = false;
				}else
				{
					$('#tDate').css("borderColor","");
					status = true;
				}
			}catch(e)
			{
				//alert(e.message);
			}
			return status;

			}
		
		$("#sServiceScope").change(function() {
			try {
					$("#message").html("");
					if($("#aFTE").val()==""||isNaN($("#aFTE").val()))
						{
							$("#aFTE").val(-1);
						}
				$("#rForm").submit();
				}catch(e)
				{
					//alert(e.message);
				}
		});
		$("#delete").click(function() {
			try {
				$("#message").removeClass();
				$("#message").html("");
				//$("#sServiceScope").val(-1);
				$("#jobrole").val(-1);
				$("#site").val("NONE");
				$("#aFTE").val("");
				$("#fDate").val("");
				$("#tDate").val("");
				//$("#additionalFteid").val(-1);
				$("#sServiceScope").css("borderColor","");
				$("#jobrole").css("borderColor","");
				$("#site").css("borderColor","");
				$("#aFTE").css("borderColor","");
				$("#fDate").css("borderColor","");
				$("#tDate").css("borderColor","");
				$("#message").removeClass();
				$("#message").html("");
				if(isSubmitted)
					{
						return;
					}
				if (validateData('rForm')) {
					if($("#aFTE").val()=="")
						{
							$("#aFTE").val(-1);
						}
					var dataString = $("#rForm").serialize();
					//alert(dataString);
					isSubmitted=true;
					$.ajax({
						type : "POST",
						url : "./saveAdditionalFTE",
						data : dataString,
						async : false,
						success : function(data) {
							//alert(data);
							if($("#aFTE").val()<0)
							{
								$("#aFTE").val("");
							}
							
							if(data!="failure")
								{
									$("#message").addClass('actionPerformedMessage');
									if(deleteFTE)
										{
											$("#message").html('<msg:message code="fte.succ.delt.msg"/>');
										}else
										{
											$("#message").html('<msg:message code="msg.common.datasave.success"/>');
										}
									if($("#additionalFteid").val()==""||$("#additionalFteid").val()<0)
										{
											$("#additionalFteid").val(data);
										}
								}else
									{
										$("#message").addClass('errorMessageDisp');
										$("#message").html('<msg:message code="fte.actn.fail.err.msg"/>');
									}
							if(deleteFTE)
							{
								$("#additionalFteid").val(-1);
								deleteFTE = false;
							}
							//document.location.reload();
							//location.reload;
							// append the returned result to your #dynamicdivisSubmitted
							//alert($("#additionalFteid").val());
							isSubmitted =false;
							$("#rForm").submit();
						}
					});
					return true;
				}
			} catch (e) {
				//alert(e.message);
			}
		});
		$("#formReset").click(function(){
			$("#message").removeClass();
			$("#message").html("");
			document.getElementById("rForm").reset();
		});
		var isSubmitted = false;
		$("#save").click(function() {
			$("#message").removeClass();
			$("#message").html("");
			if(isSubmitted)
				{
					return;
				}
			if (validateData('rForm')) {
				if($("#aFTE").val()=="")
					{
						$("#aFTE").val(-1);
					}
				var dataString = $("#rForm").serialize();
				//alert(dataString);
				isSubmitted=true;
				$.ajax({
					type : "POST",
					url : "./saveAdditionalFTE",
					data : dataString,
					async : false,
					success : function(data) {
						//alert(data);
						if($("#aFTE").val()<0)
						{
							$("#aFTE").val("");
						}
						if(data>=0)
							{
								if(data!="failure")
									{
										$("#message").addClass('actionPerformedMessage');
										if(deleteFTE)
											{
												$("#message").html('<msg:message code="fte.succ.delt.msg"/>');
											}else
											{
												$("#message").html('<msg:message code="msg.common.datasave.success"/>');
											}
										if($("#additionalFteid").val()==""||$("#additionalFteid").val()<0)
											{
												$("#additionalFteid").val(data);
											}
									}else
										{
											$("#message").addClass('errorMessageDisp');
											$("#message").html('<msg:message code="fte.actn.fail.err.msg"/>');
										}
							}else
								{
									$("#message").addClass('errorMessageDisp');
									$("#message").html('<msg:message code="fte.dt.outofrange.err.msg"/>');
								}
						if(deleteFTE)
						{
							$("#additionalFteid").val(-1);
							deleteFTE = false;
						}
						//document.location.reload();
						//location.reload;
						// append the returned result to your #dynamicdivisSubmitted
						//alert($("#additionalFteid").val());
						isSubmitted =false;
						if(data>=0)
							{
								$("#rForm").submit();
							}
					}
				});
				return true;
			}
		});
		$("#saveNext").click(function() {
			if($("#fDate").val()=="" && $("#tDate").val()=="" && 
					$('#jobrole').val()==-1 && $("#site").val()=="NONE"){
				location.href='./labourCost';
				return;
			}
			
			$("#message").removeClass();
			$("#message").html("");
			if(isSubmitted)
				{
					return;
				}
			if (validateData('rForm')) {
				var dataString = $("#rForm").serialize();
				//alert(dataString);
				isSubmitted=true;
				$.ajax({
					type : "POST",
					url : "./saveAdditionalFTE",
					data : dataString+"&action=labourCost",
					async : false,
					success : function(data) {
					$('#rForm').attr('action', data);
					$("#rForm").submit();
						isSubmitted =false;
					}
				});
				return true;
			}
		});
		$('#fDate').datepicker({
			showOn : 'button',
			buttonImageOnly : true,
			buttonImage : '../images/icon_cal.png',
			dateFormat: 'dd/mm/yy',
		    beforeShowDay: function (date) {
		       if (date.getDate() == 1) {
		           return [true, ''];
		       }
		       return [false, ''];
		    }
		}).focus(function() {
	        $("#fDate").datepicker("show");
	    });
		function getLastDayOfYearAndMonth(year, month)
		{
		    return(new Date((new Date(year, month + 1, 1)) - 1)).getDate();
		}
		$("#tDate").datepicker({
			showOn : 'button',
			buttonImageOnly : true,
			buttonImage : '../images/icon_cal.png',
			dateFormat: 'dd/mm/yy',
			 beforeShowDay: function (date) {
			       if (date.getDate() ==
			            getLastDayOfYearAndMonth(date.getFullYear(), date.getMonth())) {
			           return [true, ''];
			       }
			       return [false, ''];
			    }
		}).focus(function() {
	        $("#tDate").datepicker("show");
	    });
		
		/* $("#jobrole").on("change", function() {
			var dataString = $("#rForm").serialize();
			$.ajax({
				type : "POST",
				url : "./jobStages",
				data : dataString,
				async : false,
				success : function(response) {
					var htmlStr = "<option value='-1' label='--- Select ---' selected='selected'>-- Select ---</option>";
					$.each(response, function(key, value) {
						htmlStr += "<option value='"+key+"'>"+value+"</option>";
					});
					
					$("#jobStage").html(htmlStr);
					$("#jobStage").removeAttr("disabled");
					if($("#jobStageValue").val() != -1){
						var jobStage = $("#jobStageValue").val();
						$("#jobStage option[value='" + jobStage + "']").attr("selected","selected");
					}
				}
			});
		}); 
		
		if($("#jobrole").find('option:selected').val() != -1){
			$("#jobrole").trigger("change");
		}*/
	});
	function changeView(obj)
	{
		try
		{
			//If onshore check box checked
			if(obj.id=='onshoreCount')
				{	
					if($('#overallCount').get(0).checked)
					{
						$('#overallCount').attr('checked', false);
					}
					if(obj.checked)
						{
							if(!$('#offshoreCount').get(0).checked)
							{
								$("#offshore").css('display',"none");
							}
							$("#onshore").css('display',"");
						}else
						{
							$("#onshore").css('display',"none");
							if($('#offshoreCount').get(0).checked)
							{
								$("#offshore").css('display',"");
								$('#overallCount').attr('checked', false);
							}
							
						}
				}
			//If offshore check box checked
			if(obj.id=='offshoreCount')
			{
				if($('#overallCount').get(0).checked)
				{
					$('#overallCount').attr('checked', false);
				}
				if(obj.checked)
					{
						$("#offshore").css('display',"");
						if(!$('#onshoreCount').get(0).checked)
						{
							$("#onshore").css('display',"none");
						}
					}else
					{
						$("#offshore").css('display',"none");
						if($('#onshoreCount').get(0).checked)
						{
							$("#onshore").css('display',"");
							$('#overallCount').attr('checked', false);
						}
					}
			}
			//If overall check box checked
			if(obj.id=='overallCount')
			{
				if(obj.checked)
					{
						$("#offshore").css('display',"");
						$("#onshore").css('display',"");
						$('#offshoreCount').attr('checked', true);
						$('#onshoreCount').attr('checked', true);
					}else
					{
						$("#offshore").css('display',"none");
						$("#onshore").css('display',"none");
						$('#offshoreCount').attr('checked', false);
						$('#onshoreCount').attr('checked', false);
					}
			}else{
				if(!$('#onshoreCount').get(0).checked && !$('#offshoreCount').get(0).checked ||$('#onshoreCount').get(0).checked && $('#offshoreCount').get(0).checked)
				{
					$('#overallCount').attr('checked', true);
				}
			}
			if($('#overallCount').get(0).checked)
			{
				$("#offshore").css('display',"");
				$("#onshore").css('display',"");
			}
		}catch (e) {
			//alert(e.message);
		}
	}
	function toggleDisplay(value)
	{
			if($("#oppDetails").css('display')=="none")
			{
				$("#oppDetails").css('display',"");
			}else
			{
				$("#oppDetails").css('display',"none");
			}
	}
	function isNotAuthorized() {
		if ("${hasEditSolAccess}" == 'false') {
			$("#message").addClass('errorMessageDisp');
			$("#message").html(
					'<msg:message code="msg.common.err.notauthorized"/>');
			return true;
		}
		return false;
	}
	function generate()
	{
		if(isNotAuthorized())
			{
				return false;
			}
		$('#message').addClass('actionPerformedMessage');
		$('#message').html('<msg:message code="fte.generate.msg"/>');
		var img = document.createElement("IMG");
		img.src = "../images/loading.gif";
		document.getElementById('message').appendChild(img);
		document.location.href='./generateFTE';
	}
</script>


<!--start your page from here  -->
<div id="serviceBucket" style="margin-bottom:10px;">
			<jsp:include page="./serviceBucket.jsp"/>
		</div>

<div align="center" id="message"></div>
<form:form id="rForm" method="POST" commandName="reviewFTEForm"
	action="./reviewFTE">
	<c:choose>
		<c:when
			test="${empty reviewFTEForm.nonEmptyRoles || reviewFTEForm.opMode == 'V' || reviewFTEForm.sServiceScope<0}">
			<c:if
				test="${empty reviewFTEForm.nonEmptyRoles && reviewFTEForm.opMode == 'V'}">
				<%-- <div align="left" class="errorMessageDisp">
					<msg:message code="fte.solid.err.msg" />
				</div> --%>
			</c:if>
			<c:if
				test="${empty reviewFTEForm.nonEmptyRoles && reviewFTEForm.opMode != 'V'}">
				<c:choose>
					<c:when test="${empty reviewFTEForm.monthYears}">
						<div align="left" class="errorMessageDisp">
							<msg:message code="fte.timeline.err.msg" />
						</div>
					</c:when>
					<%-- <c:otherwise>
						<div align="left" class="errorMessageDisp">
							<msg:message code="fte.solid.err.msg" />
						</div>
					</c:otherwise> --%>
				</c:choose>
			</c:if>
			<c:if
				test="${not empty reviewFTEForm.nonEmptyRoles && reviewFTEForm.opMode == 'V'}">
				<div class="tdOddRowCenter" style="font-weight: bold;" align="left">
					<msg:message code="fte.non.edit.msg" />
				</div>
			</c:if>
		</c:when>
		<%-- <c:otherwise>
					
						<div class="tdOddRowCenter" style="font-weight: bold;" 
							id="message"></div>
					
				</c:otherwise> --%>
	</c:choose>

	<c:set var="defaultVal" value="NA" />
	<c:set var="defaultDispVal" value="_" />
	<c:if test="${empty reviewFTEForm.monthYears }">
		<c:set var="splittedYrs" value="${fn:split('Year', ';')}" />
	</c:if>
	<c:if test="${not empty reviewFTEForm.monthYears }">
		<c:set var="splittedYrs"
			value="${fn:split(reviewFTEForm.monthYears, ';')}" />
	</c:if>
	<c:set var="loopLength" value="${fn:length(splittedYrs)}" />
	
	<input type="hidden" id="solutionJobRoleModel" name="solutionJobRoleModel" value="${reviewFTEForm.solutionJobRoleModel}" />

	<table width="1112" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><div class="tdHeaderLabel FteHeaderText"
					style="mafloat: left; margin-bottom: 5px;">
					<msg:message code="fte.header.msg" />
				</div></td>
		</tr>
		<tr>
			<td align="center"><jsp:include page="opportunitySummary.jsp"></jsp:include></td>
		</tr>
				
		<tr>
			<td height="5"></td>
		</tr>
		<tr>
			<td>
				<table width="1112" border="0" cellspacing="0" cellpadding="0">
					<%-- <tr>
						<td colspan="${loopLength+1}" class="tdHeaderLabel"><div
								class="tdHeaderLabel FteHeaderText">
								<msg:message code="fte.header.msg" />
							</div></td>
					</tr> --%>
					<tr>
						<td>
							<div class="FteBodyDiv">
								<div class="FTEscrollProperty">
									<table cellspacing="0" cellpadding="0" class="tdLabelLeft">
										<tr>
											<td width="130" height="40">Select Service Scope</td>
											<td width="200"><form:select path="sServiceScope"
													cssClass="listBoxSmall"
													title="Initial view is FTE pool value& On select service scope FTE breakup view">
													<form:option value="-1" label="--- Select ---" />
													<form:options items="${reviewFTEForm.dropDownSS}" />
												</form:select></td>
											<!-- <td width="140" class="tdInputBox"><input
												type="checkbox" id="onshoreCount"
												onclick="changeView(this);" /> <b>Onshore Count</b></td>
											<td width="140" class="tdInputBox"><input
												id="offshoreCount" type="checkbox"
												onclick="changeView(this);"> <b
												style="vertical-align: baseline;">Offshore Count</b></td>
											<td class="tdInputBox"><input id="overallCount"
												type="checkbox" checked="checked"
												onclick="changeView(this);"> <b>Overall Count</b></td> -->

											<td><a class="TabsCommonButtons" id="genrate"
												onclick="generate();"
												onmouseover="Tip('Click to re-generate FTE table values.<br/>All additional FTE values will get removed!', BGCOLOR, '#FFFFFF')"
												onmouseout="UnTip()">Re-Generate</a></td>
											
											<td>
												<a href="/ADM_PRE/solution/tntReviewFTE" style="text-decoration: none;">
													<i><font style="color: blue;" size="2px"></font> &nbsp; <b>Click For Review FTE: TnT</b></i>
												</a>
											</td>
										</tr>

									</table>

									<table border="0" cellpadding="0" cellspacing="2"
										class="reviewFTETable">
										<tbody id="onshoreLocal">
											<tr>
												<td width="230" height="25" bgcolor="#e3e3e3"><strong>Onshore Local&nbsp;&nbsp;&nbsp;(Time
														lines)</strong></td>


												<c:forEach var="value" items="${splittedYrs}">
													<c:choose>
														<c:when test="${not empty value && value != defaultVal}">
															<td width="100" align="center" bgcolor="#e3e3e3"><strong><form:label
																		path="monthYear">
																		<c:out value="${value}" />
																	</form:label></strong></td>
														</c:when>
														<c:otherwise>
															<td width="100" align="center" bgcolor="#e3e3e3"><strong></strong></td>
														</c:otherwise>
													</c:choose>
												</c:forEach>

											</tr>
											<c:if test="${not empty reviewFTEForm.nonEmptyRoles}">
												<c:set var="nonEmpRoles"
													value="${fn:split(reviewFTEForm.nonEmptyRoles, ';')}" />
													
												<c:forEach var="values" items="${nonEmpRoles}"
												varStatus="status">
												<tr>
													<td height="25" bgcolor="#f7f7f7" nowrap="nowrap">${values}</td>
													<c:set var="dataString"
														value="${reviewFTEForm.onShoreLocalDataList[status.index]}" />
													<c:choose>
														<c:when test="${not empty dataString}">
															<c:set var="splitted"
																value="${fn:split(dataString, ';')}" />
															<c:set var="localLength" value="${fn:length(splitted)}" />
															<c:forEach var="value" items="${splitted}">
																<c:choose>
																	<c:when
																		test="${not empty value && value != defaultVal}">
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${value}"></c:out></td>
																	</c:when>
																	<c:otherwise>
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${defaultDispVal}"></c:out></td>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
															<c:choose>
																<c:when test="${(loopLength gt localLength)}">
																	<c:forEach begin="1" end="${loopLength-localLength}">
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${defaultDispVal}"></c:out></td>
																	</c:forEach>
																</c:when>
															</c:choose>
														</c:when>
														<c:otherwise>
															<c:forEach var="val" begin="1" end="${loopLength}">
																<td align="center" bgcolor="#f7f7f7"><c:out
																		value="${defaultDispVal}"></c:out></td>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
											</c:if>
											<c:if test="${empty reviewFTEForm.nonEmptyRoles}">
												<c:set var="nonEmpRoles" value="" />
												<%-- <c:forEach var="values"
													items="${reviewFTEForm.dropDownJobRoles}"
													varStatus="status">
													<c:set var="nonEmpRoles"
														value="${nonEmpRoles},${values.value}" />
												</c:forEach> --%>
												<tr>
													<td class="reviewFTENoData" colspan="13" align="center">
														<msg:message code="fte.noData" />
													</td>
												</tr>
											</c:if>
											


											<tr>
												<td>&nbsp;</td>
											</tr>
										</tbody>
										<tbody id="onshoreGsci">
											<tr>
												<td width="230" height="25" bgcolor="#e3e3e3"><strong>Onshore GSC&nbsp;&nbsp;&nbsp;(Time
														lines)</strong></td>


												<c:forEach var="value" items="${splittedYrs}">
													<c:choose>
														<c:when test="${not empty value && value != defaultVal}">
															<td width="100" align="center" bgcolor="#e3e3e3"><strong><form:label
																		path="monthYear">
																		<c:out value="${value}" />
																	</form:label></strong></td>
														</c:when>
														<c:otherwise>
															<td width="100" align="center" bgcolor="#e3e3e3"><strong></strong></td>
														</c:otherwise>
													</c:choose>
												</c:forEach>

											</tr>
											<c:if test="${not empty reviewFTEForm.nonEmptyRoles}">
												<c:set var="nonEmpRoles"
													value="${fn:split(reviewFTEForm.nonEmptyRoles, ';')}" />
												<c:forEach var="values" items="${nonEmpRoles}"
												varStatus="status">
												<tr>
													<td height="25" bgcolor="#f7f7f7" nowrap="nowrap">${values}</td>
													<c:set var="dataString"
														value="${reviewFTEForm.onShoreGsciDataList[status.index]}" />
													<c:choose>
														<c:when test="${not empty dataString}">
															<c:set var="splitted"
																value="${fn:split(dataString, ';')}" />
															<c:set var="localLength" value="${fn:length(splitted)}" />
															<c:forEach var="value" items="${splitted}">
																<c:choose>
																	<c:when
																		test="${not empty value && value != defaultVal}">
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${value}"></c:out></td>
																	</c:when>
																	<c:otherwise>
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${defaultDispVal}"></c:out></td>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
															<c:choose>
																<c:when test="${(loopLength gt localLength)}">
																	<c:forEach begin="1" end="${loopLength-localLength}">
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${defaultDispVal}"></c:out></td>
																	</c:forEach>
																</c:when>
															</c:choose>
														</c:when>
														<c:otherwise>
															<c:forEach var="val" begin="1" end="${loopLength}">
																<td align="center" bgcolor="#f7f7f7"><c:out
																		value="${defaultDispVal}"></c:out></td>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
												
											</c:if>
											<c:if test="${empty reviewFTEForm.nonEmptyRoles}">
												<c:set var="nonEmpRoles" value="" />
												<%-- <c:forEach var="values"
													items="${reviewFTEForm.dropDownJobRoles}"
													varStatus="status">
													<c:set var="nonEmpRoles"
														value="${nonEmpRoles},${values.value}" />
												</c:forEach> --%>
												<tr>
													<td class="reviewFTENoData" colspan="13" align="center">
														<msg:message code="fte.noData" />
													</td>
												</tr>
											</c:if>
											

											<tr>
												<td>&nbsp;</td>
											</tr>
										</tbody>
										<tbody id="onshore3pp">
											<tr>
												<td width="230" height="25" bgcolor="#e3e3e3"><strong>Onshore 3PP&nbsp;&nbsp;&nbsp;(Time
														lines)</strong></td>


												<c:forEach var="value" items="${splittedYrs}">
													<c:choose>
														<c:when test="${not empty value && value != defaultVal}">
															<td width="100" align="center" bgcolor="#e3e3e3"><strong><form:label
																		path="monthYear">
																		<c:out value="${value}" />
																	</form:label></strong></td>
														</c:when>
														<c:otherwise>
															<td width="100" align="center" bgcolor="#e3e3e3"><strong></strong></td>
														</c:otherwise>
													</c:choose>
												</c:forEach>

											</tr>
											<c:if test="${not empty reviewFTEForm.nonEmptyRoles}">
												<c:set var="nonEmpRoles"
													value="${fn:split(reviewFTEForm.nonEmptyRoles, ';')}" />
												<c:forEach var="values" items="${nonEmpRoles}"
												varStatus="status">
												<tr>
													<td height="25" bgcolor="#f7f7f7" nowrap="nowrap">${values}</td>
													<c:set var="dataString"
														value="${reviewFTEForm.onShore3ppDataList[status.index]}" />
													<c:choose>
														<c:when test="${not empty dataString}">
															<c:set var="splitted"
																value="${fn:split(dataString, ';')}" />
															<c:set var="localLength" value="${fn:length(splitted)}" />
															<c:forEach var="value" items="${splitted}">
																<c:choose>
																	<c:when
																		test="${not empty value && value != defaultVal}">
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${value}"></c:out></td>
																	</c:when>
																	<c:otherwise>
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${defaultDispVal}"></c:out></td>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
															<c:choose>
																<c:when test="${(loopLength gt localLength)}">
																	<c:forEach begin="1" end="${loopLength-localLength}">
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${defaultDispVal}"></c:out></td>
																	</c:forEach>
																</c:when>
															</c:choose>
														</c:when>
														<c:otherwise>
															<c:forEach var="val" begin="1" end="${loopLength}">
																<td align="center" bgcolor="#f7f7f7"><c:out
																		value="${defaultDispVal}"></c:out></td>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
												
											</c:if>
											<c:if test="${empty reviewFTEForm.nonEmptyRoles}">
												<c:set var="nonEmpRoles" value="" />
												<%-- <c:forEach var="values"
													items="${reviewFTEForm.dropDownJobRoles}"
													varStatus="status">
													<c:set var="nonEmpRoles"
														value="${nonEmpRoles},${values.value}" />
												</c:forEach> --%>
												<tr>
													<td class="reviewFTENoData" colspan="13" align="center">
														<msg:message code="fte.noData" />
													</td>
												</tr>
											</c:if>
											

											<tr>
												<td>&nbsp;</td>
											</tr>
										</tbody>
										<tbody id="nearshore">
											<tr>
												<td width="230" height="25" bgcolor="#e3e3e3"><strong>Nearshore&nbsp;&nbsp;&nbsp;
														(Time lines)</strong></td>

												<c:forEach var="value" items="${splittedYrs}">
													<c:choose>
														<c:when test="${not empty value && value != defaultVal}">
															<td width="100" align="center" bgcolor="#e3e3e3"><strong><form:label
																		path="monthYear">
																		<c:out value="${value}" />
																	</form:label></strong></td>
														</c:when>
														<c:otherwise>
															<td></td>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
											<c:if test="${not empty reviewFTEForm.nonEmptyRoles}">
												<c:set var="nonEmpRoles"
													value="${fn:split(reviewFTEForm.nonEmptyRoles, ';')}" />
												<c:forEach var="values" items="${nonEmpRoles}"
												varStatus="status">
												<tr>
													<td height="25" bgcolor="#f7f7f7" nowrap="nowrap">${values}</td>
													<c:set var="dataString"
														value="${reviewFTEForm.nearShoreDataList[status.index]}" />
													<c:choose>
														<c:when test="${not empty dataString}">
															<c:set var="splitted"
																value="${fn:split(dataString, ';')}" />
															<c:set var="localLength" value="${fn:length(splitted)}" />
															<c:forEach var="value" items="${splitted}">
																<c:choose>
																	<c:when
																		test="${not empty value && value != defaultVal}">
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${value}"></c:out></td>
																	</c:when>
																	<c:otherwise>
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${defaultDispVal}"></c:out></td>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
															<c:choose>
																<c:when test="${(loopLength gt localLength)}">
																	<c:forEach begin="1" end="${loopLength-localLength}">
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${defaultDispVal}"></c:out></td>
																	</c:forEach>
																</c:when>
															</c:choose>
														</c:when>
														<c:otherwise>
															<c:forEach var="val" begin="1" end="${loopLength}">
																<td align="center" bgcolor="#f7f7f7"><c:out
																		value="${defaultDispVal}"></c:out></td>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
											</c:if>
											
											<c:if test="${empty reviewFTEForm.nonEmptyRoles}">
												<c:set var="nonEmpRoles" value="" />
												<%-- <c:forEach var="values"
													items="${reviewFTEForm.dropDownJobRoles}"
													varStatus="status">
													<c:set var="nonEmpRoles"
														value="${nonEmpRoles},${values.value}" />
												</c:forEach> --%>
												<tr>
													<td class="reviewFTENoData" colspan="13" align="center">
														<msg:message code="fte.noData" />
													</td>
												</tr>
											</c:if>
											<tr>
												<td>&nbsp;</td>
											</tr>
											
										</tbody>
										<tbody id="offshore">
											<tr>
												<td width="230" height="25" bgcolor="#e3e3e3"><strong>Offshore&nbsp;&nbsp;&nbsp;
														(Time lines)</strong></td>

												<c:forEach var="value" items="${splittedYrs}">
													<c:choose>
														<c:when test="${not empty value && value != defaultVal}">
															<td width="100" align="center" bgcolor="#e3e3e3"><strong><form:label
																		path="monthYear">
																		<c:out value="${value}" />
																	</form:label></strong></td>
														</c:when>
														<c:otherwise>
															<td></td>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</tr>
											<c:if test="${not empty reviewFTEForm.nonEmptyRoles}">
												<c:set var="nonEmpRoles"
													value="${fn:split(reviewFTEForm.nonEmptyRoles, ';')}" />
													
												<c:forEach var="values" items="${nonEmpRoles}"
												varStatus="status">
												<tr>
													<td height="25" bgcolor="#f7f7f7" nowrap="nowrap">${values}</td>
													<c:set var="dataString"
														value="${reviewFTEForm.offShoreDataList[status.index]}" />
													<c:choose>
														<c:when test="${not empty dataString}">
															<c:set var="splitted"
																value="${fn:split(dataString, ';')}" />
															<c:set var="localLength" value="${fn:length(splitted)}" />
															<c:forEach var="value" items="${splitted}">
																<c:choose>
																	<c:when
																		test="${not empty value && value != defaultVal}">
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${value}"></c:out></td>
																	</c:when>
																	<c:otherwise>
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${defaultDispVal}"></c:out></td>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
															<c:choose>
																<c:when test="${(loopLength gt localLength)}">
																	<c:forEach begin="1" end="${loopLength-localLength}">
																		<td align="center" bgcolor="#f7f7f7"><c:out
																				value="${defaultDispVal}"></c:out></td>
																	</c:forEach>
																</c:when>
															</c:choose>
														</c:when>
														<c:otherwise>
															<c:forEach var="val" begin="1" end="${loopLength}">
																<td align="center" bgcolor="#f7f7f7"><c:out
																		value="${defaultDispVal}"></c:out></td>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
											</c:if>
											
											<c:if test="${empty reviewFTEForm.nonEmptyRoles}">
												<c:set var="nonEmpRoles" value="" />
												<%-- <c:forEach var="values"
													items="${reviewFTEForm.dropDownJobRoles}"
													varStatus="status">
													<c:set var="nonEmpRoles"
														value="${nonEmpRoles},${values.value}" />
												</c:forEach> --%>
												<tr>
													<td class="reviewFTENoData" colspan="13" align="center">
														<msg:message code="fte.noData" />
													</td>
												</tr>
											</c:if>
											<tr>
												<td>&nbsp;</td>
											</tr>
										</tbody>
										<tr>
											<td height="25" style="padding: 0px 0px 0px 0px;"
												colspan="${loopLength+1}">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="fteAdditional">
													<tr>
														<td height="25" bgcolor="#e3e3e3"><strong>Addition
																of Resources Details</strong></td>
													</tr>
													<tr>
														<td height="25" align="left" valign="top"><table
																width="1100" border="0" cellpadding="0" cellspacing="0"
																style="margin-top: 1px;">
																<tr>
																	<td width="81" height="25">Select Job Role and Job Stage</td>
																	<td width="187"><form:select path="jobrole"
																			class="listBoxSmall">
																			<form:option value="-1" label="--- Select ---" />
																			<c:forEach var="item"
																				items="${reviewFTEForm.dropDownJobRoles}">
																				<c:choose>
																					<c:when test="${item.key==jobrole}">
																						<form:option selected="true" value="${item.key}">${item.value}</form:option>
																					</c:when>
																					<c:otherwise>
																						<form:option value="${item.key}">${item.value}</form:option>
																					</c:otherwise>
																				</c:choose>
																			</c:forEach>
																		</form:select></td>
																		<%-- <td width="81" height="25">Select Job Stage</td>
																		<input type="hidden" id="jobStageValue" name="jobStageValue" value="${reviewFTEForm.jobStage}" />
																	<td width="187"><form:select path="jobStage" id="jobStage"
																			class="listBoxSmall" disabled="disabled">
																		</form:select></td> --%>
																	<td width="81">Select Site</td>
																	<td width="193"><form:select class="listBoxSmall"
																			name="site" id="site" path="site">
																			<option value="NONE">--- Select ---</option>
																			<c:choose>
																				<c:when test="${'OnshoreLocal'== reviewFTEForm.site}">
																					<option selected="selected" value="OnshoreLocal">Onshore Local</option>
																				</c:when>
																				<c:otherwise>
																					<option value="OnshoreLocal">Onshore Local</option>
																				</c:otherwise>
																			</c:choose>
																			<c:choose>
																				<c:when test="${'OnshoreGsci'== reviewFTEForm.site}">
																					<option selected="selected" value="OnshoreGsci">Onshore GSC</option>
																				</c:when>
																				<c:otherwise>
																					<option value="OnshoreGsci">Onshore GSC</option>
																				</c:otherwise>
																			</c:choose>
																			<c:choose>
																				<c:when test="${'Onshore3pp'== reviewFTEForm.site}">
																					<option selected="selected" value="Onshore3PP">Onshore 3PP</option>
																				</c:when>
																				<c:otherwise>
																					<option value="Onshore3PP">Onshore 3PP</option>
																				</c:otherwise>
																			</c:choose>
																			<c:choose>
																				<c:when test="${'Nearshore'== reviewFTEForm.site}">
																					<option selected="selected" value="Nearshore">Nearshore</option>
																				</c:when>
																				<c:otherwise>
																					<option value="Nearshore">Nearshore</option>
																				</c:otherwise>
																			</c:choose>
																			<c:choose>
																				<c:when test="${'Offshore'== reviewFTEForm.site}">
																					<option selected="selected" value="Offshore">Offshore</option>
																				</c:when>
																				<c:otherwise>
																					<option value="Offshore">Offshore</option>
																				</c:otherwise>
																			</c:choose>
																		</form:select></td>
																	<td width="52"># Of FTE</td>
																	<td width="120"><form:input maxlength="7"
																			type="text" id="aFTE" name="aFTE" path="aFTE"
																			value="" class="reviewFTEInputArea"
																			onkeypress="return isNumericWithDecimal(event,this);"></form:input></td>
																	<td width="39">From</td>
																	<td width="130"><form:input maxlength="10"
																			readonly="true" title="DD/MM/YYYY" path="fDate"
																			class="reviewFTEInputArea"></form:input></td>
																	<td width="23">To</td>
																	<td width="175"><form:input maxlength="10"
																			title="DD/MM/YYYY" path="tDate" readonly="true"
																			class="reviewFTEInputArea"></form:input></td>


																</tr>
																<tr>
																	<form:hidden path="additionalFteid"
																		cssStyle="border: none;" />
																</tr>
																</tr>
																<tr></tr>
															</table></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>


								</div>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="5"></td>
		</tr>
	</table>
	<!-- <div class="tabsButtonPanel"> -->
	<div style="float: left; width: 1112px; height: 35px;">
		<!-- <div class="TabsCommonButtons" onclick="javascript:clearForm();">Reset</div>
                	<div class="TabsCommonButtons" id="saveForm1">Save</div> -->
		<c:choose>
			<%-- <c:when
				test="${not empty reviewFTEForm.nonEmptyRoles && reviewFTEForm.opMode != 'V' && reviewFTEForm.sServiceScope>=0 && hasEditSolAccess!='false'}"> --%>
			<c:when
				test="${reviewFTEForm.opMode != 'V' && reviewFTEForm.sServiceScope>=0 && hasEditSolAccess!='false'}">
				<div class="portfolioButtons" id="saveNext">Next</div>
				<a class="portfolioButtons" id="save">Save</a>
				<a class="portfolioButtons" id="delete"
					onmouseover="Tip('Click to delete additional FTE', BGCOLOR, '#FFFFFF')"
					onmouseout="UnTip()">Delete</a>
				<a class="portfolioButtons" id="formReset">Reset</a>
			</c:when>
			<c:otherwise>
				<div class="portfolioButtons" onclick="location.href='./labourCost'" id="nextLabourCost">Next</div>
			</c:otherwise>
		</c:choose>
		<div class="portfolioButtons"
			onclick="location.href='./productivityLevers';">Previous</div>
	</div>
</form:form>

