<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
var errormsg="";
var confirmmsg ="";
var hasNoScope = "hasNoScope";
var isSaveClicked =false;

function enableListBox(obj){
	
	if(obj.value == 'Manage Delivery'){
		document.getElementById("solutionDTO.solutionType").disabled=false;
	}else{
		document.getElementById("solutionDTO.solutionType").selectedIndex=0;
		document.getElementById("solutionDTO.solutionType").disabled=true;
	}
}

function getOtherDiv(obj) {
	if(obj.value == 'Other'){
		document.getElementById("otherDiv").style.display = "block";
	} else {
		document.getElementById("otherDiv").style.display = "none";
	}
	
	if(obj.value == ''){
		document.getElementById("attributeName").disabled=true;
	}else {
		document.getElementById("attributeName").disabled=false;
	}
}




function validateDimensionAttributes(){
	
	//var attrListBox = $('#solutionDTO.solutionApproachDimensionDTO.dimensionAttributes');
	var attrListBox = document.getElementById('solutionDTO.solutionApproachDimensionDTO.dimensionAttributes');
	var selDimensionAttributes = document.getElementById('solutionDTO.dimentionAttributesString').value;
	
	var selAttrDimArray = selDimensionAttributes.split(";");
	
	var newDimAttributesList=""; 
	var deleltedDimAttributesIds=""; 
	var deleltedDimAttributesNames=""; 
	
	if(selAttrDimArray.length>0){
		for(i=0;i<attrListBox.options.length;i++){
			flag= false;
			for(j=0;j<selAttrDimArray.length;j++){
				var selAttrArray =selAttrDimArray[j].split("_");
			//	alert(attrListBox.options[i].value  + '  ======  '+selAttrArray[1]);
				if(attrListBox.options[i].value==selAttrArray[1]){
					flag=true;
					break;
				}
			}
			if(flag==false){
				newDimAttributesList += attrListBox[i].value +",";
			}
		}
	}
	
	if(selAttrDimArray != ''){
		for(j=0;j<selAttrDimArray.length;j++){
			flag= false;
			var selAttrArray =selAttrDimArray[j].split("_");
			
			for(i=0;i<attrListBox.options.length;i++){
				
					if(attrListBox.options[i].value==selAttrArray[1]){
					flag=true;
					break;
				}
			}
			if(flag==false){
				deleltedDimAttributesIds += selAttrArray[0] +",";
				deleltedDimAttributesNames+= selAttrArray[1] +",";
			}
		}
	}
	
	
	document.getElementById('solutionDTO.deleltedDimAttributesIds').value = deleltedDimAttributesIds;
	document.getElementById('solutionDTO.newDimAttributesList').value = newDimAttributesList;
	if(deleltedDimAttributesNames != 'undefined' && deleltedDimAttributesNames !=''){
		confirmmsg += '\n You have deleted '+ deleltedDimAttributesNames+' dimension attributes'; 
	}
}
 function validateServiceScopes(){
	
	var selServiceScopes = $('#selServiceScopes').val();
	var selSSArray = selServiceScopes.split(";");
	
	
	var serviceScopes = $('[name="serviceScopes"]:checked');

	var newServiceScopeIds ="";
	var deleletedOppScopeIds ="";
	var deleletedServiceNames ="";
	var flag= false;
	if(selSSArray.length>0){
		for(i=0;i<serviceScopes.length;i++){
			flag= false;
			for(j=0;j<selSSArray.length;j++){
				var selServiceArray =selSSArray[j].split("_");
				//alert(serviceScopes[i].value  + '  ======  '+selServiceArray[0]);
				if(serviceScopes[i].value==selServiceArray[0]){
					flag=true;
					break;
				}
			}
			if(flag==false){
				newServiceScopeIds += serviceScopes[i].value +",";
			}
		}
	}
	
	if(selSSArray != ''){
		for(j=0;j<selSSArray.length;j++){
			flag= false;
			var selServiceArray =selSSArray[j].split("_");
			for(i=0;i<serviceScopes.length;i++){
					if(serviceScopes[i].value==selServiceArray[0]){
					flag=true;
					break;
				}
			}
			if(flag==false){
				deleletedOppScopeIds += selServiceArray[1] +",";
				deleletedServiceNames += selServiceArray[2] +",";
			}
		}
	}
	
	$("#deleletedOppScopeIds").val(deleletedOppScopeIds);
	$("#newServiceScopeIds").val(newServiceScopeIds);
	
	if(deleletedServiceNames != 'undefined' && deleletedServiceNames !=''){
		confirmmsg += ' You have deleted '+ deleletedServiceNames+' service scopes'; 
	}

} 
function setDimentionNameList(){
	
	var listbox = document.getElementById('solutionDTO.solutionApproachDimensionDTO.dimensionAttributes');
		   if(listbox != null && listbox != 'undefined'){
			 var srcLength = listbox.length;
		     if(srcLength != 0){
			  	 for(var i=0;i<srcLength;i++){
			  			listbox.options[i].selected = true;
			   	}
			  	 return true;
		     }else{
		    	return false;
		   }
	}

}
function addNewItem()
{
	// Retrieve the elements from the document body
	var textbox = document.getElementById('attributeName');
	var listbox = document.getElementById('solutionDTO.solutionApproachDimensionDTO.dimensionAttributes');
	var exists = isAlreadyExist(listbox,textbox.value);
	if(!exists && textbox.value !=''){
		// Now we need to create a new 'option' tag to add to MyListbox
		var newOption = document.createElement('option');
		newOption.value = textbox.value; // The value that this option will have
		newOption.innerHTML = textbox.value; // The displayed text inside of the <option> tags
	
		listbox.appendChild(newOption);
	}else{
		alert(" Solution approach dimension can't be duplicate or Empty !!");
	}
}


