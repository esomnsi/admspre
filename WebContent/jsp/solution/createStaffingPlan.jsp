<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<!-- <script type="text/javascript">
var currentPage = 0;
function displayPart(showPage)
{
	try
	{//16,11
		showPage=(showPage<0||showPage>1)?0:showPage;
		currentPage = showPage;
		var table = document.getElementById('dataTable');
		for(var row=0;row<table.rows.length;row++)
		{
			var oneRow = table.rows[row];
		//alert('Rowes='+row+'| Col='+oneRow.cells.length);
			if(oneRow.cells.length==29)
			{
				if(showPage == 0)
				{
					for(var cel=0;cel<oneRow.cells.length;cel++)
					{ //16,11
					 if(cel>16)
						 {
						 	oneRow.cells[cel].style.display = 'none';
						 }else
							 {
							 	oneRow.cells[cel].style.display = '';
							 }
					
					}
				}
			if(showPage == 1)
				{
					for(var cel=0;cel<oneRow.cells.length;cel++)
					{ //8,6,7
					 if(!cel>16)
						 {
						 	oneRow.cells[cel].style.display = 'none';
						 }else
							 {
							 	oneRow.cells[cel].style.display = '';
							 }
					
					}
				}
			}
		}
		
		}catch(e)
		{
			alert(e.message);
		}

	}
</script> -->

<script type="text/javascript">
	var i = 0;
	$(document)
			.ready(
					function() {
						if ("${hasEditSolAccess}" == 'false') {
							$("input").attr("disabled", "disabled");
							$("select").attr("disabled","disabled");
						}
						//for(var i=0;i<13;i++)
						<c:forEach items="${staffAugForm.staffAugDTOList}" var="staffAugDTO" varStatus="status">
						$("#startdate_" + i).datepicker({
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '../images/icon_cal.png'
						});
						$("#enddate_" + i).datepicker({
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '../images/icon_cal.png'
						});
						$("#startdate1_" + i).datepicker({
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '../images/icon_cal.png'
						});
						$("#enddate1_" + i).datepicker({
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '../images/icon_cal.png'
						});
					/* 	$("#demandDate_" + i).datepicker({
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '../images/icon_cal.png'
						}); */
						i = i + 1;
						</c:forEach>
						$("#startdate").datepicker({
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '../images/icon_cal.png'
						});
						$("#enddate").datepicker({
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '../images/icon_cal.png'
						});
						$("#startdate1").datepicker({
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '../images/icon_cal.png'
						});
						$("#enddate1").datepicker({
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '../images/icon_cal.png'
						});
						/* $("#demandDate").datepicker({
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '../images/icon_cal.png'
						}); */
						$("input").change(function () {
							if(this.value != ""){
								this.style.borderColor = "";
							}
							
						});
					});
