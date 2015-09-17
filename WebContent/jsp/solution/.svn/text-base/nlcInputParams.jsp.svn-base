<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="msg" uri="http://www.springframework.org/tags"%>


<table cellspacing="2" cellpadding="0" width="100%"
	class="NonLeabrCostPanel">
	<tr class="NonLeabrCostPanelTDColor">
		<td width="400">Region</td>
		<td><form:input path="inputParams.region"  readonly="true" class="nonLaborReadOnly"/></td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Currency to be Quoted in</td>
		<td><form:input path="inputParams.currrencyCode" readonly="true" class="nonLaborReadOnly" /></td>
	</tr>
	<%--<tr class="NonLeabrCostPanelTDColor">
		<td>Conversion Rate</td>
		<td><form:input class="NonLeabrCostInputPanel" readonly="true"
				id="conversionRate" path="inputParams.conversionRate" /></td>
	</tr>  --%>
	<%--<tr class="NonLeabrCostPanelTDColor">
		<td>MS Project Start Date</td>
		<td ><form:input class="nonLaborReadOnly" 
				type="text" path="inputParams.stDateString" readonly="true" /></td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>MS Project End Date </td>
		<td><form:input class="nonLaborReadOnly" type="text" readonly="true"
				path="inputParams.endDateString" />
		</td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>MS Project Duration in Months</td>
		<td><form:input class="nonLaborReadOnly" readonly="true"
				type="text" path="inputParams.projectDuration" /></td>
		<td colspan="2">&nbsp;</td>
	</tr>  --%>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Inflation Rate %</td>
		<td><form:input path="inputParams.inflationRateMS" id="inflationRateMS" readonly="true" class="nonLaborReadOnly" />
		</td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Working Days in a Month</td>
		<td><form:input
				path="inputParams.workingDaysInMonth" class="nonLaborReadOnly" readonly="true"  id="workingDaysInMonth"/></td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Working Hours in a Day</td>
		<td><form:input id="workingHrsInMonth"
				path="inputParams.workingHrsInMonth" readonly="true" class="nonLaborReadOnly" /></td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Invoicing Type <sup style="color: red">*</sup></td>
		<td><form:select class="NonLeabrCostInputPanel" id="invoiceType"
				path="inputParams.invoiceType" items="${invoiceTypeMap}" /></td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>One Round Trip Ticket Cost <sup style="color: red">*</sup></td>
		<td><form:input class="NonLeabrCostInputPanel"
				path="inputParams.roundTripTicketCost" id="roundTripTicketCost" onblur="calculateDependencies(this);"/></td>
	</tr>
	<%--<tr class="NonLeabrCostPanelTDColor">
		<td>One Long Term Visa Cost <sup style="color: red">*</sup></td>
		<td><form:input class="NonLeabrCostInputPanel" id="longTermVisaCost"
				path="inputParams.longTermVisaCost" onblur="calculateDependencies(this);"/></td>
	</tr>  --%>
	<tr class="NonLeabrCostPanelTDColor">
		<td>One Short Term Visa Cost <sup style="color: red">*</sup></td>
		<td ><form:input class="NonLeabrCostInputPanel" id="shortTermVisaCost"
				path="inputParams.shortTermVisaCost" onblur="calculateDependencies(this);" /></td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Onsite Hotel Cost Per Night of stay <sup style="color: red">*</sup></td>
		<td><form:input class="NonLeabrCostInputPanel" id="onsiteHotelCostPerNight"
		 path="inputParams.onsiteHotelCost" onblur="calculateDependencies(this);" /></td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Onsite Daily Per Diem <sup style="color: red">*</sup></td>
		<td><form:input class="NonLeabrCostInputPanel" id="onsitePerdiem"
				path="inputParams.onsitePerdiem" onblur="calculateDependencies(this);" /></td>
	</tr>
	<%--<tr class="NonLeabrCostPanelTDColor">
		<td>Onsite FSI Costs Per Employee Per Year <sup style="color: red">*</sup> </td>
		<td><form:input class="NonLeabrCostInputPanel" id="onsiteFSICost"
				path="inputParams.onsiteFSICost" /></td>
	</tr>  --%>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Onsite Conveyance Costs Per Day per FTE <sup style="color: red">*</sup></td>
		<td><form:input class="NonLeabrCostInputPanel" id="onsiteConveyanceCost"
				path="inputParams.onsiteConveyanceCost" onblur="calculateDependencies(this);" /></td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Mobile communication cost per Onsite FTE per month <sup style="color: red">*</sup></td>
		<td ><form:input class="NonLeabrCostInputPanel" id="mobileCostPerOnsiteFTE"
				path="inputParams.mobileCostPerOnsiteFTE" onblur="calculateDependencies(this);" />
		</td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Will the platform be used for delivery<sup style="color: red">*</sup></td>
		<td><form:select class="NonLeabrCostInputPanel" id="msdpPlatformUsed"
				path="inputParams.msdpPlatformUsed" items="${optionMap}" onchange="calculateMSDPCostOth(this);" /></td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Cost per FTE Per Month</td>
		<td><form:input class="NonLeabrCostInputPanel" id="msdpCostPerFTEMnth"
				path="inputParams.msdpCost" onblur="calculateMSDPCostOth(this);"/>
		</td>
	</tr>
	<%--<tr class="NonLeabrCostPanelTDColor">
		<td>Service Tax Rate</td>
		<td><form:input path="inputParams.serviceTax" class="nonLaborReadOnly"/>
		</td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>VAT %</td>
		<td><form:input path="inputParams.vat" class="nonLaborReadOnly" /></td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>Customs on HW</td>
		<td><form:input path="inputParams.customs" class="nonLaborReadOnly"/>
		</td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr class="NonLeabrCostPanelTDColor">
		<td>WHT on SW</td>
		<td><form:input path="inputParams.wht" class="nonLaborReadOnly" />
		</td>
		<td colspan="2">&nbsp;</td>
	</tr>  --%>
	
</table>
