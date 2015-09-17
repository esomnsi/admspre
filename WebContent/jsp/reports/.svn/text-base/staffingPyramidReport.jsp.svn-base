<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<link href="../css/report.css" rel="stylesheet" type="text/css" />
<style>
.width{
	width: 25%;
}
</style>	
<div class="mainBodyDivwithoutLine ">
<%-- <div class="tdHeaderLabel" style="width: 1096px; margin-left: 1px; margin-bottom: 3px;">Staffing Pyramid Report in ${currency}</div>
 --%>
<c:if test="${empty stafPyrReport }">
		<div id="showError" class="errorMessageLogin"
			style="text-align: left; -webkit-border-radius: 5px 5px 5px 5px; -moz-border-radius: 5px 5px 5px 5px; border: 1px solid #FF0000;">
			<ul>
				<li>No data to be displayed</li>
			</ul>
		</div>
	</c:if>


<c:if test="${not empty stafPyrReport }">
	<display:table uid="costSummaryReportListInstance" name="stafPyrReport" export="true" requestURI="" class="table" >
			
		<c:forEach items="${costSummaryReportListInstance}" var="currentInstance" varStatus="loop">
		
		<c:set var="className" value="reportCell"/>
			
			<c:if test="${currentInstance.key=='Job Level & Career Stage'}">
			<c:set var="className" value="reportSubHeader"/> 
			
			</c:if> 
			<c:if test="${currentInstance.value[0]==''}">
			<c:set var="className" value="reportSubHeader"/> 
			</c:if> 
			
			
			<display:column  class="${className} width reportCellLeft" title="    " media="html csv excel pdf">
			<c:if test="${currentInstance.key !='LINE'}">
				${currentInstance.key}
			</c:if>
			
			<c:if test="${currentInstance.key =='LINE'}">
				&nbsp;
			</c:if>
				
				</display:column>
				
			
			
			
			<c:forEach items="${currentInstance.value}" var="item">
			
				<display:column class="${className} " title="    " media="html csv excel pdf">
					${item}   
				</display:column>
			</c:forEach>
		</c:forEach>
	
	<display:caption title="ADR Report" media="html csv excel pdf" class="tdHeaderLabel">Staffing Pyramid Report</display:caption>
		<display:setProperty name="export.excel.filename" value="report.xls"/>	
		<display:setProperty name="export.csv.filename" value="report.csv"/>	
	    <display:setProperty name="export.pdf.filename" value="report.pdf"/> 
	
	</display:table>
</c:if>
</div>

