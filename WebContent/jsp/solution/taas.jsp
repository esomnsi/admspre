<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String appName = request.getContextPath();
	String contextPath = "http://"+request.getServerName()+":"+request.getServerPort()+appName;
%>
    <script type="text/javascript" src="../js/taas.js"></script>
    <script type="text/javascript">
    $(document).ready(function()
	{
    	//window.location.href="/solution/taas";
		//$('#id_taas_submit').attr('disabled', 'disabled');
		var newId = '';
		//$('#taasOutputDiv').hide();
		//$('#id_div_taas_input').hide();
		$('#iddate_0').datepicker({dateFormat : 'yy-mm-dd'});
		$("#id_addNew").unbind('click');
		 $("#id_addNew").click(function() {
		      var row = $('#id_tab_generic_input tbody>tr:last').clone(true);
		      $("td input:text", row).val("");
		      row.insertAfter('#id_tab_generic_input tbody>tr:last'); 
		      var lastIndex = row.index()-2;
		      $("td input:text, td select", row).each(function(){
		    	 // var newName = $(this).attr("name").replace($(this).attr("name").split(".")[0],"id_tab_generic_input["+lastIndex+"]");
		    	  newId = $(this).attr("id").split("_")[0]+'_'+lastIndex;
		    	 // $(this).attr("name",newName);
		    	  $(this).attr("id",newId);
		    	  $(this).attr('readonly', 'readonly');
		    	  if(newId.search('iddate') >= 0)
			        {
				     	$('#'+newId).datepicker('destroy');    
				     	$('#'+newId).datepicker({dateFormat : 'yy-mm-dd'}); 
				     	$('#'+newId).attr('name','testServList['+lastIndex+'].releaseDate');
				     	$('#'+newId).attr('path','testServList['+lastIndex+'].releaseDate');
			        }
		    	  else if(newId.search('select') >= 0)
			        {
				     	$('#'+newId).attr('name','testServList['+lastIndex+'].releaseType');
				     	$('#'+newId).attr('path','testServList['+lastIndex+'].releaseType');
				     	$('#'+newId).attr('value','testServList['+lastIndex+'].releaseType'); 
			        }
		    	  else if(newId.search('idtaasInputTgtDistrn') >= 0)
			        {
				     	$('#'+newId).attr('name','testServList['+lastIndex+'].pcdistribution');
				     	$('#'+newId).attr('path','testServList['+lastIndex+'].pcdistribution');    
			        }
		      });
		      var newVal = Number($("#id_tab_generic_input"+lastIndex+"\\.dtoPK\\.resourceId").val())+1;
		      $("#id_tab_generic_input"+lastIndex+"\\.dtoPK\\.resourceId").val(newVal);
		        return false;
		   });
		 $('select[id^="select_"]').each(function() {
				if(this.value == 'Major' )
				{
					var idRight = this.id.split('_')[1];
					document.getElementById('idtaasInputTgtDistrn_'+idRight).removeAttribute('readonly');
				}
		 });
		 $('input[id^="iddate_"]').each(function() {
				$('#'+this.id).datepicker('destroy');
				$('#'+this.id).datepicker({dateFormat : 'yy-mm-dd'});
		 });
	});

	function validateAndSubmit()
	{
		var appName = $('#id_hiddenAppname').val();
		var genInputForm = $('#id_FormGenericInput');
		$.ajax({
			type : 'POST',
			url : appName+'/solution/savetaas',
			data : genInputForm.serialize(),
			success : function(response)
			{
				$('#id_testingInput').hide();
				$('#id_taas_submit').removeAttr('disabled');
				},
				error : function(response)
				{
					$('#id_div_errorMsg').show();
					$('#id_div_errorMsg').html('Sorry!! An error occurred while saving data.');
					//alert('Sorry!! An error occurred while saving data.');
					} 
			});
	}
	
    </script>
