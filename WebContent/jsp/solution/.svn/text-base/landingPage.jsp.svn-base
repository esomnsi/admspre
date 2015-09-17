<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#messageBox1{
	display: none
}
</style>



<script type="text/javascript">
	$(document).ready(function() {
		$(document.forms[0]).validate({
			errorContainer : "#showError",
			errorLabelContainer : "#showError ",
			/* wrapper : "li", */
			focusInvalid : true,
			rules : {
				opportunityName : {
					required: true,
					maxlength: 100
				},
				'customerDTO.customerName' : {
					required : true,
					maxlength: 100
				},
				'customerDTO.countryDTO.countryId' : {
					required: true
				},
				/* 'workflowTimelinesDTO.rfpRecieveDate': {
					required: true,
					date: true
				}, */
				'workflowTimelinesDTO.qSubdate': {
					required: true,
					date:true
				},
				/* 'workflowTimelinesDTO.sReviewDate': {
					required: true,
					date:true
				},
				'workflowTimelinesDTO.sDate': {
					required: true,
					date:true
				},
				'workflowTimelinesDTO.aDate': {
					required: true,
					date:true
				}, */
				'workflowTimelinesDTO.comments': {
					maxlength:500
				}
			},
			messages : {
				opportunityName : {
					required: "<ul><li>Please enter the opportunity name</li></ul>",
					maxlength: "<ul><li>Opportunity name should be less than 100 characters</li></ul>"
				},
				'customerDTO.customerName' : {
					required : "<ul><li>Please enter the customer name</li></ul>",
					maxlength: "<ul><li>Customer name should be less than 100 characters</li></ul>"
				},
				'customerDTO.countryDTO.countryId' : {
					required: "<ul><li>Please enter the owning country</li></ul>"
				},
				/* 'workflowTimelinesDTO.rfpRecieveDate': {
					required: "<ul><li>Please enter the opportunity recieve date</li></ul>",
					date:"<ul><li>please enter a valid Oppurtunity Receive Date</li></ul>"
				}, */
				'workflowTimelinesDTO.qSubdate': {
					required: "<ul><li>Please enter the questionnaire submission date</li></ul>",
					date:"<ul><li>please enter a valid Questionnaire Submission Date</li></ul>"
				},
				/* 'workflowTimelinesDTO.sReviewDate': {
					required: "<ul><li>Please enter the solution review date</li></ul>",
					date:"<ul><li>please enter a valid Solution Review Date</li></ul>"
				},
				'workflowTimelinesDTO.sDate': {
					required: "<ul><li>Please enter the submission date</li></ul>",
					date:"<ul><li>please enter a valid Submission Date</li></ul>"
				},
				'workflowTimelinesDTO.aDate': {
					required: "<ul><li>Please enter the approval date</li></ul>",
					date:"<ul><li>please enter a valid Approval Date</li></ul>"
				}, */
				'workflowTimelinesDTO.comments': {
					maxlength:"<ul><li>Comments should be less than 500 characters</li></ul>"
				}
			}
		});

		$("#Reqdate").datepicker();
		$("#qSubdate").datepicker();
		$("#Appdate").datepicker();
		$("#Subdate").datepicker();
		$("#solReviewdate").datepicker();
		
		$('#submit').click(function() {
	    	$('#LForm').submit();
	   });
		
	$('#Reqdate').change(function(){
		var now = new Date();
		var day1, day2;
		var month1, month2;
		var year1, year2;
		var sd = document.getElementById("Reqdate").value;
		var ed = document.getElementById("solReviewdate").value;
		
		month1 = sd.substring (0, sd.indexOf ("/"));
		day1 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
		year1 = sd.substring (sd.lastIndexOf ("/")+1, sd.length);
		
		var date1 = year1+"/"+month1+"/"+day1;
		var date11 = new Date(date1);
		
		if(date11 < now){
			var c = confirm('SDP0 is less than current date. Do you want to continue ?');
			if(c == true){
				
			}
			else{
				document.getElementById("Reqdate").value = "";
				return false;
			}
		}
		if(ed == ''){
			
		}
		else{
		/* month1 = sd.substring (0, sd.indexOf ("/"));
		day1 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
		year1 = sd.substring (sd.lastIndexOf ("/")+1, sd.length); */

		month2 = ed.substring (0, ed.indexOf ("/"));
		day2 = ed.substring (ed.indexOf ("/")+1, ed.lastIndexOf ("/"));
		year2 = ed.substring (ed.lastIndexOf ("/")+1, ed.length); 
		
		var date2 = year2+"/"+month2+"/"+day2;
		
		var date22 = new Date(date2);
		
		if(date11 < date22){
			alert("SDP-1 should be greater than SDP-0");
			/* document.getElementById("showError").style.display = 'block';
			document.getElementById("showError").innerHTML = "<ul><li>SDP-2 should be greater than SDP-1</li></ul>"; */
			document.getElementById("solReviewdate").value = "";
			return false;
		}
		
		}
	});
		
	});
	
	
	function validateDateSDP1()
	{
		
		var day1, day2;
		var month1, month2;
		var year1, year2;
		var sd = document.getElementById("Reqdate").value;
		var ed = document.getElementById("solReviewdate").value;
		
		
		month1 = sd.substring (0, sd.indexOf ("/"));
		day1 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
		year1 = sd.substring (sd.lastIndexOf ("/")+1, sd.length);

		month2 = ed.substring (0, ed.indexOf ("/"));
		day2 = ed.substring (ed.indexOf ("/")+1, ed.lastIndexOf ("/"));
		year2 = ed.substring (ed.lastIndexOf ("/")+1, ed.length); 
		
		var date1 = year1+"/"+month1+"/"+day1;
		var date2 = year2+"/"+month2+"/"+day2;
		
		var now = new Date();
		var date22 = new Date(date2);
		
		if(date22 < now){
			var c = confirm('SDP1 is less than current date. Do you want to continue ?');
			if(c == true){
				
			}
			else{
				document.getElementById("solReviewdate").value = "";
				return false;
			}
		}
		
		
		msPerDay = 24 * 60 * 60 * 1000;
		
		var dd = Math.round((Date.parse(date2)).valueOf() - (Date.parse(date1)).valueOf())/msPerDay + 1;
		if(isNaN(dd))
		{
			alert("Please check either SDP-0 or SDP-1 date entered");
			//document.getElementById("showError").innerHTML = "<ul><li>Please check the date entered</li></ul>";
			document.getElementById("Reqdate").value = "";
			document.getElementById("solReviewdate").value = "";
			return false;
		}		
		if(dd <= 0){
			alert("SDP-1 should be greater than SDP-0");
			//document.getElementById("showError").style.display = 'block';
			//document.getElementById("showError").innerHTML = "<ul><li>SDP-2 should be greater than SDP-1</li></ul>";
			/* document.getElementById("Reqdate").value = ""; */
			document.getElementById("solReviewdate").value = "";
			return false;
		}
		else
			{
			document.getElementById("showError").style.display = 'none';
			}
	}
	
	function validateDateSDP2()
	{
		
		var day1, day2;
		var month1, month2;
		var year1, year2;
		var sd = document.getElementById("solReviewdate").value;
		var ed = document.getElementById("Appdate").value;
		
		month1 = sd.substring (0, sd.indexOf ("/"));
		day1 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
		year1 = sd.substring (sd.lastIndexOf ("/")+1, sd.length);

		month2 = ed.substring (0, ed.indexOf ("/"));
		day2 = ed.substring (ed.indexOf ("/")+1, ed.lastIndexOf ("/"));
		year2 = ed.substring (ed.lastIndexOf ("/")+1, ed.length); 
		
		var date1 = year1+"/"+month1+"/"+day1;
		var date2 = year2+"/"+month2+"/"+day2;
		
		var now = new Date();
		var date22 = new Date(date2);
		
		if(date22 < now){
			var c = confirm('SDP2 is less than current date. Do you want to continue ?');
			if(c == true){
				
			}
			else{
				document.getElementById("Appdate").value = "";
				return false;
			}
		}
		
		
		msPerDay = 24 * 60 * 60 * 1000;
		
		var dd = Math.round((Date.parse(date2)).valueOf() - (Date.parse(date1)).valueOf())/msPerDay + 1;
		if(isNaN(dd))
		{
			alert("Please check value of either SDP-2 or SDP-1 date entered");
			/* document.getElementById("showError").style.display = 'block';
			document.getElementById("showError").innerHTML = "<ul><li>Please check the date entered</li></ul>"; */
			document.getElementById("solReviewdate").value = "";
			document.getElementById("Appdate").value = "";
			return false;
		}		
		if(dd < 0){
			alert("SDP-2 should be greater than SDP-1");
			/* document.getElementById("showError").style.display = 'block';
			document.getElementById("showError").innerHTML = "<ul><li>SDP-3 should be greater than SDP-2</li></ul>"; */
			document.getElementById("Appdate").value = "";
			/* document.getElementById("solReviewdate").value = ""; */
			return false;
		}
		else
			{
			document.getElementById("showError").style.display = 'none';
			}
	}
	
	function validateSubmissionDate()
	{
		
		var day1, day2, day3;
		var month1, month2, month3;
		var year1, year2, year3;
		var sd = document.getElementById("Reqdate").value;
		var ed = document.getElementById("Appdate").value;
		var fd = document.getElementById("Subdate").value;
		
		month1 = sd.substring (0, sd.indexOf ("/"));
		day1 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
		year1 = sd.substring (sd.lastIndexOf ("/")+1, sd.length);

		month2 = ed.substring (0, ed.indexOf ("/"));
		day2 = ed.substring (ed.indexOf ("/")+1, ed.lastIndexOf ("/"));
		year2 = ed.substring (ed.lastIndexOf ("/")+1, ed.length);
		
		month3 = fd.substring (0, fd.indexOf ("/"));
		day3 = fd.substring (fd.indexOf ("/")+1, fd.lastIndexOf ("/"));
		year3 = fd.substring (fd.lastIndexOf ("/")+1, fd.length);
		
		var date1 = year1+"/"+month1+"/"+day1;
		var date2 = year2+"/"+month2+"/"+day2;
		var date3 = year3+"/"+month3+"/"+day3;
		
		var date11 = new Date(date1);
		var date22 = new Date(date2);
		var date33 = new Date(date3);
		
		
		var now = new Date();
		
		
		if(date33 < now){
			var c = confirm('Submission date is less than current date. Do you want to continue ?');
			if(c == true){
				
			}
			else{
				document.getElementById("Subdate").value = "";
				return false;
			}
		}
		
		if(date33 < date11)
		{
			alert("Submission date should be between SDP-0 and SDP-2");
			/* document.getElementById("showError").style.display = 'block';
			document.getElementById("showError").innerHTML = "<ul><li>Submission date should be between SDP-1 and SDP-3</li></ul>"; */
			document.getElementById("Subdate").value = "";
		}
		else if(date33 > date22)
		{
			alert("Submission date should be between SDP-0 and SDP-2");
			/* document.getElementById("showError").style.display = 'block';
			document.getElementById("showError").innerHTML = "<ul><li>Submission date should be between SDP-1 and SDP-3</li></ul>"; */
			document.getElementById("Subdate").value = "";
		}
		else
		{
			document.getElementById("showError").style.display = 'none';
		}
	}
	
	function hideDiv1(){
		document.getElementById("showError").style.display = 'none';
	}
	function nextPage() {
		document.forms[0].action = "/ADM_PRE/opportunity/openSolution";
		document.forms[0].submit();
	}
	function newOpportunity(){
		document.forms[0].action = "/ADM_PRE/opportunity/newOpportunity";
		document.forms[0].submit();
	}
	
	function next(){
		document.forms[0].action = "/ADM_PRE/opportunity/displayOpportunity";
		document.forms[0].submit();
	}
	
	function clearForm(){
		
		document.forms[0].reset();
	}
	window.onload = hideDiv1;