</script>
<script type="text/javascript">
	var sd = i;
	var ed = i;
	var sd1 = i;
	var ed1 = i;
	var dd = i;
	$(document).ready(function() {
		
		
	/* 	$("#copyLastRow").click(function() {
			//alert(1);
			  var row = $('#dataTable tbody>tr:last').clone(true);
			//var row = addRow();
			//$("td input:text", row).val("");
			//$("td input:checkbox", row).attr('checked', false);
			$(row).insertAfter('#dataTable tbody>tr:last'); 
		}); */
		//Copy row code here
		$("#addC").click(function() {
			$("#addA").click();
			
			var len =  $('input[name="demandRaisedBy"]').length;
		//setting values for new rows
			$('select[name="status"]')[len-1].value = $('select[name="status"]')[len-2].value;
			$('input[name="egibu"]')[len-1].value = $('input[name="egibu"]')[len-2].value;
			$('input[name="projectType"]')[len-1].value = $('input[name="projectType"]')[len-2].value;
			$('select[name="gttopportunity"]')[len-1].value = $('select[name="gttopportunity"]')[len-2].value;
			
			$('input[name="noDemandedPositions"]')[len-1].value = $('input[name="noDemandedPositions"]')[len-2].value;
			$('select[name="acceptSubcontractors"]')[len-1].value = $('select[name="acceptSubcontractors"]')[len-2].value;
			$('input[name="winOddsProbability"]')[len-1].value = $('input[name="winOddsProbability"]')[len-2].value;
			
			$('input[name="primaryLocation"]')[len-1].value = $('input[name="primaryLocation"]')[len-2].value;
			$('input[name="secondaryLocation"]')[len-1].value = $('input[name="secondaryLocation"]')[len-2].value;
			$('input[name="onshoreLocation"]')[len-1].value = $('input[name="onshoreLocation"]')[len-2].value;
			
			$('input[name="positionStartdate"]')[len-1].value = $('input[name="positionStartdate"]')[len-2].value;
			$('input[name="positionEnddate"]')[len-1].value = $('input[name="positionEnddate"]')[len-2].value;
			$('input[name="onshoreStartdate"]')[len-1].value = $('input[name="onshoreStartdate"]')[len-2].value;
			$('input[name="onshoreEnddate"]')[len-1].value = $('input[name="onshoreEnddate"]')[len-2].value;

			$('input[name="gradeLow"]')[len-1].value = $('input[name="gradeLow"]')[len-2].value;
			$('input[name="gradeHigh"]')[len-1].value = $('input[name="gradeHigh"]')[len-2].value;
			$('input[name="jobFamily"]')[len-1].value = $('input[name="jobFamily"]')[len-2].value;
			$('select[name="jobRole"]')[len-1].value = $('select[name="jobRole"]')[len-2].value;
			$('input[name="competency"]')[len-1].value = $('input[name="competency"]')[len-2].value;
			$('input[name="practice"]')[len-1].value = $('input[name="practice"]')[len-2].value;
			$('input[name="practiceArea"]')[len-1].value = $('input[name="practiceArea"]')[len-2].value;
			$('input[name="primarySkillSet"]')[len-1].value = $('input[name="primarySkillSet"]')[len-2].value;
			$('input[name="secondarySkillSet"]')[len-1].value = $('input[name="secondarySkillSet"]')[len-2].value;
			
			$('input[name="typeofRequest"]')[len-1].value = $('input[name="typeofRequest"]')[len-2].value;
			$('input[name="resourceManager"]')[len-1].value = $('input[name="resourceManager"]')[len-2].value;
			$('input[name="additionalInformation"]')[len-1].value = $('input[name="additionalInformation"]')[len-2].value;
			
		
			
		/* $('#dTable tbody>tr:last').clone(true)
		.insertAfter(
				'#dTable tbody>tr:last'); */
	});	
		$("#addA").click(function() {
		$("#message").removeClass();
			$("#message").html('');
			$("#staffingPlanForm input").each(function() {
				this.style.borderColor = "";
			});
			// var row = $('#dataTable tbody>tr:last').clone(true);
			var row = addRow();
			$("td input:text", row).val("");
			$("td input:checkbox", row).attr('checked', false);
			$(row).insertAfter('#dataTable tbody>tr:last');
			$("#startdate11" + sd).datepicker({
				showOn : 'button',
				buttonImageOnly : true,
				buttonImage : '../images/icon_cal.png'
			});
			$("#enddate11" + ed).datepicker({
				showOn : 'button',
				buttonImageOnly : true,
				buttonImage : '../images/icon_cal.png'
			});
			$("#startdate1" + sd1).datepicker({
				showOn : 'button',
				buttonImageOnly : true,
				buttonImage : '../images/icon_cal.png'
			});
			$("#enddate1" + ed1).datepicker({
				showOn : 'button',
				buttonImageOnly : true,
				buttonImage : '../images/icon_cal.png'
			});
			/* $("#demandDate" + dd).datepicker({
				showOn : 'button',
				buttonImageOnly : true,
				buttonImage : '../images/icon_cal.png'
			}); */
			// row.insertAfter('#dataTable tbody>tr:last'); 
			return false;
		});

		$("#Reset_Table").click(function() {
		$("#message").removeClass();
			$("#message").html('');
			/* $("#dataTable").find('input:text').val('');
			$("#dataTable").find('option:selected').removeAttr('selected');
			$("#dataTable").find('input:checkbox').attr('checked', false); */
			document.getElementById("staffingPlanForm").reset();
			$("#staffingPlanForm input").each(function() {
				this.style.borderColor = "";
			});
		});

		/*         $("#Delete_FadeOut").click(function() {
		         $(this).parent().parent().css("background-color","#FF3700");
		            $(this).parent().parent().fadeOut(600, function(){
		                $(this).remove();
		            });
		     });
		        $("#Delete").click(function() { 
		                $(this).parent().parent().remove();
		        });
		        $("#Checked_Delete").click(function() {
		         $('#dataTable input:checkbox').each(function(){
		          if(this.checked){
		           $(this).parent().parent().remove();
		          }
		         });
		         return false;
		     });

		   $("#Reset_Table").click(function() {
		            $("#dataTable").find('input:text').val('');
		            $("#dataTable").find('input:checkbox').attr('checked',false);
		     });
		        $("#Reset_Form").click(function() { 
		            $("#myForm").find('input').val('');
		     }); */
	});
	var isSuccessValidation=true;
	function isDate(txtDate)
	{
	  var currVal = txtDate;
	  if(currVal == '')
		    return false;
	   
	  //Declare Regex 
		var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
	  var dtArray = currVal.match(rxDatePattern); // is format OK?
	 
	  if (dtArray == null)
	     return false;
	  
	  //Checks for mm/dd/yyyy format.
	  dtMonth = dtArray[1];
	  dtDay= dtArray[3];
	  dtYear = dtArray[5];
	 
	  if (dtMonth < 1 || dtMonth > 12)
	      return false;
	  else if (dtDay < 1 || dtDay> 31)
	      return false;
	  else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
	      return false;
	  else if (dtMonth == 2)
	  {
	     var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	     if (dtDay> 29 || (dtDay ==29 && !isleap))
	          return false;
	  }
	  return true;
	}

	function checkMandateField() {
		var mandateFlag = true;
			$("#dataTable input").each(function() {
					if (this.value == "" || this.value == null) {
					$("#message").addClass('errorMessageDisp');
					this.style.borderColor = "red";
					$("#message").html('Please provide all fields value!');
					mandateFlag = false;
				}
						
			});
    	return mandateFlag;
	}		
	function isValidDate(obj) {
		try
		{
		//fDate = this.value;
		/* $('#staffingPlanForm input[id^="demandDate*"]').each(function() {
		alert(this.value);
	}); */
		if(!isDate(obj.value))
		{
			obj.style.borderColor = "red";
			obj.focus();	
			$("#message").addClass('errorMessageDisp');
			$("#message").html('Please enter a valid date!');
			isSuccessValidation = false;
			obj.value = "";
			return false;
		}else
		{
			$("#message").removeClass();
			$("#message").html('');
			obj.style.borderColor = "";
			isSuccessValidation = true;
			return true;
		}
		}catch(e)
		{
			//alert(e.message);
			}			
	}

	function saveStaffingPlan() {
	if(!checkMandateField())
		{
			return false;
		}
		if(!isSuccessValidation)
			{
				$("#message").addClass('errorMessageDisp');
				$("#message").html('Please check as you have not done successful field validations!');
				return false;
			}
		$("#message").removeClass();
		$("#message").html('');
	
		/* 	document.forms[0].action = "/ADM_PRE/solution/saveStaffAug";
			document.forms[0].submit(); */
		document.getElementById("staffingPlanForm").submit();
	}
	
	function saveNextStaffingPlan() {
		if(!checkMandateField())
			{
				return false;
			}
			if(!isSuccessValidation)
				{
					$("#message").addClass('errorMessageDisp');
					$("#message").html('Please check as you have not done successful field validations!');
					return false;
				}
			$("#message").removeClass();
			$("#message").html('');
		
			 	document.forms[0].action = "./saveNextStaffPlan";
				document.forms[0].submit(); 
			/* document.getElementById("staffingPlanForm").submit(); */
		}
	
	/* function changeOnshoreStartDate(onShoreStartDateObj){
			 var otherInputs = $(onShoreStartDateObj).parents('td').siblings().find('input');
			 $(otherInputs[11]).val('');
	} */
	
	function onChangeOnshoreDate(obj){
		var strId = obj.id;
		if(strId.indexOf("start")>=0){
			var otherInputs = $(obj).parents('td').siblings().find('input');
			if(new Date(obj.value)>new Date($(otherInputs[11]).val())){
				obj.style.borderColor = "red";
				obj.focus();	
				$("#message").addClass('errorMessageDisp');
				$("#message").html('Please enter Onshore Start date less than Onshore End date!');
				obj.value = "";
			}
			/*  $(otherInputs[11]).val('');
			 otherInputs[11].focus();
			 otherInputs[11].style.borderColor = "red"; */
			return ;
		}
		
		 var otherInputs = $(obj).parents('td').siblings().find('input');
		if($(otherInputs[11]).val() == ''){
			obj.style.borderColor = "red";
			otherInputs[11].focus();	
			$("#message").addClass('errorMessageDisp');
			$("#message").html('Please enter Onshore Start date!');
			obj.value = "";
			return false;
		}
		else if(new Date(obj.value)<new Date($(otherInputs[11]).val())){
			obj.style.borderColor = "red";
			obj.focus();	
			$("#message").addClass('errorMessageDisp');
			$("#message").html('Please enter Onshore End date greater than Onshore Start date!');
			obj.value = "";
			return false;
		 }
	}
	function onChangePositionDate(obj){
		
		var strId = obj.id;
		if(strId.indexOf("start")>=0){
			var otherInputs = $(obj).parents('td').siblings().find('input');
			if(new Date(obj.value)>new Date($(otherInputs[9]).val())){
				obj.style.borderColor = "red";
				obj.focus();	
				$("#message").addClass('errorMessageDisp');
				$("#message").html('Please enter Position Start Date less than 	Position End Date!');
				obj.value = "";
			}
			/*  $(otherInputs[11]).val('');
			 otherInputs[11].focus();
			 otherInputs[11].style.borderColor = "red"; */
			return ;
		}
		
		 var otherInputs = $(obj).parents('td').siblings().find('input');
		if($(otherInputs[9]).val() == ''){
			obj.style.borderColor = "red";
			otherInputs[9].focus();	
			$("#message").addClass('errorMessageDisp');
			$("#message").html('Please enter Postion Start date!');
			obj.value = "";
			return false;
		}
		else if(new Date(obj.value)<new Date($(otherInputs[9]).val())){
			obj.style.borderColor = "red";
			obj.focus();	
			$("#message").addClass('errorMessageDisp');
			$("#message").html('Please enter Position End date greater than Position Start date!');
			obj.value = "";
			return false;
		 }
	}
