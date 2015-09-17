<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<script src="../js/APAnalysis.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/accordion.js"></script>
<script src="../js/jquery.blockUI.js" type="text/javascript"></script>
<script type="text/javascript">

$(document).ready(function () {
       
    $('#save').click(function() {
    	$("#messageDiv").removeClass();
    	$("#messageDiv").html("");
    	if(validate()){
//    	$('#solutionAPAPage').attr("action",'../solution/saveAPAnalysis');
    	if(validateAnnexe()){
    	$('#solutionAPAPage').submit();
    	}
    	}
    });
   
    $("#solutionAPAPage").submit(function() {
    	showProgress();
		var options = {				
				success: function(html) {					
					$("#messageDiv").addClass('actionPerformedMessage');
					$("#messageDiv").html("Your data has been saved successfully.");
				},
				url: "<%=request.getContextPath()%>/solution/saveAPAnalysis"
			};		
			$(this).ajaxSubmit(options);
			hideProgress();
			return false;
	});
    
    $('#next').click(function() {
    	$("#messageDiv").removeClass();
    	$("#messageDiv").html("");
//    	if(validate()){
//    	$('#solutionAPAPage').attr("action",'../solution/productivityLevers');
//    	$('#solutionAPAPage').submit();
//    	}
    	var url = "<%=request.getContextPath()%>/solution/complexityAdjuster";    
    	$(location).attr('href',url);
    });

    $('#prev').click(function() {
		<%-- var url = "<%=request.getContextPath()%>/solution/volumetricAdmSupp"; --%>
		var url = "<%=request.getContextPath()%>/solution/productEstimation";
    	$(location).attr('href',url);
	  });
    
/*   
	 $('#back').click(function() {
	    	$('#solutionAPAPage').attr("action",'../opportunity/defineScope');
	    	$('#solutionAPAPage').submit();
	   });
*/	 
	 $('#reset').click(function() {
		 $("#messageDiv").removeClass();
	     $("#messageDiv").html("");
		 /*$('#solutionAPAPage').each (function(){
	   		  this.reset();
	   		});*/
	   	//reset should reload page
	     window.location.href="../solution/APAnalysis";
	   });
		
	$('.addrow').click(function() {
		$("#messageDiv").removeClass();
    	$("#messageDiv").html("");
		var solnAPAPosition = $('.oppscoperow').length;	
		var el = $(this);
		var opportunityscopeid = el.attr('opportunityscopeid');
				
		$.get("<%=request.getContextPath()%>/solution/appendSolnAPARow", { rowId: solnAPAPosition, oppScopeId: opportunityscopeid},
			 function(data){

				$("tr[rowid='"+opportunityscopeid+"']").last().after(data);

			}); 
	});
	 
	 $('.deleterow').click(function() {
		 var el = $(this);
		 var solnAPAPosition = $('.oppscoperow').length;	
		 var osid = el.attr('osid');
    		
	    	if(!($("tr[rowid='"+osid+"']").last().is($("tr[rowid='"+osid+"']").first()))) {
	    		solnAPAPosition--;    	    		
	    		$.get("<%=request.getContextPath()%>/solution/appendSolnAPARow", { rowId: solnAPAPosition, oppScopeId: osid},
	    			function(data){
	    					
	    				$("tr[rowid='"+osid+"']").last().remove();
	    			}); 
	    	}
	    	else {
	    		$("#messageDiv").addClass('errorMessageDisp');
				$("#messageDiv").html('Default row can not be deleted');
	    	}
	   });
});

function showProgress(){
	$.blockUI({ message: '<img src="../images/ajax-loader.gif" /><font size="2px;">Please wait.....Request is in progress. </font>' ,css: { 
        border: '1px solid #00477D', 
        padding: '2px', 
        backgroundColor: 'white', 
        '-webkit-border-radius': '5px', 
        '-moz-border-radius': '5px', 
        opacity: .4, 
        width:'400px',
        height:'40px',
        color: 'black' 
    } }); 
	
	 var tr = $('#dataTable').find('tr');
     tr.bind('click', function(event) {
    	 var values = '';
         tr.removeClass('highlight');
         var tds = $(this).addClass('highlight').find('td');
     });		
}

function hideProgress(){
	$.unblockUI();
}
function validate(){
	
	var errorMsg='';
	var percentageSharePerScope;
	var flag=true;	
//	$('#serviceScopeIdList input').each(function(){
	$('#dataDiv input').each(function(){
		percentageSharePerScope=0;
		var trId = $(this).val();
//alert("trId = "+trId);		
		var trCount = $('tr[rowid="'+trId+'"]');
		trCount.each(function(index,element){
	       	if($('.appName', this).val()==''){
				errorMsg += "<li>Please enter application name </li>";
			}
			if(/* $('.appName', this).val()!="" || */ typeof($('.appName', this).val()) !="undefined"  &&  
					($('.percentageShare', this).val()=="" || $('.percentageShare', this).val()=="0")){
//alert("Share not selected");				
				errorMsg += "<li>Please select Share Percentage </li>";
			}
			if(errorMsg != ''){
			$("#messageDiv").addClass('errorMessageDisp');
			$("#messageDiv").html(errorMsg);
			flag=false;
			return flag;
			}
			
			if($('.percentageShare', this).val()!=""){
				percentageSharePerScope += parseFloat($('.percentageShare', this).val());
			}
		});
		if(percentageSharePerScope>100){
//alert("Share > 100");			
				$("#messageDiv").addClass('errorMessageDisp');
				$("#messageDiv").html('Total Share Percentage per scope cannot exceed 100');
			flag=false;
			return flag;
		}
		if(!flag) return flag;
	});
	return flag;
}
</script>
<link href="css/it_mssp.css" rel="stylesheet" type="text/css" />
</head>

<div>
<%-- 
	<div id="messageDiv" class="message">
		${message}
	</div>
