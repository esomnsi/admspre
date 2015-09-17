<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<script type="text/javascript">
	$(document).ready(function() {
		
		$("#startdate").change(function(){
			var sd = document.getElementById("startdate").value;
			var ed = document.getElementById("enddate").value;
			
			if(ed == ""){
				
			}
			else{
			month1 = sd.substring (0, sd.indexOf ("/"));
			day1 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
			year1 = sd.substring (sd.lastIndexOf ("/")+1, sd.length);
			
			month2 = ed.substring (0, ed.indexOf ("/"));
			day2 = ed.substring (ed.indexOf ("/")+1, ed.lastIndexOf ("/"));
			year2 = ed.substring (ed.lastIndexOf ("/")+1, ed.length); 
			
			var date1 = year1+"/"+month1+"/"+day1;
			var date2 = year2+"/"+month2+"/"+day2;
			msPerDay = 24 * 60 * 60 * 1000;
			
			var dd1 = Math.round((Date.parse(date2)).valueOf() - (Date.parse(date1)).valueOf())/msPerDay + 1;
			
			if(isNaN(dd1))
			{
				document.getElementById("showError").style.display = 'block';
				document.getElementById("showError").innerHTML = "<ul><li>Please check the date entered for Steady State end date and transformation start date</li></ul>";
				document.getElementById("enddate").value = "";
				document.getElementById("contractDuration").value = "";
				return false;
			}
			if(dd1 <= 0){
				document.getElementById("showError").style.display = 'block';
				document.getElementById("showError").innerHTML = "<ul><li>The steady state end date should be greater than transformation start date</li></ul>";
				document.getElementById("enddate").value = "";
				document.getElementById("contractDuration").value = "";
				return false;
			}
			
			document.getElementById("steadyStateDuration").value = (dd1/30).toFixed();
			var tsd = document.getElementById("Tstartdate").value;
			if(tsd == ""){
				document.getElementById("contractDuration").value = dd1;
			}
			}
		});
		
		
		$("#Tstartdate").change(function(){
			var ed = document.getElementById("enddate").value;
			var tsd = document.getElementById("Tstartdate").value;
			if(ed == ""){
				}
			else if(tsd != ""){
				month1 = tsd.substring (0, tsd.indexOf ("/"));
				day1 = tsd.substring (tsd.indexOf ("/")+1, tsd.lastIndexOf ("/"));
				year1 = tsd.substring (tsd.lastIndexOf ("/")+1, tsd.length);

				month2 = ed.substring (0, ed.indexOf ("/"));
				day2 = ed.substring (ed.indexOf ("/")+1, ed.lastIndexOf ("/"));
				year2 = ed.substring (ed.lastIndexOf ("/")+1, ed.length); 
				
				var date1 = year1+"/"+month1+"/"+day1;
				var date2 = year2+"/"+month2+"/"+day2;
				
				msPerDay = 24 * 60 * 60 * 1000;
				
				var dd1 = Math.round((Date.parse(date2)).valueOf() - (Date.parse(date1)).valueOf())/msPerDay + 1;
				
				if(isNaN(dd1))
				{
					document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>Please check the date entered for Steady State end date and transformation start date</li></ul>";
					document.getElementById("enddate").value = "";
					document.getElementById("contractDuration").value = "";
					return false;
				}
				if(dd1 <= 0){
					document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>The steady state end date should be greater than transformation start date</li></ul>";
					document.getElementById("enddate").value = "";
					document.getElementById("contractDuration").value = "";
					return false;
				}
				
				document.getElementById("contractDuration").value = dd1 ;
			}else if(tsd == ""){
				var sd = document.getElementById("startdate").value;
				var ed = document.getElementById("enddate").value;
				
				month1 = sd.substring (0, sd.indexOf ("/"));
				day1 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
				year1 = sd.substring (sd.lastIndexOf ("/")+1, sd.length);
				
				month2 = ed.substring (0, ed.indexOf ("/"));
				day2 = ed.substring (ed.indexOf ("/")+1, ed.lastIndexOf ("/"));
				year2 = ed.substring (ed.lastIndexOf ("/")+1, ed.length); 
				
				var date1 = year1+"/"+month1+"/"+day1;
				var date2 = year2+"/"+month2+"/"+day2;
				
				var dd = Math.round((Date.parse(date2)).valueOf() - (Date.parse(date1)).valueOf())/msPerDay + 1;
				if(isNaN(dd))
				{
					document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>Please check the date entered for Steady State</li></ul>";
					document.getElementById("startdate").value = "";
					document.getElementById("enddate").value = "";
					document.getElementById("steadyStateDuration").value = "";
					return false;
				}
				if(dd <= 0){
					document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>The steady state end date should be greater than start date</li></ul>";
					document.getElementById("enddate").value = "";
					document.getElementById("steadyStateDuration").value = "";
					return false;
				}
				document.getElementById("contractDuration").value = (dd/30).toFixed();;
			}
			
		});
			
		$('#saveForm4').click(function() {
			if(myValidate())
				{
				var day1, day2,day3;
				var month1, month2,month3;
				var year1, year2,year3;
				var tsd = document.getElementById("Tstartdate").value;
				var ted = document.getElementById("Tenddate").value;
				var sd = document.getElementById("startdate").value;
				
				var gscVal = $("#gsc1").val().length;
				if(gscVal == 0 || gscVal == ''){
					document.getElementById("showError").style.display = 'block';
					document.getElementById("showError").innerHTML = "<ul><li>Please fill Rmeote Location 1</li></ul>";
					return false;
				}
				
				if((tsd != null) && (ted != null)){
					month1 = tsd.substring (0, tsd.indexOf ("/"));
					day1 = tsd.substring (tsd.indexOf ("/")+1, tsd.lastIndexOf ("/"));
					year1 = tsd.substring (tsd.lastIndexOf ("/")+1, tsd.length);
					
					var date1 = year1+"/"+month1+"/"+day1;
					
					month2 = ted.substring (0, ted.indexOf ("/"));
					day2 = ted.substring (ted.indexOf ("/")+1, ted.lastIndexOf ("/"));
					year2 = ted.substring (ted.lastIndexOf ("/")+1, ted.length); 
					
					var date2 = year2+"/"+month2+"/"+day2;
					
					msPerDay = 24 * 60 * 60 * 1000;
					var dd = Math.round((Date.parse(date2)).valueOf() - (Date.parse(date1)).valueOf())/msPerDay + 1;
					if(dd < 35){
						$("#messageDiv").text('');
						document.getElementById("messageDiv").style.display = 'none';
						document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>The difference between Transformation Start and End Dates must be equal or greater than 5 weeks</li></ul>";
						document.getElementById("Tstartdate").value = "";
						document.getElementById("Tenddate").value = "";
						return false;
					}
					month3 = sd.substring (0, sd.indexOf ("/"));
					day3 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
					year3 = sd.substring (sd.lastIndexOf ("/")+1, sd.length);
					var date3 = year3+"/"+month3+"/"+day3;
					
					var d = Math.round((Date.parse(date3)).valueOf() - (Date.parse(date2)).valueOf())/msPerDay + 1;
					
					if(d <= 0){
						document.getElementById("messageDiv").style.display = 'none';
						document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Steady State Start Date must be greater than Transformation End Date</li></ul>";
						document.getElementById("startdate").value = "";
						return false;
					}
				}
					if(!validateFields()){
						document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please enter numeric value greater than 0 for "+errorMessage+" under Customer and Competitive Information tab</li></ul>";
						return false;
					}
					if(validateOpportunityDetails()){
						$('#form1').submit();
						document.getElementById("showError").style.display = 'none';
					}
					else{
						document.getElementById("showError").style.display = 'block';
						document.getElementById("showError").innerHTML = "<ul><li>Please fill all the mandatory fields of Opportunity Details Tab</li></ul>";
					}
				}
		    });
		
		$('#form1').validate({
			errorContainer : "#showError",
			errorLabelContainer : "#showError",
			wrapper : "li",
			focusInvalid : true,
			rules : {
				sssd: {
					required: true,
					date:true
				},
				ssed: {
					required: true,
					date:true
				}
			},
			messages : {
				
				sssd: {
					required: "Please specify steady state start date under project delivery tab",
					date: "Please specify a valid steady state start date"
				},
				ssed: {
					required: "Please specify steady state end date under project delivery tab",
					date: "Please specify a valid steady state end date"
				}
			}
		});
		
		
		
		$("#startdate").datepicker();
		$("#enddate").datepicker();
		/* $("#Tstartdate").datepicker();
		$("#Tenddate").datepicker(); */
		
		$("#Tstartdate").datepicker({ 
			beforeShowDay: function(date){ return [date.getDay() == 1,""];}
		});	
		
		$("#Tenddate").datepicker({  
			beforeShowDay: function(date){ return [date.getDay() == 5,""];}
		});	

		
	});
	
	function myValidate()
	{
		var flag = true;
		$("#messageDiv").text('');
		document.getElementById("messageDiv").style.display = 'none';
		document.getElementById("showError").innerHTML = '';
		document.getElementById("showError").style.display = 'block';
		var sd = document.getElementById("startdate").value;
		var ed = document.getElementById("enddate").value;
		
		/* 
		var local = document.getElementById("onshoreLocal").value;
		var gsc = document.getElementById("onshoreGSC").value;
		var ppp = document.getElementById("onshore3PP").value;
		var near = document.getElementById("nearShore").value;
		var off = document.getElementById("offShore").value; */
		
		
		
		var messageError = "<ul>";
		if(sd == "")
		{
			messageError += "<li>"+"Enter steady state start date"+"</li>";
			flag = false;
		}
		if(ed == "")
			{
			messageError += "<li>"+"Enter steady state end date"+"</li>";
			flag = false;
			}
	
		/* if(local != ""){
			if (isNaN(local)){
				messageError += "<li>"+"Enter only numeric value for Onshore Local"+"</li>";
				flag = false;
			}
		}else{
			messageError += "<li>"+"Enter a numeric value for Onshore Local"+"</li>";
			flag = false;
		}
		if(gsc != ""){
			if (isNaN(gsc)){
				messageError += "<li>"+"Enter only numeric value for Onshore GSC"+"</li>";
				flag = false;
			}
		}else{
			messageError += "<li>"+"Enter a numeric value for Onshore GSC"+"</li>";
			flag = false;
		}
		if(ppp != ""){
			if (isNaN(ppp)){
				messageError += "<li>"+"Enter only numeric value for Onshore 3PP"+"</li>";
				flag = false;
			}
		}else{
			messageError += "<li>"+"Enter a numeric value for Onshore 3PP"+"</li>";
			flag = false;
		}
		if(near != ""){
			if (isNaN(near)){
				messageError += "<li>"+"Enter only numeric value for Nearshore"+"</li>";
				flag = false;
			}
		}else{
			messageError += "<li>"+"Enter a numeric value for Nearshore"+"</li>";
			flag = false;
		}
		if(off != ""){
			if (isNaN(off)){
				messageError += "<li>"+"Enter only numeric value for Offshore"+"</li>";
				flag = false;
			}
		}else{
			messageError += "<li>"+"Enter a numeric value for Offshore"+"</li>";
			flag = false;
		} */
		
		document.getElementById("showError").innerHTML = messageError+"</ul>";
		return flag;
	}

	function cadenceValueCheck()
	{
		var val = document.getElementById("cId").value; 
		if(val <= 0)
			{
			document.getElementById("messageBox1").innerHTML = "value gretaer than 0";
			document.getElementById("cId").value="";
			}
	}
	
	function validateSteadyStateDuration()
			{
		
			var day1, day2, day11,day22;
			var month1, month2,month11,month22;
			var year1, year2,year11,year22;
			var date11, date22;
			
			var dd1;
			var flag = false;
			
			msPerDay = 24 * 60 * 60 * 1000;
			
			document.getElementById("showError").style.display = 'block';
			var sd = document.getElementById("startdate").value;
			var ed = document.getElementById("enddate").value;
			var tsd = document.getElementById("Tstartdate").value;
			var ted = document.getElementById("Tenddate").value;
			
			month1 = sd.substring (0, sd.indexOf ("/"));
			day1 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
			year1 = sd.substring (sd.lastIndexOf ("/")+1, sd.length);

			month2 = ed.substring (0, ed.indexOf ("/"));
			day2 = ed.substring (ed.indexOf ("/")+1, ed.lastIndexOf ("/"));
			year2 = ed.substring (ed.lastIndexOf ("/")+1, ed.length); 
			
			var date1 = year1+"/"+month1+"/"+day1;
			var date2 = year2+"/"+month2+"/"+day2;
			
			if(tsd == "" && ted == ""){
				flag = true;
			}else if(tsd != ""){
				month11 = tsd.substring (0, tsd.indexOf ("/"));
				day11 = tsd.substring (tsd.indexOf ("/")+1, tsd.lastIndexOf ("/"));
				year11 = tsd.substring (tsd.lastIndexOf ("/")+1, tsd.length);
				
				date11 = year11+"/"+month11+"/"+day11;
				
				dd1 = Math.round((Date.parse(date2)).valueOf() - (Date.parse(date11)).valueOf())/msPerDay + 1;
			}
			var dd = Math.round((Date.parse(date2)).valueOf() - (Date.parse(date1)).valueOf())/msPerDay + 1;
			
			
			if(flag){
				dd1 = dd;
			}
			
			if(isNaN(dd1))
			{
				$("#messageDiv").text('');
				document.getElementById("messageDiv").style.display = 'none';
				document.getElementById("showError").style.display = 'block';
				document.getElementById("showError").innerHTML = "<ul><li>Please check the date entered for Steady State end date and transformation start date</li></ul>";
				document.getElementById("enddate").value = "";
				document.getElementById("contractDuration").value = "";
				return false;
			}
			if(dd1 <= 0){
				$("#messageDiv").text('');
				document.getElementById("messageDiv").style.display = 'none';
				document.getElementById("showError").style.display = 'block';
				document.getElementById("showError").innerHTML = "<ul><li>The steady state end date should be greater than transformation start date</li></ul>";
				document.getElementById("enddate").value = "";
				document.getElementById("contractDuration").value = "";
				return false;
			}
			
			if(isNaN(dd))
			{
				$("#messageDiv").text('');
				document.getElementById("messageDiv").style.display = 'none';
				document.getElementById("showError").style.display = 'block';
				document.getElementById("showError").innerHTML = "<ul><li>Please check the date entered for Steady State</li></ul>";
				document.getElementById("startdate").value = "";
				document.getElementById("enddate").value = "";
				document.getElementById("steadyStateDuration").value = "";
				return false;
			}
			if((dd <= 0)&& (dd < 31)){
				document.getElementById("showError").style.display = 'block';
				document.getElementById("showError").innerHTML = "<ul><li>The difference between steady state end date and start date should be greater than 1 month</li></ul>";
				document.getElementById("enddate").value = "";
				document.getElementById("steadyStateDuration").value = "";
				return false;
			}
			else
				{
					document.getElementById("showError").style.display = 'none';
				}
			
			document.getElementById("contractDuration").value = dd1;
			document.getElementById("steadyStateDuration").value = (dd/30).toFixed(); ;
		    }
	
	function validateContractDuration()
	{
		
		var day1, day2;
		var month1, month2;
		var year1, year2;
		document.getElementById("showError").style.display = 'block';
		var sd = document.getElementById("Tstartdate").value;
		var ed = document.getElementById("Tenddate").value;
		
		var sssd = document.getElementById("startdate").value;
		var ssed = document.getElementById("enddate").value;
		var dd;
		
		var msPerDay = 24 * 60 * 60 * 1000;
		
		if(sd == ""){
			dd = 0;
		}else if(sd != "" && ed == ""){
			
			month1 = sd.substring (0, sd.indexOf ("/"));
			day1 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
			year1 = sd.substring (sd.lastIndexOf ("/")+1, sd.length);
			
			month2 = ssed.substring (0, ssed.indexOf ("/"));
			day2 = ssed.substring (ssed.indexOf ("/")+1, ssed.lastIndexOf ("/"));
			year2 = ssed.substring (ssed.lastIndexOf ("/")+1, ssed.length); 
			
			var date1 = year1+"/"+month1+"/"+day1;
			var date2 = year2+"/"+month2+"/"+day2;
			
			dd = Math.round((Date.parse(date1)).valueOf() - (Date.parse(date2)).valueOf())/msPerDay + 1;
			
		}else{
		month1 = sd.substring (0, sd.indexOf ("/"));
		day1 = sd.substring (sd.indexOf ("/")+1, sd.lastIndexOf ("/"));
		year1 = sd.substring (sd.lastIndexOf ("/")+1, sd.length);

		month2 = ed.substring (0, ed.indexOf ("/"));
		day2 = ed.substring (ed.indexOf ("/")+1, ed.lastIndexOf ("/"));
		year2 = ed.substring (ed.lastIndexOf ("/")+1, ed.length); 
		
		var date1 = year1+"/"+month1+"/"+day1;
		var date2 = year2+"/"+month2+"/"+day2;
		
		
		dd = Math.round((Date.parse(date2)).valueOf() - (Date.parse(date1)).valueOf())/msPerDay + 1;
		
		if(isNaN(dd))
		{
			$("#messageDiv").text('');
			document.getElementById("messageDiv").style.display = 'none';
			document.getElementById("showError").innerHTML = "<ul><li>Please check the date entered for Transformation dates</li></ul>";
			document.getElementById("Tstartdate").value = "";
			document.getElementById("Tenddate").value = "";
			document.getElementById("contractDuration").value = "";
			return false;
		}		
		if(dd < 0){
			$("#messageDiv").text('');
			document.getElementById("messageDiv").style.display = 'none';
			document.getElementById("showError").innerHTML = "<ul><li>The transformation end date should be greater than start date</li></ul>";
			document.getElementById("Tenddate").value = "";
			document.getElementById("contractDuration").value = "";
			return false;
		}
		else{
			document.getElementById("showError").style.display = 'none';
		}
		}
		
		document.getElementById("contractDuration").value = dd;
	}

	
	function nextForm()
	{
		document.forms[0].action = "/ADM_PRE/opportunity/saveOpportunity?next=next";
		document.forms[0].submit();
	}
	
