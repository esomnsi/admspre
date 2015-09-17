<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>



function calTotalTransPerYear(index){
	
	var transObj = document.getElementById("ServiceDeskDTOList["+index+"].transactionsPerMonth");
//	var handlingTimeObj = document.getElementById("ServiceDeskDTOList["+index+"].averageHandlingTime");
	var transPerYearObj = document.getElementById("ServiceDeskDTOList["+index+"].totalTransPerYear");
	
	var transVal = transObj.value;
	
	var transPerYear = transVal*20*12;
	
	transPerYearObj.value=parseFloat(transPerYear).toFixed(2);;
	
	calDuriOfCall(index);
	 //calculating FTE onblure event
	calBaseFTE(index);
	
	//calculate sum 

	calTotalObjectWise('totalTransPerYear',true);
	
	calTotalObjectWise('durationOfCallsPerYear',true);
	
	calTotalObjectWise('transactionsPerMonth',true); 
	
	calTotalObjectWise('baseServiceDeskFte',true);
	
	calTotalObjectWise('augmentedHeads',true); 
}


function calDuriOfCall(index){
	var handlingTimeObj = document.getElementById("ServiceDeskDTOList["+index+"].averageHandlingTime");
	var transPerYearObj = document.getElementById("ServiceDeskDTOList["+index+"].totalTransPerYear");
	var durationOfCallsPerYear = document.getElementById("ServiceDeskDTOList["+index+"].durationOfCallsPerYear");
		
	if(isNaN(parseInt(transPerYearObj.value)) || isNaN(parseInt(handlingTimeObj.value))){
		return false;
	}
	var total = parseFloat(transPerYearObj.value) *(parseFloat(handlingTimeObj.value)/60);
	durationOfCallsPerYear.value=parseFloat(total).toFixed(2);
	//calculating FTE onblure event
	calBaseFTE(index);
	
	 calTotalObjectWise('durationOfCallsPerYear',true);
	
	calTotalObjectWise('transactionsPerMonth',true);
	 
	calTotalObjectWise('baseServiceDeskFte',true);
	
	calTotalObjectWise('augmentedHeads',true); 
}


function calTotalObjectWise(str, isFloat){
	
	var total=0;
	for(var i=0;i<=2;i++){
		var obj = document.getElementById("ServiceDeskDTOList["+i+"]."+str);
		if(!isNaN(parseFloat(obj.value))){
		total= total+ parseFloat(obj.value);
		}
	}
	if(isFloat){
		document.getElementById("ServiceDeskDTOList[3]."+str).value=parseFloat(total).toFixed(2);
	}else{
		document.getElementById("ServiceDeskDTOList[3]."+str).value=parseInt(total);
	}
}



function calBaseFTE(index){
	
	var durationOfCallsPerYear = document.getElementById("ServiceDeskDTOList["+index+"].durationOfCallsPerYear");
//	var utilizationPerYear = document.getElementById("ServiceDeskDTOList["+index+"].utilizationPerYear");
	var baseServiceDeskFte = document.getElementById("ServiceDeskDTOList["+index+"].baseServiceDeskFte");
	var augmentedHeads = document.getElementById("ServiceDeskDTOList["+index+"].augmentedHeads");
	//alert(durationOfCallsPerYear);
	if(isNaN(parseInt(durationOfCallsPerYear.value))){
		return false;
	}
	
	var baseFTE = parseFloat(durationOfCallsPerYear.value).toFixed(2);
	baseServiceDeskFte.value=parseFloat(baseFTE).toFixed(2);
	
	if(isNaN(baseFTE)){
		return false;
	}
	
	augmentedHeads.value=parseFloat(baseFTE*derivedFactor).toFixed(2);
}

