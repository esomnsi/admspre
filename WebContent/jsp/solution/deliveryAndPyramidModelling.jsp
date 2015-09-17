		<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$( window ).load(function() {
	document.getElementById("messageDiv").style.display = "none";
	var deliveryPyramidMessage= '${deliveryPyramidMessage}';
	
	
	if(deliveryPyramidMessage== "success"){
	document.getElementById("messageDiv").style.display = "block";
 	document.getElementById("messageDiv").innerHTML ="Data Saved successfully !";
	} 
	
	if(deliveryPyramidMessage== "exception"){
		document.getElementById("messageDiv").style.display = "none";

		document.getElementById("errorDiv").style.display = "block";
		document.getElementById("errorDiv").innerHTML = " Error wile saving the data ! ";
	}
});

function save(){
	//alert(validate());
	if(!(validate() == "<ul>")){
		document.getElementById("messageDiv").style.display = "none";
		document.getElementById("errorDiv").style.display = "block";

		document.getElementById("errorDiv").innerHTML = validate()+"</ul>";
	}
	
	else{
	document.getElementById("deliveryPyramidForm").action = "./saveDeliveryPyramid";
	document.getElementById("deliveryPyramidForm").submit(); 
	
	
	}
}

$(document).ready(function() {
/* alert($("#selectedOppScopeId").val());
 */	
	$("#selectedOppScopeId").change(function(){
		
		$("#deliveryPyramidForm").attr("action","./deliveryPyramid");
		$("#deliveryPyramidForm").submit();
	});
});

function validate(){
	//var valid = true;
	var errorMsg = "<ul>";

 var size = '${yearListDP.size()}';
 var yList = new Array();
 var yearList='${yearListDP}';
 yList = yearList.slice(1 ,(yearList.length -1));
 yList=yList.split(", ");

  for(var i=0; i < size; i++ ){
	 
	var total = Number (document.getElementById("locationBreakupDTOList"+i+".onshoreLocalPc").value);
	    total+= Number (document.getElementById("locationBreakupDTOList"+i+".onshoreGSCiPc").value); 
	    total+= Number (document.getElementById("locationBreakupDTOList"+i+".onshore3PPPc").value); 
	    total+= Number (document.getElementById("locationBreakupDTOList"+i+".nearshorePc").value); 
	    total+= Number (document.getElementById("locationBreakupDTOList"+i+".offshorePc").value); 
		if(total !== 100){
	  //  alert("Column "+Number(i+1)+"Failed 100 sum validation");
	//  errorMsg+="<li>Sum of Column-"+Number(i+1)+" is not 100.00 </li>";
	errorMsg+="<ul>Location Breakup sum for Year-"+yList[i] +" is not 100% </ul>";
	   // valid=false;
		}
	}  
  return errorMsg;
}

function newPopup(id) {
	var size = '${yearListDP.size()}';
	var row = 0;
	
	var rowMsg = "";
	switch(id){
	
	case('Onshore Local'):
		 for(var i=0; i < size; i++ ){
			 row+= Number (document.getElementById("locationBreakupDTOList"+i+".onshoreLocalPc").value);
			 }
		if(row==0){
			//alert(" Row for Onshore Local is Empty");
			rowMsg= "<ul>Onshore Location Pyramid row is Empty<ul>";
		}
		
		break;
	case('Onshore GSCi'):
		for(var i=0; i < size; i++ ){
			 row+= Number (document.getElementById("locationBreakupDTOList"+i+".onshoreGSCiPc").value);
			 }
		if(row==0){
			//alert(" Row for Onshore GSCi is Empty !!");
			rowMsg="<ul>Onshore GSC Pyramid row is Empty !!<ul>";
		}
		break;
	case('Onshore 3PP'):
		for(var i=0; i < size; i++ ){
			 row+= Number (document.getElementById("locationBreakupDTOList"+i+".onshore3PPPc").value);
			 }
		if(row==0){
			//alert(" Row for Onshore 3PP is Empty !!");
			rowMsg="<ul>Onshore 3PP row is Empty !!<ul>";
		}
		break;
		
	case('Nearshore GSC'):
		for(var i=0; i < size; i++ ){
			 row+= Number (document.getElementById("locationBreakupDTOList"+i+".nearshorePc").value);
			 }
		if(row==0){
			//alert("Row for Nearshore GSC is Empty !!");
			rowMsg="<ul>Nearshore Pyramid row is Empty !!<ul>";
		}
		break;
		
	case('Offshore GSC'):
		for(var i=0; i < size; i++ ){
			 row+= Number (document.getElementById("locationBreakupDTOList"+i+".offshorePc").value);
			 }
		if(row==0){
			//alert(" Row for Offshore GSC is Empty !!");
			rowMsg="<ul>Offshore Pyramid row is Empty !!<ul>";
		}
		break;
		
	}
	
	if(row !==0 ){
 	popupWindow = window.open("/ADM_PRE/solution/displayPopup?param="+Number(document.getElementById("selectedOppScopeId").value)+"&param2="+id+"&param3="+ "", '', 'height=350,width=1120,left=75,top=200,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no');
	}
	
	else{
		document.getElementById("errorDiv").style.display = "block";

		document.getElementById("errorDiv").innerHTML= "<li>"+ rowMsg+ "</li>";
	}
	
	
 }
 
