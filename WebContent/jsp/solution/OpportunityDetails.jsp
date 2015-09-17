<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">

	$(document).ready(function() {
		
		/* $("#opportunityowner").focusout(function() {
			document.getElementById("messageDiv").style.display = 'none';
			 var admssolutionRepresentativeLen = $("#opportunityowner").val().length;
			 if(admssolutionRepresentativeLen == 0){
				 document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>Please enter Signum Id to Search for Opportunity Owner</li></ul>";
					return;
				 } 
						
	            $.ajax({
	                url : "/ADM_PRE/opportunity/searchLdap",
	                type : "post",
	                cache: false,
	                async:false,
	                data : "searchUserVar="+$("#opportunityowner").val(),
	                success : function(resp) {
						if(resp.length == 0){
							 document.getElementById("showError").style.display = 'block';
							 document.getElementById("showError").innerHTML = "<ul><li>Please enter Valid Signum Id to Search for Opportunity Owner</li></ul>";
							 document.getElementById("opportunityowner").value ='';
							 return;
							}else {
								 $('#opportunityowner').val(resp);
								} 
	                }, 
	            });
	        }); */
		
		$('#saveForm1').click(function() {
				if(validateOpportunityDetails()){
					/* if(!myValidate()){
						document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please enter Steady State Dates under Project Delivery Tab</li></ul>";
						return false;
					}
					else
					{*/
						$('#form1').submit();
						document.getElementById("showError").style.display = 'none';
					/*}*/
			}
		    });
		
		$('#form1').validate({
		
			errorContainer : "#showError",
			errorLabelContainer : "#showError",
			wrapper : "li",
			focusInvalid : true,
			rules : {
				existingCustomer: {
					
					},
				newBusiness: {
					
					},
				cadenceId : {
					/* required:true,
					 */maxlength : 50,
					cadRegex : true
					 },
				opportunityOwner : {
					/* required:true,
					 */maxlength : 50
				}
			},
			messages : {
				existingCustomer: {
					},
				newBusiness: {
					},
				cadenceId : {
					maxlength:"<ul><li>CRM 360 ID should be less than 50 characters</li></ul>"
				},
				opportunityOwner : {
					maxlength:"<ul><li>Opportunity Owner should be less than 50 characters</li></ul>"
				}
			}
		});
		
		$.validator.addMethod("cadRegex", function(value, element) {
	        return this.optional(element) || /^[a-z0-9\-]+$/i.test(value);
	    }, "<ul><li>Cadence Id must contain only letters, numbers, or dashes</li></ul>");
		
	});

	
	
	function validateOpportunityDetails()
	{
		var flag = true;
		
		document.getElementById("showError").innerHTML = '';
		document.getElementById("showError").style.display = 'block';
		var sd = document.getElementById("opportunityowner").value;
		var ed = document.getElementById("cId").value;
		var sd1 = document.getElementById("ec").value;
		var ed1 = document.getElementById("nb").value;
		var messageError = "<ul>";
		
		if(sd == "")
		{
			messageError += "<li>"+"Enter opportunity owner"+"</li>";
			flag = false;
		 }
		if(ed <= 0){
			document.getElementById("cId").value="";
			messageError += "<li>"+"Enter CRM 360 ID "+"</li>";
			flag = false;
		}
		
		if(sd1 == "")
			{
			messageError += "<li>"+"Enter existing customer" +"</li>";			
			flag = false;
			}
		
		if(ed1 == "")
		{
			messageError += "<li>"+"Enter new business"+"</li>";
			flag = false;
		}
		document.getElementById("showError").innerHTML = messageError+"</ul>";
		return flag;
	}
	
	
	
	function goTopreviousPage(){
		document.forms[0].action = "/ADM_PRE/opportunity/landingPage";
		document.forms[0].submit();
	}

	
	function nextForm()
	{
		
		document.forms[0].action = "/ADM_PRE/opportunity/saveOpportunity?next=next";
		document.forms[0].submit();
	}
	/* function newPopup(id) {
		alert(id);
		var popupWindow = window.open("/ADM_PRE/opportunity/searchOpportunityOwner?param="+id, '', 'height=300,width=1200,left=75,top=200,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no,status=no');
		//for Region KAM
		document.getElementById("rk").value = document.getElementById("opportunityowner").value; 
	} */
	