<body>
<div id="id_div_errorMsg" style="display: none;" class="errorMessageDisp"></div>
<input type="hidden" value="<%=appName %>" id="id_hiddenAppname">
        <div id="id_testingInput">
        <form:form method="post" modelAttribute="taasGenIPForm" id="id_FormGenericInput" action="/save/taasdetails">
         <form:hidden path="solution" value="${taasGenIPForm.solution}"/>
        <table width="800" cellpadding="2" cellspacing="1" class="tableTextTAAS" id="id_tab_generic_input">
        <tbody>
     <tr align="center">
      <td height="23"><strong>Release Type</strong></td>
      <td><strong>Release Date</strong></td>
      <td><strong>Target Distribution for a major release (%)</strong></td>
    </tr>
    <c:forEach items="${taasGenIPForm.testServList}" var="details" varStatus="status" >
    <form:hidden path="testServList[${status.index}].testingServiceId" value="${testServList[status.index].testingServiceId}"/>
    <tr>
      <td width="250" height="23">
      	<form:select name="select" class="taasSelcd" id = "select_${status.index}" bgcolor="#efefef" onchange="checkForTgtDistrn(this)" path="testServList[${status.index}].releaseType" value="testServList[${status.index}].releaseType">
            <form:option value="Select">Select </form:option>
			<form:option value="Major">Major</form:option>
			<form:option value="Minor">Minor</form:option>
        </form:select>
      </td>
      <td width="250"><form:input type="text" class="taasInputRelDate" id="iddate_${status.index}" readonly="true" path="testServList[${status.index}].releaseDate"/></td>
      <td><form:input name="input" type="text" class="taasInputTgtDistrn" id="idtaasInputTgtDistrn_${status.index}" onkeyup="checkIfNumber(this)" readOnly="true" path="testServList[${status.index}].pcdistribution"/></td>
    </tr>
    </c:forEach>
    </tbody>
  </table>
  
  <div class="tabsDefineScoprButtons">
            <div class="TAASSubmitButton" id="id_addNew">ADD</div><div class="TAASSubmitButton" id="id_addNew" onclick="validateGenericInputs()">DONE</div>
        </div>
        </form:form>
        </div>
<div id="id_div_taas_input" class="taasinput">
<div class="testingTablHeader">Generic Testing Inputs</div>
<form:form method="post" modelAttribute="taasForm" id="id_taas_form">
<form:hidden path="solution" value="${taasForm.solution}"/>
<form:hidden path="oppScopeID" value="${taasForm.oppScopeID}"/>
<form:hidden path="serviceList[0].solutionTestingAsAserviceId" value="${taasForm.serviceList[0].solutionTestingAsAserviceId}"/>
<table width="1080" cellpadding="0" cellspacing="1" class="tableTaas" style="margin-bottom:3px;">
<tr>
			<td class="tdLabelLeft" align="right">Percentage Of Application Testing Effort</br></td>

			<td class="tdInputBox" align="left" ><form:input type="text" 
				path="serviceList[0].percentOfServiceElementEffort" onkeyup="checkIfInt(this)"
				id="taasPercentOfServiceElementEffort"
				class="textBoxSNumric" style="font-weight: bold;" maxlength="3"/></td>
