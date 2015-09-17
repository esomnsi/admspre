<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/jquery-1.js"></script>
<script type="text/javascript" src="js/jquery_003.js"></script>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/it_mssp.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css"  rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>
<script type="text/javascript">
var oTable;
var hlr = 0;
var checked = false;
var rowClicked = false;
var data;
var selectedRowIndex;
var selectedDataArray = new Array();
//var map = new Map();
$(document).ready(function() {
	 oTable = $('#dataTable').dataTable( {			
			 "sPaginationType": "full_numbers",			
			 "bJQueryUI": true,	
			 "bSort" :false,
			 "oLanguage": {
			        "sEmptyTable":     "<br /><br />There are no items in your Inbox to take action.",	
			         "sLoadingRecords": "Please wait - loading..."
			    },		 
			 "fnDrawCallback": function () {
				 $('#dataTable tr').click(function(event){
					    $(this).find('input[type=radio]').prop('checked', true);
					})
			 }
			 
		});
	 
	 if(typeof $('#dataTable tbody').delegate!='undefined'){
		 $('#dataTable tbody').delegate("tr", "click", rowClick); 
	 }else{
		 $('#dataTable tbody tr').bind("click", rowClick); 
	 }
	 
	 
	 
	 $('#submit').click(function() {		 
		 	var oppId=0, solId=0,mode;
		 	
		 	$("input[type='radio']").each(function() {
				if($(this).attr("checked")){
	            	oppId=$(this).attr("oppId");
	            	solId=$(this).attr("solId");
	            	mode=$(this).attr("mode");
	            	//break;
	            }
			});
		 	if(!rowClicked){
		 		document.getElementById("messageDiv").style.display = "block";
				document.getElementById("messageDiv").innerHTML = "<ul><li>Please select atleast one record</li></ul>";
		 	}
		 	
		 	//alert("oppId="+oppId+"solId="+solId);
		 	if(mode=="Approver"){		 		
		 		window.location = "../approval/approvalDetails?myActionSolutionId="+solId+"&myActionOpportunityId="+oppId;
		 	}else if(mode=="SolutionManager"){
		 		/* alert(12); 				 		
		 		document.forms[0].action="/IT_MSSP/action/submitApprovalData?solId="+solId+"&oppId="+oppId;
				document.forms[0].submit(); */
		 		$.ajax({
					type : "POST",
					url : "./submitApprovalData",
					data : {"oppId": oppId,"solId":solId},
					datatype : "text",
					success : function(response){
						window.location = "../opportunity/displayOpportunity";
						//alert("Success");
					},
					error : function(e) {
						alert("Operation failed.");
					}
				});			 
				
		 	}
		 	
		});
	 	  
});

var hlr = 0;
function rowClick(){
	document.getElementById("messageDiv").style.display = "none";
	if (hlr)
	      $("td:first", hlr).parent().children().each(function(){$(this).removeClass('markrow');});
	   hlr = this;
	   $("td:first", this).parent().children().each(function(){$(this).addClass('markrow');});
	
	 rowClicked = true;
	 var aData = oTable.fnGetData(this);
	 data = aData;
	 //alert(aData[1]);
	 selectedRowIndex = oTable.fnGetPosition( this );
	 //alert(aData[1]);		
}

</script>

<style type="text/css">
       .markrow { background-color:#ffbba9 !important; }
       
       div.dataTables_wrapper .ui-widget-header {
	 font-weight:bold;
	 width:1100px;
	 margin-left:1px;
	 color:white;
	-moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    -khtml-border-radius: 5px;
    border-radius: 5px;
    font-family:arial;
	
 	background-image:-webkit-gradient(linear, left top, left bottom, from(#0c3d67), to(#275d92));
	background-image:-webkit-linear-gradient(#0c3d67, #275d92);
	background-image:-moz-linear-gradient(#0c3d67, #275d92);
	background-image:-ms-linear-gradient(#0c3d67, #275d92);
	background-image:-o-linear-gradient(#0c3d67, #275d92);
	background-image:linear-gradient(#0c3d67, #275d92);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#0c3d67', endColorstr='#275d92');
}

.subminApprovalTable{
 	font-size: 12px;
  	color: #FFF;
  	margin-left:1px;
	font-family: arial;
	border:1px solid #265C90;
		-moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    -khtml-border-radius: 5px;
    border-radius: 5px;
    width:1112px;
	height: 150px;
	
 }
 </style>

<form:form id="approvalDataForm" method="post" commandName="initiateApprovalProcessDTO">
<div id="messageDiv" class="message" style="color:red;border:1px solid red;display:none; font-size: 12px;font-family: arial;text-align: left;"></div>
<c:if test="${mode eq 'SuperUser'}">
			<div style="color:red;font-size: 13px;margin-left: 250px;">To perform operations on this page, please login with your respective roles.</div>
</c:if>		
 <div class="tdHeaderLabel" style="width:1096px;margin-bottom:4px;margin-left:1px;">My Action</div> 
<table id="dataTable" border="0" align="center" cellpacing="1" class="subminApprovalTable">
	 <thead>		
			<tr class="subminApprovalTdProperty">
				<td width="10" ></td>
       			<td width="340">Opportunity Name (Id)</td>
        		<td width="401">Solution Name (Id)</td>
        		<td width="200">Customer</td>
        			<td width="200">Country</td>
        			<td width="100">Region</td>
        			<td width="200">Status</td>
        		<c:if test="${mode eq 'Approver'}">
        			<td width="324">Solution Submitted Date</td>        			
        		</c:if>
        		<c:if test="${mode eq 'SolutionManager'}">
        			<td width="324">Created By</td>        			
        		</c:if>	
			</tr>
		</thead>
		<tbody>
			<c:forEach var="search" items="${searchResult}">
				<tr bgcolor="#f1f1f1">
					<%-- <td><form:radiobutton id="accessRadio"  path="selected"/></td> --%>
					<td><input type="radio" id="accessRadio" path="selected"  name="radio" oppId="${search.opportunityId}" solId="${search.solutionId}" mode="${mode}"/></td>
					<td style="color:black;">${search.opportunityName} (${search.opportunityId})</td>
					<td style="color:black;">${search.solutionName} (${search.solutionId})</td>
					<td style="color:black;">${search.customer}</td>
					<td style="color:black;">${search.country}</td>
					<td style="color:black;">${search.region}</td>
					<td style="color:black;">${search.statusName}</td>		
					<c:if test="${mode eq 'Approver'}">
						<td style="color:black;">${search.submittedDate}</td>
					</c:if>		
					<c:if test="${mode eq 'SolutionManager'}">
						<td style="color:black;">${search.createdBy}</td>					
					</c:if>
				</tr>
		   </c:forEach>
		</tbody>	
	</table>
	<table id="tLower" style="margin-left: 79%;">
		<tr>
			<!-- <td align="center" colspan="8" class="tdButtonRow" ><a s
						class="button" target="_blank" style="margin-right: -80px;">Save</a>  -->
		</tr>
		<!-- <tr>
			<td colspan="2">&nbsp;</td>
		</tr> -->
		<c:if test="${mode ne 'SuperUser'}">
		<tr>
			<td align="center" colspan="2" class="tdButtonRow">
				<a class="TabsCommonButtons" id="submit" target="_blank"  type="submit" style="width:100px;margin-left: 120px;">VIEW DETAILS</a>
			</td>			
		</tr>
		</c:if>
		
		</table>
  </form:form>