<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
$(document).ready(function(){
	$("#close").click(function(){
		window.close();
	});
$("#updateRateData").click(function(){
	var rate = $("#newRate").val();
	/* var modelType = $("input[type='radio']:checked").val(); */
	var values=rate+"-"+'${rowId}'+"-"+${countryID}+"-"+'${jobRole}'+"-"+'${jobStage}'+"-"+'${gsc}';
	$.ajax({
        url : "/ADM_PRE/admin/rateCardUpdate",
        type : "post",
        cache: false,
        datatype : "text",
        data : "values="+values,
        success : function(resp) {
     	    alert(resp);
     	   window.close();
			},
		error : function(resp) {
				alert("update failed");
			}
    });
	
	
	
	
});
});
</script>

</head>
<body>
<br>
<br>

	<form id="rateManagementForm"  method="POST" action="updateRateCard">
	<div style="display: block; width:99%; " id="popUpId2" class="tabBodyArea">
	<!-- <table>
		<tr> -->
		<table width="98%" cellpadding="1" cellspacing="2" class="tablepanel" style="margin-bottom:3px;" bgcolor="#FFFFFF";>
                  <tr bgcolor="#efefef" >
			<td>
				Old Rate  
			</td>
			<td>
				<input  type="text" class="textBoxSmallNumeric" value="${rate}" readonly="readonly">
			</td>
		</tr>
		<tr bgcolor="#efefef" >
			<td>
				Job Role  
			</td>
			<td>
				<input  type="text" class="textBoxSmallNumeric" value="${jobRole }" readonly="readonly">
			</td>
		</tr>
		<tr bgcolor="#efefef" >
			<td>
				Job Stage  
			</td>
			<td>
				<input  type="text" class="textBoxSmallNumeric" value="${jobStage }" readonly="readonly">
			</td>
		</tr>
		<tr bgcolor="#efefef" >
			<td>
				GSC  
			</td>
			<td>
				<input  type="text" class="textBoxSmallNumeric" value="${gsc }" readonly="readonly">
			</td>
		</tr>
		<tr bgcolor="#efefef" >
			<td>
				Enter New Rate  
			</td>
			<td>
				<input id="newRate" type="text" class="textBoxSNumric" >
			</td>
		</tr>
	 </table>
	
<table width="98%" cellpadding="1" cellspacing="2"  ;" >
				<tr >
				<td height="20" >
	              	<a id="updateRateData"	class="TabsCommonButtons">Update</a> 
					<a id="close" class="TabsCommonButtons">Close</a>
				</td>
				</tr>
				</table>
	
<!-- 	<a id="updateRateData"	class="TabsCommonButtons">Update</a> 
	<a id="close" class="TabsCommonButtons">Close</a> -->
	</div>
	</form>


	

</body>
</html>