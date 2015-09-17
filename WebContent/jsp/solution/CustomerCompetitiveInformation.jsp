<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
var errorMessage = "";
$(document).ready(function() {
	
	
	if("${opportunityDetailDTO.rfpreason}" == 'five'){
			$("#other").show();
	}else{
		$("#other").hide();
		
	}
	
	$('#saveForm3').click(function() {
		if(validateOpportunityDetails()){
			if(validateFields()){
					$('#form1').submit();
					}
					else
						{
						document.getElementById("messageDiv").style.display = 'none';
						document.getElementById("showError").style.display = 'block';
						if(errorMessage == "length"){
							document.getElementById("showError").innerHTML = "<ul><li>Length of field Competitors should not exceed 255</li></ul>";
							
						}else if(errorMessage == "length1"){
							document.getElementById("showError").innerHTML = "<ul><li>Length of field Primary Business Line should not exceed 50</li></ul>";
						}
						else{
							document.getElementById("messageDiv").style.display = 'none';
							document.getElementById("showError").style.display = 'block';
							document.getElementById("showError").innerHTML = "<ul><li>Please enter numeric value greater than 0 for "+errorMessage+"</li></ul>";
						}
						}
		}
		else{
			document.getElementById("showError").style.display = 'block';
			document.getElementById("showError").innerHTML = "<ul><li>Please fill all the mandatory fields of Opportunity Details Tab</li></ul>";
			
		}
		
		});
});
	
	function validateFields(){
		var flag = true;
		var a = document.getElementById("cs").value;
		var b = document.getElementById("os").value;
		var c = document.getElementById("ef").value;
		var d = document.getElementById("to").value;
		var e = document.getElementById("comp").value;
		var f = document.getElementById("pb").value;
		
		if(a != "") 
			if(isNaN(a)){
			 errorMessage = "capex spend";	
					return false;
			}
			else if(a < 0)
			{
				errorMessage = "capex spend";
			flag = false;	
		}
		if(b != "")
			if(isNaN(b))
				{
				errorMessage = "opex spend";
				return false;
				}
			else if(b < 0){
				errorMessage = "opex spend";
			flag = false;
		}
		if(c != "")
			if(isNaN(c)){
				errorMessage = "fte";
				return false;
			}
			else if(c < 0 ){
				errorMessage = "fte";
			flag = false;	
		}
		if(d != "")
			if(isNaN(d)){
				errorMessage = "turn over";
				return false;
			}
			else if(d < 0){
				errorMessage = "turn over";
			flag = false;
		}
		
		if(e.length > 255){
			errorMessage = "length";
			flag = false;
			}
		if(f.length > 50){
			errorMessage = "length1";
			flag = false;
			}
		
		return flag;
	}

	
	
	function onReasonChange(obj){
		
		if(obj.value == 'five'){
			$("#other").show();
		}else{
			$("#other").hide();
		}
		
	}
	
		


</script>
<div class="inputRow3rd">
                	<h3>Competitors  :</h3>
                    <span><form:input id="comp"
                     readonly="${opportunityDetailDTO.createdBy}" path="competitors"
                      value="${OpportunityDetail.competitors}" name="" type="text" class="inputtextTabs" /></span>
                </div>

            	<div class="inputRow3rd">
                	<h3>Capex Spend <i><font size="1" color="#09AE53">[usd]:</h3>
                    <span><form:input id="cs" readonly="${opportunityDetailDTO.createdBy}" 
                    path="capexSpend" type="text" class="inputtextTabs"
                    /></span>
                </div>

            	<div class="inputRow3rd">
                	<h3>Opex Spend <i><font size="1" color="#09AE53">[usd]:</h3>
                    <span><form:input id="os" readonly="${opportunityDetailDTO.createdBy}" 
                    path="opexSpend" name="" type="text" class="inputtextTabs" 
                    /></span>
                </div>

            	<div class="inputRow3rd">
                	<h3>Existing FTE</h3>
                    <span><form:input id="ef" readonly="${opportunityDetailDTO.createdBy}" 
                    path="existingFte" name="" type="text" class="inputtextTabs"
                    /></span>
                </div>

            	<div class="inputRow3rd">
                	<h3>Deal Value <i><font size="1" color="#09AE53">[usd]:</h3>
                    <span><form:input id="to" readonly="${opportunityDetailDTO.createdBy}" 
                    path="turnOver" name="" type="text" class="inputtextTabs" 
                    /></span>
                </div>

            	<div class="inputRow3rd">
                	<h3>Primary Business:</h3>
                    <span><form:input id="pb" 
                    readonly="${opportunityDetailDTO.createdBy}" path="primaryBusinessLine"
                     type="text" class="inputtextTabs" /></span>
                </div>

				<div class="tab3Row">
                	<h3>Reasons for Requesting Services:</h3>
                    <span>
                    	<form:select path="rfpreason" class="inputtextTabs" disabled="${opportunityDetailDTO.createdBy}" onchange="onReasonChange(this);">
										<form:option value="" label="--- Select ---"/>
										<form:options items="${reasons}" />
								</form:select>
                    </span>
                </div>                     

            	<div class="inputRow3rd" id="other" >
                    <span><form:input type="text" path="otherReason" class="inputtextTabs" readonly="${opportunityDetailDTO.createdBy}"/></span>
                </div>
                
                
               <div class="tabsButtonPanel">
                	<div class="TabsCommonButtons" onclick="javascript:previousPage()">Previous</div>
                	<c:if test="${!(opportunityDetailDTO.createdBy)}">
                	<c:if test="${role_guest != 'true'}">
                	<div class="TabsCommonButtons" onclick="javascript:clearForm()">Reset</div>
                	<div class="TabsCommonButtons" id="saveForm3">Save</div>
                	</c:if>
                	</c:if>
                	<div class="TabsCommonButtons" onclick="javascript:nextPage()">Next</div>              
                </div>
                
