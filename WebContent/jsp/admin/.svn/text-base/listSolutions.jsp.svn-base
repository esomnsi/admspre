<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  <script type="text/javascript">
  var vTable = $('#dataTable2').dataTable( {			
		 "sPaginationType": "full_numbers",			
		 "bJQueryUI": true,
		 "fnDrawCallback": function () {
			 $('input:radio[id=accessRadio]').click(function() {
				  var val = $('input:radio[id=accessRadio]:checked').val();		
				  prev = val;
			 })	      
	       
		 	 $('#dataTable2 tr').click(function(event){
				    $(this).find('input[type=radio]').prop('checked', true);							    
				})
			 
			 $("#dataTable2 tbody td").hover(function() {
		         $(this).parents('tr').addClass('highlight');
		       }, function() {
		         $(this).parents('tr').removeClass('highlight');
		       })
		     
			}	
	});
  
	$('#dataTable2 tbody').delegate("tr", "click", rowClick);
	
	var hlr = 0;
	var rowClicked = false;
	function rowClick(){
		rowClicked = true;
		 if (hlr)
		      $("td:first", hlr).parent().children().each(function(){$(this).removeClass('markrow');});
		   hlr = this;
		   $("td:first", this).parent().children().each(function(){$(this).addClass('markrow');});
		  
	}
	
	
	function clearFields() {
		document.getElementById("opname").value = "";
		document.getElementById("custname").value = "";
		document.getElementById("region").selectedIndex = 0;
	}
	
	
	function deleteSolution() {
		if(!rowClicked){
			document.getElementById("msgDiv").style.display = "block";
			document.getElementById("msgDiv").style.color = "red";
			document.getElementById("msgDiv").innerHTML = "Please select atleast one record to perform operation.";
			return false;
		}
		var solId=0;
	 	$("input[type='radio']").each(function() {
			if($(this).attr("checked")){
            	solId=$(this).attr("solId");            	        	//break;
            }
		});
	 		 	
	 	var custName = $("#custname").val();
		var oppName = $("#opname").val();
		var region = $("#region").val();
		showProgress();
		var dataString = oppName + "," + custName + "," + region;
		
	 	$.ajax({
			type : "POST",
			url : "./deleteSolution",
			data : {"solId": solId,"dataString" : dataString},
			datatype : "text",
			success : function(html){
				hideProgress();
				$("#deleteSolutionDiv").html(html);
				document.getElementById("msgDiv").style.display = "block";
				document.getElementById("msgDiv").style.color = "green";
				document.getElementById("msgDiv").innerHTML = "Solution Deleted Succesfully.";
			},
			error : function(e) {				
				hideProgress();
				document.getElementById("msgDiv").style.display = "block";
				document.getElementById("msgDiv").style.color = "red";
				document.getElementById("msgDiv").innerHTML = "Unexpected error occured, try again later.";
			}
		});
		/* document.forms[0].action = "../admin/deleteSolution";
		document.forms[0].submit(); */
	}
	
  
  </script>
  
  <style type="text/css">
      
       .highlight{
      	color:blue;
        font-size: 10px;
        background-color: red;
      }
      
      .row_selected{
      	background-color: grey;
      	background: #FFFFBB;
      }
      
      
       .markrow { background-color:#ffbba9 !important; }
     
 </style>
  	<form:form id="VForm" commandName="sdt" method="POST" >
  		<div id="msgDiv" style="color:red;display:none;text-align: center;margin-left: 380px;font-size: 13px;"></div>
							<div class="adminDivdHeaderr">Delete Solutions</div>
							<div class="adminTitle">
                			<h3 style="width:110px;">Opportunity Name :</h3>
                  			  <span><form:input id="opname" path="opportunityName" type="text" class="inputtextTabs"></form:input></span>
              			  </div>

            			<div class="adminTitle">
                			<h3 style="width:110px;"> Customer Name :</h3>
                 		   <span><form:input id="custname" path="customerName" type="text" class="inputtextTabs"></form:input></span>
               			 </div>
            			<div class="adminTitle">
                			<h3>Region :</h3>
                 		   <span><form:select  path="region" id="region"	class="inputtextTabs">
							<c:forEach var="item" items="${regions}">
												<form:option value="${item.key}">${item.value}</form:option>
								</c:forEach>
							</form:select>
							</span>
               		 	</div>
			              <div class="tabsDefineScoprButtons" style="margin-left: 120px;margin-top: 10px;margin-bottom: 15px;">
			               	<a id="searcUser" class="TabsCommonButtons" onClick="javascript:searchSolutions();" 	target="_blank" >SEARCH</a>
			                <a id="clear" class="TabsCommonButtons" target="_blank" onClick="javascript:clearFields();">CLEAR</a>             
			              </div>
  
  	<table id="dataTable2" width="1098px;" border="0" align="center" cellpacing="1" style="margin-left: 6px;font-size: 10px;">		
					<thead>		
					<tr class="subminApprovalTdProperty">
						<td class="tdSubTableHead">Select</td>
						<td class="tdSubTableHead" nowrap="nowrap">Opportunity ID</td>
						<td class="tdSubTableHead" nowrap="nowrap">Solution ID</td>
						<td class="tdSubTableHead" nowrap="nowrap">Opportunity Name</td>
						<td class="tdSubTableHead" nowrap="nowrap">Customer Name</td>
						<td class="tdSubTableHead">Region</td>
						<!-- <td class="tdSubTableHead" nowrap="nowrap">Deal Value</td>
						<td class="tdSubTableHead">DBR</td> -->
						<!-- <td class="tdSubTableHead">Status</td> -->
					</tr>
				</thead>
				<tbody>
			<c:forEach items="${resultList}" var="search" varStatus="status">
					<tr bgcolor="#f1f1f1">
						<td><form:radiobutton id="accessRadio"  path="selected" solId="${search.solutionID}"/></td>
						<td style="color:black;">${search.opportunityID}</td>
						<td style="color:black;">${search.solutionID}</td>
						<td style="color:black;">${search.opportunityName}</td>
						<td style="color:black;">${search.customerName}</td>
						<td style="color:black;">${search.region}</td>
						<%-- <td style="color:black;">${search.}</td>
						<td style="color:black;">${search.dbr}</td> --%>
						<%-- <td style="color:black;">${search.status}</td> --%>			
					</tr>					
				</c:forEach>				
		</tbody>	
	</table>
				</form:form>			<tr>
								<td class="tdButtonRow" nowrap="nowrap"><a class="TabsCommonButtons" onclick="javascript:deleteSolution();" style="width:110px;">Delete Solution</a></td>
							</tr>
						