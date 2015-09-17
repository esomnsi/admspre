<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>Location Pyramid</title>


<link href="../css/it_mssp.css" rel="stylesheet" type="text/css" />
<link href="../css/main.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../js/it_mssp.js"> </script>
<script type="text/javascript" src="../js/jquery-1.8.2.js"> </script>

<script src="../js/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="../js/jquery.multiSelect.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery.dataTables.js"></script>

<link href="../css/jquery.multiSelect.css" rel="stylesheet"	type="text/css" />
<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css"  rel="stylesheet" type="text/css" media="all" />


<script type="text/javascript">




$( window ).load(function() {
	
	var rows = '${solutionLeverDTO.serviceElementJobRoleStagesDTO.size()}';
	 var msg= '${message}';
	if(msg!= ""){
	document.getElementById("messageDiv").style.display = "block";
 	document.getElementById("messageDiv").innerHTML =" "+msg ;
	} 
 	
if(rows==0){
	//alert("Insert Job Roles and stages !! ");
	document.getElementById("errorDiv").style.display = "block";
	document.getElementById("errorDiv").innerHTML= " Mapping for Service Element & Job Roles are unavailable. Please contact Admin !! ";
	document.getElementById("pyramidPopUpForm").style.display = "none";
	
	document.getElementById("close_error_div").style.display = "block";
}
	});
function save(){ 
	document.getElementById("messageDiv").style.display = "none";
	if(!(validate() == "<ul>")){
		document.getElementById("errorDiv").style.display = "block";
		document.getElementById("errorDiv").innerHTML = validate()+"</ul>";
	}
	else{

	//document.getElementById("pyramidPopUpForm").action = "./savePopUp";
	var p1= '${oppScopeId}';
	var p2= '${location}'+" "+'${subLocation}'; 
	document.getElementById("pyramidPopUpForm").action = "/ADM_PRE/solution/savePopUp?param="+p1+"&param2="+p2;
	document.getElementById("pyramidPopUpForm").submit(); 
	
	}
}


function validate(){
	
var errorMsg = "<ul>";

var size = '${yearListDP.size()}';
var yList = new Array();
var yearList='${yearListDP}';

yList = yearList.slice(1 ,(yearList.length -1));
yList=yList.split(", ");

var rows = '${solutionLeverDTO.serviceElementJobRoleStagesDTO.size()}';
var total = 0;
  for(var i=0; i < size; i++ ){
	  total=0;
	  for (var r = 0 ; r<rows ;r++){
		   total+= Number(document.getElementById("locationPyramidDTOList["+((r*size)+i)+"]").value);
		    
	  }
	  if(total !== 100){
		  errorMsg+="<ul>Sum for Year-"+yList[i] +" is not 100% </ul>";
	  }
  }

  return errorMsg;
}




function close_current(){
	document.getElementById("pyramidPopUpForm").style.display = "none";
		window.close();
	}
	

</script>


<br>
<br>
<div id="errorDiv"  class="errorMessageDisp" style="width:99%; display:none ;text-align:center;"></div>
<form:form id="pyramidPopUpForm" commandName="solutionLeverDTO">
<%--  <form:hidden path="solutionLeverDTO.oppScopeId"/> --%>
<input type="hidden" name="oppScopeId" value="${oppScopeId}">

	<div id="messageDiv" class="message" style="width:99%; display:none;"></div>


	 <div style="display: block; width:99%; " id="popUpId" class="tabBodyArea">
