<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form:form id="id_taasOutputForm" modelAttribute="form">
<div class="testingTablHeader">Testing Output </div>
<table width="1080" cellpadding="0" cellspacing="1" class="tableTaas" style="margin-bottom:3px;">
  <tr bgcolor="#d6d6d6">
    <td width="193" height="35" align="center"><strong>YEAR 1</strong></td>
    <td width="171" align="center">Release#</td>
    <td width="180" height="35" align="center">No of manual test cases</td>
    <td width="171" align="center">No of automated test case</td>
    <td width="174" align="center">Manual  Test efforts<br />
(Per Month)</td>
    <td width="182" colspan="2" align="center">Automation Dev effort<br />
    (Per Month)</td>
  </tr>
  <td rowspan="${fn:length(form.majTaasOpList)}" align="center" bgcolor="#EFEFEF"><strong>Major Release</strong></td>
    <td height="20" align="center" bgcolor="#fafafa">${form.majTaasOpList[0].numRelease}</td>
    <td align="center" bgcolor="#fafafa">${form.majTaasOpList[0].numManualTC}</td>
    <td align="center" bgcolor="#fafafa">${form.majTaasOpList[0].numAutoTC}</td>
    <td align="center" bgcolor="#fafafa">${form.majTaasOpList[0].manualTestEff}</td>
    <td colspan="2" align="center" bgcolor="#fafafa">${form.majTaasOpList[0].autoDevEff}</td>
  </tr>
  <c:if test="${fn:length(form.majTaasOpList) gt 1}">
   <c:forEach  var="i" begin="1" end="${fn:length(form.majTaasOpList)-1}"> 
 <!--  <tr>
    <td height="20" align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="majTaasOpList[${i}].numRelease" readonly="true"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="majTaasOpList[${i}].numManualTC" readonly="true"/></td>
    <td align="center" bgcolor="#fafaf"><form:input type="text" class="txtinputComonfield" path="majTaasOpList[${i}].numAutoTC" readonly="true"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="majTaasOpList[${i}].manualTestEff" readonly="true"/></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="majTaasOpList[${i}].autoDevEff" readonly="true"/></td>
  </tr>-->
  <tr>
    <td height="20" align="center" bgcolor="#fafafa">${form.majTaasOpList[i].numRelease}</td>
    <td align="center" bgcolor="#fafafa">${form.majTaasOpList[i].numManualTC}</td>
    <td align="center" bgcolor="#fafaf">${form.majTaasOpList[i].numAutoTC}</td>
    <td align="center" bgcolor="#fafafa">${form.majTaasOpList[i].manualTestEff}</td>
    <td colspan="2" align="center" bgcolor="#fafafa">${form.majTaasOpList[i].autoDevEff}</td>
  </tr>
   </c:forEach> 
  </c:if> 
 <!--  <tr>
    <td rowspan="${fn:length(form.minTaasOpList)}" align="center" bgcolor="#EFEFEF"><strong>Minor Release</strong></td>
    <td height="20" align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="minTaasOpList[0].numRelease" readonly="true"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="minTaasOpList[0].numManualTC" readonly="true"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="minTaasOpList[0].numAutoTC" readonly="true"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="minTaasOpList[0].manualTestEff" readonly="true"/></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="minTaasOpList[0].autoDevEff" readonly="true"/></td>
  </tr>  -->
  <tr>
    <td rowspan="${fn:length(form.minTaasOpList)}" align="center" bgcolor="#EFEFEF"><strong>Minor Release</strong></td>
    <td height="20" align="center" bgcolor="#fafafa">${form.minTaasOpList[0].numRelease}</td>
    <td align="center" bgcolor="#fafafa">${form.minTaasOpList[0].numManualTC}</td>
    <td align="center" bgcolor="#fafafa">${form.minTaasOpList[0].numAutoTC}</td>
    <td align="center" bgcolor="#fafafa">${form.minTaasOpList[0].manualTestEff}</td>
    <td colspan="2" align="center" bgcolor="#fafafa">${form.minTaasOpList[0].autoDevEff}</td>
  </tr>
  <c:if test="${fn:length(form.minTaasOpList) gt 1}">
   <c:forEach  var="i" begin="1" end="${fn:length(form.minTaasOpList)-1}"> 
 <!--  <tr>
    <td height="20" align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="minTaasOpList[${i}].numRelease" readonly="true"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="minTaasOpList[${i}].numManualTC" readonly="true"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="minTaasOpList[${i}].numAutoTC" readonly="true"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="minTaasOpList[${i}].manualTestEff" readonly="true"/></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="minTaasOpList[${i}].autoDevEff" readonly="true"/></td>
  </tr>  -->
  <tr>
    <td height="20" align="center" bgcolor="#fafafa">${form.minTaasOpList[i].numRelease}</td>
    <td align="center" bgcolor="#fafafa">${form.minTaasOpList[i].numManualTC}</td>
    <td align="center" bgcolor="#fafafa">${form.minTaasOpList[i].numAutoTC}</td>
    <td align="center" bgcolor="#fafafa">${form.minTaasOpList[i].manualTestEff}</td>
    <td colspan="2" align="center" bgcolor="#fafafa">${form.minTaasOpList[i].autoDevEff}</td>
  </tr>
  </c:forEach>
  </c:if>
  <tr bgcolor="#d6d6d6">
    <td height="30" align="center"><strong>Yearly TOTAL</strong></td>
    <td align="center">${form.yearlyOPObj.numRelease}</td>
    <td align="center">${form.yearlyOPObj.numManualTC}</td>
    <td align="center">${form.yearlyOPObj.numAutoTC}</td>
    <td align="center">${form.yearlyOPObj.manualTestEff}</td>
    <td colspan="2" align="center">${form.yearlyOPObj.autoDevEff}</td>
  </tr>
   
</table>
<table width="897" cellpadding="0" cellspacing="1" class="tableTaas" style="margin-bottom:3px;">
  <tr bgcolor="#d6d6d6">
    <td width="193" height="35" align="center"><strong>Year #</strong></td>
    <td width="171" align="center">Total Effort <br />
    PerMonth / Year</td>
    <td width="180" height="35" align="center">Total Effort <br />
    (pd)/ Year</td>
    <td width="171" align="center">Total Effort<br />
(ph)/ Year</td>
    <td width="174" align="center">Avg FTE / Year</td>
  </tr>
  <c:forEach var="i" begin="0" end="6">
  <tr>
    <td align="center" bgcolor="#FAFAFA">Year ${i+1}</td>
    <td height="20" align="center" bgcolor="#fafafa">${form.yearlyTaasOpList[i].totalEffPMPY}</td>
    <td align="center" bgcolor="#fafafa">${form.yearlyTaasOpList[i].totalEffPDPY}</td>
    <td align="center" bgcolor="#fafafa">${form.yearlyTaasOpList[i].totalEffPHPY}</td>
    <td align="center" bgcolor="#fafafa">${form.yearlyTaasOpList[i].avgFTEPY}</td>
  </tr>
   </c:forEach>
</table>
</form:form>
 <div class="tabsDefineScoprButtons">
            <div class="TAASSubmitButton" id="id_div_backTAAS" onclick="goBack()">BACK</div></div>