--%>	
<div align="center" id="messageDiv"></div>
	<div>
	
		<!--start your page from here  -->
		<form:form name="solutionAPAPage" action="saveAPAnalysis" method="post" commandName="apaDTO" id="solutionAPAPage">
		<div>
			<!-- <div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 10px; padding-bottom: 10px;"> -->
			<div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 10px; padding-bottom: 10px;">
					<table width="100%">
						<tr>
							<td class="tdHeaderLabel" colspan="10"><b>Application Portfolio Analysis</b></td>
						</tr>
						<tr>
						<td height="5" colspan="2"></td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<div id="bodyDiv" class="mainBodyDiv1">
								<jsp:include page="opportunitySummary.jsp"></jsp:include>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center" height="5" colspan="2"></td>
					</tr>
					</table>
					<tr>
						<td align="center"><jsp:include page="./serviceBucket.jsp" />
						</td>
					</tr>					
					<table id="dataTableWA" width="40%" border="0" align="center" >
						<tr>
							<td class="tdTableHead" colspan="5">SCORE CARD (WEIGHTED
								AVERAGE)</td>
							<td colspan="9"></td>
						</tr>
						<tr>
							<c:forEach var="ComplexityFactorList" items="${ComplexityFactorList}" varStatus="i">
								<c:if test="${i.index < 5}">
									<td id="first" class="tdTableSubHead" nowrap="nowrap">${ComplexityFactorList}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<c:choose>
							<c:when test="${empty apaDTO.apaWeightage}">							
							<td><select name="apaWeightage.businessCriticalilty"
								id="businessCriticaliltyWA" class="jumpMenuPortfolio_small" onchange="calculateIndividualScores();">
									<!-- <option value="0">--Select--</option> -->
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10" selected>10</option>
							</select></td>
							<td><select name="apaWeightage.levelofDocuments" id="levelofDocumentsWA"
								class="jumpMenuPortfolio_small" onchange="calculateIndividualScores();">
									<!-- <option value="0">--Select--</option> -->
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10" selected>10</option>
							</select></td>
							<td><select name="apaWeightage.stability" id="stabilityWA"
								class="jumpMenuPortfolio_small" onchange="calculateIndividualScores();">
									<!-- <option value="0">--Select--</option> -->
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10" selected>10</option>
							</select></td>
							<td><select name="apaWeightage.longivity" id="longivityWA"
								class="jumpMenuPortfolio_small" onchange="calculateIndividualScores();">
									<!-- <option value="0">--Select--</option> -->
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10" selected>10</option>
							</select></td>
							<td><select name="apaWeightage.complexity" id="complexityWA"
								class="jumpMenuPortfolio_small" onchange="calculateIndividualScores();">
									<!-- <option value="0">--Select--</option> -->
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10" selected>10</option>
							</select></td>
							</c:when>
							<c:otherwise>
							<td><select name="apaWeightage.businessCriticalilty"
								id="businessCriticaliltyWA" class="jumpMenuPortfolio_small" onchange="calculateIndividualScores();" >
									<!-- <option value="0">--Select--</option> -->									
									<option value="1"<c:if test="${apaDTO.apaWeightage.businessCriticalilty eq 1}"> selected="selected"</c:if>>1</option>
									<option value="2"<c:if test="${apaDTO.apaWeightage.businessCriticalilty eq 2}"> selected="selected"</c:if>>2</option>
									<option value="3"<c:if test="${apaDTO.apaWeightage.businessCriticalilty eq 3}"> selected="selected"</c:if>>3</option>
									<option value="4"<c:if test="${apaDTO.apaWeightage.businessCriticalilty eq 4}"> selected="selected"</c:if>>4</option>
									<option value="5"<c:if test="${apaDTO.apaWeightage.businessCriticalilty eq 5}"> selected="selected"</c:if>>5</option>
									<option value="6"<c:if test="${apaDTO.apaWeightage.businessCriticalilty eq 6}"> selected="selected"</c:if>>6</option>
									<option value="7"<c:if test="${apaDTO.apaWeightage.businessCriticalilty eq 7}"> selected="selected"</c:if>>7</option>
									<option value="8"<c:if test="${apaDTO.apaWeightage.businessCriticalilty eq 8}"> selected="selected"</c:if>>8</option>
									<option value="9"<c:if test="${apaDTO.apaWeightage.businessCriticalilty eq 9}"> selected="selected"</c:if>>9</option>
									<option value="10"<c:if test="${apaDTO.apaWeightage.businessCriticalilty eq 10}"> selected="selected"</c:if>>10</option>
							</select></td>
							<td><select name="apaWeightage.levelofDocuments" id="levelofDocumentsWA"
								class="jumpMenuPortfolio_small" onchange="calculateIndividualScores();">
									<!-- <option value="0">--Select--</option> -->
									<option value="1"<c:if test="${apaDTO.apaWeightage.levelofDocuments eq 1}"> selected="selected"</c:if>>1</option>
									<option value="2"<c:if test="${apaDTO.apaWeightage.levelofDocuments eq 2}"> selected="selected"</c:if>>2</option>
									<option value="3"<c:if test="${apaDTO.apaWeightage.levelofDocuments eq 3}"> selected="selected"</c:if>>3</option>
									<option value="4"<c:if test="${apaDTO.apaWeightage.levelofDocuments eq 4}"> selected="selected"</c:if>>4</option>
									<option value="5"<c:if test="${apaDTO.apaWeightage.levelofDocuments eq 5}"> selected="selected"</c:if>>5</option>
									<option value="6"<c:if test="${apaDTO.apaWeightage.levelofDocuments eq 6}"> selected="selected"</c:if>>6</option>
									<option value="7"<c:if test="${apaDTO.apaWeightage.levelofDocuments eq 7}"> selected="selected"</c:if>>7</option>
									<option value="8"<c:if test="${apaDTO.apaWeightage.levelofDocuments eq 8}"> selected="selected"</c:if>>8</option>
									<option value="9"<c:if test="${apaDTO.apaWeightage.levelofDocuments eq 9}"> selected="selected"</c:if>>9</option>
									<option value="10"<c:if test="${apaDTO.apaWeightage.levelofDocuments eq 10}"> selected="selected"</c:if>>10</option>
							</select></td>
							<td><select name="apaWeightage.stability" id="stabilityWA"
								class="jumpMenuPortfolio_small" onchange="calculateIndividualScores();">
									<!-- <option value="0">--Select--</option> -->
									<option value="1"<c:if test="${apaDTO.apaWeightage.stability eq 1}"> selected="selected"</c:if>>1</option>
									<option value="2"<c:if test="${apaDTO.apaWeightage.stability eq 2}"> selected="selected"</c:if>>2</option>
									<option value="3"<c:if test="${apaDTO.apaWeightage.stability eq 3}"> selected="selected"</c:if>>3</option>
									<option value="4"<c:if test="${apaDTO.apaWeightage.stability eq 4}"> selected="selected"</c:if>>4</option>
									<option value="5"<c:if test="${apaDTO.apaWeightage.stability eq 5}"> selected="selected"</c:if>>5</option>
									<option value="6"<c:if test="${apaDTO.apaWeightage.stability eq 6}"> selected="selected"</c:if>>6</option>
									<option value="7"<c:if test="${apaDTO.apaWeightage.stability eq 7}"> selected="selected"</c:if>>7</option>
									<option value="8"<c:if test="${apaDTO.apaWeightage.stability eq 8}"> selected="selected"</c:if>>8</option>
									<option value="9"<c:if test="${apaDTO.apaWeightage.stability eq 9}"> selected="selected"</c:if>>9</option>
									<option value="10"<c:if test="${apaDTO.apaWeightage.stability eq 10}"> selected="selected"</c:if>>10</option>
							</select></td>
							<td><select name="apaWeightage.longivity" id="longivityWA"
								class="jumpMenuPortfolio_small" onchange="calculateIndividualScores();">
									<!-- <option value="0">--Select--</option> -->
									<option value="1"<c:if test="${apaDTO.apaWeightage.longivity eq 1}"> selected="selected"</c:if>>1</option>
									<option value="2"<c:if test="${apaDTO.apaWeightage.longivity eq 2}"> selected="selected"</c:if>>2</option>
									<option value="3"<c:if test="${apaDTO.apaWeightage.longivity eq 3}"> selected="selected"</c:if>>3</option>
									<option value="4"<c:if test="${apaDTO.apaWeightage.longivity eq 4}"> selected="selected"</c:if>>4</option>
									<option value="5"<c:if test="${apaDTO.apaWeightage.longivity eq 5}"> selected="selected"</c:if>>5</option>
									<option value="6"<c:if test="${apaDTO.apaWeightage.longivity eq 6}"> selected="selected"</c:if>>6</option>
									<option value="7"<c:if test="${apaDTO.apaWeightage.longivity eq 7}"> selected="selected"</c:if>>7</option>
									<option value="8"<c:if test="${apaDTO.apaWeightage.longivity eq 8}"> selected="selected"</c:if>>8</option>
									<option value="9"<c:if test="${apaDTO.apaWeightage.longivity eq 9}"> selected="selected"</c:if>>9</option>
									<option value="10"<c:if test="${apaDTO.apaWeightage.longivity eq 10}"> selected="selected"</c:if>>10</option>
							</select></td>
							<td><select name="apaWeightage.complexity" id="complexityWA"
								class="jumpMenuPortfolio_small" onchange="calculateIndividualScores();">
									<!-- <option value="0">--Select--</option> -->
									<option value="1"<c:if test="${apaDTO.apaWeightage.complexity eq 1}"> selected="selected"</c:if>>1</option>
									<option value="2"<c:if test="${apaDTO.apaWeightage.complexity eq 2}"> selected="selected"</c:if>>2</option>
									<option value="3"<c:if test="${apaDTO.apaWeightage.complexity eq 3}"> selected="selected"</c:if>>3</option>
									<option value="4"<c:if test="${apaDTO.apaWeightage.complexity eq 4}"> selected="selected"</c:if>>4</option>
									<option value="5"<c:if test="${apaDTO.apaWeightage.complexity eq 5}"> selected="selected"</c:if>>5</option>
									<option value="6"<c:if test="${apaDTO.apaWeightage.complexity eq 6}"> selected="selected"</c:if>>6</option>
									<option value="7"<c:if test="${apaDTO.apaWeightage.complexity eq 7}"> selected="selected"</c:if>>7</option>
									<option value="8"<c:if test="${apaDTO.apaWeightage.complexity eq 8}"> selected="selected"</c:if>>8</option>
									<option value="9"<c:if test="${apaDTO.apaWeightage.complexity eq 9}"> selected="selected"</c:if>>9</option>
									<option value="10"<c:if test="${apaDTO.apaWeightage.complexity eq 10}"> selected="selected"</c:if>>10</option>
							</select></td>
							</c:otherwise>
							</c:choose>
						</tr>
					</table>
				</div>
			<!-- ====================================================================================================== -->
				<div id="dataDiv" class="mainBodyDiv1" style="overflow-y: auto; overflow-x: auto; width: 100%">															
				
					<table id="paramOfScope" width="100%" border="0">
					
						<c:set var="rowcount" value="${0}"/>
						<c:forEach var="oppScope" items="${apaDTO.oppScopeList}"
							varStatus="i">
			
							<tr>
								<td id="first" class="tdTableHead" colspan="7" nowrap="nowrap"
									onclick="displayPart(-1);">${oppScope.serviceScope.serviceScopeName}</td>
								<td class="tdTableHead" colspan="8" nowrap="nowrap">Assign
									Complexity Factor</td>
							</tr>
							<tr>
								<c:forEach var="paramScope" items="${paramScope}">
									<td id="first" class="tdTableSubHead" nowrap="nowrap">${paramScope}
									<c:if test="${paramScope eq 'Application Name'}">
									<sup style="color: red" >*</sup>
									</c:if>
									</td>									
								</c:forEach>
								<c:forEach var="ComplexityFactorList"
									items="${ComplexityFactorList}">
									<td id="first" class="tdTableSubHead" nowrap="nowrap">${ComplexityFactorList}
									<c:if test="${ComplexityFactorList eq 'Share'}">
									<sup style="color: red" >*</sup>
									</c:if>
									</td>									
								</c:forEach>
							</tr>
							<%-- For NEW Solution ============================================================= 
							<c:if test="${empty apaDTO.solutionAPAList}">		--%> 	
							
							
							<%-- The following section has been modified to show multiple rows under a scope--%>
							<c:choose>
							<c:when test="${not empty apaDTO.solutionAPAList}">
							<c:set var="opscid" value="${oppScope.opportunityScopeId}"/>							
							<%
							Long opscidL = Long.valueOf(String.valueOf(pageContext.getAttribute("opscid")));
							pageContext.setAttribute("opscid",opscidL);
							%>
							<%--				
							<c:out value="${opscid}"/><br>
							<c:out value="${oppScope.opportunityScopeId}"/><br>						
							<c:out value="${scopeAPACountMap[opscid]}"/><br>
							