function clearRedBorder(Obj){
	if(Obj.value != ""){
		Obj.style.borderColor = "";
	}
	
}

	function addRow() {
		<%java.text.DateFormat df = new java.text.SimpleDateFormat(
					"MM/dd/yyyy");
			String demandDate = df.format(new java.util.Date()); %>
			var row = '<tr class="tdOddRow">'
				+ '<td class="tdInputBox"><input type="text" path="demandRaisedBy" maxlength="100" name="demandRaisedBy" readonly="readonly" value=<%=session.getAttribute("userName")%> class="staffingPlanInputArea"></input></td>'
				+ '<td class="tdInputBox"><input maxlength="10" id="demandDate'+ ++dd +'" name="demandCreateddate" readonly="readonly"  value=<%=demandDate%> onchange="isValidDate(this);clearRedBorder(this);" path="demandCreateddate" type="textbox" class="staffingPlanInputArea"></input></td>'
				+ '<td class="tdInputBox"><select path="status" name="status" class="staffingPlanJumpMenu">'
				+ '<option value="1">Exported </option>'
				+ '<option value="2">Not Exported </option>'
				+ '</select>'
				+'</td>'
				+ '<td class="tdInputBox"><input maxlength="100" path="egibu" name="egibu" onchange="clearRedBorder(this);" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td class="tdInputBox"><input maxlength="100" path="projectType" name="projectType" onchange="clearRedBorder(this);" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td class="tdInputBox">'
				+ '<select path="gttopportunity" name="gttopportunity" class="staffingPlanJumpMenu">'
				+ '<option value="1">No </option>'
				+ '<option value="2">Yes </option>'
				+ '</select>'
				+ '</td>'
				+ '<td class="tdInputBox"><input maxlength="10" path="noDemandedPositions" onchange="clearRedBorder(this);" name="noDemandedPositions" value="1" readonly="true" class="staffingPlanInputArea"></input></td>'
				+ '<td class="tdInputBox"> <select path="acceptSubcontractors" name="acceptSubcontractors" class="staffingPlanJumpMenu">'
				+ '<option value="1">No </option>'
				+ '<option value="2">Yes </option>'
				+ '</select>'
				+ '</td>'
				+

				'<td><input path="winOddsProbability" maxlength="100" onchange="clearRedBorder(this);" name="winOddsProbability" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="primaryLocation" name="primaryLocation" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="secondaryLocation" name="secondaryLocation" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="onshoreLocation" name="onshoreLocation" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td class="tdInputBox"><input maxlength="10" id="startdate11'+ ++sd +'" path="positionStartdate" name="positionStartdate" onchange="isValidDate(this);onChangePositionDate(this);clearRedBorder(this);" type="textbox" class="staffingPlanInputArea"></input></td>'
				+ '<td class="tdInputBox"><input maxlength="10" id="enddate11'+ ++ed +'" path="positionEnddate" name="positionEnddate" onchange="isValidDate(this);onChangePositionDate(this);clearRedBorder(this);" type="textbox" class="staffingPlanInputArea"></input></td>'
				+ '<td class="tdInputBox"><input maxlength="10" id="startdate1'+ ++sd1 +'" path="onshoreStartdate" name="onshoreStartdate" onchange="isValidDate(this);onChangeOnshoreDate(this);clearRedBorder(this);" type="textbox" class="staffingPlanInputArea"></input></td>'
				+ '<td class="tdInputBox"><input maxlength="10" id="enddate1'+ ++ed1 +'" path="onshoreEnddate" name="onshoreEnddate" onchange="isValidDate(this);onChangeOnshoreDate(this);clearRedBorder(this);" type="textbox" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="gradeLow" name="gradeLow" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="gradeHigh" name="gradeHigh" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="jobFamily" name="jobFamily" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td class="tdInputBox"> <select  name="jobRole" class="staffingPlanJumpMenu">'
				+ <c:forEach items="${jobroleList}" var="jobrole" varStatus="status">
		'<option value="${jobrole.key}"> ${jobrole.value} </option>'
				+ </c:forEach>
		'</select>'
				+ '</td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="competency" name="competency" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="practice" name="practice" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="practiceArea" name="practiceArea" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="primarySkillSet" name="primarySkillSet" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="secondarySkillSet" name="secondarySkillSet" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="typeofRequest" name="typeofRequest" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="100" onchange="clearRedBorder(this);" path="resourceManager" name="resourceManager" value="" class="staffingPlanInputArea"></input></td>'
				+ '<td><input type="text" maxlength="255" onchange="clearRedBorder(this);" path="additionalInformation" name="additionalInformation" value="" class="staffingPlanInputArea"></input></td>'
				+ '</tr>';
		/* 
		var row = '<tr>'+
				  '<td class="tdInputBox">PR001</td>'+	
			'<td class="tdInputBox" ><input path="itemDesc" name="itSpendDto['+ rowIndex++ +'].itemDesc" id="itemDesc" value="" class="textBoxMedium"></input></td></tr>'; */
		return row;
	}
