<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<script type="text/javascript">
	$(document).ready(function() {

		$("#onShoreLabour").change(function() {
			$("#onShore").toggle();
		});
		$("#offShoreLabour").change(function() {
			$("#offShore").toggle();
		});

		$('#previouspage').click(function() {
			$('#form1').attr('action', './reviewUpdatedFTE');
			$('#form1').submit();
		});
		$('#nextpage').click(function() {
			$('#form1').attr('action', './loadNLC');
			
			$('#form1').submit();
		});

		$('#selectedServiceScope').change(function() {
			document.getElementById("form1").submit();
		});
		
		$('#currencyCode').change(function(){
			var curCode = document.getElementById("currencyCode").value;
			
			if(curCode.length == 0 || curCode.length == '' ){
				return;
			}
			
			$.ajax({
                url : "/ADM_PRE/solution/changeRate",
                type : "post",
                async:false,
                data : "currencyCode="+curCode,
                success : function(resp1) {
                	if(!resp1){
                		alert("No exchange rate found");
                		return false;
                	}
                	var temp = resp1.split("-");
                	var resp = temp[0];
                	var factor = temp[1];
                	
                	$('input[id="hValue"]').each(function(index, value){
                			
                			var val = $(this).val();
                			
                			val=val.replace(/,/g, "");
                			
                			var total = 0;
                			if(!isNaN(parseFloat(val)) && !isNaN(parseFloat(resp)) ){
                				if(factor == "D"){
                				
                				 total = parseFloat(parseFloat(val) / parseFloat(resp)).toFixed(2);
                				}
                				else if(factor == "M"){
                				
                					total = parseFloat(parseFloat(val) * parseFloat(resp)).toFixed(2);
                				}
                				$('span[name="disp_value"]').each(function(index1, value1){
                						if(index == index1){
                							$(this).text(total);
                						}
                					});
                    				
                			}
                		
                		});
                	
                	var t = document.getElementById("totalValue").value;
                	var tl =0;
                	t=t.replace(/,/g, "");
                	if(factor == "D"){
                		
                		t1= parseFloat(parseFloat(t) / parseFloat(resp)).toFixed(2);
       				}
       				else if(factor == "M"){
       				
       					t1= parseFloat(parseFloat(t) * parseFloat(resp)).toFixed(2);
       				}
                	
                	document.getElementById("cur_value").innerHTML = curCode;
                	document.getElementById("total_value").innerHTML =t1+"";
                	
                	
                },
                error : function(resp){
                	alert("No exchange factor found");
                }
            });
		});
	});
	function toggleDisplay(value) {
		if ($("#oppDetails").css('display') == "none") {
			$("#oppDetails").css('display', "");
		} else {
			$("#oppDetails").css('display', "none");
		}
	}
</script>
<script type="text/javascript" src="../js/wz_tooltip.js"></script>


		<c:if test="${not empty partitionNamesMessage}">
		<div id="messageBox1" class="errorMessageDisp" style="display: block;">
			${partitionNamesMessage}
			</div>
		</c:if>
	
<c:if test="${empty interval }">
		<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
	-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;">
		<ul>
			<li>
				Please check the Steady State Start and End dates for this Opportunity under Project Delivery
			</li>
		</ul>
	</div>
	</c:if>
	<c:if test="${empty data }">
		<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
	-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;">
		<ul>
			<li>
				No rate data found for the country ${currency}
			</li>
			<li>
				Enter the Rate related data under Admin module in section Rate Management using Admin Rights
			</li>
		</ul>
	</div>
	</c:if>

