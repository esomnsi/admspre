<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">
$(document).ready(function () {
	$('#next').click(function() {
		var url = "<%=request.getContextPath()%>/solution/ttFTEBreakup";    
		$(location).attr('href',url);
	});

	$('#prev').click(function() {
    	var url = "<%=request.getContextPath()%>/solution/reviewFTE";    
    	$(location).attr('href',url);
    });

});

</script>
<div id="bodyDiv" class="mainBodyDiv1" style="margin-top:5px;">
	<jsp:include page="opportunitySummary.jsp"></jsp:include>
			</div>
			<div id="serviceBucket">
		<table width="730" align="center">
	<tr>
		<td align="center" colspan="6">
		<jsp:include page="./serviceBucket.jsp"/>
		</td>
	</tr>
	</table>
	</div>

<div id="tntDiv">			
<table width="500" border="0" cellspacing="0" cellpadding="0" align="center">
 
  
  <tr>
    <td align="center" height="5"></td>
  </tr>
  <tr>
    <td align="center"><table width="500" cellpadding="0" cellspacing="1" class="tableText">
      <tr class="ComontableTextTd">
        <td width="214" height="20" bgcolor="#f1f1f1"><strong>Enter the number of partitions</strong></td>
        <td width="281" align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <input type="text" class="txtinputComonfield2" value="3"/> 
          </span>  <a id="#" class="portfolioButtons" href="">Go</a></td>
        
        
      </tr>
      <tr class="ComontableTextTd">
        <td width="214" align="center" bgcolor="#f1f1f1"><span class="smfVal">
        Partition 1
          </span></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <input type="text" class="txtinputComonfield2" value="40"/>
        </span></td>
        
       
      </tr>
      <tr class="ComontableTextTd">
       <td width="214" align="center" bgcolor="#f1f1f1"><span class="smfVal">
              Partition 2
          </span></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <input type="text" class="txtinputComonfield2" value="30"/>
        </span></td>
       
        
      </tr>
      <tr class="ComontableTextTd">
        <td width="214" align="center" bgcolor="#f1f1f1"><span class="smfVal">
              Partition 3
          </span></td>
         <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <input type="text" class="txtinputComonfield2" value="15"/>
        </span></td>
       
        
      </tr>
      <tr class="ComontableTextTd">
        <td width="214" height="20" bgcolor="#f1f1f1"><strong>Total FTE Count</strong></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal" >
          <input type="text" class="txtinputComonfield2" value="85"/>
        </span></td>
       
        
      </tr>
      <tr class="ComontableTextTd">
        <td width="214" height="20" bgcolor="#f1f1f1"></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          
        </span></td>
       
        
      </tr>
      <tr class="ComontableTextTd">
        <td width="214" height="20" bgcolor="#f1f1f1"><strong>Start date of the T&amp;T</strong></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <input type="text" class="txtinputComonfield2" value="1-Jun-2013" />
        </span></td>
       
        
      </tr>
      <tr class="ComontableTextTd">
        <td width="214" height="20" bgcolor="#f1f1f1"><strong>End date of the T&amp;T</strong></td>
        <td align="left" bgcolor="#f1f1f1"><span class="smfVal">
          <input type="text" class="txtinputComonfield2" value="31-Jul-2013"/>
        </span></td>
       
        
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="5"></td>
  </tr>
</table>
<table align="right">
	<tr>
    	<td align="right"><a id="next" class="portfolioButtons" >Next</a> <a id="#" class="portfolioButtons" href="#" >Save</a> <a id="prev" class="portfolioButtons" href="#" >Previous</a> </td>
    </tr>
</table>

</div>	


<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String appName = request.getContextPath();
	String contextPath = "http://"+request.getServerName()+":"+request.getServerPort()+appName;
%>
<link rel="stylesheet" type="text/css" href="../css/jsgantt.css"/>
<script language="javascript" src="../js/jsgantt.js"></script>
<link rel="stylesheet" href="../css/it_mssp.css" />
<link rel="stylesheet" href="../css/landingPageStyle.css" />
<script language="javascript" src="../js/operations.js"></script>
<script src="../js/jquery-1.8.2.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function()
{
	$("table tr[class='child']").hide();
	$("tr.headerRow").click(function () { 
		   //$("tr.child", $(this).parent()).slideToggle("slow", "linear");
		$("tr.child", $(this).parent()).slideToggle(20);
		});
});
</script>
<body>
<div class="tdHeaderLabel">T&T Design</div>
<form:form  method="post" modelAttribute="tntForm">
<!-- <div id="bodyDiv" class="mainBodyDiv1" style="margin-top:5px;">
				<table  width="100%" border="0" cellspacing="2" cellpadding="0" class="staffingPlantopTable">
							<tr class="headerRow" style="cursor: pointer;">
								<td class="tdHeaderLabel" colspan="4">Oppurtunity Details</td>
							</tr>
							<tr class="child">
							 <td class="tdLabel" width="25%"> Oppurtunity Name :</td>   <td class="staffingPlanBgColor" >${tntForm.partitionDate.opportunityName} </td>
							 <td class="tdLabel" > Cadence Id :</td>   <td class="staffingPlanBgColor" >${tntForm.partitionDate.cadenceId} </td>
							
							</tr>
							<tr class="child">
							 <td class="tdLabel" width="25%"> Customer :</td>   <td class="staffingPlanBgColor" > ${tntForm.partitionDate.customerName} </td>
							 <td class="tdLabel" > Country :</td>   <td class="staffingPlanBgColor" > ${tntForm.partitionDate.countryName} </td>
							</tr>
						</table>
					</div> -->