<c:out value="${scopeAPACountMap[opscid]}"></c:out>		

							<c:set var="opsccount" value="${scopeAPACountMap[opscid]-1}"/>
								<c:if test="${opsccount == -1}">
									<c:set var="opsccount" value="${1}"/>
								</c:if>			
--%>										
								<c:forEach var="r" begin="0" end="${scopeAPACountMap[opscid]-1}">																				
								<tr class="oppscoperow" rowindex="${i.index}" rowid="${oppScope.opportunityScopeId}" ssid="${oppScope.serviceScope.serviceScopeId}">		
									<td class="tdInputBox"><input type="text" 
									class="textBoxPortfolio_big" 
									<%-- name="solutionAPAList[${rowcount*i.index+r}].businessFunction" 
									value="${apaDTO.solutionAPAList[rowcount*i.index+r].businessFunction}"> --%>
									name="solutionAPAList[${rowcount+r}].businessFunction"
									value="${apaDTO.solutionAPAList[rowcount+r].businessFunction}">
									</input>										
									</td>
									<td class="tdInputBox"><input type="text"
										class="appName textBoxPortfolio_big"
										name="solutionAPAList[${rowcount+r}].applicationName"
										value="${apaDTO.solutionAPAList[rowcount+r].applicationName}"></td>
									<td class="tdInputBox">
										<input style="background-color:#EDFFDA;" type="text" onkeypress="return isDecimalField(this,event);return false;"
										class="textBoxPortfolio_small" name="solutionAPAList[${rowcount+r}].noUsers" 
										value="${apaDTO.solutionAPAList[rowcount+r].noUsers}"></input>
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${rowcount+r}].platform" 
										value="${apaDTO.solutionAPAList[rowcount+r].platform}">
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${rowcount+r}].databasenm" 
										value="${apaDTO.solutionAPAList[rowcount+r].databasenm}">
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${rowcount+r}].primarySkill" 
										value="${apaDTO.solutionAPAList[rowcount+r].primarySkill}">
									</td>
									<td><input type="text" class="textBoxPortfolio_big"
										name="solutionAPAList[${rowcount+r}].secondarySkill" 
										value="${apaDTO.solutionAPAList[rowcount+r].secondarySkill}">
									</td>
									<td class="tdInputBox">
