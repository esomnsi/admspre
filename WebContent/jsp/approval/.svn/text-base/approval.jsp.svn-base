<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ericsson.mssp.common.dto.OpportunityDTO" %>
<script type="text/javascript" src="../js/jquery-1.js"></script>
<script type="text/javascript" src="../js/jquery_003.js"></script>
<script type="text/javascript" src="../js/approval.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script>

$(document).ready(function() {
	
	/* $('#savePDFButton').click(function() {
		$('#form1').attr('action','../approval/approvalDetails?isCreatePDF=true&myActionSolutionId='+"${myActionSolutionId}"+'&myActionOpportunityId='+"${myActionOpportunityId}");
		$('#form1').submit();
    }); */
	
	$('#approveButton').click(function() {
		//var solId=$('#solutionId').val();
		var approverId =0;
		$("input[type='radio']").each(function() {
				if($(this).attr("checked")){
					approverId=$(this).attr("appId");					
	            	//break;
	            }
			});
		$('#form1').attr('action','../approval/approveRequest?currentApproverId='+approverId );
		$('#form1').submit();
    });
	$('#rejectButton').click(function() {
		//var solId=$('#solutionId').val();
		var approverId =0;
		$("input[type='radio']").each(function() {
				if($(this).attr("checked")){
					approverId=$(this).attr("appId");
				   	//break;
	            }
			});
		$('#form1').attr('action','../approval/rejectRequest?currentApproverId='+approverId );	
		$('#form1').submit();
    });
	$('#saveADR').click(function() {
		$('#level1Approver').val("");
		$('#level2Approver').val("");
		$('#level3Approver').val("");
		
		var sda1 = document.getElementById('yyy');
		for(var i=0;i<sda1.length;i++){
			alert("----"+sda1.options[i].text);
			$('#level'+(i+1)+'Approver').val(sda1.options[i].text);
		}
		$('#form1').submit();
    });
	var invoiceType="${execSummary.invoiceType}";
	test_1(invoiceType);
});


</script>
<style></style>
</head>
<div class="commonHeader">Summary Details</div>
<div id="bodyDiv" class="subminApprovalTabDiv">
   <!-- <div id="m" class="message"> ${message}</div>-->      
   <c:if test="${approvalDetails.allowAction}">
   <div id="usual1" class="subminApproval">
            <ul>
              <li><a class="selected" href="#tab1"><span></span>Opportunity</a></li>
              <li><a class="" href="#tab2"><span></span>Solution</a></li>
             <!--  <li><a class="" href="#tab3"><span></span>Skills</a></li> -->
              <li><a class="" href="#tab4"><span></span>Transformation</a></li>
              <li><a class="" href="#tab5"><span></span>Top 3 ADR</a></li>
              <li><a class="" href="#tab6"><span></span>CheckLists</a></li>
              <li><a class="" href="#tab7"><span></span>Financial</a></li>

            </ul>
       
      <form:form id= "form1" method="post" modelAttribute="approvalDetails">
      
      
      <br>
     	
        
