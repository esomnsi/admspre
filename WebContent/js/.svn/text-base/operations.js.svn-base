var number_of_partitions=0;
var arr_planning = new Array(2);
var arr_learn = new Array(2);
var arr_assist = new Array(2);
var arr_perform = new Array(2);
var arr_deliver = new Array(2);
var arr_partitions = new Array();
var arr_fte = new Array();
var arr_percent_fte = new Array();
var arr_color = new Array('89ba1d','066aa9','9b0051','e40e15','9def75');
var span;
var eftArray;
var partition_num;
var max_Start_date;
var max_End_date;
var isNumPartitionsChanged = false;
var newNumPartitions = 0;
var isStartDateTntChanged = false;
var isEndDateTntChanged = false;

//------- this function is for creating the partition fte count -------
function drawPartitionFTE(){
	partition_num = document.getElementById('id_text_num_part').value;
	if(partition_num == null || partition_num == '')
	{
		alert('Please provide the number of partitions');
		return;
	}
	newNumPartitions = document.getElementById('id_hidden_num_part').value;
	if(!isNaN(parseInt(newNumPartitions, 10)) && (partition_num != newNumPartitions))
	{
		isNumPartitionsChanged = true;
	}
	max_Start_date =  document.getElementById('id_start_date').value.split('-').join('/');
	if(max_Start_date == null || max_Start_date == '')
	{
		alert('Please provide the starting date of T&T in the project delivery page.');
		return;
	}
	max_End_date = 	document.getElementById('id_end_date').value.split('-').join('/');
	if(max_End_date == null || max_End_date == '')
	{
		alert('Please provide the end date of T&T in the project delivery page.');
		return;
	}
	max_Start_date = changeDateFormat(max_Start_date);
	max_End_date = changeDateFormat(max_End_date);
	var is_start_date_greater = checkStartEndDate();
	if(is_start_date_greater)
	{
		alert('Start date should be before end date.');
		return;
	}
	document.getElementById('id_div_num_part').style.display = "none";
	document.getElementById('partition_FTE').style.display = "block";
	
	var x = document.getElementById('partition_FTE_table');
	
	eftArray = new Array(partition_num);
	for(var k=0; k<partition_num; k++){
		eftArray[k] = 0;
	}
	
	var row, cell=null, textnode, textStr;
	
	//------- normal row -------
//	var totalFteList = $("#totalFteList").val().split(",");
	var total=0, emptyDiv = null, formElement;
	emptyDiv = document.getElementById('id_div_num_part');
	if(isNumPartitionsChanged)
	{
		createFormElements();
	}
	for(var i=0; i<partition_num; i++){
		if(document.getElementById('tntDetailSize').value == '0')
		{
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].solutionId');
			formElement.setAttribute('path', 'tntDetailList['+i+'].solutionId');
			formElement.setAttribute('name', 'tntDetailList['+i+'].solutionId');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].id_tnt_detail');
			formElement.setAttribute('path', 'tntDetailList['+i+'].id_tnt_detail');
			formElement.setAttribute('name', 'tntDetailList['+i+'].id_tnt_detail');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].partitionName');
			formElement.setAttribute('path', 'tntDetailList['+i+'].partitionName');
			formElement.setAttribute('name', 'tntDetailList['+i+'].partitionName');
			formElement.setAttribute('value', 'P'+(i+1));
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].totalFte');
			formElement.setAttribute('path', 'tntDetailList['+i+'].totalFte');
			formElement.setAttribute('name', 'tntDetailList['+i+'].totalFte');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].start_date_planning');
			formElement.setAttribute('path', 'tntDetailList['+i+'].start_date_planning');
			formElement.setAttribute('name', 'tntDetailList['+i+'].start_date_planning');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].end_date_planning');
			formElement.setAttribute('path', 'tntDetailList['+i+'].end_date_planning');
			formElement.setAttribute('name', 'tntDetailList['+i+'].end_date_planning');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].start_date_learn');
			formElement.setAttribute('path', 'tntDetailList['+i+'].start_date_learn');
			formElement.setAttribute('name', 'tntDetailList['+i+'].start_date_learn');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].end_date_learn');
			formElement.setAttribute('path', 'tntDetailList['+i+'].end_date_learn');
			formElement.setAttribute('name', 'tntDetailList['+i+'].end_date_learn');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].start_date_assist');
			formElement.setAttribute('path', 'tntDetailList['+i+'].start_date_assist');
			formElement.setAttribute('name', 'tntDetailList['+i+'].start_date_assist');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].end_date_assist');
			formElement.setAttribute('path', 'tntDetailList['+i+'].end_date_assist');
			formElement.setAttribute('name', 'tntDetailList['+i+'].end_date_assist');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].start_date_perform');
			formElement.setAttribute('path', 'tntDetailList['+i+'].start_date_perform');
			formElement.setAttribute('name', 'tntDetailList['+i+'].start_date_perform');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].end_date_perform');
			formElement.setAttribute('path', 'tntDetailList['+i+'].end_date_perform');
			formElement.setAttribute('name', 'tntDetailList['+i+'].end_date_perform');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].start_date_deliver');
			formElement.setAttribute('path', 'tntDetailList['+i+'].start_date_deliver');
			formElement.setAttribute('name', 'tntDetailList['+i+'].start_date_deliver');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].end_date_deliver');
			formElement.setAttribute('path', 'tntDetailList['+i+'].end_date_deliver');
			formElement.setAttribute('name', 'tntDetailList['+i+'].end_date_deliver');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].fte_count_planning');
			formElement.setAttribute('path', 'tntDetailList['+i+'].fte_count_planning');
			formElement.setAttribute('name', 'tntDetailList['+i+'].fte_count_planning');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].fte_count_learn');
			formElement.setAttribute('path', 'tntDetailList['+i+'].fte_count_learn');
			formElement.setAttribute('name', 'tntDetailList['+i+'].fte_count_learn');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].fte_count_assist');
			formElement.setAttribute('path', 'tntDetailList['+i+'].fte_count_assist');
			formElement.setAttribute('name', 'tntDetailList['+i+'].fte_count_assist');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].fte_count_perform');
			formElement.setAttribute('path', 'tntDetailList['+i+'].fte_count_perform');
			formElement.setAttribute('name', 'tntDetailList['+i+'].fte_count_perform');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].fte_count_deliver');
			formElement.setAttribute('path', 'tntDetailList['+i+'].fte_count_deliver');
			formElement.setAttribute('name', 'tntDetailList['+i+'].fte_count_deliver');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].fte_percent_planning');
			formElement.setAttribute('path', 'tntDetailList['+i+'].fte_percent_planning');
			formElement.setAttribute('name', 'tntDetailList['+i+'].fte_percent_planning');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].fte_percent_learn');
			formElement.setAttribute('path', 'tntDetailList['+i+'].fte_percent_learn');
			formElement.setAttribute('name', 'tntDetailList['+i+'].fte_percent_learn');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].fte_percent_assist');
			formElement.setAttribute('path', 'tntDetailList['+i+'].fte_percent_assist');
			formElement.setAttribute('name', 'tntDetailList['+i+'].fte_percent_assist');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].fte_percent_perform');
			formElement.setAttribute('path', 'tntDetailList['+i+'].fte_percent_perform');
			formElement.setAttribute('name', 'tntDetailList['+i+'].fte_percent_perform');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
			formElement = document.createElement('input');
			formElement.setAttribute('type', 'hidden');
			formElement.setAttribute('id', 'details['+i+'].fte_percent_deliver');
			formElement.setAttribute('path', 'tntDetailList['+i+'].fte_percent_deliver');
			formElement.setAttribute('name', 'tntDetailList['+i+'].fte_percent_deliver');
			formElement.setAttribute('value', '');
			emptyDiv.appendChild(formElement);
		}
		row = document.createElement("tr");
		for(var j=0; j<2; j++){
			var s = i+"_"+j;
			cell = document.createElement("td");
			cell.setAttribute("align", "center");
			cell.setAttribute("bgcolor", "#fafafa");
			if(j==0)
			{
				textStr = document.createElement("input");
				textStr.setAttribute("value", "Partition-"+parseInt(i+1));
				textStr.setAttribute("style", "outline:none; border:0px;");
				textStr.setAttribute("class", "textBoxTnt_invisible_partition");
				textStr.setAttribute("tabindex", "-1");
				textStr.setAttribute("readonly", "");
				cell.appendChild(textStr);
			}else if(j==1){
				cell.setAttribute("id", "fte_"+s);
				textnode = document.createElement("input");
				textnode.setAttribute("type", "text");
				textnode.setAttribute("id", "partition_fte_"+s);
				textnode.setAttribute("size", "6");
				textnode.setAttribute("class", "textBoxTnt_Small");
				textnode.setAttribute("onkeyup", "return calcTotalFte('partition_fte_"+s+"','"+i+"')");
				var val = 0;
				if(document.getElementById("details["+i+"].totalFte"))
				{	
					val = (document.getElementById("details["+i+"].totalFte").value?document.getElementById("details["+i+"].totalFte").value:'');
				}	
				total += Number(val);
				eftArray[i] = val;
				textnode.setAttribute("value", val);
				cell.appendChild(textnode);
				cell.setAttribute("height", "10");
			}
			row.appendChild(cell);
		}
		x.appendChild(row);
	}
	//--------------
	//------- total count row -------
	row = document.createElement("tr");

	cell = document.createElement("td");
	cell.setAttribute("align", "center");
	cell.setAttribute("bgcolor","#efefef");
	cell.setAttribute("height","25");
	
	textStr = document.createTextNode("Total");
	cell.appendChild(textStr);
	row.appendChild(cell);
	
	cell = document.createElement("td");
	cell.setAttribute("align", "center");
	cell.setAttribute("bgcolor","#efefef");
	cell.setAttribute("height","25");
	span = document.createElement('span');
	span.setAttribute("id", "total_fte");
	textStr = document.createTextNode("0");
	span.appendChild(textStr);
	cell.appendChild(span);
	row.appendChild(cell);
	
	x.appendChild(row);
	//--------------
	//document.getElementById("total_fte").innerHTML=total;
	document.getElementById('total_fte').innerHTML = document.getElementById('id_hidden_serviceFTECount').value;
}