function handleRows(obj,index){
	
	var transObj = document.getElementById("ServiceDeskDTOList["+index+"].transactionsPerMonth");
	var handlingTimeObj = document.getElementById("ServiceDeskDTOList["+index+"].averageHandlingTime");
	var transPerYearObj = document.getElementById("ServiceDeskDTOList["+index+"].totalTransPerYear");
	var durationOfCallsPerYear = document.getElementById("ServiceDeskDTOList["+index+"].durationOfCallsPerYear");
	//var utilizationPerYear = document.getElementById("ServiceDeskDTOList["+index+"].utilizationPerYear");
	var baseServiceDeskFte = document.getElementById("ServiceDeskDTOList["+index+"].baseServiceDeskFte");
	var augmentedHeads = document.getElementById("ServiceDeskDTOList["+index+"].augmentedHeads");
	
	
	if(obj.checked){
		transObj.readonly=false;
		handlingTimeObj.readonly=false;
		
	}else{
		transObj.readonly=true;
		handlingTimeObj.readonly=true;
		
		
		transObj.value='';
		handlingTimeObj.value='';
		transPerYearObj.value='';
		durationOfCallsPerYear.value='';
		//utilizationPerYear.value='';
		baseServiceDeskFte.value='';
		augmentedHeads.value='';
	}
	
	calTotalTransPerYear(index);
	
}



function handleWindowChangeSD(){
	
	var index = $("#supportWindowMatrixId").prop("selectedIndex");
	var derivedFacStr = $("#derivedFactor").val();
	derivedFacStr = derivedFacStr.substr(1,derivedFacStr.length);
	var arr = derivedFacStr.split(';');
	derivedFactor = arr[index];
  	
	document.getElementById('windowDiv').innerHTML = $("#supportWindowMatrixId option:selected").text();
}

function calculateSD(){
	 var channels = $('[name="channels"]:checked');
	  for(var i=0;i<channels.length;i++){
		  var val = channels[i].value;
		  calTotalTransPerYear(val); 
		  calDuriOfCall(val);
		  
	  }
	
}

/* function setHiddenValuesForUtilization(){
	document.getElementById("ServiceDeskDTOList[0].utilizationPerYear").value=utilizationPerYear; 
	document.getElementById("ServiceDeskDTOList[1].utilizationPerYear").value=utilizationPerYear; 
	document.getElementById("ServiceDeskDTOList[2].utilizationPerYear").value=utilizationPerYear; 
	document.getElementById("ServiceDeskDTOList[3].utilizationPerYear").value=utilizationPerYear; 
	if(document.getElementById("dimensionAttributeId") != null){
	document.getElementById("solutionDimentionAttId").value = document.getElementById("dimensionAttributeId").value;
	}

}
 */

	$(document).ready (function () {
	
		/*var utilizationPerYear = $('#utilizatioPerYear').val();
		document.getElementById("ServiceDeskDTOList[0].utilizationPerYear").value=utilizationPerYear; 
		document.getElementById("ServiceDeskDTOList[1].utilizationPerYear").value=utilizationPerYear; 
		document.getElementById("ServiceDeskDTOList[2].utilizationPerYear").value=utilizationPerYear; 
		*/
		handleWindowChangeSD();
		
		$('#saveSD').click(function() {
			
			calculateSD();
			
			var fte = document.getElementById("ServiceDeskDTOList[3].augmentedHeads").value;
			
			if(fte == '0.00' ||fte == '' ){
				alert("Please calculate FTE before saving the details");
			}else{
				
				//setHiddenValuesForUtilization();
				
				$("#ServiceDeskForm").submit();
				
			}
		});
		
		$("#ServiceDeskForm").submit(function() {
			showProgress();
			var options = {
					success: function(html) {
						 $("#sdDiv").html(html);
						 $("#messageDiv").addClass("message");
						 $("#messageDiv").html("Your data has been saved successfully.");
						 refreshServiceBucket();
					},
					url: "./saveServiceDesk"
					
				};
			
				$(this).ajaxSubmit(options);
			
				hideProgress();
				
				return false;
		});
		
		 $('#resetSD').click(function() {
			  	$('#ServiceDeskForm').each (function(){
	    		  this.reset();
	    		});
		    });
		  
		$('#supportWindowMatrixId').change(function(){
			handleWindowChangeSD();
			  
		});
		
		  $('#calculateSD').click(function(){
			  calculateSD();
		  });
		  
		  
			
		  $('#ServiceDeskForm input[name="defaultSD"]').change(function(e){	
			  if (!$(this).is(':checked')) {
				  $('#ServiceDeskForm').each (function(){
		    		  this.reset();
		    	});
			  }else{
				 $.ajax({
						type : "POST",
						url : "./loadDefaultValuesForServiceScopes",
						data : {"serviceId": '1'},
						datatype : "text",
						success : function(result){
							var resultArr = result.split('$');
							var transPerMon = resultArr[0].split(';');
							var handlingTime = resultArr[1].split(';');      
							for(var i=0;i<3;i++){
								document.getElementById("ServiceDeskDTOList["+i+"].transactionsPerMonth").value=transPerMon[i];
								document.getElementById("ServiceDeskDTOList["+i+"].averageHandlingTime").value=handlingTime[i];
								//document.getElementById("ServiceDeskDTOList["+i+"].utilizationPerYear").value=handlingTime[3];
							}
							//calculateSD();
						},
						error : function(e) {
							alert("Operation failed.");
						}
					});
			  }
			 
		 });		 	
	});
	
	var derivedFactor;