<!---------------------------------------Tab 1 (Oppurtunity Details DIV Start from Here)--------------------------------------------->
   		
            <div style="display: block;" id="tab1" class="tabBodyArea">
            	<div class="inputRow3rd_appr">
                	<h3>Client Name :</h3>
                    <span>${opportunityDTO.customerDTO.customerName}_${opportunityDTO.customerDTO.countryDTO.countryName}</span>
                </div>
			<div class="inputRow3rd_appr">
                	<h3>Owning GSC Vertical :</h3>
                    <span>${opportunityDTO.opportunityDetailsDTO.vertical}</span>
                </div>
                <div style="clear:both"></div>
                
            	 <div class="inputRow3rd_appr">
                	<h3>Originating From Region :</h3>
                    <span>${opportunityDTO.customerDTO.countryDTO.region}</span>
                </div>
                
            	<div class="inputRow3rd_appr">
                	<h3>Duration of Engagement (In Months) :</h3>
                    <span>${opportunityDTO.opportunityDetailsDTO.contractDuration}</span>
                </div>            
                <div style="clear:both"></div>
            	<div class="inputRow3rd_appr">
                	<h3>Opportunity Name :</h3>
                    <span>${opportunityDTO.opportunityName}</span>
                </div>
                
            	<div class="inputRow3rd_appr">
                	<h3>Opportunity Id :</h3>
                    <span>${opportunityDTO.opportunityId}</span>
                </div>
                <div style="clear:both"></div>
                <div class="inputRow3rd_appr">
                	<h3>Source Of Opportunity :</h3>
                    <span>${opportunityDTO.opportunitySourceDTO.name}</span>
                </div>
                
            	<div class="inputRow3rd_appr">
                	<h3>Commercial Model :</h3>
                    <span>${opportunityDTO.commercialModelDTO.name}</span>
                </div>
                <div style="clear:both"></div>
			</div>
      
 <!---------------------------------------Tab 1 (Oppurtunity Details DIV END from Here)--------------------------------------------->
           
 <!---------------------------------------Tab 2 (Steady state Profile DIV Start from Here)--------------------------------------------->           
            <div style="display: none;" id="tab2" class="subminApprovalBodyArea">
                <div class="inputRow3rd_appr">
                	<h3>Delivery Model :</h3>
                    <span>${solutionSummary.deliveryModel}</span>
                </div>
			<div class="inputRow3rd_appr">
                	<h3>Delivery Type :</h3>
                    <span>${solutionSummary.deliveryType}</span>
                </div>
                <div style="clear:both"></div>
                
            	 <div class="inputRow3rd_appr">
                	<h3>Proposed Steady State Start Date(MM/DD/YYYY) </h3>
                    <span>${solutionSummary.ssdText}</span>
                </div>
                
            	<div class="inputRow3rd_appr">
                	<h3>Offshore Delivery Location for GSC Staff :</h3>
                    <span>${solutionSummary.offShoreLocation}</span>
                </div>            
                <div style="clear:both"></div>
            	
            	<div class="inputRow3rd_appr">
                	<h3>Onshore Location for GSC Staff :</h3>
                    <span>${solutionSummary.onShoreLocation}</span>
                </div>
                
            	<div class="inputRow3rd_appr">
                	<h3>Scope of Services Defined By :</h3>
                    <span>${solutionSummary.serviceScopeDefinedBy}</span>
                </div>
                <div style="clear:both"></div>
                
              <%--  <div class="inputRow3rd_appr">
                	<h3>Y-O-Y FTE :</h3>
                    <span>${solutionSummary.yoyFTE}</span>
                </div>  --%>
                
            	<div class="inputRow3rd_appr">
                	<h3>Knowledge Transfer In Scope :</h3>
                    <span>${solutionSummary.knowledgeTrnsfrInScope}</span>
                </div>
                <div style="clear:both"></div>
            	
            	<div class="inputRow3rd_appr">
                	<h3>Proposed KT Start Date (MM/DD/YYYY) :</h3>
                    <span>${solutionSummary.KTStartDtText}</span>
                </div>
                
                <div class="inputRow3rd_appr">
                	<h3>Duration of KT (Months) :</h3>
                    <span>${solutionSummary.durationKT}</span>
                </div>
                <div style="clear:both"></div>
                
            	<div class="inputRow3rd_appr">
                	<h3>Location of Knowledge Transfer :</h3>
                    <span></span>
                </div>
                
                <div class="inputRow3rd_appr">
                	<h3>YOY Productivity Lever Applied at Steady State:</h3>
                    <span>${solutionSummary.pLeverApplied}</span>
                </div>
                <div style="clear:both"></div>
                
            	<div class="inputRow3rd_appr">
                	<h3>Input Volumetrics Defined By :</h3>
                    <span>${solutionSummary.inputVolumetricsDefinedBy}</span>
                </div>
                
                <div class="inputRow3rd_appr">
                	<h3>Productivity Gain First applied Date</h3>
                    <span>${solutionSummary.pLeverAppliedMnthText}</span>
                </div>
                <div style="clear:both"></div>
            	
                <br/>
                <table width="100%"><tbody>
                <tr><td class="tdHeaderLabel" colspan="7">Service Scope </tr>
				<tr>
					<td class="tdSubTableHead" nowrap="nowrap">Services</td>
					<td class="tdSubTableHead" nowrap="nowrap">Operation Window</td>
					<td class="tdSubTableHead">GSC FTE(Day 1 SS)</td>
				</tr>
			<c:forEach items="${sericeFTEList}" var="eachrow"
				varStatus="x">
				<tr>
					<td><input type="text" style="border:0; width:150px; background:transparent;" value="${eachrow.serviceName}" />
					</td>
					<td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachrow.operationWindow}" />
					</td>
					<td><input input type="text" style="border:0; width:250px; background:transparent;" value="${eachrow.fte}" />
					</td>
				</tr>
				</c:forEach>
                 </tbody>
                </tbody></table>
           </div>
            
  <!---------------------------------------Tab 2 (Steady state DIV END from Here)--------------------------------------------->
  
 <!---------------------------------------Tab 3 (Skills DIV Start from Here)--------------------------------------------->           
           <!--  <div style="display: none;" id="tab3" class="subminApprovalBodyArea">
                    <p>
                    	Div3..Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                        Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
                        when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
                        It has survived not only five centuries, 
                        but also the leap into electronic typesetting, remaining essentially unchanged.       
                    </p>


            </div> -->
            
  <!---------------------------------------Tab 3 (Skills Profile DIV END from Here)--------------------------------------------->

 <!---------------------------------------Tab 4 (Transformation DIV Start from Here)--------------------------------------------->           
            <div style="display: none;" id="tab4" class="subminApprovalBodyArea">
			<table width="100%" border="0" align="center" cellpacing="1">
			<tbody>
			<tr><td class="tdHeaderLabel" colspan="7">Transformation </tr>
			<tr>
					<!-- <td class="tdSubTableHead" nowrap="nowrap">SolutionADRID</td>-->
					<!-- <td class="tdSubTableHead" nowrap="nowrap">ADR Category</td> -->
					<td class="tdSubTableHead" nowrap="nowrap">PartitionName</td>
					<td class="tdSubTableHead" nowrap="nowrap">Duration Weeks(P-L-A-P-D)=Total</td>
					<td class="tdSubTableHead">Staff Ramp Up Pattern(%)</td>
					<%-- <td class="tdSubTableHead" nowrap="nowrap">KT Location</td> --%>
					<td class="tdSubTableHead">StartDate to EndDate</td>
			</tr>
			<c:forEach items="${tntReportRows}" var="eachrow"
				varStatus="x">
			<tr>
					 <!-- <td><input type="text" style="border:0; width:20px; background:transparent;" value="${eachADR.solutionAdrid}" />
					</td> -->
					<!-- <td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachADR.adrcategory}" />
					</td> -->
					<td><input type="text" style="border:0; width:150px; background:transparent;" value="${eachrow.partitionName}" />
					</td>
					<td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachrow.durationWeeks}" />
					</td>
					<td><input input type="text" style="border:0; width:250px; background:transparent;" value="${eachrow.staffRampUp}" />
					</td>
					<%-- <td><input type="text" style="border:0; width:50px; background:transparent;" value="" />
					</td> --%>
					<td><input type="text" style="border:0; width:200px; background:transparent;" value="${eachrow.partitionDuration}" />
					</td>
				</tr>
				</c:forEach>
                 </tbody>
			</table>   
		 </div>
            
  <!---------------------------------------Tab 4 (Transformatio  DIV END from Here)--------------------------------------------->

 <!---------------------------------------Tab 5 (Top 3 ADR DIV Start from Here)--------------------------------------------->           
            <div style="display: none;" id="tab5" class="subminApprovalBodyArea">
            <table width="100%" border="0" align="center" cellpacing="1">
			<tbody>
			<tr><td class="tdHeaderLabel" colspan="7">Top 3 Assumptions</td></tr>
			<tr>
					<!-- <td class="tdSubTableHead" nowrap="nowrap">SolutionADRID</td>-->
					<!-- <td class="tdSubTableHead" nowrap="nowrap">ADR Category</td> -->
					<td class="tdSubTableHead" nowrap="nowrap">ADR Statement</td>
					<td class="tdSubTableHead" nowrap="nowrap">ADR Area</td>
					<td class="tdSubTableHead">ADR Type</td>
					<td class="tdSubTableHead" nowrap="nowrap">Impact</td>
					<td class="tdSubTableHead">Weightage</td>
			</tr>
			<c:forEach items="${adrReport.assumptionList}" var="eachADR"
				varStatus="x">
				<tr>
					 <!-- <td><input type="text" style="border:0; width:20px; background:transparent;" value="${eachADR.solutionAdrid}" />
					</td> -->
					<!-- <td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachADR.adrcategory}" />
					</td> -->
					<td><input type="text" style="border:0; width:300px; background:transparent;" value="${eachADR.adrstatement}" />
					</td>
					<td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachADR.adrarea}" />
					</td>
					<td><input input type="text" style="border:0; width:100px; background:transparent;" value="${eachADR.adrtype}" />
					</td>
					<td><input type="text" style="border:0; width:50px; background:transparent;" value="${eachADR.adrimpact}" />
					</td>
					<td><input type="text" style="border:0; width:50px; background:transparent;" value="${eachADR.adrweightage}" />
					</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
			<br/>
			<table width="100%" border="0" align="center" cellpacing="1">
			<tbody>
			<tr><td class="tdHeaderLabel" colspan="7">Top 3 Dependencies</td></tr>
			<tr>
					<!-- <td class="tdSubTableHead" nowrap="nowrap">SolutionADRID</td>
					<td class="tdSubTableHead" nowrap="nowrap">ADR Category</td> -->
					<td class="tdSubTableHead" nowrap="nowrap">ADR Statement</td>
					<td class="tdSubTableHead" nowrap="nowrap">ADR Area</td>
					<td class="tdSubTableHead">ADR Type</td>
					<td class="tdSubTableHead" nowrap="nowrap">Impact</td>
					<td class="tdSubTableHead">Weightage</td>
			</tr>
			<c:forEach items="${adrReport.dependencyList}" var="eachADR"
				varStatus="x">
				<tr>
					<!-- <td><input type="text" style="border:0; width:20px; background:transparent;" value="${eachADR.solutionAdrid}" />
					</td>
					<td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachADR.adrcategory}" />
					</td> -->
					<td><input type="text" style="border:0; width:300px; background:transparent;" value="${eachADR.adrstatement}" />
					</td>
					<td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachADR.adrarea}" />
					</td>
					<td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachADR.adrtype}" />
					</td>
					<td><input type="text" style="border:0; width:50px; background:transparent;" value="${eachADR.adrimpact}" />
					</td>
					<td><input type="text" style="border:0; width:50px; background:transparent;" value="${eachADR.adrweightage}" />
					</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
			<br/>
			<table width="100%" border="0" align="center" cellpacing="1">
			<tbody>
			<tr><td class="tdHeaderLabel" colspan="7">Top 3 Risks</td></tr>
			<tr>
					<!-- <td class="tdSubTableHead" nowrap="nowrap">SolutionADRID</td>
					<td class="tdSubTableHead" nowrap="nowrap">ADR Category</td> -->
					<td class="tdSubTableHead" nowrap="nowrap">ADR Statement</td>
					<td class="tdSubTableHead" nowrap="nowrap">ADR Area</td>
					<td class="tdSubTableHead">ADR Type</td>
					<td class="tdSubTableHead" nowrap="nowrap">Impact</td>
					<td class="tdSubTableHead">Weightage</td>
			</tr>
			<c:forEach items="${adrReport.riskList}" var="eachADR"
				varStatus="x">
				<tr>
					<!-- <td><input type="text" style="border:0; width:20px; background:transparent;" value="${eachADR.solutionAdrid}" />
					</td>
					<td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachADR.adrcategory}" />
					</td> -->
					<td><input type="text" style="border:0; width:300px; background:transparent;" value="${eachADR.adrstatement}" />
					</td>
					<td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachADR.adrarea}" />
					</td>
					<td><input type="text" style="border:0; width:100px; background:transparent;" value="${eachADR.adrtype}" />
					</td>
					<td><input type="text" style="border:0; width:50px; background:transparent;" value="${eachADR.adrimpact}" />
					</td>
					<td><input type="text" style="border:0; width:50px; background:transparent;" value="${eachADR.adrweightage}" />
					</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
			</div>
            
  <!---------------------------------------Tab 5 (Top 3 ADR DIV END from Here)--------------------------------------------->