function next_prodMod() {
	document.getElementById("solLeverForm").action="./productivityModelling";
	document.getElementById("solLeverForm").submit();
  };
  
  
  function prev_solLever() {
	 	document.getElementById("solLeverForm").action="./solutionLever";
		document.getElementById("solLeverForm").submit();
	  };

</script>


<%-- 		<form:form id="deliveryPyramidForm" action="" method="post" commandName="solutionLeverDTO">

<c:forEach items="${solutionLeverDTO.locationBreakupDTOList}" varStatus="i">
           <form:input path="locationBreakupDTOList[${i.index}].nearshorePc" type="text"/>
    </c:forEach>
 --%>
		<%-- <form:form id="deliveryPyramidForm" action="" method="post" commandName="locationBreakupDTO"> --%>
				<div id="errorDiv" class="errorMessageDisp" style=" width:1080px;display:none ;margin-left:10px;text-align:left;" ></div>
		        <div id="messageDiv" class="message" style=" width:1080px;display:none; ;margin-left:10px" ></div>
		        <div>
		         <form:form id="deliveryPyramidForm" action="" method="post" commandName="solutionLeverDTO">
		            <table width="102%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td ><div class="headerBulepanel"  >Location Breakup & Pyramids</div></td>
                  </tr>
                   <tr>
                    <td height="5"></td>
                  </tr>

                </table>            
            <span style=" font-family:Arial; font-size:12px; margin-left:13px; ">Service Scope</span>
           				  <form:select path="oppScopeId" class="prodectivLeverJumpMenu" id="selectedOppScopeId">
								<form:options items="${oppScopesMap}" />
 							</form:select> 
 			<c:set var="totalBaseHours" value="${totalBaseHours}" />				
