var totalOverrideWeightage = 0.00;

/**
 * *****************************************************LOAD Complexity Adjuster
 * SECTION STARTS HERE***********************************************
 */

/**
 * ----------------------loadVerticalCreationPage---------------------------- On
 * Click Adjust Complexity Tab in the menu bar the loadAdjustComplexity method
 * will call and complexityAdjuster.jsp page will load into the browser
 * --------------------------------------------------------------------------
 */
var secureDomainUrl = "";// this is here only upto the implementation of
// Spring Security
function computedComplexityLoad() {
	var computedComplexity = $('#computedComplexity').val();
	if(computedComplexity.trim()!= ""){
		if (computedComplexity >= "3") {
			$('#computedComplexityHtmlValue').html("Complex");
			$('#computedComplexityA').html("Complex");
			$('#defaultComplexityAdjustorValue').html("10 %");
		} else if (computedComplexity < "3" && computedComplexity > "1") {
			$('#computedComplexityHtmlValue').html("Medium");
			$('#computedComplexityA').html("Medium");
			$('#defaultComplexityAdjustorValue').html("5 %");
		} else if (computedComplexity <= "1") {
			$('#computedComplexityHtmlValue').html("Simple");
			$('#computedComplexityA').html("Simple");
			$('#defaultComplexityAdjustorValue').html("0 %");
		} else if (computedComplexity == "") {
			$('#computedComplexityHtmlValue').html("ERROR");
		}
	}
	setUtilizationPerYear();
	//adjustedValueAll("onLoad");
}

/**
 * This function calculates the calculateOverrideValue data in the add
 * .DoneMS2E_Rel1_June2012
 * 
 * @param el
 */
function calculateOverrideValue(el) {
	var totalVal = 0;
	if ($.trim($(el).val()) == "") {
		$(el).val("0");
		return;
	} else {
		var skillWeightage = $('#skillWeightage').val();
		var regionWeightage = $('#regionWeightage').val();
		var auditWeightage = $('#auditWeightage').val();
		var slaweightage = $('#slaweightage').val();
		if (skillWeightage == '' || isNaN(skillWeightage)) {
			skillWeightage = 0;
		}
		if (regionWeightage == '' || isNaN(regionWeightage)) {
			regionWeightage = 0;
		}
		if (auditWeightage == '' || isNaN(auditWeightage)) {
			auditWeightage = 0;
		}
		if (slaweightage == '' || isNaN(slaweightage)) {
			slaweightage = 0;
		}
		if (slaweightage < 0) {
			slaweightage = 0;
		}
		var calc = parseFloat(parseFloat(skillWeightage)
				+ parseFloat(regionWeightage) + parseFloat(auditWeightage)) + parseFloat(slaweightage);
		totalVal = calc;
		var calculatedSLAweightage = (10 - parseFloat(calc));
		if(parseInt($.trim($(el).val())) > parseInt("10")){
			//$('.errorMessageDisp').html(" Value is greater than 10");
			$('#'+el.id+'').val(parseInt($.trim($(el).val()))-Math.abs(parseInt(calculatedSLAweightage)));
			totalVal = 10;
		}else if(parseInt(calc) > parseInt("10")){
			//$('.errorMessageDisp').html("Total Value is greater than 10");
			$('#'+el.id+'').val(parseInt($.trim($(el).val()))-Math.abs(parseInt(calculatedSLAweightage)));
			totalVal = 10;
		}
		
		$('.calc').html(totalVal);
	}
}

/**
 * Allows only numbers and decimal points in the input element
 * 
 * @param evt
 * @returns {Boolean}
 */
function isDecimalField(field, evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && ((charCode < 48 && charCode != 46) || charCode > 57)) {
		return false;
	}

	var decPos = $(field).val().split('.');
	if (decPos.length == 2) {
		if (charCode == 46) {
			return false;
		}

		/*
		 * else if(charCode != 8){ var decPlaces = decPos[1];
		 * if(decPlaces.length >= 2){ alert("3"); return false; } }
		 */
	}
	return true;
}
/**
 * Empties the field when focus is brought on it
 * 
 * @param field
 */
function emptyFieldForInput(field) {
	var value = $.trim($(field).val());
	if (parseFloat(value) <= "0") {
		$(field).val("");
	}
}
/**
 * Empties the field when focus is brought on it
 * 
 * @param Summary
 *            Of Complexity Adjustors
 */