<td></td><td></td>
		</tr> 
  <tr bgcolor="#d6d6d6">
    <td width="441" rowspan="2"><strong>Complexity factor of test cases</strong></td>
    <td width="130" rowspan="2" align="center"><strong>Ratio</strong></td>
    <td height="24" colspan="3" align="center"><strong>Year 1 avg productivity (testcases / pd)</strong></td>
    <td width="117" colspan="2" rowspan="2" align="center"><strong>Assumption</strong></td>
  </tr>
  <tr bgcolor="#d6d6d6">
    <td width="134" height="24" align="center">Test design</td>
    <td width="120" align="center">Test execution</td>
    <td width="129" align="center">Automation scripting</td>
  </tr>
  <form:hidden path="genTestInputList[0].genericTestingInputsId" value="${genTestInputList[0].genericTestingInputsId}"/>
  <form:hidden path="genTestInputList[1].genericTestingInputsId" value="${genTestInputList[1].genericTestingInputsId}"/>
  <form:hidden path="genTestInputList[2].genericTestingInputsId" value="${genTestInputList[2].genericTestingInputsId}"/>
  <form:hidden path="genTestInputList[0].complexity" value="${genTestInputList[0].complexity}"/>
  <form:hidden path="genTestInputList[1].complexity" value="${genTestInputList[1].complexity}"/>
  <form:hidden path="genTestInputList[2].complexity" value="${genTestInputList[2].complexity}"/>
  <tr>
    <td height="20" bgcolor="#fafafa">Simple</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_simple_ratio" onkeyup="checkIfNumber(this)" path="genTestInputList[0].ratio" value="${genTestInputList[0].ratio}"/>
     </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_simple_test_design" onkeyup="checkIfInt(this)" path="genTestInputList[0].testDesignProductivity" value="${genTestInputList[0].testDesignProductivity}"/>
     </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_simple_test_exec" onkeyup="checkIfInt(this)" path="genTestInputList[0].testExecutionProductivity" value="${genTestInputList[0].testExecutionProductivity}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text"  class="textBoxSNumric" id="id_simple_automatic_script" onkeyup="checkIfInt(this)" path="genTestInputList[0].automationProductivity" value="${genTestInputList[0].automationProductivity}"/>
     </td>
    <td colspan="2" rowspan="3" align="center" bgcolor="#fafafa"><em>Test design productivity, factors in test case review / rework effort</em></td>
  </tr>
  <tr>
    <td height="20" bgcolor="#fafafa">Medium</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_medium_ratio" onkeyup="checkIfNumber(this)" path="genTestInputList[1].ratio" value="${genTestInputList[1].ratio}"/>
     </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_medium_test_design" onkeyup="checkIfInt(this)" path="genTestInputList[1].testDesignProductivity" value="${genTestInputList[1].testDesignProductivity}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_medium_test_exec" onkeyup="checkIfInt(this)" path="genTestInputList[1].testExecutionProductivity" value="${genTestInputList[1].testExecutionProductivity}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_medium_automatic_script" onkeyup="checkIfInt(this)" path="genTestInputList[1].automationProductivity" value="${genTestInputList[1].automationProductivity}"/>
    </td>
  </tr>
  <tr>
    <td height="20" bgcolor="#fafafa">Complex</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_complex_ratio" onkeyup="checkIfNumber(this)" path="genTestInputList[2].ratio" value="${genTestInputList[2].ratio}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_complex_test_design" onkeyup="checkIfInt(this)" path="genTestInputList[2].testDesignProductivity" value="${genTestInputList[2].testDesignProductivity}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_complex_test_exec" onkeyup="checkIfInt(this)" path="genTestInputList[2].testExecutionProductivity" value="${genTestInputList[2].testExecutionProductivity}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_complex_automatic_script" onkeyup="checkIfInt(this)" path="genTestInputList[2].automationProductivity" value="${genTestInputList[2].automationProductivity}"/>
    </td>
  </tr>
</table>
<!-- <div id="id_div_releaseCount">
<table width="500" cellpadding="0" cellspacing="1" class="tableText" style="margin-bottom:3px;">
  <tr>
    <td height="24" colspan="2" align="center" bgcolor="#efefef"><strong>Year 1</strong></td>
  </tr>
  <tr>
    <td width="134" height="24" align="center" bgcolor="#efefef">No of major release/year</td>
    <td width="120" align="center" bgcolor="#efefef">No of Minor release/year</td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="#fafafa">
      <input type="text" class="txtinputComonfield" id="id_num_major_rel" onkeyup="checkIfInt(this)"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <input type="text" class="txtinputComonfield" id="id_num_minor_rel" onkeyup="checkIfInt(this)"/>
    </td>
  </tr>
</table>
 <div class="tabsDefineScoprButtons">
            <div class="TAASSubmitButton" onclick="showRelDatesTable()">SUBMIT</div>
        </div>
        <br>
        </div> -->
  <!-- <div id="id_div_relDates">
  <div class="testingTablHeader">Enter tentative release dates</div>
  <table width="424" cellpadding="0" cellspacing="1" class="" id="id_tab_relDates">
  </table>
  <div class="tabsDefineScoprButtons">
            <div class="TAASSubmitButton" onclick="validateRelDates()">DONE</div>
        </div>
        <br><br>
  </div> -->
  <div id="id_div_eff">
