/**
 * *****************************************************LOAD APAAnalysis SECTION STARTS HERE***********************************************
 */
var previousValue;
/**
 * ----------------------loadAPAAnalysis---------------------------- On Click
 * APAAnalysis Tab in the menu bar the loadAPAAnalysis method will call and
 * APAAnalysis.jsp page will load into the browser
 * --------------------------------------------------------------------------
 */

function deleteRow(el) {
	//$('#paramOfScope tr[rowid=' + el + ']:last').remove();
	var rowCountPerScope=$('#paramOfScope tr[rowid=' + el + ']').size();
//alert(rowCountPerScope);	
	if(rowCountPerScope==1){
		$('.message').html("Default row cannot be deleted");
	}else{
	$('.message').html('');	
	$('#paramOfScope tr[rowid=' + el + ']:last').remove();
	}
}

function addRow(el) {	
	var itm = $('#paramOfScope tr[rowid=' + el + ']:first').clone();
	$('.computedRiskFactor', itm).val('');
	itm.insertAfter('#paramOfScope  tr[rowid=' + el + ']:last');
	
	//All values should be blank by default
	var addedRow=$('#paramOfScope  tr[rowid=' + el + ']:last');
	$('.businessCriticalilty',addedRow).val(0); 
	$('.levelofDocuments',addedRow).val(0);
	$('.stability',addedRow).val(0);
	$('.longivity',addedRow).val(0);
	$('.complexity',addedRow).val(0);
	$('.percentageShare',addedRow).val(0);
	$('.businessCriticaliltySelect',addedRow).val(0);
	$('.levelofDocumentsSelect',addedRow).val(0);
	$('.stabilitySelect',addedRow).val(0);
	$('.longivitySelect',addedRow).val(0);
	$('.stabilitySelect',addedRow).val(0);
	$('.complexity',addedRow).val(0);
	$( '.textBoxMedium,.textBoxMedium2,.textBoxSmall',addedRow).each(function(i){
		if($(this).attr('type')=="text"){
			$(this).val("");	
		}
	});
	
	/*
	var tdCount = $('#paramOfScope tr#' + el + ':first td');
	//alert(tdCount.length);
	var tempHtml = "<tr id="+el+">";
	for(var i=13;i<tdCount.length;i++){
		//tempHtml = tempHtml + $('td.eq('+tdCount(i)+')').clone().find('name');
		alert($('td.eq('+tdCount.length(i)+')').clone().find('name'));
	}
	*/	
	
}
function appendSolnAPARow(i) {
	$.get("<%=request.getContextPath()%>/solution/appendSolnAPARow", { rowId: solnAPAPosition},
			 function(data){				
			});
}

