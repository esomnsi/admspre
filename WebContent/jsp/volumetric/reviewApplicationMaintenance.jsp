<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>
<link href="../css/demo_table.css" rel="stylesheet" type="text/css" />
<link href="../css/demo_table_jui.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jquery-ui-1.7.2.custom.css" rel="stylesheet"	type="text/css" media="all" />

<title>Application Maintenance Review</title>
<style type="text/css">
.myDiv {
	-webkit-border-radius: 5px 5px 5px 5px; 
	display: block; 
	-moz-border-radius: 5px 5px 5px 5px; 
	/* border: 1px solid #083369; */
	border: 1px solid #000000;
	font-size: 10px;
	font-family: arial;
	color: #083359;
	font-weight: bold;
	width: 29cm;
	position: absolute;
	left: 95px;
	background-color: #eee;
}


</style>
<script type="text/javascript">
var aTable;
var bTable;
var cTable;
var dTable;
$(document).ready(function() {
	
	$('#prevAppMain').click(function() {
	    var url = "<%=request.getContextPath()%>/solution/productEstimation";    
    	$(location).attr('href',url);
	});
	
	$('#nextAppMain').click(function() {
		var url = "<%=request.getContextPath()%>/solution/productAppTesting";
		$(location).attr('href',url);
	});
	
	$('#back').click(function() {
	    var url = "<%=request.getContextPath()%>/solution/applicationMaintenanceNew";    
    	$(location).attr('href',url);
	});

aTable = $('#dataTbl0').dataTable({
	
	'sDom': 't' ,
	"bInfo": false,
	"bFilter" : false,
	"bPaginate" : false,
	"bJQueryUI" : true,
	"bSort" : false,
		});
	
bTable = $('#dataTbl1').dataTable({
	'sDom': 't' ,
	"bInfo": false,
	"bFilter" : false,
	"bPaginate" : false,
	"bJQueryUI" : true,
	"bSort" : false,
		});
		
cTable = $('#dataTbl2').dataTable({
	'sDom': 't' ,
	"bInfo": false,
	"bFilter" : false,
	"bPaginate" : false,
	"bJQueryUI" : true,
	"bSort" : false,
	});
	
dTable = $('#dataTbl3').dataTable({
	'sDom': 't' ,
	"bInfo": false,
	"bFilter" : false,
	"bPaginate" : false,
	"bJQueryUI" : true,
	"bSort" : false,
	});
	
eTable = $('#servScopeDataTbl').dataTable({
	'sDom': 't' ,
	"bInfo": false,
	"bFilter" : false,
	"bPaginate" : false,
	"bJQueryUI" : true,
	"bSort" : false,
	}); 
	

});
</script>
<br>
<div id="mainBody" class="bodyCss">
	<div id="bodyDiv" class="mainBodyDivwithoutLine"><br>
	<!-- <div class="tdHeaderLabel" style="font-weight:bold; margin-bottom:4px;margin-left:1px;">Application Maintenance Review</div> -->
	<div class="adminDivdHeaderr" style="color: #000000">Application Maintenance Review</div><br><br><br>
	<!-- Table start -->
	<div align="center">
	<table id="" style="margin-left: 6px; font-size: 10px; width: 400px;" class="tablepanel" border="0">
					<thead>
						<tr>
							<td class="tdSubTableHead" width="82%" style="font-weight: normal;font-size: small;">Service Element</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">TotalBaseFTE</td>
							<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">TotalBaseHours</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${servScopeData}" var="beanObject" varStatus="status">
						
							<tr bgcolor="fdf7e7">											
								<td style="color: black;">${beanObject.servScopeName}</td>
								<td style="color: black;">${beanObject.totalBaseFTE}</td>
								<td style="color: black;">${beanObject.totalBaseHours}</td>		
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	<!-- Table end -->
		<c:forEach items="${componentMap}" var="entry" varStatus="count">			
			<c:set var="stringArr" value="${fn:split(entry.key,'_')}" />
			<div class="adminDivdHeaderr2" style="color: #000000">${stringArr[0]}</div><br><br>
			<%-- <div class="tdHeaderLabel" style="width:1096px;margin-bottom:4px;margin-left:1px;">${entry.key}</div> --%>
			<div>
			<table <%-- id="dataTbl${count.index}" --%> width="1098px;" border="0" align="center" cellpacing="1" style="margin-left: 6px; font-size: 10px;" class="tablepanel">
				<thead>
					<tr class="subminApprovalTdProperty">
						<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Support Activity</td>
						<td class="tdSubTableHead" nowrap="nowrap" style="font-weight: normal;font-size: small;">Priority/Severity</td>
						<td class="tdSubTableHead" nowrap="nowrap" style="font-weight: normal;font-size: small;">Total Yearly Ticket</td>
						<td class="tdSubTableHead" nowrap="nowrap" style="font-weight: normal;font-size: small;">Base Effort (in Hrs)</td>
						<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Base Effort (in PD)</td>
						<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Customer Base factor</td>
						<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">CBIO 3PP Nodes Factor</td>
						<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Call Volume Factor</td>
						<td class="tdSubTableHead" style="font-weight: normal;font-size: small;">Total PD</td>
						<!-- <td class="tdSubTableHead">Total Base Effort(Hrs)</td>
						<td class="tdSubTableHead">Total Base FTE</td> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${entry.value}" var="listMap">
						<tr bgcolor="fdf7e7">
					   		<c:forEach items="${listMap}" var="mapItem">
					   		<%-- <c:set var="stringArr1" value="${fn:split(mapItem.key,'_')}" />
					   			<td>${stringArr1[0]}</td> --%>
					 			<td>${mapItem.key}</td>
					 			<td>
						 			<c:forEach items="${mapItem.value}" var="obj">
									 	${obj.priority}<br>
									 </c:forEach>
					 			</td>
					 			<td>
						 			<c:forEach items="${mapItem.value}" var="obj">
									 	${obj.totalYearlyTicket}<br>
									 </c:forEach>
					 			</td>
					 			<td>
						 			<c:forEach items="${mapItem.value}" var="obj">
									 	${obj.baseEffortHrs}<br>
									 </c:forEach>
					 			</td>
					 			<td>
						 			<c:forEach items="${mapItem.value}" var="obj">
									 	${obj.baseEffortPd}<br>
									 </c:forEach>
					 			</td>
					 			<td>
						 			<c:forEach items="${mapItem.value}" var="obj">
									 	${obj.customerBaseFactor}<br>
									 </c:forEach>
					 			</td>
					 			<td>
						 			<c:forEach items="${mapItem.value}" var="obj">
									 	${obj.nodesFactor}<br>
									 </c:forEach>
					 			</td>
					 			<td>
						 			<c:forEach items="${mapItem.value}" var="obj">
									 	${obj.callVolumeFactor}<br>
									 </c:forEach>
					 			</td>
					 			<td>
						 			<c:forEach items="${mapItem.value}" var="obj">
									 	${obj.totalPD}<br>
									 </c:forEach>
					 			</td>
					 			<%-- <td>${stringArr1[1]}</td>
					 			<td>${stringArr1[2]}</td> --%>
					 		</c:forEach>
						   
						</tr>
					</c:forEach>								
				</tbody>
			</table></div>
			<div class="myDiv">	
				<table width="100%">
					<tr>
						<td width="50%" align="left" style="text-align: left;text-indent: 5px;color: #000000">Total :</td>
						<td width="50%" align="right" style="text-align: left;text-indent: 13cm;color: #000000">${stringArr[1]}</td>
					</tr>
				</table>
			</div>
				<br><br>
		</c:forEach>
	</div>
</div>
<div style="margin-top: 20px; float: right">
		<table width="100%">
			<tr bgcolor="#f7f7f7">
				<td class="tdButtonRow" align="right" bgcolor="#f7f7f7">					
					<a id="nextAppMain" class="TabsCommonButtons">Next</a> 
					<a id="back" class="TabsCommonButtons">Back</a>
					<a id="prevAppMain" class="TabsCommonButtons">Previous</a>
				</td>
			</tr>
		</table>
	</div>
