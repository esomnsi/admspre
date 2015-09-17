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
var values;

function getCountryData(){
	var selectedCountryIDLen = $("#selectcountry").val().length;
	if(selectedCountryIDLen == 0 || selectedCountryIDLen == ''){
		//alert(selectedCountryIDLen);
		return;
	}
	document.forms[0].action="/ADM_PRE/admin/rateManagement";
	document.forms[0].submit();
}


$(document).ready(function(){
	
	
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
		 var rate = $("td:eq(3)", this).text();
		 
		 var rowID = $("input[type='radio']:checked").val();
		 values=rate+"-"+rowID+"-"+$("#selectcountry").val()+"-"+jobRole+"-"+jobStage;
	}
	
	$("#editPopup").click(function(){
		
		window.open("/ADM_PRE/admin/updateRate?param="+values, '', 'height=300,width=500,left=450,top=300,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no');
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
		document.getElementById("addData").style.display = "none";
		document.forms[0].submit();
	});
   
   $("#closeDiv").click(function(){
	   document.getElementById("addData").style.display = "none";
   })
   
});

</script>



<div id="messageDiv" class="message">
	
</div>

<div id="bodyDiv1" class="mainBodyDivwithoutLine">
	<form:form id="rateManagementForm" commandName="rateManagementDTO" method="POST" action="saveRateData">
	
	<div style="width: 100%;float: left;">
	<div style="float: left;">
	
		Choose Country :
        <form:select  path="countryID" id="selectcountry" class="inputjumptext">
        	<form:options items="${countries}" itemValue="countryId" itemLabel="countryName" />
		</form:select>
	</div>
	
	<div class="tabsDefineScoprButtons" style="float: left;">
    	<a id="sub" class="TabsCommonButtons" onclick="getCountryData()">Get Data</a>
    </div>
    
	<div class="tabsDefineScoprButtons" style="float: left;">
		<a id="addCountryData" class="TabsCommonButtons">Add Data</a>
    </div>
    </div>
	<div id="addData" style="display: none;float: left;margin-top: 10px;margin-bottom: 10px;float: left;">
		
		<table>
			<tr>
				<td>Select JobRole</td>
				<td>
					<form:select  path="jobRoleID" id="selectJobrole" class="inputjumptext">
        				<form:options items="${jobRoleList}" itemValue="jobRoleId" itemLabel="role" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Select JobStage</td>
				<td>
					<form:select path="jobStageID" id="jobStageID" class="inputjumptext">
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Enter Rate</td>
				<td>
					<form:input  path="rate" value="${rateManagementDTO.rate}" type="text" class="textBoxSmallNumeric"/>
				</td>
			</tr>
		</table>
		<a id="saveData"	class="TabsCommonButtons">Save</a>
		<a id="closeDiv"	class="TabsCommonButtons">Close</a>
		
	</div>
	
	&nbsp;
	
	<table id="dataTable" width="100%" border="0" align="center" cellpacing="1" class="subminApprovalTable">
		<thead>
			<tr class="subminApprovalTdProperty">
				<td class="tdSubTableHead">Select</td>
				<td class="tdSubTableHead" nowrap="nowrap">Job Role</td>
				<td class="tdSubTableHead" nowrap="nowrap">Job Stage</td>
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
				<td width="10%">${rateManagementInstance.rate}</td>
				
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a id="editPopup"	class="TabsCommonButtons">Edit</a>
	</form:form>
	
</div>





