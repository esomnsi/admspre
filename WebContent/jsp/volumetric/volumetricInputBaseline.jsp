<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">

$(document).ready(function() {
	loadTab();
	//window.onload = loadTab;
	
	<%-- $('#prev').click(function() {
	    var url = "<%=request.getContextPath()%>/solution/productEstimation";    
    	$(location).attr('href',url);
	});
	
	$('#next').click(function() {
		var url = "<%=request.getContextPath()%>/solution/complexityAdjuster";
		$(location).attr('href',url);
	}); --%>
	
	<%-- $('#innerT1').click(function() {
		var url = "<%=request.getContextPath()%>/solution/applicationMaintenanceNew";
		$(location).attr('href',url);
	}); --%>
	
	$('#tt1').click(function() {
		var url = "<%=request.getContextPath()%>/solution/productEstimation";
		$(location).attr('href', url);
	});

});

	/* var tab = "${selectedTab}";
	var innertab = "${selectedInnerTab}"; */

	function loadTab() {
		var index = 1;
		var tab = "${selectedTab}";
		if ('APP_DEV' == tab) {
			index = 1;
			//setTab(index);
		} else if ('APP_TEST' == tab) {
			index = 3;
			//setTab(index);
		} else if ('APP_MAIN' == tab){
			index = 2;
		}if ('APP_MGMT' == tab) {
			index = 4;
			//setTab(index);
		}
		setTab(index);
	};

	function setTab(index) {

		currentSelectedTab = index;

		document.getElementById('tt1').className = 'usual';
		document.getElementById('tt2').className = 'usual';

		document.getElementById('tab1').style.display = "none";
		document.getElementById('tab2').style.display = "none";

		document.getElementById('tt' + (currentSelectedTab)).className = 'selected';
		document.getElementById('tab' + (currentSelectedTab)).style.display = "block";

	};

	function populate(index) {
		if (index == 1) {// this case is not been called.
			document.getElementById("admSup").action = "./productEstimation";
			document.getElementById("admSup").submit();
		} else if (index == 2) {
			document.getElementById("admSup").action = "./applicationMaintenanceNew";
			document.getElementById("admSup").submit();
		} else if (index == 3) {
			document.getElementById("admSup").action = "./productAppTesting";
			document.getElementById("admSup").submit();
		} else if (index == 4) {
			document.getElementById("admSup").action = "./applicationManagement";
			document.getElementById("admSup").submit();
		}
	};

	/* function populateTab(index) {
		if (index == 1) { // this case is not been called
			document.getElementById("appMainTabs").action = "./applicationMaintenanceNew";
			document.getElementById("appMainTabs").submit();

		} else if (index == 2) {
			document.getElementById("appMainTabs").action = "./applicationMaintenanceView";
			document.getElementById("appMainTabs").submit();
		}
	}; */

	/* function loadInnerTab() {
		if ('APP_MAIN_INPUT' == innertab) {
			innerTabid = 1;
		} else if ('APP_MAIN_REVIEW' == innertab) {
			innerTabid = 2;
		}
		setInnerTab(innerTabid);

	}; */

	/* function setInnerTab(i) {
		currentSelectedInnerTab = i;

		document.getElementById('innerT1').className = 'usual';
		document.getElementById('innerT2').className = 'usual';

		document.getElementById('innerTab1').style.display = "none";
		document.getElementById('innerTab2').style.display = "none";

		document.getElementById('innerT' + (currentSelectedInnerTab)).className = 'selected';
		document.getElementById('innerTab' + (currentSelectedInnerTab)).style.display = "block";
	}; */
	
	
</script>
<form:form id="admSup">

	<div id="serviceBucket" style="margin-bottom: 5px;">
		<table width="730" align="center">
			<tr>
				<td align="center" colspan="6"><jsp:include
						page="../solution/serviceBucket.jsp" /></td>
			</tr>
		</table>
	</div>

	<div class="tdHeaderLabel"
		style="width: 1096px; margin-bottom: 4px; margin-left: 1px;">Input
		Baseline</div>

	<div id="estimationInput" style="margin-bottom: 5px;">
		<table width="730" align="center">
			<tr>
				<td align="center" colspan="6"><jsp:include
						page="productEstimationCommonInput.jsp"></jsp:include></td>
			</tr>
		</table>
	</div>

	<div id="usual1" class="usual">
		<ul>
			<li><a class="" href="#tab1" id="tt1">Application
					Development</a></li>
			<li><a class="" href="#tab2" id="tt2" onclick="populate(2);">Application
					Maintenance</a></li>
			<li><a class="" href="#tab3" id="tt3" onclick="populate(3);">Application Testing</a></li>
			<li><a class="" href="#tab4" id="tt4" onclick="populate(4);">Application Management</a></li>
		</ul>
	</div>
</form:form>