<!--  <div class="closeIcon" onClick="close_current()"><img src="images/closeIcon.png" align="right"></div>    
 -->
 
   <div style="font-family:Arial; font-size:13px; font-weight:bold; line-height: 32px;
    margin-left: 7px; text-transform:uppercase;"> ${location} ${subLocation} Pyramid</div>
  
     

            <table width="98%" cellpadding="1" cellspacing="2" class="tablepanel" style="margin-bottom:3px;" bgcolor="#FFFFFF";>
                  <tr bgcolor="#efefef" align="center">
                    <td height="25" align="left" bgcolor="#efefef"  width="25%" >Time Lines</td>
                  <c:forEach  var="year" items="${yearListDP}">
                   <td > <c:out value="${year}"/> </td>
    				</c:forEach>
                  </tr>


			<c:set var="rowcount4lp" value="${0}" />
			
			<c:forEach var="servElmdTO"
				items="${solutionLeverDTO.serviceElementJobRoleStagesDTO}"
				varStatus="i">
				
					<c:set var="recordFound" value="false"/>
		                  	 <c:forEach var="locPyramid" items="${locationPyramidDTOList}">
			                  	 <c:if test="${locPyramid.jobRoleStages.jobRoleStagesId == servElmdTO.jobRoleStages.jobRoleStagesId}">
			                  	 		<c:set var="recordFound" value="true"/>
			                  	 </c:if>
	                  	 </c:forEach>
	                  	 
				<tr bgcolor="#fafafa" align="center">
					<td height="20" align="left" class="CommonInputs">
					<c:out	value="${servElmdTO.jobRoleStages.jobRole.role}" /> 
					(<c:out	value="${servElmdTO.jobRoleStages.jobStage.stage}" />)
							<%--  <br /> <c:out value="${servElmdTO.jobRoleStages.jobRoleStagesId}" /> --%>
					</td>

					<!--                 for creating boxes
 -->
			<c:forEach var="year" items="${yearListDP}" varStatus="i">
						
				<td height="20" align="center" class="CommonInputs">
 					
 					<c:forEach var="locPyramid" items="${locationPyramidDTOList}">
 					
 					<fmt:formatDate var="fYear" value="${locPyramid.monthYear}" pattern="yyyy" />
									<%-- <c:out value="${fYear}  ${year}" /> --%>
								
				<c:if test="${locPyramid.jobRoleStages.jobRoleStagesId == servElmdTO.jobRoleStages.jobRoleStagesId && fYear == year 
				
									&& 	locPyramid.location == location && locPyramid.subLocation	==	subLocation	}">				
								
								<input type="text"
									id="locationPyramidDTOList[${rowcount4lp}]"
									name="locationPyramidDTOList[${rowcount4lp}].distributionPc" class="textBoxSNumric" 
									value="${locPyramid.distributionPc}" 
									maxlength="5" onkeypress="return isNumericWithDecimal(event,this);"
									/>
									
									<input type="hidden"
									name="locationPyramidDTOList[${rowcount4lp}].locationPyramidId"
									value="${locPyramid.locationPyramidId}" />
				</c:if>
				</c:forEach>

								<c:if test="${empty locationPyramidDTOList}">
									<input type="text"
									    id="locationPyramidDTOList[${rowcount4lp}]"
										name="locationPyramidDTOList[${rowcount4lp}].distributionPc"
										class="textBoxSNumric"
										maxlength="5" onkeypress="return isNumericWithDecimal(event,this);"
										 />
								</c:if>
								
								  <c:if test="${not empty locationPyramidDTOList && recordFound==false}" >
								  <input type="text"
									    id="locationPyramidDTOList[${rowcount4lp}]"
										name="locationPyramidDTOList[${rowcount4lp}].distributionPc"
										class="textBoxSNumric"
										maxlength="5" onkeypress="return isNumericWithDecimal(event,this);"
										 />
										 
	                   		   	
	                   		   </c:if>
								
								<input type="hidden"
									name="locationPyramidDTOList[${rowcount4lp}].MonthYear"
									value="JAN-${year}">

								<input type="hidden"
									name="locationPyramidDTOList[${rowcount4lp}].location"
									value="${location}">
									
								<input type="hidden"
									name="locationPyramidDTOList[${rowcount4lp}].subLocation"
									value="${subLocation}">

								<%-- <input type="hidden"
									name="locationPyramidDTOList[${rowcount4lp}].ftecount"
									value="99999"> --%>

								<input type="hidden"
									name="locationPyramidDTOList[${rowcount4lp}].jobRoleStages.jobRoleStagesId"
									value="${servElmdTO.jobRoleStages.jobRoleStagesId}">

						<div align="left">%</div></td>
						<c:set var="rowcount4lp" value="${rowcount4lp+1}" />
			</c:forEach>
				</tr>
		</c:forEach>

			
                </table> 
               
               <table width="100%" cellpadding="1" cellspacing="2"  ;" >
				<tr >
				<td height="20" >
				 <c:choose>
                	 <c:when test="${hasEditSolAccess!='false'}">
	                <a id="Save" class="buttoncomonStyle" onClick="save()">Save</a>   
	                </c:when>
	                </c:choose>
	 				<a id="Close" class="buttoncomonStyle" onClick="close_current()">Close</a>
	
				</td>
				</tr>
				</table>
          </div>
          
          
</form:form>

<div id="close_error_div"  style="display:none;" >


<table width="50%" cellpadding="1" cellspacing="2"  style="align-text:center;" >
	<tr align="center">
	<td height="40" align="center">
          <a id="close_error"  class="buttoncomonStyle" onClick="close_current()">Close</a>
          &nbsp;
     </td>
     </tr>
	</table>
          </div>