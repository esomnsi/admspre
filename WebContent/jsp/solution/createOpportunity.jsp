<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
var action='';
	$(document).ready(function() {
		$("#form1").submit(function() {
			var options = {
					success: function(html) {
						if(html == "rkamLengthError"){
							document.getElementById("showError").style.display = 'block';
							document.getElementById("showError").innerHTML = "<ul><li>Length of Region KAM should not exceed 50 under Ericsson Personnel Tab</li></ul>";
							return false;
						}
						if(html == "compLengthError"){
							document.getElementById("showError").style.display = 'block';
							document.getElementById("showError").innerHTML = "<ul><li>Length of Competitors should not exceed 255 under Customer and Competitive Information Tab </li></ul>";
							return false;
						}
						if(html == "pBusiness"){
							document.getElementById("showError").style.display = 'block';
							document.getElementById("showError").innerHTML = "<ul><li>Length of Primary Business Line should not exceed 50 under Customer and Competitive Information Tab </li></ul>";
							return false;
						}
						showProgress('Please wait while your data is being saved..and redirecting to next stage');
						if(action=='next'){
							nextForm();
							action='';
						}else{
							document.getElementById("messageDiv").style.display = 'block';
						 $("#messageDiv").html("Your data has been saved successfully.");
						}
						$('#opportunityDetailId').val(html);
						hideProgress();
					},
					url: "/ADM_PRE/opportunity/saveOpportunity"
				};
				$(this).ajaxSubmit(options);
				return false;
		});
	});
	

	var currentSelectedTab =1;
	
	function previousPage(){
		setPreviousTab('',false);
	}
	function nextPage(flag) {
	
		if(flag == 'oppdetails')
		{
			document.getElementById("showError").style.display = 'block';
			if(document.getElementById("opportunityowner").value == '')
			{
				document.getElementById("showError").innerHTML = "<ul><li>Please enter opportunity owner</li></ul>";
				return false;
			}
			if(document.getElementById("cId").value == '')
			{
				document.getElementById("showError").innerHTML = "<ul><li>Please enter CRM 360 ID</li></ul>";
				return false;
			}
			if(document.getElementById("ec").value == '')
			{
				document.getElementById("showError").innerHTML = "<ul><li>Please select existing customer</li></ul>";
				return false;
			}
			if(document.getElementById("nb").value == '')
			{
				document.getElementById("showError").innerHTML = "<ul><li>Please select new business</li></ul>";
				return false;
			}
		}
		else if(flag == 'projectdetails')
		{
			document.getElementById("showError").style.display = 'block';
			if(document.getElementById("startdate").value == '')
			{
				document.getElementById("showError").innerHTML = "<ul><li>Please select steady state start date</li></ul>";
				return false;
			}
			if(document.getElementById("enddate").value == '')
			{
				document.getElementById("showError").innerHTML = "<ul><li>Please select steady state end date</li></ul>";
				return false;
			}
		}
		setTab('',false);
	}
	
	function setTab(index,flag){
		hideDiv();
		if(flag){
			hideDiv();
			currentSelectedTab=index;
		}else{
			hideDiv();
			
			document.getElementById('t' + (currentSelectedTab+1)).className = 'selected';
			document.getElementById('t' + (currentSelectedTab)).className = 'usual';
			
			document.getElementById('tab' + (currentSelectedTab)).style.display = "none";
			document.getElementById('tab' + (currentSelectedTab+1)).style.display = "block";
			
			currentSelectedTab = currentSelectedTab+1;			
		}
	}
	
	function setPreviousTab(index,flag){
		hideDiv();
		if(flag){
			hideDiv();
			currentSelectedTab=index;
		}else{
			hideDiv();
			document.getElementById('t' + (currentSelectedTab-1)).className = 'selected';
			document.getElementById('t' + (currentSelectedTab)).className = 'usual';
			
			document.getElementById('tab' + (currentSelectedTab)).style.display = "none";
			document.getElementById('tab' + (currentSelectedTab-1)).style.display = "block";
			
			currentSelectedTab = currentSelectedTab-1;			
		}
	}
	
	function nextForm()
	{
		
		document.forms[0].action = "/ADM_PRE/opportunity/saveOpportunity?next=next";
		document.forms[0].submit();
	}
	
	
	window.onload = hideDiv;
	function hideDiv(){
		$("#messageDiv").text('');
		$("#showError").text('');
		document.getElementById("showError").style.display = 'none';
		document.getElementById("messageBox1").style.display = 'none';
		document.getElementById("messageDiv").style.display = 'none';
	}
	
	function clearForm(){

		document.forms[0].reset();
	
	}