<div style="display: none; padding-bottom: 40px;" id="tab1" class="tabBodyArea">
<c:if test="${(selectedTab =='APP_DEV')}">
	<c:choose>
	<c:when test="${(hasServiceElements)}">
		
			<jsp:include page="productInputBaseline.jsp"></jsp:include>
		
	</c:when>
	<c:when test="${(hasFastTrackServiceElement)}">
		<br><br><br>
		<div class="message">Since Fast Track Request has been selected in Application Management, this tab needs to be filled.
		</div>
		<jsp:include page="productInputBaseline.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<br><br><br>
		<div id="showError" class="errorMessageLogin" style="font-weight: ;text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
			 -moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;"><br> &nbsp&nbsp* Service Elements for Application Development are not selected. <br><br>
		</div>
	</c:otherwise>
	</c:choose>
	</c:if>
</div>

<div style="display: none;" id="tab2" class="tabBodyArea">
	<c:if test="${(selectedTab =='APP_MAIN')}">
		<c:if test="${(selectedInnerTab == 'APP_MAIN_INPUT')}">
			<jsp:include page="applicationMaintenanceNew.jsp"></jsp:include>
		</c:if>
		<c:if test="${(selectedInnerTab == 'APP_MAIN_REVIEW')}">
			<jsp:include page="reviewApplicationMaintenance.jsp"></jsp:include>
		</c:if>
		<c:if test="${!isAppMainSelected}">
			<br><br><br>
			<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
				 -moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;"><br> &nbsp&nbsp* Service Elements for Application Maintenance are not selected. <br><br>
			</div>
		</c:if>
	</c:if>
</div>

<%-- <div style="display: none;" id="tab2" class="">
	<c:if test="${(selectedTab =='APP_MAIN')}">
		<c:choose>
			<c:when test="${isAppMainSelected}">
			<form:form id="appMainTabs">
			<div id="usual1" class="usual"><br>
				<ul>
					<li><a class="" id="innerT1" href="#innerTab1"><span></span>Baseline
							Data</a></li>
					<li><a class="" id="innerT2" href="#innerTab2"
						onclick="populateTab(2);"><span></span>Review</a></li>
				</ul>
			</div>
		</form:form>	
		
		<div style="display: none;" id="innerTab1" class="tabBodyArea">
			<c:if test="${(selectedInnerTab =='APP_MAIN_INPUT')}">
			<br><br><br><br>
				<jsp:include page="applicationMaintenanceNew.jsp" />
			</c:if>
		</div>
		<div style="display: none;" id="innerTab2" class="tabBodyArea">
			<c:if test="${(selectedInnerTab =='APP_MAIN_REVIEW')}">
			<br>
				<jsp:include page="reviewApplicationMaintenance.jsp" />
			</c:if>
		</div>
		</c:when>
			<c:otherwise>
			<br><br><br>
			<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
	-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;"><br> &nbsp&nbsp* Service Elements for Application Maintenance are not selected. <br><br>
		</div>
			</c:otherwise>
		</c:choose>
	</c:if>
	<div style="margin-top: 20px; float: right">
		<table width="100%">

			<tr bgcolor="#f7f7f7">
				<td class="tdButtonRow" align="right" bgcolor="#f7f7f7"><a
					id="next" class="TabsCommonButtons">Next</a> <a id="prev"
					class="TabsCommonButtons">Previous</a></td>
			</tr>
		</table>
	</div>
</div> --%>


<div style="display: none; padding-bottom: 40px;" id="tab3" class="tabBodyArea">
<c:if test="${(selectedTab =='APP_TEST')}">
	<c:choose>
	<c:when test="${(hasServiceElements)}">		
		
			<jsp:include page="applicationTestingForProduct.jsp"></jsp:include>
		
	</c:when>
	<c:otherwise>
		<br><br><br>
		<div id="showError" class="errorMessageLogin" style="font-weight: ;text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
			 -moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;"><br> &nbsp&nbsp* Service Elements for Application Testing are not selected. <br><br>
		</div>
	</c:otherwise>
	</c:choose>
	</c:if>
</div>
	
<div style="display: none;" id="tab4" class="tabBodyArea">
	<c:if test="${(selectedTab =='APP_MGMT')}">
	<c:choose>
			<c:when test="${isAppMgmtSelected}">
		<jsp:include page="ApplicationManagement.jsp"></jsp:include>
		</c:when>
			<c:otherwise>
			<br><br><br>
			<div id="showError" class="errorMessageLogin" style="text-align: left;-webkit-border-radius: 5px 5px 5px 5px;
	-moz-border-radius: 5px 5px 5px 5px;border: 1px solid #FF0000;"><br> &nbsp&nbsp* Service Elements for Application Management are not selected. <br><br>
		</div>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>

<script type="text/javascript">
	$("#usual1 ul").idTabs();
</script>