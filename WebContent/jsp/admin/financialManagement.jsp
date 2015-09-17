<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>
<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />

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

<script type="text/javascript">

var oTable;
var oTable1;
var values;

function getCountryData(){
	var selectedCountryIDLen = $("#selectcountry").val().length;
	if(selectedCountryIDLen == 0 || selectedCountryIDLen == ''){
		alert(selectedCountryIDLen);
		return;
	}
	document.forms[0].action="/ADM_PRE/admin/rateManagement";
	document.forms[0].submit();
}

function refreshData(){
	document.forms[0].action="/ADM_PRE/admin/rateManagement";
	document.forms[0].submit();
}


$(document).ready(function(){
	
	$("#rm").click(function(){
		document.getElementById("messageDiv").style.display = "none";
	});
	$("#em").click(function(){
		document.getElementById("messageDiv").style.display = "none";
	});
	
	oTable1 = $('#dataTable2').dataTable({
		"sPaginationType" : "full_numbers",
		"bJQueryUI" : true,
		 "oLanguage": {
		        "sEmptyTable":     "kindly select country from drop down list",	
		         "sLoadingRecords": "Please wait - loading..."
		    },
		"bSort" : true,
		"fnDrawCallback" : function() {
			$('#dataTable2 tr').click(function(event) {
			});
			
			 $("#dataTable2 tbody td").hover(function() {
		         $(this).parents('tr').addClass('highlight');
		       }, function() {
		         $(this).parents('tr').removeClass('highlight');
		       });
			}
	});
	
	$(this).find('input[type=radio]').prop('checked', false);

	oTable = $('#dataTable').dataTable({
		"sPaginationType" : "full_numbers",
		"bJQueryUI" : true,
		 "oLanguage": {
		        "sEmptyTable":     "kindly select country from drop down list",	
		         "sLoadingRecords": "Please wait - loading..."
		    },
		"bSort" : true,
		"fnDrawCallback" : function() {
			$('#dataTable tr').click(function(event) {
				$(this).find('input[type=radio]').prop('checked', true);
			});
			
			 $("#dataTable tbody td").hover(function() {
		         $(this).parents('tr').addClass('highlight');
		       }, function() {
		         $(this).parents('tr').removeClass('highlight');
		       });
			}
	});
	
	$('#dataTable tbody ').delegate("tr", "click", rowClick);
	var hlr = 0;
	var selectedRowIndex;
	function rowClick(){
		if (hlr)
		      $("td:first", hlr).parent().children().each(function(){$(this).removeClass('highlight');});
		   hlr = this;
		   $("td:first", this).parent().children().each(function(){$(this).addClass('highlight');});
		
		 rowClicked = true;
		 var aData = oTable.fnGetData(this);
		 var position = oTable.fnGetPosition( this );
		 
		 var jobRole = $("td:eq(1)", this).text();
		 var jobStage = $("td:eq(2)", this).text();
		 var gsc = $("td:eq(3)", this).text();
		 var rate = $("td:eq(4)", this).text();
		 
		 var rowID = $("input[type='radio']:checked").val();
		 values=rate+"-"+rowID+"-"+$("#selectcountry").val()+"-"+jobRole+"-"+jobStage+"-"+gsc;
		
	}
	
	$("#editPopup").click(function(){
		if(values == "" || values == null){
			alert("Please select a row to update");
			return false;
		}
		window.open("/ADM_PRE/admin/updateRate?param="+values, '', 'height=350,width=800,left=175,top=200,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no');
	});

	
	
	$("#addCountryData").click(function() {
		document.getElementById("addData").style.display = "block";
		 $("#selectJobrole").change();
			
   });
	
	
   $("#selectJobrole").change(function(){
	
	   
	   var selectedJobRoleIDLen = $("#selectJobrole").val().length;
		if(selectedJobRoleIDLen == 0 || selectedJobRoleIDLen == ''){
			alert(selectedJobRoleIDLen);
			return; 
		}
		$('#jobStageID option').each(function(i, option){ $(option).remove(); });
	   $.ajax({
           url : "/ADM_PRE/admin/getJobStages",
           type : "post",
           cache: false,
           datatype : "text",
           data : "jobRoleID="+$("#selectJobrole").val(),
           success : function(obj) {
        	   if(obj.length == 0){
        		   alert("No job stages for the selected job role");
        	   }
        	   $('#jobStageID').append(obj);
			},
			error : function(resp) {
				alert("Error");
			}
       });
   });
   
   $("#saveData").click(function(){
	   
	   var gscLen = $("#selectedGsc").val().length;
	   
	   if(gscLen == 0 || gscLen == ''){
		   alert("Select valid GSC");
		   return false;
	   }
	   
		document.getElementById("addData").style.display = "none";
		document.getElementById("messageDiv").style.display = "none";
		/* document.forms[0].submit(); */
		var sau="";
		var data = document.getElementById("selectcountry").value+"-"+document.getElementById("selectJobrole").value+"-"+document.getElementById("jobStageID").value+"-"+document.getElementById("selectedGsc").value+"-"+document.getElementById("selectedLocation").value+"-"+document.getElementById("rate").value;
		
		
		$.ajax({
	           url : "/ADM_PRE/admin/saveRateData",
	           type : "post",
	           cache: false,
	           datatype : "text",
	           data : "data="+data,
	           success : function(resp) {
	        	   if(resp == "DAE"){
	        		   document.getElementById("messageDiv").style.display = "block";
	        		   document.getElementById("messageDiv").innerHTML="Data already exists, please select the row and click Edit button at the bottom";
	        	   }else{
	        		   document.getElementById("messageDiv").innerHTML=" ";
	        		   document.getElementById("messageDiv").style.display = "block";
	        		   document.getElementById("messageDiv").innerHTML="Data added successfully";
	        	   }
				},
				error : function(resp) {
					alert(resp);
				}
	       });
	});
   
   $("#closeDiv").click(function(){
	   document.getElementById("addData").style.display = "none";
	   document.getElementById("messageDiv").style.display = "none";
   });
   
   $("#selectcountry1").change(function(){
	   document.getElementById("messageDiv").style.display = "none";
	  document.getElementById("curCode").innerHTML=$("#selectcountry1").val().split("-")[0];
   });
   
});