function SummaryOfComplexityAdjustors(el) {
	
	var skillRatingSelected = $('.skillRating option:selected').val();
	var regionRatingSelected = $('.regionRating option:selected').val();
	var auditRatingSelected = $('.auditRating option:selected').val();
	var slaratingSelected = $('.slarating option:selected').val();

	var skillWeightage = parseFloat($('#skillWeightage').val()) * parseFloat(skillRatingSelected);
	var regionWeightage = parseFloat($('#regionWeightage').val()) * parseFloat(regionRatingSelected);
	var auditWeightage = parseFloat($('#auditWeightage').val())	* parseFloat(auditRatingSelected);
	var slaweightage = parseFloat($('#slaweightage').val())	* parseFloat(slaratingSelected);
	
	var totalRating = parseFloat(skillWeightage) + parseFloat(regionWeightage) + parseFloat(auditWeightage) + parseFloat(slaweightage);
	
	var totalWeightage = parseFloat($('#skillWeightage').val()) + parseFloat($('#regionWeightage').val()) + parseFloat($('#auditWeightage').val())
			+ parseFloat($('#slaweightage').val());
	var tempValue = parseFloat(totalRating) / parseFloat(totalWeightage);

	tempValue = roundNumber(tempValue, 0);
	tempValue = parseInt(tempValue);
	if (tempValue >= 3) {
		$('#computedComplexityHtmlValue').html("Complex");
		$('#computedComplexity').val("3");
		$('#computedComplexityA').html("Complex");
		$('#defaultComplexityAdjustorValue').html("10 %");
	} else if (tempValue < 3 && tempValue > 1) {
		$('#computedComplexityHtmlValue').html("Medium");
		$('#computedComplexity').val("2");
		$('#computedComplexityA').html("Medium");
		$('#defaultComplexityAdjustorValue').html("5 %");
	} else if (tempValue <= 1) {
		$('#computedComplexityHtmlValue').html("Simple");
		$('#computedComplexity').val("1");
		$('#computedComplexityA').html("Simple");
		$('#defaultComplexityAdjustorValue').html("0 %");
	} else {
		//$('#computedComplexityHtmlValue').html("Try It Again");
	}
}

function roundNumber(num, dec) {
	var result = Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec);
	return result;
}

function OverAllComplexity(field) {
	if (parseInt($.trim($(field).val())) == 1) {
		$('#complexityAdjustor').val("0");
	} else if (parseInt($.trim($(field).val())) == 2) {
		$('#complexityAdjustor').val("5");
	} else if (parseInt($.trim($(field).val())) == 3) {
		$('#complexityAdjustor').val("10");
	} 
	// $('#overAllRating').val("Simple"); $('#overAllRating').val("Medium");
	// $('#overAllRating').val("Complex");
}

function OverAllEffortContigency(field) {
	$('#contingencyEffort').val(parseInt($.trim($(field).val())));
}
function setUtilizationPerYear() {
	$.each('.actualHrs',function (){
		$('.actualHrs').val("2040");
	});
	/*var utilizationPerYear = $('#utilizationPerYear').val();
	//alert(" utilizationPerYear "+utilizationPerYear);
	if(parseFloat(utilizationPerYear) > 2040 * 3){
		alert("Please Insert valid UtilizationPerYear");
		$('#utilizationPerYear').val("0");
	}else{
		$.each('.actualHrs',function (){
			$('.actualHrs').val(utilizationPerYear);
		});
	}*/
}

/**
 * -------------------------------------------------- This function adds the
 * service name Selected or not.
 * --------------------------------------------------
 */