function calScore(el) {
	var scopeRow = $(el).parent().parent();
	var businessCriticalilty = $('.businessCriticalilty',scopeRow).val();
	var levelofDocuments = $('.levelofDocuments',scopeRow).val();
	var stability = $('.stability',scopeRow).val();
	var longivity = $('.longivity',scopeRow).val();
	var complexity = $('.complexity',scopeRow).val();
	
	var businessCriticaliltyWeightedValue = $('#businessCriticaliltyWA option:selected').val();
	var levelofDocumentsWeightedValue = $('#levelofDocumentsWA option:selected').val();
	var stabilityWeightedValue = $('#stabilityWA option:selected').val();
	var longivityWeightedValue = $('#longivityWA option:selected').val();
	var complexityWeightedValue = $('#complexityWA option:selected').val();
	var weightedValue = 3;
	var high=0,low=0;
	if(weightedValue == 3){
		high = (parseFloat(businessCriticaliltyWeightedValue) * weightedValue) + (parseFloat(levelofDocumentsWeightedValue) * weightedValue) + 
		   (parseFloat(stabilityWeightedValue) * weightedValue) + (parseFloat(longivityWeightedValue) * weightedValue) + 
		   (parseFloat(complexityWeightedValue) * weightedValue);
		high = parseFloat(high) * .85;
		high = roundNumber(high,2);
	}
	weightedValue = 1;
	if(weightedValue == 1){
		low = (parseFloat(businessCriticaliltyWeightedValue) * weightedValue) + (parseFloat(levelofDocumentsWeightedValue) * weightedValue) + 
		   (parseFloat(stabilityWeightedValue) * weightedValue) + (parseFloat(longivityWeightedValue) * weightedValue) + 
		   (parseFloat(complexityWeightedValue) * weightedValue);
		low = parseFloat(low) * 1.45;
		low = roundNumber(low,2);
	}
	/*
	var scoreCardValue = parseInt(parseInt(parseInt(businessCriticaliltyWeightedValue) * parseInt(businessCriticalilty))
			+ parseInt(parseInt(levelofDocumentsWeightedValue) * parseInt(levelofDocuments))
			+ parseInt(parseInt(stabilityWeightedValue) * parseInt(stability))
			+ parseInt(parseInt(longivityWeightedValue) * parseInt(longivity))
			+ parseInt(parseInt(complexityWeightedValue) * parseInt(complexity)));
			*/
	
	scoreCardValue = parseInt(parseInt(businessCriticaliltyWeightedValue * businessCriticalilty)
			+ parseInt(levelofDocumentsWeightedValue * levelofDocuments)
			+ parseInt(stabilityWeightedValue * stability)
			+ parseInt(longivityWeightedValue * longivity)
			+ parseInt(complexityWeightedValue * complexity));
		
	if(scoreCardValue >= high){
		//$('#computedRiskFactor',scopeRow).html(scoreCardValue);
		//$('#computedRiskExposure',scopeRow).html(3);
		$('.computedRiskFactor',scopeRow).val(scoreCardValue);
		$('.computedRiskExposure',scopeRow).val(3);
		$('.computedRiskExposure',scopeRow).change();
	}else if(scoreCardValue <= low){
		//$('#computedRiskFactor',scopeRow).html(scoreCardValue);
		//$('#computedRiskExposure',scopeRow).html(1);
		$('.computedRiskFactor',scopeRow).val(scoreCardValue);
		$('.computedRiskExposure',scopeRow).val(1);
		$('.computedRiskExposure',scopeRow).change();
	}else if(scoreCardValue > low && scoreCardValue < high){
		//$('#computedRiskFactor',scopeRow).html(scoreCardValue);
		//$('#computedRiskExposure',scopeRow).html(2);
		$('.computedRiskFactor',scopeRow).val(scoreCardValue);
		$('.computedRiskExposure',scopeRow).val(2);
		$('.computedRiskExposure',scopeRow).change();
	}
	calculateRiskExposure(scopeRow,high,low);
}

function calculateRiskExposure(scopeRow,high,low) {
	//var percentageShare = $('#percentageShare option:selected',scopeRow).val();
	var trId = scopeRow.attr('rowid');
	var trCount = $('tr[rowid="'+trId+'"]');	
	var ssId = scopeRow.attr('ssid');
	var percentageShare, computedRiskFactor, riskExposure = 0;
	trCount.each(function(){
		percentageShare = $('.percentageShare', this).val();
		computedRiskFactor = $('.computedRiskFactor', this).val();
		riskExposure = riskExposure + roundNumber(parseFloat(computedRiskFactor * parseFloat(percentageShare/100)),2);
		/*if(riskExposure >= high){
			$('.finalScoreCardValue', trCount.last().next()).val(riskExposure);
			$('.finalRiskExposure', trCount.last().next()).val(3);
		}else if(riskExposure <= low){
			$('.finalScoreCardValue', trCount.last().next()).val(riskExposure);
			$('.finalRiskExposure', trCount.last().next()).val(1);
		}else if(riskExposure > low && riskExposure < high){
			$('.finalScoreCardValue', trCount.last().next()).val(riskExposure);
			$('.finalRiskExposure', trCount.last().next()).val(2);
		}*/
	});
//alert("risk exposure = "+riskExposure);	
	/*if(riskExposure > 120 && riskExposure <=150  ){
		$('.finalScoreCardValue', trCount.last().next()).val(riskExposure);
		$('.finalRiskExposure', trCount.last().next()).val(3);
	}else if(riskExposure> 0 && riskExposure<=60){
		$('.finalScoreCardValue', trCount.last().next()).val(riskExposure);
		$('.finalRiskExposure', trCount.last().next()).val(1);
	}else {
		$('.finalScoreCardValue', trCount.last().next()).val(riskExposure);
		$('.finalRiskExposure', trCount.last().next()).val(2);
	}
	if(trId==1||trId==2){ 
		if($('.finalRiskExposure', trCount.last().next()).val()==3){
			$('.finalScoreCardValue', trCount.last().next()).val(5);
		}else{
			$('.finalScoreCardValue', trCount.last().next()).val(0);
		}
	}else{
		if($('.finalRiskExposure', trCount.last().next()).val()==3){
			$('.finalScoreCardValue', trCount.last().next()).val(10);
		}else{
			$('.finalRiskExposure', trCount.last().next()).val(0);
		}
	}*/
	
	if(riskExposure> 0 && riskExposure<=60) {
		$('.complexity', trCount.last().next()).val(1);
		$('.complexityovr', trCount.last().next()).val(1);
	}
	else if(riskExposure > 120 && riskExposure <=150) {
		$('.complexity', trCount.last().next()).val(3);
		$('.complexityovr', trCount.last().next()).val(3);
	}
	else {
		$('.complexity', trCount.last().next()).val(2);
		$('.complexityovr', trCount.last().next()).val(2);
	}
//alert(ssId);
	if($('.complexity', trCount.last().next()).val()==3) {
		if(ssId==1||ssId==2) {
			$('.ftePercentage', trCount.last().next()).val(5);
		}
		else {
			$('.ftePercentage', trCount.last().next()).val(10);
		}
	}
	else {
		$('.ftePercentage', trCount.last().next()).val(0);
	}
}