function saveUpdate(){
		$.ajax({
           url : "/ADM_PRE/admin/saveExchangeRate",
           type : "POST",
           cache: false,
           datatype : "text",
           data : "values="+$("#selectcountry1").val().split("-")[0]+"-"+$("#usdRate").val()+"-"+$("#selectcountry1").val().split("-")[1],
           success : function(resp) {
        	   
        	   if(resp == "dataExists"){
        		 alert("Update Successfull");
        	   }
        	   else{
        		   alert("Save Successfull");
        	   }
        	   
        	 /*   document.forms[0].action="/ADM_PRE/admin/rateManagement";
        		document.forms[0].submit(); */
        	   
			},
			error : function(resp) {
				alert("DB Operation Failed");
			}
       });
}

</script>




<div id="messageDiv" class="message" style="display: none;">

</div>


<div id="mainBody" class="bodyCss">
		<div id="bodyDiv" class="mainBodyDivwithoutLine">
		  <div id="bodyDiv" class="mainBodyDiv1"></div>
		  <div id="messageDiv" class="message" style="color:red;border:1px solid red;display:none;"></div>
		  <div id="usual1" class="adminTabPanel">
            <ul>
              <li><a class="selected" href="#tab1"><span id="rm">Rate Management</span> </a></li>
              <li style="width: 205px;"><a class="" href="#tab2"><span id="em">Exchange Management</span></a></li>
            </ul>


	<form:form id="rateManagementForm" commandName="rateManagementDTO" method="POST" action="saveRateData">
	
	<div id="bodyDiv" class="mainBodyDiv1">
		<div style="display: block;" id="tab1" class="subminApprovalBodyArea">
			
				<table>
					<tr>
					<td class="tdLabel">
		
 		Choose Country :
		
        		</td>
        
        <td>
        <form:select  path="countryID" id="selectcountry" class="listBoxMedium">
        	<form:options items="${countries}" itemValue="countryId" itemLabel="countryName" />
		</form:select>
		</td>
		
		<td>
		<div class="tabsDefineScoprButtons" style="float: left;">
	    	<a id="sub" class="TabsCommonButtons" onclick="getCountryData()">Get Data</a>
	    </div>
	    </td>
    
	    <td>
		<div class="tabsDefineScoprButtons" style="float: left;">
			<a id="addCountryData" class="TabsCommonButtons">Add Data</a>
	    </div>
		
				</td>
			</tr>
			</table>
	
	&nbsp;
	
	<div id="addData" style="display: none;margin-top: 10px;margin-bottom: 10px;">
		
		<table>
			<tr>
				<td class="tdLabel">
					<!-- <span style="font-family: sans-serif;font-weight: normal;font-size: 13px;"> -->
					Select JobRole :
					<!-- </span> -->
					
					</td>
				<td>
					<form:select  path="jobRoleID" id="selectJobrole" class="listBoxMedium">
        				<form:options items="${jobRoleList}" itemValue="jobRoleId" itemLabel="role" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td class="tdLabel">
					<!-- <span style="font-family: sans-serif;font-weight: normal;font-size: 13px;"> -->
					Select JobStage :
					<!-- </span> -->
					</td>
				<td>
					<form:select path="jobStageID" id="jobStageID" class="listBoxMedium">
					</form:select>
				</td>
			</tr>
			
			<tr>
				<td class="tdLabel">
					<!-- <span style="font-family: sans-serif;font-weight: normal;font-size: 13px;"> -->
					Choose GSC :
					<!-- </span> -->
				</td>
				<td>
					<form:select id="selectedGsc" class="listBoxMedium" path="gsc">
						
							<form:option value="" label="--Select--" />
							<form:options items="${gscCountriesList}" itemValue="countryId" itemLabel="countryName" />
				
					</form:select>
				</td>
			</tr>

			<tr>
				<td class="tdLabel">
					<!-- <span style="font-family: sans-serif;font-weight: normal;font-size: 13px;"> -->
					Choose Location :
					<!-- </span> -->
				</td>
				<td>
					<form:select id="selectedLocation" class="listBoxMedium" path="subLocation">
						<option value="Onshore local">Onshore (Local)</option>
						<option value="Onshore 3pp">Onshore (3PP)</option>
						<option value="Onshore gsci">Onshore (GSC Landed)</option>
						<option value="nearshore gsc">Near shore (GSC)</option>
						<option value="offshore gsc">Offshore (GSC)</option>
					</form:select>
				</td>
			</tr>
			<tr>
				<td class="tdLabel">
				<!-- <span style="font-family: sans-serif;font-weight: normal;font-size: 13px;"> -->
				Enter Rate :
				<!-- </span> -->
				</td>
				<td>
					<form:input id="rate" path="rate" value="${rateManagementDTO.rate}" type="text" class="textBoxSNumric"/>
				</td>
			</tr>
		</table>
		<a id="saveData"	class="TabsCommonButtons">Save</a>
		<a id="closeDiv"	class="TabsCommonButtons">Close</a>
		
	</div>
	
	&nbsp;
	
	<table id="dataTable" border="0" width="1098px;" cellspacing="2"
								cellpadding="0" style="margin-left: 6px; font-size: 10px;">
		<thead>
			<tr class="subminApprovalTdProperty">
				<td class="tdSubTableHead">Select</td>
				<td class="tdSubTableHead" nowrap="nowrap">Job Role</td>
				<td class="tdSubTableHead" nowrap="nowrap">Job Stage</td>
				<td class="tdSubTableHead" nowrap="nowrap">GSC</td>
				<td class="tdSubTableHead" nowrap="nowrap">Rate</td>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="rateManagementInstance" items="${rateManagementList}">
			<tr class="tdOddRow">
				<td width="2%">
					<form:radiobutton id="accessRadio"  path="selected" value="${rateManagementInstance.rateManagementID }"/>
				</td>
				<td width="10%">${rateManagementInstance.jobRole }</td>
				<td width="12%">${rateManagementInstance.jobStage}</td>
				<td width="12%">${rateManagementInstance.gsc}</td>
				<td width="10%">${rateManagementInstance.rate}</td>
				
				
			</tr>
			</c:forEach>
		</tbody>
	
	
	</table>
	
	<a id="editPopup"	class="TabsCommonButtons">Edit</a>
	
		</div>
	</div>

	</form:form>