<input class="businessCriticalilty" name="solutionAPAList[${rowcount+r}].businessCriticalilty" type="hidden" 
									value="${apaDTO.solutionAPAList[rowcount+r].businessCriticalilty}"></input>
									<select	class="businessCriticaliltySelect jumpMenuPortfolio_small"
										onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="1"<c:if test="${apaDTO.solutionAPAList[rowcount+r].businessCriticalilty eq 1}"> selected="selected"</c:if>>Low</option>
											<option value="2"<c:if test="${apaDTO.solutionAPAList[rowcount+r].businessCriticalilty eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="3"<c:if test="${apaDTO.solutionAPAList[rowcount+r].businessCriticalilty eq 3}"> selected="selected"</c:if>>High</option>
									</select>
									</td>
									<td class="textBoxMedium2">
<input class="levelofDocuments" name="solutionAPAList[${rowcount+r}].levelofDocuments" type="hidden" 
									value="${apaDTO.solutionAPAList[rowcount+r].levelofDocuments}"></input>									
									<select class="levelofDocumentsSelect jumpMenuPortfolio_small"
										onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="3"<c:if test="${apaDTO.solutionAPAList[rowcount+r].levelofDocuments eq 3}"> selected="selected"</c:if>>Low</option>
											<option value="2"<c:if test="${apaDTO.solutionAPAList[rowcount+r].levelofDocuments eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="1"<c:if test="${apaDTO.solutionAPAList[rowcount+r].levelofDocuments eq 1}"> selected="selected"</c:if>>High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="stability" name="solutionAPAList[${rowcount+r}].stability" type="hidden" 
									value="${apaDTO.solutionAPAList[rowcount+r].stability}"></input>										
									<select onchange="selectedValue(this);" class="stabilitySelect jumpMenuPortfolio_small">
											<option value="0">--Select--</option>
											<option value="3"<c:if test="${apaDTO.solutionAPAList[rowcount+r].stability eq 3}"> selected="selected"</c:if>>Low</option>
											<option value="2"<c:if test="${apaDTO.solutionAPAList[rowcount+r].stability eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="1"<c:if test="${apaDTO.solutionAPAList[rowcount+r].stability eq 1}"> selected="selected"</c:if>>High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="longivity" name="solutionAPAList[${rowcount+r}].longivity" type="hidden" 
									value="${apaDTO.solutionAPAList[rowcount+r].longivity}"></input>	
									<select class="longivitySelect jumpMenuPortfolio_small" onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="3"<c:if test="${apaDTO.solutionAPAList[rowcount+r].longivity eq 3}"> selected="selected"</c:if>>Low</option>
											<option value="2"<c:if test="${apaDTO.solutionAPAList[rowcount+r].longivity eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="1"<c:if test="${apaDTO.solutionAPAList[rowcount+r].longivity eq 1}"> selected="selected"</c:if>>High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="complexity" name="solutionAPAList[${rowcount+r}].complexity" type="hidden" 
									value="${apaDTO.solutionAPAList[rowcount+r].complexity}"></input>									
									<select onchange="selectedValue(this);" class="complexity jumpMenuPortfolio_small">
											<option value="0">--Select--</option>
											<option value="1"<c:if test="${apaDTO.solutionAPAList[rowcount+r].complexity eq 1}"> selected="selected"</c:if>>Low</option>
											<option value="2"<c:if test="${apaDTO.solutionAPAList[rowcount+r].complexity eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="3"<c:if test="${apaDTO.solutionAPAList[rowcount+r].complexity eq 3}"> selected="selected"</c:if>>High</option>
									</select></td>
									<td id="sharePercentage" class="textBoxMedium2">
