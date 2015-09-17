
function showTab(tabName){
	if(tabName=="tab1"){
		document.getElementById('t1').className = 'selected';
		document.getElementById('t2').className = 'usual';
		document.getElementById('t3').className = 'usual';
		document.getElementById('t4').className = 'usual';
		$("#tab1").show();
		$("#tab2").hide();$("#tab3").hide();$("#tab4").hide();
	}else if(tabName=="tab2"){
		document.getElementById('t2').className = 'selected';
		document.getElementById('t1').className = 'usual';
		document.getElementById('t3').className = 'usual';
		document.getElementById('t4').className = 'usual';
		$("#tab2").show();
		$("#tab1").hide();$("#tab3").hide();$("#tab4").hide();
	}else if(tabName=="tab3"){
		document.getElementById('t3').className = 'selected';
		document.getElementById('t1').className = 'usual';
		document.getElementById('t2').className = 'usual';
		document.getElementById('t4').className = 'usual';
		$("#tab3").show();
		$("#tab1").hide();$("#tab2").hide();$("#tab4").hide();
	}else if(tabName=="tab4"){
		document.getElementById('t4').className = 'selected';
		document.getElementById('t3').className = 'usual';
		document.getElementById('t2').className = 'usual';
		document.getElementById('t1').className = 'usual';
		$("#tab4").show();
		$("#tab3").hide();$("#tab2").hide();$("#tab1").hide();
	}
}

