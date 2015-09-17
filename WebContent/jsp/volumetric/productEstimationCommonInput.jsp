<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="acdnmenu"
	style="width: 1112px; background-color: White; border: medium none; margin-top: 5px;">
	<ul>
		<li><div>Common Inputs</div>
			<ul>
				<li><form:form id="productEstimationInputForm"
						action="./productEstimation" method="post"
						modelAttribute="inputForm">
						<div style="width: 35%;">
							<label class="tdLabel">Steady State Duration: </label> <input
								type="text" style="width: 10%;" class="inputtextTabsNotEditable"
								name="steadyStateDuration" id="steadyStateDuration"
								readonly="readonly" value="${steadyStateDuration}" /><label
								class="tdLabel"> months</label>
						</div>
	&nbsp;
	<div>
							<c:forEach var="parameter" items="${paramMap}">
								<c:set var="paramDesc" value="${fn:split(parameter.key,'__')}" />
								<label class="tdLabel">${paramDesc[1]}</label>
								<c:choose>
									<c:when test="${hasEditSolAccess}">
										<select name="param_${paramDesc[0]}"
											id="param_${paramDesc[0]}" class="tdLabel">
									</c:when>
									<c:otherwise>
										<select name="param_${paramDesc[0]}"
											id="param_${paramDesc[0]}" class="tdLabel"
											disabled="disabled">
									</c:otherwise>
								</c:choose>

								<c:forEach var="item" items="${parameter.value}">
									<c:set var="paramName" value="${fn:split(item.key,'__')}" />
									<c:choose>
										<c:when test="${paramName[1] == 'selected'}">
											<option value="${item.value}" selected="${paramName[1]}"
												calculationFactor="${paramName[3]}" selectedKey="${paramName[2]}">${paramName[0]}</option>
										</c:when>
										<c:otherwise>
											<option value="${item.value}"
												calculationFactor="${paramName[2]}" selectedKey="">${paramName[0]}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</select>
							</c:forEach>
						</div>
						<br>
					</form:form></li>
			</ul>
	</ul>
</div>