<table width="1080" cellpadding="0" cellspacing="1" class="tableTaas">
<form:hidden path="genTestOverheadList[0].genericTestingOverheadId" value="${genTestOverheadList[0].genericTestingOverheadId}"/>
  <tr>
    <td width="830" height="24" bgcolor="#fafafa">Test Management Effort (% of Test Plan+Design+Execution Effort)</td>
    <td width="126" align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_test_mgmt_eff" onkeyup="checkIfNumber(this)" path="genTestOverheadList[0].testMgmtEffort" value="${genTestOverheadList[0].testMgmtEffort}"/>
    </td>
    <td width="118" align="center" bgcolor="#fafafa">between 5 to 10%</td>
  </tr>
  <tr>
    <td width="830" height="24" bgcolor="#fafafa">Requirement Analysis & Test Planning effort (% of Test desgn effort)</td>
    <td width="126" align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_reqmntAnalysis_testPlanning" onkeyup="checkIfNumber(this)" path="genTestOverheadList[0].ratpeffort" value="${genTestOverheadList[0].ratpeffort}"/>
    </td>
    <td width="118" align="center" bgcolor="#fafafa">between 5 to 10%</td>
  </tr>
  <tr>
    <td width="830" height="24" bgcolor="#fafafa">Automation Execution effort (% of Test execution effort)</td>
    <td width="126" align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_auto_exec_eff" onkeyup="checkIfNumber(this)" path="genTestOverheadList[0].automationExecEffort" value="${genTestOverheadList[0].automationExecEffort}"/>
    </td>
    <td width="118" align="center" bgcolor="#fafafa">between 5 to 10%</td>
  </tr>
  <tr>
    <td height="24" bgcolor="#fafafa">Automation Maintenance Effort in subsequent releases</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_auto_maint_eff" onkeyup="checkIfNumber(this)" path="genTestOverheadList[0].automationMaintEffort" value="${genTestOverheadList[0].automationMaintEffort}"/>
    </td>
	<td width="118" align="center" bgcolor="#fafafa">between 5 to 10%</td>
  </tr>