function changeDateFormat(dateString)
{
	var s = dateString.substr(5,dateString.length);
	var j = dateString.substr(0,4);
	s = s.concat('-'+j);
	s = s.split('-').join('/');
	return s;
}

//------- calculate total fte -------
function calcTotalFte(valIdStr,rowNum){
	var partFteVal = document.getElementById(''+valIdStr+'').value;
	var totalFteVal = 0;
	if(partFteVal == null || partFteVal == ""){
		partFteVal = 0;
	}
	if(isNumeric(partFteVal) == false){
		alert("Please enter numeric characters.");
		document.getElementById(''+valIdStr+'').value = "";
		document.getElementById(''+valIdStr+'').focus();
	}else if(!isFloat(partFteVal) == false){
		alert("Please enter only numeric value.");
		document.getElementById(''+valIdStr+'').value = "";
		document.getElementById(''+valIdStr+'').focus();
	}else{
		eftArray[rowNum] = partFteVal;
		for(var k=0; k<partition_num; k++){
			totalFteVal = (isNaN(parseFloat(totalFteVal))? 0 :parseFloat(totalFteVal)+(isNaN(parseFloat(eftArray[k])) ? 0 : parseFloat(eftArray[k])));
		}
	}
}

function checkStartEndDate()
{
	var regExp = /(\d{1,2})\/(\d{1,2})\/(\d{2,4})/;
	if(parseInt(max_End_date.replace(regExp, "$3$2$1")) < parseInt(max_Start_date.replace(regExp, "$3$2$1")))
	{
		return true;
	}
	return false;
}