</script>

		<div  class="message" style="font-family: arial;font-size: 12px;">
		${message}
		</div>
	
	<div id="messageBox1"  class="errorMessage" style="font-family: arial;font-size: 12px;">
	<ul id="c">
	
	</ul>

	</div>
	
	<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
	-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;font-family: arial;font-size: 12px;">
		
	</div>

	<div class="banner_MainWrapper">
	<div class="banner_reportAnd3DiamensionalPanel">
    	<div class="banner_threeDiamensionalIcon tooltipBanner"><a href="#"></a>
                    <p>
						Tool suffices the need to cater solutioning based on  varied inputs as received from RFP / Customer where in the need is to branch out and create different methods. The model will consider client's volumetric stack, client's people stack and details of client's IT spending (in the form of CAPEX and OPEX details) as three separate dimensions. All of these could be mutually inclusive to each other.                    
					</p>
        </div>
        <div class="banner_reportingIcon tooltipBanner"><a href="#"></a>
                    <p>
                    	Tool enabled with extensive reporting and dashboard mechanism which allow users to slice and dice data and generate graphical views dynamically for the selected scope of services. With varied type of reports like FTE summary view, Cost summary view, Report describing percentage of contribution on different cost component, Summary and detailed staffing/ramp up, pyramid summary, deal comparison report         
                    </p>
        </div>
    </div>
    
    <div class="banner_costRateAndDesignPanel">
    	<div class="banner_costRateIcon tooltipBanner"><a href="#"></a>
                    <p>
						Model as an analytics to better understand Loaded Cost Rate trend for Delivery and Solution optimization for similar type of opportunities or opportunities triggering out of same geographies or client profile. This feature will also help Win-Loss analysis process to effectively utilize the "Lessons Learnt" from one Solution to the other.                     
					</p>
        </div>
        <div class="banner_designAndPlaningIcon tooltipBanner"><a href="#"></a>
                    <p>
						Tool enables transformation design & planning , the ramp up plan would be created which would consider the application alignment along with optimization of cost. Transformation Design and Planning section will derive ideally derive input from Application Portfolio Analysis and will apply portfolio level complexity to generate application transformation plan.
					</p>        
        </div>
    </div>
    
    <div class="banner_historyAndPortfolioPanel">
    	<div class="banner_historyIcon tooltipBanner"><a href="#"></a>
                    <p>
						Tool enables the historical analysis of parameters considered for similar kind of Solution profiling done earlier so to optimize the offer as required. A versioning system where each iteration from creation to the approval / rejection would have to be stored and time stamped with a single view window of all the versions with associated owners.
					</p>                
        </div>
        <div class="banner_portfolioIcon tooltipBanner"><a href="#"></a>
                    <p>
						Model to enable analyzing the client's AS-IS application portfolio using current scope, volumetric, application complexity factors. Output put will Application Portfolio Balanced Score Card. This section will also provide necessary commentary during the journey. Application portfolio analysis forms the basis of any solution.  The analysis augments the solution with the basis of Application risk and complexity. 					
					</p>                
        </div>
    </div>