</script>


			<div class="tab4DatePanel">
                	<h3>Transformation Start Date :</h3>
                    <span style="width: 75px;">
                    <form:input id="Tstartdate" readonly="true" 
                    path="tsd" value="${OpportunityDetail.transformationStartDate}" type="text" class="inputtextTabs" /></span>
                </div>

            	<div class="tab4DatePanel">
                	<h3>Transformation End Date :</h3>
                    <span style="width: 75px;"><form:input id="Tenddate" readonly="true"  
                    path="ted" value="${OpportunityDetail.transformationEndDate}" type="text" class="inputtextTabs"
                     /></span>
                    <span id="sapn1"></span>
                </div>
                <!-- onchange="javascript:validateContractDuration();" -->
                
                
                <div class="tab4DatePanel" style="width: 230px;">
                	<h3 style="width: 95px;">Delivery Model : </h3>
                    <span>
                    <form:select  path="deliveryModelDTO.deliveryModelId" id=""
							class="dropdownInputList" disabled="${opportunityDetailDTO.createdBy }">
							<form:option value="" label="--Select--" />
							<form:options items="${devModel}" itemValue="deliveryModelId"
								itemLabel="name" />
					</form:select>
                    </span>
                </div>
                
                
                <!-- <div style="clear:both;"></div> -->
                <div class="tab4DatePanel" style="width: 300px;">
                	<h3>Scope/Volumetrics Defined By </h3>
                    <span>
                    	<form:select  path="scopeDefinedByDTO.scopeOfServicesDefinedById" id=""
							class="dropdownInputList" disabled="${opportunityDetailDTO.createdBy }">
							<form:option value="" label="--Select--" />
							<form:options items="${scopeBy}" itemValue="scopeOfServicesDefinedById"
								itemLabel="name" />
					</form:select>
                    </span>
                </div>
            	
                
               <!-- <div style="clear:both;"></div> -->
            	
                
                
                
                
                
				<div style="clear:both;"></div>
            	<div class="tab4DatePanel">
                	<h3>Steady State Start Date<sup style="color: red">*</sup> :</h3>
                    <span style="width: 75px;">
                    	<form:input  id="startdate" path="sssd"
                    		type="text" class="inputtextTabs"  readonly="true"/>
                    </span>
                </div>

            	<div class="tab4DatePanel">
                	<h3>Steady State End Date<sup style="color: red">*</sup> :</h3>
                    <span style="width: 75px;"><form:input id="enddate" readonly="true" 
                     path="ssed" type="text" class="inputtextTabs" onchange="javascript:validateSteadyStateDuration();" />
                     </span>
                    <span id="sapn2"></span>
                </div>
                
                
                <%-- <div class="tab4DatePanel">
                	<h3 style="width: 95px;">Delivery Type : </h3>
                    <span>
                    	<form:select  path="deliveryTypeModelDTO.deliveryTypeId" id=""
							class="dropdownInputList" disabled="${opportunityDetailDTO.createdBy }">
							<form:option value="" label="--Select--" />
							<form:options items="${devType}" itemValue="deliveryTypeId"
								itemLabel="name" />
					</form:select>
                    </span>
                </div>
                <div class="tab8DatePanel">
                	<h3>Volumetrics </h3>
                    <span >
                    	<form:select  path="volumetricsDefinedByDTO.inputVolumetricsDefinedById" id=""
							class="dropdownInputList" disabled="${opportunityDetailDTO.createdBy }">
							<form:option value="" label="--Select--" />
							<form:options items="${volumetricsBy}" itemValue="inputVolumetricsDefinedById"
								itemLabel="name" />
					</form:select>
                    </span>
                </div> --%>
                
                
                
				<div style="clear:both;"></div>
            	<div class="tab4DatePanel">
                	<h3>Contract Duration <i><font size="1" color="#09AE53">[Days]:</h3>
                    <span style="width: 75px;"><form:input readonly="true" path="contractDuration" value="${OpportunityDetail.contractDuration}" 
                    type="text" class="inputtextTabs"/></span>
                </div>

            	<div class="tab4DatePanel">
                	<h3>Steady State Duration<i><font size="1" color="#09AE53">[Months]:</h3>
                    <span style="width: 75px;"><form:input readonly="true" path="steadyStateDuration" value="${OpportunityDetail.steadyStateDuration}" 
                    type="text" class="inputtextTabs"/></span>
                </div>
                 <div style="clear:both;"></div>
              
                <%-- <div class="tab4DatePanel">
                	<h3><b>Utilization Per Year :</b></h3>
                </div>
                	
                <div style="clear:both;"></div>
                <div class="tab4DatePanel" style="float: left;">
                	<h3>Onshore GSC :</h3>
                    <span style="width: 75px;">
                    	<form:input  id="onshoreGSC" path="onshoreGSC" 
                    	readonly="${opportunityDetailDTO.createdBy}" 
                    	value="${OpportunityDetail.onshoreGSC}" 
                    		type="text" class="inputtextTabs"  />
                    </span>
                </div>
                
                
                
                <div class="tab4DatePanel" style="float: left;">
                	<h3>Onshore 3PP :</h3>
                    <span style="width: 75px;">
                    	<form:input  id="onshore3PP" path="onshore3PP" 
                    	readonly="${opportunityDetailDTO.createdBy}" 
                    	value="${OpportunityDetail.onshore3PP}"
                    		type="text" class="inputtextTabs"  />
                    </span>
                </div>
                
                
               
                
                <div style="clear:both;"></div>
                 <div class="tab4DatePanel" style="float: left;">
                	<h3 >Onshore Local :</h3>
                    <span style="width: 75px;">
                    	<form:input  id="onshoreLocal" path="onshoreLocal" 
                    	readonly="${opportunityDetailDTO.createdBy}" 
                    	value="${OpportunityDetail.onshoreLocal}"
                    		type="text" class="inputtextTabs"  />
                    </span>
                </div>
                
                
                
                 <div class="tab4DatePanel" style="float: left;">
                	<h3>Nearshore :</h3>
                    <span style="width: 75px;">
                    	<form:input  id="nearShore" path="nearShore" 
                    	readonly="${opportunityDetailDTO.createdBy}" 
                    	value="${OpportunityDetail.nearShore}"
                    		type="text" class="inputtextTabs"  />
                    </span>
                </div>
                
                <div style="clear:both;"></div>
                <div class="tab4DatePanel" style="float: left;">
                	<h3>Offshore :</h3>
                    <span style="width: 75px;">
                    	<form:input  id="offShore" path="offShore" 
                    	readonly="${opportunityDetailDTO.createdBy}" 
                    	value="${OpportunityDetail.offShore}"
                    		type="text" class="inputtextTabs"  />
                    </span>
                </div>
                --%>
                
                
               
                <div class="tabsButtonPanel">
               	 	<div class="TabsCommonButtons" onclick="javascript:previousPage()">Previous</div>
                   <c:if test="${!(opportunityDetailDTO.createdBy)}">
                   <c:if test="${role_guest != 'true'}">
                    <div class="TabsCommonButtons" onclick="javascript:clearForm()">Reset</div> 
                    <div class="TabsCommonButtons" id="saveForm4">Save</div>
                    </c:if>
                    </c:if>
                    <div class="TabsCommonButtons" onclick="javascript:nextPage('projectdetails')">Next</div>             
                </div>
                
