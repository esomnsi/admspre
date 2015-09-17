<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Search User</title>

<link href="../css/it_mssp.css" rel="stylesheet" type="text/css" />
<link href="../css/main.css" rel="stylesheet" type="text/css" />

 <script type="text/javascript" src="../js/jquery-1.8.2.js"> </script>

<script src="../js/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="../js/jquery.multiSelect.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery.dataTables.js"></script>

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
		"group.jsp", 'popUpWindow', 'height=300,width=700,left=10,top=10,resizable=yes,scrollbars=1,toolbar=no,menubar=no,location=no,directories=no,status=no')
		popupWindow.document.title = "Search User";
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

	function searchUser(){
		var ot = "${otype}";
		if(!isSingleDataAvailable()){
			document.getElementById("messageDiv").style.display = "block";
			document.getElementById("messageDiv").innerHTML = "Please provide atleast one field to search.";
			return false;
		}		
		document.forms[0].action="/ADM_PRE/opportunity/searchUser?param="+ot;
		document.forms[0].submit();
	}
	
	
	function setVal(){
		var owt = "${ownerType}";
		
		var val = $('input:radio[id=accessRadio]:checked').val();
		var ownerName = window.opener.document.getElementById(owt);
		ownerName.value = val;
		if(owt=='opportunityowner'){
			//for Region KAM
			window.opener.document.getElementById("rk").value = val; 
		}
		//self.close();
		window.close();
	}


</script>



<form:form id="manageAccessForm" method="post" commandName="userAccessDTO">

	<div id="messageDiv" class="message" style="color:red;border:1px solid red;display:none;"></div>
	<c:if test="${not empty noRecords }">
		<div id="messageDiv" class="message" style="color:red;border:1px solid red;">
			${noRecords}
		</div>
	</c:if>
	<div id="mainBody" class="bodyCss">
	
		  
		 
          
              
            
            
<!---------------------------------------Tab 1 (Oppurtunity Details DIV Start from Here)--------------------------------------------->
		
            <div style="display: block;height: 350px;width: 1110px;" class="subminApprovalBodyArea">
            	<table style="width:100%;">
            	<tr><td class="tdHeaderLabel" colspan="4"> Search User </td></tr>
            	
            	<tr><td class="tdLabel"> First Name : </td> <td><form:input id="firstName" type="text" path="firstName" value="${firstName}" ></form:input></td>
            		<td class="tdLabel"> Last Name : </td> <td><form:input id="lastName" type="text" path="lastName" value="${lastName}"	></form:input>
            		</td>
            	</tr> 
            	
            	<tr><td class="tdLabel"> Signum Id : </td> <td><form:input id="signumId" type="text" path="signumId" value="${signumId}"	></form:input></td>
            		<td class="tdLabel"> Email : </td> <td><form:input id="emailId" type="text" path="emailId" value="${emailId}" ></form:input>
            		</td>
            	</tr> 
              
	          
	              
	              <tr><td class="tabsDefineScoprButtons" style="text-align: right;" colspan="4"> 
	              		<a id="searcUser" class="TabsCommonButtons" onClick="javascript:searchUser();" 	target="_blank" >Search</a>
	                <a id="clear" class="TabsCommonButtons" target="_blank" >Clear</a>       
	              </td>
	              </tr>
	           </table>
	              
              <br>
			  <div id="data">
              <table id="dataTable1" border="0" align="center" style="width: 1100px;margin-left: 6px;">		
				<thead>		
				<tr>
					<td class="tdSubTableHead"></td>
					<td class="tdSubTableHead">First Name</td>
					<td class="tdSubTableHead">Last Name</td>
					<td class="tdSubTableHead">Signum Id</td>
					<td class="tdSubTableHead">Email Id</td>			
				</tr>
				</thead>
				<tbody>
					<c:forEach var="search" items="${searchResult}">
					<tr class="tdOddRow">
						<td width="2%"><form:radiobutton id="accessRadio"  path="selected" value="${search.firstName} ${search.lastName} (${search.signumId})"/></td>
						<!-- <td><input type="radio" name="a" value="" /></td> -->
						<td width="10%">${search.firstName}</td>
						<td width="12%">${search.lastName}</td>
						<td width="7%">${search.signumId}</td>
						<td width="15%">${search.emailId}</td>
					</tr>
					</c:forEach>
				</tbody>	
			</table>		
			</div>	
			
			<div class="tabsDefineScoprButtons">
               	<a id="sub" class="TabsCommonButtons" onClick="javascript:setVal()">Select</a>
            </div>
       
              
            </div>
          </div>
                
    
						

</form:form>