<input class="percentageShare" name="solutionAPAList[${rowcount+r}].percentageShare" type="hidden" 
									value="${apaDTO.solutionAPAList[rowcount+r].percentageShare}"></input>										
									<select	class="percentageShare jumpMenuPortfolio_small" onblur="calScore(this);"
										onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="5"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 5}"> selected="selected"</c:if>>5</option>
											<option value="10"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 10}"> selected="selected"</c:if>>10</option>
											<option value="15"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 15}"> selected="selected"</c:if>>15</option>
											<option value="20"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 20}"> selected="selected"</c:if>>20</option>
											<option value="25"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 25}"> selected="selected"</c:if>>25</option>
											<option value="30"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 30}"> selected="selected"</c:if>>30</option>
											<option value="35"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 35}"> selected="selected"</c:if>>35</option>
											<option value="40"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 40}"> selected="selected"</c:if>>40</option>
											<option value="45"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 45}"> selected="selected"</c:if>>45</option>
											<option value="50"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 50}"> selected="selected"</c:if>>50</option>
											<option value="55"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 55}"> selected="selected"</c:if>>55</option>
											<option value="60"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 60}"> selected="selected"</c:if>>60</option>
											<option value="65"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 65}"> selected="selected"</c:if>>65</option>
											<option value="70"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 70}"> selected="selected"</c:if>>70</option>
											<option value="75"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 75}"> selected="selected"</c:if>>75</option>
											<option value="80"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 80}"> selected="selected"</c:if>>80</option>
											<option value="85"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 85}"> selected="selected"</c:if>>85</option>
											<option value="90"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 90}"> selected="selected"</c:if>>90</option>
											<option value="95"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 95}"> selected="selected"</c:if>>95</option>
											<option value="100"<c:if test="${apaDTO.solutionAPAList[rowcount+r].percentageShare eq 100}"> selected="selected"</c:if>>100</option>
									</select>
									</td>
									<td class="tdInputBox">
										<input type="text" readonly class="computedRiskFactor textBoxPortfolio_small"
										name="solutionAPAList[${rowcount+r}].computedRiskFactor" 
										value="${apaDTO.solutionAPAList[rowcount+r].computedRiskFactor}"/>
									</td>
									<td class="tdInputBox"><input class="computedRiskExposure textBoxSmall" onchange="readTextValue(this);"
										type="hidden" name="solutionAPAList[${rowcount+r}].computedRiskExposure"
										value="${apaDTO.solutionAPAList[rowcount+r].computedRiskExposure}"/>
									
										
										<input type="text" readonly name="riskExposureSelected"	class="textBoxPortfolio_small riskExposureSelected"
											<c:choose>
												<c:when test="${apaDTO.solutionAPAList[rowcount+r].computedRiskExposure == 1}">
													value="Low"
												</c:when>
												<c:when test="${apaDTO.solutionAPAList[rowcount+r].computedRiskExposure == 2}">
													value="Medium"
												</c:when>
												<c:when test="${apaDTO.solutionAPAList[rowcount+r].computedRiskExposure == 3}">
													value="High"
												</c:when>
											</c:choose>	
										/>	
																	
										<input name="solutionAPAList[${rowcount+r}].solution.solutionId"
										type="hidden" class="textBoxMedium"
										value="${apaDTO.solutionId}"></input>
										<input name="solutionAPAList[${rowcount+r}].solutionApaid"
										type="hidden" class="textBoxMedium"
										value="${apaDTO.solutionAPAList[rowcount+r].solutionApaid}"></input>									
										<input name="solutionAPAList[${rowcount+r}].opportunityScope.opportunityScopeId" 
										type="hidden" class="textBoxMedium" 
										value="${oppScope.opportunityScopeId}"></input>
										
									</td>
									</tr>
								
								</c:forEach>
							<c:set var="rowcount" value="${rowcount+scopeAPACountMap[opscid]}"/>
<%-- <c:out value="${rowcount}"></c:out> --%>							
							</c:when>
							<c:otherwise>																	
								<tr class="oppscoperow" rowindex="${i.index}" rowid="${oppScope.opportunityScopeId}" ssid="${oppScope.serviceScope.serviceScopeId}">
		
									<td class="tdInputBox"><input type="text" 
									class="textBoxPortfolio_big" 
									name="solutionAPAList[${i.index}].businessFunction" 
									value="${apaDTO.solutionAPAList[i.index].businessFunction}"></input>										
									</td>
									<td class="tdInputBox"><input type="text"
										class="appName textBoxPortfolio_big"
										name="solutionAPAList[${i.index}].applicationName"
										value="${apaDTO.solutionAPAList[i.index].applicationName}"></td>
									<td class="tdInputBox">
										<input style="background-color:#EDFFDA;" type="text" onkeypress="return isDecimalField(this,event);return false;"
										class="textBoxPortfolio_small" name="solutionAPAList[${i.index}].noUsers" 
										value="${apaDTO.solutionAPAList[i.index].noUsers}"></input>
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${i.index}].platform" 
										value="${apaDTO.solutionAPAList[i.index].platform}">
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${i.index}].databasenm" 
										value="${apaDTO.solutionAPAList[i.index].databasenm}">
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${i.index}].primarySkill" 
										value="${apaDTO.solutionAPAList[i.index].primarySkill}">
									</td>
									<td><input type="text" class="textBoxPortfolio_big"
										name="solutionAPAList[${i.index}].secondarySkill" 
										value="${apaDTO.solutionAPAList[i.index].secondarySkill}">
									</td>
									<td class="tdInputBox">