/****Steady State Calculations *****/
function calculateIndividualCostSS(element){
	if($(element).val()==""){
		$(element).val(0);
	}
	if(!isNumber($(element).val())){
		$('.errorMessage_nonlabor').html("Please enter a positive number");
		$('.errorMessage_nonlabor').show();
		$('.successMessage_nonLabor').hide();
		$(element).val("");
		$(element).focus();
		return;
	}
	$('.errorMessage_nonlabor').hide();
	var columnIndex=$(element).attr('colIndex');
	var currentYr=$('#cs_'+columnIndex).text().split('-')[1];
	var baseYr = $('#cs_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var inflationTr=$('#inflationRateMS').val()/100;
	//alert("columnIndex="+columnIndex);
	var thisId=$(element).attr('id');
	var thisVal=$(element).val();
	switch (thisId) {
	case 'ssRoundTripNo':
		var oneRTripTicketCost = $('#roundTripTicketCost').val();
		var cost = (thisVal * oneRTripTicketCost * Math.pow((1 + inflationTr),
				yrDiff));
		if(isFloat(cost)){
			cost=cost.toFixed(2);
		}
		var target = $("input[id='ssRoundTripCost'][colIndex=" + columnIndex
				+ "]");
		$(target).val(cost); 
		target = $("input[id='ssTotCost'][colIndex=" + columnIndex
				+ "]");
		calculateTotalCostSS(columnIndex);
		break;
	case 'ssVisaNoLTerm':
		var oneLTermVisaCost=$('#longTermVisaCost').val();
		var cost = (thisVal * oneLTermVisaCost * Math.pow((1 + inflationTr),
				yrDiff));
		if(isFloat(cost)){
			cost=cost.toFixed(2);
		}
		var target = $("input[id='ssVisaCostLTerm'][colIndex=" + columnIndex
				+ "]");
		$(target).val(cost);
		calculateTotalCostSS(columnIndex);
		break;	
	case 'ssVisaNoSTerm':
		var oneSTermVisaCost=$('#shortTermVisaCost').val();
		var cost = (thisVal * oneSTermVisaCost * Math.pow((1 + inflationTr),
				yrDiff));
		if(isFloat(cost)){
			cost=cost.toFixed(2);
		}
		var target = $("input[id='ssVisaCostSTerm'][colIndex=" + columnIndex
				+ "]");
		$(target).val(cost);
		calculateTotalCostSS(columnIndex);
		break;
	default:
		alert('not ' + thisId);
	}
}

function calculateTotalCostSS(columnIndex){
	
	var cost1 = $("input[id='ssRoundTripCost'][colIndex=" + columnIndex
			+ "]").val();
	cost1=cost1==""?0:cost1;
	/*var cost2 = $("input[id='ssVisaCostLTerm'][colIndex=" + columnIndex
			+ "]").val();
	cost2=cost2==""?0:cost2;*/
	cost2=0;
	var cost3 = $("input[id='ssVisaCostSTerm'][colIndex=" + columnIndex
			+ "]").val();
	cost3=cost3==""?0:cost3;
	var cost4 = $("input[id='ssOnsiteHotelCost'][colIndex=" + columnIndex
			+ "]").val();
	cost4=cost4==""?0:cost4;
	var cost5 = $("input[id='ssOnsitePerDiemCost'][colIndex=" + columnIndex
			+ "]").val();
	cost5=cost5==""?0:cost5;
	/*var cost6 = $("input[id='ssOnsiteFSICost'][colIndex=" + columnIndex
			+ "]").val();
	cost6=cost6==""?0:cost6;*/
	cost6=0;
	var cost7 = $("input[id='ssOnsiteConveyanceCost'][colIndex=" + columnIndex
			+ "]").val();
	cost7=cost7==""?0:cost7;
	var cost8 = $("input[id='ssMobileCommCost'][colIndex=" + columnIndex
			+ "]").val();
	cost8=cost8==""?0:cost8;
	var totCost=parseFloat(cost1)+parseFloat(cost2)+parseFloat(cost3)+parseFloat(cost4)+parseFloat(cost5)
	            +parseFloat(cost6)+parseFloat(cost7)+parseFloat(cost8);
	//alert("totCost="+totCost);
	var target=$("input[id='ssTotCost'][colIndex=" + columnIndex
			+ "]");
	if(isFloat(totCost)){
		totCost=totCost.toFixed(2);
	}
	$(target).val(totCost);
	
}
function calculateDependencies(source){
	if(!isNumeric($(source).val())){
		$('.errorMessage_nonlabor').html("Please enter a positive number");
		$('.errorMessage_nonlabor').show();
		return false;	
	}
	calculateDependenciesSS(source);
	calculateDependenciesTrans(source);
}

function calculateDependenciesSS(source){
	/*$("input[id='ssRoundTripCost']").each(function(index, element){
		calculateRoundTripCostSS(element,"trans");
	});*/
	var srcID=$(source).attr('id');
	switch (srcID) {
	case 'roundTripTicketCost':
		$("input[id='ssRoundTripNo']").each(function(index, element){
			if($(element).val()==""){
				$(element).val(0);
			}
			calculateRoundTripCostSS(element);
		});
		break;
	case 'shortTermVisaCost':
		$("input[id='ssVisaNoSTerm']").each(function(index, element){
			if($(element).val()==""){
				$(element).val(0);
			}
			calculateSTermVisaCostSS(element);
		});
		break;
	case 'longTermVisaCost':
		$("input[id='ssVisaNoLTerm']").each(function(index, element){
			calculateLTermVisaCostSS(element);
		});
		break;	
	case 'onsiteHotelCostPerNight':
		$("input[id='ssCalDaysOnsite']").each(function(index, element){
			calculateOnsiteHotelCostSS(element);
		});
		break;
	case 'onsitePerdiem':
		$("input[id='ssOnshoreFTE']").each(function(index, element){
			calculateOnsitePerDiemSS(element);
		});
		break;	
	case 'onsiteConveyanceCost':
		$("input[id='ssWrkDaysOnsite']").each(function(index, element){
			calculateOnsiteConveyanceCostSS(element);
		});
		break;	
	case 'mobileCostPerOnsiteFTE':
		$("input[id='ssOnshoreFTE']").each(function(index, element){
			calculateMobileCommCostSS(element);
		});
		break;
	default:
		alert('default ');
	}
}
function calculateRoundTripCostSS(actor){
	//noOfRoundTrips*IP_OneRoundTripCost*(inflation,yr)
	var thisVal=$(actor).val();
	//thisVal=isNaN(thisVal)?0:thisVal;
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#cs_'+columnIndex).text().split('-')[1];
	var baseYr = $('#cs_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var oneRTripTicketCost = $('#roundTripTicketCost').val();
	//oneRTripTicketCost=isNaN(oneRTripTicketCost)?0:oneRTripTicketCost;
	var cost = (thisVal * oneRTripTicketCost * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='ssRoundTripCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostSS(columnIndex);
}

function calculateSTermVisaCostSS(actor){
	//no of short term visa *IP_1STermVisaCst*(inflation yr)
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#cs_'+columnIndex).text().split('-')[1];
	var baseYr = $('#cs_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var oneSTermVisaCost = $('#shortTermVisaCost').val();
	var cost = (thisVal * oneSTermVisaCost * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='ssVisaCostSTerm'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostSS(columnIndex);
}

function calculateLTermVisaCostSS(actor){
	//no of Long term visa *IP_1LTermVisaCst*(inflation yr)
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#cs_'+columnIndex).text().split('-')[1];
	var baseYr = $('#cs_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var oneLTermVisaCost = $('#longTermVisaCost').val();
	var cost = (thisVal * oneLTermVisaCost * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='ssVisaCostLTerm'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostSS(columnIndex);
}

function calculateOnsiteHotelCostSS(actor){
	//no of CalDaysOnsite *IP_OnsiteHoletCostPerNight*(inflation yr)
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#cs_'+columnIndex).text().split('-')[1];
	var baseYr = $('#cs_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var oneNightHotelCost = $('#onsiteHotelCostPerNight').val();
	var cost = (thisVal * oneNightHotelCost * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='ssOnsiteHotelCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostSS(columnIndex);
}

function calculateOnsitePerDiemSS(actor){
	//no of OnsiteResource *IP_OnsiteDailyPediem*30*(inflation yr)
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#cs_'+columnIndex).text().split('-')[1];
	var baseYr = $('#cs_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var dailyPerdiem = $('#onsitePerdiem').val();
	var cost = (thisVal * dailyPerdiem * Math.pow((1 + inflationTr),
			yrDiff)*30);
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='ssOnsitePerDiemCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostSS(columnIndex);
}

function calculateOnsiteFSICost(){
	// dependent on number of Onsite Resources long term * (inflation yr)
	
}

function calculateOnsiteConveyanceCostSS(actor){
	//WrkDaysOnsite*IP_OtherCobnveyanceCostPerDayPerFTE
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#cs_'+columnIndex).text().split('-')[1];
	var baseYr = $('#cs_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var costPerDay = $('#onsiteConveyanceCost').val();
	var cost = (thisVal * costPerDay * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='ssOnsiteConveyanceCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostSS(columnIndex);
}

function calculateMobileCommCostSS(actor){
	//OnsiteResources*IP_MobCommCostPerOnsiteFTEPerMnth*(inflation,yr)
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#cs_'+columnIndex).text().split('-')[1];
	var baseYr = $('#cs_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var costPerUnit = $('#mobileCostPerOnsiteFTE').val();
	var cost = (thisVal * costPerUnit * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='ssMobileCommCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostSS(columnIndex);
}

/*************Calculation of Transition Costs**************/

function calculateIndividualCostTrans(element){
	if($(element).val()==""){
		$(element).val(0);
	}
	if(!isNumber($(element).val())){
		$('.errorMessage_nonlabor').html("Please enter a positive number ");
		$('.errorMessage_nonlabor').show();
		$('.successMessage_nonLabor').hide();
		$(element).val("");
		$(element).focus();
		return;
	}
	var columnIndex=$(element).attr('colIndex');
	var currentYr=$('#ct_'+columnIndex).text().split('-')[1];
	var baseYr = $('#ct_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var inflationTr=$('#inflationRateMS').val()/100;
	//alert("columnIndex="+columnIndex);
	var thisId=$(element).attr('id');
	var thisVal=$(element).val();
	switch (thisId) {
	case 'transRoundTripNo':
		var oneRTripTicketCost = $('#roundTripTicketCost').val();
		var cost = (thisVal * oneRTripTicketCost * Math.pow((1 + inflationTr),
				yrDiff));
		if(isFloat(cost)){
			cost=cost.toFixed(2);
		}
		var target = $("input[id='transRoundTripCost'][colIndex=" + columnIndex
				+ "]");
		$(target).val(cost); 
		target = $("input[id='transRoundTripCost'][colIndex=" + columnIndex
				+ "]");
		calculateTotalCostTrans(columnIndex);
		break;
	case 'transVisaNoLTerm':
		var oneLTermVisaCost=$('#longTermVisaCost').val();
		var cost = (thisVal * oneLTermVisaCost * Math.pow((1 + inflationTr),
				yrDiff));
		if(isFloat(cost)){
			cost=cost.toFixed(2);
		}
		var target = $("input[id='transVisaCostLTerm'][colIndex=" + columnIndex
				+ "]");
		$(target).val(cost);
		calculateTotalCostTrans(columnIndex);
		break;
	case 'transVisaNoSTerm':
		var oneSTermVisaCost=$('#shortTermVisaCost').val();
		var cost = (thisVal * oneSTermVisaCost * Math.pow((1 + inflationTr),
				yrDiff));
		if(isFloat(cost)){
			cost=cost.toFixed(2);
		}
		var target = $("input[id='transVisaCostSTerm'][colIndex=" + columnIndex
				+ "]");
		$(target).val(cost);
		calculateTotalCostTrans(columnIndex);
		break;	
	default:
		alert('not ' + thisId);
	}
}

function calculateTotalCostTrans(columnIndex){
	var cost1 = $("input[id='transRoundTripCost'][colIndex=" + columnIndex
			+ "]").val();
	cost1=cost1==""?0:cost1;
	/*var cost2 = $("input[id='transVisaCostLTerm'][colIndex=" + columnIndex
			+ "]").val();
	cost2=cost2==""?0:cost2;*/
	cost2=0;
	var cost3 = $("input[id='transVisaCostSTerm'][colIndex=" + columnIndex
			+ "]").val();
	cost3=cost3==""?0:cost3;
	var cost4 = $("input[id='transOnsiteHotelCost'][colIndex=" + columnIndex
			+ "]").val();
	cost4=cost4==""?0:cost4;
	var cost5 = $("input[id='transOnsitePerDiemCost'][colIndex=" + columnIndex
			+ "]").val();
	cost5=cost5==""?0:cost5;
	/*var cost6 = $("input[id='transOnsiteFSICost'][colIndex=" + columnIndex
			+ "]").val();
	cost6=cost6==""?0:cost6;*/
	cost6=0;
	var cost7 = $("input[id='transOnsiteConveyanceCost'][colIndex=" + columnIndex
			+ "]").val();
	cost7=cost7==""?0:cost7;
	var cost8 = $("input[id='transMobileCommCost'][colIndex=" + columnIndex
			+ "]").val();
	cost8=cost8==""?0:cost8;
	var totCost=parseFloat(cost1)+parseFloat(cost2)+parseFloat(cost3)+parseFloat(cost4)+parseFloat(cost5)
	            +parseFloat(cost6)+parseFloat(cost7)+parseFloat(cost8);
	if(isFloat(totCost)){
		totCost=totCost.toFixed(2);
	}
	//alert("totCost="+totCost);
	var target=$("input[id='transTotCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(totCost);
	
}

function calculateDependenciesTrans(source){
	
	/*$("input[id='ssRoundTripCost']").each(function(index, element){
		calculateRoundTripCostSS(element,"trans");
	});*/
	var srcID=$(source).attr('id');
	switch (srcID) {
	case 'roundTripTicketCost':
		$("input[id='transRoundTripNo']").each(function(index, element){
			if($(element).val()==""){
				$(element).val(0);
			}
			calculateRoundTripCostTrans(element);
		});
		break;
	case 'shortTermVisaCost':
		$("input[id='transVisaNoSTerm']").each(function(index, element){
			if($(element).val()==""){
				$(element).val(0);
			}
			calculateSTermVisaCostTrans(element);
		});
		break;
	case 'longTermVisaCost':
		$("input[id='transVisaNoLTerm']").each(function(index, element){
			calculateLTermVisaCostTrans(element);
		});
		break;	
	case 'onsiteHotelCostPerNight':
		$("input[id='transCalDaysOnsite']").each(function(index, element){
			calculateOnsiteHotelCostTrans(element);
		});
		break;
	case 'onsitePerdiem':
		$("input[id='transOnshoreFTE']").each(function(index, element){
			calculateOnsitePerDiemTrans(element);
		});
		break;	
	case 'onsiteConveyanceCost':
		$("input[id='transWrkHrsOnsite']").each(function(index, element){
			calculateOnsiteConveyanceCostTrans(element);
		});
		break;	
	case 'mobileCostPerOnsiteFTE':
		$("input[id='transOnshoreFTE']").each(function(index, element){
			calculateMobileCommCostTrans(element);
		});
		break;
	default:
		alert('default ');
	}
}
function calculateRoundTripCostTrans(actor){
	//noOfRoundTrips*IP_OneRoundTripCost*(inflation,yr)
	var thisVal=$(actor).val();
	//thisVal=isNaN(thisVal)?0:thisVal;
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#ct_'+columnIndex).text().split('-')[1];
	var baseYr = $('#ct_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var oneRTripTicketCost = $('#roundTripTicketCost').val();
	//oneRTripTicketCost=isNaN(oneRTripTicketCost)?0:oneRTripTicketCost;
	var cost = (thisVal * oneRTripTicketCost * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='transRoundTripCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostTrans(columnIndex);
}

function calculateSTermVisaCostTrans(actor){
	//no of short term visa *IP_1STermVisaCst*(inflation yr)
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#ct_'+columnIndex).text().split('-')[1];
	var baseYr = $('#ct_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var oneSTermVisaCost = $('#shortTermVisaCost').val();
	var cost = (thisVal * oneSTermVisaCost * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='transVisaCostSTerm'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostTrans(columnIndex);
}

function calculateLTermVisaCostTrans(actor){
	//no of Long term visa *IP_1LTermVisaCst*(inflation yr)
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#ct_'+columnIndex).text().split('-')[1];
	var baseYr = $('#ct_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var oneLTermVisaCost = $('#longTermVisaCost').val();
	var cost = (thisVal * oneLTermVisaCost * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='transVisaCostLTerm'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostTrans(columnIndex);
}

function calculateOnsiteHotelCostTrans(actor){
	//no of CalDaysOnsite *IP_OnsiteHoletCostPerNight*(inflation yr)
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#ct_'+columnIndex).text().split('-')[1];
	var baseYr = $('#ct_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var oneNightHotelCost = $('#onsiteHotelCostPerNight').val();
	var cost = (thisVal * oneNightHotelCost * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='transOnsiteHotelCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostTrans(columnIndex);
}

function calculateOnsitePerDiemTrans(actor){
	//no of OnsiteResource *IP_OnsiteDailyPediem*30*(inflation yr)
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#ct_'+columnIndex).text().split('-')[1];
	var baseYr = $('#ct_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var dailyPerdiem = $('#onsitePerdiem').val();
	var cost = (thisVal * dailyPerdiem * Math.pow((1 + inflationTr),
			yrDiff)*30);
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='transOnsitePerDiemCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostTrans(columnIndex);
}

function calculateOnsiteFSICost(){
	// dependent on number of Onsite Resources long term * (inflation yr)
	
}

function calculateOnsiteConveyanceCostTrans(actor){
	//WrkDaysOnsite*IP_OtherCobnveyanceCostPerDayPerFTE
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#ct_'+columnIndex).text().split('-')[1];
	var baseYr = $('#ct_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var costPerDay = $('#onsiteConveyanceCost').val();
	var cost = (thisVal * costPerDay * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='transOnsiteConveyanceCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostTrans(columnIndex);
}

function calculateMobileCommCostTrans(actor){
	//OnsiteResources*IP_MobCommCostPerOnsiteFTEPerMnth*(inflation,yr)
	var thisVal=$(actor).val();
	var columnIndex=$(actor).attr('colIndex');
	var inflationTr=$('#inflationRateMS').val()/100;
	var currentYr=$('#ct_'+columnIndex).text().split('-')[1];
	var baseYr = $('#ct_1').text().split('-')[1];
	var yrDiff=currentYr-baseYr;
	var costPerUnit = $('#mobileCostPerOnsiteFTE').val();
	var cost = (thisVal * costPerUnit * Math.pow((1 + inflationTr),
			yrDiff));
	if(isFloat(cost)){
		cost=cost.toFixed(2);
	}
	var target = $("input[id='transMobileCommCost'][colIndex=" + columnIndex
			+ "]");
	$(target).val(cost);
	calculateTotalCostTrans(columnIndex);
}

/************Calculation of Other Costs********************/
function calculateIndividualCostOther(element){
	if(!isNumeric($(element).val())){
		$('.errorMessage_nonlabor').html("Please enter a positive number");
		$('.errorMessage_nonlabor').show();
		$('.successMessage_nonLabor').hide();
		$(element).val("");
		$(element).focus();
		return;
	}
	$('.errorMessage_nonlabor').hide();
	calculateTotalCostOther(element);
}

function calculateMSDPCostOth(source){
	/*IF ip_msdp_platform_used=yes
	    offshoreFTE*IP_MsdpcostPerFTEPerMnth * inflation yr
	 */ 
	
	if($('#msdpPlatformUsed').val()==1){
		if(!isNumeric($('#msdpCostPerFTEMnth').val())){
			$('.errorMessage_nonlabor').html("Please enter a positive number");
			$('.errorMessage_nonlabor').show();
			$('.successMessage_nonLabor').hide();
			$('#msdpCostPerFTEMnth').val("");
			$('#msdpCostPerFTEMnth').focus();
			return;
		}
		$("input[id='otherOffShoreFTE']").each(function(index,actor){
			var thisVal=$(actor).val();
			var columnIndex=$(actor).attr('colIndex');
			var inflationTr=$('#inflationRateMS').val()/100;
			var currentYr=$('#co_'+columnIndex).text().split('-')[1];
			var baseYr = $('#co_1').text().split('-')[1];
			var yrDiff=currentYr-baseYr;
			var msdpCost = $('#msdpCostPerFTEMnth').val();
			var cost = (thisVal * msdpCost * Math.pow((1 + inflationTr),
					yrDiff));
			if(isFloat(cost)){
				cost=cost.toFixed(2);
			}
			var target = $("input[id='msdpCost'][colIndex=" + columnIndex
					+ "]");
			$(target).val(cost);
			//totalcost
			calculateTotalCostOther(actor);
		});
	}else{
		$("#msdpCostPerFTEMnth").val(0);
		$("input[id='otherOffShoreFTE']").each(function(index,actor){
			var columnIndex=$(actor).attr('colIndex');
			var target = $("input[id='msdpCost'][colIndex=" + columnIndex
					+ "]");
			$(target).val(0);
			calculateTotalCostOther(actor);
		});
			
	}
}
function calculateTotalCostOther(actor){
	var columnIndex=$(actor).attr('colIndex');
	var msdpCost=$("input[id='msdpCost'][colIndex=" + columnIndex
			+ "]").val();
	msdpCost=msdpCost==""?0:msdpCost;
	var connectToDevCost=$("input[id='connectivityToDev'][colIndex=" + columnIndex
			+ "]").val();
	connectToDevCost=connectToDevCost==""?0:connectToDevCost;
	var connectToIndiaCost=$("input[id='connectivityToIndia'][colIndex=" + columnIndex
			+ "]").val();
	connectToIndiaCost=connectToIndiaCost==""?0:connectToIndiaCost;
	var totalCost=parseFloat(msdpCost)+parseFloat(connectToIndiaCost)+parseFloat(connectToDevCost);
	if(isFloat(totalCost)){
		totalCost=totalCost.toFixed(2);
	}
	target = $("input[id='otherTotal'][colIndex=" + columnIndex
			+ "]");
	$(target).val(totalCost);
}
/***************General Functions*****************/
function isFloat(num){
	if(num % 1 != 0) return true;
	else return false;
}

function isNumber(input)
{
    var isNumber= (input - 0) == input && (input+'').replace(/^\s+|\s+$/g, "").length > 0;
	return isNumber;
}

function isNumeric(input)
{
    var isPositive=false;
	var isNumber= (input - 0) == input && (input+'').replace(/^\s+|\s+$/g, "").length > 0;
    if(isNumber){
    	if(input>=0){
    		isPositive=true;
    	}
    }
    return isPositive;
}

function validate(){
	/*roundTripTicketCost , longTermVisaCost , shortTermVisaCost , onsiteHotelCostPerNight , onsitePerdiem
	inputParams.onsiteFSICost, onsiteConveyanceCost, msdpPlatformUsed , msdpCostPerFTEMnth;
	*/
	$('.successMessage_nonLabor').hide();
	var success=true;
	if(!isNumeric($('#roundTripTicketCost').val())){
		$('.errorMessage_nonlabor').html("Please enter positive numeric value for One Round Trip Ticket Cost");
		$('.errorMessage_nonlabor').show();
		showTab('tab1');
		return false;
	}
	/*if(!isNumeric($('#longTermVisaCost').val())){
		$('.errorMessage_nonlabor').html("Please enter numeric value for One Long Term Visa Cost");
		$('.errorMessage_nonlabor').show();
		return false;
	}*/
	if(!isNumeric($('#shortTermVisaCost').val())){
		$('.errorMessage_nonlabor').html("Please enter positive  numeric value for One Short Term Visa Cost ");
		$('.errorMessage_nonlabor').show();
		showTab('tab1');
		return false;
	}
	if(!isNumeric($('#onsiteHotelCostPerNight').val())){
		$('.errorMessage_nonlabor').html("Please enter positive numeric value for Onsite Hotel Cost Per Night of stay");
		$('.errorMessage_nonlabor').show();
		showTab('tab1');
		return false;
	}
	if(!isNumeric($('#onsitePerdiem').val())){
		$('.errorMessage_nonlabor').html("Please enter positive numeric value for Onsite Daily Per Diem");
		$('.errorMessage_nonlabor').show();
		showTab('tab1');
		return false;
	}
	if(!isNumeric($('#onsiteConveyanceCost').val())){
		$('.errorMessage_nonlabor').html("Please enter positive numeric value for Onsite Conveyance Costs Per Day per FTE");
		$('.errorMessage_nonlabor').show();
		showTab('tab1');
		return false;
	}
	if($('#msdpPlatformUsed').val()==1){
		if(!isNumeric($('#msdpCostPerFTEMnth').val())){
			$('.errorMessage_nonlabor').html("Please enter positive numeric value for MSDP Costs per FTE Per Month");
			$('.errorMessage_nonlabor').show();
			showTab('tab1');
			return false;
		}else if($('#msdpCostPerFTEMnth').val()==0) {
			$('.errorMessage_nonlabor').html("Please enter positive numeric value for MSDP Costs per FTE Per Month");
			$('.errorMessage_nonlabor').show();
			showTab('tab1');
			return false;
		}
	}
	
	/****Transition State Mandatory Fields*******/
	$("input[id='transRoundTripNo']").each(function(index, element){
		if($(element).val()==""){
			$(element).val(0);
		}
		if(!isNumber($(element).val())){
			$('.errorMessage_nonlabor').html("Please enter positive numeric value for Number of Round Trips ");
			$('.errorMessage_nonlabor').show();
			success=false;
			showTab('tab2');
			return false;
		}
	});
	if(!success) return success;
	
	$("input[id='transVisaNoSTerm']").each(function(index, element){
		if($(element).val()==""){
			$(element).val(0);
		}
		if(!isNumber($(element).val())){
			$('.errorMessage_nonlabor').html("Please enter positive numeric value for Number of Short Term Visa");
			$('.errorMessage_nonlabor').show();
			success=false;
			showTab('tab2');
			return false;
		}
	});
	if(!success) return success;
	
	/*$("input[id='transVisaNoLTerm']").each(function(index, element){
		if(!isNumeric($(element).val())){
			$('.errorMessage_nonlabor').html("Please enter numeric value for Number of Long Term Visa");
			$('.errorMessage_nonlabor').show();
			success=false;
			return false;
		}
	});
	if(!success) return success;*/
	
	/*****Steady State Mandatory Fields ***********/
	$("input[id='ssRoundTripNo']").each(function(index, element){
		if($(element).val()==""){
			$(element).val(0);
		}
		if(!isNumber($(element).val())){
			$('.errorMessage_nonlabor').html("Please enter positive numeric value for Number of Round Trips");
			$('.errorMessage_nonlabor').show();
			success=false;
			showTab('tab3');
			return false;
		}
	});
	if(!success) return success;
	
	$("input[id='ssVisaNoSTerm']").each(function(index, element){
		if($(element).val()==""){
			$(element).val(0);
		}
		if(!isNumber($(element).val())){
			$('.errorMessage_nonlabor').html("Please enter positive numeric value for Number of Short Term Visa");
			$('.errorMessage_nonlabor').show();
			success=false;
			showTab('tab3');
			return false;
		}
	});
	if(!success) return false;
	
	/*$("input[id='ssVisaNoLTerm']").each(function(index, element){
		if(!isNumeric($(element).val())){
			$('.errorMessage_nonlabor').html("Please enter numeric value for Number of Long Term Visa");
			$('.errorMessage_nonlabor').show();
			success=false;
			return false;
		}
	});
	if(!success) return false;*/
	/************Other Cost Mandatory Fields****************/
	$("input[id='connectivityToIndia']").each(function(index, element){
		if(!isNumeric($(element).val())){
			$('.errorMessage_nonlabor').html("Please enter positive numeric value for Connectivity - Client Env to \\\ India");
			$('.errorMessage_nonlabor').show();
			success=false;
			showTab('tab4');
			return false;
		}
	});
	if(!success) return false;
	
	$("input[id='connectivityToDev']").each(function(index, element){
		if(!isNumeric($(element).val())){
			$('.errorMessage_nonlabor').html("Please enter positive numeric value for Connectivity - \\\ India to Dev Centre");
			$('.errorMessage_nonlabor').show();
			success=false;
			showTab('tab4');
			return false;
		}
	});
	if(!success) return false;

	return success;
	
}

function saveData(){
	var result=validate();
	if(result){
		$('#form1').submit();
	}
}