function isAlreadyExist(listBox,value){
	var exists=false;
	for(var x=0;x<listBox.options.length;x++){
		if(listBox.options[x].value==value){
			exists=true;
			break;
		}
	}
	return exists;
}
	
function deleteItem(){
	var htmlSelect=document.getElementById('solutionDTO.solutionApproachDimensionDTO.dimensionAttributes');//HTML select box
	var optionToRemove=htmlSelect.options.selectedIndex;//get the selected index
	if(optionToRemove >=0){
		htmlSelect.remove(optionToRemove);//remove option from list box
	}else{
		alert("Please select a attribute to delete !!!");
	}

}

var currentSelectedTab =1;
function setTab(index,flag){
	
	if(flag){
		currentSelectedTab=index;
	}else{
		
		document.getElementById('t' + (currentSelectedTab+1)).className = 'selected';
		document.getElementById('t' + (currentSelectedTab)).className = 'usual';
		
		document.getElementById('tab' + (currentSelectedTab)).style.display = "none";
		document.getElementById('tab' + (currentSelectedTab+1)).style.display = "block";
		
		currentSelectedTab = currentSelectedTab+1;
		
	}
	
}
function previousTab(index,flag){
	
	if(flag){
		currentSelectedTab=index;
	}else{
		
		document.getElementById('t' + (currentSelectedTab-1)).className = 'selected';
		document.getElementById('t' + (currentSelectedTab)).className = 'usual';
		
		document.getElementById('tab' + (currentSelectedTab)).style.display = "none";
		document.getElementById('tab' + (currentSelectedTab-1)).style.display = "block";
		
		currentSelectedTab = currentSelectedTab-1;
		
	}
	
}


