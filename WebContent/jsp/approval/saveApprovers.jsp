<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="../js/jquery-1.js"></script>
<script type="text/javascript" src="../js/jquery_003.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script>
$(document).ready(function() {
	
	
	$('#saveOpportunityDetails').click(function() {
	    	$('#form1').submit();
	    });
	$('#saveApprovers').click(function() {
		$('#level1Approver').val("");
		$('#level2Approver').val("");
		$('#level3Approver').val("");
		
		var sda1 = document.getElementById('yyy');
		if(sda1.length!=3){
			$("#messageinfo").html("Please select 3 approvers");
			return;
		}
		for(var i=0;i<sda1.length;i++){
			//alert("----"+sda1.options[i].text);
			$('#level'+(i+1)+'Approver').val(sda1.options[i].value);
		}
		$('#form1').submit();
    });
});

function moveoutid()
{
	
	var sda = document.getElementById('xxx');
	var sda1 = document.getElementById('yyy');
	
	var len = sda.length;
	var selCnt=0;
	for(var i=0;i<sda.length;i++){
		if(sda[i].selected){
			selCnt++;
		}
	}
	//alert("selcnt="+selCnt +"sda1.length="+sda1.length);
	if(selCnt>3 || (selCnt+sda1.length)>3 || sda1.length>=3 ){
		$("#messageinfo").html("Maximum 3 approvers can be selected");
		return;
	}
	for(var j=0; j<len; j++)
	{
		if(sda[j].selected)
		{
			var tmp = sda.options[j].text;
			var tmp1 = sda.options[j].value;
			sda.remove(j);
			j--;
			var y=document.createElement('option');
			y.text=tmp;
			y.value=tmp1;
			try
			{sda1.add(y,null);
			}
			catch(ex)
			{
			sda1.add(y);
			}
		}
	}
}


function moveinid()
{
	
	var sda = document.getElementById('xxx');
	var sda1 = document.getElementById('yyy');
	var len = sda1.length;
	for(var j=0; j<len; j++)
	{    
		
		if(sda1[j].selected)
		{
			var tmp = sda1.options[j].text;
			var tmp1 = sda1.options[j].value;
			sda1.remove(j);
			j--;
			var y=document.createElement('option');
			y.text=tmp;
			y.value=tmp1;
			try
			{
			sda.add(y,null);}
			catch(ex){
			sda.add(y);	
			}

		}
	}	
}
// end
function moveUp(){
	
	var sda1 = document.getElementById('yyy');
	var len = sda1.length; 
	var selCnt=0;
	for(var i=0;i<sda1.length;i++){
		if(sda1[i].selected){
			selCnt++;
		}
	}
	if(len==0||selCnt==0){
		$("#messageinfo").html("Atleast 1 option is to be selected to moved up");
		return;
	}
	if(selCnt>1){
		$("#messageinfo").html("Up and Down movements are applicable for single selection only");
		return;
	}
	selCnt=0;
	var selectedIndex=0;
	var redefinedOptions=new Array(sda1.length);
	for(var j=0; j<len; j++)
	{
		if(sda1[j].selected)
		{
			var tmp = sda1.options[j].text;
			var tmp1 = sda1.options[j].value;
			selectedIndex=j;
			if(j==0){
				$("#messageinfo").html("1st element cannot be moved up");
				return;
			}else{
				redefinedOptions[j-1]=sda1.options[j].text;
				redefinedOptions[j-1]=sda1.options[j].value;
			}
		}
	}
	for(var j=0;j<sda1.length;j++){
		if(j!=selectedIndex){
			if(redefinedOptions[j]==undefined){
				redefinedOptions[j]=sda1.options[j].text;
				redefinedOptions[j]=sda1.options[j].value;
			}else{
				redefinedOptions[j+1]=sda1.options[j].text;
				redefinedOptions[j+1]=sda1.options[j].value;
			}
		}
	}
	document.getElementById('yyy').innerHTML="";
	for(var i=0;i<redefinedOptions.length;i++){
		var y=document.createElement('option');
		y.text=redefinedOptions[i];
		try
		{
		sda1.add(y,null);}
		catch(ex){
		sda.add(y);	
		}
	}
}

</script>
</head>
<div class="commonHeader">Approvers</div>
<div id="bodyDiv" class="subminApprovalTabDiv">      
 <!--   <div id="messageBox1" class="errorMessage_approval" style="display:block">