function adjustedValue(event, obj, serviceNameId) {
	
	var scopeRow = $(obj).parent().parent();
	var actualFTE = $('.actualFTE', scopeRow).html();
	if (actualFTE == "" || isNaN(actualFTE)) {
		actualFTE = 0;
	}
	var actualHrs = $('.actualHrs', scopeRow).val();
	if (actualHrs == '' || isNaN(actualHrs)) {
		actualHrs = 0;
	}
	var complexityAdjustor = $('#complexityAdjustor').val();
	if (complexityAdjustor == '' || isNaN(complexityAdjustor)) {
		complexityAdjustor = $('#defaultComplexityAdjustorValue').html();
		if (complexityAdjustor == '' || isNaN(complexityAdjustor)) {
			complexityAdjustor = 0;
		}
	}
	var contingencyEffort = 0;
	var effort = $('.effort', scopeRow).val();
	var cost = $('.cost', scopeRow).val();
	if(effort != ""){
		contingencyEffort = parseInt(effort) +  parseInt(cost);
	}
	if (contingencyEffort == "" || isNaN(contingencyEffort)) {
		contingencyEffort = 0;
	}
	//alert(effort+"------"+cost+"========"+contingencyEffort);
	var overriddenFTE = $('#overriddenFTE',scopeRow).val();
	if(overriddenFTE == "" || isNaN(overriddenFTE)){
		overriddenFTE = 0;
	}
	var tempValueTotal;
	if(serviceNameId < 6){
		tempValueTotal = parseFloat(actualFTE)
		* (1 + (parseFloat(complexityAdjustor) / 100))
		* (1 + (parseFloat(contingencyEffort) / 100))
		* (1 + (parseFloat(overriddenFTE) / 100));
	}else{
		tempValueTotal = parseFloat(actualFTE)
		* (1 + (parseFloat(complexityAdjustor) / 100))
		* (1 + (parseFloat(overriddenFTE) / 100));
	}
	tempValueTotal = roundNumber(tempValueTotal,2);
	//alert(actualFTE + " :overriddenFTE >>" +  overriddenFTE + "<<contingency>> " + contingencyEffort+" :complexityAdjustor : "+complexityAdjustor+" : tempValueTotal == "+tempValueTotal);
	$('.adjustedFTE',scopeRow).val(tempValueTotal);
	var tempHrs = roundNumber((tempValueTotal * actualHrs),2);
	$('.adjustedHrs',scopeRow).val(tempHrs);
	//alert(actualFTE+" serviceNameId : "+serviceNameId+" AdjustedFTE : "+tempValueTotal+"<<--->>"+effort+"------"+cost+"========"+contingencyEffort);
}

function adjustedValueAll(el) {
	var listOfactualFTE = [];
	listOfactualFTE = $('td').find('.actualFTE');
	
	var complexityAdjustor = $('#complexityAdjustor').val();
	if (complexityAdjustor == '' || isNaN(complexityAdjustor)) {
		complexityAdjustor = $('#defaultComplexityAdjustorValue').html();
		if (complexityAdjustor == '' || isNaN(complexityAdjustor)) {
			complexityAdjustor = 0;
		}
	}
	
	$.each(listOfactualFTE,function (){
		var scopeRow = $(this).parent();
		var actualHrs = $('.actualHrs', scopeRow).val();
		if (actualHrs == '' || isNaN(actualHrs)) {
			actualHrs = 0;
		}
		var actualFTE = $('.actualFTE', scopeRow).html();
		//alert(el.id+"<===>"+complexityAdjustor+"<===>"+actualFTE);
		if(el.id == 'complexityAdjustor' || el == "onLoad"){
			var tempValue = 0.0;
			if($('#overriddenFTE',scopeRow).val() != ''){
				tempValue = parseFloat(actualFTE) * (1 + (parseFloat(complexityAdjustor) / 100)) * (1 + (parseFloat($('#overriddenFTE',scopeRow).val()) / 100));
			}else{
				tempValue = parseFloat(actualFTE) * (1 + (parseFloat(complexityAdjustor) / 100));
			}
			tempValue = roundNumber(tempValue,2);
			$('.adjustedFTE',scopeRow).val(tempValue);
			var tempHrs = 0.0;
			tempHrs = roundNumber((tempValue * actualHrs),2);
			$('.adjustedHrs',scopeRow).val(tempHrs);
		}
	});
}
/*
 * dealCharacteristics selection contigency
 */
