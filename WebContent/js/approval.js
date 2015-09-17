function test_1 (invoiceType){
	//alert("i have been called" + invoiceType);
	calculateRatio1();
	calculateRatio2();
	calculateRatio3();
	calculateRatio7();
	calculateRatio8();
	calculateRatio9();
	calculateRatio10();
	calculateMarkUp(invoiceType);
	calculatePercentMarkUp(invoiceType);
}

//Onsite Effort to Total Effort
function calculateRatio1(){
	$("input[id='ratio1']").each(function(index, element){
		var columnIndex=$(element).attr('colIndex');
		var onshoreFTE=$("input[id='onshoreFTE'][colIndex=" + (columnIndex)
				+ "]").val();
		var totFTE=$("input[id='FTEGT'][colIndex=" + (index+1)
				+ "]").val();
		var ratio=onshoreFTE/totFTE;
		ratio=checkNaNValue(ratio);
		ratio = ratio.toFixed(2);
		$(element).val(ratio);
	});
	var s1=$("#onshoreFTE_Tot").val();
	var s2=$("#FTEGT_Tot").val();
	var s3=s1/s2;
	s3=checkNaNValue(s3);
	s3=s3.toFixed(2);
	$('#ratio1_Tot').val(s3);
}

//Ofshore Effort to Total Effort
function calculateRatio2(){
	$("input[id='ratio2']").each(function(index, element){
		var columnIndex=$(element).attr('colIndex');
		var onshoreFTE=$("input[id='ofshoreFTE'][colIndex=" + (columnIndex)
				+ "]").val();
		var totFTE=$("input[id='FTEGT'][colIndex=" + (index+1)
				+ "]").val();
		var ratio=onshoreFTE/totFTE;
		ratio=checkNaNValue(ratio);
		ratio=ratio.toFixed(2);
		$(element).val(ratio);
	});
	var s1=$("#ofshoreFTE_Tot").val();
	var s2=$("#FTEGT_Tot").val();
	var s3=s1/s2;
	s3=checkNaNValue(s3);
	s3=s3.toFixed(2);
	$('#ratio2_Tot').val(s3);
}

//Blended Cost/Hour
function calculateRatio3(){
	$("input[id='ratio3']").each(function(index, element){
		var columnIndex=$(element).attr('colIndex');
		var manpowerCost=$("input[id='CLC'][colIndex=" + (columnIndex)
				+ "]").val();
		var totFTE=$("input[id='FTEGT'][colIndex=" + (index+1)
				+ "]").val();
		var ratio=manpowerCost/totFTE;
		ratio=checkNaNValue(ratio);
		ratio=ratio.toFixed(2);
		$(element).val(ratio);
	});
	var s1=$("#CLC_Tot").val();
	var s2=$("#FTEGT_Tot").val();
	var s3=s1/s2;
	s3=checkNaNValue(s3);
	s3=s3.toFixed(2);
	$('#ratio3_Tot').val(s3);
}

//Manpower as % of Total cost
function calculateRatio7(){
	$("input[id='ratio7']").each(function(index, element){
		var columnIndex=$(element).attr('colIndex');
		var manpowerCost=$("input[id='CLC'][colIndex=" + (columnIndex)
				+ "]").val();
		var totCost=$("input[id='CGT'][colIndex=" + (index+1)
				+ "]").val();
		var ratio=manpowerCost/totCost;
		ratio=checkNaNValue(ratio);
		ratio = ratio.toFixed(2);
		$(element).val(ratio);
	});
	var s1=$("#CLC_Tot").val();
	var s2=$("#CGT_Tot").val();
	var s3=s1/s2;
	s3 = checkNaNValue(s3);
	s3=s3.toFixed(2);
	$('#ratio7_Tot').val(s3);
}

//Travel as % of Total cost
function calculateRatio8(){
	$("input[id='ratio8']").each(function(index, element){
		var columnIndex=$(element).attr('colIndex');
		var manpowerCost=$("input[id='CTC'][colIndex=" + (columnIndex)
				+ "]").val();
		var totCost=$("input[id='CGT'][colIndex=" + (index+1)
				+ "]").val();
		var ratio=manpowerCost/totCost;
		ratio = checkNaNValue(ratio);
		ratio = ratio.toFixed(2);
		$(element).val(ratio);
	});
	var s1=$("#CTC_Tot").val();
	var s2=$("#CGT_Tot").val();
	var s3=s1/s2;
	s3 = checkNaNValue(s3);
	s3=s3.toFixed(2);
	$('#ratio8_Tot').val(s3);
}

//Other as % of Total cost
function calculateRatio9(){
	$("input[id='ratio9']").each(function(index, element){
		var columnIndex=$(element).attr('colIndex');
		var manpowerCost=$("input[id='COC'][colIndex=" + (columnIndex)
				+ "]").val();
		var totCost=$("input[id='CGT'][colIndex=" + (index+1)
				+ "]").val();
		var ratio=manpowerCost/totCost;
		
		ratio = checkNaNValue(ratio);
		ratio = ratio.toFixed(2);
		$(element).val(ratio);
	});
	var s1=$("#COC_Tot").val();
	var s2=$("#CGT_Tot").val();
	var s3=s1/s2;
	s3 = checkNaNValue(s3);
	s3=s3.toFixed(2);
	$('#ratio9_Tot').val(s3);
}