</table>
</div>
<div class="testingTablHeader">Oppurtunity Specific Testing Inputs	</div>
<table width="1080" cellpadding="0" cellspacing="1" class="tableTaas" style="margin-bottom:3px;">
<form:hidden path="majEffLeverList[0].efficiencyLeverId" value="${majEffLeverList[0].efficiencyLeverId}"/>
<form:hidden path="majEffLeverList[1].efficiencyLeverId" value="${majEffLeverList[1].efficiencyLeverId}"/>
<form:hidden path="majEffLeverList[2].efficiencyLeverId" value="${majEffLeverList[2].efficiencyLeverId}"/>
<form:hidden path="majEffLeverList[0].testType" value="${majEffLeverList[0].testType}"/>
<form:hidden path="majEffLeverList[1].testType" value="${majEffLeverList[1].testType}"/>
<form:hidden path="majEffLeverList[2].testType" value="${majEffLeverList[2].testType}"/>
<form:hidden path="majEffLeverList[0].releaseType" value="${majEffLeverList[0].releaseType}"/>
<form:hidden path="majEffLeverList[1].releaseType" value="${majEffLeverList[1].releaseType}"/>
<form:hidden path="majEffLeverList[2].releaseType" value="${majEffLeverList[2].releaseType}"/>
  <tr bgcolor="#d6d6d6">
    <td width="351" rowspan="2"><strong>For a Major release</strong></td>
    <td width="134" align="center"><strong>Year 1</strong></td>
    <td height="24" colspan="3" align="center"><strong>Efficiency Levers</strong></td>
    <td width="150" colspan="2" align="center"><strong>AS IS</strong></td>
  </tr>
  <tr bgcolor="#d6d6d6">
    <td width="134" align="center">Avg No of test cases</td>
    <td width="161" height="24" align="center">% Automation Design</td>
    <td width="134" align="center">Test Design reuse</td>
    <td width="141" align="center">Test execution Cycles</td>
    <td width="150" colspan="2" align="center">Test execution Cycles</td>
  </tr>
  <tr>
    <td height="20" bgcolor="#fafafa">Regression</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_reg_avgNumTestCases" onkeyup="checkIfNumber(this)" path="majEffLeverList[0].avgNoTestCases" value="${majEffLeverList[0].avgNoTestCases}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_reg_perAutoDesign" onkeyup="checkIfNumber(this)" path="majEffLeverList[0].pcautomationDesign" value="${majEffLeverList[0].pcautomationDesign}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_reg_testDesignReuse" onkeyup="checkIfNumber(this)" path="majEffLeverList[0].testDesignReuse" value="${majEffLeverList[0].testDesignReuse}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_reg_testExecCycles" onkeyup="checkIfNumber(this)" path="majEffLeverList[0].testExecutionCycles" value="${majEffLeverList[0].testExecutionCycles}"/>
    </td>
    <td colspan="2" align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_reg_asIsTestExecCycles" onkeyup="checkIfNumber(this)" path="majEffLeverList[0].asistestExecutionCycles" value="${majEffLeverList[0].asistestExecutionCycles}"/>
    </td>
  </tr>
  <tr>
    <td height="20" bgcolor="#fafafa">New functionality</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_newFunc_avgNumTestCases" onkeyup="checkIfNumber(this)" path="majEffLeverList[1].avgNoTestCases" value="${majEffLeverList[1].avgNoTestCases}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_newFunc_perAutoDesign" onkeyup="checkIfNumber(this)" path="majEffLeverList[1].pcautomationDesign" value="${majEffLeverList[1].pcautomationDesign}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_newFunc_testDesignReuse" onkeyup="checkIfNumber(this)" path="majEffLeverList[1].testDesignReuse" value="${majEffLeverList[1].testDesignReuse}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_newFunc_testExecCycles" onkeyup="checkIfNumber(this)" path="majEffLeverList[1].testExecutionCycles" value="${majEffLeverList[1].testExecutionCycles}"/>
    </td>
    <td colspan="2" align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_newFunc_asIsTestExecCycles" onkeyup="checkIfNumber(this)" path="majEffLeverList[1].asistestExecutionCycles" value="${majEffLeverList[1].asistestExecutionCycles}"/>
    </td>
  </tr>
  <tr>
    <td height="20" bgcolor="#fafafa">UAT/Prod/Go Live</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_uat_avgNumTestCases" onkeyup="checkIfNumber(this)" path="majEffLeverList[2].avgNoTestCases" value="${majEffLeverList[2].avgNoTestCases}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_uat_perAutoDesign" onkeyup="checkIfNumber(this)" path="majEffLeverList[2].pcautomationDesign" value="${majEffLeverList[2].pcautomationDesign}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_uat_testDesignReuse" onkeyup="checkIfNumber(this)" path="majEffLeverList[2].testDesignReuse" value="${majEffLeverList[2].testDesignReuse}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_uat_testExecCycles" onkeyup="checkIfNumber(this)" path="majEffLeverList[2].testExecutionCycles" value="${majEffLeverList[2].testExecutionCycles}"/>
    </td>
    <td colspan="2" align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_maj_uat_asIsTestExecCycles" onkeyup="checkIfNumber(this)" path="majEffLeverList[2].asistestExecutionCycles" value="${majEffLeverList[2].asistestExecutionCycles}"/>
    </td>
  </tr>
