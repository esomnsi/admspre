<!DOCTYPE html>

<head>

   <link rel="stylesheet" type="text/css" href="jchartfx.attributes.css" />
   <link rel="stylesheet" type="text/css" href="jchartfx.palette.css" />

   <script type="text/javascript" src="../js/jchartfx.system.js"></script>
   <script type="text/javascript" src="../js/jchartfx.coreBasic.js"></script>  
	<script type="text/javascript" src="../js/jchartfx.animation.js"></script>
	
		

</head>
<body onload="loadCharts()">


<div id="ChartDiv4" style="width:100%;height:300px; float: left;"></div>

<div id="ChartDiv" style="width:50%;height:300px;float: left;" ></div>
<div id="ChartDiv2" style="width:50%;height:300px; float: right;"></div>

<script type="text/javascript" language="javascript">
 var chart1;
 function loadCharts(){
	 loadChart1();
	 loadChart2();
	 loadChart4();
 }

 function loadChart1(){	
	 
	 	var td;
		chart1 = new cfx.Chart();
		var laborCostValues = '${laborDataString}'.split(",");
		td = new cfx.TitleDockable();
		td.setText("Labor Cost Summary Report");
		chart1.getTitles().add(td);
		chart1.getLegendBox().setVisible(true);
		chart1.setGallery(cfx.Gallery.Pie);
		chart1.getAllSeries().getPointLabels().setVisible(true);
		chart1.getView3D().setEnabled(true);
		chart1.getAnimations().getLoad().setEnabled(true);

		var myPie;
		myPie = (chart1.getGalleryAttributes());
		myPie.setExplodingMode(cfx.ExplodingMode.All);
		myPie.setSliceSeparation(10);

		var data = chart1.getData();

		for(var i=0;i<'${years}';i++){
			data.setItem(0, i,parseInt(laborCostValues[i]));
		}
	  	var labels = chart1.getAxisX().getLabels();
	 	labels.clear();
		for(var j=0;j<'${years}';j++){
	   		labels.setItem(j, "Year "+(j+1));
		}
		for(var k=0;k<'${years}';k++){
			chart1.getPoints().getItem(k).setFillMode(cfx.FillMode.Gradient);
		}
		var divHolder = document.getElementById('ChartDiv');
		chart1.create(divHolder);
 }
 
function loadChart2()
{ 
	var td;
	chart1 = new cfx.Chart();
	var laborCostValues = '${nonLaborDataString}'.split(",");
	
	td = new cfx.TitleDockable();
	td.setText("Non Labor Cost Summary Report");
	chart1.getTitles().add(td);
	chart1.getLegendBox().setVisible(true);
	chart1.setGallery(cfx.Gallery.Pie);
	chart1.getAllSeries().getPointLabels().setVisible(true);
	chart1.getView3D().setEnabled(true);
	chart1.getAnimations().getLoad().setEnabled(true);
	var myPie;
	myPie = (chart1.getGalleryAttributes());
	myPie.setExplodingMode(cfx.ExplodingMode.All);
	myPie.setSliceSeparation(10);

	var data = chart1.getData();
	for(var i=0;i<'${years}';i++){
		data.setItem(0, i,parseInt(laborCostValues[i]));
	}
  	var labels = chart1.getAxisX().getLabels();
 	labels.clear();
	for(var j=0;j<'${years}';j++){
   		labels.setItem(j, "Year "+(j+1));
	}
	for(var k=0;k<'${years}';k++){
		chart1.getPoints().getItem(k).setFillMode(cfx.FillMode.Gradient);
	}
	var divHolder = document.getElementById('ChartDiv2');
	chart1.create(divHolder);
}

function loadChart4(){
	chart1 = new cfx.Chart();
	chart1.setGallery(cfx.Gallery.Bar);
	chart1.getLegendBox().setVisible(true);
	chart1.getAllSeries().setStackedStyle(cfx.Stacked.Normal);
	chart1.getView3D().setEnabled(true);
	chart1.getAnimations().getLoad().setEnabled(true);
	var td;
	td = new cfx.TitleDockable();
	td.setText("Total Cost Summary Report");
	chart1.getTitles().add(td);

	var obj = JSON.parse('${totalCostData}');
	chart1.setDataSource(obj);
	
	var divHolder = document.getElementById('ChartDiv4');
	chart1.create(divHolder);
}