</script>

         <div class="inputRow3rd" style="width: 300px;">
                	<h3>Oppurtunity Name :</h3>
                    <span style="width: 160px;">
                    	<form:input path="opportunityDTO.opportunityName" 
                    		value="${opportunityDTO.opportunityName}" 
                    		readonly="true" 
                    		name="" 
                    		type="text" 
                    		class="inputtextTabs"/>
                    </span>
          </div>
			<div class="inputRow3rd" style="margin-left: 7px;">
                	<!-- <h3>Opportunity Owner :</h3> -->
                	<div class="TabsCommonButtons" style="width: 120px;"  onclick="newPopup('opportunityowner')">Choose Opp. Owner</div>
                    <span style="width: 200px;">
                    	<form:input id="opportunityowner" path="opportunityOwner"
                    		value="${OpportunityDetail.opportunityOwner}" 
                    		readonly="true" 
                     		type="text" class="inputtextTabs"
                     		placeholder="Opportunity Owner"
                     		onblur="javascript:cleanDiv(this.id);"/>
                     </span>
                     
             </div>

            	<div class="inputRow3rd" style="margin-left: 22px;">
                	<h3>CRM 360 ID :</h3>
                    <span>
                    	<form:input id="cId" 
                    		readonly="${opportunityDetailDTO.createdBy}" 
                    		path="cadenceId" 
                    		value="${OpportunityDetail.cadenceId}" 
                    		type="text" 
                    		class="inputtextTabs"/>
                    </span>
                </div>
                
            	<div class="inputRow3rd">
                	<h3>Customer Name :</h3>
                    <span><form:input id="customername" readonly="true" path="opportunityDTO.customerDTO.customerName" value="${OpportunityDetail.opportunityDTO.customerDTO.customerName}" name="" type="text" class="inputtextTabs" /></span>
                </div>            
                
            	<div class="inputRow3rd">
                	<h3>Existing Customer :</h3>
                    <span>
                    	<form:select id="ec" path="existingCustomer" disabled="${opportunityDetailDTO.createdBy}" 
									class="inputtextTabs">
										<form:option value="" label="Select" />
										<form:option value="Y" label="Yes" />
										<form:option value="N" label="No" />
						</form:select>
                   	</span>
                </div>
                
            	<div class="inputRow3rd">
                	<h3>New Business :</h3>
                    <span>
                    <form:select id="nb" path="newBusiness" disabled="${opportunityDetailDTO.createdBy}" 
									class="inputtextTabs">
										<form:option value="" label="Select" />
										<form:option value="Y" label="Yes" />
										<form:option value="N" label="No" />
					</form:select>
                    </span>
                </div>
                
				<%-- <div class="inputRow3rd">
                	<h3>Vertical Name :</h3>
                    <span>
                       	
                    		<form:select  id="vn" path="vertical" class="inputtextTabs" disabled="${opportunityDetailDTO.createdBy }">
										<form:option value="" label="--- Select ---"/>
										<form:options items="${verticalList}" />
								</form:select>	
                    	
                    	</td>
                    
                    </span>
                </div> --%>
               
	                <div class="tabsButtonPanel">
	                	<div class="TabsCommonButtons" onclick="javascript:goTopreviousPage();">Previous</div>
	                	 <c:if test="${!(opportunityDetailDTO.createdBy)}">
	                		<c:if test="${role_guest != 'true'}">
		                		<div class="TabsCommonButtons" id="rset" onclick="javascript:clearForm();">Reset</div>
		                		<div class="TabsCommonButtons" id="saveForm1">Save</div>
	                   		</c:if>
	                   	</c:if>
	                    <div class="TabsCommonButtons" onclick="javascript:nextPage('oppdetails');">Next</div>
	                </div>
				



