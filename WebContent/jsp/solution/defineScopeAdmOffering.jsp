<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../js/image-picker.js" type="text/javascript"></script>
<link href="../css/image-picker.css" rel="stylesheet" media="screen"
	type="text/css" />

<input type="hidden" name="serviceType" id="serviceType"
	value="${serviceType}" />
	
<div style="width: 70%; height: 200px; margin-top: 15px; margin-left: 10px; padding-left: 220px;"
	id="serviceTypeSelection">
	<select>
		<option></option>
		<option title="Break In Installed base"
			data-img-src="../images/breakInInstalledBase.png" value="1" data-img-label="ADM services on existing Ericsson implementations" serviceType=""></option>
		<option title="Transformation Led"
			data-img-src="../images/transformationLed.png" value="2" data-img-label="ADM as a part of continued Transformation deal" serviceType="Product"></option>
		<option title="3PP ADM Around non Ericsson applications"
			data-img-src="../images/thirdPartyAdm.png" value="3" data-img-label="ADM as a door opener for CSI business into IT ecosystem" serviceType="Generic"></option>
	</select>
</div>
<div style="width: 100%; margin-top: 10px;"
	id="contentDiv"></div>

<div class="tabsDefineScoprButtons" style="float: right; width: 35%;">
	<div id="serviceOfferingPrev" class="TabsCommonButtons">Previous</div>
	<c:choose>
		<c:when test="${hasEditSolAccess!='false'}">
			<div id="serviceOfferingSave" class="TabsCommonButtons">Save</div>
		</c:when>
	</c:choose>
	<div id="serviceOfferingNext" class="TabsCommonButtons">Next</div>
</div>