//------- is numeric -------
function isNumeric(numStr){
	return !isNaN(parseInt(numStr * 1));
}

//------- is whole number -------
function isWholeNumber(str){
	var regexp = /^[0-9]+([\,\.][0-9]+)?$/g;
	var result = regexp.test(str);
	return result;
}

function isFloat(str)
{
	return isNaN(parseFloat(str));
}

function showDataTable()
{
	for(var m=0; m<partition_num; m++)
	{
		if(document.getElementById('partition_fte_'+m+'_1').value == '')
		{
			alert('Please enter FTE count for all partitions');
			return;
		}
	}
	var totalFteVal = 0;
	for(var s=0; s<partition_num; s++)
	{
		totalFteVal += parseFloat(eftArray[s]);
	}
	if(totalFteVal != parseFloat(document.getElementById('id_hidden_serviceFTECount').value))
	{
		alert('Total number of FTE is different from that entered for steady state.');
		return;
	}
	document.getElementById('id_div_tab_data').style.display = "none";
	var val_partition = document.getElementById('id_text_num_part').value;
	var isNumber = !isNaN(parseInt(val_partition * 1));
	if(isNumber)
		{
			var tableDiv = document.getElementById('id_div_tab_data');
			tableDiv.setAttribute("style", "display:block;width:1112px;");
			for(var i=0; i<val_partition; i++)
			{
				arr_partitions[i] = 'Partition'+(i+1);
			}
			drawTableWithRows(val_partition);
		}
	else
		{
			alert('Please enter a numeric value');
		}
}
function drawTableWithRows(val_partition)
{
	if (!document.getElementsByTagName) return;
	document.getElementById('partition_FTE').style.display = 'none';
	number_of_partitions = val_partition;
	var x = document.getElementById('main_tbody');
	while(x.firstChild)
		{
			x.removeChild(x.firstChild);
		}
	var row, cell=null, textnode;
	for(var i=0; i<val_partition; i++)
		{
			row = document.createElement("tr");
			for(var j=0; j<11; j++)
				{
				if(j == 0){
						cell = document.createElement("td");
						textStr = document.createTextNode("Partition - "+(i+1));
						cell.setAttribute("rowspan", "2");
						cell.appendChild(textStr);
				}
				else
				{
						var s = i+"_"+j;
						cell = document.createElement("td");
						cell.setAttribute("align", "right");
						cell.setAttribute("id", s);
						textnode = document.createElement("input");
						textnode.setAttribute("type", "text");
						textnode.setAttribute("id", "text"+s);
						textnode.setAttribute("size", "6");
						textnode.setAttribute("class", "textBoxTnt_Small");
						textnode.setAttribute("style", "float:left;");
						textnode.setAttribute("onkeypress", "return dateKeyPressed()");
						if((j==1) || (j==2))
						{
							if(j==1 && document.getElementById("details["+i+"].start_date_planning"))
							{
								textnode.setAttribute("value", (document.getElementById("details["+i+"].start_date_planning")).value?(document.getElementById("details["+i+"].start_date_planning").value):'');
							}
							else
							{
								if(document.getElementById("details["+i+"].end_date_planning"))
									textnode.setAttribute("value", (document.getElementById("details["+i+"].end_date_planning")).value?(document.getElementById("details["+i+"].end_date_planning").value):'');
							}
						}
						else if((j==3) || (j==4))
						{
							if(j==3 && document.getElementById("details["+i+"].start_date_learn"))
							{
								textnode.setAttribute("value", (document.getElementById("details["+i+"].start_date_learn").value)?(document.getElementById("details["+i+"].start_date_learn").value):'');
							}
							else
							{
								if(document.getElementById("details["+i+"].end_date_learn"))
								textnode.setAttribute("value", (document.getElementById("details["+i+"].end_date_learn").value)?document.getElementById("details["+i+"].end_date_learn").value:'');
							}
						}
						else if((j==5) || (j==6))
						{
							if(j==5 && document.getElementById("details["+i+"].start_date_assist"))
							{
								textnode.setAttribute("value", (document.getElementById("details["+i+"].start_date_assist").value)?document.getElementById("details["+i+"].start_date_assist").value:'');
							}
							else
							{
								if(document.getElementById("details["+i+"].end_date_assist"))
								textnode.setAttribute("value", (document.getElementById("details["+i+"].end_date_assist").value)?document.getElementById("details["+i+"].end_date_assist").value:'');
							}
						}
						else if((j==7) || (j==8))
						{
							if(j==7 && document.getElementById("details["+i+"].start_date_perform"))
							{
								textnode.setAttribute("value", (document.getElementById("details["+i+"].start_date_perform").value)?document.getElementById("details["+i+"].start_date_perform").value:'');
							}
							else
							{ 
								if(document.getElementById("details["+i+"].end_date_perform"))
									textnode.setAttribute("value", (document.getElementById("details["+i+"].end_date_perform").value)?document.getElementById("details["+i+"].end_date_perform").value:'');
							}
						}
						else if((j==9) || (j==10))
						{
							if(j==9 && document.getElementById("details["+i+"].start_date_deliver"))
							{
								textnode.setAttribute("value", (document.getElementById("details["+i+"].start_date_deliver").value)?document.getElementById("details["+i+"].start_date_deliver").value:'');
							}
							else
							{
								if(document.getElementById("details["+i+"].end_date_deliver"))
									textnode.setAttribute("value", (document.getElementById("details["+i+"].end_date_deliver").value)?document.getElementById("details["+i+"].end_date_deliver").value:'');
							}
						}
						cell.appendChild(textnode);
						cell.setAttribute("height", "20");
					}
				row.appendChild(cell);
				row.setAttribute("bgcolor", "#fafafa");
				row.setAttribute("style", "font-family:Arial;font-size:12px;");
		}
			x.appendChild(row);
			//------- for EFT -------
			row = document.createElement("tr");
			for(var j=1; j<=5; j++)
				{
					var eftId = i+"_"+j;
					cell = document.createElement("td");
					cell.setAttribute("colspan", "2");
					cell.setAttribute("align", "left");
					cell.setAttribute("id", eftId);
					textStr = document.createTextNode("FTE% ");
					cell.appendChild(textStr);
					textnode = document.createElement("input");
					textnode.setAttribute("class", "textBoxTnt_Small");
					textnode.setAttribute("type", "text");
					textnode.setAttribute("id", "eft_"+eftId);
					textnode.setAttribute("size", "3");
					textnode.setAttribute("onkeyup", "return calcAndshowFTECount(this,"+j+")");
					if(j==1 && document.getElementById("details["+i+"].fte_percent_planning"))
					{
						textnode.setAttribute("value", (document.getElementById("details["+i+"].fte_percent_planning").value)?document.getElementById("details["+i+"].fte_percent_planning").value:'');
					}
					else if(j==2 && document.getElementById("details["+i+"].fte_percent_learn"))
					{
						textnode.setAttribute("value", (document.getElementById("details["+i+"].fte_percent_learn").value)?document.getElementById("details["+i+"].fte_percent_learn").value:'');
					}
					else if(j==3 && document.getElementById("details["+i+"].fte_percent_assist"))
					{
						textnode.setAttribute("value", (document.getElementById("details["+i+"].fte_percent_assist").value)?document.getElementById("details["+i+"].fte_percent_assist").value:'');
					}
					else if(j==4 && document.getElementById("details["+i+"].fte_percent_perform")) 
					{
						textnode.setAttribute("value", (document.getElementById("details["+i+"].fte_percent_perform").value)?document.getElementById("details["+i+"].fte_percent_perform").value:'');
					}
					else if(j==5 && document.getElementById("details["+i+"].fte_percent_deliver"))
					{
						textnode.setAttribute("value", (document.getElementById("details["+i+"].fte_percent_deliver").value)?document.getElementById("details["+i+"].fte_percent_deliver").value:'');
					}
					cell.appendChild(textnode);
					textnode = document.createElement("input");
					textnode.setAttribute("type", "text");
					textnode.setAttribute("class", "textBoxTnt_invisible");
					textnode.setAttribute("id", "percent_"+eftId);
					textnode.setAttribute("style", "outline:none; border:0px; float:right;background-color:#fafafa;");
					textnode.setAttribute("readonly", "");
					textnode.setAttribute("tabindex", "-1");
					textnode.setAttribute("size", "3");
					if(j==1 && document.getElementById("details["+i+"].fte_count_planning"))
					{
						textnode.setAttribute("value", (document.getElementById("details["+i+"].fte_count_planning").value)?document.getElementById("details["+i+"].fte_count_planning").value:'');
					}
					else if(j==2 && document.getElementById("details["+i+"].fte_count_learn"))
					{
						textnode.setAttribute("value", (document.getElementById("details["+i+"].fte_count_learn").value)?document.getElementById("details["+i+"].fte_count_learn").value:'');
					}
					else if(j==3 && document.getElementById("details["+i+"].fte_count_assist"))
					{
						textnode.setAttribute("value", (document.getElementById("details["+i+"].fte_count_assist").value)?document.getElementById("details["+i+"].fte_count_assist").value:'');
					}
					else if(j==4 && document.getElementById("details["+i+"].fte_count_perform"))
					{
						textnode.setAttribute("value", (document.getElementById("details["+i+"].fte_count_perform").value)?document.getElementById("details["+i+"].fte_count_perform").value:'');
					}
					else if(j==5 && document.getElementById("details["+i+"].fte_count_deliver"))
					{
						textnode.setAttribute("value", (document.getElementById("details["+i+"].fte_count_deliver").value)?document.getElementById("details["+i+"].fte_count_deliver").value:'');
					}
					cell.appendChild(textnode);
					cell.setAttribute("height", "20");
					row.appendChild(cell);
					row.setAttribute("bgcolor", "#fafafa");
					row.setAttribute("style", "font-family:Arial;font-size:12px;");
				}
			x.appendChild(row);
		}
	
	for(var i=0; i<val_partition; i++)
	{
		for(var j=0; j<11; j++)
			{
				var s = i+"_"+j;
				$('#text'+s).datepicker({ showOn: 'button', buttonImageOnly: true, buttonImage: '../images/icon_cal.png', dateFormat: 'yy-mm-dd' });
			}
	}
}