</script>
<div id="mainBody" class="bodyCss">

	<div id="serviceBucket" style="margin-bottom:10px;">
			<jsp:include page="./serviceBucket.jsp"/>
	</div>
		
	<div id="message"></div>
	<c:if test="${not empty message}">
	<div class="message" style="display: block;">${message}</div>
	</c:if>
	<div class="tdHeaderLabel FteHeaderText"
		style="mafloat: left; margin-bottom: 5px;">Steady State Staffing
		Plan</div>
	<table width="1112" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center"><jsp:include page="opportunitySummary.jsp"></jsp:include>
			</td>
		</tr>
		

		
	</table>
	<!-- 
<div id="bodyDiv1" class="mainBodyDiv1" style="width: 100%; mafloat: left; margin-bottom: 5px;">

			<h1 class="tdHeaderLabel" style="margin-top: 0px;margin-bottom: 0px;"> Steady State Staffing Report</h1>
</div> -->


	<form:form id="staffingPlanForm" method="POST"
		commandName="staffAugForm" action="saveStaffingPlan">
		<div id="bodyDiv1" class="mainBodyDiv1"
			style="float: left; width: 100%; margin-top: 2px;">
			<table width="100%" border="0" cellspacing="2" cellpadding="0"
				class="staffingPlantopTable">
				<tbody border="1">
					<tr>
						<!-- <td width="10%" class="tdInputBox">Demand Raised by :</td>
							<td class="tdInputBox"><input type="text" name="" value=""
								class="textBoxM"></td> -->
						<td width="152" bgcolor="#e3e3e3">Region:</td>
						<td width="290" bgcolor="#f7f7f7">${staffAugForm.region}</td>
						<td width="170" bgcolor="#e3e3e3">Client:</td>
						<td width="483" bgcolor="#f7f7f7">${staffAugForm.client}</td>
					</tr>
					<tr>
						<!-- <td class="tdInputBox">Demand Created On :</td>
							<td class="tdInputBox"><input id="demandDate" type="textbox"
								class="staffingPlanInputArea"></input></td> -->
						<td bgcolor="#e3e3e3">Business Unit :</td>
						<td bgcolor="#f7f7f7">${staffAugForm.vertical}</td>
						<td bgcolor="#e3e3e3">Opportunity:</td>
						<td bgcolor="#f7f7f7">${staffAugForm.opportunity}</td>
					</tr>
					<%-- 	<tr>
							<td class="tdInputBox">Status :</td>
							<td class="tdInputBox"><input type="text" name="" value=""
								class="textBoxM"></td>
							 <td class="tdLabel">Business Unit :</td>
							<td class="tdInputBox">${staffAugForm.egibu}</td>
							<td class="tdLabel">Project Type :</td>
							<td class="tdInputBox">${staffAugForm.projectType}</td> 
						</tr> --%>
					<tr>

					</tr>
				</tbody>
			</table>
		</div>

		<!-- <div id="bodyDiv1" class="mainBodyDiv1"
				style="margin-top: 1px; float: left; width: 100%; margin-bottom: 5px; overflow-y: auto; overflow-x: auto;"> -->
		<div class="FTEscrollProperty">

			<!-- <table id="dataTable" width="100%" border="0"
					style="margin-bottom: 15px;"> -->
			<table id="dTable" border="0" width="100%" cellspacing="0"
				cellpadding="0">
				<tbody>
					<tr>
						<td>
							<table id="dataTable" border="0" cellpadding="0" cellspacing="2"
								class="staffingPlanTable">
								<tr>
									<!-- 		 <td bgcolor="#e3e3e3"><strong>Region</td>
			<td bgcolor="#e3e3e3"><strong>Vertical</td> -->
									<td bgcolor="#e3e3e3"><strong>Demand Raised by</strong></td>
									<td bgcolor="#e3e3e3"><strong>Demand Created On</strong></td>
									<td bgcolor="#e3e3e3"><strong>Status</strong></td>
									<td bgcolor="#e3e3e3"><strong>Business Unit</strong></td>
									<!-- <td bgcolor="#e3e3e3"><strong>Client</td>
			<td bgcolor="#e3e3e3"><strong>Opportunity</td> -->

									<td bgcolor="#e3e3e3"><strong>Project Type</strong></td>

									<td bgcolor="#e3e3e3"><strong>GTT Opportunity</strong></td>
									<td bgcolor="#e3e3e3"><strong>Number of <br />Demanded
											Positions
									</strong></td>
									<td bgcolor="#e3e3e3"><strong>Accept
											Subcontractors</strong></td>
									<td bgcolor="#e3e3e3"><strong>Win Odds
											Probability</strong></td>
									<td bgcolor="#e3e3e3"><strong>Primary Delivery <br />Location
									</strong></td>
									<td bgcolor="#e3e3e3"><strong>Secondary Delivery
											<br />Location
									</strong></td>
									<td bgcolor="#e3e3e3"><strong>Onshore Location</strong></td>
									<td bgcolor="#e3e3e3"><strong>Position Start Date</strong></td>
									<td bgcolor="#e3e3e3"><strong>Position End Date
											**</strong></td>
									<td bgcolor="#e3e3e3"><strong>Onshore Start date
									</strong></td>
									<td bgcolor="#e3e3e3"><strong>Onshore end date</strong></td>
									<td bgcolor="#e3e3e3"><strong>Grade Low</strong></td>
									<td bgcolor="#e3e3e3"><strong>Grade High</strong></td>
									<td bgcolor="#e3e3e3"><strong>Job Family</strong></td>
									<td bgcolor="#e3e3e3"><strong>Job Role</strong></td>
									<td bgcolor="#e3e3e3"><strong>Competence Domain</strong></td>
									<td bgcolor="#e3e3e3"><strong>Sub-Competence Domain</strong></td>
									<td bgcolor="#e3e3e3"><strong>Engagement Practice</strong></td>
									<td bgcolor="#e3e3e3"><strong>Primary Skillset</strong></td>
									<td bgcolor="#e3e3e3"><strong>Secondary Skillset</strong></td>
									<td bgcolor="#e3e3e3"><strong>Type of Request</strong></td>
									<td bgcolor="#e3e3e3"><strong>Resource Manager</strong></td>
									<td bgcolor="#e3e3e3"><strong>Additional
											Information</strong></td>

								</tr>
								<c:if test="${empty staffAugForm.staffAugDTOList}">

									<tr class="tdOddRow">
										<%--  	<td class="tdInputBox"  ><select path="region" name="region" class="staffingPlanJumpMenu">
		<option value="">Select </option>
		<option value="RNAM">RNAM </option>
		<option value="RINA">RINA </option>
		<option value="RMIA">RMIA </option>
		
	</select>	
	</td>
			<td class="tdInputBox"><form:input type="text" name="vertical" path="vertical" value="" class="staffingPlanInputArea"></form:input></td>--%>
										<c:choose>
											<c:when test="${hasEditSolAccess!='false'}">
												<td class="tdInputBox"><input type="text"
													maxlength="100" name="demandRaisedBy" path="demandRaisedBy"
													readonly="readonly"
													value=<%=session.getAttribute("userName")%>
													class="staffingPlanInputArea"></input></td>
												<td class="tdInputBox"><input id="demandDate"
													maxlength="10" name="demandCreateddate" readonly="readonly"
													path="demandCreateddate" type="textbox"
													value=<%=demandDate%> class="staffingPlanInputArea"></input></td>
											</c:when>
											<c:otherwise>
												<td class="tdInputBox"><input type="text"
													maxlength="100" name="demandRaisedBy" path="demandRaisedBy"
													readonly="readonly" value="" class="staffingPlanInputArea"></input></td>
												<td class="tdInputBox"><input id="demandDate"
													maxlength="10" name="demandCreateddate" readonly="readonly"
													path="demandCreateddate" type="textbox" value=""
													class="staffingPlanInputArea"></input></td>
											</c:otherwise>
										</c:choose>
										<td class="tdInputBox"><form:select path="status"
												multiple="false" name="status" class="staffingPlanJumpMenu">
												<form:option value="1">Exported</form:option>
												<form:option value="2">Not Exported</form:option>
											</form:select></td>
										<%-- 		<td class="tdInputBox"><form:input type="text"
											name="status" path="status" value=""
											class="staffingPlanInputArea"></form:input></td> --%>

										<td class="tdInputBox"><form:input type="text"
												maxlength="100" name="egibu" path="egibu" value=""
												class="staffingPlanInputArea"></form:input></td>
										<%-- <td class="tdInputBox"><form:input type="text" name="client" path="client" value="" class="staffingPlanInputArea"></form:input></td>
			<td class="tdInputBox"><form:input type="text" name="opportunity" path="opportunity" value="" class="staffingPlanInputArea"></form:input></td> --%>

										<td class="tdInputBox">
											<!-- <select path="projectType" nademandCreateddateme="projectType" class="staffingPlanJumpMenu">
				<option value="">Select </option>
				<option value="Existing">Existing </option>
				<option value="Opportunity">Opportunity </option>
				</select> --> <form:input type="text" name="projectType"
												maxlength="100" path="projectType" value=""
												class="staffingPlanInputArea"></form:input>
										</td>
										<td class="tdInputBox"><form:select path="gttopportunity"
												multiple="false" name="gttopportunity"
												class="staffingPlanJumpMenu">
												<form:option value="1">No </form:option>
												<form:option value="2">Yes </form:option>
											</form:select></td>
										<c:choose>
											<c:when test="${hasEditSolAccess!='false'}">
												<td class="tdInputBox"><form:input
														path="noDemandedPositions" readonly="true" maxlength="10"
														name="noDemandedPositions" value="1"
														class="staffingPlanInputArea"
														onkeypress="return isNumeric(event);"></form:input></td>
											</c:when>
											<c:otherwise>
												<td class="tdInputBox"><form:input
														path="noDemandedPositions" readonly="true" maxlength="10"
														name="noDemandedPositions" value=""
														class="staffingPlanInputArea"
														onkeypress="return isNumeric(event);"></form:input></td>
											</c:otherwise>
										</c:choose>
										<td class="tdInputBox"><form:select
												path="acceptSubcontractors" multiple="false"
												name="acceptSubcontractors" class="staffingPlanJumpMenu">
												<form:option value="1">No</form:option>
												<form:option value="2">Yes</form:option>
											</form:select></td>
										<td><form:input path="winOddsProbability" maxlength="100"
												name="winOddsProbability" value=""
												class="staffingPlanInputArea"></form:input></td>

										<td><form:input type="text" path="primaryLocation"
												maxlength="100" name="primaryLocation" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="secondaryLocation"
												maxlength="100" name="secondaryLocation" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="onshoreLocation"
												maxlength="100" name="onshoreLocation" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td class="tdInputBox"><form:input id="startdate"
												maxlength="10"
												onchange="isValidDate(this);onChangePositionDate(this);"
												path="positionStartdate" type="textbox"
												class="staffingPlanInputArea"></form:input></td>
										<td class="tdInputBox"><form:input id="enddate"
												maxlength="10" path="positionEnddate"
												onchange="isValidDate(this);onChangePositionDate(this);"
												type="textbox" class="staffingPlanInputArea"></form:input></td>
										<td class="tdInputBox"><form:input id="startdate1"
												maxlength="10"
												onchange="isValidDate(this);onChangeOnshoreDate(this);"
												path="onshoreStartdate" type="textbox"
												class="staffingPlanInputArea"></form:input></td>
										<td class="tdInputBox"><form:input id="enddate1"
												maxlength="10" path="onshoreEnddate"
												onchange="isValidDate(this);onChangeOnshoreDate(this);"
												type="textbox" class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="gradeLow"
												maxlength="100" name="gradeLow" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="gradeHigh"
												maxlength="100" name="gradeHigh" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="jobFamily"
												maxlength="100" name="jobFamily" value=""
												class="staffingPlanInputArea"></form:input></td>

										<td class="tdInputBox"><form:select multiple="false"
												path="jobRole" class="staffingPlanJumpMenu">
												<c:forEach var="item" items="${jobroleList}">
													<form:option value="${item.key}">${item.value}</form:option>
												</c:forEach>
											</form:select></td>
										<%-- <td><form:input type="text" path="" name="" value="" class="textBoxM"></form:input></td> --%>
										<td><form:input type="text" path="competency"
												maxlength="100" name="competency" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="practice"
												maxlength="100" name="practice" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="practiceArea"
												maxlength="100" name="practiceArea" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="primarySkillSet"
												maxlength="100" name="primarySkillSet" value=""
												class="staffingPlanInputArea"></form:input></td>

										<td><form:input type="text" path="secondarySkillSet"
												maxlength="100" name="secondarySkillSet" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="typeofRequest"
												maxlength="100" name="typeofRequest" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="resourceManager"
												maxlength="100" name="resourceManager" value=""
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="additionalInformation"
												name="additionalInformation" value="" maxlength="255"
												class="staffingPlanInputArea"></form:input></td>

									</tr>
								</c:if>
								<c:forEach items="${staffAugForm.staffAugDTOList}"
									var="staffingPlanDTO" varStatus="status">
									<tr class="tdOddRow">
										<%--  	<td class="tdInputBox"  ><select path="region" name="region" class="staffingPlanJumpMenu">
		<option value="">Select </option>
		<option value="RNAM">RNAM </option>
		<option value="RINA">RINA </option>
		<option value="RMIA">RMIA </option>
		
	</select>	
	</td>
			<td class="tdInputBox"><form:input type="text" name="vertical" path="vertical" value="" class="textBoxM"></form:input></td>--%>
										<td class="tdInputBox"><form:input type="text"
												name="demandRaisedBy" path="demandRaisedBy" maxlength="100"
												value="${staffingPlanDTO.demandRaisedBy}" readonly="true"
												class="staffingPlanInputArea"></form:input></td>
										<td class="tdInputBox"><form:input maxlength="10"
												id="demandDate_${status.index}" readonly="true"
												path="demandCreateddate" onchange="isValidDate(this);"
												type="textbox" value="${staffingPlanDTO.demandCreateddate}"
												class="staffingPlanInputArea"></form:input></td>
										<td class="tdInputBox"><form:select path="status"
												multiple="false" name="status" class="staffingPlanJumpMenu">
												<c:choose>
													<c:when test="${staffingPlanDTO.status==1 }">
														<form:option selected="true" value="1">Exported </form:option>
													</c:when>
													<c:otherwise>
														<form:option value="1">Exported </form:option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${staffingPlanDTO.status==2 }">
														<form:option selected="true" value="2">Not Exported </form:option>
													</c:when>
													<c:otherwise>
														<form:option value="2">Not Exported </form:option>
													</c:otherwise>
												</c:choose>
											</form:select> <%-- <form:select
											path="status" multiple="false"
											name="status" class="staffingPlanJumpMenu">
											<form:option value="1">No</form:option>
											<form:option value="2">Yes</form:option>
										</form:select> --%></td>
										<%-- 									<td class="tdInputBox"><form:input type="text"
											name="status" path="status" value="${staffingPlanDTO.status}"
											class="staffingPlanInputArea"></form:input></td> --%>
										<td class="tdInputBox"><form:input type="text"
												maxlength="100" name="egibu" path="egibu"
												value="${staffingPlanDTO.egibu}"
												class="staffingPlanInputArea"></form:input></td>
										<%-- 	<td class="tdInputBox"><form:input type="text" name="client" path="client" value="" class="staffingPlanInputArea"></form:input></td>
			<td class="tdInputBox"><form:input type="text" name="opportunity" path="opportunity" value="" class="staffingPlanInputArea"></form:input></td> --%>

										<td class="tdInputBox">
											<!-- <select path="projectType" name="projectType" class="staffingPlanJumpMenu">
				<option value="">Select </option>
				<option value="Existing">Existing </option>
				<option value="Opportunity">Opportunity </option>
				</select> --> <form:input type="text" name="projectType"
												maxlength="100" path="projectType"
												value="${staffingPlanDTO.projectType}"
												class="staffingPlanInputArea"></form:input>
										</td>
										<td class="tdInputBox"><form:select path="gttopportunity"
												multiple="false" name="gttopportunity"
												class="staffingPlanJumpMenu">
												<c:choose>
													<c:when test="${staffingPlanDTO.gttopportunity==1 }">
														<form:option selected="true" value="1">No </form:option>
													</c:when>
													<c:otherwise>
														<form:option value="1">No </form:option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${staffingPlanDTO.gttopportunity==2 }">
														<form:option selected="true" value="2">Yes </form:option>
													</c:when>
													<c:otherwise>
														<form:option value="2">Yes </form:option>
													</c:otherwise>
												</c:choose>

											</form:select></td>
										<td>
											<%-- <form:input path="noDemandedPositions"
											name="noDemandedPositions"
											value="${staffingPlanDTO.noDemandedPositions}"
											class="staffingPlanInputArea"></form:input> --%> <form:input
												path="noDemandedPositions" name="noDemandedPositions"
												maxlength="10" value="1" readonly="true"
												class="staffingPlanInputArea"></form:input>
										</td>
										<td class="tdInputBox"><form:select
												path="acceptSubcontractors" name="acceptSubcontractors"
												class="staffingPlanJumpMenu" multiple="false">
												<c:choose>
													<c:when test="${staffingPlanDTO.acceptSubcontractors==1 }">
														<form:option selected="true" value="1">No</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="1">No</form:option>
													</c:otherwise>
												</c:choose>

												<c:choose>
													<c:when test="${staffingPlanDTO.acceptSubcontractors==2 }">
														<form:option selected="true" value="2">Yes</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="2">Yes</form:option>
													</c:otherwise>
												</c:choose>

											</form:select></td>
										<td><form:input path="winOddsProbability"
												name="winOddsProbability" maxlength="100"
												value="${staffingPlanDTO.winOddsProbability}"
												class="staffingPlanInputArea"></form:input></td>

										<td><form:input type="text" path="primaryLocation"
												maxlength="100" name="primaryLocation"
												value="${staffingPlanDTO.primaryLocation}"
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="secondaryLocation"
												maxlength="100" name="secondaryLocation"
												value="${staffingPlanDTO.secondaryLocation}"
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="onshoreLocation"
												maxlength="100" name="onshoreLocation"
												value="${staffingPlanDTO.onshoreLocation}"
												class="staffingPlanInputArea"></form:input></td>
										<td class="tdInputBox"><form:input maxlength="100"
												id="startdate_${status.index}"
												onchange="isValidDate(this);onChangePositionDate(this);"
												value="${staffingPlanDTO.positionStartdate}"
												path="positionStartdate" type="textbox"
												class="staffingPlanInputArea"></form:input></td>
										<td class="tdInputBox"><form:input maxlength="100"
												id="enddate_${status.index}"
												value="${staffingPlanDTO.positionEnddate}"
												path="positionEnddate"
												onchange="isValidDate(this);onChangePositionDate(this);"
												type="textbox" class="staffingPlanInputArea"></form:input></td>
										<td class="tdInputBox"><form:input maxlength="100"
												id="startdate1_${status.index}"
												onchange="isValidDate(this);onChangeOnshoreDate(this);"
												value="${staffingPlanDTO.onshoreStartdate}"
												path="onshoreStartdate" type="textbox"
												class="staffingPlanInputArea"></form:input></td>
										<td class="tdInputBox"><form:input maxlength="100"
												id="enddate1_${status.index}"
												value="${staffingPlanDTO.onshoreEnddate}"
												path="onshoreEnddate"
												onchange="isValidDate(this);onChangeOnshoreDate(this);"
												type="textbox" class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="gradeLow"
												maxlength="100" name="gradeLow"
												value="${staffingPlanDTO.gradeLow}"
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="gradeHigh"
												maxlength="100" name="gradeHigh"
												value="${staffingPlanDTO.gradeHigh}"
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="jobFamily"
												maxlength="100" name="jobFamily"
												value="${staffingPlanDTO.jobFamily}"
												class="staffingPlanInputArea"></form:input></td>

										<td class="tdInputBox">
											<%-- <form:select multiple="false"
									path="jobRole" class="staffingPlanJumpMenu">
									<form:option value="" label="--Select--" />
									<form:options items="${jobroleList}" itemValue="jobRoleId"
										itemLabel="role" />
									<!-- <option value="2">Role2 </option> -->
								</form:select> --%> <form:select multiple="false" path="jobRole"
												class="staffingPlanJumpMenu">
												<c:forEach var="item" items="${jobroleList}">
													<c:choose>
														<c:when test="${item.key == staffingPlanDTO.jobRole}">
															<form:option selected="true" value="${item.key}"> ${item.value}</form:option>
														</c:when>
														<c:otherwise>
															<form:option value="${item.key}">${item.value}
											</form:option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<!-- <option value="2">Role2 </option> -->
											</form:select>

										</td>
										<%-- <td><form:input type="text" path="" name="" value="" class="textBoxM"></form:input></td> --%>
										<td><form:input type="text" path="competency"
												maxlength="100" name="competency"
												value="${staffingPlanDTO.competency}"
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="practice"
												maxlength="100" name="practice"
												value="${staffingPlanDTO.practice}"
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="practiceArea"
												maxlength="100" name="practiceArea"
												value="${staffingPlanDTO.practiceArea}"
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="primarySkillSet"
												maxlength="100" name="primarySkillSet"
												value="${staffingPlanDTO.primarySkillSet}"
												class="staffingPlanInputArea"></form:input></td>

										<td><form:input type="text" path="secondarySkillSet"
												maxlength="100" name="secondarySkillSet"
												value="${staffingPlanDTO.secondarySkillSet}"
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="typeofRequest"
												maxlength="100" name="typeofRequest"
												value="${staffingPlanDTO.typeofRequest}"
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="resourceManager"
												maxlength="100" name="resourceManager"
												value="${staffingPlanDTO.resourceManager}"
												class="staffingPlanInputArea"></form:input></td>
										<td><form:input type="text" path="additionalInformation"
												maxlength="255" name="additionalInformation"
												value="${staffingPlanDTO.additionalInformation}"
												class="staffingPlanInputArea"></form:input></td>
										<input type="hidden"
											name="staffAugDTOList[${status.index}].staffingPlanId"
											value="${staffingPlanDTO.staffingPlanId}">
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="region" path="region"
				value="${staffAugForm.region}"> <input type="hidden"
				name="client" path="client" value="${staffAugForm.client}">
			<input type="hidden" name="vertical" path="vertical"
				value="${staffAugForm.vertical}"> <input type="hidden"
				name="opportunity" path="opportunity"
				value="${staffAugForm.opportunity}">
			<%-- <input type="hidden"
					name="egibu" path="egibu" value="${staffAugForm.egibu}"> <input
					type="hidden" name="projectType" path="projectType"
					value="${staffAugForm.projectType}"> --%>
		</div>
		<div style="float: left; width: 1112px; height: 35px;">
			<c:choose>
				<c:when test="true">
					<%-- <c:when test="${hasEditSolAccess!='false'}"> --%>
					<a id="SaveNext" onClick="javascript:saveNextStaffingPlan();"
						class="portfolioButtons">Next</a>
					<a id="Save" onClick="javascript:saveStaffingPlan();"
						class="portfolioButtons">Save</a>
					<a onclick="location.href='./createStaffingPlan'"
						class="portfolioButtons">Reset</a>
					<a id="addC" class="portfolioButtons">Add Copied</a>
					<a id="addA" class="portfolioButtons">Add New</a>
					<!-- <a id="copyLastRow" class="portfolioButtons">Copy Row</a> -->
				</c:when>
				<c:otherwise>
					<a onClick="location.href='./labourCost';" class="portfolioButtons">Next</a>
				</c:otherwise>
			</c:choose>
			<div class="portfolioButtons"
				onclick="location.href='../solution/ttDetail';">Previous</div>
		</div>
		<!-- <div class="mainBodyDiv1"
				style="width: 100%; float: left; margin-left: 0px;">
				<table width="100%" border="0">
					<tr>
						<td align="center" colspan="4" class="tdButtonRow"><a
							href="#" id="addA" class="button" target="_blank">Add New</a> <a
							class="button" id="Reset_Table" target="_self">Reset</a> 
							<input type="button" onClick="javascript:saveStaffingPlan();" class="button" target="_self" value="Save"></input></td>
					</tr>
				</table>
			</div> -->

	</form:form>

</div>