function loadChart5(){
	chart1 = new cfx.Chart();
	chart1.setGallery(cfx.Gallery.Pie);
chart1.getAllSeries().getPointLabels().setVisible(true);
var pie = chart1.getGalleryAttributes();
pie.setExplodingMode(cfx.ExplodingMode.All);

PopulateBrowserUsage(chart1);
var fields = chart1.getDataSourceSettings().getFields();

var field1 = new cfx.FieldMap();
field1.setName("Service Element");
field1.setUsage(cfx.FieldUsage.RowHeading);
fields.add(field1);



var fieldVal = new cfx.FieldMap();
fieldVal.setName("% Contribution");
fieldVal.setUsage(cfx.FieldUsage.Value);
fields.add(fieldVal);

var data = chart1.getData();
data.setSeries(1);

chart1.getAllSeries().getPointLabels().setVisible(true);
var titles = chart1.getTitles();
var title = new cfx.TitleDockable();
title.setText("% Contribution Of Cost Elements ");
titles.add(title);

	function PopulateBrowserUsage(chart1) {
	var obj = JSON.parse('${serviceElementData}');
	chart1.setDataSource(obj);
	var divHolder = document.getElementById('ChartDiv5');
	chart1.create(divHolder);
	}
}



function loadDetails(){
	document.getElementById("ChartDiv3").style.display = 'block';
	document.getElementById("close").style.display = 'block';
	document.getElementById("costElements").style.display = 'none';
	loadChart3();
	document.getElementById("detail").style.display = 'none';
}

function loadDetails1(){
	document.getElementById("ChartDiv5").style.display = 'block';
	document.getElementById("close1").style.display = 'block';
	loadChart5();
	document.getElementById("costElements").style.display = 'none';
	document.getElementById("detail").style.display = 'none';
}

function hide(){
	document.getElementById("ChartDiv3").innerHTML = "";
	document.getElementById("ChartDiv3").style.display = 'none';
	document.getElementById("close").style.display = 'none';
	document.getElementById("detail").style.display = 'block';
	document.getElementById("costElements").style.display = 'block';
	
}
function hide1(){
	document.getElementById("ChartDiv5").innerHTML = "";
	document.getElementById("ChartDiv5").style.display = 'none';
	document.getElementById("close1").style.display = 'none';
	document.getElementById("costElements").style.display = 'block';
	document.getElementById("detail").style.display = 'block';
}

function loadChart3(){
	chart1 = new cfx.Chart();
	chart1.setGallery(cfx.Gallery.Bar);
	chart1.getLegendBox().setVisible(true);
	chart1.getAllSeries().setStackedStyle(cfx.Stacked.Normal);
	chart1.getView3D().setEnabled(true);
	chart1.getAnimations().getLoad().setEnabled(true);
	var td;
	td = new cfx.TitleDockable();
	td.setText("Detailed Labor Cost Summary Report");
	chart1.getTitles().add(td);

	var obj = JSON.parse('${data1}');
	chart1.setDataSource(obj);
	
	var divHolder = document.getElementById('ChartDiv3');
	chart1.create(divHolder);
}



</script>

<a id="detail" style="color: white;text-decoration: none; display: block;" onclick="loadDetails()">
<div class="TabsCommonButtons" style="width: 140px; float: left;">
	Detailed Labor Cost
</div>
</a> 
<a id="costElements" style="color: white;text-decoration: none; display: block;" onclick="loadDetails1()">
<div class="TabsCommonButtons" style="width: 140px; float: left;">
	Cost Elements %
</div>
</a> 


<div id="ChartDiv3" style="width:60%;height:300px; float: left; display: none;"></div>
<a id="close" style="color: white;text-decoration: none; display: none;" onclick="hide()">
<div class="TabsCommonButtons" style="float: left;">
	Close
</div>
</a>


<div id="ChartDiv5" style="width:100%;height:300px; float: left; display: none;"></div>
<a id="close1" style="color: white;text-decoration: none; display: none;" onclick="hide1()">
<div class="TabsCommonButtons" style="float: left;">
	Close
</div>
</a>



</body>

<a href="/ADM_PRE/report/reportCostSummary" style="color: white;text-decoration: none;">
<div class="TabsCommonButtons">
	Back
</div>
</a>
