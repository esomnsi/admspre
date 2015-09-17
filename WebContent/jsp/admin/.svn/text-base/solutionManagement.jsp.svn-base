<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css"  rel="stylesheet" type="text/css" media="all" />

<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>

<title>Solution Management</title>
<script type="text/javascript">
	var oTable;
	var vTable;
	$(document).ready(function() {
		oTable = $('#dataTable').dataTable({
			"sPaginationType" : "full_numbers",
			"bJQueryUI" : true,
			"bSort" : true,
			"fnDrawCallback" : function() {
				$('#dataTable tr').click(function(event) {
					$(this).find('input[type=radio]').prop('checked', true);
				});
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
		//$('#dataTable tbody').delegate("tr", "click", rowClick);

		$('#LForm input:radio').click(function() {
			$("#dataTable option:selected").removeAttr("selected");
		});
		
	

	});

	var selectedRow = -1;
	var previousSelected = -1;
	function rowClick() {
		var iPos = oTable.fnGetPosition(this);
		selectedRow = iPos;
	}

	function MM_jumpMenu(targ, selObj, restore) {
		eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value
				+ "'");
		if (restore)
			selObj.selectedIndex = 0;
	}
	function clearForm() {
		document.getElementById("oppname").value = "";
		document.getElementById("cname").value = "";
		document.getElementById("selectregion").selectedIndex = 0;
	}
	function searchOpportunities() {
		document.forms[0].action = "/ADM_PRE/admin/listOpportunities";
		document.forms[0].submit();
	}
	
	function searchSolutions() {
		/* document.forms[1].action = "../admin/listSolutions";
		document.forms[1].submit(); */
		var custName = $("#custname").val();
		var oppName = $("#opname").val();
		var region = $("#region").val();
		
		if(custName=="" && oppName=="" && region==""){
			document.getElementById("msgDiv").style.display = "block";
			document.getElementById("msgDiv").style.color = "red";
			document.getElementById("msgDiv").innerHTML = "Please provide atleast one value to perform search.";
			return false;
		}
		
		
		showProgress();
		var dataString = oppName + "," + custName + "," + region;
		
		$.ajax({
			type : "POST",
			url : "./listSolutions",
			data : {"dataString": dataString},
			datatype : "text",
			success : function(html){
				hideProgress();
				$("#deleteSolutionDiv").html(html);
			},
			error : function(e) {
				hideProgress();
			}
		});
	}
	
		

	function viewRecord() {
		document.forms[0].action = "/ADM_PRE/admin/viewRecordsToReAssign";
		document.forms[0].submit();
	}
	
	function deleteSolution() {
		var solId=0;
	 	$("input[type='radio']").each(function() {
			if($(this).attr("checked")){
            	solId=$(this).attr("solId");
            	alert(solId);            	//break;
            }
		});
		
		/* document.forms[0].action = "../admin/deleteSolution";
		document.forms[0].submit(); */
	}
	
	
	var progressIndicator = true;
	function isSingleDataAvailable() {
		var firstName = document.getElementById("oppname").value;
		var lastName = document.getElementById("cname").value;
		var region = document.getElementById("selectregion").value;

		if ((firstName == "") && (lastName == "") && (region == "")) {
			document.getElementById("showError").style.display = 'block';
			document.getElementById("showError").innerHTML = "<ul><li>Please select any one of opportunity or customer name or region</li></ul>";
			return false;
		} else {
			searchOpportunities();
			return true;
		}

	}

	function myFunction() {
		previousSelected = selectedRow;
	}
	
	window.onload = reSet;
	 function reSet(){
		 document.getElementById("accessRadio1").checked = true;
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

	function enableListBox(obj) {
		if (obj.value == 1) {
			document.getElementById("solType").disabled = false;
		} else {
			document.getElementById("solType").disabled = true;
		}

	}

	function showTable(what) {
		if (document.getElementById(what).style.display == "none") {
			document.getElementById(what).style.display = "block";
		} else if (document.getElementById(what).style.display == "block") {
			document.getElementById(what).style.display = "none";
		}
	}
	
	function hideMessages(){
		document.getElementById("messageDiv").style.display = "none";
		document.getElementById("showError").style.display = "none";
	}
	
	
	function showAssignSolutionDiv(){
		 $("#deleteSolutionDiv").hide(1000);
		 $("#assignSolutionDiv").show(1000);
	}
	
	function showDeleteSolutionDiv(){
		 $("#assignSolutionDiv").hide(1000);
		 $("#deleteSolutionDiv").show(1000);
	}

	function popup(mylink, windowname) {
		if (!window.focus)
			return true;
		var href;
		if (typeof (mylink) == 'string')
			href = mylink;
		else
			href = mylink.href;
		window.open(href, windowname, 'width=400,height=200,scrollbars=yes');
		return false;
	}
	function newPopup() {
		popupWindow = window
				.open(
						"group.jsp",
						'popUpWindow',
						'height=300,width=700,left=10,top=10,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no,status=no')
	}

	
	function uploadFile(ele){
		var p = document.getElementById(ele).value;
		$.ajax({
			type : "POST",
			url : "/ADM_PRE/admin/upload",
			data : {'fileName' : p},
			datatype : "text",
			success : function(response){
				if(response == "true"){
				document.getElementById("messageDiv").style.display = 'block';
				 $("#Divmessage").html("Your data has been saved successfully.");
				}
				else{
					document.getElementById("showError").style.display = 'block';
					 $("#showError").html(response);
				}
			},
			error : function(t) {
				
			}
		});
	}
	

</script>
<script type="text/javascript" src="js/jquery-1.js"></script>
<script type="text/javascript" src="js/jquery_003.js"></script>

<link href="it_mssp.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />


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
<%-- </head>

<jsp:include page="header.jsp"/>
<jsp:include page="menu.jsp"/> --%>
<div id="messageDiv" class="message" style="display:none;">
	<c:if test="${not empty successMessage}">
			${successMessage}
		</c:if>
</div>
<div id="Divmessage" class="message" style="display:block;">

</div>
<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;display:block;
	-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;">
		<c:if test="${not empty message}">
			<font color="red" size="2" style="margin-left: 40%;">${message}
		</c:if>
</div>
<div id="mainBody" class="bodyCss">
	<div id="bodyDiv" class="mainBodyDivwithoutLine">
		<div id="bodyDiv" class="mainBodyDiv1"></div>
		<div id="usual1" class="adminTabPanel">
			<ul>
				<li><a class="selected" href="#tab1"><span></span>Assign/Delete Solution</a></li>
				<!-- <li><a class="" href="#tab2" onclick="hideMessages();"><span></span>Region country Mgt</a></li> -->
				<!-- <li><a class="" href="#tab3" onclick="hideMessages();"><span></span>Currency Mgt </a></li>
				<li><a class="" href="#tab4" onclick="hideMessages();"><span></span>Exchange Rate Mgt</a></li>
				<li><a class="" href="#tab5" onclick="hideMessages();"><span></span>Rate card Mgt</a></li> -->
				<!-- <li><a class="" href="#tab6" onclick="hideMessages();"><span></span>Parameter Mgt</a></li> -->
			</ul>

			<!---------------------------------------Tab 1 (Oppurtunity Details DIV Start from Here)--------------------------------------------->

		<div id="bodyDiv" class="mainBodyDiv1">
		
			 <div style="display: block;" id="tab1" class="subminApprovalBodyArea">
			 	
			 	<div style="text-align: left;font-size: 13px;font-style:italic;color:#b16400;margin-left: 13px;" class="subminApproval">Please Select an Option :
					<input type="radio" id="accessRadio1" path="selected"  name="radio" checked="checked" onclick="showAssignSolutionDiv();"> Assign Solution		
					<input type="radio" id="accessRadio2" path="selected"  name="radio" onclick="showDeleteSolutionDiv();"> Delete Solution
				</div>			 	
				
				<div id="assignSolutionDiv" style="margin-top: 20px;">		
					<form:form id="LForm" commandName="searchDTO" method="POST" >		
					<div class="adminDivdHeaderr" >Assign Solution</div>	 		
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
	              <div class="tabsDefineScoprButtons" style="margin-left: 120px;margin-top: 10px;margin-bottom: 15px;">
	               	<a id="searcUser" class="TabsCommonButtons" onClick="javascript:searchOpportunities();showProgress();" 	target="_blank" >SEARCH</a>
	                <a id="clear" class="TabsCommonButtons" target="_blank" onclick="javascript:clearForm()">CLEAR</a>             
	              </div>
	             <!--  <div class="adminDivdHeaderr">Manage Solutions</div> -->
						<table id="dataTable" width="1098px;" border="0" align="center" cellpacing="1" style="margin-left: 6px;font-size: 10px;">
							<thead>
								<tr class="subminApprovalTdProperty">
									<td class="tdSubTableHead">Select</td>
									<td class="tdSubTableHead" nowrap="nowrap">Opportunity ID</td>
									<td class="tdSubTableHead" nowrap="nowrap">Opportunity Name</td>
									<td class="tdSubTableHead" nowrap="nowrap">Customer Name</td>
									<td class="tdSubTableHead">Region</td>
									<td class="tdSubTableHead">Assigned To</td>
									<td class="tdSubTableHead">Assign To</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${searchList}" var="search" varStatus="status">
									<tr bgcolor="#f1f1f1">
										<td><form:radiobutton id="accessRadio" path="selected" value="${search.opportunityID}" solId="${search.solutionID}" /></td>
										<td style="color: black;">${search.opportunityID}</td>
										<td style="color: black;">${search.opportunityName}</td>
										<td style="color: black;">${search.customerName}</td>
										<td style="color: black;">${search.region}</td>
										<td style="color: black;">${search.currencyCode}</td>
										<td style="color: black;" width="10%;"><form:select	path="currencyCode" id="assignedto" class="textBoxM">
											<form:option value="" label="--Select--" />
												<form:options items="${representatives}" itemValue="signumId" itemLabel="signumId" />
											</form:select></td>
										</tr>
								</c:forEach>
							</tbody>
							</table>							
							<tr>
								<td colspan="10" class="tdButtonRow" nowrap="nowrap"><a	class="TabsCommonButtons" onclick="javascript:viewRecord();">Submit</a>
								</td>
							</tr>
							</form:form>
						</div>
						
						<div id="deleteSolutionDiv" style="display: none;margin-top: 20px;">						
							<jsp:include page="listSolutions.jsp"></jsp:include>						
						</div>
				
						</div>
						</div>
					
				<!-- </div>
			</div> -->
			<!---------------------------------------Tab 1 (Oppurtunity Details DIV END from Here)--------------------------------------------->



			<!---------------------------------------Tab 2 (Location Profile DIV Start from Here)--------------------------------------------->
			<div style="display: none;" id="tab2" class="subminApprovalBodyArea">
				<div class="adminTitle">				
					<div class="adminDivdHeaderr">Upload Your Files</div>
					<h3 style="width:110px;margin-top: 10px;">Select File :</h3>
					<span><input name="" id="regionCountry" type="file" style="margin-top: 10px;"/></span>
					<div style="margin-left: 0px;"><a class="TabsCommonButtons" id="submit" 
					style="width:80px;margin-top: 25px;" onclick="uploadFile('regionCountry');">Upload</a></div>
					</div>					
					<div style="font-size: 13px;font-style:italic;color:#b16400;margin-top: 125px;margin-left:125px;" class="subminApproval">
					* Only .xls, .csv and .txt files are allowed to upload.<br>
					* Maximum file size should be 500MB.
				</div>
			</div>

			<!---------------------------------------Tab 2 (Location Profile DIV END from Here)--------------------------------------------->

			<!---------------------------------------Tab 2 (Location Profile DIV Start from Here)--------------------------------------------->
			<!-- <div style="display: none;" id="tab3" class="subminApprovalBodyArea">
			<div class="adminTitle">				
					<div class="adminDivdHeaderr">Upload Your Files</div>
					<h3 style="width:110px;margin-top: 10px;">Select File :</h3>
					<span><input name="" id="currencyMng" type="file" style="margin-top: 10px;"/></span>
					<div style="margin-left: 0px;"><a class="TabsCommonButtons" id="submit"  
					style="width:80px;margin-top: 25px;" onclick="uploadFile('currencyMng');">Upload</a></div>
				</div>
				<div style="font-size: 13px;font-style:italic;color:#b16400;margin-top: 125px;margin-left:125px;" class="subminApproval">
					* Only .xls, .csv and .txt files are allowed to upload.<br>
					* Maximum file size should be 500MB.
				</div>
			</div> -->

			<!---------------------------------------Tab 2 (Location Profile DIV END from Here)--------------------------------------------->

			<!---------------------------------------Tab 2 (Location Profile DIV Start from Here)--------------------------------------------->
		<!-- 	<div style="display: none;" id="tab4" class="subminApprovalBodyArea">
			<div class="adminTitle">				
					<div class="adminDivdHeaderr">Upload Your Files</div>
					<h3 style="width:110px;margin-top: 10px;">Select File :</h3>
					<span><input name="" id="exchangeRate" type="file" style="margin-top: 10px;"/></span>
					<div style="margin-left: 0px;"><a class="TabsCommonButtons" id="submit"  
					style="width:80px;margin-top: 25px;" onclick="uploadFile('exchangeRate');">Upload</a></div>
				</div>
				<div style="font-size: 13px;font-style:italic;color:#b16400;margin-top: 125px;margin-left:125px;" class="subminApproval">
					* Only .xls, .csv and .txt files are allowed to upload.<br>
					* Maximum file size should be 500MB.
				</div>
			</div> -->

			<!---------------------------------------Tab 2 (Location Profile DIV END from Here)--------------------------------------------->

			<!---------------------------------------Tab 2 (Location Profile DIV Start from Here)--------------------------------------------->
		<!-- 	<div style="display: none;" id="tab5" class="subminApprovalBodyArea">
				<div class="adminTitle">				
					<div class="adminDivdHeaderr">Upload Your Files</div>
					<h3 style="width:110px;margin-top: 10px;">Select File :</h3>
					<span><input name="" id="rateCard" type="file" style="margin-top: 10px;"/></span>
					<div style="margin-left: 0px;"><a class="TabsCommonButtons" id="submit"  
					style="width:80px;margin-top: 25px;" onclick="uploadFile('rateCard');">Upload</a></div>
				</div>
				<div style="font-size: 13px;font-style:italic;color:#b16400;margin-top: 125px;margin-left:125px;" class="subminApproval">
					* Only .xls, .csv and .txt files are allowed to upload.<br>
					* Maximum file size should be 500MB.
				</div>
			</div>	 -->

			<!---------------------------------------Tab 2 (Location Profile DIV END from Here)--------------------------------------------->

			<!---------------------------------------Tab 2 (Location Profile DIV Start from Here)--------------------------------------------->
			<div style="display: none;" id="tab6" class="subminApprovalBodyArea">
			<div class="adminTitle">				
					<div class="adminDivdHeaderr">Upload Your Files</div>
					<h3 style="width:110px;margin-top: 10px;">Select File :</h3>

					<span><input name="" id="parameters" type="file" style="margin-top: 10px;"/></span>
					<div style="margin-left: 0px;"><a class="TabsCommonButtons" id="submit" 
					style="width:80px;margin-top: 25px;" onclick="uploadFile('parameters');">Upload</a></div>

					<!-- <span><input name="" type="file" style="margin-top: 10px;" /></span>
					<div style="margin-left: 0px;"><a class="TabsCommonButtons" id="submit" target="_blank"  type="submit" style="width:80px;margin-top: 25px;">Upload</a></div>
 -->
				</div>
				<div style="font-size: 13px;font-style:italic;color:#b16400;margin-top: 125px;margin-left:125px;" class="subminApproval">
					* Only .xls, .csv and .txt files are allowed to upload.<br>
					* Maximum file size should be 500MB.
				</div>
			</div>

			<!---------------------------------------Tab 2 (Location Profile DIV END from Here)--------------------------------------------->

		</div>
	</div>

</div>
<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>