<input class="businessCriticalilty" name="solutionAPAList[${i.index}].businessCriticalilty" type="hidden" value=""></input>
									<select	class="businessCriticaliltySelect jumpMenuPortfolio_small"
										onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="1">Low</option>
											<option value="2">Medium</option>
											<option value="3">High</option>
									</select>
									</td>
									<td class="textBoxMedium2">
<input class="levelofDocuments" name="solutionAPAList[${i.index}].levelofDocuments" type="hidden" value=""></input>									
									<select class="levelofDocumentsSelect jumpMenuPortfolio_small"
										onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="3">Low</option>
											<option value="2">Medium</option>
											<option value="1">High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="stability" name="solutionAPAList[${i.index}].stability" type="hidden" value=""></input>										
									<select onchange="selectedValue(this);" class="stabilitySelect jumpMenuPortfolio_small">
											<option value="0">--Select--</option>
											<option value="3">Low</option>
											<option value="2">Medium</option>
											<option value="1">High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="longivity" name="solutionAPAList[${i.index}].longivity" type="hidden" value=""></input>	
									<select class="longivitySelect jumpMenuPortfolio_small" onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="3">Low</option>
											<option value="2">Medium</option>
											<option value="1">High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="complexity" name="solutionAPAList[${i.index}].complexity" type="hidden" value=""></input>									
									<select onchange="selectedValue(this);" class="complexity jumpMenuPortfolio_small">
											<option value="0">--Select--</option>
											<option value="1">Low</option>
											<option value="2">Medium</option>
											<option value="3">High</option>
									</select></td>
									<td id="sharePercentage" class="textBoxMedium2">
<input style="background-color:#EDFFDA;" class="percentageShare" name="solutionAPAList[${i.index}].percentageShare" type="text" value=""></input>										
									<select	class="percentageShare jumpMenuPortfolio_small" onblur="calScore(this);"
										onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="5">5</option>
											<option value="10">10</option>
											<option value="15">15</option>
											<option value="20">20</option>
											<option value="25">25</option>
											<option value="30">30</option>
											<option value="35">35</option>
											<option value="40">40</option>
											<option value="45">45</option>
											<option value="50">50</option>
											<option value="55">55</option>
											<option value="60">60</option>
											<option value="65">65</option>
											<option value="70">70</option>
											<option value="75">75</option>
											<option value="80">80</option>
											<option value="85">85</option>
											<option value="90">90</option>
											<option value="95">95</option>
											<option value="100">100</option>
									</select>
									</td>
									<td class="tdInputBox">
										<input type="text" readonly class="computedRiskFactor textBoxPortfolio_small"
										name="solutionAPAList[${i.index}].computedRiskFactor" 
										value="${apaDTO.solutionAPAList[i.index].computedRiskFactor}"/>
									</td>
									<td class="tdInputBox"><input class="computedRiskExposure textBoxSmall" onchange="readTextValue(this);"
										type="hidden" name="solutionAPAList[${i.index}].computedRiskExposure"
										value="${apaDTO.solutionAPAList[i.index].computedRiskExposure}"/>
									<input type="text" readonly name="riskExposureSelected"	class="textBoxPortfolio_small riskExposureSelected"/>										
										<input name="solutionAPAList[${i.index}].solution.solutionId"
										type="hidden" class="textBoxMedium"
										value="${apaDTO.solutionId}"></input>
										<%-- <input name="solutionAPAList[${i.index}].solutionApaid"
										type="hidden" class="textBoxMedium"
										value="${apaDTO.solutionAPAList[i.index].solutionApaid}"></input> --%>									
										<input name="solutionAPAList[${i.index}].opportunityScope.opportunityScopeId" 
										type="hidden" class="textBoxMedium" 
										value="${oppScope.opportunityScopeId}"></input>
										
									</td>
									</tr>									
								</c:otherwise>	
								</c:choose>									
<%--							
							</c:if>
							
 							
							For exsisting Solution =============================================================
							<c:forEach var="solutionAPADTO" items="${solutionAPADTO}" varStatus="j">
		
							<c:if test="${listOfServiceScope.serviceScopeId eq solutionAPADTO.solution.solutionId || solutionAPADTO eq 0}">
							<c:if test="${listOfServiceScope.serviceScopeId eq solutionAPADTO.solution.solutionId}">
								 <tr rowid="${listOfServiceScope.serviceScopeId}">
		
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${i.index}].businessFunction"
										value="${solutionAPADTO.businessFunction}"></input>
										<input name="solutionAPAList[${i.index}].solution.solutionId"
										type="hidden" class="textBoxMedium"
										value="${listOfServiceScope.serviceScopeId}"></input>
										<input name="solutionAPAList[${i.index}].solutionApaid"
										type="hidden" class="textBoxMedium"
										value="${solutionAPADTO.solutionApaid}"></input>	
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${i.index}].applicationName"
										value="${solutionAPADTO.applicationName}"></td>
									<td class="tdInputBox"><input type="text" style="background-color:#EDFFDA;" onkeypress="return isDecimalField(this,event);return false;"
										class="textBoxPortfolio_small"
										name="solutionAPAList[${i.index}].noUsers" value="${solutionAPADTO.noUsers}"></input>
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${i.index}].platform" value="${solutionAPADTO.platform}">
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${i.index}].databasenm" value="${solutionAPADTO.databasenm}">
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${i.index}].primarySkill" value="${solutionAPADTO.primarySkill}">
									</td>
									<td><input type="text" class="textBoxPortfolio_big" 
										name="solutionAPAList[${i.index}].secondarySkill" value="${solutionAPADTO.secondarySkill}">
									</td>
									<td>
