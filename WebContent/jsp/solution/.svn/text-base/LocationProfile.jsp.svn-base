
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	$(document).ready(function() {
		
		$('#saveForm2').click(function() {
			if(validateOpportunityDetails()){
				var gscVal = $("#gsc1").val().length;
				if(gscVal == 0 || gscVal == ''){
					document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>Please fill Rmeote Location 1</li></ul>";
					return; 
				}
		    	$('#form1').submit();
		
			}
			else{
				document.getElementById("showError").style.display = 'block';
				document.getElementById("showError").innerHTML = "<ul><li>Please fill all the mandatory fields of Opportunity Details Tab</li></ul>";
			}
		    });
		
		$("#myReset").click(function(){
            document.forms[0].reset();
            populate(document.getElementById("selectNearShore"),1);
            populate(document.getElementById("gsc1"),2);
            populate(document.getElementById("gsc2"),3);
		});
		
	});

	function populate(e, f) {
		$.ajax({
			type : "POST",
			url : "./populateCountryTable",
			data : {"co": $("#"+e.id).val()},
			datatype : "text",
			 success : function(response){
				var parts = response.split('|');
				for ( var i = 0; i < parts.length; i++) {
					$("#currency_" + f).html(parts[i]);
					i++;
					$("#currencyCode_" + f).html(parts[i]);
					i++;
					$("#timeZone_" + f).html(parts[i]);
				}
			},
			error : function(t) {
					$("#currency_" + f).html('');
					$("#currencyCode_" + f).html('');
					$("#timeZone_" + f).html('');
			}
		});
	}



</script>

	      	<div class="tab2LeftPanel">
        	<div class="inputRowFirst">
        	<h3>Client Country </h3>
                <span>
                	<form:hidden path="opportunityLocationsDTO.customerCountry" value="${opportunityDetailDTO.opportunityDTO.customerDTO.countryDTO.countryId}"></form:hidden>
                    <input readonly="true" value="${opportunityDetailDTO.opportunityDTO.customerDTO.countryDTO.countryName}" name="" type="text" class="inputtextTabs" />
                </span>
			</div>
			
			<div style="clear:both;"></div>
			<div class="inputRowFirst">
            	<h3>Near Shore </h3>
                <span>
		            <form:select 
						path="opportunityLocationsDTO.nearShore" id="selectNearShore" disabled="${opportunityDetailDTO.createdBy}"
						onchange="populate(this,'1');" class="inputtextTabs">
						<form:option value="" label="--Select--" />
						<form:options items="${countries}" itemValue="countryId" itemLabel="countryName" />
					</form:select>
                </span>
            </div>
			<div style="clear:both;"></div>
				<div class="inputRowFirst">
                	<h3>Remote Location 1 <sup style="color: red" >*</sup></h3>
                    <span>
	                    <form:select
							path="opportunityLocationsDTO.gsc1" id="gsc1" disabled="${opportunityDetailDTO.createdBy}"
							onchange="populate(this,'2');" class="inputtextTabs">
							<form:option value="" label="--Select--" />
							<form:options items="${gscCountries}" itemValue="countryId" itemLabel="countryName" />
						
						</form:select>
                    </span>	
                </div>                     
				<div style="clear:both;"></div>
				<div class="inputRowFirst">
                	<h3>Remote Location 2</h3>
                    <span>
                    	<form:select
							path="opportunityLocationsDTO.gsc2" id="gsc2" disabled="${opportunityDetailDTO.createdBy}"
							onchange="populate(this,'3');" class="inputtextTabs">
							<form:option value="" label="--Select--" />
							<form:options items="${countries}" itemValue="countryId" itemLabel="countryName" />
						</form:select>
                    </span>
                </div> 
		</div>
        <div  class="tab2RightPanel">
            	<div class="loationPanelDinamicText">
                	<div class="dinamicText"><b><em>Currency</em></b></div>
                    <div class="dinamicText"><b><em>Currency Code</em></b></div>
                    <div class="dinamicText"><b><em>Time Zone</em></b></div>  	
                </div>
				<div  class="loationPanelDinamicText">
                	<div id="currency_0" class="dinamicText">${opportunityDetailDTO.opportunityDTO.customerDTO.countryDTO.currencyName}</div>
                	<div id="currencyCode_0" class="dinamicText">${opportunityDetailDTO.opportunityDTO.customerDTO.countryDTO.currencyCode}</div>
                    <div id="timeZone_0" class="dinamicText">${opportunityDetailDTO.opportunityDTO.customerDTO.countryDTO.timeZone}</div>
                </div>
                <div style="clear:both;"></div>
                <div class="loationPanelDinamicText">
                	<div id="currency_1" class="dinamicText">${nearShore.currencyName}</div>
                	<div id="currencyCode_1" class="dinamicText">${nearShore.currencyCode }</div>
                    <div id="timeZone_1" class="dinamicText">${nearShore.timeZone }</div>
                </div>
                <div style="clear:both;"></div>
                <div class="loationPanelDinamicText">
                	<div id="currency_2" class="dinamicText">${gsc1.currencyName}</div>
                	<div id="currencyCode_2" class="dinamicText">${gsc1.currencyCode }</div>
                    <div id="timeZone_2" class="dinamicText">${gsc1.timeZone }</div>
                </div>
                <div class="loationPanelDinamicText">
                	<div id="currency_3" class="dinamicText">${gsc2.currencyName}</div>
                	<div id="currencyCode_3" class="dinamicText">${gsc2.currencyCode }</div>
                    <div id="timeZone_3" class="dinamicText">${gsc2.timeZone }</div>
                </div>
		</div>
		
	    <div class="tabsButtonPanel">
	    <div class="TabsCommonButtons" onclick="javascript:previousPage();">Previous</div>
	    	<c:if test="${!(opportunityDetailDTO.createdBy)}">
	    	<c:if test="${role_guest != 'true'}">
	    	<div class="TabsCommonButtons" id="myReset">Reset</div>
	    	<div class="TabsCommonButtons" id="saveForm2">Save</div>
	    	</c:if>
	    	</c:if>
	    	<div class="TabsCommonButtons" onclick="javascript:nextPage();">Next</div>              
	    </div>    
	    