function calcAndshowFTECount(valIdStr,rowNum)
{
	var partFteVal = document.getElementById(valIdStr.id).value;
	if(partFteVal == null || partFteVal == ""){
		partFteVal = 0;
	}
	if((isNumeric(partFteVal) == true) || (isWholeNumber(partFteVal) == true))
	{
		var str = valIdStr.id.split('_');
		var s = eftArray[parseInt(str[1], 10)];
		var value = (partFteVal*s)/100;
		document.getElementById('percent_'+str[1]+'_'+str[2]).value=value;
	}
	else
	{
		alert("Please enter a valid % value.");
		document.getElementById(valIdStr.id).value = "";
		document.getElementById(valIdStr.id).focus();
	}
}

function dateKeyPressed()
{
	alert('Please use the calendar for input.');
	return false;
}

function createChartWithValues()
{
	var goVal = 0;
	document.getElementById('id_div_num_part').style.display = "none";
	document.getElementById('id_div_tab_data').style.display = "none";
	for(var i=0; i<number_of_partitions; i++){
		var rowTotal = 0;
		for(var j=1; j<=5; j++){
			rowTotal = rowTotal+parseInt(document.getElementById('percent_'+i+'_'+j).value);
		}
		
		var partitionNumber = parseInt(i)+parseInt(1); 
		if(eftArray[i] < rowTotal){
			var diff = ((rowTotal - eftArray[i])/eftArray[i])*100;
			goVal = 1;
			alert("Partition "+partitionNumber+" FTE count exceeds the total FTE count by "+diff+"%.");
			document.getElementById('id_div_tab_data').style.display = "block";
			return;
		}
	}
	if(goVal == 0)
	{
		document.getElementById('GanttChartDIV').style.display = "block";
		for(var i=0; i<number_of_partitions; i++)
		{
			arr_planning[i] = new Array(2*number_of_partitions);
			arr_learn[i] = new Array(2*number_of_partitions);
			arr_assist[i] = new Array(2*number_of_partitions);
			arr_perform[i] = new Array(2*number_of_partitions);
			arr_deliver[i] = new Array(2*number_of_partitions);
			arr_fte[i] = new Array(number_of_partitions);
			arr_percent_fte[i] = new Array(number_of_partitions);
		}
		for(var i=0; i<number_of_partitions; i++)
			{
				document.getElementById('details['+i+'].partitionName').value = arr_partitions[i];
				document.getElementById('details['+i+'].totalFte').value = document.getElementById('partition_fte_'+i+'_1').value;
				document.getElementById('details['+i+'].solutionId').value = document.getElementById('solutionId').value; 
				var j = 1;
				arr_planning[i][0] = document.getElementById('text'+i+'_'+j).value;
				document.getElementById('details['+i+'].start_date_planning').value = arr_planning[i][0];
				arr_planning[i][0] = changeDateFormat(arr_planning[i][0]);
				arr_fte[i][0] = document.getElementById('eft_'+i+'_'+j).value;
				document.getElementById('details['+i+'].fte_percent_planning').value = arr_fte[i][0];
				arr_percent_fte[i][0] = document.getElementById('percent_'+i+'_'+j).value;
				document.getElementById('details['+i+'].fte_count_planning').value = arr_percent_fte[i][0];
				j++;
				arr_planning[i][1] = document.getElementById('text'+i+'_'+j).value;
				document.getElementById('details['+i+'].end_date_planning').value = arr_planning[i][1];
				arr_planning[i][1] = changeDateFormat(arr_planning[i][1]);
				arr_fte[i][1] = document.getElementById('eft_'+i+'_'+j).value;
				document.getElementById('details['+i+'].fte_percent_learn').value = arr_fte[i][1];
				arr_percent_fte[i][1] = document.getElementById('percent_'+i+'_'+j).value;
				document.getElementById('details['+i+'].fte_count_learn').value = arr_percent_fte[i][1];
				j++;
				arr_learn[i][0] = document.getElementById('text'+i+'_'+j).value;
				document.getElementById('details['+i+'].start_date_learn').value = arr_learn[i][0];
				arr_learn[i][0] = changeDateFormat(arr_learn[i][0]);
				arr_fte[i][2] = document.getElementById('eft_'+i+'_'+j).value;
				document.getElementById('details['+i+'].fte_percent_assist').value = arr_fte[i][2];
				arr_percent_fte[i][2] = document.getElementById('percent_'+i+'_'+j).value;
				document.getElementById('details['+i+'].fte_count_assist').value = arr_percent_fte[i][2];
				j++;
				arr_learn[i][1] = document.getElementById('text'+i+'_'+j).value;
				document.getElementById('details['+i+'].end_date_learn').value = arr_learn[i][1];
				arr_learn[i][1] = changeDateFormat(arr_learn[i][1]);
				arr_fte[i][3] = document.getElementById('eft_'+i+'_'+j).value;
				document.getElementById('details['+i+'].fte_percent_perform').value = arr_fte[i][3];
				arr_percent_fte[i][3] = document.getElementById('percent_'+i+'_'+j).value;
				document.getElementById('details['+i+'].fte_count_perform').value = arr_percent_fte[i][3];
				j++;
				arr_assist[i][0] = document.getElementById('text'+i+'_'+j).value;
				document.getElementById('details['+i+'].start_date_assist').value = arr_assist[i][0];
				arr_assist[i][0] = changeDateFormat(arr_assist[i][0]);
				arr_fte[i][4] = document.getElementById('eft_'+i+'_'+j).value;
				document.getElementById('details['+i+'].fte_percent_deliver').value = arr_fte[i][4];
				arr_percent_fte[i][4] = document.getElementById('percent_'+i+'_'+j).value;
				document.getElementById('details['+i+'].fte_count_deliver').value = arr_percent_fte[i][4];
				j++;
				arr_assist[i][1] = document.getElementById('text'+i+'_'+j).value;
				document.getElementById('details['+i+'].end_date_assist').value = arr_assist[i][1];
				arr_assist[i][1] = changeDateFormat(arr_assist[i][1]);
				j++;
				arr_perform[i][0] = document.getElementById('text'+i+'_'+j).value;
				document.getElementById('details['+i+'].start_date_perform').value = arr_perform[i][0];
				arr_perform[i][0] = changeDateFormat(arr_perform[i][0]);
				j++;
				arr_perform[i][1] = document.getElementById('text'+i+'_'+j).value;
				document.getElementById('details['+i+'].end_date_perform').value = arr_perform[i][1];
				arr_perform[i][1] = changeDateFormat(arr_perform[i][1]);
				j++;
				arr_deliver[i][0] = document.getElementById('text'+i+'_'+j).value;
				document.getElementById('details['+i+'].start_date_deliver').value = arr_deliver[i][0];
				arr_deliver[i][0] = changeDateFormat(arr_deliver[i][0]);
				j++;
				arr_deliver[i][1] = document.getElementById('text'+i+'_'+j).value;
				document.getElementById('details['+i+'].end_date_deliver').value = arr_deliver[i][1];
				arr_deliver[i][1] = changeDateFormat(arr_deliver[i][1]);
			}
		var isAllValueEnteredAndValidated = checkAndValidateValues();
		if(isAllValueEnteredAndValidated)
			{
				drawGanttChart(arr_planning, arr_learn, arr_assist, arr_perform, arr_deliver,number_of_partitions);
			}
		else
			{
				alert('Please enter correct values and retry.');
				document.getElementById('id_div_tab_data').style.display="block";
			}
	}
}

