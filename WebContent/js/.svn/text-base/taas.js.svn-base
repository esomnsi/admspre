var numMajorRelease, numMinorRelease;

function saveData()
{
	
	var sumRatio;
	// Check if any of the fields are empty
	if($.trim($('#taasPercentOfServiceElementEffort').val()).length <= 0 )
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the percent value for Application Testing Effort');
//		alert('Please enter the percent value for Application Testing Effort');
		return;
	}
	else if($.trim($('#taasPercentOfServiceElementEffort').val()) != 100  )
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('The total percent value for Application Testing Effort should be 100');
//		alert('Please enter the percent value for Application Testing Effort');
		return;
	}
	else if($.trim($('#id_simple_ratio').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the ratio value for complexity factor - Simple');
//		alert('Please enter the ratio value for complexity factor - Simple');
		return;
	}
	else if($.trim($('#id_medium_ratio').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the ratio value for complexity factor - Medium');
//		alert('Please enter the ratio value for complexity factor - Medium');
		return;
	}
	else if($.trim($('#id_complex_ratio').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the ratio value for complexity factor - Complex');
//		alert('Please enter the ratio value for complexity factor - Complex');
		return;
	}
	else if($.trim($('#id_simple_test_design').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the test design value for complexity factor - Simple');
//		alert('Please enter the test design value for complexity factor - Simple');
		return;
	}
	else if($.trim($('#id_simple_test_exec').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the test execution value for complexity factor - Simple');
//		alert('Please enter the test execution value for complexity factor - Simple');
		return;
	}
	else if($.trim($('#id_simple_automatic_script').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the automatic scripting value for complexity factor - Simple');
//		alert('Please enter the automatic scripting value for complexity factor - Simple');
		return;
	}
	else if($.trim($('#id_medium_test_design').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the test design value for complexity factor - Medium');
//		alert('Please enter the test design value for complexity factor - Medium');
		return;
	}
	else if($.trim($('#id_medium_test_exec').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the test execution value for complexity factor - Medium');
//		alert('Please enter the test execution value for complexity factor - Medium');
		return;
	}
	else if($.trim($('#id_medium_automatic_script').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the automatic scripting value for complexity factor - Medium');
//		alert('Please enter the automatic scripting value for complexity factor - Medium');
		return;
	}
	else if($.trim($('#id_complex_test_design').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the test design value for complexity factor - Complex');
//		alert('Please enter the test design value for complexity factor - Complex');
		return;
	}
	else if($.trim($('#id_complex_test_exec').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the test execution value for complexity factor - Complex');
//		alert('Please enter the test execution value for complexity factor - Complex');
		return;
	}
	else if($.trim($('#id_complex_automatic_script').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the automatic scripting value for complexity factor - Complex');
//		alert('Please enter the automatic scripting value for complexity factor - Complex');
		return;
	}
	else if($.trim($('#id_test_mgmt_eff').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Test Management effort');
//		alert('Please enter the Test Management effort');
		return;
	}
	else if($.trim($('#id_reqmntAnalysis_testPlanning').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Requirement Analysis & Test Planning effort');
//		alert('Please enter the Requirement Analysis & Test Planning effort');
		return;
	}
	else if($.trim($('#id_auto_exec_eff').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Automation Execution effort');
//		alert('Please enter the Automation Execution effort');
		return;
	}
	else if($.trim($('#id_auto_maint_eff').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Automation Maintenance effort');
//		alert('Please enter the Automation Maintenance effort');
		return;
	}
	else if($.trim($('#id_maj_reg_avgNumTestCases').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Average Number of Regression test cases for a major release');
//		alert('Please enter the Average Number of Regression test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_reg_perAutoDesign').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Automation Design of Regression test cases for a major release');
//		alert('Please enter the Percent Automation Design of Regression test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_reg_testDesignReuse').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Test Design Reuse of Regression test cases for a major release');
//		alert('Please enter the Percent Test Design Reuse of Regression test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_reg_testExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Test Execution cycles of Regression test cases for a major release');
//		alert('Please enter the Test Execution cycles of Regression test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_reg_asIsTestExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the As Is Test Execution cycles of Regression test cases for a major release');
//		alert('Please enter the As Is Test Execution cycles of Regression test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_newFunc_avgNumTestCases').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Average Number of New Functionality test cases for a major release');
//		alert('Please enter the Average Number of New Functionality test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_newFunc_perAutoDesign').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Automation Design of New Functionality test cases for a major release');
//		alert('Please enter the Percent Automation Design of New Functionality test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_newFunc_testDesignReuse').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Test Design Reuse of New Functionality test cases for a major release');
//		alert('Please enter the Percent Test Design Reuse of New Functionality test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_newFunc_testExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Test Execution cycles of New Functionality test cases for a major release');
//		alert('Please enter the Test Execution cycles of New Functionality test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_newFunc_asIsTestExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the As Is Test Execution cycles of New Functionality test cases for a major release');
//		alert('Please enter the As Is Test Execution cycles of New Functionality test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_uat_avgNumTestCases').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Average Number of UAT/Prod/Go Live test cases for a major release');
//		alert('Please enter the Average Number of UAT/Prod/Go Live test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_uat_perAutoDesign').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Automation Design of UAT/Prod/Go Live test cases for a major release');
//		alert('Please enter the Percent Automation Design of UAT/Prod/Go Live test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_uat_testDesignReuse').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Test Design Reuse of UAT/Prod/Go Live test cases for a major release');
//		alert('Please enter the Percent Test Design Reuse of UAT/Prod/Go Live test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_uat_testExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Test Execution cycles of UAT/Prod/Go Live test cases for a major release');
//		alert('Please enter the Test Execution cycles of UAT/Prod/Go Live test cases for a major release');
		return;
	}
	else if($.trim($('#id_maj_uat_asIsTestExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the As Is Test Execution cycles of UAT/Prod/Go Live test cases for a major release');
//		alert('Please enter the As Is Test Execution cycles of UAT/Prod/Go Live test cases for a major release');
		return;
	}
	else if($.trim($('#id_min_reg_avgNumTestCases').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Average Number of Regression test cases for a minor release');
//		alert('Please enter the Average Number of Regression test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_reg_perAutoDesign').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Automation Design of Regression test cases for a minor release');
//		alert('Please enter the Percent Automation Design of Regression test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_reg_testDesignReuse').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Test Design Reuse of Regression test cases for a minor release');
//		alert('Please enter the Percent Test Design Reuse of Regression test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_reg_testExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Test Execution cycles of Regression test cases for a minor release');
//		alert('Please enter the Test Execution cycles of Regression test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_reg_asIsTestExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the As Is Test Execution cycles of Regression test cases for a minor release');
//		alert('Please enter the As Is Test Execution cycles of Regression test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_newFunc_avgNumTestCases').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Average Number of New Functionality test cases for a minor release');
//		alert('Please enter the Average Number of New Functionality test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_newFunc_perAutoDesign').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Automation Design of New Functionality test cases for a minor release');
//		alert('Please enter the Percent Automation Design of New Functionality test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_newFunc_testDesignReuse').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Test Design Reuse of New Functionality test cases for a minor release');
//		alert('Please enter the Percent Test Design Reuse of New Functionality test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_newFunc_testExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Test Execution cycles of New Functionality test cases for a minor release');
//		alert('Please enter the Test Execution cycles of New Functionality test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_newFunc_asIsTestExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the As Is Test Execution cycles of New Functionality test cases for a minor release');
//		alert('Please enter the As Is Test Execution cycles of New Functionality test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_uat_avgNumTestCases').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Average Number of UAT/Prod/Go Live test cases for a minor release');
//		alert('Please enter the Average Number of UAT/Prod/Go Live test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_uat_perAutoDesign').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Automation Design of UAT/Prod/Go Live test cases for a minor release');
//		alert('Please enter the Percent Automation Design of UAT/Prod/Go Live test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_uat_testDesignReuse').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Percent Test Design Reuse of UAT/Prod/Go Live test cases for a minor release');
//		alert('Please enter the Percent Test Design Reuse of UAT/Prod/Go Live test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_uat_testDesignReuse').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the Test Execution cycles of UAT/Prod/Go Live test cases for a minor release');
//		alert('Please enter the Test Execution cycles of UAT/Prod/Go Live test cases for a minor release');
		return;
	}
	else if($.trim($('#id_min_uat_asIsTestExecCycles').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the As Is Test Execution cycles of UAT/Prod/Go Live test cases for a minor release');
//		alert('Please enter the As Is Test Execution cycles of UAT/Prod/Go Live test cases for a minor release');
		return;
	}
	else if($.trim($('#id_reg_opt_obs_rel').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the percent Regression optimized/obsolete testcases per release');
//		alert('Please enter the percent Regression optimized/obsolete testcases per release');
		return;
	}
	else if($.trim($('#id_newFunc_testCase_rel').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the percent New Functionality test cases added to regression test cases per release');
//		alert('Please enter the percent New Functionality test cases added to regression test cases per release');
		return;
	}
	else if($.trim($('#id_auto_reg_rel').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the percent Automated regression scripts of Major release, reused in Minor release');
//		alert('Please enter the percent Automated regression scripts of Major release, reused in Minor release');
		return;
	}
	/*else if($.trim($('#id_test_eff_red_year2').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the percent Test Effort Reduction for Year 2');
//		alert('Please enter the percent Test Effort Reduction for Year 2');
		return;
	}
	else if($.trim($('#id_test_eff_red_year3').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the percent Test Effort Reduction for Year 3');
//		alert('Please enter the percent Test Effort Reduction for Year 3');
		return;
	}
	else if($.trim($('#id_test_eff_red_year4').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the percent Test Effort Reduction for Year 4');
//		alert('Please enter the percent Test Effort Reduction for Year 4');
		return;
	}
	else if($.trim($('#id_test_eff_red_year5').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the percent Test Effort Reduction for Year 5');
//		alert('Please enter the percent Test Effort Reduction for Year 5');
		return;
	}
	else if($.trim($('#id_test_eff_red_year6').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the percent Test Effort Reduction for Year 6');
//		alert('Please enter the percent Test Effort Reduction for Year 6');
		return;
	}
	else if($.trim($('#id_test_eff_red_year7').val()).length <= 0)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter the percent Test Effort Reduction for Year 7');
//		alert('Please enter the percent Test Effort Reduction for Year 7');
		return;
	}*/
	//Validate if the sum of the ratio is equal to 100
	sumRatio = parseFloat($.trim($('#id_simple_ratio').val())) + parseFloat($.trim($('#id_medium_ratio').val())) + parseFloat($.trim($('#id_complex_ratio').val()));
	if(sumRatio != 100)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Sum of ratio should be 100%');
//		alert('Sum of ratio should be 100%');
		return;
	}
	document.getElementById('id_taas_form').action = $('#id_hiddenAppname').val()+'/save/taasdetails';
	document.getElementById('id_taas_form').submit();
	
/*
	var appName = $('#id_hiddenAppname').val();
	var taasInputForm = $('#id_taas_form');
	$.ajax({
		type : 'POST',
		url : appName+'/save/taasdetails',
		data : taasInputForm.serialize(),
		success : function(response)
		{
			alert("Ajax Success");
			window.location.href="../solution/taas_output.jsp";
			//window.location = document.getElementById('id_hiddenAppname').value+"/solution/taas_output";
			$('#id_div_taas_input').hide();
			$('#taasOutputDiv').show();
			$('#taasOutputDiv').html(response);
			},
			error : function(response)
			{
				$('#id_div_errorMsg').show();
				$('#id_div_errorMsg').html('Error occurred while calculating output. Please try again.');
//				alert('Error Occurred. Please try again.');
				} 
		});*/
	/*$('#id_div_taas_input').hide();
	$('#taasOutputDiv').show();*/
}

function checkIfNumber(s)
{
	$('#id_div_errorMsg').hide();
	var n = $('#'+s.id).val();
	if(!isNaN(parseFloat(n)) && isFinite(n))
	{
		
	}
	else
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter a valid number.');
//		alert('Please enter correct values');
		$("#"+s.id).attr("value", "");
		return;
	}
}

function checkIfInt(a)
{
	$('#id_div_errorMsg').hide();
	var n = $('#'+a.id).val();
	var k = ~~Number(n);
	if((String(k) === n) && (n >=0))
	{
		
	}
	else
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Please enter a valid number.');
//		alert('Please enter correct values');
		$("#"+a.id).attr("value", "");
		return;
	}
}
function checkForTgtDistrn(s)
{
	var value = s.options[s.selectedIndex].value;
	var arr_s = s.id.split('_');
	if(value == 'Major')
	{
		document.getElementById('idtaasInputTgtDistrn_'+arr_s[1]).removeAttribute('readonly');
	}
	else
	{
		document.getElementById('idtaasInputTgtDistrn_'+arr_s[1]).value='';
		document.getElementById('idtaasInputTgtDistrn_'+arr_s[1]).setAttribute('readonly', 'readonly');
	}
}
function validateGenericInputs()
{
	sum = 0;
	flag = 0;
	$('select[id^="select_"]').each(function() {
		if(this.value == 'Select' )
		{
			$('#id_div_errorMsg').show();
			$('#id_div_errorMsg').html('Please select a Major or Minor release.');
//			alert('Please select a Major or Minor release.');
			flag =1;
			return false;
		}
		else if(this.value == 'Major')
		{
			var idRight = this.id.split('_')[1];
			sum = sum + Number($('#idtaasInputTgtDistrn_'+idRight).val());
		}
		else if(this.value != 'Major')
		{
			var idRight = this.id.split('_')[1];
			$('#idtaasInputTgtDistrn_'+idRight).val('');
		}
		if(this.value != 'Select' )
		{
			$('input[id^="iddate_"]').each(function() {
				if($.trim(this.value) == '')
				{
					$('#id_div_errorMsg').show();
					$('#id_div_errorMsg').html('Please select a proper release date.');
//					alert('Please select a proper release date.');
					flag =1;
					return false;
				}	
			});
		}	
	});
	if(sum!=100)
	{
		$('#id_div_errorMsg').show();
		$('#id_div_errorMsg').html('Sum of all target distribution should be 100%');
//        alert("Please provide the target distribution value for all major releases.");
        flag =1;
        return false;
    }
	if(flag==0)validateAndSubmit();
}

function goBack()
{
	window.location = document.getElementById('id_hiddenAppname').value+"/solution/volumetricApplicationTesting";
}
