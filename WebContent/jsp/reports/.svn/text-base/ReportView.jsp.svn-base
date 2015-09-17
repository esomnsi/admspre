<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="css/it_mssp.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/report.js"> </script>

<style type="text/css">      
       .style_1{       		
       		overflow: visible;
       		/* margin-left: 170px; */
       }     
 </style>
</head>

<body>
 <form:form method="get" name='reportSubmit' id='reportSubmit' commandName="report" >
<table id="reports" cellpadding="0" cellspacing="1" class="adminDivdHeaderr" style="width:1111px;margin-left: 1px;">
  <tr bgcolor="#093862" height="22px;">
    <td width="89" align="center" style="color:white;"><b>S. No.</b></td>
    <td width="315" align="center" style="color:white;"><b>Report Name</b></td>
    <td width="608" align="center" style="color:white;"><b>Report Description</b></td>
    <td width="94" align="center" style="color:white;"><b>Action</b></td>
  </tr>
  <tr class="ReportTableBodyPanel">
    <td align="center">1</td>
    <td>FTE Summary Report</td>
    <td>Off/On FTE summary (for all In-scope services)</td>
  <!--   <td align="center"><input type="radio" name="radio" class="reportradio" id="radio1" value="radio" checked="checked"/></td> -->
     <td align="center"><a href="<%=request.getContextPath()%>/report/reportFTESummary">Show</a> </td>
  </tr >
  <tr class="ReportTableBodyPanel">
    <td align="center">2</td>
    <td>Cost Summary Report</td>
    <td>Cost optics (both Labor/Non Labor &amp; hourly cost rates) against all In-scope services</td>
     <td align="center"><a href="<%=request.getContextPath()%>/report/reportCostSummary">Show</a> </td>
  </tr >
  <tr class="ReportTableBodyPanel">
    <td align="center">3</td>
    <td>A-D-R Report</td>
    <td>Assumption Dependency & Risk Report</td>
    <td align="center"><a href="<%=request.getContextPath()%>/report/viewRiskReport">Show</a> </td>
  </tr >
 <%--  <tr class="ReportTableBodyPanel">
    <td align="center">4</td>
    <td>Assumption Report</td>
    <td>Assumption Report</td>
    <td align="center"><a href="<%=request.getContextPath()%>/report/viewAssumptionReport">Show</a> </td>
    
  </tr >
  <tr class="ReportTableBodyPanel">
    <td align="center">5</td>
    <td>Dependency Report</td>
    <td>Dependency Report</td>
    <td align="center"><a href="<%=request.getContextPath()%>/report/viewDependencyReport">Show</a> </td>
    <!-- <td align="center"><input type="radio" name="radio" class="reportradio" id="radio3" value="radio" /></td> -->
  </tr >
 --%>
  <tr class="ReportTableBodyPanel">
    <td align="center">4</td>
    <td>Staffing Pyramid Report</td>
    <td>Staffing Pyramid Report </td>
     <td align="center"><a href="<%=request.getContextPath()%>/report/viewStaffPyramid">Show</a> </td>
  </tr >
</table>
<%-- <tr>
    <td><a id="next" class="TabsCommonButtons" onclick="javascript:generateReport('<%=request.getContextPath()%>/images')" style="margin-bottom: 4px;">Open</a></td>
  </tr> --%>

<div id="reportHeader" style="border:1px solid #d8d8d8;margin-bottom: 5px;font-family:Arial;width:1100px; font-size:12px; color:#083359; text-align:left; font-weight:bold; line-height:24px;
	-webkit-border-radius: 4px 4px 4px 4px;
	-moz-border-radius: 4px 4px 4px 4px;
	border-radius: 4px 4px 4px 4px;
	background-image:-webkit-gradient(linear, left top, left bottom, from(#e6e6e6), to(#f8f8f8));
	background-image:-webkit-linear-gradient(#e6e6e6, #f8f8f8);
	background-image:-moz-linear-gradient(#e6e6e6, #f8f8f8);
	background-image:-ms-linear-gradient(#e6e6e6, #f8f8f8);
	background-image:-o-linear-gradient(#e6e6e6, #f8f8f8);
	background-image:linear-gradient(#e6e6e6, #f8f8f8);
	margin-left:0px;
	padding-left:10px;
	margin-top:1px;display: none;">Reports</div>
<!-- <div id="reportHeader" class="adminDivdHeaderr" style="display:none;margin-top: -33px;width: 1100px;margin-left: 0px;">Report</div> -->
<div id="reportData" style="height: 380px;display:none;width:1110px;text-align: center;overflow: scroll;" class="subminApprovalTable">

</div>
<img id="excel" style="width: 34.8px; height: 34.6px; display:none;opacity:0.5;" onclick="downloadReport('xls')"
	 src="<%=request.getContextPath()%>/images/excel.jpeg" onmouseover="this.style.opacity=1.0;" onmouseout="this.style.opacity=0.5;">
<img id="pdf" style="width: 34.8px; height: 34.6px; display:none;opacity:0.5;" onclick="downloadReport('pdf')"
	 src="<%=request.getContextPath()%>/images/pdf.jpeg" onmouseover="this.style.opacity=1.0;" onmouseout="this.style.opacity=0.5;">
<img id="doc" style="width: 34.8px; height: 34.6px; display:none;opacity:0.5;" onclick="downloadReport('doc')"
     src="<%=request.getContextPath()%>/images/doc.jpeg" onmouseover="this.style.opacity=1.0;" onmouseout="this.style.opacity=0.5;">
<td><a id="back" class="TabsCommonButtons" onclick="loadReportsPage();" style="display:none;margin-bottom: 5px;">Back</a></td>


</form:form>
</body>
</html>