function checkAndValidateValues()
{
	//Check 1. All the fields are populated
	for(var i=0; i<number_of_partitions; i++)
		{
			for(var j=0; j<2; j++)
				{
					if(arr_planning[i][j]==null || arr_planning[i][j]=='')
					{
						alert('Please enter dates for all phases.');
						return false;
					}
					else if(arr_learn[i][j]==null || arr_learn[i][j]=='')
					{
						alert('Please enter dates for all phases.');
						return false;
					}
					else if(arr_assist[i][j]==null || arr_assist[i][j]=='')
					{
						alert('Please enter dates for all phases.');
						return false;
					}
					else if(arr_perform[i][j]==null || arr_perform[i][j]=='')
					{
						alert('Please enter dates for all phases.');
						return false;
					}
					else if(arr_deliver[i][j]==null || arr_deliver[i][j]=='')
					{
						alert('Please enter dates for all phases.');
						return false;
					}
				}
		}
	//2. Check that start date is before end date
	var is_planning_dates_ok = checkPlanning();
	if(is_planning_dates_ok)
		{
			var is_learn_dates_ok = checkLearn();
			if(is_learn_dates_ok)
				{
					var is_assist_dates_ok = checkAssist();
					if(is_assist_dates_ok)
						{
							var is_perform_dates_ok = checkPerform();
							if(is_perform_dates_ok)
								{
									var is_deliver_dates_ok = checkDeliver();
									if(is_deliver_dates_ok)
										{
											
										}
									else
										{
											return false;
										}
								}
							else
							{
								return false;
							}
						}
					else
					{
						return false;
					}
				}
			else
			{
				return false;
			}
		}
	else
		{
			return false;
		}
	// Check if FTE % is filled up
	for(var i=0; i<number_of_partitions; i++)
	{
		for(var j=0; j<5; j++)
			{
				if((arr_fte[i][j]==null) || (arr_fte[i][j]==''))
				{
					return false;
				}
				if((arr_percent_fte[i][j]==null) || (arr_percent_fte[i][j]==''))
				{
					return false;
				}
			}
	}
	return true;
}
function checkPlanning()
{
	var lowValue = null;
	var highValue = null;
	for(var i=0; i<number_of_partitions; i++)
		{
			lowValue = new Date(arr_planning[i][0]);
			highValue = new Date(arr_planning[i][1]);
			if(highValue < lowValue)
			{
				return false;
			}
			if(lowValue < new Date(max_Start_date))
			{
				return false;
			}
			if(highValue > new Date(max_End_date))
			{
				return false;
			}
			highValue = null;
			lowValue = null;
		}
	return true;
}
function checkLearn()
{
	var lowValue = null;
	var highValue = null;
	for(var i=0; i<number_of_partitions; i++)
		{
			lowValue = new Date(arr_learn[i][0]);
			highValue = new Date(arr_learn[i][1]);
			if(highValue < lowValue)
			{
				return false;
			}
			if(lowValue < new Date(max_Start_date))
			{
				return false;
			}
			if(highValue > max_End_date)
			{
				return false;
			}
			if(lowValue < new Date(arr_planning[i][1]))
			{
				return false;
			}
			highValue = null;
			lowValue = null;
		}
	return true;
}
function checkAssist()
{
	var lowValue = null;
	var highValue = null;
	for(var i=0; i<number_of_partitions; i++)
		{
			lowValue = new Date(arr_assist[i][0]);
			highValue = new Date(arr_assist[i][1]);
			if(highValue < lowValue)
				{
					return false;
				}
			if(lowValue < new Date(max_Start_date))
			{
				return false;
			}
			if(highValue > new Date(max_End_date))
			{
				return false;
			}
			if(lowValue < new Date(arr_learn[i][1]))
			{
				return false;
			}
			highValue = null;
			lowValue = null;
		}
	return true;
}
function checkPerform()
{
	var lowValue = null;
	var highValue = null;
	for(var i=0; i<number_of_partitions; i++)
		{
			lowValue = new Date(arr_perform[i][0]);
			highValue = new Date(arr_perform[i][1]);
			if(highValue < lowValue)
				{
					return false;
				}
			if(lowValue < new Date(max_Start_date))
			{
				return false;
			}
			if(highValue > new Date(max_End_date))
			{
				return false;
			}
			if(lowValue < new Date(arr_assist[i][1]))
			{
				return false;
			}
			highValue = null;
			lowValue = null;
		}
	return true;
}
function checkDeliver()
{
	var lowValue = null;
	var highValue = null;
	for(var i=0; i<number_of_partitions; i++)
		{
			lowValue = new Date(arr_deliver[i][0]);
			highValue = new Date(arr_deliver[i][1]);
			if(highValue < lowValue)
			{
				return false;
			}
			if(lowValue < new Date(max_Start_date))
			{
				return false;
			}
			if(highValue > new Date(max_End_date))
			{
				return false;
			}
			if(lowValue < new Date(arr_perform[i][1]))
			{
				return false;
			}
			highValue = null;
			lowValue = null;
		}
	return true;
}

