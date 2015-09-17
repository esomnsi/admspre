<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="../js/wz_tooltip.js"></script>
<%@taglib prefix="msg" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
 $(document).ready(function(){
	 //var receivedVal = (new String(window.location).split('?')[1]).split("=")[1];
	 var recval = new String(window.location).split('?')[1];
	 if (recval.indexOf('&')!= -1) {
		 var recval1 = recval.split('&')[0];
		 var receivedVal = recval1.split("=")[1];
		 
	 } else {
		 var receivedVal = recval.split("=")[1];
	 }
	 $('#sServiceScope').find('option[value="'+receivedVal+'"]').prop('selected','1')
	 
	$('#sServiceScope').change(function() {
		var scopeId = $(this).val();
		if (scopeId != -1 ) {
			window.location = 'reviewsscopeFTE?sServiceScope='+scopeId;
		} else {
			window.location = 'reviewUpdatedFTE?sServiceScope='+scopeId;
		}
	});
	 $('#fdate').datepicker({
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
	 
	 
	 
	 $('#tDate').datepicker({
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
	 
	 $("#save").click(function() {
		 //$( "#rForm" ).submit();
		 
		 if (validateForm()){
			 //alert('validation Successful');
			 $( "#rForm" ).submit();
		 } else {
			 //alert('validation failed');
		 }
	 });
	 
});
 function validateForm() {
	   var result = false;	
	   var jobRoleFlag = false;
	   var siteFlag = false;
	   var fteFlag = false;
	   var fdateFlag = false;
	   var tdateFlag = false;
	   var dateChqFlag = false;
	   var message = "";
	   if ($("#jobrole").val()!= -1) {
		   jobRoleFlag = true;
		   $('#jobrole').css("borderColor","");
	   } else {
		   $('#jobrole').css("borderColor","red");
		   $('#jobrole').focus();
		   $("#message").addClass('errorMessageDisp');
		   message = "Job Role can not be blank";
	   }
	   
	   if ($("#site").val()!='NONE') {
		   siteFlag = true;
		   $('#site').css("borderColor","");
	   } else {
		   $('#site').css("borderColor","red");
		   $('#site').focus();
		   $("#message").addClass('errorMessageDisp');
		   message =  message + " " + "Site can not be blank";
	   }
	   
	   if ($("#aFTE").val()!="") {
		   if (!isNaN($('#aFTE').val())) {
			   fteFlag = true;
			   $('#aFTE').css("borderColor","");
		   } else {
			   $('#aFTE').css("borderColor","red");
			   $('#aFTE').focus();
			   $("#message").addClass('errorMessageDisp');
			   message =  message + " " + "FTE value should be numeric";
		   }
		   
	   } else {
		   $('#aFTE').css("borderColor","red");
		   $('#aFTE').focus();
		   $("#message").addClass('errorMessageDisp');
		   message =  message + " " + "FTE value can not be blank";
	   }
	   
	   if ($("#fdate").val()!="") {
		   if (isDate($('#fdate').val())){
			   fdateFlag = true;
			   $('#fdate').css("borderColor","");
		   } else {
			   $('#fdate').css("borderColor","red");
			   $('#fdate').focus();
			   $("#message").addClass('errorMessageDisp');
			   message =  message + " " + "From Date should be in Date";
		   }
	   } else {
		   $('#fdate').css("borderColor","red");
		   $('#fdate').focus();
		   $("#message").addClass('errorMessageDisp');
		   message =  message + " " + "From Date can not be blank";
	   } 

	   
	   
	   if ($("#tDate").val()!="") {
		   if (isDate($('#tDate').val())){
			   tdateFlag = true;
			   $('#tDate').css("borderColor","");
		   } else {
			   $('#tDate').css("borderColor","red");
			   $('#tDate').focus();
			   $("#message").addClass('errorMessageDisp');
			   message =  message + " " + "To Date should be date";
		   }
	   } else {
		   $('#tDate').css("borderColor","red");
		   $('#tDate').focus();
		   $("#message").addClass('errorMessageDisp');
		   message =  message + " " + "To Date can not be blank";
	   }
	   
	   if (fdateFlag & tdateFlag) {
		    var fromDt = $("#fdate").val();
		    var toDt = $("#tDate").val();
		    var dt = fromDt.split("/");
			var fDate = new Date( dt[1]+"/"+dt[0]+"/"+dt[2] );
			dt = toDt.split("/");
			var tDate = new Date( dt[1]+"/"+dt[0]+"/"+dt[2] );
			
			if(fDate>tDate) {
				$('#tDate').css("borderColor","red");
				$('#tDate').focus();
				$("#message").addClass('errorMessageDisp');
				message =  message + " " + "To Date should be greater than from date";
			} else {
				dateChqFlag = true;
				$('#tDate').css("borderColor","");
			}
	   } 
	   
	   if (jobRoleFlag & siteFlag & fteFlag & fdateFlag & tdateFlag & dateChqFlag){
		   result = true;
		   $("#message").html('');
	   } else {
		   $("#message").html(message);
	   }
	    //alert(result);
		return result;
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
		document.location.href='./regenerateFTE';
	}
	function getLastDayOfYearAndMonth(year, month)
	{
	    return(new Date((new Date(year, month + 1, 1)) - 1)).getDate();
	}
	
	
	
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
</script>


<!--start your page from here  -->
			<div id="serviceBucket" style="margin-bottom:0px;" >
					<table width="730" align="center">
					<tr>
						<td align="center" colspan="6">
						<jsp:include page="../solution/serviceBucket.jsp"/>
						</td>
					</tr>
					</table>
			</div>

<div align="center" id="message"></div>



<c:if test="${fn:length(errormessage)!=0}">
	<div align="center" id="errormessage" class="errorMessageDisp">${errormessage}</div>
</c:if>



<form:form id="rForm" method="POST" commandName="reviewFTEForm" action="./manipulateFTEValues?sServiceScope=${sServiceScope}">

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
								    		<td width="200">
								    		     <select name="sServiceScope" id="sServiceScope" class="listBoxSmall">
								    		     	<option value="-1">-----Select-----</option>
								    		     	<c:forEach var="servicescopeentry" items="${solutionScope}">
								    		     		<option value="${servicescopeentry.key}">${servicescopeentry.value}</option>
								    		     	</c:forEach>
								    		     </select>
								    		</td>
								    		<td>
								    			<a onmouseout="UnTip()" onmouseover="Tip('Click to re-generate FTE table values.&lt;br/&gt;All additional FTE values will get removed!', BGCOLOR, '#FFFFFF')" onclick="generate();" id="genrate" class="TabsCommonButtons">Re-Generate</a>
								    		</td>
								    	</tr>
								    </table>
								    
								    
								    
								    
								    
								    <c:if test="${isrecordsavailable}">
									 <table class="reviewFTETable" cellspacing="2" cellpadding="0" class="reviewFTETable">
									  
									  <!-- -----------------------------------------Onshore Local FTE Count---------------------------------------------------------------- -->
									   <tbody id="onshoreLocal">
									   		<tr>
									   		    <td height="25" bgcolor="#e3e3e3"><strong>Onshore Local&nbsp;&nbsp;&nbsp;(Time lines)</strong></td>
									   		    <c:forEach var="monthyear" items="${monthList}">
									   		       <td width="100" align="center" bgcolor="#e3e3e3"><strong><c:out value="${monthyear}"/></strong></td>
									   		    </c:forEach>
									   		</tr>
									   		<c:forEach var="scopeentry" items="${OnShoreLocalFTEMap}">
									   		   <tr>
									   		        <td bgcolor="#f7f7f7" nowrap="nowrap" height="25"><c:out value="${scopeentry.key}"/></td>
									   		        <c:forEach var="yearmonthentry" items="${scopeentry.value}">
									   		          <td bgcolor="#f7f7f7" align="center">
									   		          	<c:choose>
									   		          	     <c:when test="${yearmonthentry.value== 0.0 }">
									   		          	     	<c:out value="-"/>
									   		          	     </c:when>
									   		          	     <c:otherwise>
									   		          	     	<c:out value="${yearmonthentry.value}"/>
									   		          	     </c:otherwise>
									   		          	</c:choose>
									   		          </td>
									   		        </c:forEach>
									   		   </tr>
									   		</c:forEach>
									   		<tr>
												<td>&nbsp;</td>
											</tr>
									   </tbody>
									   
									   <!-- -----------------------------------------Onshore GSCi FTE Count---------------------------------------------------------------- -->
									  <tbody id="onshoreLocal">
									   		<tr>
									   		    <td height="25" bgcolor="#e3e3e3"><strong>Onshore GSC&nbsp;&nbsp;&nbsp;(Time lines)</strong></td>
									   		    <c:forEach var="monthyear" items="${monthList}">
									   		       <td width="100" align="center" bgcolor="#e3e3e3"><strong><c:out value="${monthyear}"/></strong></td>
									   		    </c:forEach>
									   		</tr>
									   		<c:forEach var="scopeentry" items="${OnShoreLocalGSCFTEMap}">
									   		   <tr>
									   		        <td bgcolor="#f7f7f7" nowrap="nowrap" height="25"><c:out value="${scopeentry.key}"/></td>
									   		        <c:forEach var="yearmonthentry" items="${scopeentry.value}">
									   		          <td bgcolor="#f7f7f7" align="center">
									   		          		<c:choose>
									   		          	     <c:when test="${yearmonthentry.value== 0.0 }">
									   		          	     	<c:out value="-"/>
									   		          	     </c:when>
									   		          	     <c:otherwise>
									   		          	     	<c:out value="${yearmonthentry.value}"/>
									   		          	     </c:otherwise>
									   		          	</c:choose>
									   		          </td>
									   		        </c:forEach>
									   		   </tr>
									   		</c:forEach>
									   		<tr>
												<td>&nbsp;</td>
											</tr>
									   </tbody>
									   
									   <!-- -----------------------------------------Onshore 3PP FTE Count---------------------------------------------------------------- -->
									   <tbody id="onshoreLocal">
									   		<tr>
									   		    <td height="25" bgcolor="#e3e3e3"><strong>Onshore 3PP&nbsp;&nbsp;&nbsp;(Time lines)</strong></td>
									   		    <c:forEach var="monthyear" items="${monthList}">
									   		       <td width="100" align="center" bgcolor="#e3e3e3"><strong><c:out value="${monthyear}"/></strong></td>
									   		    </c:forEach>
									   		</tr>
									   		<c:forEach var="scopeentry" items="${OnShoreLocal3PPFTEMap}">
									   		   <tr>
									   		        <td bgcolor="#f7f7f7" nowrap="nowrap" height="25"><c:out value="${scopeentry.key}"/></td>
									   		        <c:forEach var="yearmonthentry" items="${scopeentry.value}">
									   		          <td bgcolor="#f7f7f7" align="center">
									   		          	<c:choose>
									   		          	     <c:when test="${yearmonthentry.value== 0.0 }">
									   		          	     	<c:out value="-"/>
									   		          	     </c:when>
									   		          	     <c:otherwise>
									   		          	     	<c:out value="${yearmonthentry.value}"/>
									   		          	     </c:otherwise>
									   		          	</c:choose>
									   		          </td>
									   		        </c:forEach>
									   		   </tr>
									   		</c:forEach>
									   		<tr>
												<td>&nbsp;</td>
											</tr>
									   </tbody>
									   
									   <!-- -----------------------------------------Nearshore FTE Count---------------------------------------------------------------- -->
									   <tbody id="onshoreLocal">
									   		<tr>
									   		    <td height="25" bgcolor="#e3e3e3"><strong>Nearshore&nbsp;&nbsp;&nbsp;(Time lines)</strong></td>
									   		    <c:forEach var="monthyear" items="${monthList}">
									   		       <td width="100" align="center" bgcolor="#e3e3e3"><strong><c:out value="${monthyear}"/></strong></td>
									   		    </c:forEach>
									   		</tr>
									   		<c:forEach var="scopeentry" items="${NearshoreFTEMap}">
									   		   <tr>
									   		        <td bgcolor="#f7f7f7" nowrap="nowrap" height="25"><c:out value="${scopeentry.key}"/></td>
									   		        <c:forEach var="yearmonthentry" items="${scopeentry.value}">
									   		          <td bgcolor="#f7f7f7" align="center">
									   		          	<c:choose>
									   		          	     <c:when test="${yearmonthentry.value== 0.0 }">
									   		          	     	<c:out value="-"/>
									   		          	     </c:when>
									   		          	     <c:otherwise>
									   		          	     	<c:out value="${yearmonthentry.value}"/>
									   		          	     </c:otherwise>
									   		          	</c:choose>
									   		          </td>
									   		        </c:forEach>
									   		   </tr>
									   		</c:forEach>
									   		<tr>
												<td>&nbsp;</td>
											</tr>
									   </tbody>
									   
									   <!-- -----------------------------------------Offshore FTE Count---------------------------------------------------------------- -->
									   <tbody id="onshoreLocal">
									   		<tr>
									   		    <td height="25" bgcolor="#e3e3e3"><strong>Offshore&nbsp;&nbsp;&nbsp;(Time lines)</strong></td>
									   		    <c:forEach var="monthyear" items="${monthList}">
									   		       <td width="100" align="center" bgcolor="#e3e3e3"><strong><c:out value="${monthyear}"/></strong></td>
									   		    </c:forEach>
									   		</tr>
									   		<c:forEach var="scopeentry" items="${OffshoreFTEMap}">
									   		   <tr>
									   		        <td bgcolor="#f7f7f7" nowrap="nowrap" height="25"><c:out value="${scopeentry.key}"/></td>
									   		        <c:forEach var="yearmonthentry" items="${scopeentry.value}">
									   		          <td bgcolor="#f7f7f7" align="center">
									   		          	<c:choose>
									   		          	     <c:when test="${yearmonthentry.value== 0.0 }">
									   		          	     	<c:out value="-"/>
									   		          	     </c:when>
									   		          	     <c:otherwise>
									   		          	     	<c:out value="${yearmonthentry.value}"/>
									   		          	     </c:otherwise>
									   		          	</c:choose>
									   		          </td>
									   		        </c:forEach>
									   		   </tr>
									   		</c:forEach>
									   		<tr>
												<td>&nbsp;</td>
											</tr>
									   </tbody> 
									   
									   
									   <!-- -----------------------------------------Additional FTE-------------------------------------------- -->
									   <tbody><tr>
											<td height="25" colspan="23" style="padding: 0px 0px 0px 0px;">
												<table cellspacing="0" cellpadding="0" border="0" width="100%" class="fteAdditional">
													<tbody><tr>
														<td bgcolor="#e3e3e3" height="25"><strong>Addition of Resources Details</strong></td>
													</tr>
													<tr>
														<td align="left" valign="top" height="25"><table cellspacing="0" cellpadding="0" border="0" width="1100" style="margin-top: 1px;">
																<tbody><tr>
																	<td width="81" height="25">Select Job Role and Job Stage</td>
																	<td width="187">
																			<form:select path="jobrole" class="listBoxSmall">
																				<form:option value="-1" label="--- Select ---" />
																				 	<c:forEach var="jobrole" items="${jobRoleDropdownMap}">
																				 		<form:option value="${jobrole.key}">${jobrole.value}</form:option>
																				 	</c:forEach>
																			</form:select>
																	</td>
																		
																	<td width="81">Select Site</td>
																	<td width="193">
																		<form:select class="listBoxSmall" name="site" id="site" path="site">
																			<option value="NONE">--- Select ---</option>
																					<option value="OnshoreLocal">Onshore Local</option>
																					<option value="OnshoreGsci">Onshore GSC</option>
																					<option value="Onshore3PP">Onshore 3PP</option>
																					<option value="Nearshore">Nearshore</option>
																					<option value="Offshore">Offshore</option>
																		</form:select>
																	</td>
																	<td width="52"># Of FTE</td>
																	<td width="120"><form:input maxlength="7"
																			type="text" id="aFTE" name="aFTE" path="aFTE"
																			value="" class="reviewFTEInputArea"
																			onkeypress="return isNumericWithDecimal(event,this);"></form:input></td>
																	<td width="39">From</td>
																	<td width="130"><form:input maxlength="10"
																			readonly="true" title="DD/MM/YYYY" path="fDate"
																			class="reviewFTEInputArea" id="fdate"></form:input></td>
																	<td width="23">To</td>
																	<td width="175"><form:input maxlength="10"
																			title="DD/MM/YYYY" path="tDate" readonly="true"
																			class="reviewFTEInputArea" id="tDate"></form:input></td>
																</tr>
																<tr>
																	<form:hidden path="additionalFteid"
																		cssStyle="border: none;" />
																</tr>
																<tr></tr>
															</tbody></table></td>
													</tr>
												</tbody></table>
											</td>
										</tr>
									</tbody>
									<!-- -------------------------------------------------------------------------------------------------- --> 
									</table>
									</c:if>
									<!-- --here -->
								</div>
							</div>
						</td>
					</tr>
				</table>
			</td>	
		</tr>
</table>


<div style="float: left; width: 1112px; height: 35px;">
		<a class="portfolioButtons" onclick="location.href='./labourCost'" id="nextLabourCost">Next</a>
		<a class="portfolioButtons" id="save">Save</a>
		<a class="portfolioButtons" onclick="location.href='./productivityModelling';">Previous</a>
		
		<!-- <div class="TabsCommonButtons" onclick="javascript:clearForm();">Reset</div>
                	<div class="TabsCommonButtons" id="saveForm1">Save</div> -->
		<%-- <c:choose>
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
			onclick="location.href='./productivityLevers';">Previous</div> --%>
</div>
</form:form>

