<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">

function nextForm()
{
	document.forms[0].action = "/ADM_PRE/opportunity/defineScope";
	document.forms[0].submit();
}

function searchLdap()
{
	var progressIndicator = true;
	var signumId = document.getElementById("accountCommercialResponsible").value;
	if (signumId == ""){
		progressIndicator = false;
		document.getElementById("messageDiv").style.display = "block";
		document.getElementById("messageDiv").innerHTML = "Please provide atleast one field to search.";
		
	}else{
		progressIndicator = true;
		document.forms[0].action = "/ADM_PRE/opportunity/searchLdap";
		document.forms[0].submit();
	}
}
	function validateRK(){
		var r = document.getElementById("rk").value;
		if(r.length > 50){
			document.getElementById("messageDiv").style.display = 'none';
			document.getElementById("showError").style.display = 'block';
			document.getElementById("showError").innerHTML = "<ul><li>Length of Region KAM should not exceed 50</li></ul>";
		return false;
		}
		return true;
	}
	var action='';
	$(document).ready(function() {

			 $("#customerSolutionResponsible").focusout(function() {
				 document.getElementById("messageDiv").style.display = 'none';
			var customerSolutionResponsibleLen = $("#customerSolutionResponsible").val().length;
			 if(customerSolutionResponsibleLen == 0){
				 /* document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>Please enter Signum Id to Search for CSR</li></ul>";
					*/return; 
				 }
						
	            $.ajax({
	                url : "/ADM_PRE/opportunity/searchLdap",
	                type : "post",
	                cache: false,
	                async:false,
	                data : "searchUserVar="+$("#customerSolutionResponsible").val(),
	                success : function(resp) {
						if(resp.length == 0){
							 document.getElementById("showError").style.display = 'block';
							 document.getElementById("showError").innerHTML = "<ul><li>Please enter Valid Signum Id to Search for CSR</li></ul>";
							 document.getElementById("customerSolutionResponsible").value ='';	
							 return;
							}else {
								 $('#customerSolutionResponsible').val(resp);
								} 
	                },
	            });
	        });


			 $("#accountCommercialResponsible").focusout(function() {
				 document.getElementById("messageDiv").style.display = 'none';
				var actCommerResponsibleLen = $("#accountCommercialResponsible").val().length;
				 if(actCommerResponsibleLen == 0){
					/* document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please enter Signum Id to Search  for ACR</li></ul>"; */
						return;
					 }
							
		            $.ajax({
		                url : "/ADM_PRE/opportunity/searchLdap",
		                type : "post",
		                cache: false,
		                async:false,
		                data : "searchUserVar="+$("#accountCommercialResponsible").val(),
		                success : function(resp) {
							if(resp.length == 0){
								 document.getElementById("showError").style.display = 'block';
								 document.getElementById("showError").innerHTML = "<ul><li>Please enter Valid Signum Id to Search for ACR</li></ul>";
								 document.getElementById("accountCommercialResponsible").value ='';	
								 return;
								}else {
									 $('#accountCommercialResponsible').val(resp);
									} 
		                }, 
		            });
		        });

			 $("#customerFulfillmentResponsible").focusout(function() {
				 document.getElementById("messageDiv").style.display = 'none';
				 var customerFulfillmentResponsibleLen = $("#customerFulfillmentResponsible").val().length;
				 if(customerFulfillmentResponsibleLen == 0){
					/*  document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please enter Signum Id to Search for CFR</li></ul>";
						 */return;
					 } 
							
		            $.ajax({
		                url : "/ADM_PRE/opportunity/searchLdap",
		                type : "post",
		                cache: false,
		                async:false,
		                data : "searchUserVar="+$("#customerFulfillmentResponsible").val(),
		                success : function(resp) {
							if(resp.length == 0){
								 document.getElementById("showError").style.display = 'block';
								 document.getElementById("showError").innerHTML = "<ul><li>Please enter Valid Signum Id to Search for CFR</li></ul>";
								 document.getElementById("customerFulfillmentResponsible").value ='';	
								 return;
								}else {
									 $('#customerFulfillmentResponsible').val(resp);
									} 
		                }, 
		            });
		        });

			 $("#primaryVerticalRepresentative").focusout(function() {
				 document.getElementById("messageDiv").style.display = 'none';
				 var primaryVerticalRepresentativeLen = $("#primaryVerticalRepresentative").val().length;
				 if(primaryVerticalRepresentativeLen == 0){
					 document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please enter Signum Id to Search for Primary Solution Manager</li></ul>";
						return;
					 } 
							
		            $.ajax({
		                url : "/ADM_PRE/opportunity/searchLdap",
		                type : "post",
		                cache: false,
		                async:false,
		                data : "searchUserVar="+$("#primaryVerticalRepresentative").val(),
		                success : function(resp) {
							if(resp.length == 0){
								 document.getElementById("showError").style.display = 'block';
								 document.getElementById("showError").innerHTML = "<ul><li>Please enter Valid Signum Id to Search for Primary Solution Manager</li></ul>";
								 document.getElementById("primaryVerticalRepresentative").value ='';	
								 return;
								}else {
									 $('#primaryVerticalRepresentative').val(resp);
									} 
		                }, 
		            });
		        });

			 $("#admssolutionRepresentative").focusout(function() {
				 document.getElementById("messageDiv").style.display = 'none';
				 var admssolutionRepresentativeLen = $("#admssolutionRepresentative").val().length;
				 if(admssolutionRepresentativeLen == 0){
					 document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please enter Signum Id to Search for Secondary Solution SME</li></ul>";
						return;
					 } 
							
		            $.ajax({
		                url : "/ADM_PRE/opportunity/searchLdap",
		                type : "post",
		                cache: false,
		                async:false,
		                data : "searchUserVar="+$("#admssolutionRepresentative").val(),
		                success : function(resp) {
							if(resp.length == 0){
								 document.getElementById("showError").style.display = 'block';
								 document.getElementById("showError").innerHTML = "<ul><li>Please enter Valid Signum Id to Search for Secondary Solution SME</li></ul>";
								 document.getElementById("admssolutionRepresentative").value ='';	
								 return;
								}else {
									 $('#admssolutionRepresentative').val(resp);
									} 
		                }, 
		            });
		        });

			 $("#tertiarySolutionSME").focusout(function() {
				 document.getElementById("messageDiv").style.display = 'none';
				 var tertiarySolutionSMELen = $("#tertiarySolutionSME").val().length;
				 if(tertiarySolutionSMELen == 0){
					/*  document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please enter Signum Id to Search for Tertiary Solution SME</li></ul>";
					 */	return;
					 } 
							
		            $.ajax({
		                url : "/ADM_PRE/opportunity/searchLdap",
		                type : "post",
		                cache: false,
		                async:false,
		                data : "searchUserVar="+$("#tertiarySolutionSME").val(),
		                success : function(resp) {
							if(resp.length == 0){
								 document.getElementById("showError").style.display = 'block';
								 document.getElementById("showError").innerHTML = "<ul><li>Please enter Valid Signum Id to Search for Tertiary Solution SME</li></ul>";
								 document.getElementById("tertiarySolutionSME").value ='';	
								 return;
								}else {
									 $('#tertiarySolutionSME').val(resp);
									} 
		                }, 
		            });
		        });
		
		
		$('#saveForm5').click(function() {
			var ed = document.getElementById("primaryVerticalRepresentative").value;
			var sd = document.getElementById("admssolutionRepresentative").value;
			if(!validateRK()){
				return false;
			}
			if(!validateFields()){
				document.getElementById("showError").style.display = 'block';
				document.getElementById("showError").innerHTML = "<ul><li>Please enter numeric value greater than 0 for "+errorMessage+" under Customer and Competitive Information tab</li></ul>";
				return false;
			}
			if(ed != "" && sd != ""){
					if(validateOpportunityDetails()){
						if(myValidate())
							{
				    	$('#form1').submit();
					}else{
						document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please fill all the mandatory fields of Project Delivery Tab</li></ul>";
						}
					}
					else{
						document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please fill all the mandatory fields of Opportunity Details Tab</li></ul>";
					}
			}else{
				if(ed == ""){
					document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>Please enter Primary Solution Manager</li></ul>";
				}
				if(sd == ""){
					document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>Please enter Secondary Solution SME</li></ul>";
				}
				return false;
			}
		    });
		
		$('#nextForm5').click(function() {
			var ed = document.getElementById("primaryVerticalRepresentative").value;
			var sd = document.getElementById("admssolutionRepresentative").value;
			var r = document.getElementById("rk").value;
			if(r.length > 50){
				document.getElementById("showError").style.display = 'block';
				document.getElementById("showError").innerHTML = "<ul><li>Length of Region KAM should not exceed 50</li></ul>";
			return false;
			}
			if(!validateFields()){
				document.getElementById("showError").style.display = 'block';
				document.getElementById("showError").innerHTML = "<ul><li>Please enter numeric value greater than 0 for "+errorMessage+" under Customer and Competitive Information tab</li></ul>";
				return false;
			}
			if(ed != "" && sd != ""){
					if(validateOpportunityDetails()){
						if(myValidate())
							{
							action='next';
					    	$('#form1').submit();
					}else{
						document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please fill all the mandatory fields of Project Delivery Tab</li></ul>";
						}
					}
					else{
						document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please fill all the mandatory fields of Opportunity Details Tab</li></ul>";
					}
			}else{
				if(ed == ""){
					document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>Please select primary solution manager</li></ul>";
				}
				if(sd == ""){
					document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>Please select secondary solution SME</li></ul>";
				}
				return false;
			}
		    });
		
		$('#nextForm5_rg').click(function() {
			
			
				action = 'roleGuest';
				$('#form1').submit();
				
		    });
		
	});

	
	
	function newPopup(id) {
		var strWindowFeatures = "menubar=no,location=no,addressbar=no,scrollbars=no,status=yes,height=350,width=1112,left=160,top=185";
		popupWindow = window.open("/ADM_PRE/opportunity/searchOpportunityOwner?param="+id, '', strWindowFeatures);
	}

