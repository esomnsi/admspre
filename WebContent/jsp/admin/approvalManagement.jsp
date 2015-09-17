<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>
<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css"  rel="stylesheet" type="text/css" media="all" />

<title>Define Scope</title>
<script type="text/javascript">

var oTable;
var vTable;
var rowClicked = false;
$(document).ready(function() {
	 oTable = $('#dataTable').dataTable( {			
			 "sPaginationType": "full_numbers",			
			 "bJQueryUI": true,	
			 "bSort" :true,
			 "fnDrawCallback": function () {
				 $('input:radio[id=accessRadio]').click(function() {
					  var val = $('input:radio[id=accessRadio]:checked').val();		
					  prev = val;
				 })	      
		       
				 $('#dataTable tr').click(function(event){
					    $(this).find('input[type=radio]').prop('checked', true);							    
					})
				 
				 $("#dataTable tbody td").hover(function() {
			         $(this).parents('tr').addClass('highlight');
			       }, function() {
			         $(this).parents('tr').removeClass('highlight');
			       })
			 }			 
		});
	 
	 
	 vTable = $('#dataTable2').dataTable( {			
		 "sPaginationType": "full_numbers",			
		 "bJQueryUI": true,	
		 "bSort" :true,
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
	 
 
 $('#dataTable tbody').delegate("tr", "click", rowClick); 
 $('#dataTable2 tbody').delegate("tr", "click", rowClick1); 
 
 
 
 $('#submit').click(function() {		 
	 	
	 	var oppId=0, solId=0;	 	
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
			document.getElementById("messageDiv").innerHTML = "Please select atleast one record.";
			return false;
	 	}
	 	window.location = "../approval/approvalDetails?myActionSolutionId="+solId+"&myActionOpportunityId="+oppId;	 		 	
	});
 
 
 $("#clear").click(function() {
	 document.getElementById("oppname").value = "";
	 document.getElementById("cname").value = "";
	 document.getElementById("selectregion").selectedIndex = 0;	
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

var hlr1 = 0;
function rowClick1(){
	document.getElementById("messageDiv").style.display = "none";
	if (hlr1)
	      $("td:first", hlr1).parent().children().each(function(){$(this).removeClass('markrow');});
	   hlr1 = this;
	   $("td:first", this).parent().children().each(function(){$(this).addClass('markrow');});
	
	 rowClicked = true;
	 var aData = vTable.fnGetData(this);
	 data = aData;
	 //alert(aData[1]);
	 selectedRowIndex = vTable.fnGetPosition( this );
	 //alert(aData[1]);		
}

var progressIndicator = true;
function isSingleDataAvailable(){		
	var oppName = document.getElementById("oppname").value;
	var countryName = document.getElementById("cname").value;
	var region = document.getElementById("selectregion").value;
	
	
	if((oppName == "") &&  (countryName == "") && (region == "")){
		progressIndicator = false;
		return false;
	}else{
		progressIndicator = true;
		return true;
	}
	
}

function showProgress(){
	 if(!progressIndicator){
		return false;
	} 
	$.blockUI({ message: '<h1><img src="../images/ajax-loader.gif" /><font size="3px;"> Please wait,it may take few seconds...</font></h1>' ,css: { 
        border: '3px solid #00477D', 
        padding: '10px', 
        backgroundColor: 'white', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px', 
        opacity: .8, 
        width:'300px',
        height:'80px',
        color: 'black' 
    } }); 
	
	  var tr = $('#dataTable').find('tr');
     tr.bind('click', function(event) {
    	 var values = '';
         tr.removeClass('highlight');
         var tds = $(this).addClass('highlight').find('td');
     }); 
}

function searchOpportunities(){
	
	if(!isSingleDataAvailable()){
		document.getElementById("messageDiv").style.display = "block";
		document.getElementById("messageDiv").innerHTML = "Please provide atleast one field to search.";
		//alert(1);
		return false;
	}	
	
	document.forms[0].action="../admin/listAllOpportunities";
	document.forms[0].submit();
}


function changeApprover(){
	if(!rowClicked){
 		document.getElementById("messageDiv").style.display = "block";
		document.getElementById("messageDiv").innerHTML = "Please select atleast one record.";
		return false;
 	}
	document.forms[0].action="../admin/changeApprovers";
	document.forms[0].submit();
}

function enableListBox(obj){
	if(obj.value == 1){
		document.getElementById("solType").disabled=false;
	}else{
		document.getElementById("solType").disabled=true;
	}
	
}

function showTable(what) {
	if (document.getElementById(what).style.display == "none") {
		document.getElementById(what).style.display = "block";
	} else if (document.getElementById(what).style.display == "block") {
		document.getElementById(what).style.display = "none";
	}
}

 


function popup(mylink, windowname)
{
		if (! window.focus)return true;
			var href;
		if (typeof(mylink) == 'string')
		   href=mylink;
		else
		   	href=mylink.href;
			window.open(href, windowname, 'width=400,height=200,scrollbars=yes');
		return false;
}

function newPopup() {
	popupWindow = window.open(
		"group.jsp", 'popUpWindow', 'height=300,width=700,left=10,top=10,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no,status=no')
}
</script>
 <script type="text/javascript" src="js/jquery-1.js"></script>
<script type="text/javascript" src="js/jquery_003.js"></script>
<link href="../css/it_mssp.css" rel="stylesheet" type="text/css" />
<link href="../css/main.css" rel="stylesheet" type="text/css" />
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

<%-- <jsp:include page="header.jsp"/>
<jsp:include page="menu.jsp"/> --%>

	<div id="mainBody" class="bodyCss">
		<div id="bodyDiv" class="mainBodyDivwithoutLine">
		  <div id="bodyDiv" class="mainBodyDiv1"></div>
		  <div id="messageDiv" class="message" style="color:red;border:1px solid red;display:none;"></div>
		  <div id="usual1" class="adminTabPanel">
            <ul>
              <li><a class="selected" href="#tab1"><span></span>Change Approver </a></li>
              <li><a class="" href="#tab2"><span></span>Approve any Solution</a></li>
            </ul>
            
<!---------------------------------------Tab 1 (Oppurtunity Details DIV Start from Here)--------------------------------------------->

 <!--  <div style="display: block;" id="tab1" class="subminApprovalBodyArea"> -->
 	    <form:form id="LForm" commandName="searchDTO" method="POST" >
		<div id="bodyDiv" class="mainBodyDiv1">
			 <div style="display: block;" id="tab1" class="subminApprovalBodyArea">
                  <div class="adminTitle">
                	<h3 style="width:110px;">Opportunity Name :</h3>
                    <span><form:input id="oppname" path="opportunityName" type="text" class="inputtextTabs"></form:input></span>
                </div>

            	<div class="adminTitle">
                	<h3 style="width:110px;"> Customer Name :</h3>
                    <span><form:input id="cname" path="customerName" type="text" class="inputtextTabs"></form:input></span>
                </div>
            	<div class="adminTitle">
                	<h3>Region :</h3>
                    <span><form:select  path="region" id="selectregion"	class="inputtextTabs">
								<c:forEach var="item" items="${regions}">
												<form:option value="${item.key}">${item.value}</form:option>
								</c:forEach>
							</form:select>
					</span>
                </div>
              <div class="tabsDefineScoprButtons" style="margin-left: 120px;margin-top: 10px;">
               	<a id="searcUser" class="TabsCommonButtons" onClick="javascript:searchOpportunities();showProgress();" 	target="_blank" >SEARCH</a>
                <a id="clear" class="TabsCommonButtons" target="_blank" >CLEAR</a>             
              </div>
			
			 <div class="adminDivdHeaderr">Open Solutions </div>	
		
	<table id="dataTable" width="1098px;" border="0" align="center" cellpacing="1" style="margin-left: 6px;font-size: 10px;">		
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
			<c:forEach items="${searchList}" var="search" varStatus="status">
					<tr bgcolor="#f1f1f1">
						<td><form:radiobutton id="accessRadio"  path="selected" value="${search.opportunityID}_${search.solutionID}"/></td>
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
			
			<tr>
					<td  colspan="10" class="tdButtonRow" nowrap="nowrap">
						<a class="TabsCommonButtons" onclick="javascript:changeApprover();" style="width:100px;">Change Approver</a>
					</td>
				</tr>
	
		</div>
	</form:form>
            
           <!--  </div> -->
            
            
 <!---------------------------------------Tab 1 (Oppurtunity Details DIV END from Here)--------------------------------------------->
           
 <!---------------------------------------Tab 2 (Location Profile DIV Start from Here)--------------------------------------------->  
 	<form:form id="approvalDataForm" method="post" commandName="initiateApprovalProcessDTO">
	<div id="messageDiv" class="message" style="color:red;border:1px solid red;display:none;"></div>         
            <div style="display: none;" id="tab2" class="subminApprovalBodyArea">           
            <div class="adminDivdHeaderr">Open Solutions </div>	
		
			<table id="dataTable2" width="1098px;" border="0" align="center" cellpacing="1" style="margin-left: 6px;font-size: 10px;">		
			<thead>		
			<tr class="subminApprovalTdProperty">
					<td class="tdSubTableHead">Select</td>
					<td class="tdSubTableHead" nowrap="nowrap">Opportunity Name</td>
					<td class="tdSubTableHead" nowrap="nowrap">Solution Name</td>
					<td class="tdSubTableHead" nowrap="nowrap">Solution Submitted Date</td>
					<!-- <td class="tdSubTableHead" nowrap="nowrap">Deal Value</td>
					<td class="tdSubTableHead">DBR</td> -->
					<!-- <td class="tdSubTableHead">Status</td> -->
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${allApprovalRequests}" var="search" varStatus="status">
					<tr bgcolor="#f1f1f1">
						<td><form:radiobutton id="accessRadio"  path="selected" oppId="${search.opportunityId}" solId="${search.solutionId}"/></td>
						<td style="color:black;">${search.opportunityName}</td>
						<td style="color:black;">${search.solutionName}</td>
						<td style="color:black;">${search.submittedDate}</td>									
					</tr>					
				</c:forEach>				
			</tbody>	
		</table>				
		<tr>
			<td  colspan="10" class="tdButtonRow" nowrap="nowrap">
				<a class="TabsCommonButtons" id="submit" target="_blank"  type="submit" style="width:100px;">VIEW DETAILS</a>
			</td>
		</tr>
		</div>
		</form:form>
            
  <!---------------------------------------Tab 2 (Location Profile DIV END from Here)--------------------------------------------->    
 </div>					
</div>
			
			<%-- <div>
				<jsp:include page="footer.jsp" />
			</div> --%>
	</div>
<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>