//Travel Cost Per Onsite Hour
function calculateRatio10(){
	$("input[id='ratio10']").each(function(index, element){
		var columnIndex=$(element).attr('colIndex');
		var travelCost=$("input[id='CTC'][colIndex=" + (columnIndex)
				+ "]").val();
		var onshoreFTE=$("input[id='onshoreFTE'][colIndex=" + (columnIndex)
				+ "]").val();
		var ratio=travelCost/onshoreFTE;
	
		if(!isNaN(parseFloat(ratio).toFixed(2))){
			ratio = checkNaNValue(ratio);
			ratio = ratio.toFixed(2);
		}
		 if(ratio =='Infinity'){
			ratio= '0.0';
		}
		
		
		$(element).val(ratio);
	});
	var s1=$("#CTC_Tot").val();
	var s2=$("#onshoreFTE_Tot").val();
	var s3=s1/s2;
	s3 = checkNaNValue(s3);
	s3=s3.toFixed(2);
	$('#ratio10_Tot').val(s3);
}

function calculateMarkUp(invoiceType){
	if(invoiceType=="PO"){
		$("input[id='mnm']").each(function(index, element){
			var columnIndex=$(element).attr('colIndex');
			var gTotRev=$("input[id='RGT'][colIndex=" + (columnIndex)
					+ "]").val();	
			var gTotCost=$("input[id='CGT'][colIndex=" + (columnIndex)
					+ "]").val();	
			var ratio=(gTotRev-gTotCost).toFixed(2);;
			$(element).val(ratio);
		});
		var s1=$("#RGT_Tot").val();
		var s2=$("#CGT_Tot").val();
		var s3=s1-s2;
		s3=s3.toFixed(2);
		$('#mnm_Tot').val(s3);
		
	}else{
		var tot=0;
		$("input[id='mnm']").each(function(index, element){
			var columnIndex=$(element).attr('colIndex');
			//D9=gTotRev,D15=gTotCost,D5=ManpowerRevenue,D11=Manpower Cost
			var d9=$("input[id='RGT'][colIndex=" + (columnIndex)
					+ "]").val();	  
			var d15=$("input[id='CGT'][colIndex=" + (columnIndex)
					+ "]").val();	
			var d5=$("input[id='RLC'][colIndex=" + (columnIndex)
					+ "]").val();	
			var d11=$("input[id='CLC'][colIndex=" + (columnIndex)
					+ "]").val();	
			var ratio=((d9-d5)-(d15-d11));
			tot+=ratio;
			ratio=ratio.toFixed(2);
			$(element).val(ratio);
		});
		tot=tot.toFixed(2);
		$("#mnm_Tot").val(tot);
	}
}

function calculatePercentMarkUp(invoiceType){
	if(invoiceType=="PO"){
	
		$("input[id='pmnm']").each(function(index, element){
		
			var columnIndex=$(element).attr('colIndex');
			var d16=$("input[id='mnm'][colIndex=" + (columnIndex)
					+ "]").val();	
					
			var d15=$("input[id='CGT'][colIndex=" + (columnIndex)
					+ "]").val();
					//alert("d16 : " + d16 +"  D15 : " + d15);
			var ratio=d16/d15;
			ratio = checkNaNValue(ratio);
			ratio=ratio.toFixed(2);
			$(element).val(ratio);
		}); 
		var n15=$("#CGT_Tot").val();
		var n16=$("#mnm_Tot").val();
		var ratio=n16/n15;
		ratio=checkNaNValue(ratio);
		ratio=ratio.toFixed(2);
		$("#pmnm_Tot").val(ratio);
	}else{
		$("input[id='pmnm']").each(function(index, element){
			var columnIndex=$(element).attr('colIndex');
			var d16=$("input[id='mnm'][colIndex=" + (columnIndex)
					+ "]").val();	
			var d12=$("input[id='CTC'][colIndex=" + (columnIndex)
					+ "]").val();
			var d14=$("input[id='COT'][colIndex=" + (columnIndex)
					+ "]").val();
			var ratio;
			if(d16==0){
				ratio=0;
			}else{
				ratio=d16/(d12+d14);				
			}
			ratio=checkNaNValue(ratio);
			ratio=ratio.toFixed(2);
			$(element).val(ratio);
		});
		var n16=$("#mnm_Tot").val();
		var n12=$("#CTC_Tot").val();
		var n14=$("#COT_Tot").val();
		var ratio;
		if(n16==0){
			ratio=0;
		}else{
			ratio=n16/(n12+n14);
				
		}
		ratio = checkNaNValue(ratio);
		ratio=ratio.toFixed(2);
		$("#pmnm_Tot").val(ratio);
	}
	
	
}

function checkNaNValue(value){
	var result;
	if(isNaN(value)) {
		result = 0;
	}else{
		result = value;
	}
	return result;
	}