</table>
<table width="1080" cellpadding="0" cellspacing="1" class="tableTaas" style="margin-bottom:3px;">
<form:hidden path="minEffLeverList[0].efficiencyLeverId" value="${minEffLeverList[0].efficiencyLeverId}"/>
<form:hidden path="minEffLeverList[1].efficiencyLeverId" value="${minEffLeverList[1].efficiencyLeverId}"/>
<form:hidden path="minEffLeverList[2].efficiencyLeverId" value="${minEffLeverList[2].efficiencyLeverId}"/>
<form:hidden path="minEffLeverList[0].testType" value="${minEffLeverList[0].testType}"/>
<form:hidden path="minEffLeverList[1].testType" value="${minEffLeverList[1].testType}"/>
<form:hidden path="minEffLeverList[2].testType" value="${minEffLeverList[2].testType}"/>
<form:hidden path="minEffLeverList[0].releaseType" value="${minEffLeverList[0].releaseType}"/>
<form:hidden path="minEffLeverList[1].releaseType" value="${minEffLeverList[1].releaseType}"/>
<form:hidden path="minEffLeverList[2].releaseType" value="${minEffLeverList[2].releaseType}"/>
  <tr bgcolor="#d6d6d6">
    <td width="349"><strong>For a Minor release</strong></td>
    <td width="135" align="center">Avg No of test cases</td>
    <td width="162" height="24" align="center">% new Automation Design</td>
    <td width="134" align="center">Test case reuse</td>
    <td width="141" align="center">Test execution Cycles</td>
    <td width="150" colspan="2" align="center">Test execution Cycles</td>
  </tr>
  <tr>
    <td height="20" bgcolor="#fafafa">Regression</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_reg_avgNumTestCases" onkeyup="checkIfNumber(this)" path="minEffLeverList[0].avgNoTestCases" value="${minEffLeverList[0].avgNoTestCases}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_reg_perAutoDesign" onkeyup="checkIfNumber(this)" path="minEffLeverList[0].pcautomationDesign" value="${minEffLeverList[0].pcautomationDesign}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_reg_testDesignReuse" onkeyup="checkIfNumber(this)" path="minEffLeverList[0].testDesignReuse" value="${minEffLeverList[0].testDesignReuse}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_reg_testExecCycles" onkeyup="checkIfNumber(this)" path="minEffLeverList[0].testExecutionCycles" value="${minEffLeverList[0].testExecutionCycles}"/>
    </td>
    <td colspan="2" align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_reg_asIsTestExecCycles" onkeyup="checkIfNumber(this)" path="minEffLeverList[0].asistestExecutionCycles" value="${minEffLeverList[0].asistestExecutionCycles}"/>
    </td>
  </tr>
  <tr>
    <td height="20" bgcolor="#fafafa">New functionality</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_newFunc_avgNumTestCases" onkeyup="checkIfNumber(this)" path="minEffLeverList[1].avgNoTestCases" value="${minEffLeverList[1].avgNoTestCases}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_newFunc_perAutoDesign" onkeyup="checkIfNumber(this)" path="minEffLeverList[1].pcautomationDesign" value="${minEffLeverList[1].pcautomationDesign}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_newFunc_testDesignReuse" onkeyup="checkIfNumber(this)" path="minEffLeverList[1].testDesignReuse" value="${minEffLeverList[1].testDesignReuse}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_newFunc_testExecCycles" onkeyup="checkIfNumber(this)" path="minEffLeverList[1].testExecutionCycles" value="${minEffLeverList[1].testExecutionCycles}"/>
    </td>
    <td colspan="2" align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_newFunc_asIsTestExecCycles" onkeyup="checkIfNumber(this)" path="minEffLeverList[1].asistestExecutionCycles" value="${minEffLeverList[1].asistestExecutionCycles}"/>
    </td>
  </tr>
  <tr>
    <td height="20" bgcolor="#fafafa">UAT/Prod/Go Live</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_uat_avgNumTestCases" onkeyup="checkIfNumber(this)" path="minEffLeverList[2].avgNoTestCases" value="${minEffLeverList[2].avgNoTestCases}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_uat_perAutoDesign" onkeyup="checkIfNumber(this)" path="minEffLeverList[2].pcautomationDesign" value="${minEffLeverList[2].pcautomationDesign}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_uat_testDesignReuse" onkeyup="checkIfNumber(this)" path="minEffLeverList[2].testDesignReuse" value="${minEffLeverList[2].testDesignReuse}"/>
    </td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_uat_testDesignReuse" onkeyup="checkIfNumber(this)" path="minEffLeverList[2].testExecutionCycles" value="${minEffLeverList[2].testExecutionCycles}"/>
    </td>
    <td colspan="2" align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_min_uat_asIsTestExecCycles" onkeyup="checkIfNumber(this)" path="minEffLeverList[2].asistestExecutionCycles" value="${minEffLeverList[2].asistestExecutionCycles}"/>
    </td>
  </tr>
