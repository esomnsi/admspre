  		<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
	<script type="text/javascript">
	$( window ).load(function() {
		
		var prodModMessage= '${prodModMessage}';
		
		if(prodModMessage== "success"){
		document.getElementById("messageDiv").style.display = "block";
	 	document.getElementById("messageDiv").innerHTML ="Data Saved successfully !";
		} 
		
		if(prodModMessage== "exception"){
			document.getElementById("messageDiv").style.display = "none";
			document.getElementById("errorDiv").style.display = "block";
			document.getElementById("errorDiv").innerHTML = "<ul> Error wile saving the data ! </ul>";
		}
	});
	
	$(document).ready(function () {
		
		  $(":input").focusout(function(){
		        var $this = $(this),
		            input = $this.val();

		        if (input > 100){ 
		            alert('Value inserted cannot not be greater than 100 !');
		            setTimeout(function(){
		            $this.focus();
		            }, 1);
		            
		        }
		    })
		
		
		
		 $("#fteList td span").dblclick(function (e) {
		       e.stopPropagation();      //<-------stop the bubbling of the event here
		       var currentEle = $(this);
		       var value = $(this).html();
		      
		        updateVal(currentEle, value);
		    });
		});

		function updateVal(currentEle, value) {
		  $(currentEle).html('<input class="Input" type="text" value="' + value + '" />');
		  $(".Input").focus();
		  $(".Input").keyup(function (event) {
		      if (event.keyCode == 13) {
		          $(currentEle).html($(".Input").val().trim());
		      }
		     
		  });
		  $(".Input").blur(function (event) {
			  var con = confirm("Are you sure to update ?");
		      if(con){
		    	  var id = currentEle.attr('id');
		    	  var fte = $(".Input").val().trim();
		    	 // alert(id +"::::   "+fte);
		    		 $.ajax({
		 				type : "POST",
		 				url : "./updateFTESummary",
		 				data : {"fteSummaryId": id, "fteValue": fte},
		 				datatype : "text",
		 				success : function(result){
		 					$(currentEle).html(fte);
		 				},
		 				error : function(e) {
		 					alert("Operation failed.");
		 				}
		 			});	
		      }else{
		    	  $(currentEle).html(value);
		      }
		  });productivityModelling

		/*   $(document).click(function () { // you can use $('html')
		        $(currentEle).html($(".Input").val().trim());
		  }); */
		}
		

	
	
	
	function saveProductivity(){
		document.getElementById("prodLeverForm").action = "./saveProductivityModelling";
		document.getElementById("prodLeverForm").submit();
	}
	function reviewMonthwise(){
		document.getElementById("prodLeverForm").action = "./monthwiseProductivity";
		document.getElementById("prodLeverForm").submit();
	}
	
	function getData(datestr,page){
	
		var date= datestr.split(";");
		document.getElementById("startDate").value=date[0];
		document.getElementById("endDate").value=date[1];
		document.getElementById("page").value=page;
	
		document.getElementById("monthwiseForm").action = "./monthwiseProductivity";
		document.getElementById("monthwiseForm").submit();
	}
	
	function submitPage(){
		document.getElementById("monthwiseForm").action = "./monthwiseProductivity";
		document.getElementById("monthwiseForm").submit();
	}
	
	function prev_delvPyramid() {
		document.getElementById("solLeverForm").action="./deliveryPyramid";
		document.getElementById("solLeverForm").submit();
	  };
	  
	function back_Monthwise() {
			document.getElementById("solLeverForm").action="./productivityModelling";
			document.getElementById("solLeverForm").submit();
		  };
		  
	function next_revFte() {
			document.getElementById("solLeverForm").action="/ADM_PRE/solution/reviewUpdatedFTE";
			document.getElementById("solLeverForm").submit();
			  };
			  
    function onBlurrValidation( i){
		 if(i>100){
			document.getElementById("errorDiv").style.display = "block";
		 	document.getElementById("errorDiv").innerHTML ="Value inserted cannot not be greater than 100 !";
				 }
		 if(i<=100){
			 document.getElementById("errorDiv").style.display = "none";
				 }
			  }
	</script>
	<style>
	
	.InputReadOnly{
	background-color:#fafafa;
	 border: 1px solid #eeeeee;
    border-radius: 4px;
    height: 18px;
    margin: 2px 2px;
    outline: medium none;
    width: 40px;
    
	
	}
	.Input{
	 border: 1px solid #eeeeee;
    border-radius: 4px;
    height: 18px;
    margin: 2px 2px;
    outline: medium none;
    width: 40px;
	
	}
	
	</style>
	
	
	<c:if test="${displayType=='yearwise'}">	
	<form:form id="prodLeverForm" action = "./saveProductivityModelling" method="post" modelAttribute="solutionLeverDTO"> 
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
	<td>
	<div id="errorDiv" class="errorMessageDisp" style=" width:1080px;display:none ;margin-left:10px;text-align:left;" ></div>
    <div id="messageDiv" class="message" style=" width:1080px;display:none; margin-left:10px;" ></div>
    </td>
    </tr>
	</table>
	
 	 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td><div class="headerBulepanel">Productivity Levers</div></td>
                  </tr>
                   <tr>
                    <td height="5"></td>
                  </tr>

                </table>            
            	       
                <table width="1080" cellpadding="1" cellspacing="2" class="tablepanel_popup" style="margin:0 0 5px 12px;" bgcolor="#FFFFFF";>
                  
                <tr>
				<c:choose>
				
				
					<c:when test="${empty solutionLeverApproach}">
				
					 <td width="100" height="25" align="left" bgcolor="#fafafa">&nbsp;</td>
					 <td width="30%" height="25" align="left" bgcolor="#fafafa"  ><input type="radio" id="solutionLeverApproach"
						name="solutionLeverApproach" value="Stair Case"
						checked="checked"> Stair Case</td>
						 <td width="70%" height="25" align="left" bgcolor="#fafafa"  ><input type="radio" id="solutionLeverApproach"
						name="solutionLeverApproach" value="Uniform">Uniform</td>
					</c:when>
					<c:otherwise>
						 <td width="30%" height="25" align="left" bgcolor="#fafafa"  ><input type="radio" id="solutionLeverApproach"
						name="solutionLeverApproach" value="Stair Case"
						<c:if test="${solutionLeverApproach == 'Stair Case'}"> 
						checked="checked"</c:if>> Stair Case
						</td>
						 <td width="70%" height="25" align="left" bgcolor="#fafafa"  ><input type="radio" id="solutionLeverApproach"
						name="solutionLeverApproach" value="Uniform"
						<c:if test="${solutionLeverApproach == 'Uniform'}"> 
						checked="checked"</c:if>>Uniform
						<input type="hidden" name="solutionLeverId" 
						value="${solutionLeverId}">
						</td>
					</c:otherwise>
				</c:choose>	
											
				</tr>
                  
            
                </table>       
                       
         
		<table width="1080" cellpadding="1" cellspacing="2" class="tablepanel" style="margin-bottom:3px;" bgcolor="#FFFFFF";>
                  <tr bgcolor="#efefef" align="center">
                    <td height="25" align="left" bgcolor="#efefef" >Time Lines</td>
                   	<c:forEach var="year" items="${yearList}">
                     <td >${year}</td>
                   </c:forEach>
                  
                  </tr>
                  	<c:set var="rowcount4pl" value="${0}"/>
                  		<c:forEach var="serviceElement" items="${oppScopesMap}">
		                  	<c:set var="recordFound" value="false"/>
		                  	 <c:forEach var="prodLever" items="${prodLeverDTOList}">
			                  	 <c:if test="${prodLever.opportunityScopeDTO.opportunityScopeId == serviceElement.key}">
			                  	 		<c:set var="recordFound" value="true"/>
			                  	 </c:if>
	                  	 </c:forEach>
	                  <tr  bgcolor="#fafafa" align="center">
	                    <td height="20" align="left" width="25%" >${serviceElement.value}</td>
	                  
	                    <c:forEach var="year" items="${yearList}" varStatus="i">
	                       <c:set var="readonly" value="" />
	                        <c:set var="defaultValue" value="5" />
	                         <c:set var="class1" value="textBoxSNumric" />
	                    	<c:if test="${i.index == 0}">
	                    		 <c:set var="readonly" value="readonly" />
	                    		 <c:set var="class1" value="textBoxSCalculateResults" />
	                    		   <c:set var="defaultValue" value="0" />
	                    	</c:if>
	                    
	                    	 <td  align="center">
	                    	 <c:forEach var="prodLever" items="${prodLeverDTOList}">
	                   			<fmt:formatDate var="fyear" value="${prodLever.monthYear}" pattern="yyyy" />
	                   			<c:if test="${prodLever.opportunityScopeDTO.opportunityScopeId == serviceElement.key && fyear == year  }">
	                   				<input  type="text" name="prodLeverList[${rowcount4pl}].productivityPC" class="${class1}" 
	                   		 		value="${prodLever.productivityPC}" ${readonly}
	                   		 		maxlength="5" onkeypress="return isNumericWithDecimal(event,this);"/>
	                   		 		 <input type="hidden" name="prodLeverList[${rowcount4pl}].productivityLeverId" class="CommonInputs" value="${prodLever.productivityLeverId}"/>
	                   		 	</c:if>
	                   		
	                   		 </c:forEach>
	                   		 <c:if test="${empty prodLeverDTOList}" >
	                   			  <input type="text"  name="prodLeverList[${rowcount4pl}].productivityPC" class="${class1}" 
	                   		 		maxlength="5" onkeypress="return isNumericWithDecimal(event,this);" value="${defaultValue}" ${readonly}
	                   		 		/>
	                   		  </c:if>
	                   		  
	                   		   <c:if test="${not empty prodLeverDTOList && recordFound==false}" >
	                   		   		<input  type="text" name="prodLeverList[${rowcount4pl}].productivityPC" class="${class1}" 
	                   		 		value="${prodLever.productivityPC}" ${readonly}
	                   		 		maxlength="5" onkeypress="return isNumericWithDecimal(event,this);"/>
	                   		   </c:if>
	                   		  
	                   		   <input type="hidden" name="prodLeverList[${rowcount4pl}].monthYear" class="CommonInputs" value="JAN-${year}"/>
	                   		 <input type="hidden" name="prodLeverList[${rowcount4pl}].opportunityScopeDTO.opportunityScopeId"
	                   		  value="${serviceElement.key}"/>
	                   				
	                   		
	                   		  </td>
	                   		 <c:set var="rowcount4pl" value="${rowcount4pl+1}"/>
	                 	</c:forEach>
	                  </tr>
	                  
                 </c:forEach>
                 </table>
                 
                 <table style="width:100%;">
                  <tr>
                  <td width="60%">&nbsp;</td>
                  
                   <td >
                  <a id="next" class="portfolioButtons" onClick="next_revFte()" >Next</a> 
                  <a id="monthwise" class="buttoncomonStyle" onclick="reviewMonthwise();" >Monthwise FTE</a> 
                   <c:choose>
                	 <c:when test="${hasEditSolAccess!='false'}">
                  <a id="save" class="buttoncomonStyle" onclick="saveProductivity();" >Save</a>   
                  </c:when>
                  </c:choose>
                  <a id="prev" class="portfolioButtons" onClick="prev_delvPyramid()">Previous</a>  
                  </td>
             
                </tr>
                </table>   
     </form:form> 
                 
    </c:if>
     <c:if test="${displayType=='monthwise'}">
     	
     	<form:form id="monthwiseForm" method="post"  modelAttribute="solutionLeverDTO"> 
     	<input type="hidden" name="startDate" id="startDate"/>
     	 <input type="hidden" name="endDate" id="endDate"/>
     	  <input type="hidden" name="page" id="page"/>
     	 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td colspan="4"><div class="headerBulepanel">Monthwise FTE Distribution</div></td>
                  </tr>
                   <tr>
                    <td height="5" colspan="4">&nbsp;</td>
                  </tr>
                  <tr>    
                 	 <c:if test="${isCal==true}">
							<c:set var="chk1" value="checked" ></c:set>
							<c:set var="chk2" value="" ></c:set>
						</c:if>
						<c:if test="${isCal==false}">
							<c:set var="chk2" value="checked" ></c:set>
							<c:set var="chk1" value="" ></c:set>
						</c:if>
						
					 <td width="30%" height="25" align="left" bgcolor="#fafafa" class="tdLabelLeft" >
					 <input type="radio" id="isCal" name="isCal" value="true" ${chk1} onclick="submitPage();" >Calendar View</td>
					 <td width="45%" height="25" align="left" bgcolor="#fafafa"  class="tdLabelLeft">
					 <input type="radio" id="isCal" name="isCal" value="false" ${chk2} onclick="submitPage();">Contract View</td>
						 <td width="20%" >&nbsp;</td>
						  <td width="5%" >&nbsp;</td>
						</tr>
					  <tr>
                    <td  colspan="2">&nbsp;</td>  	
                    <td class="" style="text-align: right; margin-right :10px;font-size: 12px;">
                    <c:forEach var="list" items="${pageList}">
                     <c:if test="${list.key==page}">
                      <a >${list.key}</a>
                     </c:if>
                      <c:if test="${list.key!=page}">
					     <a href="#" onclick="getData('${list.value}', '${list.key}');" >${list.key}</a>
					   </c:if>
					</c:forEach>
                   
                    
                    </td>
					  <td width="5%" >&nbsp;</td>
                  </tr>
                </table>            
                
                
     	<table id="fteList" width="1080" cellpadding="1" cellspacing="2" class="tablepanel" style="margin-bottom:3px;" bgcolor="#FFFFFF";>
                  <tr bgcolor="#efefef" align="center">
                    <td height="25" align="left" bgcolor="#efefef" >Time Lines</td>
                   	<c:forEach var="date1" items="${monthList}">
                   		<fmt:formatDate var="monYear" value="${date1}" pattern="MMM-yyyy" />
                     <td >${monYear}</td>
                   </c:forEach>
                  
                  </tr>
                  
                  
                
                  	<c:forEach var="serviceElement" items="${oppScopesMap}">
	                  <tr  bgcolor="#fafafa" align="center">
	                    <td height="20" align="left" width="25%" >${serviceElement.value}</td>
			     		 
			     		   <c:forEach var="date1" items="${monthList}" varStatus="i">
			     		   <fmt:formatDate var="monYear" value="${date1}" pattern="MMM-yyyy" />
			     		  <td> 
			     		 <c:forEach var="fteSummary" items="${monthwiseFTEList}">
			     		
			     		 	<fmt:formatDate var="mon2" value="${fteSummary.toDate}" pattern="MMM-yyyy" />
			     	
	                   			<c:if test="${fteSummary.opportunityScopeDTO.opportunityScopeId == serviceElement.key && (monYear==mon2)}">
	                   				<span id="${fteSummary.ftesummaryId}">${fteSummary.ftecount} </span> 	
	                   				<%-- <input type="text" name="fte_" class="CommonInputs" value="${fteSummary.ftecount}" readonly/> --%>
	                   				<%-- <a href="#" data-pk="${fteSummary.ftesummaryId}">  ${fteSummary.ftecount}</a> --%>
			     		 		 	
			     		 		</c:if> 	
			     		 </c:forEach>
			     		
			     		 </td>
			     		
			     		  </c:forEach>
			     		</tr>
			     		</c:forEach>
			     		
			  	</table>
     	   
                 <table style="width:100%;">
                  <tr>
                  <td width="60%">&nbsp;</td>
                   <td >
                    <a id="backMonthwise" class="buttoncomonStyle" onclick="back_Monthwise();" >Back</a> 
                  </td>
                 </tr>
                </table>   
     	</form:form>
     	
     </c:if>
                 
                 
                       
                                  
            