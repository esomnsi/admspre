<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="msg" uri="http://www.springframework.org/tags"%>



<c:set var="loopLength" value="${fn:length(execSummary.otherCost)}" />
<c:set var="modLength_LC" value="${fn:length(execSummary.laborCost)}" />
<c:set var="modLength_NLC" value="${fn:length(execSummary.laborCost)}" />
<c:set var="lcDiff" value="${loopLength-modLength_LC}" />
<c:set var="totVal" value="0"/>

			<c:if test="${loopLength<1 }">
				<table border="0" cellpadding="0" cellspacing="2"
										class="NonLeabrCostPanel">
										
								<tr align="center" bgcolor="" class="">
								<td width="200" height="25"><b>No Data found</b>
								</td>
					</tr>
					</table>
			</c:if>
			<c:if test="${loopLength>=1 }">
			<c:set var="loopLength" value="${loopLength-1}"/>
			<table border="0" cellpadding="0" cellspacing="2"
							class="NonLeabrCostPanel">
							<tr><td>
							<b>Currency : ${execSummary.currency}</b>
							</td>
							</tr>
							<tr align="center" bgcolor="#e3e3e3" class="financialSummHearderPanel">
								<td width="200" height="25"><div class="leftHeaderSpace"><b>Particulars</b></div>
								</td>
								<c:forEach var="value" items="${execSummary.otherCost}" varStatus="count">
									<td align="center"  id="ct_${count.index+1}">
									<b><c:out value="${value.year}"></c:out></b>
									</td>
								</c:forEach>
								<td width="100" height="25"><b>Grand Total</b></div>
								</td>
							</tr>
							<tr align="center" bgcolor="#e3e3e3" class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b> Revenue</b></div>
								</td>
							</tr>
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Manpower</div>
								</td>
								<c:set var="totVal" value="0"/>
								<c:forEach var="lcValue" items="${execSummary.laborCost}" varStatus="count">
									<c:set var="totVal" value="${totVal+lcValue.revenueValue}"/> 
									<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${lcValue.revenueValue}" />
									<td align="center"  id="">
									<input type="text" readonly id="RLC" colIndex="${count.index+1}" value="${fmt_no}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<td width="100" height="25"><input type="text" readonly id="RLC_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Travel</div>
								</td>
								<c:set var="totVal" value="0"/>
								<c:forEach var="nlcValue" items="${execSummary.travelCost}" varStatus="count">
									<c:set var="totVal" value="${totVal+nlcValue.revenueValue}"/>
									<td align="center"  id="">
									<input type="text" readonly id="RTC" colIndex="${count.index+1}" value="${nlcValue.revenueValue}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25">
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<input type="text" readonly id="RTC_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Others</div>
								</td>
								<c:set var="totVal" value="0"/>
								<c:forEach var="ocValue" items="${execSummary.otherCost}" varStatus="count">
									<c:set var="totVal" value="${totVal+ocValue.revenueValue}"/>
									<td align="center"  id="">
									<input type="text" readonly id="ROC" colIndex="${count.index+1}"  value="${ocValue.revenueValue}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<td width="100" height="25"><input type="text" readonly id="ROC_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Grand Total Revenue</b></div>
								</td>
									<c:set var="totVal" value="0"/>
								<c:forEach var="gTotRevenue" items="${execSummary.grandTotalRevenue}" varStatus="count">
									<c:set var="totVal" value="${totVal+gTotRevenue}"/>
									<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${gTotRevenue}" />
									<td align="center"  id="">
									<input type="text" readonly id="RGT" colIndex="${count.index+1}" value="${fmt_no}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<td width="100" height="25"><input type="text" readonly id="RGT_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<tr align="center" bgcolor="#e3e3e3" class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b> Cost</b></div>
								</td>
							</tr>
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Manpower</div>
								</td>
								<c:set var="totVal" value="0"/>
								<c:forEach var="lcValue" items="${execSummary.laborCost}" varStatus="count">
									<c:set var="totVal" value="${totVal+lcValue.costValue}"/> 
									<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${lcValue.costValue}" />
									<td align="center"  id="">
									<input type="text" readonly id="CLC" colIndex="${count.index+1}"  value="${fmt_no}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<td width="100" height="25"><input type="text" readonly id="CLC_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Travel</div>
								</td>
								<c:set var="totVal" value="0"/>
								<c:forEach var="nlcValue" items="${execSummary.travelCost}" varStatus="count">
									<c:set var="totVal" value="${totVal+nlcValue.costValue}"/>
									<td align="center"  id="">
									<input type="text" readonly id="CTC" colIndex="${count.index+1}" value="${nlcValue.costValue}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<td width="100" height="25"><input type="text" readonly id="CTC_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Others</div>
								</td>
								<c:set var="totVal" value="0"/>
								<c:forEach var="ocValue" items="${execSummary.otherCost}" varStatus="count">
									<c:set var="totVal" value="${totVal+ocValue.costValue}"/>
									<td align="center"  id="">
									<input type="text" readonly id="COC" colIndex="${count.index+1}" value="${ocValue.costValue}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<td width="100" height="25"><input type="text" readonly id="COC_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Grand Total Cost</b></div>
								</td>
								<c:set var="totVal" value="0"/>
								<c:forEach var="gTotCost" items="${execSummary.grandTotalCost}" varStatus="count">
								   <c:set var="totVal" value="${totVal+gTotCost}"/>
									<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${gTotCost}" />
									<td align="center"  id="">
									<input type="text" readonly id="CGT" colIndex="${count.index+1}"  value="${fmt_no}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<td width="100" height="25"><input type="text" readonly id="CGT_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>MarkUp On Non Manpower Cost</b></div>
								</td>
								<c:forEach var="ocValue" items="${execSummary.otherCost}" varStatus="count">
									<td align="center"  id="">
									<input type="text" readonly id="mnm" colIndex="${count.index+1}" value="" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="mnm_Tot" value="" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>% MarkUp On Non Manpower Cost</b></div>
								</td>
								<c:forEach var="ocValue" items="${execSummary.otherCost}" varStatus="count">
									<td align="center"  id="">
									<input type="text" readonly id="pmnm" colIndex="${count.index+1}" value="" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="pmnm_Tot" value="" class="nonLaborReadOnly"/>
								</td>
							</tr>
							<tr></tr>
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Onsite Effort(In Hours)</b></div>
								</td>
								<c:set var="totVal" value="0"/>
								<c:forEach var="nlcValue" items="${execSummary.travelCost}" varStatus="count">
									<c:set var="totVal" value="${totVal+nlcValue.fteValue}"/>
									<td align="center"  id="">
									<input type="text" readonly id="onshoreFTE" colIndex="${count.index+1}" value="${nlcValue.fteValue}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<td width="100" height="25"><input type="text" readonly id="onshoreFTE_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Offshore Effort(In Hours)</b></div>
								</td>
								<c:set var="totVal" value="0"/>
								<c:forEach var="ocValue" items="${execSummary.otherCost}" varStatus="count">
									<c:set var="totVal" value="${totVal+ocValue.fteValue}"/>
									<td align="center"  id="">
									<input type="text" readonly id="ofshoreFTE" colIndex="${count.index+1}" value="${ocValue.fteValue}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<td width="100" height="25"><input type="text" readonly id="ofshoreFTE_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Grand Total Effort(In Hours)</b></div>
								</td>
								<c:set var="totVal" value="0"/>
								<c:forEach var="gTotEffort" items="${execSummary.grandTotalEffort}" varStatus="count">
									<c:set var="totVal" value="${totVal+gTotEffort}"/>
									<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${gTotEffort}" />
									<td align="center"  id="">
									<input type="text" readonly id="FTEGT" colIndex="${count.index+1}" value="${fmt_no}" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<fmt:formatNumber var="fmt_no" type="number" groupingUsed="false" maxFractionDigits="2" value="${totVal}" />
								<td width="100" height="25"><input type="text" readonly id="FTEGT_Tot" value="${fmt_no}" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<tr></tr>
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Key Ratios</b></div>
								</td>
					  		</tr>
						
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Onsite Effort to Total Effort</b></div>
								</td>
								<c:forEach var="index" begin="0" end="${loopLength}">
									<td align="center"  id="">
									<input type="text" readonly id="ratio1" colIndex="${index+1}" value="" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="ratio1_Tot" value="" class="nonLaborReadOnly"/>
								</td>
							</tr> 
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Offshore Effor to Total Effort</b></div>
								</td>
								<c:forEach var="index" begin="0" end="${loopLength}">
									<td align="center"  id="">
									<input type="text" readonly id="ratio2" colIndex="${index+1}" value="" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="ratio2_Tot" value="" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Blended Cost/Hour</b></div>
								</td>
								<c:forEach var="index" begin="0" end="${loopLength}">
									<td align="center"  id="">
									<input type="text" readonly id="ratio3" colIndex="${index+1}" value="" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="ratio3_Tot" value="" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<%-- <tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>% Deviation of Blended Cost from CPCH</b></div>
								</td>
								<c:forEach var="index" begin="0" end="${loopLength}">
									<td align="center"  id="">
									<input type="text" readonly id="ratio4" colIndex="${index+1}" value="" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="ratio4_Tot" value="" class="nonLaborReadOnly"/>
								</td>
							</tr> --%>
							
							<!--  value is 1 for MS GrandSummary Cost AC41/AC41 -->
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Normal Rate Cost as % of Overall Cost</b></div>
								</td>
								<c:forEach var="index" begin="0" end="${loopLength}">
									<td align="center"  id="">
									<input type="text" readonly id="ratio5" colIndex="${index+1}" value="1" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="ratio5_Tot" value="1" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<!--  Value=1-ratio5 -->
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace"><b>Hot Skill Rate as % of overall cost</b></div>
								</td>
								<c:forEach var="index" begin="0" end="${loopLength}">
									<td align="center"  id="">
									<input type="text" readonly id="ratio6" colIndex="${index+1}" value="0" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="ratio6_Tot" value="0" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Manpower as % of Total cost</div>
								</td>
								<c:forEach var="index" begin="0" end="${loopLength}">
									<td align="center"  id="">
									<input type="text" readonly id="ratio7" colIndex="${index+1}" value="" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="ratio7_Tot" value="" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Travel as % of Total cost</div>
								</td>
								<c:forEach var="index" begin="0" end="${loopLength}">
									<td align="center"  id="">
									<input type="text" readonly id="ratio8" colIndex="${index+1}" value="" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="ratio8_Tot" value="" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Other as % of Total cost</div>
								</td>
								<c:forEach var="index" begin="0" end="${loopLength}">
									<td align="center"  id="">
									<input type="text" readonly id="ratio9" colIndex="${index+1}" value="" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="ratio9_Tot" value="" class="nonLaborReadOnly"/>
								</td>
							</tr>
							
							<tr align="center" bgcolor="#e3e3e3"
								class="financialSummHearderPanel">
								<td width="230" height="25"><div class="leftHeaderSpace">Travel cost per Onsite Hour</div>
								</td>
								<c:forEach var="index" begin="0" end="${loopLength}">
									<td align="center"  id="">
									<input type="text" readonly id="ratio10" colIndex="${index+1}" value="" class="nonLaborReadOnly"/>
									</td>
								</c:forEach>
								<td width="100" height="25"><input type="text" readonly id="ratio10_Tot" value="" class="nonLaborReadOnly"/>
								</td>
							</tr> 
							
							
</table>
</c:if>								

<br/>