<div id="bodyDiv" class="mainBodyDiv1" style="margin-top:5px;">
			<jsp:include page="opportunitySummary.jsp"></jsp:include>
					</div>
					<div id="serviceBucket">
				<table width="730" align="center">
				<tr>
					<td align="center" colspan="6">
					<jsp:include page="./serviceBucket.jsp"/>
					</td>
				</tr>
				</table>
				</div>
<div id="id_errorDiv" class="errorMessage" style="display: none;"></div>
<div style="position:relative;display:none;" class="gantt" id="GanttChartDIV"></div>
<div style="position:relative;display:none;" class="gantt" id="GanttChartTableDIV"></div>
<div id="saveChartDiv" style="display: none;text-align: right; padding-right: 189px;"><input type="button" class="tntButtons" value="Save Chart Data" onclick="saveData()"></div>

<!-- ------- first section ------- -->
<center>
<div id="id_div_num_part" class="tnt_textPanel">
<form:hidden path="partitionDate.solution.solutionId" value="${tntForm.solutionId}"/>
<form:hidden path="solutionId" value="${tntForm.solutionId}"/>
<form:hidden id="id_hidden_num_part" value="${tntForm.partitionDate.noOfPart}" path=""/>
<form:hidden id="id_hidden_start_date_tnt" value="${tntForm.partitionDate.startDate}" path=""/>
<form:hidden id="id_hidden_end_date_tnt" value="${tntForm.partitionDate.endDate}" path=""/>
<form:hidden id="id_hidden_serviceFTECount" value="${tntForm.partitionDate.serviceFteCount}" path=""/>
<form:hidden path="partitionDate.tNTPartitionDateID" value="${tntForm.partitionDate.tNTPartitionDateID}"/>
<form:hidden path="" id="tntDetailSize" value="${tntForm.tntDetailSize}"/>
<c:forEach items="${tntForm.tntDetailList}" var="details" varStatus="status" >
	<form:hidden id="details[${status.index}].solutionId" path="tntDetailList[${status.index}].solutionId"   value="${tntForm.solutionId}"/>
	<form:hidden id="details[${status.index}].id_tnt_detail" path="tntDetailList[${status.index}].id_tnt_detail"   value="${details.id_tnt_detail}"/>
	<form:hidden id="details[${status.index}].partitionName" path="tntDetailList[${status.index}].partitionName"   value="P${status.index}"/>
	<form:hidden id="details[${status.index}].totalFte" path="tntDetailList[${status.index}].totalFte"   value="${details.totalFte}"/>
	<form:hidden id="details[${status.index}].start_date_planning" path="tntDetailList[${status.index}].start_date_planning"   value="${details.start_date_planning}"/>
	<form:hidden id="details[${status.index}].end_date_planning" path="tntDetailList[${status.index}].end_date_planning"   value="${details.end_date_planning}"/>
	<form:hidden id="details[${status.index}].start_date_learn" path="tntDetailList[${status.index}].start_date_learn"   value="${details.start_date_learn}"/>
	<form:hidden id="details[${status.index}].end_date_learn" path="tntDetailList[${status.index}].end_date_learn"   value="${details.end_date_learn}"/>
	<form:hidden id="details[${status.index}].start_date_assist" path="tntDetailList[${status.index}].start_date_assist"   value="${details.start_date_assist}"/>
	<form:hidden id="details[${status.index}].end_date_assist" path="tntDetailList[${status.index}].end_date_assist"   value="${details.end_date_assist}"/>
	<form:hidden id="details[${status.index}].start_date_perform" path="tntDetailList[${status.index}].start_date_perform"   value="${details.start_date_perform}"/>
	<form:hidden id="details[${status.index}].end_date_perform" path="tntDetailList[${status.index}].end_date_perform"   value="${details.end_date_perform}"/>
	<form:hidden id="details[${status.index}].start_date_deliver" path="tntDetailList[${status.index}].start_date_deliver"   value="${details.start_date_deliver}"/>
	<form:hidden id="details[${status.index}].end_date_deliver" path="tntDetailList[${status.index}].end_date_deliver"   value="${details.end_date_deliver}"/>
	<form:hidden id="details[${status.index}].fte_count_planning" path="tntDetailList[${status.index}].fte_count_planning"   value="${details.fte_count_planning}"/>
	<form:hidden id="details[${status.index}].fte_count_learn" path="tntDetailList[${status.index}].fte_count_learn"   value="${details.fte_count_learn}"/>
	<form:hidden id="details[${status.index}].fte_count_assist" path="tntDetailList[${status.index}].fte_count_assist"   value="${details.fte_count_assist}"/>
	<form:hidden id="details[${status.index}].fte_count_perform" path="tntDetailList[${status.index}].fte_count_perform"   value="${details.fte_count_perform}"/>
	<form:hidden id="details[${status.index}].fte_count_deliver" path="tntDetailList[${status.index}].fte_count_deliver"   value="${details.fte_count_deliver}"/>
	<form:hidden id="details[${status.index}].fte_percent_planning" path="tntDetailList[${status.index}].fte_percent_planning"   value="${details.fte_percent_planning}"/>
	<form:hidden id="details[${status.index}].fte_percent_learn" path="tntDetailList[${status.index}].fte_percent_learn"   value="${details.fte_percent_learn}"/>
	<form:hidden id="details[${status.index}].fte_percent_assist" path="tntDetailList[${status.index}].fte_percent_assist"   value="${details.fte_percent_assist}"/>
	<form:hidden id="details[${status.index}].fte_percent_perform" path="tntDetailList[${status.index}].fte_percent_perform"   value="${details.fte_percent_perform}"/>
	<form:hidden id="details[${status.index}].fte_percent_deliver" path="tntDetailList[${status.index}].fte_percent_deliver"   value="${details.fte_percent_deliver}"/>