<form:form id="form1" action="/ADM_PRE/solution/getjobRole">
	<table width="1112" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><div class="tdHeaderLabel FteHeaderText"
					style="mafloat: left; margin-bottom: 5px;">Labour Cost</div></td>
		</tr>
		<tr>
			<td align="center"><jsp:include page="opportunitySummary.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td align="center" height="5"></td>
		</tr>
		<tr>
			<td align="center"><jsp:include page="./serviceBucket.jsp" /></td>
		</tr>
		<tr>
			<td height="5"></td>
		</tr>
		<tr>
			<td><table width="1112" border="0" cellspacing="0"
					cellpadding="0">

					<tr>
						<td><div class="FteBodyDiv">
								<div class="FTEscrollProperty">
									
									
	<table cellspacing="0" cellpadding="0" class="tdLabelLeft"	border="0">
		<tr>
			<td width="130" height="40">Select Service Scope</td>										
			<td width="200">
				<select name="selectedServiceScope" id="selectedServiceScope" class="reviewFTEInputArea" style="width: 150px;">
					<option value="">--Select--</option>
					
					<c:forEach items="${serviceScopes}" var="serviceScope">
						<c:if test="${selectedScope==serviceScope.key}">
						<c:set var="selVal" value="${serviceScope.value}"></c:set>
						<option value="${serviceScope.key}" selected >${serviceScope.value}</option>
						</c:if>
						<c:if test="${selectedScope!=serviceScope.key}">
						<option value="${serviceScope.key}" >${serviceScope.value}</option>
						</c:if>
						
					</c:forEach>
				</select>
			</td>
			<c:if test="${empty secondTime}">
				<td>
				<b>Please select any of the Service Scope from the dropdown list</b>
				</td>
			</c:if>
			<c:if test="${not empty secondTime }">
				<!-- <td width="140" class="tdInputBox">
					<input type="checkbox" name="input" value="Voice" id="onShoreLabour" checked="checked" />
					<b>Onshore	Labour</b>
				</td> -->
				<!-- <td width="140" class="tdInputBox">
					<input type="checkbox" name="input" value="Web" id="offShoreLabour"	checked="checked" />
					<b style="vertical-align: baseline;">Offshore Labour</b>
				</td> -->
				<td>
					<c:if test="${partitionname == true}">
						<a href="/adm_pre/solution/ttlaborcost" style="text-decoration: none;">
							<i><font style="color: blue;" size="2px"><b>click for labor cost: tnt</b></i>
						</a>
					</c:if>
				</td>
				<c:if test="${totalLCforSlectedOppScopeID != 0.0}">
				<td width="100" height="40">Select Currency</td>
				<td>
					<select name="currencyCode" id="currencyCode" class="reviewFTEInputArea" style="width: 60px;">
						<option value="">Select</option>
						<c:forEach items="${currencyCodeList}" var="currencyCodeInstance">
							<option value="${currencyCodeInstance.currencyCode}">${currencyCodeInstance.currencyCode}</option>
						</c:forEach>						
					</select>
				</td>
				</c:if>
				<td style="width: 50px;"></td>
				
				<td style="width: 180px;">
					Opportunity is created in
				</td>
				<th class="reviewFTEInputArea" style="width: 100px;text-align: center;margin-top: 10px;">
					${currency}
				</th>
			</c:if>
											
		</tr>
	</table>
	
			<c:if test="${not empty secondTime}">

			
			
			<!--    local table     -->
			<c:if test="${not empty localValues}">
			<table border="0" cellpadding="0" cellspacing="2"class="payroTable" id="onShore" style="width: 100%;">
				<tr>
					<td bgcolor="#e3e3e3">
						<strong>Onshore (Local) </strong>
					</td>
					<c:forEach items="${opportunityInterval}" varStatus="loop">
						<td colspan="2" align="center" bgcolor="#e3e3e3"><strong>
								${opportunityInterval[loop.index]} </strong>
						</td>
					</c:forEach>
				</tr>
				
				<c:forEach items="${localValues}" var="localValue">
						<tr>
							<td bgcolor="#fafafa">
								${localValue.key}
							</td>
							 <c:forEach items="${localValue.value}" var="val">
								<td colspan="2" bgcolor="#fafafa">
								<fmt:formatNumber var="v_local" value="${val}" maxFractionDigits="2" />
								
								<%-- <span id="disp_local_value" name="disp_local_value">${v_local}</span>
								<input type="hidden" id="hidden_local_Value" value="${v_local}"> --%>
								<span id="disp_value" name="disp_value">${v_local}</span>
								<input type="hidden" id="hValue" value="${v_local}">
									
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				
			</table>
			<br>
			</c:if>



			<!--    gsc table     -->
			<c:if test="${not empty gsciValues}">
			<table border="0" cellpadding="0" cellspacing="2"class="payroTable" id="onShore" style="width: 100%;">
				<tr>
					<td bgcolor="#e3e3e3">
						<strong>Onshore (GSC Landed) </strong>
					</td>
					<c:forEach items="${opportunityInterval}" varStatus="loop">
						<td colspan="2" align="center" bgcolor="#e3e3e3"><strong>
								${opportunityInterval[loop.index]} </strong>
						</td>
					</c:forEach>
				</tr>
				
				
				<c:forEach items="${gsciValues}" var="gsciValue">
					<tr>
						<td bgcolor="#fafafa">
							${gsciValue.key}
						</td>
						 <c:forEach items="${gsciValue.value}" var="val">
							<td colspan="2" bgcolor="#fafafa">
							<fmt:formatNumber var="v_gsci" value="${val}" maxFractionDigits="2" />
								
								<span id="disp_value" name="disp_value">${v_gsci}</span>
								<input type="hidden" id="hValue" value="${v_gsci}">
							
								
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
				
			</table>
			<br>
			</c:if>
			
			<!--    3PP table     -->
			<c:if test="${not empty pppValues}">
			<table border="0" cellpadding="0" cellspacing="2"class="payroTable" id="onShore" style="width: 100%;">
				<tr>
					<td bgcolor="#e3e3e3"><strong>Onshore (3PP) </strong></td>
					<c:forEach items="${opportunityInterval}" varStatus="loop">
						<td colspan="2" align="center" bgcolor="#e3e3e3"><strong>
								${opportunityInterval[loop.index]} </strong>
						</td>
					</c:forEach>
				</tr>
				
				
				<c:forEach items="${pppValues}" var="pppValue">
					<tr>
						<td bgcolor="#fafafa">
							${pppValue.key}
						</td>
						 <c:forEach items="${pppValue.value}" var="val">
							<td colspan="2" bgcolor="#fafafa">
							<fmt:formatNumber var="v_ppp" value="${val}" maxFractionDigits="2" />
								
								<span id="disp_value" name="disp_value">${v_ppp}</span>
								<input type="hidden" id="hValue" value="${v_ppp}">
							
								
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
				
			</table>
			<br>
			</c:if>
			
			
			<c:if test="${not empty offShoreValues}">
			<table border="0" cellpadding="0" cellspacing="2"
					class="payroTable" id="onShore" style="width: 100%;">
					<tr>
						<td bgcolor="#e3e3e3">
							<strong >Offshore (GSC)</strong>
						</td>
						<c:forEach items="${opportunityInterval}" varStatus="loop">
							<td colspan="2" align="center" bgcolor="#e3e3e3"><strong>
									${opportunityInterval[loop.index]} </strong>
							</td>
						</c:forEach>
					</tr>
					
					
					<c:forEach items="${offShoreValues}" var="offshoreValue" varStatus="ofsv">
						<tr>
							<td bgcolor="#fafafa">
								${offshoreValue.key}
							</td>
							 <c:forEach items="${offshoreValue.value}"  var="ofsVal">
								<td colspan="2" bgcolor="#fafafa">
								<fmt:formatNumber var="oval" value="${ofsVal}" maxFractionDigits="2" />
								
								<span id="disp_value" name="disp_value">${oval}</span>
								<input type="hidden" id="hValue" value="${oval}">
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
					
			</table>
			<br>
			
			</c:if>
			
			
			<!--    nearShore table     -->
			<c:if test="${not empty nearShoreValues}">
			<table border="0" cellpadding="0" cellspacing="2"class="payroTable" id="onShore" style="width: 100%;">
				<tr>
					<td bgcolor="#e3e3e3"><strong>Near Shore (GSC)</strong></td>
					<c:forEach items="${opportunityInterval}" varStatus="loop">
						<td colspan="2" align="center" bgcolor="#e3e3e3"><strong>
								${opportunityInterval[loop.index]} </strong>
						</td>
					</c:forEach>
				</tr>
				
				
				<c:forEach items="${nearShoreValues}" var="nearShoreValue">
					<tr>
						<td bgcolor="#fafafa">
							${nearShoreValue.key}
						</td>
						 <c:forEach items="${nearShoreValue.value}" var="val">
							<td colspan="2" bgcolor="#fafafa">
								<fmt:formatNumber var="nsv" value="${val}" maxFractionDigits="2" />
								
								<span id="disp_value" name="disp_value">${nsv}</span>
								<input type="hidden" id="hValue" value="${nsv}">
								
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
			<br>
			</c:if>
			</c:if>		
									
								</div>
					
					<c:if test="${not empty secondTime}">			
			<div class="tdHeaderLabel FteHeaderText" style="mafloat: left;margin-bottom: 5px;text-align: right;">
				
				<c:choose>
				<c:when test="${totalLCforSlectedOppScopeID != 0.0}">
					Total Labor Cost for ${selVal} : 
					<fmt:formatNumber var="total" value="${totalLCforSlectedOppScopeID}" maxFractionDigits="2" />
					
						<span id="total_value" name="total_value">${total}</span>
						<input type="hidden" id="totalValue" value="${total}">
					
					
					
					<span id="cur_value" name="cur_value">${currencyCode}</span>
					
				</c:when>
				<c:otherwise>
					No Data Available For Service Scope : ${selVal}
				</c:otherwise>
				</c:choose>
			
			</div>
			</c:if>
								
								<div id="nextpage" class="TabsCommonButtons">Next</div>
								<div id="previouspage" class="TabsCommonButtons">Previous</div>
								
								<!-- <a id="Reset" class="portfolioButtons" >Next</a> -->
							</div></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>