Error
</div> -->
<div id="m" class="message"><div id="messageinfo"> ${message}</div>  Solution Status :
 				<c:choose>
					<c:when test="${approvalDetails.solutionStatus==1}">
							Draft
					</c:when>
					<c:when test="${approvalDetails.solutionStatus==2}">
							Submitted
					</c:when>
					<c:when test="${approvalDetails.solutionStatus==3}">
							Approved
					</c:when>
					<c:when test="${approvalDetails.solutionStatus==4}">
							Rejected
					</c:when>
				</c:choose>
 
 </div>
  <div id="usual1" class="">
    
      <form:form id= "form1" method="POST" commandName="approvalDetails" action="/ADM_PRE/approval/submitSolution">     
           
  

 
 <!---------------------------------------Select Signum Div--------------------------------------------->           
            <div style="display: block; float:left;" id="tab5" class="subminApprovalBodyArea">
            <br/>
			<table border=0 align=center valign=center>
					<tr><td class="tdHeaderLabel">Available Signums</td><td></td><td class="tdHeaderLabel">Selected Signums &nbsp;&nbsp;&nbsp;<!-- <img src="../images/upButton.png" style="cursor:pointer;" onclick="moveUp();"/> -->&nbsp;</td></tr>
					<tr><td>
					<select id="xxx" multiple size="10" style="width:160px; background-color:#f4f4f4  ">
					<c:forEach var="eachRep"  items="${availableApprovers}" >
					<option value="${eachRep.signumId}">${eachRep.signumId} (${eachRep.firstName} ${eachRep.lastName})</option>
					</c:forEach>
					</select>
					</td>
					<td>
						<table><tbody>
						<tr><td><input type=button value=">>" onclick="moveoutid();"/></td></tr>
						<tr><td><input type=button value="<<" onclick="moveinid();"/></td></tr>
						</tbody></table>
					</td>
					<td>
					<select id="yyy" style="width:160px; background-color:#f4f4f4" multiple size="10">
					   <c:forEach var="eachRep"  items="${existingApprovers}" >
						 <option value="${eachRep.approver}">${eachRep.approver}</option>
					  </c:forEach>
					</select>
				</td>
				</tr>
				</table>
			    
			    <form:input type="hidden" id="level1Approver" path="Level1Approver"></form:input>
			    <form:input type="hidden" id="level2Approver" path="Level2Approver"></form:input>
			    <form:input type="hidden" id="level3Approver" path="Level3Approver"></form:input>

				<table width="1100" border="0" cellspacing="0" cellpadding="0"
					style="border: 1px solid #d6d5d4;" class="checkList_approval">
					<tr class="tdHeaderLabel">
						<td colspan="2">Check List -Solution Development</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td width="854">Staffing Pyramid applied as per CSI-ADM
							Solution Metrics Guideline</td>
						<td width="246"><form:select
								path="checkListElements.checkList1" items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Governance & PMO Lever applied as per defined guidelines</td>
						<td><form:select path="checkListElements.checkList2"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Transformation (KT) duration is as per defined guidelines
							and reviewed by T&T PA Lead</td>
						<td><form:select path="checkListElements.checkList3"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Onshore - Offshore Mix is as per defined guidelines</td>
						<td><form:select path="checkListElements.checkList4"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>All cost adders for out of India business hour support
							has been added as per HR Policy</td>
						<td><form:select path="checkListElements.checkList5"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Onsite travel Restrictions, Policies, Visa Timelines,
							Costs have been checked and applied</td>
						<td><form:select path="checkListElements.checkList6"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Any GSC SLA applicable for this opportunity?</td>
						<td><form:select path="checkListElements.checkList7"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Is the SLA parameters defined by the Region/End Client</td>
						<td><form:select path="checkListElements.checkList8"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr class="tdHeaderLabel">
						<td colspan="2">Check List- Staff Planning</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Availability of Skills have been pre-checked with RM Team</td>
						<td><form:select path="checkListElements.checkList9"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Is the start Date of the project within 90 days of the
							proposal submission date ?</td>
						<td><form:select path="checkListElements.checkList10"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>For <= 90 days to start project, confirmation of
							Deployment is available from RM</td>
						<td><form:select path="checkListElements.checkList11"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Visa Eligibility of Practitioners & associated timelines
							have been confirmed by Travel Desk</td>
						<td><form:select path="checkListElements.checkList12"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>For SI->AMS, written approval from respective Practice
							heads and/or RM team available to continue same practitioners for
							atleast 6 mths.</td>
						<td><form:select path="checkListElements.checkList13"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>All Demands have been raised in EriPro with appropriate
							Win-Odds Probability</td>
						<td><form:select path="checkListElements.checkList14"
								items="${checkListValues}" />
						</td>
					</tr>
				</table>
				<div class="submitButton">
                	<c:choose>
					<c:when test="${approvalDetails.solutionStatus==1 || changeApproverList=='true'}">
							<div class="TabsCommonButtons" id="saveApprovers">Save</div>
					</c:when>
					<c:otherwise>.
					      <!--   <div class="TabsCommonButtons" id="saveApprovers" disabled="true">Save</div> --> 
					</c:otherwise>
					</c:choose>
                </div>
                
                </div>
            
  <!---------------------------------------Select Signum DIV END from Here--------------------------------------------->

  </form:form>
</div>	
	<div class="tabsDefineScoprButtons">
     	<!-- <div class="TabsCommonButtons">SUBMIT</div>
    	<div class="TabsCommonButtons">RESET</div> -->
    </div>
</div>




<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>
