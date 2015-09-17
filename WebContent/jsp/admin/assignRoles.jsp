<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Select Approver</title>
<script src="../js/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="../js/jquery.multiSelect.js" type="text/javascript"></script>
<script src="../js/jquery.blockUI.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>

<link href="../css/jquery.multiSelect.css" rel="stylesheet"	type="text/css" />
<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css"  rel="stylesheet" type="text/css" media="all" />

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
    var sel = new Array();
    var prev;
    var table;
    var roles;
    
	$(document).ready(function() {		
	
		// With callback
		 var multi = $("select").multiSelect(null, function(el) {			
			$("#callbackResult").show().fadeOut();
		});
		
			
		var roleList = new Array();
		var previousRow = -1;
		
		 $('#manageAccessForm input[type="checkbox"]').change(function(e){			 		
			 		if(previousRow!=selectedRow){			 			
			 			roleList.length = 0;
			 		}
			 		if($(this).is(":checked")){			 		
			 			roleList.push($(this).val());
			 		}else{
			 			var index = roleList.indexOf($(this).val());
			 			roleList.splice(index, 1);
			 		}	
			 		previousRow = selectedRow;
			 		roles = roleList;			 		
			});		 
	});
	
	
	
	
	$().ready(function () {
	    $('.collapse').collapsiblePanel();
	});
	
	var progressIndicator = true;
	function isSingleDataAvailable(){		
		var firstName = document.getElementById("firstName").value;
		var lastName = document.getElementById("lastName").value;
		var emailId = document.getElementById("emailId").value;
		var signumId = document.getElementById("signumId").value;
		
		if((firstName == "") &&  (lastName == "") && (emailId == "") && (signumId == "")){
			progressIndicator = false;
			return false;
		}else{
			progressIndicator = true;
			return true;
		}
		
	}
	
	
	function searchUser(){		
		if(!isSingleDataAvailable()){
			document.getElementById("messageDiv").style.display = "block";
			document.getElementById("messageDiv").innerHTML = "Please provide atleast one field to search.";
			//alert(1);
			return false;
		}		
		document.forms[0].action="/ADM_PRE//admin/searchUser";
		document.forms[0].submit();
	}
	
	
	function hideMessageDiv(){
		document.getElementById("messageDiv").style.display = "none";
	}
	
	
	$(document).ready(function() {
	 var oTable = $('#dataTable').dataTable( {			
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
	 
	 table = oTable;
	 
	 
	 $('#dataTable tbody').delegate("tr", "click", rowClick); 
	 	
	 $('#save').click(function() {		
		
		 roleData = roleData + "|" + roles;		 // adding roles to data		
		
		if(roles == "undefined" || roles== '' || roles == null){
			alert("Please select atleast one role.");
			return false;
		}
		 $.ajax({
				type : "POST",
				url : "./saveAssignedRole",
				data : {"roleData": roleData},
				datatype : "text",
				success : function(response){
					alert("Roles Updated Succesfully.");
				},
				error : function(e) {
					alert("Operation failed.");
				}
			});			 	
		 
		 });		 		
	 
		 $("#clear").click(function() {
			 document.getElementById("firstName").value = "";
			 document.getElementById("lastName").value = "";
			 document.getElementById("signumId").value = "";
			 document.getElementById("emailId").value = "";
		});	 
		 
		 
		
		  
		 
	 		
	});
	
	
	var hlr = 0;
	var selectedRow;	
	var roleData;
	function rowClick()
	{
	   if (hlr)
	      $("td:first", hlr).parent().children().each(function(){$(this).removeClass('markrow');});
	   hlr = this;
	   $("td:first", this).parent().children().each(function(){$(this).addClass('markrow');});

	   var iPos = table.fnGetPosition( this );
	   selectedRow = iPos;
	 
	   // You can pull the values out of the row here if required
	  
	   var firstName = $("td:eq(1)", this).text();
	   var lastName = $("td:eq(2)", this).text();
	   var signum = $("td:eq(3)", this).text();
	   var email = $("td:eq(4)", this).text();
	   var contact = $("td:eq(5)", this).text();
	   
	   var data = firstName + "|" + lastName + "|" + signum + "|" + email + "|" + contact;	 	
	   roleData = data;
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
	
	
			
</script>


<form:form id="manageAccessForm" method="post" commandName="userAccessDTO">
<div id="bodyDiv" class="mainBodyDiv1" onload="hideMessageDiv();">
	<div id="messageDiv" class="message" style="color:red;border:1px solid red;display:none;"></div>
	<table width="70%" border="0" align="center">
		<tr>
			<td class="tdHeaderLabel" colspan="4">Search People</td>
		</tr>
		<tr>
			<td class="tdLabel" width="25%">First Name :</td>
			<td class="tdInputBox">
				<form:input id="firstName" type="text" path="firstName" value="${firstName}"
				class="textBoxMedium"></form:input></td>
			<td class="tdLabel">Last Name :</td>
			<td class="tdInputBox"><form:input id="lastName" type="text" path="lastName" value="${lastName}"
				class="textBoxMedium"></form:input></td>
		</tr>
		<tr>
			<td class="tdLabel">Signum Id :</td>
			<td class="tdInputBox"><form:input id="signumId" type="text" path="signumId" value="${signumId}"
				class="textBoxMedium"></form:input></td>
			<td class="tdLabel">Email :</td>
			<td class="tdInputBox"><form:input id="emailId" type="text" path="emailId" value="${emailId}"
				class="textBoxMedium"></form:input></td>
		</tr>
		<tr>
			<td align="center" colspan="4" class="tdButtonRow"><a id="clear"
				class="button" target="_blank" >Clear</a> <a id="searcUser" class="button" onClick="javascript:searchUser();showProgress();" 
				target="_blank" >Search</a>
		</tr>
		<tr>
			<td width="9%">&nbsp;</td>
		</tr>
	</table>
	
	<div class="tdHeaderLabel">Assign Roles</div>
	
	
	<table id="dataTable" width="100%" border="0" align="center" cellpacing="1">
		
		<thead>		
		<tr>
			<td class="tdSubTableHead"></td>
			<td class="tdSubTableHead">First Name</td>
			<td class="tdSubTableHead">Last Name</td>
			<td class="tdSubTableHead">Signum Id</td>
			<td class="tdSubTableHead">Email Id</td>			
			<td class="tdSubTableHead">Contact No</td>
			<td class="tdSubTableHead">Assign Roles</td>

		</tr>
		</thead>
		<tbody>
		<c:forEach var="search" items="${searchResult}">
		<tr class="tdOddRow">
			<td><form:radiobutton id="accessRadio"  path="selected" value="${search.id}"/></td>
			<!-- <td><input type="radio" name="a" value="" /></td> -->
			<td>${search.firstName}</td>
			<td>${search.lastName}</td>
			<td>${search.signumId}</td>
			<td width="1%">${search.emailId}</td>
			<!-- <td class="">-</td>
			<td >-</td> -->
			<td width="1%;">${search.contactNumber}</td>
			<%-- <td width="1%;">
			 	<form:select id="control_3"  name="control_3[]" path="roleList" style="width: 80px;" multiple="true">
					<form:options items="${roleList}" itemLabel="roleName" itemValue="applicationRoleId"/>												
				</form:select>
			</td> --%>
		</tr>
		</c:forEach>
		</tbody>	
	</table>
	<table id="tLower" style="margin-left: 90%;">
		<tr>
			<!-- <td align="center" colspan="8" class="tdButtonRow" ><a s
						class="button" target="_blank" style="margin-right: -80px;">Save</a>  -->
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>

			<td align="center" colspan="10" class="tdButtonRow">
			<a  id="reset" class="button"   target="_blank">Reset</a>
			<a class="button" id="save" target="_blank"  type="submit">Save</a></td>
		</tr>
		</table>
</div>


</form:form>