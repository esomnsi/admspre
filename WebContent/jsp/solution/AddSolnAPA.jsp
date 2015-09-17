<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

	<tr class="oppscoperow" rowindex="${solnAPANumber}" rowid="${opsId}" ssid="${oppScope.serviceScope.serviceScopeId}">
									<td class="tdInputBox"><input type="text" 
									class="textBoxPortfolio_big" 
									name="solutionAPAList[${solnAPANumber}].businessFunction" 
									value=""></input>
										
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${solnAPANumber}].applicationName"
										value=""></td>
									<td class="tdInputBox">
										<input type="text" onkeypress="return isDecimalField(this,event);return false;"
										class="textBoxPortfolio_small" name="solutionAPAList[${solnAPANumber}].noUsers" value=""></input>
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${solnAPANumber}].platform" value="">
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${solnAPANumber}].databasenm" value="">
									</td>
									<td class="tdInputBox"><input type="text"
										class="textBoxPortfolio_big"
										name="solutionAPAList[${solnAPANumber}].primarySkill" value="">
									</td>
									<td><input type="text" class="textBoxPortfolio_big"
										name="solutionAPAList[${solnAPANumber}].secondarySkill" value="">
									</td>
									<td class="tdInputBox">
<input class="businessCriticalilty" name="solutionAPAList[${solnAPANumber}].businessCriticalilty" type="hidden" value=""></input>
									<select	class="businessCriticaliltySelect jumpMenuPortfolio_small"
										onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="1">Low</option>
											<option value="2">Medium</option>
											<option value="3">High</option>
									</select>
									</td>
									<td class="textBoxMedium2">
<input class="levelofDocuments" name="solutionAPAList[${solnAPANumber}].levelofDocuments" type="hidden" value=""></input>									
									<select class="levelofDocumentsSelect jumpMenuPortfolio_small"
										onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="3">Low</option>
											<option value="2">Medium</option>
											<option value="1">High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="stability" name="solutionAPAList[${solnAPANumber}].stability" type="hidden" value=""></input>										
									<select onchange="selectedValue(this);" class="stabilitySelect jumpMenuPortfolio_small">
											<option value="0">--Select--</option>
											<option value="3">Low</option>
											<option value="2">Medium</option>
											<option value="1">High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="longivity" name="solutionAPAList[${solnAPANumber}].longivity" type="hidden" value=""></input>	
									<select class="longivitySelect jumpMenuPortfolio_small" onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="3">Low</option>
											<option value="2">Medium</option>
											<option value="1">High</option>
									</select></td>
									<td class="textBoxMedium2">
<input class="complexity" name="solutionAPAList[${solnAPANumber}].complexity" type="hidden" value=""></input>									
									<select onchange="selectedValue(this);" class="complexity jumpMenuPortfolio_small">
											<option value="0">--Select--</option>
											<option value="1">Low</option>
											<option value="2">Medium</option>
											<option value="3">High</option>
									</select></td>
									<td id="sharePercentage" class="textBoxMedium2">
<input class="percentageShare" name="solutionAPAList[${solnAPANumber}].percentageShare" type="hidden" value=""></input>										
									<select	class="percentageShare jumpMenuPortfolio_small" onblur="calScore(this);"
										onchange="selectedValue(this);">
											<option value="0">--Select--</option>
											<option value="5">5</option>
											<option value="10">10</option>
											<option value="15">15</option>
											<option value="20">20</option>
											<option value="25">25</option>
											<option value="30">30</option>
											<option value="35">35</option>
											<option value="40">40</option>
											<option value="45">45</option>
											<option value="50">50</option>
											<option value="55">55</option>
											<option value="60">60</option>
											<option value="65">65</option>
											<option value="70">70</option>
											<option value="75">75</option>
											<option value="80">80</option>
											<option value="85">85</option>
											<option value="90">90</option>
											<option value="95">95</option>
											<option value="100">100</option>
									</select>
									</td>
									<td class="tdInputBox">
										<input type="text" readonly class="computedRiskFactor textBoxPortfolio_small"
										name="solutionAPAList[${solnAPANumber}].computedRiskFactor" value=""/>
									</td>
									<td class="tdInputBox"><input class="computedRiskExposure textBoxSmall" onchange="readTextValue(this);"
										type="hidden" name="solutionAPAList[${solnAPANumber}].computedRiskExposure"
										value=""/>
									<input type="text" readonly name="riskExposureSelected"	class="textBoxPortfolio_small riskExposureSelected"/>									
										<input name="solutionAPAList[${solnAPANumber}].solution.solutionId"
										type="hidden" class="textBoxMedium"
										value="${apaDTO.solutionId}"></input>
										<input name="solutionAPAList[${solnAPANumber}].solutionApaid"
										type="hidden" class="textBoxMedium"
										value=""></input>
										<input name="solutionAPAList[${solnAPANumber}].opportunityScope.opportunityScopeId" 
										type="hidden" class="textBoxMedium" 
										value="${opsId}"></input>
									</td>
</tr>