<!-- -------------------   END OF DIV 1   ---------------------- -->

<!-- -------------------   START OF DIV 2   ---------------------- -->




	<form id="exchangeRateForm"  method="POST" >
		<div style="display: none;" id="tab2" class="subminApprovalBodyArea">
			
<!-- 			<span style="font-family: sans-serif;font-weight: normal;font-size: 13px;">Exchanges rate from Country 1 to Country 2</span><br><br>
 -->			
		<table>
			<tr>
				<td class="tdLabel">
					<!-- <span style="font-family: sans-serif;font-weight: normal;font-size: 13px;"> -->Choose Country :<!-- </span> -->
				
				</td>
				
				<td>
        			<select  id="selectcountry1" class="listBoxSmall">
        				<c:forEach items="${countries}" var="country" varStatus="con">
        					<c:if test="${con.index < 1 }">
        						<c:set var="cuCode" value="${country.currencyCode }"></c:set>
        					</c:if>
        					<option value="${country.currencyCode}-${country.countryName}">${country.countryName }</option>
        				</c:forEach>
					</select>
				</td>
			</tr>
		<!-- </table>
		<table> -->
		
			<tr>
			<td class="tdLabel">
				<!-- <span style="font-family: sans-serif;font-weight: normal;font-size: 13px;"> -->
				Default value :
				<!-- </span>  -->
			</td>
			
			<td >
				<input type="text" id="val" value="1" readonly="readonly" class="textBoxSNumric">
			</td>
			<td>
				<span id="curCode" style="font-family: sans-serif;font-weight: normal;font-size: 13px;text-align: left;">
					${cuCode}
				</span>
			</td>
			</tr>
			<tr>
			<td class="tdLabel">
				<!-- <span style="font-family: sans-serif;font-weight: normal;font-size: 13px;"> -->Enter equivalent USD value :<!-- </span> --> 
			</td>
			<td>
				<input type="text" id="usdRate" class="textBoxSNumric">
			</td>
			<td>
				<span id="usdCode" style="font-family: sans-serif;font-weight: normal;font-size: 13px;text-align: left;">USD</span>
			</td>
			</tr>
			<tr>
				<td><a id="saveExRate" class="TabsCommonButtons" onclick="saveUpdate()">Save / Update</a></td><td  style="float: left;"><a class="TabsCommonButtons" onclick="refreshData()">Refresh Data</a></td><td></td>
			</tr>
		</table>
		<br>
		<br>
		
		<table id="dataTable2" border="0" width="1098px;" cellspacing="2"
								cellpadding="0" style="margin-left: 6px; font-size: 10px;">
		<thead>
			<tr class="subminApprovalTdProperty">
				<td class="tdSubTableHead" nowrap="nowrap">Country</td>
				<td class="tdSubTableHead" nowrap="nowrap">Currency Code</td>
				<td class="tdSubTableHead" nowrap="nowrap">Equivalent USD Value</td>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="currencyExchangeInstance" items="${currencyExchangeList}">
			<tr class="tdOddRow">
				
				<td width="10%">${currencyExchangeInstance.countryName }</td>
				<td width="12%">${currencyExchangeInstance.currencyCode}</td>
				<td width="10%">${currencyExchangeInstance.usdValue}</td>
				
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
		
		
		
		</div>
	
		
	
	</form>

</div>
</div>
</div>

<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>