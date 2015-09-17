<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<link href="../css/report.css" rel="stylesheet" type="text/css" />


<style>


</style>

<div class="mainBodyDivwithoutLine reportDiv">

<div class="tdHeaderLabel" style="width: 1096px; margin-left: 1px; margin-bottom: 3px;">Dependency Report</div>
<display:table export="true"  id="data"  name="${solADRList}" requestURI="">
	<display:column property="solutionId" title="SolutionID"   media="html excel" class="reportCell" />
	<display:column property="adrStatement" title="ADRStatement"   media="html excel" class="reportCell reportCellLeft" />
	<display:column property="adrArea" title="ADRArea"   media="html excel" class="reportCell" />
	<display:column property="adrType" title="ADRType"  media="html excel" class="reportCell" />
	<display:column property="adrImpact" title="ADRImpact"   media="html excel" class="reportCell" />
	<display:column property="adrWeightage" title="ADRWeightage"   media="html excel" class="reportCell" />
	
	<display:setProperty name="export.excel.filename" value="assumption.xls"/>
    <display:setProperty name="export.csv.filename" value="assumption.csv"/>
    
   
    
  </display:table>
    
   
</div>

