<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<link href="../css/report.css" rel="stylesheet" type="text/css" />


<style>
.tdHeaderLabel_local{
 	font-size: 13px;
	line-height:25px;
	padding-left:15px;
   	text-align:left;
    height:25px;
	color: #FFFFFF;
	font-family: Arial;
	font-weight:bold;
   
    
	background-image:-webkit-gradient(linear, left top, left bottom, from(#0c3d67), to(#275d92));
	background-image:-webkit-linear-gradient(#0c3d67, #275d92);
	background-image:-moz-linear-gradient(#0c3d67, #275d92);
	background-image:-ms-linear-gradient(#0c3d67, #275d92);
	background-image:-o-linear-gradient(#0c3d67, #275d92);
	background-image:linear-gradient(#0c3d67, #275d92);
	 filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#0c3d67', endColorstr='#275d92');
	
 }
 
 
 .tdTableHead_local{
	border:1px solid #d8d8d8;
    height:18px;
	font-family:Arial; font-size:12px; color:#083359; text-align:left; font-weight:bold; line-height:22px; padding-left:10px; 
	background-color: #FFFF;
	
 }

</style>

<div class="mainBodyDivwithoutLine reportDiv">
<%-- 
<div class="tdHeaderLabel" style="width: 1096px; margin-left: 1px; margin-bottom: 3px;">A-D-R Consolidated Report in ${currency}</div> --%>
<c:if test="${displayFlag == 'NO' }">
		<div id="showError" class="errorMessageLogin"
			style="text-align: left; -webkit-border-radius: 5px 5px 5px 5px; -moz-border-radius: 5px 5px 5px 5px; border: 1px solid #FF0000;">
			<ul>
				<li>No data to be displayed</li>
			</ul>
		</div>
	</c:if>


 <c:if test="${displayFlag != 'NO' }">
<!-- <div class="tdHeaderLabel_local" style="width: 1096px; margin-left: 1px; margin-bottom: 3px;">Assumption Report</div> -->
<display:table export="true"  id="finalListInstance"  name="${finalList}" requestURI="" >

	<%-- <c:set var="className" value="tdHeaderLabel"/>
	<c:if test="${solutionId == null}">
		<c:set var="className" value="tdHeaderLabel"/>
	</c:if> --%>
	
	<c:choose>
            <c:when test="${finalListInstance.adrType == ' '}">
                <c:set var="className" value="tdHeaderLabel_local"/>
            </c:when>   
              <c:when test="${finalListInstance.adrStatement eq ' ' }">
                <c:set var="className" value="tdTableHead_local"/>
            </c:when>                                            

            <c:otherwise>
            <c:out value="${adrType}" />
                <c:set var="className" value="reportCell reportCellLeft"/>
            </c:otherwise>
 	</c:choose>
	<display:caption title="ADR Report" media="html csv excel pdf" class="tdHeaderLabel_local">A-D-R Consolidated Report</display:caption>
	<display:column property="adrStatement" title="            Statement            "   media="html csv excel pdf" class="${className}" style="width:70%" />
	<display:column property="solutionId" title="SolutionID"   media="html csv excel pdf" class="${className}" />
	<display:column property="adrArea" title="Area"    media="html csv excel pdf" class="${className}" />
	
	<display:column property="adrType" title="Type"  media="html csv excel pdf"  class="${className}" />
	<display:column property="adrImpact" title="Impact"   media="html csv excel pdf" class="${className}" />
	<display:column property="adrWeightage" title="Weightage"   media="html csv excel pdf" class="${className}" />
	
 	<display:setProperty name="export.excel.filename" value="A-D-R_Report.xls"/>
     <display:setProperty name="export.csv.filename" value="A-D-R_Report.csv"/>
    <display:setProperty name="export.pdf.filename" value="A-D-R_Report.pdf"/> 
   
<%--     <display:setProperty name="export.pdf.filename" value="true" />
 --%>    
    
</display:table>
     </c:if>
    </div>
    
   