<input class="businessCriticalilty" name="solutionAPAList[${i.index}].businessCriticalilty" type="hidden" value="${solutionAPADTO.businessCriticalilty}"></input>
									<select	onblur="calScore(this);" class="businessCriticaliltySelect jumpMenuPortfolio_small"
										onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="1"<c:if test="${solutionAPADTO.businessCriticalilty eq 1}"> selected="selected"</c:if>>Low</option>
											<option value="2"<c:if test="${solutionAPADTO.businessCriticalilty eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="3"<c:if test="${solutionAPADTO.businessCriticalilty eq 3}"> selected="selected"</c:if>>High</option>
									</select>
									</td>
									<td class="textBoxMedium2">
<input class="levelofDocuments" name="solutionAPAList[${i.index}].levelofDocuments" type="hidden" value="${solutionAPADTO.levelofDocuments}"></input>									
									<select class="levelofDocumentsSelect jumpMenuPortfolio_small" onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="3" <c:if test="${solutionAPADTO.levelofDocuments eq 3}"> selected="selected"</c:if>>Low</option>
											<option value="2" <c:if test="${solutionAPADTO.levelofDocuments eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="1" <c:if test="${solutionAPADTO.levelofDocuments eq 1}"> selected="selected"</c:if>>High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="stability" name="solutionAPAList[${i.index}].stability" type="hidden" value="${solutionAPADTO.stability}"></input>										
									<select onchange="selectedValue(this);" class="stabilitySelect jumpMenuPortfolio_small">
											<option value="0">--Select--</option>
											<option value="3" <c:if test="${solutionAPADTO.stability eq 3}"> selected="selected"</c:if>>Low</option>
											<option value="2" <c:if test="${solutionAPADTO.stability eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="1" <c:if test="${solutionAPADTO.stability eq 1}"> selected="selected"</c:if>>High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="longivity" name="solutionAPAList[${i.index}].longivity" type="hidden" value="${solutionAPADTO.longivity}"></input>	
									<select class="longivitySelect jumpMenuPortfolio_small" onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="3" <c:if test="${solutionAPADTO.longivity eq 3}"> selected="selected"</c:if>>Low</option>
											<option value="2" <c:if test="${solutionAPADTO.longivity eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="1" <c:if test="${solutionAPADTO.longivity eq 1}"> selected="selected"</c:if>>High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="complexity" name="solutionAPAList[${i.index}].complexity" type="hidden" value="${solutionAPADTO.complexity}"></input>									
									<select onchange="selectedValue(this);" class="complexity jumpMenuPortfolio_small">
											<option value="0">--Select--</option>
											<option value="1" <c:if test="${solutionAPADTO.complexity eq 1}"> selected="selected"</c:if>>Low</option>
											<option value="2" <c:if test="${solutionAPADTO.complexity eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="3" <c:if test="${solutionAPADTO.complexity eq 3}"> selected="selected"</c:if>>High</option>
									</select></td>
									<td id="sharePercentage" class="textBoxMedium2">