</div>
</div>





<form:form id="LForm" commandName="OpportunityDTO" method="POST" action="./InitiateOpportunity">
	
	<form:hidden  path="opportunityId" value="${OpportunityDTO.opportunityId}"></form:hidden>
	<form:hidden  path="workflowTimelinesDTO.workflowTimelineId" value="${OpportunityDTO.workflowTimelinesDTO.workflowTimelineId}"></form:hidden>
		
	<div class="basicForm_wrapper" style="margin-left: 2px;margin-top: 0px;width:447px;">
	<!-- <div class="basicForm_headerPanel">
		<div class="myActionButton">
   	    <h1>My Action </h1>
       	  <span></span>
        </div>
        </div> -->
        <div class="basicForm_bodyPanel">
    	<div class="basicForm_activePanel">
        	
        	<h1>Provide Basic Opportunity Information </h1>
        	<span></span>
        </div>
        <!-- <div class="basicForm_deActivePanel">
        	<span></span>
        	<h1>Augment with additional<br/ >opportunity intelligence </h1>
        </div> -->
        
    
    	<div class="informationPanel">
        	<div class="textHedrPanel">Opportunity Name	<sup style="color: red" >*</sup><b> : </b></div>
            <div class="textInputPanel">
            	<div class="inputLeftBg"></div>
                <div class="inputMidBg" >
                	<form:input path="opportunityName" type="text" class="inputjumptext" id="oppName" readonly="${createdByFlag}"/>
                </div>
                <div class="inputRightBg"></div>
            </div>
        </div>
        
    	<div class="informationPanel">
        	<div class="textHedrPanel">Customer Name<sup style="color: red" >*</sup>	<b>: </b></div>
            <div class="textInputPanel">
            	<div class="inputLeftBg"></div>
                <div class="inputMidBg">
                	<form:input path="customerDTO.customerName" type="text" class="inputjumptext" id="custName" readonly="${createdByFlag}"/>
                </div>
                <div class="inputRightBg"></div>
            </div>
        </div>
        
        <div class="informationPanel">
        	<div class="textHedrPanel">Owning Country<sup style="color: red" >*</sup>	<b>: </b></div>
            <div class="textInputPanel">
            	<div class="inputLeftBg"></div>
                <div class="inputMidBg">
                	<form:select  path="customerDTO.countryDTO.countryId" id="selectcountry"
							class="inputjumptext" disabled="${createdByFlag}">
							<form:option value="" label="--Select--" />
							<form:options items="${countries}" itemValue="countryId"
								itemLabel="countryName" />
						</form:select>
                </div>
                <div class="inputRightBg"></div>
            </div>
        </div>
        
        
        <div class="dateHeadingText">Key Milestone Dates<!-- <sup style="color: red" >*</sup> --></div>
        <div class="datePanel" style="height: 73px;">
        
        	<div class="datemainWraper">
            	<!-- <div class="datetextPanel">Opp. Recv. Date</div> -->
            	<div class="datetextPanel">SDP-0</div>
                <div class="dateInputPanel">
                	
                	<div class="dateLeftBg"></div>
                    <div class="dateMidBg" >
                    	<form:input readonly="true"
							path="workflowTimelinesDTO.rfpRecieveDate" id="Reqdate"
							type="textbox" class="inputTextfield_date" />
                    </div>
                    <div class="dateRightBg"></div>
                </div>
                
            </div>


        	<div class="datemainWraper">
            	<!-- <div class="datetextPanel">Soln. Recv. Date</div> -->
            	<div class="datetextPanel">SDP-1</div>
                <div class="dateInputPanel">
                	<div class="dateLeftBg"></div>
                    <div class="dateMidBg">
                    	<form:input readonly="true"
							path="workflowTimelinesDTO.sReviewDate" id="solReviewdate"
							type="text" class="inputTextfield_date" onchange="javascript:validateDateSDP1();"/>
                    </div>
                    <div class="dateRightBg"></div>
                </div>
            </div>

			<div class="datemainWraper">
            	<!-- <div class="datetextPanel">Approval  Date</div> -->
            	<div class="datetextPanel">SDP-2</div>
                <div class="dateInputPanel">
                	<div class="dateLeftBg"></div>
                    <div class="dateMidBg">
                    	<form:input readonly="true"
							path="workflowTimelinesDTO.aDate" id="Appdate"
							type="text" class="inputTextfield_date" onchange="javascript:validateDateSDP2();"/>
                    </div>
                    <div class="dateRightBg"></div>
                </div>
            </div>

        	<div class="datemainWraper">
            	<div class="datetextPanel">Submission to Customer</div>
                <div class="dateInputPanel">
                	<div class="dateLeftBg"></div>
                    <div class="dateMidBg">
                    	<form:input readonly="true"
							path="workflowTimelinesDTO.sDate" id="Subdate"
							type="text" class="inputTextfield_date"/>
                    </div>
                    <div class="dateRightBg"></div>
                </div>
            </div>
        </div>
        
        
        <div class="informationPanel">
        	<div class="textHedrPanel">Opportunity Source<b>: </b></div>
            <div class="textInputPanel">
            	<div class="inputLeftBg"></div>
                <div class="inputMidBg">
                	<form:select  path="opportunitySourceDTO.opportunitySourceId" id=""
							class="inputjumptext" disabled="${createdByFlag}">
							<form:option value="" label="--Select--" />
							<form:options items="${oppSource}" itemValue="opportunitySourceId"
								itemLabel="name" />
						</form:select>
                </div>
                <div class="inputRightBg"></div>
            </div>
        </div>
        
         <div class="informationPanel">
        	<div class="textHedrPanel">Commercial Model	<b>: </b></div>
            <div class="textInputPanel">
            	<div class="inputLeftBg"></div>
                <div class="inputMidBg">
                	<form:select  path="commercialModelDTO.commercialModelId" id=""
							class="inputjumptext" disabled="${createdByFlag}">
							<form:option value="" label="--Select--" />
							<form:options items="${comModel}" itemValue="commercialModelId"
								itemLabel="name" />
					</form:select>
                </div>
                <div class="inputRightBg"></div>
            </div>
        </div>
        
        
    	<div class="commentinformationPanel">
        	<div class="textHedrPanel">Comments	<b>: </b></div>
            <div class="textInputPanel">
            	<div class="commentPanelLeftBg"></div>
                <div class="commentPanelMidBg">
                	<form:textarea id="comments" cols="" rows="" 
                	path="workflowTimelinesDTO.comments" class="inputTextArea" readonly="${createdByFlag}"/>
                </div>
                <div class="commentPanelRightBg"></div>
            </div>
        </div>
			
			
			<c:if test="${role_guest != 'true'}">
			<c:choose>
			<c:when test="${createdByFlag == false  || empty checkOppId}">
				
					<div id="submit" class="TabsCommonButtons">Next</div>
				
			</c:when>
			</c:choose>
			<div class="TabsCommonButtons " onclick="javascript:clearForm()">Reset</div>
			
			<c:if test="${not empty checkOppId}">
			<div id="new" class="TabsCommonButtons" onclick="javascript:newOpportunity()">Create New</div>
			</c:if>
			</c:if>
			
			<c:if test="${role_guest == 'true' && not empty checkOppId}">
				<div class="TabsCommonButtons" onclick="next()">Next</div>
			</c:if>
    </div>

</div>
</form:form>