<!---------------------------------------Tab 6 (CheckLists DIV Start from Here)--------------------------------------------->           
             <div style="display: none;" id="tab6" class="subminApprovalBodyArea">
                   <table width="1100" border="0" cellspacing="0" cellpadding="0"
					style="border: 1px solid #d6d5d4;" class="checkList_approval">
					<tr class="tdHeaderLabel">
						<td colspan="2">Check List -Solution Development</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td width="854">Staffing Pyramid applied as per CSI-ADM
							Solution Metrics Guideline</td>
						<td width="246"><form:select readonly="true"
								path="checkListElements.checkList1" items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Governance & PMO Lever applied as per defined guidelines</td>
						<td><form:select path="checkListElements.checkList2" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Transformation (KT) duration is as per defined guidelines
							and reviewed by T&T PA Lead</td>
						<td><form:select path="checkListElements.checkList3" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Onshore - Offshore Mix is as per defined guidelines</td>
						<td><form:select path="checkListElements.checkList4" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>All cost adders for out of India business hour support
							has been added as per HR Policy</td>
						<td><form:select path="checkListElements.checkList5" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Onsite travel Restrictions, Policies, Visa Timelines,
							Costs have been checked and applied</td>
						<td><form:select path="checkListElements.checkList6" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Any GSC SLA applicable for this opportunity?</td>
						<td><form:select path="checkListElements.checkList7" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Is the SLA parameters defined by the Region/End Client</td>
						<td><form:select path="checkListElements.checkList8" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr class="tdHeaderLabel">
						<td colspan="2">Check List- Staff Planning</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Availability of Skills have been pre-checked with RM Team</td>
						<td><form:select path="checkListElements.checkList9" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Is the start Date of the project within 90 days of the
							proposal submission date ?</td>
						<td><form:select path="checkListElements.checkList10" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>For <= 90 days to start project, confirmation of
							Deployment is available from RM</td>
						<td><form:select path="checkListElements.checkList11" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>Visa Eligibility of Practitioners & associated timelines
							have been confirmed by Travel Desk</td>
						<td><form:select path="checkListElements.checkList12" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>For SI->AMS, written approval from respective Practice
							heads and/or RM team available to continue same practitioners for
							atleast 6 mths.</td>
						<td><form:select path="checkListElements.checkList13" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
					<tr bgcolor="#f4f4f4">
						<td>All Demands have been raised in EriPro with appropriate
							Win-Odds Probability</td>
						<td><form:select path="checkListElements.checkList14" readonly="true"
								items="${checkListValues}" />
						</td>
					</tr>
				</table>


            </div> 
            
  <!---------------------------------------Tab 3 (Skills Profile DIV END from Here)--------------------------------------------->
  <!---------------------------------------Tab 6 (Financial DIV Start from Here)--------------------------------------------->           
            <div style="display: none;" id="tab7" class="subminApprovalBodyArea">
                    <div style="width:1111px;overflow-x: auto;white-space: nowrap;">
                     	<jsp:include page="./approvalExecSummary.jsp" />
                     	<br/>
                    </div>


            </div>
            
  <!---------------------------------------Tab 6 (Financial  DIV END from Here)--------------------------------------------->		
 <form:input type="hidden" id="solutionId" path="solutionId"></form:input>
 
 

	<div class="approval_commentText">Comment</div>
	<div class="approval_comment">
   <form:textarea  id="comments" path="comments" rows="5" cols="30" class="approval_comment"></form:textarea>
   </div>
   
   <c:if test="${currentRole eq 'ROLE_SUPER_USER'}">
	<div style="text-align: left;font-size: 13px;font-style:italic;color:#b16400;" class="subminApproval">Approve/Reject on behalf of :
	
	<c:forEach var="item" items="${solAppList}">
			<input type="radio" id="accessRadio" path="selected"  name="radio" appId="${item.approver}" checked="checked">			
			${item.approver}			
	</c:forEach>
	</div>
   </c:if>
	
	<div class="tabsDefineScoprButtons">
     	<div class="TabsCommonButtons" id="approveButton">APPROVE</div>
    	<div class="TabsCommonButtons" id="rejectButton">REJECT</div>
    	<!-- <div class="TabsCommonButtons" id="savePDFButton">SavePDF</div> -->
    	<a href="./savePDF?myActionSolutionId=${myActionSolutionId}&myActionOpportunityId=${myActionOpportunityId}" style="text-decoration:none;" class="TabsCommonButtons">EXPORT PDF</a>
    </div>
    </form:form>
    </div>
  </c:if>
  <c:if test="${not approvalDetails.allowAction}">
  	<div class="errorMessageDisp" align="left">${message}</div>
  </c:if>
</div>




<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>
