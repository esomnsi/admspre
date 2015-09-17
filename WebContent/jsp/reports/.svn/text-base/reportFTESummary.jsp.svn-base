<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<link href="../css/report.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

$(document).ready(function(){
	$('#yearType').click(function() {
		$('#timelineType').attr('action', '/ADM_PRE/report/timelineType');
		$('#timelineType').submit();
	});	
});

</script>
<c:if test="${empty intervals }">
		<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
	-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;">
		<ul>
			<li>
				Please check the Steady State Start and End dates for this Opportunity under Project Delivery
			</li>
			<c:if test="${empty fteSummaryReportList }">
			<li>
				No data to be displayed
			</li>
			</c:if>
		</ul>
	</div>
	</c:if>

<div class="mainBodyDivwithoutLine ">

	<form id="timelineType">
	<div style="font-family: sans-serif;font-weight: normal;font-size: 13px;">
		<c:if test="${calanderYear==true}">
		<c:set var='val' value='checked'></c:set>
		<c:set var='val1' value=''></c:set>
		
		</c:if>
		<c:if test="${calanderYear==false}">
		<c:set var='val1' value='checked'></c:set>
		<c:set var='val' value=''></c:set>
		</c:if>
		
		<c:if test="${not empty intervals}">
			<span>
			Please select the Time Line interval type
		</span>
		<a href="/ADM_PRE/report/fteChart"><span style="float: right;">Graphical Representation</span></a>
		<table>
		<tr>
		<td>
		<span>
			<input type="radio" name="intervalType" value="calYear-frs" ${val} >Calendar Year
		</span>
		</td><td>
		<span>
			<input type="radio" name="intervalType" value="conYear-frs" ${val1} >Contract Year
		</span>
		</td><td>
		<span>
			<a id="yearType" class="TabsCommonButtons" style="width: 35px;">Go</a>
		</span>
		<td>
		</tr>
		</table>		
			</c:if>
		</div>
	</form>
	<br>


<c:if test="${not empty  fteSummaryReportList}">
	<display:table uid="fteSummaryReportListInstance" name="fteSummaryReportList" export="true" requestURI="" class="table" >
			
		<c:forEach items="${fteSummaryReportListInstance}" var="currentInstance">
	
			<c:set var="className" value=""/>
			<c:if test="${currentInstance.key=='Service Scopes'}">
			<c:set var="className" value="reportSubHeader"/> 
			</c:if>
			<c:if test="${currentInstance.key!='Service Scopes'}">
			<c:set var="className" value="reportCell"/> 
			</c:if>
			
			<c:if test="${currentInstance.key=='Onshore (Local) FTESummary' || currentInstance.key=='Onshore (GSC Landed) FTESummary' || currentInstance.key=='Onshore (3PP) FTESummary' || currentInstance.key=='Nearshore (GSC) FTESummary' || currentInstance.key=='Offshore (GSC) FTESummary' || currentInstance.key == 'Total FTESummary'}">
			<c:set var="className" value="reportHeaderLabel"/>
			</c:if>
		
			<c:if test="${currentInstance.key =='LINE'}">
			<c:set var="className" value=""/> 
			</c:if>
			<display:column  class="${className} reportCellLeft" title = "    "  media="html csv excel pdf">
			<c:if test="${currentInstance.key !='LINE'}">
				${currentInstance.key}
			</c:if>
			
			<c:if test="${currentInstance.key =='LINE'}">
				&nbsp;
			</c:if>
				
			</display:column> 
			
			
			<c:forEach items="${currentInstance.value}" var="item">
				<display:column class="${className}"  title = "    " media="html csv excel pdf">
					<c:choose>
						<c:when test="${item == '0.0'}">
						</c:when>
						<c:otherwise>
							${item}
						</c:otherwise>
					</c:choose>
					
					 
				</display:column>
			</c:forEach>
		</c:forEach>
		<display:caption title="FTE Summary Report" media=" csv excel pdf" class="tdHeaderLabel">FTE Summary Report</display:caption>
		<display:setProperty name="export.excel.filename" value="report.xls"/>	
		<display:setProperty name="export.csv.filename" value="report.csv"/>
		<display:setProperty name="export.pdf.filename" value="report.pdf"/>	
	
	</display:table>
</c:if>
</div>