</table>
<table width="651" cellpadding="0" cellspacing="1" class="tableTaas">
<form:hidden path="regLeverList[0].regressionLeverId" value="${regLeverList[0].regressionLeverId}"/> 
  <tr bgcolor="#d6d6d6">
    <td width="485" height="24"><strong>Regression Lever</strong></td>
    <td width="161" align="center"><strong>Target Values</strong></td>
  </tr>
  <tr>
    <td height="24" bgcolor="#fafafa">% Regression optimized/obsolete testcases/release</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_reg_opt_obs_rel" onkeyup="checkIfNumber(this)" path="regLeverList[0].pcregression" value="${regLeverList[0].pcregression}"/>
    </td>
  </tr>
  <tr>
    <td height="20" bgcolor="#fafafa">% New functionality testcases added to regression test cases/release</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_newFunc_testCase_rel" onkeyup="checkIfNumber(this)" path="regLeverList[0].pcnewFunctionality" value="${regLeverList[0].pcnewFunctionality}"/>
    </td>
  </tr>
  <tr>
    <td height="20" bgcolor="#fafafa">% Automated regression scripts of major release, reused in Minor release</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="textBoxSNumric" id="id_auto_reg_rel" onkeyup="checkIfNumber(this)" path="regLeverList[0].pcautomatedRegression" value="${regLeverList[0].pcautomatedRegression}"/>
    </td>
  </tr>
</table>
<!-- <table width="800" cellpadding="0" cellspacing="1" class="tableTaas">
<form:hidden path="testEffRedList[0].testEffReductionId" value="${testEffRedList[0].testEffReductionId}"/>
  <tr>
    <td width="484" rowspan="5" bgcolor="#fafafa">Year on Year Test %Effort Reduction, compared to previous Year</td>
    <td width="161" align="center" bgcolor="#fafafa">
      <form:input type="text" class="txtinputComonfield" id="id_test_eff_red_year2" onkeyup="checkIfNumber(this)" path="testEffRedList[0].secondYear" value="${testEffRedList[0].secondYear}"/>
    </td>
    <td width="149" height="24" align="center" bgcolor="#fafafa">Year 2</td>
  </tr>
  <tr>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="txtinputComonfield" id="id_test_eff_red_year3" onkeyup="checkIfNumber(this)" path="testEffRedList[0].thirdYear" value="${testEffRedList[0].thirdYear}"/>
    </td>
    <td height="20" align="center" bgcolor="#fafafa">Year 3</td>
  </tr>
  <tr>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="txtinputComonfield" id="id_test_eff_red_year4" onkeyup="checkIfNumber(this)" path="testEffRedList[0].fourthYear" value="${testEffRedList[0].fourthYear}"/>
    </td>
    <td height="20" align="center" bgcolor="#fafafa">Year 4</td>
  </tr>
  <tr>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="txtinputComonfield" id="id_test_eff_red_year5" onkeyup="checkIfNumber(this)" path="testEffRedList[0].fifthYear" value="${testEffRedList[0].fifthYear}"/>
   </td>
    <td height="20" align="center" bgcolor="#fafafa">Year 5</td>
  </tr>
  <tr>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="txtinputComonfield" id="id_test_eff_red_year6" onkeyup="checkIfNumber(this)" path="testEffRedList[0].sixthYear" value="${testEffRedList[0].sixthYear}"/>
    </td>
    <td height="20" align="center" bgcolor="#fafafa">Year 6</td>
  </tr>
  <tr>
    <td bgcolor="#fafafa">&nbsp;</td>
    <td align="center" bgcolor="#fafafa">
      <form:input type="text" class="txtinputComonfield" id="id_test_eff_red_year7" onkeyup="checkIfNumber(this)" path="testEffRedList[0].seventhYear" value="${testEffRedList[0].seventhYear}"/>
</td>
    <td height="20" align="center" bgcolor="#fafafa">Year 7</td>
  </tr>
</table> -->


<div id="bodyDiv1" class="mainBodyDiv1"
			style="width: 100%; mafloat: right; margin-bottom: 5x;">
			<table width="100%" border="0" style="padding-right: 0%">
				<tr>
					<td class="tdButtonRow" align="right">
					 <a  onclick="saveData()" id="id_taas_submit"
						class="TabsCommonButtons">Save</a></td>
				</tr>
			</table>
		</div>
		</form:form>