</script>

<style>
#messageBox1{
	display: none
}
</style>

	<div id="messageBox1" class="errorMessage">
		<ul></ul>
	</div>
	<div id="messageDiv" class="message">
	
	</div>
	<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
	-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;">
		
	</div>
	<div id="bodyDiv1" class="mainBodyDiv1"
		style="width: 100%; mafloat: left; margin-bottom: 5px;">

		<h1 class="tdHeaderLabel" style="margin-top: 0px; margin-bottom: 0px;">Create Opportunity Details</h1>
	</div>
<form:form id= "form1" method="POST" commandName="opportunityDetailDTO" action="/ADM_PRE/opportunity/saveOpportunity">
<form:hidden  path="opportunityDetailId" value="${opportunityDetailDTO.opportunityDetailId}"></form:hidden>
	<div id="usual1" class="usual">
            <ul>
              <li><a class="" href="#tab1" id="t1" onclick="setTab(1,true);"><span></span>Opportunity Details<sup style="color: red" >*</sup></a></li>
              <li><a class="" href="#tab2" id="t2" onclick="setTab(2,true);"><span></span>Customer & Competitor</a></li>
              <li><a class="" href="#tab3" id="t3" onclick="setTab(3,true);"><span></span>Location Profile</a></li>
              <li><a class="" href="#tab4" id="t4" onclick="setTab(4,true);"><span></span>Project Delivery<sup style="color: red" >*</sup></a></li>
              <li><a class="" href="#tab5" id="t5" onclick="setTab(5,true);"><span></span>Ericsson Personnel<sup style="color: red" >*</sup></a></li>
            </ul>
            
<!---------------------------------------Tab 1 (Oppurtunity Details DIV Start from Here)--------------------------------------------->

            <div style="display: block;" id="tab1" class="tabBodyArea" >
            	<jsp:include page="OpportunityDetails.jsp"></jsp:include>
            </div>
             
 <!---------------------------------------Tab 1 (Oppurtunity Details DIV END from Here)--------------------------------------------->
           
 <!---------------------------------------Tab 2 (Location Profile DIV Start from Here)--------------------------------------------->           
           
              <div style="display: none;" id="tab2" class="tabBodyArea">
            	<jsp:include page="CustomerCompetitiveInformation.jsp"></jsp:include>
            </div>
            
  <!---------------------------------------Tab 2 (Location Profile DIV END from Here)--------------------------------------------->    
 
  <!---------------------------------------Tab 3 (Customer and Competitive Information DIV Start from Here)--------------------------------------------->             
           <div style="display: none;" id="tab3" class="tabBodyArea">
            	<jsp:include page="LocationProfile.jsp"></jsp:include>
            </div>
            
<!---------------------------------------Tab 3 (Customer and Competitive Information DIV End from Here)------------------------------------------->              

<!-------------------------------------------Tab 4 (Project Delivery Time Lines DIV Start from Here)---------------------------------------------->  
            <div style="display: none;" id="tab4" class="tabBodyArea">
           		<jsp:include page="ProjectDelivery.jsp"></jsp:include>
            </div>
<!-------------------------------------------Tab 4 (Project Delivery Time Lines DIV End from Here)---------------------------------------------->  
            
<!-------------------------------------------Tab 5 (Ericsson Personnel DIV Start from Here)---------------------------------------------->  
          
            <div style="display: none;" id="tab5" class="tabBodyArea">
            	<jsp:include page="EricssonPersonnel.jsp"></jsp:include>
            </div>
          
<!-------------------------------------------Tab 5 (Ericsson Personnel DIV Start from Here)---------------------------------------------->  
  </div>
</form:form>
          
<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>


