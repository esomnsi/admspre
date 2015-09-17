<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">

	var success_message = "Data saved successfully.";
	var ServiceType_Product = "Product";

	$(document)
			.ready(
					function() {					
						$(document)
								.ajaxStart(
										function() {
											$
													.blockUI({
														message : '<h1><img src="../images/ajax-loader.gif" /><font size="3px;"> Please wait,it may take few seconds...</font></h1>',
														css : {
															border : '3px solid #00477D',
															padding : '10px',
															backgroundColor : 'white',
															'-webkit-border-radius' : '10px',
															'-moz-border-radius' : '10px',
															opacity : .8,
															width : '300px',
															height : '80px',
															color : 'black'
														}
													});
										});

						$(document).ajaxStop(function() {
							$.unblockUI();
						});
						
						$('#serviceElementPrev').click(function() {
							$( "#t2" ).trigger( "click" );
							$("#messageDiv").hide();
						});
						
						$('#serviceElementNext').click(function() {
							$('#serviceElementSave').trigger( "click" );
							$( "#t3" ).trigger( "click" );
							$("#messageDiv").hide();
						});
						
						$('#serviceElementReset').click(function() {
							$('#serviceScopeForm').each(function() {
								this.reset();
							});
						});
						
						$('#serviceElementSave').click(function() {
							var selectedServiceElements = [];
							$("#serviceElements:checked").each(function() {
								selectedServiceElements.push($(this).val());
						     });
							
							$.ajax({
								type : "POST",
								url : "./saveServices?selectedServiceElements="+selectedServiceElements,
								success : function(
										response) {
									loadMessageInDiv(success_message,"messageDiv","message");
								},
								error : function(e) {
									loadMessageInDiv(e,"messageDiv","errorMessageDisp");
								}
							});
						});
						
						$('#saveSol').click(function() {
				  			//setDimentionNameList();
							$('#formAction').val('saveSol');
							 $("#messageDiv").hide();
							if($('#solutionForm').valid()){
					    		$('#solutionForm').submit();
							}
							
					    });
						$('#createNew').click(function() {
							 if(confirm("Are you sure to create a solution?")){
						    	$('#defineScopeForm').attr("action",'./createNewSolution');
						    	$('#defineScopeForm').submit();
							 }
					   });
					  	
					   $('#resetSol').click(function() {
					    	$('#solutionForm').each (function(){
					    		  this.reset();
					    	});
					   });

						$('#prevSol').click(function() {
							$("#t1").trigger("click");
							$("#messageDiv").hide();
						});

						$('#nextSol').click(function() {
						   formAction = 'nextSol';
						   $("#messageDiv").hide();
						   if($('#solutionForm').valid()){
							$('#solutionForm').submit();
						   }
					   });
						$("#solutionForm").validate({
							errorContainer : "#errorDiv",
							errorLabelContainer : "#errorDiv ul",
							wrapper : "li",
							focusInvalid : true,
						  	/* submitHandler: function(form) {
						  		if($('#formAction').val() == 'save'){
						  			 confirmmsg='';
							 		//validateServiceScopes();
									//validateDimensionAttributes();
									if(confirmmsg != ''){
										confirmmsg += "\n\n Deleting dimension(s) will delete all the associated details from the system.\n\n Cick 'Ok' if you want to continue otherwise click 'Cancel'";
										if(confirm(confirmmsg)){
									      form.submit();
							        	}
									}else{ 
										form.submit();
									//}
						  		}else{
									form.submit();
								}
						 		
						    } ,  */
							rules : {
									 'solutionDTO.solutionApproachDimensionDTO.dimensionAttributes':{
										 required: function(e){
											//return $('solutionDTO.solutionApproachDimensionDTO.dimensionName').val() == '';
											 return document.getElementById('solutionDTO.solutionApproachDimensionDTO.dimensionName').value !='';
											 }
										
									},
									'solutionDTO.solutionApproach':{
										required: true
									},
									'solutionDTO.solutionType':{
										 required: function(e){
											return $('solutionDTO.solutionApproach').val() != 'Manage Delivery';
										 }
									} 
							}
						     ,
							messages : {
									'solutionDTO.solutionApproachDimensionDTO.dimensionName': {
										required : "Please select dimension name"
									},
									'solutionDTO.solutionApproachDimensionDTO.dimensionAttributes': {
										required : "Dimension attribute name list can't be empty"
									},
									'solutionDTO.solutionApproach': {
										required : "Please select solution approach"
									},
									'solutionDTO.solutionType': {
										required : "Please select solution type"
									}
							
							} 
						
						});
				  		$("#solutionForm").submit(function() {
				  		
				  			showProgress();
							var options = {
									success: function(html) {
										// $("#operationL1Div").html(html);
										
										
										 $("#messageDiv").show();
										 $("#messageDiv").addClass("message");
										 if(formAction != 'nextSol'){
											 $("#messageDiv").html("Your data has been saved successfully.");
										 }
										//$("#sdDiv").replaceWith($('#sdDiv', $(html)));
										 if(formAction == 'nextSol' ){
										 $("#messageDiv").html("Your data has been saved successfully. You will be redirected to next page..");
										 	$('#defineScopeForm').attr("action",'./defineScopeNext');
										  	$('#defineScopeForm').submit();	
							  				
							  			}
										
									},
									error: function(res) {
										 $("#messageDiv").html("There is a error while saving data. Please contact administrator");
									},
									url: "./saveSolution"
									
								};
							
								$(this).ajaxSubmit(options);
								hideProgress();
								return false;
						});
						
						$('#serviceOfferingPrev').on("click", function() {
							$('#defineScopeForm').attr("action",'./defineScopePrev');
						    $('#defineScopeForm').submit();
						});
						
						$('#serviceOfferingNext').on("click", function() {
							$('#serviceOfferingSave').trigger( "click" );
							$( "#t1" ).trigger( "click" );
							$("#messageDiv").hide();
						});
						
						$('#serviceOfferingSave').on("click", function() {
							var componentIDs = [];
							$("#component:checked").each(function() {
								componentIDs.push($(this).val());
						     });
							
							$.ajax({
								type : "POST",
								url : "./saveOpportunityComponent?componentIDs="+componentIDs+"&serviceType="+$("#serviceType").val(),
								success : function(response) {
									loadMessageInDiv(response,"messageDiv","message");
								},
								error : function(e) {
									loadMessageInDiv(e,"messageDiv","message");
								}
							});
						});
						
						$("#serviceTypeSelection select").imagepicker({
							show_label : true,
							selected : function() {
								var selectedServiceType = $(this).val();
								/* var serviceType = $("#serviceTypeSelection select option:selected").attr("serviceType");
								$("#serviceType").val(serviceType); */
								
								if (selectedServiceType == 2) {
									populateProducts();
								}else{
									$("#contentDiv").html("");
									loadMessageInDiv("Currently not implemented.","messageDiv","message");
								}
							},
							changed: function(old, newval){
								if(newval == ""){
									$("#messageDiv").hide();
									$("#contentDiv").html("");
								}
							}
						});
						
						selectServiceType();
						
						$("#product").live( "click", function() {
							$.ajax({
								type : "POST",
								url : "./components?productID="+$(this).attr("value"),
								success : function(response) {
									var htmlStr = "<div class=\"defineScopeBodyPanel\">";
									$.each(response, function() {
										htmlStr += "<div class=\"defineScopeTabs tooltip\">"
											+"<span><input type=\"checkbox\" name=\"component\" id=\"component\" value=\""
											+this.componentID
											+"\" title=\""
											+this.componentName
											+"\"";
										
										if(this.checked){
											htmlStr += " checked=\""
											+this.checked
											+"\"";
										}
							  			
										htmlStr += " />"
							  				+"</span>"
							  				+"<h3>"
							  				+this.componentName
							  				+"</h3>"
							  				+"</div>";
									});
									htmlStr += "</div>";
									$("#contentDiv").append(htmlStr);
								},
								error : function(e) {
									loadMessageInDiv("Currently not implemented.","messageDiv","message");
								}
							});
						});
						
						$("input[id|='selectAll']").on("click",function(){
				  			var value = $(this).attr("value");
				  			var isChecked = ($(this).attr("checked") == "checked")?true:false;
				  			$("#serviceElements[serviceFunction|='"+value+"']").attr('checked', isChecked);
				  		});
						
					});
	var currentSelectedTab = 1;
	function setTab(index, flag) {

		if (flag) {
			currentSelectedTab = index;
		} else {

			document.getElementById('t' + (currentSelectedTab + 1)).className = 'selected';
			document.getElementById('t' + (currentSelectedTab)).className = 'usual';

			document.getElementById('tab' + (currentSelectedTab)).style.display = "none";
			document.getElementById('tab' + (currentSelectedTab + 1)).style.display = "block";

			currentSelectedTab = currentSelectedTab + 1;

		}

	}

	function previousTab(index, flag) {

		if (flag) {
			currentSelectedTab = index;
		} else {

			document.getElementById('t' + (currentSelectedTab - 1)).className = 'selected';
			document.getElementById('t' + (currentSelectedTab)).className = 'usual';

			document.getElementById('tab' + (currentSelectedTab)).style.display = "none";
			document.getElementById('tab' + (currentSelectedTab - 1)).style.display = "block";

			currentSelectedTab = currentSelectedTab - 1;
		}
	}
	
	function selectServiceType(){
		$("#serviceTypeSelection select option").prop('selected', false)
	    .filter('[serviceType="'+$("#serviceType").val()+'"]')
	    .prop('selected', true);
		$("#serviceTypeSelection select").data('picker').sync_picker_with_select();
		
		var selectedServiceType = $("#serviceTypeSelection select option:selected").val();		
		if (selectedServiceType == 2) {
			populateProducts();
		}else{
			$("#contentDiv").html("");
			loadMessageInDiv("Currently not implemented.","messageDiv","message");
		}
		
	}
	
	function populateProducts(){
		$.ajax({
			type : "POST",
			url : "./products",
			success : function(response) {
				$("#messageDiv").hide();
				var htmlStr = "<div class=\"defineScopeFirstHeaderPanel\">Ericsson Products"
					+"</div>";
				$.each(response, function() {
					htmlStr += "<div class=\"defineScopeSecondHeaderPanel tooltip\">"
						+"<span><input type=\"radio\" name=\"product\" id=\"product\" value=\""
						+this.productID
						+"\" title=\""
						+this.productDescription
		  				+"\" />"
		  				+"</span>"
		  				//+"<h3>"
		  				+this.productName
		  				//+"</h3>"
		  				+"</div>";
				});
				$("#contentDiv").html(htmlStr);
				$("#product").attr("selected","selected");
				$("#product").trigger("click");
			},
			error : function(e) {
				loadMessageInDiv(e,"messageDiv","errorMessageDisp");
			}
		});
	}
	
