/**
 * This js contains two method 1)generateReport(reportName) : Requesting to load
 * the report content in div. 2)downloadReport(format): Downloading the current
 * report in given format.
 */

// this will hold the currently loaded report name.
var currentReportName = "";
/**
 * This method is responsible for loading the reports in the report div.
 * 
 * @param localReportName
 */
function generateReport(context) {
	var reportId = "";
	$("input[type='radio']").each(function() {
		if ($(this).attr("checked")) {
			reportId = $(this).attr("id");
			// break;
		}
	});

	switch (reportId) {
	case "radio1":
		reportName = context + '/fteReport.png';
		currentReportName = context + '/fteReport';
		break;
	case "radio2":
		reportName = context + '/costReport.png';
		currentReportName = context + '/costReport';
		break;
	default:
		reportName = context + '/fteReport.png';
		currentReportName = context + '/fteReport';
		break;
	}

	$("#reports").hide(1000);
	$("#reportData").show(1000);
	$("#next").hide(10);
	$("#reportHeader").show(100);
	$("#excel").show(1000);
	$("#pdf").show(1000);
	$("#doc").show(1000);
	document.getElementById("back").style.display = "block";

	$("#reportData")
			.html(
					"Loading...<br><img src='/IT_MSSP/images/loading.gif' align='middle' >");

	var reportImage = "<img src=\"" + reportName + "\">";

	$('#reportData').html(reportImage);
}

/**
 * Download report function
 * 
 * @param format
 */
function downloadReport(format) {

	if (currentReportName == "") {
		alert("Please Select the report.");
		return;
	}
	// here relative url is given if relative url is not working try giving full
	// url
	var reporturl = currentReportName + "." + format;
	window
			.open(
					reporturl,
					'_target',
					'height=700,width=900,left=10,top=10,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no,status=no');
}

function loadReportsPage() {
	$("#reports").show(1000);
	$("#reportData").hide(1000);
	$("#back").hide(1000);
	$("#next").show(100);
	$("#reportHeader").hide(1000);
	$("#excel").hide(1000);
	$("#pdf").hide(1000);
	$("#doc").hide(1000);
}