</script>

<%-- "${opportunityDetailDTO.createdBy}" for rkam readonly--%>

<div class="tab5HeaderPanel">Core 3 Team</div>
            	<div class="tab5EricssonPanel" style="width: 100%;">
                	<h3 style="margin-left: 370px;width: 82px;">Region KAM :</h3>
                    <span><form:input  id="rk"
                    style="width: 200px;height: 25px;text-align: center;"
                    readonly='true'
                    path="regionKam" value="${OpportunityDetailDTO.opportunityOwner}" type="text" class="textBoxSmallNumric"  /></span>
                </div>
                <div style="clear:both;"></div>
				<div class="tab5EricssonPanel">
                	<!-- <h3>Customer Solution Responsible (CSR) :</h3> -->
                	<div class="TabsCommonButtons" style="width: 90px;margin-left: 11px;" onclick="newPopup('customerSolutionResponsible')">Choose CSR</div>
                    <span >
                    	<form:input type="text" 
                    		path="customerSolutionResponsible" 
                    		readonly="true" 
                    		placeholder="Customer Solution Responsible"
							id="customerSolutionResponsible" 
							class="textBoxSmallNumric" 
							style="width: 200px;height: 25px;text-align: center;"/>
                    </span>
                </div>

				<div class="tab5EricssonPanel">
                	<!-- <h3>Account Commercial Responsible (ACR) :</h3> -->
                	<div class="TabsCommonButtons" style="width: 90px;" onclick="newPopup('accountCommercialResponsible')">Choose ACR</div>
                	<span>
                		<form:input type="text" 
                			path="accountCommercialResponsible" 
                			placeholder="Account Commercial Responsible"
							id="accountCommercialResponsible" 
							class="textBoxSmallNumric" 
							readonly="true"
							style="width: 200px;height: 25px;text-align: center;" />
                	</span>
                </div>

				<div class="tab5EricssonPanel">
                	<!-- <h3>Customer Fulfillment Responsible (CFR) :</h3> -->
                	<div class="TabsCommonButtons" style="width: 90px;" onclick="newPopup('customerFulfillmentResponsible')">Choose CFR</div>
                    <span>
                    	<form:input type="text" 
                    		path="customerFulfillmentResponsible" 
                    		placeholder="Customer Fulfillment Responsible"
							id="customerFulfillmentResponsible" 
							class="textBoxSmallNumric" 
							readonly="true"
							style="width: 200px;height: 25px;text-align: center;" />
                    </span>
                </div>                                     
        
            	<div class="tab5HeaderPanel">Solution Management Team </div>    
                
                
                <div class="tab5EricssonPanel">
                	<!-- <h3>Primary Solution Manager<sup style="color: red" >*</sup> :</h3> -->
                	<div class="TabsCommonButtons" style="width: 90px;margin-left: 11px;" onclick="newPopup('primaryVerticalRepresentative')">Choose PSM</div>
                    <span>
                    	<form:input type="text" 
                    		path="primaryVerticalRepresentative" 
                    		placeholder="Primary Solution Manager"
							id="primaryVerticalRepresentative" 
							class="textBoxSmallNumric" 
							readonly="true"
							style="width: 200px;height: 25px;text-align: center;" />
						</span>
                </div>

				<div class="tab5EricssonPanel">
                	<!-- <h3>Secondary Solution SME<sup style="color: red" >*</sup> :</h3> -->
                	<div class="TabsCommonButtons" style="width: 90px;" onclick="newPopup('admssolutionRepresentative')">Sec. SME</div>
                    <span>
                    	<form:input type="text" 
                    		path="admssolutionRepresentative" 
                    		placeholder="Secondary Solution SME"
							id="admssolutionRepresentative" 
							class="textBoxSmallNumric" 
							readonly="true"
							style="width: 200px;height: 25px;text-align: center;" />
						</span>
                </div>
                
                <div class="tab5EricssonPanel">
                	<!-- <h3>Tertiary Solution SME</h3> -->
                	<div class="TabsCommonButtons" style="width: 90px;" onclick="newPopup('tertiarySolutionSME')">Ter. SME</div>
                    <span>
                    	<form:input type="text" 
                    		path="tertiarySolutionSME" 
                    		placeholder="Tertiary Solution SME"
							id="tertiarySolutionSME" 
							class="textBoxSmallNumric"
							readonly="true"
							style="width: 200px;height: 25px;text-align: center;" />
						</span>
                </div>                                     



			<div class="tabsButtonPanel">
					<div class="TabsCommonButtons" onclick="javascript:previousPage()">Previous</div>
					<c:choose>
						<c:when test="${!(opportunityDetailDTO.createdBy)}">
							<c:choose>
								<c:when test="${role_guest != 'true'}">
							
									<div class="TabsCommonButtons" onclick="javascript:clearForm()">Reset</div>
			                		<div class="TabsCommonButtons" id="saveForm5">Save</div>
			                		<div class="TabsCommonButtons" id="nextForm5">Next</div>
		                    
		                    	</c:when>
		            			<c:otherwise>
		            				<div class="TabsCommonButtons" id="nextForm5">Next</div>
		            			</c:otherwise>
	            			</c:choose>
                    	</c:when>
                   		<c:otherwise>
	                    	<a href="./defineScope">
								<div class="TabsCommonButtons">Next</div>
							</a>
						</c:otherwise>
                    </c:choose>
                                 
                </div>