<table width="1080" cellpadding="1" cellspacing="2" class="tablepanel" style="margin-bottom:3px;" bgcolor="#FFFFFF";>
                  <tr bgcolor="#efefef" align="center">
                    <td height="25" align="left" bgcolor="#efefef" width="110" >Populate pyramid</td>
                    
                    <td align="left" bgcolor="#efefef" >Time Lines</td>
                   <c:forEach  var="year" items="${yearListDP}">
                   <td > <c:out value="${year}"/> </td>
    				</c:forEach>
                   <!--  <td >2014</td>
                    <td >2015</td>
                    <td >2016</td>
                    <td >2017</td>
                    <td >2018</td>
                    <td >&nbsp;</td> -->
                  </tr>
      <tr  bgcolor="#fafafa" align="center">
                    <td height="20" align="center" >
                    
                    
                    
                    <img src="../images/pyramidIcon.png" class="pyramidImg" alt="" onClick="newPopup('Onshore Local');" />
                   
                    
                    
                    </td>
                    <td height="20" align="left" >Onshore Location Pyramid</td>
                       <c:forEach var="locBrekup" items="${solutionLeverDTO.locationBreakupDTOList}" varStatus="i">
                   <td ><form:input  type="text"  path="locationBreakupDTOList[${i.index}].onshoreLocalPc" class="textBoxSNumric" maxlength="5" onkeypress="return isNumericWithDecimal(event,this);"/><div align="left">%</div></td>
    				<form:hidden path="locationBreakupDTOList[${i.index}].locationBreakupId"/>
    				<fmt:formatDate var="mYear" value="${locBrekup.monthYear}" pattern="MMM-yyyy" type="date"/>
     				<input type="hidden" name="locationBreakupDTOList[${i.index}].monthYear" 
						value="${mYear}">
					<%-- <input type="hidden" name="locationBreakupDTOList[${i.index}].location" 
						value="Onshore Location"> --%>
		<%-- 	 <c:out value="${solutionLeverDTO.oppScopeId}"/>  --%>
 				 
 				   <input type="hidden" name="locationBreakupDTOList[${i.index}].onshoreLocalFte" value="${(locBrekup.onshoreLocalPc/100) * perMonthHours /solutionUtilPerYearDTO.onshoreLocal}">
 						
   				</c:forEach>
                    
                
                  </tr>
                  <tr bgcolor="#fafafa"  align="center">
                    <td height="20" align="center" >
                    <img src="../images/pyramidIcon.png" class="pyramidImg" alt="" onClick="newPopup('Onshore GSCi');" />
					</td>
                    <td height="20" align="left" >Onshore GSC Pyramid</td>
                    <c:forEach var="locBrekup"  items="${solutionLeverDTO.locationBreakupDTOList}" varStatus="i">
                   <td ><form:input type="text"  path="locationBreakupDTOList[${i.index}].onshoreGSCiPc" class="textBoxSNumric" maxlength="5" onkeypress="return isNumericWithDecimal(event,this);" /><div align="left">%</div>  </td>
    				 <input type="hidden" name="locationBreakupDTOList[${i.index}].onshoreGSCiFte" value="${(locBrekup.onshoreGSCiPc/100) * perMonthHours /solutionUtilPerYearDTO.onshoreGSC}">
     				</c:forEach>
                    
                   
                  </tr>
                  <tr bgcolor="#fafafa"  align="center">
                    <td height="20" align="center" >
                    <img src="../images/pyramidIcon.png" class="pyramidImg" alt="" onClick="newPopup('Onshore 3PP');" />
                    </td>
                    <td height="20" align="left">Onshore 3PP Pyramid</td>
                    <c:forEach var="locBrekup" items="${solutionLeverDTO.locationBreakupDTOList}" varStatus="i">
                   <td ><form:input type="text"  path="locationBreakupDTOList[${i.index}].onshore3PPPc" class="textBoxSNumric" maxlength="5" onkeypress="return isNumericWithDecimal(event,this);"/><div align="left">%</div></td>
     			    <input type="hidden" name="locationBreakupDTOList[${i.index}].onshore3PPFte" value="${(locBrekup.onshore3PPPc/100) * perMonthHours /solutionUtilPerYearDTO.onshore3PP}">
     				</c:forEach>
                    
                  </tr>
                  <tr  bgcolor="#fafafa"  align="center">
                    <td height="20" align="center" >
                    <img src="../images/pyramidIcon.png" class="pyramidImg" alt="" onClick="newPopup('Nearshore GSC');" />
                    </td>
                    <td height="20" align="left">Nearshore Pyramid</td>
                     <c:forEach var="locBrekup" items="${solutionLeverDTO.locationBreakupDTOList}" varStatus="i">
                   <td ><form:input type="text"  path="locationBreakupDTOList[${i.index}].nearshorePc" class="textBoxSNumric" maxlength="5" onkeypress="return isNumericWithDecimal(event,this);"/><div align="left">%</div></td>
     			 <input type="hidden" name="locationBreakupDTOList[${i.index}].nearshoreFte" value="${(locBrekup.nearshorePc/100) * perMonthHours /solutionUtilPerYearDTO.nearShore}">
   				
    				</c:forEach>
                  
                  </tr>
                  <tr bgcolor="#fafafa"  align="center">
                    <td height="20" align="center" >
                    <img src="../images/pyramidIcon.png" class="pyramidImg" alt="" onClick="newPopup('Offshore GSC');" />
                    </td>
                    <td height="20" align="left" >Offshore Pyramid</td>
                     <c:forEach  var="locBrekup" items="${solutionLeverDTO.locationBreakupDTOList}" varStatus="i">
                   <td ><form:input type="text"  path="locationBreakupDTOList[${i.index}].offshorePc" class="textBoxSNumric" maxlength="5" onkeypress="return isNumericWithDecimal(event,this);" /><div align="left">%</div></td>
     			 <input type="hidden" name="locationBreakupDTOList[${i.index}].offshoreFte" value="${(locBrekup.offshorePc/100) * perMonthHours /solutionUtilPerYearDTO.offShore}">
     				
    				</c:forEach>
                   
                  </tr>
                </table>                 
                	 <a id="next" class="portfolioButtons" onClick="next_prodMod()" >Next</a>   
                	 <c:choose>
                	 <c:when test="${hasEditSolAccess!='false'}">
                    <a id="Save" class="buttoncomonStyle" onclick="save()">Save</a>
                    </c:when>
                    </c:choose>
               
                    <a id="prev" class="portfolioButtons" onClick="prev_solLever()">Previous</a>   

            </div>
            

</form:form>

            