</c:forEach>
<table>
<tr>
<td>Enter the number of partitions</td>
<td><form:input id="id_text_num_part" path="partitionDate.noOfPart" class="textBoxTnt" onkeyup="checkIfNumber(this)"/></td>
</tr>
<tr>
<td>Start date of the T&T</td>
<td><form:input id="id_start_date" path="partitionDate.startDate" type="text" class="textBoxTnt" readonly="true"/></td>
</tr> 
<tr>
<td>End date of the T&T</td>
<td><form:input id="id_end_date" path="partitionDate.endDate" type="text" class="textBoxTnt" readonly="true"/></td>
</tr>
</table>
<input type="button" id="id_btn_num_part" value="Confirm" onclick="drawPartitionFTE()" class="tntButtons" />
</div>
</center>
<!-- ------- second section ------- -->
<div id="partition_FTE" style="display: none; width: 330px; float: left;" class="tnt_textPanel_Part">
<table id="partition_FTE_table" style="width: 100%; border:1px solid #E4E4E4; margin-bottom: 5px;" cellpadding="0" cellspacing="2">
<tr>
	<th bgcolor="#efefef" height="30"><b>Partition</b></th> 
	<th bgcolor="#efefef" height="30"><b>FTE</b></th>
</tr>
<!-- <tbody id="fte_main_tbody">
</tbody> -->
</table>
<input class="tntButtons" type="button" value="Submit" onclick="showDataTable();">
</div>
 <div id="id_fte_data" style="display: none;">
</div>
<div id="id_div_tab_data" style="width:1112px; display:none; float: left;"> 
<table id="id_main_table" border="0" width="1112" style="border:1px solid #c7c7c7;">
<tr bgcolor="#efefef" class="tnt_textPanel_Part">
<th height="30" width="101">Partition</th>
<th width="101">Planning -Start Date</th>
<th width="101">Planning -End Date</th>
<th width="101">Learn -Start Date</th>
<th width="101">Learn -End Date</th>
<th width="101">Assist -Start Date</th>
<th width="101">Assist -End Date</th>
<th width="101">Perform -Start Date</th>
<th width="101">Perform -End Date</th>
<th width="101">Deliver -Start Date</th>
<th width="101">Deliver -End Date</th>
</tr>
<tbody id="main_tbody">
</tbody>
</table>
<input type="button" value="Submit" onclick="createChartWithValues()" class="tntButtons">
</div>
<center>
<div id="id_tnt_loading_div" style="display: none; height: 300px; width:100%; float: left;">
<img alt="Loading...Please Wait" src="../images/loading4.gif">
</div>
</center>
</form:form>
<script>
var g = new JSGantt.GanttChart('g',document.getElementById('GanttChartDIV'), 'day');
</script>
<script src="../js/drawgantt.js"></script>
<script>
	function saveData()
	{
		document.forms[0].action = "<%=appName%>/solution/save";
		document.forms[0].submit();
	}
	function checkIfNumber(a)
	{
		var value = document.getElementById(a.id).value;
		if(isNaN(parseInt(value * 1)))
		{
			alert('Please enter numeric values');
			document.getElementById(a.id).value = '';
			return;		
		}	
	}
</script>
</body>
 --%>