function createFormElements()
{
	var emptyDiv = document.getElementById('id_div_num_part');
	for(var i=newNumPartitions; i<partition_num; i++)
	{
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].solutionId');
		formElement.setAttribute('name', 'tntDetailList['+i+'].solutionId');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].id_tnt_detail');
		formElement.setAttribute('name', 'tntDetailList['+i+'].id_tnt_detail');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].partitionName');
		formElement.setAttribute('name', 'tntDetailList['+i+'].partitionName');
		formElement.setAttribute('value', 'P'+(i+1));
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].totalFte');
		formElement.setAttribute('name', 'tntDetailList['+i+'].totalFte');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].start_date_planning');
		formElement.setAttribute('name', 'tntDetailList['+i+'].start_date_planning');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].end_date_planning');
		formElement.setAttribute('name', 'tntDetailList['+i+'].end_date_planning');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].start_date_learn');
		formElement.setAttribute('name', 'tntDetailList['+i+'].start_date_learn');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].end_date_learn');
		formElement.setAttribute('name', 'tntDetailList['+i+'].end_date_learn');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].start_date_assist');
		formElement.setAttribute('name', 'tntDetailList['+i+'].start_date_assist');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].end_date_assist');
		formElement.setAttribute('name', 'tntDetailList['+i+'].end_date_assist');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].start_date_perform');
		formElement.setAttribute('name', 'tntDetailList['+i+'].start_date_perform');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].end_date_perform');
		formElement.setAttribute('name', 'tntDetailList['+i+'].end_date_perform');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].start_date_deliver');
		formElement.setAttribute('name', 'tntDetailList['+i+'].start_date_deliver');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].end_date_deliver');
		formElement.setAttribute('name', 'tntDetailList['+i+'].end_date_deliver');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].fte_count_planning');
		formElement.setAttribute('name', 'tntDetailList['+i+'].fte_count_planning');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].fte_count_learn');
		formElement.setAttribute('name', 'tntDetailList['+i+'].fte_count_learn');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].fte_count_assist');
		formElement.setAttribute('name', 'tntDetailList['+i+'].fte_count_assist');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].fte_count_perform');
		formElement.setAttribute('name', 'tntDetailList['+i+'].fte_count_perform');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].fte_count_deliver');
		formElement.setAttribute('name', 'tntDetailList['+i+'].fte_count_deliver');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].fte_percent_planning');
		formElement.setAttribute('name', 'tntDetailList['+i+'].fte_percent_planning');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].fte_percent_learn');
		formElement.setAttribute('name', 'tntDetailList['+i+'].fte_percent_learn');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].fte_percent_assist');
		formElement.setAttribute('name', 'tntDetailList['+i+'].fte_percent_assist');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].fte_percent_perform');
		formElement.setAttribute('name', 'tntDetailList['+i+'].fte_percent_perform');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
		formElement = document.createElement('input');
		formElement.setAttribute('type', 'hidden');
		formElement.setAttribute('id', 'details['+i+'].fte_percent_deliver');
		formElement.setAttribute('name', 'tntDetailList['+i+'].fte_percent_deliver');
		formElement.setAttribute('value', '');
		emptyDiv.appendChild(formElement);
	}
}
