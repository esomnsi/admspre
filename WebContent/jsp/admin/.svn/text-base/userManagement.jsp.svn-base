<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Define Scope</title>
<script type="text/javascript">

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


 //Migrating code from assign roles
 
 
  var sel = new Array();
    var prev;
    var table;
    var roles;
    var signumId;
    
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
	 var oTable = $('#dataTable1').dataTable( {			
			 "sPaginationType": "full_numbers",			
			 "bJQueryUI": true,
			 "oLanguage": {
			        "sEmptyTable":     "No records found, kindly perform search with relevant data.",	
			         "sLoadingRecords": "Please wait - loading..."
			    },
			 "fnDrawCallback": function () {
				 $('input:radio[id=accessRadio]').click(function() {
					  var val = $('input:radio[id=accessRadio]:checked').val();		
					  prev = val;
				 })	      
		       
				 $('#dataTable1 tr').click(function(event){
					    $(this).find('input[type=radio]').prop('checked', true);							    
					})
				 
				 $("#dataTable1 tbody td").hover(function() {
			         $(this).parents('tr').addClass('highlight');
			       }, function() {
			         $(this).parents('tr').removeClass('highlight');
			       })
			     
				}	
		});
	 
	 
	 var vTable = $('#dataTable2').dataTable( {			
		 "sPaginationType": "full_numbers",			
		 "bJQueryUI": true,
		 "fnDrawCallback": function () {
			 $('input:radio[id=accessRadio]').click(function() {
				  var val = $('input:radio[id=accessRadio]:checked').val();		
				  prev = val;
			 })	      
	       
		/* 	 $('#dataTable2 tr').click(function(event){
				    $(this).find('input[type=radio]').prop('checked', true);							    
				}) */
			 
			 $("#dataTable2 tbody td").hover(function() {
		         $(this).parents('tr').addClass('highlight');
		       }, function() {
		         $(this).parents('tr').removeClass('highlight');
		       })
		     
			}	
	});
	 
	 table = oTable;
	 
	 
	 $('#dataTable1 tbody').delegate("tr", "click", rowClick1);
	 
	 	
	 /* $('#save').click(function() {		
		
		 roleData = roleData + "|" + roles;		 // adding roles to data		
		
		if(roles == "undefined" || roles== '' || roles == null){
			document.getElementById("messageDiv").style.display = "block";
			document.getElementById("messageDiv").innerHTML = "Please select atleast one record.";
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
		 
		 }); */	
		 
		 
		 $("#save").click(function(){
			 var roles = new Array();
			 var roleData="";
			 // save updated roles....
			$('#rolesDiv input:checked').each(function() {
				roles.push($(this).attr('id'));
			 });			 
			 
			 if(roles.length>0){
				 for(var j=0;j<roles.length;j++){
					 roleData += roles[j] + ",";
				 }				
			 }
		   
			roleData = userData + "$"+ roleData;
			//roleData = roleData.trim();
			roleData = roleData.substring(0, roleData.length - 1);
			showProgress();
			$.ajax({
					type : "POST",
					url : "./saveAssignedRole",
					data : {"roleData": roleData},
					datatype : "text",
					success : function(response){
						$('#rolesDiv').fadeOut('slow');
						hideProgress();
						document.getElementById("messageDiv").style.display = "block";
						document.getElementById("messageDiv").style.color = "green";
						document.getElementById("messageDiv").innerHTML = "User details saved succesfully.";
					},
					error : function(e) {
						// error..
						hideProgress();
						document.getElementById("messageDiv").style.display = "block";
						document.getElementById("messageDiv").style.color = "red";
						document.getElementById("messageDiv").innerHTML = "Error in saving user details.";
					}
				});	
				
			
		 });
	 
		 $("#clear").click(function() {
			 document.getElementById("firstName").value = "";
			 document.getElementById("lastName").value = "";
			 document.getElementById("signumId").value = "";
			 document.getElementById("emailId").value = "";
		});	 
		 
		 $("#reset").click(function() {
			 document.getElementById("approver").checked=false;
			 document.getElementById("solManager").checked=false;
			 document.getElementById("guest").checked=false;
			 document.getElementById("superUser").checked=false;
		});	
		
		  
		 
	 		
	});
	
	
	var hlr = 0;
	var selectedRow;	
	var userData;
	var roles;
	function rowClick1()
	{
	   if (hlr)
	      $("td:first", hlr).parent().children().each(function(){$(this).removeClass('markrow');});
	   hlr = this;
	   $("td:first", this).parent().children().each(function(){$(this).addClass('markrow');});

	   var iPos = table.fnGetPosition( this );
	   selectedRow = iPos;
	 
	   // You can pull the values out of the row here if required
	  document.getElementById("messageDiv").style.display = "none";
	   var firstName = $("td:eq(1)", this).text();
	   var lastName = $("td:eq(2)", this).text();
	   var signum = $("td:eq(3)", this).text();
	   var email = $("td:eq(4)", this).text();
	   var contact = $("td:eq(5)", this).text();
	   
	   var data = firstName + "|" + lastName + "|" + signum + "|" + email + "|" + contact;	 	
	   userData = data;
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
	
	
	function showRolesDiv(e){
		document.getElementById('rolesDiv').style.display = 'block';
		//document.getElementById('rolesDiv').innerHTML = "Assign Roles To :" + e;
	}
	
	function getRolesForUser(signum){	
		signumId = signum;
		$.ajax({
			type : "GET",
			url : "./getRoles",
			data : {"signum": signum},
			datatype : "text",
			success : function(data){
				$('#rolesDiv').fadeIn('slow');
				var arr = new Array();
				document.getElementById("name").innerHTML = "Role Management for " + signum;
				if(data.length==4){
					// all roles aleready assigned..
					document.getElementById("superUser").checked=true;
					document.getElementById("approver").checked=true;
					document.getElementById("solManager").checked=true;
					document.getElementById("guest").checked=true;
				}
				
				if(data.length==0){
					return; // do nothing..
				}
				
				for(var i=0;i<data.length;i++){
				    switch(data[i])
					{
					case 1:
					  document.getElementById("superUser").checked=true;
					  break;
					case 2:
					  document.getElementById("approver").checked=true;
					  break;
					case 3:
					  document.getElementById("solManager").checked=true;
					  break;
					case 4:
					  document.getElementById("guest").checked=true;
					  break;
					default:
					  //
					}
				}
			},
			error : function(e) {
				document.getElementById("messageDiv").style.display = "block";
				document.getElementById("messageDiv").style.color = "red";
				document.getElementById("messageDiv").innerHTML = "Unexpected error occured, try again later.";
			}
		});		
		
	}
	
	
	function getroles(signum){
		document.forms[0].action="/ADM_PRE//admin/getRoles?currentUser="+signum;
		document.forms[0].submit();
	}
	
	function closeIt(ele){
		$('#rolesDiv').fadeOut('slow');
	}
	
	
	function loadAllUser(){
		showProgress();
		$.ajax({
			type : "POST",
			url : "./loadUserList",
			datatype : "text",
			success : function(html){
				hideProgress();
				$("#tab2").html(html);
			},
			error : function(e) {
				hideProgress();
			}
		});	
	}
	



</script>

<link href="../css/it_mssp.css" rel="stylesheet" type="text/css" />
<link href="../css/main.css" rel="stylesheet" type="text/css" />

<script src="../js/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="../js/jquery.multiSelect.js" type="text/javascript"></script>
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

<form:form id="manageAccessForm" method="post" commandName="userAccessDTO">
	<div id="messageDiv" class="message" style="color:red;border:1px solid red;display:none;"></div>
	
	<div id="mainBody" class="bodyCss">
		<div id="bodyDiv" class="mainBodyDivwithoutLine">
		  <div id="bodyDiv" class="mainBodyDiv1"></div>
		  
		  	 
		  
		  <div id="usual1" class="adminTabPanel">
            <ul>
              <li><a class="selected" href="#tab1"><span></span>User Update</a></li>
              <li><a class="" href="#tab2" onclick="loadAllUser();"><span></span>List users</a></li>
            </ul>
            
<!---------------------------------------Tab 1 (Oppurtunity Details DIV Start from Here)--------------------------------------------->
			
            <div style="display: block;" id="tab1" class="subminApprovalBodyArea">
                  <div class="adminTitle">
                	<h3>First Name :</h3>
                    <span><form:input id="firstName" type="text" path="firstName" value="${firstName}" class="inputtextTabs"></form:input></span>
                </div>

            	<div class="adminTitle">
                	<h3> Last Name :</h3>
                    <span><form:input id="lastName" type="text" path="lastName" value="${lastName}"	class="inputtextTabs"></form:input></span>
                </div>
            	<div class="adminTitle">
                	<h3>Signum Id :</h3>
                    <span><form:input id="signumId" type="text" path="signumId" value="${signumId}"	class="inputtextTabs"></form:input></span>
                </div>
                
            	<div class="adminTitle">
                	<h3>Email :</h3>
                    <span><form:input id="emailId" type="text" path="emailId" value="${emailId}" class="inputtextTabs"></form:input></span>
                </div> 
           	  <div class="tabsDefineScoprButtons">
               	<a id="searcUser" class="TabsCommonButtons" onClick="javascript:searchUser();showProgress();" 	target="_blank" >SEARCH</a>
                <a id="clear" class="TabsCommonButtons" target="_blank" >CLEAR</a>             
              </div>
               <div class="adminDivdHeaderr">Assign Roles </div>    
               <div id="rolesDiv" class="adminDivdHeaderr" style="background: white;border:1px solid #BEEC8E;height:170px;width:240px;display:none;margin-left: 420px;">
               			<div id="name"></div> <a href="#" onclick="closeIt('name');" style="margin-left:35px;text-decoration: none;color:red;">X</a><br>
               			<input type="checkbox" id="superUser"> Super User <br>
	               		<input type="checkbox" id="solManager"> Solution Manager <br> 
	               		<input type="checkbox" id="approver"> Approver <br>
	               		<input type="checkbox" id="guest"> Guest <br><br>
	               		 <div class="tabsDefineScoprButtons" style="margin-top: -12px;">
              				  <a  id="reset" class="TabsCommonButtons"   target="_blank">RESET</a>
							<a class="TabsCommonButtons" id="save" target="_blank"  type="submit">SAVE</a>            
              			</div>	               		               		
			   </div>       
			  <div id="data">
              <table id="dataTable1" border="0" align="center" cellpacing="1" style="width: 1100px;margin-left: 6px;">		
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
						<td width="2%"><form:radiobutton id="accessRadio"  path="selected" value="${search.signumId}"/></td>
						<!-- <td><input type="radio" name="a" value="" /></td> -->
						<td width="10%">${search.firstName}</td>
						<td width="12%">${search.lastName}</td>
						<td width="7%">${search.signumId}</td>
						<td width="15%">${search.emailId}</td>
						<!-- <td class="">-</td>
						<td >-</td> -->
						<td width="10%;">${search.contactNumber}</td>
						<td width="5%;">
						 	<%-- <form:select id="control_3"  name="control_3[]" path="roleList" style="width: 80px;" multiple="true">
								<form:options items="${roleList}" itemLabel="roleName" itemValue="applicationRoleId"/>												
							</form:select> --%>
							<a href="#" onclick="getRolesForUser('${search.signumId}');" style="color:blue;">Manage Roles</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>	
			</table>		
			</div>	
          <!--  	  <div class="tabsDefineScoprButtons">
                <a  id="reset" class="TabsCommonButtons"   target="_blank">RESET</a>
				<a class="TabsCommonButtons" id="save" target="_blank"  type="submit">SAVE</a>            
              </div>
 -->
              
            </div>
 <!---------------------------------------Tab 1 (Oppurtunity Details DIV END from Here)--------------------------------------------->
           
 <!---------------------------------------Tab 2 (Location Profile DIV Start from Here)--------------------------------------------->           
            <div style="display: none;" id="tab2" class="subminApprovalBodyArea">            
         	<jsp:include page="userList.jsp"></jsp:include>

     <!--  <div class="tabsDefineScoprButtons">
         <div class="TabsCommonButtons">SAVE</div>
         <div class="TabsCommonButtons">RESET</div>              
     </div> -->

            </div>
            
  <!---------------------------------------Tab 2 (Location Profile DIV END from Here)--------------------------------------------->    
 
          </div>
                
    
						
</div>
</div>
<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>
</form:form>