</script>
<form:form id="defineScopeForm"  action="" method="post" modelAttribute="defineScopeForm">
</form:form>
<div id="bodyDiv1" class="mainBodyDiv1"
	style="width: 100%; mafloat: left; margin-bottom: 5px;">
	<h1 class="tdHeaderLabel" style="margin-top: 0px; margin-bottom: 0px;">
		Define Scope</h1>
</div>
<c:set var="str" value="display:none;"></c:set>
<c:if test="${not empty message}">
	<c:set var="str" value="display:block;"></c:set>
</c:if>
<div id="messageDiv" class="message" style="${str}">${message}</div>
<div id="errorDiv" class="errorMessage">
	<ul id="c"></ul>
</div>

<div id="usual1" class="usual" style="width: 100%;">
	<form:form id="serviceScopeForm" action="saveServiceScope"
		method="post" modelAttribute="defineScopeForm">
		<br>
		<input type="hidden" id="formAction" />
		<form:hidden path="deleletedOppScopeIds" />
		<form:hidden path="newServiceScopeIds" />
		<form:hidden path="selServiceScopes" />

		<ul>
			<li><a class="selected" href="#tab2" id="t2"
				onclick="setTab(2,true);"><span></span>Select ADM Offering</a></li>
			<li><a class="" href="#tab1" id="t1" onclick="setTab(1,true);"><span></span>Service
					Components</a></li>
			<li><a class="" href="#tab3" id="t3" onclick="setTab(3,true);"><span></span>ADM
					Engagement </a></li>
		</ul>

		<!-- Service Offering -->
		<div style="display: none; width: 100%;" id="tab2" class="tabBodyArea">
			<jsp:include page="defineScopeAdmOffering.jsp"></jsp:include>
		</div>
		<!-- End - Service Offering -->

		<!-- Service ELements -->
		<div style="display: block;" id="tab1" class="tabBodyArea">
			<c:forEach var="serviceFunction" items="${serviceElementDTOMap}">
				<div class="tab5HeaderPanel">
					<c:out value="${serviceFunction.key}" />
					<input type="checkbox" name="selectAll"
						id="selectAll-${serviceFunction.key}"
						value="${serviceFunction.key}" />
				</div>
				<c:forEach var="serviceElement" items="${serviceFunction.value}">
					<div class="defineScopeTabs tooltip">
						<span><form:checkbox path="serviceElements"
								name="serviceElements" id="serviceElements"
								value="${serviceElement.serviceElementID}"
								serviceFunction="${serviceFunction.key}" /></span>
						<h3>${serviceElement.serviceElementName}</h3>
						<p>${serviceElement.serviceElementDescription}</p>
					</div>
				</c:forEach>
			</c:forEach>

			<div class="tabsDefineScoprButtons" style="float: right; width: 35%;">
				<div id="serviceElementPrev" class="TabsCommonButtons">Previous</div>
				<c:choose>
					<c:when test="${hasEditSolAccess!='false'}">
						<div id="serviceElementReset" class="TabsCommonButtons">Reset</div>
						<div id="serviceElementSave" class="TabsCommonButtons">Save</div>
					</c:when>
				</c:choose>
				<div id="serviceElementNext" class="TabsCommonButtons">Next</div>
			</div>
		</div>
		<!-- End - Service Element -->

	</form:form>
	<div style="display: none; width: 100%;" id="tab3" class="tabBodyArea">
		<jsp:include page="solutionApproach.jsp"></jsp:include>
	</div>
</div>

<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>


