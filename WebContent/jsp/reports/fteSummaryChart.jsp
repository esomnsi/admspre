<!DOCTYPE html>

<head>

   <link rel="stylesheet" type="text/css" href="jchartfx.attributes.css" />
   <link rel="stylesheet" type="text/css" href="jchartfx.palette.css" />

   <script type="text/javascript" src="../js/jchartfx.system.js"></script>
   <script type="text/javascript" src="../js/jchartfx.coreBasic.js"></script>  
	<script type="text/javascript" src="../js/jchartfx.animation.js"></script>
	
		

</head>
<body onload="loadCharts()">




<script type="text/javascript" language="javascript">
 var chart1;
 function loadCharts(){
	 loadChart1();
 }

function loadChart1(){
	chart1 = new cfx.Chart();
	chart1.setGallery(cfx.Gallery.Lines);
	chart1.getLegendBox().setVisible(true);
	/* chart1.getAllSeries().setStackedStyle(cfx.Stacked.Normal);
	chart1.getView3D().setEnabled(true); */
	chart1.getAnimations().getLoad().setEnabled(true);
	var td;
	td = new cfx.TitleDockable();
	td.setText("FTE SUMMARY CHART");
	chart1.getTitles().add(td);

	var obj = JSON.parse('${data}');
	chart1.setDataSource(obj);
	
	var divHolder = document.getElementById('ChartDiv3');
	chart1.create(divHolder);
}



</script>

<div id="ChartDiv3" style="width:100%;height:300px; float: left;"></div>
<a href="/ADM_PRE/report/reportFTESummary" style="color: white;text-decoration: none;">
<div class="TabsCommonButtons" style="float: left;">
	Back
</div>
</a>

</body>