$(document).ready(function() {
		var formAction;
		
		var hasEditSolAccess = "${hasEditSolAccess}";
		if(hasEditSolAccess=='false'){
			 $("input").attr("disabled","disabled");
			 $("select").attr("disabled","disabled");
		}
		
		 if(document.getElementById('solutionDTO.solutionApproach') != null &&
			document.getElementById('solutionDTO.solutionApproach').value == 'Manage Delivery'){
			document.getElementById("solutionDTO.solutionType").disabled=false;
		 } 
		 
		 //handling of service scope tab
		 
		 $('#reset').click(function() {
			 $('#serviceScopeForm').each (function(){
	    		  this.reset();
	    	});
		   });
		 
		 $('#serviceElementReset').click(function() {
			 $('#serviceScopeForm').each (function(){
	    		  this.reset();
	    	});
		   });
		 
		 $('#serviceElementPrev').click(function() {
			 $('#defineScopeForm').attr("action",'./defineScopePrev');
		  	  // $("#solutionForm").validate().cancelSubmit = true;
		    	$('#defineScopeForm').submit();
		   });
		 
	  	  $('#save').click(function() {
	  		 $("#messageDiv").hide();
				$('#formAction').val('save');
				confirmmsg='';
				validateServiceScopes();
				 $("#messageDiv").hide();
				 if($('#serviceScopeForm').valid()){
			  			if(confirmmsg != ''){
							confirmmsg += "\n\n Deleting service scope(s) will delete all the associated details from the system.\n\n Cick 'Ok' if you want to continue otherwise click 'Cancel'";
							if(confirm(confirmmsg)){
								$('#serviceScopeForm').submit();
				        	}else{
				        		return false;
				        	}
			  			}else{
			  				$('#serviceScopeForm').submit();
			  			}
			  			
					}
		  });
	  	  
	  	$("#serviceScopeForm").submit(function() {
			showProgress();
			var options = {
					success: function(res) {
						$('#selServiceScopes').val(res);
						// $("#operationL1Div").html(html);
						 $("#messageDiv").addClass("message");
						 $("#messageDiv").show();
						 $("#messageDiv").html('<spring:message code="msg.common.datasave.success"/>');
						
						//$("#sdDiv").replaceWith($('#sdDiv', $(html)));
						  if(formAction == 'next' ){
							 setTab('',false);
			  			} 
						
					},
					error: function(res) {
							 $("#messageDiv").html("There is a error while saving data. Please contact administrator");
					},
					url: "./saveServiceScope"
					
				};
			
				$(this).ajaxSubmit(options);
				hideProgress();
				return false;
		});	
	  	
	  	  
	  	 $('#next').click(function() {
	  		 formAction = 'next';
	  		confirmmsg='';
	  		validateServiceScopes();
	  		if($('#serviceScopeForm').valid()){
	  			if(confirmmsg != ''){
					confirmmsg += "\n\n Deleting service scope(s) will delete all the associated details from the system.\n\n Cick 'Ok' if you want to continue otherwise click 'Cancel'";
					if(confirm(confirmmsg)){
						$('#serviceScopeForm').submit();
		        	}
	  			}else{
	  				$('#serviceScopeForm').submit();
	  			}
	  			
			}
		  });
	  	  
	  	 
	  	$.validator.addMethod('checkServiceScope', function(value, ele) {
		     return $("input:checked").length >= 1;
       }, 'Please select atleast one service scopes');
	
	  $("#serviceScopeForm").validate({
			errorContainer : "#errorDiv",
			errorLabelContainer : "#errorDiv ul",
			wrapper : "li",
			focusInvalid : true,
		 /*  	submitHandler: function(form) {
		  		if($('#formAction').val() == 'save'){
		  			confirmmsg='';
			 		validateServiceScopes();
				//	validateDimensionAttributes();
					if(confirmmsg != ''){
						confirmmsg += "\n\n Deleting service scope(s) will delete all the associated details from the system.\n\n Cick 'Ok' if you want to continue otherwise click 'Cancel'";
						if(confirm(confirmmsg)){
					      form.submit();
			        	}
					}else{
						form.submit();
					}
		  		}else{
					form.submit();
				}
		 		
		    },    */
			rules : {
					serviceScopes: {
						checkServiceScope:true
					}
			}
		
	});
	  	  
			
		/*  $('#next').click(function() {
			 setDimentionNameList();
			 $('#formAction').val('save');
		    	$('#defineScopeForm').attr("action",'../opportunity/defineScopeNext');
		    	$('#defineScopeForm').submit();
		   });
			
		 $('#back').click(function() {
			
		    	$('#defineScopeForm').attr("action",'../opportunity/defineScopeBack');
		    	 $("#defineScopeForm").validate().cancelSubmit = true;
		    	$('#defineScopeForm').submit();
		   }); */
		 
		   $('#prevSol').click(function() {
			   var isServiceScopeVisible = $("#t2List").is(':visible');
				if(isServiceScopeVisible){
					previousTab('',false);
				}else{
					previousTab('',false);
					previousTab('',false);
				}			   
		   });
		   
		   $('#prev').click(function() {
			   previousTab('',false);
		   });
		
		  	
	  	 $('#createNew').click(function() {
		    	$('#defineScopeForm').attr("action",'./createNewSolution');
		  	  // $("#solutionForm").validate().cancelSubmit = true;
		    	$('#defineScopeForm').submit();
		   });
		  	
		   $('#resetSol').click(function() {
		    	$('#solutionForm').each (function(){
		    		  this.reset();
		    	});
		   });
		   
		   $('#nextSol').click(function() {
			   formAction = 'nextSol';
			   $("#messageDiv").hide();
			   if($('#solutionForm').valid()){
				$('#solutionForm').submit();
			   }
		   });
		   
		   $('#serviceElementNext').click(function() {
			   formAction = 'serviceElementNext';
			   $("#messageDiv").hide();
			   showProgress();
			   var serviceElements = $('[name="serviceElements"]:checked');
			   
				var options = {
						success: function(response) {
							var noServiceScope = true;
							var htmlStr = "";
							var selServiceScopes = "";
							var selSSArray = "";
							var count = 0;
							var selServiceArray = "";
							
							$.each(response, function(serviceScopeCategory) {
								if(serviceScopeCategory != hasNoScope){
									htmlStr += "<div class=\"tab5HeaderPanel\">"
												+serviceScopeCategory
												+"<input type=\"checkbox\" name=\"selectAllScope\" id=\"selectAllScope-"+serviceScopeCategory+"\" value=\""+serviceScopeCategory+"\" />"
												+"</div>";
								}
											
								$.each(this, function(count, serviceScope) {
									if(serviceScopeCategory == hasNoScope){
										htmlStr += "<input type=\"checkbox\" name=\"serviceScopes\" id=\"serviceScopes\" value=\""
													+serviceScope.serviceScopeId
									  				+"\" style=\"display\: none\" checked=\"checked\" />";
									}else{
										noServiceScope = false;
										htmlStr += "<div class=\"defineScopeTabs tooltip\">"
													+"<span><input type=\"checkbox\" name=\"serviceScopes\" id=\"serviceScopes\" value=\""
													+serviceScope.serviceScopeId
									  				+"\" style=\"display\: block\" serviceElement=\""+serviceScopeCategory+"\"";
													
									  	if($('#selServiceScopes') != null && $('#selServiceScopes').val() != null){
									  		selServiceScopes = $('#selServiceScopes').val();
									  		selSSArray = selServiceScopes.split(";");
									  		if(selSSArray.length>0){
									  			for(count=0;count<selSSArray.length;count++){
									  				selServiceArray = selSSArray[count].split("_");
									  				if(selServiceArray[0] == serviceScope.serviceScopeId){
									  					htmlStr += "checked=\"checked\"";
									  				}
									  			}
									  		}
									  	}

										htmlStr += "/></span>"
									  				+"<h3>"
									  				+serviceScope.serviceScopeName
									  				+"</h3>"
									  				+"<p>"
									  				+serviceScope.serviceScopeDescription
									  				+"</p>"
									  				+"</div>";
								  }
								});
							});
							
							if(noServiceScope){
								$("#tab2Body").html(htmlStr);
								$('#save').trigger("click");
								$("#t2List").hide();
								
								if(isSaveClicked){
									$("#messageDiv").addClass("message");
									$("#messageDiv").show();
									$("#messageDiv").html('<spring:message code="msg.common.datasave.success"/>');
									isSaveClicked = false;
								}else{
									setTab('',false);
									setTab('',false);
								}
							}else{
								$("#tab2").show();
								$("#tab2Body").html(htmlStr);
								
								if(isSaveClicked){
									$("#messageDiv").addClass("message");
									$("#messageDiv").show();
									$("#messageDiv").html('<spring:message code="msg.serviceElement.hasScope"/>');
									isSaveClicked = false;
								}
								
								setTab('',false);
								
								$("#t2List").show();
							}

						   var hasEditSolAccess = "${hasEditSolAccess}";
							if(hasEditSolAccess=='false'){
								 $("input").attr("disabled","disabled");
								 $("select").attr("disabled","disabled");
							}
						},
						error: function(res) {
							 $("#messageDiv").html("There is a error while saving data. Please contact administrator");
						},
						url: "./getServiceScopeByServiceElement",
						data: serviceElements,
						type: "POST"
					};
				
					$(this).ajaxSubmit(options);
					hideProgress();
					return false;
		   });
		   
		   $('#serviceElementSave').click(function() {
			   isSaveClicked = true;
			   $('#serviceElementNext').trigger("click");
		   });		   
		 
	  	  $('#saveSol').click(function() {
	  			//setDimentionNameList();
				$('#formAction').val('saveSol');
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
	  		
	  		$("input[id|='selectAll']").on("click",function(){
	  			var value = $(this).attr("value");
	  			var isChecked = ($(this).attr("checked") == "checked")?true:false;
	  			$("#serviceElements[serviceFunction|='"+value+"']").attr('checked', isChecked);
	  		});
	  		
	  		$("#tab2Body").on("click","input[id|='selectAllScope']",function(){
	  			var value = $(this).attr("value");
	  			var isChecked = ($(this).attr("checked") == "checked")?true:false;
	  			$("#serviceScopes[serviceElement|='"+value+"']").attr('checked', isChecked);
	  		});
	  		
	  		$('#saveComp').click(function() {
	  			$('#serviceScopeForm').submit();
	  		});
	  		
	  		
	  		
});
			

function submitPage(type){
	
	$('#defineScopeForm').attr("action",'./defineScope?serviceType='+type);
	$('#defineScopeForm').submit();
}


</script>

		<form:form id="defineScopeForm"  action="" method="post" modelAttribute="defineScopeForm">
		</form:form>
		 
		
		
			<div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 5px;">
				<h1 class="tdHeaderLabel" style="margin-top: 0px;margin-bottom: 0px;"> Define Scope</h1>
			</div>
				<c:set var="str" value="display:none;"></c:set>
				<c:if test="${not empty message}">
					<c:set var="str" value="display:block;"></c:set>
				</c:if>
				<div  id="messageDiv" class="message" style="${str}">
						${message}
					</div>
			<div id="errorDiv" class="errorMessage">
				<ul id="c"></ul>
			</div>
			
		 <div id="usual1" class="usual" style="width: 100%;">
			<form:form id="serviceScopeForm"  action="saveServiceScope" method="post" modelAttribute="defineScopeForm">
			<br>
				<%-- <jsp:include page="opportunitySummary.jsp"></jsp:include> --%>
				
				<input type="hidden" id="formAction"  /> 
				<form:hidden path="deleletedOppScopeIds"  /> 
				<form:hidden path="newServiceScopeIds"  />
				<form:hidden path="selServiceScopes"  /> 
			
				<form:hidden path="serviceType"  /> 
         
            <ul>
              <li><a class="selected" href="#tab1" id="t1" onclick="setTab(1,true);"><span></span>Service Element</a></li>
              <li id="t2List" style="display:none"><a class="" href="#tab2" id="t2" onclick="setTab(2,true);"><span></span>Service Scope</a></li>
              <li><a class="" href="#tab4" id="t4" onclick="setTab(4,true);"><span></span>Component</a></li>
              <li><a class="" href="#tab3" id="t3" onclick="setTab(3,true);"><span></span>ADM Engagement </a></li>
            </ul>
            <!-- Service ELements -->
        
             <c:if test="${defineScopeForm.serviceType=='EricssonProduct'}">
            	<c:set var="check1" value="checked" />
            </c:if>
            
            <c:if test="${defineScopeForm.serviceType=='Generic'}">
            	<c:set var="check2" value="checked" />
            </c:if>
             
           
			<div style="display: block;" id="tab1" class="tabBodyArea">
				<div class="tab5HeaderPanel">
						 <input type="radio" id="serviceType" value="EricssonProduct"  onclick="submitPage('EricssonProduct');" ${check1}/> Ericssion Product
						<input type="radio" id="serviceType" value="Generic" style="margin-left:80px;" onclick="submitPage('Generic');"  ${check2} /> Generic
				
				 </div>
				<c:forEach var="serviceFunction" items="${serviceElementDTOMap}">
					<div class="tab5HeaderPanel">
						<c:out value="${serviceFunction.key}" />
						<input type="checkbox" name="selectAll" id="selectAll-${serviceFunction.key}" value="${serviceFunction.key}" />
					</div>
					<c:forEach var="serviceElement" items="${serviceFunction.value}">
						<div class="defineScopeTabs tooltip">
							<span><form:checkbox path="serviceElements" name="serviceElements" id="serviceElements"
									value="${serviceElement.serviceElementID}" serviceFunction="${serviceFunction.key}" /></span>
							<h3>${serviceElement.serviceElementName}</h3>
							<p>${serviceElement.serviceElementDescription}</p>
							<input type="hidden" name="elementHasServiceScope" id="elementHasServiceScope" value="${serviceElement.hasServiceScope}"/>
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
						<%-- <c:otherwise>
							<div onclick="setTab('',false)" class="TabsCommonButtons">Next</div>
						</c:otherwise> --%>
	
					</c:choose>
					<div id="serviceElementNext" class="TabsCommonButtons">Next</div>
				</div>
			</div>
			<!-- End - Service Element -->
		
				<!---------------------------------------Tab 1 (Oppurtunity Details DIV Start from Here)--------------------------------------------->
			<div style="display: none;" id="tab2" class="tabBodyArea">
				<div id="tab2Body">
				</div>
				<div class="tabsDefineScoprButtons" style="float: right; width: 35%;">
					<div id="prev" class="TabsCommonButtons">Previous</div>
					<c:choose>
						<c:when test="${hasEditSolAccess!='false'}">
							<div id="reset" class="TabsCommonButtons">Reset</div>
							<div id="save" class="TabsCommonButtons">Save</div>
							<div id="next" class="TabsCommonButtons">Next</div>
						</c:when>
						<c:otherwise>
							<div onclick="setTab('',false)" class="TabsCommonButtons">Next</div>
						</c:otherwise>
	
					</c:choose>
				</div>
			</div>
			
			<!-- Tab 4 for Components Start -->
			<div style="display: none;" id="tab4" class="tabBodyArea">
			
			<div class="adminDivdHeaderr">Select Components</div>
			<table><tr><td>
				<div style="margin-left: 10px;font-style: italic;font-size: small;">
		       	<form:checkboxes id="chkbox" items="${componentsList}" path="componentIDs" itemLabel="componentName" itemValue="componentID" delimiter="</br>" />		       	
		       </div>
		    </td></tr>
		       <tr><td>
		       <div class="tabsDefineScoprButtons" style="width: 20cm;">
		       <!-- <div id="prev" class="TabsCommonButtons">Previous</div> -->
					<c:choose>
						<c:when test="${hasEditSolAccess!='false'}">
							<!-- <div id="resetComp" class="TabsCommonButtons">Reset</div> -->
							<div id="saveComp" class="TabsCommonButtons">Save</div>
							<!-- <div id="nextComp" class="TabsCommonButtons">Next</div> -->
						</c:when>
						<c:otherwise>
							<!-- <div onclick="setTab('',false)" class="TabsCommonButtons">Next</div> -->
						</c:otherwise>
	
					</c:choose>
					</div>
					</td>
					</tr>
				</table>
			</div>
			<!-- Tab 4 for Components End -->
	 
            </form:form>
 <!---------------------------------------Tab 1 (Oppurtunity Details DIV END from Here)--------------------------------------------->
	<!---------------------------------------Tab 2 (Location Profile DIV Start from Here)--------------------------------------------->  
		    <div style="display: none; width:100%;" id="tab3" class="tabBodyArea"> 
				<jsp:include page="solutionApproach.jsp"></jsp:include>
		</div>
  <!---------------------------------------Tab 2 (Location Profile DIV END from Here)--------------------------------------------->    
</div>
         
<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>			
				
		