</div>
<br>
<!--  Uncommented by KP - 7 July -->
<div class="taasOutputDiv" id="taasOutputDiv">
<form:form id="id_taasOutputForm" modelAttribute="taasOPForm">
<div class="testingTablHeader">Testing Output </div>
<table width="1080" cellpadding="0" cellspacing="1"  style="margin-bottom:3px;">
  <tr>
    <td width="193" height="35" align="center" bgcolor="#efefef"><strong>YEAR 1</strong></td>
    <td width="171" bgcolor="#efefef" align="center">Release#</td>
    <td width="180" height="35" align="center" bgcolor="#efefef">No of manual test cases</td>
    <td width="171" align="center" bgcolor="#efefef">No of automated test case</td>
    <td width="174" align="center" bgcolor="#efefef">Manual  Test efforts<br />
(Per Month)</td>
    <td width="182" colspan="2" align="center" bgcolor="#efefef">Automation Dev effort<br />
    (Per Month)</td>
  </tr>
  
  <tr>
    <td rowspan="3" align="center" bgcolor="#EFEFEF"><strong>Major release</strong></td>
    <td height="20" align="center" bgcolor="#fafafa"><form:input class="txtinputComonfield" path="majTaasOpList[0].numRelease" value="${majTaasOpList[0].numRelease}"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="majTaasOpList[0].numManualTC" value="${majTaasOpList[0].numManualTC}"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="majTaasOpList[0].numAutoTC" /></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="majTaasOpList[0].manualTestEff"/></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="majTaasOpList[0].autoDevEff"/></td>
  </tr>
   <c:forEach var="i" begin="1" end="2"> 
  <tr>
    <td height="20" align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="majTaasOpList[${i}].numRelease"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="${majTaasOpList[i].numManualTC}"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="${majTaasOpList[i].numAutoTC}"/></td>
    <td align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="${majTaasOpList[i].manualTestEff}"/></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><form:input type="text" class="txtinputComonfield" path="${majTaasOpList[i].autoDevEff}"/></td>
  </tr>
   </c:forEach> 
  <!-- tr>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr
  <tr>
    <td rowspan="6" align="center" bgcolor="#EFEFEF"><strong>Minor release</strong></td>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td colspan="2" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="#EFEFEF"><strong>Yearly TOTAL</strong></td>
    <td align="center" bgcolor="#EFEFEF"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#EFEFEF"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#EFEFEF"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#EFEFEF"><input type="text" class="txtinputComonfield" /></td>
    <td colspan="2" align="center" bgcolor="#EFEFEF"><input type="text" class="txtinputComonfield" /></td>
  </tr>
</table>
<table width="897" cellpadding="0" cellspacing="1" class="tableText" style="margin-bottom:3px;">
  <tr>
    <td width="193" height="35" align="center" bgcolor="#efefef"><strong>Year #</strong></td>
    <td width="171" bgcolor="#efefef" align="center">Total Effort <br />
    PerMonth / Year</td>
    <td width="180" height="35" align="center" bgcolor="#efefef">Total Effort <br />
    (pd)/ Year</td>
    <td width="171" align="center" bgcolor="#efefef">Total Effort<br />
(ph)/ Year</td>
    <td width="174" align="center" bgcolor="#efefef">Avg FTE / Year</td>
  </tr>
  <tr>
    <td align="center" bgcolor="#FAFAFA">Year 1</td>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td align="center" bgcolor="#FAFAFA">Year 2</td>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td align="center" bgcolor="#FAFAFA">Year 3</td>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td align="center" bgcolor="#FAFAFA">Year 4</td>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td align="center" bgcolor="#FAFAFA">Year 5</td>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td align="center" bgcolor="#FAFAFA">Year 6</td>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr>
  <tr>
    <td align="center" bgcolor="#FAFAFA">Year 7</td>
    <td height="20" align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
    <td align="center" bgcolor="#fafafa"><input type="text" class="txtinputComonfield" /></td>
  </tr> -->
</table>
</form:form> 
</div> 
 
 <!--  Uncommented End by KP - 7 July -->
<p>&nbsp;</p>
</body>