function dealCalculate(event){
	//previous param list event,serviceDeliveryYear,serviceScopeId
	// Service level cost effort : future implementation
	var dealCharacteristics = e.options[e.selectedIndex].value;
	if(dealCharacteristics.trim() == "0"){
		$('.effort',parentRow).val("");$('.cost',parentRow).val("");
	}
	//alert("serviceDeliveryYear "+serviceDeliveryYear);
	/*
	
	var complexityAdjustor = $('#complexityAdjustor').val();
	var parentRow= $(e).parent().parent();
	var adjustedFTE = $('#overriddenFTE',parentRow).val();

	if(serviceDeliveryYear == "1"){
		if(dealCharacteristics.trim() == "1"){
			$('.effort',parentRow).val("0");$('.cost',parentRow).val("3");
		}else if(dealCharacteristics.trim() == "2"){
			$('.effort',parentRow).val("2");$('.cost',parentRow).val("3");
		}else if(dealCharacteristics.trim() == "3"){
			$('.effort',parentRow).val("5");$('.cost',parentRow).val("3");
		}else if(dealCharacteristics.trim() == "0"){
			$('.effort',parentRow).val("");$('.cost',parentRow).val("");
		}
	}else{
		if(dealCharacteristics.trim() == "1"){
			$('.effort',parentRow).val("0");$('.cost',parentRow).val("2");
		}else if(dealCharacteristics.trim() == "2"){
			$('.effort',parentRow).val("1");$('.cost',parentRow).val("2");
		}else if(dealCharacteristics.trim() == "3"){
			$('.effort',parentRow).val("3");$('.cost',parentRow).val("2");
		}else if(dealCharacteristics.trim() == "0"){
			$('.effort',parentRow).val("");$('.cost',parentRow).val("");
		}
	}*/
}
/*
 * dealCharacteristics selection contigency
 */
function dealChange(e,serviceDeliveryYear,serviceNameId){
	var dealCharacteristics = e.options[e.selectedIndex].value;
	//alert(dealCharacteristics+" : -----"+serviceNameId+"------ : "+serviceDeliveryYear);
	//var complexityAdjustor = $('#complexityAdjustor').val();
	var parentRow= $(e).parent().parent();
	//var adjustedFTE = $('#overriddenFTE',parentRow).val();
	if(serviceNameId <= 3){
		if(serviceDeliveryYear == "1"){
			if(dealCharacteristics.trim() == "1"){
				$('.effort',parentRow).val("0");$('.cost',parentRow).val("3");
			}else if(dealCharacteristics.trim() == "8"){
				$('.effort',parentRow).val("2");$('.cost',parentRow).val("3");
			}else if(dealCharacteristics.trim() == "9"){
				$('.effort',parentRow).val("5");$('.cost',parentRow).val("3");
			}else if(dealCharacteristics.trim() == "0"){
				$('.effort',parentRow).val("");$('.cost',parentRow).val("");
			}
		}else if(serviceDeliveryYear == "2"){
			if(dealCharacteristics.trim() == "2"){
				$('.effort',parentRow).val("0");$('.cost',parentRow).val("2");
			}else if(dealCharacteristics.trim() == "7"){
				$('.effort',parentRow).val("1");$('.cost',parentRow).val("2");
			}else if(dealCharacteristics.trim() == "10"){
				$('.effort',parentRow).val("3");$('.cost',parentRow).val("2");
			}else if(dealCharacteristics.trim() == "0"){
				$('.effort',parentRow).val("");$('.cost',parentRow).val("");
			}
		}
	}else if(serviceNameId > 3 && serviceNameId < 7){
		if(serviceDeliveryYear == "1"){
			if(dealCharacteristics.trim() == "3"){
				$('.effort',parentRow).val("0");$('.cost',parentRow).val("3");
			}else if(dealCharacteristics.trim() == "6"){
				$('.effort',parentRow).val("4");$('.cost',parentRow).val("3");
			}else if(dealCharacteristics.trim() == "11"){
				$('.effort',parentRow).val("7");$('.cost',parentRow).val("3");
			}else if(dealCharacteristics.trim() == "0"){
				$('.effort',parentRow).val("");$('.cost',parentRow).val("");
			}
		}else if(serviceDeliveryYear == "2"){
			if(dealCharacteristics.trim() == "4"){
				$('.effort',parentRow).val("0");$('.cost',parentRow).val("2");
			}else if(dealCharacteristics.trim() == "5"){
				$('.effort',parentRow).val("2");$('.cost',parentRow).val("2");
			}else if(dealCharacteristics.trim() == "12"){
				$('.effort',parentRow).val("3");$('.cost',parentRow).val("2");
			}else if(dealCharacteristics.trim() == "0"){
				$('.effort',parentRow).val("");$('.cost',parentRow).val("");
			}
		}
	}	
}

function InputValueValidation(event,checkValue) {
	//alert(event.Id+" : "+$(event).val()+" : "+event.name);
	if(parseFloat($(event).val()) > parseFloat(checkValue)){
		alert("Please Insert Valid Data");
		$(event).val("0");
	}
}