<input class="percentageShare" name="solutionAPAList[${i.index}].percentageShare" type="hidden" value="${solutionAPADTO.percentageShare}"></input>										
									<select	class="percentageShare jumpMenuPortfolio_small"
										onchange="return sumTotal(this)">
											<option value="0">--Select--</option>
											<option value="5" <c:if test="${solutionAPADTO.percentageShare eq 5}"> selected="selected"</c:if>>5</option>
											<option value="10" <c:if test="${solutionAPADTO.percentageShare eq 10}"> selected="selected"</c:if>>10</option>
											<option value="15" <c:if test="${solutionAPADTO.percentageShare eq 15}"> selected="selected"</c:if>>15</option>
											<option value="20" <c:if test="${solutionAPADTO.percentageShare eq 20}"> selected="selected"</c:if>>20</option>
											<option value="25" <c:if test="${solutionAPADTO.percentageShare eq 25}"> selected="selected"</c:if>>25</option>
											<option value="30" <c:if test="${solutionAPADTO.percentageShare eq 30}"> selected="selected"</c:if>>30</option>
											<option value="35" <c:if test="${solutionAPADTO.percentageShare eq 35}"> selected="selected"</c:if>>35</option>
											<option value="40" <c:if test="${solutionAPADTO.percentageShare eq 40}"> selected="selected"</c:if>>40</option>
											<option value="45" <c:if test="${solutionAPADTO.percentageShare eq 45}"> selected="selected"</c:if>>45</option>
											<option value="50" <c:if test="${solutionAPADTO.percentageShare eq 50}"> selected="selected"</c:if>>50</option>
											<option value="55" <c:if test="${solutionAPADTO.percentageShare eq 55}"> selected="selected"</c:if>>55</option>
											<option value="60" <c:if test="${solutionAPADTO.percentageShare eq 60}"> selected="selected"</c:if>>60</option>
											<option value="65" <c:if test="${solutionAPADTO.percentageShare eq 65}"> selected="selected"</c:if>>65</option>
											<option value="70" <c:if test="${solutionAPADTO.percentageShare eq 70}"> selected="selected"</c:if>>70</option>
											<option value="75" <c:if test="${solutionAPADTO.percentageShare eq 75}"> selected="selected"</c:if>>75</option>
											<option value="80" <c:if test="${solutionAPADTO.percentageShare eq 80}"> selected="selected"</c:if>>80</option>
											<option value="85" <c:if test="${solutionAPADTO.percentageShare eq 85}"> selected="selected"</c:if>>85</option>
											<option value="90" <c:if test="${solutionAPADTO.percentageShare eq 90}"> selected="selected"</c:if>>90</option>
											<option value="95" <c:if test="${solutionAPADTO.percentageShare eq 95}"> selected="selected"</c:if>>95</option>
											<option value="100" <c:if test="${solutionAPADTO.percentageShare eq 100}"> selected="selected"</c:if>>100</option>
									</select>
									</td>
									<td>
										<input type="text"class="computedRiskFactor textBoxPortfolio_small"
										name="solutionAPAList[${i.index}].computedRiskFactor" value="${solutionAPADTO.computedRiskFactor}">
						
									</td>
									<td><input class="computedRiskExposure textBoxSmall" onchange="readTextValue(this);"
										type="hidden" name="solutionAPAList[${i.index}].computedRiskExposure"
										value="${solutionAPADTO.computedRiskExposure}">
										<c:if test="${solutionAPADTO.computedRiskExposure eq 1}">
										<input type="text"  readonly name=="riskExposureSelected"	class="textBoxPortfolio_small riskExposureSelected" value="Low"/>
										</c:if>
										<c:if test="${solutionAPADTO.computedRiskExposure eq 2}">
										<input type="text"  readonly name=="riskExposureSelected"	class="textBoxPortfolio_small riskExposureSelected" value="Medium"/>
										</c:if>
										<c:if test="${solutionAPADTO.computedRiskExposure eq 3}">
										<input type="text"  readonly name=="riskExposureSelected"	class="textBoxPortfolio_small riskExposureSelected" value="High"/>
										</c:if>
										
									<!-- 	<select name="riskExposureSelected" disabled="disabled" class="textBoxMedium2 riskExposureSelected">
												<option value="0">--Select--</option>
												<option value="1" <c:if test="${solutionAPADTO.computedRiskExposure eq 1}"> selected="selected"</c:if>>Low</option>
											<option value="2" <c:if test="${solutionAPADTO.computedRiskExposure eq 2}"> selected="selected"</c:if>>Medium</option>
											<option value="3" <c:if test="${solutionAPADTO.computedRiskExposure eq 3}"> selected="selected"</c:if>>High</option>
										</select> -->
									</td>
								</c:if>
							</c:forEach>

 --%>
							
							<tr>
								<td colspan="15" class="tdLabelLeft" nowrap="nowrap">
								<a class="addrow" opportunityscopeid="${oppScope.opportunityScopeId}"><img src="../images/plus.gif"></a>
								<a class="deleterow" osid="${oppScope.opportunityScopeId}"><img src="../images/minus.gif"> </a>
								<%-- <a id="addrow"><img src="../images/plus.gif"></a> 
								<a id="deleterow"><img src="../images/minus.gif"> </a> --%>		
									
									<input class="oppsco" type="hidden" name="oppScopeList[${i.index}].opportunityScopeId" 
									value="${oppScope.opportunityScopeId}">	
									
									<input name="apaComplexityList[${i.index}].solution.solutionId" 
										type="hidden" class="textBoxMedium" value="${apaDTO.solutionId}">
									<c:choose>
										<c:when test="${not empty apaDTO.apaComplexityList}">
											<input name="apaComplexityList[${i.index}].apacomplexityId" 
												type="hidden" class="textBoxMedium" value="${apaDTO.apaComplexityList[i.index].apacomplexityId}">
											<input class="complexity" type="hidden" name="apaComplexityList[${i.index}].complexityAssessed" 
												value="${apaDTO.apaComplexityList[i.index].complexityAssessed}">
											<input class="complexityovr" type="hidden" name="apaComplexityList[${i.index}].complexityOverride" 
												value="${apaDTO.apaComplexityList[i.index].complexityOverride}">
											<input class="ftePercentage" type="hidden" name="apaComplexityList[${i.index}].ftepercentage" 
												value="${apaDTO.apaComplexityList[i.index].ftepercentage}">	
										</c:when>
										<c:otherwise>
											<%-- <input name="apaComplexityList[${i.index}].apacomplexityId" 
												type="hidden" class="textBoxMedium" value=""> --%>
											<input class="complexity" type="hidden" name="apaComplexityList[${i.index}].complexityAssessed" value="">
											<input class="complexityovr" type="hidden" name="apaComplexityList[${i.index}].complexityOverride" value="">
											<input class="ftePercentage" type="hidden" name="apaComplexityList[${i.index}].ftepercentage" value="">	
										</c:otherwise>
									</c:choose>											
									<input name="apaComplexityList[${i.index}].opportunityScope.opportunityScopeId" 
										type="hidden" class="textBoxMedium" value="${oppScope.opportunityScopeId}">																			
								</td>
							</tr>
							<%-- c:set var="solnAPANumber" value="${solnAPANumber+1}"/>  --%>
						</c:forEach>
						
					</table>

				</div>
				<!-- ================================================================================  -->
					<div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 5x;">
					 	 <table width="100%" border="0" >
							 <tr id="buttonRow">
						        <td   class="tdButtonRow">  
								<a id="next" class="portfolioButtons" >Next</a>
								<a id="save" class="portfolioButtons" >Save</a>
								<a id="reset" class="portfolioButtons">Reset</a>
								<a id="prev" class="portfolioButtons" >Previous</a>
							</tr>
						</table>
		 			</div>
			</div>
		</form:form>
		<!--End of input page -->
		<%-- <!--  List of service scope ids -->
		<div id="serviceScopeIdList">
		<c:forEach var="listOfServiceScope" items="${listOfServiceScope}"
							varStatus="i">
							For NEW Solution ============================================================= 
							<c:if test="${empty apaDTO.solnAPAList}">
								<input type="hidden" value="${listOfServiceScope.serviceScopeId}" />
							</c:if>
							
 							For exsisting Solution ============================================================= 
							<c:forEach var="solutionAPADTO" items="${solutionAPADTO}" varStatus="j">
							<c:if test="${listOfServiceScope.serviceScopeId eq solutionAPADTO.solution.solutionId}">
								 <input type="hidden" value="${listOfServiceScope.serviceScopeId}" />
						    </c:if>
						    </c:forEach>	 	 
						    
						    
	  </c:forEach>
	  </div> --%>
	</div>
</div>