</script>


<form:form id="ServiceDeskForm" action="saveServiceDesk" method="post" modelAttribute="serviceDeskForm">

<form:hidden path="solutionDimentionAttId"/> 
<form:hidden path="opportunityScopeId"/> 

<table width="100%">
				<tr>
				<td class="tdLabelLeft">Window Of Operation</td>
				<td class="tdLabelLeft">
					<form:select path="supportWindowMatrixId" class="listBoxSmall" >
					<c:forEach var="item" items="${supportWindowList}">
					<c:set var="derivedFactor" value="${derivedFactor};${item.derivedFactor}" />
						<form:option  value="${item.supportWindowMatrixId}">${item.supportWindow}</form:option>
						
					</c:forEach>
				</form:select>
				<input type="hidden" id="derivedFactor"  value="${derivedFactor}" />
				
				</td>
				 <td class="tdInputBox">
			      <input type="checkbox" name="defaultSD"  >
			      <b>Default</b></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
				<td class="tdLabelLeft"  width="30%">Touch Point Channels</td>
			
				<td class="tdInputBox">&nbsp;&nbsp;&nbsp; <input type="checkbox" name="channels" value="0" onclick="handleRows(this,0);" checked > <b>Voice</b></td>
				<td class="tdInputBox"> &nbsp;&nbsp;&nbsp;<input type="checkbox" name="channels" value="1" onclick="handleRows(this,1);" checked><b>Web</b></td>
				<td class="tdInputBox">&nbsp;&nbsp;&nbsp; <input type="checkbox" name="channels" value="2" onclick="handleRows(this,2);" checked><b>Email</b></td>
				<td class="tdInputBox" width="30%">&nbsp;&nbsp;&nbsp;<b>Total</b></td>
				<input type="hidden" name="ServiceDeskDTOList[0].touchPointChannelDTO.touchPointChannelId" id="ServiceDeskDTOList[0].touchPointChannelDTO.touchPointChannelId" value="1" >
				<input type="hidden" name="ServiceDeskDTOList[1].touchPointChannelDTO.touchPointChannelId" id="ServiceDeskDTOList[1].touchPointChannelDTO.touchPointChannelId" value="2" >
				<input type="hidden" name="ServiceDeskDTOList[2].touchPointChannelDTO.touchPointChannelId" id="ServiceDeskDTOList[2].touchPointChannelDTO.touchPointChannelId" value="3" >
				<input type="hidden" name="ServiceDeskDTOList[3].touchPointChannelDTO.touchPointChannelId" id="ServiceDeskDTOList[3].touchPointChannelDTO.touchPointChannelId" value="4" >
				
				<input type="hidden" name="ServiceDeskDTOList[0].serviceDeskId" id="ServiceDeskDTOList[0].serviceDeskId" value="${serviceDeskForm.serviceDeskDTOList[0].serviceDeskId}" >
				<input type="hidden" name="ServiceDeskDTOList[1].serviceDeskId" id="ServiceDeskDTOList[1].serviceDeskId"  value="${serviceDeskForm.serviceDeskDTOList[1].serviceDeskId}" >
				<input type="hidden" name="ServiceDeskDTOList[2].serviceDeskId" id="ServiceDeskDTOList[2].serviceDeskId" value="${serviceDeskForm.serviceDeskDTOList[2].serviceDeskId}" >
				<input type="hidden" name="ServiceDeskDTOList[3].serviceDeskId" id="ServiceDeskDTOList[3].serviceDeskId"  value="${serviceDeskForm.serviceDeskDTOList[3].serviceDeskId}" >
				
			<%-- 	<input type="hidden" name="ServiceDeskDTOList[0].utilizationPerYear" id="ServiceDeskDTOList[0].utilizationPerYear" value="${serviceDeskForm.serviceDeskDTOList[0].utilizationPerYear}" class="textBoxSmallNumric" maxlength="4">
				<input type="hidden" name="ServiceDeskDTOList[1].utilizationPerYear" id="ServiceDeskDTOList[1].utilizationPerYear" value="${serviceDeskForm.serviceDeskDTOList[0].utilizationPerYear}" class="textBoxSmallNumric" maxlength="4">
				
				<input type="hidden" name="ServiceDeskDTOList[2].utilizationPerYear" id="ServiceDeskDTOList[2].utilizationPerYear" value="${serviceDeskForm.serviceDeskDTOList[0].utilizationPerYear}" class="textBoxSmallNumric" maxlength="4">
				
				<input type="hidden" name="ServiceDeskDTOList[3].utilizationPerYear" id="ServiceDeskDTOList[3].utilizationPerYear" value="${serviceDeskForm.serviceDeskDTOList[0].utilizationPerYear}" class="textBoxSmallNumric" maxlength="4">
			 --%>	
				</tr>
			<!-- 	<tr>
					<td>&nbsp;</td>
				</tr>
				
			 -->
			
			<%--  <fmt:formatNumber var="transactionsPerMonth0" value="${serviceDeskForm.serviceDeskDTOList[0].transactionsPerMonth}" maxFractionDigits="1" />
			<fmt:formatNumber var="transactionsPerMonth1" value="${serviceDeskForm.serviceDeskDTOList[1].transactionsPerMonth}" maxFractionDigits="1" />
			<fmt:formatNumber var="transactionsPerMonth2" value="${serviceDeskForm.serviceDeskDTOList[2].transactionsPerMonth}" maxFractionDigits="1" />
			<fmt:formatNumber var="transactionsPerMonth3" value="${serviceDeskForm.serviceDeskDTOList[3].transactionsPerMonth}" maxFractionDigits="1" />
			
			 <fmt:formatNumber var="averageHandlingTime0" value="${serviceDeskForm.serviceDeskDTOList[0].averageHandlingTime}" maxFractionDigits="1" />
			<fmt:formatNumber var="averageHandlingTime1" value="${serviceDeskForm.serviceDeskDTOList[1].averageHandlingTime}" maxFractionDigits="1" />
			<fmt:formatNumber var="averageHandlingTime2" value="${serviceDeskForm.serviceDeskDTOList[2].averageHandlingTime}" maxFractionDigits="1" /> --%>

		<tr>
			<td class="tdLabelLeft">Transactions/Day</td>
			<td class="tdLabelLeft"><input type="text" name="ServiceDeskDTOList[0].transactionsPerMonth" id="ServiceDeskDTOList[0].transactionsPerMonth" value="${serviceDeskForm.serviceDeskDTOList[0].transactionsPerMonth}" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[1].transactionsPerMonth" id="ServiceDeskDTOList[1].transactionsPerMonth" value="${serviceDeskForm.serviceDeskDTOList[1].transactionsPerMonth}" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[2].transactionsPerMonth" id="ServiceDeskDTOList[2].transactionsPerMonth" value="${serviceDeskForm.serviceDeskDTOList[2].transactionsPerMonth}" class="textBoxSmallNumric" maxlength="5" onkeypress="return isDecimal(event);"></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[3].transactionsPerMonth" id="ServiceDeskDTOList[3].transactionsPerMonth" value="${serviceDeskForm.serviceDeskDTOList[3].transactionsPerMonth}" class="textBoxCalculateResults" tabindex="-1" readonly></td>
		</tr>
			<tr>
			<td class="tdLabelLeft">Average Handling Time (Mins)</td>
			<td class="tdLabelLeft"><input type="text" name="ServiceDeskDTOList[0].averageHandlingTime" id="ServiceDeskDTOList[0].averageHandlingTime" value="${serviceDeskForm.serviceDeskDTOList[0].averageHandlingTime}" class="textBoxSmallNumric" maxlength="4" onkeypress="return isDecimal(event);"></td>
			<td class="tdInputBox"><input type="text"  name="ServiceDeskDTOList[1].averageHandlingTime" id="ServiceDeskDTOList[1].averageHandlingTime" value="${serviceDeskForm.serviceDeskDTOList[1].averageHandlingTime}" class="textBoxSmallNumric" maxlength="4" onkeypress="return isDecimal(event);"></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[2].averageHandlingTime" id="ServiceDeskDTOList[2].averageHandlingTime" value="${serviceDeskForm.serviceDeskDTOList[2].averageHandlingTime}" class="textBoxSmallNumric" maxlength="4" onkeypress="return isDecimal(event);"></td>
	<!-- 			<td class="tdInputBox"><input type="text" name="averageHandlingTime" id="averageHandlingTime" value="" class="textBoxSmallNumric" readonly></td>
	 -->	</tr>
			<tr>
			<td class="tdLabelLeft">Total Transactions Per Year</td>
			<td class="tdLabelLeft"><input type="text" name="ServiceDeskDTOList[0].totalTransPerYear" id="ServiceDeskDTOList[0].totalTransPerYear" value="${serviceDeskForm.serviceDeskDTOList[0].totalTransPerYear}" class="textBoxCalculateResults" tabindex="-1" readonly ></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[1].totalTransPerYear" id="ServiceDeskDTOList[1].totalTransPerYear" value="${serviceDeskForm.serviceDeskDTOList[1].totalTransPerYear}" class="textBoxCalculateResults" tabindex="-1" readonly></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[2].totalTransPerYear" id="ServiceDeskDTOList[2].totalTransPerYear" value="${serviceDeskForm.serviceDeskDTOList[2].totalTransPerYear}" class="textBoxCalculateResults" tabindex="-1" readonly></td>
				<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[3].totalTransPerYear" id="ServiceDeskDTOList[3].totalTransPerYear" value="${serviceDeskForm.serviceDeskDTOList[3].totalTransPerYear}" class="textBoxCalculateResults" tabindex="-1" readonly></td>
		</tr>
			<tr>
			<td class="tdLabelLeft">Duration of Calls (Hrs/Yr.)</td>
			<td class="tdLabelLeft"><input type="text" name="ServiceDeskDTOList[0].durationOfCallsPerYear" id="ServiceDeskDTOList[0].durationOfCallsPerYear" value="${serviceDeskForm.serviceDeskDTOList[0].durationOfCallsPerYear}" class="textBoxCalculateResults" tabindex="-1" readonly></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[1].durationOfCallsPerYear" id="ServiceDeskDTOList[1].durationOfCallsPerYear" value="${serviceDeskForm.serviceDeskDTOList[1].durationOfCallsPerYear}" class="textBoxCalculateResults" tabindex="-1" readonly></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[2].durationOfCallsPerYear" id="ServiceDeskDTOList[2].durationOfCallsPerYear" value="${serviceDeskForm.serviceDeskDTOList[2].durationOfCallsPerYear}" class="textBoxCalculateResults" tabindex="-1" readonly></td>
				<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[3].durationOfCallsPerYear" id="ServiceDeskDTOList[3].durationOfCallsPerYear" value="${serviceDeskForm.serviceDeskDTOList[3].durationOfCallsPerYear}" class="textBoxCalculateResults" tabindex="-1" readonly></td>
		</tr>
			
			<%--  <tr>
			<td class="tdLabelLeft">Utilization Per Year</td>
			<td class="tdLabelLeft"><input type="text" name="ServiceDeskDTOList[0].utilizationPerYear" id="ServiceDeskDTOList[0].utilizationPerYear" value="${serviceDeskForm.serviceDeskDTOList[0].utilizationPerYear}" class="textBoxSmallNumric" maxlength="4"></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[1].utilizationPerYear" id="ServiceDeskDTOList[1].utilizationPerYear" value="${serviceDeskForm.serviceDeskDTOList[1].utilizationPerYear}" class="textBoxSmallNumric" maxlength="4"></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[2].utilizationPerYear" id="ServiceDeskDTOList[2].utilizationPerYear" value="${serviceDeskForm.serviceDeskDTOList[2].utilizationPerYear}" class="textBoxSmallNumric" maxlength="4"></td>
		</tr>
	  --%>
			<tr>
			<td class="tdLabelLeft">Base Service Desk FTE</td>
			<td class="tdLabelLeft"><input type="text"  name="ServiceDeskDTOList[0].baseServiceDeskFte" id="ServiceDeskDTOList[0].baseServiceDeskFte"  value="${serviceDeskForm.serviceDeskDTOList[0].baseServiceDeskFte}" class="textBoxCalculateResults" tabindex="-1" readonly ></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[1].baseServiceDeskFte" id="ServiceDeskDTOList[1].baseServiceDeskFte" value="${serviceDeskForm.serviceDeskDTOList[1].baseServiceDeskFte}" class="textBoxCalculateResults" tabindex="-1" readonly ></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[2].baseServiceDeskFte" id="ServiceDeskDTOList[2].baseServiceDeskFte" value="${serviceDeskForm.serviceDeskDTOList[2].baseServiceDeskFte}" class="textBoxCalculateResults" tabindex="-1" readonly ></td>
				<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[3].baseServiceDeskFte" id="ServiceDeskDTOList[3].baseServiceDeskFte" value="${serviceDeskForm.serviceDeskDTOList[3].baseServiceDeskFte}" class="textBoxCalculateResults" tabindex="-1" readonly ></td>
		</tr>
			<tr>
			<td class="tdLabelLeft">Augmented Heads for <b> <span id="windowDiv" > </span></b> support </td>
			<td class="tdLabelLeft"><input type="text" name="ServiceDeskDTOList[0].augmentedHeads" id="ServiceDeskDTOList[0].augmentedHeads" value="${serviceDeskForm.serviceDeskDTOList[0].augmentedHeads}" class="textBoxCalculateResults" tabindex="-1" readonly ></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[1].augmentedHeads" id="ServiceDeskDTOList[1].augmentedHeads" value="${serviceDeskForm.serviceDeskDTOList[1].augmentedHeads}" class="textBoxCalculateResults" tabindex="-1" readonly ></td>
			<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[2].augmentedHeads" id="ServiceDeskDTOList[2].augmentedHeads" value="${serviceDeskForm.serviceDeskDTOList[2].augmentedHeads}" class="textBoxCalculateResults" tabindex="-1" readonly ></td>
				<td class="tdInputBox"><input type="text" name="ServiceDeskDTOList[3].augmentedHeads" id="ServiceDeskDTOList[3].augmentedHeads" value="${serviceDeskForm.serviceDeskDTOList[3].augmentedHeads}" class="textBoxCalculateResults" tabindex="-1" readonly ></td>
		</tr>
		</table>
		 <c:if test="${hasEditSolAccess!='false'}">
	<div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 5x;">
	  <table width="100%" border="0" style="padding-right:5%" >
	    <tr>
	      <td class="tdButtonRow" align="right">
	      	<a id="resetSD" class="TabsCommonButtons" >Reset</a>
	      	<a id="saveSD" class="TabsCommonButtons" >Save</a>
	      	<a id="calculateSD" class="TabsCommonButtons">Calculate</a>
	      </td>
	    </tr>
	  </table>
	 </div>
	 </c:if>
</form:form>