function selectedValue(el) {
	$(el).prev().val($(el).val());
	calScore(el);
}

function sumTotal(el) {
	var sumTotal = 0;
	var rowid = $(el).parent().parent().attr('rowid');
	$('tr[rowid="' + rowid + '"]').each(function(){
		sumTotal += parseInt($('.percentageShare').val());
	});
	if (sumTotal > 100) {
		return false;
	}
	selectedValue(el);
}

function savePage(){
	document.forms[0].action="/ADM_PRE/solution/saveAPAnalysis";
	document.forms[0].submit();
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

function roundNumber(num, dec) {
	var result = Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec);
	return result;
}

function previousValueCheck(fieldName) {
	if(fieldName == "businessCriticalilty"){
		previousValue = businessCriticalilty;
	}else if(fieldName == "levelofDocuments"){
		previousValue = levelofDocuments;
	}else if(fieldName == "stability"){
		previousValue = stability;
	}else if(fieldName == "longivity"){
		previousValue = longivity;
	}else if(fieldName == "complexity"){
		previousValue = complexity;
	}else if(fieldName == "percentageShare"){
		previousValue = percentageShare;
	}else{
		alert(fieldName+" Not mached");
	}
}

function readTextValue(el)
{	
	//var txtVal = $('.computedRiskExposure').val();
	var RowCount = $(el).parent();
	var txtVal = $('.computedRiskExposure',RowCount).val();
	var displayVal="";
	switch(parseInt(txtVal))
	{
	case 1:
	  displayVal="Low";
	  break;
	case 2:
		displayVal="Medium";
	    break;
	case 3:
		displayVal="High";
	    break;  
	}
	$('.riskExposureSelected',RowCount).val(displayVal);
}

// added by Sibayan to fix bugs
function calculateIndividualScores(){
	var rowLength=$(".oppscoperow").length;
	$(".oppscoperow").each(function(index, element){
		var businessCriticalilty = $('.businessCriticalilty',element);
		calScore(businessCriticalilty);
	});
}

function validateAnnexe(){
	var validate=true;
	$(".oppscoperow").each(function(index, element){
		var column2=$(this).find("td:eq(1)");
		var column13=$(this).find("td:eq(12)");
		var appName=$(column2).children().val();
		if(appName==""){
			var errorMsg = "<li>Please enter application name </li>";
			$("#messageDiv").addClass('errorMessageDisp');
			$("#messageDiv").html(errorMsg);
			validate=false;
			return validate;
		}
		var sharePercent=$(column13).find('select').val();
		if(sharePercent==0){
			var errorMsg = "<li>Please enter share percentage</li>";
			$("#messageDiv").addClass('errorMessageDisp');
			$("#messageDiv").html(errorMsg);
			validate=false;
			return validate;
		}
	});
	return validate;
}